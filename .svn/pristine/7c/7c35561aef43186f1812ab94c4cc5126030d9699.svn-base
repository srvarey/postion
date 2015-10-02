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
package org.gaia.simulationService.agents;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.Agent;
import jade.core.Location;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.core.behaviours.TickerBehaviour;
import jade.core.event.NotificationService;
import jade.core.mobility.AgentMobilityService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPANames;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Vector;
import org.apache.log4j.Logger;
import org.gaia.core.database.DBConnection;
import org.gaia.core.project.DistributionSetup;
import org.gaia.core.sim.setup.SimulationSetup;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.AgentCreation;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.jade.ontology.PricerAgentCreation;
import org.gaia.jade.server.Application;
import org.gaia.jade.server.GaiaAgentCreator;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.LoadServiceHelper;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.balancing.ContainerContent;
import org.gaia.simulationService.balancing.DynamicLoadBalancing;
import org.gaia.simulationService.balancing.DynamicLoadBalancingBase;
import org.gaia.simulationService.load.LoadAgentMap;
import org.gaia.simulationService.load.LoadInformation.NodeDescription;
import org.gaia.simulationService.load.LoadMeasureThread;
import org.gaia.simulationService.load.LoadMerger;
import org.gaia.simulationService.load.LoadThresholdLevels;
import org.gaia.simulationService.ontology.DistributionOntology;
import org.gaia.simulationService.ontology.OSInfo;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.PlatformPerformance;

/**
 * This class represents the agent, which monitors the load information of all
 * involved JVM's, container and agents of the platform.<br>
 *
 */
public class LoadMeasureAgent extends Agent implements GaiaVocabulary {

    private static final Logger logger = Logger.getLogger(LoadMeasureAgent.class);
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 3035508112883482740L;
    /**
     * The current SimulationSetup.
     */
    protected SimulationSetup currSimSetup = null;
    /**
     * The current DistributionSetup.
     */
    protected DistributionSetup currDisSetup = null;
    /**
     * The monitor behaviour, which is a TickerBehaviour.
     */
    public MonitorBehaviour monitorBehaviour = null;
    private long monitorBehaviourTickingPeriod = 0;
    // --- The balancing algorithm of this agent --------------------
    private DynamicLoadBalancing loadBalancing = null;
    /**
     * Indicator if a activate DynamicLoadBalancing is still active.
     */
    public boolean loadBalancingIsStillActivated = false;
    // --- Over all and current threshold level ---------------------
    /**
     * The indicator if thresholds exceeded over all.
     */
    public Integer loadThresholdExceededOverAll = 0;
    /**
     * The currently configured threshold levels.
     */
    public LoadThresholdLevels loadThresholdLevels = null;
    /**
     * The average cycle time of a simulation.
     */
    public double loadCycleTime = 0;
    /**
     * The used (dead or alive) nodes of the system, ordered ascending.
     */
    public Vector<String> loadContainer2Display = null;
    /**
     * The current LoadAgentMap.
     */
    public LoadAgentMap loadContainerAgentMap = null;
    /**
     * The PlatformLoad in the different container.
     */
    public HashMap<String, PlatformLoad> loadContainer = null;
    /**
     * The Location information in the different container.
     */
    public HashMap<String, Location> loadContainerLocations = null;
    /**
     * The benchmark value /results in the different container.
     */
    public HashMap<String, Float> loadContainerBenchmarkResults = new HashMap<>();
    /**
     * The currently running JVM's for this platform.
     */
    public HashMap<String, LoadMerger> loadJVM4Balancing = new HashMap<>();
    /**
     * The currently running physical machines for this platform.
     */
    public HashMap<String, LoadMerger> loadMachines4Balancing = new HashMap<>();
    // --------------------------------------------------------------
    // --------------------------------------------------------------
    // ---- Variables for storing the measurements in a file --------
    private boolean monitorSaveLoad = false;
    private BufferedWriter monitorDatasetWriter = null;
    // --- Some System-String ---------------------------------------
    private final String monitorDatasetDelimiter = ";";
    private final String monitorDatasetLineSeperator = System.getProperty("line.separator");
    // --- Files which will be created for storing monitoring data --
    private final String monitorFileMeasurementTmp = "LoadMeasurement.tmp";
    private final String monitorFileMeasurement = "LoadMeasurement.csv";
    private final String monitorFileMachines = "LoadMachines.txt";
    private HashMap<String, String> monitorDatasetParts = new HashMap<>();
    private HashMap<String, String> monitorDatasetPartsHeader = new HashMap<>();
    private HashMap<String, String> monitorDatasetPartsDescription = new HashMap<>();
    // --------------------------------------------------------------
    public HashMap<String, Integer> threadNos = new HashMap();
    public boolean localMode = false;
    private DBConnection dbConn = Application.getDatabaseConnection();

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        this.getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL0);
        this.getContentManager().registerOntology(JADEManagementOntology.getInstance());
        this.getContentManager().registerOntology(DistributionOntology.getInstance());

        loadBalancing = new DynamicLoadBalancing(this);

        loadBalancing.setCurrDisSetup(currDisSetup);
        // --- get the instance of the ThreadedBehaviour --------------
        ThreadedBehaviourFactory loadBalancingThread = new ThreadedBehaviourFactory();
        // --- execute the dynamic load balancing ---------------------
        this.addBehaviour(loadBalancingThread.wrap(loadBalancing));
        monitorBehaviourTickingPeriod = 20 * 1000;
        monitorBehaviour = new MonitorBehaviour(this, monitorBehaviourTickingPeriod);
        this.addBehaviour(monitorBehaviour);
        this.addBehaviour(new AgentCreationMessageBehaviour(this));
    }

    /* (non-Javadoc)
     * @see jade.core.Agent#takeDown()
     */
    @Override
    protected void takeDown() {
        super.takeDown();
        if (monitorDatasetWriter != null) {
            this.closeMonitorFile();
        }
    }

    /**
     * Sets the instance of the dynamic load balancing algorithm to use.
     *
     * @param loadBalancing the new load balancing
     */
    public void setLoadBalancing(DynamicLoadBalancing loadBalancing) {
        this.loadBalancing = loadBalancing;
    }

    /**
     * Gets the currently use dynamic load balancing algorithm.
     *
     * @return the loadBalancing
     */
    public DynamicLoadBalancingBase getLoadBalancing() {
        return loadBalancing;
    }

    /**
     * This TickerBehaviour measures, displays (if wanted) and stores the
     * measured load values.
     *
     */
    public class MonitorBehaviour extends TickerBehaviour {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = -5802791218164507242L;

        /**
         * Instantiates a new monitor behaviour.
         *
         * @param agent the agent
         * @param tickerPeriod the period in which the measurements are done
         */
        public MonitorBehaviour(Agent agent, long tickerPeriod) {
            super(agent, tickerPeriod);
        }

        /* (non-Javadoc)
         * @see jade.core.behaviours.TickerBehaviour#onTick()
         */
        @Override
        protected void onTick() {


            try {
                LoadServiceHelper loadHelper = (LoadServiceHelper) getHelper(LoadService.NAME);

                // --- Get the PlatformLoad and the Agents at their locations -----------
                loadCycleTime = loadHelper.getAvgCycleTime();
                loadContainer = loadHelper.getContainerLoads();
                loadContainerAgentMap = loadHelper.getAgentMap();
                loadContainerLocations = loadHelper.getContainerLocations();
                loadThresholdLevels = LoadMeasureThread.getThresholdLevels();

                // Initialise variables JVM-balancing -----------------------------------
                loadThresholdExceededOverAll = 0;
                loadMachines4Balancing = new HashMap<>();
                loadJVM4Balancing = new HashMap<>();

                // --- Walk through the list of all containers --------------------------
                loadContainer2Display = new Vector<>(loadHelper.getContainerQueue());
                Iterator<String> it = loadContainer2Display.iterator();
                while (it.hasNext()) {

                    String containerName = it.next();
                    // --- Get the benchmark-result for this node/container -------------
                    NodeDescription containerDesc = loadHelper.getContainerDescription(containerName);
                    Float benchmarkValue = containerDesc.getBenchmarkValue().getBenchmarkValue();
                    String jvmPID = containerDesc.getJvmPID();
                    String machineURL = containerDesc.getPlAddress().getUrl();
                    // --- Get all needed load informations -----------------------------
                    PlatformLoad containerLoad = loadContainer.get(containerName);
                    Integer containerNoAgents = loadContainerAgentMap.noAgentsAtContainer.get(containerName);
                    loadContainerBenchmarkResults.put(containerName, benchmarkValue);
                    // ------------------------------------------------------------------
                    // --- Store informations also by the JVM (merge) -------------------
                    // ------------------------------------------------------------------
                    if (containerLoad != null && jvmPID != null) {
                        // --- Observe the over all Threshold -----------------
                        loadThresholdExceededOverAll += ((Math.abs(containerLoad.getLoadExceeded())));
                        // --- Merge the load per physical machine  -----------
                        LoadMerger loadMachine = loadMachines4Balancing.get(machineURL);
                        if (loadMachine == null) {
                            loadMachine = new LoadMerger(machineURL);
                        }
                        loadMachine.merge(containerName, jvmPID, benchmarkValue, containerLoad, containerNoAgents);
                        loadMachines4Balancing.put(machineURL, loadMachine);

                        // --- Merge the load per JVM -------------------------
                        LoadMerger loadJvmMachine = loadJVM4Balancing.get(jvmPID);
                        if (loadJvmMachine == null) {
                            loadJvmMachine = new LoadMerger(jvmPID);
                        }
                        loadJvmMachine.merge(containerName, jvmPID, benchmarkValue, containerLoad, containerNoAgents);
                        loadJVM4Balancing.put(jvmPID, loadJvmMachine);

                        if (containerNoAgents == null) {
                            containerNoAgents = new Integer("0");
                        }
                        threadNos.put(containerName, containerNoAgents);
                    }
                    if (monitorSaveLoad == true) {
                        buildDatasetPart(containerName, containerDesc, benchmarkValue, containerLoad, containerNoAgents);
                    }

                }

            } catch (ServiceException e) {
                logger.error(e);
            }
            // --------------------------------------------------------------------------
            // --- Now, activate the load balancing algorithm in a dedicated thread -----
            // --------------------------------------------------------------------------
            this.doCheckDynamicLoadBalancing();
            // --------------------------------------------------------------------------
        }

        /**
         * This method check if the dynamic load balancing is activate in the
         * currently executed project and which class should be used for it.
         */
        private void doCheckDynamicLoadBalancing() {

            // --- If the dynamic load balancing is still running/executed  	 ---
            // --- from the last measure tick, exit here to prevent side effects ---
            if (loadBalancingIsStillActivated == true) {
//                return;
            }
            loadBalancingIsStillActivated = true;
            // --- If the dynamic load balancing is activated: ----------------
            if (currDisSetup != null && currDisSetup.isDoDynamicLoadBalancing() == true) {

                LoadMeasureAgent thisLoadAgent = (LoadMeasureAgent) myAgent;
                try {
                    @SuppressWarnings("unchecked")
                    Class<? extends DynamicLoadBalancing> dynLoBaClass = (Class<? extends DynamicLoadBalancing>) Class.forName(currDisSetup.getDynamicLoadBalancingClass());
                    loadBalancing = dynLoBaClass.getDeclaredConstructor(new Class[]{thisLoadAgent.getClass()}).newInstance(new Object[]{thisLoadAgent});

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException e) {
                    logger.error(e);
                }
                // --- If loading of the class was not successful ----
                // --- start the default class for balancing	  ----
                if (loadBalancing == null) {
                    loadBalancing = new DynamicLoadBalancing(thisLoadAgent);
                }

                loadBalancing.setCurrDisSetup(currDisSetup);
                // --- get the instance of the ThreadedBehaviour --------------
                ThreadedBehaviourFactory loadBalancingThread = new ThreadedBehaviourFactory();
                // --- execute the dynamic load balancing ---------------------
                myAgent.addBehaviour(loadBalancingThread.wrap(loadBalancing));
                int state = myAgent.getState();
                while (state != 2) {//LoadMeasureThread.getCompositeBenchmarkValue() == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error(e);
                    }
                    state = myAgent.getState();
                }
                loadBalancingIsStillActivated = false;
            }

        }// --- end dynamic LoadBalancing
    } // --- End of MonitorBehaviour (class) ----

    private class AgentCreationMessageBehaviour extends CyclicBehaviour {

        public AgentContainer container = Application.getJadePlatform().jadeGetMainContainer();
        private DynamicLoadBalancing load = null;
        private final Ontology ontology = DistributionOntology.getInstance();
        private final Codec codec = new SLCodec();
        String targetContainer = "";

        public AgentCreationMessageBehaviour(LoadMeasureAgent agent) {
            super();
            load = new DynamicLoadBalancing(agent);
            load.setCurrDisSetup(currDisSetup);
            agent.addBehaviour(load);
            getContentManager().registerLanguage(codec);
            getContentManager().registerOntology(ontology);
        }

        @Override
        public synchronized void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                try {
                    if (msg.getPerformative() == ACLMessage.FAILURE) {
                        logger.error("Error " + msg.getContent());
                    } else {
//                        Action act = (Action) getContentManager().extractContent(msg);
//                        if (act != null) {
//                            Concept agentAction = act.getAction();
                        Serializable agentAction = msg.getContentObject();
                        if (agentAction instanceof AgentCreation || agentAction instanceof PricerAgentCreation) {

                            int minThreadNo;
                            localMode = load.localMode;
                            load.setMeasurements();
                            load.refreshCountingsAndLists();
                            ArrayList<ContainerContent> containers = load.calcContainers();

                            minThreadNo = load.maxThreadsByContainer * 10;
                            int maxThreadNo = 0;
                            String selectedContainer = StringUtils.EMPTY_STRING;

                            // look for the container that has the lesser number of threads
                            for (Entry<String, Integer> entry : threadNos.entrySet()) {
                                if (entry.getKey().toString().contains("remote")) {
                                    int n = entry.getValue();
                                    if (n < minThreadNo) {
                                        minThreadNo = n;
                                        selectedContainer = entry.getKey().toString();
                                    }
                                    if (maxThreadNo < n) {
                                        maxThreadNo = n;
                                    }
                                }
                            }
                            targetContainer = selectedContainer;

                            // if no container found, launch one
                            if (targetContainer != null && targetContainer.isEmpty() && !localMode) {
                                targetContainer = load.startRemoteContainer();
                                logger.info("Starting container " + targetContainer);
                                load.updateLoadInfo4JVMandMachine(targetContainer);
                            }
                            // if the number of agents if higher than max in all containers, launch a new one
                            if (minThreadNo > load.maxThreadsByContainer && minThreadNo != load.maxThreadsByContainer * 10 && !localMode) {
                                targetContainer = load.startRemoteContainer();
                                load.updateLoadInfo4JVMandMachine(targetContainer);
                            }

                            String targetAgent;
                            if (targetContainer != null && !targetContainer.isEmpty()) {
                                // increment the no of agents launched on the container
                                if (threadNos.get(targetContainer) == null) {
                                    threadNos.put(targetContainer, 1);
                                }
//                                if (threadNos.get(targetContainer).intValue()==load.maxThreadsByContainer){
//                                     Integer containerNoAgents = loadContainerAgentMap.noAgentsAtContainer.get(targetContainer);
//                                     threadNos.put(targetContainer,containerNoAgents);
//                                } else {
                                    threadNos.put(targetContainer, new Integer(threadNos.get(targetContainer).intValue() + 1));
//                                }
                                String sIndex = targetContainer.substring(6);
                                Integer id = Integer.parseInt(sIndex);
                                targetAgent = "agentCreator" + id;
                            } else {
                                // at this moment if no container found, we swing to the local mode
                                logger.error("ERROR : Unable to find remote server");
                                boolean found = false;
                                targetContainer = "remote0";
                                targetAgent = "agentCreator0";
                                for (ContainerContent _container : containers) {
                                    if (_container.getContainer().equalsIgnoreCase(targetContainer)) {
                                        found = true;
                                    }
                                }
                                // if local container is not found start it
                                if (!found) {
                                    createLocalContainer(targetContainer, targetAgent);
                                    localMode = true;
                                }
                            }
                            // wait for agent creator is up
                            DFAgentDescription[] result = GaiaAgentCreator.getService(targetAgent, myAgent);
                            while (result.length == 0) {
                                result = GaiaAgentCreator.getService(targetAgent, myAgent);
                                wait(10);
                            }

                            ACLMessage forward = new ACLMessage(ACLMessage.REQUEST);
                            forward.setSender(msg.getSender());
                            forward.addReceiver(new AID(targetAgent, AID.ISLOCALNAME));
                            forward.setContentObject(agentAction);
                            forward.setConversationId(AGENT_CREATION_REQUEST);
                            send(forward);
                            AgentCreation crea = (AgentCreation) agentAction;
                            logger.debug("Sending to " + targetAgent + " request for " + crea.getAgentClassName() + StringUtils.SPACE + crea.getAgentName());
                        }
                    }
                } catch (Exception e) {
                    logger.error(e);
                }
            } else {
                block();
            }
        }
    }

    public AgentContainer createLocalContainer(String targetContainer, String targetAgent) {
        AgentContainer container = null;
        try {
            jade.core.Runtime jadeRuntime = jade.core.Runtime.instance();
            Profile p = new ProfileImpl();
            p.setParameter(Profile.CONTAINER_NAME, targetContainer);
            StringBuilder services = new StringBuilder(NotificationService.class.getName());
            services.append(";");
            services.append(AgentMobilityService.class.getName());
            services.append(";");
            services.append(SimulationService.class.getName());
            p.setParameter(Profile.SERVICES, services.toString());
            container = jadeRuntime.createAgentContainer(p);
            GaiaAgentCreator gac = new GaiaAgentCreator();
            AgentController controller = container.acceptNewAgent(targetAgent, gac);
            controller.start();
            threadNos.put(targetContainer, new Integer("0"));
        } catch (StaleProxyException | NumberFormatException e) {
            logger.error("FATAL ERROR : NO SERVER REACHABLE", e);
        }
        return container;
    }

    protected Long getSlaveNumber() {
        Long no = Long.MIN_VALUE;
        try {
            String sqlStmt = "select count(*) FROM platforms WHERE  contact_agent like 'server.slave%'";
            ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);
            while (res.next()) {
                no = res.getLong(1);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return no;
    }

    /**
     * Checks if the current LoadAgent has to the save the load information in a
     * file.
     *
     * @return the monitorSaveLoad
     */
    public boolean isMonitorSaveLoad() {
        return monitorSaveLoad;
    }

    /**
     * Sets the current LoadAgent to save or not save the load information in a
     * file.
     *
     * @param monitorSaveLoad true, if the information should be saved
     */
    public void setMonitorSaveLoad(boolean monitorSaveLoad) {
        this.monitorSaveLoad = monitorSaveLoad;

        // --- Create DatasetWriter -----------------------
        if (this.monitorSaveLoad == true) {
            monitorDatasetWriter = this.createMonitorFile(this.monitorFileMeasurementTmp);
        }
        // --- Close the current DatasetWriter ------------
        if (this.monitorSaveLoad == false && this.monitorDatasetWriter != null) {
            this.closeMonitorFile();
        }
    }

    /**
     * This method builds one part for the load dataset where one part
     * corresponds to one container.
     *
     * @param containerName the container name
     * @param nodeDescription the NodeDescription
     * @param benchmarkValue the benchmark value
     * @param platformLoad the PlatformLoad
     * @param numberOfAgents the number of agents
     */
    private void buildDatasetPart(String containerName, NodeDescription nodeDescription, float benchmarkValue, PlatformLoad platformLoad, Integer numberOfAgents) {

        String dataSet;
        if (platformLoad == null) {

            dataSet = getDatasetPartEmpty();
        } else {

            StringBuilder sb = new StringBuilder();
            // --- CPU-Load -----------------------------------
            sb.append(platformLoad.getLoadCPU()).append(monitorDatasetDelimiter);
            // --- Memory-Load of the machine -----------------
            sb.append(platformLoad.getLoadMemorySystem()).append(monitorDatasetDelimiter);
            // --- Java Heap-Load -----------------------------
            sb.append(platformLoad.getLoadMemoryJVM()).append(monitorDatasetDelimiter);
            // --- Number of Threads --------------------------
            sb.append(platformLoad.getLoadNoThreads()).append(monitorDatasetDelimiter);
            // --- Number of Agents ---------------------------
            sb.append(numberOfAgents).append(monitorDatasetDelimiter);
            dataSet = sb.toString();
        }
        monitorDatasetParts.put(containerName, dataSet);

        // --- Build the Header of the dataset part ------
        if (monitorDatasetPartsHeader.get(containerName) == null) {

            StringBuilder sbHeader = new StringBuilder();
            // --- CPU-Load -----------------------------------
            sbHeader.append(containerName).append(": % CPU-load").append(monitorDatasetDelimiter);
            // --- Memory-Load of the machine -----------------
            sbHeader.append(containerName).append(": % Memory-load machine").append(monitorDatasetDelimiter);
            // --- Java Heap-Load -----------------------------
            sbHeader.append(containerName).append(": % Memory-load JVM").append(monitorDatasetDelimiter);
            // --- Number of Threads --------------------------
            sbHeader.append(containerName).append(": No. Threads").append(monitorDatasetDelimiter);
            // --- Number of Agents ---------------------------
            sbHeader.append(containerName).append(": No. Agents").append(monitorDatasetDelimiter);
            monitorDatasetPartsHeader.put(containerName, sbHeader.toString());
        }

        // --- Build the part for the description of a machine ---
        if (monitorDatasetPartsDescription.get(containerName) == null) {

            String newLine = monitorDatasetLineSeperator;

            OSInfo os = nodeDescription.getOsInfo();
            String opSys = os.getOs_name() + StringUtils.SPACE + os.getOs_version();

            PlatformPerformance pP = nodeDescription.getPlPerformace();
            String perform = pP.getCpu_vendor() + ": " + pP.getCpu_model();
            perform = perform.replaceAll("  ", StringUtils.SPACE);
            perform += newLine + pP.getCpu_numberOf() + " x " + pP.getCpu_speedMhz() + "MHz [" + pP.getMemory_totalMB() + " MB RAM]";

            String bench = benchmarkValue + " Mflops";
            String description = "=>" + containerName + newLine + opSys + newLine + perform + newLine + bench + newLine;

            monitorDatasetPartsDescription.put(containerName, description);
        }
    }

    /**
     * This method builds one EMPTY part for the load dataset, where one part
     * correspond to one container.
     *
     * @return an empty String for a dataset
     */
    private String getDatasetPartEmpty() {

        StringBuilder sb = new StringBuilder();
        // --- CPU-Load -----------------------------------
        sb.append(monitorDatasetDelimiter);
        // --- Memory-Load of the machine -----------------
        sb.append(monitorDatasetDelimiter);
        // --- Java Heap-Load -----------------------------
        sb.append(monitorDatasetDelimiter);
        // --- Number of Threads --------------------------
        sb.append(monitorDatasetDelimiter);
        // --- Number of Agents ---------------------------
        sb.append(monitorDatasetDelimiter);
        return sb.toString();
    }

    /**
     * This Method returns the header line for the main monitoring file.
     *
     * @return the header line as a String
     */
    private String getHeaderLine() {

        StringBuilder sb = new StringBuilder();

        // --- Add Date / Millisecond - Header ------------
        sb.append("Time").append(monitorDatasetDelimiter);
        sb.append("Millis").append(monitorDatasetDelimiter);

        // --- Build complete dataset ---------------------
        Iterator<String> it = loadContainer2Display.iterator();
        while (it.hasNext()) {
            String containerName = it.next();
            sb.append(monitorDatasetPartsHeader.get(containerName));
        }
        return sb.toString();
    }

    /**
     * This method creates a BufferedWriter for the measurements.
     *
     * @param fileName the file name
     * @return the buffered writer
     */
    private BufferedWriter createMonitorFile(String fileName) {

        File monitorFile = new File(fileName);
        FileWriter fw;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(monitorFile);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            logger.error(e);
        }
        return bw;
    }

    /**
     * This method closes the BufferedWriter for the Measurements.
     */
    private void closeMonitorFile() {

        // ----------------------------------------------------------
        // --- Close the file now -----------------------------------
        // ----------------------------------------------------------
        try {
            this.monitorDatasetWriter.close();
        } catch (IOException e) {
            logger.error(e);
        }
        this.monitorDatasetWriter = null;

        // ----------------------------------------------------------
        // --- Create a complete file of the Monitoring with header -
        // ----------------------------------------------------------
        // --- Writer to copy the tmp-file ----------------
        BufferedWriter bw = createMonitorFile(this.monitorFileMeasurement);
        BufferedReader br;
        String currLine;
        try {
            // --- add the header-part --------------------
            bw.write(this.getHeaderLine() + this.monitorDatasetLineSeperator);
            // --- open tmp-file and write it new ---------
            br = new BufferedReader(new FileReader(this.monitorFileMeasurementTmp));
            while ((currLine = br.readLine()) != null) {
                bw.write(currLine + this.monitorDatasetLineSeperator);
                bw.flush();
            }
            bw.close();
            br.close();

        } catch (FileNotFoundException e) {
            logger.error(e);
        } catch (IOException e) {
            logger.error(e);
        }

        // ----------------------------------------------------------
        // --- Write down all container descriptions ----------------
        // ----------------------------------------------------------
        bw = createMonitorFile(this.monitorFileMachines);
        try {
            Iterator<String> it = this.loadContainer2Display.iterator();
            while (it.hasNext()) {

                String containerName = it.next();
                bw.write(this.monitorDatasetPartsDescription.get(containerName));
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void setCurrentDistributionSetup(DistributionSetup currDisSetup) {
        this.currDisSetup = currDisSetup;
        logger.info("Setup " + currDisSetup.getDynamicLoadBalancingClass());
    }
}
