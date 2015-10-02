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
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.assets;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.MarketDataSourceUtils;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.referentials.CountryAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.Country;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEquity;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaProductTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.common.ProductMarketDataCodesPanel;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.io.XMLExporter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays equities.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//Fund//EN", autostore = false)
@TopComponent.Description(preferredID = "FundTopComponent",iconBase="org/gaia/gui/assets/Funds.png", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.FundTopComponent")
@ActionReference(path = "Menu"+MenuManager.FundTopComponentMenu, position = MenuManager.FundTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FundAction")
@Messages({"CTL_FundAction=Funds", "CTL_FundTopComponent=Fund", "HINT_FundTopComponent=Funds window"})
public final class FundTopComponent extends GaiaProductTopComponent {

    private static final Logger logger = Logger.getLogger(FundTopComponent.class);
    private AssetFinder assetFinder = null;

    public FundTopComponent() {
        initComponents();
        setName(Bundle.CTL_FundTopComponent());
        setToolTipText(Bundle.HINT_FundTopComponent());
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldShortName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldLongName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxQuotationType = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextFieldLotSize = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextFieldSettlementDelay = new javax.swing.JFormattedTextField(new DecimalFormat("#"));
        jLabel8 = new javax.swing.JLabel();
        jFormattedTextFieldIssuedShare = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxPortfolio = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxCountry = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jButtonShowReferences = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jLabelProductId = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jButtonClose = new javax.swing.JButton();
        jButtonLoad = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jButtonExport = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jToggleShowMarketCodes = new javax.swing.JToggleButton();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldUnderlying = new javax.swing.JTextField();
        jButtonLoadUnderlying = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jComboBoxIssuer = new javax.swing.JComboBox();
        jLabelUnderlyingId = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel1.text")); // NOI18N

        jTextFieldShortName.setName("jTextFieldShortName"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel3.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel4.text")); // NOI18N

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel5.text")); // NOI18N

        jComboBoxQuotationType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuotationType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel6.text")); // NOI18N

        jFormattedTextFieldLotSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel7.text")); // NOI18N

        jFormattedTextFieldSettlementDelay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel8.text")); // NOI18N

        jFormattedTextFieldIssuedShare.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxPortfolio.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPortfolio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel10.text")); // NOI18N

        jComboBoxCountry.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel12.text")); // NOI18N

        jButtonShowReferences.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowReferences, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonShowReferences.text")); // NOI18N
        jButtonShowReferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowReferencesActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel19.text")); // NOI18N

        jButtonClose.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonClose.text")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonExport.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel14.text")); // NOI18N

        jToggleShowMarketCodes.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jToggleShowMarketCodes, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jToggleShowMarketCodes.text")); // NOI18N
        jToggleShowMarketCodes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleShowMarketCodesActionPerformed(evt);
            }
        });

        loadEventXButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel13.text")); // NOI18N

        jTextFieldUnderlying.setEditable(false);
        jTextFieldUnderlying.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButtonLoadUnderlying.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadUnderlying, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButtonLoadUnderlying.text")); // NOI18N
        jButtonLoadUnderlying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadUnderlyingActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabel11.text")); // NOI18N

        jComboBoxIssuer.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxIssuer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlyingId, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jLabelUnderlyingId.text")); // NOI18N
        jLabelUnderlyingId.setEnabled(false);

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(FundTopComponent.class, "FundTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButtonExport)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(jButtonLoad)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonNew)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonSave)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonClose))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4)
                                .addComponent(jLabel1))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jComboBoxPortfolio, javax.swing.GroupLayout.Alignment.LEADING, 0, 161, Short.MAX_VALUE)
                                        .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(jButtonLoadUnderlying)))
                                .addComponent(jComboBoxQuotationType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldLongName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(40, 40, 40)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(81, 81, 81)
                                        .addComponent(jFormattedTextFieldLotSize, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(73, 73, 73)
                                        .addComponent(jFormattedTextFieldSettlementDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTextFieldIssuedShare, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel9)
                                                    .addComponent(jLabel10))
                                                .addGap(51, 51, 51)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jComboBoxIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jButtonShowReferences)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel14)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jToggleShowMarketCodes)))
                                        .addGap(8, 8, 8)))))
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING))
                    .addComponent(jLabel13)
                    .addComponent(jLabel11))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldLotSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLongName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jFormattedTextFieldSettlementDelay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextFieldIssuedShare, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel9)
                    .addComponent(jComboBoxIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxQuotationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadUnderlying)
                    .addComponent(jLabel12)
                    .addComponent(jButtonShowReferences))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jComboBoxPortfolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleShowMarketCodes)
                    .addComponent(jLabel14)
                    .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSave)
                        .addComponent(jButtonClose)
                        .addComponent(jButtonLoad)
                        .addComponent(jButtonNew)
                        .addComponent(jButtonExport)
                        .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1))
                    .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
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

    private void jButtonLoadUnderlyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadUnderlyingActionPerformed
        /**
         * find underlying
         */
        assetFinder = new AssetFinder(ProductTypeUtil.loadEquityUnderlyings());

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            try {
                Integer productId = assetFinder.getAssetId();
                if (productId!=null){
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                getProduct().getUnderlyingProducts().clear();
                getProduct().addUnderlying(underlying);
                jTextFieldUnderlying.setText(underlying.getShortName());
                jLabelUnderlyingId.setText(underlying.getProductId().toString());
                } else {
                    getProduct().getUnderlyingProducts().clear();
                    jTextFieldUnderlying.setText(StringUtils.EMPTY_STRING);
                    jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }//GEN-LAST:event_jButtonLoadUnderlyingActionPerformed

    private void jButtonShowReferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowReferencesActionPerformed
        /**
         * open product references
         */
        productRefsPanel = new ProductReferencesPanel(getProduct().getProductReferences());
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(productRefsPanel, "Product References", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {

            JTable table = productRefsPanel.getTable();
            getProduct().setProductReferences(new ArrayList());
            for (int i = 0; i < table.getRowCount(); i++) {
                if (table.getValueAt(i, 1) != null) {
                    if (!table.getValueAt(i, 1).toString().isEmpty()) {
                        ProductReference prodref = new ProductReference();
                        prodref.setProduct(getProduct());
                        prodref.setReferenceType(table.getValueAt(i, 0).toString());
                        prodref.setValue(table.getValueAt(i, 1).toString());
                        getProduct().getProductReferences().add(prodref);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonShowReferencesActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        /**
         * Save product
         */
        fillProduct();
        storeProduct();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    public void fillProduct(){
        try {
            if (getProduct() == null) {
                setProduct(new Product());
            }

            getProduct().setIsAsset(true);
            getProduct().setShortName(jTextFieldShortName.getText());
            getProduct().setLongName(jTextFieldLongName.getText());
            getProduct().setProductType(jComboBoxType.getSelectedItem().toString());
            getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getProduct().setQuantityType(Trade.QuantityType.QUANTITY.name);
            getProduct().setQuotationType(jComboBoxQuotationType.getSelectedItem().toString());
            getProduct().setComment(jTextArea1.getText());

            if (jTextFieldUnderlying.getText() != null && !jTextFieldUnderlying.getText().isEmpty()) {
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID,
                        Integer.parseInt(jLabelUnderlyingId.getText()));
                getProduct().getUnderlyingProducts().clear();
                if (underlying != null) {
                    getProduct().addUnderlying(underlying);
                }
            }
            if (!jFormattedTextFieldSettlementDelay.getText().isEmpty()) {
                getProduct().setSettlementDelay(numberFormat.parse(jFormattedTextFieldSettlementDelay.getText()).shortValue());
            } else {
                getProduct().setSettlementDelay(new Short("3"));
            }

            // product equity
            ProductEquity equity = getProduct().getProductEquity();
            if (equity == null) {
                equity = new ProductEquity();
                getProduct().setProductEquity(equity);
                equity.setProduct(getProduct());
            }
            if (!jFormattedTextFieldLotSize.getText().isEmpty()) {
                equity.setMinimumQuantity(numberFormat.parse(jFormattedTextFieldLotSize.getText()).longValue());
            } else {
                equity.setMinimumQuantity(new Long("1"));
            }
            if (!jFormattedTextFieldIssuedShare.getText().isEmpty()) {
                equity.setIssuedShares(Long.parseLong(jFormattedTextFieldIssuedShare.getText()));
            } else {
                equity.setIssuedShares(null);
            }
            if (jComboBoxPortfolio.getSelectedItem() != null && !jComboBoxPortfolio.getSelectedItem().toString().isEmpty()) {
                LegalEntity portfolio = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxPortfolio.getSelectedItem().toString());
                if (portfolio != null) {
                    equity.setPortfolio(portfolio);
                } else {
                    equity.setPortfolio(null);
                }
            } else {
                equity.setPortfolio(null);
            }

            if (jComboBoxIssuer.getSelectedItem() != null && !jComboBoxIssuer.getSelectedItem().toString().isEmpty()) {
                LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxIssuer.getSelectedItem().toString());
                if (issuer != null) {
                    equity.setIssuer(issuer);
                } else {
                    equity.setIssuer(null);
                }
            } else {
                equity.setIssuer(null);
            }
            if (jComboBoxCountry.getSelectedItem() != null) {
                Country country = (Country) DAOCallerAgent.callMethod(CountryAccessor.class, CountryAccessor.GET_COUNTRY_BY_NAME,jComboBoxCountry.getSelectedItem().toString());
                if (country != null) {
                    equity.setCountry(country);
                } else {
                    equity.setCountry(null);
                }
            } else {
                equity.setCountry(null);
            }

        } catch (Exception e) {
            System.err.println(e);
        }
    }
    public void storeProduct(){
        try {

            if (getProduct().getShortName().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Short Name must not be empty.");
            } else if (getProduct().getNotionalCurrency().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Currency must not be empty.");
            } else if (getProduct().getProductType().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Product Type must not be empty.");
            } else if (getProduct().getQuotationType().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Quotation Type must not be empty.");
            } else {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, getProduct()));
                if (getProduct().getId() != null) {
                    jLabelProductId.setText(getProduct().getId().toString());
                    JOptionPane.showMessageDialog(this, "Saved with id " + getProduct().getId());
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void jButtonCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCloseActionPerformed
        this.close();
    }//GEN-LAST:event_jButtonCloseActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        /**
         * find and load
         */
        assetFinder = new AssetFinder(ProductTypeUtil.loadFunds());
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            load(productId);
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    public void load(Integer productId) {
        if (productId != null) {
            try {
                setDisplayName(getDisplayName());
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));
                List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, productId);
                getProduct().setProductReferences(references);

                jLabelProductId.setText(productId.toString());
                jTextFieldShortName.setText(getProduct().getShortName());
                jTextFieldLongName.setText(getProduct().getLongName());
                jComboBoxType.setSelectedItem(getProduct().getProductType());
                jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());
                jComboBoxQuotationType.setSelectedItem(getProduct().getQuotationType());

                if (getProduct().loadSingleUnderlying() != null) {
                    Product und = getProduct().loadSingleUnderlying();
                    jTextFieldUnderlying.setText(und.getShortName());
                    jLabelUnderlyingId.setText(und.getId().toString());

                } else {
                    jTextFieldUnderlying.setText(StringUtils.EMPTY_STRING);
                    jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
                }
                if (getProduct().getProductEquity() != null) {
                    if (getProduct().getProductEquity().getMinimumQuantity() != null) {
                        jFormattedTextFieldLotSize.setText(getProduct().getProductEquity().getMinimumQuantity().toString());
                    } else {
                        jFormattedTextFieldLotSize.setText(StringUtils.EMPTY_STRING);
                    }
                    if (getProduct().getSettlementDelay() != null) {
                        jFormattedTextFieldSettlementDelay.setText(String.valueOf(getProduct().getSettlementDelay()));
                    } else {
                        jFormattedTextFieldSettlementDelay.setText(StringUtils.EMPTY_STRING);
                    }
                    if (getProduct().getProductEquity().getIssuedShares() != null) {
                        jFormattedTextFieldIssuedShare.setText(getProduct().getProductEquity().getIssuedShares().toString());
                    } else {
                        jFormattedTextFieldIssuedShare.setText(StringUtils.EMPTY_STRING);
                    }
                    if (getProduct().getProductEquity().getCountry() != null) {
                        jComboBoxCountry.setSelectedItem(getProduct().getProductEquity().getCountry().getName());
                    } else {
                        jComboBoxCountry.setSelectedItem(null);
                    }
                    if (getProduct().getProductEquity().getPortfolio() != null) {
                        LegalEntity portfolio=(LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class,ProductAccessor.GET_PRODUCT_PORTFOLIO,product.getProductId());
                        if (portfolio!=null){
                            jComboBoxPortfolio.setSelectedItem(portfolio.getShortName());
                        } else {
                            jComboBoxPortfolio.setSelectedItem(null);
                        }
                    } else {
                        jComboBoxPortfolio.setSelectedItem(null);
                    }
                    if (getProduct().getProductEquity().getIssuer() != null) {
                        LegalEntity issuer=(LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class,ProductAccessor.GET_PRODUCT_ISSUER,product.getProductId());
                        if (issuer!=null){
                            jComboBoxIssuer.setSelectedItem(issuer.getShortName());
                        } else {
                            jComboBoxIssuer.setSelectedItem(null);
                        }
                    } else {
                        jComboBoxIssuer.setSelectedItem(null);
                    }

                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        /**
         * Clear screen
         */
        setProduct(new Product());
        jLabelProductId.setText("");
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        /**
         * Export to xml
         */
        XMLExporter exportxml = new XMLExporter();
        exportxml.export(getProduct(), this);
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jToggleShowMarketCodesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleShowMarketCodesActionPerformed

        /**
         * open market codes
         */
        Collection<MarketDataCode> codes = getProduct().getMarketDataCodes();
        try {
            codes.size(); // trigger the lazy initialize exception if not initialied
        } catch (Exception e) {
            try {
                codes = (Collection) DAOCallerAgent.callMethod(MarketDataSourceUtils.class, MarketDataSourceUtils.GET_MARKET_CODES, getProduct().getProductId());
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
        ProductMarketDataCodesPanel productCodesPanel = new ProductMarketDataCodesPanel(codes);
        NotifyDescriptor notifyDescriptor = new NotifyDescriptor(productCodesPanel, "Product Market Codes", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(notifyDescriptor) == NotifyDescriptor.OK_OPTION) {
            getProduct().setMarketDataCodes(productCodesPanel.getMarketcodes());
        }
    }//GEN-LAST:event_jToggleShowMarketCodesActionPerformed

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       /**
        * save as new
        */
        jLabelProductId.setText(StringUtils.EMPTY_STRING);
        setProduct(null);
        fillProduct();
        storeProduct();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonClose;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonLoadUnderlying;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonShowReferences;
    private javax.swing.JComboBox jComboBoxCountry;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxIssuer;
    private javax.swing.JComboBox jComboBoxPortfolio;
    private javax.swing.JComboBox jComboBoxQuotationType;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JFormattedTextField jFormattedTextFieldIssuedShare;
    private javax.swing.JFormattedTextField jFormattedTextFieldLotSize;
    private javax.swing.JFormattedTextField jFormattedTextFieldSettlementDelay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelProductId;
    private javax.swing.JLabel jLabelUnderlyingId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldLongName;
    private javax.swing.JTextField jTextFieldShortName;
    private javax.swing.JTextField jTextFieldUnderlying;
    private javax.swing.JToggleButton jToggleShowMarketCodes;
    private org.jdesktop.swingx.JXButton loadEventXButton;
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

    /**
     * information of product
     */
    @Override
    public void initContext() {

        try {
            setProductReferences(new ArrayList());

            List<ProductTypeUtil.ProductType> productTypes = ProductTypeUtil.loadFunds();
            if (productTypes != null) {
                for (ProductTypeUtil.ProductType type : productTypes) {
                    jComboBoxType.addItem(type.name);
                }
            }

            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            String defaultCurrency = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY,LoggedUser.getLoggedUserId());
            jComboBoxCurrency.setSelectedItem(defaultCurrency);

            List<String> quotationTypes = MarketQuoteAccessor.getQuoteTypes();
            GUIUtils.fillCombo(jComboBoxQuotationType, quotationTypes);
            jComboBoxQuotationType.setSelectedItem(MarketQuote.QuotationType.PRICE.getName());

            List<String> issuers = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_FUNDS);
            GUIUtils.fillComboWithNullFirst(jComboBoxPortfolio, issuers);

            List<String> countries = (List) DAOCallerAgent.callMethod(CountryAccessor.class, CountryAccessor.LOAD_COUNTRY_NAMES);
            GUIUtils.fillComboWithNullFirst(jComboBoxCountry, countries);

            List<String> portfolios = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_FUNDS);
            GUIUtils.fillComboWithNullFirst(jComboBoxPortfolio, portfolios);

            List<String> managers = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITIES);
            GUIUtils.fillComboWithNullFirst(jComboBoxIssuer, managers);



        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }
}
