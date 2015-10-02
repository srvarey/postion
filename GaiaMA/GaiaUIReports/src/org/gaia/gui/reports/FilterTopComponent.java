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

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketDataSourceUtils;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.AvailableValueList;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.DateEditorPanel;
import org.gaia.gui.common.FieldValuesListChooser;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.dateFormatter;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/*
 * Top component which displays filters.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//Filter//EN", autostore = false)
@TopComponent.Description(preferredID = "FilterTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.FilterTopComponent")
@ActionReference(path = "Menu" + MenuManager.FilterTopComponentMenu, position = MenuManager.FilterTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FilterAction", preferredID = "FilterTopComponent")
@Messages({"CTL_FilterAction=Filter", "CTL_FilterTopComponent=Filter Window", "HINT_FilterTopComponent=This is a Filter window"})
/**
 * class FilterTopComponent, management filter
 */
public class FilterTopComponent extends GaiaTopComponent {

    private List<String> filterNames = new ArrayList();
    private ArrayList<FilterCriteria> removedElts = new ArrayList();
    private Filter filter;
    private Class objectType;
    private ReportTemplate template = null;
    private boolean isDrillDownActive;
    private static final Logger logger = Logger.getLogger(FilterTopComponent.class);
    private GaiaReportTopComponent reportWindow = null;
    private DefaultTableModel model;
    private static final int iColumn = 0;
    private static final int iIn = 1;
    private static final int iValue1 = 2;
    private static final int iValue2 = 3;
    protected static final int iPostTreatment = 4;
    private static final int iGetter = 5;
    private static final int iParam = 6;
    private static final int iType = 7;
    private static final int iColumnType = 8;
    private static final int iId = 9;
    private static final String[] inNotInArray = {"in", "not in", "between"};

    public FilterTopComponent() {
        initComponents();
        setName(Bundle.CTL_FilterTopComponent());
        setToolTipText(Bundle.HINT_FilterTopComponent());
        model = (DefaultTableModel) jTableCriteria.getModel();
    }

    public void setTemplate(ReportTemplate _template, GaiaReportTopComponent _reportWindow) {
        reportWindow = _reportWindow;
        objectTypeComboBox.setSelectedItem(ReportUtils.getReportType(_template.getObjectTypeClass()));
        template = _template;
        if (template != null && template.getFilter() != null) {
            refreshWindow();
        }
    }

    @Override
    public void initContext() {
        try {
            List<String> objectTypelist = ReportUtils.getReportObjectList();
            GUIUtils.fillCombo(objectTypeComboBox, objectTypelist);
            objectTypeComboBox.setSelectedItem(0);
            objectType = ReportUtils.getObjectType(GUIUtils.getComponentStringValue(objectTypeComboBox));
            reloadFilterNameCombo(objectType);
            jTableCriteria.getTableHeader().setReorderingAllowed(false);
            if (template != null) {
                refreshWindow();
            }
            TableColumnModel columnModel = jTableCriteria.getColumnModel();
            for (int i = iPostTreatment; i <= iId; i++) {
                columnModel.getColumn(i).setMinWidth(0);
                columnModel.getColumn(i).setMaxWidth(0);
            }
            jTableCriteria.getColumnModel().getColumn(iIn).setMinWidth(70);
            jTableCriteria.getColumnModel().getColumn(iIn).setMaxWidth(70);
            jTableCriteria.getColumnModel().getColumn(iValue1).setCellEditor(new CustomTableCellEditor());
            jTableCriteria.getColumnModel().getColumn(iValue2).setCellEditor(new CustomTableCellEditor());
            jTableCriteria.getColumnModel().getColumn(iValue2).setMinWidth(200);
            jTableCriteria.getColumnModel().getColumn(iValue2).setMaxWidth(200);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }

    /**
     * reload Filter
     *
     * @param objectType
     */
    public void reloadFilterNameCombo(Class objectType) {
        if (objectType != null) {
            try {
                filterNames = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, objectType);
                GUIUtils.fillComboWithNullFirst(jComboBoxFilterName, filterNames);
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     * load
     */
    public void load() {

        DateFormat hibernateDateFormat = (DateFormat) DAOCallerAgent.callMethod(DateUtils.class, DateUtils.GET_HIBERNATE_DATE_FORMAT);
        if (jComboBoxFilterName.getSelectedItem() != null) {
            try {
                filter = (Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, jComboBoxFilterName.getSelectedItem().toString(), objectType);

                model = (DefaultTableModel) jTableCriteria.getModel();
                model.getDataVector().clear();
                model.setRowCount(0);
                if (filter != null) {
                    if (filter.getFilterCriteria() != null) {
                        if (template!=null && template.isFundLookThrough()){
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setMinWidth(100);
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setMaxWidth(100);
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setPreferredWidth(100);
                        } else {
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setMinWidth(0);
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setMaxWidth(0);
                            jTableCriteria.getColumnModel().getColumn(iPostTreatment).setPreferredWidth(0);
                        }
                        jTableCriteria.repaint();

                        for (FilterCriteria filterCriteria : filter.getFilterCriteria()) {
                            Vector tableData = new Vector();
                            tableData.add(filterCriteria.getColumnName());
                            if (filterCriteria.getReturnType().equalsIgnoreCase(Date.class.getName())) {
                                tableData.add("between");
                            } else {
                                tableData.add(filterCriteria.getNotIn() ? "not in" : "in");
                            }
                            try {
                                Date date = hibernateDateFormat.parse(filterCriteria.getValue());
                                tableData.add(GaiaTopComponent.dateFormatter.format(date));
                            } catch (Exception e) {
                                if (filterCriteria.getReturnType().equalsIgnoreCase(Boolean.class.getName())) {
                                    tableData.add(Boolean.parseBoolean(filterCriteria.getValue()));
                                    jTableCriteria.getColumnModel().getColumn(iValue1).setCellRenderer(new myFormatRenderer());
                                } else {
                                    tableData.add(filterCriteria.getValue());
                                }
                            }
                            try {
                                Date date = hibernateDateFormat.parse(filterCriteria.getValue2());
                                tableData.add(GaiaTopComponent.dateFormatter.format(date));
                            } catch (Exception e) {
                                tableData.add(filterCriteria.getValue2());
                            }

                            tableData.add(filterCriteria.isPostTreatment());
                            tableData.add(filterCriteria.getGetter());
                            tableData.add(filterCriteria.getParam());
                            tableData.add(filterCriteria.getReturnType());
                            tableData.add(filterCriteria.getColumnType());
                            tableData.add(filterCriteria.getFilterCriteriaId());
                            addRowSettings(tableData);
                        }
                    }
                }
                model.fireTableDataChanged();
                removedElts = new ArrayList();
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        } else {
            model.getDataVector().clear();
        }
    }

    /**
     * add Row
     *
     * @param v
     */
    private void addRowSettings(Vector v) {
        ((DefaultTableModel) jTableCriteria.getModel()).addRow(v);
        jTableCriteria.getColumnModel().getColumn(iIn).setCellEditor(new DefaultCellEditor(new JComboBox(inNotInArray)));
        jTableCriteria.getColumnModel().getColumn(iValue1).setCellEditor(new CustomTableCellEditor());
        jTableCriteria.getColumnModel().getColumn(iValue2).setCellEditor(new CustomTableCellEditor());
        jTableCriteria.getColumnModel().getColumn(iPostTreatment).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        jTableCriteria.getColumnModel().getColumn(iPostTreatment).setCellRenderer(jTableCriteria.getDefaultRenderer(Boolean.class));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jComboBoxFilterName = new javax.swing.JComboBox();
        jButtonAddLine = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonApply = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCriteria = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>0) return true;
                return false;   //Disallow the editing of any cell
            }
        };
        fieldsLabel = new javax.swing.JLabel();
        objectTypeLabel = new javax.swing.JLabel();
        objectTypeComboBox = new javax.swing.JComboBox();
        jButtonDelete = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1491, 196));

        jScrollPane2.setPreferredSize(new java.awt.Dimension(1491, 681));

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(1056, 150));

        jComboBoxFilterName.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFilterName.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxFilterName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxFilterNameItemStateChanged(evt);
            }
        });

        jButtonAddLine.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddLine, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.jButtonAddLine.text")); // NOI18N
        jButtonAddLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddLineActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonApply.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonApply, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.jButtonApply.text")); // NOI18N
        jButtonApply.setEnabled(false);
        jButtonApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonApplyActionPerformed(evt);
            }
        });

        jTableCriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Column","in","Value","Value 2","Is Post Treatment","Getter","Param","Type","Column type","Id"
            }
        ));
        jTableCriteria.setRowHeight(20);
        jScrollPane1.setViewportView(jTableCriteria);

        org.openide.awt.Mnemonics.setLocalizedText(fieldsLabel, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.fieldsLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(objectTypeLabel, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.objectTypeLabel.text")); // NOI18N

        objectTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        objectTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                objectTypeComboBoxItemStateChanged(evt);
            }
        });
        objectTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                objectTypeComboBoxActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(FilterTopComponent.class, "FilterTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.setMaximumSize(new java.awt.Dimension(57, 23));
        jButtonDelete.setMinimumSize(new java.awt.Dimension(57, 23));
        jButtonDelete.setPreferredSize(new java.awt.Dimension(57, 28));
        jButtonDelete.setRequestFocusEnabled(false);
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane1)
                .addGap(1, 1, 1))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(objectTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(objectTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fieldsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jComboBoxFilterName, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddLine, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 665, Short.MAX_VALUE)
                        .addComponent(jButtonApply, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(objectTypeLabel)
                    .addComponent(fieldsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSave)
                        .addComponent(jButtonApply)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxFilterName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(objectTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonRemove)
                        .addComponent(jButtonAddLine)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddLineActionPerformed

        FieldChooser fieldChooser = new FieldChooser(objectType, template, null);

        NotifyDescriptor nd = new NotifyDescriptor(fieldChooser, "Field Chooser", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            List<TemplateColumnItem> tciList = fieldChooser.getTemplateColumnItem();
            for (TemplateColumnItem tci : tciList) {
                addToTable(tci);
            }
        }
    }//GEN-LAST:event_jButtonAddLineActionPerformed

    /**
     * add criteria line to table from templateColumnItem
     *
     * @param templateColumnItem
     */
    public void addToTable(TemplateColumnItem templateColumnItem) {
        DefaultTableModel tm = (DefaultTableModel) jTableCriteria.getModel();
        Vector tableData = new Vector();
        if (templateColumnItem != null) {
            tableData.add(templateColumnItem.getName());
            if (Date.class.getName().equalsIgnoreCase(templateColumnItem.getReturnType())) {
                tableData.add("between");
            } else {
                tableData.add("in");
            }
            tableData.add(StringUtils.EMPTY_STRING);
            tableData.add(StringUtils.EMPTY_STRING);
            tableData.add(Boolean.FALSE);
            tableData.add(templateColumnItem.getGetter());
            tableData.add(templateColumnItem.getParam());
            tableData.add(templateColumnItem.getReturnType());
            tableData.add(templateColumnItem.getColumnType());
            tableData.add(null);

            addRowSettings(tableData);
        }
        jTableCriteria.setRowSelectionInterval(tm.getRowCount() - 1, tm.getRowCount() - 1);
    }

    /**
     * save function
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * save
     */
    public void save() {
        String name;
        if (jComboBoxFilterName.getSelectedItem() != null) {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Filter", "Trade Filter", JOptionPane.PLAIN_MESSAGE, null, null, jComboBoxFilterName.getSelectedItem().toString());
        } else {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Filter", "Trade Filter", JOptionPane.PLAIN_MESSAGE, null, null, "");
        }

        if (name != null && !name.isEmpty()) {
            try {
                filter = (Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, name, objectType);
                boolean isNew = false;
                if (filter == null) {
                    isNew = true;
                }
                fillFilter(name);
                if (isNew) {
                    jComboBoxFilterName.addItem(name);
                    filter.setName(name);
                }

                DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.STORE_FILTER, filter);

                if (removedElts != null) {
                    for (FilterCriteria filterCriteria : removedElts) {
                        DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.DELETE_FILTER_CRITERIA, filterCriteria);
                    }
                }
                if (template != null) {
                    int applyToTemplate = JOptionPane.showConfirmDialog(this, "Apply new Filter on current report?", "New filter saved", JOptionPane.YES_NO_OPTION);
                    if (JOptionPane.YES_OPTION == applyToTemplate) {
                        applyToCurrentTemplate();
                    }
                }

            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
            jComboBoxFilterName.setSelectedItem(name);

            load();
        } else {
            JOptionPane.showMessageDialog(this, "Must not be empty.");
        }
    }

    public void fillFilter(String name_) {
        if (filter == null) {
            filter = new Filter();
            filter.setObjectTypeClass(objectType);
            removedElts.clear();
        }
        filter.setFilterCriteria(new ArrayList());

        DefaultTableModel tableModel = (DefaultTableModel) jTableCriteria.getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if (!GUIUtils.getTableValueAt(jTableCriteria, i, 0).isEmpty()) {
                FilterCriteria filterCriteria = null;
                try {
                    String sId = GUIUtils.getTableValueAt(jTableCriteria, i, iId);
                    if (!sId.isEmpty()) {
                        Integer id = Integer.parseInt(sId);
                        filterCriteria = (FilterCriteria) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_CRITERIA_BY_ID, id);
                        if (!filterCriteria.getFilter().getName().equalsIgnoreCase(name_)) {
                            filterCriteria = null;
                        }
                    }
                    if (filterCriteria == null) {
                        filterCriteria = new FilterCriteria();
                    }
                    filterCriteria.setColumnName(GUIUtils.getTableValueAt(jTableCriteria, i, iColumn));
                    filterCriteria.setNotIn(GUIUtils.getTableValueAt(jTableCriteria, i, iIn).equals("not in"));

                    filterCriteria.setReturnType(GUIUtils.getTableValueAt(jTableCriteria, i, iType));
                    if (filterCriteria.getReturnType().equalsIgnoreCase(Date.class.getName())) {
                        if (NumberUtils.isInteger(GUIUtils.getTableValueAt(jTableCriteria, i, iValue1))) {
                            filterCriteria.setValue(GUIUtils.getTableValueAt(jTableCriteria, i, iValue1));
                        } else if (!StringUtils.isEmptyString(GUIUtils.getTableValueAt(jTableCriteria, i, iValue1))) {
                            filterCriteria.setValue(GUIUtils.guiDateFormatToHibernateFormat(GUIUtils.getTableValueAt(jTableCriteria, i, iValue1)));
                        } else {
                            filterCriteria.setValue(null);
                        }
                        if (NumberUtils.isInteger(GUIUtils.getTableValueAt(jTableCriteria, i, iValue2))) {
                            filterCriteria.setValue2(GUIUtils.getTableValueAt(jTableCriteria, i, iValue2));
                        } else if (!StringUtils.isEmptyString(GUIUtils.getTableValueAt(jTableCriteria, i, iValue2))) {
                            filterCriteria.setValue2(GUIUtils.guiDateFormatToHibernateFormat(GUIUtils.getTableValueAt(jTableCriteria, i, iValue2)));
                        } else {
                            filterCriteria.setValue2(null);
                        }
                    } else {
                        filterCriteria.setValue(GUIUtils.getTableValueAt(jTableCriteria, i, iValue1));
                        filterCriteria.setValue2(GUIUtils.getTableValueAt(jTableCriteria, i, iValue2));
                    }
                    filterCriteria.setPostTreatment(Boolean.parseBoolean(GUIUtils.getTableValueAt(jTableCriteria, i, iPostTreatment)));
                    filterCriteria.setGetter(GUIUtils.getTableValueAt(jTableCriteria, i, iGetter));
                    filterCriteria.setParam(GUIUtils.getTableValueAt(jTableCriteria, i, iParam));
                    filterCriteria.setColumnType(GUIUtils.getTableValueAt(jTableCriteria, i, iColumnType));

                    filterCriteria.setFilter(filter);
                    filter.getFilterCriteria().add(filterCriteria);

                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
        }

    }

    /**
     * remove
     *
     * @param evt
     */
    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        if (removedElts == null) {
            removedElts = new ArrayList();
        }
        model = (DefaultTableModel) jTableCriteria.getModel();
        if (jTableCriteria.getSelectedRow() >= 0) {
            String name = GUIUtils.getTableValueAt(jTableCriteria, jTableCriteria.getSelectedRow(), 0);
            FilterCriteria toBeRemoved = null;
            for (FilterCriteria filterCriteria : filter.getFilterCriteria()) {
                if (name.equals(filterCriteria.getColumnName())) {
                    toBeRemoved = filterCriteria;
                }
            }
            if (toBeRemoved != null) {
                filter.getFilterCriteria().remove(toBeRemoved);
            }
            if (model.getValueAt(jTableCriteria.getSelectedRow(), iId) != null) {
                removedElts.add(toBeRemoved);
            }
            model.removeRow(jTableCriteria.getSelectedRow());
            jTableCriteria.setModel(model);
        }

    }//GEN-LAST:event_jButtonRemoveActionPerformed

    /**
     * Delete filter
     *
     * @param evt
     */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed

        String filterName = null;
        Integer filterId;
        Filter filterObject;

        if (filter != null) {
            filterName = filter.getName();
        }

        filterNames = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, objectType);

        filterName = (String) JOptionPane.showInputDialog(this, "Delete the filter", "Filter", JOptionPane.PLAIN_MESSAGE, null, filterNames.toArray(), filterName);

        if (filterName != null) {

            filterObject = (Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, filterName, objectType);
            filterId = filterObject.getFilterId();

            ArrayList<String> filterTemplates = (ArrayList) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_TEMPLATE_NAMES_BY_FILTER_ID, filterId);
            ArrayList<String> filterCustomColumns = (ArrayList) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_NAMES_BY_FILTER_ID, filterId);
            ArrayList<String> filterGroups = (ArrayList) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_GROUP_NAMES_BY_FILTER_ID, filterId);
            ArrayList<String> filterPricingEnvironments = (ArrayList) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_NAMES_BY_FILTER_NAME, filterName);
            ArrayList<String> filterMarketData = (ArrayList) DAOCallerAgent.callMethod(MarketDataSourceUtils.class, MarketDataSourceUtils.GET_MARKET_DATA_SOURCE_NAMES_BY_FILTER_ID, filterId);

            /**
             * if the filter exist in other data
             */
            if (!filterTemplates.isEmpty() || !filterCustomColumns.isEmpty() || !filterGroups.isEmpty()
                    || !filterPricingEnvironments.isEmpty() || !filterMarketData.isEmpty()) {

                StringBuilder error = new StringBuilder("You cannot delete this filter because it is used by\n");
                for (String marketDataSource : filterMarketData) {
                    error.append("Market Data Source ").append(marketDataSource.toString()).append("\n");
                }
                for (String pricingEnvironment : filterPricingEnvironments) {
                    error.append("Pricing Environment ").append(pricingEnvironment.toString()).append("\n");
                }
                for (String group : filterGroups) {
                    error.append("Filter Group ").append(group.toString()).append("\n");
                }
                for (String customColumn : filterCustomColumns) {
                    error.append("Custom Column ").append(customColumn.toString()).append("\n");
                }
                for (String reportTemplate : filterTemplates) {
                    error.append("Report Template ").append(reportTemplate.toString()).append("\n");
                }
                JOptionPane.showMessageDialog(this, error.toString(), "Not possible", JOptionPane.OK_OPTION);

            } else {
                DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.DELETE_FILTER_BY_NAME_AND_TYPE, filterName, objectType);
                jComboBoxFilterName.removeItem(filterName);
                ((DefaultTableModel) jTableCriteria.getModel()).getDataVector().clear();
            }
        }

    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * If current template not null apply new filter
     */
    private void applyToCurrentTemplate() {
        if (template != null) {
            template.setFilter(filter);
            template.setIsDrillDown(isDrillDownActive);
            template.setReportEnabled(true);
            WindowManager wm = WindowManager.getDefault();
            Set<TopComponent> openedComponent = wm.getRegistry().getOpened();
            for (TopComponent topComponent : openedComponent) {
                if (topComponent instanceof GaiaReportTopComponent) {
                    GaiaReportTopComponent temp = (GaiaReportTopComponent) topComponent;
                    if (temp.equals(reportWindow)) {
                        temp.setFilter(filter);
                        temp.runReport();
                        temp.requestActive();
                        break;
                    }
                }
            }
        }
    }

    private void jButtonApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonApplyActionPerformed
        fillFilter(filter.getName());
        applyToCurrentTemplate();
    }//GEN-LAST:event_jButtonApplyActionPerformed

    private void objectTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_objectTypeComboBoxItemStateChanged
        if (objectTypeComboBox.getSelectedItem() != null) {
            Class selectedObjectType = ReportUtils.getObjectType(objectTypeComboBox.getSelectedItem().toString());
            if (!selectedObjectType.equals(objectType)) {
                objectType = selectedObjectType;
                template = null;
                setDisplayName(getName() + StringUtils.SPACE + ReportUtils.getReportType(objectType));
                jButtonApply.setEnabled(false);
                reloadFilterNameCombo(objectType);
            }
        }
    }//GEN-LAST:event_objectTypeComboBoxItemStateChanged

    private void jComboBoxFilterNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxFilterNameItemStateChanged
        load();
    }//GEN-LAST:event_jComboBoxFilterNameItemStateChanged

    private void objectTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_objectTypeComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_objectTypeComboBoxActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fieldsLabel;
    private javax.swing.JButton jButtonAddLine;
    private javax.swing.JButton jButtonApply;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxFilterName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JTable jTableCriteria;
    private javax.swing.JComboBox objectTypeComboBox;
    private javax.swing.JLabel objectTypeLabel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    protected void componentActivated() {
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

    private void refreshWindow() {

        if (template != null) {
            objectType = template.getObjectTypeClass();
            filter = template.getFilter();
            setDisplayName(getName() + StringUtils.SPACE + ReportUtils.getReportType(objectType) + StringUtils.SPACE + template.getTemplateName());
            jButtonApply.setEnabled(true);
        }

        if (objectType == null) {
            objectType = ReportUtils.getObjectType((String) objectTypeComboBox.getItemAt(0));
            setDisplayName(getName() + StringUtils.SPACE + ReportUtils.getReportType(objectType));
        }
        objectTypeComboBox.setSelectedItem(ReportUtils.getReportType(objectType));
        if (filter != null) {
            reloadFilterNameCombo(objectType);
            jComboBoxFilterName.setSelectedItem(filter.getName());
        }
    }

    class CustomTableCellEditor extends AbstractCellEditor implements ActionListener, ItemListener, TableCellEditor {

        private String text;
        private final JButton button;
        private TableCellEditor editor;
        private static final String EDIT = "edit";
        private FieldValuesListChooser fieldValueChooser;
        private List<String> listOfValues;
        private List<String> selectedValuesList;
        private final JCheckBox checkBox;
        private boolean boolValue;

        public CustomTableCellEditor() {
            //Set up the editor (from the table's point of view),
            //which is a button.
            button = new JButton();
            button.setActionCommand(EDIT);
            button.addActionListener(this);
            button.setBorderPainted(false);
            checkBox = new JCheckBox();
//            checkBox.addItemListener(this);
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
//            Object source = e.getSource();
//            if (source instanceof AbstractButton == false) {
//                return;
//            }
//            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
////            text = String.valueOf(checked);
//            fireEditingStopped();
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (EDIT.equals(e.getActionCommand())) {
                //The user has clicked the cell, so
                fieldValueChooser = new FieldValuesListChooser(listOfValues, selectedValuesList);
                NotifyDescriptor nd = new NotifyDescriptor(fieldValueChooser, "Field Values Chooser", NotifyDescriptor.OK_CANCEL_OPTION,
                        NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

                if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                    text = StringUtils.collectionToString(fieldValueChooser.getSelectedValues(), StringUtils.COMMA);
                }
                fireEditingStopped();
            } else if (fieldValueChooser != null) {
                text = StringUtils.collectionToString(fieldValueChooser.getSelectedValues(), StringUtils.COMMA);
            }
        }

        @Override
        public Object getCellEditorValue() {
            if (!StringUtils.isEmptyString(text)) {
                return text;
            } else if (editor != null) {
                if (editor instanceof DateEditor && editor.getCellEditorValue() instanceof Date) {
                    Date date = (Date) editor.getCellEditorValue();
                    if (date != null) {
                        return dateFormatter.format(date);
                    }
                    return date;

                } else {
                    return editor.getCellEditorValue();
                }
            }
            return StringUtils.EMPTY_STRING;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

            String returnType = model.getValueAt(row, iType).toString();
            text = StringUtils.EMPTY_STRING;
            if (column == iValue2 || returnType.equalsIgnoreCase(Date.class.getName())) {
                editor = new DateEditor();
            } else if (column != iValue2 && returnType.equalsIgnoreCase(String.class.getName())) {
                Field field = getListedField(row);
                if (field != null) {
                    AvailableValueList annotation = (AvailableValueList) field.getAnnotation(AvailableValueList.class);
                    listOfValues = (List<String>) DAOCallerAgent.callMethod(ReportUtils.class,
                            ReportUtils.GET_FIELD_POSSIBLE_VALUE, field.getName(), annotation.isCurrency(), annotation.LegalEntityRoleList());
                    text = value.toString();
                    selectedValuesList = StringUtils.stringToArrayList(text, StringUtils.COMMA);
                    return button;
                } else {
                    editor = new DefaultCellEditor(new JTextField());
                }
            } else if (column != iValue2 && returnType.equalsIgnoreCase(Boolean.class.getName())) {
                editor = new DefaultCellEditor(checkBox);
            } else {
                editor = new DefaultCellEditor(new JTextField());
            }
            if (editor != null) {
                return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
            }
            return null;
        }
    }

    private Field getListedField(int row) {
        String getter = model.getValueAt(row, model.findColumn("Getter")).toString();
        String fieldName = IntrospectUtil.getFieldName(getter);
        Class className = IntrospectUtil.getClassNameFromColumn(objectType.getName(), getter);
        if (LegalEntity.class.equals(className) && "shortName".equalsIgnoreCase(fieldName)) {
            getter = getter.substring(0, getter.indexOf("/"));
            fieldName = IntrospectUtil.getFieldName(getter);
            className = IntrospectUtil.getClassNameFromColumn(objectType.getName(), getter);
        }
        List<Field> fieldList = IntrospectUtil.getFieldsByAnnotation(className, AvailableValueList.class);
        for (Field field : fieldList) {
            if (field.getName().equalsIgnoreCase(fieldName)) {
                return field;
            }
        }
        return null;
    }

    class DateEditor extends AbstractCellEditor implements TableCellEditor {

        private DateEditorPanel editorPanel;
        private Object value;

        @Override
        public Object getCellEditorValue() {
            value = editorPanel.getValue();
            return value;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (editorPanel == null) {
                editorPanel = new DateEditorPanel(value);
            }
            return editorPanel;
        }
    }

    public class myFormatRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component renderedComp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            try {
                if (value instanceof Boolean) {
                    renderedComp = new JCheckBox();
                }
            } catch (Exception e) {
            }
            return renderedComp;
        }
    }

}
