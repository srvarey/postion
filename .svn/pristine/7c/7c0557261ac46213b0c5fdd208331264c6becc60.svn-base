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
import java.math.BigInteger;
import javax.xml.bind.JAXBElement;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductSingleTraded;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.fpml.fpml_5.recordkeeping.FloatingRate;
import org.gaia.fpml.fpml_5.recordkeeping.InterestRateStream;

/**
 *
 * @author user
 */
public class SwapLegLoader {


     public static Product read(InterestRateStream swapStream) {
        Product product = new Product();

        product.setIsAsset(Boolean.FALSE);
        product.setQuantityType(Trade.QuantityType.NOTIONAL.name);
        product.setMaturityDate(swapStream.getCalculationPeriodDates().getTerminationDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
        product.setStartDate(swapStream.getCalculationPeriodDates().getEffectiveDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
        product.setNotionalCurrency(swapStream.getCalculationPeriodAmount().getCalculation().getNotionalSchedule().getNotionalStepSchedule().getCurrency().getValue());
        product.setStatus(ProductConst.ProductStatus.Active.name());
        product.setNotionalMultiplier(BigDecimal.ONE);

        ProductSingleTraded otc = new ProductSingleTraded();
        otc.setProduct(product);

        Scheduler scheduler = new Scheduler();
        BigInteger payLag =BigInteger.ZERO;
        BigInteger resetLag=BigInteger.ZERO ;

        /** leg specifics */
        if (swapStream.getResetDates()!=null){
            /** FLOATING LEG */
            product.setProductType(ProductTypeUtil.ProductType.FLOATING_LEG.name);
            otc.setRate(BigDecimal.ZERO);

            /** rate index lookup */
            JAXBElement rate=swapStream.getCalculationPeriodAmount().getCalculation().getRateCalculation();
            FloatingRate floatingRate =(FloatingRate)rate.getValue();

            String tenor = swapStream.getCalculationPeriodDates().getCalculationPeriodFrequency().getPeriodMultiplier().toString()
                  + swapStream.getCalculationPeriodDates().getCalculationPeriodFrequency().getPeriod();

            String frequency = FrequencyUtil.getFrequencyFromTenor(tenor);
            scheduler.setFrequency(frequency);

            tenor =floatingRate.getIndexTenor().getPeriodMultiplier().toString()
                    + floatingRate.getIndexTenor().getPeriod().name();
            String compfrequency = FrequencyUtil.getFrequencyFromTenor(tenor);

            if (compfrequency!=null&&!frequency.equalsIgnoreCase(compfrequency)){
                scheduler.setCompoundFrequency(compfrequency);
                scheduler.setIsCompound(Boolean.TRUE);
            }
            String indexIsdaLabel=floatingRate.getFloatingRateIndex().getValue();
            String currency=indexIsdaLabel.substring(0,3);
            String name=indexIsdaLabel.substring(4,indexIsdaLabel.lastIndexOf("-"));
            Product index = (Product) HibernateUtil.getObjectWithQuery("from Product p where upper(p.shortName) like '%"+name+"%' and upper(p.shortName) like '%"+tenor+"%' and p.notionalCurrency='"+currency+"' and p.productType='"+ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.name+StringUtils.QUOTE);
            if (index!=null){
                product.addUnderlying(index);
            }
            product.setNotionalCurrency(currency);
            scheduler.setIsResetLagBusDays(true);
            scheduler.setIsResetInArrears(false);
            if (swapStream.getResetDates().getFixingDates()!=null){
                resetLag=swapStream.getResetDates().getFixingDates().getPeriodMultiplier();
                scheduler.setIsResetLagBusDays(swapStream.getResetDates().getFixingDates().getDayType().name().equalsIgnoreCase("Business"));
            }
            scheduler.setResetLag(resetLag.intValue());



        } else {
            /** FIXED LEG */
            product.setProductType(ProductTypeUtil.ProductType.FIXED_LEG.name);
            otc.setRate(swapStream.getCalculationPeriodAmount().getCalculation().getFixedRateSchedule().getInitialValue());

            String currency=swapStream.getCalculationPeriodAmount().getCalculation().getNotionalSchedule().getNotionalStepSchedule().getCurrency().getValue();
            product.setNotionalCurrency(currency);
            String tenor = swapStream.getPaymentDates().getPaymentFrequency().getPeriodMultiplier().toString()
                         + swapStream.getPaymentDates().getPaymentFrequency().getPeriod();

            String frequency = FrequencyUtil.getFrequencyFromTenor(tenor);
            scheduler.setFrequency(frequency);

        }
        product.setProductSingleTraded(otc);

        String dayCount;
        if (swapStream.getCalculationPeriodAmount().getCalculation().getDayCountFraction()!=null){
            dayCount=DTCCMappings.getDayCountsFromDTCC(swapStream.getCalculationPeriodAmount().getCalculation().getDayCountFraction().getValue());
        } else {
            dayCount="30/360";
        }
        scheduler.setDaycount(dayCount);

        String adjustment=DateUtils.ADJUSTMENT_MODIFIED_FOLLOWING;
        if (swapStream.getPaymentDates().getPaymentDatesAdjustments()!=null && swapStream.getPaymentDates().getPaymentDatesAdjustments().getBusinessDayConvention()!=null){
            adjustment=DTCCMappings.getAdjustmentFromDTCC(swapStream.getPaymentDates().getPaymentDatesAdjustments().getBusinessDayConvention().name());
        }
        scheduler.setAdjustment(adjustment);
        scheduler.setPaymentLag(payLag.intValue());
        scheduler.setIsPayInArrears(true);
        scheduler.setIsPayLagBusDays(true);
//        scheduler.setFirstDate(swapStream.getCalculationPeriodDates().getEffectiveDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
        product.setScheduler(scheduler);

        return product;
     }
}
