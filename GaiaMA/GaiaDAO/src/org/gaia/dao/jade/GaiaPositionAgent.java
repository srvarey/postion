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
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import java.text.ParseException;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.Position;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.simulationService.ontology.ReportObjectNotification;

/**
 *
 * @author Benjamin
 The class GaiaPositionAgent if the agent responsible for updating a position line in real time and notify reports on change
 */
public class GaiaPositionAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private Position position;
    private Integer id;
     private static final Logger logger = Logger.getLogger(GaiaPositionAgent.class);

    public GaiaPositionAgent(AID clientAID, jade.util.leap.List args) {
        super(clientAID, null);
    }

    @Override
    protected void setup() {
        HibernateUtil.getSession();
        super.setup();
        this.addBehaviour(new PositionRefreshBehaviour());
        registerService(GaiaPositionAgent.class.getSimpleName(),this.getLocalName(),this);
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try {
            DFService.deregister(this);
        }  catch (FIPAException fe) {
            logger.error("Error "+ fe.getMessage());
        }
    }

    public static AID getPositionAgent(Agent agent,Integer positionId){
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(GaiaPositionAgent.class.getSimpleName());
        serviceDescription.setName(GaiaPositionAgent.class.getSimpleName()+"-"+positionId);
        template.addServices(serviceDescription);
        try {
            DFAgentDescription[] result = DFService.search(agent, template);
            if (result.length > 0) {
                return result[0].getName();
            }
        } catch (Exception e) {
            logger.error("DF Error looking for "+GaiaPositionAgent.class.getSimpleName()+"-"+positionId);
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }
    /**
    * PositionRefreshBehaviour listens to position refresh queries
    * it refreshes its position on demand and notifies reports when done
    */
    public class PositionRefreshBehaviour extends CyclicBehaviour {

        @Override
        public void action() {

            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                /*
                 *  Message received. Process it.
                 */
                if (msg.getPerformative() != ACLMessage.FAILURE) {
                    if (msg.getConversationId().equals(POSITION_REFRESH)) {
                        try {

                            ReportObjectNotification reportObjectNotification =(ReportObjectNotification) msg.getContentObject();

                            id=reportObjectNotification.getId();
                            if (position==null && id!=null){
                                position=(Position)HibernateUtil.getObject(Position.class, id);
                            }

                            /**
                             *  refreshes position.
                             */
                            Date fromDate =HibernateUtil.dateFormat.parse(reportObjectNotification.getFromDate());
                            if (position!=null){
                                PositionBuilder.updatePosition(position, fromDate);
                                /**
                                 * notify reports.
                                 */
                                ReportAgent.notifyReports(myAgent, reportObjectNotification);
                            }

                        } catch (UnreadableException | ParseException | NumberFormatException e) {
                           logger.error("Error "+ e.getMessage());
                        }
                    }
                }
            } else {
                block();
            }
        }
    }

}
