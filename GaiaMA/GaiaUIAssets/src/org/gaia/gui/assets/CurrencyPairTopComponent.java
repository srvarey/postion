package org.gaia.gui.assets;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaProductTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays currency pairs.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//Forex//EN", autostore = false)
@TopComponent.Description(preferredID = "ForexTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.ForexTopComponent")
@ActionReference(path = "Menu"+MenuManager.CurrencyPairTopComponentMenu, position = MenuManager.CurrencyPairTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ForexAction", preferredID = "ForexTopComponent")
@Messages({"CTL_ForexAction=Currency Pair", "CTL_ForexTopComponent=Currency Pair Window", "HINT_ForexTopComponent=This is a Currency Pair window"})
public final class CurrencyPairTopComponent extends GaiaProductTopComponent {

    private static final Logger logger = Logger.getLogger(CurrencyPairTopComponent.class);
    private final DecimalFormat tickdecimalFormat;

    public CurrencyPairTopComponent() {
        initComponents();
        setName(Bundle.CTL_ForexTopComponent());
        setToolTipText(Bundle.HINT_ForexTopComponent());

        tickdecimalFormat = new DecimalFormat("#,##0.00000", decimalFormatSymbol);
    }

    @Override
    public void initContext() {
        List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        GUIUtils.fillCombo(jComboBoxCurrency1, currencies);
        GUIUtils.fillCombo(jComboBoxCurrency2, currencies);
        displayTable();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1Forex = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1Forex = new javax.swing.JTable();
        jPanel2Forex = new javax.swing.JPanel();
        jComboBoxCurrency1 = new javax.swing.JComboBox();
        jComboBoxCurrency2 = new javax.swing.JComboBox();
        jTextFieldLag = new javax.swing.JTextField();
        jTextFieldShortName = new javax.swing.JTextField();
        jLabelShortName = new javax.swing.JLabel();
        jLabelCurrency1 = new javax.swing.JLabel();
        jLabelCurrency2 = new javax.swing.JLabel();
        jLabelLag = new javax.swing.JLabel();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonClear = new javax.swing.JButton();
        jTextFieldTickSize = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTable1Forex.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}

            },
            new String [] {
                "Short Name", "Currency 1","Currency 2"
            }
        ));
        jTable1Forex.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1ForexMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1ForexMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1Forex);

        jPanel2Forex.setBackground(new java.awt.Color(230, 230, 253));
        jPanel2Forex.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jComboBoxCurrency1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {""}));
        jComboBoxCurrency1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrency1ActionPerformed(evt);
            }
        });

        jComboBoxCurrency2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrency2ActionPerformed(evt);
            }
        });

        jTextFieldLag.setText(org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jTextFieldLag.text")); // NOI18N

        jTextFieldShortName.setText(org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jTextFieldShortName.text")); // NOI18N
        jTextFieldShortName.setInheritsPopupMenu(true);
        jTextFieldShortName.setName("jTextFieldShortName"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelShortName, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jLabelShortName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCurrency1, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jLabelCurrency1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCurrency2, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jLabelCurrency2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelLag, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jLabelLag.text")); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.setPreferredSize(new java.awt.Dimension(64, 23));
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.setPreferredSize(new java.awt.Dimension(63, 23));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonClear.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonClear, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jButtonClear.text")); // NOI18N
        jButtonClear.setPreferredSize(new java.awt.Dimension(63, 23));
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });

        jTextFieldTickSize.setText(org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jTextFieldTickSize.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CurrencyPairTopComponent.class, "CurrencyPairTopComponent.jLabel1.text")); // NOI18N

        javax.swing.GroupLayout jPanel2ForexLayout = new javax.swing.GroupLayout(jPanel2Forex);
        jPanel2Forex.setLayout(jPanel2ForexLayout);
        jPanel2ForexLayout.setHorizontalGroup(
            jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2ForexLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2ForexLayout.createSequentialGroup()
                        .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabelCurrency1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelCurrency2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabelLag, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabelShortName, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.Alignment.LEADING, 0, 133, Short.MAX_VALUE)
                            .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldLag, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldTickSize)
                            .addComponent(jTextFieldShortName)))
                    .addGroup(jPanel2ForexLayout.createSequentialGroup()
                        .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        jPanel2ForexLayout.setVerticalGroup(
            jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2ForexLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldShortName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelShortName))
                .addGap(18, 18, 18)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCurrency1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCurrency2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldLag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelLag))
                .addGap(18, 18, 18)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTickSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel2ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonClear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2Forex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2Forex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1ForexLayout = new javax.swing.GroupLayout(jPanel1Forex);
        jPanel1Forex.setLayout(jPanel1ForexLayout);
        jPanel1ForexLayout.setHorizontalGroup(
            jPanel1ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1ForexLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1ForexLayout.setVerticalGroup(
            jPanel1ForexLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1ForexLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1Forex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1Forex, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed

        String shortName = jTextFieldShortName.getText();
        product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, shortName);
        if (product != null) {
            DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.DELETE_PRODUCT, product);
        }
        displayTable();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        ProductForex forex = null;
        Product productCcy;
        if (!StringUtils.isEmptyString(jTextFieldShortName.getText())) {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jTextFieldShortName.getText());
            if (product != null) {
                forex = product.getProductForex();
            } else {
                product = new Product();
                product.setProductType("Currency Pair");
                product.setLongName(jComboBoxCurrency1.getSelectedItem() + "/" + jComboBoxCurrency2.getSelectedItem());
            }
            if (forex == null) {
                forex = new ProductForex();
                forex.setProduct(product);
                product.setProductForex(forex);
            }
            product.setShortName(jTextFieldShortName.getText());
            product.setQuantityType(Trade.QuantityType.QUANTITY.name);
            product.setIsAsset(Boolean.FALSE);
            product.setStatus(ProductConst.ProductStatus.Active.name());
            product.setQuotationType(MarketQuote.QuotationType.PRICE.getName());
            product.setLongName(jComboBoxCurrency1.getSelectedItem() + "/" + jComboBoxCurrency2.getSelectedItem());
            if (!StringUtils.isEmptyString(jTextFieldLag.getText())) {
                try {
                    product.setSettlementDelay(numberFormat.parse(jTextFieldLag.getText()).shortValue());
                } catch (ParseException ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            } else {
                product.setSettlementDelay(Short.parseShort("0"));
            }
            if (!StringUtils.isEmptyString(jTextFieldTickSize.getText())) {
                try {
                    forex.setTickSize(new BigDecimal(tickdecimalFormat.parse(jTextFieldTickSize.getText()).doubleValue()));
                } catch (ParseException ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            } else {
                forex.setTickSize(BigDecimal.ZERO);
            }
            if (!StringUtils.isEmptyString(jComboBoxCurrency1.getSelectedItem().toString())) {
                productCcy = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(jComboBoxCurrency1));
                product.setNotionalCurrency(GUIUtils.getComponentStringValue(jComboBoxCurrency1));
                forex.setCurrency1(productCcy);
            }

            if (!StringUtils.isEmptyString(jComboBoxCurrency2.getSelectedItem().toString())) {
                productCcy = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(jComboBoxCurrency2));
                forex.setCurrency2(productCcy);
            }
        }
        product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, product);

        displayTable();
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxCurrency1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrency1ActionPerformed
        fillTextFieldShortName();
    }//GEN-LAST:event_jComboBoxCurrency1ActionPerformed

    private void jComboBoxCurrency2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrency2ActionPerformed
        fillTextFieldShortName();
    }//GEN-LAST:event_jComboBoxCurrency2ActionPerformed

    private void jTable1ForexMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1ForexMouseClicked
        refereshData(evt);
    }//GEN-LAST:event_jTable1ForexMouseClicked

    private void jTable1ForexMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1ForexMousePressed
        refereshData(evt);
    }//GEN-LAST:event_jTable1ForexMousePressed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonClearActionPerformed

    /**
     * display table
     */
    private void displayTable() {

        List<String> pairs = (List<String>) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_PAIRS);

        DefaultTableModel tableModel = (DefaultTableModel) jTable1Forex.getModel();
        tableModel.getDataVector().clear();

        if (pairs != null) {

            for (String pair : pairs) {
                Object[] row = new Object[3];
                row[0] = pair;
                if (pair.length() == 7) {

                    row[1] = pair.substring(0, 3);
                    row[2] = pair.substring(4, 7);
                }
                tableModel.addRow(row);
            }
        }
    }

    public void fillTextFieldShortName() {
        if (jComboBoxCurrency1.getSelectedItem() != null && jComboBoxCurrency2.getSelectedItem() != null) {
            StringBuilder shortName = new StringBuilder(GUIUtils.getComponentStringValue(jComboBoxCurrency1));
            shortName.append("/").append(GUIUtils.getComponentStringValue(jComboBoxCurrency2));
            jTextFieldShortName.setText(shortName.toString());
        }

    }

    /**
     * refresh data
     */
    private void refereshData(java.awt.event.ComponentEvent evt) {

        int rownum = jTable1Forex.getSelectedRow();
        if (rownum >= 0) {
            String shortName = GUIUtils.getTableValueAt(jTable1Forex, rownum, 0);
            if (shortName != null) {
                product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, shortName);
                ProductForex productforex = product.getProductForex();

                jTextFieldShortName.setText(product.getShortName());
                if (productforex != null) {
                    if (product.getSettlementDelay() != null) {
                        jTextFieldLag.setText(product.getSettlementDelay().toString());
                    }
                    if (productforex.getCurrency1() != null) {
                        jComboBoxCurrency1.setSelectedItem(productforex.getCurrency1().getShortName());
                    }
                    if (productforex.getCurrency1() != null) {
                        jComboBoxCurrency2.setSelectedItem(productforex.getCurrency2().getShortName());
                    }
                    if (productforex.getTickSize() != null) {
                        jTextFieldTickSize.setText(tickdecimalFormat.format(productforex.getTickSize()));
                    }
                }
                setDisplayName(getDisplayName());
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxCurrency1;
    private javax.swing.JComboBox jComboBoxCurrency2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCurrency1;
    private javax.swing.JLabel jLabelCurrency2;
    private javax.swing.JLabel jLabelLag;
    private javax.swing.JLabel jLabelShortName;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel1Forex;
    private javax.swing.JPanel jPanel2Forex;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1Forex;
    private javax.swing.JTextField jTextFieldLag;
    private javax.swing.JTextField jTextFieldShortName;
    private javax.swing.JTextField jTextFieldTickSize;
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
