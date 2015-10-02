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

import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 * This is a stroke time model, which inherits just a simple counter and can be

 *
 */
public class TimeModelStroke extends TimeModel {

    private static final Logger logger = Logger.getLogger(TimeModelStroke.class);
    private static final long serialVersionUID = -63223704339241994L;
    public final static String PROP_CounterStart = "CounterStart";
    public final static String PROP_CounterStop = "CounterStop";
    public final static String PROP_CounterCurrent = "CounterCurrent";
    private int counterStart = 1;
    private int counterStop = 9999;
    private int counter = 0;

    /**
     * Instantiates a new time model stroke.
     */
    public TimeModelStroke() {
    }

    /**
     * Instantiates a new time model stroke.
     *
     * @param currentCounterValue the position number the counter is currently
     * set
     */
    public TimeModelStroke(Integer currentCounterValue) {
        this.counter = currentCounterValue;
    }

    /**
     * Instantiates a new time model stroke.
     *
     * @param counterValueStart the counter start value
     * @param counterValueStop the counter stop value
     */
    public TimeModelStroke(Integer counterValueStart, Integer counterValueStop) {
        this.counterStart = counterValueStart;
        this.counterStop = counterValueStop;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#getCopy()
     */
    @Override
    public TimeModel getCopy() {
        TimeModelStroke tms = new TimeModelStroke();
        tms.setCounterStart(this.counterStart);
        tms.setCounterStop(this.counterStop);
        tms.setCounter(this.counter);
        return tms;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#step()
     */
    @Override
    public void step() {
        counter++;
    }
    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#stepBack()
     */

    @Override
    public void stepBack() {
        counter--;
    }

    /**
     * Sets the value at which the TimeModel starts.
     *
     * @param counterStart the new counter start
     */
    public void setCounterStart(int counterStart) {
        this.counterStart = counterStart;
    }

    /**
     * Returns the value at which the TimeModel starts.
     *
     * @return the counter start
     */
    public int getCounterStart() {
        return counterStart;
    }

    /**
     * Sets the value to stop the TimeModel.
     *
     * @param counterStop the new counter stop
     */
    public void setCounterStop(int counterStop) {
        this.counterStop = counterStop;
    }

    /**
     * Returns the value to stop the TimeModel.
     *
     * @return the counter stop
     */
    public int getCounterStop() {
        return counterStop;
    }

    /**
     * Sets the current counter.
     *
     * @param counter the counter to set
     */
    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    /**
     * Returns the current counter.
     *
     * @return the counter
     */
    public Integer getCounter() {
        return counter;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#setSetupConfiguration(java.util.HashMap)
     */
    @Override
    public void setTimeModelSettings(HashMap<String, String> timeModelSettings) {

        try {

            if (timeModelSettings.isEmpty()) {
                // --- Use Default values -----------------
                this.counterStart = 1;
                this.counterStop = 9999;
                this.counter = 0;
                return;
            }

            String stringCounterStart = timeModelSettings.get(PROP_CounterStart);
            String stringCounterStop = timeModelSettings.get(PROP_CounterStop);
            String stringCounter = timeModelSettings.get(PROP_CounterCurrent);

            if (stringCounterStart != null) {
                this.counterStart = Integer.parseInt(stringCounterStart);
            }
            if (stringCounterStop != null) {
                this.counterStop = Integer.parseInt(stringCounterStop);
            }
            if (stringCounter != null) {
                this.counter = Integer.parseInt(stringCounter);
            }

        } catch (Exception ex) {
            logger.error("Error while converting TimeModel settings from setup",ex);
        }

    }
    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#getSetupConfiguration()
     */

    @Override
    public HashMap<String, String> getTimeModelSetting() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put(PROP_CounterStart, ((Integer) this.counterStart).toString());
        hash.put(PROP_CounterStop, ((Integer) this.counterStop).toString());
        hash.put(PROP_CounterCurrent, ((Integer) this.counter).toString());
        return hash;
    }
}
