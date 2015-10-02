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
package org.gaia.dao.observables;

import com.bloomberglp.blpapi.CorrelationID;
import com.bloomberglp.blpapi.Element;
import com.bloomberglp.blpapi.Event;
import com.bloomberglp.blpapi.EventHandler;
import com.bloomberglp.blpapi.Message;
import com.bloomberglp.blpapi.MessageIterator;
import com.bloomberglp.blpapi.Name;
import com.bloomberglp.blpapi.Session;
import com.bloomberglp.blpapi.SessionOptions;
import com.bloomberglp.blpapi.Subscription;
import com.bloomberglp.blpapi.SubscriptionList;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.MarketDataAgent;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketDataSource;

/**
 *
 * @author Benjamin Frerejean
 *
 */
public class BloombergConnector implements IMarketDataConnector {

    private static final Name SLOW_CONSUMER_WARNING = Name.getName("SlowConsumerWarning");
    private static final Name SLOW_CONSUMER_WARNING_CLEARED = Name.getName("SlowConsumerWarningCleared");
    private static final Name DATA_LOSS = Name.getName("DataLoss");
    private static final Name SUBSCRIPTION_TERMINATED = Name.getName("SubscriptionTerminated");
    private static final Name SOURCE = Name.getName("source");
    private SessionOptions d_sessionOptions;
    private Session d_session;
    private ArrayList<String> d_fields;
    private ArrayList<String> d_options;
    private SubscriptionList d_subscriptions;
    private SimpleDateFormat d_dateFormat;
    private String d_service;
    private boolean d_isSlow;
    private boolean d_isStopped;
    private final SubscriptionList d_pendingSubscriptions;
    private final Set<CorrelationID> d_pendingUnsubscribe;
    private final Object d_lock;
    private static final Logger logger = Logger.getLogger(BloombergConnector.class.getName());
    private Map<String, Integer> codesMap;
    private static final String LAST_PRICE = "LAST_PRICE";
    private static final String BID = "BID";
    private static final String ASK = "ASK";
    private static final String BID_YIELD = "BID_YIELD";
    private static final String ASK_YIELD = "ASK_YIELD";

    public BloombergConnector() {

        d_sessionOptions = new SessionOptions();
        d_sessionOptions.setMaxEventQueueSize(10000);
        d_service = "//blp/mktdata";
        d_fields = new ArrayList();
        d_options = new ArrayList();
        d_subscriptions = new SubscriptionList();
        d_dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
        d_isSlow = false;
        d_isStopped = false;
        d_pendingSubscriptions = new SubscriptionList();
        d_pendingUnsubscribe = new HashSet();
        d_lock = new Object();
    }

    @Override
    public void subscribe(Integer productId, String code) {
        d_subscriptions.add(new Subscription(code, d_fields, d_options, new CorrelationID(code)));
        codesMap.put(code, productId);
    }

    @Override
    public boolean createSession(MarketDataSource marketDataSource, MarketDataAgent myAgent) throws Exception {

        if (marketDataSource != null && marketDataSource.getServerHost() != null && marketDataSource.getServerPort() != null) {
            d_sessionOptions.setServerHost(marketDataSource.getServerHost());//"localhost");
            d_sessionOptions.setServerPort(marketDataSource.getServerPort());//8194);

            if (d_session != null) {
                d_session.stop();
            }

            logger.info("Connecting to " + d_sessionOptions.getServerHost() + "/" + d_sessionOptions.getServerPort());
            if (!"//blp/mktdata".equalsIgnoreCase(d_service)) {
                d_sessionOptions.setDefaultSubscriptionService(d_service);
            }
            d_session = new Session(d_sessionOptions, new SubscriptionEventHandler(myAgent));
            if (!d_session.start()) {
                logger.error("Failed to start session");
                d_session = null;
                return false;
            }
            logger.info("Connected successfully\n");

            if (!d_session.openService(d_service)) {
                logger.error("Failed to open service ");
                d_session.stop();
                return false;
            }

            d_fields.add(LAST_PRICE + StringUtils.COMMA + BID + StringUtils.COMMA + ASK + StringUtils.COMMA + BID_YIELD + StringUtils.COMMA + ASK_YIELD);

            logger.info("Subscribing...");
            /*
             * load product filter product references .
             */
            //        TemplateColumnItem productReferenceColumn=new TemplateColumnItem();
            //        productReferenceColumn.setGetter("getProductReferences");
            //        productReferenceColumn.setParam(marketDataSource.getProductReference());
            //        HashSet<TemplateColumnItem> columns=new HashSet();
            //        columns.add(productReferenceColumn);
            //        List<ReportLine> lines =ObjectAccessor.getReportLinesWithFilter(null,Product.class.getName(),marketDataSource.getFilter(),columns,DateUtils.getDate());
            //        for (ReportLine line : lines){
            //            String topic = line.getObjectMap().get("getProductReferences."+marketDataSource.getProductReference()).toString();
            //            d_subscriptions.add(new Subscription(topic, d_fields, d_options,new CorrelationID(topic)));
            //        }
            codesMap = MarketDataSourceUtils.getMarketCodesMap(marketDataSource.getMarketDataSourceName());
            for (String topic : codesMap.keySet()) {
                d_subscriptions.add(new Subscription(topic, d_fields, d_options, new CorrelationID(topic)));
            }
            d_session.subscribe(d_subscriptions);
            return true;
        } else {
            logger.error("ERROR ON BLOOMBERG CONNECTION");
            if (marketDataSource != null) {
                logger.error("Invalid connection host:port = " + marketDataSource.getServerHost() + ":" + marketDataSource.getServerPort());
            }
            return false;
        }
    }

    @Override
    public boolean isConnected() {
        return d_session == null;
    }

    @Override
    public void unsubscribe(Integer productId, String code) {
        codesMap.remove(code);
        Subscription sub = new Subscription(code, d_fields, d_options, new CorrelationID(code));
        int i = 0;
        int index = -1;
        for (Subscription subscribe : d_subscriptions) {
            if (subscribe.equals(sub)) {
                index = i;
            }
            i++;
        }
        if (index >= 0) {
            d_subscriptions.remove(index);
        }
    }

    class SubscriptionEventHandler implements EventHandler {

        MarketDataAgent myAgent;

        public SubscriptionEventHandler(MarketDataAgent myAgent) {
            this.myAgent = myAgent;
        }

        @Override
        public void processEvent(Event event, Session session) {
            try {
                switch (event.eventType().intValue()) {
                    case Event.EventType.Constants.SUBSCRIPTION_DATA:
                        processSubscriptionDataEvent(event, session);
                        break;
                    case Event.EventType.Constants.SUBSCRIPTION_STATUS:
                        synchronized (d_lock) {
                            processSubscriptionStatus(event, session);
                        }
                        break;
                    case Event.EventType.Constants.ADMIN:
                        synchronized (d_lock) {
                            processAdminEvent(event, session);
                        }
                        break;
                    default:
                        processMiscEvents(event, session);
                        break;
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }

        private boolean processSubscriptionStatus(Event event, Session session) throws Exception {
            logger.info("Processing SUBSCRIPTION_STATUS: ");
            SubscriptionList subscriptionList = null;
            MessageIterator msgIter = event.messageIterator();
            while (msgIter.hasNext()) {
                Message msg = msgIter.next();
                CorrelationID cid = msg.correlationID();
                String topic = (String) cid.object();
                logger.info(d_dateFormat.format(Calendar.getInstance().getTime()) + StringUtils.SPACE + topic + StringUtils.SPACE + msg);

                if (msg.messageType() == SUBSCRIPTION_TERMINATED && d_pendingUnsubscribe.remove(cid)) {
                    /**
                     * If this message was due to a previous unsubscribe
                     */
                    Subscription subscription = getSubscription(cid);
                    if (d_isSlow) {
                        logger.info("Deferring subscription for topic because session is slow " + topic);
                        d_pendingSubscriptions.add(subscription);
                    } else {
                        if (subscriptionList == null) {
                            subscriptionList = new SubscriptionList();
                        }
                        subscriptionList.add(subscription);
                    }
                }
            }

            if (subscriptionList != null && !d_isStopped) {
                session.subscribe(subscriptionList);
            }
            return true;
        }

        private boolean processSubscriptionDataEvent(Event event, Session session) throws Exception {
            logger.info("Processing SUBSCRIPTION_DATA");
            MessageIterator msgIter = event.messageIterator();
            while (msgIter.hasNext()) {
                Message msg = msgIter.next();
                String topic = (String) msg.correlationID().object();
                Integer productId = codesMap.get(topic);

                Element elmMktData = msg.getElement("MarketDataEvents");
                double last = elmMktData.getElementAsFloat64(LAST_PRICE);

                myAgent.manageRealTimePriceChange(productId, last);

            }
            return true;
        }

        private boolean processAdminEvent(Event event, Session session) throws Exception {
            logger.info("Processing ADMIN: ");
            ArrayList<CorrelationID> cidsToCancel = null;
            boolean previouslySlow = d_isSlow;
            MessageIterator msgIter = event.messageIterator();
            while (msgIter.hasNext()) {
                Message msg = msgIter.next();
                /**
                 * An admin event can have more than one messages.
                 */
                if (msg.messageType() == SLOW_CONSUMER_WARNING) {
                    logger.info("MESSAGE:" + msg);
                    d_isSlow = true;
                } else if (msg.messageType() == SLOW_CONSUMER_WARNING_CLEARED) {
                    logger.info("MESSAGE:" + msg);
                    d_isSlow = false;
                } else if (msg.messageType() == DATA_LOSS) {
                    CorrelationID cid = msg.correlationID();
                    String topic = (String) cid.object();
                    logger.info(d_dateFormat.format(Calendar.getInstance().getTime()) + StringUtils.SPACE + topic + StringUtils.SPACE + msg);
                    if (msg.hasElement(SOURCE)) {
                        String sourceStr = msg.getElementAsString(SOURCE);
                        if (sourceStr.compareTo("InProc") == 0 && !d_pendingUnsubscribe.contains(cid)) {
                            /**
                             * DataLoss was generated "InProc". This can only happen if applications are processing events slowly and hence are not able to keep-up with the incoming events.
                             */
                            if (cidsToCancel == null) {
                                cidsToCancel = new ArrayList();
                            }
                            cidsToCancel.add(cid);
                            d_pendingUnsubscribe.add(cid);
                        }
                    }
                }
            }

            if (!d_isStopped) {
                if (cidsToCancel != null) {
                    session.cancel(cidsToCancel);
                } else if ((previouslySlow && !d_isSlow) && !d_pendingSubscriptions.isEmpty()) {
                    /**
                     * Session was slow but is no longer slow. subscribe to any topics for which we have previously received SUBSCRIPTION_TERMINATED
                     */
                    logger.info("Subscribing to topics " + getTopicsString(d_pendingSubscriptions));
                    session.subscribe(d_pendingSubscriptions);
                    d_pendingSubscriptions.clear();
                }
            }
            return true;
        }

        private boolean processMiscEvents(Event event, Session session) throws Exception {
            logger.info("Processing " + event.eventType());
            MessageIterator msgIter = event.messageIterator();
            while (msgIter.hasNext()) {
                Message msg = msgIter.next();
                logger.info(d_dateFormat.format(Calendar.getInstance().getTime()) + StringUtils.SPACE + msg);
            }
            return true;
        }

        private Subscription getSubscription(CorrelationID cid) {
            for (Subscription subscription : d_subscriptions) {
                if (subscription.correlationID().equals(cid)) {
                    return subscription;
                }
            }
            throw new IllegalArgumentException("No subscription found corresponding to cid = " + cid.toString());
        }

        private String getTopicsString(SubscriptionList list) {
            StringBuilder strBuilder = new StringBuilder();
            for (int count = 0; count < list.size(); ++count) {
                Subscription subscription = list.get(count);
                if (count != 0) {
                    strBuilder.append(", ");
                }
                strBuilder.append((String) subscription.correlationID().object());
            }
            return strBuilder.toString();
        }

    }

}
