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
package org.gaia.gui.trades;

import com.l2fprod.common.beans.BaseBeanInfo;
import org.gaia.domain.trades.Trade;

/**
 *
 * @author Jawhar Kamoun
 */
public class TradeBeanInfo extends BaseBeanInfo {

    /** Property identifiers */
    private static final String[] PROPERTY_tradeId = new String[]{"tradeId", "Trade id"};
    private static final String[] PROPERTY_internalCounterparty = new String[]{"internalCounterparty", "InternalCounterparty"};
    private static final String[] PROPERTY_counterparty = new String[]{"counterparty", "Counterparty"};
    private static final String[] PROPERTY_quantity = new String[]{"quantity", "Quantity"};
    private static final String[] PROPERTY_quantityType = new String[]{"quantityType", "Quantity Type"};
    private static final String[] PROPERTY_product = new String[]{"product", "Product"};
    private static final String[] PROPERTY_tradeDate = new String[]{"tradeDate", "Trade date"};
    private static final String[] PROPERTY_valueDate = new String[]{"valueDate", "Settlement date"};
    private static final String[] PROPERTY_price = new String[]{"price", "Price"};
    private static final String[] PROPERTY_priceType = new String[]{"priceType", "Price type"};
    private static final String[] PROPERTY_priceCurrency = new String[]{"priceCurrency", "Price currency"};
    private static final String[] PROPERTY_amount = new String[]{"amount", "Amount"};
    private static final String[] PROPERTY_settlementCurrency = new String[]{"settlementCurrency", "Settlement currency"};
    private static final String[] PROPERTY_forexRate = new String[]{"forexRate", "Forex rate"};
    private static final String[] PROPERTY_trader = new String[]{"trader", "Trader"};
    private static final String[] PROPERTY_comment = new String[]{"comment", "Comment"};
    private static final String[] PROPERTY_isCollateral = new String[]{"isCollateral", "Is collateral"};
    private static final String[] PROPERTY_status = new String[]{"status", "Status"};
    private static final String[] PROPERTY_lifecycle_status = new String[]{"lifeCycleStatus", "lifecycle status"};
    private static final String[] PROPERTY_tradeEntityCollection = new String[]{"tradeEntityCollection", "Trade entities"};
    private static final String[] PROPERTY_tradeTime = new String[]{"tradeTime", "Trade time"};
    private static final String[] PROPERTY_updateDateTime = new String[]{"updateDateTime", "Update datetime"};
    private static final String[] PROPERTY_creationDateTime = new String[]{"creationDateTime", "Creation dateTime"};
    private static final String[] PROPERTY_tradeVersion = new String[]{"tradeVersion", "Trade Version"};
    private static final String[] PROPERTY_tradeType = new String[]{"tradeType", "Trade type"};

    private static final String CATEGORY_basic_info = "Basic Info";
    private static final String CATEGORY_more_details = "More details";
    private static final String CATEGORY_audit = "Audit";

    public TradeBeanInfo() {
        super(Trade.class);
        addProperty(PROPERTY_tradeId[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_tradeId[1]);
        addProperty(PROPERTY_tradeType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_tradeType[1]);
        addProperty(PROPERTY_internalCounterparty[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_internalCounterparty[1]);
        addProperty(PROPERTY_counterparty[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_counterparty[1]);
        addProperty(PROPERTY_quantity[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_quantity[1]);
        addProperty(PROPERTY_quantityType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_quantityType[1]);
        addProperty(PROPERTY_product[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_product[1]);
        addProperty(PROPERTY_tradeDate[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_tradeDate[1]);
        addProperty(PROPERTY_valueDate[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_valueDate[1]);
        addProperty(PROPERTY_price[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_price[1]);
        addProperty(PROPERTY_priceType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_priceType[1]);
        addProperty(PROPERTY_priceCurrency[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_priceCurrency[1]);
        addProperty(PROPERTY_amount[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_amount[1]);
        addProperty(PROPERTY_settlementCurrency[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_settlementCurrency[1]);
        addProperty(PROPERTY_forexRate[0]).setReadOnly().setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_forexRate[1]);

        addProperty(PROPERTY_trader[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_trader[1]);
        addProperty(PROPERTY_isCollateral[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_isCollateral[1]);
        addProperty(PROPERTY_comment[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_comment[1]);
        addProperty(PROPERTY_status[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_status[1]);
        addProperty(PROPERTY_lifecycle_status[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_lifecycle_status[1]);
        addProperty(PROPERTY_tradeEntityCollection[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_tradeEntityCollection[1]);

        addProperty(PROPERTY_tradeTime[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_tradeTime[1]);
        addProperty(PROPERTY_updateDateTime[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_updateDateTime[1]);
        addProperty(PROPERTY_creationDateTime[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_creationDateTime[1]);
        addProperty(PROPERTY_tradeVersion[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_tradeVersion[1]);

    }
}
