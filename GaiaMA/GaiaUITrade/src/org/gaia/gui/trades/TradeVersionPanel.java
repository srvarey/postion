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
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.gaia.dao.audit.AuditAccessor;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaPanel;

/**
 *
 * @author Jawhar Kamoun
 */
public class TradeVersionPanel extends GaiaPanel {

    private Trade trade;
    private List<Trade> oldtradeList;

    /**
     * Creates new form TradeVersionPanel
     */
    public TradeVersionPanel() {
        initComponents();
    }

    public void setTrade(Trade _trade) {
        trade = _trade;
        loadAudit();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        versionScrollPane = new javax.swing.JScrollPane();
        versionTable = new javax.swing.JTable();
        loadAuditButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(254, 252, 254));

        versionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Version", "Date Version", "User"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        versionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                versionTableMouseClicked(evt);
            }
        });
        versionScrollPane.setViewportView(versionTable);

        org.openide.awt.Mnemonics.setLocalizedText(loadAuditButton, org.openide.util.NbBundle.getMessage(TradeVersionPanel.class, "TradeVersionPanel.loadAuditButton.text")); // NOI18N
        loadAuditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadAuditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(versionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 217, Short.MAX_VALUE)
                        .addComponent(loadAuditButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(versionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadAuditButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    private void loadAudit() {
        if (trade != null) {
            trade=(Trade)DAOCallerAgent.callMethod(TradeAccessor.class , TradeAccessor.GET_TRADE_BY_ID,trade.getTradeId());
            oldtradeList = (List<Trade>) DAOCallerAgent.callMethod(AuditAccessor.class, AuditAccessor.GET_OLD_VERSION_LIST,trade);
            fillTable();
        }
    }
    private void versionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_versionTableMouseClicked
        if (evt.getClickCount() == 2 && versionTable.getSelectedRow() >= 0) {
            if (versionTable.getSelectedRow()==versionTable.getRowCount()-1){
                TradeUtils.loadTrade(trade, false, this);
            } else {
                TradeUtils.loadTrade(oldtradeList.get(versionTable.getSelectedRow()),true, this);
            }
        }
    }//GEN-LAST:event_versionTableMouseClicked

    private void loadAuditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadAuditButtonActionPerformed
        loadAudit();
    }//GEN-LAST:event_loadAuditButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton loadAuditButton;
    private javax.swing.JScrollPane versionScrollPane;
    private javax.swing.JTable versionTable;
    // End of variables declaration//GEN-END:variables

    private void fillTable() {
        Vector dataTable = new Vector<>();
        DefaultTableModel model = (DefaultTableModel) versionTable.getModel();
        if (oldtradeList!=null){
            for (Trade trade_ : oldtradeList) {
                Vector dataRow = new Vector<>();
                dataRow.add(trade_.getTradeVersion());
                dataRow.add(dateFormatterWithTime.format(
                        trade_.getUpdateDateTime() == null ? trade_.getCreationDateTime() : trade_.getUpdateDateTime()));
                dataRow.add(trade_.getTrader());
                dataTable.add(dataRow);
            }
        }
        model.getDataVector().removeAllElements();
        model.getDataVector().addAll(dataTable);
        versionTable.setModel(model);
        model.fireTableDataChanged();
    }
}
