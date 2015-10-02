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
package org.gaia.jade.server;

import jade.core.Profile;
import jade.wrapper.AgentContainer;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.core.benchmark.BenchmarkMeasurement;
import org.gaia.core.database.DBConnection;
import org.gaia.core.network.JadeUrlChecker;
import org.gaia.core.project.PlatformJadeConfig;
import org.gaia.domain.utils.GlobalInfo;
import org.gaia.domain.utils.StringUtils;
import org.gaia.simulationService.load.LoadMeasureThread;

public class Application {

    private static final Logger logger = Logger.getLogger(Application.class);

    /**
     * True, if a remote container has to be started (see start arguments)
     */
    private static boolean justStartJade = false;
    /**
     * Indicates if the benchmark is currently running
     */
    private static boolean benchmarkRunning = false;
    /**
    /**
     * This ClassDetector is used in order to search for agent classe's,
     * ontology's and BaseService'. If a project was newly opened, the search
     * process will restart in order to determine the integrated classes of the
     * project.
     */
//	private static ClassSearcher classSearcher = null;
    /**
     * The instance of this singleton class
     */
    private static Application thisApp = new Application();
    /**
     * With this attribute/class the agent platform (JADE) will be controlled.
     */
    private static Platform jadePlatform = null;
    /**
     * The ODBC connection to the database
     */
    private static DBConnection dbConnection = null;

    /**
     * Singleton-Constructor: Instantiates a new application.
     */
    private Application() {
    }

    /**
     * Will return the instance of this singleton class
     *
     * @return The instance of this class
     */
    public static Application getInstance() {
        return Application.thisApp;
    }


    /**
     * Returns the application-wide information system.
     *
     * @return the global info
     */
    public static GlobalInfo getGlobalInfo() {
        return GlobalInfo.getInstance();
    }

    /**
     * Gets the execution mode from configuration.
     *
     * @return the execution mode from configuration
     */
    public static GlobalInfo.ExecutionMode getExecutionMode() {

        GlobalInfo.ExecutionMode execMode;
        if (!GlobalInfo.isRunningAsServer()) {
            // ----------------------------------
            // --- Running as Application -------
            // ----------------------------------
            execMode = GlobalInfo.ExecutionMode.APPLICATION;
        } else {
            // ----------------------------------
            // --- Running as Server-Tool -------
            // ----------------------------------
            execMode = GlobalInfo.ExecutionMode.SERVER;

            // --- Does JADE run? ---------------
            Platform platform = Application.getJadePlatform();
            AgentContainer mainContainer = platform.jadeGetMainContainer();
            if (mainContainer != null) {
                // --------------------------------------------------
                // --- JADE is running ------------------------------
                // --------------------------------------------------
                JadeUrlChecker urlConfigured = new JadeUrlChecker(GlobalInfo.getInstance().getServerMasterURL());
                urlConfigured.setPort(GlobalInfo.getInstance().getServerMasterPort());
                urlConfigured.setPort4MTP(GlobalInfo.getInstance().getServerMasterPort4MTP());

                JadeUrlChecker urlCurrent = new JadeUrlChecker(mainContainer.getPlatformName());
                if (urlCurrent.getHostIP().equalsIgnoreCase(urlConfigured.getHostIP()) && urlCurrent.getPort().equals(urlConfigured.getPort())) {
                    // --- Running as Server [Master] ---------------
                    execMode = GlobalInfo.ExecutionMode.SERVER_MASTER;
                } else {
                    // --- Running as Server [Slave] ----------------
                    execMode = GlobalInfo.ExecutionMode.SERVER_SLAVE;
                }
                // --------------------------------------------------
            }

        }
        return execMode;
    }

    /**
     * This Method returns the default JADE container-profile of the
     * AgentGUI-Application
     *
     * @return The local JADE default profile
     * @see Profile
     */
    public static Profile getJadeDefaultProfile() {
        PlatformJadeConfig jadeConfig = getJadeDefaultPlatformConfig();
        return jadeConfig.getNewInstanceOfProfilImpl();
    }

    /**
     * This method return the default platform configuration for JADE
     *
     * @return instance of class 'PlatformJadeConfig', which holds the
     * configuration of JADE
     * @see PlatformJadeConfig
     */
    public static PlatformJadeConfig getJadeDefaultPlatformConfig() {

        // --- Here the default-values can be configured ------------
        PlatformJadeConfig jadeConfig = new PlatformJadeConfig();
        jadeConfig.setLocalPort(GlobalInfo.getInstance().getJadeLocalPort());

        jadeConfig.addService(PlatformJadeConfig.SERVICE_LoadService);
        jadeConfig.addService(PlatformJadeConfig.SERVICE_SimulationService);
        jadeConfig.addService(PlatformJadeConfig.SERVICE_NotificationService);

        if (!GlobalInfo.isRunningAsServer()) {
            // --- Running as application ---------------------------
            jadeConfig.addService(PlatformJadeConfig.SERVICE_AgentMobilityService);
        }
        return jadeConfig;
    }

    /**
     * Gets the jade platform.
     *
     * @return the jade platform
     */
    public static Platform getJadePlatform() {
        if (Application.jadePlatform == null) {
            Application.jadePlatform = new Platform();
        }
        return jadePlatform;
    }

    /**
     * Returns the database connection.
     *
     * @param renewDatabaseConnection the renew database connection
     * @return the database connection
     */
    public static DBConnection getDatabaseConnection(boolean renewDatabaseConnection) {
        if (renewDatabaseConnection) {
            Application.dbConnection = new DBConnection();
            return Application.dbConnection;
        }
        return Application.getDatabaseConnection();
    }

    /**
     * Returns the database connection for the Application.
     *
     * @return the database connection
     */
    public static DBConnection getDatabaseConnection() {
        if (Application.dbConnection == null) {
            Application.dbConnection = new DBConnection();
        }
        return Application.dbConnection;
    }

    /**
     * Main method for the start of the application running as end user
     * application or server-tool
     *
     * @param args The arguments which can be configured in the command line.
     * -help will print all possible command line arguments
     */
    public static void agentMain(String[] args) {

        // --- Read the start arguments and react on it?! -----------
        String[] remainingArgs = proceedStartArguments(args);

        if (!Application.justStartJade) {
            getGlobalInfo();
            startGaiaAgentPlatform();

        } else {
            // --- Just start JADE ----------------------------------
            getGlobalInfo();
            logger.info("Just starting JADE now ...");
            jade.Boot.main(remainingArgs);
        }

    }

    /**
     * This method will proceed the start-arguments of the application and
     * returns these arguments, which are not understood.
     *
     * @param args the start arguments
     * @return the remaining arguments
     */
    private static String[] proceedStartArguments(String[] args) {

        Vector<String> remainingArgsVector = new Vector<>();

        int i = 0;
        while (i < args.length) {
            // --- Pack the single argument to the remainingArgsVector --------
            remainingArgsVector.addElement(args[i]);
            if (args[i].startsWith("-")) {

                if (args[i].equalsIgnoreCase("-jade")) {
                    // --- JADE has to be started as remote container ---------
                    remainingArgsVector.removeElement(args[i]);
                    justStartJade = true;

                } else if (args[i].equalsIgnoreCase("-container")
                        || args[i].equalsIgnoreCase("-gui")) {
                } else if (args[i].equalsIgnoreCase("-dispatcher")) {
                    GlobalInfo.getInstance().setDispatcher("dispatcher");
                } else {
                    if ((args.length - 1) > i) {
                        i++;
                        remainingArgsVector.addElement(args[i]);
                    }

                }
            } else {
                logger.error("Argument" + StringUtils.SPACE + (i + 1) + " '" + args[i] + "': unspecified start argument");
            }
            i++;
        }
        // --- Rebuild the Array of the start arguments -------------------
        String[] remainingArgs = new String[remainingArgsVector.size()];
        remainingArgsVector.toArray(remainingArgs);
        return remainingArgs;
    }

    /**
     * This method can be invoked in order to start a project. It was
     * implemented to open a project given by the start arguments after the
     * needed instantiations are done.
     */
    private static void proceedStartArgumentOpenProject() {

        if (!GlobalInfo.isRunningAsServer()) {
            // --- wait for the end of the benchmark ------
            while (benchmarkRunning) {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    logger.error(ex);
                }
            }
        }
    }

    /**
     * This methods starts gaia in application or server mode depending on the
     * configuration in 'properties/gaia.ini'
     */
    public static void startGaiaAgentPlatform() {
        // --- Start the Application as defined by 'isServer' -------
        getJadePlatform();
        if (GlobalInfo.isRunningAsServer()) {
            logger.debug("Server starting  ...");
            doBenchmark(false);
            startServer();

        } else {
            logger.debug("Program starting  ...");
            startApplication();
            doBenchmark(false);
            proceedStartArgumentOpenProject();

        }
    }

    /**
     * Opens the agentMain application window (JFrame)
     */
    public static void startApplication() {
        Platform platform = Application.getJadePlatform();
        platform.jadeStart(true);
    }

    /**
     * Starts the agentMain procedure for the Server-Version of gaia
     */
    public static void startServer() {

        // --- Wait until the benchmark result is available -----
        while (LoadMeasureThread.getCompositeBenchmarkValue() == 0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }
        getJadePlatform().jadeStart();

    }

    /**
     * Executes the Benchmark-Test of SciMark 2.0 to determine the ability of
     * this system to deal with number crunching.<br> The result will be
     * available in Mflops (Millions of floating point operations per second)
     */
    public static void doBenchmark(boolean forceBenchmark) {
        if (!Application.isBenchmarkRunning()) {
            benchmarkRunning = true;
            new BenchmarkMeasurement(forceBenchmark).start();
        }
    }

    /**
     * Sets that the benchmark is running or not.
     *
     * @param running the new benchmark is running
     */
    public static void setBenchmarkRunning(boolean running) {
        benchmarkRunning = running;
    }

    /**
     * Checks if is benchmark is running.
     *
     * @return true, if is benchmark is running
     */
    public static boolean isBenchmarkRunning() {
        return benchmarkRunning;
    }

    /**
     * This method can be used in order to change the application title during
     * runtime
     *
     * @param newApplicationTitle sets a new application title/name
     */
    public static void setApplicationTitle(String newApplicationTitle) {
        getGlobalInfo().setApplicationTitle(newApplicationTitle);
    }

    /**
     * This method return the current title of the application
     *
     * @return the Application title/name
     */
    public static String getApplicationTitle() {
        return getGlobalInfo().getApplicationTitle();
    }
}
