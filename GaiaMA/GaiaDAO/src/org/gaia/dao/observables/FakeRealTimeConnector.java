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
package org.gaia.dao.observables;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.MarketDataAgent;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketDataSource;
import org.gaia.domain.observables.MarketQuote;

/**
 *
 * @author Benjamin Frerejean
 *
 */
public class FakeRealTimeConnector implements IMarketDataConnector{

    private static final Logger logger = Logger.getLogger(FakeRealTimeConnector.class.getName());

    private Map<String,Integer> codesMap;
    private Map<Integer,Double> quotes;
    private String quoteSet;
    private MarketDataAgent myAgent;

    public FakeRealTimeConnector() {
        quoteSet=MarketQuoteAccessor.getDefaultQuoteSet();
        quotes=new ConcurrentHashMap();
    }

    @Override
    public boolean createSession(MarketDataSource marketDataSource,MarketDataAgent myAgent) throws Exception {
        logger.info("Start fake real time connector");
        codesMap = MarketDataSourceUtils.getMarketCodesMap(marketDataSource.getMarketDataSourceName());
        this.myAgent=myAgent;
        Thread thread = new Thread() {
            @Override
            public void run() {
                runSimulation();
            }
        };
        thread.start();
        return true;
    }

    public void runSimulation(){
         while (codesMap!=null){
            try {
                for (Entry<Integer,Double> entry : quotes.entrySet()){
                    if (Math.random() > 0.95) {
                        double factor = (Math.random() - .5) * 0.1 + 1;
                        double value = entry.getValue().doubleValue() * factor;
                        myAgent.manageRealTimePriceChange(entry.getKey(),value);
                    }
                }
                Thread.sleep(100);
            } catch (Exception e) {
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
        }
    }

    @Override
    public void subscribe(Integer productId, String code){
        logger.info("subscribed to "+code);
        codesMap.put(code, productId);
        MarketQuote quote=MarketQuoteAccessor.getLastQuote(productId, new Date(), quoteSet);
        if (quote!=null && quote.getClose()!=null){
            quotes.put(productId,(double) quote.getClose().floatValue());
        }
    }

    @Override
    public boolean isConnected(){
        return (codesMap!=null);
    }

    @Override
    public void unsubscribe(Integer productId, String code) {
        codesMap.remove(code);
        quotes.remove(productId);
    }

}
