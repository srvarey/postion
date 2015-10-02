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
package org.gaia.dao.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import static org.gaia.dao.utils.MappingsAccessor.getMappingByNameAndKey;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.SnapshotReport;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.BatchLog;
import org.gaia.domain.utils.Mapping;
import org.gaia.domain.utils.Message;

/**
 *
 * @author Benjamin Frerejean
 */
public class ReportUtils {

    /**
     * An enum. More detailed enum description.
     */
    public static final Map<Class, String> mapReportClass;

    static {
        mapReportClass = new HashMap<>();
        mapReportClass.put(Position.class, Position.class.getSimpleName());
        mapReportClass.put(Trade.class, Trade.class.getSimpleName());
        mapReportClass.put(Flow.class, Flow.class.getSimpleName());
        mapReportClass.put(SnapshotReport.class, SnapshotReport.class.getSimpleName());
        mapReportClass.put(Product.class, Product.class.getSimpleName());
        mapReportClass.put(ProductEvent.class, ProductEvent.class.getSimpleName());
        mapReportClass.put(BatchLog.class, BatchLog.class.getSimpleName());
    }

    public static List<String> getReportObjectList() {
        List<String> objectList = new ArrayList();
        objectList.add(mapReportClass.get(Position.class));
        objectList.add(mapReportClass.get(Trade.class));
        objectList.add(mapReportClass.get(Flow.class));
        objectList.add(mapReportClass.get(SnapshotReport.class));
        objectList.add(mapReportClass.get(Product.class));
        objectList.add(mapReportClass.get(ProductEvent.class));
        objectList.add(mapReportClass.get(BatchLog.class));
        return objectList;
    }

    public static Class getObjectType(String reportType) {
        for (Map.Entry<Class, String> entry : mapReportClass.entrySet()) {
            if (entry.getValue().equalsIgnoreCase(reportType)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public static String getReportType(Class objectType) {
        return mapReportClass.get(objectType);
    }

    public static final String GET_DEFAULT_TEMPLATE = "getDefaultTemplate";

    public static String getDefaultTemplate(String reportType, Integer userId) {
        String res = StringUtils.EMPTY_STRING;
        Mapping mapping = null;
        if (reportType.equals(Position.class.getSimpleName())) {
            res = UserAccessor.getDefaultPositionView(userId);
            if (res == null) {
                mapping = getMappingByNameAndKey("defaults", "position report template");
            }
        } else if (reportType.equals(Trade.class.getSimpleName())) {
            res = UserAccessor.getDefaultTradeView(userId);
            if (res == null) {
                mapping = getMappingByNameAndKey("defaults", "trade report template");
            }
        } else if (reportType.equals(Flow.class.getSimpleName())) {
            mapping = getMappingByNameAndKey("defaults", "flow report template");
        } else if (reportType.equals(SnapshotReport.class.getSimpleName())) {
            mapping = getMappingByNameAndKey("defaults", "snapshot report template");
        } else if (reportType.equals(Product.class.getSimpleName())) {
            mapping = getMappingByNameAndKey("defaults", "product report template");
        } else if (reportType.equals(ProductEvent.class.getSimpleName())) {
            mapping = getMappingByNameAndKey("defaults", "product event report template");
        }else if (reportType.equals(BatchLog.class.getSimpleName())) {
            mapping = getMappingByNameAndKey("defaults", "batch log report template");
        }
        if (mapping != null) {
            return mapping.getValue();
        } else {
            return res;
        }
    }

    public static Message makeMessage(String messageText) {
        return new Message(null, null, messageText);
    }

    public static List<Message> makeMessageList(String messageText) {
        List<Message> messages = new ArrayList();
        messages.add(new Message(null, null, messageText));
        return messages;
    }

    public static Message makeErrorMessage(Exception e) {
        return new Message(null, null, StringUtils.formatErrorMessage(e));
    }

    public static List<Message> makeErrorMessageList(Exception e) {
        List<Message> messages = new ArrayList();
        messages.add(new Message(null, null, StringUtils.formatErrorMessage(e)));
        return messages;
    }

    public static String formatMessageList(List<Message> messages) {
        String formatted = StringUtils.EMPTY_STRING;
        if (messages != null) {
            for (Message message : messages) {
                if (message.getMessageText().endsWith(StringUtils.getNewLine())) {
                    formatted += message.getMessageText();
                } else {
                    formatted += message.getMessageText() + StringUtils.getNewLine();
                }
            }
        }
        return formatted;
    }
    public static final String GET_FIELD_POSSIBLE_VALUE = "getFieldPossibleValue";

    public static List<String> getFieldPossibleValue(String fieldName, boolean isCcy, String role) {
        List<String> result = new ArrayList<>();
        if (isCcy) {
            result = CurrencyAccessor.loadCurrencyCodes();
        }
        if (!StringUtils.isEmptyString(role)) {
            result = LegalEntityAccessor.getShortNamesByRoles(StringUtils.stringToArrayList(role, StringUtils.COMMA));
        } else {
            switch (fieldName) {
                case "productType":
                    result = ProductTypeUtil.loadProductTypeName();
                    break;
                case "status":
                    result = TradeAccessor.getStatusList();
                    break;
                case "lifeCycleStatus":
                    result = TradeAccessor.getLifeCycleStatusList();
                    break;
            }
        }

        return result;
    }
}
