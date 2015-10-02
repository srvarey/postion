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
package org.gaia.core.network;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.apache.log4j.Logger;
import org.gaia.jade.server.Application;

/**
 * This class can be used in order to evaluate the currently configured JADE URL
 * and its ports.
 *
 */
public class JadeUrlChecker {

    private static final Logger logger = Logger.getLogger(JadeUrlChecker.class);
    private InetAddress currAddress = null;
    private InetAddress addressLocal = null;
    private InetAddress addressLocalAlt = null;
    private String currURL = null;
    private Integer currPort = 0;
    private Integer currPort4MTP = 0;

    /**
     * Constructor of this class.
     *
     * @param url The URL on which JADE is running or acting (e.g
     * "localhost:1099/JADE")
     */
    public JadeUrlChecker(String url) {

        currURL = this.filterPort(url);
        try {
            currAddress = InetAddress.getByName(currURL);
            addressLocal = InetAddress.getLocalHost();
            addressLocalAlt = InetAddress.getByName("127.0.0.1");
            if (currAddress.equals(addressLocalAlt)) {
                currAddress = addressLocal;
            }
        } catch (UnknownHostException err) {            
            logger.error("[" + Application.getGlobalInfo().getApplicationTitle() + "] Error while try to resolve the address '" + err.getLocalizedMessage() + "'. Please check your Agent.GUI - start options.");
            currURL = null;
        }

    }

    /**
     * Filters the Port on which JADE is running or acting.
     *
     * @param url The URL on which JADE is running or acting (e.g
     * "localhost:1099/JADE")
     * @return The URL or IP, which is used by JADE (e.g "localhost")
     */
    private String filterPort(String url) {

        String workURL = url;
        String workPort ;
        String workPortNew = "";

        if (workURL == null) {
            return null;
        }
        if (url.contains(":")) {

            workURL = url.substring(0, url.indexOf(":"));
            workPort = url.substring(url.indexOf(":") + 1).trim();
            String workPortArr[] = workPort.split("");

            for (int i = 0; i < workPortArr.length; i++) {
                if (workPortArr[i].equalsIgnoreCase("") == false) {
                    String sngChar = workPortArr[i];
                    if (sngChar.matches("[0-9]") == true) {
                        workPortNew += sngChar;
                    } else {
                        break;
                    }
                }
            }
            currPort = Integer.parseInt(workPortNew);
        }
        return workURL;
    }

    /**
     * Returns true, if the current JADE instance is located on a local machine.
     *
     * @return True, if the JADE URL is pointing to the local machine
     */
    public boolean isLocalhost() {
        if (currAddress.equals(addressLocal)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Provides the JADE URL out of the analysed subcomponents like IP, Port and
     * "/JADE"-Suffix.
     *
     * @return JADE URL
     */
    public String getJADEurl() {
        if (currAddress != null && !currPort.equals(-1) && !currAddress.getHostAddress().equalsIgnoreCase("127.0.0.1")) {
            return currAddress.getHostAddress() + ":" + currPort + "/JADE";
        } else if (currAddress.getHostAddress().equalsIgnoreCase("127.0.0.1")){
            return "localhost:" + currPort + "/JADE";
        } else {
            return null;
        }
    }

    /**
     * return the URL for the MTP of JADE.
     *
     * @return JADE URL for MTP
     */
    public String getJADEurl4MTP() {
        if (currAddress != null && !currPort4MTP.equals(-1)) {
            return "http://" + currAddress.getHostAddress() + ":" + currPort4MTP + "/acc";
        
        } else {
            return null;
        }
    }

    /**
     * Provides the IP-Address of the current JADE-URL.
     *
     * @return IP address (e.g. 127.0.0.1)
     */
    public String getHostIP() {
        if (currAddress == null) {
            return null;
        } else {
            return currAddress.getHostAddress();
        }
    }

    /**
     * Provides the host name of the current JADE-URL.
     *
     * @return host name (e.g. 'localhost')
     */
    public String getHostName() {
        if (currAddress == null) {
            return null;
        } else {
            return currAddress.getHostName();
        }
    }

    /**
     * Provides the port number of JADE.
     *
     * @return The port number
     */
    public Integer getPort() {
        return currPort;
    }

    /**
     * Can be used to set the port number for JADE.
     *
     * @param newPort The port to be used
     */
    public void setPort(Integer newPort) {
        currPort = newPort;
    }

    /**
     * Provides the port number of JADE, that is used for the MTP of the
     * MainContainer.
     *
     * @return the port4 mtp
     */
    public Integer getPort4MTP() {
        return currPort4MTP;
    }

    /**
     * Can be used to set the port number for the MTP of the Main-Container.
     *
     * @param newPort4MTP the new port4 mtp
     */
    public void setPort4MTP(Integer newPort4MTP) {
        this.currPort4MTP = newPort4MTP;
    }
}
