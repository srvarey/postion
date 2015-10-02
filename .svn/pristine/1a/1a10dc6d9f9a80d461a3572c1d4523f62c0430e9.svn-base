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
import java.util.HashSet;
import javafx.application.Application;
public class GlobalInfo {

    private static GlobalInfo globalInfo;

    // --- constant values --------------------------------------------------
    private static String localAppTitle = "Gaia Jade Dispatcher";
    private final static String localFileRunnableJar = "GaiaJadeServerJar.jar";
    private final static String fileSeparator = File.separator;
    private final static String newLineSeparator = System.getProperty("line.separator");
    // --- JADE-Variables ---------------------------------------------------
    private Integer localeJadeLocalPort = 1099;
    // --- Variables --------------------------------------------------------
    public final static String ExecutedOverIDE = "IDE";
    public final static String ExecutedOverAgentGuiJar = "Executable";
    private static String localAppExecutedOver = ExecutedOverIDE;
    private static String localBaseDir = "";
    private static final String localPathAgentGUI = "bin";
    private static final String localPathJade = "lib" + fileSeparator + "jade" + fileSeparator + "lib";
    private static final String localPathProperty = "";//properties" + fileSeparator;

    private static final String localFileProperties = "gaia.ini";
    private boolean filePropRunAsServer = false;
    private float filePropBenchValue = 0;
    private String filePropBenchExecOn = null;
    private boolean filePropRMA = false;
    private String filePropServerMasterURL = null;
    private Integer filePropServerMasterPort = this.localeJadeLocalPort;
    private Integer filePropServerMasterPort4MTP = 7778;
    private String filePropServerMasterDBHost = null;
    private String filePropServerMasterDBName = null;
    private String filePropServerMasterDBUser = null;
    private String filePropServerMasterDBPswd = null;
    private String filePropEhcacheFile = null;
    private Integer filePropmaxThreadsByContainer = null;
    private String dispatcher = null;
    private boolean jnlpStart = false;
    // --- Reminder information for file dialogs ----------------------------
    private File lastSelectedFolder = null;
    // --- FileProperties and VersionInfo -----------------------------------
    /**
     * Holds the instance of the file properties which are defined in
     * '/properties/gaia.ini'
     */
    private FileProperties fileProperties = null;
    private static boolean isRunningAsServer = false;

    /**
     * The Enumeration of possible ExecutionModes. In order to get the current
     * execution mode use see
     *
     * @see GlobalInfo#getExecutionMode()
     */
    public enum ExecutionMode {

        APPLICATION, SERVER, SERVER_MASTER, SERVER_SLAVE
    }

    public boolean isJnlpStart() {
        return jnlpStart;
    }

    public void setJnlpStart(boolean _jnlpStart) {
        jnlpStart = _jnlpStart;
    }

    /**
     * Constructor of this class. //
     */
    @SuppressWarnings("empty-statement")
    public GlobalInfo() {

        Integer cutAt;
        String[] JCP_Files = System.getProperty("java.class.path").split(System.getProperty("path.separator"));
        String[] JCP_Folders = JCP_Files.clone();
        HashSet<String> Folders = new HashSet<>();

        // --- Class-Path untersuchen ---------------------------------------
        for (int i = 0; i < JCP_Files.length; i++) {

            if (JCP_Files[i].endsWith(localFileRunnableJar)) {
                localAppExecutedOver = ExecutedOverAgentGuiJar;

                File agentGuiJar = new File(JCP_Files[i]);
                String agentGuiJarPath = agentGuiJar.getAbsolutePath();
                cutAt = agentGuiJarPath.lastIndexOf(fileSeparator) + 1;
                localBaseDir = agentGuiJarPath.substring(0, cutAt);
            }
            if (JCP_Files[i].endsWith(".jar")) {
                cutAt = JCP_Files[i].lastIndexOf(fileSeparator);
                if (cutAt != -1) { //only if seperator was actually found
                    JCP_Folders[i] = JCP_Folders[i].substring(0, cutAt);
                }
            }
            Folders.add(JCP_Folders[i]);
        }

        if (localAppExecutedOver.equals(ExecutedOverIDE)) {
            JCP_Folders = (String[]) Folders.toArray(new String[Folders.size()]);
            for (String JCP_Folder : JCP_Folders) {
                if (JCP_Folder.endsWith(localPathAgentGUI)) {
                    // --- bin-Verzeichnis gefunden ---
                    cutAt = JCP_Folder.lastIndexOf(localPathAgentGUI);
                    localBaseDir = JCP_Folder.substring(0, cutAt);
                    break;
                }
            }

        }

    }

    /**
     * Returns the application-wide information system.
     *
     * @return the global info
     */
    public static GlobalInfo getInstance() {
        if (globalInfo == null) {
            globalInfo = new GlobalInfo();
            globalInfo.initialize();
        }
        return globalInfo;
    }
    /**
     * Initialises this class by reading the file properties and the current
     * version information.
     */
    public void initialize() {
        this.getFileProperties();
    }


    /**
     * Checks if is running as server.
     *
     * @return true, if jade is running as server tool
     */
    public static boolean isRunningAsServer() {
        return isRunningAsServer;
    }
    /**
     * Returns the title/name of the application
     *
     * @return the current application title
     */
    public String getApplicationTitle() {
        return localAppTitle;
    }

    ;
	/**
	 * This method can be used in order to set a new application title.
	 * For the actual renaming of the Application, use the method in the
	 * class agentgui.core.Application
	 *
	 * @param newApplicationTitle the Application Title to set
	 * @see Application#setApplicationTitle(String)
	 */
	public void setApplicationTitle(String newApplicationTitle) {
        GlobalInfo.localAppTitle = newApplicationTitle;
    }

    /**
     * This method can be used in order to evaluate how Agent.GUI is currently
     * executed
     *
     * @return - 'IDE', if Agent.GUI is executed out of the IDE, where Agent.GUI
     * is developed OR<br> - 'Executable', if Agent.GUI is running as executable
     * jar-File
     */
    public String AppExecutedOver() {
        return localAppExecutedOver;
    }

    /**
     * This method returns the actual String for a new line string
     *
     * @return a String that can be used for new lines in text
     */
    public String getNewLineSeparator() {
        return newLineSeparator;
    }


    // --- Allgemeine Verzeichnisangaben ---------------------
    /**
     * This method returns the base path of the application (e. g.
     * 'C:\Java_Workspace\AgentGUI\')
     *
     * @return the base path of the application
     */
    public String PathBaseDir() {
        return localBaseDir;
    }

    /**
     * Aktuelles KLassenverzeichnis der Anwendung fuer die IDE-Umgebung
     */
    /**
     * This method returns the base path of the application, when Agent.GUI is
     * running in its development environment / IDE (e. g.
     * 'C:\Java_Workspace\AgentGUI\bin\')
     *
     * @return the binary- or bin- base path of the application
     */
    public String PathBaseDirIDE_BIN() {
        return localBaseDir + localPathAgentGUI + fileSeparator;
    }

    /**
     * This method can be invoked in order to get the path to the JADE libraries
     * (e. g. 'lib\jade\lib').
     *
     * @param absolute set true if you want to get the full path to this
     * @return the path to the JADE libraries
     */
    public String PathJade(boolean absolute) {
        if (absolute) {
            return FilePath2Absolute(localPathJade);
        } else {
            return localPathJade;
        }
    }

    /**
     * This method can be invoked in order to get the path to the property
     * folder 'properties\'.
     *
     * @param absolute set true if you want to get the full path to this
     * @return the path reference to the property folder
     */
    public String PathProperty(boolean absolute) {
        if (absolute) {
            return FilePath2Absolute(localPathProperty);
        } else {
            return localPathProperty;
        }
    }

    /**
     * This method will return the concrete path to the property file 'gaia.ini'
     * relatively or absolute
     *
     * @param absolute set true if you want to get the full path to this
     * @return the path reference to the property file gaia.ini
     */
    public String PathConfigFile(boolean absolute) {
        if (absolute) {
            return PathProperty(true) + localFileProperties;
        } else {
            return PathProperty(false) + localFileProperties;
        }
    }

    /**
     * This method will convert relative Agent.GUI paths to absolute paths. (e.
     *
     * @param filePathRelative The relative path to convert
     * @return The absolute path of the given relative one
     */
    private String FilePath2Absolute(String filePathRelative) {
        String pathAbsolute = localBaseDir + filePathRelative;
        return pathAbsolute;
    }

    /**
     * This method can be used in order to set the port on which JADE is running
     * (by default: 1099)
     *
     * @param localeJadeDefaultPort the localeJadeDefaultPort to set
     */
    public void setJadeLocalPort(int localeJadeDefaultPort) {
        localeJadeLocalPort = localeJadeDefaultPort;
    }

    /**
     * Returns the port on which JADE will run.
     *
     * @return the localeJadeDefaultPort
     */
    public Integer getJadeLocalPort() {
        return localeJadeLocalPort;
    }


    // ---------------------------------------------------------
    // --- File-Properties -------------------------------------
    // ---------------------------------------------------------
    /**
     * Gets the file properties.
     *
     * @return the file properties
     */
    public FileProperties getFileProperties() {
        if (this.fileProperties == null) {
            this.fileProperties = new FileProperties(this);
        }
        return this.fileProperties;
    }

    /**
     * This method can be used in order to configure the current execution of
     * Agent.GUI as an server tool (for 'server.master' or 'server.slave')
     *
     * @param runAsServer The boolean to set
     */
    public void setRunAsServer(boolean runAsServer) {
        this.filePropRunAsServer = runAsServer;
        setRunningAsServer(runAsServer);
    }

    /**
     * Sets that jade is running as server or not.
     *
     * @param runningAsServer the new running as server
     */
    public static void setRunningAsServer(boolean runningAsServer) {
        isRunningAsServer = runningAsServer;
    }
    /**
     * Can be accessed in order to find out whether the current execution of
     * Agent.GUI is running as server or as application
     *
     * @return true if the application is running in server mode - otherwise
     * false
     */
    public boolean isRunAsServer() {
        return this.filePropRunAsServer;
    }

    // ---- SciMark 2.0 Benchmark ----------------------------
    /**
     * Returns the benchmark value of the current execution of Agent.GUI that
     * will be measured at the initial program execution (if required)
     *
     * @return The benchmark value, stored in the file properties
     * @see BenchmarkMeasurement
     * @see FileProperties
     */
    public Float getBenchValue() {
        return filePropBenchValue;
    }

    /**
     * Can be used in order to set the benchmark value in the file properties
     *
     * @param benchValue The result of the initial benchmark
     * @see BenchmarkMeasurement
     * @see FileProperties
     */
    public void setBenchValue(float benchValue) {
        this.filePropBenchValue = benchValue;
    }

    /**
     * Here the reminder value can be get, which stores the computer/host name
     * on which Agent.GUI was executed the last time (evaluated due:
     * <code>InetAddress.getLocalHost().getCanonicalHostName())</code>.<br> This
     * can prevent the execution of the benchmark measurement, if the
     * application will be executed on the same machine next time.
     *
     * @return the filePropBenchExecOn
     * @see BenchmarkMeasurement
     * @see FileProperties
     */
    public String getBenchExecOn() {
        return filePropBenchExecOn;
    }

    /**
     * This method can be used in order to set the reminder value for the
     * computer/host name on which the benchmark test was executed the last time
     * (With Agent.GUI evaluated due:
     * <code>InetAddress.getLocalHost().getCanonicalHostName()</code>)
     *
     * @param benchExecOn the filePropBenchExecOn to set
     * @see BenchmarkMeasurement
     * @see FileProperties
     */
    public void setBenchExecOn(String benchExecOn) {
        this.filePropBenchExecOn = benchExecOn;
    }

    /**
     * Set value (true or false) to see or not RMA
     *
     * @param rma
     */
    public void setRMA(boolean rma) {
        this.filePropRMA = rma;
    }

    /**
     * Show RMA jade panel
     *
     * @return boolean value from filePropRMA
     */
    public boolean showRMA() {
        return this.filePropRMA;
    }

    /**
     * Set Value of max threads by container
     *
     * @param maxThreads
     */
    public void setmaxThreadsByContainer(Integer maxThreads) {
        this.filePropmaxThreadsByContainer = maxThreads;

        /**
         * Return value of max threads by container
         */
    }

    public Integer getmaxThreadsByContainer() {
        return this.filePropmaxThreadsByContainer;

    }

    /**
     * Here the URLS or IP of the server.master can be set
     *
     * @param serverMasterURL the filePropMasterURL to set
     * @see FileProperties
     */
    public void setServerMasterURL(String serverMasterURL) {
        this.filePropServerMasterURL = serverMasterURL;
    }

    /**
     * Here the URL or IP of the server.master can be get
     *
     * @return the filePropMasterURL
     * @see FileProperties
     */
    public String getServerMasterURL() {
        return this.filePropServerMasterURL;
    }

    /**
     * Here the port for the server.master can be set
     *
     * @param serverMasterPort the filePropMasterPort to set
     * @see FileProperties
     */
    public void setServerMasterPort(Integer serverMasterPort) {
        this.filePropServerMasterPort = serverMasterPort;
    }

    /**
     * This method returns the port on which s�the server.master can be reached
     *
     * @return The port of the server.master
     * @see FileProperties
     */
    public Integer getServerMasterPort() {
        return this.filePropServerMasterPort;
    }

    /**
     * @param dispatcher
     */
    public void setDispatcher(String dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * @return dispatcher
     */
    public String getDispatcher() {
        return this.dispatcher;
    }

    /**
     * This method can be used in order to set the MTP port of the server.master
     *
     * @param serverMasterPort4MTP the filePropMasterPort to set
     * @see FileProperties
     */
    public void setServerMasterPort4MTP(Integer serverMasterPort4MTP) {
        this.filePropServerMasterPort4MTP = serverMasterPort4MTP;
    }

    /**
     * Returns the MTP port of the server.master
     *
     * @return the filePropMasterPort
     * @see FileProperties
     */
    public Integer getServerMasterPort4MTP() {
        return this.filePropServerMasterPort4MTP;
    }

    /**
     * Database property for the server.nmaster
     *
     * @param newDBHost the filePropServerMasterDBHost to set
     * @see FileProperties
     */
    public void setServerMasterDBHost(String newDBHost) {
        this.filePropServerMasterDBHost = newDBHost;
    }

    /**
     * Path of ehcach.xml
     *
     * @param ehcachfile
     */
    public void setEhcachFile(String ehcachfile) {
        this.filePropEhcacheFile = ehcachfile;
    }

    /**
     * Return Path of ehcach.xml
     *
     * @return filePropEhcacheFile
     */
    public String getEhcachFile() {
        return filePropEhcacheFile;
    }

    /**
     * Database property for the server.nmaster
     *
     * @return the filePropServerMasterDBHost
     * @see FileProperties
     */
    public String getServerMasterDBHost() {
        return filePropServerMasterDBHost;
    }

    /**
     * Database property for the server.nmaster
     *
     * @param newDBName the filePropServerMasterDBName to set
     * @see FileProperties
     */
    public void setServerMasterDBName(String newDBName) {
        this.filePropServerMasterDBName = newDBName;
    }

    /**
     * Database property for the server.nmaster
     *
     * @return the filePropServerMasterDBName
     * @see FileProperties
     */
    public String getServerMasterDBName() {
        return filePropServerMasterDBName;
    }

    /**
     * Database property for the server.nmaster
     *
     * @param newDBUser the filePropServerMasterDBUser to set
     * @see FileProperties
     */
    public void setServerMasterDBUser(String newDBUser) {
        this.filePropServerMasterDBUser = newDBUser;
    }

    /**
     * Database property for the server.nmaster
     *
     * @return the filePropServerMasterDBUser
     * @see FileProperties
     */
    public String getServerMasterDBUser() {
        return filePropServerMasterDBUser;
    }

    /**
     * Database property for the server.nmaster
     *
     * @param newDBPswd the filePropServerMasterDBPswd to set
     * @see FileProperties
     */
    public void setServerMasterDBPswd(String newDBPswd) {
        this.filePropServerMasterDBPswd = newDBPswd;
    }

    /**
     * Database property for the server.nmaster
     *
     * @return the filePropServerMasterDBPswd
     * @see FileProperties
     */
    public String getServerMasterDBPswd() {
        return filePropServerMasterDBPswd;
    }

    // ---- Methods for the reminder of the last selected folder ----
    /**
     * This method can be used in order to remind the last folder in which a
     * file was selected (e. g. while using a JFileChooser)
     *
     * @param lastSelectedFolder the lastSelectedFolder to set
     */
    public void setLastSelectedFolder(File lastSelectedFolder) {
        this.lastSelectedFolder = lastSelectedFolder;
    }

    /**
     * Returns the reminder value of the last selected folder as File object
     *
     * @return the lastSelectedFolder
     */
    public File getLastSelectedFolder() {
        if (lastSelectedFolder == null) {
            lastSelectedFolder = new File(this.PathBaseDir());
        }
        return lastSelectedFolder;
    }

    /**
     * Returns the reminder value of the last selected folder as String
     *
     * @return the lastSelectedFolder as String
     */
    public String getLastSelectedFolderAsString() {
        String returnFolder = this.getLastSelectedFolder().getAbsolutePath();
        if (!returnFolder.endsWith(File.separator)) {
            returnFolder += File.separator;
        }
        return returnFolder;
    }

}
