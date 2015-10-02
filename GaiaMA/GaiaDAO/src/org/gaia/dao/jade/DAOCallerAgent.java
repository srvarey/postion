package org.gaia.dao.jade;

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
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.DAOCall;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.simulationService.ontology.DistributionOntology;

/**
 *
 * @author Benjamin Frerejean
 */
public class DAOCallerAgent extends Agent implements GaiaVocabulary {

    private static AID gaiaDAOAgent;
    private static DAOCallerAgent myCallerAgent;
    public String codecName;
    public String ontologyName;
    public Serializable result;
    private static XStream xstream;
    private static final Logger logger = Logger.getLogger(DAOCallerAgent.class);

    /**
     * share the same language
     */
    public DAOCallerAgent() {
        Ontology ontology = DistributionOntology.getInstance();
        ontologyName = ontology.getName();
        getContentManager().registerOntology(ontology);
        Codec codec = new SLCodec();
        codecName = codec.getName();
        getContentManager().registerLanguage(codec);
        xstream = new XStream(new StaxDriver());
    }

    /**
     * call DAOAgent with name and adress from Agent.
     */
    public static AID getDAOAgent() {
        int i = 0;
        if (gaiaDAOAgent == null) {
            gaiaDAOAgent = getDAOQueryAgentFromDF();
            int nbOfLoop = 200;
            while (gaiaDAOAgent == null) {
                i = 0;
                GaiaMobilityAgent.sendAgentCreationrequest(GaiaDAOAgent.class, null, myCallerAgent);
                /**
                 * timer
                 */
                try {
                    while (gaiaDAOAgent == null && i < nbOfLoop) {
                        gaiaDAOAgent = getDAOQueryAgentFromDF();
                        Thread.sleep(100);
                        i++;
                    }
                } catch (Exception e) {
                }
                if (i >= nbOfLoop) {
                    logger.error("Failed to launch GaiaDAOAgent.");
                    logger.error("Relaunching...");
                }
                getDAOCallerAgent().blockingReceive(2000);
            }
        }
        return gaiaDAOAgent;
    }

    public static DAOCallerAgent getDAOCallerAgent() {
        if (myCallerAgent == null) {
            myCallerAgent = new DAOCallerAgent();
            GaiaAgentController reportAgentController = GaiaAgentController.getInstance();
            myCallerAgent = (DAOCallerAgent) reportAgentController.createLocalAgent(DAOCallerAgent.class);
        }
        return myCallerAgent;
    }

    /**
     * asynchrone calls.
     *
     * @param clazz
     * @param methodName
     * @param argument
     */
    public static void callAsynchroneMethod(Class clazz, String methodName, Serializable... argument) {
        List<Serializable> args = new ArrayList();
        args.addAll(Arrays.asList(argument));
        callAsynchroneMethod(clazz, methodName, args);
    }

    public static void callAsynchroneMethod(Class clazz, String methodName, List<Serializable> arguments) {
        AID daoagent = getDAOAgent();
        if (daoagent != null) {
            DAOCallerAgent.DAOCallBehaviour request = new DAOCallerAgent.DAOCallBehaviour(daoagent, clazz.getName(), methodName, arguments, true, false);
            getDAOCallerAgent().addBehaviour(new ThreadedBehaviourFactory().wrap(request));
        }
    }

    /**
     * synchrone calls.
     *
     * @param clazz
     * @param methodName
     * @param argument
     * @return
     */
    public static Serializable callMethod(Class clazz, String methodName, Serializable... argument) {
        List<Serializable> args = new ArrayList();
        args.addAll(Arrays.asList(argument));
        return callMethod(clazz, methodName, args, false);
    }

    /**
     * synchrone calls with XML serialization
     *
     * @param clazz
     * @param methodName
     * @param argument
     * @return
     */
    public static Serializable callMethodWithXMLSerialization(Class clazz, String methodName, Serializable... argument) {
        List<Serializable> args = new ArrayList();
        args.addAll(Arrays.asList(argument));
        return callMethod(clazz, methodName, args, true);

    }

    public static Serializable callMethod(Class clazz, String methodName, List<Serializable> arguments, Boolean isXMLSerialized) {

        AID daoagent = getDAOAgent();
        DAOCallBehaviour request = new DAOCallBehaviour(daoagent, clazz.getName(), methodName, arguments, false, isXMLSerialized);
        getDAOCallerAgent().addBehaviour(request);
        ACLMessage msg = getDAOCallerAgent().blockingReceive();
        try {
            Serializable object = msg.getContentObject();
            if (msg.getPerformative() == ACLMessage.FAILURE) {
                logger.error(object.toString());
                return null;
            }
            if (!isXMLSerialized) {
                return object;
            } else {
                String xml = (String) object;
                return (Serializable) xstream.fromXML(xml);
            }
        } catch (UnreadableException unreadable) {
            logger.error("ERROR SERVICE CALL FAILURE : " + clazz.getSimpleName() + StringUtils.DOT + methodName);
            logger.error("Unreadable:" + unreadable.getMessage());
            logger.error(StringUtils.formatErrorMessage(unreadable));
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static AID getDAOQueryAgentFromDF() {

        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(GaiaDAOAgent.class.getSimpleName());
        serviceDescription.setName(GaiaDAOAgent.class.getSimpleName());
        template.addServices(serviceDescription);
        try {
            Agent agent = getDAOCallerAgent();
            DFAgentDescription[] result = DFService.search(agent, template);
            /**
             * get any.
             */
            if (result.length > 0) {
                int i = (int) Math.round((Math.random() * result.length - .5));
                return result[i].getName();
            }

        } catch (FIPAException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static class DAOCallBehaviour extends OneShotBehaviour implements GaiaVocabulary {

        private final String className;
        private final String methodName;
        private final List arguments;
        private final AID DAOAgent;
        private final String ontologyName;
        private final String codecName;
        private final Boolean isAsynchrone;
        private final Boolean isXMLSerialized;

        public DAOCallBehaviour(AID daoAddress, String className, String methodName, java.util.List<Serializable> arguments, Boolean isAsynchrone, Boolean isXMLSerialized) {
            this.className = className;
            this.methodName = methodName;
            this.arguments = arguments;
            this.DAOAgent = daoAddress;
            this.isAsynchrone = isAsynchrone;
            this.isXMLSerialized = isXMLSerialized;

            Ontology ontology = DistributionOntology.getInstance();
            ontologyName = ontology.getName();
            Codec codec = new SLCodec();
            codecName = codec.getName();
        }

        /**
         * send request
         */
        @Override
        public void action() {
            sendDAOrequest(DAOAgent, className, methodName, arguments, isAsynchrone, isXMLSerialized);
        }

        public void sendDAOrequest(AID daoAddress, String className, String methodName, List arguments, Boolean isAsynchrone, Boolean isXMLSerialized) {
            try {

                DAOCall daoCall = new DAOCall();
                daoCall.setClassName(className);
                daoCall.setMethodName(methodName);
                daoCall.setArguments(arguments);
                daoCall.setIsAsynchrone(isAsynchrone);
                daoCall.setIsXMLSerialized(isXMLSerialized);

                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(daoAddress);
                msg.setSender(myAgent.getAID());
                msg.setContentObject(daoCall);
                msg.setConversationId(DAO_QUERY);

                myAgent.send(msg);
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
    }

    public static class DAOReceiveBehaviour extends OneShotBehaviour implements GaiaVocabulary {

        Serializable result = null;

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            try {
                if (msg != null) {
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        result = msg.getContentObject();
                        logger.info(result.toString());
                    }
                } else {
                    logger.error("Error in DAOReceiveBehaviour " + msg.getContent());
                }

            } catch (UnreadableException e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }

        public Serializable getResult() {
            return result;
        }
    }
}
