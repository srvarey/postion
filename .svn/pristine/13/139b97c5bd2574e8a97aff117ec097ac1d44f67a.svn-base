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
package org.gaia.gui.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketDataSourceUtils;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketDataCode;
import org.gaia.domain.observables.MarketDataSource;

/**
 *
 * @author Benjamin Frerejean
 *
 */
public class ProductMarketDataCodesPanel extends GaiaPanel {

    private List<String> quoteSets;
    private List<String> dataSources;
    private List<String> currencies;
    private Integer productId;
    private static final Logger logger = Logger.getLogger(ProductMarketDataCodesPanel.class);
    /**
     * Creates new form ProductReferences
     */
     public ProductMarketDataCodesPanel(){
        this(null);
     }

    public ProductMarketDataCodesPanel(Collection<MarketDataCode> codes) {
        initComponents();

        try {
            quoteSets=(List)DAOCallerAgent.callMethod(MarketQuoteAccessor.class,MarketQuoteAccessor.GET_QUOTE_SETS);
        } catch (Exception ex) {
           logger.error(StringUtils.formatErrorMessage(ex));
        }
        try {
            dataSources=(List)DAOCallerAgent.callMethod(MarketDataSourceUtils.class,MarketDataSourceUtils.GET_MARKET_DATA_SOURCES);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        try {
            currencies=(List)DAOCallerAgent.callMethod(CurrencyAccessor.class,CurrencyAccessor.LOAD_CURRENCY_CODES);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        if (codes!=null){
            for (MarketDataCode code : codes){
                Vector<Object> oneRow = new Vector<Object>();
                oneRow.add(code.getMarketDataSource().getMarketDataSourceName());
                oneRow.add(code.getQuoteSet());
                oneRow.add(code.getProductCode());
                addRow(oneRow);
            }
        }
    }

    public Collection<MarketDataCode> getMarketcodes(){
         List<MarketDataCode> codes = new ArrayList();
         for (int i = 0; i < jTableMarketCodes.getRowCount(); i++) {

            String sourceName=null;
            if (jTableMarketCodes.getValueAt(i, 0)!=null){
                sourceName=jTableMarketCodes.getValueAt(i, 0).toString();
            }
            MarketDataCode marketDataCode=null;
            try {
                marketDataCode = (MarketDataCode) DAOCallerAgent.callMethod(MarketDataSourceUtils.class,MarketDataSourceUtils.GET_MARKET_CODE,productId,sourceName);
                if (marketDataCode==null){
                    marketDataCode =new MarketDataCode();
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
            MarketDataSource source=null;
            try {
                source = (MarketDataSource)DAOCallerAgent.callMethod(MarketDataSourceUtils.class,MarketDataSourceUtils.GET_MARKET_DATA_SOURCE_BY_NAME,sourceName);
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
            String quoteSet=null;
            if (jTableMarketCodes.getValueAt(i, 1)!=null){
                quoteSet=jTableMarketCodes.getValueAt(i, 1).toString();
            }
            String code=null;
            if (jTableMarketCodes.getValueAt(i, 2)!=null){
                code=jTableMarketCodes.getValueAt(i, 2).toString();
            }
            String currency=null;
            if (jTableMarketCodes.getValueAt(i, 3)!=null){
                currency=jTableMarketCodes.getValueAt(i, 3).toString();
            }
            if (marketDataCode!=null){
                marketDataCode.setMarketDataSource(source);
                marketDataCode.setQuoteSet(quoteSet);
                marketDataCode.setProductCode(code);
                marketDataCode.setCurrency(currency);
                if (source!=null && quoteSet!=null && code!=null){
                    codes.add(marketDataCode);
                }
            }
         }
         return codes;
    }

    private void addRow(Vector data) {
        ((DefaultTableModel) jTableMarketCodes.getModel()).addRow(data);

        String[] array0 = (String[]) dataSources.toArray(new String[dataSources.size()]);
        TableColumn col0 = jTableMarketCodes.getColumnModel().getColumn(0);
        col0.setCellEditor(new DefaultCellEditor(new JComboBox(array0)));

        String[] array1 = (String[]) quoteSets.toArray(new String[quoteSets.size()]);
        TableColumn col1 = jTableMarketCodes.getColumnModel().getColumn(1);
        col1.setCellEditor(new DefaultCellEditor(new JComboBox(array1)));

        String[] array2 = (String[]) currencies.toArray(new String[currencies.size()]);
        TableColumn col2 = jTableMarketCodes.getColumnModel().getColumn(3);
        col2.setCellEditor(new DefaultCellEditor(new JComboBox(array2)));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMarketCodes = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jTableMarketCodes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Data Source", "Quote Set","Code","Currency"    }));
    jScrollPane1.setViewportView(jTableMarketCodes);

    jButton1.setBackground(new java.awt.Color(195, 229, 255));
    org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ProductMarketDataCodesPanel.class, "ProductMarketDataCodesPanel.jButton1.text")); // NOI18N
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    jButton2.setBackground(new java.awt.Color(195, 229, 255));
    org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(ProductMarketDataCodesPanel.class, "ProductMarketDataCodesPanel.jButton2.text")); // NOI18N
    jButton2.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton2ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addComponent(jButton1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jButton2))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         addRow(new Vector(2));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         if (jTableMarketCodes.getSelectedRow() > -1) {
            DefaultTableModel tm = (DefaultTableModel) jTableMarketCodes.getModel();
            tm.removeRow(jTableMarketCodes.getSelectedRow());
         }
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableMarketCodes;
    // End of variables declaration//GEN-END:variables
}
