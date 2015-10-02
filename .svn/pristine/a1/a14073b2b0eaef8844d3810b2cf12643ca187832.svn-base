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
package org.gaia.gui.common;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Benjamin FREREJEAN
 */
public class LegalEntityFinder extends javax.swing.JPanel {

    private Integer tradeId = null;
    private static final Logger logger = Logger.getLogger(LegalEntityFinder.class);
    private String roleList;
    private TableRowSorter filter;
    private DefaultTableModel model;

    /**
     * Creates new form AssetFinder
     * @param roles
     */
    public LegalEntityFinder(List<String> roles) {
        initComponents();
        jTable1.getTableHeader().setReorderingAllowed(false);

        if (roles == null) {
            try {
                roles = (ArrayList) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ALL_ROLES);
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
        Collections.sort(roles);
        GUIUtils.fillComboWithNullFirst(jComboBoxRoles, roles);
        roleList = StringUtils.EMPTY_STRING;
        for (String role : roles) {
            if (!StringUtils.isEmptyString(role)) {
                roleList = roleList + StringUtils.QUOTE + role + "',";
            }
        }
        if (roleList.length() > 0) {
            roleList = roleList.substring(1, roleList.length() - 2);
        }

        List<String> attributes = (ArrayList) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ATTRIBUTES);
        GUIUtils.fillComboWithNullFirst(jComboBoxAttribute, attributes);

        jFormattedTextFieldId.setHorizontalAlignment(JFormattedTextField.RIGHT);
        jTable1.setAutoCreateRowSorter(true);
        TableColumnModel tcm = jTable1.getColumnModel();
        tcm.getColumn(0).setMaxWidth(45);
        ActionListener listener = new EnterListener(jTextFieldNameLike);
        filter = new TableRowSorter<>(model);
        jTable1.setRowSorter(filter);
        jTextFieldNameLike.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
    }

    /**
     * Update the row filter regular expression from the expression in the text
     * box.
     */
    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rf;
        // If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter("(?i)" + jTextFieldNameLike.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        filter.setRowFilter(rf);
    }

    public Integer getLegalEntityId() {
        Integer id = null;
        int row = jTable1.getSelectedRow();
        if (row > -1) {
            String value = GUIUtils.getTableValueAt(jTable1, row, 0);
            if (!value.isEmpty()) {
                id = Integer.parseInt(value);
            }
        }
        return id;
    }

    public class EnterListener implements ActionListener {

        /**
         * chargement quand on tape enter dans trade id
         */
        public EnterListener(JTextField jTextFieldTradeId) {
            KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
            jTextFieldNameLike.registerKeyboardAction(this, "Enter", enter, JComponent.WHEN_FOCUSED);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().compareTo("Enter") == 0) {
                jButtonFindActionPerformed(e);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jComboBoxRoles = new javax.swing.JComboBox();
        jComboBoxAttribute = new javax.swing.JComboBox();
        jTextFieldAttributeLike = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jFormattedTextFieldId = new javax.swing.JFormattedTextField(new DecimalFormat("########"));
        jTextFieldNameLike = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Name", "Code", "Roles"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(LegalEntityFinder.class, "LegalEntityFinder.jLabel1.text")); // NOI18N

        jComboBoxRoles.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxAttribute.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(LegalEntityFinder.class, "LegalEntityFinder.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        jTextFieldNameLike.setName("jTextFieldNameLike"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(LegalEntityFinder.class, "LegalEntityFinder.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(LegalEntityFinder.class, "LegalEntityFinder.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(LegalEntityFinder.class, "LegalEntityFinder.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldNameLike, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldAttributeLike)
                            .addComponent(jButtonFind, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxRoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldAttributeLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNameLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFind)
                    .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed
        try {
            /**
             * looking for LegalEntity
             */
            String name = jTextFieldNameLike.getText();
            String role = null;
            if (jComboBoxRoles.getSelectedItem() != null) {
                role = jComboBoxRoles.getSelectedItem().toString();
            }
            String attribute = null;
            if (jComboBoxAttribute.getSelectedItem() != null) {
                attribute = jComboBoxAttribute.getSelectedItem().toString();
            }
            String attributeVal = jTextFieldAttributeLike.getText();
            String legalEntityId = jFormattedTextFieldId.getText();

            Vector<String> tableHeaders = new Vector<String>();
            Vector tableData = new Vector();
            JTableHeader h = jTable1.getTableHeader();
            Enumeration<TableColumn> cols = h.getColumnModel().getColumns();

            while (cols.hasMoreElements()) {
                TableColumn c = cols.nextElement();
                tableHeaders.add(c.getHeaderValue().toString());
            }
            if (StringUtils.isEmptyString(role)) {
                role = roleList;
            }
            List<Object[]> list = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                    LegalEntityAccessor.LOAD_LEGAL_ENTITIES_WITH_FINDER, legalEntityId, attribute, name, role, attributeVal);

            Integer lastId = Integer.MIN_VALUE;
            Vector values = null;
            for (Object[] arrObjects : list) {
                if (lastId.equals(arrObjects[0])) {
                    values.set(3, values.get(3).toString() + StringUtils.COMMA + arrObjects[3]);
                } else {
                    /**
                     * Adding the element found in the table
                     */
                    if (values != null) {
                        tableData.add(values);
                    }
                    values = new Vector(Arrays.asList(arrObjects));
                    lastId = (Integer) arrObjects[0];
                }
            }
            if (values != null) {
                tableData.add(values);
            }
            model = new DefaultTableModel(tableData, tableHeaders);
            filter = new TableRowSorter<>(model);
            jTable1.setModel(model);
            jTable1.setRowSorter(filter);
            TableColumnModel tcm = jTable1.getColumnModel();
            tcm.getColumn(0).setMaxWidth(45);
            model.fireTableDataChanged();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }//GEN-LAST:event_jButtonFindActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFind;
    private javax.swing.JComboBox jComboBoxAttribute;
    private javax.swing.JComboBox jComboBoxRoles;
    private javax.swing.JFormattedTextField jFormattedTextFieldId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAttributeLike;
    private javax.swing.JTextField jTextFieldNameLike;
    // End of variables declaration//GEN-END:variables
}
