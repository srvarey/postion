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
import java.util.Date;
import org.gaia.domain.observables.MarketQuote.QuotationType;
import org.gaia.domain.trades.Product;

/**
 * @author Benjamin Frerejean
 */

public abstract class AbstractObservable implements IObservable, Cloneable, Serializable {

    public enum ObservableType {

        MARKET_QUOTE(OBSERVABLE_MARKET_QUOTE, new String[]{}, MarketQuoteObservable.class,QuotationType.PRICE),
        PRODUCT_HISTORICAL(OBSERVABLE_PRODUCT_HISTORICAL, new String[]{}, ProductHistoricalObservable.class,QuotationType.PRICE),
        IR_CURVE(OBSERVABLE_IR_CURVE, new String[]{DISCOUNTING, FORCASTING}, CurveObservable.class,QuotationType.RATE),
        CREDIT_CURVE(OBSERVABLE_CREDIT_CURVE, new String[]{DEFAULT_FORCASTING, CVA}, CurveObservable.class,QuotationType.SPREAD),
        RECOVERY_CURVE(OBSERVABLE_RECOVERY_CURVE, new String[]{DEFAULT_FORCASTING, CVA}, CurveObservable.class,QuotationType.RECOVERY_RATE),
        VOLATILITY(OBSERVABLE_VOLATILITY, new String[]{}, VolatilityObservable.class,QuotationType.VOLATILITY),
        FX_VOLATILITY(OBSERVABLE_FX_VOLATILITY, new String[]{}, VolatilityObservable.class,QuotationType.VOLATILITY),
        TIME(OBSERVABLE_TIME, new String[]{},null,QuotationType.PRICE),
        FX_FORWARD_CURVE(OBSERVABLE_FX_FORWARD_CURVE, new String[]{}, CurveObservable.class,QuotationType.RATE);

        public String name;
        public String[] functions;
        public Class observableClass;
        public QuotationType quotationType;

        ObservableType(String nom, String[] functions, Class observableClass,QuotationType quotationType) {
            this.name = nom;
            this.functions = functions;
            this.observableClass = observableClass;
            this.quotationType=quotationType;
        }

        public static ObservableType getObservableByName(String name){
            for (ObservableType observable : ObservableType.values()){
                if (observable.name.equalsIgnoreCase(name)){
                    return observable;
                }
            }
            return null;
        }

    }

    public static final String DISCOUNTING = "Discounting";
    public static final String FORCASTING = "Forcasting";
    public static final String DEFAULT_FORCASTING = "Default Forcasting";
    public static final String CVA = "CVA";
    public static final String VOLATILITY = "Volatility";
    public static final String OBSERVABLE_MARKET_QUOTE = "Market Quote";
    public static final String OBSERVABLE_PRODUCT_HISTORICAL = "Product Historical";
    public static final String OBSERVABLE_VOLATILITY="Volatility";
    public static final String OBSERVABLE_TIME="Time";
    public static final String OBSERVABLE_IR_CURVE = "IR Curve";
    public static final String OBSERVABLE_CREDIT_CURVE = "Credit Curve";
    public static final String OBSERVABLE_RECOVERY_CURVE = "Recovery Curve";
    public static final String OBSERVABLE_FX_FORWARD_CURVE = "FX Forward Curve";
    public static final String OBSERVABLE_FX_VOLATILITY="FX Volatility";

    protected ObservableType observableType;
    protected String function;
    protected Integer productId;
    protected boolean isFilled = false;
    protected Date valDate;
    protected Product product;
    protected Product lookupProduct;

    public enum CoordinateClass {};

    @Override
    public boolean isFilled() {
        return isFilled;
    }

    @Override
    public Integer getProductId() {
        return productId;
    }

    @Override
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public ObservableType getObservableType() {
        return observableType;
    }

    public void setObservableType(ObservableType observableType) {
        this.observableType = observableType;
    }

    @Override
    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public void setIsFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    @Override
    public Product getProduct() {
        return product;
    }

    @Override
    public void setProduct(Product product) {
        this.product = product;
        if (product!=null){
            this.productId=product.getId();
        }
    }

    @Override
    public Product getLookupProduct() {
        return lookupProduct;
    }

    @Override
    public void setLookupProduct(Product lookupProduct) {
        this.lookupProduct = lookupProduct;
    }

    @Override
    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return o;
    }
}
