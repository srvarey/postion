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
package org.gaia.dao.utils;

import java.util.Date;
import org.gaia.domain.utils.BatchLog;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 * @author Benjamin Frerejean
 */

public class BatchLogAccessor {

    public static final String CREATE_BATCH_LOG="createBatchLog";
    public static Integer createBatchLog(String batchName,Class paramObjectClass,Integer paramObjectId, Date valueDate,String description){
        BatchLog batchLog=new BatchLog();
        if (batchName==null){
            batchName=paramObjectClass.getSimpleName();
        }
        batchLog.setBatchName(batchName);
        batchLog.setParamObjectClass(paramObjectClass.getName());
        batchLog.setParamObjectId(paramObjectId);
        batchLog.setValueDate(valueDate);
        batchLog.setStartDate(new Date());
        batchLog.setDescription(description);

        batchLog= (BatchLog)HibernateUtil.storeAndReturnObject(batchLog);
        if (batchLog!=null){
            return batchLog.getBatchLogId();
        }
        return null;
    }

    public static final String END_BATCH_LOG="endBatchLog";
    public static void endBatchLog(Integer logId){
        BatchLog log =(BatchLog)HibernateUtil.getObject(BatchLog.class, logId);
        log.setEndDate(new Date());
        HibernateUtil.storeObject(log);
    }

    public static final String GET_LAST_BATCH_LOG="getLastBatchLog";
    public static BatchLog getLastBatchLog(Class paramObject,Integer paramObjectId){
        Date date=getLastBatchLogDate(paramObject,paramObjectId);
        if (date!=null){
            return (BatchLog)HibernateUtil.getObjectWithQuery("from BatchLog bl where bl.paramObjectClass='"+paramObject.getName()+"' "
                     + " and bl.paramObjectId=" + paramObjectId+ " and bl.valueDate='"+HibernateUtil.dateFormat.format(date)+StringUtils.QUOTE);
        }
        return null;
    }

    public static Date getLastBatchLogDate(Class paramObject,Integer paramObjectId){
        String query = "select max(bl.valueDate) from BatchLog bl where bl.paramObjectClass='"+paramObject.getName()+StringUtils.QUOTE
            + " and bl.paramObjectId=" + paramObjectId;
        return (Date) HibernateUtil.getObjectWithQuery(query);
    }
}
