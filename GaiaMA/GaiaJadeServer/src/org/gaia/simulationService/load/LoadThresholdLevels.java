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

import java.io.Serializable;

/**
 * This is a serializable class, which holds the (default) threshold levels for
 * the running platform.
 *
 */
public class LoadThresholdLevels implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1795189499689460795L;
    /**
     * Default Level.
     */
    private Integer thCpuL = 5;
    /**
     * Default Level.
     */
    private Integer thCpuH = 80;
    /**
     * Default Level.
     */
    private Integer thMemoL = 5;
    /**
     * Default Level.
     */
    private Integer thMemoH = 80;
    /**
     * Default Level.
     */
    private Integer thNoThreadsL = 0;
    /**
     * Default Level.
     */
    private Integer thNoThreadsH = 1000;

    /**
     * Returns the threshold level for CPU-LOW level
     *
     * @return the CPU-LOW level
     */
    public Integer getThCpuL() {
        return thCpuL;
    }

    /**
     * Sets the threshold level for CPU-LOW level
     *
     * @param thCpuL the CPU-LOW level
     */
    public void setThCpuL(Integer thCpuL) {
        this.thCpuL = thCpuL;
    }

    /**
     * Returns the threshold level for CPU-HIGH level
     *
     * @return the CPU-HIGH level
     */
    public Integer getThCpuH() {
        return thCpuH;
    }

    /**
     * Sets the threshold level for CPU-HIGH level
     *
     * @param thCpuH the CPU-HIGH level
     */
    public void setThCpuH(Integer thCpuH) {
        this.thCpuH = thCpuH;
    }

    /**
     * Returns the threshold level for MEMORY-LOW level
     *
     * @return the MEMORY-LOW level
     */
    public Integer getThMemoL() {
        return thMemoL;
    }

    /**
     * Sets the threshold level for MEMORY-LOW level
     *
     * @param thMemoL the MEMORY-LOW level
     */
    public void setThMemoL(Integer thMemoL) {
        this.thMemoL = thMemoL;
    }

    /**
     * Returns the threshold level for MEMORY-HIGH level
     *
     * @return the MEMORY-HIGH level
     */
    public Integer getThMemoH() {
        return thMemoH;
    }

    /**
     * Sets the threshold level for MEMORY-HIGH level
     *
     * @param thMemoH the MEMORY-HIGH level
     */
    public void setThMemoH(Integer thMemoH) {
        this.thMemoH = thMemoH;
    }

    /**
     * Sets the threshold Number-of-threads-LOW level
     *
     * @param thNoThreadsL the threshold Number-of-threads-LOW level
     */
    public void setThNoThreadsL(Integer thNoThreadsL) {
        this.thNoThreadsL = thNoThreadsL;
    }

    /**
     * Returns the threshold Number-of-threads-LOW level
     *
     * @return the threshold Number-of-threads-LOW level
     */
    public Integer getThNoThreadsL() {
        return thNoThreadsL;
    }

    /**
     * Sets the threshold Number-of-threads-HIGH level
     *
     * @param thNoThreadsH the threshold Number-of-threads-HIGH level
     */
    public void setThNoThreadsH(Integer thNoThreadsH) {
        this.thNoThreadsH = thNoThreadsH;
    }

    /**
     * Returns the threshold Number-of-threads-HIGH level
     *
     * @return the threshold Number-of-threads-HIGH level
     */
    public Integer getThNoThreadsH() {
        return thNoThreadsH;
    }
}
