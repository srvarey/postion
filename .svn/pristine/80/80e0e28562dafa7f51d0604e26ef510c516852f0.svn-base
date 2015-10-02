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
package org.gaia.core.agents.behaviour;

import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShowGui;
import jade.lang.acl.ACLMessage;
import org.apache.log4j.Logger;

/**
 * This behaviour class will be used by the UtilityAgent, if the DF should be
 * displayed.<br> Actually a message to the DF will be send and the DF will
 * appear.
 *
 *
 */
public class ShowDFBehaviour extends OneShotBehaviour {

    private static final long serialVersionUID = 1921236046994970137L;
    private static final Logger logger = Logger.getLogger(ShowDFBehaviour.class);

    @Override
    public void action() {

        myAgent.getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
        myAgent.getContentManager().registerOntology(JADEManagementOntology.getInstance());

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(myAgent.getDefaultDF());
        msg.setOntology(JADEManagementOntology.NAME);
        msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        Action a = new Action();
        a.setActor(myAgent.getDefaultDF());
        a.setAction(new ShowGui());
        try {
            myAgent.getContentManager().fillContent(msg, a);
        } catch (CodecException | OntologyException e) {
            logger.error(e);
        }
        myAgent.send(msg);
        myAgent.doDelete();
    }
}
