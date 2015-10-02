/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.reports;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.swing.JOptionPane;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.reports.customColumns.FormulaCustom;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.CustomColumnDetail;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * @author Benjamin frerejean
 */

/*
 * Top component which displays formula columns.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//FormulaColumn//EN", autostore = false)
@TopComponent.Description(preferredID = "FormulaColumnTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.FormulaColumnTopComponent")
@ActionReference(path = "Menu"+MenuManager.FormulaColumnTopComponentMenu , position = MenuManager.FormulaColumnTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FormulaColumnAction", preferredID = "FormulaColumnTopComponent")
@Messages({"CTL_FormulaColumnAction=Formula Column", "CTL_FormulaColumnTopComponent=Formula Column", "HINT_FormulaColumnTopComponent=This is a FormulaColumn window"})
public final class FormulaColumnTopComponent extends TopComponent {

    private ArrayList<Integer> removedElts = null;
    private IntrospectTree.IntrospectNode root = null;
    private HashMap<String, String[]> fieldMap = null;
    private ArrayList<String[]> fieldList = null;
    private final int iColName = 0;
    private final int igetter = 1;
    private final int iparam = 2;
    private final int iColType = 3;
    private final int iType = 4;
    private final int iSuffix = 5;
    private final int arrLength = 6;

    public FormulaColumnTopComponent() {
        initComponents();
        setName(Bundle.CTL_FormulaColumnTopComponent());
        setToolTipText(Bundle.HINT_FormulaColumnTopComponent());
    }

    public void initContext() {

        List<String> reportObjects = ReportUtils.getReportObjectList();
        GUIUtils.fillCombo(jComboBoxObject, reportObjects);

        List<String> arrayColumns = (List) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_FORMULA_COLUMN_LIST);
        GUIUtils.fillComboWithNullFirst(jComboBoxFormulaColumn, arrayColumns);

        List<String> categories = (List) DAOCallerAgent.callMethod(DomainValuesAccessor.class, DomainValuesAccessor.GET_DOMAIN_VALUES_BY_NAME, "customColumnCategories");
        GUIUtils.fillCombo(categoryComboBox, categories);
        categoryComboBox.setSelectedItem("Standard");

        fieldList = new ArrayList();
        jComboBoxFieldType.setSelectedItem(BigDecimal.class.getName());

    }

    public void addFieldsToMap(HashMap fieldMap, IntrospectTree.IntrospectNode node) {

        if (node.isLeaf()) {
            String[] arr = new String[arrLength];

            arr[iColName] = node.getUserObject().toString().replaceAll("get", StringUtils.EMPTY_STRING).replace('/', '.');
            arr[igetter] = node.getUserObject().toString().substring(node.getUserObject().toString().lastIndexOf(StringUtils.SPACE) + 1);
            arr[iparam] = node.getParam();
            arr[iColType] = node.getColumnType();
            arr[iType] = node.getResultType();
            arr[iSuffix] = "";
            fieldMap.put(arr[iColName], arr);
        } else {
            for (int i = 0; i < node.getChildCount(); i++) {
                addFieldsToMap(fieldMap, (IntrospectTree.IntrospectNode) node.getChildAt(i));
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

        jPanel1 = new javax.swing.JPanel();
        jComboBoxFormulaColumn = new javax.swing.JComboBox();
        jButtonLoad = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jComboBoxFieldType = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldFormula = new javax.swing.JTextField();
        jComboBoxOperators = new javax.swing.JComboBox();
        jComboBoxFields = new javax.swing.JComboBox();
        jButtonAddOperator = new javax.swing.JButton();
        jButtonAddField = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jCheckBoxMakeEditable = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxObject = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxSuffix = new javax.swing.JComboBox();
        categoryComboBox = new javax.swing.JComboBox();
        categoryLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(195, 229, 255));

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jComboBoxFormulaColumn.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFormulaColumn.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxFormulaColumn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFormulaColumnActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jComboBoxFieldType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFieldType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "java.lang.String", "java.math.BigDecimal", "java.lang.Integer", "java.lang.Boolean" }));
        jComboBoxFieldType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxFieldTypeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jLabel8.text")); // NOI18N

        jTextFieldFormula.setEditable(false);

        jComboBoxOperators.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxOperators.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "+", "-", "*", "/", "(", ")", "abs(", "exp(", "log(", "pow(", "-1" }));

        jComboBoxFields.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFields.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonAddOperator.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddOperator, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonAddOperator.text")); // NOI18N
        jButtonAddOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddOperatorActionPerformed(evt);
            }
        });

        jButtonAddField.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddField, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonAddField.text")); // NOI18N
        jButtonAddField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddFieldActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jCheckBoxMakeEditable.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxMakeEditable, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jCheckBoxMakeEditable.text")); // NOI18N
        jCheckBoxMakeEditable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxMakeEditableActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jLabel2.text")); // NOI18N

        jComboBoxObject.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxObjectActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.jLabel3.text")); // NOI18N

        jComboBoxSuffix.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxSuffix.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ReportTemplate.VALUEDATE_SUFFIX, ReportTemplate.FIRSTDATE_SUFFIX, ReportTemplate.EVOLUTION_SUFFIX }));
        jComboBoxSuffix.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxSuffixActionPerformed(evt);
            }
        });

        categoryComboBox.setBackground(new java.awt.Color(255, 255, 255));
        categoryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoryComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(categoryLabel, org.openide.util.NbBundle.getMessage(FormulaColumnTopComponent.class, "FormulaColumnTopComponent.categoryLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(255, 255, 255)
                        .addComponent(jButtonLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDelete))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextFieldFormula)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jCheckBoxMakeEditable)
                                                .addGap(8, 8, 8))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoryLabel)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxFormulaColumn, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxFields, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonAddField)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxOperators, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonAddOperator)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonRemove)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFormulaColumn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonDelete)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(categoryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFields, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddField)
                    .addComponent(jComboBoxOperators, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddOperator)
                    .addComponent(jButtonRemove)
                    .addComponent(jComboBoxSuffix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldFormula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBoxMakeEditable)
                .addContainerGap(32, Short.MAX_VALUE))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxFormulaColumnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFormulaColumnActionPerformed
        load();
    }//GEN-LAST:event_jComboBoxFormulaColumnActionPerformed

    /**
     * load
     *
     * @param evt
     */
    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        load();
    }

    public void load() {
        if (jComboBoxFormulaColumn.getSelectedItem() != null && !jComboBoxFormulaColumn.getSelectedItem().toString().isEmpty()) {
            String formulaName = jComboBoxFormulaColumn.getSelectedItem().toString();

            CustomColumn customColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, formulaName);

            if (customColumn != null && customColumn.getObjectTypeClass()!=null) {
                jComboBoxFieldType.setSelectedItem(customColumn.getType());
                jTextFieldFormula.setText(customColumn.getFormula());
                jComboBoxObject.setSelectedItem(customColumn.getObjectTypeClass().getName());
                fieldList = new ArrayList();
                categoryComboBox.setSelectedItem(customColumn.getCategory());
                for (CustomColumnDetail ccd : customColumn.getCustomColumnDetails()) {
                    String[] arr = new String[arrLength];
                    arr[iColName] = ccd.getColumnName();
                    arr[igetter] = ccd.getGetter();
                    arr[iparam] = ccd.getParam();
                    arr[iColType] = ccd.getColumnType();
                    arr[iType] = customColumn.getType();
                    arr[iSuffix] = ccd.getSuffix();
                    fieldList.add(arr);
                }
            }
            removedElts = new ArrayList();
            jComboBoxFormulaColumn.setSelectedItem(formulaName);
        } else {
            jTextFieldFormula.setText(StringUtils.EMPTY_STRING);
            fieldList = new ArrayList();
            removedElts = new ArrayList();
            categoryComboBox.setSelectedItem("Standard");
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void filterOnType(String stype, Class objectType) {

        if (objectType != null) {

            /**
             * get .fields
             */
            IntrospectTree t = new IntrospectTree(objectType);
            root = t.getClassTree(null, true);
            /**
             * custom measures
             */
            IntrospectTree.IntrospectNode ccRoot = (IntrospectTree.IntrospectNode) DAOCallerAgent.callMethodWithXMLSerialization(CustomColumnAccessor.class,
                    CustomColumnAccessor.GET_CUSTOM_COLUMNS_TREE, objectType, FormulaCustom.class.getName());

            root.add(ccRoot);
            if (objectType.equals(Trade.class) || objectType.equals(Position.class)) {
                /**
                 * pricing measures
                 */
                IntrospectTree.IntrospectNode mRoot = (IntrospectTree.IntrospectNode) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class, MeasuresAccessor.GET_MEASURES_TREE);
                root.add(mRoot);
            }

            fieldMap = new HashMap();
            addFieldsToMap(fieldMap, root);

            jComboBoxFields.removeAllItems();
            ArrayList<String> vals = new ArrayList();
            for (Object o : fieldMap.values()) {
                String[] arr = (String[]) o;
                if (arr[iType] != null && arr[iType].equals(stype)) {
                    vals.add(arr[iColName]);
                }
            }
            Collections.sort(vals);
            for (String s : vals) {
                jComboBoxFields.addItem(s);
            }
            jComboBoxOperators.removeAllItems();
            String[] arr = null;
            if (stype.equals(String.class.getName())) {
                arr = new String[]{StringUtils.COMMA, ")", "charAt(", "concat(", "endsWith(", "indexOf(", "lastIndexOf(", "length(", "trim(", "toUpperCase(", "toLowerCase("};
            } else if (stype.equals(BigDecimal.class.getName()) || stype.equals(Integer.class.getName())) {
                arr = new String[]{StringUtils.COMMA, "+", "-", "*", "/", "(", ")", "abs(", "exp(", "log(", "pow(", "%", "max(", "min(", "sqrt(", "-1", "0"};        //
            } else if (stype.equals(Boolean.class.getName())) {
                arr = new String[]{StringUtils.COMMA, "=", "!=", "&&", "||", "<", ">", "<=", ">=", "!", ")", "eval(", "equals(", "endsWith(", "like("};
            }
            for (String arr1 : arr) {
                jComboBoxOperators.addItem(arr1);
            }
        }
    }

    /**
     * save
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        String name = StringUtils.EMPTY_STRING;
        if (jComboBoxFormulaColumn.getSelectedItem() != null) {
            name = jComboBoxFormulaColumn.getSelectedItem().toString();
        }

        name = (String) JOptionPane.showInputDialog(this, "Label of the Column", "Column", JOptionPane.PLAIN_MESSAGE, null, null, name);
        if (name != null && !name.isEmpty() && !jTextFieldFormula.getText().isEmpty()) {
            CustomColumn customColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, name);

            if (customColumn == null) {
                customColumn = new CustomColumn();
                jComboBoxFormulaColumn.addItem(name);
                jComboBoxFormulaColumn.setSelectedItem(name);
            } else if (!customColumn.getClassName().equalsIgnoreCase(FormulaCustom.class.getName())) {
                JOptionPane.showMessageDialog(this, "Name already used. Please find another one");
                return;
            }
            customColumn.setObjectTypeClass(ReportUtils.getObjectType(GUIUtils.getComponentStringValue(jComboBoxObject)));
            String formula = jTextFieldFormula.getText();
            if (formula.contains("{" + name + "}")) {
                JOptionPane.showMessageDialog(this, "Reccursive formula are forbidden.");
                return;
            }
            customColumn.setCustomColumnDetails(new ArrayList());
            customColumn.setName(name);
            customColumn.setType(GUIUtils.getComponentStringValue(jComboBoxFieldType));
            customColumn.setFormula(formula);
            customColumn.setClassName(FormulaCustom.class.getName());
            customColumn.setCategory(GUIUtils.getComponentStringValue(categoryComboBox));
            DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.STORE_CUSTOM_COLUMN, customColumn);

            if (removedElts != null) {
                for (Integer id : removedElts) {
                    CustomColumnDetail selectorColumnDetail = (CustomColumnDetail) DAOCallerAgent.callMethod(CustomColumnAccessor.class,
                        CustomColumnAccessor.GET_CUSTOM_COLUMN_DETAIL_BY_ID, id);
                    if (selectorColumnDetail != null) {
                        DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.DELETE_CUSTOM_COLUMN_DETAIL,
                                name, selectorColumnDetail.getColumnName(), selectorColumnDetail.getSuffix());
                        for (CustomColumnDetail customColumnDetail : customColumn.getCustomColumnDetails()) {
                            if (customColumnDetail.equals(selectorColumnDetail)) {
                                customColumn.getCustomColumnDetails().remove(customColumnDetail);
                            }
                        }
                    }
                }
            }

            for (String[] arr : fieldList) {
                if (formula.contains(arr[iColName])) {

                    CustomColumnDetail customColumnDetail = (CustomColumnDetail) DAOCallerAgent.callMethod(CustomColumnAccessor.class,
                            CustomColumnAccessor.GET_CUSTOM_COLUMN_DETAIL_BY_NAMES, name, arr[iColName],arr[iSuffix]);

                    if (customColumnDetail == null) {
                        customColumnDetail = new CustomColumnDetail();
                    }
                    customColumnDetail.setCustomColumn(customColumn);
                    customColumnDetail.setColumnName(arr[iColName]);
                    customColumnDetail.setGetter(arr[igetter]);
                    customColumnDetail.setParam(arr[iparam]);
                    customColumnDetail.setColumnType(arr[iColType]);
                    customColumnDetail.setSuffix(arr[iSuffix]);
                    DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.STORE_CUSTOM_COLUMN_DETAIL, customColumnDetail);
                }
            }

            if (!name.isEmpty()) {
                jComboBoxFormulaColumn.setSelectedItem(name);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid name and formula");
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxFieldTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxFieldTypeActionPerformed
        if (!jComboBoxFieldType.getSelectedItem().toString().isEmpty() && !jComboBoxObject.getSelectedItem().toString().isEmpty()) {
            filterOnType(jComboBoxFieldType.getSelectedItem().toString(), ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString()));
        }
    }//GEN-LAST:event_jComboBoxFieldTypeActionPerformed

    private void jButtonAddOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddOperatorActionPerformed
        String formula = jTextFieldFormula.getText();
        String operator = jComboBoxOperators.getSelectedItem().toString();
        if (!formula.endsWith("}")
                && !(formula.endsWith(")") || formula.endsWith("(")
                || operator.contains("(") || operator.contains(")"))) {
            JOptionPane.showMessageDialog(this, "incompatible operator");
        } else {
            formula = formula + StringUtils.SPACE + operator;
            jTextFieldFormula.setText(formula);
        }
    }//GEN-LAST:event_jButtonAddOperatorActionPerformed

    private void jButtonAddFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddFieldActionPerformed

        String formula = jTextFieldFormula.getText();
        if (formula.endsWith("}")) {
            JOptionPane.showMessageDialog(this, "Missing operator");
        } else {
            String field = jComboBoxFields.getSelectedItem().toString();
            String suffix = jComboBoxSuffix.getSelectedItem().toString();
            formula = formula + " #{" + field + suffix + "}";
            jTextFieldFormula.setText(formula);
            String[] arr = Arrays.copyOf(fieldMap.get(field),arrLength);
            arr[iSuffix] = suffix;
            fieldList.add(arr);
        }
    }//GEN-LAST:event_jButtonAddFieldActionPerformed

    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        String formula = jTextFieldFormula.getText();
        int i =formula.lastIndexOf(StringUtils.SPACE);
        if (formula.endsWith("}")){
           i = formula.lastIndexOf("#{")-1;
        }
        if (!formula.isEmpty() && jComboBoxFormulaColumn.getSelectedItem()!=null) {

            String colName=jComboBoxFormulaColumn.getSelectedItem().toString();
            if (i>0&&formula.substring(i).contains("#{") && fieldList.size() > 0) {
                CustomColumnDetail selectorColumnDetail = (CustomColumnDetail) DAOCallerAgent.callMethod(CustomColumnAccessor.class,
                        CustomColumnAccessor.GET_CUSTOM_COLUMN_DETAIL_BY_NAMES,colName, fieldList.get(fieldList.size() - 1)[iColName],fieldList.get(fieldList.size() - 1)[iSuffix]);
                if (selectorColumnDetail!=null){
                    removedElts.add(selectorColumnDetail.getCustomColumnDetailId());
                }
                fieldList.remove(fieldList.size() - 1);
            }
        }
        if (i==-1){
            formula=StringUtils.EMPTY_STRING;
        }
        else {
            formula = formula.substring(0, i);
        }
        jTextFieldFormula.setText(formula);
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        if (jComboBoxFormulaColumn.getSelectedItem() != null) {
            CustomColumn customColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, jComboBoxFormulaColumn.getSelectedItem().toString());
            if (customColumn != null) {
                Boolean used = (Boolean) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.IS_A_CUSTOM_COLUMN_USED_IN_TEMPLATES, customColumn.getName());
                if (!used) {
                    DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.DELETE_CUSTOM_COLUMN, customColumn);
                    jComboBoxFormulaColumn.removeItemAt(jComboBoxFormulaColumn.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(this, "This column is present in report views", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
    }//GEN-LAST:event_jButtonDeleteActionPerformed
    }
    private void jCheckBoxMakeEditableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxMakeEditableActionPerformed

        if (jCheckBoxMakeEditable.isSelected()) {
            jTextFieldFormula.setEditable(true);
        } else {
            jTextFieldFormula.setEditable(false);
        }
    }//GEN-LAST:event_jCheckBoxMakeEditableActionPerformed

    private void jComboBoxObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxObjectActionPerformed
        if (jComboBoxFieldType.getSelectedItem() != null && jComboBoxObject.getSelectedItem() != null) {
            filterOnType(jComboBoxFieldType.getSelectedItem().toString(), ReportUtils.getObjectType(jComboBoxObject.getSelectedItem().toString()));

            List<String> arrayColumns = (List) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_FORMULA_COLUMN_LIST_BY_OBJECT_TYPE, ReportUtils.getObjectType(GUIUtils.getComponentStringValue(jComboBoxObject)));
            GUIUtils.fillComboWithNullFirst(jComboBoxFormulaColumn, arrayColumns);

            jTextFieldFormula.setText(StringUtils.EMPTY_STRING);
        }

    }//GEN-LAST:event_jComboBoxObjectActionPerformed

    private void jComboBoxSuffixActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxSuffixActionPerformed
    }//GEN-LAST:event_jComboBoxSuffixActionPerformed

    private void categoryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoryComboBoxActionPerformed
    }//GEN-LAST:event_categoryComboBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox categoryComboBox;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JButton jButtonAddField;
    private javax.swing.JButton jButtonAddOperator;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxMakeEditable;
    private javax.swing.JComboBox jComboBoxFieldType;
    private javax.swing.JComboBox jComboBoxFields;
    private javax.swing.JComboBox jComboBoxFormulaColumn;
    private javax.swing.JComboBox jComboBoxObject;
    private javax.swing.JComboBox jComboBoxOperators;
    private javax.swing.JComboBox jComboBoxSuffix;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldFormula;
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
