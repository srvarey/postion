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

import com.toedter.calendar.JSpinnerDateEditor;
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
import org.gaia.dao.referentials.SectorAccessor;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.Country;
import org.gaia.domain.referentials.Sector;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEquity;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaProductTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.common.ProductMarketDataCodesPanel;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
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
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//Equity//EN", autostore = false)
@TopComponent.Description(preferredID = "EquityTopComponent",iconBase="org/gaia/gui/assets/Stocks.png", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.EquityTopComponent")
@ActionReference(path = "Menu"+MenuManager.EquityIndexTopComponentMenu, position = MenuManager.EquityTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EquityAction")
@Messages({"CTL_EquityAction=Equity", "CTL_EquityTopComponent=Equity", "HINT_EquityTopComponent=This is a Equity window"})
public final class EquityTopComponent extends GaiaProductTopComponent {

    private static final Logger logger = Logger.getLogger(EquityTopComponent.class);
    private AssetFinder assetFinder = null;

    public EquityTopComponent() {
        initComponents();
        setName(Bundle.CTL_EquityTopComponent());
        setToolTipText(Bundle.HINT_EquityTopComponent());
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
        jComboBoxIssuer = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jComboBoxCountry = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextFieldUnderlying = new javax.swing.JTextField();
        jButtonLoadUnderlying = new javax.swing.JButton();
        jLabelUnderlyingId = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jFormattedTextFieldStrike = new javax.swing.JFormattedTextField(new DecimalFormat("### ### ##0.00"));
        jLabel17 = new javax.swing.JLabel();
        jFormattedTextFieldParity = new javax.swing.JFormattedTextField(new DecimalFormat("## ###"));
        jComboBoxCallPut = new javax.swing.JComboBox();
        jLabel20 = new javax.swing.JLabel();
        jComboBoxExerciseType = new javax.swing.JComboBox();
        jdateChooserMaturity = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
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
        jButton1 = new javax.swing.JButton();
        sectorsComboBox = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel1.text")); // NOI18N

        jTextFieldShortName.setName("jTextFieldShortName"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel3.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel4.text")); // NOI18N

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel5.text")); // NOI18N

        jComboBoxQuotationType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuotationType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel6.text")); // NOI18N

        jFormattedTextFieldLotSize.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel7.text")); // NOI18N

        jFormattedTextFieldSettlementDelay.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel8.text")); // NOI18N

        jFormattedTextFieldIssuedShare.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxIssuer.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxIssuer.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel9.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel10, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel10.text")); // NOI18N

        jComboBoxCountry.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jPanel2.setBackground(new java.awt.Color(235, 235, 254));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel11.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel13.text")); // NOI18N

        jTextFieldUnderlying.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButtonLoadUnderlying.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadUnderlying, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonLoadUnderlying.text")); // NOI18N
        jButtonLoadUnderlying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadUnderlyingActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel15.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel16.text")); // NOI18N

        jFormattedTextFieldStrike.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel17.text")); // NOI18N

        jFormattedTextFieldParity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxCallPut.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCallPut.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","Call","Put" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel20.text")); // NOI18N

        jComboBoxExerciseType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxExerciseType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "","European", "American", "Bermuda" }));

        jdateChooserMaturity.setBackground(new java.awt.Color(235, 235, 254));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldStrike, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel20)
                        .addGap(33, 74, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUnderlying)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jdateChooserMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextFieldParity))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCallPut, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonLoadUnderlying, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jComboBoxExerciseType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonLoadUnderlying)
                            .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabel17)
                            .addComponent(jFormattedTextFieldParity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jdateChooserMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(jFormattedTextFieldStrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCallPut)
                        .addComponent(jLabel20)
                        .addComponent(jComboBoxExerciseType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 19, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel12.text")); // NOI18N

        jButtonShowReferences.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowReferences, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonShowReferences.text")); // NOI18N
        jButtonShowReferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowReferencesActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel19.text")); // NOI18N

        jButtonClose.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonClose, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonClose.text")); // NOI18N
        jButtonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCloseActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonExport.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel14.text")); // NOI18N

        jToggleShowMarketCodes.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jToggleShowMarketCodes, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jToggleShowMarketCodes.text")); // NOI18N
        jToggleShowMarketCodes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleShowMarketCodesActionPerformed(evt);
            }
        });

        loadEventXButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        sectorsComboBox.setBackground(new java.awt.Color(255, 255, 255));
        sectorsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(EquityTopComponent.class, "EquityTopComponent.jLabel18.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonExport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jButtonLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonClose))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addGap(64, 64, 64)
                                    .addComponent(jButtonShowReferences)
                                    .addGap(87, 87, 87))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jComboBoxQuotationType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldLongName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(54, 54, 54)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jFormattedTextFieldSettlementDelay, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addGap(1, 1, 1)
                                            .addComponent(jLabel14)
                                            .addGap(24, 24, 24))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel18))
                                            .addGap(53, 53, 53)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBoxIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jToggleShowMarketCodes, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(sectorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel6))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jFormattedTextFieldLotSize, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jFormattedTextFieldIssuedShare, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(10, Short.MAX_VALUE))
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
                    .addComponent(jComboBoxIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxQuotationType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sectorsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jButtonShowReferences))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(jToggleShowMarketCodes)))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelProductId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonExport)
                                .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonSave)
                        .addComponent(jButtonClose)
                        .addComponent(jButtonLoad)
                        .addComponent(jButtonNew)
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                getProduct().getUnderlyingProducts().clear();
                getProduct().addUnderlying(underlying);
                jTextFieldUnderlying.setText(underlying.getShortName());
                jLabelUnderlyingId.setText(underlying.getProductId().toString());
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
            if (jdateChooserMaturity.getDate() != null) {
                getProduct().setMaturityDate(jdateChooserMaturity.getDate());
            } else {
                getProduct().setMaturityDate(null);
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
            if (!jFormattedTextFieldStrike.getText().isEmpty()) {
                equity.setStrike(Double.valueOf(jFormattedTextFieldStrike.getText()));
            } else {
                equity.setStrike(null);
            }
            if (!jFormattedTextFieldParity.getText().isEmpty()) {
                equity.setParity(Short.parseShort(jFormattedTextFieldParity.getText()));
            } else {
                equity.setParity(null);
            }
            if (jComboBoxCallPut.getSelectedItem() != null && !jComboBoxCallPut.getSelectedItem().toString().isEmpty()) {
                equity.setOptionStyle(jComboBoxCallPut.getSelectedItem().toString());
            } else {
                equity.setOptionStyle(null);
            }
            if (jComboBoxExerciseType.getSelectedItem() != null && !jComboBoxExerciseType.getSelectedItem().toString().isEmpty()) {
                equity.setExerciseType(jComboBoxExerciseType.getSelectedItem().toString());
            } else {
                equity.setExerciseType(null);
            }
            if (sectorsComboBox.getSelectedItem() != null && !sectorsComboBox.getSelectedItem().toString().isEmpty()) {
                Sector sector = (Sector)DAOCallerAgent.callMethod(SectorAccessor.class
                        , SectorAccessor.GET_GICS_SECTOR_BY_NAME,sectorsComboBox.getSelectedItem().toString());
                equity.setSector(sector);
            } else {
                equity.setSector(null);
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
        assetFinder = new AssetFinder(ProductTypeUtil.loadEquities());
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
                if (getProduct().getMaturityDate() != null) {
                    jdateChooserMaturity.setDate(getProduct().getMaturityDate());
                } else {
                    jdateChooserMaturity.setDate(null);
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
                    if (getProduct().getProductEquity().getCountry() != null) {
                        jComboBoxCountry.setSelectedItem(getProduct().getProductEquity().getCountry().getName());
                    } else {
                        jComboBoxCountry.setSelectedItem(null);
                    }
                    if (getProduct().getProductEquity().getStrike() != null) {
                        jFormattedTextFieldStrike.setText(getProduct().getProductEquity().getStrike().toString());
                    } else {
                        jFormattedTextFieldStrike.setText(StringUtils.EMPTY_STRING);
                    }
                    if (getProduct().getProductEquity().getParity() != null) {
                        jFormattedTextFieldParity.setText(getProduct().getProductEquity().getParity().toString());
                    } else {
                        jFormattedTextFieldParity.setText(StringUtils.EMPTY_STRING);
                    }
                    if (getProduct().getProductEquity().getOptionStyle() != null) {
                        jComboBoxCallPut.setSelectedItem(getProduct().getProductEquity().getOptionStyle());
                    } else {
                        jComboBoxCallPut.setSelectedItem(null);
                    }
                    if (getProduct().getProductEquity().getExerciseType() != null) {
                        jComboBoxExerciseType.setSelectedItem(getProduct().getProductEquity().getExerciseType());
                    } else {
                        jComboBoxExerciseType.setSelectedItem(null);
                    }
                    if (getProduct().getProductEquity().getSector() != null) {
                        Sector sector=(Sector) DAOCallerAgent.callMethod(SectorAccessor.class,SectorAccessor.GET_PRODUCT_SECTOR,product.getProductId());
                        if (sector!=null){
                            sectorsComboBox.setSelectedItem(sector.getSectorName());
                        } else {
                            sectorsComboBox.setSelectedItem(null);
                        }
                    } else {
                        sectorsComboBox.setSelectedItem(null);
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
    private javax.swing.JComboBox jComboBoxCallPut;
    private javax.swing.JComboBox jComboBoxCountry;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxExerciseType;
    private javax.swing.JComboBox jComboBoxIssuer;
    private javax.swing.JComboBox jComboBoxQuotationType;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JFormattedTextField jFormattedTextFieldIssuedShare;
    private javax.swing.JFormattedTextField jFormattedTextFieldLotSize;
    private javax.swing.JFormattedTextField jFormattedTextFieldParity;
    private javax.swing.JFormattedTextField jFormattedTextFieldSettlementDelay;
    private javax.swing.JFormattedTextField jFormattedTextFieldStrike;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextFieldLongName;
    private javax.swing.JTextField jTextFieldShortName;
    private javax.swing.JTextField jTextFieldUnderlying;
    private javax.swing.JToggleButton jToggleShowMarketCodes;
    private com.toedter.calendar.JDateChooser jdateChooserMaturity;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private javax.swing.JComboBox sectorsComboBox;
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

            List<ProductTypeUtil.ProductType> productTypes = ProductTypeUtil.loadEquities();
            if (productTypes != null) {
                for (ProductTypeUtil.ProductType type : productTypes) {
                    jComboBoxType.addItem(type.name);
                }
            }
            jComboBoxType.setSelectedItem(ProductTypeUtil.ProductType.STOCK.getName());

            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            String defaultCurrency = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY,LoggedUser.getLoggedUserId());
            jComboBoxCurrency.setSelectedItem(defaultCurrency);

            List<String> quotationTypes = MarketQuoteAccessor.getQuoteTypes();
            GUIUtils.fillCombo(jComboBoxQuotationType, quotationTypes);
            jComboBoxQuotationType.setSelectedItem(MarketQuote.QuotationType.PRICE.getName());

            List<String> issuers = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ISSUERS);
            GUIUtils.fillComboWithNullFirst(jComboBoxIssuer, issuers);

            List<String> countries = (List) DAOCallerAgent.callMethod(CountryAccessor.class, CountryAccessor.LOAD_COUNTRY_NAMES);
            GUIUtils.fillComboWithNullFirst(jComboBoxCountry, countries);

            List<String> sectors = (List) DAOCallerAgent.callMethod(SectorAccessor.class, SectorAccessor.GET_GICS_SECTOR_NAMES);
            GUIUtils.fillComboWithNullFirst(sectorsComboBox, sectors);

            DateShortCut.eventkey((JSpinnerDateEditor) jdateChooserMaturity.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }
}
