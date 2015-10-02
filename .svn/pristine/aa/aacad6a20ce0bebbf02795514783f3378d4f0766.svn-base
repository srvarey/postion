
package org.gaia.dao.trades.schedulers;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;

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
 *

/**
 * @author Benjamin Frerejean
 */

public class FXOptionScheduler implements IProductScheduler{
    private static final Logger logger = Logger.getLogger(FXOptionScheduler.class);

    @Override
    public Schedule buildSchedule(Product product) {
        Schedule schedule=new Schedule();
        Product underlying =null;
        if (product.loadSingleUnderlying() != null) {
            underlying = product.loadSingleUnderlying();
        } else {
            logger.error("ERROR : The option "+product.getProductId()+" has no underlying");
        }
        Short payLag=product.getSettlementDelay();
        if (payLag==null){
            payLag=new Short("2");
        }
        Date payDate=DateUtils.addOpenDay(product.getMaturityDate(),payLag );
        /* add schedule line if:
         *  - european
         *  +
         *    - no barrier
         *    - or out barrier and not crossed
         *    - or in barrier and crossed
        */
        boolean isCrossed=false;
        Trade trade=null;
        try{
            Collection<Trade> lazyTrades=product.getTrades();
            if (lazyTrades!=null&& lazyTrades.size()>0){
                trade=lazyTrades.iterator().next();
            }
        } catch (Exception e){}
        if (trade==null){
            List<Trade> trades = TradeAccessor.getTradesByProduct(product);
            if (trades!=null && trades.size()>0){
                trade=trades.get(0);
            }
        }
        if (trade!=null){
            if (trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.ACTIVATED.name)
              ||trade.getLifeCycleStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.DEACTIVATED.name)){
                        isCrossed=true;
            }
        }
        if (product.getProductForex().getExerciceType()!=null
          &&product.getProductForex().getExerciceType().equalsIgnoreCase(ProductConst.ExerciseType.European.name())
          && (
                StringUtils.isEmptyString(product.getProductForex().getBarrierType())
                ||(product.getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndOut.display)&&!isCrossed)
                ||(product.getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndOut.display)&&!isCrossed)
                ||(product.getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)&&isCrossed)
                ||(product.getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)&&isCrossed))){
                    ScheduleLine line = new ScheduleLine(product.getMaturityDate(),product.getProductForex().getCurrency2().getShortName(),
                                payDate,product,underlying,product.getStartDate(),product.getMaturityDate());
                    schedule.getScheduleLines().add(line);
        }
        return schedule;
    }

    @Override
    public BigDecimal calculateAmount(ScheduleLine line) {
        if (line.getProduct().getProductForex().getOptionStyle().equalsIgnoreCase(ProductConst.OptionType.Call.name())){
            return line.getFixing().subtract(line.getProduct().getProductForex().getStrike()).multiply(line.getNotional());
        } else {
            return line.getProduct().getProductForex().getStrike().subtract(line.getFixing()).multiply(line.getNotional());
        }
    }

}
