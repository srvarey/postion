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
package org.gaia.dao.observables;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public class ProductHistoricalObservable extends AbstractObservable implements IObservable, Serializable {

    private ArrayList<ProductHistoricalMeasure> histoMeasures;
    private static final Logger logger = Logger.getLogger(ProductHistoricalObservable.class);

    public ProductHistoricalObservable(Integer productId) {
        this.productId = productId;
        this.observableType = AbstractObservable.ObservableType.PRODUCT_HISTORICAL;
    }

    @Override
    public void fillData(Date date, String quoteSet) {
        isFilled = true;
        histoMeasures = new ArrayList();
        histoMeasures.add(ProductHistoricalAccessor.getLastHistorical(productId, date, quoteSet));
        valDate = date;
    }
    @Override
    public void replaceQuote(Integer productId, Double last){
    }

    public ArrayList<ProductHistoricalMeasureValue> getHistoricalMeasures() {
        ArrayList<ProductHistoricalMeasureValue> ret = null;
        if (histoMeasures != null) {
            for (ProductHistoricalMeasure phm : histoMeasures) {
                if (phm != null) {
                    if (phm.getProductHistoricalMeasuresValueCollection() != null) {
                        ret = new ArrayList();
                        for (ProductHistoricalMeasureValue phmv : phm.getProductHistoricalMeasuresValueCollection()) {
                            ret.add(phmv);
                        }
                    }
                }
            }
        }
        return ret;
    }

    @Override
    public Class[] getCoordinateClass(){
        return null;
    }

    @Override
    public BigDecimal getObservableValue(Object[] coord) {
        BigDecimal ret = BigDecimal.ZERO;
        if (coord != null) {
            if (coord[0] != null) {
                for (ProductHistoricalMeasure phm : histoMeasures) {
                    if (phm != null) {
                        for (ProductHistoricalMeasureValue phmv : phm.getProductHistoricalMeasuresValueCollection()) {
                            if (phmv.getMeasureName().equals(coord[0].toString())) {
                                return phmv.getMeasureValue();
                            }
                        }
                    }
                }
            }
        }

        return ret;
    }

    @Override
    public BigDecimal getObservableValueFromPriceable(IPriceable priceable) {
        return BigDecimal.ZERO;
    }

    @Override
    public IObservable shift(List<ObservableShift> shifts, boolean isAbsolute) {
        ProductHistoricalObservable observable = null;
        try {
            observable = (ProductHistoricalObservable) this.clone();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));

        }
        return observable;
    }

    @Override
    public List<Product> getUnderlyings() {
        List<Product> underlyings = new ArrayList<>();
        underlyings.add(product);
        return underlyings;
    }

}
