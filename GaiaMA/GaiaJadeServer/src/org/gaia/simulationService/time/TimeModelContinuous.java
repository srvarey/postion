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

import jade.core.Agent;
import jade.core.ServiceException;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.SimulationServiceHelper;

/**
 * The Class TimeModelContinuous.
 *
 */
public class TimeModelContinuous extends TimeModelDateBased {

    private static final long serialVersionUID = -6787156387409895035L;
    private static final Logger logger = Logger.getLogger(TimeModelContinuous.class);
    public final static String PROP_TimeStart = "TimeStart";
    public final static String PROP_TimeStop = "TimeStop";
    public final static String PROP_AccelerationFactor = "AccelerationFactor";
    public final static String PROP_TimeFormat = "TimeFormat";
    private transient Agent timeAskingAgent = null;
    private transient SimulationServiceHelper simHelper = null;
    private boolean executed = false;
    private long pauseTime = 0;
    private long timeDiff = 0;
    private double accelerationFactor = 1.F;
    private long timeMeasuredAcceleratedLast = 0;
    private long timeMeasuredLast = 0;

    /**
     * Instantiates a new continuous TimeModel .
     */
    public TimeModelContinuous() {
    }

    /**
     * Instantiates a new continuous TimeModel ..
     *
     * @param startTime the start time for the time model
     */
    public TimeModelContinuous(Long startTime, Long stopTime) {
        this.setTimeStart(startTime);
        this.setTimeStop(stopTime);
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#getCopy()
     */
    @Override
    public TimeModel getCopy() {
        TimeModelContinuous tmc = new TimeModelContinuous();
        // --- Do this first to avoid side effects --------
        tmc.setExecuted(this.executed);
        tmc.setPauseTime(this.pauseTime);
        tmc.setTimeDiff(this.timeDiff);
        // --- Do this first to avoid side effects --------
        tmc.setTimeStart(this.timeStart);
        tmc.setTimeStop(this.timeStop);
        tmc.setAccelerationFactor(this.accelerationFactor);
        tmc.setTimeFormat(this.timeFormat);
        return tmc;
    }

    /**
     * Gets the acceleration factor.
     *
     * @return the accelerationFactor
     */
    public double getAccelerationFactor() {
        return accelerationFactor;
    }

    /**
     * Sets the acceleration factor.
     *
     * @param accelerationFactor the accelerationFactor to set
     */
    public void setAccelerationFactor(double accelerationFactor) {
        this.accelerationFactor = accelerationFactor;
    }

    /**
     * Sets the time, where the time model was paused.
     *
     * @param pauseTime the new pause time
     */
    private void setPauseTime(long pauseTime) {
        this.pauseTime = pauseTime;
    }

    /**
     * Returns the time, where the time model was paused.
     *
     * @return the pause time
     */
    private long getPauseTime() {
        if (this.pauseTime == 0) {
            this.pauseTime = this.getTimeStart();
        }
        return pauseTime;
    }

    /**
     * Sets the time model to be executed in order to forward the time in the
     * model. In this method the platform time (provided by the
     * SimulationService) will be used to set the timeDiff.
     *
     * @param executeTimeModel the new executed
     */
    public void setExecuted(boolean executeTimeModel) {

        if (this.getTimeAskingAgent() == null) {
            // --- Work on local time settings ----------------------
            if (executeTimeModel == true) {
                this.setTimeDiff(this.getSystemTime() - this.getTimeStart());
            } else {
                this.setPauseTime(this.getTimeLocal());
            }

        } else {
            // --- Work on the synchronised platform time -----------
            if (executeTimeModel == true) {
                this.setTimeDiff(this.getSystemTimeSynchronized() - this.getTimeStart());
            } else {
                this.setPauseTime(this.getTimePlatform());
            }

        }
        this.executed = executeTimeModel;
    }

    /**
     * Checks if this time model is executed and so, if time is moving forward.
     *
     * @return true, if is executed
     */
    public boolean isExecuted() {
        return executed;
    }

    /**
     * Sets the time difference.
     *
     * @param timeDiff the timeDiff to set
     */
    private void setTimeDiff(long timeDiff) {
        this.timeDiff = timeDiff;
    }

    /**
     * Returns the time difference.
     *
     * @return the timeDiff
     */
    public long getTimeDiff() {
        return timeDiff;
    }

    /**
     * Gets the accelerated time.
     *
     * @return the accelerated time
     */
    private long getAcceleratedTime(long measuredTime) {

        long acceleratedTime = 0;
        if (this.timeMeasuredLast == 0) {
            this.timeMeasuredLast = measuredTime;
        }
        if (this.timeMeasuredAcceleratedLast == 0) {
            this.timeMeasuredAcceleratedLast = measuredTime;
        }

        if (this.accelerationFactor == 1.000) {
            acceleratedTime = measuredTime;

        } else {
            // --- get the real time interval between now and -----------------
            // --- the last time where the time was measured ------------------
            long timeInterval = measuredTime - this.timeMeasuredLast;

            // --- get the new value to add to the last return value ----------
            double timeAdditionDouble = this.accelerationFactor * (double) timeInterval;
            long timeAddition = Math.round(timeAdditionDouble);

            // --- add the calculated value to the old value ------------------
            acceleratedTime = this.timeMeasuredAcceleratedLast + timeAddition;
        }

        this.timeMeasuredLast = measuredTime;
        this.timeMeasuredAcceleratedLast = acceleratedTime;
        return acceleratedTime;

    }

    /**
     * Returns the current time depending on the simulation setup. If an agent
     * was set to the instance of the class the synchronized platform time will
     * be used. If no agent is set, the time based on the local system time will
     * be returned
     *
     * @see #setTimeAskingAgent(Agent)
     * @see SimulationServiceHelper#getSynchTimeMillis()
     *
     * @return the time
     */
    @Override
    public long getTime() {
        long timeAnswer = 0;
        if (this.getTimeAskingAgent() == null) {
            timeAnswer = this.getTimeLocal();
        } else {
            timeAnswer = this.getTimePlatform();
        }
        // --- Consider the factor of acceleration --------
        timeAnswer = this.getAcceleratedTime(timeAnswer);
        return timeAnswer;
    }

    /**
     * Returns the local time as long including the currently set timeDiff.
     *
     * @return the time
     */
    private long getTimeLocal() {
        long currTime;
        if (isExecuted() == true) {
            currTime = this.getSystemTime() - getTimeDiff();
        } else {
            currTime = this.getPauseTime();
        }
        return currTime;
    }

    /**
     * Returns the synchronized platform time, if available, or the local time
     * including the timeDiff. Platform time requires that JADE is running and
     * that the SimulationService is available.
     *
     * @see SimulationServiceHelper#getSynchTimeMillis()
     *
     * @return the time
     */
    private long getTimePlatform() {
        long currTime;
        if (isExecuted() == true) {
            currTime = this.getSystemTimeSynchronized() - getTimeDiff();
        } else {
            currTime = this.getPauseTime();
        }
        return currTime;
    }

    /**
     * Returns the local system time.
     *
     * @return the system time
     */
    private long getSystemTime() {
        return System.currentTimeMillis();
    }

    /**
     * Returns the system time, which is either the synchronized time of the
     * SimulationService or, in case of an inactive JADE platform, the time of
     * the local machine.
     *
     * @see SimulationServiceHelper#getSynchTimeMillis()
     *
     * @param agent the agent
     * @return the synchronized system time
     */
    private long getSystemTimeSynchronized() {
        long sysTime;
        if (this.getTimeAskingAgent() == null) {
            // --- Just take the local time -------------------------
            sysTime = System.currentTimeMillis();
            logger.warn("No agent was set. Took local time!");

        } else {
            // --- Try to get the synchronized platform time --------
            try {
                sysTime = this.getSimulationServiceHelper().getSynchTimeMillis();

            } catch (ServiceException se) {
                logger.error("Could not get synchronized platform time. Took local time!", se);
                sysTime = System.currentTimeMillis();
            }
        }
        return sysTime;
    }

    /**
     * Gets the agent.
     *
     * @return the agent
     */
    public Agent getTimeAskingAgent() {
        return this.timeAskingAgent;
    }

    /**
     * Sets the agent.
     *
     * @param askingAgent the new agent
     */
    public void setTimeAskingAgent(Agent askingAgent) {
        this.timeAskingAgent = askingAgent;
    }

    /**
     * Returns the SimulationServiceHelper that allows to connect to the
     * SimulationService.
     *
     * @see SimulationService
     * @see SimulationServiceHelper
     * @return the SimulationServiceHelper
     */
    private SimulationServiceHelper getSimulationServiceHelper() {

        if (this.getTimeAskingAgent() == null) {
            return null;
        }

        if (this.simHelper == null) {
            try {
                this.simHelper = (SimulationServiceHelper) this.getTimeAskingAgent().getHelper(SimulationService.NAME);
            } catch (ServiceException se) {
                logger.error("Could not connect to SimulationService!", se);
            }
        }
        return this.simHelper;
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#step()
     */
    @Override
    public void step() {
        // TODO Auto-generated method stub
    }
    /* (non-Javadoc)
     * @see agentgui.simulationService.time.TimeModel#stepBack()
     */

    @Override
    public void stepBack() {
        // TODO Auto-generated method stub
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
                this.accelerationFactor = 1;
                this.timeFormat = TimeModelDateBased.DEFAULT_TIME_FORMAT;
                return;
            }

            String stringStartTime = timeModelSettings.get(PROP_TimeStart);
            String stringStopTime = timeModelSettings.get(PROP_TimeStop);
            String stringAccelerationFactor = timeModelSettings.get(PROP_AccelerationFactor);
            String stringTimeModelFormat = timeModelSettings.get(PROP_TimeFormat);

            if (stringStartTime != null) {
                this.timeStart = Long.parseLong(stringStartTime);
            }
            if (stringStopTime != null) {
                this.timeStop = Long.parseLong(stringStopTime);
            }
            if (stringAccelerationFactor != null) {
                this.accelerationFactor = Float.parseFloat(stringAccelerationFactor);
            } else {
                this.accelerationFactor = 1;
            }
            if (stringTimeModelFormat != null) {
                this.timeFormat = stringTimeModelFormat;
            } else {
                this.timeFormat = TimeModelDateBased.DEFAULT_TIME_FORMAT;
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
        HashMap<String, String> hash = new HashMap<String, String>();
        hash.put(PROP_TimeStart, ((Long) this.timeStart).toString());
        hash.put(PROP_TimeStop, ((Long) this.timeStop).toString());
        hash.put(PROP_AccelerationFactor, ((Double) this.accelerationFactor).toString());
        hash.put(PROP_TimeFormat, this.timeFormat);
        return hash;
    }
}
