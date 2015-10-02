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
package org.gaia.simulationService.balancing;

import org.gaia.simulationService.load.LoadAgentMap.AID_Container_List;

/**
 *
 * @author Benji
 */
public class ContainerContent {

    private int threads = 0;

    /**
     * Get the value of threads
     *
     * @return the value of threads
     */
    public int getThreads() {
        return threads;
    }

    /**
     * Set the value of threads
     *
     * @param threads new value of threads
     */
    public void setThreads(int threads) {
        this.threads = threads;
    }
    private String machine;

    /**
     * Get the value of machine
     *
     * @return the value of machine
     */
    public String getMachine() {
        return machine;
    }

    /**
     * Set the value of machine
     *
     * @param machine new value of machine
     */
    public void setMachine(String machine) {
        this.machine = machine;
    }
    private String container;

    /**
     * Get the value of container
     *
     * @return the value of container
     */
    public String getContainer() {
        return container;
    }

    /**
     * Set the value of container
     *
     * @param container new value of container
     */
    public void setContainer(String container) {
        this.container = container;
    }
    private boolean isMainLocal;

    /**
     * Get the value of isMainLocal
     *
     * @return the value of isMainLocal
     */
    public boolean isIsMainLocal() {
        return isMainLocal;
    }

    /**
     * Set the value of isMainLocal
     *
     * @param isMainLocal new value of isMainLocal
     */
    public void setIsMainLocal(boolean isMainLocal) {
        this.isMainLocal = isMainLocal;
    }
    private AID_Container_List agentList;

    /**
     * Get the value of agentList
     *
     * @return the value of agentList
     */
    public AID_Container_List getAgentList() {
        return agentList;
    }

    /**
     * Set the value of agentList
     *
     * @param agentList new value of agentList
     */
    public void setAgentList(AID_Container_List agentList) {
        this.agentList = agentList;
    }
}
