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
package org.gaia.simulationService.ontology;

import jade.content.*;
import jade.util.leap.*;

public class RemoteContainerConfig implements Concept {

    /**
     * Protege name: jadeContainerName
     */
    private String jadeContainerName;

    public void setJadeContainerName(String value) {
        this.jadeContainerName = value;
    }

    public String getJadeContainerName() {
        return this.jadeContainerName;
    }
    /**
     * Protege name: jvmMemAllocInitial
     */
    private String jvmMemAllocInitial;

    public void setJvmMemAllocInitial(String value) {
        this.jvmMemAllocInitial = value;
    }

    public String getJvmMemAllocInitial() {
        return this.jvmMemAllocInitial;
    }
    /**
     * Protege name: jvmMemAllocMaximum
     */
    private String jvmMemAllocMaximum;

    public void setJvmMemAllocMaximum(String value) {
        this.jvmMemAllocMaximum = value;
    }

    public String getJvmMemAllocMaximum() {
        return this.jvmMemAllocMaximum;
    }
    /**
     * Protege name: jadeServices
     */
    private String jadeServices;

    public void setJadeServices(String value) {
        this.jadeServices = value;
    }

    public String getJadeServices() {
        return this.jadeServices;
    }
    /**
     * Protege name: jadePort
     */
    private String jadePort;

    public void setJadePort(String value) {
        this.jadePort = value;
    }

    public String getJadePort() {
        return this.jadePort;
    }
    /**
     * Protege name: jadeShowGUI
     */
    private boolean jadeShowGUI;

    public void setJadeShowGUI(boolean value) {
        this.jadeShowGUI = value;
    }

    public boolean getJadeShowGUI() {
        return this.jadeShowGUI;
    }
    /**
     * Protege name: jadeIsRemoteContainer
     */
    private boolean jadeIsRemoteContainer;

    public void setJadeIsRemoteContainer(boolean value) {
        this.jadeIsRemoteContainer = value;
    }

    public boolean getJadeIsRemoteContainer() {
        return this.jadeIsRemoteContainer;
    }
    /**
     * Protege name: jadeHost
     */
    private String jadeHost;

    public void setJadeHost(String value) {
        this.jadeHost = value;
    }

    public String getJadeHost() {
        return this.jadeHost;
    }
   
    /**
     * Protege name: preventUsageOfUsedComputer
     */
    private boolean preventUsageOfUsedComputer;

    public void setPreventUsageOfUsedComputer(boolean value) {
        this.preventUsageOfUsedComputer = value;
    }

    public boolean getPreventUsageOfUsedComputer() {
        return this.preventUsageOfUsedComputer;
    }
    /**
     * Protege name: jadeJarIncludeList
     */
    private List jadeJarIncludeList = new ArrayList();

    public void addJadeJarIncludeList(String elem) {
        List oldList = this.jadeJarIncludeList;
        jadeJarIncludeList.add(elem);
    }

    public boolean removeJadeJarIncludeList(String elem) {
        boolean result = jadeJarIncludeList.remove(elem);
        return result;
    }

    public void clearAllJadeJarIncludeList() {
        jadeJarIncludeList.clear();
    }

    public Iterator getAllJadeJarIncludeList() {
        return jadeJarIncludeList.iterator();
    }

    public List getJadeJarIncludeList() {
        return jadeJarIncludeList;
    }

    public void setJadeJarIncludeList(List l) {
        jadeJarIncludeList = l;
    }
}
