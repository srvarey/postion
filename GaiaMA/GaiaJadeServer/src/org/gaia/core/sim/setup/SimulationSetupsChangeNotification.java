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
package org.gaia.core.sim.setup;

/**
 * Class which is used for notifications about changes and actions
 * inside the SimulationSetups, like 'addNew', 'saved' and so on.
 * Which action was used can be set by the constructor while using 
 * the static globals of {@link SimulationSetups}. 
 *     
 *  
 */
public class SimulationSetupsChangeNotification {
	
	private int updateReason;
	
	/**
	 * Instantiates a new simulation setups change notification.
	 * @param reason the reason
	 */
	public SimulationSetupsChangeNotification(int reason) {
		updateReason = reason;
	}
	/**
	 * Sets the update reason.
	 * @param updateReason the new update reason
	 */
	public void setUpdateReason(int updateReason) {
		this.updateReason = updateReason;
	}
	/**
	 * Gets the update reason.
	 * @return the update reason
	 */
	public int getUpdateReason() {
		return updateReason;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
    @Override
	public String toString() {
		return SimulationSetups.CHANGED;
	}
	
}

