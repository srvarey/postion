/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * jComboBoxIssuer but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.assets;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaProductTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.dateFormatter;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.common.ProductReferencesPanel;
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
 * Top component which displays credit entities.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//CDSProduct//EN", autostore = false)
@TopComponent.Description(preferredID = "CDSProductTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.CDSProductTopComponent")
@ActionReference(path = "Menu"+MenuManager.CDSProductTopComponentMenu, position = MenuManager.CDSProductTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CDSProductAction")
@Messages({"CTL_CDSProductAction=CDS Product", "CTL_CDSProductTopComponent=CDS Product", "HINT_CDSProductTopComponent=This is a CDS Product window"})
public final class CDSProductTopComponent extends GaiaProductTopComponent {

    private AssetFinder assetFinder = null;
    private ProductReferencesPanel productReferences = null;
    private List<ProductReference> prodrefs = null;
    public String dayCount = "ACT/360";
    public String adjustment = "Following";
    public boolean paymentInArrears = true;
    public int payLag = 1;
    public boolean payLagBusDays = true;
    public ArrayList<ProductTypeUtil.ProductType> listTypes = null;
    private static final Logger logger = Logger.getLogger(CDSProductTopComponent.class);

    public CDSProductTopComponent() {
        initComponents();
        setName(Bundle.CTL_CDSProductTopComponent());
        setToolTipText(Bundle.HINT_CDSProductTopComponent());
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    public CDSProductTopComponent(Integer productId) {
        this();
        load(productId);
    }

    @Override
    public void initContext() {
        jDateChooserIssueDate.setDate(DateUtils.getDate());
        try {
            prodrefs = new ArrayList();

            listTypes = new ArrayList();
            listTypes.add(ProductTypeUtil.ProductType.CDS_PRODUCT);

            if (listTypes != null) {
                for (ProductTypeUtil.ProductType type : listTypes) {
                    jComboBoxType.addItem(type.name);
                }
            }
            jComboBoxType.setSelectedIndex(0);

            /**
             * list of currencies
             */
            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            String defaultCurrency = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId());
            jComboBoxCurrency.setSelectedItem(defaultCurrency);

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

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldShortName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldSettlementdelay = new javax.swing.JFormattedTextField(new DecimalFormat("#"));
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButtonShowreferences = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaComment = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextFieldCoupon = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBoxSeniority = new javax.swing.JComboBox();
        jButtonSchedule = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxTenor = new javax.swing.JComboBox();
        jButtonSaveAsNew = new javax.swing.JButton();
        jComboBoxMaturity = new javax.swing.JComboBox();
        jDateChooserIssueDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        contractTypeComboBox = new javax.swing.JComboBox();
        creditEntityField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        showEntityButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        loadEventXButton = new org.jdesktop.swingx.JXButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setPreferredSize(new java.awt.Dimension(746, 900));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel1.text")); // NOI18N

        jTextFieldShortName.setName("jTextFieldShortName"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel3.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxType.setName("jComboBoxType"); // NOI18N
        jComboBoxType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxTypeActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel4.text")); // NOI18N

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxCurrency.setName("jComboBoxCurrency"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel7.text")); // NOI18N

        jFormattedTextFieldSettlementdelay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel12.text")); // NOI18N

        jButtonShowreferences.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowreferences, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonShowreferences.text")); // NOI18N
        jButtonShowreferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowreferencesActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextAreaComment.setColumns(20);
        jTextAreaComment.setRows(5);
        jScrollPane1.setViewportView(jTextAreaComment);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel19.text")); // NOI18N

        jButtonClose.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonClose.text")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel15.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel16.text")); // NOI18N

        jFormattedTextFieldCoupon.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldCoupon.setName("jFormattedTextFieldCoupon"); // NOI18N
        jFormattedTextFieldCoupon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldCouponActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel30, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel30.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel31, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel31.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel35, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel35.text")); // NOI18N

        jComboBoxSeniority.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxSeniority.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jButtonSchedule.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSchedule, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonSchedule.text")); // NOI18N
        jButtonSchedule.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonScheduleActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel5.text")); // NOI18N

        jComboBoxTenor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jButtonSaveAsNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButtonSaveAsNew.text")); // NOI18N
        jButtonSaveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNewActionPerformed(evt);
            }
        });

        jComboBoxMaturity.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMaturity.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxMaturity.setName("jComboBoxMaturity"); // NOI18N

        jDateChooserIssueDate.setBackground(new java.awt.Color(254, 252, 254));

        jTextFieldId.setEditable(false);
        jTextFieldId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldId.setText(org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jTextFieldId.text")); // NOI18N
        jTextFieldId.setName("jTextFieldId"); // NOI18N
        jTextFieldId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldIdActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel8.text")); // NOI18N

        contractTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        contractTypeComboBox.setName("contractTypeComboBox"); // NOI18N

        creditEntityField.setText(org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.creditEntityField.text")); // NOI18N
        creditEntityField.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        showEntityButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(showEntityButton, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.showEntityButton.text")); // NOI18N
        showEntityButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showEntityButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.jLabel2.text_1")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setName("statusComboBox"); // NOI18N

        loadEventXButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(CDSProductTopComponent.class, "CDSProductTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel15)
                                            .addComponent(jLabel16))
                                        .addGap(26, 26, 26))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel35, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel30, javax.swing.GroupLayout.Alignment.LEADING))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBoxMaturity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jDateChooserIssueDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBoxSeniority, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                            .addComponent(jFormattedTextFieldCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel31)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jComboBoxType, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(creditEntityField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton1))
                                            .addComponent(contractTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(showEntityButton))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel19)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxTenor, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jFormattedTextFieldSettlementdelay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jButtonSchedule))
                                    .addComponent(jButtonShowreferences, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(153, 153, 153)
                                .addComponent(jButtonLoad)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonNew)
                                .addGap(14, 14, 14)
                                .addComponent(jButtonSave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonSaveAsNew)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonClose)))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditEntityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(showEntityButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooserIssueDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jFormattedTextFieldCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxSeniority, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(statusComboBox)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jFormattedTextFieldSettlementdelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contractTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonShowreferences))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jButtonSchedule)
                    .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonClose)
                    .addComponent(jButtonLoad)
                    .addComponent(jButtonNew)
                    .addComponent(jButtonSaveAsNew))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTextFieldId.setVisible(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveAsNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNewActionPerformed
        /**
         * save as new
         */
        jTextFieldId.setText(StringUtils.EMPTY_STRING);
        if (jTextFieldShortName.getText().equalsIgnoreCase(getProduct().getShortName())) {
            jTextFieldShortName.setText(StringUtils.EMPTY_STRING);
        }

        setProduct(null);
        fillProduct();
        storeProduct();
    }//GEN-LAST:event_jButtonSaveAsNewActionPerformed

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

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        /**
         * Clear screen
         */
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        /**
         * find and load
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

        if (!jTextFieldId.getText().isEmpty()) {
            getProduct().setId(Integer.parseInt(jTextFieldId.getText()));
        }

        try {
            getProduct().setIsAsset(true);
            getProduct().setShortName(jTextFieldShortName.getText());
            getProduct().setProductType(jComboBoxType.getSelectedItem().toString());
            getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getProduct().setQuantityType(Trade.QuantityType.NOTIONAL.name);
            getProduct().setNotionalMultiplier(BigDecimal.ONE);
            getProduct().setComment(jTextAreaComment.getText());
            getProduct().setProductReferences(getProductReferences());
            getProduct().setStatus(statusComboBox.getSelectedItem().toString());
            getProduct().setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());

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

    private void jButtonShowreferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowreferencesActionPerformed
        /**
         * open product references
         */
        productReferences = new ProductReferencesPanel(prodrefs);
        NotifyDescriptor nd = new NotifyDescriptor(productReferences, "Product References", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

            JTable t = productReferences.getTable();
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

    private void jComboBoxTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxTypeActionPerformed
    }//GEN-LAST:event_jComboBoxTypeActionPerformed

    private void jFormattedTextFieldCouponActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldCouponActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldCouponActionPerformed

    private void jTextFieldIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldIdActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LegalEntity entity = GUIUtils.findCreditEntity();
        if (entity != null) {
            creditEntityField.setText(entity.getShortName());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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

    /**
     * store product
     */
    public void storeProduct() {

        if (getProduct().getNotionalCurrency().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "The currency must not be empty.");
        } else if (getProduct().getProductType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "The Product Type must not be empty.");
        } else if (getProduct().getQuotationType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "The Quotation Type must not be empty.");
        } else if (getProduct().getProductCredit().getIssuer()==null) {
            JOptionPane.showMessageDialog(this, "The credit entity must not be empty.");
        } else {
            try {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, getProduct()));
                if (getProduct().getId() != null) {
                    jTextFieldId.setText(getProduct().getId().toString());
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
                }
                prodrefs = new ArrayList();
                for (ProductReference productReference : getProduct().getProductReferences()) {
                    prodrefs.add(productReference);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox contractTypeComboBox;
    private javax.swing.JTextField creditEntityField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAsNew;
    private javax.swing.JButton jButtonSchedule;
    private javax.swing.JButton jButtonShowreferences;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxMaturity;
    private javax.swing.JComboBox jComboBoxSeniority;
    private javax.swing.JComboBox jComboBoxTenor;
    private javax.swing.JComboBox jComboBoxType;
    private com.toedter.calendar.JDateChooser jDateChooserIssueDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldCoupon;
    private javax.swing.JFormattedTextField jFormattedTextFieldSettlementdelay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextAreaComment;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldShortName;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private javax.swing.JButton showEntityButton;
    private javax.swing.JComboBox statusComboBox;
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
