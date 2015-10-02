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

import jade.core.Location;
import org.gaia.simulationService.agents.SimulationAgent;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * This is the class for the so called ServiceSensor, which is an attribute of
 * the {@link SimulationAgent}. It transfers information about new environment
 * models, notifications in relation to the environment, the location where an
 * agent has to migrate and that a agent will be shut down.
 *
 *
 */
public class ServiceSensor {

    protected ServiceSensorInterface myServiceSensor;

    /**
     * Instantiates a new service sensor.
     *
     * @param serviceSensor the service sensor
     */
    public ServiceSensor(ServiceSensorInterface serviceSensor) {
        myServiceSensor = serviceSensor;
    }

    /**
     * Puts a new environment model to the agent.
     *
     * @param environmentModel the environment model
     * @param aSynchron true, if synchronously
     */
    public void putEnvironmentModel(EnvironmentModel environmentModel, boolean aSynchron) {
        myServiceSensor.setEnvironmentModel(environmentModel, aSynchron);
    }

    /**
     * Notifies an agent about things, taht happen in the environment.
     *
     * @param notification the EnvironmentNotification
     */
    public void notifyAgent(EnvironmentNotification notification) {
        myServiceSensor.setNotification(notification);
    }

    /**
     * Does the
     * <code>doDelete()</code> method of the agent.
     */
    public void doDelete() {
        myServiceSensor.doDelete();
    }

    /**
     * Puts the Location to the agent, where the agent has to migrate to.
     *
     * @param newLocation the new location
     */
    public void putMigrationLocation(Location newLocation) {
        myServiceSensor.setMigration(newLocation);
    }
}
