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
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.reports.Position;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin
 */
public class ObjectListFinderAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private List<Integer> ids;
    private String className;
    private HashMap<Integer, Serializable> objectMap;
    private Method idGetter;
    private String sDate;
    private String sFirstDate;
    private static final Logger logger = Logger.getLogger(ObjectListFinderAgent.class);

    /**
     * list of Finder Agent
     *
     * @param ids
     * @param className
     * @param aid
     */
    public ObjectListFinderAgent() {
        super(null, null);
    }

    public ObjectListFinderAgent(AID aid, jade.util.leap.List args) {
        super(aid, null);
        try {
            if (args != null) {
                if (args.size() >= 2) {

                    String listId = args.get(0).toString();
                    this.ids = new ArrayList();
                    if (!listId.isEmpty()) {
                        String str[] = listId.split(";");
                        for (int i = 0; i < str.length; i++) {
                            this.ids.add(Integer.parseInt(str[i]));
                        }
                    }
                    this.className = (String) args.get(1);

                    if (args.size() >= 3 && args.get(2) != null) {
                        this.sDate = args.get(2).toString();

                        if (args.size() >= 4 && args.get(3) != null) {
                            this.sFirstDate = args.get(3).toString();
                        }
                    }
                }
            }
            this.objectMap = new HashMap();
            idGetter = IntrospectUtil.getIdGetter(className);
        } catch (Exception e) {
            logger.error("Error " + e.getMessage());
        }
    }

    @Override
    protected void setup() {
        super.setup();

        /**
         * Add a TickerBehaviour that schedules a request to seller agents every
         * minute
         */
        addBehaviour(new RequestBehaviour());
        addBehaviour(new MessagingServer());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

    private class MessagingServer extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                /**
                 * Message received. Process it.
                 */
                try {
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        if (msg.getConversationId().equals(OBJECT_REPLY)) {

                            Serializable object = msg.getContentObject();
                            if (object != null) {
                                Integer id = (Integer) idGetter.invoke(object, null);
                                objectMap.put(id, object);
                            }
                            /**
                             * reply when filled
                             */
                            if (objectMap.size() == ids.size()) {
                                ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                                request.addReceiver(clientAID);
                                request.setSender(myAgent.getAID());
                                request.setConversationId(OBJECTS_REPLY);
                                request.setContentObject(objectMap);
                                myAgent.send(request);
                                myAgent.doDelete();
                            }
                        }
                    } else {
                        ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                        request.addReceiver(clientAID);
                        request.setSender(myAgent.getAID());
                        request.setConversationId(OBJECTS_REPLY);
                        request.setContent(msg.getContent());
                        request.setPerformative(ACLMessage.FAILURE);
                        myAgent.send(request);
                        myAgent.doDelete();
                        System.err.println("Object List Finder error " + msg.getContent());
                    }
                } catch (UnreadableException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
                    System.err.println(e.getMessage());
                    logger.error("Error " + e.getMessage());
                }
            } else {
                block();
            }
        }
    }

    public class RequestBehaviour extends OneShotBehaviour {

        @Override
        public void action() {

            try {

                if (false) {
                    for (int i = 0; i < ids.size(); i++) {

                        jade.util.leap.List argList = new jade.util.leap.ArrayList();
                        argList.add(ids.get(i).toString());
                        argList.add(className);
                        if (className.equals(Position.class.getName()) && sDate != null) {
                            argList.add(sDate);
                            if (sFirstDate != null) {
                                argList.add(sFirstDate);
                            }
                            sendAgentCreationrequest(PositionFinderAgent.class, argList);
                        } else {
                            sendAgentCreationrequest(ObjectFinderAgent.class, argList);
                        }
                    }

                } else {// without individual agents
                    Class clazz = Class.forName(className);
                    Date date = HibernateUtil.dateFormat.parse(sDate);
                    Date firstDdate = null;
                    if (sFirstDate!=null){
                        firstDdate = HibernateUtil.dateFormat.parse(sFirstDate);
                    }
                    for (int i = 0; i < ids.size(); i++) {
                        Serializable object = null;
                        if (className.equals(Position.class.getName())) {
                            if (sFirstDate == null) {
                                object = PositionBuilder.getPositionAndPositionHistory(ids.get(i), date);
                            } else {
                                object = PositionBuilder.getPositionAndTwoPositionHistories(ids.get(i), date, firstDdate);
                            }
                        } else {

                            object = HibernateUtil.getObject(clazz, ids.get(i));
                        }
                        objectMap.put(ids.get(i), object);
                    }
                    ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                    request.addReceiver(clientAID);
                    request.setSender(myAgent.getAID());
                    request.setConversationId(OBJECTS_REPLY);
                    request.setContentObject(objectMap);
                    myAgent.send(request);
                    myAgent.doDelete();
                }
            } catch (Exception e) {
                logger.error("Error " + e.getMessage());
            }
        }
    }
}
