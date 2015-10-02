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
import org.gaia.simulationService.load.LoadThresholdLevels;

/**
 * This class represents the model data for the distribution of an agency, which
 * can be configured in the tab {@link Distribution} of the simulation setup.
 *
 * @see Distribution
 *
 */
public class DistributionSetup implements Serializable {

    private static final long serialVersionUID = -3727386932566490036L;
    public final static String DEFAULT_StaticLoadBalancingClass = org.gaia.simulationService.balancing.StaticLoadBalancing.class.getName();
    public final static String DEFAULT_DynamicLoadBalancingClass = org.gaia.simulationService.balancing.DynamicLoadBalancing.class.getName();
    private boolean doStaticLoadBalancing = false;
    private String staticLoadBalancingClass = DEFAULT_StaticLoadBalancingClass;
    private int numberOfAgents = 0;
    private int numberOfContainer = 0;
    private boolean doDynamicLoadBalancing = false;
    private String dynamicLoadBalancingClass = DEFAULT_DynamicLoadBalancingClass;
    private boolean useUserThresholds = false;
    private LoadThresholdLevels UserThresholds = new LoadThresholdLevels();
    private boolean localOnly = false;
    private boolean excludeLocal = true;
    private String dispatcher;

    public String getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * Checks if is do static load balancing.
     *
     * @return the doStaticLoadBalalncing
     */
    public boolean isDoStaticLoadBalancing() {
        return doStaticLoadBalancing;
    }

    /**
     * Sets the do static load balancing.
     *
     * @param doStaticLoadBalalncing the doStaticLoadBalalncing to set
     */
    public void setDoStaticLoadBalancing(boolean doStaticLoadBalalncing) {
        this.doStaticLoadBalancing = doStaticLoadBalalncing;
    }

    /**
     * Gets the number of agents.
     *
     * @return the numberOfAgents
     */
    public int getNumberOfAgents() {
        return numberOfAgents;
    }

    /**
     * Sets the number of agents.
     *
     * @param numberOfAgents the numberOfAgents to set
     */
    public void setNumberOfAgents(int numberOfAgents) {
        this.numberOfAgents = numberOfAgents;
    }

    /**
     * Gets the number of container.
     *
     * @return the numberOfContainer
     */
    public int getNumberOfContainer() {
        return numberOfContainer;
    }

    /**
     * Sets the number of container.
     *
     * @param numberOfContainer the numberOfContainer to set
     */
    public void setNumberOfContainer(int numberOfContainer) {
        this.numberOfContainer = numberOfContainer;
    }

    /**
     * Sets the static load balancing class.
     *
     * @param staticLoadBalancingClass the staticLoadBalancingClass to set
     */
    public void setStaticLoadBalancingClass(String staticLoadBalancingClass) {
        this.staticLoadBalancingClass = staticLoadBalancingClass;
    }

    /**
     * Gets the static load balancing class.
     *
     * @return the staticLoadBalancingClass
     */
    public String getStaticLoadBalancingClass() {
        return staticLoadBalancingClass;
    }

    /**
     * Checks if is do dynamic load balalncing.
     *
     * @return the doDynamicLoadBalalncing
     */
    public boolean isDoDynamicLoadBalancing() {
        return doDynamicLoadBalancing;
    }

    /**
     * Sets the do dynamic load balalncing.
     *
     * @param doDynamicLoadBalalncing the doDynamicLoadBalalncing to set
     */
    public void setDoDynamicLoadBalancing(boolean doDynamicLoadBalalncing) {
        this.doDynamicLoadBalancing = doDynamicLoadBalalncing;
    }

    /**
     * Gets the dynamic load balancing class.
     *
     * @return the dynamicLoadBalancingClass
     */
    public String getDynamicLoadBalancingClass() {
        return dynamicLoadBalancingClass;
    }

    /**
     * Sets the dynamic load balancing class.
     *
     * @param dynamicLoadBalancingClass the dynamicLoadBalancingClass to set
     */
    public void setDynamicLoadBalancingClass(String dynamicLoadBalancingClass) {
        this.dynamicLoadBalancingClass = dynamicLoadBalancingClass;
    }

    /**
     * Checks if is use user thresholds.
     *
     * @return the useUserThresholds
     */
    public boolean isUseUserThresholds() {
        return useUserThresholds;
    }

    /**
     * Sets the use user thresholds.
     *
     * @param useUserThresholds the useUserThresholds to set
     */
    public void setUseUserThresholds(boolean useUserThresholds) {
        this.useUserThresholds = useUserThresholds;
    }

    /**
     * Gets the user thresholds.
     *
     * @return the userThresholds
     */
    public LoadThresholdLevels getUserThresholds() {
        return UserThresholds;
    }

    /**
     * Sets the user thresholds.
     *
     * @param userThresholds the userThresholds to set
     */
    public void setUserThresholds(LoadThresholdLevels userThresholds) {
        UserThresholds = userThresholds;
    }

    public boolean isLocalOnly() {
        return localOnly;
    }

    public void setLocalOnly(boolean localOnly) {
        this.localOnly = localOnly;
    }

    public boolean isExcludeLocal() {
        return excludeLocal;
    }

    public void setExcludeLocal(boolean excludeLocal) {
        this.excludeLocal = excludeLocal;
    }
}
