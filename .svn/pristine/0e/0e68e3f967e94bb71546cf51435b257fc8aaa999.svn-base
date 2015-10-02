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
package org.gaia.core.agents.behaviour;

import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.ServiceException;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.JADEAgentManagement.ShutdownPlatform;
import jade.lang.acl.ACLMessage;
import org.apache.log4j.Logger;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;

/**
 * This behaviour class will be used by the UtilityAgent, if JADE should be shut
 * down.<br> Actually a message to the AMS will be send and the AMS will do the
 * shut down of the platform.
 * 
 *
 */
public class PlatformShutdownBehaviour extends OneShotBehaviour {

    private static final Logger logger = Logger.getLogger(PlatformShutdownBehaviour.class);
    private static final long serialVersionUID = 1921236046994970137L;

    @Override
    public void action() {

        if (simulationServiceIsRunning()) {
            // --- Stop all simulation-agents via Simulation Service ----------
            try {
                SimulationServiceHelper simHelper = (SimulationServiceHelper) myAgent.getHelper(SimulationService.NAME);
                simHelper.stopSimulationAgents();
            } catch (ServiceException e) {
                logger.error(e);
            }
        }

        myAgent.getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
        myAgent.getContentManager().registerOntology(JADEManagementOntology.getInstance());

        ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
        msg.addReceiver(myAgent.getAMS());
        msg.setOntology(JADEManagementOntology.NAME);
        msg.setLanguage(FIPANames.ContentLanguage.FIPA_SL0);
        msg.setProtocol(FIPANames.InteractionProtocol.FIPA_REQUEST);
        Action a = new Action();
        a.setActor(myAgent.getAMS());
        a.setAction(new ShutdownPlatform());
        try {
            myAgent.getContentManager().fillContent(msg, a);
        } catch (CodecException | OntologyException e) {
            logger.error(e);
        }
        myAgent.send(msg);
        myAgent.doDelete();
    }

    /**
     * Checks if the simulations service is running or not
     *
     * @return
     */
    private boolean simulationServiceIsRunning() {

        try {
            @SuppressWarnings("unused")
            SimulationServiceHelper simHelper = (SimulationServiceHelper) myAgent.getHelper(SimulationService.NAME);
            return true;
        } catch (ServiceException e) {
            //e.printStackTrace();
            return false;
        }
    }
}
