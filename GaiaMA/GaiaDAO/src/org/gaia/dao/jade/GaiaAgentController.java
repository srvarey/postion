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
package org.gaia.dao.jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.core.event.NotificationService;
import jade.core.mobility.AgentMobilityService;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.agents.ServerClientAgent;


/*
 *
 * @author Benjamin
 */
public class GaiaAgentController {

    private static GaiaAgentController reportAgentController;
    private static final DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private ContainerController container;
    private AgentController creationRequestController;
    private ServerClientAgent serverClientAgent;
    private Boolean isLocal = false;
    private static final Logger logger = Logger.getLogger(DAOCallerAgent.class);

    private GaiaAgentController() {

        /**
         * launch jade
         *
         */
        Application.getGlobalInfo();

        logger.info("Library Path : " + System.getProperty("java.library.path"));
        long id = (new Date()).getTime();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, Application.getGlobalInfo().getServerMasterURL());
        profile.setParameter(Profile.MAIN_PORT, Application.getGlobalInfo().getServerMasterPort().toString());
        /**
         * needed by load measure agent - pb with sigar dll
         *
         */
        StringBuilder services = new StringBuilder(NotificationService.class.getName());
        services.append(";");
        services.append(AgentMobilityService.class.getName());
        services.append(";");
        services.append(SimulationService.class.getName());
        profile.setParameter(Profile.SERVICES, services.toString());
        profile.setParameter(Profile.CONTAINER_NAME, "client-" + id);

        Runtime jadeRuntime = Runtime.instance();
        try {
            container = jadeRuntime.createAgentContainer(profile);

        } catch (Exception e) {
            logger.error("FATAL ERROR : NO SERVER REACHABLE");
            logger.error(StringUtils.formatErrorMessage(e));
            System.exit(0);
        }
        Application.getJadePlatform().setUrlChecker();
        if (container != null) {
            try {
                serverClientAgent = new ServerClientAgent();
                creationRequestController = container.acceptNewAgent("agentCreatorRequester-" + id, serverClientAgent);
                creationRequestController.start();
            } catch (StaleProxyException e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        } else {
            isLocal = true;
            logger.error("FATAL ERROR : NO SERVER REACHABLE");
            logger.error("PLEASE START SERVER");
            logger.error("APPLICATION STOPPED");
            System.exit(0);
        }

        // the split container way
//        Properties pp = new Properties();
//        pp.setProperty(MicroRuntime.HOST_KEY, Application.getGlobalInfo().getServerMasterURL());
//        pp.setProperty(MicroRuntime.PORT_KEY, Application.getGlobalInfo().getServerMasterPort().toString());
//
//        MicroRuntime.startJADE(pp, new Runnable() {
//            public void run() {
//                // Code to be executed when JADE WILL terminate
//            }
//        });
//        try {
//            serverClientAgent = new ServerClientAgent();
//            MicroRuntime.startAgent("agentCreatorRequester-" + id, ServerClientAgent.class.getName(), new Object[0]);
//        } catch (Exception e) {
//            logger.error(StringUtils.formatErrorMessage(e));
//        }
    }

    public static GaiaAgentController getInstance() {
        if (reportAgentController == null) {
            reportAgentController = new GaiaAgentController();
        }
        return reportAgentController;
    }

    public Agent createLocalAgent(Class clazz) {
        Agent agent = null;
        try {
            agent = (Agent) clazz.newInstance();
            long id = Math.round(Math.random() * 1000000000);
            String name = clazz.getName().substring(clazz.getName().lastIndexOf(StringUtils.DOT) + 1) + "/" + Long.toString(id) + "/";
            AgentController acceptNewAgent = container.acceptNewAgent(name, agent);
            acceptNewAgent.start();
        } catch (InstantiationException | IllegalAccessException | StaleProxyException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return agent;
    }

    public AID registerAgent(Agent a) {
        AID aid = null;

        try {
            long id = Math.round(Math.random() * 1000000000);
            String name = a.getClass().getName().substring(a.getClass().getName().lastIndexOf(StringUtils.DOT) + 1) + "/" + Long.toString(id) + "/";
            AgentController acceptNewAgent = container.acceptNewAgent(name, a);
            acceptNewAgent.start();
            aid = new AID(name, false);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return aid;
    }

    /**
     * add agent function
     *
     */
    public void addAgent(String name, Agent a) {
        try {
            AgentController acceptNewAgent = container.acceptNewAgent(name, a);
            acceptNewAgent.start();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static DateFormat getDateFormat() {
        return dateformat;
    }

    public void terminateAgent(AID aid) {
        try {
            AgentController controller = container.getAgent(aid.getLocalName());
            controller.kill();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static Integer getId(AID aid) {
        Integer id = -1;
        String name = aid.getName();
        if (name.indexOf("/") > -1) {
            name = name.substring(name.indexOf("/") + 1);
            if (name.indexOf("/") > -1) {
                name = name.substring(0, name.indexOf("/"));
                id = Integer.parseInt(name);
            }
        }
        return id;
    }

    public static GaiaAgentController getReportAgentController() {
        return reportAgentController;
    }

    public ContainerController getContainer() {
        return container;
    }

    public AgentController getCreationRequestController() {
        return creationRequestController;
    }

    public ServerClientAgent getServerClientAgent() {
        return serverClientAgent;
    }

    public Boolean isLocal() {
        return isLocal;
    }
}
