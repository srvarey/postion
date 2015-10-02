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
package org.gaia.domain.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;
import org.apache.log4j.Logger;

/**
 * This class manages the properties that are located in the file gaia.ini. In
 * the Application class the running instance can be accessed by accessing the
 * reference Application.Properties.
 *
 *
 */
public class FileProperties extends Properties {

    private static final long serialVersionUID = 7953205356494195952L;
    private GlobalInfo globalInfo = null;
    private String configFile = null;
    private final String configFileDefaultComment = "";
    private final String DEF_RUNAS = "01_RUNAS";
    private final String DEF_BENCH_VALUE = "02_BENCH_VALUE";
    private final String DEF_BENCH_EXEC_ON = "03_BENCH_EXEC_ON";
    private final String DEF_MASTER_URL = "04_MASTER_URL";
    private final String DEF_MASTER_PORT = "05_MASTER_PORT";
    private final String DEF_MASTER_PORT4MTP = "06_MASTER_PORT4MTP";
    private final String DEF_MASTER_DB_HOST = "07_MASTER_DB_HOST";
    private final String DEF_MASTER_DB_NAME = "08_MASTER_DB_NAME";
    private final String DEF_MASTER_DB_USER = "09_MASTER_DB_USER";
    private final String DEF_MASTER_DB_PSWD = "10_MASTER_DB_PSWD";
    private final String DEF_DISPATCHER = "11_DISPATCHER";
    private final String DEF_PORT = "12_PORT";
    private final String DEF_RMA = "13_RMA";
    private final String DEF_EHCACHEFILE = "14_EHCACHEFILE";
    private final String DEF_MAXTHREADS = "15_MAXTHREADS";
    private final String JNLP = "16_JNLP";
    private final String[] mandatoryProps = {this.DEF_RUNAS,
        this.DEF_BENCH_VALUE,
        this.DEF_BENCH_EXEC_ON,
        this.DEF_RMA,
        this.DEF_MASTER_URL,
        this.DEF_MASTER_PORT,
        this.DEF_MASTER_PORT4MTP,
        this.DEF_EHCACHEFILE,
        this.DEF_MAXTHREADS};
    private static final Logger logger = Logger.getLogger(FileProperties.class);

    /**
     * Default constructor of this class. Will use the default config-file
     *
     *
     * @param globalInfo
     */
    public FileProperties(GlobalInfo globalInfo) {

        this.globalInfo = globalInfo;
        this.configFile = this.globalInfo.PathConfigFile(true);
        initialize();
    }

    /**
     * Initialises the instance of this class
     */
    private void initialize() {
        // --- open or create the config file -----------------------
        try {
            // --- Does the configFile exists ? ---------------------
            logger.info("Looks for config file " + System.getProperty("user.dir") + "\\" + configFile);
            if (new File(configFile).exists()) {
                // --- configFile found -----------------------------
                this.load(new FileInputStream(configFile));
                this.checkDefaultConfigValues();
            } else {
                logger.info("Read config file From gaiama.jar");
                System.out.println("Read config file From gaiama.jar");
                InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(configFile);
                if (inputStream != null) {
                    this.load(inputStream);
                    this.checkDefaultConfigValues();
                } else {
                    logger.error("config file " + configFile + " not found on " + System.getProperty("user.dir"));
                    this.setDefaultConfigValues();
                    this.setConfig2Global();
                    this.save();
                }

                // --- configFile NOT found -------------------------
            }
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
        // --- set values of the config-file to global --------------
        setConfig2Global();
    }

    /**
     * Overrides the super-method in order to sort the entries, when the
     * store-method will be invoked during the save()-method is invoked.
     *
     * @return
     */
    @Override
    public synchronized Enumeration<Object> keys() {
        Enumeration<Object> keysEnum = super.keys();
        Vector<Object> keySorted = new Vector<>();
        Vector<String> keyList = new Vector<>();
        while (keysEnum.hasMoreElements()) {
            keyList.add((String) keysEnum.nextElement());
        }
        Collections.sort(keyList);
        for (String singelEntry : keyList) {
            keySorted.add(singelEntry);
        }
        return keySorted.elements();
    }

    /**
     * This method sets the values from the config-file to the Runtime Variables
     * in class Global ('Application.RunInfo')
     */
    private void setConfig2Global() {

        String propValue;

        // --- this.DEF_RUNAS ------------------------
        propValue = this.getProperty(this.DEF_RUNAS).trim();
        GlobalInfo.getInstance().setRunAsServer("server".equalsIgnoreCase(propValue));

        // --- this.DEF_BENCH_VALUE ------------------
        propValue = this.getProperty(this.DEF_BENCH_VALUE).trim();
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setBenchValue(0);
        } else {
            GlobalInfo.getInstance().setBenchValue(Float.parseFloat(propValue));
        }
        // --- this.DEF_BENCH_EXEC_ON ----------------
        propValue = this.getProperty(this.DEF_BENCH_EXEC_ON).trim();
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setBenchExecOn(null);
        } else {
            GlobalInfo.getInstance().setBenchExecOn(propValue);
        }

        // --- this.DEF_RMA--------------------
        propValue = this.getProperty(this.DEF_RMA).trim();
        if (propValue != null && propValue.equalsIgnoreCase("true")) {
            GlobalInfo.getInstance().setRMA(true);
        } else {
            GlobalInfo.getInstance().setRMA(false);
        }

        // --- this.DEF_MASTER_URL -------------------
        propValue = this.getProperty(this.DEF_MASTER_URL).trim();
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterURL(null);
        } else {
            GlobalInfo.getInstance().setServerMasterURL(propValue.trim());
        }
        // --- this.DEF_MASTER_PORT ------------------
        propValue = this.getProperty(this.DEF_MASTER_PORT).trim();
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterPort(0);
        } else {
            Integer propValueInt = Integer.parseInt(propValue.trim());
            GlobalInfo.getInstance().setServerMasterPort(propValueInt);
        }
        // --- this.DEF_MASTER_PORT4MTP --------------
        propValue = this.getProperty(this.DEF_MASTER_PORT4MTP);
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterPort4MTP(0);
        } else {
            Integer propValueInt = Integer.parseInt(propValue.trim());
            GlobalInfo.getInstance().setServerMasterPort4MTP(propValueInt);
        }

        // --- this.DEF_MASTER_PORT ------------------
        propValue = this.getProperty(this.DEF_MAXTHREADS).trim();
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setmaxThreadsByContainer(1000);
        } else {
            Integer propValueInt = Integer.parseInt(propValue.trim());
            GlobalInfo.getInstance().setmaxThreadsByContainer(propValueInt);
        }

        // --- this.DEF_MASTER_DB_HOST ---------------
        propValue = this.getProperty(this.DEF_MASTER_DB_HOST);
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterDBHost(null);
        } else {
            GlobalInfo.getInstance().setServerMasterDBHost(propValue.trim());
        }
        // --- this.DEF_MASTER_DB_NAME ---------------
        propValue = this.getProperty(this.DEF_MASTER_DB_NAME);
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterDBName(null);
        } else {
            GlobalInfo.getInstance().setServerMasterDBName(propValue.trim());

        }
        // --- this.DEF_MASTER_DB_USER ---------------
        propValue = this.getProperty(this.DEF_MASTER_DB_USER);
        if (propValue != null && !propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterDBUser(propValue.trim());
        } else if (propValue != null) {
            GlobalInfo.getInstance().setServerMasterDBUser(null);
        }
        // --- this.DEF_MASTER_DB_PSWD ---------------
        propValue = this.getProperty(this.DEF_MASTER_DB_PSWD);
        if (propValue != null && !propValue.isEmpty()) {
            GlobalInfo.getInstance().setServerMasterDBPswd(propValue.trim());
        } else {
            GlobalInfo.getInstance().setServerMasterDBPswd(null);
        }

        // --- this.DEF_DISPATCHER --------------
        propValue = this.getProperty(this.DEF_DISPATCHER);
        if (propValue != null && !propValue.isEmpty()) {
            String propValueString = propValue.trim();
            GlobalInfo.getInstance().setDispatcher(propValueString);
        } else {
            GlobalInfo.getInstance().setDispatcher("NO");
        }

        // --- this.DEF_EHCACHEFILE ---------------
        propValue = this.getProperty(this.DEF_EHCACHEFILE);
        if (propValue == null || propValue.isEmpty()) {
            GlobalInfo.getInstance().setEhcachFile(null);
        } else {
            GlobalInfo.getInstance().setEhcachFile(propValue.trim());
        }

        // --- this.DEF_PORT --------------
        propValue = this.getProperty(this.DEF_PORT);
        if (propValue != null && !propValue.isEmpty()) {
            Integer propValueInt = Integer.parseInt(propValue.trim());
            GlobalInfo.getInstance().setJadeLocalPort(propValueInt);
        } else {
            GlobalInfo.getInstance().setJadeLocalPort(0);
        }
        // --- this.DEF_RMA--------------------
        propValue = this.getProperty(this.JNLP);
        if (propValue != null && propValue.trim().equalsIgnoreCase("true")) {
            GlobalInfo.getInstance().setJnlpStart(true);
        } else {
            GlobalInfo.getInstance().setJnlpStart(false);
        }
    }

    /**
     * This method sets the values from the Runtime Variables in class Global
     * ('Application.RunInfo') to this property-file / config-file /
     * 'agentgui.xml'
     */
    private void setGlobal2Config() {

        // --- this.DEF_RUNAS ------------------------
        if (GlobalInfo.getInstance().isRunAsServer()) {
            this.setProperty(this.DEF_RUNAS, "Server");
        } else {
            this.setProperty(this.DEF_RUNAS, "Application");
        }

        // --- this.DEF_BENCH_VALUE ------------------
        this.setProperty(this.DEF_BENCH_VALUE, GlobalInfo.getInstance().getBenchValue().toString());
        // --- this.DEF_BENCH_EXEC_ON ----------------
        if (GlobalInfo.getInstance().getBenchExecOn() != null) {
            this.setProperty(this.DEF_BENCH_EXEC_ON, GlobalInfo.getInstance().getBenchExecOn());
        }

        // --- this.DEF_RMA --------------------
        if (GlobalInfo.getInstance().showRMA()) {
            this.setProperty(this.DEF_RMA, "true");
        } else {
            this.setProperty(this.DEF_RMA, "false");
        }
        // --- this.DEF_MAXTHREADS --------------------

        this.setProperty(this.DEF_MAXTHREADS, GlobalInfo.getInstance().getmaxThreadsByContainer().toString());

        // --- this.DEF_MASTER_URL -------------------
        if (GlobalInfo.getInstance().getServerMasterURL() == null) {
            this.setProperty(this.DEF_MASTER_URL, "");
        } else {
            this.setProperty(this.DEF_MASTER_URL, GlobalInfo.getInstance().getServerMasterURL());
        }

        // --- this.DEF_MASTER_PORT ------------------
        this.setProperty(this.DEF_MASTER_PORT, GlobalInfo.getInstance().getServerMasterPort().toString());

        // --- this.DEF_MASTER_PORT4MTP --------------
        this.setProperty(this.DEF_MASTER_PORT4MTP, GlobalInfo.getInstance().getServerMasterPort4MTP().toString());

        // --- this.DEF_MASTER_DB_HOST ---------------
        if (GlobalInfo.getInstance().getServerMasterDBHost() == null) {
            this.setProperty(this.DEF_MASTER_DB_HOST, "");
        } else {
            this.setProperty(this.DEF_MASTER_DB_HOST, GlobalInfo.getInstance().getServerMasterDBHost());
        }
        // --- this.DEF_MASTER_DB_NAME ---------------
        if (GlobalInfo.getInstance().getServerMasterDBName() == null) {
            this.setProperty(this.DEF_MASTER_DB_NAME, "");
        } else {
            this.setProperty(this.DEF_MASTER_DB_NAME, GlobalInfo.getInstance().getServerMasterDBName());
        }
        // --- this.DEF_MASTER_DB_USER ---------------
        if (GlobalInfo.getInstance().getServerMasterDBUser() == null) {
            this.setProperty(this.DEF_MASTER_DB_USER, "");
        } else {
            this.setProperty(this.DEF_MASTER_DB_USER, GlobalInfo.getInstance().getServerMasterDBUser());
        }
        // --- this.DEF_MASTER_DB_PSWD ---------------
        if (GlobalInfo.getInstance().getServerMasterDBPswd() == null) {
            this.setProperty(this.DEF_MASTER_DB_PSWD, "");
        } else {
            this.setProperty(this.DEF_MASTER_DB_PSWD, GlobalInfo.getInstance().getServerMasterDBPswd());
        }

        // --- this.DEF_DISPATCHER ---------------
        if (GlobalInfo.getInstance().getDispatcher() == null) {
            this.setProperty(this.DEF_DISPATCHER, "");
        } else {
            this.setProperty(this.DEF_DISPATCHER, GlobalInfo.getInstance().getDispatcher());
        }
        // --- this.DEF_PORT ---------------
        if (GlobalInfo.getInstance().getJadeLocalPort() == null) {
            this.setProperty(this.DEF_PORT, "");
        } else {
            this.setProperty(this.DEF_PORT, GlobalInfo.getInstance().getJadeLocalPort().toString());
        }
    }

    /**
     * This method sets the mandatory properties with default values to this
     * properties
     */
    private void setDefaultConfigValues() {
        for (String mandatoryProp : mandatoryProps) {
            // --- Here the mandatory properties in general ---------
            this.setProperty(mandatoryProp, "");
            // --- Here some special mandatory properties -----------
            if (mandatoryProp.equalsIgnoreCase(this.DEF_MASTER_PORT)) {
                this.setProperty(this.DEF_MASTER_PORT, GlobalInfo.getInstance().getJadeLocalPort().toString());
            } else if (mandatoryProp.equalsIgnoreCase(this.DEF_MASTER_PORT4MTP)) {
                this.setProperty(this.DEF_MASTER_PORT4MTP, GlobalInfo.getInstance().getServerMasterPort4MTP().toString());
            }
        } // end for

    }

    /**
     * This method checks if some mandatory properties in the the config-file
     * are available. If not, they will be added.
     */
    private void checkDefaultConfigValues() {

        boolean somethingMissed = false;
        for (String mandatoryProp : mandatoryProps) {
            if (!this.containsKey(mandatoryProp)) {
                somethingMissed = true;
            }
        }
        // --- If something was not there, save the file --
        if (somethingMissed) {
            this.setConfig2Global();
            this.save();
        }
    }

    /**
     * This method saves the current settings to the property file
     */
    public void save() {
        // --- getting the current values of the mandantory variables ---
        this.setGlobal2Config();
        try {
            this.store(new FileOutputStream(configFile), configFileDefaultComment);
        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }
    }
}
