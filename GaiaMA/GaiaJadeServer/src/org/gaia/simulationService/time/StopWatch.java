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

import java.io.Serializable;

/**
 * The Class StopWatch can be used as tool to measure time between two events.
 *
 */
public class StopWatch implements Serializable {

    private static final long serialVersionUID = 975852140501115006L;
    private long stopWatchStart = 0;
    private long stopWatchStop = 0;

    /**
     * Instantiates a new stop watch.
     */
    public StopWatch() {
    }

    /**
     * Stopwatch start.
     */
    public void start() {
        this.stopWatchStart = System.currentTimeMillis();
    }

    /**
     * Stopwatch stop.
     */
    public void stop() {
        this.stopWatchStop = System.currentTimeMillis();
    }

    /**
     * Stopwatch restart. Works only, if the clock was stopped and not reseted
     */
    public void restart() {
        if (this.stopWatchStop != 0) {
            this.stopWatchStart = System.currentTimeMillis() - this.getTimeMeasured();
            this.stopWatchStop = 0;
        }
    }

    /**
     * Stopwatch reset.
     */
    public void reset() {
        this.stopWatchStart = 0;
        this.stopWatchStop = 0;
    }

    /**
     * Returns the currently stopped time in milliseconds.
     *
     * @return the long
     */
    public long getTimeMeasured() {
        long stopWatchTime = 0;
        if (stopWatchStart == 0 && stopWatchStop == 0) {
            // --- Stop watch is not running ----
            // => Do nothing
        } else if (stopWatchStart != 0 && stopWatchStop != 0) {
            // --- Stop watch was stopped -------
            stopWatchTime = stopWatchStop - stopWatchStart;
        } else if (stopWatchStart == 0 && stopWatchStop != 0) {
            // --- Something wrong --------------
            // => Do nothing
        } else if (stopWatchStart != 0 && stopWatchStop == 0) {
            // --- Clock is running ! -----------
            stopWatchTime = System.currentTimeMillis() - stopWatchStart;
        }
        return stopWatchTime;
    }
}
