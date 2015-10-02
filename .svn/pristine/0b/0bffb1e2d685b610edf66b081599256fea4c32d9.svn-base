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

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.IMeasureValue;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.reports.Position;
import org.gaia.domain.utils.IPriceable;
import org.gaia.jade.ontology.GaiaMobilityAgent;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin FREREJEAN
 */
public class GaiaPricerAgent extends GaiaMobilityAgent implements GaiaVocabulary {

    private static final long serialVersionUID = 1L;
    private IPriceable priceable;
    private Integer priceableId;
    private Map<MeasuresAccessor.MeasureGroup, IPricer> pricersMap;
    private Map<MeasuresAccessor.MeasureGroup,Map<Integer, IObservable>> groupObservablesMap;
    private Map<Integer, Boolean> observables;
    private Date valuationDate;
    private static final Logger logger = Logger.getLogger(GaiaPricerAgent.class);
    private String className;
    private Map<String, Map<Integer, String>> measures2DUnderlyingIds;
    private long startTimeOutTime = (new Date()).getTime();
    boolean allFilled = true;
    private int noReply = 0;
    private AID marketDataAgent;
    private boolean isRealTime;
    private PricingSetting pricingSetting;

    public GaiaPricerAgent() {
        super(null, null);
    }

    public GaiaPricerAgent(AID aid, jade.util.leap.List args) {
        super(aid,args);
        if (args != null) {
            if (args.size() >= 3) {
                this.priceableId = (Integer) args.get(0);
                this.className = args.get(1).toString();
                this.pricingSetting=(PricingSetting) args.get(2);
            }
        }
        this.isRealTime = pricingSetting.getIsRealTime();
        this.valuationDate = pricingSetting.getValuationDate();
        List measures2D = pricingSetting.getMeasures2D();
        measures2DUnderlyingIds = new HashMap();
        if (measures2D != null) {

            for (int i = 0; i < measures2D.size(); i++) {
                String measureName = measures2D.get(i).toString();
                if (measureName.indexOf(StringUtils.DOT) >= 0) {
                    measureName = measureName.substring(0, measureName.indexOf(StringUtils.DOT));
                }
                HashMap map = (HashMap) measures2DUnderlyingIds.get(measureName);
                if (map==null){
                    map=new HashMap();
                }
                String sId = pricingSetting.getListIds2D().get(i).toString();
                map.put(sId, null);
                measures2DUnderlyingIds.put(measureName, map);
            }
        }
    }

    public static String getServiceName(Integer priceableId, Date valuationDate, AID clientAid) {
        return priceableId + "/" + GaiaAgentController.getDateFormat().format(valuationDate) + "/" + clientAid.getLocalName();
    }

    public static jade.util.leap.List buildArguments(Integer priceableId, Class clazz, PricingSetting pricingSetting) {
        jade.util.leap.List argList = new jade.util.leap.ArrayList();
        argList.add(priceableId);
        argList.add(clazz.getName());
        argList.add(pricingSetting);
        return argList;
    }

    @Override
    protected void setup() {
//        logger.error("GaiaPricerAgent started : "+ getAID().getLocalName()+" on " + priceableId);
        super.setup();
        addBehaviour(new RequestMarketdata());
        addBehaviour(new PricerAgentServer());
        if (isRealTime) {
            /** publish market data service */
            GaiaMobilityAgent.registerService(GaiaPricerAgent.class.getSimpleName(), getServiceName(priceableId, valuationDate, clientAID),this);
        }
        // real time agents die if not used for one hour
        addBehaviour(new DieAfterTimeOut(this, 60000, 3600000));
    }

    @Override
    protected void takeDown() {
        try {
            if (isRealTime) {
                DFService.deregister(this);
            }
            super.takeDown();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    private class RequestMarketdata extends OneShotBehaviour {

        @Override
        public synchronized void action() {

//            logger.error("GaiaPricerAgent load : "+ getAID().getLocalName()+" on " + priceableId);
            /**
             * load pricers and needed observable list with pricing environment.
             */
            try {
                /**
                 * load priceable.
                 */
                Class clazz = Class.forName(className);
                if (className.equals(Position.class.getName())) {
                    priceable = (IPriceable) PositionBuilder.getPositionAndPositionHistory(priceableId, valuationDate);
                } else {
                    priceable = (IPriceable) HibernateUtil.getObject(clazz, priceableId);
                }

                PricingEnvironment pricingEnvironment = pricingSetting.getPricingEnvironment();

                MeasureGroup[] measureGroups=pricingSetting.getMeasureGroups();
                /**
                 * load pricer.
                 */
                pricersMap = PricingBuilder.loadPricerMap(priceable, valuationDate, pricingEnvironment, measureGroups);
                /**
                 * load needed observables list
                 */
                groupObservablesMap = PricingBuilder.getObservables(pricersMap, priceable, pricingEnvironment);

                boolean useMarketDataAgent = true;
                /**
                 * send market data load
                 */
                observables=new HashMap();
                for (Map<Integer,IObservable> observables_ : groupObservablesMap.values()){
                    for (IObservable observable : observables_.values()) {
                        if (!observables.containsKey(observable.getProductId())){
                            observables.put(observable.getProductId(), Boolean.FALSE);
                        }
                    }
                }
                if (!observables.isEmpty() && useMarketDataAgent) {
                    /**
                     * ask for quotes.
                     */
                    logger.debug("PRICINGAGENT asks for market data" + DateUtils.getTime());

                    String quoteSet = PricingEnvironmentAccessor.getObservableQuoteSet(priceable, pricingEnvironment);
                    if (marketDataAgent == null) {
                        marketDataAgent = MarketDataAgent.getMarketDataAgent(quoteSet, valuationDate, isRealTime, myAgent);
                    }
                    for (Map<Integer,IObservable> observables_ : groupObservablesMap.values()){
                        for (IObservable observable : observables_.values()) {

                            if (!observables.get(observable.getProductId())){
                                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                                try {
                                    request.addReceiver(marketDataAgent);
                                    request.setSender(myAgent.getAID());
                                    request.setConversationId(MARKET_DATA_REQUEST);
                                    AbstractObservable aObs = (AbstractObservable) observable;
                                    request.setContentObject(aObs);
                                    myAgent.send(request);
                                    startTimeOutTime = (new Date()).getTime();
                                } catch (Exception e) {
                                    logger.error("GaiaPricerAgent FATAL FAILURE ON " + priceableId);
                                    logger.error(StringUtils.formatErrorMessage(e));
                                }
                                observables.put(observable.getProductId(), Boolean.TRUE);
                            }
                        }
                    }

                } else {

                    if (!useMarketDataAgent) {
                        // fill market data directly here
                        // lower or better performances ??
                        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
                        for (Map<Integer,IObservable> observables : groupObservablesMap.values()){
                            for (IObservable observable : observables.values()) {
                                observable.fillData(valuationDate, quoteSet);
                            }
                        }
                        noReply = observables.size();
                    }
                    try {
                        /**
                         * if no market data needed send message to
                         * PricerAgentServer.
                         */
                        ACLMessage forwardToMe = new ACLMessage(ACLMessage.REQUEST);
                        forwardToMe.addReceiver(getAID());
                        forwardToMe.setContent(null);
                        forwardToMe.setConversationId(MARKET_DATA_REPLY);
                        myAgent.send(forwardToMe);
                    } catch (Exception e) {
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
//            logger.error("GaiaPricerAgent data request sent : "+ getAID().getLocalName()+" on " + priceableId);
        }
    }

    private class PricerAgentServer extends CyclicBehaviour {

        boolean allreplied = false;

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            if (msg != null) {
                /**
                 * Message received. Process it .
                 */
                try {
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        switch (msg.getConversationId()) {
                            case MARKET_DATA_REPLY:
                            case PRICE_CHANGE:

                                AbstractObservable  msgObservable = (AbstractObservable)msg.getContentObject();

                                if (msg.getConversationId().equals(MARKET_DATA_REPLY)) {
                                    noReply++;
                                }
                                allFilled = true;
                                for (Map<Integer,IObservable> observables: groupObservablesMap.values()) {

                                    if (msgObservable != null) {
                                        if (observables.containsKey(msgObservable.getProductId())){
                                            observables.put(msgObservable.getProductId(), msgObservable);
                                        }
                                    }
                                    for (IObservable obs : observables.values()){
                                        if (obs!=null && !obs.isFilled()) {
                                            allFilled = false;
                                        }
                                    }
                                }
                                if (noReply >= observables.size()) {
                                    logger.debug("PRICINGAGENT received all market data observable => pricing " + DateUtils.getTime());
                                    /**
                                     * PRICING
                                     * ==================
                                     */
                                    Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate;
                                    if (allFilled) {
                                        /**
                                         * if all observables are filled , launch the pricer
                                         */
                                        //  PRICING HERE !
                                        //====================
                                        mapWithDate = PricingBuilder.pricePriceable(priceable, groupObservablesMap, valuationDate, pricersMap, measures2DUnderlyingIds, pricingSetting);
                                        PricingBuilder.calculateUnitPostPricings(priceable, mapWithDate);

                                        logger.debug("PRICING AGENT end of pricing " + DateUtils.getTime());
                                    } else {
                                        // else reply to report anyway
                                        mapWithDate = createEmptyMap();
                                    }

                                    sendReply(mapWithDate);
                                    if (!isRealTime) {
                                        myAgent.doDelete();
                                    }
                                }
                                break;
                            case STOP_REAL_TIME:
                                // on real time stop i forward to market data and die
                                for (Map<Integer,IObservable> observables : groupObservablesMap.values()) {
                                    for (IObservable observable : observables.values()) {
                                        msg.setSender(myAgent.getAID());
                                        msg.removeReceiver(myAgent.getAID());
                                        msg.addReceiver(marketDataAgent);
                                        msg.setContentObject(observable.getProductId());
                                        myAgent.send(msg);
                                    }
                                }
                                sendReply(createEmptyMap());
                                myAgent.doDelete();
                                break;
                        }
                    }

                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                    logger.error("object : "+msg.getContent());
                }
            } else {
                block();
            }
        }

        public void sendReply(Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate){
            try{
                ACLMessage reply = new ACLMessage(ACLMessage.REQUEST);
                reply.addReceiver(clientAID);
                PricerResult pricerResult = new PricerResult();
                pricerResult.setId(priceableId);
                pricerResult.setMapWithDate(mapWithDate);
                pricerResult.setLineData(priceable);
                reply.setContentObject(pricerResult);
                reply.setConversationId(PRICING_REPLY);
                myAgent.send(reply);
            }catch (Exception e){
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }

        public Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> createEmptyMap(){

            Map<Date, Map<Integer, Map<IPricerMeasure, IMeasureValue[]>>> mapWithDate = new HashMap();
            Map<Integer, Map<IPricerMeasure, IMeasureValue[]>> mapWithId = new HashMap();
            Map<IPricerMeasure, IMeasureValue[]> measureMap = new HashMap();
            mapWithId.put(priceable.getId(), measureMap);
            mapWithDate.put(valuationDate, mapWithId);
            return mapWithDate;
        }
    }

    public static DFAgentDescription[] findPricerAgents(Integer priceableId, Date valueDate, AID clientAID, Agent a) {
        try {
            DFAgentDescription template = new DFAgentDescription();
            ServiceDescription serviceDescription = new ServiceDescription();
            serviceDescription.setType(GaiaPricerAgent.class.getSimpleName());
            serviceDescription.setName(getServiceName(priceableId, valueDate, clientAID));
            template.addServices(serviceDescription);
            return DFService.search(a, template);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    private class DieAfterTimeOut extends TickerBehaviour {

        private long TIMEOUT;

        public DieAfterTimeOut(Agent agent, long period, long deathTimeOut) {
            super(agent, period);
            TIMEOUT = deathTimeOut;
        }

        @Override
        public void onTick() {
            if (Calendar.getInstance().getTimeInMillis() - startTimeOutTime > TIMEOUT) {
                try {
                    // notify market data agent
                    ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                    try {
                        request.addReceiver(marketDataAgent);
                        request.setSender(myAgent.getAID());
                        request.setConversationId(AGENT_DEATH);
                        request.setContent(myAgent.getAID().getName());
                        myAgent.send(request);
                        startTimeOutTime = Calendar.getInstance().getTimeInMillis();
                    } catch (Exception e) {
                        logger.error("GAIAPRICERAGENT FATAL FAILURE ON " + priceableId);
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                    // and die
                    myAgent.doDelete();
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
        }
    }
}
