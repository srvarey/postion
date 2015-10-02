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
package org.gaia.simulationService;

import jade.core.Location;
import jade.core.ServiceException;
import jade.core.ServiceHelper;
import java.util.HashMap;
import java.util.Vector;
import org.gaia.simulationService.load.LoadAgentMap;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.load.LoadInformation.Container2Wait4;
import org.gaia.simulationService.load.LoadInformation.NodeDescription;
import org.gaia.simulationService.load.LoadThresholdLevels;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * Here, the accessible methods of the LoadService can be found.
 *
 */
public interface LoadServiceHelper extends ServiceHelper {

	/** The name of this service. */
	public static final String SERVICE_NAME = LoadService.class.getName();
	// --- Methods for agent and container handling -----------------
	/**
	 * Start an agent on a specified location.
	 *
	 * @param localName4Agent the local name for the agent
	 * @param agentClassName the class of the agent
	 * @param args the serializable Object array for the start arguments
	 * @param containerName the name of the container, where the agent should start
	 * @return true, if successful
	 * @throws ServiceException the service exception
	 */
	public boolean startAgent(String localName4Agent, String agentClassName, Object[] args, String containerName) throws ServiceException;

	/**
	 * Will start a new remote container.
	 *
	 * @return the name of the new container
	 * @throws ServiceException the service exception
	 */
	public String startNewRemoteContainer() throws ServiceException;

	/**
	 * Will start a new remote container.
	 *
	 * @param remoteConfig the remote container configuration
	 * @return the name of the new container
	 * @throws ServiceException the service exception
	 */
	public String startNewRemoteContainer(RemoteContainerConfig remoteConfig) throws ServiceException;

	/**
	 * Provides the default remote container configuration.
	 *
	 * @return the default remote container configuration
	 * @throws ServiceException the service exception
	 */
	public RemoteContainerConfig getAutoRemoteContainerConfig() throws ServiceException;

	/**
	 * Sets the default remote container configuration.
	 *
	 * @param remoteContainerConfig the new default remote container configuration
	 * @throws ServiceException the service exception
	 */
	public void setDefaults4RemoteContainerConfig(RemoteContainerConfig remoteContainerConfig) throws ServiceException;

	/**
	 * Returns the current status of the requested new container.
	 *
	 * @param containerName the new containers name
	 * @return current status of the requested new remote container
	 * @throws ServiceException the service exception
	 * @see Container2Wait4
	 */
	public Container2Wait4 startNewRemoteContainerStatus(String containerName) throws ServiceException;

	// --- Methods for the load balancing ---------------------------
	/**
	 * Returns the list of all available container.
	 *
	 * @return the container queue
	 * @throws ServiceException the service exception
	 */
	public Vector<String> getContainerQueue() throws ServiceException;

	/**
	 * Provides the average cycle time for a simulation.
	 *
	 * @return the average cycle time fo a simulation
	 * @throws ServiceException the service exception
	 * @see #setSimulationCycleStartTimeStamp()
	 */
	public double getAvgCycleTime() throws ServiceException;

	/**
	 * Sets System.currentTimeMillis() as an indicator for the start of an new simulations cycle.
	 *
	 * @throws ServiceException the service exception
	 * @see #getAvgCycleTime()
	 */
	public void setSimulationCycleStartTimeStamp() throws ServiceException;

	/**
	 * Sets the threshold levels for all involved container.
	 *
	 * @param currThresholdLevels the new threshold levels
	 * @throws ServiceException the service exception
	 */
	public void setThresholdLevels(LoadThresholdLevels currThresholdLevels) throws ServiceException;

	/**
	 * Returns the load of all connected container.
	 *
	 * @return the container loads
	 * @throws ServiceException the service exception
	 */
	public HashMap<String, PlatformLoad> getContainerLoads() throws ServiceException;

	/**
	 * Returns the load of a specified container.
	 *
	 * @param containerName the container name
	 * @return the container load
	 * @throws ServiceException the service exception
	 */
	public PlatformLoad getContainerLoad(String containerName) throws ServiceException;

	/**
	 * Provides the container locations of all connected container.
	 *
	 * @return the container locations
	 * @throws ServiceException the service exception
	 */
	public HashMap<String, Location> getContainerLocations() throws ServiceException;

	/**
	 * Gets the container location of a specified container.
	 *
	 * @param containerName the container name
	 * @return the container location
	 * @throws ServiceException the service exception
	 */
	public Location getContainerLocation(String containerName) throws ServiceException;

	/**
	 * Sets and save the local ClientRemoteContainerReply.
	 *
	 * @param crcReply the ClientRemoteContainerReply to save
	 * @throws ServiceException the service exception
	 */
	public void setAndSaveCRCReplyLocal(ClientRemoteContainerReply crcReply) throws ServiceException;

	/**
	 * Returns the locally saved ClientRemoteContainerReply.
	 *
	 * @return the local ClientRemoteContainerReply
	 * @throws ServiceException the service exception
	 */
	public ClientRemoteContainerReply getLocalCRCReply() throws ServiceException;

	/**
	 * Broadcasts a container description to all involved node, if the ClientRemoteContainerReply is valid.
	 *
	 * @param crcReply the crc reply
	 * @throws ServiceException the service exception
	 */
	public void putContainerDescription(ClientRemoteContainerReply crcReply) throws ServiceException;

	/**
	 * Gets the NodeDescription of all involved nodes.
	 *
	 * @return the container descriptions
	 * @throws ServiceException the service exception
	 * @see NodeDescription
	 */
	public HashMap<String, NodeDescription> getContainerDescriptions() throws ServiceException;

	/**
	 * Gets the NodeDescription of a specified container.
	 *
	 * @param containerName the container name
	 * @return the container description
	 * @throws ServiceException the service exception
	 * @see NodeDescription
	 */
	public NodeDescription getContainerDescription(String containerName) throws ServiceException;

	/**
	 * Returns the current LoadAgentMap.
	 *
	 * @return the current LoadAgentMap
	 * @throws ServiceException the service exception
	 * @see LoadAgentMap
	 */
	public LoadAgentMap getAgentMap() throws ServiceException;

	/**
	 * Sets the agent migration.
	 *
	 * @param transferAgents the agents, which has to be migrated
	 * @throws ServiceException the service exception
	 */
	public void setAgentMigration(Vector<AID_Container> transferAgents) throws ServiceException;



         public void setNewContainer2Wait4(String containerName) ;

}
