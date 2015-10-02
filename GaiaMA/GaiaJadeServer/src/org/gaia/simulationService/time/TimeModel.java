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
package org.gaia.simulationService.time;

import jade.util.leap.Serializable;
import java.util.HashMap;

/**
 * This is the abstract base class for any time model.
 *
 */
public abstract class TimeModel implements Serializable {

    private static final long serialVersionUID = 4597561080133786915L;
    private StopWatch stopWatch = null;

    /**
     * Instantiates a new time model.
     */
    public TimeModel() {
    }

    /**
     * Steps the TimeModel.
     */
    public abstract void step();

    /**
     * Steps the TimeModel.
     */
    public abstract void stepBack();

    /**
     * Sets the stopwatch.
     *
     * @param stopWatch the new stopwatch
     */
    public void setStopWatch(StopWatch stopWatch) {
        this.stopWatch = stopWatch;
    }

    /**
     * Returns the current or a new stopwatch.
     *
     * @return the stop watch
     */
    public StopWatch getStopWatch() {
        if (this.stopWatch == null) {
            this.stopWatch = new StopWatch();
        }
        return stopWatch;
    }

    /**
     * Returns a copy of the current TimeModel.
     *
     * @return the copy of the current TimeModel
     */
    public abstract TimeModel getCopy();

    /**
     * Sets the setup configuration as HashSet<String, String> (property, value)
     * to the TimeModel.
     *
     * @param timeModelSettings the time model setup configuration as
     * HashSet<String, String> (property, value)
     */
    public abstract void setTimeModelSettings(HashMap<String, String> timeModelSettings);

    /**
     * Returns the setup configuration of the TimeModel as HashSet<String,
     * String> consisting of (property, value).
     *
     * @return the setup configuration as HashSet<String, String> consisting of
     * (property, value)
     */
    public abstract HashMap<String, String> getTimeModelSetting();
}
