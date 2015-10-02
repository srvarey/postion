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
package org.gaia.gui.referentials;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.referentials.UserAccessor.UserSettings;
import org.gaia.domain.referentials.UserPreference;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.ComboBoxTableEditor;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.common.TableCellListener;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays User Settings.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.referentials//UserPreference//EN", autostore = false)
@TopComponent.Description(preferredID = "UserPreferenceTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.referentials.UserPreferenceTopComponent")
@ActionReference(path = "Menu/Admin", position = MenuManager.UserPreferenceTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_UserPreferenceAction", preferredID = "UserPreferenceTopComponent")
@Messages({
    "CTL_UserPreferenceAction=User Preferences",
    "CTL_UserPreferenceTopComponent=User Preferences Window",
    "HINT_UserPreferenceTopComponent=This is a User Preferences window"
})
public final class UserPreferenceTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(UserPreferenceTopComponent.class);
    private DefaultTableModel model;
    List<UserPreference> removedsetting = null;
    List<UserPreference> userPreferences;

    public UserPreferenceTopComponent() {
        initComponents();
        setName(Bundle.CTL_UserPreferenceTopComponent());
        setToolTipText(Bundle.HINT_UserPreferenceTopComponent());
        if (LoggedUser.getLoggedUserName() != null) {
            shortNamejFormattedTextField.setText("    " + LoggedUser.getLoggedUserName());
        }
    }

    @Override
    public void initContext() {
        model = (DefaultTableModel) userPreferencejTable.getModel();
        ComboBoxTableEditor editor = new ComboBoxTableEditor(model.findColumn("Setting"));
        for (UserSettings setting : UserSettings.values()) {
            List<String> values = (List) DAOCallerAgent.callMethod(setting.clazz, setting.method, setting.args);
            editor.addModel(setting.name(), values);
        }
        userPreferencejTable.getColumnModel().getColumn(model.findColumn("Value")).setCellEditor(editor);
        displayTable();
        Action action = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableCellListener tcl = (TableCellListener) e.getSource();
                int column = tcl.getColumn();
                if (column == model.findColumn("Setting")) {
                    int row = tcl.getRow();
                    model.setValueAt(null, row, model.findColumn("Value"));
                }
            }
        };
        new TableCellListener(userPreferencejTable, action);
    }

    public void setUpColumnEditor(JTable userPreferenceTable) {
        JComboBox combo = new JComboBox();
        for (UserAccessor.UserSettings preference : UserAccessor.UserSettings.values()) {
            combo.addItem(preference.name());
        }
        userPreferenceTable.getColumnModel().getColumn(model.findColumn("Setting")).setCellEditor(new ComboBoxCellEditor(combo));
    }

    /**
     * display table
     */
    private void displayTable() {
        if (LoggedUser.getLoggedUserId() != null) {
            userPreferences = (List<UserPreference>) DAOCallerAgent.callMethod(UserAccessor.class,
                    UserAccessor.LOAD_USER_PREFERENCES, LoggedUser.getLoggedUserId());
            DefaultTableModel tableModel = (DefaultTableModel) userPreferencejTable.getModel();
            tableModel.getDataVector().clear();
            setUpColumnEditor(userPreferencejTable);

            if (userPreferences != null) {
                for (UserPreference userPref : userPreferences) {
                    Vector row = new Vector();
                    if (userPref.getUserPreferenceId() != null) {
                        row.add(userPref.getUserPreferenceId());
                    }
                    row.add(userPref.getSettingName());
                    if (userPref.getSettingValue() != null) {
                        row.add(userPref.getSettingValue());
                    }
                    tableModel.addRow(row);
                }
            }
        }
    }

    /**
     * saver a couple of setting and value
     */
    /**
     * saver a couple of setting and value
     */
    private void saveSettings() {
        int count;
        String value;
        String setting;
        Integer settingId;
        UserPreference userPreference;
        userPreferences = new ArrayList();
        List<String> errors = new ArrayList<>();
        for (int i = 0; i < userPreferencejTable.getRowCount(); i++) {
            if (userPreferencejTable.getValueAt(i, model.findColumn("Setting")) != null) {
                settingId = (Integer) userPreferencejTable.getValueAt(i, model.findColumn("Id"));
                setting = userPreferencejTable.getValueAt(i, model.findColumn("Setting")).toString();
                Object objectValue = userPreferencejTable.getValueAt(i, model.findColumn("Value"));
                if (objectValue == null) {
                    errors.add("Empty value not allowed for setting row " + i + 1 + " skipped");
                    continue;
                }
                value = objectValue.toString();
                if (settingId != null) {
                    userPreference = (UserPreference) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_USER_PREFERENCE_BY_ID, settingId);
                } else {
                    userPreference = new UserPreference();
                }
                userPreference.setSettingName(setting);
                userPreference.setSettingValue(value);
                userPreference.setUserId(LoggedUser.getLoggedUserId());
                count = 0;

                // checks if it is unique
                for (UserPreference compare : userPreferences) {
                    if (compare.getSettingName().equalsIgnoreCase(userPreference.getSettingName())) {
                        count++;
                    }
                }

                if (count == 0 || !UserAccessor.UserSettings.valueOf(userPreference.getSettingName()).isUnique) {
                    userPreferences.add(userPreference);
                    DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.STORE_USER_PREFERENCE, userPreference);
                }
            }
        }
        StringBuilder msg = new StringBuilder("Saved");
        if (!errors.isEmpty()) {
            msg.append(StringUtils.collectionToString(errors, "\n"));
        }
        JOptionPane.showMessageDialog(this, msg.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonAdd = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        shortNamejFormattedTextField = new javax.swing.JFormattedTextField();
        schortNamejLabel = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        userPreferencejTable = new org.jdesktop.swingx.JXTable();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jButtonAdd.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(UserPreferenceTopComponent.class, "UserPreferenceTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(UserPreferenceTopComponent.class, "UserPreferenceTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        shortNamejFormattedTextField.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(204, 204, 0)));
        shortNamejFormattedTextField.setForeground(new java.awt.Color(240, 240, 240));
        shortNamejFormattedTextField.setText(org.openide.util.NbBundle.getMessage(UserPreferenceTopComponent.class, "UserPreferenceTopComponent.shortNamejFormattedTextField.text")); // NOI18N
        shortNamejFormattedTextField.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        shortNamejFormattedTextField.setEnabled(false);
        shortNamejFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        schortNamejLabel.setBackground(new java.awt.Color(102, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(schortNamejLabel, org.openide.util.NbBundle.getMessage(UserPreferenceTopComponent.class, "UserPreferenceTopComponent.schortNamejLabel.text")); // NOI18N

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(UserPreferenceTopComponent.class, "UserPreferenceTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        userPreferencejTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Setting", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        userPreferencejTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(userPreferencejTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(schortNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(shortNamejFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRemove)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(shortNamejFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(schortNamejLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonSave))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        model = (DefaultTableModel) userPreferencejTable.getModel();
        model.addRow(new Vector());
        setUpColumnEditor(userPreferencejTable);
        userPreferencejTable.setRowSelectionInterval(model.getRowCount() - 1, model.getRowCount() - 1);
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        removeRow();
    }

    private void removeRow() {
        int selectedRow = userPreferencejTable.getSelectedRow();
        if (selectedRow < 0) {
            return;
        }
        Integer settingId = (Integer) userPreferencejTable.getValueAt(selectedRow, model.findColumn("Id"));
        model.removeRow(selectedRow);
        UserPreference userSetting = (UserPreference) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_USER_PREFERENCE_BY_ID, settingId);
        DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.DELETE_USER_PREFERENCE, userSetting);
        displayTable();
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            saveSettings();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel schortNamejLabel;
    private javax.swing.JFormattedTextField shortNamejFormattedTextField;
    private org.jdesktop.swingx.JXTable userPreferencejTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
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
