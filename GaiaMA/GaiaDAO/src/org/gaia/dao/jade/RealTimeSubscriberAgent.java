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
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import java.awt.Component;
import java.io.IOException;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.ontology.GaiaVocabulary;

/**
 *
 * @author Benjamin Frerejean
 */
public class RealTimeSubscriberAgent extends GuiAgent implements GaiaVocabulary {

    public static final int SUBSCRIBE = 0;
    private static final Logger logger = Logger.getLogger(RealTimeSubscriberAgent.class);
    private IRefreshableWindow UI;
    private AID marketDataAgentAID = null;
    private static XStream xstream;

    public RealTimeSubscriberAgent() {
        super();
        xstream = new XStream(new StaxDriver());
    }

    @Override
    protected void setup() {
        addBehaviour(new ReplyListener());
    }

    @Override
    protected void onGuiEvent(GuiEvent ge) {
        int command = ge.getType();
        if (command == SUBSCRIBE) {
            AbstractObservable observable = (AbstractObservable) ge.getParameter(command);
            addBehaviour(new SubscribeToRealTime(observable));
        }
    }

    public class SubscribeToRealTime extends OneShotBehaviour {

        AbstractObservable observable;
        String quoteSet;

        public SubscribeToRealTime(AbstractObservable observable) {
            super();
            this.observable = observable;
            this.quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        }

        @Override
        public void action() {
            if (marketDataAgentAID == null) {
                marketDataAgentAID = MarketDataAgent.getMarketDataAgent(quoteSet, new Date(), true, myAgent);
            }
            if (observable != null) {
                ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
                try {
                    request.addReceiver(marketDataAgentAID);
                    request.setSender(getAID());
                    request.setConversationId(MARKET_DATA_REQUEST);
                    request.setContentObject(observable);
                    send(request);
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            } else {
                logger.warn("null observable requested by " + ((Component) UI).getName());
            }
        }
    }

    private class ReplyListener extends CyclicBehaviour {

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
            String xml=null;
            AbstractObservable observable=null;
            if (msg != null) {
                try {
                    logger.debug(msg.getConversationId() + " received at " + DateUtils.getTime());
                    if (msg.getPerformative() != ACLMessage.FAILURE) {
                        switch (msg.getConversationId()) {
                            case PRICE_CHANGE:

                                xml = msg.getContent();
                                if (!xml.isEmpty()) {
                                    observable = (AbstractObservable) xstream.fromXML(xml);
                                }
                                UI.refresh(observable);

                                break;
                        }
                    } else {
                        logger.error(this.toString() + StringUtils.SPACE + msg.getContent());
                    }
                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                    System.out.println(xml);
                }
            } else {
                block();
            }
        }
    }

    public void sendStopRealTimeRequest(Integer observableId) {

        ACLMessage request = new ACLMessage(ACLMessage.REQUEST);

        try {
            request.addReceiver(marketDataAgentAID);
            request.setSender(getAID());
            request.setConversationId(STOP_REAL_TIME);
            request.setContentObject(observableId);
            send(request);
        } catch (IOException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void setUI(IRefreshableWindow UI) {
        this.UI = UI;
    }
}
