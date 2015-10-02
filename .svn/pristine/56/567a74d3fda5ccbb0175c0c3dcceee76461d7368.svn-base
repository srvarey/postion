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
package org.gaia.gui.reports;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.jade.GaiaAgentController;
import org.gaia.dao.jade.IRefreshableWindow;
import org.gaia.dao.jade.ReportLauncherAgent;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.AbstractObservable.ObservableType;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductReferenceTypeAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.trades.ProductReferenceType;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class AssetSelector extends JPanel implements IRefreshableWindow {

    Integer tradeId = null;
    List<String> availableProductTypes = null;
    private final ReportTemplate template;
    private final GreekSetting greekSetting;

    /**
     * Creates new form AssetFinder
     *
     * @param types
     * @param template
     * @param measureName
     */
    public AssetSelector(List<ProductTypeUtil.ProductType> types, ReportTemplate template, String measureName) {
        initComponents();
        this.template = template;
        if (template == null) {
            jButtonLoadFromReport.setVisible(false);
            jLabel5.setVisible(false);
            jComboBoxPricingEnv.setVisible(false);
        }

        jTable1.getTableHeader().setReorderingAllowed(false);

        Collections.sort(types);

        ObservableType observableType = null;
        greekSetting = (GreekSetting) DAOCallerAgent.callMethod(MeasuresAccessor.class, MeasuresAccessor.GET_GREEK_SETTING, measureName);
        if (greekSetting != null) {
            observableType = AbstractObservable.ObservableType.getObservableByName(greekSetting.getShifted());
        }

        availableProductTypes = new ArrayList();
        if (types != null) {
            for (ProductTypeUtil.ProductType type : types) {
                if (observableType == null && type != null) {
                    jComboBoxType.addItem(type.name);
                    availableProductTypes.add(type.name);
                } else if (type != null && type.observableType != null && type.observableType.equalsIgnoreCase(observableType.name)) {
                    jComboBoxType.addItem(type.name);
                    availableProductTypes.add(type.name);
                }
            }
        }

        List<ProductReferenceType> referenceTypes = (List) DAOCallerAgent.callMethod(ProductReferenceTypeAccessor.class, ProductReferenceTypeAccessor.GET_ALL_PRODUCT_REFERENCE_TYPES);
        if (referenceTypes != null) {
            for (ProductReferenceType type : referenceTypes) {
                jComboBoxProductRefTypes.addItem(type.getReferenceType());
            }
        }

        List<String> pricingEnvs = (List) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_LIST);
        for (String pricingEnv : pricingEnvs) {
            jComboBoxPricingEnv.addItem(pricingEnv);
        }
        jFormattedTextFieldId.setHorizontalAlignment(JFormattedTextField.RIGHT);

        marketDataTypeComboBox.addItem(null);
        for (AbstractObservable.ObservableType type : AbstractObservable.ObservableType.values()) {
            marketDataTypeComboBox.addItem(type.name);
        }

        jTable1.setAutoCreateRowSorter(true);
        TableColumnModel columnModel = jTable1.getColumnModel();
        columnModel.getColumn(0).setMaxWidth(45);

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
        jTable1 = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex==1)
                return true;
                else
                return false;   //Disallow the editing  cells
            }
        };
        jLabel1 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jComboBoxProductRefTypes = new javax.swing.JComboBox();
        jTextFieldCodeLike = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jFormattedTextFieldId = new javax.swing.JFormattedTextField(new DecimalFormat("########"));
        jTextFieldNameLike = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxPricingEnv = new javax.swing.JComboBox();
        jButtonLoadFromReport = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        marketDataTypeComboBox = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id", "Select",  "Name", "Code", "Type"
            }
        ));
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel1.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxProductRefTypes.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxProductRefTypes.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel5.text")); // NOI18N

        jComboBoxPricingEnv.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPricingEnv.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonLoadFromReport.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoadFromReport, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jButtonLoadFromReport.text")); // NOI18N
        jButtonLoadFromReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadFromReportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(AssetSelector.class, "AssetSelector.jLabel6.text")); // NOI18N

        marketDataTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxPricingEnv, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxProductRefTypes, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(10, 10, 10)
                                .addComponent(jTextFieldNameLike, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(10, 10, 10)
                                .addComponent(jFormattedTextFieldId)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldCodeLike)
                                .addComponent(jButtonFind, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButtonLoadFromReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(marketDataTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxProductRefTypes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCodeLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldNameLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFind)
                    .addComponent(jFormattedTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxPricingEnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoadFromReport))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(marketDataTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed
        /**
         * Looking
         */
        String name = jTextFieldNameLike.getText();
        String type = jComboBoxType.getSelectedItem().toString();
        String productRefType = jComboBoxProductRefTypes.getSelectedItem().toString();
        String productRefVal = jTextFieldCodeLike.getText();
        String productIds = jFormattedTextFieldId.getText();

        loadInstruments(productIds, type, name, productRefType, productRefVal);
    }

    /**
     * load Instruments
     */
    public void loadInstruments(String productIds, String productTypes, String name, String productReferenceType, String productReferenceVal) {

        if (productTypes == null) {
            productTypes = StringUtils.EMPTY_STRING;
        }
        if (productTypes.isEmpty()) {
            if (availableProductTypes != null && availableProductTypes.size() > 0) {
                for (String type : availableProductTypes) {
                    productTypes += StringUtils.QUOTE + type + "',";
                }
                productTypes = productTypes.substring(0, productTypes.length() - 1);
            }
        } else {
            productTypes = StringUtils.QUOTE + productTypes + StringUtils.QUOTE;
        }

        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        Vector newTableData = new Vector();
        Vector tableData = tableModel.getDataVector();
        for (int i = 0; i < tableData.size(); i++) {
            Object o = tableData.get(i);
            Vector row = (Vector) o;
            if (row.elementAt(1).equals(Boolean.TRUE)) {
                newTableData.add(row);
            }
        }
        Vector header = GUIUtils.getHeader(jTable1);

        List<Object[]> products = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCTS_WITH_FINDER, productIds, productTypes, name, productReferenceType, productReferenceVal);
        for (Object[] product : products) {
            Vector row = new Vector();
            row.add(product[0]);
            row.add(Boolean.FALSE);
            row.add(product[1]);
            row.add(product[2]);
            row.add(product[3]);
            newTableData.add(row);
        }
        DefaultTableModel tm = new DefaultTableModel(newTableData, header);
        jTable1.setModel(tm);

        GUIUtils.setCheckBoxEditor(jTable1, 1);

        TableColumnModel tableColumnModel = jTable1.getColumnModel();
        tableColumnModel.getColumn(0).setMaxWidth(45);
        tableColumnModel.getColumn(1).setMaxWidth(45);
        tm.fireTableDataChanged();

    }//GEN-LAST:event_jButtonFindActionPerformed

    private void jButtonLoadFromReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadFromReportActionPerformed
        /**
         * Load From Report
         */

        GaiaAgentController reportAgentController = GaiaAgentController.getInstance();
        ReportLauncherAgent myAgent = (ReportLauncherAgent) reportAgentController.createLocalAgent(ReportLauncherAgent.class);
        myAgent.setUI(this);
        String pricingEnv = jComboBoxPricingEnv.getSelectedItem().toString();
        ReportSettings reportSetting = new ReportSettings(template, new Date(), pricingEnv, true, false, null, true, "", false, false);
        myAgent.addBehaviour(myAgent.new LaunchReport(reportSetting));

    }//GEN-LAST:event_jButtonLoadFromReportActionPerformed

    @Override
    public void refresh(Object obj) {
        if (obj instanceof HashMap) {
            HashMap<Integer, String> observables = (HashMap) obj;
            String ids = StringUtils.EMPTY_STRING;
            for (Entry e : observables.entrySet()) {
                ids += e.getKey().toString() + StringUtils.COMMA;
            }
            if (!ids.isEmpty()) {
                ids = ids.substring(0, ids.length() - 1);
                if (!ids.isEmpty()) {
                    loadInstruments(ids, null, StringUtils.EMPTY_STRING, StringUtils.EMPTY_STRING, StringUtils.EMPTY_STRING);
                }
            }
        }
    }

    public String getObservableType(){
        if (marketDataTypeComboBox.getSelectedItem()!=null){
            return marketDataTypeComboBox.getSelectedItem().toString();
        }
        return null;
    }

    public Map<Integer, String> getSelectedUnderlyings() {
        Map<Integer, String> ids = new HashMap();
        DefaultTableModel tm1 = (DefaultTableModel) jTable1.getModel();
        Vector tableData = tm1.getDataVector();
        for (int i = 0; i < tableData.size(); i++) {
            Object o = tableData.get(i);
            Vector v = (Vector) o;
            if (v.elementAt(1).equals(Boolean.TRUE)) {
                ids.put((Integer) v.elementAt(0), v.elementAt(2).toString());
            }
        }
        return ids;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonLoadFromReport;
    private javax.swing.JComboBox jComboBoxPricingEnv;
    private javax.swing.JComboBox jComboBoxProductRefTypes;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JFormattedTextField jFormattedTextFieldId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldCodeLike;
    private javax.swing.JTextField jTextFieldNameLike;
    private javax.swing.JComboBox marketDataTypeComboBox;
    // End of variables declaration//GEN-END:variables
}
