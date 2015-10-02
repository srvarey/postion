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

import com.l2fprod.common.propertysheet.PropertySheet;
import java.beans.PropertyDescriptor;
import java.util.Collection;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.ProductBeanInfo;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Top component which displays trade properties.
 */
@ConvertAsProperties(    dtd = "-//org.gaia.gui.trades//TradePropertiesEditor//EN", autostore = false)
@TopComponent.Description(    preferredID = "TradePropertiesEditorTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.TradePropertiesEditorTopComponent")
@ActionReference(path = "Menu/File" /*, position = 333 */)
@TopComponent.OpenActionRegistration(    displayName = "#CTL_TradePropertiesEditorAction", preferredID = "TradePropertiesEditorTopComponent")
@Messages({"CTL_TradePropertiesEditorAction=Trade Properties Editor", "CTL_TradePropertiesEditorTopComponent=Trade Properties Editor Window", "HINT_TradePropertiesEditorTopComponent=This is a Trade Properties Editor window"})
public final class TradePropertiesEditorTopComponent extends TopComponent implements LookupListener {

    private TradeEntityJPanel tradeEntityJPanel = new TradeEntityJPanel();
    private TradeAttributesPanel tradeAttributesJPanel = new TradeAttributesPanel();
    private TradeVersionPanel tradeVersionPanel = new TradeVersionPanel();
    private TradeGroupPanel tradeGroupPanel = new TradeGroupPanel();
    private Lookup.Result<Trade> result = null;

    public TradePropertiesEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_TradePropertiesEditorTopComponent());
        setToolTipText(Bundle.HINT_TradePropertiesEditorTopComponent());
        tradeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(TradePropertiesEditorTopComponent.class, "TradePropertiesEditorTopComponent.tradeEntityJPanel.TabConstraints.tabTitle"), tradeEntityJPanel);
        tradeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(TradePropertiesEditorTopComponent.class, "TradePropertiesEditorTopComponent.tradeAttributesJPanel.TabConstraints.tabTitle"), tradeAttributesJPanel);
        tradeTabbedPane.addTab("Trade Groups", tradeGroupPanel);
        tradeTabbedPane.addTab("Trade Version", tradeVersionPanel);
        tradeTabbedPane.setShowCloseButton(false);
    }

    @Override
    /**
     * add Listener
     */
    protected void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult(Trade.class);
        result.addLookupListener(this);
    }

    @Override
    /**
     * aremove Listener
     */
    public void componentClosed() {
        result.removeLookupListener(this);
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection<? extends Trade> allEvents = result.allInstances();
        if (!allEvents.isEmpty()) {
            Trade event = allEvents.iterator().next();
            setTrade(event);
        }
    }

    /**
     * add trade info
     * @param trade
     */
    public void setTrade(Trade trade) {
        if (trade!= null) {
            String label=StringUtils.EMPTY_STRING;
            // trade
            TradeBeanInfo tradeBeanInfo = new TradeBeanInfo();
            PropertyDescriptor[] beanProperties = tradeBeanInfo.getPropertyDescriptors();
            tradePropertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
            tradePropertySheetPanel.setDescriptionVisible(true);
            tradePropertySheetPanel.setSortingProperties(false);
            tradePropertySheetPanel.setRestoreToggleStates(true);
            tradePropertySheetPanel.setSortingCategories(false);
            tradePropertySheetPanel.setBeanInfo(tradeBeanInfo);
            tradePropertySheetPanel.setProperties(beanProperties);
            tradePropertySheetPanel.readFromObject(trade);

            // product
            ProductBeanInfo productBean = new ProductBeanInfo();
            productPropertySheetPanel.setBeanInfo(productBean);
            productPropertySheetPanel.setProperties(productBean.getPropertyDescriptors());
            productPropertySheetPanel.readFromObject(trade.getProduct());
            productPropertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
            productPropertySheetPanel.setDescriptionVisible(true);
            productPropertySheetPanel.setSortingProperties(false);
            productPropertySheetPanel.setRestoreToggleStates(true);
            productPropertySheetPanel.setSortingCategories(false);
            if (trade.getProduct()!=null){
                tradeTabbedPane.setTitleAt(1, trade.getProduct().getProductType());
                label+=trade.getProduct().getProductType();
            }

            // other trade related
            tradeEntityJPanel.setTradeEntities(trade);

            tradeAttributesJPanel.setTradeAttribute(trade);

            tradeGroupPanel.setTradeGroup(trade);

            tradeVersionPanel.setTrade(trade);

            if (trade.getTradeId()!=null){
               label+=StringUtils.SPACE+trade.getTradeId();
            } else {
               label="new "+label;
            }
            jocLabel1.setText(label);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tradeTabbedPane = new com.xzq.osc.JocTabbedPane();
        productPropertySheetPanel = new com.l2fprod.common.propertysheet.PropertySheetPanel();
        tradePropertySheetPanel = new com.l2fprod.common.propertysheet.PropertySheetPanel();
        jPanel1 = new javax.swing.JPanel();
        jocLabel1 = new com.xzq.osc.JocLabel();

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        tradeTabbedPane.setBackground(new java.awt.Color(216, 216, 254));

        productPropertySheetPanel.setBackground(new java.awt.Color(216, 216, 223));
        tradeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(TradePropertiesEditorTopComponent.class, "TradePropertiesEditorTopComponent.productPropertySheetPanel.TabConstraints.tabTitle"), productPropertySheetPanel); // NOI18N

        tradePropertySheetPanel.setBackground(new java.awt.Color(216, 216, 254));
        tradeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(TradePropertiesEditorTopComponent.class, "TradePropertiesEditorTopComponent.tradePropertySheetPanel.TabConstraints.tabTitle"), tradePropertySheetPanel); // NOI18N

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jocLabel1, org.openide.util.NbBundle.getMessage(TradePropertiesEditorTopComponent.class, "TradePropertiesEditorTopComponent.jocLabel1.text_1")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jocLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jocLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tradeTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tradeTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private com.xzq.osc.JocLabel jocLabel1;
    private com.l2fprod.common.propertysheet.PropertySheetPanel productPropertySheetPanel;
    private com.l2fprod.common.propertysheet.PropertySheetPanel tradePropertySheetPanel;
    private com.xzq.osc.JocTabbedPane tradeTabbedPane;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
