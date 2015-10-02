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
package org.gaia.dao.jade;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.MessageTemplate.MatchExpression;
import jade.lang.acl.UnreadableException;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportLine;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionHistory;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import static org.gaia.jade.ontology.GaiaMobilityAgent.sendAgentCreationrequest;
import org.gaia.jade.ontology.GaiaVocabulary;
import static org.gaia.jade.ontology.GaiaVocabulary.FORCE_REPORT_REPLY;
import org.gaia.simulationService.ontology.ReportObjectNotification;

/**
 *
 * @author Benjamin

 The ReportAgent is responsible for calculating a report It is linked to a
 user report window Main functionalities are hold by the ReportServer
 behaviour
 *
 */
public class ReportAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private PositionTree.PositionNode root;
    private ReportSettings reportSettings;
    private ReportBuilder reportBuilder;
    private final XStream xstream;
    private Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTrade;
    private ACLMessage reportRequest;
    private Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricerResultsByTradeFirstDate;
    private static final Logger logger = Logger.getLogger(ReportAgent.class);
    private long lastUpdateTime = 0;
    private Boolean isRunning = false;
    private long TIMEOUT = 5000; // in milliseconds
    private LinkedHashMap<Integer, ReportLine> lineList;
    private List<IObservable> forexRates = new ArrayList();
    private Map<String, BigDecimal> mapFXEnd = new HashMap();
    private Map<String, BigDecimal> mapFXStart = new HashMap();

    public ReportAgent(AID aid, jade.util.leap.List args) {
        super(aid, null);
        xstream = new XStream(new StaxDriver());
    }

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new ReportServer());
        addBehaviour(new ForceAfterTimeOut(this, 1000));
        registerService(ReportAgent.class.getSimpleName(), ReportAgent.class.getSimpleName(), this);
    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }

    /**
     * ReportServer runs the report on demand and reply to the enquirer.
     * It  receives these messages:
     * REPORT_REQUEST : request for a report calculation => fills static fields and asks for object list.
     * OBJECTS_REPLY : object list reply => launch pricers and asks for forex rates.
     * MARKET_DATA_REPLY : forex rates reply => stores forex rates.
     * PRICING_REPLY : pricing reply => stores pricings
     * => if complete, proceeds with final report treatments and sends result to client.
     * REPORT_OBJECT_CHANGE_NOTIFY : object change notification => looks if the
     * report is concened by the object => modify result if needed and sends new
     * result to client
     * PRICE_CHANGE : price change notification => concerned lines have to be refreshed
     *
     */
    private class ReportServer extends CyclicBehaviour {

        private AID marketDataAgent;
        private AID marketDataAgentFirstDate;
        private PricingSetting pricingSettings;
        private AID clientAID;
        private Map<String, List<ReportLine>> additionalReportData = new HashMap();
        private final MessageTemplate messageTemplate = new MessageTemplate(new MatchByConversationId(PRICING_REPLY));
        private final SimpleDateFormat dateFormat = (SimpleDateFormat) HibernateUtil.dateFormat.clone();
        private final int millsecByLine = 300;
        private final int millsecMinimumTimeout = 2000;


        @Override
        public synchronized void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                /**
                 * Message received. Process it
                 */
                try {
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        switch (msg.getConversationId()) {
                            case REPORT_REQUEST:
                                logger.info("REPORT QUERY RECEIVED AT " + DateUtils.getTime());
                                if (!isRunning) {
                                    isRunning = true;
                                    lastUpdateTime = Calendar.getInstance().getTimeInMillis();
                                    reportRequest = msg;
                                    if (clientAID == null) {
                                        clientAID = msg.getSender();
                                    }

                                    Serializable msgContent = msg.getContentObject();
                                    reportSettings = (ReportSettings) msgContent;
                                    reportBuilder = new ReportBuilder();
                                    pricerResultsByTrade = new HashMap();
                                    mapFXEnd = new HashMap();
                                    mapFXStart = new HashMap();
                                    pricerResultsByTradeFirstDate = new HashMap();
                                    if (reportSettings.isRealTime() && reportSettings.getValDate().before(DateUtils.getDate())) {
                                        reportSettings.setIsRealTime(false);
                                    }
                                    lineList = null;
                                    /**
                                     * get objects from report
                                     */
                                    try {
                                        lineList = reportBuilder.fillReportLines(lineList, reportSettings.getTemplate(), reportSettings.getValDate(), false, reportSettings.getTemplate().isFundLookThrough());
                                        reportSettings.getTemplate().setTemplateColumnItems(reportBuilder.getReportTemplate().getTemplateColumnItems());
                                        if (reportSettings.hasFirstDate()) {
                                            lineList = reportBuilder.fillReportLines(lineList, reportSettings.getTemplate(), reportSettings.getFirstDate(), true, reportSettings.getTemplate().isFundLookThrough());
                                        }
                                        // if empty
                                        if (lineList == null || lineList.isEmpty()) {
                                            root = new PositionTree().new AggregNode(null);
                                            sendReply();
                                            return;
                                        }
                                        TIMEOUT = lineList.size() * millsecByLine + millsecMinimumTimeout;
//                                        logger.error("TIMEOUT="+TIMEOUT);
                                    } catch (Exception e) {
                                        sendErrorReply(" looking for data " + StringUtils.formatErrorMessage(e));
                                        logger.error(" looking for data " + StringUtils.formatErrorMessage(e));
                                    }
                                    logger.info("DATABASE QUERY RETURNED AT " + DateUtils.getTime());
                                    try {
                                        /**
                                         * case when no pricing is needed : for observable lookup
                                         */
                                        if (!reportSettings.hasPricing()) {
                                            fillForexRatesDirectly();

                                            root = reportBuilder.buildOutPutTree(lineList, reportSettings, null, null, null);
                                            sendReply();
                                        } else {
                                            PricingEnvironment pricingEnvironment = PricingEnvironmentAccessor.getPricingEnvironmentFromName(reportSettings.getPricingEnv());
                                            Class clazz = reportBuilder.getObjectClass();
                                            if (reportSettings.isJustNeededObservables()) {
                                                /**
                                                 * case when no report is needed just the list of observables linked to trade/positions
                                                 */
                                                HashMap<Integer, String> observables = new HashMap();
                                                for (ReportLine line : lineList.values()) {
                                                    IPriceable priceable = (IPriceable) HibernateUtil.getObject(clazz, line.getId());
                                                    MeasuresAccessor.MeasureGroup[] measureGroups = null;
                                                    /**
                                                     * load pricer map
                                                     */
                                                    Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap = PricingBuilder.loadPricerMap(priceable, reportSettings.getValDate(), pricingEnvironment, measureGroups);

                                                    /**
                                                     * load needed list
                                                     */
                                                    Map<MeasureGroup, Map<Integer, IObservable>> groupObservables = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);
                                                    for (Map<Integer, IObservable> observablesListById : groupObservables.values()) {
                                                        for (IObservable obs : observablesListById.values()) {
                                                            if (observables.get(obs.getProductId()) == null) {
                                                                observables.put(obs.getProductId(), StringUtils.EMPTY_STRING);
                                                            }
                                                        }
                                                    }
                                                }

                                                ACLMessage reply = reportRequest.createReply();
                                                reply.setContentObject(observables);
                                                reply.setConversationId(OBSERVABLES_REPLY);
                                                myAgent.send(reply);

                                            } else {

                                                /**
                                                 * REAL REPORT PRICING LAUNCH
                                                 * =============================
                                                 */
                                                pricerResultsByTrade = new HashMap();
                                                pricerResultsByTradeFirstDate = new HashMap();
                                                /**
                                                 * launch market data agents at
                                                 * given dates
                                                 */
                                                String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
                                                /**
                                                 * value date
                                                 */
                                                marketDataAgent = MarketDataAgent.getMarketDataAgent(quoteSet, reportSettings.getValDate(), reportSettings.isRealTime(), myAgent);

                                                /**
                                                 * start date
                                                 */
                                                if (reportSettings.hasFirstDate()) {
                                                    marketDataAgentFirstDate = MarketDataAgent.getMarketDataAgent(quoteSet, reportSettings.getFirstDate(), false, myAgent);
                                                }

                                                /**
                                                 * launch pricing
                                                 * build object PricingSetting passed to pricer agent
                                                 */
                                                pricingSettings = PricingBuilder.buildPricingSetting(reportSettings.getTemplate().getTemplateColumnItems(), pricingEnvironment, reportSettings.getValDate(), reportSettings.isRealTime());

                                                /**
                                                 * send PricerAgent creation request
                                                 */
                                                for (ReportLine line : lineList.values()) {
                                                    /**
                                                     * initialize result map
                                                     */
                                                    pricerResultsByTrade.put(line.getId(), null);
                                                    /**
                                                     * launch pricing agents
                                                     */
                                                    jade.util.leap.List argList = GaiaPricerAgent.buildArguments(line.getId(), clazz, pricingSettings);
                                                    sendAgentCreationrequest(GaiaPricerAgent.class, argList, myAgent);
                                                }
                                                /**
                                                 * same thing in D-1
                                                 */
                                                if (reportSettings.hasFirstDate()) {
                                                    pricerResultsByTradeFirstDate = new HashMap();
                                                    /**
                                                     * create pricing agents
                                                     */
                                                    pricingSettings.setValuationDate(reportSettings.getFirstDate());

                                                    for (ReportLine line : lineList.values()) {
                                                        /**
                                                         * initialize result map
                                                         */
                                                        pricerResultsByTradeFirstDate.put(line.getId(), null);
                                                        /**
                                                         * launch pricing agents
                                                         */
                                                        jade.util.leap.List argList = GaiaPricerAgent.buildArguments(line.getId(), clazz, pricingSettings);
                                                        sendAgentCreationrequest(GaiaPricerAgent.class, argList, myAgent);
                                                    }
                                                }
                                                logger.info("PRICING LAUNCHED at " + DateUtils.getTime());

                                                /**
                                                 * now prepare currency
                                                 * conversions. first fill a
                                                 * list with needed forex rates
                                                 */
                                                fillForexRatesAndSendRequest(marketDataAgent, marketDataAgentFirstDate);

                                            }
                                        }
                                        lastUpdateTime = Calendar.getInstance().getTimeInMillis();

                                    } catch (Exception e) {
                                        sendErrorReply(StringUtils.formatErrorMessage(e));
                                        logger.error(StringUtils.formatErrorMessage(e));
                                    }
                                } else {
                                    logger.error("The report is already running");
                                }
                                break;
                            case MARKET_DATA_REPLY:
                                // reception of forex rates

                                MarketQuoteObservable msgObservable = (MarketQuoteObservable) msg.getContentObject();
                                manageForexRate(msgObservable);
                                break;
                            case PRICING_REPLY:
                            case FORCE_REPORT_REPLY:
                                lastUpdateTime = Calendar.getInstance().getTimeInMillis();
                                int noPricingsNotDone = 0;
                                boolean allPricingsDone = true;
                                boolean forexRateChange = false;
                                boolean force = false;
                                List<ReportLine> alreadySetLines = new ArrayList();
                                PricerResult pricerResult;
                                Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> pricingResult = null;
                                Serializable content;

                                try {

                                    do {    // read all pricing reply messages
                                        content = msg.getContentObject();
                                        if (content == null) {
                                            forexRateChange = true;
                                        } else {
                                            pricerResult = (PricerResult) content;
                                            Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> messageContent = pricerResult.getMapWithDate();
                                            pricingResult = messageContent.get(reportSettings.getValDate());

                                            // fill object
                                            if (pricingResult != null && lineList.get(pricerResult.getId()) != null) {
                                                if (lineList.get(pricerResult.getId()).getLineData() != null) {
                                                    alreadySetLines.add(lineList.get(pricerResult.getId()));
                                                    logger.debug("refresh id " + pricerResult.getId());
                                                }
                                                lineList.get(pricerResult.getId()).setLineData(pricerResult.getLineData());
                                            } else if (reportSettings.getTemplate().getObjectTypeClass().equals(Position.class) && lineList.get(pricerResult.getId()) != null) {
                                                // on positions : if on start date complete the object
                                                Position position = (Position) lineList.get(pricerResult.getId()).getLineData();
                                                Position positionFirst = (Position) pricerResult.getLineData();
                                                if (position != null && positionFirst != null && positionFirst.getPositionHistoryCollection() != null && positionFirst.getPositionHistoryCollection().size() > 0) {
                                                    position.getPositionHistoryCollection().add(positionFirst.getPositionHistoryCollection().iterator().next());
                                                    lineList.get(pricerResult.getId()).setLineData(position);
                                                } else if (position == null && positionFirst != null) {
                                                    lineList.get(pricerResult.getId()).setLineData(positionFirst);
                                                } else if (position == null && positionFirst == null) {
                                                    lineList.remove(pricerResult.getId());
                                                }
                                            }

                                            // fill pricer results
                                            if (pricingResult != null) {
                                                pricerResultsByTrade.putAll(pricingResult);
                                            } else {
                                                pricingResult = messageContent.get(reportSettings.getFirstDate());
                                                if (pricingResult != null) {
                                                    pricerResultsByTradeFirstDate.putAll(pricingResult);
                                                }
                                            }
                                        }
                                        if (msg.getConversationId().equalsIgnoreCase(FORCE_REPORT_REPLY)) {
                                            force = true;
                                        }
                                        msg = myAgent.receive(messageTemplate);
                                    } while (msg != null);

                                    /**
                                     * look if all pricing are done
                                     */
                                    for (Map<IPricerMeasure, IMeasureValue[]> measure : pricerResultsByTrade.values()) {
                                        if (measure == null) {
                                            noPricingsNotDone++;
                                            allPricingsDone = false;
                                        }
                                    }
                                    TIMEOUT = noPricingsNotDone * millsecByLine + millsecMinimumTimeout;
//                                    logger.error("TIMEOUT="+TIMEOUT);
                                    for (BigDecimal forexRate : mapFXEnd.values()) {
                                        if (forexRate == null) {
                                            allPricingsDone = false;
                                        }
                                    }
                                    if (reportSettings.hasFirstDate()) {
                                        for (Map<IPricerMeasure, IMeasureValue[]> measure : pricerResultsByTradeFirstDate.values()) {
                                            if (measure == null) {
                                                allPricingsDone = false;
                                            }
                                        }
                                        for (BigDecimal forexRate : mapFXStart.values()) {
                                            if (forexRate == null) {
                                                allPricingsDone = false;
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    logger.error(StringUtils.formatErrorMessage(e));
                                    allPricingsDone = false;
                                }
//                                logNotPriced();
                                if (allPricingsDone || force) { // we can finish the process
                                    try {
                                        if (alreadySetLines.size() > 0 && !forexRateChange && root!=null) {
                                            // case when only one line changed
                                            ReportBuilder.mergeColumns(lineList, pricingResult, false);
                                            ReportBuilder.calculatePostTreatments(lineList, pricerResultsByTrade, reportSettings.getValDate(), mapFXEnd, additionalReportData, false, reportSettings);
                                            if (reportSettings.hasFirstDate()) {
                                                ReportBuilder.calculatePostTreatments(lineList, pricerResultsByTrade, reportSettings.getFirstDate(), mapFXStart, additionalReportData, true, reportSettings);
                                            }
                                            String ids = StringUtils.EMPTY_STRING;
                                            List idList = new ArrayList();
                                            for (ReportLine alreadySetLine : alreadySetLines) {
                                                reportBuilder.fillLine(alreadySetLine, root, reportSettings.hasFirstDate(), reportSettings.getCurrency(), reportSettings.getValDate(), reportSettings.getFirstDate(), mapFXEnd, mapFXStart);
                                                ReportBuilder.aggregationsCalculations(root, reportSettings.getTemplate().getTemplateColumnItems());
                                                ids += alreadySetLine.getId() + StringUtils.COMMA;
                                                idList.add(alreadySetLine.getId());
                                            }
                                            ids = ids.substring(0, ids.length() - 1);
                                            ReportBuilder.setHiddenObject(idList, root);
                                            logger.info("Refresh " + ids);
                                        } else {
                                            // calculate all end of report treatments
                                            logger.info("PRICING ENDED AT " + DateUtils.getTime());
                                            additionalReportData = new HashMap();
                                            reportBuilder.addReportData(lineList, reportSettings, mapFXEnd, mapFXStart);
                                            ReportBuilder.mergeColumns(lineList, pricerResultsByTrade, false);
                                            ReportBuilder.calculatePostTreatments(lineList, pricerResultsByTrade, reportSettings.getValDate(), mapFXEnd, additionalReportData, false, reportSettings);
                                            if (reportSettings.hasFirstDate()) {
                                                ReportBuilder.mergeColumns(lineList, pricerResultsByTradeFirstDate, true);
                                                ReportBuilder.calculatePostTreatments(lineList, pricerResultsByTradeFirstDate, reportSettings.getFirstDate(), mapFXStart, additionalReportData, true, reportSettings);
                                            }
                                            root = reportBuilder.buildOutPutTree(lineList, reportSettings, mapFXEnd, mapFXStart, additionalReportData);
                                            if (force){
                                                ReportBuilder.setHiddenObject("", root);
                                            } else {
                                                ReportBuilder.setHiddenObject("Id", root);
                                            }
                                            logger.info("REPORT REPLY SENT AT " + DateUtils.getTime() + " to " + clientAID.getLocalName());
                                        }
                                    } catch (Exception e) {
                                        logger.error(StringUtils.formatErrorMessage(e));
                                    }
                                    sendReply();
                                }
                                lastUpdateTime = Calendar.getInstance().getTimeInMillis();
                                break;

                            // END OF TREATEMENT
                            // ==========================

                            case STOP_REAL_TIME:
                                // if I stop real time I have to notify pricer agents
                                try {
                                    int i = 0;
                                    while (isRunning && i < TIMEOUT) {
                                        //waiting the end of treatment
                                        Thread.sleep(1000);
                                        logger.info("Waiting end of report");
                                        i++;
                                    }
                                    // on forex rates I notify market data agents
                                    for (IObservable forexRate : forexRates) {
                                        ACLMessage newMessage = new ACLMessage(ACLMessage.REQUEST);
                                        newMessage.addReceiver(marketDataAgent);
                                        newMessage.setSender(myAgent.getAID());
                                        newMessage.setConversationId(STOP_REAL_TIME);
                                        newMessage.setContentObject(forexRate.getProductId());
                                        myAgent.send(newMessage);
                                        logger.info("Notify " + marketDataAgent.getLocalName() + " for end of RT on " + forexRate.getProduct().getShortName() + "  " + forexRate.getProductId());
                                    }
                                    for (ReportLine line : lineList.values()) {
                                        DFAgentDescription[] agentDescs = GaiaPricerAgent.findPricerAgents(line.getId(), reportSettings.getValDate(), myAgent.getAID(), myAgent);
                                        if (agentDescs.length > 0) {
                                            ACLMessage newMessage = new ACLMessage(ACLMessage.REQUEST);
                                            for (DFAgentDescription dfd : agentDescs) {
                                                newMessage.addReceiver(dfd.getName());
                                            }
                                            newMessage.setSender(myAgent.getAID());
                                            newMessage.setConversationId(STOP_REAL_TIME);
                                            newMessage.setContentObject(myAgent.getAID());
                                            myAgent.send(newMessage);
                                        }
                                    }
                                } catch (Exception e) {
                                    logger.error(StringUtils.formatErrorMessage(e));
                                }
                                break;
                            case REPORT_OBJECT_CHANGE:
                                /**
                                 * Notification when a trade is updated/created
                                 * for real time position the report must be
                                 * updated
                                 */
                                ReportObjectNotification reportObjectNotification = null;
                                try {
                                    reportObjectNotification = (ReportObjectNotification) msg.getContentObject();
                                    /**
                                     * if the report is concerned, it has to be
                                     * refreshed.
                                     */
                                    if (reportSettings.getTemplate().getObjectTypeClass().equals(reportObjectNotification.getObjectType()) && lineList != null) {

                                        int incomingId = reportObjectNotification.getId().intValue();
                                        /**
                                         * if it is an existing object, lookup if it is part of current report and
                                         * updates it if it is
                                         */
                                        ReportLine line = lineList.get(incomingId);
                                        if (line != null) {
                                            /**
                                             * positions
                                             */
                                            if (reportSettings.getTemplate().getObjectTypeClass().equals(Position.class) && reportObjectNotification.getPositionConfigurationId().equals(reportSettings.getTemplate().getPositionConfiguration().getPositionConfigurationId())) {
                                                /**
                                                 * get the new position.
                                                 */
                                                Position position;
                                                if (reportSettings.hasFirstDate()) {
                                                    position = PositionBuilder.getPositionAndTwoPositionHistories(incomingId, reportSettings.getValDate(), reportSettings.getFirstDate());
                                                    if (position != null) {
                                                        PositionHistory posHistory=position.getPositionHistory(reportSettings.getValDate());
                                                        if (posHistory!=null){
                                                        line.getObjectMap().put("PositionHistory.Quantity", posHistory.getQuantity());
                                                        }
                                                        posHistory=position.getPositionHistory(reportSettings.getFirstDate());
                                                        if (posHistory!=null){
                                                            line.getObjectMapFirst().put("PositionHistory.Quantity", posHistory.getQuantity());
                                                        }
                                                    }
                                                } else {
                                                    position = PositionBuilder.getPositionAndPositionHistory(incomingId, reportSettings.getValDate());
                                                    if (position != null) {
                                                        PositionHistory posHistory=position.getPositionHistory(reportSettings.getValDate());
                                                        if (posHistory!=null){
                                                            line.getObjectMap().put("PositionHistory.Quantity", posHistory.getQuantity());
                                                        }
                                                    }
                                                }
                                                line.setLineData(position);
                                                /**
                                                 * trades
                                                 */
                                            } else if (reportSettings.getTemplate().getObjectTypeClass().equals(Trade.class)) {
                                                if (reportObjectNotification.isCancel()) {
                                                    lineList.remove(incomingId);
                                                } else {
                                                    Trade trade = TradeAccessor.getTradeById(incomingId);
                                                    if (FilterAccessor.isInFilter(trade, reportSettings.getTemplate().getFilter())) {
                                                        line.getObjectMap().put("Quantity", trade.getQuantity());
                                                        line.setLineData(trade);
                                                    } else {
                                                        lineList.remove(incomingId);
                                                    }
                                                }
                                                /**
                                                 * flows
                                                 */
                                            } else if (reportSettings.getTemplate().getObjectTypeClass().equals(Flow.class)) {
                                                Flow flow = FlowAccessor.getFlowById(incomingId);
                                                if (FilterAccessor.isInFilter(flow, reportSettings.getTemplate().getFilter())) {
                                                    line.getObjectMap().put("Quantity", flow.getQuantity());
                                                    line.setLineData(flow);
                                                } else {
                                                    lineList.remove(incomingId);
                                                }
                                            }

                                            /**
                                             * launch pricing agents.
                                             */
                                            PricingSetting setting_= pricingSettings.clone();
                                            setting_.setIsRealTime(false);//one pricing only: in case of real time, the agent already exists
                                            jade.util.leap.List argList = GaiaPricerAgent.buildArguments(line.getId(), reportBuilder.getObjectClass(), pricingSettings);
                                            sendAgentCreationrequest(GaiaPricerAgent.class, argList, myAgent);
                                            if (reportSettings.hasFirstDate()) {
                                                setting_.setValuationDate(reportSettings.getFirstDate());
                                                pricerResultsByTradeFirstDate.put(line.getId(), null);
                                                sendAgentCreationrequest(GaiaPricerAgent.class, argList, myAgent);
                                            }
                                            isRunning = true;


                                        } else {
                                            /**
                                             * new object : looks if the report
                                             * dates are impacted
                                             */
                                            if (!StringUtils.isEmptyString(reportObjectNotification.getFromDate())
                                                    && !dateFormat.parse(reportObjectNotification.getFromDate()).after(reportSettings.getValDate())
                                                    && !(reportSettings.getTemplate().getObjectTypeClass().equals(Position.class)
                                                        && !reportObjectNotification.getPositionConfigurationId().equals(reportSettings.getTemplate().getPositionConfiguration().getPositionConfigurationId()))) {
                                                /**
                                                 * if it is a new object lookup
                                                 * if it enters the filter and
                                                 * relaunch the report if it does
                                                 */

                                                IPriceable priceable = ReportBuilder.getPriceable(reportObjectNotification.getId(), reportObjectNotification.getObjectType().getName(), reportSettings.getValDate(), reportSettings.getFirstDate());

                                                if (priceable == null || FilterAccessor.isInFilter(priceable, reportSettings.getTemplate().getFilter())) {
                                                    ACLMessage newMessage = new ACLMessage(ACLMessage.REQUEST);
                                                    newMessage.addReceiver(myAgent.getAID());
                                                    newMessage.setSender(myAgent.getAID());
                                                    newMessage.setConversationId(REPORT_REQUEST);
                                                    newMessage.setContentObject(reportSettings);
                                                    isRunning = false;
                                                    myAgent.send(newMessage);
                                                }
                                            }
                                        }
                                    }
                                } catch (Exception e) {
                                    logger.error("ERROR on report notification " + reportObjectNotification.getObjectType().getName() + " id " + reportObjectNotification.getId() + " on " + reportObjectNotification.getFromDate());
                                    logger.error(StringUtils.formatErrorMessage(e));
                                }
                                break;
                            case PRICE_CHANGE:
                                //manage forex rates real time changes
                                msgObservable = (MarketQuoteObservable) msg.getContentObject();
                                manageForexRate(msgObservable);
                                break;
                            case CLIENT_SHUTDOWN:
                                String client = msg.getContent();
                                if (client != null && client.equalsIgnoreCase(myAgent.getAID().getName())) {
                                    myAgent.doDelete();
                                }
                                break;
                        }
                    } else {
                        if (msg.getConversationId().equals(OBJECTS_REPLY)) {
                            ACLMessage reply = reportRequest.createReply();
                            reply.setContentObject(null);
                            reply.setPerformative(ACLMessage.FAILURE);
                            reply.setConversationId(REPORT_REPLY);
                            myAgent.send(reply);
                        }
                        logger.debug(msg.getConversationId() + " received at " + DateUtils.getTime());
                    }
                } catch (UnreadableException | IOException | NullPointerException e) {
                    logger.fatal(StringUtils.formatErrorMessage(e));
                }
            } else {
                block();
            }
        }

        public void sendReply() {
            try {
                isRunning = false;
                ACLMessage reply = new ACLMessage(ACLMessage.REQUEST);
                reply.setSender(myAgent.getAID());
                reply.addReceiver(clientAID);
                reply.setContentObject(xstream.toXML(root));
                reply.setConversationId(REPORT_REPLY);
                myAgent.send(reply);
//                logger.info(reply.getConversationId() + " sent at " + DateUtils.getTime() + " to " + reportRequest.getSender().getName());
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }

        public void sendErrorReply(String message) {
            try {
                ACLMessage reply = new ACLMessage(ACLMessage.REQUEST);
                reply.setSender(myAgent.getAID());
                reply.addReceiver(clientAID);
                reply.setContentObject(null);
                reply.setConversationId(REPORT_REPLY);
                myAgent.send(reply);
                logger.info(reply.getConversationId() + " sent at " + DateUtils.getTime() + " to " + reportRequest.getSender().getName());
                isRunning = false;
            } catch (Exception e) {
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
        }
    }

    public void sendMarketDataRequest(AID marketDataAgent, AbstractObservable observable) {
        try {
            ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
            request.addReceiver(marketDataAgent);
            request.setSender(this.getAID());
            request.setConversationId(MARKET_DATA_REQUEST);
            request.setReplyWith("request " + System.currentTimeMillis());// ? intéret ?
            request.setContentObject(observable);
            this.send(request);

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void fillForexRatesAndSendRequest(AID marketDataAgent, AID marketDataAgentFirstDate) {
        forexRates.clear();

        for (ReportLine line : lineList.values()) {
            String currency = ReportBuilder.getCurrency(line);
            MarketQuoteAccessor.addToFXRatesList(forexRates, currency, reportSettings.getCurrency());
        }
        /**
         * then ask for market data
         */
        for (IObservable forexRate : forexRates) {
            this.sendMarketDataRequest(marketDataAgent, (AbstractObservable) forexRate);
            mapFXEnd.put(forexRate.getProduct().getShortName(), null);
            /**
             * same thing in D-1
             */
            if (reportSettings.hasFirstDate()) {
                sendMarketDataRequest(marketDataAgentFirstDate, (AbstractObservable) forexRate);
                mapFXStart.put(forexRate.getProduct().getShortName(), null);
            }
        }
    }

    public void fillForexRatesDirectly() {
        forexRates.clear();

        for (ReportLine line : lineList.values()) {
            String currency = ReportBuilder.getCurrency(line);
            MarketQuoteAccessor.addToFXRatesList(forexRates, currency, reportSettings.getCurrency());
        }
        /**
         * then ask for market data
         */
        for (IObservable forexRate : forexRates) {
            MarketQuoteAccessor.getLastForexRate(forexRate.getProduct().getShortName(), reportSettings.getValDate());
            mapFXEnd.put(forexRate.getProduct().getShortName(), null);
            /**
             * same thing in D-1
             */
            if (reportSettings.hasFirstDate()) {
                MarketQuoteAccessor.getLastForexRate(forexRate.getProduct().getShortName(), reportSettings.getFirstDate());
                mapFXStart.put(forexRate.getProduct().getShortName(), null);
            }
        }
    }

    public void manageForexRate(MarketQuoteObservable msgObservable) {

        try {
            if (msgObservable != null) {
                if (msgObservable.getMarketQuote() != null) {
                    if (msgObservable.getValDate().equals(reportSettings.getValDate())) {
                        mapFXEnd.put(msgObservable.getProduct().getShortName(), msgObservable.getMarketQuote().getClose());
                    } else {
                        mapFXStart.put(msgObservable.getProduct().getShortName(), msgObservable.getMarketQuote().getClose());
                    }
                }

                // recalculate the whole report:
                // content object = null
                ACLMessage newMessage = new ACLMessage(ACLMessage.REQUEST);
                newMessage.addReceiver(getAID());
                newMessage.setSender(getAID());
                newMessage.setConversationId(PRICING_REPLY);
                newMessage.setContentObject(null);
                send(newMessage);
            }
        } catch (Exception e) {
            logger.error("error looking for data " + e.getMessage());
        }
    }

    private class ForceAfterTimeOut extends TickerBehaviour {

        public ForceAfterTimeOut(Agent agent, long period) {
            super(agent, period);
        }

        @Override
        public void onTick() {
            if (Calendar.getInstance().getTimeInMillis() - lastUpdateTime > TIMEOUT && isRunning) {
                try {
                    // notify
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    try {
                        request.addReceiver(myAgent.getAID());
                        request.setSender(myAgent.getAID());
                        request.setConversationId(FORCE_REPORT_REPLY);
                        request.setContent(null);
                        myAgent.send(request);
                        logger.error("FORCE REPORT REPLY AFTER TIMEOUT " + myAgent.getAID().getLocalName() + " at " + DateUtils.getTime() + " with timeout=" + TIMEOUT + "ms");
                        logNotPriced();
                    } catch (Exception e) {
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
        }
    }

    public void logNotPriced() {

        String notPriced = "";
        String missingObject = "";
        String missingFXRates = "";
        for (Entry<Integer, Map<IPricerMeasure, IMeasureValue[]>> entry : pricerResultsByTrade.entrySet()) {
            if (entry.getValue() == null) {
                notPriced += entry.getKey() + StringUtils.COMMA;
            }
        }
        if (reportSettings.hasFirstDate()) {
            for (Entry<Integer, Map<IPricerMeasure, IMeasureValue[]>> entry : pricerResultsByTradeFirstDate.entrySet()) {
                if (entry.getValue() == null) {
                    notPriced += entry.getKey() + "/first date,";
                }
            }
        }
        if (lineList != null) {
            for (ReportLine line : lineList.values()) {
                if (line.getLineData() == null) {
                    missingObject += line.getId() + StringUtils.COMMA;
                }
            }
        }
        for (Entry<String, BigDecimal> forexRate : mapFXEnd.entrySet()) {
            if (forexRate.getValue() == null) {
                missingFXRates += forexRate.getKey() + StringUtils.COMMA;
            }
        }
        if (!notPriced.isEmpty() || !missingObject.isEmpty() || !missingFXRates.isEmpty()) {
            logger.info("ROW NOT PRICED :" + notPriced + " / MISSING OBJECT : " + missingObject + " / MISSING FX RATE : " + missingFXRates);
        }

    }

    public static void notifyReports(Agent agent, ReportObjectNotification reportObjectNotification) {
        /**
         * now its done notify report agents.
         */
        DFAgentDescription template = new DFAgentDescription();
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setType(ReportAgent.class.getSimpleName());
        serviceDescription.setName(ReportAgent.class.getSimpleName());
        template.addServices(serviceDescription);
        try {
            ACLMessage notify = new ACLMessage(ACLMessage.REQUEST);
            notify.setConversationId(REPORT_OBJECT_CHANGE);
            notify.setSender(agent.getAID());
            notify.setContentObject(reportObjectNotification);
            DFAgentDescription[] result = DFService.search(agent, template);
            for (DFAgentDescription result1 : result) {
                notify.addReceiver(result1.getName());
            }
            if (result.length > 0) {
                agent.send(notify);
            }
        } catch (Exception e) {
            logger.error("No report agent found : type=" + serviceDescription.getType() + " name=" + serviceDescription.getName());
            logger.error(StringUtils.formatErrorMessage(e));
            logger.error("It happends because a behaviour of agent " + agent.getAID().getLocalName() + " catches INFORM messages used by the df to answer");
        }

    }

    public class MatchByConversationId implements MatchExpression {

        private final String conversationId;

        public MatchByConversationId(String conversationId) {
            this.conversationId = conversationId;
        }

        @Override
        public boolean match(ACLMessage msg) {
            return msg.getConversationId().equalsIgnoreCase(conversationId);
        }
    }
}
