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
import jade.core.ServiceException;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import org.apache.log4j.Logger;
import org.gaia.core.network.JadeUrlChecker;
import org.gaia.jade.server.Application;
import org.gaia.simulationService.LoadService;
import org.gaia.simulationService.LoadServiceHelper;
import org.gaia.simulationService.distribution.ContainerKiller;
import org.gaia.simulationService.distribution.JadeRemoteStart;
import org.gaia.simulationService.load.LoadMeasureThread;
import org.gaia.simulationService.ontology.BenchmarkResult;
import org.gaia.simulationService.ontology.ClientRemoteContainerReply;
import org.gaia.simulationService.ontology.ClientRemoteContainerRequest;
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
import org.gaia.simulationService.ontology.SlaveRegister;
import org.gaia.simulationService.ontology.SlaveTrigger;
import org.gaia.simulationService.ontology.SlaveUnregister;

/**
 * This agent is part of the <b>Agent.GUI</b> background-system and waits for a
 * so called {@link ClientRemoteContainerRequest} in order to start a new
 * container for a remotely located platform.<br> Additionally this agent
 * informs the {@link ServerMasterAgent} about the current state of the machine,
 * where this agent is located.
 *
 */
public class ServerSlaveAgent extends Agent {
    private static final Logger logger = Logger.getLogger(ServerSlaveAgent.class);
    private static final long serialVersionUID = -3947798460986588734L;
    private Ontology ontology = DistributionOntology.getInstance();
    private Codec codec = new SLCodec();
    private PlatformAddress mainPlatform = new PlatformAddress();
    private AID mainPlatformAgent = null;
    private boolean mainPlatformReachable = true;
    private ClientRemoteContainerReply myCRCreply = null;
    private PlatformAddress myPlatform = null;
    private PlatformPerformance myPerformance = null;
    private OSInfo myOS = null;
    private PlatformTime myPlatformTime = new PlatformTime();
    private SlaveRegister myRegistration = new SlaveRegister();
    private PlatformLoad myLoad = new PlatformLoad();
    private ParallelBehaviour parBehaiv = null;
    private TriggerBehaiviour trigger = null;
    private SaveNodeDescriptionBehaviour sndBehaiv = null;
    private long triggerTime = new Long(1000 * 1);
    private long triggerTime4Reconnection = new Long(1000 * 20);
    private int sendNotReachable = 0;
    private int sendNotReachableMax = 3;
    private int portIndex;

    /* (non-Javadoc)
     * @see jade.core.Agent#setup()
     */
    @Override
    protected void setup() {
        super.setup();
        // --- Register Codec and Ontology ----------------
        getContentManager().registerLanguage(codec);
        getContentManager().registerLanguage(new SLCodec(), FIPANames.ContentLanguage.FIPA_SL);
        getContentManager().registerOntology(ontology);

        LoadServiceHelper simHelper = null;
        try {
            simHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
            // --- get the local systems-informations ---------
            myCRCreply = simHelper.getLocalCRCReply();

            // --- Define Platform-Info -----------------------
            myPlatform = myCRCreply.getRemoteAddress();
            // --- Set the Performance of machine -------------
            myPerformance = myCRCreply.getRemotePerformance();
            // --- Set OS-Informations ------------------------
            myOS = myCRCreply.getRemoteOS();
          
            portIndex = myPlatform.getPort() + 10;

        } catch (ServiceException e) {
            logger.error(e);
        }

        // --- Define Main-Platform-Info ------------------
        JadeUrlChecker myURL = Application.getJadePlatform().urlChecker;
        mainPlatform.setIp(myURL.getHostIP());
        mainPlatform.setUrl(myURL.getHostName());
        mainPlatform.setPort(myURL.getPort());
        mainPlatform.setHttp4mtp(myURL.getJADEurl4MTP());

        // --- Define Receiver of local Status-Info -------
        mainPlatformAgent = new AID("server.master" + "@" + myURL.getJADEurl(), AID.ISGUID);
        mainPlatformAgent.addAddresses(mainPlatform.getHttp4mtp());

        // --- Set myTime ---------------------------------
        myPlatformTime.setTimeStampAsString(Long.toString(System.currentTimeMillis()));

        // --- Send 'Register'-Information ----------------
        myRegistration.setSlaveAddress(myPlatform);
        myRegistration.setSlaveTime(myPlatformTime);
        myRegistration.setSlavePerformance(myPerformance);
        myRegistration.setSlaveOS(myOS);
        this.sendMessage2MainServer(myRegistration);

        // --- Add Main-Behaviours -----------------------
        parBehaiv = new ParallelBehaviour(this, ParallelBehaviour.WHEN_ALL);
        parBehaiv.addSubBehaviour(new MessageReceiveBehaviour());
        trigger = new TriggerBehaiviour(this, triggerTime);
        parBehaiv.addSubBehaviour(trigger);
        sndBehaiv = new SaveNodeDescriptionBehaviour(this, 500);
        parBehaiv.addSubBehaviour(sndBehaiv);

        // --- Add Parallel Behaiviour --------------------
        this.addBehaviour(parBehaiv);


    }

    /* (non-Javadoc)
     * @see jade.core.Agent#takeDown()
     */
    @Override
    protected void takeDown() {
        super.takeDown();

        // --- Stop Parallel-Behaiviour -------------------
        this.removeBehaviour(parBehaiv);

        // --- Send 'Unregister'-Information --------------
        SlaveUnregister unReg = new SlaveUnregister();
        sendMessage2MainServer(unReg);


    }

    /**
     * This method sends a message to the ServerMasterAgent.
     *
     * @param agentAction the agent action
     * @return true, if successful
     */
    private boolean sendMessage2MainServer(Concept agentAction) {

        try {
            // --- Definition einer neuen 'Action' --------
            Action act = new Action();
            act.setActor(getAID());
            act.setAction(agentAction);

            // --- Nachricht zusammenbauen und ... --------
            ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
            msg.setSender(getAID());
            msg.addReceiver(mainPlatformAgent);
            msg.setLanguage(codec.getName());
            msg.setOntology(ontology.getName());

            // --- ... versenden --------------------------
            getContentManager().fillContent(msg, act);
            send(msg);
            return true;

        } catch (CodecException | OntologyException e) {
            logger.error(e);
            return false;
        }

    }

    /**
     * The TriggerBehaiviour of the agents, which informs the ServerMasterAgent
     * about the current system state and load.
     */
    private class TriggerBehaiviour extends TickerBehaviour {

        private static final long serialVersionUID = -1701739199514787426L;

        /**
         * Instantiates a new trigger behaiviour.
         *
         * @param agent the agent
         * @param period the period
         */
        public TriggerBehaiviour(Agent agent, long period) {
            super(agent, period);
        }
        /* (non-Javadoc)
         * @see jade.core.behaviours.TickerBehaviour#onTick()
         */

        @Override
        protected void onTick() {
            // --- Current Time ---------------------------------
            myPlatformTime.setTimeStampAsString(Long.toString(System.currentTimeMillis()));

            if (mainPlatformReachable == false) {
                myRegistration.setSlaveTime(myPlatformTime);

                // --- Send Message ---------------------------------
                sendMessage2MainServer(myRegistration);

            } else {

                SlaveTrigger trig = new SlaveTrigger();

                // --- Current Time ---------------------------------
                trig.setTriggerTime(myPlatformTime);

                // --- get current Load-Level -----------------------
                myLoad.setLoadCPU(LoadMeasureThread.getLoadCPU());
                myLoad.setLoadMemorySystem(LoadMeasureThread.getLoadMemorySystem());
                myLoad.setLoadMemoryJVM(LoadMeasureThread.getLoadMemoryJVM());
                myLoad.setLoadNoThreads(LoadMeasureThread.getLoadNoThreads());
                myLoad.setLoadExceeded(LoadMeasureThread.getThresholdLevelExceeded());
                trig.setSlaveLoad(myLoad);

                // --- get Current Benchmark-Result -----------------
                BenchmarkResult bmr = new BenchmarkResult();
                bmr.setBenchmarkValue(LoadMeasureThread.getCompositeBenchmarkValue());
                trig.setSlaveBenchmarkValue(bmr);

                // --- Send Message ---------------------------------
                sendMessage2MainServer(trig);
            }
        }
    }
    /**
     * The Class MessageReceiveBehaviour of this agent.
     */
    private class MessageReceiveBehaviour extends CyclicBehaviour {

        private static final long serialVersionUID = -1701739199514787426L;

        /* (non-Javadoc)
         * @see jade.core.behaviours.Behaviour#action()
         */
        @Override
        public void action() {

            Action act ;
            Concept agentAction ;
            @SuppressWarnings("unused")
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                act = null;
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
                      logger.error("Server.Master not reachable! Try to reconnect in " + (triggerTime4Reconnection / 1000) + " seconds ...");
                        trigger.reset(triggerTime4Reconnection);
                        mainPlatformReachable = false;
                    } else {
                       logger.error("ACLMessage.FAILURE from " + msg.getSender().getName() + ": " + msg.getContent());
                    }

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

                    // ------------------------------------------------------------------
                    // --- Different cases of AgentAction --- S T A R T -----------------
                    // ------------------------------------------------------------------
                    if (agentAction instanceof ClientRemoteContainerRequest) {

                        ClientRemoteContainerRequest crcr = (ClientRemoteContainerRequest) agentAction;
                        startRemoteContainer(crcr.getRemoteConfig());

                    } else if (agentAction instanceof RegisterReceipt) {
                        logger.info("Server.Master (re)connected!");
                        sendNotReachable = 0;
                        mainPlatformReachable = true;
                        trigger.reset(triggerTime);

                    } else if (agentAction instanceof MasterUpdateNote) {
                        logger.info("Server.Master (re)connected, but call for an update!");
                        MasterUpdateNote masterUpdateNote = (MasterUpdateNote) agentAction;
                        String updateInfoURL = masterUpdateNote.getUpdateInfoURL();
                        logger.info("Download Update-Information: " + updateInfoURL);


                    } else if (agentAction instanceof ClientUnregister) {
                        try {
                            ClientUnregister unr = (ClientUnregister) agentAction;
                            logger.info("Try to kill " + unr.getClientAddress());
                            (new ContainerKiller(unr.getClientAddress())).start();
                        } catch (Exception e) {
                            logger.error(e);
                        }

                    }
                }
            } else {
                block();
            }
        }
    }
    // -----------------------------------------------------
    // --- Message-Receive-Behaiviour --- E N D ------------
    // -----------------------------------------------------

    /**
     * Starts a Remote-Container for given RemoteContainerConfig-Instance.
     *
     * @param remoteContainerConfig the remote container configuration
     */
    private void startRemoteContainer(RemoteContainerConfig remoteContainerConfig) {

        try {
           logger.info("Starting remote container ... ");
            portIndex++;
            new JadeRemoteStart(remoteContainerConfig, portIndex).start();


        } catch (Exception e) {
          logger.error(e);
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
                try {
                    BenchmarkResult bench = new BenchmarkResult();
                    bench.setBenchmarkValue(LoadMeasureThread.getCompositeBenchmarkValue());
                    myCRCreply.setRemoteBenchmarkResult(bench);

                    LoadServiceHelper simHelper = (LoadServiceHelper) getHelper(LoadService.NAME);
                    simHelper.putContainerDescription(myCRCreply);
                    simHelper.setAndSaveCRCReplyLocal(myCRCreply);

                } catch (ServiceException e) {
                   logger.error(e);
                }
                this.stop();
            }
        }
    }
}
