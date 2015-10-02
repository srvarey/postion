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
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.util.leap.List;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.gaia.jade.ontology.AgentCreation;
import org.gaia.simulationService.ontology.DistributionOntology;

/**
 *
 * @author Benji
 */
public class CreateAgentBehaviour extends OneShotBehaviour {

    private static final Logger logger = Logger.getLogger(CreateAgentBehaviour.class);
    String agentName;
    String agentClassName;
    List args;
    Codec codec = new SLCodec();
    Ontology ontology = DistributionOntology.getInstance();
    Boolean addNoSuffixToName = Boolean.FALSE;

    public CreateAgentBehaviour(Agent agent, String agentName, String agentClassName, Boolean addNoSuffixToName, List args) {
        super(agent);
        this.agentName = agentName;
        this.agentClassName = agentClassName;
        if (addNoSuffixToName != null) {
            this.addNoSuffixToName = addNoSuffixToName;
        }
        this.args = args;
    }

    @Override
    public void action() {
        // send message

        AgentCreation create = new AgentCreation();
        create.setAgentClassName(agentClassName);
        create.setAgentName(agentName);
        create.setAddNoSuffixToName(addNoSuffixToName);
        create.setArgs(args);

        Action act = new Action();
        act.setActor(myAgent.getAID());
        act.setAction(create);

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(new AID("server.load", AID.ISLOCALNAME));
        msg.setSender(myAgent.getAID());

        try {
            msg.setContentObject(create);
            myAgent.send(msg);
        } catch (NullPointerException | IOException e) {
            logger.error(e);
        }
    }
}
