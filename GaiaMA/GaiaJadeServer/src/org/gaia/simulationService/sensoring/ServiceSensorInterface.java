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
import jade.core.Location;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.transaction.EnvironmentNotification;

/**
 * This interface is the super class for connections to the
 *
 */
public interface ServiceSensorInterface {

    /**
     * This method will be used by the ServiceActuator (class) to inform this
     * agent about its new migration location.
     *
     * @param newLocation the new Location for the migration
     */
    public abstract void setMigration(Location newLocation);

    /**
     * This method will be used by the {@link ServiceActuator} to inform this
     * agent about changes in the environment. It can be either used to do this
     * asynchronously or synchronously. It is highly recommended to do this
     * asynchronously, so that the agency can act parallel and not sequentially.
     *
     * @param envModel the current or new EnvironmentModel
     * @param aSynchron true, if this should be done asynchronously
     */
    public abstract void setEnvironmentModel(EnvironmentModel envModel, boolean aSynchron);

    /**
     * This method can be invoked from the simulation service, if a notification
     * for the manager has to be delivered.
     *
     * @param notification the new notification
     */
    public abstract void setNotification(EnvironmentNotification notification);

    /**
     * Will delete/kill the current agent.
     */
    public abstract void doDelete();

    /**
     * Returns the AID of the current Agent.
     *
     * @return the AID
     */
    public abstract AID getAID();
}