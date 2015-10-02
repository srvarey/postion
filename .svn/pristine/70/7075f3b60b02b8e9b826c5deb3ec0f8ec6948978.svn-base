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
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin
 */
public class PositionFinderAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    String className;
    Integer id;
    Date date;
    Date firstDdate;
    private static final Logger logger = Logger.getLogger(PositionFinderAgent.class);

    public PositionFinderAgent(AID aid, jade.util.leap.List args) {
        super(aid,null);
        this.id = Integer.parseInt(args.get(0).toString());
        this.className = args.get(1).toString();
        try {
            if (args.size()>=3 && args.get(2)!=null){
                this.date=HibernateUtil.dateFormat.parse(args.get(2).toString());
                if (args.size()>=4 && args.get(3)!=null){
                    this.firstDdate=HibernateUtil.dateFormat.parse(args.get(3).toString());
                }
            }
        }catch (Exception e){
         logger.error("Error "+ e.getMessage());

        }
    }
/**
 *  create position.
 */
    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new CreatePositionBehaviour());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }


    /**
     *  call agent to create position.
     */

    public class CreatePositionBehaviour extends OneShotBehaviour {



        @Override
        public void action() {
            /** send message */
            try {
                Serializable o = null;
                if (firstDdate==null){
                    o = PositionBuilder.getPositionAndPositionHistory(id, date);
                } else {
                    o = PositionBuilder.getPositionAndTwoPositionHistories(id, date, firstDdate);
                }

                ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                request.addReceiver(clientAID);
                request.setSender(myAgent.getAID());
                request.setConversationId(OBJECT_REPLY);
                request.setContentObject(o);
                myAgent.send(request);
            } catch (Exception e) {

                ACLMessage request = new ACLMessage(ACLMessage.AGREE);
                request.addReceiver(clientAID);
                request.setSender(myAgent.getAID());
                request.setConversationId(OBJECT_REPLY);
                request.setContent(e.getMessage());
                request.setPerformative(ACLMessage.FAILURE);
                myAgent.send(request);

            logger.error("Error "+ e.getMessage());

            }
            myAgent.doDelete();
        }
    }


}
