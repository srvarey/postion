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
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin
 */
public class ObjectFinderAgent extends GaiaMobilityAgent implements GaiaVocabulary {


    private static final Logger logger = Logger.getLogger(MarketDataAgent.class);
    private static final long serialVersionUID = 1L;
    String className;
    Integer id;

    public ObjectFinderAgent(AID aid, jade.util.leap.List args) {
        super(aid,null);
        this.id = Integer.parseInt(args.get(0).toString());
        this.className = args.get(1).toString();
    }

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new MessageErrorListenerBehaviour());
        addBehaviour(new CreateBehaviour());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

    public class CreateBehaviour extends OneShotBehaviour {

        @Override
        public void action() {
            /** send message */
            try {
                Class clazz = Class.forName(className);
                Serializable object = HibernateUtil.getObject(clazz, id);

                ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                request.addReceiver(clientAID);
                request.setSender(myAgent.getAID());
                request.setConversationId(OBJECT_REPLY);
                request.setContentObject(object);
                myAgent.send(request);
            } catch (Exception e) {
                logger.error("Error "+ e.getMessage());
            }
            myAgent.doDelete();
        }
    }
}
