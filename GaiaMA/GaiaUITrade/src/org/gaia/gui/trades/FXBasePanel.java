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
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.CurveUtils;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.pricing.pricers.gaia.PricerFXForward;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.referentials.DomainValue;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;

/**
 *
 * @author Jawhar Kamoun
 */
public class FXBasePanel extends JPanel {

    /**
     * Creates new form FXBasePanel
     */
    private FXBasePanel relatedFxFwdPanel;
    private FxGlobalInfoPanel relatedGlobalinfoPanel;
    private GaiaTradeTopComponent parentTopComponent;
    private final Color buyColor = new java.awt.Color(202, 249, 221);
    private final Color sellColor = new java.awt.Color(255, 196, 196);
    private static final String dateFormat = "dd/MM/yyyy";
    public static DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols(Locale.US);
    public Format decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbol);
    public static final NumberFormat nformat = NumberFormat.getNumberInstance(Locale.US);
    private static final Logger logger = Logger.getLogger(FXBasePanel.class);
    private static final BigDecimal defaultQuantity = new BigDecimal(1000000);
    private Trade trade;
    private Product productCcyPair;
    private Product productFxSwap;
    private Product productNDF;
    private final String quoteSet;
    public boolean isFxFwd = true;
    private boolean isSecondaryPanel = false;
    public static Format pointdecimalFormat = null;
    private final BigDecimal rateMultiplicator = NumberUtils.BIGDECIMAL_100;
    private String NDFCurrency = null;

    public FXBasePanel() {
        initComponents();
        quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        pointdecimalFormat = new DecimalFormat("#,##0.0000", decimalFormatSymbol);
        fwdRateLabel1.setValueMultiplicator(rateMultiplicator);
        fwdRateLabel2.setValueMultiplicator(rateMultiplicator);
        jPanelNDF.setVisible(false);
    }

    public void initContext() {
        buyToggleButton.setSelected(true);
        valueDateChooser.setDate(DateUtils.getDate());
        fixingDateChooser.setDate(DateUtils.getDate());
        List<String> currencyCodes = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIRS, LoggedUser.getLoggedUserId());
        GUIUtils.fillCombo(ccyPairComboBox, currencyCodes);
        ccyPairComboBox.setSelectedItem(DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIR, LoggedUser.getLoggedUserId()));
        List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        GUIUtils.fillCombo(jComboBoxDeliveryCurrency, currencies);
        jComboBoxDeliveryCurrency.setSelectedItem(DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId()));

        startRTQuote();
        /**
         * ShortCut for Amout and date
         */
        AmountShortCut.eventkey(quantityFormattedTextField);
        DateShortCut.eventkey((JSpinnerDateEditor) valueDateChooser.getComponent(1));

    }

    public Trade getTrade(boolean newtrade) {
        trade = relatedGlobalinfoPanel.getTrade(newtrade);
        if (trade != null) {
            trade = fillTrade(trade);
        }
        return trade;
    }

    public void setTrade(Trade _trade) {
        trade = _trade;
        if (trade != null && trade.getProduct().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.CURRENCY_PAIR.getName())) {
            productCcyPair = trade.getProduct();
        } else if (trade.getProduct().loadSingleUnderlying() != null) {
            productCcyPair = trade.getProduct().loadSingleUnderlying();
        }
        if (relatedFxFwdPanel != null) {
            relatedFxFwdPanel.setTrade(_trade);
        }
        fillPanel();
        calculateConvertedAmount();
    }

    private void fillPanel() {

        if (productCcyPair != null) {
            ccyPairComboBox.setSelectedItem(productCcyPair.getShortName());
        }
        if (trade != null) {
            if (isFxFwd) { // forwards only
                fwdPointsFormattedTextField.setText(decimalFormat.format(trade.getNegociatedPrice()));
                jLabelSpotInfo.setText(pointdecimalFormat.format(trade.getMarketPrice()));
            } // fx and forwards and swap spot legs
            if (!isSecondaryPanel) {
                quantityFormattedTextField.setText(decimalFormat.format(trade.getQuantity()));
                // checks if ndf
                if (trade.getProduct() != null && trade.getProduct().getProductType().equalsIgnoreCase(ProductType.FX_NDF.name)
                        && !trade.getProduct().getUnderlyingProducts().isEmpty()) {
                    NDFCurrency = trade.getProduct().loadSingleUnderlying().getShortName().substring(0, 3);
                    jPanelNDF.setVisible(true);
                }
                if (StringUtils.isEmptyString(NDFCurrency)) {//spot & forwards & swaps
                    priceFormattedTextField.setText(pointdecimalFormat.format(trade.getPrice()));
                    if (relatedFxFwdPanel == null) { ////spot & forwards
                        productCcyPair = trade.getProduct();
                    } else { //swap
                        productCcyPair = trade.getProduct().loadSingleUnderlying();
                    }
                    amountFormattedTextField.setText(decimalFormat.format(trade.getAmount()));
                    valueDateChooser.setDate(trade.getValueDate());
                    isNDFCheckBox.setSelected(false);
                } else { // ndf
                    productNDF = trade.getProduct();
                    priceFormattedTextField.setText(pointdecimalFormat.format(trade.getForexRate()));
                    isNDFCheckBox.setSelected(true);
                    if (trade.getProduct().loadSingleUnderlying() != null) {
                        productCcyPair = trade.getProduct().loadSingleUnderlying();
                    }
                    if (productNDF.getProductForex() != null) {
                        fixingDateChooser.setDate(productNDF.getProductForex().getFixingDate());
                    } else {
                        fixingDateChooser.setDate(null);
                    }
                    if (productCcyPair == null) {
                        productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(ccyPairComboBox));
                    }
                    amountFormattedTextField.setText(decimalFormat.format(trade.getConvertedAmount()));
                    valueDateChooser.setDate(productNDF.getMaturityDate());
                    jComboBoxDeliveryCurrency.setSelectedItem(productNDF.getNotionalCurrency());

                }
            } else { // fx swap forward leg
                productFxSwap = trade.getProduct();
                quantityFormattedTextField.setText(decimalFormat.format(trade.getQuantity().negate()));
                BigDecimal fwd = trade.getPrice().add(trade.getNegociatedPrice().multiply(productCcyPair.getProductForex().getTickSize()));
                priceFormattedTextField.setText(pointdecimalFormat.format(fwd));
                valueDateChooser.setDate(productFxSwap.getMaturityDate());
                if (trade.getProduct().loadSingleUnderlying() != null) {
                    productCcyPair = trade.getProduct().loadSingleUnderlying();
                } else {
                    JOptionPane.showMessageDialog(this, "missing undelying");
                }
                amountFormattedTextField.setText(decimalFormat.format(fwd.multiply(trade.getQuantity())));
                jLabelSpotInfo.setText(pointdecimalFormat.format(trade.getMarketPrice()));

            }
            if (trade.getQuantity().doubleValue() <= 0) {
                updateUIColor(sellColor);
            } else {
                updateUIColor(buyColor);
            }
        } else {  // trade == null
            productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(ccyPairComboBox));
            valueDateChooser.setDate(DateUtils.getDate());
            jLabelSpotInfo.setText(StringUtils.EMPTY_STRING);
            quantityFormattedTextField.setText(StringUtils.EMPTY_STRING);
            priceFormattedTextField.setText(BigDecimal.ZERO.toString());
            if (isFxFwd) {
                fwdPointsFormattedTextField.setText(decimalFormat.format(updateFwdPoints()));
            }
        }
    }

    public void setSecondaryPanel() {
        buySellPanel.setVisible(false);
        ccyPairComboBox.setEnabled(false);
        isSecondaryPanel = Boolean.TRUE;
        realTimeCheckBox.setVisible(false);
    }

    private Trade fillTrade(Trade trade) {
        if (!isSecondaryPanel) {  // fx and forwards & ndf and swap spot legs
            if (relatedFxFwdPanel != null) { // swaps
                trade = relatedFxFwdPanel.fillTrade(trade);
            }
            trade.setQuantity(GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField));
            if (StringUtils.isEmptyString(NDFCurrency)) { // spots & fwds & swap spot leg
                trade.setPrice(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField));
                if (isFxFwd) {
                    BigDecimal pts = GUIUtils.getComponentBigDecimalValue(fwdPointsFormattedTextField);
                    trade.setNegociatedPrice(pts);
                    trade.setNegociatedPriceType(MarketQuote.QuotationType.FWDpts.name());
                    pts = pts.multiply(productCcyPair.getProductForex().getTickSize());
                    trade.setMarketPrice(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField).subtract(pts));
                } else {
                    trade.setMarketPrice(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField));
                }
                trade.setAmount(GUIUtils.getComponentBigDecimalValue(amountFormattedTextField));
                trade.setSettlementCurrency(GUIUtils.getComponentStringValue(amount2Label));
                trade.setPriceCurrency(GUIUtils.getComponentStringValue(amount1Label));
                trade.setValueDate(GUIUtils.getComponentDateValue(valueDateChooser));
                if (relatedFxFwdPanel == null) { // spot & fwds
                    trade.setProduct(productCcyPair);
                } else { // swaps
                    trade.getProduct().setSingleUnderlying(productCcyPair);
                    trade.getProduct().setStartDate(valueDateChooser.getDate());
                    trade.getProduct().setForexRate(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField));
                }
            } else { //  NDF
                trade.setPrice(BigDecimal.ZERO);
                trade.setAmount(BigDecimal.ZERO);
                trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());

                if (trade.getProduct() == null) {
                    trade.setProduct(new Product());
                }
                if (trade.getProduct().getProductForex() == null) {
                    trade.getProduct().setProductForex(new ProductForex());
                    trade.getProduct().getProductForex().setProduct(trade.getProduct());
                }
                trade.getProduct().setForexRate(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField));
                trade.getProduct().setProductType(ProductTypeUtil.ProductType.FX_NDF.name);
                trade.getProduct().setStartDate(trade.getTradeDate());
                trade.setConvertedAmount(GUIUtils.getComponentBigDecimalValue(amountFormattedTextField));
                trade.setSettlementCurrency(jComboBoxDeliveryCurrency.getSelectedItem().toString());
                trade.setPriceCurrency(jComboBoxDeliveryCurrency.getSelectedItem().toString());
                trade.setValueDate(trade.getTradeDate());
                trade.getProduct().addUnderlying(productCcyPair);
                trade.getProduct().setMaturityDate(valueDateChooser.getDate());
                trade.getProduct().setNotionalCurrency(jComboBoxDeliveryCurrency.getSelectedItem().toString());
                trade.getProduct().setNotionalMultiplier(BigDecimal.ONE);
                trade.getProduct().getProductForex().setFixingDate(fixingDateChooser.getDate());
            }

        } else { // swap fwd leg
            if (productFxSwap == null) {
                productFxSwap = new Product();
            }
            productFxSwap.setProductType(ProductTypeUtil.ProductType.FX_SWAP.name);
            productFxSwap.setMaturityDate(valueDateChooser.getDate());
            productFxSwap.setIsAsset(false);
            productFxSwap.setNotionalCurrency(productCcyPair.getNotionalCurrency());
            trade.setProduct(productFxSwap);
            if (productFxSwap.getProductForex() == null) {
                ProductForex forex = new ProductForex();
                forex.setProduct(productFxSwap);
                productFxSwap.setProductForex(forex);
            }
            productFxSwap.getProductForex().setStrike(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField));
            trade.setConvertedAmount(GUIUtils.getComponentBigDecimalValue(amountFormattedTextField));
            trade.setNegociatedPrice(GUIUtils.getComponentBigDecimalValue(fwdPointsFormattedTextField));
            trade.setNegociatedPriceType(MarketQuote.QuotationType.FWDpts.name());
        }
        return trade;
    }

    public void startRTQuote() {
        if (productCcyPair != null) {
            if (isFxFwd) {
                CurveObservable curveCcy1 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                        CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency1().getShortName());
                CurveObservable curveCCy2 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                        CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency2().getShortName());
                if (trade != null && trade.getProduct() != null) {
                    fwdRateLabel1.setObservable(curveCcy1);

                    fwdRateLabel2.setObservable(curveCCy2);
                }
                fwdRateLabel1.startRTQuote();
                fwdRateLabel2.startRTQuote();
            }
            realTimeQuoteLabel.setObservable(new MarketQuoteObservable(productCcyPair));
            realTimeQuoteLabel.startRTQuote();
            updateRTMaturity();
        }
    }

    public void changeRTQuoteSubscription() {
        if (productCcyPair != null) {
            if (isFxFwd) {
                String pricer = parentTopComponent.getNpvPricerName();
                if (pricer != null && pricer.equalsIgnoreCase(PricerFXForward.class.getSimpleName())) {
                    CurveObservable fwdCurve = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                            CurveUtils.GET_DEFAULT_FX_FORWARD_CURVE, productCcyPair.getProductForex().getCurrency1().getShortName(),
                            productCcyPair.getProductForex().getCurrency2().getShortName());
                    fwdRateLabel1.setObservable(fwdCurve);
                    fwdRateLabel2.setObservable(null);
                    fwdRateLabel2.setText(StringUtils.EMPTY_STRING);
                    rateLabel3.setText("RT Fwd Pts");
                    rateLabel4.setText("");
                } else {

                    CurveObservable curveCCy1 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                            CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency1().getShortName());
                    CurveObservable curveCCy2 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                            CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency2().getShortName());

                    fwdRateLabel1.setObservable(curveCCy1);
                    fwdRateLabel2.setObservable(curveCCy2);

                    rateLabel3.setText(productCcyPair.getProductForex().getCurrency1().getShortName() + " rate %");
                    rateLabel4.setText(productCcyPair.getProductForex().getCurrency2().getShortName() + " rate %");
                }
                updateRTMaturity();
            }
            realTimeQuoteLabel.setObservable(new MarketQuoteObservable(productCcyPair));
        }
    }

    public void updateRTMaturity() {
        Product prdct;
         if ( trade !=null && trade.getProduct()!=null){
             prdct=ProductAccessor.cloneProduct(trade.getProduct());
         } else {
            prdct = new Product();
         }
        prdct.setMaturityDate(valueDateChooser.getDate());
        fwdRateLabel1.setProduct(prdct);
        fwdRateLabel2.setProduct(prdct);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rateLabel2 = new javax.swing.JLabel();
        buySellPanel = new javax.swing.JPanel();
        buyToggleButton = new javax.swing.JToggleButton();
        sellToggleButton = new javax.swing.JToggleButton();
        amountsPanel = new javax.swing.JPanel();
        amountFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        amount2Label = new javax.swing.JLabel();
        amount1Label = new javax.swing.JLabel();
        fxRateLabel = new javax.swing.JLabel();
        quantityFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        priceFormattedTextField = new javax.swing.JFormattedTextField();
        valueDateChooser = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        valueDateLabel = new javax.swing.JLabel();
        realTimeCheckBox = new javax.swing.JCheckBox();
        isNDFCheckBox = new javax.swing.JCheckBox();
        fwdRatePanel = new javax.swing.JPanel();
        fwdPointsFormattedTextField = new javax.swing.JFormattedTextField();
        fwdPtsLabel = new javax.swing.JLabel();
        rateLabel3 = new javax.swing.JLabel();
        rateLabel4 = new javax.swing.JLabel();
        fwdRateLabel2 = new org.gaia.gui.common.GaiaRTLabel();
        fwdRateLabel1 = new org.gaia.gui.common.GaiaRTLabel();
        ccyPairComboBox = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        realTimeQuoteLabel = new org.gaia.gui.common.GaiaRTLabel();
        jLabelSpotInfo = new javax.swing.JLabel();
        jLabelSpot = new javax.swing.JLabel();
        jPanelNDF = new javax.swing.JPanel();
        fixingDateChooser = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBoxDeliveryCurrency = new javax.swing.JComboBox();
        jLabelNDF = new javax.swing.JLabel();

        rateLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(rateLabel2, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.rateLabel2.text")); // NOI18N

        setBackground(new java.awt.Color(202, 249, 221));
        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        setMaximumSize(new java.awt.Dimension(625, 205));
        setMinimumSize(new java.awt.Dimension(625, 205));
        setPreferredSize(new java.awt.Dimension(625, 200));

        buySellPanel.setBackground(new java.awt.Color(202, 249, 221));
        buySellPanel.setMaximumSize(new java.awt.Dimension(591, 134));
        buySellPanel.setMinimumSize(new java.awt.Dimension(591, 134));

        buyToggleButton.setBackground(new java.awt.Color(153, 255, 153));
        buyToggleButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buyToggleButton.setForeground(new java.awt.Color(0, 153, 51));
        buyToggleButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(buyToggleButton, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.buyToggleButton.text")); // NOI18N
        buyToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyToggleButtonActionPerformed(evt);
            }
        });

        sellToggleButton.setBackground(new java.awt.Color(255, 204, 204));
        sellToggleButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        sellToggleButton.setForeground(new java.awt.Color(255, 0, 0));
        org.openide.awt.Mnemonics.setLocalizedText(sellToggleButton, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.sellToggleButton.text")); // NOI18N
        sellToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellToggleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buySellPanelLayout = new javax.swing.GroupLayout(buySellPanel);
        buySellPanel.setLayout(buySellPanelLayout);
        buySellPanelLayout.setHorizontalGroup(
            buySellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buySellPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(buyToggleButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sellToggleButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buySellPanelLayout.setVerticalGroup(
            buySellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buySellPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buySellPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buyToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sellToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        amountsPanel.setBackground(new java.awt.Color(202, 249, 221));

        amountFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.amountFormattedTextField.text")); // NOI18N
        amountFormattedTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        amountFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountFormattedTextFieldActionPerformed(evt);
            }
        });

        amount2Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(amount2Label, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.amount2Label.text")); // NOI18N

        amount1Label.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(amount1Label, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.amount1Label.text")); // NOI18N

        fxRateLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(fxRateLabel, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.fxRateLabel.text")); // NOI18N

        quantityFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantityFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.quantityFormattedTextField.text")); // NOI18N
        quantityFormattedTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        quantityFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityFormattedTextFieldActionPerformed(evt);
            }
        });
        quantityFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                quantityFormattedTextFieldFocusLost(evt);
            }
        });

        priceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        priceFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.priceFormattedTextField.text")); // NOI18N
        priceFormattedTextField.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        priceFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFormattedTextFieldActionPerformed(evt);
            }
        });
        priceFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                priceFormattedTextFieldFocusLost(evt);
            }
        });
        priceFormattedTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                priceFormattedTextFieldPropertyChange(evt);
            }
        });

        valueDateChooser.setBackground(new java.awt.Color(202, 249, 221));
        valueDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                valueDateChooserPropertyChange(evt);
            }
        });

        valueDateLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(valueDateLabel, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.valueDateLabel.text")); // NOI18N

        realTimeCheckBox.setBackground(new java.awt.Color(255, 196, 196));
        org.openide.awt.Mnemonics.setLocalizedText(realTimeCheckBox, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.realTimeCheckBox.text")); // NOI18N
        realTimeCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.realTimeCheckBox.toolTipText")); // NOI18N
        realTimeCheckBox.setBorder(null);
        realTimeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realTimeCheckBoxActionPerformed(evt);
            }
        });

        isNDFCheckBox.setBackground(new java.awt.Color(255, 196, 196));
        org.openide.awt.Mnemonics.setLocalizedText(isNDFCheckBox, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.isNDFCheckBox.text")); // NOI18N
        isNDFCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isNDFCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout amountsPanelLayout = new javax.swing.GroupLayout(amountsPanel);
        amountsPanel.setLayout(amountsPanelLayout);
        amountsPanelLayout.setHorizontalGroup(
            amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountsPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(valueDateLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amountsPanelLayout.createSequentialGroup()
                        .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(amount1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fxRateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(amountsPanelLayout.createSequentialGroup()
                        .addComponent(valueDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(realTimeCheckBox)))
                .addGap(28, 28, 28)
                .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amountFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amount2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isNDFCheckBox, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        amountsPanelLayout.setVerticalGroup(
            amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(amountsPanelLayout.createSequentialGroup()
                .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fxRateLabel)
                    .addComponent(amount1Label)
                    .addComponent(amount2Label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(amountsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(amountsPanelLayout.createSequentialGroup()
                        .addComponent(valueDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 1, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, amountsPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(valueDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(realTimeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(isNDFCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        fwdRatePanel.setBackground(new java.awt.Color(202, 249, 221));

        fwdPointsFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fwdPointsFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.fwdPointsFormattedTextField.text")); // NOI18N
        fwdPointsFormattedTextField.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fwdPointsFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fwdPointsFormattedTextFieldActionPerformed(evt);
            }
        });
        fwdPointsFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fwdPointsFormattedTextFieldFocusLost(evt);
            }
        });
        fwdPointsFormattedTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fwdPointsFormattedTextFieldPropertyChange(evt);
            }
        });

        fwdPtsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(fwdPtsLabel, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.fwdPtsLabel.text")); // NOI18N

        rateLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(rateLabel3, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.rateLabel3.text")); // NOI18N

        rateLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(rateLabel4, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.rateLabel4.text")); // NOI18N

        fwdRateLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fwdRateLabel2.setForeground(new java.awt.Color(51, 51, 255));
        fwdRateLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(fwdRateLabel2, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.fwdRateLabel2.text")); // NOI18N
        fwdRateLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fwdRateLabel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fwdRateLabel2PropertyChange(evt);
            }
        });

        fwdRateLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fwdRateLabel1.setForeground(new java.awt.Color(51, 51, 255));
        fwdRateLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(fwdRateLabel1, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.fwdRateLabel1.text")); // NOI18N
        fwdRateLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        fwdRateLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fwdRateLabel1PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout fwdRatePanelLayout = new javax.swing.GroupLayout(fwdRatePanel);
        fwdRatePanel.setLayout(fwdRatePanelLayout);
        fwdRatePanelLayout.setHorizontalGroup(
            fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fwdRatePanelLayout.createSequentialGroup()
                .addGroup(fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fwdPointsFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fwdPtsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 159, Short.MAX_VALUE)
                .addGroup(fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fwdRateLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rateLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(fwdRatePanelLayout.createSequentialGroup()
                        .addComponent(fwdRateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addComponent(rateLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        fwdRatePanelLayout.setVerticalGroup(
            fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fwdRatePanelLayout.createSequentialGroup()
                .addGroup(fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fwdRateLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(fwdRateLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1)
                .addGroup(fwdRatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rateLabel3)
                    .addComponent(rateLabel4))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fwdRatePanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fwdPtsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fwdPointsFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        ccyPairComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ccyPairComboBox.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ccyPairComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccyPairComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabel1.text")); // NOI18N

        realTimeQuoteLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        realTimeQuoteLabel.setForeground(new java.awt.Color(0, 51, 255));
        realTimeQuoteLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(realTimeQuoteLabel, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.realTimeQuoteLabel.text")); // NOI18N
        realTimeQuoteLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        realTimeQuoteLabel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                realTimeQuoteLabelPropertyChange(evt);
            }
        });

        jLabelSpotInfo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabelSpotInfo, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabelSpotInfo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSpot, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabelSpot.text")); // NOI18N

        jPanelNDF.setBackground(new java.awt.Color(202, 249, 221));

        fixingDateChooser.setBackground(new java.awt.Color(202, 249, 221));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabel3.text")); // NOI18N

        jComboBoxDeliveryCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jLabelNDF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabelNDF, org.openide.util.NbBundle.getMessage(FXBasePanel.class, "FXBasePanel.jLabelNDF.text")); // NOI18N

        javax.swing.GroupLayout jPanelNDFLayout = new javax.swing.GroupLayout(jPanelNDF);
        jPanelNDF.setLayout(jPanelNDFLayout);
        jPanelNDFLayout.setHorizontalGroup(
            jPanelNDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNDFLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel2)
                .addGap(10, 10, 10)
                .addComponent(fixingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabelNDF, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBoxDeliveryCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(104, Short.MAX_VALUE))
        );
        jPanelNDFLayout.setVerticalGroup(
            jPanelNDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelNDFLayout.createSequentialGroup()
                .addComponent(jComboBoxDeliveryCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanelNDFLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanelNDFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fixingDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jPanelNDFLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanelNDFLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jLabelNDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanelNDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(buySellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(fwdRatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ccyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(45, 45, 45)
                                        .addComponent(jLabelSpotInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabelSpot, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(realTimeQuoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(amountsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buySellPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSpotInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ccyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(realTimeQuoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelSpot, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fwdRatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(amountsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelNDF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(210, 210, 210))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buyToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyToggleButtonActionPerformed
        if (relatedFxFwdPanel != null) {
            //on swaps : the fwd leads
            updateUIColor(sellColor);
        } else {
            // on spot & fwds
            updateUIColor(buyColor);
        }
        sellToggleButton.setSelected(false);
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        if (relatedFxFwdPanel != null && quantity.doubleValue() > 0) {
            //on swaps : the fwd leads
            quantityFormattedTextField.setText(decimalFormat.format(quantity.negate()));
            relatedFxFwdPanel.quantityFormattedTextField.setText(decimalFormat.format(quantity));
        } else if (quantity.doubleValue() < 0 && relatedFxFwdPanel == null) {
            // on spot & fwds
            quantityFormattedTextField.setText(decimalFormat.format(quantity.negate()));
        }
        calculateConvertedAmount();
        if (relatedFxFwdPanel != null) {
            relatedFxFwdPanel.calculateConvertedAmount();
        }
    }//GEN-LAST:event_buyToggleButtonActionPerformed

    public void setCcyPair(String ccyPair) {

        if (!isSecondaryPanel) {

            DomainValue domainValue = (DomainValue) DAOCallerAgent.callMethod(DomainValuesAccessor.class,
                    DomainValuesAccessor.GET_DOMAIN_VALUE_BY_NAME_AND_VALUE, "defaultCcyPair", ccyPair);
            try {
                BigDecimal quantity = BigDecimal.valueOf(Double.parseDouble(domainValue.getDescription()));
                setQuantity(quantity);
                calculateConvertedAmount();
                if (relatedFxFwdPanel != null) {
                    relatedFxFwdPanel.setCcyPair(ccyPair);
                    relatedFxFwdPanel.setQuantity(quantity.negate());
                    relatedFxFwdPanel.calculateConvertedAmount();
                }
            } catch (Exception e) {
                logger.warn("Empty or wrong default value");
                setQuantity(defaultQuantity);
            }
        }
        ccyPairComboBox.setSelectedItem(ccyPair);
    }

    public void setQuantity(BigDecimal quantity) {
        quantityFormattedTextField.setText(decimalFormat.format(quantity));
        if (quantity.doubleValue() > 0) {
            updateUIColor(buyColor);
            if (relatedFxFwdPanel != null) {
                relatedFxFwdPanel.updateUIColor(sellColor);
            }
        } else {
            updateUIColor(sellColor);
            if (relatedFxFwdPanel != null) {
                relatedFxFwdPanel.updateUIColor(buyColor);
            }
        }
    }

    private void sellToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellToggleButtonActionPerformed
        if (relatedFxFwdPanel != null) {
            //on swaps : the fwd leads
            updateUIColor(buyColor);
        } else {
            // on spot & fwds
            updateUIColor(sellColor);
        }
        buyToggleButton.setSelected(false);
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        if (relatedFxFwdPanel != null && quantity.doubleValue() < 0) {
            //on swaps : the fwd leads
            quantityFormattedTextField.setText(decimalFormat.format(quantity.negate()));
            relatedFxFwdPanel.quantityFormattedTextField.setText(decimalFormat.format(quantity));
        } else if (quantity.doubleValue() > 0 && relatedFxFwdPanel == null) {
            // on spot & fwds
            quantityFormattedTextField.setText(decimalFormat.format(quantity.negate()));
        }
        calculateConvertedAmount();
        if (relatedFxFwdPanel != null) {
            relatedFxFwdPanel.calculateConvertedAmount();
        }
    }//GEN-LAST:event_sellToggleButtonActionPerformed

    private void ccyPairComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccyPairComboBoxActionPerformed
        if (ccyPairComboBox.getSelectedItem() != null) {
            productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(ccyPairComboBox));
            setQuotes(quoteSet);
            if (productCcyPair != null) {
                String pair = productCcyPair.getShortName();
                if (pair.length() == 7) {
                    String cur1 = pair.substring(0, 3);
                    String cur2 = pair.substring(4, 7);
                    amount1Label.setText(cur1);
                    amount2Label.setText(cur2);
                    if (isFxFwd) { // on fwds look if it is a ndf
                        Currency currency1 = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE, cur1);
                        Currency currency2 = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE, cur2);
                        if (currency1 != null && currency1.isNonDeliverable()) {
                            NDFCurrency = cur1;
                            jPanelNDF.setVisible(true);
                        } else if (currency2 != null && currency2.isNonDeliverable()) {
                            NDFCurrency = cur2;
                            jPanelNDF.setVisible(true);
                        } else {
                            NDFCurrency = null;
                            jPanelNDF.setVisible(false);
                        }
                    }
                    changeRTQuoteSubscription();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Currency pair not found :" + ccyPairComboBox.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_ccyPairComboBoxActionPerformed

    private void calculateConvertedAmount() {
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        amountFormattedTextField.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(priceFormattedTextField).multiply(quantity).negate()));
        if (relatedFxFwdPanel != null && relatedFxFwdPanel.isFxFwd) {
            relatedFxFwdPanel.quantityFormattedTextField.setText(decimalFormat.format(quantity.negate()));
            relatedFxFwdPanel.calculateConvertedAmount();
        }
    }
    private void quantityFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityFormattedTextFieldFocusLost
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(priceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_quantityFormattedTextFieldFocusLost

    private void priceFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_priceFormattedTextFieldFocusLost
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(priceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_priceFormattedTextFieldFocusLost

    private void fwdPointsFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fwdPointsFormattedTextFieldFocusLost
        updateForwardWithFwdPoint();
    }//GEN-LAST:event_fwdPointsFormattedTextFieldFocusLost

    private void priceFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFormattedTextFieldActionPerformed
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(priceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_priceFormattedTextFieldActionPerformed

    private void amountFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountFormattedTextFieldActionPerformed
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        BigDecimal amount = GUIUtils.getComponentBigDecimalValue(amountFormattedTextField);
        priceFormattedTextField.setText(pointdecimalFormat.format(quantity.divide(amount, 20, RoundingMode.HALF_UP)));
    }//GEN-LAST:event_amountFormattedTextFieldActionPerformed

    private void priceFormattedTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_priceFormattedTextFieldPropertyChange
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(priceFormattedTextField.getText())) {
            calculateConvertedAmount();
            if (relatedFxFwdPanel != null) {
                relatedFxFwdPanel.jLabelSpotInfo.setText(priceFormattedTextField.getText());
                relatedFxFwdPanel.updateForwardWithFwdPoint();
            }
        }
    }//GEN-LAST:event_priceFormattedTextFieldPropertyChange

    private void fwdPointsFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fwdPointsFormattedTextFieldActionPerformed
        updateForwardWithFwdPoint();
    }//GEN-LAST:event_fwdPointsFormattedTextFieldActionPerformed

    private void realTimeQuoteLabelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_realTimeQuoteLabelPropertyChange
        if (realTimeCheckBox.isSelected()) {
            if (isFxFwd) {
                updateForwardWithFwdPoint();
            } else {
                priceFormattedTextField.setText(realTimeQuoteLabel.getText());
                calculateConvertedAmount();
            }
        }
    }//GEN-LAST:event_realTimeQuoteLabelPropertyChange

    private void fwdRateLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fwdRateLabel1PropertyChange
        if (realTimeCheckBox.isSelected()) {
            updateForwardPointsWithRate();
        }
    }//GEN-LAST:event_fwdRateLabel1PropertyChange

    private void fwdRateLabel2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fwdRateLabel2PropertyChange
        if (realTimeCheckBox.isSelected()) {
            updateForwardPointsWithRate();
        }
    }//GEN-LAST:event_fwdRateLabel2PropertyChange

    private void fwdPointsFormattedTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fwdPointsFormattedTextFieldPropertyChange
        updateForwardWithFwdPoint();
    }//GEN-LAST:event_fwdPointsFormattedTextFieldPropertyChange

    private void realTimeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeCheckBoxActionPerformed
        if (relatedFxFwdPanel != null) {
            relatedFxFwdPanel.realTimeCheckBox.setSelected(this.realTimeCheckBox.isSelected());
        }
    }//GEN-LAST:event_realTimeCheckBoxActionPerformed

    private void isNDFCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isNDFCheckBoxActionPerformed
        if (isNDFCheckBox.isSelected()) {
            NDFCurrency = productCcyPair.getShortName().substring(0, 3);
            jPanelNDF.setVisible(true);
        } else {
            NDFCurrency = null;
            jPanelNDF.setVisible(false);
        }
    }//GEN-LAST:event_isNDFCheckBoxActionPerformed

    private void valueDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_valueDateChooserPropertyChange
        updateRTMaturity();
    }//GEN-LAST:event_valueDateChooserPropertyChange

    private void quantityFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityFormattedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityFormattedTextFieldActionPerformed

    public void updateForwardPointsWithRate() {
        String pricer = parentTopComponent.getNpvPricerName();
        BigDecimal fwdPoints;
        if (pricer != null && pricer.equalsIgnoreCase(PricerFXForward.class.getSimpleName())) {
            fwdPoints = GUIUtils.getComponentBigDecimalValue(fwdRateLabel1).divide(rateMultiplicator, 20, RoundingMode.UP);
            fwdPoints = fwdPoints.divide(productCcyPair.getProductForex().getTickSize(), 20, RoundingMode.UP);
        } else {
            BigDecimal rate1 = GUIUtils.getComponentBigDecimalValue(fwdRateLabel1).divide(rateMultiplicator, 20, RoundingMode.UP);
            BigDecimal rate2 = GUIUtils.getComponentBigDecimalValue(fwdRateLabel2).divide(rateMultiplicator, 20, RoundingMode.UP);
            fwdPoints = BigDecimal.ONE;
            if (getBackground().equals(this.buyColor) && rate2.doubleValue() != 0) {
                fwdPoints = rate1.add(BigDecimal.ONE).divide(rate2.add(BigDecimal.ONE), 20, RoundingMode.UP).subtract(BigDecimal.ONE);
                fwdPoints = fwdPoints.divide(productCcyPair.getProductForex().getTickSize(), 20, RoundingMode.UP);
                fwdPointsFormattedTextField.setText(pointdecimalFormat.format(fwdPoints));
            } else if (rate1.doubleValue() != 0) {
                fwdPoints = rate2.add(BigDecimal.ONE).divide(rate1.add(BigDecimal.ONE), 20, RoundingMode.UP).subtract(BigDecimal.ONE);
                fwdPoints = fwdPoints.divide(productCcyPair.getProductForex().getTickSize(), 20, RoundingMode.UP);

            }
        }
        fwdPointsFormattedTextField.setText(pointdecimalFormat.format(fwdPoints));
        updateForwardWithFwdPoint(fwdPoints);
    }

    private void updateUIColor(Color color) {
        setBackground(color);
        fwdRatePanel.setBackground(color);
        buySellPanel.setBackground(color);
        amountsPanel.setBackground(color);
        realTimeCheckBox.setBackground(color);
        jPanelNDF.setBackground(color);
        isNDFCheckBox.setBackground(color);
        valueDateChooser.setBackground(color);
        if (relatedFxFwdPanel != null) {
            if (buyColor.equals(color)) {
                relatedFxFwdPanel.updateUIColor(sellColor);
            } else {
                relatedFxFwdPanel.updateUIColor(buyColor);
            }
        }
    }

    public void setRelatedFxPanel(FXBasePanel _relatedFxPanel, FxGlobalInfoPanel globalInfo) {
        relatedFxFwdPanel = _relatedFxPanel;
        updateUIColor(getBackground());
        relatedGlobalinfoPanel = globalInfo;
    }

    public void setParentTopComponent(GaiaTradeTopComponent parentTopComponent) {
        this.parentTopComponent = parentTopComponent;
    }

    public void setFXFwd(boolean isFwd) {
        isFxFwd = isFwd;
        fwdRatePanel.setVisible(isFwd);
        isNDFCheckBox.setVisible(isFwd);
        if (isFwd) {
            fxRateLabel.setText(Trade.TradeType.FORWARD.name);
        } else {
            fxRateLabel.setText(Trade.TradeType.SPOT.name);
        }
        if (!isFwd) {
            jPanelNDF.setVisible(false);
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amount1Label;
    private javax.swing.JLabel amount2Label;
    private javax.swing.JFormattedTextField amountFormattedTextField;
    private javax.swing.JPanel amountsPanel;
    private javax.swing.JPanel buySellPanel;
    private javax.swing.JToggleButton buyToggleButton;
    private javax.swing.JComboBox ccyPairComboBox;
    private com.toedter.calendar.JDateChooser fixingDateChooser;
    public javax.swing.JFormattedTextField fwdPointsFormattedTextField;
    private javax.swing.JLabel fwdPtsLabel;
    private org.gaia.gui.common.GaiaRTLabel fwdRateLabel1;
    private org.gaia.gui.common.GaiaRTLabel fwdRateLabel2;
    private javax.swing.JPanel fwdRatePanel;
    private javax.swing.JLabel fxRateLabel;
    private javax.swing.JCheckBox isNDFCheckBox;
    private javax.swing.JComboBox jComboBoxDeliveryCurrency;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelNDF;
    private javax.swing.JLabel jLabelSpot;
    private javax.swing.JLabel jLabelSpotInfo;
    private javax.swing.JPanel jPanelNDF;
    public javax.swing.JFormattedTextField priceFormattedTextField;
    private javax.swing.JFormattedTextField quantityFormattedTextField;
    private javax.swing.JLabel rateLabel2;
    private javax.swing.JLabel rateLabel3;
    private javax.swing.JLabel rateLabel4;
    public javax.swing.JCheckBox realTimeCheckBox;
    private org.gaia.gui.common.GaiaRTLabel realTimeQuoteLabel;
    private javax.swing.JToggleButton sellToggleButton;
    private com.toedter.calendar.JDateChooser valueDateChooser;
    private javax.swing.JLabel valueDateLabel;
    // End of variables declaration//GEN-END:variables

    public void setQuotes(String quoteSet) {
        if (ccyPairComboBox.getSelectedItem() != null && productCcyPair != null) {
            MarketQuote quote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_LAST_QUOTE,
                    productCcyPair.getId(), DateUtils.getDate(), quoteSet);
            BigDecimal close;
            if (quote == null) {
                close = BigDecimal.ZERO;
            } else {
                close = quote.getClose();
            }
            priceFormattedTextField.setText(pointdecimalFormat.format(close));
            jLabelSpotInfo.setText(pointdecimalFormat.format(close));
            calculateConvertedAmount();
            if (isFxFwd && productCcyPair.getProductForex() != null) {
                updateFwdPoints();
            }
        }
    }

    private BigDecimal updateFwdPoints() {
        BigDecimal fwdPoint = (BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_FORWARD_POINTS, productCcyPair.getProductForex()
                .getCurrency1().getShortName(), productCcyPair.getProductForex()
                .getCurrency2().getShortName(), valueDateChooser.getDate());
        if (fwdPoint != null) {
            fwdPointsFormattedTextField.setText(pointdecimalFormat.format(fwdPoint));
            calculateConvertedAmount();
        } else {
            fwdPoint = BigDecimal.ZERO;
        }
        return fwdPoint;
    }

    private synchronized void updateForwardWithFwdPoint(BigDecimal fwdPoints) {
        if (productCcyPair != null && productCcyPair.getProductForex() != null && fwdPoints != null) {

            fwdPoints = fwdPoints.multiply(productCcyPair.getProductForex().getTickSize());
            BigDecimal price;
            if (realTimeCheckBox.isSelected()) {
                price = GUIUtils.getComponentBigDecimalValue(realTimeQuoteLabel);
            } else {
                price = GUIUtils.getComponentBigDecimalValue(jLabelSpotInfo);
            }
            priceFormattedTextField.setText(pointdecimalFormat.format(fwdPoints.add(price)));

            calculateConvertedAmount();
        }
    }

    private void updateForwardWithFwdPoint() {
        BigDecimal fwdPoints = GUIUtils.getComponentBigDecimalValue(fwdPointsFormattedTextField);
        updateForwardWithFwdPoint(fwdPoints);
    }

    public void closeComponent() {
        if (isFxFwd) {
            fwdRateLabel1.stopRTQuoteSubscription();
            fwdRateLabel2.stopRTQuoteSubscription();
        }
        realTimeQuoteLabel.stopRTQuoteSubscription();
    }

    public BigDecimal getFwdPoints() {
        try {
            BigDecimal pts = BigDecimal.valueOf(GaiaTopComponent.numberFormat.parse(fwdPointsFormattedTextField.getText()).doubleValue());
            return pts.multiply(productCcyPair.getProductForex().getTickSize());
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return BigDecimal.ZERO;
    }
}
