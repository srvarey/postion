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
package org.gaia.io.dtcc5_3.products;

import java.math.BigDecimal;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.fpml.fpml_5.recordkeeping.GenericProduct;

/**
 *
 * @author Saber
 */
public class GenericProductLoader {

    public static Product read(GenericProduct genericProduct) {
        Product product = new Product();
    

        /**
         * Option Barrier Type
         */
        if (genericProduct.getProductId().get(0).getValue().equalsIgnoreCase("SFX_BAR")) {

            product.setProductType(ProductTypeUtil.ProductType.FX_BARRIER_OPTION.name);
        }
        /**
         * Option Binary Type
         */
        if (genericProduct.getProductId().get(0).getValue().equalsIgnoreCase("SFX_BIN")) {

            product.setProductType(ProductTypeUtil.ProductType.FX_BINARY_OPTION.name);
        }
        /**
         * Option Digital Type
         */
        if (genericProduct.getProductId().get(0).getValue().equalsIgnoreCase("SFX_DIG")) {

            product.setProductType(ProductTypeUtil.ProductType.FX_DIGITAL_OPTION.name);
        }
        /**
         * Optipon Complex Type 
         */
        if (genericProduct.getProductId().get(0).getValue().equalsIgnoreCase("CFX")) {

            product.setProductType(ProductTypeUtil.ProductType.FX_COMPLEX_OPTION.name);
        }

        
       
        product.setIsAsset(Boolean.FALSE);
        product.setQuantityType(Trade.QuantityType.NOTIONAL.name);

        product.setStartDate(genericProduct.getPremium().getPaymentDate().getAdjustableDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
        product.setNotionalMultiplier(BigDecimal.ONE);


        product.setNotionalCurrency(genericProduct.getNotional().iterator().next().getCurrency().getValue());


        String currencyPair = genericProduct.getUnderlyer().iterator().next().getQuotedCurrencyPair().getCurrency1().getValue() + "/" + genericProduct.getUnderlyer().iterator().next().getQuotedCurrencyPair().getCurrency2().getValue();
        Product underlying = ProductAccessor.getProductByShortName(currencyPair);
    
        product.setMaturityDate(genericProduct.getExpirationDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
        product.addUnderlying(underlying);
        return product;
    }
}
