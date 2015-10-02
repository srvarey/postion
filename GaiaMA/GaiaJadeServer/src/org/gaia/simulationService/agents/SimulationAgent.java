/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.simulationService.agents;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.sensoring.ServiceSensor;
import org.gaia.simulationService.sensoring.ServiceSensorInterface;
import org.gaia.simulationService.sensoring.ServiceSensorListener;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * This agent class can be used for simulations based on agents that are using
 *
 *
 */
public abstract class SimulationAgent extends Agent implements ServiceSensorInterface {

    private static final long serialVersionUID = 1782853782362543057L;
    private boolean passive = false;
    /**
     * The ServiceSensor of this agent.
     */
    protected ServiceSensor mySensor;
    /**
     * The current EnvironmentModel.
     */
    protected EnvironmentModel myEnvironmentModel;
    /**
     * The location, where the agent has to migrate to.
     */
    protected Location myNewLocation;
    private static final Logger logger = Logger.getLogger(SimulationAgent.class);
    private CyclicNotificationHandler notificationHandler = null;
    private Vector<EnvironmentNotification> notifications = new Vector<>();
    private Vector<ServiceSensorListener> simulationServiceListeners = null;

    /**
     * Instantiates a new simulation agent as an active agent.
     */
    public SimulationAgent() {
    }

    public SimulationAgent(Object[] args) {
    }

    /**
     * Instantiates a new simulation agent as a passive agent. The agent will
     * just listening to changes in the environment model but it is not
     * expected, that it will react on it. This is used for example for agents,
     * which are displaying the current environment.
     *
     * @param passive the passive
     */
    public SimulationAgent(boolean passive) {
        this.passive = passive;
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        super.setup();
        this.sensorPlugIn();
        this.addNotificationHandler();
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#beforeMove()
     */
    @Override
    protected void beforeMove() {
        super.beforeMove();
        this.removeNotificationHandler();
        this.sensorPlugOut();
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#afterMove()
     */
    @Override
    protected void afterMove() {
        this.myNewLocation = null;
        super.afterMove();
        this.sensorPlugIn();
        this.addNotificationHandler();
        this.checkAndActOnEnvironmentChanges();
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#beforeClone()
     */
    @Override
    protected void beforeClone() {
        super.beforeClone();
        this.removeNotificationHandler();
        this.sensorPlugOut();
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#afterClone()
     */
    @Override
    protected void afterClone() {
        super.afterClone();
        this.sensorPlugIn();
        this.checkAndActOnEnvironmentChanges();
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#takeDown()
     */
    @Override
    protected void takeDown() {
        super.takeDown();
        this.removeNotificationHandler();
        this.sensorPlugOut();
    }

    /**
     * This Method plugs IN the service sensor.
     */
    protected void sensorPlugIn() {
        // --- Start the ServiceSensor ------------------------------
        mySensor = new ServiceSensor(this);
        // --- Register the sensor to the SimulationService ---------
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            if (this.passive == true) {
                simHelper.sensorPlugIn(mySensor, true);
            } else {
                simHelper.sensorPlugIn(mySensor);
            }

        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    /**
     * This Method plugs OUT the service sensor.
     */
    protected void sensorPlugOut() {
        // --- plug-out the Sensor ----------------------------------
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            simHelper.sensorPlugOut(mySensor);
        } catch (ServiceException e) {
            logger.error(e);
        }
        mySensor = null;
    }

    /**
     * Grab the environment model from the simulation service.
     *
     * @return the current EnvironmentModel
     */
    protected EnvironmentModel getEnvironmentModelFromSimulationService() {
        EnvironmentModel envModel = null;
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            envModel = simHelper.getEnvironmentModel();
        } catch (ServiceException e) {
            System.err.println(getLocalName() + " - Error: Could not retrieve SimulationServiceHelper, shutting down!");
            this.doDelete();
        }
        return envModel;
    }

    /**
     * This Method checks if the environment changed in the meantime. If so, the
     * method 'onEnvironmentStimulus' will be fired
     */
    protected void checkAndActOnEnvironmentChanges() {
        // --- Has the EnvironmentModel changed? ----------------
        EnvironmentModel tmpEnvMode = this.getEnvironmentModelFromSimulationService();
        if (tmpEnvMode != null) {
            if (tmpEnvMode.equals(this.myEnvironmentModel) == false) {
                this.myEnvironmentModel = tmpEnvMode;
                this.onEnvironmentStimulusIntern();
            }
        }
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.agents.SimulationServiceListener#setMigration(jade.core.Location)
     */
    @Override
    public void setMigration(Location newLocation) {
        myNewLocation = newLocation;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.agents.SimulationServiceListener#setEnvironmentModel(agentgui.simulationService.environment.EnvironmentModel, boolean)
     */
    @Override
    public void setEnvironmentModel(EnvironmentModel envModel, boolean aSynchron) {
        myEnvironmentModel = envModel;
        if (aSynchron == true) {
            this.addBehaviour(new ServiceStimulus());
        } else {
            this.onEnvironmentStimulusIntern();
        }
    }

    /**
     * This Behaviour is used to stimulate the agent from the outside in a
     * asynchronous way.
     *
     */
    private class ServiceStimulus extends OneShotBehaviour {

        private static final long serialVersionUID = 1441989543791055996L;

        @Override
        public void action() {
            onEnvironmentStimulusIntern();
        }
    }

    /**
     * This method is internally called if a stimulus from the outside reached
     * this agent.
     */
    private void onEnvironmentStimulusIntern() {
        this.onEnvironmentStimulus();
        for (ServiceSensorListener listener : this.getSimulationServiceListeners()) {
            listener.onEnvironmentStimulus();
        }
    }

    /**
     * This method is called if a stimulus from the outside reached this agent.
     * It can be overwritten in the child class to act on environment changes.
     */
    protected void onEnvironmentStimulus() {
    }

    /**
     * This method sets the answer respectively the change of a single
     * simulation agent back to the central simulation manager.
     *
     * @param myNextState the next state of this agent in the next instance of
     * the environment model
     */
    protected void setMyStimulusAnswer(Object myNextState) {
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            simHelper.setEnvironmentInstanceNextPart(getAID(), myNextState);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    /**
     * This method can be used to transfer any kind of information to the
     * Manager of the current environment model.
     *
     * @param notification the notification
     * @return true, if successful
     */
    public boolean sendManagerNotification(Object notification) {
        boolean send = false;
        EnvironmentNotification myNotification = new EnvironmentNotification(this.getAID(), false, notification);
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            send = simHelper.notifyManagerAgent(myNotification);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return send;
    }

    /**
     * This method can be used to transfer any kind of information to one member
     * of the current environment model.
     *
     * @param receiverAID the AID of receiver agent
     * @param notification the notification
     * @return true, if successful
     */
    public boolean sendAgentNotification(AID receiverAID, Object notification) {
        boolean send = false;
        EnvironmentNotification myNotification = new EnvironmentNotification(this.getAID(), false, notification);
        try {
            SimulationServiceHelper simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            send = simHelper.notifySensorAgent(receiverAID, myNotification);
        } catch (ServiceException e) {
            logger.error(e);
        }
        return send;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.agents.SimulationServiceListener#setNotification(agentgui.simulationService.transaction.EnvironmentNotification)
     */
    public void setNotification(EnvironmentNotification notification) {
        // --- place the notification into the notification vector -------
        synchronized (this.notifications) {
            this.notifications.add(notification);
        }
        // --- restart the CyclicNotificationHandler ---------------------
        if (this.notificationHandler == null) {
            this.addNotificationHandler();
        } else {
            this.notificationHandler.restart();
        }

    }

    /**
     * This method adds the CyclicNotificationHandler to this agent.
     */
    private void addNotificationHandler() {
        if (this.notificationHandler == null) {
            this.notificationHandler = new CyclicNotificationHandler();
        }
        this.addBehaviour(this.notificationHandler);
    }

    /**
     * This method removes the CyclicNotificationHandler from this agent.
     */
    private void removeNotificationHandler() {
        if (this.notificationHandler != null) {
            this.removeBehaviour(this.notificationHandler);
            this.notificationHandler = null;
        }
    }

    /**
     * This CyclicBehaviour is used in order to act on the incoming
     * notifications.
     *
     */
    private class CyclicNotificationHandler extends CyclicBehaviour {

        private static final long serialVersionUID = 4638681927192305608L;

        /* (non-Javadoc)
         * @see jade.core.behaviours.Behaviour#action()
         */
        @Override
        public void action() {

            EnvironmentNotification notification = null;
            boolean removeFirstElement = false;
            boolean moveAsLastElement = false;

            // --- Get the first element and work on it ------------------
            if (notifications.size() != 0) {
                notification = notifications.get(0);
                notification = onEnvironmentNotificationIntern(notification);
                if (notification.getProcessingInstruction().isDelete()) {
                    removeFirstElement = true;
                    moveAsLastElement = false;

                } else if (notification.getProcessingInstruction().isBlock()) {
                    removeFirstElement = false;
                    moveAsLastElement = false;
                    this.block(notification.getProcessingInstruction().getBlockPeriod());

                }
                if (notification.getProcessingInstruction().isMoveLast()) {
                    removeFirstElement = false;
                    moveAsLastElement = true;
                }
            }

            // --- remove this element and control the notifications -----
            synchronized (notifications) {
                if (removeFirstElement == true) {
                    notifications.remove(0);
                }
                if (moveAsLastElement == true) {
                    if (notifications.size() > 1) {
                        notifications.remove(0);
                        notifications.add(notification);
                    } else {
                        this.block(notification.getProcessingInstruction().getBlockPeriod());
                    }
                }
                if (notification != null) {
                    notification.resetProcessingInstruction();
                }
                if (notifications.size() == 0) {
                    block();
                }
            }

        }
    }

    /**
     * This method will be executed if a ManagerNotification arrives this agent.
     *
     * @param notification the notification
     */
    private EnvironmentNotification onEnvironmentNotificationIntern(EnvironmentNotification notification) {
        notification = this.onEnvironmentNotification(notification);
        for (ServiceSensorListener listener : this.getSimulationServiceListeners()) {
            notification = listener.onEnvironmentNotification(notification);
        }
        return notification;
    }

    /**
     * This method will be executed if a ManagerNotification arrives this agent.
     *
     * @param notification the notification
     */
    protected EnvironmentNotification onEnvironmentNotification(EnvironmentNotification notification) {
        return notification;
    }

    /**
     * Returns the current simulation service listeners.
     *
     * @return the simulation service listeners
     */
    private Vector<ServiceSensorListener> getSimulationServiceListeners() {
        if (this.simulationServiceListeners == null) {
            this.simulationServiceListeners = new Vector<ServiceSensorListener>();
        }
        return this.simulationServiceListeners;
    }

    /**
     * Adds the simulation service listener.
     *
     * @param simulationServiceListener the simulation service listener
     */
    public void addSimulationServiceListener(ServiceSensorListener simulationServiceListener) {
        this.getSimulationServiceListeners().add(simulationServiceListener);
    }

    /**
     * Removes the simulation service listener.
     *
     * @param simulationServiceListener the simulation service listener
     */
    public void removeSimulationServiceListener(ServiceSensorListener simulationServiceListener) {
        this.getSimulationServiceListeners().remove(simulationServiceListener);
    }

    /**
     * Registers a service for the agency to the DFService of JADE.
     *
     * @param type the type
     * @param name the name
     */
    protected void registerDFService(String type, String name, String ownership) {
        DFAgentDescription agentDescription = createAgentDescription(type, name, ownership);
        try {
            DFService.register(this, agentDescription);
        } catch (FIPAException e) {
            logger.error(e);
        }
    }

    /**
     * Unregisters a service for the agency to the DFService of JADE.
     */
    protected void deregisterDFService() {
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            logger.error(e);
        }
    }

    /**
     * Returns the Agent description for the DF-Service of JADE
     *
     * @param type the service type
     * @param name AID address or name of the agent
     * @param ownership the ownership
     * @return the DFAgentDescription for the JADE DF
     */
    private DFAgentDescription createAgentDescription(String type, String name, String ownership) {
        DFAgentDescription agentDescription = new DFAgentDescription();
        agentDescription.setName(getAID());

        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(type);
        serviceDescription.setName(name);
        serviceDescription.setOwnership(ownership);
        agentDescription.addServices(serviceDescription);

        return agentDescription;
    }

    /**
     * Find and returns agents by a service type.
     *
     * @param serviceType the service type
     * @return the dF agent description[]
     */
    protected DFAgentDescription[] findAgentsByServiceType(String serviceType) {

        DFAgentDescription[] dfAgentDescriptions = null;

        try {
            DFAgentDescription agentDescription = new DFAgentDescription();
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType(serviceType);
            agentDescription.addServices(serviceDescription);

            dfAgentDescriptions = DFService.search(this, agentDescription);

        } catch (FIPAException e) {
            logger.error("DFService: Error while requesting the DFService!", e);
        }
        return dfAgentDescriptions;
    }
}
