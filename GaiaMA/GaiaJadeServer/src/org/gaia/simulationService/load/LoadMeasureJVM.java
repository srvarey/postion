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
package org.gaia.simulationService.load;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Hashtable;
import org.apache.log4j.Logger;

/**
 * This class will do the actual load measurement of the currently running JVM.
 * It is used from the {@link LoadMeasureThread}, which coordinates all
 * measurements.
 *
 *
 */
public class LoadMeasureJVM implements Cloneable {

    private static final long serialVersionUID = -5202880756096638141L;
    /**
     * The JVM PID (process ID).
     */
    private String jvmPID = null;
    /**
     * The free memory of the JVM.
     */
    private long jvmMemoFree = 0;
    /**
     * The total memory of the JVM.
     */
    private long jvmMemoTotal = 0;
    /**
     * The maximum memory of the JVM.
     */
    private long jvmMemoMax = 0;
    /**
     * The currently used heap .
     */
    private long jvmHeapUsed = 0;
    /**
     * The heap committed.
     */
    private long jvmHeapCommitted = 0;
    /**
     * The maximum heap.
     */
    private long jvmHeapMax = 0;
    /**
     * The initial heap.
     */
    private long jvmHeapInit = 0;
    /**
     * The current MemoryMXBean.
     */
    private MemoryMXBean memXB = ManagementFactory.getMemoryMXBean();
    /**
     * The current ThreadMXBean.
     */
    private ThreadMXBean threadXB = ManagementFactory.getThreadMXBean();
    /**
     * True, if thread monitoring is supported.
     */
    private boolean threadMonitoringSupported = threadXB.isThreadContentionMonitoringSupported();
    /**
     * True, if threadCpuTime is supported.
     */
    private boolean threadCpuTimeSupported = threadXB.isThreadCpuTimeSupported();
    /**
     * The number of threads running in this JVM.
     */
    private int jvmThreadCount = 0;
    private static final Logger logger = Logger.getLogger(LoadMeasureJVM.class);
    /**
     * The thread times in the JVM.
     */
    private Hashtable<String, Long> jvmThreadTimes = null;

    /**
     * Instantiates this class.
     */
    public LoadMeasureJVM() {

        this.jvmPID = ManagementFactory.getRuntimeMXBean().getName();

        if (threadMonitoringSupported) {
            threadXB.setThreadContentionMonitoringEnabled(true);
        }
        if (threadCpuTimeSupported) {
            threadXB.setThreadCpuTimeEnabled(true);
        }
    }

    /**
     * Returns a clone of the current object.
     *
     * @return LoadMeasureJVM, a new instance of this class
     */
    @Override
    protected LoadMeasureJVM clone() {
        try {
            return (LoadMeasureJVM) super.clone();
        } catch (CloneNotSupportedException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     * This method measures the current load of the system by using the on board
     * functionalities of the JVM.
     */
    public void measureLoadOfSystem() {

        jvmMemoFree = Runtime.getRuntime().freeMemory();
        jvmMemoTotal = Runtime.getRuntime().totalMemory();
        jvmMemoMax = Runtime.getRuntime().maxMemory();

        jvmHeapInit = memXB.getHeapMemoryUsage().getInit();
        jvmHeapMax = memXB.getHeapMemoryUsage().getMax();
        jvmHeapUsed = memXB.getHeapMemoryUsage().getUsed();
        jvmHeapCommitted = memXB.getHeapMemoryUsage().getCommitted();

        if (threadXB.isObjectMonitorUsageSupported()) {

            jvmThreadCount = threadXB.getThreadCount();

            long[] jvmThreadIDs = threadXB.getAllThreadIds();
            ThreadInfo[] jvmThreadInfo = threadXB.getThreadInfo(jvmThreadIDs);
            jvmThreadTimes = new Hashtable<>();
            for (int i = 0; i < jvmThreadInfo.length; i++) {
                if (jvmThreadInfo[i] != null) {
                    if (jvmThreadInfo[i].getThreadName() != null) {
                        String threadName = jvmThreadInfo[i].getThreadName();
                        long threadID = jvmThreadInfo[i].getThreadId();
                        jvmThreadTimes.put(threadName, threadXB.getThreadCpuTime(threadID));
                    }
                }
            }
        }

    }

    /**
     * This method return true/false if a Thread, given by it's name exists or
     * not.
     *
     * @param threadName2LookAt the name of the thread
     * @return boolean true, if the named thread exists
     */
    public boolean threadExists(String threadName2LookAt) {

        boolean exists = false;

        long[] jvmThreadIDs = threadXB.getAllThreadIds();
        ThreadInfo[] jvmThreadInfo = threadXB.getThreadInfo(jvmThreadIDs);
        for (int i = 0; i < jvmThreadInfo.length; i++) {
            String threadName = jvmThreadInfo[i].getThreadName();
            if (threadName.equalsIgnoreCase(threadName2LookAt)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    /**
     * Sets the JVM PID.
     *
     * @param jvmPID the jvmPID to set
     */
    public void setJvmPID(String jvmPID) {
        this.jvmPID = jvmPID;
    }

    /**
     * Returns the JVM PID.
     *
     * @return the jvmPID
     */
    public String getJvmPID() {
        return jvmPID;
    }

    /**
     * Returns the JVM free memory.
     *
     * @return the jvmMemoFree
     */
    public long getJvmMemoFree() {
        return jvmMemoFree;
    }

    /**
     * Sets the JVM free memory.
     *
     * @param jvmMemoFree the jvmMemoFree to set
     */
    public void setJvmMemoFree(long jvmMemoFree) {
        this.jvmMemoFree = jvmMemoFree;
    }

    /**
     * Returns the JVM total memory.
     *
     * @return the jvmMemoTotal
     */
    public long getJvmMemoTotal() {
        return jvmMemoTotal;
    }

    /**
     * Sets the JVM total memory.
     *
     * @param jvmMemoTotal the jvmMemoTotal to set
     */
    public void setJvmMemoTotal(long jvmMemoTotal) {
        this.jvmMemoTotal = jvmMemoTotal;
    }

    /**
     * Returns the JVM maximum memory.
     *
     * @return the jvmMemoMax
     */
    public long getJvmMemoMax() {
        return jvmMemoMax;
    }

    /**
     * Sets the JVM maximum memory.
     *
     * @param jvmMemoMax the jvmMemoMax to set
     */
    public void setJvmMemoMax(long jvmMemoMax) {
        this.jvmMemoMax = jvmMemoMax;
    }

    /**
     * Returns the JVM used heap memory.
     *
     * @return the jvmHeapUsed
     */
    public long getJvmHeapUsed() {
        return jvmHeapUsed;
    }

    /**
     * Sets the JVM used heap memory.
     *
     * @param jvmHeapUsed the jvmHeapUsed to set
     */
    public void setJvmHeapUsed(long jvmHeapUsed) {
        this.jvmHeapUsed = jvmHeapUsed;
    }

    /**
     * Returns the JVM committed heap memory.
     *
     * @return the jvmHeapCommited
     */
    public long getJvmHeapCommitted() {
        return jvmHeapCommitted;
    }

    /**
     * Sets the JVM committed heap memory.
     *
     * @param jvmHeapCommited the jvmHeapCommited to set
     */
    public void setJvmHeapCommitted(long jvmHeapCommited) {
        this.jvmHeapCommitted = jvmHeapCommited;
    }

    /**
     * Returns the JVM maximum heap memory.
     *
     * @return the jvmHeapMax
     */
    public long getJvmHeapMax() {
        return jvmHeapMax;
    }

    /**
     * Sets the JVM maximum heap memory.
     *
     * @param jvmHeapMax the jvmHeapMax to set
     */
    public void setJvmHeapMax(long jvmHeapMax) {
        this.jvmHeapMax = jvmHeapMax;
    }

    /**
     * Returns the JVM initial heap memory.
     *
     * @return the jvmHeapInit
     */
    public long getJvmHeapInit() {
        return jvmHeapInit;
    }

    /**
     * Returns the JVM initial heap memory.
     *
     * @param jvmHeapInit the jvmHeapInit to set
     */
    public void setJvmHeapInit(long jvmHeapInit) {
        this.jvmHeapInit = jvmHeapInit;
    }

    /**
     * Returns the number of threads running in the JVM.
     *
     * @return the jvmThreadCount
     */
    public int getJvmThreadCount() {
        return jvmThreadCount;
    }

    /**
     * Sets the number of threads running in the JVM.
     *
     * @param jvmThreadCount the jvmThreadCount to set
     */
    public void setJvmThreadCount(int jvmThreadCount) {
        this.jvmThreadCount = jvmThreadCount;
    }

    /**
     * Returns the times of the threads running in the JVM.
     *
     * @return the jvmThreadTimes
     */
    public Hashtable<String, Long> getJvmThreadTimes() {
        return jvmThreadTimes;
    }

    /**
     * Sets the times of the threads running in the JVM.
     *
     * @param jvmThreadTimes the jvmThreadTimes to set
     */
    public void setJvmThreadTimes(Hashtable<String, Long> jvmThreadTimes) {
        this.jvmThreadTimes = jvmThreadTimes;
    }
}
