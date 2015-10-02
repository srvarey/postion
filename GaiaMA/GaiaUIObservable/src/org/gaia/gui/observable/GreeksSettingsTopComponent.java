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
package org.gaia.gui.observable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.AbstractObservable.ObservableType;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.Measure;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.observables.Stress;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
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
 * Top component which displays Greek settings.
 * @author Benjamin Frerejean
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//GreeksSettings//EN", autostore = false)
@TopComponent.Description(preferredID = "GreeksSettingsTopComponent", iconBase="org/gaia/gui/observable/greeks.png", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.GreeksSettingsTopComponent")
@ActionReference(path = "Menu"+MenuManager.GreeksSettingsTopComponentMenu , position = MenuManager.GreeksSettingsTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_GreeksSettingsAction", preferredID = "GreeksSettingsTopComponent")
@Messages({"CTL_GreeksSettingsAction=Greek Settings", "CTL_GreeksSettingsTopComponent=Greek Settings Window", "HINT_GreeksSettingsTopComponent=This is a Greek Settings window"})

public final class GreeksSettingsTopComponent extends GaiaTopComponent {

    private List<String> listDataTypes;
    private List<IPricerMeasure> listMeasures;
    private List<String> listMeasuresNames;
    private List<GreekSetting> removed;
    private Map<String, Stress> greeksShifts;

    private final int iMeasureName = 0;
    private final int iMovingMeasure = 1;
    private final int iShifted = 2;
    private final int iShift = 3;
    private final int iAbsolute = 4;
    private final int iInAmount = 5;
    private final int iIsBusDays = 6;

    public GreeksSettingsTopComponent() {
        initComponents();
        setName(Bundle.CTL_GreeksSettingsTopComponent());
        setToolTipText(Bundle.HINT_GreeksSettingsTopComponent());
        jTableGreeks.getDefaultEditor(String.class).addCellEditorListener(new MyCellEditorListener());
    }

    @Override
    public void initContext() {
        jTableGreeks.getTableHeader().setReorderingAllowed(false);

        listMeasures = (List) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class,
                MeasuresAccessor.GET_MEASURES_BY_GROUP_NO_UNITS, MeasuresAccessor.MeasureGroup.PV_GROUP);
        listMeasuresNames = new ArrayList();
        for (IPricerMeasure measure : listMeasures) {
            listMeasuresNames.add(measure.getName());
        }

        listDataTypes = new ArrayList();
        ObservableType[] obsTypes = AbstractObservable.ObservableType.values();
        for (ObservableType obsType : obsTypes) {
            listDataTypes.add(obsType.name);
        }

        load();
    }

    public void load() {
        ((DefaultTableModel) jTableGreeks.getModel()).getDataVector().clear();

        greeksShifts = new HashMap();
        List<GreekSetting> settings = (List) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.GET_GREEK_SETTINGS);
        for (GreekSetting setting : settings) {
            Object[] row = new Object[iIsBusDays + 1];
            row[iMeasureName] = setting.getPricerMeasure();
            row[iMovingMeasure] = setting.getMovingPricerMeasure();
            row[iShifted] = setting.getShifted();
            if (setting.getStress() != null) {
                row[iShift] = setting.getStress().shiftsDesc();
            }
            row[iAbsolute] = setting.isAbsolute();
            row[iInAmount] = setting.isInAmount();
            row[iIsBusDays] = setting.isBusDaysTimeShift();
            addRow(row);
            greeksShifts.put(setting.getPricerMeasure(), setting.getStress());
        }
        removed = new ArrayList();
    }

    public void addRow(Object[] row) {
        ((DefaultTableModel) jTableGreeks.getModel()).addRow(row);

        TableColumnModel columnModel = jTableGreeks.getColumnModel();

        String[] arrayListMeasures = (String[]) listMeasuresNames.toArray(new String[listMeasuresNames.size()]);
        columnModel.getColumn(iMovingMeasure).setCellEditor(new DefaultCellEditor(new JComboBox(arrayListMeasures)));

        String[] arrayDataTypes = (String[]) listDataTypes.toArray(new String[listDataTypes.size()]);
        columnModel.getColumn(iShifted).setCellEditor(new DefaultCellEditor(new JComboBox(arrayDataTypes)));

        columnModel.getColumn(iAbsolute).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        columnModel.getColumn(iAbsolute).setCellRenderer(jTableGreeks.getDefaultRenderer(Boolean.class));

        columnModel.getColumn(iInAmount).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        columnModel.getColumn(iInAmount).setCellRenderer(jTableGreeks.getDefaultRenderer(Boolean.class));

        columnModel.getColumn(iIsBusDays).setCellEditor(new DefaultCellEditor(new JCheckBox()));
        columnModel.getColumn(iIsBusDays).setCellRenderer(jTableGreeks.getDefaultRenderer(Boolean.class));
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGreeks = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex==iShift)
                return false;
                else
                return true;
            }
        };
        addJButton = new javax.swing.JButton();
        removeJButton = new javax.swing.JButton();
        saveJButton = new javax.swing.JButton();
        fillShiftButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTableGreeks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Measure", "Moving Measure", "To be shifted",  "Shift", "Is absolute / %", "Is in amount", "Is Bus days Time shift"
            }
        ));
        jTableGreeks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableGreeks.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableGreeksFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(jTableGreeks);

        addJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(addJButton, org.openide.util.NbBundle.getMessage(GreeksSettingsTopComponent.class, "GreeksSettingsTopComponent.addJButton.text")); // NOI18N
        addJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addJButtonActionPerformed(evt);
            }
        });

        removeJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(removeJButton, org.openide.util.NbBundle.getMessage(GreeksSettingsTopComponent.class, "GreeksSettingsTopComponent.removeJButton.text")); // NOI18N
        removeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeJButtonActionPerformed(evt);
            }
        });

        saveJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(saveJButton, org.openide.util.NbBundle.getMessage(GreeksSettingsTopComponent.class, "GreeksSettingsTopComponent.saveJButton.text")); // NOI18N
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });

        fillShiftButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(fillShiftButton, org.openide.util.NbBundle.getMessage(GreeksSettingsTopComponent.class, "GreeksSettingsTopComponent.text")); // NOI18N
        fillShiftButton.setName(""); // NOI18N
        fillShiftButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fillShiftButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(addJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeJButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saveJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fillShiftButton, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(addJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeJButton)
                        .addGap(31, 31, 31)
                        .addComponent(fillShiftButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(saveJButton)))
                .addContainerGap(56, Short.MAX_VALUE))
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

    private void addJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addJButtonActionPerformed
        addRow(new Object[iIsBusDays + 1]);
    }//GEN-LAST:event_addJButtonActionPerformed

    private void removeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeJButtonActionPerformed
        int row = jTableGreeks.getSelectedRow();

        if (row > -1) {

            DefaultTableModel tableModel = (DefaultTableModel) jTableGreeks.getModel();

            if (tableModel.getValueAt(row, iMeasureName) != null) {
                String name = tableModel.getValueAt(row, iMeasureName).toString();
                // check if not in report columns
                List<TemplateColumnItem> items = (List) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_MEASURE_TEMPLATE_COLUMN_ITEMS_BY_NAME, name);
                items.addAll((List) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_MEASURE_TEMPLATE_COLUMN_ITEMS_BY_NAME, name + MeasuresAccessor.UNIT_POSTFIX));
                if (!items.isEmpty()) {
                    String listTemplates = StringUtils.EMPTY_STRING;
                    for (TemplateColumnItem item : items) {
                        if (item.getTemplate() != null && !listTemplates.endsWith(item.getTemplate().getTemplateName() + StringUtils.COMMA)) {
                            listTemplates += item.getTemplate().getTemplateName() + StringUtils.COMMA;
                        }
                    }
                    if (listTemplates.length() > 0) {
                        JOptionPane.showMessageDialog(this, "The Measure cannot be removed : it belongs to following report template : " + listTemplates.substring(0, listTemplates.length() - 1));
                        return;
                    }
                }
                // check if not used by other greeks
                for (int i = 0; i < jTableGreeks.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 1).toString().equalsIgnoreCase(name)) {
                        JOptionPane.showMessageDialog(this, "The Measure cannot be removed : it is used by: " + tableModel.getValueAt(i, iMeasureName).toString());
                        return;
                    }
                }

                GreekSetting greekSetting = (GreekSetting) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.GET_GREEK_SETTING, name);
                if (greekSetting != null) {
                    removed.add(greekSetting);
                }
            }
            tableModel.removeRow(row);

        }
    }//GEN-LAST:event_removeJButtonActionPerformed

    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed
        DefaultTableModel tableModel = (DefaultTableModel) jTableGreeks.getModel();
        for (int row = 0; row < jTableGreeks.getRowCount(); row++) {
            try {
                GreekSetting greekSetting = null;
                String name = null;
                if (tableModel.getValueAt(row, iMeasureName) != null && GUIUtils.getTableValueAt(jTableGreeks, row, iMovingMeasure)!=null && !GUIUtils.getTableValueAt(jTableGreeks, row, iMovingMeasure).isEmpty()) {
                    name = tableModel.getValueAt(row, iMeasureName).toString();
                    greekSetting = (GreekSetting) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.GET_GREEK_SETTING, name);

                    if (greekSetting == null) {
                        greekSetting = new GreekSetting();
                    }
                    greekSetting.setPricerMeasure(name);
                    greekSetting.setMovingPricerMeasure(GUIUtils.getTableValueAt(jTableGreeks, row, iMovingMeasure));
                    greekSetting.setShifted(GUIUtils.getTableValueAt(jTableGreeks, row, iShifted));
                    greekSetting.setIsAbsolute(Boolean.parseBoolean(GUIUtils.getTableValueAt(jTableGreeks, row, iAbsolute)));
                    greekSetting.setIsInAmount(Boolean.parseBoolean(GUIUtils.getTableValueAt(jTableGreeks, row, iInAmount)));
                    if (greekSetting.getShifted().equalsIgnoreCase(AbstractObservable.ObservableType.TIME.name)) {
                        greekSetting.setIsInAmount(Boolean.parseBoolean(GUIUtils.getTableValueAt(jTableGreeks, row, iIsBusDays)));
                    }
                    Stress stress_ = greeksShifts.get(name);
                    // case of gamma : I force the stress to be the same
                    if (!greekSetting.getMovingPricerMeasure().equalsIgnoreCase(Measure.NPV.getName())) {
                        GreekSetting movingGreekSetting = (GreekSetting) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.GET_GREEK_SETTING, greekSetting.getMovingPricerMeasure());
                        if (movingGreekSetting != null && movingGreekSetting.getShifted().equalsIgnoreCase(greekSetting.getShifted())) {
                            stress_ = movingGreekSetting.getStress();
                        }
                    }
                    if (stress_ != null) {
                        stress_ = (Stress) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.STORE_STRESS, stress_);
                        greekSetting.setShiftDimension((short) stress_.getObservableShifts().size());
                        greekSetting.setStress(stress_);
                        DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.STORE_GREEK_SETTING, greekSetting);
                    } else {
                        JOptionPane.showMessageDialog(this, "Missing shift on row " + row);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Missing data on row " + row);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Wrong shift on row " + row);
            }
        }
        for (GreekSetting greekSetting : removed) {
            DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.DELETE_GREEK_SETTING, greekSetting);
        }
        removed = new ArrayList();
        JOptionPane.showMessageDialog(this, "Done ");
        load();
    }//GEN-LAST:event_saveJButtonActionPerformed

    private void fillShiftButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fillShiftButtonActionPerformed
        openShiftPanel();
    }//GEN-LAST:event_fillShiftButtonActionPerformed

    public void openShiftPanel() {
        int row = jTableGreeks.getSelectedRow();
        if (row >= 0) {
            String type = GUIUtils.getTableValueAt(jTableGreeks, row, iShifted);
            String name = GUIUtils.getTableValueAt(jTableGreeks, row, iMeasureName);
            if (!StringUtils.isEmptyString(type)) {
                ObservableShiftPanel shiftPanel = new ObservableShiftPanel(type, greeksShifts.get(name));
                NotifyDescriptor nd = new NotifyDescriptor(shiftPanel, "Shifts", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
                if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                    Stress stress=shiftPanel.getStress();
                    greeksShifts.put(name, stress);
                    DefaultTableModel tableModel = (DefaultTableModel) jTableGreeks.getModel();
                    tableModel.setValueAt(stress.shiftsDesc(), row, iShift);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Please fill the shifted data before");
            }
            jTableGreeks.setColumnSelectionInterval(0, 0);
        }
    }

    public class MyCellEditorListener implements CellEditorListener {

        @Override
        public void editingStopped(ChangeEvent e) {
            int row = jTableGreeks.getSelectedRow();
            String name = GUIUtils.getTableValueAt(jTableGreeks, row, iMeasureName);
            int i = 0;
            Stress stressToPut = null;
            for (Entry<String, Stress> entry : greeksShifts.entrySet()) {
                if (i == row && !entry.getKey().equalsIgnoreCase(name)) {
                    stressToPut = entry.getValue();
                }
                i++;
            }
            if (stressToPut != null) {
                greeksShifts.put(name, stressToPut);
            }
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private void jTableGreeksFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableGreeksFocusGained
        if (jTableGreeks.getSelectedColumn() == iShift) {
            openShiftPanel();
            fillShiftButton.requestFocus();
        }
    }//GEN-LAST:event_jTableGreeksFocusGained

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addJButton;
    private javax.swing.JButton fillShiftButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGreeks;
    private javax.swing.JButton removeJButton;
    private javax.swing.JButton saveJButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
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
