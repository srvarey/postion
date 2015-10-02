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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductReferenceTypeAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.ProductReferenceType;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class AssetFinder extends javax.swing.JPanel {

    private TableRowSorter filter;
    private List<String> typescoll = null;
    private static final Logger logger = Logger.getLogger(AssetFinder.class);
    private DefaultTableModel model;

    /**
     * Creates new form AssetFinder
     *
     * @param types
     */
    public AssetFinder(List<ProductTypeUtil.ProductType> types) {
        initComponents();
        jButtonFind.requestFocus();
        jTableProducts.getTableHeader().setReorderingAllowed(false);

        Collections.sort(types);

        typescoll = new ArrayList();
        if (types != null) {
            for (Object o : types) {
                if (o != null) {
                    String s = ((ProductTypeUtil.ProductType) o).name;
                    jComboBoxType.addItem(s);
                    typescoll.add(s);
                }
            }
        }

        List<ProductReferenceType> referenceTypes = null;
        try {
            referenceTypes = (List) DAOCallerAgent.callMethod(ProductReferenceTypeAccessor.class, ProductReferenceTypeAccessor.GET_ALL_PRODUCT_REFERENCE_TYPES);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        if (referenceTypes != null) {
            for (ProductReferenceType type : referenceTypes) {
                jComboBoxProductRefTypes.addItem(type.getReferenceType());
            }
        }

        jFormattedTextFieldId.setHorizontalAlignment(JFormattedTextField.RIGHT);
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

        jTableProducts.setAutoCreateRowSorter(true);
        TableColumnModel tcm = jTableProducts.getColumnModel();
        tcm.getColumn(0).setMaxWidth(45);
        ActionListener listener = new EnterListener(jTextFieldNameLike);

    }

    /**
     * Update the row filter regular expression from the expression in the text
     * box.
     */
    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rowFilter;
        // If current expression doesn't parse, don't update.
        try {
            rowFilter = RowFilter.regexFilter("(?i)" + jTextFieldNameLike.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        if (filter != null) {
            filter.setRowFilter(rowFilter);
        }
    }

    /**
     * return getAssetId
     */
    public Integer getAssetId() {
        Integer id = null;
        int r = jTableProducts.getSelectedRow();
        if (r > -1) {

            String s = GUIUtils.getTableValueAt(jTableProducts, r, 0);
            id = Integer.parseInt(s);
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
        jTableProducts = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jComboBoxProductRefTypes = new javax.swing.JComboBox();
        jTextFieldCodeLike = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jFormattedTextFieldId = new javax.swing.JFormattedTextField(new DecimalFormat("########"));
        jTextFieldNameLike = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTableProducts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Name", "Code", "Type"
            }
        ));
        jTableProducts.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableProducts);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AssetFinder.class, "AssetFinder.jLabel1.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxType.setName("jComboBoxType"); // NOI18N

        jComboBoxProductRefTypes.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxProductRefTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxProductRefTypes.setName("jComboBoxProductRefTypes"); // NOI18N

        jTextFieldCodeLike.setName("jTextFieldCodeLike"); // NOI18N

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(AssetFinder.class, "AssetFinder.jButtonFind.text")); // NOI18N
        jButtonFind.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonFind.setNextFocusableComponent(jButtonFind);
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        jFormattedTextFieldId.setName("jFormattedTextFieldId"); // NOI18N
        jFormattedTextFieldId.setOpaque(true);

        jTextFieldNameLike.setName("jTextFieldNameLike"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AssetFinder.class, "AssetFinder.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AssetFinder.class, "AssetFinder.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AssetFinder.class, "AssetFinder.jLabel2.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxProductRefTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldNameLike, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldCodeLike)
                            .addComponent(jButtonFind, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxProductRefTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodeLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
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
             * looking product
             */
            String name = jTextFieldNameLike.getText();
            String type = jComboBoxType.getSelectedItem().toString();
            String productRefType = jComboBoxProductRefTypes.getSelectedItem().toString();
            List<String> reftypes = new ArrayList();
            String productRefVal = jTextFieldCodeLike.getText();
            String productIds = jFormattedTextFieldId.getText();
            if (!type.equals("")) {
                reftypes.add(type);
            } else {
                reftypes = typescoll;
            }

            model = (DefaultTableModel) jTableProducts.getModel();
            while (model.getRowCount() > 0) {
                model.removeRow(0);
            }

            String typeList = "";
            if (reftypes != null && reftypes.size() > 0) {
                for (String refType : reftypes) {
                    typeList = typeList + StringUtils.QUOTE + refType + "',";
                }
                typeList = typeList.substring(0, typeList.length() - 1);
            }
            List<Object[]> products = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCTS_WITH_FINDER, productIds, typeList, name, productRefType, productRefVal);

            for (Object[] objectArray : products) {
                model.addRow(objectArray);
            }
            filter = new TableRowSorter<>(model);
            jTableProducts.setModel(model);
            jTableProducts.setRowSorter(filter);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }//GEN-LAST:event_jButtonFindActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFind;
    private javax.swing.JComboBox jComboBoxProductRefTypes;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JFormattedTextField jFormattedTextFieldId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableProducts;
    private javax.swing.JTextField jTextFieldCodeLike;
    private javax.swing.JTextField jTextFieldNameLike;
    // End of variables declaration//GEN-END:variables
}
