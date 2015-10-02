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
package org.gaia.gui.assets;

import java.util.Collection;
import org.gaia.domain.legalEntity.LegalEntity;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Top component which displays trade properties.
 */
@ConvertAsProperties(    dtd = "-//org.gaia.gui.legalentity//LegalEntitiesEditor//EN",autostore = false)
@TopComponent.Description(    preferredID = "LegalEntitiesEditorTopComponent", iconBase="org/gaia/gui/assets/entities",persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.legalentity.LegalEntitiesEditorTopComponent")
@TopComponent.OpenActionRegistration(    displayName = "#CTL_LegalEntitiesEditorAction",preferredID = "LegalEntitiesEditorTopComponent")
@Messages({    "CTL_LegalEntitiesEditorAction=Legal Entities Properties Editor","CTL_LegalEntitiesEditorTopComponent=Legal Entities Properties Editor Window","HINT_LegalEntitiesEditorTopComponent=This is a Legal Entities Properties Editor window"})

public final class LegalEntitiesEditorTopComponent extends TopComponent implements LookupListener {

    private LegalEntitiesAttributesPanel attributesJPanel = new LegalEntitiesAttributesPanel();
    private Lookup.Result<LegalEntity> result = null;

    public LegalEntitiesEditorTopComponent() {
        initComponents();
        setName(Bundle.CTL_LegalEntitiesEditorTopComponent());
        setToolTipText(Bundle.HINT_LegalEntitiesEditorTopComponent());

        tradeTabbedPane.addTab(org.openide.util.NbBundle.getMessage(LegalEntitiesEditorTopComponent.class, "LegalEntitiesEditorTopComponent.attributesJPanel.TabConstraints.tabTitle"), attributesJPanel);
        tradeTabbedPane.setShowCloseButton(false);
    }

    @Override
     /** add Listener */
    protected void componentOpened() {
        result = Utilities.actionsGlobalContext().lookupResult(LegalEntity.class);
        result.addLookupListener(this);
    }

    @Override
    /** aremove Listener */
    public void componentClosed() {
        result.removeLookupListener(this);
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection<? extends LegalEntity> allEvents = result.allInstances();
        if (!allEvents.isEmpty()) {
            LegalEntity event = allEvents.iterator().next();
            setEntity(event);
        }
    }

    /** add trade info */
    public void setEntity(LegalEntity entity) {
        if (entity.getLegalEntityId() != null) {
//            TradeBeanInfo tradeBeanInfo = new TradeBeanInfo();

//            PropertyDescriptor[] beanProperties = tradeBeanInfo.getPropertyDescriptors();

//            ProductBeanInfo productBean = new ProductBeanInfo();
//            productPropertySheetPanel.setBeanInfo(productBean);
//            productPropertySheetPanel.setProperties(productBean.getPropertyDescriptors());
//            productPropertySheetPanel.readFromObject(trade.getProduct());
//            productPropertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
//            productPropertySheetPanel.setDescriptionVisible(true);
//            productPropertySheetPanel.setSortingProperties(false);
//            productPropertySheetPanel.setRestoreToggleStates(true);
//            productPropertySheetPanel.setSortingCategories(false);
//            tradeTabbedPane.setTitleAt(1, trade.getProduct().getProductType());

//            tradeEntityJPanel.setTradeEntities(trade);
//            tradePropertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
//            tradePropertySheetPanel.setDescriptionVisible(true);
//            tradePropertySheetPanel.setSortingProperties(false);
//            tradePropertySheetPanel.setRestoreToggleStates(true);
//            tradePropertySheetPanel.setSortingCategories(false);
//            tradePropertySheetPanel.setBeanInfo(tradeBeanInfo);
//            tradePropertySheetPanel.setProperties(beanProperties);
//            tradePropertySheetPanel.readFromObject(trade);

            attributesJPanel.setAttribute(entity);
//            tradePropertySheetPanel.setMode(PropertySheet.VIEW_AS_CATEGORIES);
//            tradePropertySheetPanel.setDescriptionVisible(true);
//            tradePropertySheetPanel.setSortingProperties(false);
//            tradePropertySheetPanel.setRestoreToggleStates(true);
//            tradePropertySheetPanel.setSortingCategories(false);
//            tradePropertySheetPanel.setBeanInfo(tradeBeanInfo);
//            tradePropertySheetPanel.setProperties(beanProperties);
//            tradePropertySheetPanel.readFromObject(trade);

            jocLabel1.setText(entity.getShortName());
        }

    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        tradeTabbedPane = new com.xzq.osc.JocTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jocLabel1 = new com.xzq.osc.JocLabel();

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jocLabel1.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jocLabel1, org.openide.util.NbBundle.getMessage(LegalEntitiesEditorTopComponent.class, "LegalEntitiesEditorTopComponent.jocLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(76, 76, 76)
                .addComponent(jocLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tradeTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tradeTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private com.xzq.osc.JocTabbedPane tradeTabbedPane;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
