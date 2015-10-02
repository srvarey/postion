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
package org.gaia.gui.observable;

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteUtils;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaTopComponent;
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
 * Top component which displays market quotes.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//MarketQuotes//EN", autostore = false)
@TopComponent.Description(preferredID = "MarketQuotesTopComponent", iconBase = "org/gaia/gui/reports/marketData.png", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.MarketQuotesTopComponent")
@ActionReference(path = "Menu" + MenuManager.MarketQuotesTopComponentMenu, position = MenuManager.MarketQuotesTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MarketQuotesAction", preferredID = "MarketQuotesTopComponent")
@Messages({"CTL_MarketQuotesAction=Market Quotes", "CTL_MarketQuotesTopComponent=Market Quotes", "HINT_MarketQuotesTopComponent=This is a Market Quotes window"})
public final class MarketQuotesTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(MarketQuotesTopComponent.class);
    List<String> quoteTypes;
    List<String> currencies;

    public MarketQuotesTopComponent() {
        initComponents();
        setName(Bundle.CTL_MarketQuotesTopComponent());
        setToolTipText(Bundle.HINT_MarketQuotesTopComponent());
    }

    @Override
    public void initContext() {

        jDateChooserStartDate.setDate(DateUtils.getDate());
        jDateChooserEndDate.setDate(DateUtils.getDate());
        jTableQuotes.getTableHeader().setReorderingAllowed(false);
        ExcelAdapter myAd = new ExcelAdapter(jTableQuotes);
        List itemList = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_QUOTE_SETS);
        GUIUtils.fillCombo(jComboBoxQuoteSets, itemList);
        quoteTypes = MarketQuoteAccessor.getQuoteTypes();
        currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        /**
         * ShortCut for date
         */
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserStartDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserEndDate.getComponent(1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        startDateLabel = new javax.swing.JLabel();
        endDateLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableQuotes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>1)
                return true;
                else
                return false;   //Disallow the editing of any cell
            }
        };
        quoteSetLabel = new javax.swing.JLabel();
        jComboBoxQuoteSets = new javax.swing.JComboBox();
        jButtonLoad = new javax.swing.JButton();
        productLabel = new javax.swing.JLabel();
        jTextFieldProduct = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        idLabel = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jCheckBoxBusDays = new javax.swing.JCheckBox();
        jDateChooserStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jDateChooserEndDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());

        backgroundPanel.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(startDateLabel, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.startDateLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(endDateLabel, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.endDateLabel.text")); // NOI18N

        jTableQuotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {"Product Id","Valuation Date","Quotation Type","Currency","Open","Bid","Ask","Close"
            }
        ));
        jTableQuotes.setName("jTableQuotes"); // NOI18N
        jScrollPane1.setViewportView(jTableQuotes);

        org.openide.awt.Mnemonics.setLocalizedText(quoteSetLabel, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.quoteSetLabel.text")); // NOI18N

        jComboBoxQuoteSets.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuoteSets.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(productLabel, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.productLabel.text")); // NOI18N

        jTextFieldProduct.setEnabled(false);

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jCheckBoxBusDays.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxBusDays, org.openide.util.NbBundle.getMessage(MarketQuotesTopComponent.class, "MarketQuotesTopComponent.jCheckBoxBusDays.text")); // NOI18N

        jDateChooserStartDate.setBackground(new java.awt.Color(254, 252, 254));
        jDateChooserStartDate.setName("jDateChooserStartDate"); // NOI18N

        jDateChooserEndDate.setBackground(new java.awt.Color(254, 252, 254));
        jDateChooserEndDate.setName("jDateChooserEndDate"); // NOI18N

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(quoteSetLabel)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxQuoteSets, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(startDateLabel)
                            .addComponent(productLabel))
                        .addGap(18, 18, 18)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jTextFieldProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonFind)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(backgroundPanelLayout.createSequentialGroup()
                                .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(endDateLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBoxBusDays)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLoad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSave))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 690, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quoteSetLabel)
                    .addComponent(jComboBoxQuoteSets, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(productLabel)
                                .addComponent(jTextFieldProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButtonFind)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(startDateLabel)
                                .addComponent(endDateLabel)
                                .addComponent(jButtonLoad)
                                .addComponent(jButtonSave)
                                .addComponent(jCheckBoxBusDays))
                            .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * LOAD
     */
    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        ArrayList list;
        String qs = jComboBoxQuoteSets.getSelectedItem().toString();
        Date startDate = DateUtils.removeTime(jDateChooserStartDate.getDate());
        Date endDate = DateUtils.removeTime(jDateChooserEndDate.getDate());
        String quotationType;
        String currency;
        Integer productId;
        Product product = null;
        if (!idLabel.getText().isEmpty()) {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                    ProductAccessor.GET_PRODUCT_BY_ID,
                    Integer.parseInt(idLabel.getText()));
        }
        if (product != null) {
            productId = product.getId();
            quotationType = product.getQuotationType();
            currency = product.getNotionalCurrency();
            list = (ArrayList) DAOCallerAgent.callMethod(MarketQuoteAccessor.class,
                    MarketQuoteAccessor.GET_QUOTES_BY_DATES_AND_PRODUCT,
                    qs, startDate, endDate, product.getId());
        } else {
            return;
        }
        if (list == null) {
            return;
        }
        JTableHeader h = jTableQuotes.getTableHeader();
        Enumeration<TableColumn> cols;
        DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
        Date displayedDate = DateUtils.removeTime(startDate);
        int index = 0;
        MarketQuote marketQuote = null;
        Date quoteDate = null;
        if (list.size() > 0) {
            marketQuote = (MarketQuote) list.get(index);
            quoteDate = DateUtils.removeTime(marketQuote.getValuationDate());
        }
        while (displayedDate.before(endDate) || displayedDate.equals(endDate)) {
            Vector row = new Vector<>();
            row.add(productId.toString());
            row.add(dateFormatter.format(displayedDate));
            row.add(quotationType);
            MarketQuote.QuotationType type = MarketQuoteUtils.getQuotationTypeByName(quotationType);
            BigDecimal mult = BigDecimal.ONE;
            if (type != null) {
                mult = type.getMult();
            }
            row.add(currency);
            if (list.size() > 0) {
                while (quoteDate.before(displayedDate) && index < list.size() - 1) {
                    index++;
                    marketQuote = (MarketQuote) list.get(index);
                    quoteDate = DateUtils.removeTime(marketQuote.getValuationDate());
                }
                cols = h.getColumnModel().getColumns();
                cols.nextElement();
                cols.nextElement();
                cols.nextElement();
                cols.nextElement();
                if (marketQuote.getValuationDate().equals(displayedDate)) {

                    if (marketQuote.getOpen() != null) {
                        marketQuote.setOpen(marketQuote.getOpen().multiply(mult));
                    }
                    if (marketQuote.getBid() != null) {
                        marketQuote.setBid(marketQuote.getBid().multiply(mult));
                    }
                    if (marketQuote.getAsk() != null) {
                        marketQuote.setAsk(marketQuote.getAsk().multiply(mult));
                    }
                    if (marketQuote.getClose() != null) {
                        marketQuote.setClose(marketQuote.getClose().multiply(mult));
                    }
                    row.add(marketQuote.getOpen());
                    row.add(marketQuote.getBid());
                    row.add(marketQuote.getAsk());
                    row.add(marketQuote.getClose());
                }
            }
            addRow(row);
            if (jCheckBoxBusDays.isSelected()) {
                displayedDate = DateUtils.addOpenDay(displayedDate, 1);
            } else {
                displayedDate = DateUtils.addCalendarDay(displayedDate, 1);
            }
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void addRow(Vector row) {
        ((DefaultTableModel) jTableQuotes.getModel()).addRow(row);
        /**
         * column quotation type
         */
        GUIUtils.setCombo(jTableQuotes, 2, quoteTypes);
        /**
         * column currency
         */
        GUIUtils.setCombo(jTableQuotes, 3, currencies);
        /**
         * quotes columns
         */
        GUIUtils.setNumberEditor(jTableQuotes, 4, sDecimalFormat);
        GUIUtils.setNumberEditor(jTableQuotes, 5, sDecimalFormat);
        GUIUtils.setNumberEditor(jTableQuotes, 6, sDecimalFormat);
        GUIUtils.setNumberEditor(jTableQuotes, 7, sDecimalFormat);
    }

    /**
     * find product
     */
    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed

        final AssetFinder af = new AssetFinder(ProductTypeUtil.loadUnderlyings());
        NotifyDescriptor nd = new NotifyDescriptor(af, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE,
                null, NotifyDescriptor.OK_OPTION);
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = af.getAssetId();
            if (productId != null) {
                Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, 
                        ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());
                idLabel.setText(productId.toString());
                jTextFieldProduct.setText(product.getShortName());
                ((DefaultTableModel) jTableQuotes.getModel()).getDataVector().clear();
                jTableQuotes.repaint();
            }
        }
    }//GEN-LAST:event_jButtonFindActionPerformed

    /**
     * Save
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        DefaultTableModel tm = (DefaultTableModel) jTableQuotes.getModel();
        JTableHeader h = jTableQuotes.getTableHeader();
        ArrayList<TableColumn> hs = Collections.list(h.getColumnModel().getColumns());
        Integer productId = null;
        String sValueDate;
        String quotationType = StringUtils.EMPTY_STRING;
        Date valdate = null;
        BigDecimal open;
        BigDecimal bid;
        BigDecimal ask;
        BigDecimal close;
        String quoteset = jComboBoxQuoteSets.getSelectedItem().toString();
        BigDecimal mult = BigDecimal.ONE;
        if (tm.getRowCount() > 0) {
            for (int i = 0; i < tm.getRowCount(); i++) {
                open = null;
                bid = null;
                ask = null;
                close = null;
                for (int j = 0; j < tm.getColumnCount(); j++) {
                    try {
                        if (hs.get(j).getHeaderValue().toString().equals("Product Id")) {
                            productId = Integer.parseInt(tm.getValueAt(i, j).toString());
                        } else if (hs.get(j).getHeaderValue().toString().equals("Valuation Date")) {
                            sValueDate = tm.getValueAt(i, j).toString();
                            valdate = dateFormatter.parse(sValueDate);
                        } else if (hs.get(j).getHeaderValue().toString().equals("Quotation Type")) {
                            if (tm.getValueAt(i, j) != null) {
                                quotationType = tm.getValueAt(i, j).toString();
                            } else {
                                quotationType = "Price";
                            }
                            mult = MarketQuoteUtils.getQuotationTypeByName(quotationType).getMult();
                        } else if (hs.get(j).getHeaderValue().toString().equals("Open") && tm.getValueAt(i, j) != null) {
                            if (!tm.getValueAt(i, j).toString().isEmpty()) {
                                open = new BigDecimal(Double.parseDouble(tm.getValueAt(i, j).toString()));
                            }
                        } else if (hs.get(j).getHeaderValue().toString().equals("Bid") && tm.getValueAt(i, j) != null) {
                            if (!tm.getValueAt(i, j).toString().isEmpty()) {
                                bid = new BigDecimal(Double.parseDouble(tm.getValueAt(i, j).toString()));
                            }
                        } else if (hs.get(j).getHeaderValue().toString().equals("Ask") && tm.getValueAt(i, j) != null) {
                            if (!tm.getValueAt(i, j).toString().isEmpty()) {
                                ask = new BigDecimal(Double.parseDouble(tm.getValueAt(i, j).toString()));
                            }
                        } else if (hs.get(j).getHeaderValue().toString().equals("Close") && tm.getValueAt(i, j) != null) {
                            if (!tm.getValueAt(i, j).toString().isEmpty()) {
                                close = new BigDecimal(Double.parseDouble(tm.getValueAt(i, j).toString()));
                            }
                        }
                        if (open != null || ask != null || bid != null || close != null) {
                            MarketQuote marketQuote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, 
                                    MarketQuoteAccessor.GET_PRODUCT_QUOTE, productId, valdate, quoteset);
                            if (marketQuote == null) {
                                Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, 
                                        ProductAccessor.GET_PRODUCT_BY_ID, productId);
                                marketQuote = new MarketQuote();
                                marketQuote.setProduct(product);
                                marketQuote.setQuoteSet(quoteset);
                                marketQuote.setValuationDate(valdate);
                            }
                            marketQuote.setQuotationType(quotationType);
                            if (ask != null) {
                                marketQuote.setAsk(ask.divide(mult));
                            }
                            if (bid != null) {
                                marketQuote.setBid(bid.divide(mult));
                            }
                            if (open != null) {
                                marketQuote.setOpen(open.divide(mult));
                            }
                            if (close != null) {
                                marketQuote.setClose(close.divide(mult));
                            }
                            DAOCallerAgent.callMethod(MarketQuoteAccessor.class, 
                                    MarketQuoteAccessor.STORE_QUOTE, marketQuote);
                        }
                    } catch (NumberFormatException | ParseException e) {
                        logger.error("Error " + StringUtils.formatErrorMessage(e));
                    }
                }
            }
        }
        JOptionPane.showMessageDialog(this, "Market quote saved");
    }//GEN-LAST:event_jButtonSaveActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JLabel endDateLabel;
    private javax.swing.JLabel idLabel;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxBusDays;
    private javax.swing.JComboBox jComboBoxQuoteSets;
    private com.toedter.calendar.JDateChooser jDateChooserEndDate;
    private com.toedter.calendar.JDateChooser jDateChooserStartDate;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableQuotes;
    private javax.swing.JTextField jTextFieldProduct;
    private javax.swing.JLabel productLabel;
    private javax.swing.JLabel quoteSetLabel;
    private javax.swing.JLabel startDateLabel;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}