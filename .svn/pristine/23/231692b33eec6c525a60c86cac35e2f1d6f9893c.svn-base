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
package org.gaia.simulationService.sensoring;

import jade.core.AID;
import java.util.Hashtable;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.agents.SimulationManagerAgent;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * This is the class for the so called ServiceSensorManager, which is an
 * attribute of the {@link SimulationManagerAgent}. It transfers information
 * about environment changes caused by {@link SimulationAgent} and notifications
 * in relation to the environment.

 *
 */
public class ServiceSensorManager {

    protected SimulationManagerAgent myManager;

    /**
     * Instantiates a new service sensor manager.
     *
     * @param agent the agent
     */
    public ServiceSensorManager(SimulationManagerAgent agent) {
        myManager = agent;
    }

    /**
     * Puts the agent answers to the manager.
     *
     * @param agentAnswers the agent answers
     * @param aSynchron true, if synchronously
     */
    public void putAgentAnswers(Hashtable<AID, Object> agentAnswers, boolean aSynchron) {
        myManager.putAgentAnswers(agentAnswers, aSynchron);
    }

    /**
     * Notifies a manager agent about things that happen in the environment.
     *
     * @param notification the notification
     */
    public void notifyManager(EnvironmentNotification notification) {
        myManager.setManagerNotification(notification);
    }
}
