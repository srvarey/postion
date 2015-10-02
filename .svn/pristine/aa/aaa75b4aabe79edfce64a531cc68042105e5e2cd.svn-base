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
package org.gaia.simulationService.transaction;

import jade.core.AID;
import jade.core.Location;
import java.io.Serializable;

/**
 * This class provides the description of the manager, who is managing a
 * simulation. It consists of the AID an the location of that agent.
 *
 */
public class EnvironmentManagerDescription implements Serializable {

    private static final long serialVersionUID = -936922187980352665L;
    private AID manAID = null;
    private Location manLocation = null;

    /**
     * Instantiates a new environment manager description.
     *
     * @param managerAID the managers AID
     * @param managerLocation the managers Location
     */
    public EnvironmentManagerDescription(AID managerAID, Location managerLocation) {
        this.manAID = managerAID;
        this.manLocation = managerLocation;
    }

    /**
     * Returns the AID of the manager.
     *
     * @return the manAID
     */
    public AID getAID() {
        return manAID;
    }

    /**
     * Sets the AID of the manager.
     *
     * @param manAID the manAID to set
     */
    public void setAID(AID manAID) {
        this.manAID = manAID;
    }

    /**
     * Returns the location.
     *
     * @return the Location of the manager
     */
    public Location getLocation() {
        return manLocation;
    }

    /**
     * Sets the location of the manager.
     *
     * @param managerLocation the Location of the manager
     */
    public void setLocation(Location managerLocation) {
        this.manLocation = managerLocation;
    }
}
