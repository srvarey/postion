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
package org.gaia.dao.jade;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentController;
import jade.wrapper.ControllerException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.jade.server.CreateAgentBehaviour;
import org.gaia.simulationService.ontology.ReportObjectNotification;

/**
 *
 * @author Benjamin Frerejean
 */
public class DAOAgentPool implements GaiaVocabulary{
    private static List<GaiaDAOAgent> daoAgentList;
    private static DAOCallerAgent daoCallerAgent;
    private static DAOCallerAgent positionCallerAgent;
    private static final Logger logger = Logger.getLogger(DAOAgentPool.class);

    public static void register(GaiaDAOAgent agent){
        if (daoAgentList==null) {
            try {
                daoAgentList=new ArrayList();
                daoCallerAgent=new DAOCallerAgent();
                String containerName=agent.getContainerController().getContainerName();
                Integer id=new Integer(containerName.substring(6));
                AgentController controller=agent.getContainerController().acceptNewAgent(DAOCallerAgent.class.getSimpleName()+"-"+id+"-0", daoCallerAgent);
                controller.start();
                positionCallerAgent=new DAOCallerAgent();
                logger.error("accept agent PositionCallerAgent-"+id+"-0 from "+agent.getName());
                controller=agent.getContainerController().acceptNewAgent("PositionCallerAgent-"+id+"-0", positionCallerAgent);
                controller.start();
            } catch(ControllerException | NumberFormatException e){
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        daoAgentList.add(agent);
    }

    public static void unregister(GaiaDAOAgent agent){
        daoAgentList.remove(agent);
    }

    public static GaiaDAOAgent getAvailableDAOAgent(){
        if (daoAgentList!=null && daoAgentList.size()>0){
            try {
                int i=0;
                while (i<daoAgentList.size()){
                    if (!daoAgentList.get(i).isRunning()){
                        return daoAgentList.get(i);
                    }
                    i++;
                }
                return startANewGaiaDAOAgent();
            } catch(Exception e){
               logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return null;
    }

    private static GaiaDAOAgent startANewGaiaDAOAgent(){
        try {
            GaiaDAOAgent newAgent=new GaiaDAOAgent(null,null);
            String containerName=daoAgentList.get(0).getContainerController().getContainerName();
            Integer id=new Integer(containerName.substring(6));
            AgentController controller=daoAgentList.get(0).getContainerController().acceptNewAgent(GaiaDAOAgent.class.getSimpleName()+"-"+id+"-loc-"+daoAgentList.size(), newAgent);
            controller.start();
            return newAgent;
        } catch(Exception e){
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    /** asynchrone calls */

    public static void callAsynchroneMethod(Class clazz, String methodName) {
        List<Serializable> args = new ArrayList();
        callAsynchroneMethod( clazz, methodName, args);
    }

    public static void callAsynchroneMethod(Class clazz, String methodName, Serializable argument) {
        List<Serializable> args = new ArrayList();
        args.add(argument);
        callAsynchroneMethod( clazz, methodName, args);
    }


    public static void callAsynchroneMethod(Class clazz, String methodName, Serializable argument1, Serializable argument2){
        List<Serializable> args = new ArrayList();
        args.add(argument1);
        args.add(argument2);
        callAsynchroneMethod( clazz, methodName, args);
    }

    public static void callAsynchroneMethod(Class clazz, String methodName, Serializable argument1, Serializable argument2, Serializable argument3, Serializable argument4){
        List<Serializable> args = new ArrayList();
        args.add(argument1);
        args.add(argument2);
        args.add(argument3);
        args.add(argument4);
        callAsynchroneMethod( clazz, methodName, args);
    }


    public static void callAsynchroneMethod(Class clazz, String methodName, List<Serializable> arguments) {
        GaiaDAOAgent daoagent = getAvailableDAOAgent();
        if (daoagent!=null){
            DAOCallerAgent.DAOCallBehaviour request = new DAOCallerAgent.DAOCallBehaviour(daoagent.getAID(), clazz.getName(), methodName, arguments, true, false);
            daoCallerAgent.addBehaviour(new ThreadedBehaviourFactory().wrap(request));
        } else {
            logger.error("ERROR : DAOAGENTPOOL CALL FROM GUI FORBIDEN => USE DAO CALLER AGENT INSTEAD");
            logger.error("   ON : "+clazz+StringUtils.DOT+methodName);
        }
    }

    /** synchrone calls */

    public static Serializable callMethod(Class clazz, String methodName) {
        List<Serializable> args = new ArrayList();
        return callMethod(clazz, methodName, args);
    }

    public static Serializable callMethod(Class clazz, String methodName, Serializable argument) {
        List<Serializable> args = new ArrayList();
        args.add(argument);
        return callMethod(clazz, methodName, args);
    }

    public static Serializable callMethod(Class clazz, String methodName, Serializable argument1, Serializable argument2) {
         List<Serializable> args = new ArrayList();
        args.add(argument1);
        args.add(argument2);
        return callMethod(clazz, methodName,  args);
    }

    public static Serializable callMethod(Class clazz, String methodName, List<Serializable> arguments) {
        GaiaDAOAgent daoagent = getAvailableDAOAgent();
        /** thread */
        DAOCallerAgent.DAOCallBehaviour request = new DAOCallerAgent.DAOCallBehaviour(daoagent.getAID(), clazz.getName(), methodName, arguments, false, false);

        daoCallerAgent.addBehaviour(new ThreadedBehaviourFactory().wrap(request));
        ACLMessage msg = daoCallerAgent.blockingReceive();

        try {
            if (msg.getConversationId().equals(DAO_QUERY_REPLY)){
                return msg.getContentObject();
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
            logger.error(msg.getConversationId());
            logger.error(msg.getContent());
        }
        return null;
    }

    public static void updatePosition(ReportObjectNotification reportObjectNotification){
        if (positionCallerAgent!=null){
            AID aid=GaiaPositionAgent.getPositionAgent(positionCallerAgent,reportObjectNotification.getId());
            if (aid==null){
                /** create the caller agent if needed */
                CreateAgentBehaviour createAgent=new CreateAgentBehaviour(positionCallerAgent, GaiaPositionAgent.class.getSimpleName()+"-"+reportObjectNotification.getId(), GaiaPositionAgent.class.getName(),Boolean.TRUE, null);
                positionCallerAgent.addBehaviour(createAgent);
                positionCallerAgent.blockingReceive();

                try {
                    aid = GaiaPositionAgent.getPositionAgent(positionCallerAgent,reportObjectNotification.getId());
                    while (aid == null) {
                        aid = GaiaPositionAgent.getPositionAgent(positionCallerAgent,reportObjectNotification.getId());
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
            /** send a message to position agent */
            try {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(aid);
                msg.setSender(positionCallerAgent.getAID());
                msg.setContentObject(reportObjectNotification);
                msg.setConversationId(POSITION_REFRESH);
                positionCallerAgent.send(msg);
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        } else {
            logger.error("POSITION NOT UPDATED : No Position Caller Agent found");
        }
    }

    public static void notifyReports(ReportObjectNotification reportObjectNotification){
        Agent agent = getAvailableDAOAgent();
        if (agent!=null){
            ReportAgent.notifyReports(agent, reportObjectNotification);
        }
    }


}
