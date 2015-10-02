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


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class MarketQuoteObservable extends AbstractObservable implements Serializable{

    private MarketQuote quote;
    private static final Logger logger = Logger.getLogger(MarketQuoteObservable.class);
/**
 * create the market quote
 * @param product
 */
    public MarketQuoteObservable(Product product) {
        this.productId = product.getId();
        this.product=product;
        this.observableType = AbstractObservable.ObservableType.MARKET_QUOTE;
    }
/**
 *  create the market quote
 * @param productId
 */
    public MarketQuoteObservable(Integer productId) {
        this.productId = productId;
        this.observableType = AbstractObservable.ObservableType.MARKET_QUOTE;
    }

    @Override
    public void fillData(Date date, String quoteSet) {

        quote = MarketQuoteAccessor.getLastQuote(productId, date, quoteSet);
        if (quote!=null){
            product =quote.getProduct();
            isFilled = true;
            valDate = date;
        }
    }

    @Override
    public void replaceQuote(Integer productId, Double last){
        if (last!=null && quote!=null){
            quote.setClose(BigDecimal.valueOf(last));
        }
    }

    public MarketQuote getMarketQuote (){
        return quote;
    }

    public void setMarketQuote (MarketQuote quote){
        this.quote=quote;
    }

    @Override
    public BigDecimal getObservableValue(Object[] coord) {
        String arg ;
        BigDecimal ret=BigDecimal.ZERO;
        if (quote==null){
            return ret;
        }
        if (coord != null) {
            if (coord.length > 0) {
                if (coord[0] instanceof String) {
                    arg = (String) coord[0];
                    switch (arg){
                        case MarketQuote.CLOSE:
                            return quote.getClose();
                        case MarketQuote.OPEN:
                            return quote.getOpen();
                        case MarketQuote.ASK:
                            return quote.getAsk();
                        case MarketQuote.BID:
                            return quote.getBid();
                    }
                }
            }
        } else {
            if (quote.getClose()!=null){
                return quote.getClose();
            } else {
                return quote.getOpen();
            }
        }
        return ret;
    }

    @Override
    public Class[] getCoordinateClass(){
        return null;
    }

    public enum CoordinateClass {};

    @Override
    public BigDecimal getObservableValueFromPriceable(IPriceable priceable){
        return getObservableValue(new Object[] {MarketQuote.CLOSE});
    }

    @Override
    public IObservable shift(List<ObservableShift> shifts, boolean isAbsolute) {
        MarketQuoteObservable newObs=null;
        try {
            newObs = (MarketQuoteObservable) this.clone();
            MarketQuote mq = newObs.getMarketQuote();
            BigDecimal shift;
            if (!shifts.isEmpty()) {
                shift=shifts.get(0).getShift();
            } else {
                shift=BigDecimal.ZERO;
            }
            if (quote.getClose()!=null && shift!=null){
                if (isAbsolute){
                    mq.setClose(quote.getClose().add(shift));
                } else {
                    mq.setClose(quote.getClose().multiply(shift.add(BigDecimal.ONE)));
                }
            }
            if (quote.getOpen()!=null && shift!=null){
                if (isAbsolute){
                    mq.setOpen(quote.getOpen().add(shift));
                } else {
                    mq.setOpen(quote.getOpen().multiply(shift.add(BigDecimal.ONE)));
                }
            }
            newObs.setMarketQuote(mq);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return newObs;
    }

    @Override
    public Product getProduct(){
        return product;
    }

    @Override
    public Object clone() {
        MarketQuoteObservable marketQuoteObservable ;
        marketQuoteObservable = (MarketQuoteObservable)super.clone();
        if (this.quote!=null){
            marketQuoteObservable.quote= (MarketQuote) this.quote.clone();
        }
        return marketQuoteObservable;
    }

    @Override
    public String toString(){
        String str="MarketQuoteObservable";
        if (product!=null && product.getShortName()!=null){
            str+="/"+product.getShortName();
        }
        if (productId!=null){
            str+="/"+productId.toString();
        }
        return str;
    }

    @Override
    public List<Product> getUnderlyings() {
        List<Product> underlyings = new ArrayList<>();
        underlyings.add(product);
        return underlyings;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof MarketQuoteObservable)) {
            return false;
        }
        MarketQuoteObservable other = (MarketQuoteObservable) object;
        if (this.productId!=null && other.productId!=null && this.productId==other.productId){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.quote);
        return hash;
    }
}
