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
package org.gaia.simulationService;

import jade.core.AID;
import jade.core.IMTPException;
import jade.core.Service;
import java.util.Hashtable;
import java.util.Vector;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.agents.SimulationManagerAgent;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.sensoring.ServiceActuator;
import org.gaia.simulationService.sensoring.ServiceSensor;
import org.gaia.simulationService.transaction.EnvironmentManagerDescription;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * Here the remotely accessible methods of the SimulationService can be found.
 */
public interface SimulationServiceSlice extends Service.Slice {

    /**
     * The Constant SERVICE_SYNCH_GET_REMOTE_TIME.
     */
    static final String SERVICE_SYNCH_GET_REMOTE_TIME = "service-synch-get-remote-time";
    /**
     * The Constant SERVICE_SYNCH_SET_TIME_DIFF.
     */
    static final String SERVICE_SYNCH_SET_TIME_DIFF = "service-synch-set-time-diff";

    /**
     * Returns the remote time as long.
     *
     * @return the remote time
     * @throws IMTPException the IMTPException
     */
    public long getRemoteTime() throws IMTPException;

    /**
     * Sets the remote time difference in milliseconds for synchronised time.
     *
     * @param timeDifference the new remote time diff
     * @throws IMTPException the IMTPException
     */
    public void setRemoteTimeDiff(long timeDifference) throws IMTPException;
    // ----------------------------------------------------------
    // --- Method on the Manager-Agent --------------------------
    /**
     * The Constant SIM_SET_MANAGER_AGENT.
     */
    static final String SIM_SET_MANAGER_AGENT = "set-manager";

    /**
     * Sets the {@link EnvironmentManagerDescription} of the manager agent.
     *
     * @param envManager the new manager agent
     * @throws IMTPException the IMTPException
     * @see EnvironmentManagerDescription
     */
    public void setManagerAgent(EnvironmentManagerDescription envManager) throws IMTPException;
    // ----------------------------------------------------------
    // --- Methods on the EnvironmentModel ----------------------
    /**
     * The Constant SIM_SET_ENVIRONMENT_MODEL.
     */
    static final String SIM_SET_ENVIRONMENT_MODEL = "sim-set-environment-model";
    /**
     * The Constant SIM_STEP_SIMULATION.
     */
    static final String SIM_STEP_SIMULATION = "sim-step-simulation";
    /**
     * The Constant SIM_SET_ANSWERS_EXPECTED.
     */
    static final String SIM_SET_ANSWERS_EXPECTED = "sim-set-number-expected";
    /**
     * The Constant SIM_NOTIFY_AGENT.
     */
    static final String SIM_NOTIFY_AGENT = "sim-notify-agent";
    /**
     * The Constant SIM_NOTIFY_MANAGER.
     */
    static final String SIM_NOTIFY_MANAGER = "notify-manager";
    /**
     * The Constant SIM_NOTIFY_MANAGER_PUT_AGENT_ANSWERS.
     */
    static final String SIM_NOTIFY_MANAGER_PUT_AGENT_ANSWERS = "notify-manager-put-agent-answers";
    /**
     * The Constant SIM_SET_ENVIRONMENT_NEXT_PART.
     */
    static final String SIM_SET_ENVIRONMENT_NEXT_PART = "set-environment-next-part";
    /**
     * The Constant SIM_GET_ENVIRONMENT_NEXT_PARTS.
     */
    static final String SIM_GET_ENVIRONMENT_NEXT_PARTS = "get-environment-next-parts";
    /**
     * The Constant SIM_RESET_ENVIRONMENT_NEXT_PARTS.
     */
    static final String SIM_RESET_ENVIRONMENT_NEXT_PARTS = "reset-environment-next-parts";

    /**
     * Can be used in order to set and distribute an {@link EnvironmentModel}
     * without a direct notification to the involved agents.
     *
     * @param envModel the current or new EnvironmentModel instance
     * @param notifySensorAgents true, if the sensor agents should be also
     * notified
     * @throws IMTPException the IMTPException
     * @see EnvironmentModel
     */
    public void setEnvironmentModel(EnvironmentModel envModel, boolean notifySensorAgents) throws IMTPException;

    /**
     * Steps a simulation by using the current {@link EnvironmentModel} and the
     * number of expected changes / answers.
     *
     * @param envModel the current or new EnvironmentModel
     * @param aSynchron true, if the notification has to be done asynchronously
     * @throws IMTPException the iMTP exception
     * @see EnvironmentModel
     */
    public void stepSimulation(EnvironmentModel envModel, boolean aSynchron) throws IMTPException;

    /**
     * Sets the number of changes/answers expected from the simulation cycle.
     *
     * @param answersExpected the number of answers expected
     * @throws IMTPException the IMTPException
     */
    public void setAnswersExpected(int answersExpected) throws IMTPException;

    /**
     * Notifies a specified sensor agent by using an EnvironmentNotification.
     *
     * @param agentAID the agent AID
     * @param notification the notification
     * @return true, if successful
     * @throws IMTPException the IMTPException
     *
     * @see ServiceSensor
     * @see ServiceActuator
     * @see SimulationAgent
     * @see EnvironmentNotification
     */
    public boolean notifyAgent(AID agentAID, EnvironmentNotification notification) throws IMTPException;

    /**
     * Notifies a SimulationManager by using an EnvironmentNotification.
     *
     * @param notification the notification
     * @return true, if successful
     * @throws IMTPException the IMTPException
     * @see SimulationManagerAgent
     * @see EnvironmentNotification
     */
    public boolean notifyManager(EnvironmentNotification notification) throws IMTPException;

    /**
     * Sends the current set of agent answers to the manager agent of the
     * environment.
     *
     * @param allAgentAnswers the Hashtable of all agent answers
     * @throws IMTPException the IMTPException
     */
    public void notifyManagerPutAgentAnswers(Hashtable<AID, Object> allAgentAnswers) throws IMTPException;

    /**
     * Sends the local next parts of the environment-model to the
     * Main-Container.
     *
     * @param nextPartsLocal the Hashtable of local environment changes, coming
     * from different agents
     * @throws IMTPException the IMTPException
     */
    public void setEnvironmentInstanceNextPart(Hashtable<AID, Object> nextPartsLocal) throws IMTPException;

    /**
     * This method returns the complete environment-model-changes from the
     * Main-Container.
     *
     * @return the environment instance next parts
     * @throws IMTPException the IMTPException
     */
    public Hashtable<AID, Object> getEnvironmentInstanceNextParts() throws IMTPException;

    /**
     * This method resets the hash with the single environment-model-changes.
     *
     * @throws IMTPException the IMTPException
     */
    public void resetEnvironmentInstanceNextParts() throws IMTPException;
    // ----------------------------------------------------------
    // --- Methods to work on agents ----------------------------
    /**
     * The Constant SERVICE_STOP_SIMULATION_AGENTS.
     */
    static final String SERVICE_STOP_SIMULATION_AGENTS = "stop-simulation-agents";
    /**
     * The Constant SIM_PAUSE_SIMULATION.
     */
    static final String SIM_PAUSE_SIMULATION = "sim-pause";

    /**
     * Stops the simulation agents.
     *
     * @throws IMTPException the IMTPException
     */
    public void stopSimulationAgents() throws IMTPException;

    /**
     * Sets to pause the simulation.
     *
     * @param pauseSimulation the new pause simulation
     * @throws IMTPException the IMTPException
     */
    public void setPauseSimulation(boolean pauseSimulation) throws IMTPException;
    // ----------------------------------------------------------
    // --- Methods for load-informations of all containers ------
    /**
     * The Constant SERVICE_SET_AGENT_MIGRATION.
     */
    static final String SERVICE_SET_AGENT_MIGRATION = "set-agent-migration";

    /**
     * Sets the new locations to the agents.
     *
     * @param transferAgents the agents to migrate
     * @throws IMTPException the IMTPException
     * @see AID_Container
     */
    public void setAgentMigration(Vector<AID_Container> transferAgents) throws IMTPException;
    // ----------------------------------------------------------
    // --- Methods for updating display agents ------------------
    /**
     * The Constant SERVICE_DISPLAY_AGENT_NOTIFY.
     */
    static final String SERVICE_DISPLAY_AGENT_SET_ENVIRONMENT_MODEL = "service-display-agent-set-environment-model";
    static final String SERVICE_DISPLAY_AGENT_NOTIFICATION = "service-display-agent-notification";

    /**
     * Notifies all registered DisplayAgents about a new EnvironmentModel.
     *
     * @param envModel the {@link EnvironmentModel}
     * @throws IMTPException the iMTP exception
     */
    public void displayAgentSetEnvironmentModel(EnvironmentModel envModel) throws IMTPException;

    /**
     * Notifies all registered DisplayAgents.
     *
     * @param notification the notification
     * @throws IMTPException the iMTP exception
     */
    public void displayAgentNotification(EnvironmentNotification notification) throws IMTPException;
}
