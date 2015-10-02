/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.jade;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.audit.AuditAccessor;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.FakeRealTimeConnector;
import org.gaia.dao.observables.IMarketDataConnector;
import org.gaia.dao.observables.MarketDataSourceUtils;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;
import static org.gaia.jade.ontology.GaiaVocabulary.MARKET_DATA_UPDATE;

/**
 *
 * @author Benjamin Frèrejean
 *
 */
public class MarketDataAgent extends Agent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private Map<Integer, AbstractObservable> observablesList;
    private Map<Integer, List<Integer>> parentLists;
    private Date valuationDate;
    private Boolean isRealTime;
    private static final Logger logger = Logger.getLogger(MarketDataAgent.class);
    private List<IMarketDataConnector> realTimeConnectors;
    private String quoteSet;
    private Map<Integer, List<AID>> realTimePricers;
    private static XStream xstream;
    private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

    public MarketDataAgent(AID aid, jade.util.leap.List args) {
        super();
        try {
            if (args != null) {
                if (args.size() >= 3) {
                    this.quoteSet = (String) args.get(0);
                    this.valuationDate = GaiaAgentController.getDateFormat().parse(args.get(1).toString());
                    this.isRealTime = Boolean.valueOf(args.get(2).toString());
                    if (this.isRealTime) {
                        realTimePricers = new HashMap();
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        observablesList = new HashMap();
        parentLists = new HashMap();
        xstream = new XStream(new StaxDriver());
    }

    public static jade.util.leap.List buildArguments(String quoteSet, Date valueDate, boolean isRealTime) {
        jade.util.leap.List argList = new jade.util.leap.ArrayList();
        argList.add(quoteSet);
        argList.add(GaiaAgentController.getDateFormat().format(valueDate));
        argList.add(Boolean.valueOf(isRealTime).toString());
        return argList;
    }

    public static AID getMarketDataAgent(String quoteSet, Date valueDate, boolean isRealTime, Agent myAgent) {

        DFAgentDescription[] result = MarketDataAgent.findMarketDataAgents(quoteSet, valueDate, isRealTime, myAgent);
        if (result.length == 0) {
            jade.util.leap.List argList = MarketDataAgent.buildArguments(quoteSet, valueDate, isRealTime);
            GaiaMobilityAgent.sendAgentCreationrequest(MarketDataAgent.class, argList, myAgent);
        }
        while (result.length == 0) {
            result = MarketDataAgent.findMarketDataAgents(quoteSet, valueDate, isRealTime, myAgent);
        }
        return result[0].getName();
    }

    public static DFAgentDescription[] findMarketDataAgents(String quoteSet, Date valueDate, boolean isRealTime, Agent a) {
        try {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType(MarketDataAgent.class.getSimpleName());
            serviceDescription.setName(getServiceName(quoteSet, valueDate, isRealTime));
            template.addServices(serviceDescription);
            return DFService.search(a, template);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static void impactMarketDataAgent(MarketQuote marketQuote, DFAgentDescription desc, Agent myAgent) {
        try {
            ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
            msg.addReceiver(desc.getName());
            msg.setContentObject(marketQuote);
            msg.setConversationId(MARKET_DATA_UPDATE);
            myAgent.send(msg);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static String getServiceName(String quoteset, Date valueDate, boolean isRealTime) {
        return quoteset + '/' + GaiaAgentController.getDateFormat().format(valueDate) + '/' + isRealTime;
    }

    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new MarketDataServer());

        /**
         * publish market data service
         */
        GaiaMobilityAgent.registerService(MarketDataAgent.class.getSimpleName(), getServiceName(quoteSet, valuationDate, isRealTime), this);
        if (isRealTime) {
            /**
             * launch market data connectors is real time
             */
            realTimeConnectors = MarketDataSourceUtils.launchMarketDataConnectors(this);
        }
    }

    @Override
    protected void takeDown() {
        /**
         * Deregister from the DF
         */
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            logger.error("Error " + fe.getMessage());
        }
        super.takeDown();
    }

    private class MarketDataServer extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                try {
                    switch (msg.getConversationId()) {
                        case MARKET_DATA_REQUEST:
                            /**
                             * find market data
                             */
                            Object contentObject = msg.getContentObject();
                            AbstractObservable msgObservable = (AbstractObservable) contentObject;
                            if (msgObservable != null) {
                                AbstractObservable observable = observablesList.get(msgObservable.getProductId());
                                if (observable == null) {
                                    msgObservable.fillData(valuationDate, quoteSet);
                                    observablesList.put(msgObservable.getProductId(), msgObservable);
                                    if (isRealTime) {
                                        // subscribe to real time
                                        subscribe(msgObservable, msg.getSender());
                                    }
                                } else if (!observable.isFilled()) {
                                    observable.fillData(valuationDate, quoteSet);
                                    msgObservable = observable;
                                } else {
                                    msgObservable = observable;
                                    if (isRealTime) {
                                        subscribe(msgObservable, msg.getSender());
                                    }
                                }
                                ACLMessage reply = msg.createReply();
                                reply.setConversationId(MARKET_DATA_REPLY);
                                if (msg.getSender().getLocalName().equalsIgnoreCase(RealTimeSubscriberAgent.class.getSimpleName())) {
                                    reply.setContent(xstream.toXML(msgObservable));
                                } else {
                                    reply.setContentObject(msgObservable);
                                }
                                myAgent.send(reply);
                            } else {
                                logger.error("MarketDataAgent received null observable request from " + msg.getSender().getLocalName());
                            }
                            break;
                        case STOP_REAL_TIME:
                            // on real time stop , remove from list
                            Integer id = (Integer) msg.getContentObject();
                            if (realTimePricers != null) {
                                List<AID> aids = realTimePricers.get(id);
                                if (aids != null) {
                                    aids.remove(msg.getSender());
                                    if (aids.isEmpty()) {
                                        realTimePricers.remove(id);
                                        for (IMarketDataConnector connector : realTimeConnectors) {
                                            if (connector.isConnected()) {
                                                AbstractObservable myobservable = observablesList.get(id);
                                                List<Product> underlyings = myobservable.getUnderlyings();
                                                for (Product underlying : underlyings) {
                                                    Map<String, MarketDataCode> codeMap = MarketDataSourceUtils.getMarketCodesMap(id);
                                                    MarketDataCode code = codeMap.get(connector.getClass().getName());
                                                    if (code != null && !StringUtils.isEmptyString(code.getProductCode())) {
                                                        connector.unsubscribe(underlying.getProductId(), code.getProductCode());
                                                    } else if (connector.getClass().getName().equals(FakeRealTimeConnector.class.getName())) {
                                                        // case of fake real time : no code needed
                                                        connector.unsubscribe(underlying.getProductId(), id.toString());
                                                    }
                                                }
                                            }
                                        }
                                        logger.info("Stop real time on " + id);
                                    }
                                }
                            }
                            break;
                        case MARKET_DATA_UPDATE:
                            contentObject = msg.getContentObject();
                            if (contentObject instanceof MarketQuote) {
                                MarketQuote marketQuote = (MarketQuote) contentObject;
                                AbstractObservable observable = observablesList.get(marketQuote.getProduct().getProductId());
                                if (observable == null) {
                                    if (observable instanceof MarketQuoteObservable) {
                                        MarketQuoteObservable quoteObservable = (MarketQuoteObservable) observable;
                                        quoteObservable.setMarketQuote(marketQuote);
                                    }
                                }

                            } // case of curves and surfaces are still to be done

                            break;
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            } else {
                block();
            }
        }
    }

    public void subscribe(AbstractObservable observable, AID sender) {
        for (IMarketDataConnector connector : realTimeConnectors) {
            if (connector.isConnected()) {

                List<Product> underlyings = observable.getUnderlyings();
                if (underlyings != null) {
                    for (Product underlying : underlyings) {

                        Map<String, MarketDataCode> codeMap = MarketDataSourceUtils.getMarketCodesMap(underlying.getProductId());
                        MarketDataCode code = codeMap.get(connector.getClass().getName());

                        if (code != null && !StringUtils.isEmptyString(code.getProductCode())) {
                            connector.subscribe(underlying.getProductId(), code.getProductCode());
                        } else if (connector.getClass().getName().equals(FakeRealTimeConnector.class.getName())) {
                            // case of fake real time : no code needed
                            connector.subscribe(underlying.getProductId(), underlying.getProductId() + "/" + observable.getProductId());
                        }
                        List<AID> aids = realTimePricers.get(observable.getProductId());
                        if (aids == null) {
                            aids = new ArrayList();
                        }
                        if (!aids.contains(sender)) {
                            aids.add(sender);
                        }
                        realTimePricers.put(observable.getProductId(), aids);
                        List<Integer> parents = parentLists.get(underlying.getProductId());
                        if (parents == null) {
                            parents = new ArrayList();
                        }
                        if (!parents.contains(observable.getProductId())) {
                            parents.add(observable.getProductId());
                        }
                        parentLists.put(underlying.getProductId(), parents);

                        logger.debug(sender.getLocalName() + " subscribes to " + underlying.getShortName());
                    }
                } else {
                    logger.warn("Market data agent : no underlying found on " + observable.toString() + " => no real time");
                }
            }
        }
    }

    public void manageRealTimePriceChange(Integer productId, double last) {

        List<Integer> parentList = parentLists.get(productId);
        if (parentList != null) {
            for (Integer productParentId : parentList) {
                AbstractObservable observable = observablesList.get(productParentId);
                if (observable != null) {
                    observable.replaceQuote(productId, last);
                    notifyPriceChange(this, productParentId, observable, realTimePricers);
                    observablesList.put(productParentId, observable);
                }
            }
        }
    }

    public static void notifyPriceChange(Agent agent, Integer productId, AbstractObservable observable, Map<Integer, List<AID>> realTimePricers) {

        /**
         * notify pricer/report agents.
         */
        try {
            ACLMessage notifyClients = new ACLMessage(ACLMessage.REQUEST);
            notifyClients.setConversationId(PRICE_CHANGE);
            notifyClients.setSender(agent.getAID());
            AuditAccessor.unLazyObjectRecursively(observable);
            notifyClients.setContent(xstream.toXML(observable));
            ACLMessage notifyServers = new ACLMessage(ACLMessage.REQUEST);
            notifyServers.setConversationId(PRICE_CHANGE);
            notifyServers.setSender(agent.getAID());
            notifyServers.setContentObject(observable);
            List<AID> aids = realTimePricers.get(productId);
            if (aids != null) {
                for (AID aid : aids) {
                    if (aid.getName().startsWith(RealTimeSubscriberAgent.class.getSimpleName())) {
                        notifyClients.addReceiver(aid);
                    } else {
                        notifyServers.addReceiver(aid);
                    }
                }
            }
            if (notifyClients.getAllReceiver().hasNext()) {
                agent.send(notifyClients);
            }
            if (notifyServers.getAllReceiver().hasNext()) {
                agent.send(notifyServers);
            }

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }
}
