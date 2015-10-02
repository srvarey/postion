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

import com.toedter.calendar.JSpinnerDateEditor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeEntity;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaPanel;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;

/**
 *
 * @author Jawhar Kamoun
 */
public class FxGlobalInfoPanel extends GaiaPanel {

    /**
     * Creates new form FxGlobalInfoPanel
     */
    private Trade trade;
    private static final Logger logger = Logger.getLogger(FxGlobalInfoPanel.class);
    private FXBasePanel relatedFxPanel;

    public FxGlobalInfoPanel() {
        initComponents();
        tradeTypeComboBox.addItem(Trade.TradeType.FORWARD.name);
        tradeTypeComboBox.addItem(Trade.TradeType.BUY_SELL.name);

    }

    public void setRelatedFxPanel(FXBasePanel _relatedFxPanel) {
        relatedFxPanel = _relatedFxPanel;
    }

    public void initContext() {
        List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
        GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);
        List<String> statusList = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
        GUIUtils.fillCombo(jComboBoxStatus, statusList);
        jComboBoxStatus.setSelectedItem(TradeAccessor.TradeStatus.NEW);
        jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
        jSpinnerTradeDate.setDate(DateUtils.getDate());
        lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
        /**
         * ShortCut for Amout and date
         */
        DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));

    }

    private void fillTrade() {
        if (trade == null) {
            trade = new Trade();
        }
        LegalEntity entity = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty));
        trade.setInternalCounterparty(entity);
        entity = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                GUIUtils.getComponentStringValue(counterpartyTextField));
        trade.setCounterparty(entity);
        entity = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                GUIUtils.getComponentStringValue(brokerTextField));
        if (entity != null) {
            TradeEntity tradeEntity = new TradeEntity();
            tradeEntity.setRole(LegalEntityRole.LegalEntityRoleEnum.BROKER_ROLE.name);
            tradeEntity.setTrade(trade);
            tradeEntity.setLegalEntity(entity);
            Collection<TradeEntity> tradeEntityCollection = trade.getTradeEntityCollection();
            if (tradeEntityCollection == null) {
                tradeEntityCollection = new ArrayList<>();
            }
            tradeEntityCollection.add(tradeEntity);
            trade.setTradeEntityCollection(tradeEntityCollection);
        }

        trade.setStatus(GUIUtils.getComponentStringValue(jComboBoxStatus));
        trade.setTradeDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));
        trade.setTradeTime(GUIUtils.getComponentTimeValue(jTextFieldTradeTime));
        trade.setTradeType(GUIUtils.getComponentStringValue(tradeTypeComboBox));
        trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
        trade.setQuantityType(Trade.QuantityType.QUANTITY.name);
        trade.setLifeCycleStatus(lifeCycleStatusTextField.getText());
    }

    private void fillPanel() {
        jComboBoxInternalCounterparty.setSelectedItem(trade != null ? trade.getInternalCounterparty().getShortName() : StringUtils.EMPTY_STRING);
        counterpartyTextField.setText(trade != null ? trade.getCounterparty().getShortName() : StringUtils.EMPTY_STRING);
        jComboBoxStatus.setSelectedItem(trade != null ? trade.getStatus() : "New");
        jSpinnerTradeDate.setDate(trade != null ? trade.getTradeDate() : DateUtils.getDate());
        jTextFieldTradeTime.setText(trade != null ? timeFormatter.format(trade.getTradeTime()) : timeFormatter.format(new Date()));
        jTextFieldTradeId.setText(trade != null ? trade.getId().toString() : StringUtils.EMPTY_STRING);
        brokerTextField.setText(trade != null ? getBroker() : StringUtils.EMPTY_STRING);
        lifeCycleStatusTextField.setText(trade.getLifeCycleStatus());
    }

    private String getBroker() {
        String broker = null;
        if (trade.getTradeEntityCollection() != null) {
            for (TradeEntity entity : trade.getTradeEntityCollection()) {
                if (LegalEntityRole.LegalEntityRoleEnum.BROKER_ROLE.name.equalsIgnoreCase(entity.getRole())) {
                    return entity.getLegalEntity().getShortName();
                }
            }
        }
        return broker;
    }

    public Trade getTrade(boolean newtrade) {
        if (!newtrade) {
            if (trade == null) {
                JOptionPane.showMessageDialog(this, "Trade does not exist please save it before");
                return null;
            }
        } else {
            trade=null;
        }
        fillTrade();
        return trade;
    }

    public void setTrade(Trade _trade, boolean isFwd) {
        trade = _trade;
        if (relatedFxPanel != null) {
            relatedFxPanel.setTrade(trade);
        }
        if (isFwd) {
            tradeTypeComboBox.setSelectedItem(Trade.TradeType.FORWARD.name);
        } else {
            tradeTypeComboBox.setSelectedItem(Trade.TradeType.BUY_SELL.name);
        }
        fillPanel();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextFieldInternalTradeId = new javax.swing.JTextField();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabelTradeId = new javax.swing.JLabel();
        jButtonLoad = new javax.swing.JButton();
        jTextFieldTradeId = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jLabelBook = new javax.swing.JLabel();
        jLabelTradeDate = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        tradeTypeLabel = new javax.swing.JLabel();
        jLabelCounterparty = new javax.swing.JLabel();
        tradeTypeComboBox = new javax.swing.JComboBox();
        counterpartyTextField = new javax.swing.JTextField();
        counterpartyFinderButton = new javax.swing.JButton();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jLabelCounterparty1 = new javax.swing.JLabel();
        brokerTextField = new javax.swing.JTextField();
        brokerFinderButton = new javax.swing.JButton();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();

        setBackground(new java.awt.Color(254, 252, 254));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        setMaximumSize(new java.awt.Dimension(625, 285));
        setPreferredSize(new java.awt.Dimension(625, 285));

        jTextFieldInternalTradeId.setEditable(false);
        jTextFieldInternalTradeId.setText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jTextFieldInternalTradeId.text")); // NOI18N

        jComboBoxStatus.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeId, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelTradeId.text")); // NOI18N

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jTextFieldTradeId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStatus, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelStatus.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelBook.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelTradeDate.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jTextFieldTradeTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jTextFieldTradeTime.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(tradeTypeLabel, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.tradeTypeLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelCounterparty.text")); // NOI18N

        tradeTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        tradeTypeComboBox.setEnabled(false);

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.counterpartyTextField.text")); // NOI18N

        counterpartyFinderButton.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(counterpartyFinderButton, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.counterpartyFinderButton.text")); // NOI18N
        counterpartyFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.counterpartyFinderButton.toolTipText")); // NOI18N
        counterpartyFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                counterpartyFinderButtonActionPerformed(evt);
            }
        });

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty1, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.jLabelCounterparty1.text")); // NOI18N

        brokerTextField.setText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.brokerTextField.text")); // NOI18N

        brokerFinderButton.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(brokerFinderButton, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.brokerFinderButton.text")); // NOI18N
        brokerFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.brokerFinderButton.toolTipText")); // NOI18N
        brokerFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brokerFinderButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.lifeCycleStatusLabel.text")); // NOI18N

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(FxGlobalInfoPanel.class, "FxGlobalInfoPanel.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelTradeId)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tradeTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49)
                                .addComponent(tradeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelStatus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelCounterparty1)
                                        .addGap(112, 112, 112))
                                    .addComponent(jLabelTradeDate, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(156, 156, 156)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(brokerFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(counterpartyFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBook)
                                    .addComponent(jLabelCounterparty))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(brokerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelTradeId)
                        .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLoad)
                        .addComponent(jLabelTradeDate))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStatus)
                    .addComponent(jLabelBook))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(counterpartyFinderButton)
                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelCounterparty))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCounterparty1)
                    .addComponent(brokerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brokerFinderButton)
                    .addComponent(tradeTypeLabel)
                    .addComponent(tradeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        jTextFieldInternalTradeId.setVisible(false);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        loadTrade();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    /**
     * load Trade
     */
    public void loadTrade() {
        Integer tradeId;
        if (NumberUtils.isInteger(jTextFieldTradeId.getText())) {
            tradeId = Integer.parseInt(jTextFieldTradeId.getText());
        } else {
            List<ProductTypeUtil.ProductType> types=new ArrayList();
            if (this.getParent().getParent() instanceof FXSwapTopComponent){
                types.add(ProductTypeUtil.ProductType.FX_SWAP);
            }else{
            types.add(ProductTypeUtil.ProductType.CURRENCY_PAIR);
            }
            tradeId = GaiaTradeTopComponent.openTradeFinder(types);
        }
        if (tradeId != null) {
            try {
                trade = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId);
                setTrade(trade, Trade.TradeType.FORWARD.name.equalsIgnoreCase(trade.getTradeType()));

            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    private void counterpartyFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_counterpartyFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_counterpartyFinderButtonActionPerformed

    private void brokerFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brokerFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            brokerTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_brokerFinderButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brokerFinderButton;
    private javax.swing.JTextField brokerTextField;
    private javax.swing.JButton counterpartyFinderButton;
    private javax.swing.JTextField counterpartyTextField;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelCounterparty1;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JLabel jLabelTradeId;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    private javax.swing.JTextField jTextFieldInternalTradeId;
    protected javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    public javax.swing.JComboBox tradeTypeComboBox;
    private javax.swing.JLabel tradeTypeLabel;
    // End of variables declaration//GEN-END:variables
}
