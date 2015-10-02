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

import jade.core.AID;
import jade.core.GenericCommand;
import jade.core.IMTPException;
import jade.core.Location;
import jade.core.Node;
import jade.core.ServiceException;
import jade.core.SliceProxy;
import java.util.Vector;
import org.gaia.simulationService.load.LoadAgentMap.AID_Container;
import org.gaia.simulationService.load.LoadInformation.Container2Wait4;
import org.gaia.simulationService.load.LoadThresholdLevels;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This class provides the reals functionalities for the
 * {@link LoadServiceSlice}.
 */
public class LoadServiceProxy extends SliceProxy implements LoadServiceSlice {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -7016240061703852319L;

    // ----------------------------------------------------------
    // --- Method to get the Load-Informations of all ----------- 
    // --- containers ----------------------------- S T A R T ---
    // ----------------------------------------------------------
	/* (non-Javadoc)
     */
    @Override
    public boolean startAgent(String nickName, String agentClassName, Object[] args) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_START_AGENT, LoadService.NAME, null);
            cmd.addParam(nickName);
            cmd.addParam(agentClassName);
            cmd.addParam(args);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (Boolean) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public String startNewRemoteContainer(RemoteContainerConfig remoteConfig) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_START_NEW_REMOTE_CONTAINER, LoadService.NAME, null);
            cmd.addParam(remoteConfig);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (String) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public void setDefaults4RemoteContainerConfig(RemoteContainerConfig remoteConfig) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_SET_DEFAULTS_4_REMOTE_CONTAINER_CONFIG, LoadService.NAME, null);
            cmd.addParam(remoteConfig);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public RemoteContainerConfig getAutoRemoteContainerConfig() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_AUTO_REMOTE_CONTAINER_CONFIG, LoadService.NAME, null);
            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (RemoteContainerConfig) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public Container2Wait4 getNewContainer2Wait4Status(String containerName2Wait4) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_NEW_CONTAINER_2_WAIT_4_STATUS, LoadService.NAME, null);
            cmd.addParam(containerName2Wait4);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (Container2Wait4) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public Location getLocation() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_LOCATION, LoadService.NAME, null);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            Location loc = (Location) result;
            return loc;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public void setThresholdLevels(LoadThresholdLevels thresholdLevels) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_SET_THRESHOLD_LEVEL, LoadService.NAME, null);
            cmd.addParam(thresholdLevels);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    
    @Override
    public PlatformLoad measureLoad() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_MEASURE_LOAD, LoadService.NAME, null);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (PlatformLoad) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public AID[] getAIDList() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_AID_LIST, SimulationService.NAME, null);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (AID[]) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public AID[] getAIDListSensorAgents() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_AID_LIST_SENSOR, SimulationService.NAME, null);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (AID[]) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }

    /* (non-Javadoc)
     */
    @Override
    public void setAgentMigration(Vector<AID_Container> transferAgents) throws IMTPException {
        try {
            GenericCommand cmd = new GenericCommand(SERVICE_SET_AGENT_MIGRATION, LoadService.NAME, null);
            cmd.addParam(transferAgents);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }
    // ----------------------------------------------------------
    // --- Method to get the Load-Informations of all ----------- 
    // --- containers ----------------------------- E N D -------
    // ----------------------------------------------------------

    /* (non-Javadoc)
     */
    @Override
    public void putContainerDescription(ClientRemoteContainerReply crcReply) throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_PUT_CONTAINER_DESCRIPTION, LoadService.NAME, null);
            cmd.addParam(crcReply);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }

    }

    /* (non-Javadoc)
     */
    @Override
    public ClientRemoteContainerReply getCRCReply() throws IMTPException {

        try {
            GenericCommand cmd = new GenericCommand(SERVICE_GET_CONTAINER_DESCRIPTION, LoadService.NAME, null);

            Node n = getNode();
            Object result = n.accept(cmd);
            if ((result != null) && (result instanceof Throwable)) {
                if (result instanceof IMTPException) {
                    throw (IMTPException) result;
                } else {
                    throw new IMTPException("An undeclared exception was thrown", (Throwable) result);
                }
            }
            return (ClientRemoteContainerReply) result;
        } catch (ServiceException se) {
            throw new IMTPException("Unable to access remote node", se);
        }
    }
}
