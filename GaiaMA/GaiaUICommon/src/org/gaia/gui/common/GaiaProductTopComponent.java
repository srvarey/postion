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
package org.gaia.gui.common;

import java.util.List;
import javax.swing.JOptionPane;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.PricingMaps;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Benjamin Frerejean
 */
public class GaiaProductTopComponent extends GaiaTopComponent {

    protected Product product = new Product();
    protected String pricingEnv = null;
    protected PricingMaps maps = null;
    protected ProductReferencesPanel productRefsPanel = null;
    protected List<ProductReference> productReferences = null;
    protected final InstanceContent content = new InstanceContent();
    protected List<ProductEvent> productEventList = null;

    public GaiaProductTopComponent() {

        associateLookup(new AbstractLookup(content));
    }

    public ProductReferencesPanel getProductRef() {
        return productRefsPanel;
    }

    public void setProductRefsPanel(ProductReferencesPanel productRef) {
        this.productRefsPanel = productRef;
    }

    public List<ProductReference> getProductReferences() {
        return productReferences;
    }

    public void setProductReferences(List<ProductReference> productReferences) {
        this.productReferences = productReferences;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getPricingEnv() {
        return pricingEnv;
    }

    public void setPricingEnv(String pricingEnv) {
        this.pricingEnv = pricingEnv;
    }

    public PricingMaps getMaps() {
        return maps;
    }

    public void setMaps(PricingMaps maps) {
        this.maps = maps;
    }

    public void loadProductEvents() {
        if (product != null && product.getId() != null) {
            productEventList = (List<ProductEvent>) DAOCallerAgent.callMethod(ProductAccessor.class,
                    ProductAccessor.GET_PRODUCT_EVENTS_BY_PRODUCT_ID, product.getProductId());
            WindowManager wm = WindowManager.getDefault();
            ProductEventTopComponent productEventTopComponent = (ProductEventTopComponent) wm.findTopComponent(ProductEventTopComponent.class.getSimpleName());
            productEventTopComponent.setProductEventList(productEventList);
            productEventTopComponent.open();
            List<Trade> trades = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADES_BY_PRODUCT_ID, product.getId());
            if (trades.size() == 1) {
                productEventTopComponent.setDisplayName("Events on " + product.getProductType() + " id " + trades.get(0).getTradeId());
            } else {
                productEventTopComponent.setDisplayName("Events on " + product.getProductType() + StringUtils.SPACE + product.getShortName());
            }
            productEventTopComponent.requestActive();
        } else {
            JOptionPane.showMessageDialog(this, "Please save your trade before.");
        }
    }

    @Override
    public String getDisplayName() {
        StringBuilder _displayName = new StringBuilder(getName());
        if (getProduct() != null) {

            if (getProduct().getProductId() != null) {
                _displayName.append(StringUtils.SPACE).append(getProduct().getProductId());
            }
        }

        return _displayName.toString();
    }

}
