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
package org.gaia.dao.pricing;

import java.io.Serializable;
import java.rmi.server.UID;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.domain.utils.IPriceable;

/**
 *
 * @author Benjamin Frerejean
 *
 */

public class PricingMaps implements Serializable{
    List<IPriceable> listPriceables;
    /**
     *  load trade pricers map : trade id / ( measure group / pricer name) .
     */
    Map<Integer, Map<MeasuresAccessor.MeasureGroup, IPricer>> priceablePricersMap;
     /**
      *   pricer map  : pricer name / pricer
      */
    Map<String, IPricer> pricersMap = new HashMap();
     /**
      *  observable id / observable.
      */
    Map<Integer, IObservable> globalObservablesMap = new HashMap();
    /**
     *   trade id / observable list.
     */
    Map<Integer, List<IObservable>> priceablesObservables = new HashMap();
   
    /**
     *  trade_id / pricing Agent.
     */
    Map<Integer,UID> pricingAgents= new HashMap();

    public List<IPriceable> getListPriceables() {
        return listPriceables;
    }

    public void setListPriceables(List<IPriceable> listPriceables) {
        this.listPriceables = listPriceables;
    }

    public Map<Integer, Map<MeasureGroup, IPricer>> getPriceablePricersMap() {
        return priceablePricersMap;
    }

    public void setPriceablePricersMap(Map<Integer, Map<MeasureGroup, IPricer>> priceablePricersMap) {
        this.priceablePricersMap = priceablePricersMap;
    }

    public Map<String, IPricer> getPricersMap() {
        return pricersMap;
    }

    public void setPricersMap(Map<String, IPricer> pricersMap) {
        this.pricersMap = pricersMap;
    }

    public Map<Integer, IObservable> getGlobalObservablesMap() {
        return globalObservablesMap;
    }

    public void setGlobalObservablesMap(Map<Integer, IObservable> globalObservablesMap) {
        this.globalObservablesMap=globalObservablesMap;
    }



}
