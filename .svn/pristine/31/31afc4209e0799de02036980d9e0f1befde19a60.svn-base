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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.audit.AuditAccessor;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.DAOCall;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin
 */
public class GaiaDAOAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private static ThreadedBehaviourFactory threadedBehaviour;
    private Boolean isRunning=Boolean.FALSE;
    private static final Logger logger = Logger.getLogger(GaiaDAOAgent.class);
    private static XStream xstream;

    public GaiaDAOAgent(AID clientAID, jade.util.leap.List args) {
        super(clientAID, null);
        xstream = new XStream(new StaxDriver());

    }

    public Boolean isRunning() {
        return isRunning;
    }

    @Override
    protected void setup() {
        HibernateUtil.getSession();
        super.setup();
        threadedBehaviour = new ThreadedBehaviourFactory();
        this.addBehaviour(threadedBehaviour.wrap(new DAOQueryBehaviour()));
        DAOAgentPool.register(this);
        registerService(GaiaDAOAgent.class.getSimpleName(), GaiaDAOAgent.class.getSimpleName(),this);
    }

    @Override
    protected void takeDown() {

        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            logger.error("Error "+StringUtils.formatErrorMessage(fe));
        }
        DAOAgentPool.unregister(this);
        super.takeDown();
    }

    public class DAOQueryBehaviour extends CyclicBehaviour {

        @Override
        public void action() {
            String args;
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                /**
                 *  Message received. Process it.
                 */
                if (msg.getPerformative() != ACLMessage.FAILURE) {
                    if (msg.getConversationId().equals(DAO_QUERY)) {
                        DAOCall call=null;
                        try {
                            ((GaiaDAOAgent)myAgent).isRunning=Boolean.TRUE;
                            Serializable content = msg.getContentObject();
                            call = (DAOCall) content;
                            Method method=null;

                            try {

                                /**
                                 * reads arguments.
                                 */
                                Class clazz = Class.forName(call.getClassName());
                                Class[] argumentClasses = new Class[call.getArguments().size()];
                                Object[] arguments = new Object[call.getArguments().size()];
                                boolean hasNullArguments=false;
                                args=StringUtils.EMPTY_STRING;
                                for (int i = 0; i < call.getArguments().size(); i++) {
                                    Object argument =null;
                                    if (call.getArguments().get(i)!=null){
                                        argumentClasses[i] = call.getArguments().get(i).getClass();
                                        argument = argumentClasses[i].cast(call.getArguments().get(i));
                                        args+=argument.toString()+StringUtils.COMMA;
                                    } else {
                                        hasNullArguments=true;
                                    }
                                    arguments[i] = argument;

                                }
                                logger.debug("CALL OF "+call.getClassName()+StringUtils.DOT+call.getMethodName()+"("+(args.isEmpty()?args:args.substring(0,args.length()-1))+")");
                                /**
                                 *  looks for method.
                                 */
                                if (hasNullArguments){
                                    Method[] methods = clazz.getMethods();
                                    for (Method _method : methods){
                                        if (_method.getName().equalsIgnoreCase(call.getMethodName()) && _method.getGenericParameterTypes().length==call.getArguments().size()){
                                            method=_method;
                                        }
                                    }
                                }else {
                                    try {
                                        method = clazz.getMethod(call.getMethodName(), argumentClasses);
                                    } catch (java.lang.NoSuchMethodException no){
                                        Method[] methods = clazz.getMethods();
                                        for (Method _method : methods){
                                            if (_method.getName().equalsIgnoreCase(call.getMethodName()) && _method.getGenericParameterTypes().length==call.getArguments().size()){
                                                method=_method;
                                            }
                                        }
                                    }
                                }

                                /**
                                 *  CALL OF THE METHOD .
                                 */
                                Object result=null;
                                if (method!=null){
                                    if (!Modifier.isStatic(method.getModifiers())){
                                        logger.error("DAO ERROR "+call.getClassName()+StringUtils.DOT+call.getMethodName()+" IS NOT STATIC!");
                                    } else {
                                        result = method.invoke(null, arguments);
                                      /*=========================================*/
                                        if (call.getIsXMLSerialized()){
                                            AuditAccessor.unLazyObjectRecursively(result);
                                            result = xstream.toXML(result);
                                        }
                                    }
                                } else {
                                    logger.error("DAO ERROR "+call.getClassName()+StringUtils.DOT+call.getMethodName()+" NOT FOUND");
                                }

                                if (!call.isAsynchrone()){
                                    ACLMessage reply = msg.createReply();
                                    reply.setConversationId(DAO_QUERY_REPLY);
                                    reply.setSender(myAgent.getAID());
                                    reply.setContentObject((Serializable) result);
                                    myAgent.send(reply);
                                }

                            } catch (ClassNotFoundException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | IOException e) {
                                if (!call.isAsynchrone()){
                                    ACLMessage reply = msg.createReply();
                                    reply.setConversationId(DAO_QUERY_REPLY);
                                    reply.setSender(myAgent.getAID());
                                    reply.setPerformative(ACLMessage.FAILURE);
                                    reply.setContentObject(StringUtils.formatErrorMessageWithCause(e));
                                    myAgent.send(reply);
                                }
                                logger.error("DAO ERROR "+call.getClassName()+StringUtils.DOT+call.getMethodName());
                                logger.error(StringUtils.formatErrorMessageWithCause(e));
                            }
                        }catch (Exception e){
                            logger.error("DAO ERROR "+call.getClassName()+StringUtils.DOT+call.getMethodName());
                            logger.error(StringUtils.formatErrorMessage(e));
                        }

                        ((GaiaDAOAgent)myAgent).isRunning=Boolean.FALSE;
                    }
                }
            } else {
                block();
            }
        }
    }


    public static AID getDAOQueryAgentFromDF() {

        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType("GaiaDAOAgent");
        serviceDescription.setName("GaiaDAOAgent");
        template.addServices(serviceDescription);
        try {
            DFAgentDescription[] result = DFService.search(DAOAgentPool.getAvailableDAOAgent(), template);
            List<DFAgentDescription> list = new ArrayList(Arrays.asList(result));
            for (DFAgentDescription desc : result) {
                if (desc.getName().equals(DAOAgentPool.getAvailableDAOAgent().getAID())) {
                    list.remove(desc);
                }
            }
            if (list.size() > 0) { /** get any */
                int i = (int) Math.round((Math.random() * list.size() - .5));
                return list.get(i).getName();
            }
        } catch (Exception e) {
           logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }


}
