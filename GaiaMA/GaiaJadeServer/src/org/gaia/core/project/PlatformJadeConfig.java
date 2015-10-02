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

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.event.NotificationService;
import jade.core.faultRecovery.FaultRecoveryService;
import jade.core.management.AgentManagementService;
import jade.core.messaging.MessagingService;
import jade.core.messaging.PersistentDeliveryService;
import jade.core.messaging.TopicManagementService;
import jade.core.migration.InterPlatformMobilityService;
import jade.core.mobility.AgentMobilityService;
import jade.core.nodeMonitoring.UDPNodeMonitoringService;
import jade.core.replication.AddressNotificationService;
import jade.core.replication.MainReplicationService;
import jade.imtp.leap.nio.BEManagementService;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.core.network.PortChecker;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.SimulationService;

/**
 * With this class, the Profile of a new JADE-Container can be configured. To
 * use this class, just create a new instance of it and go throw configurations
 * like in the example below.<br> After configuration you can use the method
 * 'getNewInstanceOfProfilImpl()' which returns a new Instance of
 * 'jade.core.Profile'. This can be used to create a new JADE-Container.
 *
 */
public class PlatformJadeConfig implements Serializable {

    private static final long serialVersionUID = -9062155032902746361L;
    // --- Services 'Activated automatically' ---------------------------------
    public static final String SERVICE_MessagingService = MessagingService.class.getName();
    public static final String SERVICE_AgentManagementService = AgentManagementService.class.getName();
    // --- Services 'Active by default' ---------------------------------------
    public static final String SERVICE_AgentMobilityService = AgentMobilityService.class.getName();
    public static final String SERVICE_NotificationService = NotificationService.class.getName();
    // --- Services 'Inactive by default' -------------------------------------
    public static final String SERVICE_MainReplicationService = MainReplicationService.class.getName();
    public static final String SERVICE_FaultRecoveryService = FaultRecoveryService.class.getName();
    public static final String SERVICE_AddressNotificationService = AddressNotificationService.class.getName();
    public static final String SERVICE_TopicManagementService = TopicManagementService.class.getName();
    public static final String SERVICE_PersistentDeliveryService = PersistentDeliveryService.class.getName();
    public static final String SERVICE_UDPNodeMonitoringServ = UDPNodeMonitoringService.class.getName();
    public static final String SERVICE_BEManagementService = BEManagementService.class.getName();

    public static final String SERVICE_LoadService = LoadService.class.getName();
    public static final String SERVICE_SimulationService = SimulationService.class.getName();
    // --- Add-On-Services ----------------------------------------------------
    public static final String SERVICE_InterPlatformMobilityService = InterPlatformMobilityService.class.getName();
    /**
     * Array of services, which will be started with JADE in every case
     */
    private static final String[] autoServices = {SERVICE_MessagingService, SERVICE_AgentManagementService};
    private static final String AUTOSERVICE_TextAddition = "starts automatically";
    // --- Runtime variables --------------------------------------------------

    @XmlElement(name = "useLocalPort")
    private Integer useLocalPort = Application.getGlobalInfo().getJadeLocalPort();
    @XmlElementWrapper(name = "serviceList")
    @XmlElement(name = "service")
    private HashSet<String> useServiceList = new HashSet<>();
    @XmlTransient
    private DefaultListModel listModelServices = null;

    /**
     * 0
     * Constructor of this class.
     */
    public PlatformJadeConfig() {
    }

    /**
     * This method returns the TextAddition if a Service is an automatically
     * starting service of JADE.
     *
     * @return the auto service text addition
     */
    public static String getAutoServiceTextAddition() {
        return StringUtils.SPACE + AUTOSERVICE_TextAddition + StringUtils.SPACE;
    }

    /**
     * Returns if a service generally starts while JADE is starting.
     *
     * @param serviceReference the service reference
     * @return true, if is auto service
     */
    public static boolean isAutoService(String serviceReference) {
        for (int i = 0; i < autoServices.length; i++) {
            if (autoServices[i].equalsIgnoreCase(serviceReference)) {
                return true;
            }
        }
        return false;
    }



    /**
     * This Method returns a new Instance of Profil, which can be used for
     * starting a new JADE-Container.
     *
     * @return jade.core.Profile
     */
    public Profile getNewInstanceOfProfilImpl() {
        Profile prof = new ProfileImpl();
        prof = this.setProfileLocalPort(prof);
        prof = this.setProfileServices(prof);
        return prof;
    }

    /**
     * This Method scans for a free Port, which can be used for the
     * JADE-Container. It's starts searching for a free Port on
     * 'portSearchStart'. If not available, it checks the next higher Port and
     * so on.
     *
     * @param portSearchStart the port search start
     */
    private void findFreePort(int portSearchStart) {
        PortChecker portCheck = new PortChecker(portSearchStart);
        useLocalPort = portCheck.getFreePort();
    }

    /**
     * Adds the local configured 'LocalPort' to the input instance of Profile.
     *
     * @param profile the profile
     * @return jade.core.Profile
     */
    private Profile setProfileLocalPort(Profile profile) {
        this.findFreePort(useLocalPort);
        profile.setParameter(Profile.LOCAL_PORT, useLocalPort.toString());
        return profile;
    }

    /**
     * Adds the local configured services to the input instance of Profile.
     *
     * @param profile the profile
     * @return jade.core.Profile
     */
    private Profile setProfileServices(Profile profile) {
        String serviceListString = this.getServiceListArgument();
        if (serviceListString.equalsIgnoreCase("") == false || serviceListString != null) {
            profile.setParameter(Profile.SERVICES, serviceListString);
        }
        return profile;
    }

    /**
     * This method walks through the HashSet of configured Services and returns
     * them as a String separated with a semicolon (';').
     *
     * @return String
     */
    public String getServiceListArgument() {
        String serviceListString = "";
        Iterator<String> it = useServiceList.iterator();
        while (it.hasNext()) {
            String singeleService = it.next();
            if (singeleService.endsWith(";") == true) {
                serviceListString += singeleService;
            } else {
                serviceListString += singeleService + ";";
            }
        }
        return serviceListString;
    }

    /**
     * Can be used in order to add a class reference to an extended
     * JADE-BaseService.
     *
     * @param serviceClassReference the service class reference
     */
    public void addService(String serviceClassReference) {

        if (this.isUsingService(serviceClassReference) == false && serviceClassReference.contains(getAutoServiceTextAddition()) == false) {

            // --- add to the local HashSet -------------------------
            this.useServiceList.add(serviceClassReference);
            // --- add to the DefaultListModel ----------------------
            DefaultListModel delimo = this.getListModelServices();
            delimo.addElement(serviceClassReference);
            // --- sort the ListModel -------------------------------
            this.sortListModelServices();
        }
    }

    /**
     * Can be used in order to remove a class reference to an extended
     * JADE-BaseService.
     *
     * @param serviceClassReference the service class reference
     */
    public void removeService(String serviceClassReference) {

        if (this.isUsingService(serviceClassReference) == true) {
            // --- remove from the local HashSet --------------------
            this.useServiceList.remove(serviceClassReference);
            // --- remove from the DefaultListModel -----------------
            DefaultListModel delimo = this.getListModelServices();
            delimo.removeElement(serviceClassReference);

        }
    }

    /**
     * This method will remove all Services from the current Profile.
     */
    public void removeAllServices() {
        this.useServiceList.clear();
        this.listModelServices.removeAllElements();
    }

    /**
     * Checks if a Service is configured for this instance. The requested
     * Service can be given with the actual class of the service
     *
     * @param requestedService the requested service
     * @return boolean
     */
    public boolean isUsingService(String requestedService) {
        if (useServiceList.contains(requestedService) == true) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Counts the number of services which are currently configured.
     *
     * @return the integer
     */
    public Integer countUsedServices() {
        return this.useServiceList.size();
    }

    /**
     * With this class the LocalPort, which will be used from a JADE-Container
     * can be set.
     *
     * @param port2Use the new local port
     */
    public void setLocalPort(int port2Use) {
        useLocalPort = port2Use;
    }

    /**
     * Returns the current Port which is configured for a JADE-Container.
     *
     * @return Integer
     */
    public Integer getLocalPort() {
        return useLocalPort;
    }

    /**
     * Gets the list model services.
     *
     * @return the listModelServices
     */
    @XmlTransient
    public DefaultListModel getListModelServices() {
        if (listModelServices == null) {
            listModelServices = new DefaultListModel();
            Iterator<String> it = useServiceList.iterator();
            while (it.hasNext()) {
                listModelServices.addElement(it.next());
            }
            this.sortListModelServices();
        }
        return listModelServices;
    }

    /**
     * This method will sort the current list model for the chosen services.
     */
    private void sortListModelServices() {

        if (useServiceList.size() > 1) {
            Vector<String> sorty = new Vector<String>(useServiceList);
            Collections.sort(sorty);
            this.listModelServices.removeAllElements();
            for (int i = 0; i < sorty.size(); i++) {
                this.listModelServices.addElement(sorty.get(i));
            }
        }
    }

    /**
     * This Method compares the current instance with another instances of this
     * class and returns true, if they are logical identical.
     *
     * @param jadeConfig2 the jade config2
     * @return boolean
     */
    public boolean isEqual(PlatformJadeConfig jadeConfig2) {

        // --- Selbe Anzahl der ausgew�hlten Services ? -------------
        if (this.countUsedServices() != jadeConfig2.countUsedServices()) {
            return false;
        }
        // --- Sind die ausgew�hlten Services identisch? ------------
        Iterator<String> it = this.useServiceList.iterator();
        while (it.hasNext()) {
            String currService = it.next();
            if (jadeConfig2.isUsingService(currService) == false) {
                return false;
            }
        }
        // --- Soll der selbe Jade LocalPort verwendet werden ? ----
        if (jadeConfig2.getLocalPort().equals(this.getLocalPort())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This Method returns a String which shows the current configuration of
     * this instance.
     *
     * @return String
     */
    @Override
    public String toString() {
        StringBuilder bugOut = new StringBuilder("LocalPort:");
        bugOut.append(useLocalPort);
        bugOut.append(";");
        bugOut.append("Services:");
        bugOut.append(getServiceListArgument());
        return bugOut.toString();
    }
}
