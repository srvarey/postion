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

import jade.core.Location;
import java.net.InetAddress;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.core.database.DBConnection;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.agents.LoadMeasureAgent;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container_List;
import org.gaia.simulationService.load.LoadMerger;

/**
 * This is the default class for the dynamic load balancing. It will apply, if
 * the 'Enable dynamic load balancing' - checkbox is set selected.
 */
public class DynamicLoadBalancing extends DynamicLoadBalancingBase {

    private static final long serialVersionUID = -4721675611537786965L;
    private ArrayList<ContainerContent> containers = new ArrayList();
    private DBConnection dbConn = Application.getDatabaseConnection();
    public boolean localMode = false;
    public int maxThreadsByContainer = Application.getGlobalInfo().getmaxThreadsByContainer();
    private static final Logger logger = Logger.getLogger(DynamicLoadBalancing.class);

    /**
     * Instantiates a new dynamic load balancing.
     *
     * @param loadMeasureAgent the load agent
     */
    public DynamicLoadBalancing(LoadMeasureAgent loadMeasureAgent) {
        super(loadMeasureAgent);
    }

    /* (non-Javadoc)
     * @see agentgui.simulationService.balancing.DynamicLoadBalancingBase#doBalancing()
     */
    //@Override
    @Override
    public void doBalancing() {

        if (getSlaveNumber().intValue() == 0) {
            localMode = true;
        } else {
            localMode = false;
        }
        Vector<AID_Container> transferAgents = new Vector<>();

        containers = calcContainers();

        int totalAgentsNo = 0;
        int maxAgentTransferNo = 200;

        int targetContainersNo = 0;
        for (ContainerContent cc : containers) {
            totalAgentsNo += cc.getThreads();
            if (cc.getContainer().startsWith("remote")) {
                targetContainersNo++;
            }
        }

        String newContainer = "";
        if (!localMode && (totalAgentsNo > maxThreadsByContainer * targetContainersNo
                || (targetContainersNo == 0 && totalAgentsNo > 0))) {
            newContainer = this.startRemoteContainer();
        }

        if (newContainer != null) {
            this.updateLoadInfo4JVMandMachine(newContainer);
            // refill after remote container creation
            containers = calcContainers();

            totalAgentsNo = 0;
            targetContainersNo = 0;
            for (ContainerContent cc : containers) {
                totalAgentsNo += cc.getThreads();
                if (cc.getContainer().startsWith("remote")) {
                    targetContainersNo++;
                }
            }

            int noOfAgentsPerContainer = totalAgentsNo;
            if (targetContainersNo > 0) {
                noOfAgentsPerContainer = totalAgentsNo / targetContainersNo;
            }

            // choose agents to migrate
            for (ContainerContent cc : containers) {
                int threadNoInContainer = 0;
                if (cc.getAgentList() != null) {
                    Iterator<String> itAgents = cc.getAgentList().keySet().iterator();
                    if (itAgents != null) {
                        while (itAgents.hasNext()) {
                            String agentName = itAgents.next();
                            AID_Container agent = cc.getAgentList().get(agentName);

                            // to migrate or not to migrate

                            if (agent.hasServiceSensor() && (cc.getContainer().startsWith("client")
                                    || cc.isIsMainLocal() || threadNoInContainer > noOfAgentsPerContainer)
                                    && transferAgents.size() < maxAgentTransferNo) {
                                // --- Take this agent for the migration --------
                                transferAgents.add(agent);
                            }
                            threadNoInContainer++;
                        }
                    }
                }
            }

            // chose place to migrate
            int noTransfered = 0;
            for (ContainerContent cc : containers) {
                if (!cc.getContainer().startsWith("client") && !cc.isIsMainLocal() && cc.getThreads() < noOfAgentsPerContainer) {
                    int n = Math.min(noOfAgentsPerContainer - cc.getThreads(), transferAgents.size());
                    Location newLocation = currContainerLocations.get(cc.getContainer());
                    for (int i = 0; i < n; i++) {
                        if (noTransfered < transferAgents.size()) {
                            transferAgents.get(noTransfered).setNewLocation(newLocation);
                            noTransfered++;
                        }
                    }
                }
            }
            Vector<AID_Container> tmp = (Vector<AID_Container>) transferAgents.clone();
            for (AID_Container aidc : tmp) {
                if (aidc.getNewLocation() == null) {
                    transferAgents.remove(aidc);
                }
            }
            //                 --- Notify the agents that they have to migrate ----------------------
            if (transferAgents.size() > 0) {
                logger.info("Start migration of " + transferAgents.size() + " agents ...");
                this.setAgentMigration(transferAgents);
            }
        }
    }

    private Long getSlaveNumber() {
        Long no = Long.MIN_VALUE;
        try {
            String sqlStmt = "select count(*) FROM platforms WHERE  contact_agent like 'server.slave%'";
            ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);
            while (res.next()) {
                no = res.getLong(1);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return no;
    }

    public ArrayList<ContainerContent> calcContainers() {

        ArrayList<ContainerContent> _containers = new ArrayList();
        // local machine
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (Exception e) {
            logger.error(e);
        }
        for (int i = 0; i < noMachines; i++) {
            LoadMerger load = loadMachines4Balancing.get(machineArray[i]);

            Iterator<String> itJVM = load.getJvmList().iterator();
            while (itJVM.hasNext()) {
                String jvmPID = itJVM.next();
                int noOfThreads;
                if (loadJVM4Balancing.get(jvmPID) != null) {
                    noOfThreads = loadJVM4Balancing.get(jvmPID).getMachineLoad().getLoadNoThreads();

                    Iterator<String> itContainer = loadJVM4Balancing.get(jvmPID).getContainerList().iterator();
                    while (itContainer.hasNext()) {
                        String containerName = itContainer.next();
                        ContainerContent cc = new ContainerContent();
                        cc.setContainer(containerName);
                        cc.setMachine(machineArray[i]);
                        cc.setThreads(noOfThreads);
                        AID_Container_List agentList = loadContainerAgentMap.getAgentsAtContainer().get(containerName);
                        cc.setAgentList(agentList);
                        if (agentList != null) {
                            cc.setThreads(agentList.size());
                        }
                        if ((machineArray[i].equals(addr.getHostAddress()) || machineArray[i].equals(addr.getHostName())) && containerName.equals("Main-Container")) {
                            cc.setIsMainLocal(true);
                            _containers.add(cc);
                        } else {
                            cc.setIsMainLocal(false);
                            _containers.add(cc);
                        }
                    }
                }
            }
        }
        return _containers;
    }

    public ArrayList<ContainerContent> getContainers() {
        return containers;
    }
}
