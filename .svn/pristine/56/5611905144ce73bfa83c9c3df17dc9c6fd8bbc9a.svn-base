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
package org.gaia.core.project;

import java.io.Serializable;
import org.gaia.simulationService.distribution.JadeRemoteStart;

/**
 * This class manages the configuration for remote containers, which is stored
 * in a project.
 * 
 */
public class RemoteContainerConfiguration implements Serializable {

    private static final long serialVersionUID = 7745495134485079177L;
    private boolean preventUsageOfAlreadyUsedComputers = false;
    private boolean showJADErmaGUI = true;
    private String jvmMemAllocInitial = JadeRemoteStart.jvmMemo0032MB;
    private String jvmMemAllocMaximum = JadeRemoteStart.jvmMemo0128MB;

    /**
     * Instantiates a new remote container configuration.
     */
    public RemoteContainerConfiguration() {
    }

    /**
     * Checks if is prevent usage of already used computers.
     *
     * @return the preventUsageOfAlreadyUsedComputers
     */
    public boolean isPreventUsageOfAlreadyUsedComputers() {
        return preventUsageOfAlreadyUsedComputers;
    }

    /**
     * Sets the prevent usage of already used computers.
     *
     * @param preventUsageOfAlreadyUsedComputers the
     * preventUsageOfAlreadyUsedComputers to set
     */
    public void setPreventUsageOfAlreadyUsedComputers(boolean preventUsageOfAlreadyUsedComputers) {
        this.preventUsageOfAlreadyUsedComputers = preventUsageOfAlreadyUsedComputers;
    }

    /**
     * Checks if a remote RMA should be shown.
     *
     * @return the showJADErmaGUI
     */
    public boolean isShowJADErmaGUI() {
        return showJADErmaGUI;
    }

    /**
     * Sets if a remote RMA should be shown.
     *
     * @param showRMA true, if the Jade rma agent shall appear
     */
    public void setShowJADErmaGUI(boolean showRMA) {
        this.showJADErmaGUI = showRMA;
    }

    /**
     * Gets the the JVM initial memory allocation.
     *
     * @return the jvmMemAllocInitial
     */
    public String getJvmMemAllocInitial() {
        return jvmMemAllocInitial;
    }

    /**
     * Sets the JVM initial memory allocation.
     *
     * @param jvmMemAllocInitial the jvmMemAllocInitial to set
     */
    public void setJvmMemAllocInitial(String jvmMemAllocInitial) {
        this.jvmMemAllocInitial = jvmMemAllocInitial;
    }

    /**
     * Gets the JVM maximum memory allocation.
     *
     * @return the jvmMemAllocMaximum
     */
    public String getJvmMemAllocMaximum() {
        return jvmMemAllocMaximum;
    }

    /**
     * Sets the JVM maximum memory allocation.
     *
     * @param jvmMemAllocMaximum the jvmMemAllocMaximum to set
     */
    public void setJvmMemAllocMaximum(String jvmMemAllocMaximum) {
        this.jvmMemAllocMaximum = jvmMemAllocMaximum;
    }
}
