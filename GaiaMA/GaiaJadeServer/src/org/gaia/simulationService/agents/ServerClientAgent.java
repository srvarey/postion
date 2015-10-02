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
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.Codec.CodecException;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.JADEAgentManagement.JADEManagementOntology;
import jade.domain.introspection.Occurred;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;
import java.io.Serializable;
import org.apache.log4j.Logger;
import org.gaia.core.network.JadeUrlChecker;
import org.gaia.core.project.DistributionSetup;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.LoadServiceHelper;
import org.gaia.simulationService.balancing.DynamicLoadBalancing;
import org.gaia.simulationService.load.LoadMeasureThread;
import org.gaia.simulationService.ontology.BenchmarkResult;
import org.gaia.simulationService.ontology.ClientRegister;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.ClientRemoteContainerRequest;
import org.gaia.simulationService.ontology.ClientTrigger;
import org.gaia.simulationService.ontology.ClientUnregister;
import org.gaia.simulationService.ontology.DistributionOntology;
import org.gaia.simulationService.ontology.MasterUpdateNote;
import org.gaia.simulationService.ontology.OSInfo;
import org.gaia.simulationService.ontology.PlatformAddress;
import org.gaia.simulationService.ontology.PlatformLoad;
import org.gaia.simulationService.ontology.PlatformPerformance;
import org.gaia.simulationService.ontology.PlatformTime;
import org.gaia.simulationService.ontology.RegisterReceipt;
import org.gaia.simulationService.ontology.RemoteContainerConfig;

/**
 * This agent is part of the <b>Agent.GUI</b> background-system and connects the
 * end-user application with the {@link ServerMasterAgent} in order to allow the
 * start of new remote container.
 *
 */
public class ServerClientAgent extends Agent {

    private static final long serialVersionUID = -3947798460986588734L;
    private Ontology ontology = DistributionOntology.getInstance();
    private Ontology ontologyJadeMgmt = JADEManagementOntology.getInstance();
    private Codec codec = new SLCodec();
    private PlatformAddress mainPlatform = new PlatformAddress();
    private AID mainPlatformAgent = null;
    private boolean mainPlatformReachable = true;
    private ClientRemoteContainerReply myCRCreply = null;
    private PlatformAddress myPlatform = null;
    private PlatformPerformance myPerformance = null;
    private OSInfo myOS = null;
    private PlatformTime myPlatformTime = new PlatformTime();
    private ClientRegister myRegistration = new ClientRegister();
    private PlatformLoad myLoad = new PlatformLoad();
    private ParallelBehaviour parBehaiv = null;
    private TriggerBehaiviour trigger = null;
    private SaveNodeDescriptionBehaviour sndBehaiv = null;
    private long triggerTime = new Long(1000 * 1);
    private long triggerTime4Reconnection = new Long(1000 * 20);
    private int sendNotReachable = 0;
    private int sendNotReachableMax = 3;
    private static final Logger logger = Logger.getLogger(ServerClientAgent.class);

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        super.setup();

        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        getContentManager().registerOntology(ontologyJadeMgmt);

        LoadServiceHelper loadHelper = null;
        try {
            loadHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
            // --- get the local systems-informations ---------
            myCRCreply = loadHelper.getLocalCRCReply();

            // --- Define Platform-Info -----------------------
            myPlatform = myCRCreply.getRemoteAddress();
            // --- Set the Performance of machine -------------
            myPerformance = myCRCreply.getRemotePerformance();
            // --- Set OS-Informations ------------------------
            myOS = myCRCreply.getRemoteOS();

        } catch (ServiceException e) {
            // --- problems to get the SimulationsService ! ---
            if (loadHelper == null) {
                this.doDelete();
                return;
            } else {
                logger.error(e);
            }
        }
        // --- Define Main-Platform-Info ------------------
        JadeUrlChecker myURL = Application.getJadePlatform().urlChecker;
        mainPlatform.setIp(myURL.getHostIP());
        mainPlatform.setUrl(myURL.getHostName());
        mainPlatform.setPort(myURL.getPort());
        mainPlatform.setHttp4mtp(myURL.getJADEurl4MTP());

        // --- Define Receiver of local Status-Info -------
        String jadeURL = myURL.getJADEurl();
        if (jadeURL != null) {
            mainPlatformAgent = new AID("server.master" + "@" + myURL.getJADEurl(), AID.ISGUID);
            mainPlatformAgent.addAddresses(mainPlatform.getHttp4mtp());
        }

        // --- Set myTime ---------------------------------
        myPlatformTime.setTimeStampAsString(Long.toString(System.currentTimeMillis()));

        // --- Send 'Register'-Information ----------------
        myRegistration.setClientAddress(myPlatform);
        myRegistration.setClientTime(myPlatformTime);
        myRegistration.setClientPerformance(myPerformance);
        myRegistration.setClientOS(myOS);
        this.sendMessage2MainServer(myRegistration);

        // --- Add Main-Behaviours ------------------------
        parBehaiv = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
        parBehaiv.addSubBehaviour(new MessageReceiveBehaviour());
        trigger = new TriggerBehaiviour(this, triggerTime);
        parBehaiv.addSubBehaviour(trigger);
        sndBehaiv = new SaveNodeDescriptionBehaviour(this, 500);
        parBehaiv.addSubBehaviour(sndBehaiv);

        // --- Add the parallel Behaviour from above ------
        this.addBehaviour(parBehaiv);

        // --- Finally start the LoadAgent ----------------
        try {
            DistributionSetup currDissSetup = new DistributionSetup();
            currDissSetup.setDoDynamicLoadBalancing(true);
            currDissSetup.setDynamicLoadBalancingClass(DynamicLoadBalancing.class.getName());
            currDissSetup.setExcludeLocal(true);
            currDissSetup.setLocalOnly(false);
            currDissSetup.setDispatcher(Application.getGlobalInfo().getDispatcher());

            LoadMeasureAgent lma = new LoadMeasureAgent();
            lma.setCurrentDistributionSetup(currDissSetup);
            AgentController c = this.getContainerController().acceptNewAgent("server.load", lma);
            c.start();

            this.getContainerController().createNewAgent("rsa", org.gaia.simulationService.agents.RemoteStarterAgent.class.getName(), null).start();

        } catch (StaleProxyException e) {
            logger.error(e);
        }


    }

    /* (non-Javadoc)
     * @see jade.core.Agent#takeDown()
     */
    @Override
    protected void takeDown() {
        super.takeDown();

        // --- Stop Parallel-Behaviour --------------------
        if (parBehaiv != null) {
            this.removeBehaviour(parBehaiv);
        }

        // --- Send 'Unregister'-Information --------------
        ClientUnregister unReg = new ClientUnregister();
        this.sendMessage2MainServer(unReg);

    }

    /**
     * This method sends a message to the ServerMasterAgent.
     *
     * @param agentAction the agent action
     * @return true, if successful
     */
    private boolean sendMessage2MainServer(Concept agentAction) {

        // --- In case that we have no address ------------
        if (mainPlatformAgent == null) {
            return false;
        }

        // --- Define a new action ------------------------
        Action act = new Action();
        act.setActor(getAID());
        act.setAction(agentAction);

        // --- Build Messagr ... --------------------------
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.setSender(getAID());
        msg.addReceiver(mainPlatformAgent);
        msg.setLanguage(codec.getName());
        msg.setOntology(ontology.getName());

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

    /**
     * Forwards a remote container request to the ServerMasterAgent, which comes
     * from the local SimulationService.
     *
     * @param agentAction the agent action
     */
    private void forwardRemoteContainerRequest(Concept agentAction) {

        // --- Request to start a new Remote-Container -----
        ClientRemoteContainerRequest req = (ClientRemoteContainerRequest) agentAction;
        RemoteContainerConfig remConf = req.getRemoteConfig();
        if (remConf == null) {

            LoadServiceHelper loadHelper;
            try {
                loadHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
                remConf = loadHelper.getAutoRemoteContainerConfig();
            } catch (ServiceException e) {
                logger.error(e);
            }
            req.setRemoteConfig(remConf);

        }
        this.sendMessage2MainServer(req);

    }

    // -----------------------------------------------------
    // --- Message-Receive-Behaviour --- S T A R T ---------
    // -----------------------------------------------------
    /**
     * The MessageReceiveBehaviour of this agent.
     */
    private class MessageReceiveBehaviour extends CyclicBehaviour {

        private static final long serialVersionUID = -1701739199514787426L;

        @Override
        public void action() {
            Action act;
            Concept agentAction;
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                act = null;

                // --------------------------------------------------
                // --- Is the msg-content-object an AgentAction? ----
                // --------------------------------------------------

                try {
                    Serializable content = msg.getContentObject();
                    if (content instanceof Action) {
                        act = (Action) content;
                    }
                } catch (UnreadableException e) {
                    logger.trace("ServerClientAgent MessageReceiveBehaviour : " + e);
                }
                if (act == null) {
                    // --------------------------------------------------
                    // --- Try to use the ContentManager for decoding ---
                    // --------------------------------------------------
                    if (msg.getPerformative() == ACLMessage.FAILURE) {
                        // --- Server.Master not reachable ? ------------
                        String msgContent = msg.getContent();
                        if (msgContent.contains("server.master")) {
                            if (sendNotReachable < sendNotReachableMax) {
                                logger.error("Server.Master not reachable! Try to reconnect in " + (triggerTime4Reconnection / 1000) + " seconds ...");
                                sendNotReachable++;
                                if (sendNotReachable >= sendNotReachableMax) {
                                    logger.error("Server.Master not reachable! Stop now to inform that Server.Master is not reachable!");
                                }
                            }
                            trigger.reset(triggerTime4Reconnection);
                            mainPlatformReachable = false;
                        } else {
                            logger.error("ACLMessage.FAILURE from " + msg.getSender().getName() + ": " + msg.getContent());
                        }

                    } else {
                        // --- Ontology-specific Message ----------------
                        try {
                            ContentElement con = getContentManager().extractContent(msg);
                            if (con instanceof Action) {
                                act = (Action) con;
                            } else if (con instanceof Occurred) {
                                // Occurred occ = (Occurred) con;
                                // --- Messages in the context of Introspection ---
                            } else {
                                logger.error("=> " + myAgent.getName() + " - Unknown MessageType: " + con.toString());
                            }
                        } catch (UngroundedException e) {
                            logger.error(e);
                        } catch (CodecException e) {
                            logger.error(e);
                        } catch (OntologyException e) {
                            logger.error(e);
                        };
                    }
                }

                // --------------------------------------------------
                // --- Work on the AgentAction of the Message -------
                // --------------------------------------------------
                if (act != null) {
                    agentAction = act.getAction();
                    if (agentAction instanceof RegisterReceipt) {
                        logger.info("Server.Master (re)connected!");
                        sendNotReachable = 0;
                        mainPlatformReachable = true;
                        trigger.reset(triggerTime);

                    } else if (agentAction instanceof MasterUpdateNote) {
                        logger.info("Server.Master (re)connected, but call for an update!");
                        MasterUpdateNote masterUpdateNote = (MasterUpdateNote) agentAction;
                        String updateInfoURL = masterUpdateNote.getUpdateInfoURL();
                        logger.info("Download Update-Information: " + updateInfoURL);
                    } else if (agentAction instanceof ClientRemoteContainerRequest) {
                        forwardRemoteContainerRequest(agentAction);

                    } else if (agentAction instanceof ClientRemoteContainerReply) {
                        // --- Answer to 'RemoteContainerRequest' -------------
                        LoadServiceHelper loadHelper = null;
                        try {
                            loadHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
                            ClientRemoteContainerReply crcr = (ClientRemoteContainerReply) agentAction;
                            logger.info("Replied with " + crcr.getRemoteContainerName());
                            loadHelper.putContainerDescription(crcr);
                        } catch (ServiceException e) {
                            logger.error(e);
                        }

                    } else {
                        logger.warn(myAgent.getLocalName() + ": Unknown Message-Type!");
                    }
                }
            } else {
                block();
            }
        }
    }

    // -----------------------------------------------------
    // --- Trigger-Behaviour --- S T A R T -----------------
    // -----------------------------------------------------
    /**
     * The TriggerBehaiviour of the agents, which informs the ServerMasterAgent
     * about the current system state and load.
     */
    private class TriggerBehaiviour extends TickerBehaviour {

        private static final long serialVersionUID = -1701739199514787426L;

        /**
         * Instantiates a new trigger behaiviour.
         *
         * @param a the a
         * @param period the period
         */
        public TriggerBehaiviour(Agent a, long period) {
            super(a, period);
        }

        /* (non-Javadoc)
         * @see jade.core.behaviours.TickerBehaviour#onTick()
         */
        @Override
        protected void onTick() {

            myPlatformTime.setTimeStampAsString(Long.toString(System.currentTimeMillis()));

            if (mainPlatformReachable == false) {
                // --- Refresh registration time --------------------
                myRegistration.setClientTime(myPlatformTime);

                // --- Send Message ---------------------------------
                sendMessage2MainServer(myRegistration);

            } else {
                // --------------------------------------------------
                // --- Just send a trigger --------------------------
                // --------------------------------------------------
                ClientTrigger trig = new ClientTrigger();

                // --- Current Time ---------------------------------
                trig.setTriggerTime(myPlatformTime);

                // --- get current Load-Level -----------------------
                myLoad.setLoadCPU(LoadMeasureThread.getLoadCPU());
                myLoad.setLoadMemorySystem(LoadMeasureThread.getLoadMemorySystem());
                myLoad.setLoadMemoryJVM(LoadMeasureThread.getLoadMemoryJVM());
                myLoad.setLoadNoThreads(LoadMeasureThread.getLoadNoThreads());
                myLoad.setLoadExceeded(LoadMeasureThread.getThresholdLevelExceeded());
                trig.setClientLoad(myLoad);

                // --- get Current Benchmark-Result -----------------
                BenchmarkResult bmr = new BenchmarkResult();
                bmr.setBenchmarkValue(LoadMeasureThread.getCompositeBenchmarkValue());
                trig.setClientBenchmarkValue(bmr);

                // --- Send Message ---------------------------------
                sendMessage2MainServer(trig);
            }

        }
    }
    // -----------------------------------------------------
    // --- Save Node Description Behaviour --- S T A R T ---
    // -----------------------------------------------------

    /**
     * The SaveNodeDescriptionBehaviour, which waits for the result of the
     * benchmark in order to save this value also in the local node description
     * by using the LoadService.
     */
    private class SaveNodeDescriptionBehaviour extends TickerBehaviour {

        private static final long serialVersionUID = 5704581376150290621L;

        /**
         * Instantiates a new save node description behaviour.
         *
         * @param a the a
         * @param period the period
         */
        public SaveNodeDescriptionBehaviour(Agent a, long period) {
            super(a, period);
        }

        /* (non-Javadoc)
         * @see jade.core.behaviours.TickerBehaviour#onTick()
         */
        @Override
        protected void onTick() {

            if (LoadMeasureThread.getCompositeBenchmarkValue() != 0) {
                // --- Put the local NodeDescription into the -----
                // --- SimulationService 					  -----
                try {

                    BenchmarkResult bench = new BenchmarkResult();
                    bench.setBenchmarkValue(LoadMeasureThread.getCompositeBenchmarkValue());
                    myCRCreply.setRemoteBenchmarkResult(bench);

                    LoadServiceHelper loadHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
                    loadHelper.putContainerDescription(myCRCreply);
                    loadHelper.setAndSaveCRCReplyLocal(myCRCreply);

                } catch (ServiceException e) {
                    logger.error(e);
                }
                this.stop();
            }
        }
    }
}
