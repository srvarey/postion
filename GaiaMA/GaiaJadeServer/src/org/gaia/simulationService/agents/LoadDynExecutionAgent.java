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
import jade.core.behaviours.OneShotBehaviour;
import java.lang.reflect.InvocationTargetException;
import org.apache.log4j.Logger;
import org.gaia.core.project.DistributionSetup;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;
import org.gaia.simulationService.balancing.DynamicLoadBalancing;
import org.gaia.simulationService.balancing.DynamicLoadBalancingBase;

/**
 * This agent manages the start, pause, restart or stop of a simulation. For the
 * start of an agency, the agent will take the parameters set in the current
 * projects {@link DistributionSetup}, so that an eventually set class of the
 * type {@link DynamicLoadBalancingBase} can be applied.
 *
 *
 */
public class LoadDynExecutionAgent extends Agent {

    private static final Logger logger = Logger.getLogger(LoadDynExecutionAgent.class);
    private static final long serialVersionUID = 7768569966599564839L;
    /**
     * The Constant BASE_ACTION_Start.
     */
    public final static int BASE_ACTION_Start = 0;
    /**
     * The Constant BASE_ACTION_Pause.
     */
    public final static int BASE_ACTION_Pause = 1;
    /**
     * The Constant BASE_ACTION_Restart.
     */
    public final static int BASE_ACTION_Restart = 2;
    LoadMeasureAgent lma;
    private int startArg;

    @Override
    protected void setup() {
        super.setup();

        Object[] startArgs = getArguments();
        startArg = (Integer) startArgs[0];


        DistributionSetup currDisSetup = new DistributionSetup();
        currDisSetup.setDoDynamicLoadBalancing(true);
        currDisSetup.setDynamicLoadBalancingClass(DynamicLoadBalancing.class.getName());
        currDisSetup.setExcludeLocal(true);
        currDisSetup.setLocalOnly(false);
        this.addBehaviour(new DoStartAction(this));
    }

    /**
     * The Class DoStartAction.
     */
    private class DoStartAction extends OneShotBehaviour {

        private static final long serialVersionUID = -559016842727286483L;

        public DoStartAction(Agent agent) {
            super(agent);
        }

        @Override
        public void action() {
            switch (startArg) {
                case BASE_ACTION_Start:
                    DynamicLoadBalancingBase dynamicBalancing = getStartAndDynamicLoadBalancingClass(lma);
                    if (dynamicBalancing != null) {
                        myAgent.addBehaviour(dynamicBalancing);
                    } else {
                        myAgent.doDelete();
                    }
                    break;

                case BASE_ACTION_Pause:
                    setPauseSimulation(true);
                    myAgent.doDelete();
                    break;
                case BASE_ACTION_Restart:
                    setPauseSimulation(false);
                    myAgent.doDelete();
                    break;
            }
        }
    }

    /**
     * This method will pause or restart the current simulation.
     *
     * @param pause the pause simulation
     */
    private void setPauseSimulation(boolean pause) {

        SimulationServiceHelper simHelper = null;
        try {
            simHelper = (SimulationServiceHelper) getHelper(SimulationService.NAME);
            simHelper.setPauseSimulation(pause);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method will initiate the one shot behaviour of the type
     * {@link DynamicLoadBalancingBase} in order to start the agency.
     *
     * @return the start and static load balancing instance
     */
    private DynamicLoadBalancingBase getStartAndDynamicLoadBalancingClass(Agent myAgent) {
        try {
            @SuppressWarnings("unchecked")
            Class<? extends DynamicLoadBalancingBase> staLoBaClass = (Class<? extends DynamicLoadBalancingBase>) Class.forName(DynamicLoadBalancing.class.getName());
            return staLoBaClass.getDeclaredConstructor(new Class[]{myAgent.getClass()}).newInstance(new Object[]{myAgent});
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
            logger.error(e);
        }
        return null;
    }

    public void setLoadMeasureAgent(LoadMeasureAgent lma) {
        this.lma = lma;
    }
}
