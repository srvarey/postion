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
package org.gaia.core.sim.setup;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 * This class represents the list of setups available in a {@link Project}.
 *
 *
 */
public class SimulationSetups extends Hashtable<String, String> {

    private static final Logger logger = Logger.getLogger(SimulationSetups.class);
    private static final long serialVersionUID = -9078535303459653695L;
    public static final String DEFAULT_SETUP_NAME = "default";
    public static final String CHANGED = "SimSetups";
    public static final int SIMULATION_SETUP_LOAD = 0;
    public static final int SIMULATION_SETUP_ADD_NEW = 1;
    public static final int SIMULATION_SETUP_COPY = 2;
    public static final int SIMULATION_SETUP_REMOVE = 3;
    public static final int SIMULATION_SETUP_RENAME = 4;
    public static final int SIMULATION_SETUP_SAVED = 5;

    private SimulationSetup currSimSetup = null;
    private String currSimSetupName = null;
    private String currSimXMLFile = null;

    /**
     * Constructor for this class.
     *
     * @param project the project
     * @param currentSimulationSetup the current simulation setup
     */
    public SimulationSetups(String currentSimulationSetup) {
        currSimSetupName = currentSimulationSetup;
    }

    /**
     * This Method creates the 'default' - Simulation-Setup.
     */
    public void setupCreateDefault() {
        this.currSimSetupName = SimulationSetups.DEFAULT_SETUP_NAME;
    }

    /**
     * Adds a new Setup to this Hashtable.
     *
     * @param name the name
     * @param newFileName the new file name
     */
    public void setupAddNew(String name, String newFileName) {
        this.put(name, newFileName);
    }

    /**
     * Finds and returns the first Setup name using an alphabetic order.
     *
     * @return the first Setup name using an alphabetic order
     */
    public String getFirstSetup() {

        if (this.size() == 0) {
            return null;
        }

        Vector<String> v = new Vector<String>(this.keySet());
        Collections.sort(v, String.CASE_INSENSITIVE_ORDER);
        Iterator<String> it = v.iterator();
        while (it.hasNext()) {
            currSimSetupName = it.next();
            break;
        }
        return currSimSetupName;
    }

    /**
     * This Method checks if the incomming Setup-Name is already used in the
     * current List of Setups (Hashmap).
     *
     * @param setupName2Test the setup name2 test
     * @return true if the setup name is already used in the current list of
     * setups
     */
    public boolean containsSetupName(String setupName2Test) {

        String testSetupName = setupName2Test.toLowerCase();
        Iterator<String> it = this.keySet().iterator();
        while (it.hasNext()) {
            String setupName = it.next().toLowerCase();
            if (setupName.equalsIgnoreCase(testSetupName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the curr sim setup.
     *
     * @return the currSimSetup
     */
    public SimulationSetup getCurrSimSetup() {
        return currSimSetup;
    }

    /**
     * Sets the curr sim setup.
     *
     * @param currSimSetup the currSimSetup to set
     */
    public void setCurrSimSetup(SimulationSetup currSimSetup) {
        this.currSimSetup = currSimSetup;
    }

    /**
     * Sets the curr sim xml file.
     *
     * @param currSimXMLFile the currSimXMLFile to set
     */
    public void setCurrSimXMLFile(String currSimXMLFile) {
        this.currSimXMLFile = currSimXMLFile;
    }

    /**
     * Gets the curr sim xml file.
     *
     * @return the currSimXMLFile
     */
    public String getCurrSimXMLFile() {
        return currSimXMLFile;
    }
}
