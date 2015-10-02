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
 */package org.gaia.gui.admin;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.utils.ImportMapAccessor;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.referentials.BusinessCenter;
import org.gaia.domain.utils.ImportMap;
import org.gaia.domain.utils.ImportMapField;
import org.gaia.gui.common.FieldValueException;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays import mapper.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.admin//ImportMapper//EN", autostore = false)
@TopComponent.Description(preferredID = "ImportMapperTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.admin.ImportMapperTopComponent")
@ActionReference(path = "Menu/File" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ImportMapperAction", preferredID = "ImportMapperTopComponent")
@Messages({"CTL_ImportMapperAction=Import Mapper", "CTL_ImportMapperTopComponent=Import Mapper Window", "HINT_ImportMapperTopComponent=This is a Import Mapper window"})
public final class ImportMapperTopComponent extends TopComponent {

    private ArrayList<Integer> removedElts = null;
    private ImportMap map;
    private static final Logger logger = Logger.getLogger(ImportMapperTopComponent.class);

    public ImportMapperTopComponent() {
        initComponents();
        setName(Bundle.CTL_ImportMapperTopComponent());
        setToolTipText(Bundle.HINT_ImportMapperTopComponent());

        jTable1.getTableHeader().setReorderingAllowed(false);

        /**
         * add imported Map
         */
        List<String> importMaps;
        try {
            importMaps = (List<String>) DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.GET_IMPORT_MAPS);
            GUIUtils.fillCombo(jComboBoxMapList, importMaps);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        jComboBoxObjectType.addItem(BusinessCenter.class.getName());

        displayTree();

        TableColumnModel m = jTable1.getColumnModel();
        m.removeColumn(m.getColumn(7));
        m.removeColumn(m.getColumn(6));
        m.removeColumn(m.getColumn(5));
        m.removeColumn(m.getColumn(4));
        m.removeColumn(m.getColumn(3));
        jTable1.setColumnModel(m);
    }

    /**
     * Add row Data
     */
    public void addLine() {
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        tm.addRow(new String[]{"", "", "", "", "", "", "", "", ""});
        jTable1.setModel(tm);

        jTable1.setRowSelectionInterval(jTable1.getRowCount() - 1, jTable1.getRowCount() - 1);
        jTextFieldSourceField.setText("");
        jTextFieldConst.setText("");
        jTextFieldCol.setText("");
        jLabelGetter.setText("");
        jLabelParam.setText("");
        jLabelReturnType.setText("");
        jLabelColType.setText("");
        jLabelRow.setText(Integer.toString(jTable1.getRowCount() - 1));
    }

    /**
     * refresh Values
     */
    public void refreshValues() {
        jTextFieldCol.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 0));
        jTextFieldConst.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 1));
        jTextFieldSourceField.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 2));
        jLabelGetter.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 3));
        jLabelParam.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 4));
        jLabelReturnType.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 5));
        jLabelColType.setText(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 6));
        jLabelRow.setText(Integer.toString(jTable1.getSelectedRow()));

    }

    /**
     * save map
     */
    public void save() {
        String name = "";
        if (jComboBoxMapList.getSelectedItem() != null) {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Map", "Import Map", JOptionPane.PLAIN_MESSAGE, null, null, jComboBoxMapList.getSelectedItem().toString());
        } else {
            name = (String) JOptionPane.showInputDialog(this, "Label of the Map", "Import Map", JOptionPane.PLAIN_MESSAGE, null, null, "");
        }

        if (map == null) {
            map = new ImportMap();
            map.setImportMapFieldCollection(new ArrayList());
            if (name != null) {
                jComboBoxMapList.addItem(name);
            }
        }
        if (name != null) {
            map.setName(name);
        }
        map.setFilePath(jTextFieldFilePath.getText());
        map.setFileType(jComboBoxFormat.getSelectedItem().toString());
        map.setObjectType(jComboBoxObjectType.getSelectedItem().toString());
        map.setObjectPath(jTextFieldObjectPath.getText());
        try {
            DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.STORE_IMPORT_MAP, map);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        for (int i = 0; i < tm.getRowCount(); i++) {
            if (!GUIUtils.getTableValueAt(jTable1, i, 0).isEmpty()) {
                ImportMapField importMapField = null;
                try {
                    Integer id = Integer.parseInt(GUIUtils.getTableValueAt(jTable1, i, 7));
                    importMapField = (ImportMapField) DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.GET_IMPORT_MAP_FIELD, id);
                } catch (Exception e) {
                }
                if (importMapField == null) {
                    importMapField = new ImportMapField();
                }
                importMapField.setColumnName(GUIUtils.getTableValueAt(jTable1, i, 0));
                importMapField.setConstant(GUIUtils.getTableValueAt(jTable1, i, 1));
                importMapField.setSourceField(GUIUtils.getTableValueAt(jTable1, i, 2));
                importMapField.setGetter(GUIUtils.getTableValueAt(jTable1, i, 3));
                importMapField.setParam(GUIUtils.getTableValueAt(jTable1, i, 4));
                importMapField.setReturnType(GUIUtils.getTableValueAt(jTable1, i, 5));
                importMapField.setColumnType(GUIUtils.getTableValueAt(jTable1, i, 6));
                importMapField.setImportMap(map);
                map.getImportMapFieldCollection().add(importMapField);
                try {
                    DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.STORE_IMPORT_MAP_FIELD, importMapField);
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
        try {
            DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.STORE_IMPORT_MAP, map);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        if (removedElts != null) {
            for (Integer id : removedElts) {
                try {
                    ImportMapField importMapField = (ImportMapField) DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.GET_IMPORT_MAP_FIELD, id);
                    if (importMapField != null) {
                        DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.DELETE_IMPORT_MAP_FIELD);
                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                    Exceptions.printStackTrace(ex);
                }
            }
        }

        if (name != null && !name.isEmpty()) {
            jComboBoxMapList.setSelectedItem(name);
        }
    }

    public void load() {
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        jLabelRow.setText("");
        while (tm.getRowCount() > 0) {
            tm.removeRow(0);
        }
        if (jComboBoxMapList.getSelectedItem() != null) {
            try {
                map = (ImportMap) DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.GET_IMPORT_MAP, jComboBoxMapList.getSelectedItem().toString());

                if (map != null) {
                    jComboBoxObjectType.setSelectedItem(map.getObjectType());
                    jComboBoxFormat.setSelectedItem(map.getFileType());
                    jTextFieldFilePath.setText(map.getFilePath());
                    jTextFieldObjectPath.setText(map.getObjectPath());
                    if (map.getImportMapFieldCollection() != null) {
                        for (ImportMapField mapField : map.getImportMapFieldCollection()) {
                            Object[] row = new Object[8];
                            row[0] = mapField.getColumnName();
                            row[1] = mapField.getConstant();
                            row[2] = mapField.getSourceField();
                            row[3] = mapField.getGetter();
                            row[4] = mapField.getParam();
                            row[5] = mapField.getReturnType();
                            row[6] = mapField.getColumnType();
                            row[7] = mapField.getImportMapField();

                            tm.addRow(row);
                        }
                    }
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }

        jTable1.setModel(tm);
        removedElts = new ArrayList();
    }

    /**
     * refresh Data
     */
    private void refereshData(java.awt.event.ComponentEvent evt) {
        DefaultMutableTreeNode node;
        node = (DefaultMutableTreeNode) ((javax.swing.JTree) evt.getComponent()).getLastSelectedPathComponent();
        if (node != null && node.isLeaf()) {
            IntrospectTree.IntrospectNode n = (IntrospectTree.IntrospectNode) node;

            jTextFieldCol.setText(node.getUserObject().toString().replaceAll("get", "").replace('/', '.'));
            jLabelGetter.setText(node.getUserObject().toString().substring(node.getUserObject().toString().lastIndexOf(StringUtils.SPACE) + 1));
            jLabelParam.setText(n.getParam());
            jLabelReturnType.setText(n.getResultType());
            jLabelColType.setText(n.getColumnType());
            jTextFieldConst.setText("");
            jTextFieldConst.setText("");

        }
    }

    /**
     * display Tree and add node
     */
    private void displayTree() {

        if (!jComboBoxObjectType.getSelectedItem().toString().isEmpty()) {
            IntrospectTree tree = new IntrospectTree((Class) jComboBoxObjectType.getSelectedItem());
            IntrospectTree.IntrospectNode root = tree.getClassTree(null, true);
            jTree1.setModel(new DefaultTreeModel(root));
            jTree1.expandPath(new TreePath(root.getPath()));
            jTree1.repaint();
        } else {
            jTree1.setModel(new DefaultTreeModel(null));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBoxMapList = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jLabel2 = new javax.swing.JLabel();
        jTextFieldConst = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldCol = new javax.swing.JTextField();
        jLabelRow = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabelGetter = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        jLabelParam = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldSourceField = new javax.swing.JTextField();
        jLabelReturnType = new javax.swing.JLabel();
        jLabelColType = new javax.swing.JLabel();
        jComboBoxObjectType = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxFormat = new javax.swing.JComboBox();
        jTextFieldFilePath = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldObjectPath = new javax.swing.JTextField();
        jButtonImport = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jComboBoxMapList.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxMapList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxMapListActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Column","Const","Source","Getter","Param","Type","Column type","Id"
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

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel2.text")); // NOI18N

        jTextFieldConst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldConstActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel3.text")); // NOI18N

        jTextFieldCol.setEditable(false);
        jTextFieldCol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldColActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton8, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButton8.text")); // NOI18N
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTree1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel1.text")); // NOI18N

        jTextFieldSourceField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSourceFieldActionPerformed(evt);
            }
        });

        jComboBoxObjectType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"" }));
        jComboBoxObjectType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxObjectTypeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel5.text")); // NOI18N

        jComboBoxFormat.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "XML", "CSV"}));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonImport, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jButtonImport.text")); // NOI18N
        jButtonImport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonImportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(ImportMapperTopComponent.class, "ImportMapperTopComponent.jLabel8.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCol, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jTextFieldSourceField)
                                    .addComponent(jTextFieldConst, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelColType, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelParam, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelRow, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelGetter, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelReturnType, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 127, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxMapList, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLoad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonSave))
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFilePath)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonImport))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jComboBoxObjectType, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxFormat, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldObjectPath)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxObjectType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxFormat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jTextFieldObjectPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonImport)
                    .addComponent(jComboBoxMapList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad)
                    .addComponent(jButtonSave)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldCol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldConst, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldSourceField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelGetter, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelRow, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelParam, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelColType, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelReturnType, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxMapListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxMapListActionPerformed

        load();
    }//GEN-LAST:event_jComboBoxMapListActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        refreshValues();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTable1FocusGained
    }//GEN-LAST:event_jTable1FocusGained

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        /**
         * select in table
         */
        refreshValues();
    }//GEN-LAST:event_jTable1KeyPressed

    private void jTextFieldConstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldConstActionPerformed
    }//GEN-LAST:event_jTextFieldConstActionPerformed

    private void jTextFieldColActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldColActionPerformed

        jTextFieldConst.setText("");
        jTextFieldSourceField.setText("");
        jLabelGetter.setText("");
        jLabelParam.setText("");
        jLabelColType.setText("");
        jLabelReturnType.setText("");
    }//GEN-LAST:event_jTextFieldColActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        /**
         * copy into table
         */
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        int row = 0;
        if (jLabelRow.getText().isEmpty()) {
            tm.addRow(new String[]{"", "", "", "", "", "", "", "", ""});
            row = tm.getRowCount() - 1;

        } else {
            row = Integer.parseInt(jLabelRow.getText());
        }
        try {
            if (!jTextFieldConst.getText().isEmpty() || !jTextFieldSourceField.getText().isEmpty()) {

                if (jLabelReturnType.getText().equals(Date.class.getName())) {
                    DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
                    if (!jTextFieldConst.getText().isEmpty()) {
                        boolean validInt = true;
                        boolean validDate = true;
                        try {
                            Integer.parseInt(jTextFieldConst.getText());
                        } catch (Exception e) {
                            validInt = false;
                        }
                        try {
                            df.parse(jTextFieldConst.getText());
                        } catch (Exception e) {
                            validDate = false;
                        }
                        if (validDate == false && validInt == false) {
                            new ErrorMessageUI("The format is not valid\nPlease retry.").setVisible(true);
                            throw (new FieldValueException("The format is not valid\nPlease retry."));
                        }
                    }

                    if (!jTextFieldSourceField.getText().isEmpty()) {
                        boolean validInt = true;
                        boolean validDate = true;
                        try {
                            Integer.parseInt(jTextFieldSourceField.getText());
                        } catch (Exception e) {
                            validInt = false;
                        }
                        try {
                            df.parse(jTextFieldSourceField.getText());
                        } catch (Exception e) {
                            validDate = false;
                        }
                        if (validDate == false && validInt == false) {
                            new ErrorMessageUI("The format is not valid\nPlease retry.").setVisible(true);
                            throw (new FieldValueException("The format is not valid\nPlease retry."));
                        }
                    }
                } else if (jLabelReturnType.getText().equals(BigDecimal.class.getName())) {
                    boolean validInt = true;
                    try {
                        BigDecimal.valueOf(Double.valueOf(jTextFieldConst.getText()));
                    } catch (Exception e) {
                        validInt = false;
                    }
                    if (validInt == false) {
                        new ErrorMessageUI("The format is not valid\nPlease retry.").setVisible(true);
                        throw (new FieldValueException("The format is not valid\nPlease retry."));
                    }
                }
                tm.setValueAt(jTextFieldCol.getText(), row, 0);
                tm.setValueAt(jTextFieldConst.getText(), row, 1);
                tm.setValueAt(jTextFieldSourceField.getText(), row, 2);
                tm.setValueAt(jLabelGetter.getText(), row, 3);
                tm.setValueAt(jLabelParam.getText(), row, 4);
                tm.setValueAt(jLabelReturnType.getText(), row, 5);
                tm.setValueAt(jLabelColType.getText(), row, 6);

            }
            jTable1.setModel(tm);
        } catch (Exception e) {
            tm.removeRow(row);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /**
         * add line
         */
        addLine();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        /**
         * load
         */
        load();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        /**
         * save
         */
        save();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        /**
         * remove
         */
        if (removedElts == null) {
            removedElts = new ArrayList();
        }
        DefaultTableModel tm = (DefaultTableModel) jTable1.getModel();
        if (tm.getValueAt(jTable1.getSelectedRow(), 7) != null) {
            try {
                Integer id = Integer.parseInt(GUIUtils.getTableValueAt(jTable1, jTable1.getSelectedRow(), 7));
                removedElts.add(id);
            } catch (Exception e) {
            }
        }
        tm.removeRow(jTable1.getSelectedRow());
        jLabelRow.setText("");
        jTable1.setModel(tm);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTree1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MouseClicked

        refereshData(evt);
    }//GEN-LAST:event_jTree1MouseClicked

    private void jTextFieldSourceFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSourceFieldActionPerformed
    }//GEN-LAST:event_jTextFieldSourceFieldActionPerformed

    private void jComboBoxObjectTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxObjectTypeActionPerformed
        displayTree();
    }//GEN-LAST:event_jComboBoxObjectTypeActionPerformed

    private void jButtonImportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonImportActionPerformed
        /**
         * Import
         */
        if (jComboBoxMapList.getSelectedItem() != null && !jComboBoxMapList.getSelectedItem().toString().isEmpty()) {
            try {
                DAOCallerAgent.callMethod(ImportMapAccessor.class, ImportMapAccessor.IMPORT_FILE, jComboBoxMapList.getSelectedItem().toString());
                JOptionPane.showMessageDialog(this, "Done.");
            } catch (Exception e) {
                new ErrorMessageUI("parsing error : " + e.getMessage()).setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonImportActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButtonImport;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxFormat;
    private javax.swing.JComboBox jComboBoxMapList;
    private javax.swing.JComboBox jComboBoxObjectType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelColType;
    private javax.swing.JLabel jLabelGetter;
    private javax.swing.JLabel jLabelParam;
    private javax.swing.JLabel jLabelReturnType;
    private javax.swing.JLabel jLabelRow;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCol;
    private javax.swing.JTextField jTextFieldConst;
    private javax.swing.JTextField jTextFieldFilePath;
    private javax.swing.JTextField jTextFieldObjectPath;
    private javax.swing.JTextField jTextFieldSourceField;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
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
