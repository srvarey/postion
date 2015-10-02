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
package org.gaia.simulationService.balancing;

import jade.core.Location;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;
import org.gaia.core.agents.AgentClassElement4SimStart;
import org.gaia.simulationService.agents.LoadExecutionAgent;

/**
 * This class is the default class for the start of an agency of
 * <b>Agent.GUI</b>. In case, that you want to define a tailored method to start
 * an agent simulation, write a new class, which extends
 * {@link StaticLoadBalancingBase}.<br> This new class will be selectable within
 * the
 *
 *
 */
public class StaticLoadBalancing extends StaticLoadBalancingBase {

    private static final long serialVersionUID = -6884445863598676300L;

    public StaticLoadBalancing(LoadExecutionAgent agent) {
        super(agent);
    }

    @Override
    public void doBalancing() {

        if (!currDisSetup.isDoStaticLoadBalancing()) {
            // --- This is the very default case -------------------------
            // --- => Just start all defined agents ----------------------
            //
            this.startAgentsFromCurrAgentList();

        } else {
            // -----------------------------------------------------------
            // --- The case if no specific distribution is required: -----
            // --- => Start the required number of remote container  -----
            // --- and distribute the agents, defined in 			 -----
            // --- 'this.currAgentList' to these container			 -----
            // --- => The Agents defined in the visualization-setup  -----
            // --- will be distributed as well 					 	 -----
            // -----------------------------------------------------------

            // --- Just in case, that we don't have enough information ---
            if (currNumberOfContainer == 0) {
                if (currNumberOfAgents != 0) {
                    int noAgentsMax = currThresholdLevels.getThNoThreadsH();
                    currNumberOfContainer = (int) Math.ceil(((float) currNumberOfAgents / (float) noAgentsMax)) + 1;
                }
            }

            if (currNumberOfContainer <= 1) {
                // --- Just start all defined agents ---------------------
                this.startAgentsFromCurrAgentList();
                return;
            }

            // --- If we know how many container are needed, start them --
            Vector<String> locationNames = null;
            int cont4DisMax = 0;
            int cont4DisI = 0;
            HashMap<String, Location> newContainerLocations = this.startNumberOfRemoteContainer(currNumberOfContainer - 1, true, null);
            if (newContainerLocations != null) {
                locationNames = new Vector<>(newContainerLocations.keySet());
                cont4DisMax = newContainerLocations.size();
            }

            // --- merge all agents, which have to be started ------------
            Vector<AgentClassElement4SimStart> currAgentListMerged = new Vector<>();
            if (currAgentList != null) {
                currAgentListMerged.addAll(currAgentList);
            }

            // --- start the distribution of the agents to the locations -
            for (Iterator<AgentClassElement4SimStart> iterator = currAgentListMerged.iterator(); iterator.hasNext();) {
                // --- Get the agent, which has to be started ------------
                AgentClassElement4SimStart agent2Start = iterator.next();
                // --- Check for start arguments -------------------------
                Object[] startArgs = this.getStartArguments(agent2Start);

                if (locationNames == null) {
                    // --- Just start the agent locally ------------------
                    this.startAgent(agent2Start.getStartAsName(), agent2Start.getAgentClassReference(), startArgs, null);

                } else {
                    // --- Set the location for the agent ----------------
                    String containerName = locationNames.get(cont4DisI);
                    Location location = newContainerLocations.get(containerName);
                    cont4DisI++;
                    if (cont4DisI >= cont4DisMax) {
                        cont4DisI = 0;
                    }
                    // --- finally start the agent -----------------------
                    this.startAgent(agent2Start.getStartAsName(), agent2Start.getAgentClassReference(), startArgs, location);

                }
            } // --- end for
        } // --- end if

    }
}
