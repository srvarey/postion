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

import java.io.Serializable;
import org.gaia.simulationService.transaction.EnvironmentNotification;


public interface ServiceSensorListener extends Serializable {

    /**
     * Will be invoked, if the environment (model) changed.
     */
    public void onEnvironmentStimulus();

    /**
     * If an environment notification arrives the Agent, this method will be
     * invoked.
     *
     * @param notification the notification
     * @return the environment notification
     */
    public EnvironmentNotification onEnvironmentNotification(EnvironmentNotification notification);
}
