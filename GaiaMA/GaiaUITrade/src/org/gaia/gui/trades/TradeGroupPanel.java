/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.trades;

import java.util.Set;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeGroup;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Benji
 */
public class TradeGroupPanel extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(ProductReferencesPanel.class);
    private TradeGroup tradeGroup;
    private Trade trade;
    DefaultTableModel tableModel;

    /** Creates new form TradeAttributesPanel */
    public TradeGroupPanel() {
        initComponents();
        tableModel = (DefaultTableModel) jTableTradeGroups.getModel();
    }

    /** add a TradeAttribute
     * @param _trade */
    public void setTradeGroup(Trade _trade) {

        trade = _trade;
        if (trade.getTradeGroups() != null) {//|| !tradeAttribute.isEmpty()){
            displayTable(trade.getTradeGroups());
        }
    }

    private void displayTable(Set<TradeGroup> groups) {
        tableModel.getDataVector().clear();
        if (groups != null) {
            for (TradeGroup group : groups) {
                for (Trade trade_ : group.getTrades()) {
                    String[] row = new String[3];
                    row[0] = group.getTradeGroupId().toString();
                    row[1] = group.getTradeGroupType();
                    row[2] = trade_.getTradeId().toString();
                    tableModel.addRow(row);
                }
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableTradeGroups = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();

        jTableTradeGroups.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Group Id", "Type", "Trade Id"
            }
        ));
        jScrollPane1.setViewportView(jTableTradeGroups);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(TradeGroupPanel.class, "TradeGroupPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(232, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = jTableTradeGroups.getSelectedRow();
        if (row >= 0) {
            String val = GUIUtils.getTableValueAt(jTableTradeGroups, row, 2);
            if (!val.isEmpty()) {
                Integer id = Integer.parseInt(val);
                TradeUtils.openTrade(jPanel1, id, false);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableTradeGroups;
    // End of variables declaration//GEN-END:variables
}
