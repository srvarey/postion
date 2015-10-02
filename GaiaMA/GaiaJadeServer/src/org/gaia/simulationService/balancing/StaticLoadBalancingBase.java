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

import jade.core.ServiceException;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.log4j.Logger;
import org.gaia.core.agents.AgentClassElement4SimStart;
import org.gaia.core.project.RemoteContainerConfiguration;
import org.gaia.simulationService.agents.LoadExecutionAgent;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This is the base class for every tailored static load balancing class.
 *
 */
public abstract class StaticLoadBalancingBase extends BaseLoadBalancing {

    private static final Logger logger = Logger.getLogger(StaticLoadBalancingBase.class);
    private static final long serialVersionUID = 8876791160586073658L;
    /**
     * The current agent list.
     */
    protected ArrayList<AgentClassElement4SimStart> currAgentList = null;
    /**
     * The current number of agents.
     */
    protected int currNumberOfAgents = 0;
    /**
     * The current number of container.
     */
    protected int currNumberOfContainer = 0;

    /**
     * Instantiates a new static load balancing base.
     */
    public StaticLoadBalancingBase(LoadExecutionAgent agent) {
        super(agent);
        // --- Which agents are to start ----------------------------
        currAgentList = currSimSetup.getAgentList();

        // --- Get number of expected agents and the number --------- 
        // --- of container, which are wanted for this setup --------
        currNumberOfAgents = currDisSetup.getNumberOfAgents();
        currNumberOfContainer = currDisSetup.getNumberOfContainer();
    }

    @Override
    public void onStart() {
        this.openLoadMonitor();
        this.setDefaults4RemoteContainerConfig();
    }

    /**
     * This Method will be called right after the end of the action() method and
     * will remove shut down the current agent.
     *
     * @return an integer code representing the termination value of the
     * behaviour.
     */
    @Override
    public int onEnd() {
        myAgent.doDelete();
        return super.onEnd();
    }

    /**
     * This method will start all agents defined in the agent list of
     * 'Agent-Start' from the 'Simulation-Setup'.
     */
    protected void startAgentsFromCurrAgentList() {
        this.startAgentsFromCurrAgentList(false);
    }

    /**
     * This method will start all agents defined in the agent list of
     * 'Agent-Start' from the 'Simulation-Setup'.
     *
     * @param printAgentStart2Console if true, every agent start will be printed
     * in the console
     */
    protected void startAgentsFromCurrAgentList(boolean printAgentStart2Console) {
        int counter = 1;

        for (Iterator<AgentClassElement4SimStart> iterator = currAgentList.iterator(); iterator.hasNext();) {
            AgentClassElement4SimStart agent2Start = iterator.next();
            // --- Check for start arguments -------------------------
            Object[] startArgs = this.getStartArguments(agent2Start);
            // --- Start the agent -----------------------------------
            logger.debug("Start Agent " + counter + ": " + agent2Start.getStartAsName() + " Class: " + agent2Start.getAgentClassReference());
            this.startAgent(agent2Start.getStartAsName(), agent2Start.getAgentClassReference(), startArgs);
            counter++;
        }
        logger.debug(counter + " Agents were started.");

    }

    /**
     * Sets the defaults4 remote container configuration.
     */
    private void setDefaults4RemoteContainerConfig() {

        RemoteContainerConfiguration projectRemContConfig = new RemoteContainerConfiguration();

        RemoteContainerConfig remConConf = new RemoteContainerConfig();
        remConConf.setPreventUsageOfUsedComputer(projectRemContConfig.isPreventUsageOfAlreadyUsedComputers());
        remConConf.setJadeShowGUI(projectRemContConfig.isShowJADErmaGUI());
        remConConf.setJvmMemAllocInitial(projectRemContConfig.getJvmMemAllocInitial());
        remConConf.setJvmMemAllocMaximum(projectRemContConfig.getJvmMemAllocMaximum());

        try {
            loadHelper.setDefaults4RemoteContainerConfig(remConConf);
        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
