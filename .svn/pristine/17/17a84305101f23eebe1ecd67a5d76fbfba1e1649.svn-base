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
package org.gaia.jade.server;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import org.apache.log4j.Logger;
import org.gaia.jade.ontology.AgentCreation;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.jade.ontology.PricerAgentCreation;
import org.gaia.jade.ontology.PricingAgent;
import org.gaia.jade.ontology.PricingSetting;
import org.gaia.simulationService.ontology.DistributionOntology;

/**
 *
 * @author Benjamin
 */
public class GaiaAgentCreator extends Agent implements GaiaVocabulary {

    private static final Logger logger = Logger.getLogger(GaiaAgentCreator.class);
    private static final long serialVersionUID = 1L;
    private final Ontology ontology = DistributionOntology.getInstance();
    private final Codec codec = new SLCodec();

    public GaiaAgentCreator() {
        super();

    }
    public int index = 0;

    @Override
    protected void setup() {
        super.setup();

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        logger.debug(getAID().getName() + " is ready.");
        super.setup();
        addBehaviour(new Server());
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(GaiaAgentCreator.class.getSimpleName());
        sd.setName(getAID().getLocalName());
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            logger.error(fe);
        }

    }

    public static DFAgentDescription[] getService(String name, Agent a) {
        try {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription sd = new ServiceDescription();
            sd.setType(GaiaAgentCreator.class.getSimpleName());
            sd.setName(name);
            template.addServices(sd);
            return DFService.search(a, template);
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    @Override
    protected void takeDown() {
        logger.info(getAID().getName() + " terminating.");
    }

    private class Server extends CyclicBehaviour {

        public AgentContainer container = Application.getJadePlatform().jadeGetMainContainer();
        private int containerIndex = -1;//

        @Override
        public void action() {

            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));

            if (msg != null) {
                try {

                    if (msg.getConversationId().equalsIgnoreCase(GaiaVocabulary.AGENT_CREATION_REQUEST)) {
                        Serializable agentAction = msg.getContentObject();
                        AgentCreation crea = (AgentCreation) msg.getContentObject();

                        if (containerIndex == -1) {
                            String containerName = myAgent.getContainerController().getContainerName();
                            if (containerName.startsWith("remote")) {
                                containerName = containerName.substring(6);
                                containerIndex = Integer.parseInt(containerName);
                            }
                        }

                        String agentName = crea.getAgentName();
                        if (!crea.getAddNoSuffixToName()) {
                            agentName = agentName + "-" + containerIndex + "-" + index;
                        }

                        Class clazz = Class.forName(crea.getAgentClassName());

                        try {
                            AgentController controller = null;
                            if (agentAction instanceof PricerAgentCreation) {
                                PricerAgentCreation pcrea = (PricerAgentCreation) agentAction;
                                Constructor constr = clazz.getConstructor(AID.class, jade.util.leap.List.class, PricingSetting.class);
                                PricingAgent agent = (PricingAgent) constr.newInstance(msg.getSender(), pcrea.getArgs(), pcrea.getPricingSetting());
                                controller = myAgent.getContainerController().acceptNewAgent(agentName, agent);
                            } else {
                                Constructor constr = clazz.getConstructor(AID.class, jade.util.leap.List.class);
                                Agent agent = (Agent) constr.newInstance(msg.getSender(), crea.getArgs());
                                controller = myAgent.getContainerController().acceptNewAgent(agentName, agent);
                            }
                            controller.start();
                            index++;
                            ACLMessage reply = new ACLMessage(ACLMessage.REQUEST);
                            reply.addReceiver(msg.getSender());
                            reply.setSender(myAgent.getAID());
                            reply.setLanguage(codec.getName());
                            reply.setOntology(ontology.getName());
                            reply.setConversationId(GAIAAID);
                            crea.setAgentName(agentName);
                            reply.setContentObject(crea);
                            send(reply);

                        } catch (Exception e) {
                            logger.error("Class " + crea.getAgentClassName() + " not created", e);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                block();
            }
        }
    }
}
