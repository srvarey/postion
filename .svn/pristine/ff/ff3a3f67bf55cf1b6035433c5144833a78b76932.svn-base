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
package org.gaia.gui.observable;

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.ProductHistoricalAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.Measure;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.ProductHistoricalMeasure;
import org.gaia.domain.observables.ProductHistoricalMeasureValue;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.GaiaTopComponent.DecimalFormatRenderer;
import org.gaia.gui.common.HeaderChooser;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ExcelAdapter;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays product historicals.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//ProductHistorical//EN", autostore = false)
@TopComponent.Description(preferredID = "ProductHistoricalTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.ProductHistoricalTopComponent")
@ActionReference(path = "Menu"+MenuManager.ProductHistoricalTopComponentMenu, position = MenuManager.ProductHistoricalTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProductHistoricalAction", preferredID = "ProductHistoricalTopComponent")
@Messages({"CTL_ProductHistoricalAction=Product Historical", "CTL_ProductHistoricalTopComponent=Product Historical", "HINT_ProductHistoricalTopComponent=This is a Product Historical window"
})
public final class ProductHistoricalTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(ProductHistoricalTopComponent.class);
    HashMap<String, Boolean> listColumns = new HashMap();
    List<String> quoteTypes;
    List<String> currencies;

    public ProductHistoricalTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_ProductHistoricalTopComponent());
        setToolTipText(Bundle.HINT_ProductHistoricalTopComponent());
    }

    @Override
    public void initContext() {

        jDateChooserStartDate.setDate(DateUtils.getDate());
        jDateChooserEndDate.setDate(DateUtils.getDate());
        jTableHistoricals.getTableHeader().setReorderingAllowed(false);

        Measure[] arr = MeasuresAccessor.Measure.values();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].isUnit() && arr[i].getDimension() == 1) {
                listColumns.put(arr[i].getName(), Boolean.TRUE);
            }
        }
        refreshHeader(listColumns);
        ExcelAdapter myAd = new ExcelAdapter(jTableHistoricals);
        List itemList = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_QUOTE_SETS);
        if (itemList != null) {
            jComboBoxQuoteSet.removeAllItems();
            for (Object item : itemList) {
                jComboBoxQuoteSet.addItem(item);
            }
        }

        quoteTypes = MarketQuoteAccessor.getQuoteTypes();
        currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        /**
         * ShortCut for  date
         */
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserStartDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserEndDate.getComponent(1));
    }

    /**
     * refresh header
     */
    public void refreshHeader(Map<String, Boolean> listColumns) {


        Vector<String> tableHeaders = new Vector<>();
        tableHeaders.add("Product Id");
        tableHeaders.add("Valuation Date");
        tableHeaders.add("Quotation Type");
        tableHeaders.add("Currency");
        for (Entry e : listColumns.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                tableHeaders.add(e.getKey().toString());
            }
        }
        Vector data = new Vector<>();
        DefaultTableModel tm = new DefaultTableModel(data, tableHeaders);
        jTableHistoricals.setModel(tm);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableHistoricals = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>1)
                return true;
                else
                return false;   //Disallow the editing of any cell
            }
        };
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBoxQuoteSet = new javax.swing.JComboBox();
        jButtonLoad = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldProductName = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jLabelProductId = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jCheckBoxOpenDays = new javax.swing.JCheckBox();
        jButtonColumns = new javax.swing.JButton();
        jDateChooserStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jDateChooserEndDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel1.text")); // NOI18N

        jTextFieldTradeId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTradeIdFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel3.text")); // NOI18N

        jTableHistoricals.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {"Product Id","Valuation Date","Quotation Type","Currency"
            }
        ));
        jScrollPane1.setViewportView(jTableHistoricals);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel5.text")); // NOI18N

        jComboBoxQuoteSet.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuoteSet.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jLabel7.text")); // NOI18N

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jCheckBoxOpenDays.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxOpenDays, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jCheckBoxOpenDays.text")); // NOI18N

        jButtonColumns.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonColumns, org.openide.util.NbBundle.getMessage(ProductHistoricalTopComponent.class, "ProductHistoricalTopComponent.jButtonColumns.text")); // NOI18N
        jButtonColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColumnsActionPerformed(evt);
            }
        });

        jDateChooserStartDate.setBackground(new java.awt.Color(254, 252, 254));

        jDateChooserEndDate.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonFind)
                                .addGap(29, 29, 29)
                                .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jCheckBoxOpenDays)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addComponent(jButtonLoad)
                                .addGap(12, 12, 12)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonColumns, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)
                            .addComponent(jTextFieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonFind))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jButtonSave)
                                .addComponent(jButtonLoad)
                                .addComponent(jCheckBoxOpenDays))
                            .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonColumns))
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * LOAD
     */
    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed

        ArrayList<ProductHistoricalMeasure> historicalMeasuresList;
        Date startDate = DateUtils.removeTime(jDateChooserStartDate.getDate());
        Date endDate = DateUtils.removeTime(jDateChooserEndDate.getDate());
        String quotationType;
        String currency;
        Integer productId;
        Product product = null;
        if (!jLabelProductId.getText().isEmpty()) {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.parseInt(jLabelProductId.getText()));
        } else if (!jTextFieldTradeId.getText().isEmpty()) {
            Trade t = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, Integer.parseInt(jTextFieldTradeId.getText()));
            product = t.getProduct();
            if (product != null) {
                jLabelProductId.setText(product.getProductId().toString());
                jTextFieldProductName.setText(product.getShortName());
            }
        }
        if (product != null) {
            productId = product.getId();
            quotationType = product.getQuotationType();
            currency = product.getNotionalCurrency();
            historicalMeasuresList = (ArrayList) DAOCallerAgent.callMethod(ProductHistoricalAccessor.class, ProductHistoricalAccessor.GET_HISTORICAL_BY_DATES_AND_PRODUCT, productId, startDate, endDate, currency);
        } else {
            return;
        }
        if (historicalMeasuresList == null) {
            return;
        }
        JTableHeader header = jTableHistoricals.getTableHeader();
        Enumeration<TableColumn> columns = header.getColumnModel().getColumns();
        DefaultTableModel tableModel = (DefaultTableModel) jTableHistoricals.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        Date displayDate = DateUtils.removeTime(startDate);
        int index = 0;
        ProductHistoricalMeasure historicalMeasure = null;
        Date valuationDate = null;
        if (historicalMeasuresList.size() > 0) {
            historicalMeasure = (ProductHistoricalMeasure) historicalMeasuresList.get(index);
            valuationDate = DateUtils.removeTime(historicalMeasure.getValuationDate());
        }
        while (displayDate.before(endDate) || displayDate.equals(endDate)) {
            Vector row = new Vector();
            row.add(productId.toString());
            row.add(dateFormatter.format(displayDate));
            row.add(quotationType);
            row.add(currency);
            if (historicalMeasuresList.size() > 0) {
                while (valuationDate.before(displayDate) && index < historicalMeasuresList.size() - 1) {
                    index++;
                    historicalMeasure = (ProductHistoricalMeasure) historicalMeasuresList.get(index);
                    valuationDate = DateUtils.removeTime(historicalMeasure.getValuationDate());
                }
                columns = header.getColumnModel().getColumns();
                columns.nextElement();
                columns.nextElement();
                columns.nextElement();
                columns.nextElement();
                while (columns.hasMoreElements()) {
                    TableColumn tableColumn = columns.nextElement();
                    if (valuationDate.equals(displayDate)) {
                        boolean found = false;
                        for (Iterator<ProductHistoricalMeasureValue> it = historicalMeasure.getProductHistoricalMeasuresValueCollection().iterator(); it.hasNext();) {
                            ProductHistoricalMeasureValue value = it.next();
                            if (value.getMeasureName().equals(tableColumn.getHeaderValue())) {
                                row.add(value.getMeasureValue());
                                row.set(2, value.getQuotationType());
                                row.set(3, value.getCurrency());
                                found = true;
                            }
                        }
                        if (!found) {
                            row.add(StringUtils.EMPTY_STRING);
                        }
                    } else {
                        row.add(StringUtils.EMPTY_STRING);
                    }
                }
            }
            addRow(row);
            if (jCheckBoxOpenDays.isSelected()) {
                displayDate = DateUtils.addOpenDay(displayDate, 1);
            } else {
                displayDate = DateUtils.addCalendarDay(displayDate, 1);
            }
        }

        for (int c = 0; c < jTableHistoricals.getColumnCount(); c++) {
            jTableHistoricals.getColumnModel().getColumn(c).setCellRenderer(new DecimalFormatRenderer("0.00"));
            GUIUtils.packColumn(jTableHistoricals, c, 2);
        }

    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void addRow(Vector row) {
        ((DefaultTableModel) jTableHistoricals.getModel()).addRow(row);

        /**
         * column quotation type
         */
        GUIUtils.setCombo(jTableHistoricals, 2, quoteTypes);

        /**
         * column currency
         */
        GUIUtils.setCombo(jTableHistoricals, 3, currencies);
    }

    /**
     * find product
     */
    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed

        final AssetFinder af = new AssetFinder(ProductTypeUtil.loadProductTypes());

        NotifyDescriptor nd = new NotifyDescriptor(af, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = af.getAssetId();

            if (productId != null) {
                Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                jLabelProductId.setText(productId.toString());
                jTextFieldProductName.setText(product.getShortName());
            }
        }
    }//GEN-LAST:event_jButtonFindActionPerformed
    /**
     * Save
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) jTableHistoricals.getModel();
        JTableHeader tableHeader = jTableHistoricals.getTableHeader();
        ArrayList<TableColumn> headerColumns = Collections.list(tableHeader.getColumnModel().getColumns());

        Integer productId = null;
        String sValueDate;
        String quotationType = null;
        String currency = "";
        BigDecimal value;
        String measure;
        Date valdate = null;
        String quoteset = jComboBoxQuoteSet.getSelectedItem().toString();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
                try {
                    if (headerColumns.get(j).getHeaderValue().toString().equals("Product Id")) {
                        productId = Integer.parseInt(tableModel.getValueAt(i, j).toString());
                    } else if (headerColumns.get(j).getHeaderValue().toString().equals("Valuation Date")) {
                        sValueDate = tableModel.getValueAt(i, j).toString();
                        valdate = dateFormatter.parse(sValueDate);
                    } else if (headerColumns.get(j).getHeaderValue().toString().equals("Quotation Type")) {
                        if (tableModel.getValueAt(i, j) != null) {
                            quotationType = tableModel.getValueAt(i, j).toString();
                        }
                    } else if (headerColumns.get(j).getHeaderValue().toString().equals("Currency")) {
                        currency = tableModel.getValueAt(i, j).toString();
                    } else if (tableModel.getValueAt(i, j) != null) {
                        if (!tableModel.getValueAt(i, j).toString().equals("")) {
                            measure = headerColumns.get(j).getHeaderValue().toString();
                            value = BigDecimal.valueOf(Double.parseDouble(tableModel.getValueAt(i, j).toString()));
                            Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                            if (quotationType == null) {
                                quotationType = product.getQuotationType();
                            }
                            double mult = ProductAccessor.getMultiplierFromQuotationType(product.getQuotationType());
                            value = value.divide(BigDecimal.valueOf(mult));

                            ProductHistoricalMeasure historicalMeasure = (ProductHistoricalMeasure) DAOCallerAgent.callMethod(ProductHistoricalAccessor.class,
                                    ProductHistoricalAccessor.GET_HISTORICAL_BY_DATE_AND_PRODUCT, productId, valdate, quoteset);

                            if (historicalMeasure == null) {
                                historicalMeasure = new ProductHistoricalMeasure();
                                historicalMeasure.setProduct(product);
                                historicalMeasure.setProvider("internal");
                                historicalMeasure.setQuoteSet(quoteset);
                                historicalMeasure.setValuationDate(valdate);
                                Integer id = (Integer) DAOCallerAgent.callMethod(ProductHistoricalAccessor.class, ProductHistoricalAccessor.SAVE_HISTORICAL_MEASURE, historicalMeasure);
                                historicalMeasure.setProductHistoricalMeasureId(id);
                            }
                            ProductHistoricalMeasureValue measureValue = null;
                            if (historicalMeasure.getProductHistoricalMeasuresValueCollection() != null) {
                                for (ProductHistoricalMeasureValue temp : historicalMeasure.getProductHistoricalMeasuresValueCollection()) {
                                    if (temp.getMeasureName().equals(measure)) {
                                        measureValue = temp;
                                    }
                                }
                            }
                            if (measureValue == null) {
                                measureValue = new ProductHistoricalMeasureValue();
                            }
                            measureValue.setCurrency(currency);
                            measureValue.setMeasureName(measure);
                            measureValue.setQuotationType(quotationType);
                            measureValue.setDerivativeLevel(Short.valueOf("0"));
                            measureValue.setMeasureValue(value);
                            measureValue.setProductHistoricalMeasure(historicalMeasure);
                            DAOCallerAgent.callMethod(ProductHistoricalAccessor.class, ProductHistoricalAccessor.STORE_HISTORICAL_MEASURE_VALUE, measureValue);
                        }
                    }
                } catch (Exception e) {
                    logger.error("Error " + StringUtils.formatErrorMessage(e));
                }
            }
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColumnsActionPerformed
        final HeaderChooser hc = new HeaderChooser(listColumns);

        NotifyDescriptor nd = new NotifyDescriptor(hc, "Column Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            listColumns = hc.getSelectedColumns();
            refreshHeader(listColumns);
        }

    }//GEN-LAST:event_jButtonColumnsActionPerformed
    /**
     * when trade id change , empty instrument
     *
     * @param evt
     */
    private void jTextFieldTradeIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTradeIdFocusLost

        jTextFieldProductName.setText("");
        jLabelProductId.setText("");
    }//GEN-LAST:event_jTextFieldTradeIdFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonColumns;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxOpenDays;
    private javax.swing.JComboBox jComboBoxQuoteSet;
    private com.toedter.calendar.JDateChooser jDateChooserEndDate;
    private com.toedter.calendar.JDateChooser jDateChooserStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelProductId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableHistoricals;
    private javax.swing.JTextField jTextFieldProductName;
    private javax.swing.JTextField jTextFieldTradeId;
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
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
