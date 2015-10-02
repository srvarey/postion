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
package org.gaia.gui.assets;

import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.legalEntity.MarginClearerRule;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterGroup;
import org.gaia.domain.reports.Position;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays margin definitions.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//MarginDefinition//EN", autostore = false)
@TopComponent.Description(preferredID = "MarginDefinitionTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.MarginDefinitionTopComponent")
@ActionReference(path = "Menu"+MenuManager.MarginDefinitionTopComponentMenu , position = MenuManager.MarginDefinitionTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MarginDefinitionAction", preferredID = "MarginDefinitionTopComponent")
@Messages({"CTL_MarginDefinitionAction=Margin Definition", "CTL_MarginDefinitionTopComponent=Margin Definition", "HINT_MarginDefinitionTopComponent=This is the Margin Definition window"})
public final class MarginDefinitionTopComponent extends GaiaTopComponent {

    private List<String> customColumnList;
    List<String> listMarginTypes;
    private List<String> filterList;
    private Integer ccpId;
    private FilterGroup filterGroup;
    private static final Logger logger = Logger.getLogger(MarginDefinitionTopComponent.class);

    public MarginDefinitionTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_MarginDefinitionTopComponent());
        setToolTipText(Bundle.HINT_MarginDefinitionTopComponent());
    }

    /**
     * fill Table CCP
     */
    public void fillTableCCP() {
        try {
            List<LegalEntity> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                    LegalEntityAccessor.GET_LEGAL_ENTITIES_BY_ROLE, LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
            DefaultTableModel model = (DefaultTableModel) jTableCCP.getModel();
            model.getDataVector().clear();
            model.setRowCount(0);
            int i = 0;
            for (LegalEntity legalEntity : entities) {
                model.addRow(new Vector<>());
                model.setValueAt(legalEntity.getLegalEntityId(), i, 0);
                model.setValueAt(legalEntity.getShortName(), i, 1);
                i++;
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * get domain valus and selectorcolum
     */
    @Override
    public void initContext() {
        try {
            listMarginTypes = (List) DAOCallerAgent.callMethod(DomainValuesAccessor.class, DomainValuesAccessor.GET_DOMAIN_VALUES_BY_NAME, "marginTypes");
            customColumnList = (List) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_COLLAT_SELECTOR_COLUMN_LIST);
            filterList = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, Position.class.getName());
            jTableCCPRuleLink.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTableCCP.getColumnModel().getColumn(0).setPreferredWidth(20);

            fillTableCCP();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCCP = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableCCPRuleLink = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jButtonAddRule = new javax.swing.JButton();
        jButtonRemoveRule = new javax.swing.JButton();
        jButtonSaveCCPRuleLink = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableCCPTradeLink = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jButtonAddFilter = new javax.swing.JButton();
        jButtonremoveFilter = new javax.swing.JButton();
        jButtonSaveFilters = new javax.swing.JButton();

        setBackground(java.awt.Color.white);

        jPanel3.setBackground(new java.awt.Color(254, 252, 254));

        jTableCCP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Id","Name"}
        ));
        jTableCCP.setName("jTableCCP"); // NOI18N
        jTableCCP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCCPMouseClicked(evt);
            }
        });
        jTableCCP.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jTableCCPPropertyChange(evt);
            }
        });
        jScrollPane2.setViewportView(jTableCCP);

        jPanel4.setBackground(new java.awt.Color(241, 241, 241));

        jTableCCPRuleLink.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] { },
            new String [] {"Id","Margin Type","Forcast Rule"}
        ));
        jTableCCPRuleLink.setName("jTableCCPRuleLink"); // NOI18N
        jScrollPane3.setViewportView(jTableCCPRuleLink);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jLabel6.text")); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addContainerGap(285, Short.MAX_VALUE))
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE))
        );

        jButtonAddRule.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddRule, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonAddRule.text")); // NOI18N
        jButtonAddRule.setName("jButtonAddRule"); // NOI18N
        jButtonAddRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddRuleActionPerformed(evt);
            }
        });

        jButtonRemoveRule.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveRule, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonRemoveRule.text")); // NOI18N
        jButtonRemoveRule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveRuleActionPerformed(evt);
            }
        });

        jButtonSaveCCPRuleLink.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveCCPRuleLink, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonSaveCCPRuleLink.text")); // NOI18N
        jButtonSaveCCPRuleLink.setName("jButtonSaveCCPRuleLink"); // NOI18N
        jButtonSaveCCPRuleLink.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveCCPRuleLinkActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(241, 241, 241));

        jTableCCPTradeLink.setModel(new javax.swing.table.DefaultTableModel(new String [][] {},
            new String [] { "TradeFilter"}
        ));
        jTableCCPTradeLink.setName("jTableCCPTradeLink"); // NOI18N
        jTableCCPTradeLink.getTableHeader().setReorderingAllowed(false);
        jScrollPane4.setViewportView(jTableCCPTradeLink);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jLabel7.text")); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 117, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonAddFilter.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddFilter, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonAddFilter.text")); // NOI18N
        jButtonAddFilter.setName("jButtonAddFilter"); // NOI18N
        jButtonAddFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFilterActionPerformed(evt);
            }
        });

        jButtonremoveFilter.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonremoveFilter, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonremoveFilter.text")); // NOI18N
        jButtonremoveFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonremoveFilterActionPerformed(evt);
            }
        });

        jButtonSaveFilters.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveFilters, org.openide.util.NbBundle.getMessage(MarginDefinitionTopComponent.class, "MarginDefinitionTopComponent.jButtonSaveFilters.text")); // NOI18N
        jButtonSaveFilters.setName("jButtonSaveFilters"); // NOI18N
        jButtonSaveFilters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveFiltersActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRemoveRule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddRule, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveCCPRuleLink, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonremoveFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddFilter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSaveFilters, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jButtonAddFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonremoveFilter)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSaveFilters))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(jButtonAddRule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRemoveRule)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonSaveCCPRuleLink))
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveFiltersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveFiltersActionPerformed
        try {
            /**
             * if filter not existe , create a new
             */
            if (filterGroup == null) {
                filterGroup = new FilterGroup();
            }
            DefaultTableModel model = (DefaultTableModel) jTableCCP.getModel();
            ccpId = (Integer) model.getValueAt(jTableCCP.getSelectedRow(), 0);
            HashSet filters = new HashSet();
            model = (DefaultTableModel) jTableCCPTradeLink.getModel();
            for (int i = 0; i < jTableCCPTradeLink.getRowCount(); i++) {
                try {
                    String name = model.getValueAt(i, 0).toString();
                    Filter filter = (Filter) DAOCallerAgent.callMethod(FilterAccessor.class,
                            FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, name, Position.class);
                    if (filter != null) {
                        filters.add(filter);
                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
            filterGroup.setFilterCollection(filters);
            filterGroup.setFilterGroupName("CCP filter " + ccpId);
            filterGroup.setLinkedObjectClass(LegalEntity.class.getName());
            filterGroup.setLinkedObjectId(ccpId);
            DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.STORE_FILTER_GROUP, filterGroup);
            JOptionPane.showMessageDialog(this, "OK.");
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonSaveFiltersActionPerformed

    private void jButtonremoveFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonremoveFilterActionPerformed
        int i = jTableCCPTradeLink.getSelectedRow();
        if (i >= 0) {
            DefaultTableModel model = (DefaultTableModel) jTableCCPTradeLink.getModel();
            model.removeRow(i);
        }
    }//GEN-LAST:event_jButtonremoveFilterActionPerformed

    private void jButtonAddFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFilterActionPerformed
        addRowToTableCCPTradeFilter();
    }//GEN-LAST:event_jButtonAddFilterActionPerformed

    private void jButtonSaveCCPRuleLinkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveCCPRuleLinkActionPerformed
        /**
         * store CCP Rule Links
         */
        if (ccpId != null) {
            try {
                LegalEntity ccp = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, ccpId);
                DefaultTableModel model = (DefaultTableModel) jTableCCPRuleLink.getModel();
                for (int i = 0; i < jTableCCPRuleLink.getRowCount(); i++) {
                    Integer id = (Integer) model.getValueAt(i, 0);
                    MarginClearerRule rule;
                    if (id == null) {
                        rule = new MarginClearerRule();
                    } else {
                        rule = (MarginClearerRule) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_MARGIN_CLEARER_RULE, id);
                    }

                    rule.setMarginType(model.getValueAt(i, 1).toString());
                    CustomColumn customColum = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, model.getValueAt(i, 2).toString());
                    rule.setCustomColumn(customColum);
                    rule.setLegalEntity(ccp);
                    DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.STORE_MARGIN_CLEARER_RULE, rule);
                    model.setValueAt(rule.getMarginClearerRuleId(), i, 0);
                }
                JOptionPane.showMessageDialog(this, "OK.");
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }//GEN-LAST:event_jButtonSaveCCPRuleLinkActionPerformed

    private void jButtonRemoveRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveRuleActionPerformed
        int i = jTableCCPRuleLink.getSelectedRow();
        if (i >= 0) {
            DefaultTableModel model = (DefaultTableModel) jTableCCPRuleLink.getModel();
            model.removeRow(i);
        }
    }//GEN-LAST:event_jButtonRemoveRuleActionPerformed

    private void jButtonAddRuleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddRuleActionPerformed
        addRowToTableCCPRule();
    }//GEN-LAST:event_jButtonAddRuleActionPerformed

    private void jTableCCPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCCPMouseClicked
        selectionChanged();
    }//GEN-LAST:event_jTableCCPMouseClicked

    public void selectionChanged(){
        try {
            /**
             * CCP selected => display
             */
            DefaultTableModel model = (DefaultTableModel) jTableCCP.getModel();
            ccpId = (Integer) model.getValueAt(jTableCCP.getSelectedRow(), 0);

            List<MarginClearerRule> rules = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_MARGIN_CLEARER_RULES, ccpId);
            model = (DefaultTableModel) jTableCCPRuleLink.getModel();
            while (jTableCCPRuleLink.getRowCount() > 0) {
                model.removeRow(0);
            }
            int i = 0;
            for (MarginClearerRule rule : rules) {
                addRowToTableCCPRule();
                model.setValueAt(rule.getMarginClearerRuleId(), i, 0);
                model.setValueAt(rule.getMarginType(), i, 1);
                model.setValueAt(rule.getCustomColumn().getName(), i, 2);
                i++;
            }
            model = (DefaultTableModel) jTableCCPTradeLink.getModel();
            while (jTableCCPTradeLink.getRowCount() > 0) {
                model.removeRow(0);
            }
            filterGroup = (FilterGroup) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_GROUP, LegalEntity.class.getName(), ccpId);
            model = (DefaultTableModel) jTableCCPTradeLink.getModel();
            i = 0;
            if (filterGroup != null) {
                for (Filter filter : filterGroup.getFilterCollection()) {
                    addRowToTableCCPTradeFilter();
                    model.setValueAt(filter.getName(), i, 0);
                    i++;
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }
    private void jTableCCPPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jTableCCPPropertyChange
         selectionChanged();
    }//GEN-LAST:event_jTableCCPPropertyChange

    /**
     * add row
     */
    private void addRowToTableCCPRule() {
        ((DefaultTableModel) jTableCCPRuleLink.getModel()).addRow(new Vector<>());

        TableColumn col2 = jTableCCPRuleLink.getColumnModel().getColumn(1);

        String[] arrayMarginType = new String[listMarginTypes.size()];
        int i = 0;
        for (String marginType : listMarginTypes) {
            arrayMarginType[i] = marginType;
            i++;
        }

        col2.setCellEditor(new DefaultCellEditor(new JComboBox(arrayMarginType)));
        String[] arrayFilters = (String[]) customColumnList.toArray(new String[customColumnList.size()]);
        TableColumn col1 = jTableCCPRuleLink.getColumnModel().getColumn(2);
        col1.setCellEditor(new DefaultCellEditor(new JComboBox(arrayFilters)));
    }

    private void addRowToTableCCPTradeFilter() {
        ((DefaultTableModel) jTableCCPTradeLink.getModel()).addRow(new Vector<>());
        String[] arrayFilters = (String[]) filterList.toArray(new String[filterList.size()]);
        TableColumn col0 = jTableCCPTradeLink.getColumnModel().getColumn(0);
        col0.setCellEditor(new DefaultCellEditor(new JComboBox(arrayFilters)));
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddFilter;
    private javax.swing.JButton jButtonAddRule;
    private javax.swing.JButton jButtonRemoveRule;
    private javax.swing.JButton jButtonSaveCCPRuleLink;
    private javax.swing.JButton jButtonSaveFilters;
    private javax.swing.JButton jButtonremoveFilter;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableCCP;
    private javax.swing.JTable jTableCCPRuleLink;
    private javax.swing.JTable jTableCCPTradeLink;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
