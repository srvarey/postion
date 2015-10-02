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

/**
 * The Class TimeModelDateBased.
 *
 */
public abstract class TimeModelDateBased extends TimeModel {

    private static final long serialVersionUID = 6116787943288451141L;
    public static final String DEFAULT_TIME_FORMAT = "dd.MM.yyyy HH:mm:ss.SSS";
    protected long timeStart = System.currentTimeMillis();
    protected long timeStop = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
    protected String timeFormat = "dd.MM.yyyy HH:mm:ss.SSS";

    public abstract long getTime();

    /**
     * Sets the start time .
     *
     * @param timeStart the new start time
     */
    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * Gets the start time.
     *
     * @return the start time
     */
    public long getTimeStart() {
        return timeStart;
    }

    /**
     * Sets the stop time.
     *
     * @param timeStop the new stop time
     */
    public void setTimeStop(long timeStop) {
        this.timeStop = timeStop;
    }

    /**
     * Gets the stop time.
     *
     * @return the stop time
     */
    public long getTimeStop() {
        return timeStop;
    }

    /**
     * Sets the time format.
     *
     * @param timeFormat the new time format
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    /**
     * Gets the time format.
     *
     * @return the time format
     */
    public String getTimeFormat() {
        if (this.timeFormat == null) {
            this.timeFormat = TimeModelDateBased.DEFAULT_TIME_FORMAT;
        } else {
            if (this.timeFormat.equals("")) {
                this.timeFormat = TimeModelDateBased.DEFAULT_TIME_FORMAT;
            }
        }
        return this.timeFormat;
    }
}
