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

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.MeasureGroup;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.pricing.pricers.PricersUtils;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.referentials.UserPricingPreference;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays trade properties.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//TradePricing//EN", autostore = false)
@TopComponent.Description(preferredID = "TradePricingTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.TradePricingTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_TradePricingAction", preferredID = "TradePricingTopComponent")
@Messages({"CTL_TradePricingAction=Trade Pricing", "CTL_TradePricingTopComponent=Trade Pricing", "HINT_TradePricingTopComponent=This is a Trade Pricing Window"})

public final class TradePricingTopComponent extends GaiaTopComponent implements LookupListener {

    private Lookup.Result<Trade> result = null;
    private Trade trade;
    private static final Logger logger = Logger.getLogger(TradePricingTopComponent.class);
    private ArrayList<UserPricingPreference> preferences;

    public TradePricingTopComponent() {
        initComponents();
        setName(Bundle.CTL_TradePricingTopComponent());
        setToolTipText(Bundle.HINT_TradePricingTopComponent());
        List<String> pricingEnvironments = (List) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_LIST);
        GUIUtils.fillCombo(pricingEnvComboBox, pricingEnvironments);
        String pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        pricingEnvComboBox.setSelectedItem(pricingEnv);
        jTable1.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer(null));
        jSpinnerValoDate.setDate(DateUtils.getDate());
        List<String> listPricers = (List) DAOCallerAgent.callMethod(PricersUtils.class, PricersUtils.GET_PRICERS);
        GUIUtils.fillCombo(pricerComboBox, listPricers);

    }

    @Override
    public void initContext() {
        DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerValoDate.getComponent(1));
    }

    @Override
    /**
     * add Listener
     */
    public void componentOpened() {
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
            setTrade(allEvents.iterator().next());
            setDisplayName(getDisplayName());
        }
    }

    /**
     * add trade info
     *
     * @param trade
     */
    public void setTrade(Trade trade) {
        boolean isDifferent = true;
        if (this.trade != null && this.trade.getTradeId() == trade.getId()) {
            isDifferent = false;
        }
        this.trade = trade;
        if (trade != null && trade.getProduct() != null) {
            preferences = (ArrayList) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_USER_PRICING_PREFERENCES, trade.getProduct().getProductType(), LoggedUser.getLoggedUserId());
        }
        if (isDifferent) {
            updatePricer();
        }
        price();
    }

    /**
     * calculate the price
     */
    private void price() {
        if (trade == null || trade.getProduct() == null) {
            return;
        }
        try {
            String pricingEnvName = pricingEnvComboBox.getSelectedItem().toString();
            Date valDate = jSpinnerValoDate.getDate();
            GUIUtils.emptyTable(jTable1);

            PricingEnvironment pricingEnvironment = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class,
                    PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_FROM_NAME, pricingEnvName);

            MeasuresAccessor.MeasureGroup[] groups = MeasuresAccessor.MeasureGroup.values();
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricerMaps = (Map) DAOCallerAgent.callMethodWithXMLSerialization(PricingBuilder.class,
                    PricingBuilder.LOAD_PRICER_MAP, trade, valDate, pricingEnvironment, groups);

            // override pv pricer with combo
            String pvPricername = null;
            if (pricerComboBox.getSelectedItem() != null) {
                pvPricername = pricerComboBox.getSelectedItem().toString();
            }
            // PRICE HERE
            // ==============
//            Date before = new Date();
            Map<String, BigDecimal> measures = (Map<String, BigDecimal>) DAOCallerAgent.callMethod(PricingBuilder.class,
                    PricingBuilder.GET_TRADE_PRICING, trade, valDate, pricingEnvName, pvPricername, preferences);
//            Date after = new Date();
//            float lag = after.getTime() - before.getTime();
//            logger.info("Priced " + trade.getTradeId() + " in " + lag / 1000 + " sec");
            // display
            if (measures != null && pricerMaps != null) {

                DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
                tableModel.getDataVector().removeAllElements();
                for (Map.Entry<String, BigDecimal> entry : measures.entrySet()) {
                    jTable1.getColumnModel().getColumn(1).setCellRenderer(new DecimalFormatRenderer("#,##0.0000"));
                    String measureName = entry.getKey();
                    if (measureName.indexOf(StringUtils.DOT) >= 0) {
                        measureName = measureName.substring(0, measureName.indexOf(StringUtils.DOT));
                    }
                    IPricerMeasure measure = (IPricerMeasure) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class,
                            MeasuresAccessor.GET_MEASURE_BY_NAME, measureName);
                    if (measure != null) {
                        Object[] row;
                        if (measure.getGroup().equals(MeasureGroup.PV_GROUP) && pvPricername != null) {
                            row = new Object[]{entry.getKey(), entry.getValue(), pvPricername};
                        } else {
                            IPricer pricer = pricerMaps.get(measure.getGroup());
                            row = new Object[]{entry.getKey(), entry.getValue(), pricer.getClass().getSimpleName()};
                        }
                        tableModel.addRow(row);
                    }

                    int i = 0;// order the list
                    while (i < tableModel.getRowCount() && tableModel.getValueAt(i, 0).toString().compareToIgnoreCase(entry.getKey()) < 0) {
                        i++;
                    }
                    tableModel.moveRow(tableModel.getRowCount() - 1, tableModel.getRowCount() - 1, i);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pricingEnvComboBox = new javax.swing.JComboBox();
        pricerComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSpinnerValoDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jButton1 = new javax.swing.JButton();
        tradePricingPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        measuresButton = new javax.swing.JButton();

        setBackground(java.awt.Color.white);

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(TradePricingTopComponent.class, "TradePricingTopComponent.jLabel1.text")); // NOI18N

        pricingEnvComboBox.setBackground(new java.awt.Color(255, 255, 255));
        pricingEnvComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricingEnvComboBoxActionPerformed(evt);
            }
        });

        pricerComboBox.setBackground(new java.awt.Color(255, 255, 255));
        pricerComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { StringUtils.SPACE }));
        pricerComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pricerComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(TradePricingTopComponent.class, "TradePricingTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(TradePricingTopComponent.class, "TradePricingTopComponent.jLabel3.text")); // NOI18N

        jSpinnerValoDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerValoDate.setName("jSpinnerValoDate"); // NOI18N

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(TradePricingTopComponent.class, "TradePricingTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Measure", "Value", "Pricer"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout tradePricingPanelLayout = new javax.swing.GroupLayout(tradePricingPanel);
        tradePricingPanel.setLayout(tradePricingPanelLayout);
        tradePricingPanelLayout.setHorizontalGroup(
            tradePricingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tradePricingPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        tradePricingPanelLayout.setVerticalGroup(
            tradePricingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tradePricingPanelLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3, Short.MAX_VALUE))
        );

        measuresButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(measuresButton, org.openide.util.NbBundle.getMessage(TradePricingTopComponent.class, "TradePricingTopComponent.measuresButton.text")); // NOI18N
        measuresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                measuresButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerValoDate, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricingEnvComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pricerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(measuresButton, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(tradePricingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pricingEnvComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(pricerComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSpinnerValoDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(measuresButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tradePricingPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        price();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void pricingEnvComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricingEnvComboBoxActionPerformed
        updatePricer();
    }//GEN-LAST:event_pricingEnvComboBoxActionPerformed

    public void updatePricer() {
        if (trade == null) {
            return;
        }
        Date valDate = jSpinnerValoDate.getDate();
        String pricingEnvName = pricingEnvComboBox.getSelectedItem().toString();
        PricingEnvironment pricingEnvironment = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class,
                PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_FROM_NAME, pricingEnvName);
        MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
        Map<MeasuresAccessor.MeasureGroup, IPricer> pricerMaps = (Map) DAOCallerAgent.callMethodWithXMLSerialization(PricingBuilder.class,
                PricingBuilder.LOAD_PRICER_MAP, trade, valDate, pricingEnvironment, groups);
        if (pricerMaps.get(MeasuresAccessor.MeasureGroup.PV_GROUP) != null) {
            String pricer = pricerMaps.get(MeasuresAccessor.MeasureGroup.PV_GROUP).getClass().getName();
            pricer = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_MAPPING_KEY_BY_NAME_AND_VALUE, "pricers", pricer);
            pricerComboBox.setSelectedItem(pricer);
        }
    }

    private void pricerComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pricerComboBoxActionPerformed

        if (trade == null || pricerComboBox.getSelectedItem() == null) {
            return;
        }
        String pricer = pricerComboBox.getSelectedItem().toString();
        Set<TopComponent> openedComponent = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent topComponent : openedComponent) {
            if (topComponent instanceof GaiaTradeTopComponent) {
                GaiaTradeTopComponent component = (GaiaTradeTopComponent) topComponent;
                if (component.getTrade() != null && component.getTrade().getTradeId() == trade.getId()) {
//                    temp.loadByTradeId(temp.getTrade().getId());
                    component.setNpvPricer(pricer);
                }
            }
        }
    }//GEN-LAST:event_pricerComboBoxActionPerformed

    private void measuresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_measuresButtonActionPerformed
        PricerMeasureSelector pricerMeasureSelector = new PricerMeasureSelector(trade, preferences);

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(pricerMeasureSelector, "Greeks Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            preferences = pricerMeasureSelector.getPreferences();

            DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.STORE_USER_PRICING_PREFERENCES, preferences, LoggedUser.getLoggedUserId());
            price();
        }
    }//GEN-LAST:event_measuresButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jSpinnerValoDate;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton measuresButton;
    private javax.swing.JComboBox pricerComboBox;
    private javax.swing.JComboBox pricingEnvComboBox;
    private javax.swing.JPanel tradePricingPanel;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    @Override
    public String getDisplayName() {
        String display = "Pricing Trade ";
        if (trade != null && trade.getTradeId() != null) {
            display += trade.getTradeId();
        }
        return display;
    }
}
