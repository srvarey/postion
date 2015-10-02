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
package org.gaia.gui.trades;

/**
 *
 * @author Jawhar Kamoun
 */
import com.sun.glass.events.KeyEvent;
import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.events.ContractEventApply;
import org.gaia.dao.trades.events.ContractEventApply.ContractEventType;
import org.gaia.dao.trades.events.EventEngine;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays a contract event.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.common//ContractEvent//EN",autostore = false)
@TopComponent.Description(preferredID = "ContractEventTopComponent",persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.common.ContractEventTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_ContractEventAction",preferredID = "ContractEventTopComponent")
@Messages({"CTL_ContractEventAction=Contract Event","CTL_ContractEventTopComponent=Contract Event Window","HINT_ContractEventTopComponent=This is a Contract Event window"})


public final class ContractEventTopComponent extends GaiaTopComponent {

    Trade trade;
    private static final Logger logger = Logger.getLogger(ContractEventTopComponent.class);
    private ContractEvent event = new ContractEvent();
    private BigDecimal tradeQuantity;
    private BigDecimal price;

    public ContractEventTopComponent() {
        initComponents();
        setName(Bundle.CTL_ContractEventTopComponent());
        setToolTipText(Bundle.HINT_ContractEventTopComponent());
        putClientProperty(TopComponent.PROP_MAXIMIZATION_DISABLED, Boolean.TRUE);
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
        tradeQuantity=trade.getQuantity();
        event.setProduct(trade.getProduct());
        titleLabel.setText(trade.getProduct().getProductType() + StringUtils.SPACE + trade.getId());
        clearFields(this);
        String pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        Map<String, BigDecimal> measures = (Map<String, BigDecimal>) DAOCallerAgent.callMethod(PricingBuilder.class,
                PricingBuilder.GET_TRADE_PRICING, trade, DateUtils.getDate(), pricingEnv, null,null);
        if (measures!=null){
            price = measures.get(MeasuresAccessor.Measure.NPV_unit.name());
        }
        unwindAmountFormattedTextField.setText(decimalFormat.format(trade.getQuantity()));
        currencyComboBox.setSelectedItem(trade.getProduct().getNotionalCurrency());
        refreshPaymentAmount();
    }

    @Override
    public void initContext() {

        contractEventComboBox.removeAllItems();
        for (ContractEventApply.ContractEventType type : ContractEventApply.ContractEventType.values()){
            contractEventComboBox.addItem(type.name());
        }
        List<String> currencies = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCIES,LoggedUser.getLoggedUserId());
        GUIUtils.fillCombo(currencyComboBox, currencies);
        GUIUtils.fillCombo(amountCurrencyComboBox, currencies);


         /**
        * ShortCut for Amout and date
        */
        AmountShortCut.eventkey(unwindAmountFormattedTextField);
        AmountShortCut.eventkey(paymentAmountTextField);
        DateShortCut.eventkey((JSpinnerDateEditor) negociationDateChooser.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) valueDateChooser.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) paymentDateChooser.getComponent(1));


    }

    private boolean fillEvent() {
        boolean ok=true;
        BigDecimal unwind=GUIUtils.getComponentBigDecimalValue(unwindAmountFormattedTextField);
        if (unwind.abs().doubleValue()>tradeQuantity.abs().doubleValue()){
            JOptionPane.showMessageDialog(this, "The amount must be less than the trade amount ("+decimalFormat.format(tradeQuantity)+")");
            ok=false;
        } else if (trade.getProduct().getMaturityDate()!=null && negociationDateChooser.getDate().after(trade.getProduct().getMaturityDate())){
            JOptionPane.showMessageDialog(this, "The event date must be before maturity");
            ok=false;
        } else {

            if (unwind.doubleValue()==tradeQuantity.doubleValue()){
                 event.setContractEvent(ContractEventType.Termination.name());
            }
            event.setUnwindAmount(unwind);
            event.setContractEvent(GUIUtils.getComponentStringValue(contractEventComboBox));
            event.setNegociationDate(negociationDateChooser.getDate());
            event.setPaymentAmount(GUIUtils.getComponentBigDecimalValue(paymentAmountTextField));
            event.setPaymentCurrency(GUIUtils.getComponentStringValue(currencyComboBox));
            event.setPaymentDate(paymentDateChooser.getDate());
            BigDecimal factor=unwind.divide(tradeQuantity, 20, RoundingMode.UP);
            event.setUnwindFactor(factor);
            event.setValueDate(valueDateChooser.getDate());

        }
        return ok;
    }

    public void refreshFields(){
        if (contractEventComboBox.getSelectedItem()!=null){
            String event=contractEventComboBox.getSelectedItem().toString();
            if (event.equalsIgnoreCase(ContractEventType.Unwind.name())){
                counterpartyLabel.setVisible(false);
                cptyTextField.setVisible(false);
                cptyFindButton.setVisible(false);
                unwindAmountLabel.setVisible(true);
                unwindAmountFormattedTextField.setVisible(true);
            } else if (event.equalsIgnoreCase(ContractEventType.Termination.name())){
                counterpartyLabel.setVisible(false);
                cptyTextField.setVisible(false);
                cptyFindButton.setVisible(false);
                unwindAmountLabel.setVisible(false);
                unwindAmountFormattedTextField.setVisible(false);
                unwindAmountFormattedTextField.setText(decimalFormat.format(tradeQuantity));
            } else if (event.equalsIgnoreCase(ContractEventType.NovationTransfer.name())
                    || event.equalsIgnoreCase(ContractEventType.NovationUnwind.name())){
                counterpartyLabel.setVisible(true);
                cptyTextField.setVisible(true);
                cptyFindButton.setVisible(true);
                unwindAmountLabel.setVisible(true);
                unwindAmountFormattedTextField.setVisible(true);
            } else if (event.equalsIgnoreCase(ContractEventType.NovationTermination.name())){
                counterpartyLabel.setVisible(true);
                cptyTextField.setVisible(true);
                cptyFindButton.setVisible(true);
                unwindAmountLabel.setVisible(false);
                unwindAmountFormattedTextField.setVisible(false);
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
        titleLabel = new org.jdesktop.swingx.JXLabel();
        contractEventLabel = new org.jdesktop.swingx.JXLabel();
        contractEventComboBox = new javax.swing.JComboBox();
        unwindAmountLabel = new org.jdesktop.swingx.JXLabel();
        unwindAmountFormattedTextField = new javax.swing.JFormattedTextField(decimalFormatWithOptionalDecimals);
        negociationDateLabel = new org.jdesktop.swingx.JXLabel();
        negociationDateChooser = new com.toedter.calendar.JDateChooser(null,DateUtils.getDate(),dateFormat,new GaiaJSpinnerDateEditor());
        valueDateLabel = new org.jdesktop.swingx.JXLabel();
        valueDateChooser = new com.toedter.calendar.JDateChooser(null,DateUtils.getDate(),dateFormat,new GaiaJSpinnerDateEditor());
        paymentAmountLabel = new org.jdesktop.swingx.JXLabel();
        paymentAmountTextField = new javax.swing.JFormattedTextField(decimalFormatWithOptionalDecimals);
        currencyComboBox = new javax.swing.JComboBox();
        paymentDateLabel = new org.jdesktop.swingx.JXLabel();
        paymentDateChooser = new com.toedter.calendar.JDateChooser(null ,DateUtils.getDate(),dateFormat,new GaiaJSpinnerDateEditor());
        counterpartyLabel = new org.jdesktop.swingx.JXLabel();
        cptyTextField = new javax.swing.JTextField();
        cptyFindButton = new org.jdesktop.swingx.JXButton();
        saveButton = new javax.swing.JButton();
        amountCurrencyComboBox = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        titleLabel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.titleLabel.text")); // NOI18N
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(contractEventLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.contractEventLabel.text")); // NOI18N

        contractEventComboBox.setBackground(new java.awt.Color(255, 255, 255));
        contractEventComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                contractEventComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(unwindAmountLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.unwindAmountLabel.text")); // NOI18N

        unwindAmountFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        unwindAmountFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        unwindAmountFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                unwindAmountFormattedTextFieldFocusLost(evt);
            }
        });
        unwindAmountFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unwindAmountFormattedTextFieldKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(negociationDateLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.negociationDateLabel.text")); // NOI18N

        negociationDateChooser.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(valueDateLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.valueDateLabel.text")); // NOI18N

        valueDateChooser.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(paymentAmountLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.paymentAmountLabel.text")); // NOI18N

        paymentAmountTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter()));
        paymentAmountTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        currencyComboBox.setBackground(new java.awt.Color(255, 255, 255));

        org.openide.awt.Mnemonics.setLocalizedText(paymentDateLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.paymentDateLabel.text")); // NOI18N

        paymentDateChooser.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(counterpartyLabel, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.counterpartyLabel.text")); // NOI18N

        cptyTextField.setText(org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.cptyTextField.text")); // NOI18N

        cptyFindButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(cptyFindButton, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.cptyFindButton.text")); // NOI18N
        cptyFindButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cptyFindButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(ContractEventTopComponent.class, "ContractEventTopComponent.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        amountCurrencyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        amountCurrencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        amountCurrencyComboBox.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(contractEventLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(negociationDateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valueDateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(unwindAmountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentAmountLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentDateLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(counterpartyLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(contractEventComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(unwindAmountFormattedTextField)
                            .addComponent(negociationDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valueDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(paymentAmountTextField)
                            .addComponent(cptyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cptyFindButton, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amountCurrencyComboBox, 0, 65, Short.MAX_VALUE)
                            .addComponent(currencyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contractEventLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contractEventComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unwindAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unwindAmountFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountCurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(negociationDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(negociationDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(valueDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valueDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(paymentAmountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentAmountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(paymentDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(counterpartyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cptyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cptyFindButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(saveButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cptyFindButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cptyFindButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            cptyTextField.setText(legalEntity.getShortName());
            event.setCounterparty(legalEntity);
        }
    }//GEN-LAST:event_cptyFindButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        boolean ok=fillEvent();
        if (ok){ //  CLASSICAL OTC
            if (ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType()).getUse().name().equalsIgnoreCase(ProductTypeUtil.ProductTypeUse.OTC.name())) {
                int eventId = (Integer) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.SAVE_CONTRACT_EVENT, event);

                String ids = (String) DAOCallerAgent.callMethod(EventEngine.class, EventEngine.APPLY_EVENT_ON_PRODUCT,
                        ContractEventApply.class, eventId, trade.getProduct().getId());
                JOptionPane.showMessageDialog(this, "Events applied on trade " + ids);
                // reload trade
                GaiaTradeTopComponent.refreshTradeWindowByProduct(trade.getProduct().getId());
            } else {
                String ids = (String)DAOCallerAgent.callMethod(ContractEventApply.class, ContractEventApply.APPLY_ON_CLEARED_TRADE,trade, event);
                JOptionPane.showMessageDialog(this, "Events applied on trade " + ids);
                GaiaTradeTopComponent.refreshTradeWindowByProduct(trade.getProduct().getId());
            }
        }

    }//GEN-LAST:event_saveButtonActionPerformed

    private void contractEventComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_contractEventComboBoxActionPerformed
        refreshFields();
    }//GEN-LAST:event_contractEventComboBoxActionPerformed

    private void unwindAmountFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unwindAmountFormattedTextFieldFocusLost
         refreshPaymentAmount();
    }//GEN-LAST:event_unwindAmountFormattedTextFieldFocusLost

    private void unwindAmountFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unwindAmountFormattedTextFieldKeyPressed

        if (evt.getKeyCode()==KeyEvent.VK_ENTER){
            refreshPaymentAmount();
        }
    }//GEN-LAST:event_unwindAmountFormattedTextFieldKeyPressed

    public void refreshPaymentAmount(){
           try {
            if (price!=null && !unwindAmountFormattedTextField.getText().isEmpty()){
                BigDecimal amount=GUIUtils.getComponentBigDecimalValue(unwindAmountFormattedTextField);
                paymentAmountTextField.setText(decimalFormat.format(-1*amount.doubleValue()*price.doubleValue()));
            }
        } catch (Exception e){
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox amountCurrencyComboBox;
    private javax.swing.JComboBox contractEventComboBox;
    private org.jdesktop.swingx.JXLabel contractEventLabel;
    private org.jdesktop.swingx.JXLabel counterpartyLabel;
    private org.jdesktop.swingx.JXButton cptyFindButton;
    private javax.swing.JTextField cptyTextField;
    private javax.swing.JComboBox currencyComboBox;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser negociationDateChooser;
    private org.jdesktop.swingx.JXLabel negociationDateLabel;
    private org.jdesktop.swingx.JXLabel paymentAmountLabel;
    private javax.swing.JFormattedTextField paymentAmountTextField;
    private com.toedter.calendar.JDateChooser paymentDateChooser;
    private org.jdesktop.swingx.JXLabel paymentDateLabel;
    private javax.swing.JButton saveButton;
    private org.jdesktop.swingx.JXLabel titleLabel;
    private javax.swing.JFormattedTextField unwindAmountFormattedTextField;
    private org.jdesktop.swingx.JXLabel unwindAmountLabel;
    private com.toedter.calendar.JDateChooser valueDateChooser;
    private org.jdesktop.swingx.JXLabel valueDateLabel;
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
