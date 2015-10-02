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
package org.gaia.dao.jade;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.Calendar;
import java.util.HashMap;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.PositionTree.PositionNode;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.AgentCreation;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;
import org.gaia.simulationService.ontology.DistributionOntology;

/**
 *
 * @author Benjamin
 */
public class ReportLauncherAgent extends GuiAgent implements GaiaVocabulary {

    private ReportSettings reportSetting;
    private PositionTree.PositionNode root = null;
    private IRefreshableWindow UI;
    public static final int LAUNCH_REPORT = 0;
    public static final int REFRESH_REPORT = 1;
    private AID reportAgentAID = null;
    private XStream xstream;
    private Ontology ontology = DistributionOntology.getInstance();
    private Codec codec = new SLCodec();
    private static final Logger logger = Logger.getLogger(ReportLauncherAgent.class);
    private long delay;

    public ReportLauncherAgent() {
        super();
        try {
        xstream = new XStream(new StaxDriver());
        } catch (Exception e) {
        }
    }

    @Override
    protected void setup() {
        getContentManager().registerLanguage(codec);
        getContentManager().registerOntology(ontology);
        addBehaviour(new ReplyListener());
    }

    @Override
    protected void takeDown() {
        /**
         * Printout a dismissal message
         */
        logger.info("Report Launcher agent " + getAID().getName() + " terminating.");
    }

    @Override
    protected void onGuiEvent(GuiEvent ge) {
        int command = ge.getType();
        if (command == LAUNCH_REPORT) {
            addBehaviour(new LaunchReport(reportSetting));
        }
    }

    public class LaunchReport extends OneShotBehaviour {

        public LaunchReport(ReportSettings rset) {
            super();
            reportSetting = rset;
        }

        @Override
        public void action() {
            if (reportAgentAID == null) {
                GaiaMobilityAgent.sendAgentCreationrequest(ReportAgent.class,null, myAgent);
            } else {
                sendLaunchRequest();
            }
        }
    }

    public void sendLaunchRequest() {

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        try {
            request.addReceiver(reportAgentAID);
            request.setSender(getAID());
            request.setConversationId(REPORT_REQUEST);
            request.setContentObject(reportSetting);
            logger.info("REPORT STARTED AT " + DateUtils.getTime());
            delay=Calendar.getInstance().getTimeInMillis();
            send(request);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void sendStopRealTimeRequest() {

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        try {
            request.addReceiver(reportAgentAID);
            request.setSender(getAID());
            request.setConversationId(STOP_REAL_TIME);
            request.setContentObject(reportSetting);
            logger.info("REPORT STARTED AT " + DateUtils.getTime());
            delay=Calendar.getInstance().getTimeInMillis();
            send(request);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void sendCloseRequest() {

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
        try {
            request.addReceiver(reportAgentAID);
            request.setSender(getAID());
            request.setContent(this.getAID().getName());
            request.setConversationId(CLIENT_SHUTDOWN);
            logger.info("REPORT STOPPED AT " + DateUtils.getTime());
            send(request);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    private class ReplyListener extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                try {
                    logger.debug(msg.getConversationId() + " received at " + DateUtils.getTime());
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        switch (msg.getConversationId()) {
                            case REPORT_REPLY:
                                String xml = (String) msg.getContentObject();
                                if (!StringUtils.isEmptyString(xml)){
                                    root = (PositionTree.PositionNode) xstream.fromXML(xml);
                                }
                                UI.refresh(root);
                                if (!reportSetting.isRealTime()){
                                    logger.info("REPORT ENDED AT " + DateUtils.getTime());
                                    double delayInSec=Calendar.getInstance().getTimeInMillis()-delay;
                                    logger.info("REPORT DURATION " + delayInSec/1000);
                                }
                                break;
                            case GAIAAID:
                                AgentCreation ac = (AgentCreation)msg.getContentObject();
                                String agentName = ac.getAgentName();
                                reportAgentAID=new AID(agentName, AID.ISLOCALNAME);
                                sendLaunchRequest();
                                break;
                            case OBSERVABLES_REPLY:
                                HashMap<Integer, String> observables = (HashMap) msg.getContentObject();
                                UI.refresh(observables);
                                break;
                        }
                    } else {
                        if (msg.getConversationId().equals(REPORT_REPLY)) {
                            UI.refresh(root);
                        }
                        logger.error(this.toString() + StringUtils.SPACE + msg.getContent());
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            } else {
                block();
            }
        }
    }

    public void setReportSettings(ReportSettings rs) {
        this.reportSetting = rs;
    }

    public PositionNode getRoot(PositionNode root) {
        return this.root;
    }

    public void setUI(IRefreshableWindow UI) {
        this.UI = UI;
    }
}
