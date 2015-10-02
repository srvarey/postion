/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.reports;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.gaia.gui.utils.CentralLookup;
import org.gaia.gui.utils.GUIUtils;
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

/**
 * Top component which displays report columns.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//ColumnFields//EN", autostore = false)
@TopComponent.Description(preferredID = "ColumnFieldsTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "properties", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.ColumnFieldsTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_ColumnFieldsAction", preferredID = "ColumnFieldsTopComponent")
@Messages({"CTL_ColumnFieldsAction=Column Fields", "CTL_ColumnFieldsTopComponent=Column Fields", "HINT_ColumnFieldTopComponent=This is a ColumnFields window"})
public final class ColumnFieldsTopComponent extends TopComponent implements LookupListener {

    private ArrayList listAggregs;
    private ArrayList listSuffix;
    private Lookup.Result<ReportTemplate> result = null;
    private Class objectType;
    private ReportTemplate template;
    private boolean isDrillDownActive;
    private static final int iId = 0;
    private static final int iName = 1;
    private static final int iDisplayName = 2;
    private static final int iAggregation = 3;
    private static final int iAggregated = 4;
    private static final int iSuffix = 5;
    private static final int iConversion = 6;
    private static final int iConversionFirst = 7;
    private static final int iIsExposure = 8;
    private static final int iIsInChart = 9;
    private static final int iGetter = 10;
    private static final int iParam = 11;
    private static final int iReturnType = 12;
    private static final int iColumnType = 13;

    public ColumnFieldsTopComponent() {
        initComponents();
        setName(Bundle.CTL_ColumnFieldsTopComponent());
        setToolTipText(Bundle.HINT_ColumnFieldTopComponent());
        associateLookup(CentralLookup.getDefault());
    }

    public void initContext() {

//        template = CentralLookup.getLastActiveReportTemplate();
        if (template == null) {
            String name = (String) DAOCallerAgent.callMethod(ReportUtils.class, ReportUtils.GET_DEFAULT_TEMPLATE, Position.class.getName(), LoggedUser.getLoggedUserId());
            template = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_REPORT_TEMPLATE_BY_NAME_AND_TYPE, name, Position.class);
        }
        listAggregs = new ArrayList();
        listAggregs.add(StringUtils.EMPTY_STRING);
        listAggregs.add("Sum");
        listAggregs.add("Avg");

        listSuffix = new ArrayList();
        listSuffix.add(StringUtils.EMPTY_STRING);
        listSuffix.add(ReportTemplate.FIRSTDATE_SUFFIX);
        listSuffix.add(ReportTemplate.EVOLUTION_SUFFIX);
        TableColumnModel columnModel = columnPropertiesTable.getColumnModel();
        for (int i = iGetter; i <= iColumnType; i++) {
            columnModel.getColumn(i).setMinWidth(0);
            columnModel.getColumn(i).setMaxWidth(0);
        }
        columnPropertiesTable.getColumnModel().getColumn(3).setPreferredWidth(15);
        columnModel.getColumn(iId).setMinWidth(0);
        columnModel.getColumn(iId).setMaxWidth(0);
        columnPropertiesTable.getColumnModel().getColumn(iAggregation).setPreferredWidth(15);
        columnPropertiesTable.getColumnModel().getColumn(iAggregated).setPreferredWidth(15);
        columnPropertiesTable.getColumnModel().getColumn(iSuffix).setPreferredWidth(25);
        columnPropertiesTable.getColumnModel().getColumn(iConversion).setPreferredWidth(25);
        columnPropertiesTable.getColumnModel().getColumn(iConversionFirst).setPreferredWidth(25);
        columnPropertiesTable.getColumnModel().getColumn(iIsExposure).setPreferredWidth(25);
        columnPropertiesTable.getColumnModel().getColumn(iIsInChart).setPreferredWidth(25);
        columnPropertiesTable.setColumnModel(columnModel);
        List<String> objects = ReportUtils.getReportObjectList();
        jComboBoxObject.removeAllItems();
        for (String objectName : objects) {
            jComboBoxObject.addItem(objectName);
        }
        if (template != null) {
            objectType = template.getObjectTypeClass();
            fillFields(template);
            jComboBoxObject.setSelectedItem(ReportUtils.getReportType(objectType));
        }

    }

    public void loadTemplate(ReportTemplate _template) {
        template = _template;
    }

    public void fillFields(ReportTemplate template) {

        if (template != null) {
            GUIUtils.emptyTable(columnPropertiesTable);
            if (template.getTemplateColumnItems() != null) {
                for (TemplateColumnItem templateColumnItem : template.getTemplateColumnItems()) {
                    addToTable(templateColumnItem);
                }
            }
        }
    }

    @Override
    public void resultChanged(LookupEvent ev) {
        Collection<? extends ReportTemplate> allEvents = CentralLookup.getDefault().lookupAll(ReportTemplate.class);
        if (!allEvents.isEmpty()) {
            ReportTemplate evtTemplate = allEvents.iterator().next();
            CentralLookup.getDefault().remove(template);

            if (evtTemplate != null) {
                isDrillDownActive = evtTemplate.isDrillDown();
                this.template = evtTemplate;
                refreshFromTemplate(evtTemplate);
            }
        }
    }

    public void refreshFromTemplate(ReportTemplate evtTemplate) {
        if (evtTemplate != null) {
            fillFields(evtTemplate);
            template.setTemplateColumnItems(evtTemplate.getTemplateColumnItems());
            if (!evtTemplate.getObjectTypeClass().equals(objectType)) {
                objectType = evtTemplate.getObjectTypeClass();
                jComboBoxObject.setSelectedItem(ReportUtils.getReportType(objectType));
            }
            setDisplayName(getName() + StringUtils.SPACE + ReportUtils.getReportType(evtTemplate.getObjectTypeClass()) + StringUtils.SPACE + evtTemplate.getTemplateName());
        }
    }

    private void addRowSettings(Vector v) {
        ((DefaultTableModel) columnPropertiesTable.getModel()).addRow(v);

        /**
         * column aggregation
         */
        String[] arrayDataObjects = (String[]) listAggregs.toArray(new String[listAggregs.size()]);
        TableColumn col = columnPropertiesTable.getColumnModel().getColumn(iAggregation);
        col.setCellEditor(new DefaultCellEditor(new JComboBox(arrayDataObjects)));

        /**
         * column aggregated
         */
        TableColumn col2 = columnPropertiesTable.getColumnModel().getColumn(iAggregated);
        col2.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        col2.setCellRenderer(columnPropertiesTable.getDefaultRenderer(Boolean.class));

        /**
         * column conversion
         */
        TableColumn col3 = columnPropertiesTable.getColumnModel().getColumn(iConversion);
        col3.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        col3.setCellRenderer(columnPropertiesTable.getDefaultRenderer(Boolean.class));

        /**
         * column conversion first
         */
        TableColumn col5 = columnPropertiesTable.getColumnModel().getColumn(iConversionFirst);
        col5.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        col5.setCellRenderer(columnPropertiesTable.getDefaultRenderer(Boolean.class));

        /**
         * column suffixes
         */
        String[] arrayDataObjects2 = (String[]) listSuffix.toArray(new String[listSuffix.size()]);
        TableColumn col4 = columnPropertiesTable.getColumnModel().getColumn(iSuffix);
        col4.setCellEditor(new DefaultCellEditor(new JComboBox(arrayDataObjects2)));

        /**
         * column expo
         */
        TableColumn col6 = columnPropertiesTable.getColumnModel().getColumn(iIsExposure);
        col6.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        col6.setCellRenderer(columnPropertiesTable.getDefaultRenderer(Boolean.class));

        /**
         * column chart
         */
        TableColumn col7 = columnPropertiesTable.getColumnModel().getColumn(iIsInChart);
        col7.setCellEditor(new DefaultCellEditor(new JCheckBox()));
        col7.setCellRenderer(columnPropertiesTable.getDefaultRenderer(Boolean.class));
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalScrollPane = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jButtonUp = new javax.swing.JButton();
        jButtonDown = new javax.swing.JButton();
        jButtonApply = new javax.swing.JButton();
        jComboBoxObject = new javax.swing.JComboBox();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        columnPropertiesScrollPane = new javax.swing.JScrollPane();
        columnPropertiesTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex==2||colIndex==5||colIndex==3||colIndex==4||colIndex==6||colIndex==7||colIndex==8||colIndex==9)
                return true;
                else
                return false;   //Disallow the editing of any cell
            }
        };

        setMaximumSize(preferredSize());
        setPreferredSize(new java.awt.Dimension(420, 742));

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        jButtonUp.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonUp, org.openide.util.NbBundle.getMessage(ColumnFieldsTopComponent.class, "ColumnFieldsTopComponent.jButtonUp.text")); // NOI18N
        jButtonUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpActionPerformed(evt);
            }
        });

        jButtonDown.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDown, org.openide.util.NbBundle.getMessage(ColumnFieldsTopComponent.class, "ColumnFieldsTopComponent.jButtonDown.text")); // NOI18N
        jButtonDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDownActionPerformed(evt);
            }
        });

        jButtonApply.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonApply, org.openide.util.NbBundle.getMessage(ColumnFieldsTopComponent.class, "ColumnFieldsTopComponent.jButtonApply.text")); // NOI18N
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        jComboBoxObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxObject.setEnabled(false);

        jButtonAdd.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(ColumnFieldsTopComponent.class, "ColumnFieldsTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(ColumnFieldsTopComponent.class, "ColumnFieldsTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        columnPropertiesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "id","Name","Display","Sums...","Aggreg","Time","Cur.","Convert first","Expo","Chart","Class","getters","ReturnType","ColumnType"
            }
        ));
        columnPropertiesTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        columnPropertiesTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                columnPropertiesTableMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                columnPropertiesTableMousePressed(evt);
            }
        });
        columnPropertiesScrollPane.setViewportView(columnPropertiesTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(columnPropertiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonRemove)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonUp, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDown))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonApply, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxObject, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonUp)
                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonRemove)
                    .addComponent(jButtonDown)
                    .addComponent(jButtonApply))
                .addGap(18, 18, 18)
                .addComponent(columnPropertiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 464, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(180, Short.MAX_VALUE))
        );

        globalScrollPane.setViewportView(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(globalScrollPane)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * select in table
     *
     * @param evt
     */
    private void columnPropertiesTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_columnPropertiesTableMouseClicked
    }//GEN-LAST:event_columnPropertiesTableMouseClicked

    private void columnPropertiesTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_columnPropertiesTableMousePressed
    }//GEN-LAST:event_columnPropertiesTableMousePressed

    /**
     * UP
     *
     * @param evt
     */
    private void jButtonUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpActionPerformed

        DefaultTableModel tm = (DefaultTableModel) columnPropertiesTable.getModel();
        if (columnPropertiesTable.getSelectedRow() > 0) {
            tm.moveRow(columnPropertiesTable.getSelectedRow(), columnPropertiesTable.getSelectedRow(), columnPropertiesTable.getSelectedRow() - 1);
            columnPropertiesTable.setRowSelectionInterval(columnPropertiesTable.getSelectedRow() - 1, columnPropertiesTable.getSelectedRow() - 1);
        }
    }//GEN-LAST:event_jButtonUpActionPerformed

    /**
     * DOWN
     *
     * @param evt
     */
    private void jButtonDownActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDownActionPerformed

        DefaultTableModel tm = (DefaultTableModel) columnPropertiesTable.getModel();
        if (columnPropertiesTable.getSelectedRow() < columnPropertiesTable.getRowCount() - 1) {
            tm.moveRow(columnPropertiesTable.getSelectedRow(), columnPropertiesTable.getSelectedRow(), columnPropertiesTable.getSelectedRow() + 1);
            columnPropertiesTable.setRowSelectionInterval(columnPropertiesTable.getSelectedRow() + 1, columnPropertiesTable.getSelectedRow() + 1);
        }
    }//GEN-LAST:event_jButtonDownActionPerformed

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        DefaultTableModel tm = (DefaultTableModel) columnPropertiesTable.getModel();
        GaiaReportTopComponent lastActive = CentralLookup.LastActiveReportTopComponent(template);
        CentralLookup.getDefault().remove(template);
        template.setTemplateColumnItems(new ArrayList());

        for (int i = 0; i < tm.getRowCount(); i++) {
            TemplateColumnItem templateColumnItem = new TemplateColumnItem();

            templateColumnItem.setIsEvol(true);
            if (!GUIUtils.getTableValueAt(columnPropertiesTable, i, iId).isEmpty()) {
                templateColumnItem.setColumnItemId(Integer.parseInt(GUIUtils.getTableValueAt(columnPropertiesTable, i, iId)));
            }

            templateColumnItem.setColumnType(GUIUtils.getTableValueAt(columnPropertiesTable, i, 8));
            templateColumnItem.setName(GUIUtils.getTableValueAt(columnPropertiesTable, i, iName));
            templateColumnItem.setDisplayName(GUIUtils.getTableValueAt(columnPropertiesTable, i, iDisplayName));
            templateColumnItem.setAggregation(GUIUtils.getTableValueAt(columnPropertiesTable, i, iAggregation));
            templateColumnItem.setIsAggregated(Boolean.parseBoolean(GUIUtils.getTableValueAt(columnPropertiesTable, i, iAggregated)));
            templateColumnItem.setSuffix(GUIUtils.getTableValueAt(columnPropertiesTable, i, iSuffix));
            templateColumnItem.setGetter(GUIUtils.getTableValueAt(columnPropertiesTable, i, iGetter));
            templateColumnItem.setParam(GUIUtils.getTableValueAt(columnPropertiesTable, i, iParam));
            templateColumnItem.setReturnType(GUIUtils.getTableValueAt(columnPropertiesTable, i, iReturnType));
            templateColumnItem.setColumnType(GUIUtils.getTableValueAt(columnPropertiesTable, i, iColumnType));
            templateColumnItem.setIsConversion(Boolean.parseBoolean(GUIUtils.getTableValueAt(columnPropertiesTable, i, iConversion)));
            templateColumnItem.setIsConversionFirst(Boolean.parseBoolean(GUIUtils.getTableValueAt(columnPropertiesTable, i, iConversionFirst)));
            templateColumnItem.setIsExposure(Boolean.parseBoolean(GUIUtils.getTableValueAt(columnPropertiesTable, i, iIsExposure)));
            templateColumnItem.setIsInChart(Boolean.parseBoolean(GUIUtils.getTableValueAt(columnPropertiesTable, i, iIsInChart)));
            templateColumnItem.setColumnIndex(new Integer(i));
            templateColumnItem.setTemplate(template);
            template.getTemplateColumnItems().add(templateColumnItem);
        }

        template.setIsDrillDown(isDrillDownActive);
        template.setReportEnabled(true);
        if (lastActive != null) {
            CentralLookup.getDefault().add(lastActive, template);
            lastActive.runReport();
        }

    }//GEN-LAST:event_jButtonApplyActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed

        FieldChooser fieldChooser = new FieldChooser(objectType, template, null);

        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(fieldChooser, "Field Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            List<TemplateColumnItem> itemList = fieldChooser.getTemplateColumnItem();
            for (TemplateColumnItem templateColumnItem : itemList) {
                addToTable(templateColumnItem);
            }
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    /**
     * add data to table
     *
     * @param templateColumnItem
     */
    public void addToTable(TemplateColumnItem templateColumnItem) {
        DefaultTableModel tm = (DefaultTableModel) columnPropertiesTable.getModel();
        Vector tableData = new Vector();
        if (templateColumnItem != null) {
            tableData.add(templateColumnItem.getColumnItemId());
            tableData.add(templateColumnItem.getName());
            tableData.add(templateColumnItem.getDisplayName());
            tableData.add(templateColumnItem.getAggregation());
            tableData.add(templateColumnItem.isAggregated());
            tableData.add(templateColumnItem.getSuffix());
            tableData.add(templateColumnItem.isConversion());
            tableData.add(templateColumnItem.isConversionFirst());
            tableData.add(templateColumnItem.isExposure());
            tableData.add(templateColumnItem.isInChart());
            tableData.add(templateColumnItem.getGetter());
            tableData.add(templateColumnItem.getParam());
            tableData.add(templateColumnItem.getReturnType());
            tableData.add(templateColumnItem.getColumnType());
            addRowSettings(tableData);
        }
        columnPropertiesTable.setRowSelectionInterval(tm.getRowCount() - 1, tm.getRowCount() - 1);
    }

    /**
     * remove from table
     *
     * @param evt
     */
    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        DefaultTableModel tm = (DefaultTableModel) columnPropertiesTable.getModel();
        int index = columnPropertiesTable.getSelectedRow();

        String name = GUIUtils.getTableValueAt(columnPropertiesTable, index, iName);
        if (index >= 0) {
            tm.removeRow(index);
            if (template != null) {
                if (template.getTemplateColumnItems() != null) {
                    TemplateColumnItem toBeRemoved = null;
                    for (TemplateColumnItem templateColumnItem : template.getTemplateColumnItems()) {
                        if (templateColumnItem.getName().equals(name)) {
                            template.getTemplateColumnItems().remove(toBeRemoved);
                        }
                    }
                }
            }
            if (index < tm.getRowCount()) {
                columnPropertiesTable.setRowSelectionInterval(index, index);
            } else {
                columnPropertiesTable.setRowSelectionInterval(tm.getRowCount() - 1, tm.getRowCount() - 1);
            }
        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane columnPropertiesScrollPane;
    private javax.swing.JTable columnPropertiesTable;
    private javax.swing.JScrollPane globalScrollPane;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonDown;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonUp;
    private javax.swing.JComboBox jComboBoxObject;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
        result = Utilities.actionsGlobalContext().lookupResult(ReportTemplate.class);
        result.addLookupListener(this);

    }

    @Override
    public void componentClosed() {
        if (result != null) {
            //         result.removeLookupListener(this);
        }
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
