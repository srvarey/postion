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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.gaia.dao.jade.MarketDataAgent;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketDataSource;

/**
 *
 * @author Benjamin frerejean
 */
public class MarketDataSourceUtils {

    private static Logger logger= Logger.getLogger(MarketDataSourceUtils.class.getName());

    public static final String GET_MARKET_DATA_SOURCES="getMarketDataSources";
    public static List<String> getMarketDataSources(){
        return HibernateUtil.getStringListWithQuery("select mds.marketDataSourceName from MarketDataSource mds");
    }

    public static final String GET_MARKET_DATA_SOURCE_BY_NAME="getMarketDataSourceByName";
    public static MarketDataSource getMarketDataSourceByName(String name){
        return (MarketDataSource)HibernateUtil.getObjectWithQueryWithCache("from MarketDataSource mds where mds.marketDataSourceName='"+name+StringUtils.QUOTE);
    }

    public static final String STORE_MARKET_DATA_SOURCE="storeMarketDataSource";
    public static void storeMarketDataSource(MarketDataSource marketDataSource){
        HibernateUtil.storeObject(marketDataSource);
    }

    public static final String DELETE_MARKET_DATA_SOURCE="deleteMarketDataSource";
    public static void deleteMarketDataSource(MarketDataSource marketDataSource){
        HibernateUtil.deleteObject(marketDataSource);
    }

    public static List<IMarketDataConnector> launchMarketDataConnectors(MarketDataAgent myAgent){
        List<IMarketDataConnector> connectors =new ArrayList();
        List<MarketDataSource> marketDataSources= (List)HibernateUtil.getObjectsWithQuery("from MarketDataSource");
        for (MarketDataSource marketDataSource : marketDataSources){
            try {
                Class connectorClass=Class.forName(marketDataSource.getClassName());
                Constructor<?> ctor = connectorClass.getConstructor();
                IMarketDataConnector connector=(IMarketDataConnector)ctor.newInstance(new Object[] {});
                if (connector.createSession(marketDataSource,myAgent)){
                    connectors.add(connector);
                }

            } catch (Exception e){
                logger.severe(StringUtils.formatErrorMessage(e));
            }
        }
        return connectors;
    }

    public static List<String> getMarketDataSourceLinkCodes(String marketDataSourceName){
        return  HibernateUtil.getStringListWithQuery("select code.productCode from MarketDataCode code where code.marketDataSource.marketDataSourceName='"+marketDataSourceName+StringUtils.QUOTE);
    }

    public static Map<String,Integer> getMarketCodesMap(String marketDataSourceName){
        Map<String,Integer> marketDataCodes = new HashMap();
        List<Object[]> sqlResult= HibernateUtil.getObjectsWithQuery("select code.productCode,code.product.productId from MarketDataCode code where code.marketDataSource.marketDataSourceName='"+marketDataSourceName+StringUtils.QUOTE);
        for (Object[] objectArray : sqlResult){
            marketDataCodes.put((String)objectArray[0], (Integer)objectArray[1]);
        }
        return marketDataCodes;
    }

    public static String GET_MARKET_CODES="getMarketCodes";
    public static List<MarketDataCode> getMarketCodes(Integer productId){
        return HibernateUtil.getObjectsWithQuery("from MarketDataCode code where code.product.productId="+productId);
    }

    public static Map<String,MarketDataCode> getMarketCodesMap(Integer productId){
        Map output=new HashMap();
        List<MarketDataCode> codes= HibernateUtil.getObjectsWithQuery("from MarketDataCode code where code.product.productId="+productId);
        for (MarketDataCode code : codes){
            output.put(code.getMarketDataSource().getClassName(), code);
        }
        return output;
    }

    public static String GET_MARKET_CODE="getMarketCode";
    public static MarketDataCode getMarketCode(Integer productId,String sourceName){
        return (MarketDataCode)HibernateUtil.getObjectWithQuery("from MarketDataCode code where code.product.productId="+productId+" and code.marketDataSource.marketDataSourceName='"+sourceName+StringUtils.QUOTE);
    }

    public static final String GET_MARKET_DATA_SOURCE_NAMES_BY_FILTER_ID="getMarketDataSourceNamesbyFilterId";
    /**
     *  return names of filter .
     * @param objectType
     * @return
     */
    public static List<String> getMarketDataSourceNamesbyFilterId(Integer filterId ) {
        return HibernateUtil.getObjectsWithQuery("select mds.marketDataSourceName from MarketDataSource mds inner join mds.filter f where f.filterId=" + filterId );
    }

}
