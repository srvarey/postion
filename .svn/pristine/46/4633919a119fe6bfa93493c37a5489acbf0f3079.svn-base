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
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.reports.customColumns.SelectorCustom;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays selector custom columns.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//SelectorColumn//EN", autostore = false)
@TopComponent.Description(preferredID = "SelectorColumnTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.SelectorColumnTopComponent")
@ActionReference(path = "Menu"+MenuManager.SelectorColumnTopComponentMenu , position = MenuManager.SelectorColumnTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SelectorColumnAction", preferredID = "SelectorColumnTopComponent")
@Messages({"CTL_SelectorColumnAction=Selector Column", "CTL_SelectorColumnTopComponent=Selector Column", "HINT_SelectorColumnTopComponent=This is a Selector Column window"})
public final class SelectorColumnTopComponent extends TopComponent {

    private ArrayList<String> arrayColmuns = null;
    private ArrayList<Integer> removedElts = null;
    private List<String> filters;

    public SelectorColumnTopComponent() {
        initComponents();
        setName(Bundle.CTL_SelectorColumnTopComponent());
        setToolTipText(Bundle.HINT_SelectorColumnTopComponent());

        TableColumnModel tableModel = jTable1.getColumnModel();
        tableModel.getColumn(0).setMinWidth(150);
        tableModel.getColumn(0).setMaxWidth(250);
        tableModel.getColumn(1).setMinWidth(150);
        tableModel.getColumn(1).setMaxWidth(250);
        tableModel.getColumn(3).setMinWidth(0);
        tableModel.getColumn(3).setMaxWidth(0);
        tableModel.getColumn(4).setMinWidth(0);
        tableModel.getColumn(4).setMaxWidth(0);
        tableModel.getColumn(5).setMinWidth(0);
        tableModel.getColumn(5).setMaxWidth(0);
        tableModel.getColumn(6).setMinWidth(0);
        tableModel.getColumn(6).setMaxWidth(0);

        jTable1.setColumnModel(tableModel);
    }

    public void initContext() {

        List<String> stringList = ReportUtils.getReportObjectList();
        GUIUtils.fillCombo(jComboBoxObject, stringList);
        jComboBoxObject.setSelectedIndex(0);

        Class objectType = ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString());

        filters = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, objectType);

        List<String> arrayColumns = (List) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_SELECTOR_COLUMN_LIST_BY_OBJECT_TYPE, ReportUtils.getObjectType(GUIUtils.getComponentStringValue(jComboBoxObject)));
        GUIUtils.fillComboWithNullFirst(jComboBoxSelectorColumn, arrayColumns);

        List<String> categories = (List) DAOCallerAgent.callMethod(DomainValuesAccessor.class, DomainValuesAccessor.GET_DOMAIN_VALUES_BY_NAME, "customColumnCategories");
        GUIUtils.fillCombo(jComboBoxCategory, categories);
        jComboBoxCategory.setSelectedItem("Standard");

        refreshFilters();
        jComboBoxObject.setSelectedIndex(0);
        jTable1.getTableHeader().setReorderingAllowed(false);
        removedElts = new ArrayList();
    }

    /**
     * add row
     *
     * @param row
     */
    private void addRow(Vector row) {
        ((DefaultTableModel) jTable1.getModel()).addRow(row);

        String[] arrayDataObjects = (String[]) filters.toArray(new String[filters.size()]);
        TableColumn col = jTable1.getColumnModel().getColumn(0);
        col.setCellEditor(new DefaultCellEditor(new JComboBox(arrayDataObjects)));
    }

    /**
     * refreshFilters
     */
    private void refreshFilters() {
        Class objectType = ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString());

        if (objectType != null) {
//            filters = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, objectType);
            List<String> arrayColumns = (List) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_SELECTOR_COLUMN_LIST_BY_OBJECT_TYPE, ReportUtils.getObjectType(GUIUtils.getComponentStringValue(jComboBoxObject)));
            GUIUtils.fillComboWithNullFirst(jComboBoxSelectorColumn, arrayColumns);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxSelectorColumn = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex==0||colIndex==1)
                return true;
                else
                return false;   //Disallow the editing of any cell
            }
        };
        newButton = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jComboBoxReturnType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxObject = new javax.swing.JComboBox();
        jButtonRemove = new javax.swing.JButton();
        jButtonAddColumn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButtonAddValue = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxCategory = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jComboBoxSelectorColumn.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxSelectorColumn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxSelectorColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSelectorColumnActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"ALL", "", ""}
            },
            new String [] {
                "Filter", "Fixed Value", "Column","Getter","Param","Col Type","id"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTable1FocusGained(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        newButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.newButton.text")); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jComboBoxReturnType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxReturnType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "java.lang.String", "java.math.BigDecimal", "java.lang.Integer" }));
        jComboBoxReturnType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxReturnTypeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jLabel5.text")); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jLabel4.text")); // NOI18N

        jComboBoxObject.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxObjectActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonAddColumn.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddColumn, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jButtonAddColumn.text")); // NOI18N
        jButtonAddColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddColumnActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jLabel1.text")); // NOI18N

        jButtonAddValue.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddValue, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jButtonAddValue.text")); // NOI18N
        jButtonAddValue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddValueActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SelectorColumnTopComponent.class, "SelectorColumnTopComponent.jLabel2.text")); // NOI18N

        jComboBoxCategory.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCategory.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxSelectorColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(newButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonDelete))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxReturnType, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 531, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddColumn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddValue, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxSelectorColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(newButton)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonDelete)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxReturnType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAddColumn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddValue)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemove))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxSelectorColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSelectorColumnActionPerformed
        load();
    }//GEN-LAST:event_jComboBoxSelectorColumnActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        /**
         * select in table
         */
    }//GEN-LAST:event_jTable1KeyPressed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed

        jComboBoxSelectorColumn.setSelectedItem(StringUtils.EMPTY_STRING);
    }

    /**
     * load
     */
    public void load() {

        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        while (tm.getRowCount() > 0) {
            tm.removeRow(0);
        }
        if (jComboBoxSelectorColumn.getSelectedItem() != null && !StringUtils.isEmptyString(jComboBoxSelectorColumn.getSelectedItem().toString())) {
            CustomColumn SelectorColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, jComboBoxSelectorColumn.getSelectedItem().toString());
            jComboBoxReturnType.setSelectedItem(SelectorColumn.getType());
            jComboBoxCategory.setSelectedItem(SelectorColumn.getCategory());
            for (CustomColumnDetail SelectorColumnDetail : SelectorColumn.getCustomColumnDetails()) {
                Vector tableData = new Vector();
                if (SelectorColumnDetail.getFilter() != null) {
                    tableData.add(SelectorColumnDetail.getFilter().getName());
                }
                tableData.add(SelectorColumnDetail.getLitteralValue());
                tableData.add(SelectorColumnDetail.getColumnName());
                tableData.add(SelectorColumnDetail.getGetter());
                tableData.add(SelectorColumnDetail.getParam());
                tableData.add(SelectorColumnDetail.getColumnType());
                tableData.add(SelectorColumnDetail.getCustomColumnDetailId());
                addRow(tableData);
            }
        }
        removedElts = new ArrayList();
    }//GEN-LAST:event_newButtonActionPerformed

    /**
     * save
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        boolean isNew = false;
        String name = StringUtils.EMPTY_STRING;
        if (jComboBoxSelectorColumn.getSelectedItem() != null) {
            name = jComboBoxSelectorColumn.getSelectedItem().toString();
        }
        name = (String) JOptionPane.showInputDialog(this, "Label of the Custom Column", "Custom Column",
                JOptionPane.PLAIN_MESSAGE, null, null, name);

        if (!StringUtils.isEmptyString(name) && jTable1.getModel().getRowCount() > -1) {

            CustomColumn selectorColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class,
                    CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, name);
            ArrayList<CustomColumnDetail> SelectorColumnDetailList = new ArrayList<CustomColumnDetail>();
            if (selectorColumn == null) {
                isNew = true;
                selectorColumn = new CustomColumn();
                jComboBoxSelectorColumn.addItem(name);
                selectorColumn.setClassName(SelectorCustom.class.getName());
            } else if (!selectorColumn.getClassName().equalsIgnoreCase(SelectorCustom.class.getName())) {
                JOptionPane.showMessageDialog(this, "Name already used. Please find another one");
                return;
            }
            selectorColumn.setObjectTypeClass(ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString()));
            selectorColumn.setName(name);
            selectorColumn.setClassName(SelectorCustom.class.getName());
            selectorColumn.setType(jComboBoxReturnType.getSelectedItem().toString());
            if (jComboBoxCategory.getSelectedItem() != null) {
                selectorColumn.setCategory(jComboBoxCategory.getSelectedItem().toString());
            } else {
                selectorColumn.setCategory("Standard");
            }
            for (Integer id : removedElts) {
                CustomColumnDetail selectorColumnDetail = (CustomColumnDetail) DAOCallerAgent.callMethod(CustomColumnAccessor.class,
                        CustomColumnAccessor.GET_CUSTOM_COLUMN_DETAIL_BY_ID, id);
                if (selectorColumnDetail != null) {
                    DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.DELETE_CUSTOM_COLUMN_DETAIL,
                            name, selectorColumnDetail.getColumnName(), selectorColumnDetail.getSuffix());
                }
            }

            DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
            for (int i = 0; i < tm.getRowCount(); i++) {
                String id = GUIUtils.getTableValueAt(jTable1, i, 6);
                CustomColumnDetail SelectorColumnDetail = null;
                if (StringUtils.isEmptyString(id) || isNew) {
                    SelectorColumnDetail = new CustomColumnDetail();
                } else {
                    for (CustomColumnDetail ccd : selectorColumn.getCustomColumnDetails()) {
                        if (ccd.getCustomColumnDetailId().intValue() == Integer.valueOf(id).intValue()) {
                            SelectorColumnDetail = ccd;
                            break;
                        }
                    }
                }
                Filter filter = (Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE,
                        GUIUtils.getTableValueAt(jTable1, i, 0), ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString()));
                if (filter != null) {
                    SelectorColumnDetail.setFilter(filter);
                    SelectorColumnDetail.setLitteralValue(GUIUtils.getTableValueAt(jTable1, i, 1));
                    SelectorColumnDetail.setColumnName(GUIUtils.getTableValueAt(jTable1, i, 2));
                    SelectorColumnDetail.setGetter(GUIUtils.getTableValueAt(jTable1, i, 3));
                    SelectorColumnDetail.setParam(GUIUtils.getTableValueAt(jTable1, i, 4));
                    SelectorColumnDetail.setColumnType(GUIUtils.getTableValueAt(jTable1, i, 5));
                    SelectorColumnDetail.setCustomColumn(selectorColumn);
                    SelectorColumnDetailList.add(SelectorColumnDetail);
                }
            }
            selectorColumn.setCustomColumnDetails(SelectorColumnDetailList);

            DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.STORE_CUSTOM_COLUMN, selectorColumn);
            jComboBoxSelectorColumn.setSelectedItem(name);
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxReturnTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxReturnTypeActionPerformed
//        refreshFilters();
    }//GEN-LAST:event_jComboBoxReturnTypeActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (jComboBoxSelectorColumn.getSelectedItem() != null) {
            int res;
            res = JOptionPane.showConfirmDialog(this, "Delete Column " + jComboBoxSelectorColumn.getSelectedItem().toString() + " ?", "Delete", JOptionPane.YES_NO_OPTION);

            if (JOptionPane.YES_OPTION == res) {
                CustomColumn selectorColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, jComboBoxSelectorColumn.getSelectedItem().toString());
                Boolean used = (Boolean) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.IS_A_CUSTOM_COLUMN_USED_IN_TEMPLATES, selectorColumn.getName());
                if (!used) {
                    DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.DELETE_CUSTOM_COLUMN, selectorColumn);
                    jComboBoxSelectorColumn.removeItem(jComboBoxSelectorColumn.getSelectedItem());
                    GUIUtils.clearTableModel((DefaultTableModel) jTable1.getModel());
                } else {
                    JOptionPane.showMessageDialog(this, "This column is present in report templates", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jComboBoxObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxObjectActionPerformed
        if (jComboBoxObject.getSelectedItem() != null) {
            refreshFilters();
            GUIUtils.clearTableModel((DefaultTableModel) jTable1.getModel());
        }
    }//GEN-LAST:event_jComboBoxObjectActionPerformed

    /**
     * remove
     *
     * @param evt
     */
    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        if (removedElts == null) {
            removedElts = new ArrayList();
        }
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();

        if (jTable1.getSelectedRow() != -1 && tableModel.getValueAt(jTable1.getSelectedRow(), 6) != null && !tableModel.getValueAt(jTable1.getSelectedRow(), 6).toString().isEmpty()) {
            Integer id = Integer.parseInt(tableModel.getValueAt(jTable1.getSelectedRow(), 6).toString());
            removedElts.add(id);
        }
        tableModel.removeRow(jTable1.getSelectedRow());
        jTable1.setModel(tableModel);

    }//GEN-LAST:event_jButtonRemoveActionPerformed

    /**
     * add line
     *
     * @param evt
     */
    private void jButtonAddColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddColumnActionPerformed

        Class objectType = ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString());
        String returnType = jComboBoxReturnType.getSelectedItem().toString();

        FieldChooser fieldChooser = new FieldChooser(objectType, null, returnType);

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(fieldChooser, "Field Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            List<TemplateColumnItem> itemList = fieldChooser.getTemplateColumnItem();
            for (TemplateColumnItem templateColumnItem : itemList) {
                Vector tableData = new Vector();
                if (templateColumnItem != null) {
                    tableData.add(StringUtils.EMPTY_STRING);
                    tableData.add(StringUtils.EMPTY_STRING);
                    tableData.add(templateColumnItem.getName());
                    tableData.add(templateColumnItem.getGetter());
                    tableData.add(templateColumnItem.getParam());
                    tableData.add(templateColumnItem.getColumnType());
                    tableData.add(StringUtils.EMPTY_STRING);
                    addRow(tableData);
                }
            }
        }

    }//GEN-LAST:event_jButtonAddColumnActionPerformed

    private void jButtonAddValueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddValueActionPerformed

        Vector tableData = new Vector();
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        tableData.add(StringUtils.EMPTY_STRING);
        addRow(tableData);

    }//GEN-LAST:event_jButtonAddValueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddColumn;
    private javax.swing.JButton jButtonAddValue;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxCategory;
    private javax.swing.JComboBox jComboBoxObject;
    private javax.swing.JComboBox jComboBoxReturnType;
    private javax.swing.JComboBox jComboBoxSelectorColumn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton newButton;
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
