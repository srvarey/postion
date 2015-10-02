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

import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeAttribute;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.GUIUtils;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Benjamin Frerejean
 */
public class TradeAttributesPanel extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(ProductReferencesPanel.class);
    private Set<TradeAttribute> tradeAttributes;
    private Trade trade;
    List<String> attributeNameList;
    DefaultTableModel tableModel;

    /**
     * Creates new form TradeAttributesPanel
     */
    public TradeAttributesPanel() {
        initComponents();
        attributeNameList = (List) DAOCallerAgent.callMethod(DomainValuesAccessor.class, DomainValuesAccessor.GET_DOMAIN_VALUES_BY_NAME, "tradeAttribute");
        tableModel = (DefaultTableModel) jTableTradeAttribute.getModel();
    }

    /**
     * add a TradeAttribute
     */
    public void setTradeAttribute(Trade _trade) {
        tradeAttributes = (Set<TradeAttribute>) _trade.getTradeAttributes();
        trade = _trade;
        if (tradeAttributes != null) {//|| !tradeAttribute.isEmpty()){
            displayTable();
        }
    }

    private void displayTable() {
        GUIUtils.clearTableModel(tableModel);
        String[] row = new String[2];
        for (String attribute : attributeNameList) {
            row[0] = attribute;
            row[1] = getAttributeValue(attribute);
            tableModel.addRow(row);
        }
    }

    private String getAttributeValue(String attribute) {
        String values = null;
        if (tradeAttributes != null) {
            try {
                for (TradeAttribute tradeAttribute1 : tradeAttributes) {
                    if (attribute.equalsIgnoreCase(tradeAttribute1.getAttributeName())) {
                        values = tradeAttribute1.getAttributeValue();
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return values;
    }

    private void updateAttributes() {
        for (int i = 0; i < tableModel.getDataVector().size(); i++) {
            Vector row = (Vector) tableModel.getDataVector().get(i);
            TradeAttribute attribute = null;
            for (TradeAttribute _attribute : tradeAttributes) {
                if (row.get(0).toString().equalsIgnoreCase(_attribute.getAttributeName())) {
                    attribute = _attribute;
                    break;
                }
            }
            if (attribute == null) {
                attribute = new TradeAttribute();
            }
            attribute.setAttributeName(row.get(0).toString());
            attribute.setAttributeValue(row.get(1) != null ? row.get(1).toString() : StringUtils.EMPTY_STRING);
            attribute.setTrade(trade);

            if (StringUtils.isEmptyString(attribute.getAttributeValue()) && attribute.getTradeAttibuteId() != null) {
                DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.DELETE_TRADE_ATTRIBUTE, attribute);
                tradeAttributes.remove(attribute);
            } else if (!StringUtils.isEmptyString(attribute.getAttributeValue())) {
                tradeAttributes.add(attribute);
            }
        }
        trade.setTradeAttributes(tradeAttributes);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTradeAttribute = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButtonSaveTrade = new javax.swing.JButton();

        jTableTradeAttribute.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Attribute", "Value",
            }
        ));
        jScrollPane1.setViewportView(jTableTradeAttribute);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveTrade, org.openide.util.NbBundle.getMessage(TradeAttributesPanel.class, "TradeAttributesPanel.jButtonSaveTrade.text")); // NOI18N
        jButtonSaveTrade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveTradeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(238, Short.MAX_VALUE)
                .addComponent(jButtonSaveTrade, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSaveTrade))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveTradeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveTradeActionPerformed
        try {
            updateAttributes();
            DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.STORE_TRADE, trade);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        Set<TopComponent> openedComponent = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent topComponent : openedComponent) {
            if (topComponent instanceof GaiaTradeTopComponent) {
                GaiaTradeTopComponent temp = (GaiaTradeTopComponent) topComponent;
                if (temp.getTrade() != null && temp.getTrade().getTradeId() == trade.getId() && trade.getId() != null) {
                    temp.loadByTradeId(temp.getTrade().getId());
                }
            }
        }
    }//GEN-LAST:event_jButtonSaveTradeActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveTrade;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTradeAttribute;
    // End of variables declaration//GEN-END:variables
}
