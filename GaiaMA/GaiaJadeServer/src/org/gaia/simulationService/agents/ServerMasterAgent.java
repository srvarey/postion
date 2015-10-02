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
package org.gaia.simulationService.agents;

import jade.content.Concept;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.core.database.DBConnection;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.distribution.JadeRemoteStart;
import org.gaia.simulationService.ontology.BenchmarkResult;
import org.gaia.simulationService.ontology.ClientRegister;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.ClientRemoteContainerRequest;
import org.gaia.simulationService.ontology.ClientTrigger;
import org.gaia.simulationService.ontology.ClientUnregister;
import org.gaia.simulationService.ontology.DistributionOntology;
import org.gaia.simulationService.ontology.OSInfo;
import org.gaia.simulationService.ontology.PlatformAddress;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.PlatformPerformance;
import org.gaia.simulationService.ontology.PlatformTime;
import org.gaia.simulationService.ontology.RegisterReceipt;
import org.gaia.simulationService.ontology.RemoteContainerConfig;
import org.gaia.simulationService.ontology.SlaveRegister;
import org.gaia.simulationService.ontology.SlaveTrigger;
import org.gaia.simulationService.ontology.SlaveUnregister;

/**
 * This agent is part of the <b>Agent.GUI</b> background-system and collects the
 * system information of {@link ServerClientAgent}'s and
 * {@link ServerSlaveAgent}'s to the connected database.<br> <br> Furthermore
 * the agent answers to {@link ClientRemoteContainerRequest} of<br>
 * {@link ServerClientAgent}'s in that way, that it on one hand side forwards
 * <br> this request to a selected {@link ServerSlaveAgent} - on the other hand
 * it<br> answers the {@link ServerClientAgent} about the available and
 * selected<br> machine or node with a {@link ClientRemoteContainerReply}, so
 * that the start<br> of a remote container can be observed, e. g. by using time
 * outs and so on.<br>
 *
 */
public class ServerMasterAgent extends Agent {

    private static final long serialVersionUID = -3947798460986588734L;
    private Ontology ontology = DistributionOntology.getInstance();
    private Codec codec = new SLCodec();
    private ParallelBehaviour parBehaiv = null;
    private DBConnection dbConn = Application.getDatabaseConnection();
    private int portIndex = 0;
    private static final Logger logger = Logger.getLogger(ServerMasterAgent.class);

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        super.setup();

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);

        // --- Add Main-Behaviours ------------------------
        this.parBehaiv = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
        this.parBehaiv.addSubBehaviour(new MessageReceiveBehaviour());
        this.parBehaiv.addSubBehaviour(new CleanUpBehaviour(this, 1000 * 10));
        // --- Add Parallel Behaviour ---------------------
        this.addBehaviour(parBehaiv);
        String tmp = this.getName();
        portIndex = Integer.parseInt(tmp.substring(tmp.indexOf(":") + 1, tmp.indexOf("/")));
        portIndex = portIndex + 10;

    }

    /* (non-Javadoc)
     * @see jade.core.Agent#takeDown()
     */
    @Override
    protected void takeDown() {
        super.takeDown();

    }

    /**
     * Sends a reply ACL message with a specified agent action.
     *
     * @param msg the ACL message
     * @param agentAction the agent action
     * @return true, if successful
     */
    private boolean sendReply(ACLMessage msg, Concept agentAction) {

        // --- Define a new action ------------------------
        Action act = new Action();
        act.setActor(getAID());
        act.setAction(agentAction);

        try {
            // --- ... send -------------------------------
            getContentManager().fillContent(msg, act);
            send(msg);
            return true;
        } catch (CodecException | OntologyException e) {
            logger.error(e);
            return false;
        }
    }
    // --- CleanUp-Behaviour --- S T A R T -----------------
    // -----------------------------------------------------

    /**
     * The CleanUpBehaviour searches for old database entries and removes them.
     */
    private class CleanUpBehaviour extends TickerBehaviour {

        private static final long serialVersionUID = -2401912961869254054L;
        private long tickingInterval_ms = 0;
        private Long tickingInterval_s = new Long(0);

        public CleanUpBehaviour(Agent a, long period) {
            super(a, period);
            tickingInterval_ms = period;
            tickingInterval_s = tickingInterval_ms / 1000;
            // --- Execute the first 'Tick' right now ------
            this.onTick();
        }

        @Override
        protected void onTick() {
            try {
                String sqlStmt;
                Timestamp sqlDate = new Timestamp(new Date().getTime() - tickingInterval_s * 1000);

                sqlStmt = "select contact_agent,last_contact_at FROM platforms WHERE  last_contact_at  <" + StringUtils.QUOTE + sqlDate + StringUtils.QUOTE;
                ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);

                sqlStmt = "DELETE FROM platforms WHERE last_contact_at <" + StringUtils.QUOTE + sqlDate + StringUtils.QUOTE;
                dbConn.getSqlExecuteUpdate(sqlStmt);

                while (res.next()) {
                    if (res.getString(1).startsWith("server.client")) {
                        handleClientRemovedInformation(res.getString(1));
                        sendReportAgentsNotification(res.getString(1), myAgent);
                    }
                    logger.debug("Unregistered after timeout: " + res.getString(1));
                }


            } catch (Exception e) {
                logger.error(e);
            }
        }
    }

    public void sendReportAgentsNotification(String contact, Agent myAgent) {

        try {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType("ReportAgent");
            template.addServices(serviceDescription);

            ACLMessage notify = new ACLMessage(ACLMessage.INFORM);
            notify.setConversationId(GaiaVocabulary.CLIENT_SHUTDOWN);
            notify.setSender(myAgent.getAID());
            notify.setContent(contact);
            DFAgentDescription[] result = DFService.search(myAgent, template);
            for (int i = 0; i < result.length; i++) {
                notify.addReceiver(result[i].getName());
            }
            if (result.length > 0) {
                myAgent.send(notify);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    // -----------------------------------------------------
    // --- Message-Receive-Behaviour --- S T A R T ---------
    // -----------------------------------------------------
    /**
     * The MessageReceiveBehaviour.
     */
    private class MessageReceiveBehaviour extends CyclicBehaviour {

        private static final long serialVersionUID = -1701739199514787426L;

//		private WebserverControllerBehaviour webserverControllerBehaviour = null;
        /* (non-Javadoc)
         * @see jade.core.behaviours.Behaviour#action()
         */
        @Override
        public void action() {

            Action act = null;
            Concept agentAction;
            AID senderAID;

            ACLMessage msg = myAgent.receive();
            if (msg != null) {

                if (msg.getPerformative() == ACLMessage.FAILURE) {
                    // --- No Ontology-specific Message -------------
                    act = null;
                    logger.fatal("ACLMessage.FAILURE from " + msg.getSender().getName() + ": " + msg.getContent());

                } else {
                    // --- Ontology-specific Message ----------------
                    try {
                        act = (Action) getContentManager().extractContent(msg);
                    } catch (UngroundedException e) {
                        logger.error(e);
                    } catch (CodecException e) {
                        logger.error(e);
                    } catch (OntologyException e) {
                        logger.error(e);
                    }
                }

                if (act != null) {

                    agentAction = act.getAction();
                    senderAID = act.getActor();

                    // ------------------------------------------------------------------
                    // --- Cases AgentAction --- S T A R T ------------------------------
                    // ------------------------------------------------------------------
                    if (agentAction instanceof SlaveRegister) {

                        SlaveRegister sr = (SlaveRegister) agentAction;
                        PlatformAddress plAdd = sr.getSlaveAddress();
                        PlatformTime plTime = sr.getSlaveTime();
                        PlatformPerformance plPerf = sr.getSlavePerformance();
                        OSInfo os = sr.getSlaveOS();

                        Long timestamp = Long.parseLong(plTime.getTimeStampAsString());
                        Date plDate = new Date(timestamp);

                        // --- Register platform ------------------------------
                        dbRegisterPlatform(senderAID, os, plAdd, plPerf, plDate, true);
                        // --- Answer with 'RegisterReceipt' --------------
                        RegisterReceipt rr = new RegisterReceipt();
                        ACLMessage reply = msg.createReply();
                        sendReply(reply, rr);

                    } else if (agentAction instanceof ClientRegister) {

                        ClientRegister cr = (ClientRegister) agentAction;
                        PlatformAddress plAdd = cr.getClientAddress();
                        PlatformTime plTime = cr.getClientTime();
                        PlatformPerformance plPerf = cr.getClientPerformance();
                        OSInfo os = cr.getClientOS();

                        Long timestamp = Long.parseLong(plTime.getTimeStampAsString());
                        Date plDate = new Date(timestamp);

                        // --- Register platform ------------------------------
                        dbRegisterPlatform(senderAID, os, plAdd, plPerf, plDate, false);
                    } else if (agentAction instanceof SlaveTrigger) {

                        SlaveTrigger st = (SlaveTrigger) agentAction;
                        PlatformTime plTime = st.getTriggerTime();
                        PlatformLoad plLoad = st.getSlaveLoad();
                        BenchmarkResult bmr = st.getSlaveBenchmarkValue();

                        Long timestamp = Long.parseLong(plTime.getTimeStampAsString());
                        Date plDate = new Date(timestamp);

                        dbTriggerPlatform(senderAID, plDate, plLoad, bmr);

                    } else if (agentAction instanceof ClientTrigger) {

                        ClientTrigger st = (ClientTrigger) agentAction;
                        PlatformTime plTime = st.getTriggerTime();
                        PlatformLoad plLoad = st.getClientLoad();
                        BenchmarkResult bmr = st.getClientBenchmarkValue();

                        Long timestamp = Long.parseLong(plTime.getTimeStampAsString());
                        Date plDate = new Date(timestamp);

                        dbTriggerPlatform(senderAID, plDate, plLoad, bmr);

                    } else if (agentAction instanceof SlaveUnregister) {

                        dbUnregisterPlatform(senderAID);

                    } else if (agentAction instanceof ClientUnregister) {

                        dbUnregisterPlatform(senderAID);

                    } else if (agentAction instanceof ClientRemoteContainerRequest) {

                        ClientRemoteContainerRequest crcr = (ClientRemoteContainerRequest) agentAction;
                        handleClientRemoteContainerRequest(msg, crcr); // --- !!!!! ---

                    } else {
                        logger.error(myAgent.getLocalName() + ": Unknown Message-Type!");
                    }
                }
            } else {
                block();
            }
        }
    }

    /**
     * This method is used for the registration of slave- and client-platforms
     * in the database table 'platforms' of the used database.
     *
     * @param sender the AID of the sender
     * @param os the information about the operating system
     * @param platform the PlatformAddress of the platform
     * @param performance the PlatformPerformance
     * @param time the time
     * @param isServer true, if the sender is a server instance of Agent.GUI
     *
     * @see OSInfo
     * @see PlatformAddress
     * @see PlatformPerformance
     */
    private void dbRegisterPlatform(AID sender, OSInfo os, PlatformAddress platform, PlatformPerformance performance, Date time, boolean isServer) {

        String sqlStmt;
        Timestamp sqlDate = new Timestamp(time.getTime());
        int isServerBool = dbConn.dbBool2Integer(isServer);


        // --- Looks if the AID already exists in TB ----------
        sqlStmt = "SELECT * FROM platforms WHERE contact_agent='" + sender.getName() + StringUtils.QUOTE;
        ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);
        if (res != null) {
            try {

                res.last();
                if (res.getRow() == 0) {
                    // --- Insert new dataset -----------------
                    sqlStmt = "INSERT INTO platforms "
                            + "(contact_agent,platform_name,is_server,ip,url,jade_port,http4mtp,os_name,os_version,os_arch,cpu_vendor,cpu_model,cpu_n,cpu_speed_mhz,memory_total_mb,benchmark_value,online_since,last_contact_at,local_online_since,local_last_contact_at,currently_available)"
                            + "VALUES( "
                            + StringUtils.QUOTE + sender.getName() + "',"
                            + StringUtils.QUOTE + platform.getIp() + ":" + platform.getPort() + "/JADE',"
                            + "" + isServerBool + StringUtils.COMMA
                            + StringUtils.QUOTE + platform.getIp() + "',"
                            + StringUtils.QUOTE + platform.getUrl() + "',"
                            + "" + platform.getPort() + StringUtils.COMMA
                            + StringUtils.QUOTE + platform.getHttp4mtp() + "',"
                            + StringUtils.QUOTE + os.getOs_name() + "',"
                            + StringUtils.QUOTE + os.getOs_version() + "',"
                            + StringUtils.QUOTE + os.getOs_arch() + "',"
                            + StringUtils.QUOTE + performance.getCpu_vendor() + "',"
                            + StringUtils.QUOTE + performance.getCpu_model() + "',"
                            + "" + performance.getCpu_numberOf() + StringUtils.COMMA
                            + "" + performance.getCpu_speedMhz() + StringUtils.COMMA
                            + "" + performance.getMemory_totalMB() + StringUtils.COMMA
                            + "0,"
                            + StringUtils.QUOTE + sqlDate + "',"
                            + StringUtils.QUOTE + sqlDate + "',"
                            + StringUtils.QUOTE + sqlDate + "',"
                            + StringUtils.QUOTE + sqlDate + "',"
                            + "" + dbConn.dbBool2Integer(true) + ")";
                    logger.info("Registered " + sender.getName());
                    Statement statement = dbConn.getNewStatement();
                    statement.execute(sqlStmt);

                } else {
                    // --- Update dataset ---------------------
                    sqlStmt = "UPDATE platforms SET "
                            + "contact_agent = '" + sender.getName() + "',"
                            + "platform_name = '" + platform.getIp() + ":" + platform.getPort() + "/JADE',"
                            + "is_server = " + isServerBool + StringUtils.COMMA
                            + "ip = '" + platform.getIp() + "',"
                            + "url = '" + platform.getUrl() + "',"
                            + "jade_port = " + platform.getPort() + StringUtils.COMMA
                            + "http4mtp = '" + platform.getHttp4mtp() + "',"
                            + "os_name = '" + os.getOs_name() + "',"
                            + "os_version = '" + os.getOs_version() + "',"
                            + "os_arch = '" + os.getOs_arch() + "',"
                            + "cpu_vendor = '" + performance.getCpu_vendor() + "',"
                            + "cpu_model = '" + performance.getCpu_model() + "',"
                            + "cpu_n = " + performance.getCpu_numberOf() + StringUtils.COMMA
                            + "cpu_speed_mhz = " + performance.getCpu_speedMhz() + StringUtils.COMMA
                            + "memory_total_mb = " + performance.getMemory_totalMB() + StringUtils.COMMA
                            + "benchmark_value = 0,"
                            + "online_since = '" + sqlDate + "',"
                            + "last_contact_at = '" + sqlDate + "',"
                            + "local_online_since = '" + sqlDate + "',"
                            + "local_last_contact_at = '" + sqlDate + "',"
                            + "currently_available = " + dbConn.dbBool2Integer(true) + StringUtils.SPACE
                            + "WHERE contact_agent='" + sender.getName() + StringUtils.QUOTE;
                    dbConn.getSqlExecuteUpdate(sqlStmt);
                }

            } catch (SQLException e) {
                logger.error(e);
                dbConn.dbError.setErrNumber(e.getErrorCode());
                dbConn.dbError.setHead("DB-Error during registration of a Slave-Servers!");
                dbConn.dbError.setText(e.getLocalizedMessage());
                dbConn.dbError.setErr(true);
                dbConn.dbError.show();
            }
        }

    }

    /**
     * This method will process the trigger events of the involved slave- and
     * client-platforms.
     *
     * @param sender the AID of the sender
     * @param time the time
     * @param platformLoad the PlatformLoad of the platform
     * @param benchmarkResult the BenchmarkResult of the platform
     *
     * @see PlatformLoad
     * @see BenchmarkResult
     */
    private void dbTriggerPlatform(AID sender, Date time, PlatformLoad platformLoad, BenchmarkResult benchmarkResult) {

        String sqlStmt;
        Timestamp sqlDate = new Timestamp(time.getTime());

        // --- Update Dataset ---------------------
        sqlStmt = "UPDATE platforms SET "
                + "last_contact_at = '" + sqlDate + "',"
                + "local_last_contact_at = '" + sqlDate + "',"
                + "benchmark_value = " + benchmarkResult.getBenchmarkValue() + StringUtils.COMMA
                + "currently_available = -1, "
                + "current_load_cpu = " + platformLoad.getLoadCPU() + StringUtils.COMMA
                + "current_load_memory_system = " + platformLoad.getLoadMemorySystem() + StringUtils.COMMA
                + "current_load_memory_jvm = " + platformLoad.getLoadMemoryJVM() + StringUtils.COMMA
                + "current_load_no_threads = " + platformLoad.getLoadNoThreads() + StringUtils.COMMA
                + "current_load_threshold_exceeded = " + platformLoad.getLoadExceeded() + StringUtils.SPACE
                + "WHERE contact_agent='" + sender.getName() + StringUtils.QUOTE;
        dbConn.getSqlExecuteUpdate(sqlStmt);
    }

    /**
     * This method will unregister slave- and client-platform.
     *
     * @param sender the AID of the sender
     */
    private void dbUnregisterPlatform(AID sender) {

        String sqlStmt;
        // --- Update Dataset ---------------------
        sqlStmt = "UPDATE platforms SET "
                + "online_since = null,"
                + "local_online_since = null,"
                + "currently_available = " + dbConn.dbBool2Integer(false) + StringUtils.COMMA
                + "current_load_cpu = null,"
                + "current_load_memory_system = null,"
                + "current_load_memory_jvm = null,"
                + "current_load_no_threads = null,"
                + "current_load_threshold_exceeded = null "
                + "WHERE contact_agent='" + sender.getName() + StringUtils.QUOTE;
        dbConn.getSqlExecuteUpdate(sqlStmt);
    }

    /**
     * Handles the client remote container request.
     *
     * @param request the request ACLMessage
     * @param crcr the ClientRemoteContainerRequest
     * @return true, if successful
     */
    private boolean handleClientRemoteContainerRequest(ACLMessage request, ClientRemoteContainerRequest crcr) {

        boolean exitRequest = false;

        String sqlStmt;
        String slaveAgent = null;
        String slaveAgentAddress = null;
        AID slavePlatformAgent;

        Action act;
        ClientRemoteContainerReply replyContent = new ClientRemoteContainerReply();

        RemoteContainerConfig remConf = crcr.getRemoteConfig();
        String containerName = remConf.getJadeContainerName();


        // --------------------------------------------------------------------
        // --- Select the machine with the highest potential of 		 ------
        // --- Mflops (Millions of floating point operations per second) ------
        // --- in relation to the current processor/CPU-load AND 		 ------
        // --- with the needed memory									 ------
        // --------------------------------------------------------------------
        sqlStmt = "SELECT (benchmark_value-(benchmark_value*current_load_cpu/100)) AS potential, ";
        sqlStmt += "platforms.* ";
        sqlStmt += "FROM platforms ";
        sqlStmt += "WHERE is_server=-1 AND currently_available=-1 AND current_load_threshold_exceeded=0 ";
        sqlStmt += "ORDER BY (benchmark_value-(benchmark_value*current_load_cpu/100)) DESC";
        ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);
        try {
            if (res.wasNull()) {
                exitRequest = true;
            }
            if (exitRequest == false && dbConn.getRowCount(res) == 0) {
                exitRequest = true;
            }
            if (exitRequest == true) {
                // --- No Server.Slave was found => EXIT ----------------------
                logger.error(this.getLocalName() + ": No Server.Slave was found! - Cancel action of remote-container-request!");

                replyContent.setRemoteContainerName(containerName);
                replyContent.setRemoteAddress(null);
                replyContent.setRemoteOS(null);
                replyContent.setRemotePerformance(null);
                replyContent.setRemoteBenchmarkResult(null);

            } else {
                // --- Server.Slave was found => request a remote-container ---
                res.next();
                slaveAgent = res.getString("contact_agent");
                slaveAgentAddress = res.getString("http4mtp");

                // ------------------------------------------------------------
                // --- Collect all need information for the reply -------------
                // ------------------------------------------------------------
                OSInfo os = new OSInfo();
                os.setOs_name(res.getString("os_name"));
                os.setOs_version(res.getString("os_version"));
                os.setOs_arch(res.getString("os_arch"));

                PlatformAddress plAdd = new PlatformAddress();
                plAdd.setIp(res.getString("ip"));
                plAdd.setUrl(res.getString("url"));
                plAdd.setPort(res.getInt("jade_port"));
                plAdd.setHttp4mtp(res.getString("http4mtp"));

                PlatformPerformance plPerf = new PlatformPerformance();
                plPerf.setCpu_vendor(res.getString("cpu_vendor"));
                plPerf.setCpu_model(res.getString("cpu_model"));
                plPerf.setCpu_numberOf(res.getInt("cpu_n"));
                plPerf.setCpu_speedMhz(res.getInt("cpu_speed_mhz"));
                plPerf.setMemory_totalMB(res.getInt("memory_total_mb"));

                BenchmarkResult bench = new BenchmarkResult();
                bench.setBenchmarkValue(res.getFloat("benchmark_value"));

                replyContent.setRemoteContainerName(containerName);
                replyContent.setRemoteAddress(plAdd);
                replyContent.setRemoteOS(os);
                replyContent.setRemotePerformance(plPerf);
                replyContent.setRemoteBenchmarkResult(bench);
                // ------------------------------------------------------------

            }
            res.close();

        } catch (SQLException e) {
            logger.error(e);
            return false;
        }

        // --------------------------------------------------------------------
        // --- Answer Request with 'ClientRemoteContainerReply' ---------------
        // --------------------------------------------------------------------
        act = new Action();
        act.setActor(this.getAID());
        act.setAction(replyContent);

        ACLMessage reply = request.createReply();
        try {
            this.getContentManager().fillContent(reply, act);
            this.send(reply);
        } catch (CodecException | OntologyException e) {
            logger.error(e);
            return false;
        }
        if (exitRequest == true) {
            return true;
        }
        // --- Set the ReceiverAgent ------------------------------------------
        slavePlatformAgent = new AID(slaveAgent, AID.ISGUID);
        slavePlatformAgent.addAddresses(slaveAgentAddress);

        // --- Define Action --------------------------------------------------
        act = new Action();
        act.setActor(this.getAID());
        act.setAction(crcr);
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setSender(getAID());
        msg.addReceiver(slavePlatformAgent);
        msg.setLanguage(codec.getName());
        msg.setOntology(ontology.getName());


        logger.info("Inform server.slave for Remote-Container: " + slaveAgent + " | " + slaveAgentAddress);
        try {
            this.getContentManager().fillContent(msg, act);
            this.send(msg);
        } catch (CodecException | OntologyException e) {
            logger.error(e);
            return false;
        }

        return true;

    }

    /**
     * Handles the client closing.
     *
     * @param request the request ACLMessage
     * @param crcr the ClientRemoteContainerRequest
     * @return true, if successful
     */
    private boolean handleClientRemovedInformation(String name) {


        String sqlStmt = "select contact_agent,http4mtp from platforms where contact_agent like 'server.slave%'";
        ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);

        try {
            // --- Do we have a SQL-result ? ----------------------------------
            if (res.wasNull() || dbConn.getRowCount(res) == 0) {
                return true;
            } else {

                while (res.next()) {
                    String slaveAgent = res.getString(1);
                    String slaveAgentAddress = res.getString(2);

                    // --- Set the ReceiverAgent ------------------------------------------
                    AID slavePlatformAgent = new AID(slaveAgent, AID.ISGUID);
                    slavePlatformAgent.addAddresses(slaveAgentAddress);

                    // --- Define Action --------------------------------------------------
                    ClientUnregister cr = new ClientUnregister();
                    cr.setClientAddress(name);
                    Action act = new Action();
                    act.setActor(this.getAID());
                    act.setAction(cr);

                    // --- send message to all slaves to kill their container --------------------------------
                    ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                    msg.setSender(getAID());
                    msg.addReceiver(slavePlatformAgent);
                    msg.setLanguage(codec.getName());
                    msg.setOntology(ontology.getName());
                    logger.info("Inform server.slave " + slaveAgent + " for closed client: " + ((ClientUnregister) act.getAction()).getClientAddress());
                    try {
                        this.getContentManager().fillContent(msg, act);
                        this.send(msg);
                    } catch (CodecException | OntologyException e) {
                        logger.error(e);
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return true;
    }

    public AID getDispatcher(String name, String host) {

        // --- Looks if the AID already exists in DB ----------
        AID dispatch = getDispatcherFromDB();
        if (dispatch == null) {
            RemoteContainerConfig conf = new RemoteContainerConfig();
            conf.setJadeContainerName(name);
            conf.setJadeIsRemoteContainer(true);
            conf.setJadeHost(host);
            startDispatcher(conf);
        }

        dispatch = getDispatcherFromDB();
        if (dispatch == null) {
            logger.error("Unable to start dispatcher ");
        }
        return dispatch;
    }

    public AID getDispatcherFromDB() {
        AID slavePlatformAgent = null;
        String sqlStmt = "SELECT contact_agent,http4mtp FROM platforms WHERE contact_agent like 'server.slave.dispatcher%'";
        ResultSet res = dbConn.getSqlResult4ExecuteQuery(sqlStmt);
        if (res != null) {
            try {
                res.last();
                if (res.getRow() != 0) {

                    String slaveAgent = res.getString(1);
                    String slaveAgentAddress = res.getString(2);

                    // --- Set the ReceiverAgent ------------------------------------------
                    slavePlatformAgent = new AID(slaveAgent, AID.ISGUID);
                    slavePlatformAgent.addAddresses(slaveAgentAddress);
                }
            } catch (Exception e) {
                logger.error(e);
            }
        }
        return slavePlatformAgent;
    }

    /**
     * Starts a Remote-Container for given RemoteContainerConfig-Instance.
     *
     * @param remoteContainerConfig the remote container configuration
     */
    private void startDispatcher(RemoteContainerConfig remoteContainerConfig) {
        try {
            logger.debug("Starting remote container ... ");
            portIndex++;
            new JadeRemoteStart(remoteContainerConfig, portIndex).start();
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
