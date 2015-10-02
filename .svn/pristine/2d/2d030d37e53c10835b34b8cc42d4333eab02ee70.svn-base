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
package org.gaia.gui.common;

import com.l2fprod.common.beans.BaseBeanInfo;
import org.gaia.domain.trades.Product;

/**
 *
 * @author User
 */
public class ProductBeanInfo extends BaseBeanInfo {

    /** Bean descriptor
      * Property identifiers
      */
    private static final String[] PROPERTY_productId = new String[]{"productId", "Product id"};
    private static final String[] PROPERTY_longName = new String[]{"longName", "Long name"};
    private static final String[] PROPERTY_quantityType = new String[]{"quantityType", "Quantity Type"};
    private static final String[] PROPERTY_isAsset = new String[]{"isAsset", "Is asset"};
    private static final String[] PROPERTY_maturityDate = new String[]{"maturityDate", "Maturity date"};
    private static final String[] PROPERTY_notionalCurrency = new String[]{"notionalCurrency", "Notional currency"};
    private static final String[] PROPERTY_notionalMultiplier = new String[]{"notionalMultiplier", "Notional Multiplier"};
    private static final String[] PROPERTY_productType = new String[]{"productType", "Product type"};
    private static final String[] PROPERTY_quotationType = new String[]{"quotationType", "Quotation type"};
    private static final String[] PROPERTY_shortName = new String[]{"shortName", "Short name"};
    private static final String[] PROPERTY_startDate = new String[]{"startDate", "Start date"};
    private static final String[] PROPERTY_comment = new String[]{"comment", "Comment"};
    private static final String[] PROPERTY_status = new String[]{"status", "Status"};
    private static final String[] PROPERTY_userId = new String[]{"userId", "User id"};
    private static final String[] PROPERTY_updateDateTime = new String[]{"updateDateTime", "Update datetime"};
    private static final String[] PROPERTY_creationDateTime = new String[]{"creationDateTime", "Creation dateTime"};
    private static final String[] PROPERTY_productVersion = new String[]{"productVersion", "Version"};
    private static final String[] PROPERTY_valueDate = new String[]{"valueDate", "Value date"};

    private static final String CATEGORY_basic_info = "Basic Info";
    private static final String CATEGORY_more_details = "More details";
    private static final String CATEGORY_audit = "Audit";

    public ProductBeanInfo() {
        super(Product.class);
        addProperty(PROPERTY_productId[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_productId[1]);
        addProperty(PROPERTY_longName[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_longName[1]);
        addProperty(PROPERTY_shortName[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_shortName[1]);
        addProperty(PROPERTY_quantityType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_quantityType[1]);
        addProperty(PROPERTY_isAsset[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_isAsset[1]);
        addProperty(PROPERTY_valueDate[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_valueDate[1]);
        addProperty(PROPERTY_maturityDate[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_maturityDate[1]);
        addProperty(PROPERTY_notionalCurrency[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_notionalCurrency[1]);
        addProperty(PROPERTY_notionalMultiplier[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_notionalMultiplier[1]);
        addProperty(PROPERTY_productType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_productType[1]);
        addProperty(PROPERTY_quotationType[0]).setCategory(CATEGORY_basic_info).setDisplayName(PROPERTY_quotationType[1]);

        addProperty(PROPERTY_userId[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_userId[1]);
        addProperty(PROPERTY_comment[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_comment[1]);
        addProperty(PROPERTY_status[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_status[1]);
        addProperty(PROPERTY_startDate[0]).setCategory(CATEGORY_more_details).setDisplayName(PROPERTY_startDate[1]);


        addProperty(PROPERTY_updateDateTime[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_updateDateTime[1]);
        addProperty(PROPERTY_creationDateTime[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_creationDateTime[1]);
        addProperty(PROPERTY_productVersion[0]).setCategory(CATEGORY_audit).setDisplayName(PROPERTY_productVersion[1]);

    }
}
