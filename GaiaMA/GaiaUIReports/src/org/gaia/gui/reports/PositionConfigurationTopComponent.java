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
package org.gaia.gui.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.reports.PositionConfiguration;
import org.gaia.domain.reports.PositionConfigurationItem;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays position configuration.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//PositionConfiguration//EN", autostore = false)
@TopComponent.Description(preferredID = "PositionConfigurationTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.PositionConfigurationTopComponent")
@ActionReference(path = "Menu"+MenuManager.PositionConfigurationTopComponentMenu , position = MenuManager.PositionConfigurationTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PositionConfigurationAction", preferredID = "PositionConfigurationTopComponent")
@Messages({"CTL_PositionConfigurationAction=Position Configuration", "CTL_PositionConfigurationTopComponent=Position Configuration Window", "HINT_PositionConfigurationTopComponent=This is a Position Configuration window"})
public final class PositionConfigurationTopComponent extends GaiaTopComponent {

    private PositionConfiguration configuration;
    private static final Logger logger = Logger.getLogger(PositionConfigurationTopComponent.class);

    public PositionConfigurationTopComponent() {
        initComponents();
        setName(Bundle.CTL_PositionConfigurationTopComponent());
        setToolTipText(Bundle.HINT_PositionConfigurationTopComponent());

        buttonGroup1.add(jRadioButtonSettlementDate);
        buttonGroup1.add(jRadioButtonTradeDate);

        List<String> configNames = (List) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_CONFIGURATION_NAME_LIST);
        for (String name : configNames) {
            jComboBoxConfigName.addItem(name);
        }
    }

    public void load() {

        String name = GUIUtils.getComponentStringValue(jComboBoxConfigName);
        configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_CONFIGURATION, name);
        if (configuration != null) {
            if (configuration.isInTradeDate()) {
                jRadioButtonTradeDate.setSelected(true);
                jRadioButtonSettlementDate.setSelected(false);
            } else {
                jRadioButtonTradeDate.setSelected(false);
                jRadioButtonSettlementDate.setSelected(true);
            }

            jCheckBoxCCP.setSelected(false);
            jCheckBoxClearingMember.setSelected(false);
            jCheckBoxCounterparty.setSelected(false);
            jCheckBoxInternalCounterparty.setSelected(false);
            jCheckBoxIsCollateral.setSelected(false);
            jCheckBoxProduct.setSelected(false);
            jCheckBoxTradeType.setSelected(false);
            jCheckBoxTrader.setSelected(false);

            for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
                if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_PRODUCT.dbField)) {
                    jCheckBoxProduct.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.dbField)) {
                    jCheckBoxInternalCounterparty.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.dbField)) {
                    jCheckBoxCounterparty.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADER.dbField)) {
                    jCheckBoxTrader.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_IS_COLLATERAL.dbField)) {
                    jCheckBoxIsCollateral.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.dbField)) {
                    jCheckBoxCCP.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CLEARING_MEMBER.dbField)) {
                    jCheckBoxClearingMember.setSelected(true);
                } else if (item.getDbField().equalsIgnoreCase(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADE_TYPE.dbField)) {
                    jCheckBoxTradeType.setSelected(true);
                }
            }
        }
    }

    public void save() {
        boolean isNew = false;
        String name;
        if (jComboBoxConfigName.getSelectedItem() != null) {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Configuration", "Position Configuration", JOptionPane.PLAIN_MESSAGE, null, null, jComboBoxConfigName.getSelectedItem().toString());
        } else {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Configuration", "Position Configuration", JOptionPane.PLAIN_MESSAGE, null, null, "");
        }

        if (!StringUtils.isEmptyString(name)) {
            configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_CONFIGURATION, name);
            if (configuration == null) {
                configuration = new PositionConfiguration();
                configuration.setPositionConfigurationItems(new ArrayList());
                isNew = true;
            }
            configuration.setName(name);
            if (jRadioButtonTradeDate.isSelected()) {
                configuration.setIsInTradeDate(Boolean.TRUE);
            } else {
                configuration.setIsInTradeDate(Boolean.FALSE);
            }

            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_PRODUCT.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_PRODUCT.getter, jCheckBoxProduct.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.getter, jCheckBoxInternalCounterparty.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.getter, jCheckBoxCounterparty.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADER.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADER.getter, jCheckBoxTrader.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_IS_COLLATERAL.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_IS_COLLATERAL.getter, jCheckBoxIsCollateral.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.getter, jCheckBoxCCP.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CLEARING_MEMBER.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CLEARING_MEMBER.getter, jCheckBoxClearingMember.isSelected());
            addOrRemove(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADE_TYPE.dbField, PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADE_TYPE.getter, jCheckBoxTradeType.isSelected());

            int ok = JOptionPane.OK_OPTION;

            if (ok == JOptionPane.OK_OPTION) {
                DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.STORE_CONGURATION, configuration);
                if (isNew) {
                    jComboBoxConfigName.addItem(name);
                }
                ok = JOptionPane.OK_OPTION;
                if (isNew) {
                    ok = JOptionPane.showConfirmDialog(jButton1, "Configuration saved. Do you want to calculate from start?", "Sure?", JOptionPane.YES_NO_OPTION);
                }
                if (ok == JOptionPane.OK_OPTION) {
                    Date startDate = configuration.getStartDate();
                    if (isNew) {
                        startDate = (Date) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_FIRST_FLOW_DATE);
                    }
                    String res = JOptionPane.showInputDialog(this, "What is the date from which to compute the position? ", dateFormatter.format(startDate));
                    boolean goodDate = false;
                    try {
                        startDate = dateFormatter.parse(res);
                        goodDate = true;
                    } catch (Exception e) {
                        logger.error("error  " + StringUtils.formatErrorMessage(e));
                        JOptionPane.showMessageDialog(this, "Wrong date format, write : dd/mm/yyyy");
                    }
                    while (!goodDate) {
                        try {
                            res = JOptionPane.showInputDialog(this, "What is the date from which to compute the position? ", dateFormatter.format(startDate));
                            startDate = dateFormatter.parse(res);
                            goodDate = true;
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(this, "Wrong format");
                        }
                    }
                    //updates start date
                    configuration.setStartDate(startDate);
                    DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.STORE_CONGURATION, configuration);
                    // launch calculation
                    jProgressBar.setIndeterminate(true);
                    DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.RECALCULATE_POSITION_FROM_START_DATE, configuration.getPositionConfigurationId(), startDate, DateUtils.getDate());
                    jProgressBar.setIndeterminate(false);
                    jProgressBar.setValue(jProgressBar.getMinimum());
                    JOptionPane.showMessageDialog(this, "OK");
                }
            }
        }

    }

    /**
     * add or remove item
     */
    public void addOrRemove(String dbName, String getter, boolean present) {
        boolean found = false;
        PositionConfigurationItem foundItem = null;
        for (PositionConfigurationItem item : configuration.getPositionConfigurationItems()) {
            if (item.getGetter().equals(getter)) {
                found = true;
                foundItem = item;
            }
        }
        if (present != found) {
            if (present) {
                PositionConfigurationItem item = new PositionConfigurationItem(dbName, getter);
                item.setPositionConfiguration(configuration);
                configuration.getPositionConfigurationItems().add(item);
            } else {
                configuration.getPositionConfigurationItems().remove(foundItem);
            }
        }
    }

    public void delete() {
        String name = GUIUtils.getComponentStringValue(jComboBoxConfigName);
        configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_CONFIGURATION, name);
        if (configuration != null) {
            int ok = JOptionPane.showConfirmDialog(jButton1, "Are you sure? It will delete all the position...");
            if (ok == JOptionPane.OK_OPTION) {
                DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.DELETE_POSITIONS, configuration.getPositionConfigurationId());
                DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.DELETE_CONFIGURATION, configuration);
                jComboBoxConfigName.removeItem(name);
            }

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxConfigName = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jCheckBoxProduct = new javax.swing.JCheckBox();
        jCheckBoxInternalCounterparty = new javax.swing.JCheckBox();
        jCheckBoxCounterparty = new javax.swing.JCheckBox();
        jCheckBoxIsCollateral = new javax.swing.JCheckBox();
        jRadioButtonTradeDate = new javax.swing.JRadioButton();
        jRadioButtonSettlementDate = new javax.swing.JRadioButton();
        jCheckBoxTrader = new javax.swing.JCheckBox();
        jCheckBoxCCP = new javax.swing.JCheckBox();
        jCheckBoxClearingMember = new javax.swing.JCheckBox();
        jCheckBoxTradeType = new javax.swing.JCheckBox();
        jButton1 = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jProgressBar = new javax.swing.JProgressBar();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setToolTipText(org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jPanel1.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jLabel1.text")); // NOI18N

        jComboBoxConfigName.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxConfigName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxConfigName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConfigNameActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jLabel2.text")); // NOI18N

        jCheckBoxProduct.setBackground(new java.awt.Color(254, 252, 254));
        jCheckBoxProduct.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxProduct, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxProduct.text")); // NOI18N
        jCheckBoxProduct.setEnabled(false);

        jCheckBoxInternalCounterparty.setBackground(new java.awt.Color(254, 252, 254));
        jCheckBoxInternalCounterparty.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxInternalCounterparty, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxInternalCounterparty.text")); // NOI18N
        jCheckBoxInternalCounterparty.setEnabled(false);

        jCheckBoxCounterparty.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxCounterparty, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxCounterparty.text")); // NOI18N

        jCheckBoxIsCollateral.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxIsCollateral, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxIsCollateral.text")); // NOI18N

        jRadioButtonTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonTradeDate, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jRadioButtonTradeDate.text")); // NOI18N

        jRadioButtonSettlementDate.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonSettlementDate, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jRadioButtonSettlementDate.text")); // NOI18N

        jCheckBoxTrader.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxTrader, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxTrader.text")); // NOI18N

        jCheckBoxCCP.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxCCP, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxCCP.text")); // NOI18N

        jCheckBoxClearingMember.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxClearingMember, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxClearingMember.text")); // NOI18N

        jCheckBoxTradeType.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxTradeType, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jCheckBoxTradeType.text")); // NOI18N

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(PositionConfigurationTopComponent.class, "PositionConfigurationTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jProgressBar.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(44, 44, 44)
                                    .addComponent(jComboBoxConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(79, 79, 79)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBoxInternalCounterparty)
                                .addComponent(jCheckBoxProduct)
                                .addComponent(jCheckBoxCounterparty)
                                .addComponent(jCheckBoxIsCollateral)
                                .addComponent(jRadioButtonTradeDate)
                                .addComponent(jRadioButtonSettlementDate)
                                .addComponent(jCheckBoxTrader)
                                .addComponent(jCheckBoxCCP)
                                .addComponent(jCheckBoxClearingMember)
                                .addComponent(jCheckBoxTradeType)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButtonDelete))))))
                .addContainerGap(98, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxConfigName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jCheckBoxProduct)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxInternalCounterparty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxCounterparty)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxIsCollateral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRadioButtonTradeDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButtonSettlementDate)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxTrader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxCCP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxClearingMember)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxTradeType)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButtonDelete))
                .addGap(18, 18, 18)
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        save();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBoxConfigNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConfigNameActionPerformed
        load();
    }//GEN-LAST:event_jComboBoxConfigNameActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        delete();
    }//GEN-LAST:event_jButtonDeleteActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JCheckBox jCheckBoxCCP;
    private javax.swing.JCheckBox jCheckBoxClearingMember;
    private javax.swing.JCheckBox jCheckBoxCounterparty;
    private javax.swing.JCheckBox jCheckBoxInternalCounterparty;
    private javax.swing.JCheckBox jCheckBoxIsCollateral;
    private javax.swing.JCheckBox jCheckBoxProduct;
    private javax.swing.JCheckBox jCheckBoxTradeType;
    private javax.swing.JCheckBox jCheckBoxTrader;
    private javax.swing.JComboBox jComboBoxConfigName;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JRadioButton jRadioButtonSettlementDate;
    private javax.swing.JRadioButton jRadioButtonTradeDate;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
