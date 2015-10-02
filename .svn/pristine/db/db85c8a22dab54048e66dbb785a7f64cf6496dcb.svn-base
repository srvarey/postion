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

/**
 *
 * @author Jawhar Kamoun
 */
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.CountryAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays currencies.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.referentials//Currency//EN", autostore = false)
@TopComponent.Description(preferredID = "CurrencyTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.referentials.CurrencyTopComponent")
@ActionReference(path = "Menu" + MenuManager.CurrencyTopComponentMenu, position = MenuManager.CurrencyTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CurrencyAction", preferredID = "CurrencyTopComponent")
@Messages({"CTL_CurrencyAction=Currency", "CTL_CurrencyTopComponent=Currencies", "HINT_CurrencyTopComponent=This is a Currency window"})
public final class CurrencyTopComponent extends GaiaTopComponent {

    private Currency selectedCurrency = null;
    private static final Logger logger = Logger.getLogger(CalendarTopComponent.class);

    public CurrencyTopComponent() {
        initComponents();
        setName(Bundle.CTL_CurrencyTopComponent());
        setToolTipText(Bundle.HINT_CurrencyTopComponent());
    }

    @Override
    public void initContext() {

        try {
            List< String> countries = (List<String>) DAOCallerAgent.callMethod(CountryAccessor.class, CountryAccessor.LOAD_COUNTRY_NAMES);
            GUIUtils.fillComboWithNullFirst(jComboBoxCountry, countries);

            List< String> calendars = (List<String>) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.LOAD_CALENDAR_CODES);
            GUIUtils.fillComboWithNullFirst(jComboBoxCalendar, calendars);

            List< String> daycounts = (List<String>) DAOCallerAgent.callMethod(DayCountAccessor.class, DayCountAccessor.GET_DAYCOUNTS);
            GUIUtils.fillComboWithNullFirst(jComboBoxDefaultDayCount, daycounts);

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ccyCodeLabel = new javax.swing.JLabel();
        ccyNameLabel = new javax.swing.JLabel();
        countryLabel = new javax.swing.JLabel();
        isDeliverableLabel = new javax.swing.JLabel();
        jTextFieldCurrency = new javax.swing.JTextField();
        jTextFieldCurrencyName = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        roundingLabel = new javax.swing.JLabel();
        jFormattedTextFieldRounding = new javax.swing.JFormattedTextField();
        roundingTypeLabel = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jComboBoxCountry = new javax.swing.JComboBox();
        jComboBoxRoundingType = new javax.swing.JComboBox();
        defaultCalendarLabel = new javax.swing.JLabel();
        jComboBoxCalendar = new javax.swing.JComboBox();
        dayCountLabel = new javax.swing.JLabel();
        jComboBoxDefaultDayCount = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCurrencies = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jButtonQuery = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        jPanel1.setBackground(new java.awt.Color(230, 230, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setPreferredSize(new java.awt.Dimension(503, 500));

        org.openide.awt.Mnemonics.setLocalizedText(ccyCodeLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.ccyCodeLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ccyNameLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.ccyNameLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(countryLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.countryLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(isDeliverableLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.isDeliverableLabel.text")); // NOI18N

        jTextFieldCurrency.setFocusTraversalPolicyProvider(true);
        jTextFieldCurrency.setName("jTextFieldCurrency"); // NOI18N

        jCheckBox1.setBackground(new java.awt.Color(230, 230, 253));

        org.openide.awt.Mnemonics.setLocalizedText(roundingLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.roundingLabel.text")); // NOI18N

        jFormattedTextFieldRounding.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldRounding.setText(org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.jFormattedTextFieldRounding.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(roundingTypeLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.roundingTypeLabel.text")); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jComboBoxCountry.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCountry.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxRoundingType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxRoundingType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NEAREST", "UP", "DOWN" }));

        org.openide.awt.Mnemonics.setLocalizedText(defaultCalendarLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.defaultCalendarLabel.text")); // NOI18N

        jComboBoxCalendar.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCalendar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(dayCountLabel, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.dayCountLabel.text")); // NOI18N

        jComboBoxDefaultDayCount.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDefaultDayCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(roundingTypeLabel)
                                        .addComponent(defaultCalendarLabel)
                                        .addComponent(roundingLabel))
                                    .addGap(36, 36, 36))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonNew)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jFormattedTextFieldRounding, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonSave)
                                    .addGap(27, 27, 27)
                                    .addComponent(jButtonDelete))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBoxDefaultDayCount, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxRoundingType, javax.swing.GroupLayout.Alignment.LEADING, 0, 83, Short.MAX_VALUE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(ccyNameLabel)
                                .addComponent(ccyCodeLabel)
                                .addComponent(countryLabel)
                                .addComponent(isDeliverableLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox1)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldCurrencyName)
                                    .addComponent(jComboBoxCountry, 0, 147, Short.MAX_VALUE)))))
                    .addComponent(dayCountLabel))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccyCodeLabel)
                    .addComponent(jTextFieldCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldCurrencyName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccyNameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCountry, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(countryLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isDeliverableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(defaultCalendarLabel))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundingLabel)
                    .addComponent(jFormattedTextFieldRounding, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(roundingTypeLabel)
                    .addComponent(jComboBoxRoundingType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dayCountLabel)
                    .addComponent(jComboBoxDefaultDayCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTableCurrencies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {"Code", "Name", "Country", "Non deliverable"
            }
        ));
        jTableCurrencies.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableCurrencies.getTableHeader().setReorderingAllowed(false);
        jTableCurrencies.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCurrenciesMouseClicked(evt);
            }
        });
        jTableCurrencies.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableCurrenciesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCurrencies);

        jButtonQuery.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonQuery, org.openide.util.NbBundle.getMessage(CurrencyTopComponent.class, "CurrencyTopComponent.jButtonQuery.text")); // NOI18N
        jButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQueryActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonQuery)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 392, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonQuery)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * delete currency
     *
     * @param evt
     */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            String currencyCode = jTextFieldCurrency.getText();
            if (StringUtils.isEmptyString(currencyCode)) {
                return;
            }
            DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.DELETE_CURRENCY, currencyCode);

            displayTable();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * store data
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        String currencyCode = GUIUtils.getComponentStringValue(jTextFieldCurrency);
        if (!StringUtils.isEmptyString(currencyCode) && currencyCode.length() == 3) {
            try {
                if (selectedCurrency == null) {
                    selectedCurrency = new Currency();
                }
                if (jTextFieldCurrency.getText().length() != 3) {
                    JOptionPane.showMessageDialog(this, "The ISO code must be 3 characters", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                selectedCurrency.setCode(jTextFieldCurrency.getText());
                selectedCurrency.setName(jTextFieldCurrencyName.getText());
                if (NumberUtils.isInteger(jFormattedTextFieldRounding.getText())) {
                    selectedCurrency.setRounding(new Integer(jFormattedTextFieldRounding.getText()));
                }
                if (jComboBoxCountry.getSelectedItem() != null) {
                    selectedCurrency.setCountry(jComboBoxCountry.getSelectedItem().toString());
                }
                selectedCurrency.setRoundingType(jComboBoxRoundingType.getSelectedItem().toString());
                if (jComboBoxDefaultDayCount.getSelectedItem() != null && !jComboBoxDefaultDayCount.getSelectedItem().toString().isEmpty()) {
                    selectedCurrency.setDaycount(jComboBoxDefaultDayCount.getSelectedItem().toString());
                }
                if (jComboBoxCalendar.getSelectedItem() != null) {
                    HolidayCalendar calendar = (HolidayCalendar) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.GET_CALENDAR_BY_CODE, jComboBoxCalendar.getSelectedItem().toString());
                    selectedCurrency.setCalendar(calendar);
                }

                Product currencyProduct = selectedCurrency.getCurrencyProduct();
                if (currencyProduct == null) {
                    currencyProduct = new Product();
                    currencyProduct.setProductType(ProductTypeUtil.ProductType.CASH.name);
                }
                currencyProduct.setNotionalCurrency(currencyCode);
                currencyProduct.setShortName(selectedCurrency.getCode());
                currencyProduct.setLongName(selectedCurrency.getName());
                currencyProduct.setIsAsset(true);
                currencyProduct.setNotionalMultiplier(new BigDecimal(Double.parseDouble("1")));
                currencyProduct.setStatus(ProductConst.ProductStatus.Active.name());
                currencyProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, currencyProduct);
                selectedCurrency.setCurrencyProduct(currencyProduct);

                DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.STORE_CURRENCY, selectedCurrency);

                displayTable();
            } catch (HeadlessException | NumberFormatException ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jTableCurrenciesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCurrenciesMouseClicked
        refereshData();
    }//GEN-LAST:event_jTableCurrenciesMouseClicked

    private void jTableCurrenciesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCurrenciesKeyPressed
        refereshData();
    }//GEN-LAST:event_jTableCurrenciesKeyPressed

    /**
     * load currencies
     *
     * @param evt
     */
    private void jButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueryActionPerformed
        displayTable();
    }//GEN-LAST:event_jButtonQueryActionPerformed

    /**
     * refresh data
     *
     */
    private void refereshData() {
        int rowNum = jTableCurrencies.getSelectedRow();
        if (rowNum >= 0) {
            String currencyCode = GUIUtils.getTableValueAt(jTableCurrencies, rowNum, 0);
            if (currencyCode != null) {
                try {
                    selectedCurrency = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE, currencyCode);
                    jTextFieldCurrency.setText(selectedCurrency.getCode());
                    jTextFieldCurrencyName.setText(selectedCurrency.getName());

                    if (selectedCurrency.getCountry() != null) {
                        jComboBoxCountry.setSelectedItem(selectedCurrency.getCountry());
                    } else {
                        jComboBoxCountry.setSelectedItem(null);
                    }
                    if (selectedCurrency.getRounding() != null) {
                        jFormattedTextFieldRounding.setText(selectedCurrency.getRounding().toString());
                    }
                    jComboBoxRoundingType.setSelectedItem(selectedCurrency.getRoundingType());
                    jCheckBox1.setEnabled(selectedCurrency.isNonDeliverable());
                    if (selectedCurrency.getCalendar() != null) {
                        jComboBoxCalendar.setSelectedItem(selectedCurrency.getCalendar().getCalendarCode());
                    } else {
                        jComboBoxCalendar.setSelectedItem(null);
                    }
                    if (selectedCurrency.getDaycount() != null) {
                        jComboBoxDefaultDayCount.setSelectedItem(selectedCurrency.getDaycount());
                    } else {
                        jComboBoxDefaultDayCount.setSelectedItem(StringUtils.EMPTY_STRING);
                    }

                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }

    /**
     * display table
     */
    private void displayTable() {
        try {
            List<Currency> currencies = (List<Currency>) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_ALL_CURRENCIES);

            DefaultTableModel tableModel = (DefaultTableModel) jTableCurrencies.getModel();
            GUIUtils.clearTableModel(tableModel);
            for (Currency _currency : currencies) {
                Object[] row = new Object[4];
                row[0] = _currency.getCode();
                row[1] = _currency.getName();
                if (_currency.getCountry() != null) {
                    row[2] = _currency.getCountry();
                } else {
                    row[2] = StringUtils.EMPTY_STRING;
                }
                row[3] = _currency.isNonDeliverable();
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ccyCodeLabel;
    private javax.swing.JLabel ccyNameLabel;
    private javax.swing.JLabel countryLabel;
    private javax.swing.JLabel dayCountLabel;
    private javax.swing.JLabel defaultCalendarLabel;
    private javax.swing.JLabel isDeliverableLabel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonQuery;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox jComboBoxCalendar;
    private javax.swing.JComboBox jComboBoxCountry;
    private javax.swing.JComboBox jComboBoxDefaultDayCount;
    private javax.swing.JComboBox jComboBoxRoundingType;
    private javax.swing.JFormattedTextField jFormattedTextFieldRounding;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCurrencies;
    private javax.swing.JTextField jTextFieldCurrency;
    private javax.swing.JTextField jTextFieldCurrencyName;
    private javax.swing.JLabel roundingLabel;
    private javax.swing.JLabel roundingTypeLabel;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}
