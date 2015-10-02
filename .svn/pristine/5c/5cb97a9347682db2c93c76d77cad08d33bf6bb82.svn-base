/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, jComboBoxIssuer but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.assets;

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaProductTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays credit entities .
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//CDSIndex//EN", autostore = false)
@TopComponent.Description(preferredID = "CDSIndexTopComponent",iconBase="org/gaia/gui/assets/Index.png", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.CDSIndexTopComponent")
@ActionReference(path = "Menu"+MenuManager.CDSIndexTopComponentMenu, position = MenuManager.CDSIndexTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CDSIndexAction")
@Messages({"CTL_CDSIndexAction=CDS Index", "CTL_CDSIndexTopComponent=CDS Index", "HINT_CDSIndexTopComponent=This is a CDS Index window"})
public class CDSIndexTopComponent extends GaiaProductTopComponent {

    public AssetFinder assetFinder = null;
    private ProductReferencesPanel productReferencePanel = null;
    public List<ProductReference> prodrefs = null;
    public ArrayList<ProductTypeUtil.ProductType> listTypes = null;
    public HashSet<ProductUnderlying> underlyings = null;
    public DefaultTableModel model;
    private static final Logger logger = Logger.getLogger(CDSIndexTopComponent.class);
    public static final DecimalFormat rateDecimalFormat = new DecimalFormat("#,##0.000", decimalFormatSymbol);

    /**
     * class event on the table option table
     */
    public CDSIndexTopComponent() {
        initComponents();
        setName(Bundle.CTL_CDSIndexTopComponent());
        setToolTipText(Bundle.HINT_CDSIndexTopComponent());
        MyCellEditorListener listener = new MyCellEditorListener();
        indexTable.getDefaultEditor(Object.class).addCellEditorListener(listener);

    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        shortNameLabel = new javax.swing.JLabel();
        jTextFieldShortName = new javax.swing.JTextField();
        typeLabel = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        currencyLabel = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        productReferencesLabel = new javax.swing.JLabel();
        jButtonShowreferences = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaComment = new javax.swing.JTextArea();
        commentLabel = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        issueDateLabel = new javax.swing.JLabel();
        jButtonSaveAsNew = new javax.swing.JButton();
        jDateChooserIssueDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldId = new javax.swing.JTextField();
        indexScrollPane = new javax.swing.JScrollPane();
        indexTable = new org.jdesktop.swingx.JXTable();
        addIndexButton = new org.jdesktop.swingx.JXButton();
        removeIndexButton = new org.jdesktop.swingx.JXButton();
        calculateButton = new org.jdesktop.swingx.JXButton();
        jPanel2 = new javax.swing.JPanel();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        settlementDelayLabel = new javax.swing.JLabel();
        jButtonSchedule = new javax.swing.JButton();
        recoveryRateLabel = new org.jdesktop.swingx.JXLabel();
        statusLabel = new javax.swing.JLabel();
        jFormattedTextFieldSettlementdelay = new javax.swing.JFormattedTextField(new DecimalFormat("#"));
        recoveryRateTextField = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxTenor = new javax.swing.JComboBox();
        versionTextField = new javax.swing.JTextField();
        versionLabel = new javax.swing.JLabel();
        serieLabel = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        serieTextField = new javax.swing.JTextField();
        tenorLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        bpLabel = new javax.swing.JLabel();
        contractTypeComboBox = new javax.swing.JComboBox();
        seniorityLabel = new javax.swing.JLabel();
        creditEntityField = new javax.swing.JTextField();
        maturityLabel = new javax.swing.JLabel();
        couponLabel = new javax.swing.JLabel();
        contractLabel = new javax.swing.JLabel();
        jComboBoxMaturity = new javax.swing.JComboBox();
        findButton = new javax.swing.JButton();
        jFormattedTextFieldCoupon = new javax.swing.JFormattedTextField(decimalFormat);
        creditEntityLabel = new javax.swing.JLabel();
        showEntityButton = new javax.swing.JButton();
        jComboBoxSeniority = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(746, 900));

        org.openide.awt.Mnemonics.setLocalizedText(shortNameLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.shortNameLabel.text_1")); // NOI18N

        jTextFieldShortName.setName("jTextFieldShortName"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(typeLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.typeLabel.text_1")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(currencyLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.currencyLabel.text_1")); // NOI18N

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(productReferencesLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.productReferencesLabel.text_1")); // NOI18N

        jButtonShowreferences.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowreferences, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonShowreferences.text_1")); // NOI18N
        jButtonShowreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowreferencesActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonSave.text_1")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextAreaComment.setColumns(20);
        jTextAreaComment.setRows(5);
        jScrollPane1.setViewportView(jTextAreaComment);

        org.openide.awt.Mnemonics.setLocalizedText(commentLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.commentLabel.text_1")); // NOI18N

        jButtonClose.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonClose.text_1")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonLoad.text_1")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonNew.text_1")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(issueDateLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.issueDateLabel.text_1")); // NOI18N

        jButtonSaveAsNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonSaveAsNew.text_1")); // NOI18N
        jButtonSaveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNewActionPerformed(evt);
            }
        });

        jDateChooserIssueDate.setBackground(new java.awt.Color(254, 252, 254));

        jTextFieldId.setEditable(false);
        jTextFieldId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldId.setText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jTextFieldId.text_1")); // NOI18N
        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        indexTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Credit Entity", "Weight", "Recovery Rate"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        indexTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        indexTable.getTableHeader().setReorderingAllowed(false);
        indexScrollPane.setViewportView(indexTable);
        if (indexTable.getColumnModel().getColumnCount() > 0) {
            indexTable.getColumnModel().getColumn(0).setHeaderValue(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.indexTable.columnModel.title0")); // NOI18N
            indexTable.getColumnModel().getColumn(1).setHeaderValue(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.indexTable.columnModel.title1")); // NOI18N
            indexTable.getColumnModel().getColumn(2).setHeaderValue(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.indexTable.columnModel.title2")); // NOI18N
            indexTable.getColumnModel().getColumn(3).setHeaderValue(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.indexTable.columnModel.title3")); // NOI18N
        }

        addIndexButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(addIndexButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.addIndexButton.text")); // NOI18N
        addIndexButton.setToolTipText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.addIndexButton.toolTipText")); // NOI18N
        addIndexButton.setMaximumSize(new java.awt.Dimension(30, 20));
        addIndexButton.setMinimumSize(new java.awt.Dimension(30, 20));
        addIndexButton.setPreferredSize(new java.awt.Dimension(30, 20));
        addIndexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addIndexButtonActionPerformed(evt);
            }
        });

        removeIndexButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(removeIndexButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.removeIndexButton.text")); // NOI18N
        removeIndexButton.setToolTipText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.removeIndexButton.toolTipText")); // NOI18N
        removeIndexButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        removeIndexButton.setMaximumSize(new java.awt.Dimension(30, 20));
        removeIndexButton.setMinimumSize(new java.awt.Dimension(30, 20));
        removeIndexButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeIndexButtonActionPerformed(evt);
            }
        });

        calculateButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(calculateButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.calculateButton.text")); // NOI18N
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        loadEventXButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.loadEventXButton.text_1")); // NOI18N
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(settlementDelayLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.settlementDelayLabel.text_1")); // NOI18N

        jButtonSchedule.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSchedule, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.jButtonSchedule.text_1")); // NOI18N
        jButtonSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(recoveryRateLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.recoveryRateLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(statusLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.statusLabel.text_1_1")); // NOI18N

        jFormattedTextFieldSettlementdelay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        recoveryRateTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        recoveryRateTextField.setText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.recoveryRateTextField.text")); // NOI18N

        jComboBoxTenor.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxTenor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        versionTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        versionTextField.setText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.versionTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(versionLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.versionLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(serieLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.serieLabel.text")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));

        serieTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        serieTextField.setText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.serieTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(tenorLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.tenorLabel.text_1")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(serieLabel)
                        .addGap(130, 130, 130)
                        .addComponent(serieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(versionLabel)
                        .addGap(114, 114, 114)
                        .addComponent(versionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(recoveryRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(recoveryRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(statusLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tenorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(settlementDelayLabel))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGap(48, 48, 48)
                                    .addComponent(jFormattedTextFieldSettlementdelay, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonSchedule))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serieLabel)
                    .addComponent(serieTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(versionLabel)
                        .addGap(12, 12, 12))
                    .addComponent(versionTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(statusLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldSettlementdelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tenorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settlementDelayLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(recoveryRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(recoveryRateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSchedule))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(bpLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.bpLabel.text_1")); // NOI18N

        contractTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));

        org.openide.awt.Mnemonics.setLocalizedText(seniorityLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.seniorityLabel.text_1")); // NOI18N

        creditEntityField.setText(org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.creditEntityField.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(maturityLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.maturityLabel.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(couponLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.couponLabel.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(contractLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.contractLabel.text_1")); // NOI18N

        jComboBoxMaturity.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMaturity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        findButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(findButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.findButton.text_1")); // NOI18N
        findButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findButtonActionPerformed(evt);
            }
        });

        jFormattedTextFieldCoupon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(creditEntityLabel, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.creditEntityLabel.text_1")); // NOI18N

        showEntityButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(showEntityButton, org.openide.util.NbBundle.getMessage(CDSIndexTopComponent.class, "CDSIndexTopComponent.showEntityButton.text_1")); // NOI18N
        showEntityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEntityButtonActionPerformed(evt);
            }
        });

        jComboBoxSeniority.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxSeniority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(couponLabel)
                        .addGap(48, 48, 48)
                        .addComponent(jFormattedTextFieldCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bpLabel)
                        .addGap(207, 207, 207))
                    .addComponent(seniorityLabel)
                    .addComponent(contractLabel)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(maturityLabel)
                            .addComponent(creditEntityLabel))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contractTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBoxMaturity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(creditEntityField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jComboBoxSeniority, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(findButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(showEntityButton)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(maturityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creditEntityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditEntityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findButton)
                    .addComponent(showEntityButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(couponLabel)
                    .addComponent(bpLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxSeniority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(seniorityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contractTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contractLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonLoad)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNew)
                        .addGap(14, 14, 14)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSaveAsNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonClose))
                    .addComponent(jScrollPane1)
                    .addComponent(commentLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(shortNameLabel)
                                    .addComponent(typeLabel)
                                    .addComponent(currencyLabel)
                                    .addComponent(issueDateLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooserIssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(123, 123, 123)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(productReferencesLabel)
                                .addGap(24, 24, 24)
                                .addComponent(jButtonShowreferences)
                                .addGap(6, 6, 6)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(225, 225, 225)
                        .addComponent(removeIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(indexScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calculateButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(removeIndexButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(indexScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonShowreferences)
                                .addComponent(productReferencesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(shortNameLabel)
                                .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(typeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(currencyLabel))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jDateChooserIssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(issueDateLabel)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(commentLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSave)
                            .addComponent(jButtonClose)
                            .addComponent(jButtonLoad)
                            .addComponent(jButtonNew)
                            .addComponent(jButtonSaveAsNew)
                            .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextFieldId.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1091, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    public void isIndexEquityWindow() {

        jPanel2.setVisible(false);
        jPanel3.setVisible(false);
        validate();
        indexTable = new org.jdesktop.swingx.JXTable();
        indexTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Id", "Stock", "Weight"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        indexTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        indexTable.getTableHeader().setReorderingAllowed(false);
        indexScrollPane.setViewportView(indexTable);
    }
    private void jButtonScheduleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonScheduleActionPerformed
        /**
         * schedule
         */
        Trade trade_ = new Trade();
        trade_.setProduct(getProduct());
        trade_.setQuantity(new BigDecimal("100000"));
        trade_.setValueDate(getProduct().getStartDate());

        ScheduleTopComponent scheduleTopComponent = new ScheduleTopComponent();
        scheduleTopComponent.setTrade(trade_);
        scheduleTopComponent.open();
        scheduleTopComponent.requestActive();

    }//GEN-LAST:event_jButtonScheduleActionPerformed

    private void jButtonShowreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowreferencesActionPerformed
        /**
         * open product references
         */
        productReferencePanel = new ProductReferencesPanel(prodrefs);
        NotifyDescriptor nd = new NotifyDescriptor(productReferencePanel, "Product References", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

            JTable t = productReferencePanel.getTable();
            prodrefs = new ArrayList();
            for (int i = 0; i < t.getRowCount(); i++) {
                if (t.getValueAt(i, 1) != null) {
                    if (!t.getValueAt(i, 1).toString().isEmpty()) {
                        ProductReference prodref = new ProductReference();
                        prodref.setReferenceType(t.getValueAt(i, 0).toString());
                        prodref.setValue(t.getValueAt(i, 1).toString());
                        prodrefs.add(prodref);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonShowreferencesActionPerformed

    private void findButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findButtonActionPerformed
        LegalEntity entity = GUIUtils.findCreditEntity();
        if (entity != null) {
            creditEntityField.setText(entity.getShortName());
        }
    }//GEN-LAST:event_findButtonActionPerformed

    private void showEntityButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showEntityButtonActionPerformed

        try {
            LegalEntityTopComponent component = new LegalEntityTopComponent();
            component.open();
            component.loadEntity(GUIUtils.getComponentStringValue(creditEntityField));
            component.requestActive();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_showEntityButtonActionPerformed

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButtonActionPerformed

    private void removeIndexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeIndexButtonActionPerformed
        if (indexTable.getSelectedRow() >= 0) {
            removeIndex(Integer.parseInt(model.getValueAt(indexTable.getSelectedRow(), model.findColumn("Id")).toString()));
            model.removeRow(indexTable.getSelectedRow());
        }
    }//GEN-LAST:event_removeIndexButtonActionPerformed

    private void addIndexButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addIndexButtonActionPerformed
        addIndex();
    }

    public void addIndex() {
        LegalEntity issuer = GUIUtils.findCreditEntity();

        if (issuer != null && !existingCreditEntity(issuer)) {
            Vector<Object> rowToadd = new Vector<>();
            Product cdsProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                    ProductAccessor.FIND_CDS_PRODUCT_BY_ISSUER_AND_MATURITY_DATE, issuer, getProduct().getMaturityDate());
            if (cdsProduct != null) {
                rowToadd.add(cdsProduct.getProductId());
            } else {
                rowToadd.add(0);
                cdsProduct = ProductAccessor.buildIndexUnderlyingCDSProduct(getProduct(), issuer);
            }
            rowToadd.add(issuer.getShortName());
            rowToadd.add(1.0);
            rowToadd.add(recoveryRateTextField.getText());
            model.addRow(rowToadd);
            getProduct().addUnderlyingWithWeight(cdsProduct, BigDecimal.ZERO);
            underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
        }
    }//GEN-LAST:event_addIndexButtonActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        if (indexTable.getRowCount() > 0) {
            BigDecimal weightToSupply = new BigDecimal(100).divide(
                    new BigDecimal(indexTable.getRowCount()), 3, RoundingMode.HALF_UP);
            ProductUnderlying underlying;
            for (int i = 0; i < indexTable.getRowCount(); i++) {
                underlying = getUnderlyingFromTable(Integer.parseInt(
                        indexTable.getValueAt(i, model.findColumn("Id")).toString()));
                indexTable.setValueAt(weightToSupply, i, model.findColumn("Weight"));
                underlying.setWeight(weightToSupply.divide(new BigDecimal(100), 3, RoundingMode.HALF_UP));
            }
        }
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        loadAction();
    }

    public void loadAction() {
        /**
         * find and load
         *
         */

        assetFinder = new AssetFinder(listTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE,
                null, NotifyDescriptor.OK_OPTION);

        /**
         * display the dialog
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            load(productId);
            assetFinder.setVisible(false);
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        /**
         * exit
         */
        this.close();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        /**
         * Clear screen
         */
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        /**
         * Save
         */
        fillProduct();
        storeProduct();
    }

    /**
     * load product information
     */
    public void fillProduct() {

        if (getProduct() == null) {
            setProduct(new Product());
        }
        getProduct().setIsAsset(true);

        if (!jTextFieldId.getText().isEmpty()) {
            getProduct().setId(Integer.parseInt(jTextFieldId.getText()));
        }

        try {
            getProduct().setShortName(jTextFieldShortName.getText());
            getProduct().setProductType(jComboBoxType.getSelectedItem().toString());
            getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getProduct().setQuantityType(Trade.QuantityType.NOTIONAL.name);
            getProduct().setNotionalMultiplier(BigDecimal.ONE);
            getProduct().setComment(jTextAreaComment.getText());
            getProduct().setProductReferences(getProductReferences());
            getProduct().setStatus(statusComboBox.getSelectedItem().toString());
            getProduct().setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());

            getProduct().setUnderlyingProducts(underlyings);

            if (jDateChooserIssueDate.getDate() != null) {
                getProduct().setStartDate(jDateChooserIssueDate.getDate());
            }
            if (jComboBoxMaturity.getSelectedItem() != null) {
                getProduct().setMaturityDate(dateFormatter.parse(jComboBoxMaturity.getSelectedItem().toString()));
            }
            if (!jFormattedTextFieldSettlementdelay.getText().isEmpty()) {
                getProduct().setSettlementDelay(numberFormat.parse(jFormattedTextFieldSettlementdelay.getText()).shortValue());
            }

            /**
             * product rate
             */
            ProductRate productRate = getProduct().getProductRate();
            if (productRate == null) {
                productRate = new ProductRate();
                productRate.setProduct(getProduct());
                getProduct().setProductRate(productRate);
            }

            productRate.setMinimumQuantity(BigDecimal.ONE);

            if (!jFormattedTextFieldCoupon.getText().isEmpty()) {
                productRate.setRate(new BigDecimal(numberFormat.parse(jFormattedTextFieldCoupon.getText()).doubleValue()).divide(BigDecimal.valueOf(10000)));
            }
            /**
             * credit
             */
            String issuerName = GUIUtils.getComponentStringValue(creditEntityField);
            String seniority = GUIUtils.getComponentStringValue(jComboBoxSeniority);
            ProductCredit productCredit = getProduct().getProductCredit();
            if (productCredit == null) {
                productCredit = new ProductCredit();
                productCredit.setProduct(getProduct());
                getProduct().setProductCredit(productCredit);
            }
            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, issuerName);

            productCredit.setIssuer(issuer);
            productCredit.setSeniority(seniority);
            if (contractTypeComboBox.getSelectedItem() != null) {
                productCredit.setContractType(contractTypeComboBox.getSelectedItem().toString());
            }
            if (!StringUtils.isEmptyString(serieTextField.getText())) {
                productCredit.setSerie(GUIUtils.getComponentIntegerValue(serieTextField).shortValue());
            }
            if (!StringUtils.isEmptyString(versionTextField.getText())) {
                productCredit.setVersion(GUIUtils.getComponentIntegerValue(versionTextField).shortValue());
            }
            if (!StringUtils.isEmptyString(recoveryRateTextField.getText())) {
                productCredit.setRecoveryFactor(GUIUtils.getComponentBigDecimalValue(recoveryRateTextField));
            }
            /**
             * product curve
             */
            String tenor = GUIUtils.getComponentStringValue(jComboBoxTenor);
            ProductCurve curve = getProduct().getProductCurve();
            if (curve == null) {
                curve = new ProductCurve();
                curve.setProduct(getProduct());
                getProduct().setProductCurve(curve);
            }
            curve.setTenor(tenor);

            /**
             * scheduler
             */
            Scheduler scheduler = getProduct().getScheduler();
            if (scheduler == null) {
                scheduler = new Scheduler();
            }
            scheduler.setFrequency(FrequencyUtil.Frequency.QUARTERLY.name);
            scheduler.setDaycount(DayCountAccessor.DayCount.ACT_360.getName());
            scheduler.setAdjustment(DateUtils.ADJUSTMENT_FOLLOW);
            scheduler.setPaymentLag(0);
            scheduler.setIsPayInArrears(true);
            scheduler.setIsPayLagBusDays(true);
            getProduct().setScheduler(scheduler);

            if (getProduct().getShortName().isEmpty()) {
                getProduct().setShortName(GUIUtils.getComponentStringValue(creditEntityField) + StringUtils.SPACE + jComboBoxMaturity.getSelectedItem().toString() + StringUtils.SPACE + jFormattedTextFieldCoupon.getText() + "bp");
            }

        } catch (Exception e) {
            logger.error(e);
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSaveAsNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNewActionPerformed
        saveAsNewIndex();
    }

    public void saveAsNewIndex() {
        /**
         * save as new
         */
        if (getProduct() != null && getProduct().getId() != null) {
            jTextFieldId.setText(StringUtils.EMPTY_STRING);
            if (jTextFieldShortName.getText().equalsIgnoreCase(getProduct().getShortName())) {
                jTextFieldShortName.setText(StringUtils.EMPTY_STRING);
            }

            setProduct(null);
            fillProduct();
            storeProduct();
        }
    }//GEN-LAST:event_jButtonSaveAsNewActionPerformed

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private boolean existingCreditEntity(LegalEntity issuer) {
        for (int i = 0; i < model.getRowCount(); i++) {
            String value = (String) model.getValueAt(i, model.findColumn("Credit Entity"));
            if (issuer.getShortName().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * store product
     */
    public void storeProduct() {
        if (getProduct().getNotionalCurrency().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Currency must not be empty.");
        } else if (getProduct()
                .getProductType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Product Type must not be empty.");
        } else if (getProduct()
                .getQuotationType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Quotation Type must not be empty.");
        } else {
            try {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, getProduct()));
                if (getProduct().getId() != null) {
                    jTextFieldId.setText(getProduct().getId().toString());
                    load(getProduct().getId());
                    JOptionPane.showMessageDialog(this, "Saved with id " + getProduct().getId());
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     * load product
     *
     * @param productId
     */
    public void load(Integer productId) {
        if (productId != null) {
            try {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));
                List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, productId);

                getProduct().setProductReferences(references);
                jTextFieldId.setText(productId.toString());
                jTextFieldShortName.setText(getProduct().getShortName());
                jComboBoxType.setSelectedItem(getProduct().getProductType());
                jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());

                if (getProduct().getProductCurve() != null) {
                    jComboBoxTenor.setSelectedItem(getProduct().getProductCurve().getTenor());
                } else {
                    jComboBoxTenor.setSelectedItem(null);
                }

                if (getProduct().getMaturityDate() != null) {
                    jComboBoxMaturity.setSelectedItem(dateFormatter.format(getProduct().getMaturityDate()));
                } else {
                    jComboBoxMaturity.setSelectedItem(null);
                }

                if (getProduct().getStartDate() != null) {
                    jDateChooserIssueDate.setDate(getProduct().getStartDate());
                } else {
                    jDateChooserIssueDate.setDate(null);
                }

                if (statusComboBox.getSelectedItem() != null) {
                    statusComboBox.setSelectedItem(getProduct().getStatus());
                }
                if (getProduct().getSettlementDelay() != null) {
                    jFormattedTextFieldSettlementdelay.setText(getProduct().getSettlementDelay().toString());
                } else {
                    jFormattedTextFieldSettlementdelay.setText(StringUtils.EMPTY_STRING);
                }

                if (getProduct().getProductRate() != null) {

                    if (getProduct().getProductRate().getRate() != null) {
                        jFormattedTextFieldCoupon.setText(decimalFormat.format(getProduct().getProductRate().getRate().multiply(BigDecimal.valueOf(10000))));
                    } else {
                        jFormattedTextFieldCoupon.setText(StringUtils.EMPTY_STRING);
                    }
                }

                if (getProduct().getProductCredit() != null) {

                    if (getProduct().getProductCredit().getIssuer() != null) {

                        LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product.getProductId());
                        String issuerName = StringUtils.EMPTY_STRING;
                        if (issuer != null) {
                            issuerName = issuer.getShortName();
                        }
                        creditEntityField.setText(issuerName);
                    } else {
                        creditEntityField.setText(null);
                    }
                    if (getProduct().getProductCredit().getSeniority() != null) {
                        jComboBoxSeniority.setSelectedItem(getProduct().getProductCredit().getSeniority().toString());
                    } else {
                        jComboBoxSeniority.setSelectedItem(null);
                    }
                    if (getProduct().getProductCredit().getContractType() != null) {
                        contractTypeComboBox.setSelectedItem(getProduct().getProductCredit().getContractType());
                    } else {
                        contractTypeComboBox.setSelectedItem(null);
                    }
                    if (getProduct().getProductCredit().getSerie() != null) {
                        serieTextField.setText(getProduct().getProductCredit().getSerie().toString());
                    } else {
                        serieTextField.setText(null);
                    }
                    if (getProduct().getProductCredit().getVersion() != null) {
                        versionTextField.setText(getProduct().getProductCredit().getVersion().toString());
                    } else {
                        versionTextField.setText(null);
                    }
                    if (getProduct().getProductCredit().getRecoveryFactor() != null) {
                        recoveryRateTextField.setText(getProduct().getProductCredit().getRecoveryFactor().toString());
                    } else {
                        recoveryRateTextField.setText(null);
                    }
                }
                prodrefs = new ArrayList();
                for (ProductReference productReference : getProduct().getProductReferences()) {
                    prodrefs.add(productReference);
                }

                fillIndexTable();

                setDisplayName(getDisplayName());
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public org.jdesktop.swingx.JXButton addIndexButton;
    private javax.swing.JLabel bpLabel;
    private org.jdesktop.swingx.JXButton calculateButton;
    private javax.swing.JLabel commentLabel;
    private javax.swing.JLabel contractLabel;
    private javax.swing.JComboBox contractTypeComboBox;
    private javax.swing.JLabel couponLabel;
    private javax.swing.JTextField creditEntityField;
    private javax.swing.JLabel creditEntityLabel;
    private javax.swing.JLabel currencyLabel;
    private javax.swing.JButton findButton;
    private javax.swing.JScrollPane indexScrollPane;
    public org.jdesktop.swingx.JXTable indexTable;
    private javax.swing.JLabel issueDateLabel;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAsNew;
    private javax.swing.JButton jButtonSchedule;
    private javax.swing.JButton jButtonShowreferences;
    public javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxMaturity;
    private javax.swing.JComboBox jComboBoxSeniority;
    private javax.swing.JComboBox jComboBoxTenor;
    public javax.swing.JComboBox jComboBoxType;
    public com.toedter.calendar.JDateChooser jDateChooserIssueDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldCoupon;
    private javax.swing.JFormattedTextField jFormattedTextFieldSettlementdelay;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea jTextAreaComment;
    public javax.swing.JTextField jTextFieldId;
    public javax.swing.JTextField jTextFieldShortName;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private javax.swing.JLabel maturityLabel;
    private javax.swing.JLabel productReferencesLabel;
    private org.jdesktop.swingx.JXLabel recoveryRateLabel;
    private javax.swing.JFormattedTextField recoveryRateTextField;
    private org.jdesktop.swingx.JXButton removeIndexButton;
    private javax.swing.JLabel seniorityLabel;
    private javax.swing.JLabel serieLabel;
    private javax.swing.JTextField serieTextField;
    private javax.swing.JLabel settlementDelayLabel;
    private javax.swing.JLabel shortNameLabel;
    private javax.swing.JButton showEntityButton;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JLabel tenorLabel;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JTextField versionTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }

    @Override
    public void initContext() {
        jDateChooserIssueDate.setDate(DateUtils.getDate());
        try {
            prodrefs = new ArrayList();

            listTypes = new ArrayList();
            listTypes.add(ProductTypeUtil.ProductType.CDS_INDEX);

            if (listTypes != null) {
                for (ProductTypeUtil.ProductType type : listTypes) {
                    jComboBoxType.addItem(type.name);
                }
            }
            /**
             * list of currencies
             */
            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            /**
             * list of tenors
             */
            List<String> tenors = FrequencyUtil.getTenors();

            GUIUtils.fillComboWithNullFirst(jComboBoxTenor, tenors);
            /**
             * list of seniorities
             */
            List<String> seniorities = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_BOND_SENIORITIES);

            GUIUtils.fillComboWithNullFirst(jComboBoxSeniority, seniorities);
            /**
             * list of immDates
             */
            List<Date> immDates = ProductAccessor.getCdsImmDates();

            GUIUtils.fillCombo(jComboBoxMaturity, GUIUtils.formatDateList(immDates, dateFormatter));

            /**
             * list of contract types
             */
            List<String> contracts = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_CREDIT_CONTRACT_TYPE_NAMES);

            GUIUtils.fillComboWithNullFirst(contractTypeComboBox, contracts);
            /**
             * list of status
             */
            List<String> status = ProductTypeUtil.getProductStatusList();

            GUIUtils.fillCombo(statusComboBox, status);
            model = (DefaultTableModel) indexTable.getModel();

            /**
             * ShortCut for date
             */
            DateShortCut.eventkey(
                    (JSpinnerDateEditor) jDateChooserIssueDate.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    public void fillIndexTable() {
        underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
        Vector<Vector<Object>> tableData = new Vector();
        for (ProductUnderlying underlying : underlyings) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(underlying.getUnderlying().getProductId().toString());
            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class,
                    ProductAccessor.GET_PRODUCT_ISSUER, underlying.getUnderlying().getProductId());
            rowData.add(issuer.getShortName());
            rowData.add(underlying.getWeight().multiply(new BigDecimal(100)));
            if (underlying.getUnderlying().getProductCredit().getRecoveryFactor() != null) {
                rowData.add(underlying.getUnderlying().getProductCredit().getRecoveryFactor().multiply(new BigDecimal(100)));
            } else {
                rowData.add(new BigDecimal("40"));
            }
            tableData.add(rowData);
            GUIUtils.setNumberEditor(indexTable, 2, sDecimalFormat);
            GUIUtils.setNumberEditor(indexTable, 3, sDecimalFormat);

        }

        model.getDataVector().removeAllElements();
        model.getDataVector().addAll(tableData);
        indexTable.setModel(model);
        model.fireTableDataChanged();
    }

    public ProductUnderlying getUnderlyingFromTable(Integer underlyingId) {
        for (ProductUnderlying underlying : underlyings) {
            if (underlyingId.compareTo(0) == 0 && underlying.getUnderlying().getProductId() == null) {
                return underlying;
            } else if (underlying.getUnderlying().getProductId() != null
                    && underlyingId.compareTo(underlying.getUnderlying().getProductId()) == 0) {
                return underlying;
            }
        }
        return null;
    }

    private void removeIndex(Integer underlyingId) {
        underlyings.remove(getUnderlyingFromTable(underlyingId));
        getProduct().setUnderlyingProducts(underlyings);

    }

    public class MyCellEditorListener implements CellEditorListener {

        @Override
        public void editingStopped(ChangeEvent e) {
            int row = indexTable.getSelectedRow();
            ProductUnderlying underlying = getUnderlyingFromTable(Integer.parseInt(
                    indexTable.getValueAt(indexTable.getSelectedRow(), model.findColumn("Id")).toString()));
            Object objectValue = indexTable.getValueAt(row, model.findColumn("Weight"));
            String columnvalue = objectValue == null ? StringUtils.EMPTY_STRING : objectValue.toString();

            if (!StringUtils.isEmptyString(columnvalue)) {
                BigDecimal weight = NumberUtils.stringToNumber(columnvalue, rateDecimalFormat);
                underlying.setWeight(weight.divide(new BigDecimal(100), 10, RoundingMode.HALF_UP));
                indexTable.setValueAt(weight, row, model.findColumn("Weight"));
            }
            objectValue = indexTable.getValueAt(row, model.findColumn("Recovery Rate"));
            columnvalue = objectValue == null ? StringUtils.EMPTY_STRING : objectValue.toString();
            if (!StringUtils.isEmptyString(columnvalue)) {
                BigDecimal recoveryRate = NumberUtils.stringToNumber(columnvalue, rateDecimalFormat);
                underlying.getUnderlying().getProductCredit().setRecoveryFactor(recoveryRate.divide(new BigDecimal(100), 10, RoundingMode.HALF_UP));
                indexTable.setValueAt(recoveryRate, row, model.findColumn("Recove"
                        + "ry Rate"));
            }
            underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
