/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.trades;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.CurveUtils;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.assets.BondsTopComponent;
import org.gaia.gui.assets.CDSIndexTopComponent;
import org.gaia.gui.assets.CDSProductTopComponent;
import org.gaia.gui.assets.EquityTopComponent;
import org.gaia.gui.assets.FundTopComponent;
import org.gaia.gui.assets.ScheduleTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.gaia.io.XMLExporter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component asset trades
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//AssetTrade//EN", autostore = false)
@TopComponent.Description(preferredID = "AssetTradeTopComponent", iconBase = "org/gaia/gui/trades/deal.png", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.AssetTradeTopComponent")
@ActionReference(path = "Menu" + MenuManager.AssetTradeTopComponentMenu, position = MenuManager.AssetTradeTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_AssetTradeAction")
@Messages({"CTL_AssetTradeAction=Asset Trade", "CTL_AssetTradeTopComponent=Asset Trade", "HINT_AssetTradeTopComponent=This is an Asset Trade window"})
public class AssetTradeTopComponent extends GaiaTradeTopComponent {

    private AssetFinder assetFinder = null;
    private static final Logger logger = Logger.getLogger(AssetTradeTopComponent.class);

    public AssetTradeTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_AssetTradeTopComponent());
        setToolTipText(Bundle.HINT_AssetTradeTopComponent());
        availableProductTypes = new ArrayList<>();
        availableProductTypes.addAll(ProductTypeUtil.loadTypesByUse(ProductTypeUtil.ProductTypeUse.LISTED));
        availableProductTypes.addAll(ProductTypeUtil.loadTypesByUse(ProductTypeUtil.ProductTypeUse.CLEARED_OTC));
    }

    @Override
    protected void componentActivated() {
    }

    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null && getProduct() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = getName();
        }

        return _displayName;
    }

    @Override
    public void initContext() {
        try {

            jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
            pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
            setPricingEnv(pricingEnv);
            tradeTypeComboBox.addItem(Trade.TradeType.BUY_SELL.name);
            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jFormattedTextFieldSettleDate.setDate(DateUtils.getDate());
            jFormattedTextFieldQuantity.setHorizontalAlignment(JFormattedTextField.RIGHT);
            jFormattedTextFieldPrice.setHorizontalAlignment(JFormattedTextField.RIGHT);
            jFormattedTextFieldFXRate.setHorizontalAlignment(JFormattedTextField.RIGHT);
            jFormattedTextFieldAmount.setHorizontalAlignment(JFormattedTextField.RIGHT);
            isCash.setVisible(false);

            List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);

            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            List<String> currencies = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCIES, LoggedUser.getLoggedUserId());
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            GUIUtils.fillCombo(jComboBoxPriceCurrency, currencies);
            GUIUtils.fillCombo(jComboBoxAmountCurrency, currencies);

            List<String> statusList = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
            GUIUtils.fillCombo(statusComboBox, statusList);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW);

            List<Date> immDates = ProductAccessor.getCdsImmDates();
            GUIUtils.fillCombo(jComboBoxMaturity, GUIUtils.formatDateList(immDates, dateFormatter));

            /**
             * ShortCut for Amount and date
             */
            AmountShortCut.eventkey(jFormattedTextFieldQuantity);
            AmountShortCut.eventkey(jFormattedTextFieldPrice);
            AmountShortCut.eventkey(jFormattedTextFieldAmount);
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldSettleDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    public void isMargin(boolean isMargin) {
        paymentPanel.setVisible(!isMargin);
        isCash.setSelected(true);
        isCash.setVisible(isMargin);
        assetFinderPanel.setVisible(false);
        jComboBoxCurrency.setVisible(true);
    }

    @Override
    public void fillTrade() {
        if (getTrade() == null) {
            setTrade(new Trade());
            getTrade().setTrader(LoggedUser.getGaiaUser());
        }

        try {
            getTrade().setTradeDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));
            getTrade().setTradeType(GUIUtils.getComponentStringValue(tradeTypeComboBox));
            getTrade().setTradeTime(GUIUtils.getComponentTimeValue(jTextFieldTradeTime));
            getTrade().setQuantityType(GUIUtils.getComponentStringValue(jComboBoxQuantityNotional));

            /**
             * Listed
             */
            if (!jLabelProductId.getText().isEmpty()) {
                Integer productId = Integer.parseInt(jLabelProductId.getText().toString());
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));
                if (getProduct() != null) {
                    getTrade().setProduct(getProduct());
                    double mult = ProductAccessor.getMultiplierFromQuotationType(getProduct().getQuotationType());
                    getTrade().setPrice(BigDecimal.valueOf(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldPrice).doubleValue() / mult));

                }
            }
            getTrade().setQuantity(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity));
            getTrade().setAmount(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldAmount));
            getTrade().setForexRate(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldFXRate));
            getTrade().setValueDate(GUIUtils.getComponentDateValue(jFormattedTextFieldSettleDate));
            getTrade().setStatus(GUIUtils.getComponentStringValue(statusComboBox));
            getTrade().setPriceCurrency(GUIUtils.getComponentStringValue(jComboBoxPriceCurrency));
            getTrade().setSettlementCurrency(GUIUtils.getComponentStringValue(jComboBoxAmountCurrency));
            getTrade().setLifeCycleStatus(lifeCycleStatusTextField.getText());

            LegalEntity internalCounterparty = null;
            if (jComboBoxInternalCounterparty.getSelectedItem() != null && !jComboBoxInternalCounterparty.getSelectedItem().toString().isEmpty()) {
                internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxInternalCounterparty.getSelectedItem().toString());
            }
            getTrade().setInternalCounterparty(internalCounterparty);

            LegalEntity counterpart = null;
            if (!StringUtils.isEmptyString(counterpartyTextField.getText())) {
                counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
            }
            getTrade().setCounterparty(counterpart);

            /**
             * for cds only
             */
            if (getProduct() != null) {
                getTrade().setPriceType(ProductTypeUtil.getProductTypeByName(getProduct().getProductType()).getDefaultQuotationType().getName());
                if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                        || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX.name)
                        || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH.name)) {

                    Date maturity = GUIUtils.getComponentDateValue(jComboBoxMaturity);
                    if (maturity != null) {
                        if (!maturity.equals(getProduct().getMaturityDate())) {
                            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product.getProductId());
                            String issuerName = StringUtils.EMPTY_STRING;
                            if (issuer != null) {
                                issuerName = issuer.getShortName();
                            }
                            Product cdsProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_TYPE_AND_ISSUER_AND_MATURITY, getProduct().getProductType(), issuerName, maturity);
                            if (cdsProduct == null) {
                                cdsProduct = new Product();

                                cdsProduct.setProductType(ProductTypeUtil.ProductType.CDS_PRODUCT.name);
                                cdsProduct.setCalendar(getProduct().getCalendar());
                                cdsProduct.setIsAsset(false);
                                cdsProduct.setStartDate(getTrade().getValueDate());
                                cdsProduct.setMaturityDate(maturity);
                                cdsProduct.setLongName(getProduct().getLongName() + StringUtils.SPACE + dateFormatter.format(maturity));
                                cdsProduct.setShortName(getProduct().getShortName() + StringUtils.SPACE + dateFormatter.format(maturity));
                                cdsProduct.setScheduler(getProduct().getScheduler());
                                cdsProduct.setProductRate(getProduct().getProductRate());
                                cdsProduct.setProductCredit(getProduct().getProductCredit());
                                cdsProduct.setQuantityType(getProduct().getQuantityType());
                                cdsProduct.setQuotationType(getProduct().getQuotationType());
                                cdsProduct.setStartDate(getTrade().getTradeDate());
                                cdsProduct.setNotionalMultiplier(BigDecimal.ONE);
                                if (getProduct().getNotionalCurrency() != null) {
                                    cdsProduct.setNotionalCurrency(getProduct().getNotionalCurrency());
                                } else {
                                    cdsProduct.setNotionalCurrency(getTrade().getSettlementCurrency());
                                }
                                DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, cdsProduct);
                            }
                            setProduct(cdsProduct);
                        }
                    }
                }
            }

        } catch (NumberFormatException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    /**
     * loading when you hit enter in trade id
     *
     */
    public class EnterListener implements ActionListener {

        public EnterListener(JTextField jTextFieldTradeId) {
            KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
            jTextFieldTradeId.registerKeyboardAction(this, "Enter", enter, JComponent.WHEN_FOCUSED);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().compareTo("Enter") == 0) {
                loadTrade();
            }
        }
    }

    /**
     * load By Trade Id
     *
     * @param id
     */
    @Override
    public void loadByTradeId(Integer id) {
        jTextFieldTradeId.setText(id.toString());
        loadTrade();
    }

    @Override
    public void loadByTrade(Trade _trade) {
        isLoading = true;
        setTrade(_trade);
        fillWindowWithTrade(_trade);
        isLoading = false;
    }

    /**
     * load Trade
     */
    public void loadTrade() {
        isLoading = true;
        Integer tradeId;
        if (NumberUtils.isInteger(jTextFieldTradeId.getText())) {
            tradeId = Integer.parseInt(jTextFieldTradeId.getText());
        } else {
            tradeId = openTradeFinder();
        }
        if (tradeId != null) {
            try {

                setTrade((Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId));
                if (TradeUtils.isAssetTrade(getTrade()) || getTrade().getIsCollateral()) {
                    fillWindowWithTrade(getTrade());
                    setIsTradeAudited(false);
                } else {
                    TradeUtils.openTrade(this, getTrade().getId(), false);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }

        isLoading = false;
    }

    /**
     * fill Window With Trade
     *
     * @param _trade
     */
    public void fillWindowWithTrade(Trade _trade) {
        if (_trade != null) {
            setTrade(_trade);
            if (_trade.getTradeId() != null) {
                jTextFieldTradeId.setText(_trade.getTradeId().toString());
            }

            if (getTrade().getTradeType() != null) {
                tradeTypeComboBox.setSelectedItem(getTrade().getTradeType());
            }
            if (getTrade().getInternalCounterparty() != null) {
                jComboBoxInternalCounterparty.setSelectedItem(getTrade().getInternalCounterparty().getShortName());
            }
            if (getTrade().getCounterparty() != null) {
                counterpartyTextField.setText(getTrade().getCounterparty().getShortName());
            }
            if (getTrade().getTradeDate() != null) {
                jSpinnerTradeDate.setDate(getTrade().getTradeDate());
            }
            if (getTrade().getTradeTime() != null) {
                jTextFieldTradeTime.setText(timeFormatter.format(getTrade().getTradeTime()));
            }

            statusComboBox.setSelectedItem(getTrade().getStatus());
            lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
            jTextFieldAssetName.setText(getTrade().getProduct().getShortName());

            if (getTrade().getQuantity() != null) {
                jFormattedTextFieldQuantity.setText(decimalFormatWithOptionalDecimals.format(getTrade().getQuantity()));
            }

            jComboBoxQuantityNotional.setSelectedItem(getTrade().getQuantityType());
            if (getTrade().getPrice() != null) {
                double mult = ProductAccessor.getMultiplierFromQuotationType(getProduct().getQuotationType());
                jFormattedTextFieldPrice.setText(decimalFormat.format(getTrade().getPrice().multiply(BigDecimal.valueOf(mult))));
            }
            if (getTrade().getAmount() != null) {
                jFormattedTextFieldAmount.setText(decimalFormat.format(getTrade().getAmount()));
            }
            if (getTrade().getForexRate() != null) {
                jFormattedTextFieldFXRate.setText(decimalFormatWithOptionalDecimals.format(getTrade().getForexRate()));
            }

            jComboBoxPriceCurrency.setSelectedItem(getTrade().getPriceCurrency());
            jComboBoxAmountCurrency.setSelectedItem(getTrade().getSettlementCurrency());
            if (getTrade().getValueDate() != null) {
                jFormattedTextFieldSettleDate.setDate(getTrade().getValueDate());
            }
            loadProduct(getProduct());
            setDisplayName(getDisplayName());

        } else {
            (new ErrorMessageUI("No such trade.")).setVisible(true);
        }

    }

    public void loadProduct(Product product) {

        jLabelProductId.setText(product.getId().toString());

        if (product.getQuantityType() != null) {
            if (product.getQuantityType().equals(Trade.QuantityType.NOTIONAL.name)) {
                jComboBoxQuantityNotional.setSelectedItem(Trade.QuantityType.NOTIONAL.name);
                jComboBoxCurrency.setVisible(true);
                jComboBoxPriceCurrency.setVisible(false);
                jComboBoxCurrency.setSelectedItem(product.getNotionalCurrency());
                priceQualificatorLabel.setVisible(true);
                if (getProduct().getQuotationType().equals(MarketQuote.QuotationType.CLEAN.getName())
                        || getProduct().getQuotationType().equals(MarketQuote.QuotationType.DIRTY.getName())
                        || getProduct().getQuotationType().equals(MarketQuote.QuotationType.RATE.getName())) {
                    priceQualificatorLabel.setText("%");
                } else if (getProduct().getQuotationType().equals(MarketQuote.QuotationType.SPREAD.getName())
                         ||getProduct().getQuotationType().equals(MarketQuote.QuotationType.BASIS_POINT.getName())) {
                    priceQualificatorLabel.setText("bp");
                } else {
                    priceQualificatorLabel.setText(StringUtils.EMPTY_STRING);
                }
            } else {
                jComboBoxQuantityNotional.setSelectedItem(Trade.QuantityType.QUANTITY.name);
                jComboBoxCurrency.setVisible(false);
                jComboBoxPriceCurrency.setVisible(true);
                priceQualificatorLabel.setVisible(false);
            }
        }
        if (product.getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                || product.getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                || product.getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX.name)) {
            jLabelPrice.setText("Quoted Spread");
            jLabelAmount.setText("Upfront");
            jLabelCouponLabel.setVisible(true);
            jLabelCoupon.setVisible(true);
            jComboBoxMaturity.setVisible(true);
            jLabelMaturity.setVisible(true);
            addContractEventButton.setVisible(true);
            try {
                if (product.getProductRate() != null && product.getProductRate().getRate() != null) {
                    jLabelCoupon.setText(noDecimalFormat.format(product.getProductRate().getRate().multiply(BigDecimal.valueOf(10000))) + "bp");
                }
                if (product.getMaturityDate() != null) {
                    jComboBoxMaturity.setSelectedItem(dateFormatter.format(product.getMaturityDate()));
                } else {
                }
            } catch (Exception e) {
                logger.error("error " + StringUtils.formatErrorMessage(e));
            }
        } else {
            jLabelPrice.setText("Price");
            jLabelAmount.setText("Amount");
            jLabelCouponLabel.setVisible(false);
            jLabelCoupon.setVisible(false);
            jComboBoxMaturity.setVisible(false);
            jLabelMaturity.setVisible(false);
            addContractEventButton.setVisible(false);
        }
        jLabelProductType.setText(product.getProductType());
        jTextFieldAssetName.setText(product.getShortName());
        jLabelProductType.setText(product.getProductType());

        if ((getTrade() == null || getTrade().getTradeId() == null) && getProduct() != null) {
            jComboBoxPriceCurrency.setSelectedItem(getProduct().getNotionalCurrency());
            jComboBoxAmountCurrency.setSelectedItem(getProduct().getNotionalCurrency());
            Date settleDate;
            if (getProduct().getProductEquity() != null) {
                settleDate = DateUtils.addCalendarDay(DateUtils.getDate(), getProduct().getSettlementDelay());
            } else if (getProduct().getProductRate() != null) {
                settleDate = DateUtils.addCalendarDay(DateUtils.getDate(), getProduct().getSettlementDelay());
            } else {
                settleDate = DateUtils.getDate();
            }
            jFormattedTextFieldSettleDate.setDate(settleDate);
            if (getTrade() != null) {
                getTrade().setValueDate(settleDate);
            }
            jFormattedTextFieldFXRate.setText("1");
            jFormattedTextFieldFXRate.setEditable(false);
        }
    }

    public void calculate() {
        Double qtty = 0.;
        Double price = 0.;
        Double fxrate = 1.;
        Double amount = 0.;
        try {
            if (!jFormattedTextFieldQuantity.getText().isEmpty()
                    && !jFormattedTextFieldQuantity.getText().contains("m")
                    && !jFormattedTextFieldQuantity.getText().contains("k")) {
                qtty = numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue();
            }
            if (!jFormattedTextFieldPrice.getText().equals(StringUtils.EMPTY_STRING)) {
                double mult = 1;
                if (getProduct() != null) {
                    mult = ProductAccessor.getMultiplierFromQuotationType(getProduct().getQuotationType());
                }
                price = numberFormat.parse(jFormattedTextFieldPrice.getText()).doubleValue() / mult;
            }
            if (!jFormattedTextFieldFXRate.getText().equals(StringUtils.EMPTY_STRING)) {
                fxrate = numberFormat.parse(jFormattedTextFieldFXRate.getText()).doubleValue();
            }
            if (getProduct() != null) {

                if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                        || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                        || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX.name)) {

                    /**
                     * call the pricer cds isda
                     */
                    if (getTrade() == null) {
                        fillTrade();
                    }
                    if (getProduct() != null) {
                        getTrade().setProduct(getProduct());
                    }
                    Date maturity = GUIUtils.getComponentDateValue(jComboBoxMaturity);
                    getTrade().setMarketPrice(BigDecimal.valueOf(price));
                    getTrade().setQuantity(BigDecimal.valueOf(qtty));
                    if (price==0){
                        BigDecimal spread=(BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_SPREAD, getProduct());
                        if (spread!=null){
                            getTrade().setMarketPrice(spread);
                            jFormattedTextFieldPrice.setText(decimalFormat.format(spread.multiply(new BigDecimal(10000))));
                        }
                    }
                    if (maturity != null) {
                        Date valueDate = DateUtils.getDate();
//                        getTrade().setValueDate(valueDate);
                        amount = (Double) DAOCallerAgent.callMethod(PricingBuilder.class, PricingBuilder.GET_CDS_UPFRONT, getTrade(), valueDate, getPricingEnv());
                        if (price == 0 && qtty.doubleValue() != 0) {
                            getTrade().setPrice(new BigDecimal(amount / qtty.doubleValue()));
//                            jFormattedTextFieldPrice.setText(decimalFormat.format(price));
                        }
                    }
                } else if (getProduct().getQuotationType().equals(MarketQuote.QuotationType.CLEAN.getName())
                        || getProduct().getQuotationType().equals(MarketQuote.QuotationType.DIRTY.getName())
                        || getProduct().getQuotationType().equals(MarketQuote.QuotationType.RATE.getName())) {
                    amount = -1 * qtty * price * fxrate;
                } else if (getProduct().getIsAsset() == false) {
                    amount = -1 * qtty * price;
                } else {
                    amount = -1 * qtty * price * fxrate;
                }
            } else {
                amount = -1 * qtty * price * fxrate;
            }
        } catch (Exception e) {
            logger.error("error " + StringUtils.formatErrorMessage(e));
        }
        if (amount != null) {
            jFormattedTextFieldAmount.setText(decimalFormat.format(amount));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        windowToolBar = new javax.swing.JToolBar();
        jButtonPrice1 = new javax.swing.JButton();
        jButtonScheduler = new javax.swing.JButton();
        tradeDetailsButton1 = new javax.swing.JButton();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        addContractEventButton = new org.jdesktop.swingx.JXButton();
        jButtonExport1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonNew1 = new javax.swing.JButton();
        jButtonSaveAsNew = new javax.swing.JButton();
        jButtonSave1 = new javax.swing.JButton();
        jPanelAsset = new javax.swing.JPanel();
        jLabelProductId = new javax.swing.JLabel();
        jLabelSettlementDate = new javax.swing.JLabel();
        paymentPanel = new javax.swing.JPanel();
        jFormattedTextFieldPrice = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxPriceCurrency = new javax.swing.JComboBox();
        jLabelFXRate = new javax.swing.JLabel();
        jLabelPrice = new javax.swing.JLabel();
        jLabelCouponLabel = new javax.swing.JLabel();
        jFormattedTextFieldFXRate = new javax.swing.JFormattedTextField(decimalFormatWithOptionalDecimals);
        jLabelMaturity = new javax.swing.JLabel();
        jComboBoxAmountCurrency = new javax.swing.JComboBox();
        jFormattedTextFieldAmount = new javax.swing.JFormattedTextField(decimalFormat);
        jLabelAmount = new javax.swing.JLabel();
        jLabelCoupon = new javax.swing.JLabel();
        jComboBoxMaturity = new javax.swing.JComboBox();
        priceQualificatorLabel = new javax.swing.JLabel();
        assetFinderPanel = new javax.swing.JPanel();
        jButtonFindAsset = new javax.swing.JButton();
        jLabelAsset = new javax.swing.JLabel();
        jButtonShowAsset = new javax.swing.JButton();
        jTextFieldAssetName = new javax.swing.JTextField();
        isCash = new javax.swing.JCheckBox();
        jLabelProductType2 = new javax.swing.JLabel();
        jLabelProductType = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jComboBoxQuantityNotional = new javax.swing.JComboBox();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField(decimalFormatWithOptionalDecimals);
        jFormattedTextFieldSettleDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jPanel1 = new javax.swing.JPanel();
        jLabelBook = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jLabelCounterparty = new javax.swing.JLabel();
        jLabelTradeId = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        jButtonLoad = new javax.swing.JButton();
        jLabelTradeDate = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        tradeTypeLabel = new javax.swing.JLabel();
        tradeTypeComboBox = new javax.swing.JComboBox();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);

        jButtonPrice1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice1, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonPrice1.text")); // NOI18N
        jButtonPrice1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonPrice1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonPrice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrice1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonPrice1);

        jButtonScheduler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonScheduler, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonScheduler.text")); // NOI18N
        jButtonScheduler.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSchedulerActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonScheduler);

        tradeDetailsButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tradeDetailsButton1.setLabel(org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.tradeDetailsButton1.label")); // NOI18N
        tradeDetailsButton1.setMaximumSize(new java.awt.Dimension(80, 30));
        tradeDetailsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(tradeDetailsButton1);

        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadEventXButton.setMaximumSize(new java.awt.Dimension(90, 30));
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(loadEventXButton);

        org.openide.awt.Mnemonics.setLocalizedText(addContractEventButton, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.addContractEventButton.text")); // NOI18N
        addContractEventButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addContractEventButton.setMaximumSize(new java.awt.Dimension(90, 30));
        addContractEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContractEventButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(addContractEventButton);

        jButtonExport1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport1, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonExport1.text")); // NOI18N
        jButtonExport1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExport1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonExport1);

        jSeparator1.setMaximumSize(new java.awt.Dimension(16, 32767));
        jSeparator1.setPreferredSize(new java.awt.Dimension(6, 10));
        windowToolBar.add(jSeparator1);

        jButtonNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew1, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonNew1.text")); // NOI18N
        jButtonNew1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonNew1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonNew1);

        jButtonSaveAsNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonSaveAsNew.text")); // NOI18N
        jButtonSaveAsNew.setMaximumSize(new java.awt.Dimension(90, 30));
        jButtonSaveAsNew.setMinimumSize(new java.awt.Dimension(50, 30));
        jButtonSaveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNewActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSaveAsNew);

        jButtonSave1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave1, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonSave1.text")); // NOI18N
        jButtonSave1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonSave1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonSave1.setPreferredSize(new java.awt.Dimension(60, 23));
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSave1);

        jPanelAsset.setBackground(new java.awt.Color(254, 252, 254));
        jPanelAsset.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelAsset.setPreferredSize(new java.awt.Dimension(700, 188));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSettlementDate, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelSettlementDate.text")); // NOI18N

        paymentPanel.setBackground(new java.awt.Color(254, 252, 254));

        jFormattedTextFieldPrice.setName("jFormattedTextFieldPrice"); // NOI18N
        jFormattedTextFieldPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldPriceFocusLost(evt);
            }
        });
        jFormattedTextFieldPrice.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldPriceKeyPressed(evt);
            }
        });

        jComboBoxPriceCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPriceCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxPriceCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPriceCurrencyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFXRate, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelFXRate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPrice, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelPrice.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCouponLabel, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelCouponLabel.text")); // NOI18N

        jFormattedTextFieldFXRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldFXRate.setPreferredSize(new java.awt.Dimension(122, 20));
        jFormattedTextFieldFXRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFXRateActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaturity, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelMaturity.text")); // NOI18N

        jComboBoxAmountCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAmountCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxAmountCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAmountCurrencyActionPerformed(evt);
            }
        });

        jFormattedTextFieldAmount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldAmountFocusLost(evt);
            }
        });
        jFormattedTextFieldAmount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldAmountKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAmount, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelAmount.text")); // NOI18N

        jComboBoxMaturity.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxMaturity.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(priceQualificatorLabel, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.priceQualificatorLabel.text")); // NOI18N

        javax.swing.GroupLayout paymentPanelLayout = new javax.swing.GroupLayout(paymentPanel);
        paymentPanel.setLayout(paymentPanelLayout);
        paymentPanelLayout.setHorizontalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextFieldAmount)
                    .addComponent(jFormattedTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(priceQualificatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxAmountCurrency, 0, 75, Short.MAX_VALUE)
                    .addComponent(jComboBoxPriceCurrency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addComponent(jLabelCouponLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelCoupon, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelMaturity)
                    .addComponent(jLabelFXRate))
                .addGap(63, 63, 63)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBoxMaturity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        paymentPanelLayout.setVerticalGroup(
            paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentPanelLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabelFXRate))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBoxMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paymentPanelLayout.createSequentialGroup()
                        .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceQualificatorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxPriceCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(paymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxAmountCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabelCouponLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCoupon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        assetFinderPanel.setBackground(new java.awt.Color(254, 252, 254));

        jButtonFindAsset.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFindAsset, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonFindAsset.text")); // NOI18N
        jButtonFindAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindAssetActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAsset, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelAsset.text")); // NOI18N

        jButtonShowAsset.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowAsset, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonShowAsset.text")); // NOI18N
        jButtonShowAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowAssetActionPerformed(evt);
            }
        });

        jTextFieldAssetName.setBackground(new java.awt.Color(254, 252, 254));
        jTextFieldAssetName.setEnabled(false);

        isCash.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(isCash, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.isCash.text")); // NOI18N
        isCash.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isCashActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelProductType2, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelProductType2.text")); // NOI18N

        javax.swing.GroupLayout assetFinderPanelLayout = new javax.swing.GroupLayout(assetFinderPanel);
        assetFinderPanel.setLayout(assetFinderPanelLayout);
        assetFinderPanelLayout.setHorizontalGroup(
            assetFinderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetFinderPanelLayout.createSequentialGroup()
                .addComponent(jLabelAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jTextFieldAssetName, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButtonFindAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButtonShowAsset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(isCash)
                .addGap(36, 36, 36)
                .addComponent(jLabelProductType2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        assetFinderPanelLayout.setVerticalGroup(
            assetFinderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(assetFinderPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(assetFinderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(assetFinderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelProductType2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(isCash))
                    .addGroup(assetFinderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelAsset)
                        .addComponent(jTextFieldAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonFindAsset)
                        .addComponent(jButtonShowAsset)))
                .addContainerGap())
        );

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jComboBoxQuantityNotional.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuantityNotional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quantity", "Notional" }));
        jComboBoxQuantityNotional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxQuantityNotionalActionPerformed(evt);
            }
        });

        jFormattedTextFieldQuantity.setName("jFormattedTextFieldQuantity"); // NOI18N
        jFormattedTextFieldQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldQuantityFocusLost(evt);
            }
        });
        jFormattedTextFieldQuantity.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldQuantityKeyPressed(evt);
            }
        });

        jFormattedTextFieldSettleDate.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldSettleDate.setName("jFormattedTextFieldSettleDate"); // NOI18N

        javax.swing.GroupLayout jPanelAssetLayout = new javax.swing.GroupLayout(jPanelAsset);
        jPanelAsset.setLayout(jPanelAssetLayout);
        jPanelAssetLayout.setHorizontalGroup(
            jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAssetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelAssetLayout.createSequentialGroup()
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAssetLayout.createSequentialGroup()
                                .addComponent(jLabelSettlementDate, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(jFormattedTextFieldSettleDate, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(paymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(assetFinderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelAssetLayout.createSequentialGroup()
                                .addComponent(jComboBoxQuantityNotional, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelAssetLayout.setVerticalGroup(
            jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(assetFinderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxQuantityNotional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCurrency)
                        .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addComponent(paymentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldSettleDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelSettlementDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(89, 89, 89))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelBook.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxInternalCounterparty.setName("jComboBoxInternalCounterparty"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelCounterparty.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeId, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelTradeId.text")); // NOI18N

        jTextFieldTradeId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTradeId.setName("jTextFieldTradeId"); // NOI18N

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelTradeDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStatus, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jLabelStatus.text")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.counterpartyTextField.text")); // NOI18N

        legalEntityFinderButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(tradeTypeLabel, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.tradeTypeLabel.text")); // NOI18N

        tradeTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N
        jSpinnerTradeDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerTradeDatePropertyChange(evt);
            }
        });

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.jTextFieldTradeTime.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.lifeCycleStatusLabel.text")); // NOI18N

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(AssetTradeTopComponent.class, "AssetTradeTopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);
        lifeCycleStatusTextField.setName("lifeCycleStatusTextField"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTradeDate)
                            .addComponent(jLabelBook))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jComboBoxInternalCounterparty, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCounterparty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tradeTypeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(counterpartyTextField)
                            .addComponent(tradeTypeComboBox, 0, 153, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTradeId)
                            .addComponent(jLabelStatus))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBook)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCounterparty)
                    .addComponent(jLabelTradeId)
                    .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad)
                    .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalEntityFinderButton))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelStatus))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelTradeDate)
                            .addComponent(tradeTypeLabel)
                            .addComponent(tradeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelAsset, javax.swing.GroupLayout.DEFAULT_SIZE, 977, Short.MAX_VALUE)
                    .addComponent(windowToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * asset finder
     */
    private void jButtonFindAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindAssetActionPerformed

        List<ProductTypeUtil.ProductType> typeList = ProductTypeUtil.loadListed();
        typeList.add(ProductTypeUtil.ProductType.CDS_PRODUCT);
        typeList.add(ProductTypeUtil.ProductType.CDS_INDEX);
        typeList.remove(ProductTypeUtil.ProductType.CURRENCY_PAIR);

        assetFinder = new AssetFinder(typeList);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            if (productId != null) {
                try {
                    setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));
                    List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, productId);
                    getProduct().setProductReferences(references);

                    setDisplayName(getDisplayName());
                    loadProduct(getProduct());
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonFindAssetActionPerformed

    /**
     * price changed => calculate amount
     */
    private void jFormattedTextFieldPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPriceFocusLost
        if (!isLoading) {
            calculate();
        }
    }//GEN-LAST:event_jFormattedTextFieldPriceFocusLost

    /**
     * currency change
     */
    private void jComboBoxPriceCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPriceCurrencyActionPerformed
        if (!isLoading) {
            updateFxRate();
        }
    }//GEN-LAST:event_jComboBoxPriceCurrencyActionPerformed

    public void updateFxRate() {
        if (jComboBoxPriceCurrency.getSelectedItem() != null && jComboBoxAmountCurrency.getSelectedItem() != null) {
            if (jComboBoxPriceCurrency.getSelectedItem().toString().equals(jComboBoxAmountCurrency.getSelectedItem().toString())) {
                jFormattedTextFieldFXRate.setEditable(false);
                jFormattedTextFieldFXRate.setText("1");
            } else {
                jFormattedTextFieldFXRate.setEditable(true);
            }
        }
    }

    public void calculateSpread() {

        if (!isLoading) {
            if (getTrade() == null) {
                fillTrade();
            }
            Date valuationDate = new Date();
            Double qtty = 0.;
            Double amount = 0.;
            try {

                if (getProduct() != null) {
                    if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                            || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)
                            || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX.name)) {

                        if (!jFormattedTextFieldQuantity.getText().equals(StringUtils.EMPTY_STRING)) {
                            qtty = numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue();
                        }
                        if (!jFormattedTextFieldAmount.getText().equals(StringUtils.EMPTY_STRING)) {
                            amount = numberFormat.parse(jFormattedTextFieldAmount.getText()).doubleValue();
                        }

                        if (getTrade() == null) {
                            fillTrade();
                        }

                        getTrade().setAmount(BigDecimal.valueOf(amount));
                        getTrade().setQuantity(BigDecimal.valueOf(qtty));

                        Date maturity = GUIUtils.getComponentDateValue(jComboBoxMaturity);

                        Double spread = 0d;
                        if (maturity != null) {

                            spread = (Double) DAOCallerAgent.callMethod(PricingBuilder.class, PricingBuilder.GET_CDS_SPREAD, getTrade(), valuationDate, getPricingEnv());
                        }
                        if (qtty != 0) {
//                        double mult = ProductAccessor.getMultiplierFromQuotationType(getProduct().getQuotationType());
                            jFormattedTextFieldPrice.setText(numberFormat.format(spread * 10000));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("error " + StringUtils.formatErrorMessage(e));
            }
        }
    }

    /**
     * currency change
     */
    private void jComboBoxAmountCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAmountCurrencyActionPerformed

        updateFxRate();
    }//GEN-LAST:event_jComboBoxAmountCurrencyActionPerformed

    private void jFormattedTextFieldSettleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSettleDateActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldSettleDateActionPerformed

    private void jFormattedTextFieldFXRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFXRateActionPerformed
        if (!isLoading) {
            calculate();
        }
    }//GEN-LAST:event_jFormattedTextFieldFXRateActionPerformed

    private void jComboBoxQuantityNotionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxQuantityNotionalActionPerformed

        if (jComboBoxQuantityNotional.getSelectedItem().toString().equals(Trade.QuantityType.QUANTITY)) {
            if (!getTrade().getIsCollateral()) {
                jComboBoxCurrency.setVisible(false);
                jComboBoxPriceCurrency.setVisible(true);
            }

        } else {
            jComboBoxCurrency.setVisible(true);
            jComboBoxPriceCurrency.setVisible(false);
        }
    }//GEN-LAST:event_jComboBoxQuantityNotionalActionPerformed

    /**
     * Show asset
     */
    private void jButtonShowAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowAssetActionPerformed

        if (getProduct() != null && getProduct().getProductType() != null) {
            WindowManager wm = WindowManager.getDefault();
            if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.STOCK.getName())) {
                EquityTopComponent topComponenct = (EquityTopComponent) wm.findTopComponent(EquityTopComponent.class.getSimpleName());
                topComponenct.open();
                topComponenct.load(getProduct().getId());
                topComponenct.requestActive();
            } else if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.FUND.getName())
                    || getProduct().getProductType().equals(ProductTypeUtil.ProductType.ETF.getName())
                    || getProduct().getProductType().equals(ProductTypeUtil.ProductType.FUND_Of_FUNDS.getName())) {
                FundTopComponent topComponenct = (FundTopComponent) wm.findTopComponent(FundTopComponent.class.getSimpleName());
                topComponenct.open();
                topComponenct.load(getProduct().getId());
                topComponenct.requestActive();
            } else if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.BOND.getName())) {
                BondsTopComponent topComponenct = (BondsTopComponent) wm.findTopComponent(BondsTopComponent.class.getSimpleName());
                topComponenct.open();
                topComponenct.load(getProduct().getId());
                topComponenct.requestActive();
            } else if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.getName())
                    || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.getName())) {
                CDSProductTopComponent topComponenct = (CDSProductTopComponent) wm.findTopComponent(CDSProductTopComponent.class.getSimpleName());
                topComponenct.open();
                topComponenct.load(getProduct().getId());
                topComponenct.requestActive();
            } else if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX.getName())
                    || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH.getName())) {
                CDSIndexTopComponent topComponenct = (CDSIndexTopComponent) wm.findTopComponent(CDSIndexTopComponent.class.getSimpleName());
                topComponenct.open();
                topComponenct.load(getProduct().getId());
                topComponenct.requestActive();
            }
        }

    }//GEN-LAST:event_jButtonShowAssetActionPerformed

    /**
     * load trade
     */
    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed

        loadTrade();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void isCashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isCashActionPerformed
        if (isCash.isSelected()) {
            try {
                assetFinderPanel.setVisible(false);
                jComboBoxCurrency.setVisible(true);
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboBoxCurrency.getSelectedItem().toString()));
                if (getProduct() != null) {
                    loadProduct(getProduct());
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        } else {
            assetFinderPanel.setVisible(true);
            jComboBoxCurrency.setVisible(false);
        }

    }//GEN-LAST:event_isCashActionPerformed

    private void legalEntityFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButtonActionPerformed

    private void jSpinnerTradeDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerTradeDatePropertyChange
        if (!isLoading) {
            fillTrade();
            Date settleDate = (Date) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_SETTLEMENT_DATE, getTrade());
            jFormattedTextFieldSettleDate.setDate(settleDate);
        }
    }//GEN-LAST:event_jSpinnerTradeDatePropertyChange

    private void addContractEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContractEventButtonActionPerformed
        showContractEventWindow();
    }//GEN-LAST:event_addContractEventButtonActionPerformed

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loadEventXButtonActionPerformed

    private void jButtonSaveAsNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNewActionPerformed

        if (getTrade() != null && getTrade().getId() != null) {
            setTrade(null);
            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
            fillTrade();
            setTrade(saveTrade(getTrade()));
            jTextFieldTradeId.setText(getTrade().getId().toString());
            setDisplayName(getDisplayName());
        } else {
            JOptionPane.showMessageDialog(this, "No trade loaded");
        }

    }//GEN-LAST:event_jButtonSaveAsNewActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed

        fillTrade();
        setTrade(saveTrade(getTrade()));
        if (getTrade().getId() != null) {
            jTextFieldTradeId.setText(getTrade().getId().toString());
            setDisplayName(getDisplayName());
        }

    }//GEN-LAST:event_jButtonSave1ActionPerformed

    private void jButtonNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNew1ActionPerformed
        clearFields(this);
        initContext();
    }//GEN-LAST:event_jButtonNew1ActionPerformed

    private void jButtonSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSchedulerActionPerformed

        fillTrade();
        ScheduleTopComponent scheduleTopComponent = new ScheduleTopComponent();
        scheduleTopComponent.setTrade(getTrade());
        scheduleTopComponent.open();
        scheduleTopComponent.requestActive();
    }//GEN-LAST:event_jButtonSchedulerActionPerformed

    private void tradeDetailsButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeDetailsButton1ActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_tradeDetailsButton1ActionPerformed

    private void jButtonPrice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrice1ActionPerformed
        price();
    }//GEN-LAST:event_jButtonPrice1ActionPerformed

    private void jButtonExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExport1ActionPerformed
        XMLExporter.export(getTrade(), this);
    }//GEN-LAST:event_jButtonExport1ActionPerformed

    private void jFormattedTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityFocusLost
        if (!isLoading) {
            calculate();
        }
    }//GEN-LAST:event_jFormattedTextFieldQuantityFocusLost

    private void jFormattedTextFieldPriceKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPriceKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            calculate();
        }
    }//GEN-LAST:event_jFormattedTextFieldPriceKeyPressed

    private void jFormattedTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            calculate();
        }
    }//GEN-LAST:event_jFormattedTextFieldQuantityKeyPressed

    private void jFormattedTextFieldAmountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAmountFocusLost
        calculateSpread();
    }//GEN-LAST:event_jFormattedTextFieldAmountFocusLost

    private void jFormattedTextFieldAmountKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldAmountKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            calculateSpread();
        }
    }//GEN-LAST:event_jFormattedTextFieldAmountKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton addContractEventButton;
    protected javax.swing.JPanel assetFinderPanel;
    private javax.swing.JTextField counterpartyTextField;
    public javax.swing.JCheckBox isCash;
    private javax.swing.JButton jButtonExport1;
    private javax.swing.JButton jButtonFindAsset;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew1;
    private javax.swing.JButton jButtonPrice1;
    private javax.swing.JButton jButtonSave1;
    private javax.swing.JButton jButtonSaveAsNew;
    private javax.swing.JButton jButtonScheduler;
    private javax.swing.JButton jButtonShowAsset;
    private javax.swing.JComboBox jComboBoxAmountCurrency;
    public javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JComboBox jComboBoxMaturity;
    private javax.swing.JComboBox jComboBoxPriceCurrency;
    public javax.swing.JComboBox jComboBoxQuantityNotional;
    private javax.swing.JFormattedTextField jFormattedTextFieldAmount;
    private javax.swing.JFormattedTextField jFormattedTextFieldFXRate;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrice;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantity;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldSettleDate;
    private javax.swing.JLabel jLabelAmount;
    private javax.swing.JLabel jLabelAsset;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelCoupon;
    private javax.swing.JLabel jLabelCouponLabel;
    private javax.swing.JLabel jLabelFXRate;
    private javax.swing.JLabel jLabelMaturity;
    private javax.swing.JLabel jLabelPrice;
    private javax.swing.JLabel jLabelProductId;
    private javax.swing.JLabel jLabelProductType;
    private javax.swing.JLabel jLabelProductType2;
    private javax.swing.JLabel jLabelSettlementDate;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JLabel jLabelTradeId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelAsset;
    private javax.swing.JToolBar.Separator jSeparator1;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    private javax.swing.JTextField jTextFieldAssetName;
    protected javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JButton legalEntityFinderButton;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    protected javax.swing.JPanel paymentPanel;
    private javax.swing.JLabel priceQualificatorLabel;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JButton tradeDetailsButton1;
    public javax.swing.JComboBox tradeTypeComboBox;
    private javax.swing.JLabel tradeTypeLabel;
    private javax.swing.JToolBar windowToolBar;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
