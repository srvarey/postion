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
 * This is a discrete time model, which can either start from 0 or from a
 * specified time. Additionally the step width in time has to be configured in
 * order to allow a discrete temporal progression.
 *
 */
public class TimeModelDiscrete extends TimeModelDateBased {

    private static final long serialVersionUID = 3931340225354221294L;
    private static final Logger logger = Logger.getLogger(TimeModelDiscrete.class);
    public final static String PROP_TimeCurrent = "TimeCurrent";
    public final static String PROP_TimeStart = "TimeStart";
    public final static String PROP_TimeStop = "TimeStop";
    public final static String PROP_StepWidth = "StepWidth";
    public final static String PROP_DisplayUnitIndex = "DisplayUnitIndex";
    public final static String PROP_TimeFormat = "TimeFormat";
    private long time = timeStart;
    private long step = new Long(1000 * 60);
    private int stepDisplayUnitAsIndexOfTimeUnitVector = 1;

    /**
     * Instantiates a new time model discrete.
     */
    public TimeModelDiscrete() {
    }

    /**
     * Instantiates a new time model discrete.
     *
     * @param stepInTime the step width in time
     */
    public TimeModelDiscrete(long stepInTime) {
        this.step = stepInTime;
    }

    /**
     * Instantiates a new time model discrete.
     *
     * @param stepInTime the step width in time
     * @param startTime the start time
     */
    public TimeModelDiscrete(long startTime, long stopTime, long stepInTime) {
        this.timeStart = startTime;
        this.timeStop = stopTime;
        this.step = stepInTime;

        this.time = this.timeStart;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#getCopy()
     */
    @Override
    public TimeModel getCopy() {
        TimeModelDiscrete tmd = new TimeModelDiscrete();
        tmd.setTimeStart(this.timeStart);
        tmd.setTimeStop(this.timeStop);
        tmd.setTime(this.time);
        tmd.setStep(this.step);
        tmd.setTimeFormat(this.timeFormat);
        return tmd;
    }

    /**
     * Sets the current time of the model.
     *
     * @param counter the counter to set
     */
    public void setTime(long counter) {
        this.time = counter;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModelDateBased#getTime()
     */
    @Override
    public long getTime() {
        return time;
    }

    /**
     * Steps the time with the given time step.
     */
    @Override
    public void step() {
        this.time = this.time + this.step;
    }

    /**
     * Steps back the time with the given time step.
     */
    @Override
    public void stepBack() {
        this.time = this.time - this.step;
    }

    /**
     * Returns the step width as long.
     *
     * @return the step
     */
    public long getStep() {
        return step;
    }

    /**
     * Sets the step width.
     *
     * @param step the step width as long
     */
    public void setStep(long step) {
        this.step = step;
    }

    /**
     * Sets the step display unit as index of the TimeUnitVector.
     *
     * @see TimeUnitVector
     * @param stepDisplayUnitAsIndexOfTimeUnitVector the new step display unit
     * as index of the TimeUnitVector
     */
    public void setStepDisplayUnitAsIndexOfTimeUnitVector(int stepDisplayUnitAsIndexOfTimeUnitVector) {
        this.stepDisplayUnitAsIndexOfTimeUnitVector = stepDisplayUnitAsIndexOfTimeUnitVector;
    }

    /**
     * Returns the step display unit as index of the TimeUnitVector.
     *
     * @see TimeUnitVector
     * @return the step display unit as index of the TimeUnitVector
     */
    public int getStepDisplayUnitAsIndexOfTimeUnitVector() {
        return stepDisplayUnitAsIndexOfTimeUnitVector;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#setSetupConfiguration(java.util.HashMap)
     */
    @Override
    public void setTimeModelSettings(HashMap<String, String> timeModelSettings) {

        try {

            if (timeModelSettings.isEmpty()) {
                // --- Use Default values -----------------
                this.timeStart = System.currentTimeMillis();
                this.timeStop = System.currentTimeMillis() + 1000 * 60 * 60 * 24;
                this.time = new Long(0);
                this.step = new Long(1000 * 60);
                this.stepDisplayUnitAsIndexOfTimeUnitVector = 1;
                this.timeFormat = TimeModelDateBased.DEFAULT_TIME_FORMAT;
                return;
            }

            String stringTimeCurrent = timeModelSettings.get(PROP_TimeCurrent);
            String stringTimeStart = timeModelSettings.get(PROP_TimeStart);
            String stringTimeStop = timeModelSettings.get(PROP_TimeStop);
            String stringStepWidth = timeModelSettings.get(PROP_StepWidth);
            String stringDisplayUnitIndex = timeModelSettings.get(PROP_DisplayUnitIndex);
            String stringTimeFormat = timeModelSettings.get(PROP_TimeFormat);

            if (stringTimeCurrent != null) {
                this.time = Long.parseLong(stringTimeCurrent);
            }
            if (stringTimeStart != null) {
                this.timeStart = Long.parseLong(stringTimeStart);
            }
            if (stringTimeStop != null) {
                this.timeStop = Long.parseLong(stringTimeStop);
            }
            if (stringStepWidth != null) {
                this.step = Long.parseLong(stringStepWidth);
            }
            if (stringDisplayUnitIndex != null) {
                this.stepDisplayUnitAsIndexOfTimeUnitVector = Integer.parseInt(stringDisplayUnitIndex);
            }
            if (stringTimeFormat != null) {
                this.timeFormat = stringTimeFormat;
            }

        } catch (Exception ex) {
            logger.error("Error while converting TimeModel settings from setup", ex);
        }

    }
    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#getSetupConfiguration()
     */

    @Override
    public HashMap<String, String> getTimeModelSetting() {
        HashMap<String, String> hash = new HashMap<>();
        hash.put(PROP_TimeCurrent, ((Long) this.time).toString());
        hash.put(PROP_TimeStart, ((Long) this.timeStart).toString());
        hash.put(PROP_TimeStop, ((Long) this.timeStop).toString());
        hash.put(PROP_StepWidth, ((Long) this.step).toString());
        hash.put(PROP_DisplayUnitIndex, ((Integer) this.stepDisplayUnitAsIndexOfTimeUnitVector).toString());
        hash.put(PROP_TimeFormat, this.timeFormat);
        return hash;
    }
}