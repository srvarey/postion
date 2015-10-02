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
package org.gaia.gui.trades;

import java.math.BigDecimal;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays margin calls.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//MarginCall//EN", autostore = false)
@TopComponent.Description(preferredID = "MarginCallTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.MarginCallTopComponent")
@ActionReference(path = "Menu"+MenuManager.MarginCallTopComponentMenu, position = MenuManager.MarginCallTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MarginCallAction")
@Messages({"CTL_MarginCallAction=Margin Call", "CTL_MarginCallTopComponent=Margin Call", "HINT_MarginCallTopComponent=This is a Margin Call window"})
public final class MarginCallTopComponent extends AssetTradeTopComponent {

    private static final Logger logger = Logger.getLogger(MarginCallTopComponent.class);

    public MarginCallTopComponent() {
        super();
        super.isMargin(true);
    }

    @Override
    public void initContext() {
        try {
            super.initContext();
            super.isMargin(true);
            jComboBoxQuantityNotional.setSelectedItem(Trade.QuantityType.QUANTITY);
            jComboBoxQuantityNotional.setEnabled(false);

            List<String> listMarginTypes = (List) DAOCallerAgent.callMethod(DomainValuesAccessor.class, DomainValuesAccessor.GET_DOMAIN_VALUES_BY_NAME, "marginTypes");
            GUIUtils.fillCombo(tradeTypeComboBox, listMarginTypes);
            tradeTypeComboBox.addItem(Trade.TradeType.BUY_SELL.name);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    @Override
    public void loadByTradeId(Integer id) {
        jTextFieldTradeId.setText(id.toString());
        setIsTradeAudited(false);
        loadTrade();
    }

    @Override
    public String getDisplayName() {
        return "MarginCall " + super.getDisplayName();
    }

    /**
     * save trade
     */
    @Override
    public Trade saveTrade(Trade _trade) {
        if (!_trade.getIsCollateral()) {
            _trade.setIsCollateral(Boolean.TRUE);
        }
        if (isCash.isSelected()) {
            if (getProduct() == null || !getProduct().getShortName().equalsIgnoreCase(jComboBoxCurrency.getSelectedItem().toString())) {
                try {
                    setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboBoxCurrency.getSelectedItem().toString()));
                    if (getProduct() != null) {
                        _trade.setAmount(BigDecimal.ZERO);
                        _trade.setQuantityType("Notional");
                        _trade.setSettlementCurrency(getProduct().getNotionalCurrency());
                        _trade.setProduct(getProduct());
                        _trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
                        loadProduct(getProduct());
                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            } else {
                _trade.setSettlementCurrency(getProduct().getNotionalCurrency());
            }

        }
        return super.saveTrade(_trade);
    }

    @Override
    /**
     * function load trade
     */
    public void loadTrade() {
        super.loadTrade();
        fillWindowWithTrade();
    }

    @Override
    public void loadByTrade(Trade _trade) {
        super.loadByTrade(_trade);
        fillWindowWithTrade();
    }

    @Override
    /**
     * fill Window With Trade
     */
    public void fillWindowWithTrade(Trade _trade) {
        super.fillWindowWithTrade(_trade);
        if (getProduct() != null) {
            jComboBoxCurrency.setSelectedItem(getProduct().getShortName());
            if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CASH.name)) {
                assetFinderPanel.setVisible(false);
                jComboBoxCurrency.setVisible(true);
                isCash.setSelected(true);
            } else {
                assetFinderPanel.setVisible(true);
                jComboBoxCurrency.setVisible(false);
                isCash.setSelected(false);
            }
        }
        setDisplayName(getDisplayName());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    @Override
    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }

    private void fillWindowWithTrade() {
        jComboBoxCurrency.setSelectedItem(getProduct().getShortName());
        if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CASH.name)) {
            assetFinderPanel.setVisible(false);
            jComboBoxCurrency.setVisible(true);
            isCash.setSelected(true);
        } else {
            assetFinderPanel.setVisible(true);
            jComboBoxCurrency.setVisible(false);
            isCash.setSelected(false);
        }
        setDisplayName(getDisplayName());
    }
}
