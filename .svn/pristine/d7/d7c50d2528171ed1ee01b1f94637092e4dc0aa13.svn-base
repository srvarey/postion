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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.referentials.UserPricingPreference;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaPanel;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class PricerMeasureSelector extends GaiaPanel {

    Trade trade = null;
    List<String> availableProductTypes = null;
    private final ArrayList<UserPricingPreference> preferences;

    /**
     * Creates new form AssetFinder
     *
     * @param trade
     */
    public PricerMeasureSelector(Trade trade, ArrayList<UserPricingPreference> oldPreferences) {
        initComponents();
        this.trade = trade;

        jTable.getTableHeader().setReorderingAllowed(false);

        List<String> types = ProductTypeUtil.loadTradeableTypeNames();
        GUIUtils.fillCombo(jComboBoxType, types);
        jComboBoxType.setSelectedItem(trade.getProduct().getProductType());

        preferences = (ArrayList) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_USER_PRICING_PREFERENCES, trade.getProduct().getProductType(), LoggedUser.getLoggedUserId());
        for (UserPricingPreference preference : preferences) {
            addRow(preference);
        }
        if (oldPreferences != null) {
            preferences.addAll(oldPreferences);
        }

        jTable.setAutoCreateRowSorter(true);
        TableColumnModel columnModel = jTable.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(45);

        // look for possible greek values
        Date valDate = DateUtils.getDate();

        String pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        PricingEnvironment pricingEnvironment = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class,
                PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_FROM_NAME, pricingEnv);

        MeasuresAccessor.MeasureGroup[] groups = MeasuresAccessor.MeasureGroup.values();

        HashMap<MeasuresAccessor.MeasureGroup, IPricer> pricerMaps = (HashMap) DAOCallerAgent.callMethodWithXMLSerialization(PricingBuilder.class,
                PricingBuilder.LOAD_PRICER_MAP, trade, valDate, pricingEnvironment, groups);

        /**
         * load needed observables list
         */
        HashMap<MeasuresAccessor.MeasureGroup, Map<Integer, IObservable>> groupObservablesMap
                = (HashMap) DAOCallerAgent.callMethodWithXMLSerialization(PricingBuilder.class,
                        PricingBuilder.GET_OBSERBVABLES, pricerMaps, trade, pricingEnvironment);
        //
        ArrayList<TemplateColumnItem> items = (ArrayList) DAOCallerAgent.callMethod(PricingBuilder.class,
                PricingBuilder.GENERATE_DEFAULT_TEMPLATE_COLUMN_ITEMS, groupObservablesMap);

        List<String> greeks = new ArrayList();
        if (items != null) {
            for (TemplateColumnItem item : items) {
                String name = item.getName();
                name = name.substring(0, name.indexOf('.'));
                if (!greeks.contains(name)) {
                    greeks.add(name);
                }
            }
        }
        GUIUtils.setCombo(jTable, 1, greeks);

    }

    private void addRow(UserPricingPreference preference) {

        DefaultTableModel tableModel = (DefaultTableModel) jTable.getModel();

        Vector row = new Vector();
        row.add(preference.getUserPricingPreferenceId());
        row.add(preference.getPricingMeasure());
        tableModel.addRow(row);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex==1)
                return true;
                else
                return false;   //Disallow the editing  cells
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Measure"
            }
        ));
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PricerMeasureSelector.class, "PricerMeasureSelector.jLabel1.text")); // NOI18N

        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxType.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(PricerMeasureSelector.class, "PricerMeasureSelector.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(PricerMeasureSelector.class, "PricerMeasureSelector.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        addRow(new UserPricingPreference());
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        int index = jTable.getSelectedRow();

        if (index >= 0) {
            tm.removeRow(index);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    public ArrayList<UserPricingPreference> getPreferences() {
        DefaultTableModel tm = (DefaultTableModel) jTable.getModel();
        ArrayList<UserPricingPreference> result = new ArrayList();
        for (int i = 0; i < tm.getRowCount(); i++) {
            UserPricingPreference newPref = new UserPricingPreference();
            newPref.setProductType(trade.getProduct().getProductType());
            newPref.setPricingMeasure(tm.getValueAt(i, 1).toString());
            newPref.setUserId(LoggedUser.getLoggedUserId());
            Integer id = (Integer) tm.getValueAt(i, 0);
            newPref.setUserPricingPreferenceId(id);
            result.add(newPref);
        }
        return result;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
