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
import java.util.Vector;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.agents.SimulationManagerAgent;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * This is the class for an actuator of the {@link SimulationService}, which is
 * able to inform all connected {@link SimulationManagerAgent} with an
 * integrated {@link ServiceSensorManager} about the environment changes caused
 * by {@link SimulationAgent} (sometimes called agentAnswers).
 *
 *
 */
public class ServiceActuatorManager {

    private Vector<ServiceSensorManager> serviceSensors = new Vector<>();

    /**
     * Method for agents to plug-in to this actuator.
     *
     * @param currSensor the ServiceSensorManager to plug-in
     */
    public void plugIn(ServiceSensorManager currSensor) {
        serviceSensors.addElement(currSensor);
    }

    /**
     * Method to plug-out from this actuator
     *
     * @param currSensor the ServiceSensorManager to plug-out
     */
    public void plugOut(ServiceSensorManager currSensor) {
        serviceSensors.removeElement(currSensor);
    }

    /**
     * This method returns the ServiceSensor-Instance identified by the AID of
     * the agent.
     *
     * @param aid the AID of the agent
     * @return the ServiceSensorManager
     */
    public ServiceSensorManager getSensor(AID aid) {

        Object[] arrLocal = serviceSensors.toArray();
        for (int i = arrLocal.length - 1; i >= 0; i--) {
            ServiceSensorManager sensor = (ServiceSensorManager) arrLocal[i];
            AID sensorAgentAID = sensor.myManager.getAID();
            if (sensorAgentAID.equals(aid)) {
                return sensor;
            }
        }
        return null;
    }

    /**
     * This method informs the manager agent about the answers of all involved
     * agents. It can be either used to do this asynchronously or
     * asynchronously, but it. is highly recommended to do this asynchronously,
     * so that the agents can act parallel and not sequential.
     *
     * @param managerAID the AID of the manager agent
     * @param agentAnswers the agent answers
     * @param aSynchron true, if this should be don asynchronously
     */
    public void putAgentAnswers(AID managerAID, Hashtable<AID, Object> agentAnswers, boolean aSynchron) {
        ServiceSensorManager sensor = getSensor(managerAID);
        if (sensor != null) {
            sensor.putAgentAnswers(agentAnswers, aSynchron);
        }
    }

    /**
     * Notify manager.
     *
     * @param managerAID the AID of the manager
     * @param notification the EnvironmentNotification
     * @return true, if successful
     */
    public boolean notifyManager(AID managerAID, EnvironmentNotification notification) {

        ServiceSensorManager sensor = getSensor(managerAID);
        if (sensor == null) {
            // --- Manager/Sensor was NOT found ---
            return false;
        } else {
            // --- Manager/Sensor was found -------
            sensor.notifyManager(notification);
            return true;
        }
    }
}
