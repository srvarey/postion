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
package org.gaia.simulationService.agents;

import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import org.apache.log4j.Logger;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.LoadServiceHelper;

/**
 * This is a simple agent, which is able to start a new remote container, if the
 * background system is set up.<br> After doing this one time, the agents will
 * be suspended.<br> In order to restart this agent, open the JADE rma-Agent,
 * select the agent in its container and<br> press the 'Resume' button in the
 * menu.
 *
 */
public class RemoteStarterAgent extends Agent {

    private static final long serialVersionUID = 3649851139158388559L;
    private static final Logger logger = Logger.getLogger(RemoteStarterAgent.class);

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        this.addBehaviour(new StarterCycle());
    }

    /**
     * The Class StarterCycle.
     */
    private class StarterCycle extends CyclicBehaviour {

        private static final long serialVersionUID = -3389907697703023520L;

        /* (non-Javadoc)
         * @see jade.core.behaviours.Behaviour#action()
         */
        @Override
        public void action() {
            LoadServiceHelper loadHelper;
            try {
                loadHelper = (LoadServiceHelper) myAgent.getHelper(LoadService.NAME);
            } catch (ServiceException e) {
               logger.error(e);
            }
        }
    }
}
