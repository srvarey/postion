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
package org.gaia.simulationService;

import jade.content.lang.sl.SLCodec;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.BaseService;
import jade.core.ContainerID;
import jade.core.Filter;
import jade.core.HorizontalCommand;
import jade.core.IMTPException;
import jade.core.Location;
import jade.core.MainContainer;
import jade.core.NameClashException;
import jade.core.Node;
import jade.core.NotFoundException;
import jade.core.Profile;
import jade.core.ProfileException;
import jade.core.Service;
import jade.core.ServiceDescriptor;
import jade.core.ServiceException;
import jade.core.ServiceHelper;
import jade.core.VerticalCommand;
import jade.core.management.AgentManagementSlice;
import jade.core.messaging.MessagingSlice;
import jade.core.mobility.AgentMobilityHelper;
import jade.lang.acl.ACLMessage;
import jade.security.JADESecurityException;
import jade.util.ObjectManager;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.simulationService.load.LoadAgentMap;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.load.LoadInformation;
import org.gaia.simulationService.load.LoadInformation.Container2Wait4;
import org.gaia.simulationService.load.LoadInformation.NodeDescription;
import org.gaia.simulationService.load.LoadMeasureSigar;
import org.gaia.simulationService.load.LoadMeasureThread;
import org.gaia.simulationService.load.LoadThresholdLevels;
import org.gaia.simulationService.load.LoadUnits;
import org.gaia.simulationService.ontology.BenchmarkResult;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.ClientRemoteContainerRequest;
import org.gaia.simulationService.ontology.DistributionOntology;
import org.gaia.simulationService.ontology.OSInfo;
import org.gaia.simulationService.ontology.PlatformAddress;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.PlatformPerformance;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This extended BaseService is basically used to transport the load information
 * from different remote locations to the Main-Container. Beside the load,
 * measured with HypericSigar in the {@link LoadMeasureThread}, its evaluates
 * also some local information and enables to set the migration of agents.<br>
 * Additionally it can be used in order to start agents directly on remote
 * container, which requires to have the agents resources (e.g. a jar file)
 * available there.
 *
 *
 */
public class LoadService extends BaseService {

    private static final Logger logger = Logger.getLogger(LoadService.class);
    /**
     * The external NAME of the this Service
     */
    public static final String NAME = LoadServiceHelper.SERVICE_NAME;
    /**
     * The file-name which stores the local node- (computer)- description as a
     * file.
     */
//    public static final String SERVICE_NODE_DESCRIPTION_FILE = LoadServiceHelper.SERVICE_NODE_DESCRIPTION_FILE;
    /**
     * A marker to prevent concurrent access to the NODE_DESCRIPTION_FILE
     */
    public static boolean currentlyWritingFile = false;
    private AgentContainer myContainer;
    private MainContainer myMainContainer;
    private Filter incFilter;
    private Filter outFilter;
    private ServiceComponent localSlice;
    private String myContainerMTPurl = null;
    /**
     * The local ClientRemoteContainerReply instance.
     */
    private ClientRemoteContainerReply myCRCReply = null;
    /**
     * The List of Agents, which are registered to this service *
     */
    private Hashtable<String, AID> agentList = new Hashtable<>();
    /**
     * Default values for starting new remote-container *
     */
    //public static final boolean DEFAULT_preventUsageOfAlreadyUsedComputers = true;
    private RemoteContainerConfig defaults4RemoteContainerConfig = null;
    /**
     * The Load-Information Array of all slices *
     */
    private LoadInformation loadInfo = new LoadInformation();

    /* (non-Javadoc)
     * @see jade.core.BaseService#init(jade.core.AgentContainer, jade.core.Profile)
     */
    @Override
    public void init(AgentContainer ac, Profile p) throws ProfileException {

        super.init(ac, p);
        myContainer = ac;
        myMainContainer = ac.getMain();
        // --- Create filters -----------------------------
        outFilter = new CommandOutgoingFilter();
        incFilter = new CommandIncomingFilter();
        // --- Create local slice -------------------------
        localSlice = new ServiceComponent();

        if (myContainer != null) {
            logger.info("Starting LoadService: My-Container: " + myContainer.toString());
        }
        if (myMainContainer != null) {
            logger.info("Main-Container: " + myMainContainer.toString());
        }
        // --- Start the Load-Measurements on this Node ---
        new LoadMeasureThread().start();

    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#boot(jade.core.Profile)
     */

    @Override
    public void boot(Profile p) throws ServiceException {
        super.boot(p);
        if (myMainContainer == null) {
            setLocalCRCReply(true);
        }
    }
    /* (non-Javadoc)
     * @see jade.core.Service#getName()
     */

    @Override
    public String getName() {
        return NAME;
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getHelper(jade.core.Agent)
     */

    @Override
    public ServiceHelper getHelper(Agent ag) {
        return new LoadServiceImpl();
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getCommandFilter(boolean)
     */

    @Override
    public Filter getCommandFilter(boolean direction) {
        if (direction == Filter.INCOMING) {
            return incFilter;
        } else {
            return outFilter;
        }
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getHorizontalInterface()
     */

    @Override
    public Class<?> getHorizontalInterface() {
        return LoadServiceSlice.class;
    }
    /* (non-Javadoc)
     * @see jade.core.BaseService#getLocalSlice()
     */

    @Override
    public Service.Slice getLocalSlice() {
        return localSlice;
    }

    // --------------------------------------------------------------
    // ---- Inner-Class 'AgentTimeImpl' ---- Start ------------------
    // --------------------------------------------------------------
    /**
     * Sub-Class to provide interaction between Agents and this Service by using
     * the {@link LoadServiceHelper}.
     *
     */
    public class LoadServiceImpl implements LoadServiceHelper {

        @Override
        public void setNewContainer2Wait4(String containerName) {
            loadInfo.setNewContainer2Wait4(containerName);
        }

        /* (non-Javadoc)
         * @see jade.core.ServiceHelper#init(jade.core.Agent)
         */
        @Override
        public void init(Agent ag) {
            // --- Store the Agent in the agentList -----------------
            agentList.put(ag.getName(), ag.getAID());
        }

        /* (non-Javadoc)
         */
        @Override
        public boolean startAgent(String nickName, String agentClassName, Object[] args, String containerName) throws ServiceException {
            return broadcastStartAgent(nickName, agentClassName, args, containerName);
        }

        // ----------------------------------------------------------
        // --- Methods to start a new remote-container --------------
		/* (non-Javadoc)
         */
        @Override
        public RemoteContainerConfig getAutoRemoteContainerConfig() throws ServiceException {
            return broadcastGetAutoRemoteContainerConfig();
        }
        /* (non-Javadoc)
         */

        @Override
        public void setDefaults4RemoteContainerConfig(RemoteContainerConfig remoteContainerConfig) throws ServiceException {
            broadcastSetDefaults4RemoteContainerConfig(remoteContainerConfig);
        }

        /* (non-Javadoc)
         */
        @Override
        public String startNewRemoteContainer() throws ServiceException {
            return this.startNewRemoteContainer(null);
        }

        /* (non-Javadoc)
         */
        @Override
        public String startNewRemoteContainer(RemoteContainerConfig remoteConfig) throws ServiceException {
            return broadcastStartNewRemoteContainer(remoteConfig);
        }

        /* (non-Javadoc)
         */
        @Override
        public Container2Wait4 startNewRemoteContainerStatus(String containerName) throws ServiceException {
            return broadcastGetNewContainer2Wait4Status(containerName);
        }

        // ----------------------------------------------------------
        // --- Methods to set the local description of this node ----
        // --- which is stored in the file 'gaia.bin'    ----
		/* (non-Javadoc)
         */
        @Override
        public ClientRemoteContainerReply getLocalCRCReply() throws ServiceException {
            return myCRCReply;
        }

        /* (non-Javadoc)
         */
        @Override
        public void setAndSaveCRCReplyLocal(ClientRemoteContainerReply crcReply) throws ServiceException {
            myCRCReply = crcReply;
//            saveCRCReply(myCRCReply);
        }

        // ----------------------------------------------------------
        // --- Methods for container info about OS, benchmark etc. --
		/* (non-Javadoc)
         */
        @Override
        public void putContainerDescription(ClientRemoteContainerReply crcReply) throws ServiceException {
            if (crcReply.getRemoteAddress() == null && crcReply.getRemoteOS() == null && crcReply.getRemotePerformance() == null && crcReply.getRemoteBenchmarkResult() == null) {
                // --- RemoteContainerRequest WAS NOT successful ----
                loadInfo.setNewContainerCanceled(crcReply.getRemoteContainerName());
            } else {
                Service.Slice[] slices = getAllSlices();
                broadcastPutContainerDescription(slices, crcReply);
            }
        }

        /* (non-Javadoc)
         */
        @Override
        public HashMap<String, NodeDescription> getContainerDescriptions() throws ServiceException {
            return loadInfo.containerDescription;
        }

        /* (non-Javadoc)
         */
        @Override
        public NodeDescription getContainerDescription(String containerName) throws ServiceException {
            return loadInfo.containerDescription.get(containerName);
        }

        // ----------------------------------------------------------
        // --- Method for getting Location-Objects ------------------
		/* (non-Javadoc)
         */
        @Override
        public HashMap<String, Location> getContainerLocations() throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastGetContainerLocation(slices);
            return loadInfo.containerLocations;
        }

        /* (non-Javadoc)
         */
        @Override
        public Location getContainerLocation(String containerName) throws ServiceException {
            this.getContainerLocations();
            return loadInfo.containerLocations.get(containerName);
        }

        // ----------------------------------------------------------
        // --- Method to get the Load-Informations of all containers
		/* (non-Javadoc)
         */
        @Override
        public void setThresholdLevels(LoadThresholdLevels thresholdLevels) throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastThresholdLevels(slices, thresholdLevels);
        }

        /* (non-Javadoc)
         */
        @Override
        public HashMap<String, PlatformLoad> getContainerLoads() throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastMeasureLoad(slices);
            return loadInfo.containerLoads;
        }

        /* (non-Javadoc)
         */
        @Override
        public PlatformLoad getContainerLoad(String containerName) throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastMeasureLoad(slices);
            return loadInfo.containerLoads.get(containerName);
        }

        /* (non-Javadoc)
         */
        @Override
        public Vector<String> getContainerQueue() throws ServiceException {
            return loadInfo.containerQueue;
        }

        /* (non-Javadoc)
         */
        @Override
        public void setSimulationCycleStartTimeStamp() throws ServiceException {
            loadInfo.setSimulationCycleStartTimeStamp();
        }

        /* (non-Javadoc)
         */
        @Override
        public double getAvgCycleTime() throws ServiceException {
            return loadInfo.getAvgCycleTime();
        }
        // ----------------------------------------------------------
        // --- Method to get positions of Agents at this platform ---
		/* (non-Javadoc)
         */

        @Override
        public LoadAgentMap getAgentMap() throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastGetAIDListSensorAgents(slices);
            broadcastGetAIDList(slices);
            return loadInfo.agentLocations;
        }

        // ----------------------------------------------------------
        // --- Method to set the agent migration --------------------
		/* (non-Javadoc)
         */
        @Override
        public void setAgentMigration(Vector<AID_Container> transferAgents) throws ServiceException {
            Service.Slice[] slices = getAllSlices();
            broadcastAgentMigration(transferAgents, slices);
        }
    }
    // --------------------------------------------------------------
    // ---- Inner-Class 'AgentTimeImpl' ---- End --------------------
    // --------------------------------------------------------------

    /**
     * Broadcast the new locations to the agents.
     *
     * @see AID_Container
     * @param transferAgents the Vector of agents to transfer
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastAgentMigration(Vector<AID_Container> transferAgents, Service.Slice[] slices) throws ServiceException {
        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.info("Sending migration notification to agents at " + sliceName);

                slice.setAgentMigration(transferAgents);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while sending migration notification to agents at slice " + sliceName, t);
            }
        }
    }

    /**
     * This method starts an agent on an designate (remote) container.
     *
     * @param localName4Agent the nick local Name of the agent
     * @param agentClassName the agent class name
     * @param args the args
     * @param containerName the container name
     * @return true, if successful
     * @throws ServiceException the service exception
     */
    private boolean broadcastStartAgent(String localName4Agent, String agentClassName, Object[] args, String containerName) throws ServiceException {
        logger.info("Try to start agent " + localName4Agent + " on container" + containerName);
        String sliceName = null;
        try {
            LoadServiceSlice slice = (LoadServiceSlice) getSlice(containerName);
            sliceName = slice.getNode().getName();
            logger.info("Start agent '" + localName4Agent + "' on container " + sliceName + "");

            return slice.startAgent(localName4Agent, agentClassName, args);
        } catch (ServiceException | IMTPException t) {
            // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
            logger.warn("Error while trying to get the default remote container configuration from " + sliceName, t);
        }
        return false;
    }

    /**
     * Broadcast to set the defaults for a remote container configuration.
     *
     * @param remoteContainerConfig the remote container configuration
     * @throws ServiceException the service exception
     */
    private void broadcastSetDefaults4RemoteContainerConfig(RemoteContainerConfig remoteContainerConfig) throws ServiceException {
        logger.debug("Sending the default remote container configuration!");

        String sliceName = null;
        try {
            LoadServiceSlice slice = (LoadServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.debug("Sending the default remote container configuration to container " + sliceName);
            slice.setDefaults4RemoteContainerConfig(remoteContainerConfig);
        } catch (ServiceException | IMTPException t) {
            // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
            logger.warn("Error while try to send the default remote container configuration to container " + sliceName, t);
        }

    }

    /**
     * This Methods returns the default Remote-Container-Configuration, coming
     * from the Main-Container.
     *
     * @param preventUsageOfAlreadyUsedComputers the prevent usage of already
     * used computers
     * @return the RemoteContainerConfig
     * @throws ServiceException the service exception
     */
    private RemoteContainerConfig broadcastGetAutoRemoteContainerConfig() throws ServiceException {

        logger.debug("Start request for the default remote container configuration!");
        String sliceName = null;
        try {
            LoadServiceSlice slice = (LoadServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.debug("Start request for the default remote container configuration at container " + sliceName);
            return slice.getAutoRemoteContainerConfig();
        } catch (ServiceException | IMTPException t) {
            // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
            logger.warn("Error while trying to get the default remote container configuration from " + sliceName, t);
        }
        return null;
    }

    /**
     * Broadcast to start a new remote-container for this platform to the
     * Main-Container.
     *
     * @param remoteConfig the configuration of the remote container
     * @param preventUsageOfAlreadyUsedComputers the prevent usage of already
     * used computers
     * @return the name of the new container
     * @throws ServiceException the service exception
     */
    private String broadcastStartNewRemoteContainer(RemoteContainerConfig remoteConfig) throws ServiceException {

        logger.debug("Start a new remote container!");

        String sliceName = null;
        try {
            LoadServiceSlice slice = (LoadServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.debug("Try to start a new remote container " + sliceName);
            return slice.startNewRemoteContainer(remoteConfig);
        } catch (ServiceException | IMTPException t) {
            // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
            logger.warn("Error while starting a new remote-container from " + sliceName, t);
        }
        return null;
    }

    /**
     * Broadcast to start a new remote-container for this platform.
     *
     * @param containerName2Wait4 the container name2 wait4
     * @return the container2 wait4
     * @throws ServiceException the service exception
     */
    private Container2Wait4 broadcastGetNewContainer2Wait4Status(String containerName2Wait4) throws ServiceException {
        logger.debug("Start a new remote container!");

        String sliceName = null;
        try {
            LoadServiceSlice slice = (LoadServiceSlice) getSlice(MAIN_SLICE);
            sliceName = slice.getNode().getName();
            logger.debug("Try to start a new remote container " + sliceName);
            return slice.getNewContainer2Wait4Status(containerName2Wait4);
        } catch (ServiceException | IMTPException t) {
            // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
            logger.warn("Error while starting a new remote-container from " + sliceName, t);

        }
        return null;
    }

    /**
     * Collects all {@link Location} information from the connected container.
     * The information will be set to the local {@link #loadInfo}.
     *
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastGetContainerLocation(Service.Slice[] slices) throws ServiceException {

        loadInfo.containerLocations.clear();
        logger.debug("Try to get Location-Informations!");

        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try to get Location-Object for " + sliceName);
                Location cLoc = slice.getLocation();
                loadInfo.containerLocations.put(sliceName, cLoc);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while try to get Location-Object from " + sliceName, t);
            }
        }
    }

    /**
     * Broadcast informtion's of the remote-container (OS etc.) to all
     * remote-container of this platform.
     *
     * @param slices the slices
     * @param crcReply the ClientRemoteContainerReply
     * @throws ServiceException the service exception
     */
    private void broadcastPutContainerDescription(Service.Slice[] slices, ClientRemoteContainerReply crcReply) throws ServiceException {

        logger.debug("Sending remote container Information!");

        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try sending remote container Information to " + sliceName);

                slice.putContainerDescription(crcReply);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while try to send container information to " + sliceName, t);
            }
        }
    }

    /**
     * Broadcast the set of threshold levels to all container.
     *
     * @param slices the slices
     * @param thresholdLevels the threshold levels
     * @throws ServiceException the service exception
     */
    private void broadcastThresholdLevels(Service.Slice[] slices, LoadThresholdLevels thresholdLevels) throws ServiceException {

        loadInfo.containerLoads.clear();
        logger.debug("Try to set threshold level to all Containers !");

        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try to set threshold level to " + sliceName);

                slice.setThresholdLevels(thresholdLevels);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while try to set threshold level to slice " + sliceName, t);
            }
        }
    }

    /**
     * 'Broadcast' (or receive) all Informations about the containers load. The
     * information will be set to the local {@link #loadInfo}.
     *
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastMeasureLoad(Service.Slice[] slices) throws ServiceException {

        loadInfo.containerLoads.clear();
        logger.debug("Try to get Load-Information from all Containers !");

        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try to get Load-Information of " + sliceName);
                PlatformLoad pl = slice.measureLoad();
                loadInfo.containerLoads.put(sliceName, pl);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while executing 'MeasureLoad' on slice " + sliceName, t);
            }
        }
    }

    /**
     * 'Broadcast' (or receive) the list of all agents in a container. The
     * information will be set to the local {@link #loadInfo}.
     *
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastGetAIDList(Service.Slice[] slices) throws ServiceException {

        loadInfo.resetAIDs4Container();
        logger.debug("Try to get AID's from all Containers !");

        for (int i = 0;
                i < slices.length;
                i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try to get AID''s from " + sliceName);

                AID[] aid = slice.getAIDList();
                loadInfo.putAIDs4Container(sliceName, aid);
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while trying to get AID's from " + sliceName, t);
            }
        }

        loadInfo.countAIDs4Container();
    }

    /**
     * 'Broadcast' (or receive) the list of all agents in a container with a
     * registered sensor. The information will be set to the local
     * {@link #loadInfo}.
     *
     * @param slices the slices
     * @throws ServiceException the service exception
     */
    private void broadcastGetAIDListSensorAgents(Service.Slice[] slices) throws ServiceException {

        loadInfo.sensorAgents = new Vector<>();
        logger.debug("Try to get Sensor-AID's from all Containers !");

        for (int i = 0; i < slices.length; i++) {
            String sliceName = null;
            try {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                sliceName = slice.getNode().getName();
                logger.debug("Try to get Sensor-AID''s from " + sliceName);

                AID[] aidList = slice.getAIDListSensorAgents();
                loadInfo.sensorAgents.addAll(new Vector<>(Arrays.asList(aidList)));
            } catch (ServiceException | IMTPException t) {
                // NOTE that slices are always retrieved from the main and not from the cache --> No need to retry in case of failure
                logger.warn("Error while trying to get Sensor-AID's from " + sliceName, t);
            }
        }
    }

    // --------------------------------------------------------------
    // ---- Inner-Class 'ServiceComponent' ---- Start ---------------
    // --------------------------------------------------------------
    /**
     * Inner class ServiceComponent. Will receive Commands, which are coming
     * from the LoadServiceProxy and do local method invocations.
     */
    private class ServiceComponent implements Service.Slice {

        private static final long serialVersionUID = 1776886375724997808L;

        /* (non-Javadoc)
         * @see jade.core.Service.Slice#getService()
         */
        @Override
        public Service getService() {
            return LoadService.this;
        }

        /* (non-Javadoc)
         * @see jade.core.Service.Slice#getNode()
         */
        @Override
        public Node getNode() throws ServiceException {
            try {
                return LoadService.this.getLocalNode();
            } catch (IMTPException imtpe) {
                throw new ServiceException("Error retrieving local node", imtpe);
            }
        }

        /* (non-Javadoc)
         * @see jade.core.Service.Slice#serve(jade.core.HorizontalCommand)
         */
        @Override
        public VerticalCommand serve(HorizontalCommand cmd) {

            try {
                if (cmd == null) {
                    return null;
                }
                //if ( ! cmd.getService().equals(NAME) ) return null;

                String cmdName = cmd.getName();
                Object[] params = cmd.getParams();
                switch (cmdName) {
                    case LoadServiceSlice.SERVICE_START_AGENT:
                        logger.debug("Starting a new agent on this platform");
                        String nickName = (String) params[0];
                        String agentClassName = (String) params[1];
                        Object[] args = (Object[]) params[2];
                        cmd.setReturnValue(this.startAgent(nickName, agentClassName, args));
                        break;
                    case LoadServiceSlice.SERVICE_START_NEW_REMOTE_CONTAINER:
                        logger.debug("Starting a new remote-container for this platform");
                        RemoteContainerConfig remoteConfig = (RemoteContainerConfig) params[0];
                        cmd.setReturnValue(this.startRemoteContainer(remoteConfig));
                        break;
                    case LoadServiceSlice.SERVICE_SET_DEFAULTS_4_REMOTE_CONTAINER_CONFIG:
                        logger.debug("Got the default settings for ");
                        RemoteContainerConfig remoteContainerConfig = (RemoteContainerConfig) params[0];
                        defaults4RemoteContainerConfig = remoteContainerConfig;
                        break;
                    case LoadServiceSlice.SERVICE_GET_AUTO_REMOTE_CONTAINER_CONFIG:
                        logger.debug("Answering to request for 'get_default_remote_container_config'");
                        cmd.setReturnValue(this.getAutoRemoteContainerConfig());
                        break;
                    case LoadServiceSlice.SERVICE_GET_NEW_CONTAINER_2_WAIT_4_STATUS:
                        String container2Wait4 = (String) params[0];
                        logger.debug("Answering request for new container status of container " + container2Wait4);
                        cmd.setReturnValue(this.getNewContainer2Wait4Status(container2Wait4));
                        break;
                    case LoadServiceSlice.SERVICE_GET_LOCATION:
                        cmd.setReturnValue(myContainer.here());
                        break;
                    case LoadServiceSlice.SERVICE_SET_THRESHOLD_LEVEL:
                        LoadThresholdLevels thresholdLevels = (LoadThresholdLevels) params[0];
                        logger.debug("Getting new threshold levels for load");
                        this.setThresholdLevels(thresholdLevels);
                        break;
                    case LoadServiceSlice.SERVICE_MEASURE_LOAD:
                        logger.debug("Answering request for Container-Load");
                        cmd.setReturnValue(this.measureLoad());
                        break;
                    case LoadServiceSlice.SERVICE_PUT_CONTAINER_DESCRIPTION:
                        logger.debug("Putting in container description");
                        this.putContainerDescription((ClientRemoteContainerReply) params[0]);
                        break;
                    case LoadServiceSlice.SERVICE_GET_CONTAINER_DESCRIPTION:
                        logger.debug("Answering request for container description");
                        cmd.setReturnValue(this.getContainerDescription());
                        break;
                    case SimulationServiceSlice.SIM_STEP_SIMULATION:
                        logger.debug("Received 'Step Simulation'");
                        this.setSimulationCycleStartTimeStamp();
                        break;
                }
            } catch (Throwable t) {
                cmd.setReturnValue(t);
            }
            return null;
        }

        // -----------------------------------------------------------------
        // --- The real methods for the Service Component --- Start --------
        // -----------------------------------------------------------------
        /**
         * Start agent.
         *
         * @param nickName the nick name
         * @param agentClassName the agent class name
         * @param args the args
         * @return true, if successful
         */
        private boolean startAgent(String nickName, String agentClassName, Object[] args) {

            AID agentID = new AID();
            agentID.setLocalName(nickName);

            try {
                Agent agent = (Agent) ObjectManager.load(agentClassName, ObjectManager.AGENT_TYPE);
                if (agent == null) {
                    agent = (Agent) Class.forName(agentClassName).newInstance();
                }
                agent.setArguments(args);
                myContainer.initAgent(agentID, agent, null, null);
                myContainer.powerUpLocalAgent(agentID);

            } catch (IMTPException | NameClashException | NotFoundException | JADESecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                logger.fatal(e);
            }
            return true;
        }
        // ----------------------------------------------------------
        // --- Method to set the agent migration --------------------

        /**
         * Start remote container.
         *
         * @param remoteConfig the remote config
         * @param preventUsageOfAlreadyUsedComputers the prevent usage of
         * already used computers
         * @return the string
         */
        private String startRemoteContainer(RemoteContainerConfig remoteConfig) {
            return sendMsgRemoteContainerRequest(remoteConfig);
        }

        /**
         * Gets the default remote container config.
         *
         * @param preventUsageOfAlreadyUsedComputers the prevent usage of
         * already used computers
         * @return the default remote container config
         */
        private RemoteContainerConfig getAutoRemoteContainerConfig() {
            return getRemoteContainerConfigAuto();
        }

        /**
         * Gets the new container to wait status.
         *
         * @param container2Wait4 the container2 wait4
         * @return the new container2 wait4 status
         */
        private Container2Wait4 getNewContainer2Wait4Status(String container2Wait4) {
            return loadInfo.getNewContainer2Wait4Status(container2Wait4);
        }

        /**
         * Sets the threshold levels.
         *
         * @param thresholdLevels the new threshold levels
         */
        private void setThresholdLevels(LoadThresholdLevels thresholdLevels) {
            LoadMeasureThread.setThresholdLevels(thresholdLevels);
        }

        /**
         * Measures the local system load.
         *
         * @return the platform load
         */
        private PlatformLoad measureLoad() {
            PlatformLoad pl = new PlatformLoad();
            pl.setLoadCPU(LoadMeasureThread.getLoadCPU());
            pl.setLoadMemorySystem(LoadMeasureThread.getLoadMemorySystem());
            pl.setLoadMemoryJVM(LoadMeasureThread.getLoadMemoryJVM());
            pl.setLoadNoThreads(LoadMeasureThread.getLoadNoThreads());
            pl.setLoadExceeded(LoadMeasureThread.getThresholdLevelExceeded());
            return pl;
        }

        /**
         * Put the container description of the {@link LoadService#loadInfo}.
         *
         * @param crcReply the ClientRemoteContainerReply
         */
        private void putContainerDescription(ClientRemoteContainerReply crcReply) {
            loadInfo.putContainerDescription(crcReply);
        }

        /**
         * Returns the local container description.
         *
         * @return the container description
         */
        private ClientRemoteContainerReply getContainerDescription() {
            return myCRCReply;
        }

        /**
         * Step simulation.
         */
        private void setSimulationCycleStartTimeStamp() {
            loadInfo.setSimulationCycleStartTimeStamp();
        }
    }
// --------------------------------------------------------------
// ---- Inner-Class 'ServiceComponent' ---- End -----------------
// --------------------------------------------------------------

// --------------------------------------------------------------
// ---- Inner-Class 'CommandOutgoingFilter' ---- Start ----------
// --------------------------------------------------------------
    /**
     * Inner class CommandOutgoingFilter.
     */
    private class CommandOutgoingFilter extends Filter {

        /**
         * Instantiates a new command outgoing filter.
         */
        public CommandOutgoingFilter() {
            super();
            //setPreferredPosition(2);  // Before the Messaging (encoding) filter and the security related ones
        }

        /* (non-Javadoc)
         * @see jade.core.Filter#accept(jade.core.VerticalCommand)
         */
        @Override
        public final boolean accept(VerticalCommand cmd) {
            if (cmd == null) {
                return true;
            }

            String cmdName = cmd.getName();
            if (cmdName.equals(MessagingSlice.SET_PLATFORM_ADDRESSES) && myContainerMTPurl == null) {
                // --- Handle that the MTP-Address was created ------
                Object[] params = cmd.getParams();
                AID aid = (AID) params[0];
                String[] aidArr = aid.getAddressesArray();
                if (aidArr.length != 0) {
                    myContainerMTPurl = aidArr[0];
                    setLocalCRCReply(false);
                }
                // Veto the original SEND_MESSAGE command, if needed
                // return false;
            } else if (cmdName.equals(AgentManagementSlice.KILL_CONTAINER)) {
                Object[] params = cmd.getParams();
                ContainerID id = (ContainerID) params[0];
                String containerName = id.getName();
                loadInfo.containerLoads.remove(containerName);
                loadInfo.containerLocations.remove(containerName);

            } else if (cmdName.equals(AgentMobilityHelper.INFORM_MOVED)) {
                Object[] params = cmd.getParams();
                @SuppressWarnings("unused")
                AID aid = (AID) params[0];
            }
            // Never veto other commands
            return true;
        }
    }

// --------------------------------------------------------------
// ---- Inner-Class 'CommandIncomingFilter' ---- Start ----------
// --------------------------------------------------------------
    /**
     * Inner class CommandIncomingFilter.
     */
    private class CommandIncomingFilter extends Filter {

        /* (non-Javadoc)
         * @see jade.core.Filter#accept(jade.core.VerticalCommand)
         */
        @Override
        public boolean accept(VerticalCommand cmd) {

            if (cmd == null) {
                return true;
            }
            String cmdName = cmd.getName();
            if (myMainContainer != null) {
                if (cmdName.equals(Service.NEW_SLICE)) {
                    handleNewSlice(cmd);
                }
            }
            // Never veto a Command
            return true;
        }
    }
// --------------------------------------------------------------
// ---- Inner-Class 'CommandIncomingFilter' ---- End ------------
// --------------------------------------------------------------

    /**
     * If the new slice is a LoadServiceSlice notify it about the current state.
     *
     * @param cmd the VerticalCommand
     */
    private void handleNewSlice(VerticalCommand cmd) {

        if (cmd.getService().equals(NAME)) {
            // --- We ARE in the Main-Container !!! ----------------------------------------
            Object[] params = cmd.getParams();
            String newSliceName = (String) params[0];
            try {
                // --- Is this the slice, we have waited for? ------------------------------
                loadInfo.setNewContainerStarted(newSliceName);
                // --- Be sure to get the new (fresh) slice --> Bypass the service cache ---
                LoadServiceSlice newSlice = (LoadServiceSlice) getFreshSlice(newSliceName);
                // --- Set the local ThresholdLevels to the new container ------------------
                newSlice.setThresholdLevels(LoadMeasureThread.getThresholdLevels());

            } catch (ServiceException | IMTPException t) {
                logger.error("Error notifying new slice " + newSliceName + " about current LoadService-State", t);
            }
        }
    }

    /**
     * This method returns a default configuration for a new remote container.
     *
     * @param preventUsageOfAlreadyUsedComputers the prevent usage of already
     * used computers
     * @return the default RemoteContainerConfig
     */
    private RemoteContainerConfig getRemoteContainerConfigAuto() {

        // --- Variable for the new container name ------------------
        String newContainerPrefix = "remote";
        String newContainerName;
        // --- Get the local IP-Address -----------------------------
        String myIP = myContainer.getNodeDescriptor().getContainer().getAddress();
        // --- Get the local port of JADE ---------------------------
        String myPort = myContainer.getNodeDescriptor().getContainer().getPort();

        // --- Get the List of services started here ----------------
        String myServices = "";
        List<?> services = myContainer.getServiceManager().getLocalServices();
        Iterator<?> it = services.iterator();
        while (it.hasNext()) {
            ServiceDescriptor serviceDesc = (ServiceDescriptor) it.next();
            String service = serviceDesc.getService().getClass().getName() + ";";
            myServices += service;
        }

        newContainerName = newContainerPrefix + getSuffixNo(newContainerPrefix);

        logger.info("-- Infos to start the remote container ------------");
        logger.info("=> Services2Start:   " + myServices);
        logger.info("=> NewContainerName: " + newContainerName);
        logger.info("=> ThisAddresses:    " + myIP + " - Port: " + myPort);


        // --- Define the 'RemoteContainerConfig' - Object ----------
        RemoteContainerConfig remConf = new RemoteContainerConfig();
        remConf.setJadeServices(myServices);
        remConf.setJadeIsRemoteContainer(true);
        remConf.setJadeHost(myIP);
        remConf.setJadePort(myPort);
        remConf.setJadeContainerName(newContainerName);
        remConf.setJadeShowGUI(true);

        // --- Apply defaults, if set -------------------------------
        if (this.defaults4RemoteContainerConfig != null) {
            remConf.setJadeShowGUI(this.defaults4RemoteContainerConfig.getJadeShowGUI());
            remConf.setJvmMemAllocInitial(this.defaults4RemoteContainerConfig.getJvmMemAllocInitial());
            remConf.setJvmMemAllocMaximum(this.defaults4RemoteContainerConfig.getJvmMemAllocMaximum());
        }
        return remConf;
    }

    public int getSuffixNo(String newContainerPrefix) {
        int newContainerNo = 0;
        try {
            Service.Slice[] slices = getAllSlices();
            for (int i = 0; i < slices.length; i++) {
                LoadServiceSlice slice = (LoadServiceSlice) slices[i];
                String sliceName = slice.getNode().getName();
                if (sliceName.startsWith(newContainerPrefix)) {
                    String endString = sliceName.replace(newContainerPrefix, "");
                    try {
                        Integer endNumber = Integer.parseInt(endString);
                        if (endNumber > newContainerNo) {
                            newContainerNo = endNumber;
                        }

                    } catch (Exception e) {
                    }
                }
            }
        } catch (ServiceException errSlices) {
            logger.error(errSlices);
        }
        newContainerNo++;
        return newContainerNo;
    }

    /**
     * This method configures and send a ACLMessage to start a new
     * remote-Container.
     *
     * @param remConf the RemoteContainerConfig
     * @param preventUsageOfAlreadyUsedComputers the boolean prevent usage of
     * already used computers
     * @return the name of the container
     */
    private String sendMsgRemoteContainerRequest(RemoteContainerConfig inConf) {

        // --- Get the local Address of JADE ------------------------
        String myPlatformAddress = myContainer.getPlatformID();

        // --- If the remote-configuration is null configure it now -
        RemoteContainerConfig remConf = this.getRemoteContainerConfigAuto();
        if (inConf != null) {
            if (inConf.getJadeContainerName() != null) {
                String name = inConf.getJadeContainerName();
                int suf = getSuffixNo(inConf.getJadeContainerName());
                name = name + suf;
                remConf.setJadeContainerName(name);
            }
            if (inConf.getJadeHost() != null) {
                remConf.setJadeHost(inConf.getJadeHost());
            }
            if (inConf.getJadePort() != null) {
                remConf.setJadePort(inConf.getJadePort());
            }
            if (inConf.getJadeServices() != null) {
                remConf.setJadeServices(inConf.getJadeServices());
            }
        }

        // --- Define the AgentAction -------------------------------
        ClientRemoteContainerRequest req = new ClientRemoteContainerRequest();
        req.setRemoteConfig(remConf);

        Action act = new Action();
        act.setActor(myContainer.getAMS());
        act.setAction(req);

        // --- Define receiver of the Message -----------------------
        AID agentGUIAgent = new AID("server.client" + "@" + myPlatformAddress, AID.ISGUID);

        // --- Build Message ----------------------------------------
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setSender(myContainer.getAMS());
        msg.addReceiver(agentGUIAgent);
        msg.setLanguage(new SLCodec().getName());
        msg.setOntology(DistributionOntology.getInstance().getName());
        try {
            msg.setContentObject(act);
        } catch (IOException errCont) {
            logger.error(errCont);
        }

        // --- Send message -----------------------------------------
        myContainer.postMessageToLocalAgent(msg, agentGUIAgent);

        // --- Remind, that we're waiting for this container --------
        loadInfo.setNewContainer2Wait4(remConf.getJadeContainerName());

        // --- Return -----------------------------------------------
        return remConf.getJadeContainerName();
    }

    /**
     * This method defines the local field 'myCRCReply' which is an instance of
     * 'ClientRemoteContainerReply' and holds the information about Performance,
     * BenchmarkResult, Network-Addresses of this container-node.
     *
     * @param loadFile indicates if the local file should be used or not
     */
    private void setLocalCRCReply(boolean loadFile) {

        ClientRemoteContainerReply crcReply = null;
//        if (loadFile == true) {
//            // --- Load the Descriptions from the local file ----------------------------
//            crcReply = loadCRCReply();
//        }

        if (crcReply == null) {
            // --- Build the Descriptions from the running system -----------------------

            // --- Get infos about the network connection -----
            InetAddress currAddress = null;
            InetAddress addressLocal;
            InetAddress addressLocalAlt;
            String hostIP, hostName, port;

            try {
                currAddress = InetAddress.getByName(myContainer.getID().getAddress());
                addressLocal = InetAddress.getLocalHost();
                addressLocalAlt = InetAddress.getByName("127.0.0.1");
                if (currAddress.equals(addressLocalAlt)) {
                    currAddress = addressLocal;
                }
            } catch (UnknownHostException e) {
                logger.error(e);
            }
            hostIP = currAddress.getHostAddress();
            hostName = currAddress.getHostName();
            port = myContainer.getID().getPort();

            // --- Define Platform-Info -----------------------
            PlatformAddress myPlatform = new PlatformAddress();
            myPlatform.setIp(hostIP);
            myPlatform.setUrl(hostName);
            myPlatform.setPort(Integer.parseInt(port));
            myPlatform.setHttp4mtp(myContainerMTPurl);

            // --- Set OS-Informations ------------------------
            OSInfo myOS = new OSInfo();
            myOS.setOs_name(System.getProperty("os.name"));
            myOS.setOs_version(System.getProperty("os.version"));
            myOS.setOs_arch(System.getProperty("os.arch"));

            // --- Set the Performance of machine -------------
            LoadMeasureSigar sys = LoadMeasureThread.getLoadCurrent();
            PlatformPerformance myPerformance = new PlatformPerformance();
            myPerformance.setCpu_vendor(sys.getVendor());
            myPerformance.setCpu_model(sys.getModel());
            myPerformance.setCpu_numberOf(sys.getTotalCpu());
            myPerformance.setCpu_speedMhz((int) sys.getMhz());
            myPerformance.setMemory_totalMB((int) LoadUnits.bytes2(sys.getTotalMemory(), LoadUnits.CONVERT2_MEGA_BYTE));

            // --- Set the performance (Mflops) of the system -

            BenchmarkResult bench=new BenchmarkResult();
            bench.setBenchmarkValue(LoadMeasureThread.getCompositeBenchmarkValue());

            // --- Get the PID of this JVM --------------------
            String jvmPID = LoadMeasureThread.getLoadCurrentJVM().getJvmPID();
            // --- Finally define this local description ------
            crcReply = new ClientRemoteContainerReply();
            crcReply.setRemoteContainerName(myContainer.getID().getName());
            crcReply.setRemotePID(jvmPID);
            crcReply.setRemoteAddress(myPlatform);
            crcReply.setRemoteOS(myOS);
            crcReply.setRemotePerformance(myPerformance);
            crcReply.setRemoteBenchmarkResult(bench);

        }

        // --- Set the local value of the ClientRemoteContainerReply --------------------
        myCRCReply = crcReply;

        // --- Broadcast the ClientRemoteContainerReply-Object to all other container ---
        Service.Slice[] slices;
        try {
            slices = getAllSlices();
            broadcastPutContainerDescription(slices, myCRCReply);
        } catch (ServiceException e) {
            logger.error(e);
        }

    }
}
