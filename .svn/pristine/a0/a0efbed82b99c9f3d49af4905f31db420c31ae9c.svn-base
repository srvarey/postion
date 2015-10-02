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
package org.gaia.jade.ontology;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.ServiceException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.wrapper.AgentContainer;
import org.apache.log4j.Logger;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.ontology.DistributionOntology;

/**
 *
 * @author Benjamin
 */
public class GaiaMobilityAgent extends SimulationAgent implements GaiaVocabulary{

    private static final long serialVersionUID = 1L;
    public static String codecName;
    public static String ontologyName;
    public AID clientAID;
    private static final Logger logger = Logger.getLogger(GaiaMobilityAgent.class);


    private GaiaMobilityAgent() {
        super();
    }

    public GaiaMobilityAgent(AID clientAID, jade.util.leap.List args) {
        super();
        this.clientAID = clientAID;
    }

    @Override
    protected void setup() {
        super.setup();
        Ontology ontology = DistributionOntology.getInstance();
        ontologyName = ontology.getName();
        getContentManager().registerOntology(ontology);
        Codec codec = new SLCodec();
        codecName = codec.getName();
        getContentManager().registerLanguage(codec);
        logger.debug(getAID().getName() + " is ready.");
    }

    public static void registerService(String type, String name, Agent agent) {
        /** publish market data service */
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(agent.getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        sd.setName(name);
        dfd.addServices(sd);
        try {
            DFService.register(agent, dfd);
        } catch (FIPAException fe) {
            logger.error(fe.getMessage());
        }
    }


    public void sendAgentCreationrequest(Class clazz, jade.util.leap.List args) {
        sendAgentCreationrequest(clazz,args,this);
    }

    public static void sendAgentCreationrequest(Class clazz, jade.util.leap.List args, Agent myAgent ) {
        try {

            AgentCreation create = new AgentCreation();
            create.setAgentClassName(clazz.getName());
            create.setAgentName(clazz.getSimpleName());
            create.setArgs(args);

            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(new AID("server.load", AID.ISLOCALNAME));
            msg.setSender(myAgent.getAID());
            msg.setContentObject(create);
            msg.setConversationId(AGENT_CREATION_REQUEST);

            myAgent.send(msg);
        } catch (Exception e) {
          logger.error(e);
        }
    }

    @Override
    public void setMigration(Location newLocation) {
        super.setMigration(newLocation);
        this.doMove(newLocation);
    }

    @Override
    protected void beforeMove() {
        super.beforeMove();
    }

    @Override
    protected void afterMove() {
        super.afterMove();

        AgentContainer cont = this.getContainerController();
        try {
            logger.debug(this.getAID().getName() + " moved to " + cont.getContainerName());
        } catch (Exception e) {
           logger.error("Error "+ e.getMessage());
        }

        SimulationServiceHelper simHelper;
        try {
            simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            if (simHelper.getManagerAgent() == null) {
                simHelper.setManagerAgent(this.getAID());

                // --- Start Behaviours -----------------------------
            }
        } catch (ServiceException e) {
            logger.error("Error "+ e.getMessage());
        }
    }

    @Override
    protected void takeDown() {
        /** Printout a dismissal message */
       logger.debug(getAID().getName() + " terminating.");
    }

    public AID[] getAgentFromDF(String type, String name) {

        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType(type);
        sd.setName(name);
        template.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, template);
            AID[] aids = new AID[result.length];
            for (int i = 0; i < result.length; i++) {
                aids[i] = result[i].getName();
            }
            return aids;
        } catch (Exception e) {
           logger.error("Error "+ e.getMessage());
        }
        return new AID[0];
    }
}
