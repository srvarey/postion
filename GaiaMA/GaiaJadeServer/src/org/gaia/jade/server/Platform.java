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
package org.gaia.jade.server;

import jade.core.Agent;
import jade.core.ContainerID;
import jade.core.Profile;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import jade.wrapper.State;
import java.util.Hashtable;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.core.agents.UtilityAgent;
import org.gaia.core.network.JadeUrlChecker;
import static org.gaia.domain.utils.GlobalInfo.ExecutionMode.APPLICATION;
import static org.gaia.domain.utils.GlobalInfo.ExecutionMode.SERVER_MASTER;
import static org.gaia.domain.utils.GlobalInfo.ExecutionMode.SERVER_SLAVE;

/**
 * This class manages the interaction between AgentGUI and JADE.<br> It contains
 * the methods to start / stop JADE, as well as methods<br> for starting new
 * container or agents <br>
 *
 */
public class Platform extends Object {

    private static final Logger logger = Logger.getLogger(Platform.class);
    public final static int UTIL_CMD_OpenDF = 1;
    public final static int UTIL_CMD_ShutdownPlatform = 2;
    public final static int UTIL_CMD_OpenLoadMonitor = 3;
    private static final String MASapplicationAgentName = "server.client";
    private static final String MASserverMasterAgentName = "server.master";
    private static final String MASserverSlaveAgentName = "server.slave";
    private Runtime jadeRuntime;
    private AgentContainer jadeMainContainer;
    private Vector<AgentContainer> jadeContainerLocal = new Vector<>();
    private Vector<ContainerID> jadeContainerRemote = new Vector<>();
    public JadeUrlChecker urlChecker = null;

    /**
     * This Method will start - depending on the Configuration - the
     * programs-background-agents. It starts directly after starting the
     * JADE-Platform
     *
     * @param showRMA specifies if the rma should appear or not
     * @return true, if successful
     */
    private boolean jadeStartBackgroundAgents(boolean showRMA) {

        urlChecker = new JadeUrlChecker(Application.getGlobalInfo().getServerMasterURL());
        urlChecker.setPort(Application.getGlobalInfo().getServerMasterPort());
        urlChecker.setPort4MTP(Application.getGlobalInfo().getServerMasterPort4MTP());
        switch (Application.getExecutionMode()) {
            case APPLICATION:
                logger.info("Starting as " + MASapplicationAgentName);
                if (!jadeAgentIsRunning(MASapplicationAgentName)) {
                    jadeAgentStart(MASapplicationAgentName, org.gaia.simulationService.agents.ServerClientAgent.class.getName());
                }
                if (showRMA == true) {
                    jadeSystemAgentOpen("rma", null);
                }
                break;

            case SERVER_MASTER:
                if (Application.getDatabaseConnection(true).hasErrors) {
                    this.jadeStop();
                    return false;
                }
                logger.info("Starting as " + MASserverMasterAgentName);
                if (!jadeAgentIsRunning(MASserverMasterAgentName)) {
                    this.jadeAgentStart(MASserverMasterAgentName, org.gaia.simulationService.agents.ServerMasterAgent.class.getName());
                }
                break;

            case SERVER_SLAVE:
                if (Application.getGlobalInfo().getServerMasterURL() == null
                        || Application.getGlobalInfo().getServerMasterURL().equalsIgnoreCase("")
                        || Application.getGlobalInfo().getServerMasterPort().equals(0)
                        || Application.getGlobalInfo().getServerMasterPort4MTP().equals(0)) {

                    this.jadeStop();
                    return false;
                }
//                new LoadMeasureThread().start();

                if (jadeAgentIsRunning(MASserverSlaveAgentName) == false) {
                    this.jadeAgentStart(MASserverSlaveAgentName, org.gaia.simulationService.agents.ServerSlaveAgent.class.getName());
                }
                logger.info("Starting as " + MASserverSlaveAgentName);
                break;

        }
        return true;
    }

    /**
     * Starts JADE
     *
     * @return true, if successful
     */
    public boolean jadeStart() {
        return jadeStart(true);
    }

    /**
     * Starts JADE
     *
     * @param showRMA the show rma
     * @return true, if successful
     */
    public boolean jadeStart(boolean showRMA) {

        boolean startSucceed = false;

        if (!jadeIsMainContainerRunning()) {
            try {
                // --- Start Platform -------------------------------
                jadeRuntime = Runtime.instance();
                jadeRuntime.invokeOnTermination(new Runnable() {
                    public void run() {
                        jadeMainContainer = null;
                        jadeRuntime = null;
                    }
                });
                jadeMainContainer = jadeRuntime.createMainContainer(this.jadeGetContainerProfile());
                startSucceed = true;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        } else {
            logger.warn("JADE container not running " + jadeRuntime);
        }
        if (!this.jadeStartBackgroundAgents(showRMA)) {
            return false;
        }
        return startSucceed;
    }

    /**
     * This method returns the JADE-Profile, which has to be used for the
     * container-profiles. If a project is focused the specific
     * project-configuration will be used. Otherwise the default-configuration
     * of AgentGUI will be used.
     *
     * @return Profile (for Jade-Containers)
     */
    private Profile jadeGetContainerProfile() {

        Profile jadeContainerProfile;
        // --- Configure the JADE-Profile to use --------------------
        jadeContainerProfile = Application.getJadeDefaultProfile();
        logger.debug("JADE-Profile: Use AgentGUI-defaults");

        return jadeContainerProfile;
    }

    /**
     * Shutting down the jade-platform.
     */
    public void jadeStop() {
        if (jadeIsMainContainerRunning()) {
            this.jadeUtilityAgentStart(UTIL_CMD_ShutdownPlatform);
            Long timeStop = System.currentTimeMillis() + (10 * 1000);
            while (jadeIsMainContainerRunning()) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
                if (System.currentTimeMillis() >= timeStop) {
                    break;
                }
            }
            logger.info("Jade has been closed!");
        }
        jadeContainerRemote.removeAllElements();
        jadeContainerLocal.removeAllElements();
        jadeMainContainer = null;
        jadeRuntime = null;
    }

    /**
     * Asks the user to shutdown Jade.
     *
     * @return true, if the user answered 'yes'
     */
    public boolean jadeStopAskUserBefore() {

        if (this.jadeIsMainContainerRunning()) {
            this.jadeStop();
        }
        return true;
    }

    /**
     * Checks, whether the agentMain-container (Jade himself) is running or not.
     *
     * @param forceJadeStart will force the jade start, if JADE is not running
     * @return true, if the MainContainer is running
     */
    public boolean jadeMainContainerIsRunning(boolean forceJadeStart) {
        if (forceJadeStart) {
            jadeSystemAgentOpen("rma", null);
        }
        return jadeIsMainContainerRunning();
    }

    /**
     * Jade agentMain container is running.
     *
     * @return true, if the Main-Container is running
     */
    public boolean jadeIsMainContainerRunning() {
        boolean JiR;
        try {
            jadeMainContainer.getState();
            JiR = true;
        } catch (Exception eMC) {
            JiR = false;
            jadeMainContainer = null;
            try {
                jadeRuntime.shutDown();
            } catch (Exception eRT) {
            }
            jadeRuntime = null;
        }
        return JiR;
    }

    /**
     * Starts the Utility-Agent with a job defined in its start argument
     *
     * @see #UTIL_CMD_OpenDF
     * @see #UTIL_CMD_OpenLoadMonitor
     * @see #UTIL_CMD_ShutdownPlatform
     * @see UtilityAgent
     *
     * @param utilityCMD the job for the utility UtilityAgent to do
     */
    public void jadeUtilityAgentStart(int utilityCMD) {
        Object[] agentArgs = new Object[5];
        agentArgs[0] = utilityCMD;
        jadeAgentStart("utility", UtilityAgent.class
                .getName(), agentArgs);
    }

    public void setUrlChecker() {
        urlChecker = new JadeUrlChecker(Application.getGlobalInfo().getServerMasterURL());
        urlChecker.setPort(Application.getGlobalInfo().getServerMasterPort());
        urlChecker.setPort4MTP(Application.getGlobalInfo().getServerMasterPort4MTP());
    }

    /**
     * Starts an Agent, if the agentMain-container exists.
     *
     * @param rootAgentName the root agent name
     * @param optionalSuffixNo the optional postfix no
     */
    public void jadeSystemAgentOpen(String rootAgentName, Integer optionalSuffixNo) {
        this.jadeSystemAgentOpen(rootAgentName, optionalSuffixNo, null);
    }

    /**
     * Starts agents, which are available by JADE or AgentGUI like the rma,
     * sniffer etc.<br> The herein known root agent names are:<br> 'rma',
     * 'sniffer', 'dummy', 'df', 'introspector', 'log' for JADE and
     * 'loadmonitor' and 'simstarter' for Agent.GUI
     *
     *
     * @param rootAgentName the root agent name
     * @param optionalSuffixNo an optional postfix no
     * @param openArgs the open args
     */
    public void jadeSystemAgentOpen(String rootAgentName, Integer optionalSuffixNo, Object[] openArgs) {
        // --- Table of the known Jade System-Agents -----
        Hashtable<String, String> JadeSystemTools = new Hashtable<>();
        JadeSystemTools.put("rma", "jade.tools.rma.rma");
        JadeSystemTools.put("sniffer", "jade.tools.sniffer.Sniffer");
        JadeSystemTools.put("dummy", "jade.tools.DummyAgent.DummyAgent");
        JadeSystemTools.put("df", "mas.agents.DFOpener");
        JadeSystemTools.put("introspector", "jade.tools.introspector.Introspector");
        JadeSystemTools.put("log", "jade.tools.logging.LogManagerAgent");

        // --- AgentGUI - Agents --------------------------
        JadeSystemTools
                .put("loadmonitor", org.gaia.simulationService.agents.LoadMeasureAgent.class
                .getName());
        JadeSystemTools.put(
                "simstarter", org.gaia.simulationService.agents.LoadExecutionAgent.class
                .getName());

        boolean showRMA = true;
        AgentController agentController = null;
        String agentNameSearch = rootAgentName.toLowerCase();
        String agentNameClass = null;
        String agentNameForStart = rootAgentName;
        Integer msgAnswer = 0;

        if (agentNameForStart.equalsIgnoreCase("simstarter")) {
            showRMA = false;
        }
        if (optionalSuffixNo
                != null) {
            agentNameForStart = rootAgentName + optionalSuffixNo.toString();
        }

        // --- Was the system already started? ---------------
        if (!jadeIsMainContainerRunning()) {
            jadeStart(showRMA);
            if (agentNameForStart.equalsIgnoreCase("rma")) {
                try {
                    agentController = jadeMainContainer.getAgent("rma");
                } catch (ControllerException e) {
                    logger.error(e);
                }
                return;
            }
        }
        agentNameClass = JadeSystemTools.get(agentNameSearch);
        if (agentNameClass
                == null) {
            logger.warn("jadeSystemAgentOpen: Unknown System Agent => " + rootAgentName);
            return;
        }
        // --- Does an agent (see name) already exists? ------

        if (jadeAgentIsRunning(agentNameForStart)
                && !agentNameForStart.equalsIgnoreCase("df")) {
            if (msgAnswer == 0) {                // --- YES - Start another agent of this kind ---------
                jadeSystemAgentOpen(rootAgentName, newSuffixNo(rootAgentName), openArgs);
            }

        } else {
            // --- Agent doe's NOT EXISTS !! ---------------------
            try {
                if (agentNameForStart.equalsIgnoreCase("df")) {
                    // --- Show the DF-GUI -----------------------
                    this.jadeUtilityAgentStart(UTIL_CMD_OpenDF);
                } else if (agentNameForStart.equalsIgnoreCase("loadMonitor")) {
                    this.jadeUtilityAgentStart(UTIL_CMD_OpenLoadMonitor);
                } else if (agentNameForStart.equalsIgnoreCase("simstarter")) {
                    jadeAgentStart(agentNameForStart, agentNameClass, openArgs, "GAIA");
                } else {
                    // --- Show a standard jade ToolAgent --------
                    agentController = jadeMainContainer.createNewAgent(agentNameForStart, agentNameClass, openArgs);
                    agentController.start();
                }
            } catch (StaleProxyException e) {
                logger.error(e);
            }
        }
    }

    /**
     * Will find a new suffix number for the name of an agent.
     *
     * @param agentName the agent name
     * @return the integer
     */
    private int newSuffixNo(String agentName) {

        String NewName = agentName;
        Integer i = 0;

        while (jadeAgentIsRunning(NewName) == true) {
            i++;
            NewName = agentName + i.toString();
        }
        return i;
    }

    /**
     * Kills an agent in the MainContainer, if it is running.
     *
     * @param localAgentName the agent name
     */
    public void jadeKillAgentInMainContainer(String localAgentName) {

        AgentController agentController = null;
        if (jadeAgentIsRunning(localAgentName)) {
            // --- get Agent(Controller) -----
            try {
                agentController = jadeMainContainer.getAgent(localAgentName);
            } catch (ControllerException e) {
            }
            // --- Kill the Agent ------------
            try {
                agentController.kill();

            } catch (StaleProxyException e) {
            }
        }
    }

    /**
     * Checks, whether one Agent is running (or not) in the agentMain-container.
     *
     * @param localAgentName the agent name
     * @return true, if the agent is running
     */
    public boolean jadeAgentIsRunning(String localAgentName) {
        return jadeAgentIsRunning(localAgentName, jadeMainContainer.getName());
    }

    /**
     * Checks, whether one Agent is running (or not) in the specified container
     *
     * @param localAgentName the agent name
     * @param localContainerName the local container name
     * @return true, if the agent is running
     */
    public boolean jadeAgentIsRunning(String localAgentName, String localContainerName) {

        boolean AgentiR;
        AgentContainer AgenCont;
        AgentController AgeCon;

        if (jadeIsMainContainerRunning() == false) {
            return false;
        }

        // --- 0. Set to the right Container ------------------------
        AgenCont = jadeGetContainer(localContainerName);
        if (AgenCont == null) {
            return false;
        }
        // --- 1. try to find the agent in agentMain-container -----------
        try {
            AgeCon = AgenCont.getAgent(localAgentName);
        } catch (ControllerException CE) {
            return false; 	// CE.printStackTrace();
        }

        // --- 2. try to get agent's state --------------------------
        try {
            @SuppressWarnings("unused")
            State Stat = AgeCon.getState();
            AgentiR = true;
        } catch (Exception eMC) {
            AgentiR = false;
        }
        return AgentiR;
    }

    /**
     * Adding an AgentContainer to the local platform.
     *
     * @param newContainerName the container name
     * @return the agent container
     */
    public AgentContainer jadeContainerCreate(String newContainerName) {
        Profile pSub = this.jadeGetContainerProfile();
        pSub.setParameter(Profile.CONTAINER_NAME, newContainerName);
        AgentContainer MAS_AgentContainer = jadeRuntime.createAgentContainer(pSub);
        jadeContainerLocal.add(MAS_AgentContainer);
        return MAS_AgentContainer;
    }

    /**
     * Returns the Jade agentMain container.
     *
     * @return the agent container
     */
    public AgentContainer jadeGetMainContainer() {
        if (jadeIsMainContainerRunning() == false) {
            return null;
        }
        return this.jadeMainContainer;
    }

    /**
     * Returns the Container given by it's name.
     *
     * @param containerNameSearch the container name search
     * @return the agent container
     */
    public AgentContainer jadeGetContainer(String containerNameSearch) {

        AgentContainer agentContainer = null;
        String agentContainerName = null;

        // --- Falls JADE noch nicht l�uft ... ----------------
        if (!jadeIsMainContainerRunning()) {
            return null;
        }
        // --- Wird nach dem 'Main-Container' gesucht? --------
        if (containerNameSearch == this.jadeMainContainer.getName()) {
            return this.jadeMainContainer;
        }

        // --- Den richtigen Container abgreifen --------------
        for (int i = 0; i < jadeContainerLocal.size(); i++) {
            agentContainer = jadeContainerLocal.get(i);
            try {
                agentContainerName = agentContainer.getContainerName();
            } catch (ControllerException ex) {
                logger.error(ex);
            }
            if (agentContainerName.equalsIgnoreCase(containerNameSearch)) {
                break;
            }
        }
        return agentContainer;
    }

    /**
     * Kills an AgentContainer to the local platform.
     *
     * @param containerName the container name
     */
    public void jadeKillContainer(String containerName) {
        AgentContainer agentContainer;
        agentContainer = jadeGetContainer(containerName);
        jadeContainerKill(agentContainer);
    }

    /**
     * Jade container kill.
     *
     * @param agentContainer the agent container
     */
    public void jadeContainerKill(AgentContainer agentContainer) {

        jadeContainerLocal.remove(agentContainer);
        try {
            agentContainer.kill();
        } catch (StaleProxyException e) {
            logger.error(e);
        }
    }

    /**
     * Adding an Agent to a Container.
     *
     * @param newAgentName the agent name
     * @param agentClassName the agent class name
     */
    public void jadeAgentStart(String newAgentName, String agentClassName) {
        String MainContainerName = jadeMainContainer.getName();
        jadeAgentStart(newAgentName, agentClassName, null, MainContainerName);
    }

    /**
     * Starts an agent as specified
     *
     * @param newAgentName the agent name
     * @param agentClassName the agent class name
     * @param inContainer the container name
     */
    public void jadeAgentStart(String newAgentName, String agentClassName, String inContainer) {
        jadeAgentStart(newAgentName, agentClassName, null, inContainer);
    }

    /**
     * Starts an agent as specified
     *
     * @param newAgentName the agent name
     * @param agentClassName the agent class name
     * @param startArguments the start arguments for the agent
     */
    public void jadeAgentStart(String newAgentName, String agentClassName, Object[] startArguments) {
        String MainContainerName = jadeMainContainer.getName();
        jadeAgentStart(newAgentName, agentClassName, startArguments, MainContainerName);
    }

    /**
     * Starts an agent as specified
     *
     * @param newAgentName the agent name
     * @param agentClassName the agent class name
     * @param startArguments the start arguments for the agent
     * @param inContainer the container name
     */
    public void jadeAgentStart(String newAgentName, String agentClassName, Object[] startArguments, String inContainer) {

        try {
            @SuppressWarnings("unchecked")
            Class<? extends Agent> clazz = (Class<? extends Agent>) Class.forName(agentClassName);
            jadeAgentStart(newAgentName, clazz, startArguments, inContainer);

        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
    }

    /**
     * Starts an agent as specified
     *
     * @param newAgentName the agent name
     * @param clazz the class of the agent
     * @param startArguments the start arguments for the agent
     * @param inContainer the container name
     */
    public void jadeAgentStart(String newAgentName, Class<? extends Agent> clazz, Object[] startArguments, String inContainer) {

        AgentController agentController;
        AgentContainer agentContainer;

        // --- Was the system already started? ---------------
        if (!jadeIsMainContainerRunning()) {
            jadeStart();
        }
        agentContainer = this.jadeGetContainer(inContainer);
        if (agentContainer == null) {
            agentContainer = jadeContainerCreate(inContainer);
        }
        try {
            Agent agent = (Agent) clazz.newInstance();
            agent.setArguments(startArguments);
            agentController = agentContainer.acceptNewAgent(newAgentName, agent);
            agentController.start();

        } catch (StaleProxyException | InstantiationException | IllegalAccessException e) {
            logger.error(e);
        }
    }
}
