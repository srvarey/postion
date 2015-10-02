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
package org.gaia.simulationService.environment;

import java.io.Serializable;
import org.gaia.simulationService.time.TimeModel;

/**
 * This is the generalized environment model to use with the SimulationService.
 *
 *
 *
 */
public class EnvironmentModel implements Serializable {

    private static final long serialVersionUID = -2845036237763599630L;
    private TimeModel timeModel = null;
    private Object abstractEnvironment = null;

    /**
     * Returns true if nothing is set yet (e.g. timeModel, abstractEnvironment
     * or displayEnvironment)
     *
     * @return true, if is empty
     */
    public boolean isEmpty() {
        return timeModel == null && abstractEnvironment == null;
    }

    /**
     * Gets the time model.
     *
     * @return the timeModel
     */
    public TimeModel getTimeModel() {
        return timeModel;
    }

    /**
     * Sets the time model.
     *
     * @param timeModel the timeModel to set
     */
    public void setTimeModel(TimeModel timeModel) {
        this.timeModel = timeModel;
    }

    /**
     * Gets the abstract environment.
     *
     * @return the abstract environment
     */
    public Object getAbstractEnvironment() {
        return abstractEnvironment;
    }

    /**
     * Sets the abstract environment.
     *
     * @param newAbstractEnvironment the new abstract environment
     */
    public void setAbstractEnvironment(Object newAbstractEnvironment) {
        this.abstractEnvironment = newAbstractEnvironment;
    }
}
