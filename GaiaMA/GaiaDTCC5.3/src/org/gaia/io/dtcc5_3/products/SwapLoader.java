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
package org.gaia.io.dtcc5_3.products;

import java.math.BigDecimal;
import java.util.ArrayList;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.fpml.fpml_5.recordkeeping.Swap;

/**
 *
 * @author user
 */
public class SwapLoader {

    /** read swap  */
     public static Product read(Swap interestRateSwap) {
        Product product = new Product();

        if (interestRateSwap.getProductId().get(0).getValue().contains("InterestRate:IRSwap:FixedFloat")
         || interestRateSwap.getProductId().get(0).getValue().contains("InterestRate:IRSwap:OIS") ) {

            if (interestRateSwap.getProductId().get(0).getValue().contains("InterestRate:IRSwap:FixedFloat")
               || interestRateSwap.getProductId().get(0).getValue().contains("InterestRate:IRSwap:OIS") ) {
                    product.setProductType(ProductTypeUtil.ProductType.IRS.name);
            }

            product.setIsAsset(Boolean.FALSE);
            product.setQuantityType(Trade.QuantityType.NOTIONAL.name);

            product.setMaturityDate(interestRateSwap.getSwapStream().get(0).getCalculationPeriodDates().getTerminationDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
            product.setStartDate(interestRateSwap.getSwapStream().get(0).getCalculationPeriodDates().getEffectiveDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
            product.setNotionalMultiplier(BigDecimal.ONE);
            product.setNotionalCurrency(interestRateSwap.getSwapStream().get(0).getCalculationPeriodAmount().getCalculation().getNotionalSchedule().getNotionalStepSchedule().getCurrency().getValue());

            Product leg1 = SwapLegLoader.read(interestRateSwap.getSwapStream().get(0));
            Product leg2 = SwapLegLoader.read(interestRateSwap.getSwapStream().get(1));

            product.setSubProducts(new ArrayList());
            product.getSubProducts().add(leg1);
            product.getSubProducts().add(leg2);

        } else {
            return null;
        }

        return product;
     }
}
