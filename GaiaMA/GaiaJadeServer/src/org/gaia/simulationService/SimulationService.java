/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.simulationService;

import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.BaseService;
import jade.core.Filter;
import jade.core.HorizontalCommand;
import jade.core.IMTPException;
import jade.core.MainContainer;
import jade.core.Node;
import jade.core.Profile;
import jade.core.ProfileException;
import jade.core.Service;
import jade.core.ServiceException;
import jade.core.ServiceHelper;
import jade.core.VerticalCommand;
import java.util.Date;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.sensoring.ServiceActuator;
import org.gaia.simulationService.sensoring.ServiceActuatorManager;
import org.gaia.simulationService.sensoring.ServiceSensor;
import org.gaia.simulationService.sensoring.ServiceSensorManager;
import org.gaia.simulationService.transaction.EnvironmentManagerDescription;
import org.gaia.simulationService.transaction.EnvironmentNotification;
import org.gaia.simulationService.transaction.TransactionMap;

/**
 * This is the SimulationService, which provides a list of functionalities for
 * agent based simulations. These are namely:<br> - time synchronisation for all
 * involved (remote) container,<br> - methods to stop agents, that are using the
 * Actuator/Sensor relationship between agents and this service,<br> - a method
 * to pause a running simulation, <br> - methods to transport an
 * {@link EnvironmentModel} model to all connected agents in an asynchronous way
 * and<br> - methods to receive changes or notifications from single
 * {@link SimulationAgent}<br>
 *
 *
 */
public class SimulationService extends BaseService {

    public static final String NAME = SimulationServiceHelper.SERVICE_NAME;
    private AgentContainer myContainer;
    private MainContainer myMainContainer;
    private Filter incFilter;
    private Filter outFilter;
    private ServiceComponent localSlice;
    /**
     * --- Variables for the Time-Synchronisation -----------------------------
     */
    private long timeMeasureNext = 0;				// --- When was the MainContainerTime last measured
    private final long timeMeasureInterval = 1000 * 5; 		// --- measure every 5 seconds
    private final int timeMeasureCountMax = 100;			// --- How often the time-difference should be measured to build an average value?
    private long timeDiff2MainContainer = 0;		// --- Difference between this and the MainContainer-Time
    /**
     * --- The Agent who is the Manager / Controller of the Simulation --------
     */
    private EnvironmentManagerDescription environmentManagerDescription = null;
    /**
     * --- Agents which have registered as display agents for the environment -
     */
    private final Vector<AID> environmentDisplayAgents = new Vector<>();
    /**
     * --- The List of Agents, which are registered to this service -----------
     */
    private final Hashtable<String, AID> agentList = new Hashtable<>();
    /**
     * --- Pause the current simulation ---------------------------------------
     */
    private boolean pauseSimulation = false;
    /**
     * --- The TransactionMap of the simulation -------------------------------
     */
    private final TransactionMap transactionMap = new TransactionMap();
    /**
     * --- The current EnvironmentModel ---------------------------------------
     */
    private EnvironmentModel environmentModel = null;
    /**
     * --- The Actuator for this Service, which can inform registered Agents
     * about changes in the Simulation e.g. 'stepTimeModel' --------
     */
    private final ServiceActuator localServiceActuator = new ServiceActuator();
    private final ServiceActuatorManager localServiceActuator4Manager = new ServiceActuatorManager();
    /**
     * --- How should an Agent be notified about Environment-Changes? ---------
     */
    private boolean stepSimulationAsynchronous = true;
    /**
     * --- The next EnvironmentObject-Instance in parts (answers of agents) ---
     */
    private int environmentInstanceNextPartsExpected = 0;
    private Hashtable<AID, Object> environmentInstanceNextParts = new Hashtable<>();
    private Hashtable<AID, Object> environmentInstanceNextPartsLocal = new Hashtable<>();
    private static final Logger logger = Logger.getLogger(SimulationService.class);


    /* (non-Javadoc)
     * @see jade.core.BaseService#init(jade.core.AgentContainer, jade.core.Profile)
     */
    @Override
    public void init(AgentContainer ac, Profile p) throws ProfileException {

        super.init(ac, p);
        myContainer = ac;
        myMainContainer = ac.getMain();
        // --- Create filters -----------------------------
        outFilter = new CommandOutgoingFilter();
        incFilter = new CommandIncomingFilter();
        // --- Create local slice -------------------------
        localSlice = new ServiceComponent();

        if (myContainer != null) {
            logger.info("Starting SimulationService: My-Container: " + myContainer.toString());
        }
        if (myMainContainer != null) {
            logger.info("Main-Container: " + myMainContainer.toString());
        }
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#boot(jade.core.Profile)
     */

    @Override
    public void boot(Profile p) throws ServiceException {
    }
    /* (non-Javadoc)
     * @see jade.core.Service#getName()
     */

    @Override
    public String getName() {
        return NAME;
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getHelper(jade.core.Agent)
     */

    @Override
    public ServiceHelper getHelper(Agent ag) {
        return new SimulationServiceImpl();
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getCommandFilter(boolean)
     */

    @Override
    public Filter getCommandFilter(boolean direction) {
        if (direction == Filter.INCOMING) {
            return incFilter;
        } else {
            return outFilter;
        }
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getHorizontalInterface()
     */

    @Override
    public Class<?> getHorizontalInterface() {
        return SimulationServiceSlice.class;
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getLocalSlice()
     */

    @Override
    public Service.Slice getLocalSlice() {
        return localSlice;
    }

    // ---- Inner-Class 'AgentTimeImpl' ---- Start ------------------
    /**
     * Sub-Class to provide interaction between Agents and this Service.
     *
     */
    public class SimulationServiceImpl implements SimulationServiceHelper {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = 5741448121178289099L;

        /* (non-Javadoc)
         * @see jade.core.ServiceHelper#init(jade.core.Agent)
         */
        @Override
        public void init(Agent ag) {
            // --- Store the Agent in the agentList -----------------
            agentList.put(ag.getName(), ag.getAID());
        }

        // ----------------------------------------------------------
        // --- Methods for the synchronised time --------------------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getSynchTimeDifferenceMillis()
         */
        @Override
        public long getSynchTimeDifferenceMillis() throws ServiceException {
            return timeDiff2MainContainer;
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getSynchTimeMillis()
         */

        @Override
        public long getSynchTimeMillis() throws ServiceException {
            if (myMainContainer == null) {
                setMainContainerTimeLocally();
            }
            return System.currentTimeMillis() + timeDiff2MainContainer;
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getSynchTimeDate()
         */

        @Override
        public Date getSynchTimeDate() throws ServiceException {
            return new Date(this.getSynchTimeMillis());
        }

        // ----------------------------------------------------------
        // --- Methods to work on agents ----------------------------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setAgentMigration(java.util.Vector)
         */
        @Override
        public void setAgentMigration(Vector<AID_Container> transferAgents) throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastAgentMigration(transferAgents, slices);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#stopSimulationAgents()
         */

        @Override
        public void stopSimulationAgents() throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastStopSimulationAgents(slices);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setPauseSimulation(boolean)
         */

        @Override
        public void setPauseSimulation(boolean pause) throws ServiceException {
            // --- block or unblock the simulation ------------
            Service.Slice[] slices = getAllSlices();
            broadcastPauseSimulation(pause, slices);
            if (pause == false) {
                // --- Reset next parts of the environement ---
                this.resetEnvironmentInstanceNextParts();
                // --- Restart simulation  --------------------
                this.stepSimulation(environmentModel, environmentInstanceNextPartsExpected);
            }
        }

        // ----------------------------------------------------------
        // --- Methods for the Manager-Agent ------------------------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setManagerAgent(jade.core.AID)
         */
        @Override
        public void setManagerAgent(AID agentAddress) throws ServiceException {

            EnvironmentManagerDescription envManager = new EnvironmentManagerDescription(agentAddress, myContainer.here());
            Service.Slice[] slices = getAllSlices();
            broadcastManagerAgent(envManager, slices);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getManagerAgent()
         */

        @Override
        public AID getManagerAgent() throws ServiceException {
            if (environmentManagerDescription == null) {
                return null;
            } else {
                return environmentManagerDescription.getAID();
            }
        }

        // ----------------------------------------------------------
        // --- Register, unregister or notify Agents-Sensors --------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#sensorPlugIn(agentgui.simulationService.sensoring.ServiceSensor)
         */
        @Override
        public void sensorPlugIn(ServiceSensor sensor) throws ServiceException {
            localServiceActuator.plugIn(sensor);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#sensorPlugIn(agentgui.simulationService.sensoring.ServiceSensor, boolean)
         */

        @Override
        public void sensorPlugIn(ServiceSensor sensor, boolean pasive) throws ServiceException {
            localServiceActuator.plugInPassive(sensor);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#sensorPlugOut(agentgui.simulationService.sensoring.ServiceSensor)
         */

        @Override
        public void sensorPlugOut(ServiceSensor sensor) throws ServiceException {
            localServiceActuator.plugOut(sensor);
        }

        // ----------------------------------------------------------
        // --- Register, unregister or notify Manager-Sensors -------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#sensorPlugIn4Manager(agentgui.simulationService.sensoring.ServiceSensorManager)
         */
        @Override
        public void sensorPlugIn4Manager(ServiceSensorManager sensor) throws ServiceException {
            localServiceActuator4Manager.plugIn(sensor);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#sensorPlugOut4Manager(agentgui.simulationService.sensoring.ServiceSensorManager)
         */

        @Override
        public void sensorPlugOut4Manager(ServiceSensorManager sensor) throws ServiceException {
            localServiceActuator4Manager.plugOut(sensor);
        }

        // ----------------------------------------------------------
        // --- Methods for the Simulation ---------------------------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getStepSimulationAsynchronous()
         */
        @Override
        public boolean getStepSimulationAsynchronous() throws ServiceException {
            return stepSimulationAsynchronous;
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setStepSimulationAsynchronous(boolean)
         */

        @Override
        public void setStepSimulationAsynchronous(boolean stepAsynchronous) throws ServiceException {
            stepSimulationAsynchronous = stepAsynchronous;
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#stepSimulation(agentgui.simulationService.environment.EnvironmentModel, int)
         */

        @Override
        public void stepSimulation(EnvironmentModel envModel, int answersExpected) throws ServiceException {
            this.stepSimulation(envModel, answersExpected, stepSimulationAsynchronous);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#stepSimulation(agentgui.simulationService.environment.EnvironmentModel, int, boolean)
         */

        @Override
        public void stepSimulation(EnvironmentModel envModel, int answersExpected, boolean aSynchron) throws ServiceException {
            if (!pauseSimulation) {
                // --- step forward the transaction map -------
                transactionMap.put(environmentModel);
                // --- next step for the simulation -----------
                Service.Slice[] slices = getAllSlices();
                if (answersExpected != environmentInstanceNextPartsExpected) {
                    broadcastAnswersExpected(answersExpected, slices);
                }
                broadcastStepSimulation(envModel, aSynchron, slices);
            }
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#notifySensorAgent(jade.core.AID, agentgui.simulationService.transaction.EnvironmentNotification)
         */

        @Override
        public boolean notifySensorAgent(AID agentAID, EnvironmentNotification notification) throws ServiceException {
            if (!pauseSimulation) {
                return false;
            } else {
                return broadcastNotifyAgent(agentAID, notification, getAllSlices());
            }
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#notifyManagerAgent(agentgui.simulationService.transaction.EnvironmentNotification)
         */

        @Override
        public boolean notifyManagerAgent(EnvironmentNotification notification) throws ServiceException {
            if (!pauseSimulation) {
                return false;
            } else {
                return broadcastNotifyManager(notification);
            }
        }

        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getEnvironmentModel()
         */
        @Override
        public EnvironmentModel getEnvironmentModel() throws ServiceException {
            return environmentModel;
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setEnvironmentModel(agentgui.simulationService.environment.EnvironmentModel)
         */

        @Override
        public void setEnvironmentModel(EnvironmentModel envModel) throws ServiceException {
            this.setEnvironmentModel(envModel, false);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setEnvironmentModel(agentgui.simulationService.environment.EnvironmentModel, boolean)
         */

        @Override
        public void setEnvironmentModel(EnvironmentModel envModel, boolean notifySensorAgents) throws ServiceException {
            broadcastSetEnvironmentModel(envModel, notifySensorAgents, getAllSlices());
        }
        // ----------------------------------------------------------
        // --- EnvironmentModel of the next simulation step ---------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#setEnvironmentInstanceNextPart(jade.core.AID, java.lang.Object)
         */

        @Override
        public void setEnvironmentInstanceNextPart(AID fromAgent, Object nextPart) throws ServiceException {

            synchronized (environmentInstanceNextPartsLocal) {
                // --- Put single changes into the local store until ----
                // --- the expected number of answers is not reached ----
                environmentInstanceNextPartsLocal.put(fromAgent, nextPart);

                // --- If the expected number of answers came back to ---
                // --- the service, broadcast it to every other node ----
                if (environmentInstanceNextPartsLocal.size() >= localServiceActuator.getNoOfSimulationAnswersExpected()) {
                    mainSetEnvironmentInstanceNextPart(environmentInstanceNextPartsLocal);
                    environmentInstanceNextPartsLocal = new Hashtable<>();
                }
            }

        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#getEnvironmentInstanceNextParts()
         */

        @Override
        public Hashtable<AID, Object> getEnvironmentInstanceNextParts() throws ServiceException {
            return mainGetEnvironmentInstanceNextParts();
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#resetEnvironmentInstanceNextParts()
         */

        @Override
        public void resetEnvironmentInstanceNextParts() throws ServiceException {
            mainResetEnvironmentInstanceNextParts();
        }

        // ----------------------------------------------------------
        // --- EnvironmentModel of the next simulation step ---------
		/* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#displayAgentRegister(jade.core.AID)
         */
        @Override
        public void displayAgentRegister(AID displayAgent) throws ServiceException {
            synchronized (environmentDisplayAgents) {
                environmentDisplayAgents.add(displayAgent);
            }
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#displayAgentUnregister(jade.core.AID)
         */

        @Override
        public void displayAgentUnregister(AID displayAgent) throws ServiceException {
            synchronized (environmentDisplayAgents) {
                Vector<AID> displayAgents = new Vector<>(environmentDisplayAgents);
                for (AID aid : displayAgents) {
                    if (aid.getLocalName().equals(displayAgent.getLocalName())) {
                        environmentDisplayAgents.remove(aid);
                    }
                }
            }
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#displayAgentSetEnvironmentModel(agentgui.simulationService.environment.EnvironmentModel)
         */

        @Override
        public void displayAgentSetEnvironmentModel(EnvironmentModel envModel) throws ServiceException {
            broadcastDisplayAgentSetEnvironmentModel(envModel);
        }
        /* (non-Javadoc)
         * @see agentgui.simulationService.SimulationServiceHelper#displayAgentNotification(agentgui.simulationService.transaction.EnvironmentNotification)
         */

        @Override
        public void displayAgentNotification(EnvironmentNotification notification) throws ServiceException {
            broadcastDisplayAgentNotification(notification);
        }
    }
    // --------------------------------------------------------------
    // ---- Inner-Class 'AgentTimeImpl' ---- End --------------------
    // --------------------------------------------------------------

    /**
     * Broadcast the EnvironmentManagerDescription of the Simulation-Manager
     * Agent to all Slices.
     *
     * @param envManager EnvironmentManagerDescription
     * @param slices the array of slices
     * @throws ServiceException the service exception
     * @see EnvironmentManagerDescription
     */
    private void broadcastManagerAgent(EnvironmentManagerDescription envManager, Service.Slice[] slices) throws ServiceException {
        logger.debug("Sending current TimeModel!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Sending current TimeModel to " + sliceName);
                slice.setManagerAgent(envManager);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error propagating current TimeModel to slice  " + sliceName, t);
            }
        }
    }

    /**
     * Sends the local next parts of the environment-model to the
     * Main-Container.
     *
     * @param nextPartsLocal the Hashtable of local environment changes, coming
     * from different agents
     * @throws ServiceException the service exception
     */
    private void mainSetEnvironmentInstanceNextPart(Hashtable<AID, Object> nextPartsLocal) throws ServiceException {
        logger.info("Sending agent-answer of environment-change to Main-Container!");
        String sliceName = null;
        try {
            SimulationServiceSlice slice = (SimulationServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.info("Sending agent-answer of environment-change to " + sliceName);
            slice.setEnvironmentInstanceNextPart(environmentInstanceNextPartsLocal);
        } catch (ServiceException | IMTPException t) {
            logger.warn("Error while sending agent-answer of environment-change to slice  " + sliceName, t);
        }
    }

    /**
     * This method returns the complete environment-model-changes from the
     * Main-Container.
     *
     * @return the hashtable
     * @throws ServiceException the service exception
     */
    private Hashtable<AID, Object> mainGetEnvironmentInstanceNextParts() throws ServiceException {
        logger.info("Try to get new environment-part from Main-Container!");
        String sliceName = null;
        try {
            SimulationServiceSlice slice = (SimulationServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.info("Try to get new environment-parts from " + sliceName);
            return slice.getEnvironmentInstanceNextParts();
        } catch (ServiceException | IMTPException t) {
            logger.warn("Error while trying to get new environment-parts from slice  " + sliceName, t);
        }
        return null;
    }

    /**
     * This method resets the hash with the single environment-model-changes.
     *
     * @throws ServiceException the service exception
     */
    private void mainResetEnvironmentInstanceNextParts() throws ServiceException {
        logger.info("Sending reset of environment-change-hash to Main-Container!");
        String sliceName = null;
        try {
            SimulationServiceSlice slice = (SimulationServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.info("Sending reset of environment-change-hash to " + sliceName);
            slice.resetEnvironmentInstanceNextParts();
        } catch (ServiceException | IMTPException t) {
            logger.warn("Error while sending reset of environment-change-hash to slice  " + sliceName, t);
        }
    }

    /**
     * Broadcasts the current EnvironmentModel to all slices.
     *
     * @param envModel the EnvironmentModel
     * @param notifySensorAgents true, if the sensor agents should be also
     * notified
     * @param slices the slices
     * @throws ServiceException the service exception
     * @see EnvironmentModel
     */
    private void broadcastSetEnvironmentModel(EnvironmentModel envModel, boolean notifySensorAgents, Service.Slice[] slices) throws ServiceException {
        logger.info("Sending new EnvironmentModel!");
        for (Slice slice1 : slices) {
            SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
            String sliceName = slice.getNode().getName();
            logger.info("Sending new EnvironmentModel to " + sliceName);
            envModel = this.setEnvironmentModel2Slice(slice, envModel, notifySensorAgents);
        }
    }

    /**
     * Broadcasts to all agents that the simulation steps forward by using their
     * {@link ServiceSensor} and putting the new {@link EnvironmentModel} in
     * there.
     *
     * @param envModel the EnvironmentModel
     * @param aSynchron true, if asynchronous
     * @param slices the slices
     * @throws ServiceException the service exception
     * @see EnvironmentModel
     */
    private void broadcastStepSimulation(EnvironmentModel envModel, boolean aSynchron, Service.Slice[] slices) throws ServiceException {
        logger.info("Sending new EnvironmentModel + step simulation!");
        for (Slice slice1 : slices) {
            SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
            String sliceName = slice.getNode().getName();
            logger.info("Sending new EnvironmentModel + step simulation to " + sliceName);
            try {
                slice.stepSimulation(envModel, aSynchron);
            } catch (IMTPException err) {
                logger.warn("Error while sending the new EnvironmentModel + step simulation to slice " + sliceName, err);
            }
        }
    }

    /**
     * Broadcast the number of agent answers that are expected for single
     * simulation step.
     *
     * @param answersExpected the answers expected
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastAnswersExpected(int answersExpected, Service.Slice[] slices) throws ServiceException {
        logger.info("Sending number of expected agent answers!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Sending number of expected agent answers to " + sliceName);
                slice.setAnswersExpected(answersExpected);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while sending number of expected agent answers to slice " + sliceName, t);
            }
        }
    }

    /**
     * Sends the current set of agent answers to the manager agent of the
     * environment.
     *
     * @param agentAnswers the agent answers
     * @throws ServiceException the service exception
     */
    private void broadcastNotifyManagerPutAgentAnswers(final Hashtable<AID, Object> agentAnswers) throws ServiceException {

        Service.Slice[] slices = getAllSlices();
        logger.info("Sending agent answers to manager!");
        for (Slice slice1 : slices) {
            SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
            String sliceName = slice.getNode().getName();
            if (sliceName.equals(environmentManagerDescription.getLocation().getName()) == true) {
                try {
                    logger.info("Sending agent answers to manager at lice " + sliceName);
                    slice.notifyManagerPutAgentAnswers(agentAnswers);
                } catch (IMTPException t) {
                    logger.warn("Error while sending agent answers to slice " + sliceName, t);
                }
            }
        }
    }

    /**
     * Broadcast a single notification object to a specific agent by using its
     * ServiceSensor.
     *
     * @param agentAID the agent aid
     * @param notification the notification
     * @param slices the slices
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    private boolean broadcastNotifyAgent(AID agentAID, EnvironmentNotification notification, Service.Slice[] slices) throws ServiceException {
        logger.info("Sending notfication to agent '" + agentAID.getLocalName() + "'!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Try sending notfication to agent '" + agentAID.getLocalName() + "' at " + sliceName + "!");
                boolean notified = slice.notifyAgent(agentAID, notification);
                if (notified == true) {
                    return notified;
                }
            } catch (IMTPException | ServiceException t) {
                logger.warn("Error while sending a notification to agent '" + agentAID.getLocalName() + "' at slice " + sliceName, t);
            }
        }
        return false;
    }

    /**
     * Sends a notification to the manager agent of the environment.
     *
     * @param notification the notification
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    private boolean broadcastNotifyManager(EnvironmentNotification notification) throws ServiceException {

        Service.Slice[] slices = getAllSlices();
        logger.info("Sending notification to manager!");
        for (Slice slice1 : slices) {
            SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
            String sliceName = slice.getNode().getName();
            if (sliceName.equals(environmentManagerDescription.getLocation().getName()) == true) {
                try {
                    logger.info("Sending notification to manager at lice " + sliceName);
                    return slice.notifyManager(notification);
                } catch (IMTPException t) {
                    logger.info("Error while sending notification to Manager at slice " + sliceName, t);
                }
            }
        }
        return false;
    }

    /**
     * This method will broadcast that the current simulation should pause or
     * not.
     *
     * @param pauseSimulation the pause simulation
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastPauseSimulation(boolean pauseSimulation, Service.Slice[] slices) throws ServiceException {
        logger.info("Broadcasting 'pause simulation'-message!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Try sending 'pause simulation'-message to " + sliceName + "!");
                slice.setPauseSimulation(pauseSimulation);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while sending 'pause simulation'-message to slice " + sliceName, t);
            }
        }
    }

    /**
     * Broadcast the new locations to the agents.
     *
     * @param transferAgents the agents to migrate
     * @param slices the slices
     * @throws ServiceException the service exception
     * @see AID_Container
     */
    private void broadcastAgentMigration(Vector<AID_Container> transferAgents, Service.Slice[] slices) throws ServiceException {
        logger.info("Sending migration notification to agents!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Sending migration notification to agents at " + sliceName);
                slice.setAgentMigration(transferAgents);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while sending migration notification to agents at slice " + sliceName, t);
            }
        }
    }

    /**
     * Stops the simulation by invoking the doDelete method at all agents which
     * are extending the class
     * 'agentgui.simulationService.agents.SimulationAgent' and which are
     * 'connected to a service actuator
     *
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastStopSimulationAgents(Service.Slice[] slices) throws ServiceException {
        logger.info("Try to stop simulation-agents!");
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Try to stop simulation-agents on " + sliceName);
                slice.stopSimulationAgents();
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while try to get Location-Object from " + sliceName, t);
            }
        }
    }

    /**
     * Sets a new EnvironmentModel to all registered DisplayAgents.
     *
     * @param notification the notification
     * @throws ServiceException the service exception
     */
    private void broadcastDisplayAgentSetEnvironmentModel(EnvironmentModel envModel) throws ServiceException {
        logger.info("Sending EnvironmentModel to DisplayAgents!");
        Service.Slice[] slices = getAllSlices();
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Sending EnvironmentModel to DisplayAgents over " + sliceName);
                slice.displayAgentSetEnvironmentModel(envModel);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while sending EnvironmentModel to DisplayAgents over slice  " + sliceName, t);
            }
        }
    }

    /**
     * Sends a new Notification to all registered DisplayAgents.
     *
     * @param notification the notification
     * @throws ServiceException the service exception
     */
    private void broadcastDisplayAgentNotification(EnvironmentNotification notification) throws ServiceException {
        logger.info("Sending Notification to DisplayAgents!");
        Service.Slice[] slices = getAllSlices();
        for (Slice slice1 : slices) {
            String sliceName = null;
            try {
                SimulationServiceSlice slice = (SimulationServiceSlice) slice1;
                sliceName = slice.getNode().getName();
                logger.info("Sending Notification to DisplayAgents over " + sliceName);
                slice.displayAgentNotification(notification);
            } catch (ServiceException | IMTPException t) {
                logger.warn("Error while sending Notification to DisplayAgents over slice  " + sliceName, t);
            }
        }
    }

    // --------------------------------------------------------------
    // ---- Inner-Class 'ServiceComponent' ---- Start ---------------
    // --------------------------------------------------------------
    /**
     * Inner class ServiceComponent. Will receive Commands, which are coming
     * from the SimulationServiceProxy
     */
    private class ServiceComponent implements Service.Slice {

        private static final long serialVersionUID = 1776886375724997808L;

        /* (non-Javadoc)
         * @see jade.core.Service.Slice#getService()
         */
        @Override
        public Service getService() {
            return SimulationService.this;
        }
        /* (non-Javadoc)
         * @see jade.core.Service.Slice#getNode()
         */

        @Override
        public Node getNode() throws ServiceException {
            try {
                return SimulationService.this.getLocalNode();
            } catch (IMTPException imtpe) {
                throw new ServiceException("Error retrieving local node", imtpe);
            }
        }
        /* (non-Javadoc)
         * @see jade.core.Service.Slice#serve(jade.core.HorizontalCommand)
         */

        @Override
        public VerticalCommand serve(HorizontalCommand cmd) {

            try {
                if (cmd == null) {
                    return null;
                }

                String cmdName = cmd.getName();
                Object[] params = cmd.getParams();
                switch (cmdName) {
                    case SimulationServiceSlice.SERVICE_SYNCH_GET_REMOTE_TIME:
                        logger.debug("Answering Remote-Time-Request");
                        cmd.setReturnValue(getLocalTime());
                        break;
                    case SimulationServiceSlice.SERVICE_SYNCH_SET_TIME_DIFF:
                        long timeDifference = (Long) params[0];
                        logger.debug("Setting Time-Difference to Main-Platform");
                        setPlatformTimeDiff(timeDifference);
                        break;
                    case SimulationServiceSlice.SIM_SET_MANAGER_AGENT:
                        EnvironmentManagerDescription envManager = (EnvironmentManagerDescription) params[0];
                        logger.debug("Received AID of the Agent-Manager");
                        setManagerAgent(envManager);
                        break;
                    case SimulationServiceSlice.SIM_SET_ENVIRONMENT_MODEL: {
                        EnvironmentModel envModel = (EnvironmentModel) params[0];
                        boolean notifySensorAgents = (Boolean) params[1];
                        logger.debug("Received new environment model");
                        setEnvironmentModel(envModel, notifySensorAgents);
                        break;
                    }
                    case SimulationServiceSlice.SIM_STEP_SIMULATION: {
                        EnvironmentModel envModel = (EnvironmentModel) params[0];
                        boolean aSynchron = (Boolean) params[1];
                        logger.debug("Received 'Step Simulation'");
                        stepSimulation(envModel, aSynchron);
                        break;
                    }
                    case SimulationServiceSlice.SIM_SET_ANSWERS_EXPECTED:
                        int answersExpected = (Integer) params[0];
                        logger.debug("Received number of expected agent answers'");
                        setEnvironmentInstanceNextPartsExpected(answersExpected);
                        break;
                    case SimulationServiceSlice.SIM_PAUSE_SIMULATION:
                        boolean pause = (Boolean) params[0];
                        logger.debug("Getting 'pause-simulation'-message");
                        setPauseSimulation(pause);
                        break;
                    case SimulationServiceSlice.SIM_NOTIFY_AGENT: {
                        AID agentAID = (AID) params[0];
                        EnvironmentNotification notification = (EnvironmentNotification) params[1];
                        logger.debug("Received 'Notify Agent for '" + agentAID.getLocalName() + StringUtils.QUOTE);
                        cmd.setReturnValue(notifySensorAgent(agentAID, notification));
                        break;
                    }
                    case SimulationServiceSlice.SIM_NOTIFY_MANAGER: {
                        EnvironmentNotification notification = (EnvironmentNotification) params[0];
                        logger.debug("Getting parts for the next environment model");
                        cmd.setReturnValue(notifyManagerAgent(notification));
                        break;
                    }
                    case SimulationServiceSlice.SIM_NOTIFY_MANAGER_PUT_AGENT_ANSWERS:
                        @SuppressWarnings("unchecked") Hashtable<AID, Object> allAgentAnswers = (Hashtable<AID, Object>) params[0];
                        logger.debug("Getting parts for the next environment model");
                        notifyManagerAgentPutAgentAnswers(allAgentAnswers);
                        break;
                    case SimulationServiceSlice.SIM_SET_ENVIRONMENT_NEXT_PART:
                        @SuppressWarnings("unchecked") Hashtable<AID, Object> nextPartsLocal = (Hashtable<AID, Object>) params[0];
                        logger.debug("Getting parts for the next environment model");
                        setEnvironmentInstanceNextPart(nextPartsLocal);
                        break;
                    case SimulationServiceSlice.SIM_GET_ENVIRONMENT_NEXT_PARTS:
                        logger.debug("Answering request for the next parts of the environment-model");
                        cmd.setReturnValue(getEnvironmentInstanceNextParts());
                        break;
                    case SimulationServiceSlice.SIM_RESET_ENVIRONMENT_NEXT_PARTS:
                        logger.debug("Reseting next parts of the environment-model");
                        resetEnvironmentInstanceNextParts();
                        break;
                    case SimulationServiceSlice.SERVICE_STOP_SIMULATION_AGENTS:
                        logger.debug("Stoping simulation agents in this container");
                        stopSimulationAgents();
                        break;
                    case SimulationServiceSlice.SERVICE_SET_AGENT_MIGRATION:
                        @SuppressWarnings("unchecked") Vector<AID_Container> transferAgents = (Vector<AID_Container>) params[0];
                        logger.debug("Getting info about agent migration");
                        setAgentMigration(transferAgents);
                        break;
                    case SimulationServiceSlice.SERVICE_DISPLAY_AGENT_SET_ENVIRONMENT_MODEL: {
                        logger.debug("Getting EnvironmentModel for DisplayAgents");
                        EnvironmentModel envModel = (EnvironmentModel) params[0];
                        displayAgentSetEnvironmentModel(envModel);
                        break;
                    }
                    case SimulationServiceSlice.SERVICE_DISPLAY_AGENT_NOTIFICATION: {
                        logger.debug("Getting EnvironmentModel for DisplayAgents");
                        EnvironmentNotification notification = (EnvironmentNotification) params[0];
                        displayAgentNotification(notification);
                        break;
                    }
                    case LoadServiceSlice.SERVICE_GET_AID_LIST:
                        logger.debug("Answering request for the Agents in this container");
                        cmd.setReturnValue(getListOfAgents());
                        break;
                    case LoadServiceSlice.SERVICE_GET_AID_LIST_SENSOR:
                        logger.debug("Answering request for the Agents with sensors in this container");
                        cmd.setReturnValue(getListOfAgentsWithSensors());
                        break;
                }

            } catch (Throwable t) {
                cmd.setReturnValue(t);
            }
            return null;
        }

        // -----------------------------------------------------------------
        // --- The real functions for the Service Component --- Start ------
        // -----------------------------------------------------------------
        /**
         * Gets the local time.
         *
         * @return the local time
         */
        private long getLocalTime() {
            return System.currentTimeMillis();
        }

        /**
         * Sets the time difference compared to the Main-Container.
         *
         * @param timeDifference the time difference
         */
        private void setPlatformTimeDiff(long timeDifference) {
            timeDiff2MainContainer = timeDifference;
        }

        /**
         * Sets the EnvironmentManagerDescription of the manager agent.
         *
         * @param envManager the new manager agent
         */
        private void setManagerAgent(EnvironmentManagerDescription envManager) {
            environmentManagerDescription = envManager;
        }

        /**
         * Sets the environment model.
         *
         * @param newEnvironmentModel the new environment model
         * @param notifySensorAgents true, if the sensor agents should be also
         * notified
         */
        private void setEnvironmentModel(EnvironmentModel newEnvironmentModel, boolean notifySensorAgents) {
            environmentModel = newEnvironmentModel;
            if (notifySensorAgents) {
                localServiceActuator.notifySensors(environmentModel, stepSimulationAsynchronous);
            }
        }

        /**
         * Steps the simulation locally by putting the new EnvironmentModel to
         * the connected agents.
         *
         * @param newEnvironmentModel the new environment model
         * @param aSynchron true, if step is asynchronously
         */
        private void stepSimulation(EnvironmentModel newEnvironmentModel, boolean aSynchron) {
            environmentModel = newEnvironmentModel;
            localServiceActuator.notifySensors(newEnvironmentModel, aSynchron);
        }

        /**
         * Notifies the manager agent about the agent answers.
         *
         * @param allAgentAnswers the all agent answers
         */
        private void notifyManagerAgentPutAgentAnswers(Hashtable<AID, Object> allAgentAnswers) {
            localServiceActuator4Manager.putAgentAnswers(environmentManagerDescription.getAID(), allAgentAnswers, true);
        }

        /**
         * Sends a notification to the manager agent.
         *
         * @param notification the notification
         * @return true, if successful
         */
        private boolean notifyManagerAgent(EnvironmentNotification notification) {
            return localServiceActuator4Manager.notifyManager(environmentManagerDescription.getAID(), notification);
        }

        /**
         * Sends a notification to a specified sensor agent.
         *
         * @param agentAID the agent aid
         * @param notification the notification
         * @return true, if successful
         */
        private boolean notifySensorAgent(AID agentAID, EnvironmentNotification notification) {
            return localServiceActuator.notifySensorAgent(agentAID, notification);
        }

        /**
         * Sets the number of environment changes expected within one simulation
         * step.
         *
         * @param answersExpected the number of answers expected
         */
        private void setEnvironmentInstanceNextPartsExpected(int answersExpected) {
            environmentInstanceNextPartsExpected = answersExpected;
        }

        /**
         * Will receive the environment changes from a different (remote)
         * container. If the answers are complete, it will notify the manager
         * agent.
         *
         * @param nextPartsLocal the Hashtable of changes in the environment
         */
        private void setEnvironmentInstanceNextPart(Hashtable<AID, Object> nextPartsLocal) {
            environmentInstanceNextParts.putAll(nextPartsLocal);
            if (environmentInstanceNextParts.size() >= environmentInstanceNextPartsExpected) {
                // --- We are in the Main-Container and got the expected ---
                // --- number of answers for a single simulation step	 ---
                try {
                    broadcastNotifyManagerPutAgentAnswers(environmentInstanceNextParts);
                } catch (ServiceException e) {
                    logger.error(e);
                }
            }
        }

        /**
         * Provides the environment changes as a Hashtable<AID, Object>
         *
         * @return the environment changes for the next simulation step
         */
        private Hashtable<AID, Object> getEnvironmentInstanceNextParts() {
            return environmentInstanceNextParts;
        }

        /**
         * Will reset the local set of environment changes to a new and empty
         * Hashtable.
         */
        private void resetEnvironmentInstanceNextParts() {
            environmentInstanceNextParts = new Hashtable<>();
            environmentInstanceNextPartsLocal = new Hashtable<>();
        }

        /**
         * Stop simulation agents.
         */
        private void stopSimulationAgents() {
            localServiceActuator.notifySensorAgentsDoDelete();
        }

        /**
         * Sets the pause of the simulation.
         *
         * @param pause true, if the simulation should pause
         */
        private void setPauseSimulation(boolean pause) {
            pauseSimulation = pause;
        }

        /**
         * Sets the agent migration.
         *
         * @param transferAgents the new agent migration
         */
        private void setAgentMigration(Vector<AID_Container> transferAgents) {
            localServiceActuator.setMigration(transferAgents);
        }

        /**
         * Returns the list of agents running in this container.
         *
         * @return the list of agents
         */
        private AID[] getListOfAgents() {
            return myContainer.agentNames();
        }

        /**
         * Returns the list of agents, which are connected through the
         * {@link ServiceActuator}.
         *
         * @return the list of agents with sensors
         */
        private AID[] getListOfAgentsWithSensors() {
            return localServiceActuator.getSensorAgents();
        }

        /**
         * Sets a new EnvironmentModel to all registered DisplayAgents.
         *
         * @param notification the EnvironmentNotification
         */
        public void displayAgentSetEnvironmentModel(EnvironmentModel envModel) {
            synchronized (environmentDisplayAgents) {
                if (environmentDisplayAgents != null) {
                    for (AID aid : environmentDisplayAgents) {
                        ServiceSensor sensor = localServiceActuator.getSensor(aid);
                        sensor.putEnvironmentModel(envModel, stepSimulationAsynchronous);
                    }
                }
            }
        }

        /**
         * Sets a new Notification to all registered DisplayAgents.
         *
         * @param notification the EnvironmentNotification
         */
        public void displayAgentNotification(EnvironmentNotification notification) {
            synchronized (environmentDisplayAgents) {
                if (environmentDisplayAgents != null) {
                    for (AID aid : environmentDisplayAgents) {
                        ServiceSensor sensor = localServiceActuator.getSensor(aid);
                        sensor.notifyAgent(notification);
                    }
                }
            }
        }
        // -----------------------------------------------------------------
        // --- The real functions for the Service Component --- Stop -------
        // -----------------------------------------------------------------
    }
    // --------------------------------------------------------------
    // ---- Inner-Class 'ServiceComponent' ---- End -----------------
    // --------------------------------------------------------------

    // --------------------------------------------------------------
    // ---- Inner-Class 'CommandOutgoingFilter' ---- Start ----------
    // --------------------------------------------------------------
    /**
     * Inner class CommandOutgoingFilter.
     */
    private class CommandOutgoingFilter extends Filter {

        /**
         * Instantiates a new command outgoing filter.
         */
        public CommandOutgoingFilter() {
            super();
            //setPreferredPosition(2);  // Before the Messaging (encoding) filter and the security related ones
        }
        /* (non-Javadoc)
         * @see jade.core.Filter#accept(jade.core.VerticalCommand)
         */

        @Override
        public final boolean accept(VerticalCommand cmd) {
            return true;
        }
    }
    // --------------------------------------------------------------
    // ---- Inner-Class 'CommandOutgoingFilter' ---- End ------------
    // --------------------------------------------------------------

    // --------------------------------------------------------------
    // ---- Inner-Class 'CommandIncomingFilter' ---- Start ----------
    // --------------------------------------------------------------
    /**
     * Inner class CommandIncomingFilter.
     */
    private class CommandIncomingFilter extends Filter {

        /* (non-Javadoc)
         * @see jade.core.Filter#accept(jade.core.VerticalCommand)
         */
        @Override
        public boolean accept(VerticalCommand cmd) {

            if (cmd == null) {
                return true;
            }
            String cmdName = cmd.getName();

            if (myMainContainer != null) {
                if (cmdName.equals(Service.NEW_SLICE)) {
                    // --- If the new slice is a SimulationServiceSlice, notify it about the current state ---
                    handleNewSlice(cmd);
                }
            }
            // Never veto a Command
            return true;
        }
    }
    // --------------------------------------------------------------
    // ---- Inner-Class 'CommandIncomingFilter' ---- End ------------
    // --------------------------------------------------------------

    /**
     * If the new slice is a SimulationServiceSlice notify it about the current
     * state.
     *
     * @param cmd the VerticalCommand
     */
    private void handleNewSlice(VerticalCommand cmd) {

        if (cmd.getService().equals(NAME)) {
            // --- We ARE in the Main-Container !!! ----------------------------------------
            Object[] params = cmd.getParams();
            String newSliceName = (String) params[0];
            try {
                // --- Be sure to get the new (fresh) slice --> Bypass the service cache ---
                SimulationServiceSlice newSlice = (SimulationServiceSlice) getFreshSlice(newSliceName);
                // --- Set remote ManagerAgent, TimeModel,EnvironmentInstance --------------
                newSlice.setManagerAgent(environmentManagerDescription);
                // --- Set the current environment model to the new slice ------------------
                this.setEnvironmentModel2Slice(newSlice, environmentModel, false);
                // --- Synchronise the time ------------------------------------------------
                this.synchroniseTimeOfSlice(newSlice);

            } catch (ServiceException | IMTPException t) {
                logger.error("Error notifying new slice '" + newSliceName + "' about current SimulationService-State", t);
            }
        }
    }

    /**
     * Sets the environment model to the specified slice.
     *
     * @param simSlice the SimulationServiceSlice
     * @param envModel the EnvironmentModel
     * @param notifySensorAgents true, if the sensor agents should be also
     * notified
     *
     * @return the environment model
     * @throws ServiceException the service exception
     */
    private EnvironmentModel setEnvironmentModel2Slice(SimulationServiceSlice simSlice, EnvironmentModel envModel, boolean notifySensorAgents) throws ServiceException {

        try {
            simSlice.setEnvironmentModel(envModel, notifySensorAgents);
            return envModel;

        } catch (IMTPException err) {
            logger.error("Error while sending the new EnvironmentModel to slice " + simSlice.getNode().getName(), err);
        }
        return null;
    }

    /**
     * This method will synchronise the time between Main-Container and
     * Remote-Container. It is used only from a Main-Container.
     *
     * @param slice the slice
     */
    private void synchroniseTimeOfSlice(SimulationServiceSlice slice) {

        int countMax = timeMeasureCountMax;
        long locTime1Milli;
        long remTimeMilli;

        long measureTime1Nano;
        long measureTime2Nano;

        double timeDiffAccumulate = 0;

        try {

            for (int i = 0; i < countMax; i++) {
                // --- Measure local time and ask for the remote time ---
                locTime1Milli = System.currentTimeMillis();			// --- milliseconds ---
                measureTime1Nano = System.nanoTime();					// --- nanoseconds ---
                remTimeMilli = slice.getRemoteTime();				// --- milliseconds ---
                measureTime2Nano = System.nanoTime();					// --- nanoseconds ---

                timeDiffAccumulate += getContainerTimeDifference(locTime1Milli, remTimeMilli, measureTime1Nano, measureTime2Nano);

            }
            slice.setRemoteTimeDiff(Math.round(timeDiffAccumulate / countMax));

        } catch (IMTPException e) {
            logger.error(e);
        }
    }

    /**
     * This Method returns the time difference between this and the remote node
     * by using the local-time and the time to get the remote-time.
     *
     * @param locTime1Milli the local time in milliseconds
     * @param remTimeMilli the remote time in milliseconds
     * @param measureTime1Nano the time where the measurement STARTED in
     * nanoseconds
     * @param measureTime2Nano the time where the measurement STOPED in
     * nanoseconds
     * @return the container time difference
     */
    private double getContainerTimeDifference(long locTime1Milli, long remTimeMilli, long measureTime1Nano, long measureTime2Nano) {

        // --- Calculate the correction value of the remote time
        long measureTimeNanoCorrect = (measureTime2Nano - measureTime1Nano) / 2;
        double measureTimeMilliCorrect = measureTimeNanoCorrect * Math.pow(10, -6);
        // --- Correct the measured remote time -----------------
        double remTimeMilliCorrect = remTimeMilli - measureTimeMilliCorrect;
        // --- Calculate the time Difference between ------------
        // --- this and the remote platform 		 ------------
        return locTime1Milli - remTimeMilliCorrect;
    }

    /**
     * This method will synchronise the local time with the time in the
     * Main-Container.
     */
    private void setMainContainerTimeLocally() {

        int counter = timeMeasureCountMax;
        long locTime1Milli;
        long remTimeMilli;

        long locTime1Nano;
        long locTime2Nano;

        double remTimeMilliDiffAccumulate = 0;

        if (System.currentTimeMillis() >= timeMeasureNext) {
            // --- Balance the Main-Container time-differnece ------------
            timeMeasureNext = System.currentTimeMillis() + timeMeasureInterval;
            SimulationServiceSlice ssl;
            try {
                ssl = (SimulationServiceSlice) getSlice(MAIN_SLICE);

                for (int i = 0; i < counter; i++) {
                    // --- Measure local time and ask for the remote time ---
                    locTime1Milli = System.currentTimeMillis();
                    locTime1Nano = System.nanoTime();										// --- nano-seconds ---
                    remTimeMilli = ssl.getRemoteTime();
                    locTime2Nano = System.nanoTime();										// --- nano-seconds ---

                    remTimeMilliDiffAccumulate += getContainerTimeDifference(locTime1Milli, remTimeMilli, locTime1Nano, locTime2Nano);
                }
                timeDiff2MainContainer = Math.round(remTimeMilliDiffAccumulate / counter) * (-1);

            } catch (ServiceException | IMTPException e) {
                logger.error(e);
            }
        }
    }
}