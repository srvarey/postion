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
package org.gaia.domain.utils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.trades.Product;

/**
 *
 * @author Benjamin Frerejean
 */

/** interface for priceable objects = trade / positions */

public interface IPriceable extends Serializable{

     public Product getProduct();

     public void setProduct(Product product);

     public Integer getId();

     public Date getValueDate();

     public BigDecimal getQuantity(Date valDate);

     public LegalEntity getInternalCounterparty();

     public LegalEntity getCounterparty();

     public Agreement getAgreement();

     public Integer getAgreementId();

}
