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
package org.gaia.simulationService.balancing;

import jade.core.Agent;
import jade.core.Location;
import jade.core.ServiceException;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.core.agents.AgentClassElement4SimStart;
import org.gaia.core.project.DistributionSetup;
import org.gaia.core.sim.setup.SimulationSetup;
import org.gaia.jade.server.Application;
import org.gaia.jade.server.Platform;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.LoadServiceHelper;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.load.LoadInformation.Container2Wait4;
import org.gaia.simulationService.load.LoadInformation.NodeDescription;
import org.gaia.simulationService.load.LoadMeasureThread;
import org.gaia.simulationService.load.LoadThresholdLevels;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This class is the abstract super class for the dynamic and static load
 * balancing. It provide functionalities, which should be available in both kind
 * of balancing approaches.
 *
 */
public abstract class BaseLoadBalancing extends OneShotBehaviour implements BaseLoadBalancingInterface {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -4987462350754111029L;
    /**
     * The simulation helper for the connection to the SimulationsService.
     */
    protected SimulationServiceHelper simHelper = null;
    /**
     * The load helper for the connection to the LoadService.
     */
    protected LoadServiceHelper loadHelper = null;
    private static final Logger logger = Logger.getLogger(BaseLoadBalancing.class);
    /**
     * The current simulation setup.
     */
    protected SimulationSetup currSimSetup = null;
    /**
     * The current distribution setup.
     */
    protected DistributionSetup currDisSetup = null;
    /**
     * The current threshold levels.
     */
    protected LoadThresholdLevels currThresholdLevels = null;
    /**
     * The indicator that says if the thresholds were exceeded over all.
     */
    protected Integer currThresholdExceededOverAll = 0;
    /**
     * The BenchmarkResults of all involved container.
     */
    protected HashMap<String, Float> currContainerBenchmarkResults = new HashMap<>();
    /**
     * The locations in the distributed system.
     */
    protected HashMap<String, Location> currContainerLocations = null;

    /**
     * Instantiates a new base load balancing.
     */
    public BaseLoadBalancing(Agent agent) {
        super(agent);

        this.setLoadHelper();
        this.setSimulationLoadHelper();
        this.setThresholdLevels();
    }

    /* (non-Javadoc)
     * @see jade.core.behaviours.Behaviour#action()
     */
    @Override
    public void action() {
        this.doBalancing();
    }

    /**
     * This method receives the LoadServiceHelper to the corresponding local
     * variable.
     */
    private void setLoadHelper() {

        try {
            loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    /**
     * This method receives the SimulationsServiceHelper to the corresponding
     * local variable.
     */
    private void setSimulationLoadHelper() {
        try {
            simHelper = (SimulationServiceHelper) myAgent.getHelper(SimulationService.NAME);
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    /**
     * This method puts the threshold levels to the corresponding local
     * variable.
     */
    private void setThresholdLevels() {

        if (currDisSetup != null) {
            // --- If the user wants to use his own Threshold, ----------
            // --- load them to the SimulationsService		   ----------
            if (currDisSetup.isUseUserThresholds()) {
                currThresholdLevels = currDisSetup.getUserThresholds();
                try {
                    loadHelper.setThresholdLevels(currThresholdLevels);
                } catch (ServiceException e) {
                    logger.error(e);
                }
            } else {
                currThresholdLevels = LoadMeasureThread.getThresholdLevels();
            }

        } else {
            currThresholdLevels = LoadMeasureThread.getThresholdLevels();
        }

    }

    /**
     * This method will return the Object Array for the start argument of an
     * agent.
     *
     * @param ace4SimStart the AgentClassElement4SimStart
     * @return the start arguments as object array
     */
    protected Object[] getStartArguments(AgentClassElement4SimStart ace4SimStart) {

        if (ace4SimStart.getStartArguments() == null) {
            return null;
        } else {
            Object[] startArgs = null;
            return startArgs;
        }
    }

    /**
     * Method to start a new agent.
     *
     * @param nickName the nick name
     * @param agentClassName the agent class name
     * @param args the start arguments as Object array
     */
    protected void startAgent(String nickName, String agentClassName, Object[] args) {
        this.startAgent(nickName, agentClassName, args, null);
    }

    /**
     * Method to start a new agent.
     *
     * @param nickName the nick name
     * @param agentClass the agent class
     * @param args the start arguments as Object array
     */
    protected void startAgent(String nickName, Class<? extends Agent> agentClass, Object[] args) {
        this.startAgent(nickName, agentClass, args, null);
    }

    /**
     * Method to start a new agent.
     *
     * @param nickName the nick name
     * @param agentClassName the agent class name
     * @param args the start arguments as Object array
     * @param toLocation the location, where the agent should start
     */
    protected void startAgent(String nickName, String agentClassName, Object[] args, Location toLocation) {

        if (agentClassName == null || agentClassName.equalsIgnoreCase("")) {
            logger.error("AgentClassName not found : " + agentClassName);
        } else {
            // --- Initialize the agent-class -------------
            try {
                @SuppressWarnings("unchecked")
                Class<? extends Agent> agentClass = (Class<? extends Agent>) Class.forName(agentClassName);
                this.startAgent(nickName, agentClass, args, toLocation);
            } catch (ClassNotFoundException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Main-Method to start a new agent. All other methods will use this one at
     * least.
     *
     * @param nickName the nick name
     * @param agentClass the agent class
     * @param args the start arguments as Object array
     * @param toLocation the location, where the agent should start
     */
    protected void startAgent(String nickName, Class<? extends Agent> agentClass, Object[] args, Location toLocation) {

        boolean startLocally;
        ContainerController cc = myAgent.getContainerController();
        AgentController ac;

        try {
            // ----------------------------------------------------------
            // --- Start here or on a remote container? -----------------
            // ----------------------------------------------------------
            if (toLocation == null) {
                startLocally = true;
            } else {
                if (cc.getContainerName().equalsIgnoreCase(toLocation.getName())) {
                    startLocally = true;
                } else {
                    startLocally = false;
                }
            }
            // --- Start the agent now ! --------------------------------
            if (startLocally == true) {
                // --- Start on this local container ----------------
                Agent agent = (Agent) agentClass.newInstance();
                agent.setArguments(args);
                ac = cc.acceptNewAgent(nickName, agent);
                ac.start();
            } else {
                // --- Is the SimulationServioce running? -----------
                if (loadServiceIsRunning()) {
                    // ----------------------------------------------
                    // --- START: Start direct on remote-container --
                    // ----------------------------------------------
                    String containerName = toLocation.getName();
                    String agentClassName = agentClass.getName();
                    try {
                        loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
                        loadHelper.startAgent(nickName, agentClassName, args, containerName);
                    } catch (ServiceException e) {
                        logger.error(e);
                    }
                    // ----------------------------------------------
                    // --- END: Start direct on remote-container ----
                    // ----------------------------------------------
                } else {
                    // ----------------------------------------------
                    // --- START: 'Start and migrate' - procedure ---
                    // ----------------------------------------------
                    Agent agent = (Agent) agentClass.newInstance();
                    agent.setArguments(args);
                    ac = cc.acceptNewAgent(nickName, agent);
                    ac.start();
                    // --------------------------------
                    int retryCounter = 0;
                    while (!agentFound(cc, nickName)) {
                        block(100);
                        if (retryCounter >= 5) {
                            break;
                        }
                    }
                    // --------------------------------
                    retryCounter = 0;
                    while (agentFound(cc, nickName)) {
                        // --- Move the agent ---------
                        if (retryCounter == 0) {
                            agent.doMove(toLocation);
                        }
                        block(100);
                        retryCounter++;
                        if (retryCounter >= 5) {
                            retryCounter = 0;
                        }
                    } // --- end while
                    // ----------------------------------------------
                    // --- END: 'Start and migrate' - procedure -----
                    // ----------------------------------------------
                }
                // --------------------------------------------------
            }

        } catch (StaleProxyException e) {
            logger.error(e);
        } catch (ControllerException | InstantiationException | IllegalAccessException e) {
            logger.error(e);
        }
    }

    /**
     * This method will start the Load-Monitor-Agent.
     */
    protected void openLoadMonitor() {
        Application.getJadePlatform().jadeUtilityAgentStart(Platform.UTIL_CMD_OpenLoadMonitor);
    }

    /**
     * Checks if an agent can be found locally.
     *
     * @param cc the local ContainerController
     * @param nickName the local agent name to search for
     * @return true, if successful
     */
    private boolean agentFound(ContainerController cc, String nickName) {
        try {
            cc.getAgent(nickName);
            return true;
        } catch (ControllerException e) {
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * Checks if the load service is running or not.
     *
     * @return true, if the Service is running
     */
    private boolean loadServiceIsRunning() {

        try {
            @SuppressWarnings("unused")
            LoadServiceHelper _loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
            return true;
        } catch (ServiceException e) {
            //e.printStackTrace();
            return false;
        }
    }

    /**
     * This Method can be invoked, if a new remote container is required. If the
     * container was started the method returns the new containers name and will
     * update the local information of {@link #currContainerLoactions} and
     * {@link #currContainerBenchmarkResults}.
     *
     * @return the name of the new container
     *
     * @see #currContainerLoactions
     * @see #currContainerBenchmarkResults
     */
    protected String startRemoteContainer() {
        return this.startRemoteContainer(null);
    }

    /**
     * This Method can be invoked, if a new remote container is required. If the
     * container was started the method returns the new containers name and will
     * update the local information of {@link #currContainerLoactions} and
     * {@link #currContainerBenchmarkResults}.
     *
     * @param remoteContainerConfig the remote container configuration out of
     * the Project
     * @return the name of the new container
     *
     * @see #currContainerLoactions
     * @see #currContainerBenchmarkResults
     */
    protected String startRemoteContainer(RemoteContainerConfig remoteContainerConfig) {

        boolean newContainerStarted = false;
        String newContainerName;
        try {
            // --- Start a new remote container -----------------
            loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
            newContainerName = loadHelper.startNewRemoteContainer(remoteContainerConfig);

            while (true) {
                Container2Wait4 waitCont = loadHelper.startNewRemoteContainerStatus(newContainerName);
                if (waitCont.isStarted()) {
                    logger.info("Remote Container '" + newContainerName + "' was started!");
                    newContainerStarted = true;
                    break;
                }
                if (waitCont.isCancelled()) {
                    logger.info("Remote Container '" + newContainerName + "' was NOT started!");
                    newContainerStarted = false;
                    break;
                }
                if (waitCont.isTimedOut()) {
                    logger.info("Remote Container '" + newContainerName + "' timed out!");
                    newContainerStarted = false;
                    break;
                }
                this.block(100);
            } // end while

            if (newContainerStarted) {


                while (loadHelper.getContainerDescription(newContainerName) == null) {
                    this.block(100);
                }
                while (loadHelper.getContainerDescription(newContainerName).getJvmPID() == null) {
                    this.block(100);
                }
                while (loadHelper.getContainerLoads().get(newContainerName) == null) {
                    this.block(100);
                }
                // --- Update the locations of all involved container ---------------
                currContainerLocations = loadHelper.getContainerLocations();

                // --- Get the benchmark-result for this node/container -------------
                NodeDescription containerDesc = loadHelper.getContainerDescription(newContainerName);
                Float benchmarkValue = containerDesc.getBenchmarkValue().getBenchmarkValue();
                currContainerBenchmarkResults.put(newContainerName, benchmarkValue);

                return newContainerName;
            }

        } catch (ServiceException e) {
            logger.error(e);
        }
        return null;
    }

    public void updateBench(String newContainerName) {
        boolean newContainerStarted = true;
        try {
            while (true) {
                Container2Wait4 waitCont = loadHelper.startNewRemoteContainerStatus(newContainerName);
                if (waitCont.isStarted()) {
                    logger.info("Remote Container '" + newContainerName + "' was started!");
                    newContainerStarted = true;
                    break;
                }
                if (waitCont.isCancelled()) {
                    logger.info("Remote Container '" + newContainerName + "' was NOT started!");
                    newContainerStarted = false;
                    break;
                }
                if (waitCont.isTimedOut()) {
                    logger.info("Remote Container '" + newContainerName + "' timed out!");
                    newContainerStarted = false;
                    break;
                }
                this.block(100);
            } // end while

            if (newContainerStarted) {


                while (loadHelper.getContainerDescription(newContainerName) == null) {
                    this.block(100);
                }
                while (loadHelper.getContainerDescription(newContainerName).getJvmPID() == null) {
                    this.block(100);
                }
                while (loadHelper.getContainerLoads().get(newContainerName) == null) {
                    this.block(100);
                }
                // --- Update the locations of all involved container ---------------
                currContainerLocations = loadHelper.getContainerLocations();

                // --- Get the benchmark-result for this node/container -------------
                NodeDescription containerDesc = loadHelper.getContainerDescription(newContainerName);
                Float benchmarkValue = containerDesc.getBenchmarkValue().getBenchmarkValue();
                currContainerBenchmarkResults.put(newContainerName, benchmarkValue);
            }
        } catch (ServiceException e) {
            logger.error(e);
        }
    }

    public void setCurrDisSetup(DistributionSetup currDisSetup) {
        this.currDisSetup = currDisSetup;
    }

    /**
     * This method will start a number of remote container.
     *
     * @param numberOfContainer the number of container
     * @param remoteContainerConfig the remote container configuration
     * @param filterMainContainer true, if the Main-Container should be filter
     * out of the result
     *
     * @return the of all newly started locations
     */
    protected HashMap<String, Location> startNumberOfRemoteContainer(int numberOfContainer, boolean filterMainContainer, RemoteContainerConfig remoteContainerConfig) {

        HashMap<String, Location> newContainerLocations;

        // --- Is the simulation service running ? -----------------------
        if (!loadServiceIsRunning()) {
            logger.error("Can not start remote container - LoadService is not running!");
            return null;
        }

        // --- Start the required number of container --------------------
        int startMistakes = 0;
        int startMistakesMax = 2;
        Vector<String> containerList = new Vector<>();
        while (containerList.size() < numberOfContainer) {

            String newContainer = this.startRemoteContainer();
            if (newContainer != null) {
                containerList.add(newContainer);
            } else {
                startMistakes++;
            }
            if (startMistakes >= startMistakesMax) {
                break;
            }
        }

        // --- Get the locations of the started container ----------------
        LoadServiceHelper _loadHelper;
        try {
            _loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
            newContainerLocations = _loadHelper.getContainerLocations();

        } catch (ServiceException e) {
            logger.error(e);
            return null;
        }

        // --- If wanted, filter the Main-Container out ------------------
        if (filterMainContainer == true) {
            newContainerLocations.remove("Main-Container");
            if (newContainerLocations.isEmpty()) {
                newContainerLocations = null;
            }
        }
        return newContainerLocations;
    }

    /**
     * This Method transfers the new LoadAgentMap-Instance to the
     * SimulationService and informs the agent about the location they have to
     * migrate.
     *
     * @param transferAgents the new agent migration
     */
    protected void setAgentMigration(Vector<AID_Container> transferAgents) {

        try {
            simHelper.setAgentMigration(transferAgents);

        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
