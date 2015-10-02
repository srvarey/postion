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
import java.util.Date;
import java.util.List;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 */
public interface IObservable extends Serializable {

    public AbstractObservable.ObservableType getObservableType();

    public void setProductId(Integer productId);

    public Integer getProductId();

    public String getFunction();

    public void fillData(Date date, String quoteSet);

    public boolean isFilled();

    public BigDecimal getObservableValue(Object[] coord);

    public BigDecimal getObservableValueFromPriceable(IPriceable priceable);

    public IObservable shift(List<ObservableShift> shifts, boolean isAbsolute);

    public Product getProduct();

    public void setProduct(Product product);

    public List<Product> getUnderlyings();

    public Class[] getCoordinateClass();

    @Override
    public boolean equals(Object object);

    public void replaceQuote(Integer productId, Double last);

    public Product getLookupProduct();

    public void setLookupProduct(Product lookupProduct);
}
