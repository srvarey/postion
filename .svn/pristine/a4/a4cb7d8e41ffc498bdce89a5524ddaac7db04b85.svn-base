/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.simulationService.balancing;

import jade.core.ServiceException;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.simulationService.agents.LoadMeasureAgent;
import org.gaia.simulationService.load.LoadAgentMap;
import org.gaia.simulationService.load.LoadInformation.NodeDescription;
import org.gaia.simulationService.load.LoadMerger;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This is the base class for every tailored dynamic load balancing approach.
 * Its collects the load information from the running {@link LoadMeasureAgent}
 * and accumulates them in the local attribute.
 *
 */
public abstract class DynamicLoadBalancingBase extends BaseLoadBalancing {

    private static final long serialVersionUID = -7614035278070031234L;
    private static final Logger logger = Logger.getLogger(DynamicLoadBalancing.class);
    /**
     * The reference to the load agent.
     */
    protected LoadMeasureAgent myLoadAgent = null;
    // -------------
    /**
     * The load information distributed over all machines.
     */
    protected HashMap<String, LoadMerger> loadMachines4Balancing = null;
    /**
     * The load information distributed over all JVM's.
     */
    protected HashMap<String, LoadMerger> loadJVM4Balancing = null;
    /**
     * The load distributed over container.
     */
    protected HashMap<String, PlatformLoad> loadContainer = null;
    /**
     * The LoadAgentMap.
     */
    protected LoadAgentMap loadContainerAgentMap = null;
    /**
     * The number of machines.
     */
    protected int noMachines = 0;
    /**
     * The Array for all machine names
     */
    protected String[] machineArray = null;
    /**
     * The number of agents in the system.
     */
    protected int noAgents = 0;
    /**
     * the summation of all benchmark values
     */
    protected float machinesBenchmarkSummation = 0;

    /**
     * Default constructor of this class.
     *
     * @param loadMeasureAgent the running load agent of the system
     */
    public DynamicLoadBalancingBase(LoadMeasureAgent loadMeasureAgent) {
        super(loadMeasureAgent);
        myLoadAgent = loadMeasureAgent;
    }

    /* (non-Javadoc)
     * @see jade.core.behaviours.Behaviour#action()
     */
    @Override
    public void action() {
        this.setMeasurements();
        this.refreshCountingsAndLists();
        this.doBalancing();
        myLoadAgent.loadBalancingIsStillActivated = false;
    }

    /**
     * This method transfers the measurement data from the loadAgent to this
     * class. Do NOT override!!
     */
    public void setMeasurements() {

        if (myLoadAgent != null) {//&&myLoadAgent.loadContainerLocations!=null){

            currThresholdExceededOverAll = new Integer(myLoadAgent.loadThresholdExceededOverAll);
            if (myLoadAgent.loadContainerLocations != null) {
                currContainerLocations = new HashMap<>(myLoadAgent.loadContainerLocations);
            } else {
                currContainerLocations = new HashMap<>();
            }
            currContainerBenchmarkResults = new HashMap<>(myLoadAgent.loadContainerBenchmarkResults);

            loadMachines4Balancing = new HashMap<>(myLoadAgent.loadMachines4Balancing);
            loadJVM4Balancing = new HashMap<>(myLoadAgent.loadJVM4Balancing);
            if (myLoadAgent.loadContainer != null) {
                loadContainer = new HashMap<>(myLoadAgent.loadContainer);
            } else {
                loadContainer = new HashMap<>();
            }
            if (myLoadAgent.loadContainerAgentMap != null) {
                loadContainerAgentMap = myLoadAgent.loadContainerAgentMap;
            } else {
                loadContainerAgentMap = new LoadAgentMap();
            }
        }

    }

    /**
     * Here some important counting will be done by default.
     */
    public void refreshCountingsAndLists() {

        if (loadMachines4Balancing != null) {
            noMachines = loadMachines4Balancing.size();
            noAgents = loadContainerAgentMap.noAgentsAtPlatform;

            machineArray = new String[loadMachines4Balancing.size()];
            new Vector<String>(loadMachines4Balancing.keySet()).toArray(machineArray);

            machinesBenchmarkSummation = 0;
            for (int i = 0; i < noMachines; i++) {
                machinesBenchmarkSummation += loadMachines4Balancing.get(machineArray[i]).getBenchmarkValue();
            }
        }
    }

    /**
     * Updates the local load information if a new container was started. These
     * are in detail the local variables
     * {@link #currThresholdExceededOverAll}, {@link #loadMachines4Balancing}
     * and {@link #loadJVM4Balancing}.Additionally the method
     * {@link #refreshCountingsAndLists()} will be invoked at the end.
     *
     * @see #currThresholdExceededOverAll
     * @see #loadMachines4Balancing
     * @see #loadJVM4Balancing
     * @see #refreshCountingsAndLists()
     *
     * @param newContainerName the new container name
     */
    public void updateLoadInfo4JVMandMachine(String newContainerName) {

        if (newContainerName == null) {
            return;
        }

        Float benchmarkValue = null;
        String jvmPID = null;
        String machineURL = null;
        Integer containerNoAgents = 0;

        // --- Get the base information for this node/container -------------
        NodeDescription containerDesc = null;
        PlatformLoad containerLoad = null;
        try {
            containerDesc = loadHelper.getContainerDescription(newContainerName);
            containerLoad = loadHelper.getContainerLoads().get(newContainerName);

        } catch (ServiceException e) {
            logger.error(e);
        }

        if (containerDesc != null) {
            benchmarkValue = containerDesc.getBenchmarkValue().getBenchmarkValue();
            jvmPID = containerDesc.getJvmPID();
            machineURL = containerDesc.getPlAddress().getUrl();
        }

        // ------------------------------------------------------------------
        // --- Store informations also by the JVM (merge) -------------------
        // ------------------------------------------------------------------
        if (containerLoad != null && jvmPID != null) {
            // --- Observe the over all Threshold -----------------
            currThresholdExceededOverAll += ((Math.abs(containerLoad.getLoadExceeded())));

            // --- Merge the load per physical machine  -----------
            LoadMerger loadMachine = null;
            if (loadMachines4Balancing != null) {
                loadMachine = loadMachines4Balancing.get(machineURL);
            }
            if (loadMachine == null) {
                loadMachine = new LoadMerger(machineURL);
            }
            loadMachine.merge(newContainerName, jvmPID, benchmarkValue, containerLoad, containerNoAgents);
            loadMachines4Balancing.put(machineURL, loadMachine);

            // --- Merge the load per JVM -------------------------
            LoadMerger loadJvmMachine = loadJVM4Balancing.get(jvmPID);
            if (loadJvmMachine == null) {
                loadJvmMachine = new LoadMerger(jvmPID);
            }
            loadJvmMachine.merge(newContainerName, jvmPID, benchmarkValue, containerLoad, containerNoAgents);
            loadJVM4Balancing.put(jvmPID, loadJvmMachine);
        }
        // ------------------------------------------------------------------
        this.refreshCountingsAndLists();
        // ------------------------------------------------------------------

    }

    /**
     * This Method can be invoked, if a new remote container is required. If the
     * container was started the method returns the new containers name and will
     * update the local information of {@link #currContainerLoactions} and
     * {@link #currContainerBenchmarkResults}.<br> Additionally this method will
     * update the load information for machines and JVM by invoking
     * {@link #updateLoadInfo4JVMandMachine(String)}.
     *
     * @return the name of the new container
     *
     * @see #currContainerLoactions
     * @see #currContainerBenchmarkResults
     */
    @Override
    public String startRemoteContainer() {
        String newContainerName = super.startRemoteContainer();
        this.updateLoadInfo4JVMandMachine(newContainerName);
        return newContainerName;
    }

    /**
     * This Method can be invoked, if a new remote container is required. If the
     * container was started the method returns the new containers name and will
     * update the local information of {@link #currContainerLoactions} and
     * {@link #currContainerBenchmarkResults}.<br> Additionally this method will
     * update the load information for machines and JVM by invoking
     * {@link #updateLoadInfo4JVMandMachine(String)}.
     *
     * @param remoteContainerConfig the remote container configuration out of
     * the Project
     * @return the name of the new container
     *
     * @see #currContainerLoactions
     * @see #currContainerBenchmarkResults
     */
    @Override
    protected String startRemoteContainer(RemoteContainerConfig remoteContainerConfig) {
        String newContainerName = super.startRemoteContainer(remoteContainerConfig);
        this.updateLoadInfo4JVMandMachine(newContainerName);
        return newContainerName;
    }

    public LoadMeasureAgent getLoadMeasureAgent() {
        return myLoadAgent;
    }
}
