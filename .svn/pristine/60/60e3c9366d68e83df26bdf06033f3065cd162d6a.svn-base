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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.ScheduleTopComponent;
import org.gaia.gui.common.GaiaProductTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.pointDecimalFormat;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.gaia.io.XMLExporter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays forex options.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//Option//EN", autostore = false)
@TopComponent.Description(preferredID = "OptionTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "output", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.OptionTopComponent")
@ActionReference(path = "Menu"+MenuManager.FXOptionTopComponentMenu, position = MenuManager.FXOptionTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_OptionAction")
@Messages({"CTL_OptionAction=FX Option", "CTL_OptionTopComponent=FX Option", "HINT_OptionTopComponent=This is a FX Option window"})
public final class FXOptionTopComponent extends GaiaTradeTopComponent {

    private static final Logger logger = Logger.getLogger(FXOptionTopComponent.class);

    public FXOptionTopComponent() {
        initComponents();
        setName(Bundle.CTL_OptionTopComponent());
        setToolTipText(Bundle.HINT_OptionTopComponent());
        initContext();
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_OPTION);
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_VANILLA_OPTION);
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_BARRIER_OPTION);
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_BINARY_OPTION);
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_DIGITAL_OPTION);
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_COMPLEX_OPTION);
    }

    /**
     * initialize component
     */
    @Override
    public void initContext() {

        jComboBoxExerciceType.setModel(new DefaultComboBoxModel(ProductConst.ExerciseType.values()));
        jComboBoxType.setModel(new DefaultComboBoxModel(ProductConst.OptionType.values()));
        jComboBoxType.setSelectedIndex(0);
        jDateChooserMaturityDate.setDate(DateUtils.getDate());
        jDateChooserMaturityDate.setDateFormatString(GaiaProductTopComponent.dateFormat);
        List<String> listProducts = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIRS, LoggedUser.getLoggedUserId());
        for (String prd : listProducts) {
            ccyPairComboBox.addItem(prd);
        }
        String pair = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIR, LoggedUser.getLoggedUserId());
        ccyPairComboBox.setSelectedItem(pair);
        jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
        ActionListener listener = new FXOptionTopComponent.EnterListener(jTextFieldTradeId);
        tradeTypeComboBox.addItem(Trade.TradeType.BUY_SELL.name);
        jSpinnerTradeDate.setDate(DateUtils.getDate());
        jDateChooserValueDate.setDate(DateUtils.addOpenDay(DateUtils.getDate(), 2));
        List<String> statusList = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
        GUIUtils.fillCombo(statusComboBox, statusList);
        statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
        List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
        GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);
        List<String> barriers = ProductConst.BarrierType.getBarrierTypes();
        GUIUtils.fillComboWithNullFirst(jComboBoxBarrier, barriers);

        List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        GUIUtils.fillCombo(jComboBoxCurrency1, currencies);
        GUIUtils.fillCombo(jComboBoxCurrency2, currencies);
        GUIUtils.fillCombo(jComboBoxCurrencyPremium, currencies);
        String defaultCurrency = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId());
        jComboBoxCurrencyPremium.setSelectedItem(defaultCurrency);
        lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);

        /**
         * ShortCut for Amout and date
         */
        AmountShortCut.eventkey(jFormattedTextFieldQuantity);
        AmountShortCut.eventkey(jFormattedTextFieldPremium);

        DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserMaturityDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserValueDate.getComponent(1));
    }

    /**
     * load Trade
     */
    public void loadTrade() {
        Integer tradeId;
        if (NumberUtils.isInteger(jTextFieldTradeId.getText())) {
            tradeId = Integer.parseInt(jTextFieldTradeId.getText());
        } else {
            tradeId = openTradeFinder();
        }
        if (tradeId != null) {
            isLoading = true;
            try {
                Trade trade = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId);
                setTrade(trade);
                fillWindow();

            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
            isLoading = false;
        }
    }

    @Override
    public void loadByTradeId(Integer id) {
        jTextFieldTradeId.setText(id.toString());
        setIsTradeAudited(false);
        loadTrade();
    }

    @Override
    public void loadByTrade(Trade _trade) {
        setTrade(_trade);
        fillWindow();

    }

    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null) {
            _displayName = getProduct().getProductType();
            if (getTrade().getId() != null) {
                _displayName += StringUtils.SPACE + String.valueOf(getTrade().getId());
            }
        } else {
            _displayName = getName();
        }

        return _displayName;
    }

    public void notifyCcyPairChange(String ccyPair) {
        // Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, ccyPair);

        if (ccyPair.length() == 7) {
            jComboBoxCurrency1.setSelectedItem(ccyPair.substring(0, 3));
            jComboBoxCurrency2.setSelectedItem(ccyPair.substring(4));
            updatePriceCombo();
        }
    }

    /**
     * loading when you hit enter in trade id
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
     * fill window
     */
    private void fillWindow() {
        jTextFieldTradeId.setText(getTrade() != null && getTrade().getId() != null ? getTrade().getId().toString() : StringUtils.EMPTY_STRING);
        statusComboBox.setSelectedItem(getTrade() != null ? getTrade().getStatus() : TradeAccessor.TradeStatus.NEW.name());
        jComboBoxCurrency1.setSelectedItem(getProduct() != null ? getProduct().getProductForex().getCurrency1().getShortName() : jComboBoxCurrency1.getSelectedIndex());
        jComboBoxCurrency2.setSelectedItem(getProduct() != null ? getProduct().getProductForex().getCurrency2().getShortName() : jComboBoxCurrency2.getSelectedIndex());
        jFormattedTextFieldQuantity.setText(getTrade() != null ? decimalFormat.format(getTrade().getQuantity().multiply(getProduct().getNotionalMultiplier())) : BigDecimal.ZERO.toString());
        jFormattedTextFieldPremium.setText(getTrade() != null ? decimalFormat.format(getTrade().getAmount()) : BigDecimal.ZERO.toString());
        jFormattedTextFieldConvertedAmount.setText(getTrade() != null ? decimalFormat.format(getTrade().getConvertedAmount().multiply(getProduct().getNotionalMultiplier())) : BigDecimal.ZERO.toString());
        jComboBoxCurrencyPremium.setSelectedItem(getTrade() != null ? getTrade().getSettlementCurrency() : jComboBoxCurrencyPremium.getSelectedIndex());
        jDateChooserValueDate.setDate(getTrade() != null ? getTrade().getValueDate() : DateUtils.getDate());
        jComboBoxInternalCounterparty.setSelectedItem(getTrade() != null ? getTrade().getInternalCounterparty().getShortName() : jComboBoxInternalCounterparty.getItemAt(0));
        counterpartyTextField.setText(getTrade() != null ? getTrade().getCounterparty().getShortName() : StringUtils.EMPTY_STRING);
        jTextFieldTradeTime.setText(getTrade() != null ? timeFormatter.format(getTrade().getTradeTime()) : timeFormatter.format(DateUtils.getDate()));
        if (getProduct() != null && getProduct().getProductForex() != null && getProduct().getSettlementDelay() != null) {
            payLagTextField.setText(getProduct().getSettlementDelay().toString());
        } else {
            payLagTextField.setText("2");
        }
        if (getTrade().getLifeCycleStatus() != null) {
            lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
        }
        priceFormattedTextField.setText(getTrade() != null ? decimalFormat.format(getTrade().getAmount().divide(getTrade().getQuantity(), 20, RoundingMode.UP)) : BigDecimal.ZERO.toString());
        if (getProduct() != null && getProduct().getProductForex() != null
                && getProduct().getProductForex().getCurrency1() != null && getProduct().getProductForex().getCurrency2() != null) {
            String ccypair = getProduct() != null ? getProduct().getProductForex().getCurrency1().getShortName() + "/"
                    + getProduct().getProductForex().getCurrency2().getShortName() : GUIUtils.getComponentStringValue(ccyPairComboBox);

            Product cCpair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, ccypair);
            ccyPairComboBox.setSelectedItem(cCpair != null ? cCpair.getShortName() : "EUR/USD");
        }
        jComboBoxExerciceType.setSelectedItem(getProduct() != null && getProduct().getProductForex() != null ? getProduct().getProductForex().getExerciceType() : ProductConst.ExerciseType.European.name());
        jComboBoxBarrier.setSelectedItem(getProduct() != null && getProduct().getProductForex() != null ? getProduct().getProductForex().getBarrierType() : null);
        barrierFormattedTextField.setText(getProduct() != null && getProduct().getProductForex() != null && getProduct().getProductForex().getBarrier() != null ? decimalFormat.format(getProduct().getProductForex().getBarrier()) : "");
        jComboBoxType.setSelectedItem(getProduct() != null ? getProduct().getProductForex().getOptionStyle() : StringUtils.EMPTY_STRING);
        jDateChooserMaturityDate.setDate(getProduct() != null ? getProduct().getMaturityDate() : DateUtils.getDate());
        jFormattedTextFieldStrike.setText(getProduct() != null ? decimalFormat.format(getProduct().getProductForex().getStrike()) : StringUtils.EMPTY_STRING);

        if (getProduct() != null && getProduct().getProductForex() != null && getProduct().getProductForex().getIsPaymentAtEnd()) {
            jCheckBoxPayementAtEnd.setSelected(true);
        } else {
            jCheckBoxPayementAtEnd.setSelected(false);
        }
        if (getProduct() != null && getProduct().getProductForex() != null && getProduct().getProductForex().getIsDigital() != null) {
            if (getProduct().getProductForex().getIsDigital()) {
                jCheckBoxisDigital.setSelected(true);
            } else {
                jCheckBoxisDigital.setSelected(false);
            }
        }

        jSpinnerTradeDate.setDate(getTrade() != null ? getTrade().getTradeDate() : DateUtils.getDate());
        setDisplayName(getDisplayName());
    }

    /**
     * fill trade
     */
    @Override
    public void fillTrade() {
        if (ccyPairComboBox.getSelectedItem() != null) {
            if (getTrade() == null) {
                setTrade(new Trade());
                setProduct(new Product());
                getProduct().setProductForex(new ProductForex());
                getProduct().setNotionalMultiplier(BigDecimal.ONE);
            }

            getTrade().setQuantityType(Trade.QuantityType.QUANTITY.name);
            LegalEntity counterpart;
            if (!StringUtils.isEmptyString(counterpartyTextField.getText())) {
                counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
                getTrade().setCounterparty(counterpart);
            }

            LegalEntity internalCounterparty = null;
            if (!StringUtils.isEmptyString(GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty))) {
                internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                        GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty));
            }
            if (getProduct() == null) {
                setProduct(new Product());
            }
            ProductForex productForex = getProduct().getProductForex();
            if (productForex == null) {
                productForex = new ProductForex();
            }
            Short lag;
            if (NumberUtils.isInteger(payLagTextField.getText())) {
                lag = new Short(payLagTextField.getText());
            } else {
                lag = new Short("2");
            }
            getProduct().setSettlementDelay(lag);

            String ccyPair = ccyPairComboBox.getSelectedItem().toString();
            Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, ccyPair);
            Product currency1 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, ccyPair.substring(0, 3));
            Product currency2 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, ccyPair.substring(4));
            productForex.setBarrierType(GUIUtils.getComponentStringValue(jComboBoxBarrier));
            productForex.setBarrier(GUIUtils.getComponentBigDecimalValue(barrierFormattedTextField));
            productForex.setExerciceType(GUIUtils.getComponentStringValue(jComboBoxExerciceType));
            productForex.setOptionStyle(GUIUtils.getComponentStringValue(jComboBoxType));
            getProduct().setMaturityDate(GUIUtils.getComponentDateValue(jDateChooserMaturityDate));
            productForex.setStrike(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldStrike));
            getProduct().setProductType(ProductTypeUtil.ProductType.FX_OPTION.name);
            productForex.setIsDigital(jCheckBoxisDigital.isSelected());
            productForex.setIsPaymentAtEnd(jCheckBoxPayementAtEnd.isSelected());
            productForex.setCurrency1(currency1);
            productForex.setCurrency2(currency2);
            String notionalCurrency;
            if (currency1 != null) {
                notionalCurrency = currency1.getShortName();
            } else {
                notionalCurrency = "EUR";
            }
            getProduct().setNotionalCurrency(notionalCurrency);
            productForex.setProduct(getProduct());
            getProduct().setProductForex(productForex);
            getProduct().getUnderlyingProducts().clear();
            getProduct().addUnderlying(underlying);
            getProduct().setStartDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));

            getTrade().setInternalCounterparty(internalCounterparty);
            getTrade().setTradeDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));
            getTrade().setTradeType(GUIUtils.getComponentStringValue(tradeTypeComboBox));
            getTrade().setTradeTime(GUIUtils.getComponentTimeValue(jTextFieldTradeTime));
            getTrade().setStatus(GUIUtils.getComponentStringValue(statusComboBox));
            getTrade().setSettlementCurrency(getProduct().getNotionalCurrency());
            getTrade().setPriceType(MarketQuote.QuotationType.PRICE.getName());
            getTrade().setValueDate(GUIUtils.getComponentDateValue(jDateChooserValueDate));
            BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity);
            if (getProduct().getNotionalMultiplier().doubleValue() != 0) {
                quantity = quantity.divide(getProduct().getNotionalMultiplier(), 20, RoundingMode.UP);
            }
            getTrade().setQuantity(quantity);
            getTrade().setConvertedAmount((getTrade().getQuantity()).multiply((getProduct().getProductForex().getStrike())).negate());
            getTrade().setAmount(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldPremium));
            getTrade().setSettlementCurrency(GUIUtils.getComponentStringValue(jComboBoxCurrencyPremium));
            getTrade().setProduct(getProduct());
            getTrade().setLifeCycleStatus(lifeCycleStatusTextField.getText());
        }
    }

    private void storeTrade() {
        fillTrade();
        setTrade(saveTrade(getTrade()));

        if (getTrade().getId() != null) {
            jTextFieldTradeId.setText(getTrade().getId().toString());
        }
    }

    public void updatePriceCombo() {
        priceComboBox.removeAllItems();
        String ccyPair = ccyPairComboBox.getSelectedItem().toString();
        String cur1 = ccyPair.substring(0, 3);
        String cur2 = ccyPair.substring(4);
        priceComboBox.addItem("% " + cur1);
        priceComboBox.addItem("% " + cur2);
        priceComboBox.addItem("M" + cur1);
        priceComboBox.addItem("M" + cur2);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        globalInfoPanel = new javax.swing.JPanel();
        statusComboBox = new javax.swing.JComboBox();
        jLabelTradeId = new javax.swing.JLabel();
        jButtonLoad = new javax.swing.JButton();
        jTextFieldTradeId = new javax.swing.JTextField();
        jLabelStatus = new javax.swing.JLabel();
        jLabelBook = new javax.swing.JLabel();
        jLabelTradeDate = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        tradeTypeLabel = new javax.swing.JLabel();
        jLabelCounterparty = new javax.swing.JLabel();
        tradeTypeComboBox = new javax.swing.JComboBox();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jDateChooserValueDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCurrency2 = new javax.swing.JComboBox();
        jComboBoxCurrency1 = new javax.swing.JComboBox();
        jLabelPremium = new javax.swing.JLabel();
        jComboBoxCurrencyPremium = new javax.swing.JComboBox();
        jLabelUnderlying = new javax.swing.JLabel();
        ccyPairComboBox = new javax.swing.JComboBox();
        jLabelStrike = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jLabelMaturityDate = new javax.swing.JLabel();
        jDateChooserMaturityDate = new com.toedter.calendar.JDateChooser(null,null,new GaiaJSpinnerDateEditor());
        jLabelExerciceType = new javax.swing.JLabel();
        jComboBoxExerciceType = new javax.swing.JComboBox();
        jCheckBoxisDigital = new javax.swing.JCheckBox();
        jLabelBarrier = new javax.swing.JLabel();
        jComboBoxBarrier = new javax.swing.JComboBox();
        jCheckBoxPayementAtEnd = new javax.swing.JCheckBox();
        jFormattedTextFieldStrike = new javax.swing.JFormattedTextField();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField();
        jFormattedTextFieldConvertedAmount = new javax.swing.JFormattedTextField();
        jFormattedTextFieldPremium = new javax.swing.JFormattedTextField();
        priceComboBox = new javax.swing.JComboBox();
        priceFormattedTextField = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        payLagTextField = new javax.swing.JTextField();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();
        priceRefreshButton = new javax.swing.JButton();
        windowToolBar = new javax.swing.JToolBar();
        jButtonPrice1 = new javax.swing.JButton();
        jButtonScheduler = new javax.swing.JButton();
        tradeDetailsButton1 = new javax.swing.JButton();
        loadEventXButton1 = new org.jdesktop.swingx.JXButton();
        addContractEventButton1 = new org.jdesktop.swingx.JXButton();
        jButtonExport = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jButtonNew1 = new javax.swing.JButton();
        jButtonSaveAsNew1 = new javax.swing.JButton();
        jButtonSave1 = new javax.swing.JButton();
        barrierFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(195, 229, 255));

        globalInfoPanel.setBackground(new java.awt.Color(254, 252, 254));
        globalInfoPanel.setMaximumSize(new java.awt.Dimension(625, 285));
        globalInfoPanel.setPreferredSize(new java.awt.Dimension(625, 285));

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeId, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelTradeId.text")); // NOI18N

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        jTextFieldTradeId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTradeId.setName("jTextFieldTradeId"); // NOI18N
        jTextFieldTradeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTradeIdActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStatus, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelStatus.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelBook.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelTradeDate.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxInternalCounterparty.setName("jComboBoxInternalCounterparty"); // NOI18N

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jTextFieldTradeTime.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(tradeTypeLabel, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.tradeTypeLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelCounterparty.text")); // NOI18N

        tradeTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.counterpartyTextField.text")); // NOI18N

        legalEntityFinderButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N
        jSpinnerTradeDate.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jSpinnerTradeDatePropertyChange(evt);
            }
        });

        jDateChooserValueDate.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabel1.text")); // NOI18N

        jComboBoxCurrency2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxCurrency2.setEnabled(false);

        jComboBoxCurrency1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxCurrency1.setEnabled(false);
        jComboBoxCurrency1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrency1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPremium, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelPremium.text")); // NOI18N

        jComboBoxCurrencyPremium.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrencyPremium.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlying, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelUnderlying.text")); // NOI18N

        ccyPairComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ccyPairComboBox.setName("ccyPairComboBox"); // NOI18N
        ccyPairComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ccyPairComboBoxActionPerformed(evt);
            }
        });
        ccyPairComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ccyPairComboBoxPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStrike, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelStrike.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelType, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelType.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboBoxType.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBoxTypePropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaturityDate, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelMaturityDate.text")); // NOI18N
        jLabelMaturityDate.setMaximumSize(new java.awt.Dimension(51, 14));
        jLabelMaturityDate.setMinimumSize(new java.awt.Dimension(51, 14));
        jLabelMaturityDate.setPreferredSize(new java.awt.Dimension(51, 14));

        jDateChooserMaturityDate.setBackground(new java.awt.Color(254, 252, 254));
        jDateChooserMaturityDate.setName("jDateChooserMaturityDate"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelExerciceType, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelExerciceType.text")); // NOI18N

        jComboBoxExerciceType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxExerciceType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jCheckBoxisDigital.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxisDigital, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jCheckBoxisDigital.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBarrier, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabelBarrier.text")); // NOI18N

        jComboBoxBarrier.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBarrier.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jCheckBoxPayementAtEnd.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayementAtEnd, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jCheckBoxPayementAtEnd.text")); // NOI18N
        jCheckBoxPayementAtEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxPayementAtEndActionPerformed(evt);
            }
        });

        jFormattedTextFieldStrike.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldStrike.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jFormattedTextFieldStrike.text")); // NOI18N
        jFormattedTextFieldStrike.setName("jFormattedTextFieldStrike"); // NOI18N
        jFormattedTextFieldStrike.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldStrikeActionPerformed(evt);
            }
        });
        jFormattedTextFieldStrike.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextFieldStrikePropertyChange(evt);
            }
        });

        jFormattedTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldQuantity.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jFormattedTextFieldQuantity.text")); // NOI18N
        jFormattedTextFieldQuantity.setName("jFormattedTextFieldQuantity"); // NOI18N
        jFormattedTextFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldQuantityActionPerformed(evt);
            }
        });
        jFormattedTextFieldQuantity.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextFieldQuantityPropertyChange(evt);
            }
        });

        jFormattedTextFieldConvertedAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldConvertedAmount.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jFormattedTextFieldConvertedAmount.text")); // NOI18N
        jFormattedTextFieldConvertedAmount.setName("jFormattedTextFieldConvertedAmount"); // NOI18N
        jFormattedTextFieldConvertedAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldConvertedAmountActionPerformed(evt);
            }
        });
        jFormattedTextFieldConvertedAmount.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextFieldConvertedAmountPropertyChange(evt);
            }
        });

        jFormattedTextFieldPremium.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldPremium.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jFormattedTextFieldPremium.text")); // NOI18N
        jFormattedTextFieldPremium.setName("jFormattedTextFieldPremium"); // NOI18N
        jFormattedTextFieldPremium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldPremiumActionPerformed(evt);
            }
        });
        jFormattedTextFieldPremium.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jFormattedTextFieldPremiumPropertyChange(evt);
            }
        });

        priceComboBox.setBackground(new java.awt.Color(255, 255, 255));
        priceComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        priceComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                priceComboBoxPropertyChange(evt);
            }
        });

        priceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        priceFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.priceFormattedTextField.text")); // NOI18N
        priceFormattedTextField.setName("priceFormattedTextField"); // NOI18N
        priceFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceFormattedTextFieldActionPerformed(evt);
            }
        });
        priceFormattedTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                priceFormattedTextFieldPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabel2.text")); // NOI18N

        payLagTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        payLagTextField.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.payLagTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.lifeCycleStatusLabel.text")); // NOI18N

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);
        lifeCycleStatusTextField.setName("lifeCycleStatusTextField"); // NOI18N

        priceRefreshButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/trades/refresh.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(priceRefreshButton, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.priceRefreshButton.text")); // NOI18N
        priceRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceRefreshButtonActionPerformed(evt);
            }
        });

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);

        jButtonPrice1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonPrice1.text")); // NOI18N
        jButtonPrice1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonPrice1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonPrice1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPrice1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonPrice1);

        jButtonScheduler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonScheduler, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonScheduler.text")); // NOI18N
        jButtonScheduler.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSchedulerActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonScheduler);

        tradeDetailsButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tradeDetailsButton1.setLabel(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.tradeDetailsButton1.label")); // NOI18N
        tradeDetailsButton1.setMaximumSize(new java.awt.Dimension(80, 30));
        tradeDetailsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(tradeDetailsButton1);

        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.loadEventXButton1.text")); // NOI18N
        loadEventXButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadEventXButton1.setMaximumSize(new java.awt.Dimension(90, 30));
        loadEventXButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(loadEventXButton1);

        org.openide.awt.Mnemonics.setLocalizedText(addContractEventButton1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.addContractEventButton1.text")); // NOI18N
        addContractEventButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addContractEventButton1.setMaximumSize(new java.awt.Dimension(90, 30));
        addContractEventButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContractEventButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(addContractEventButton1);

        jButtonExport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonExport);

        jSeparator3.setMaximumSize(new java.awt.Dimension(16, 32767));
        jSeparator3.setPreferredSize(new java.awt.Dimension(6, 10));
        windowToolBar.add(jSeparator3);

        jButtonNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonNew1.text")); // NOI18N
        jButtonNew1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonNew1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonNew1);

        jButtonSaveAsNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonSaveAsNew1.text")); // NOI18N
        jButtonSaveAsNew1.setMaximumSize(new java.awt.Dimension(90, 30));
        jButtonSaveAsNew1.setMinimumSize(new java.awt.Dimension(50, 30));
        jButtonSaveAsNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSaveAsNew1);

        jButtonSave1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave1, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jButtonSave1.text")); // NOI18N
        jButtonSave1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonSave1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonSave1.setPreferredSize(new java.awt.Dimension(60, 23));
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSave1);

        barrierFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        barrierFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.barrierFormattedTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FXOptionTopComponent.class, "FXOptionTopComponent.jLabel3.text")); // NOI18N

        javax.swing.GroupLayout globalInfoPanelLayout = new javax.swing.GroupLayout(globalInfoPanel);
        globalInfoPanel.setLayout(globalInfoPanelLayout);
        globalInfoPanelLayout.setHorizontalGroup(
            globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator1)
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBook)
                            .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTradeDate))
                        .addGap(7, 7, 7)
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabelCounterparty)
                                        .addGap(18, 18, 18)
                                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tradeTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(tradeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelTradeId)
                                    .addComponent(jLabelStatus))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(48, Short.MAX_VALUE))))
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldConvertedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jLabelStrike, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jFormattedTextFieldStrike, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(ccyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(324, 324, 324)))
                            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                                .addComponent(priceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(priceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(priceRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addGap(256, 256, 256)
                                        .addComponent(jLabelBarrier, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxBarrier, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(barrierFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addGap(335, 335, 335)
                                        .addComponent(jCheckBoxPayementAtEnd))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(jLabelType, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                        .addGap(146, 146, 146)
                                        .addComponent(jLabelMaturityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooserMaturityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, globalInfoPanelLayout.createSequentialGroup()
                                        .addGap(79, 79, 79)
                                        .addComponent(jComboBoxExerciceType, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(payLagTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelExerciceType, javax.swing.GroupLayout.Alignment.LEADING)))
                            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                .addComponent(jLabelPremium, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(jFormattedTextFieldPremium, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCurrencyPremium, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckBoxisDigital)
                                    .addComponent(jDateChooserValueDate, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(windowToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        globalInfoPanelLayout.setVerticalGroup(
            globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBook, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(legalEntityFinderButton)
                        .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLoad)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tradeTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tradeTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelStatus))
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTradeDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccyPairComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelStrike, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldStrike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextFieldConvertedAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(jLabelType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxType)
                        .addComponent(jLabelMaturityDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelExerciceType, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxExerciceType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(payLagTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jDateChooserMaturityDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelBarrier)
                        .addComponent(jComboBoxBarrier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(barrierFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(priceRefreshButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceComboBox)
                    .addComponent(priceFormattedTextField)
                    .addComponent(jCheckBoxisDigital, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxCurrencyPremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldPremium, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPremium)))
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooserValueDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jCheckBoxPayementAtEnd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 859, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(globalInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        loadTrade();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void legalEntityFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButtonActionPerformed

    private void jTextFieldTradeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTradeIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTradeIdActionPerformed

    private void jComboBoxCurrency1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrency1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBoxCurrency1ActionPerformed

    private void ccyPairComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ccyPairComboBoxPropertyChange
        if (ccyPairComboBox.getSelectedItem() != null) {
            notifyCcyPairChange(ccyPairComboBox.getSelectedItem().toString());
        }
    }//GEN-LAST:event_ccyPairComboBoxPropertyChange

    private void ccyPairComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ccyPairComboBoxActionPerformed
        if (ccyPairComboBox.getSelectedItem() != null) {
            notifyCcyPairChange(ccyPairComboBox.getSelectedItem().toString());
        }
    }//GEN-LAST:event_ccyPairComboBoxActionPerformed

    private void jCheckBoxPayementAtEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxPayementAtEndActionPerformed
        if (jCheckBoxPayementAtEnd.isSelected()) {
            jDateChooserValueDate.setDate(jDateChooserMaturityDate.getDate());
        }
    }//GEN-LAST:event_jCheckBoxPayementAtEndActionPerformed

    private void jFormattedTextFieldStrikeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStrikeActionPerformed
        jFormattedTextFieldStrike.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldStrike)));
        if (!isLoading) {
            updateAmount2();
        }
    }//GEN-LAST:event_jFormattedTextFieldStrikeActionPerformed

    private void jFormattedTextFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityActionPerformed
        jFormattedTextFieldQuantity.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity)));
        if (!isLoading) {
            updateAmount2();
        }
    }//GEN-LAST:event_jFormattedTextFieldQuantityActionPerformed

    private void jFormattedTextFieldConvertedAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldConvertedAmountActionPerformed
        jFormattedTextFieldConvertedAmount.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldConvertedAmount)));
    }//GEN-LAST:event_jFormattedTextFieldConvertedAmountActionPerformed

    private void jFormattedTextFieldPremiumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPremiumActionPerformed
        jFormattedTextFieldPremium.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldPremium)));

    }//GEN-LAST:event_jFormattedTextFieldPremiumActionPerformed

    private void jFormattedTextFieldStrikePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStrikePropertyChange
        jFormattedTextFieldStrike.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldStrike)));
        if (!isLoading) {
            updateAmount2();
            priceTrade();
        }
    }//GEN-LAST:event_jFormattedTextFieldStrikePropertyChange

    private void jFormattedTextFieldQuantityPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityPropertyChange
        jFormattedTextFieldQuantity.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity)));
        if (!isLoading) {
            updateAmount2();
            priceTrade();
        }
    }//GEN-LAST:event_jFormattedTextFieldQuantityPropertyChange

    private void jFormattedTextFieldConvertedAmountPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextFieldConvertedAmountPropertyChange
        jFormattedTextFieldConvertedAmount.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldConvertedAmount)));
    }//GEN-LAST:event_jFormattedTextFieldConvertedAmountPropertyChange

    private void jFormattedTextFieldPremiumPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPremiumPropertyChange
        jFormattedTextFieldPremium.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldPremium)));
    }//GEN-LAST:event_jFormattedTextFieldPremiumPropertyChange

    private void priceFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceFormattedTextFieldActionPerformed
        if (!isLoading) {
            updatePremium(null);
        }
    }//GEN-LAST:event_priceFormattedTextFieldActionPerformed

    private void priceFormattedTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_priceFormattedTextFieldPropertyChange
    }//GEN-LAST:event_priceFormattedTextFieldPropertyChange

    private void priceComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_priceComboBoxPropertyChange
        if (!isLoading) {
            priceTrade();
        }
    }//GEN-LAST:event_priceComboBoxPropertyChange

    private void jComboBoxTypePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBoxTypePropertyChange
        if (!isLoading) {
            priceTrade();
        }
    }//GEN-LAST:event_jComboBoxTypePropertyChange

    private void jSpinnerTradeDatePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jSpinnerTradeDatePropertyChange

        if (!isLoading) {
            fillTrade();
            Date spotDate = (Date) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_SETTLEMENT_DATE, getTrade());
            jDateChooserValueDate.setDate(spotDate);
        }
    }//GEN-LAST:event_jSpinnerTradeDatePropertyChange

    private void priceRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceRefreshButtonActionPerformed
        priceTrade();
    }//GEN-LAST:event_priceRefreshButtonActionPerformed

    private void jButtonPrice1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPrice1ActionPerformed
        price();
    }//GEN-LAST:event_jButtonPrice1ActionPerformed

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

    private void loadEventXButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButton1ActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButton1ActionPerformed

    private void addContractEventButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContractEventButton1ActionPerformed
        showContractEventWindow();
    }//GEN-LAST:event_addContractEventButton1ActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        fillTrade();
        XMLExporter.export(getTrade(), this);
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNew1ActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonNew1ActionPerformed

    private void jButtonSaveAsNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNew1ActionPerformed

        if (getTrade() != null && getTrade().getId() != null) {
            setTrade(null);
            jTextFieldTradeId.setText(StringUtils.EMPTY_STRING);
            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
            storeTrade();
            setDisplayName(getDisplayName());
        } else {
            JOptionPane.showMessageDialog(this, "No trade loaded");
        }
    }//GEN-LAST:event_jButtonSaveAsNew1ActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed
        storeTrade();
        setDisplayName(getDisplayName());

    }//GEN-LAST:event_jButtonSave1ActionPerformed

    public void updatePremium(BigDecimal price) {
        BigDecimal _100 = new BigDecimal(100);

        String ccyPair = ccyPairComboBox.getSelectedItem().toString();
        String cur1 = ccyPair.substring(0, 3);
        String cur2 = ccyPair.substring(4);
        if (price == null) {
            price = GUIUtils.getComponentBigDecimalValue(priceFormattedTextField);
        }
        BigDecimal quantity1 = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity);
        BigDecimal quantity2 = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldConvertedAmount);
        if (priceComboBox.getSelectedIndex() == 0) {
            jFormattedTextFieldPremium.setText(decimalFormat.format(quantity1.multiply(price).divide(_100)));
            jComboBoxCurrencyPremium.setSelectedItem(cur1);
        } else if (priceComboBox.getSelectedIndex() == 1) {
            jFormattedTextFieldPremium.setText(decimalFormat.format(quantity2.multiply(price).divide(_100).negate()));
            jComboBoxCurrencyPremium.setSelectedItem(cur2);
        } else if (priceComboBox.getSelectedIndex() == 2) {
            jFormattedTextFieldPremium.setText(decimalFormat.format(quantity2.multiply(price).divide(_100).negate()));
            jComboBoxCurrencyPremium.setSelectedItem(cur1);
        } else if (priceComboBox.getSelectedIndex() == 3) {
            jComboBoxCurrencyPremium.setSelectedItem(cur2);
            jFormattedTextFieldPremium.setText(decimalFormat.format(quantity1.multiply(price).divide(_100)));
        }

    }

    public void updateAmount2() {
        BigDecimal strike = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldStrike);
        BigDecimal amount1 = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity);
        jFormattedTextFieldConvertedAmount.setText(decimalFormat.format(strike.multiply(amount1).negate()));

    }

    public void priceTrade() {
        pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        fillTrade();

        if (getTrade()
                != null) {
            Map<String, BigDecimal> measures = (Map<String, BigDecimal>) DAOCallerAgent.callMethod(PricingBuilder.class,
                    PricingBuilder.GET_TRADE_PRICING, getTrade(), DateUtils.getDate(), pricingEnv, null, null);
            if (measures != null) {
                BigDecimal price = measures.get(MeasuresAccessor.Measure.NPV_unit.name());
                if (price != null) {
                    price = price.multiply(new BigDecimal(100));
                    // if value date after trade date it lowers the price
                    BigDecimal strike = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldStrike);
                    if (priceComboBox.getSelectedIndex() == 2) {
                        price = price.divide(strike, 20, RoundingMode.CEILING);
                    } else if (priceComboBox.getSelectedIndex() == 1) {
                        BigDecimal fxRate = measures.get("QUOTE." + getProduct().loadSingleUnderlying().getShortName());
                        if (fxRate != null) {
                            price = price.multiply(fxRate).divide(strike, 20, RoundingMode.CEILING);
                        }
                    } else if (priceComboBox.getSelectedIndex() == 3) {
                        BigDecimal fxRate = measures.get("QUOTE." + getProduct().loadSingleUnderlying().getShortName());
                        if (fxRate != null) {
                            price = price.multiply(fxRate);
                        }
                    }
                    priceFormattedTextField.setText(pointDecimalFormat.format(price));
                    updatePremium(price);
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton addContractEventButton1;
    private javax.swing.JFormattedTextField barrierFormattedTextField;
    private javax.swing.JComboBox ccyPairComboBox;
    private javax.swing.JTextField counterpartyTextField;
    private javax.swing.JPanel globalInfoPanel;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew1;
    private javax.swing.JButton jButtonPrice1;
    private javax.swing.JButton jButtonSave1;
    private javax.swing.JButton jButtonSaveAsNew1;
    private javax.swing.JButton jButtonScheduler;
    private javax.swing.JCheckBox jCheckBoxPayementAtEnd;
    private javax.swing.JCheckBox jCheckBoxisDigital;
    private javax.swing.JComboBox jComboBoxBarrier;
    private javax.swing.JComboBox jComboBoxCurrency1;
    private javax.swing.JComboBox jComboBoxCurrency2;
    private javax.swing.JComboBox jComboBoxCurrencyPremium;
    private javax.swing.JComboBox jComboBoxExerciceType;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JComboBox jComboBoxType;
    private com.toedter.calendar.JDateChooser jDateChooserMaturityDate;
    private com.toedter.calendar.JDateChooser jDateChooserValueDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldConvertedAmount;
    private javax.swing.JFormattedTextField jFormattedTextFieldPremium;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantity;
    private javax.swing.JFormattedTextField jFormattedTextFieldStrike;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelBarrier;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelExerciceType;
    private javax.swing.JLabel jLabelMaturityDate;
    private javax.swing.JLabel jLabelPremium;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelStrike;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JLabel jLabelTradeId;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelUnderlying;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    protected javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JButton legalEntityFinderButton;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    private org.jdesktop.swingx.JXButton loadEventXButton1;
    private javax.swing.JTextField payLagTextField;
    private javax.swing.JComboBox priceComboBox;
    private javax.swing.JFormattedTextField priceFormattedTextField;
    private javax.swing.JButton priceRefreshButton;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JButton tradeDetailsButton1;
    public javax.swing.JComboBox tradeTypeComboBox;
    private javax.swing.JLabel tradeTypeLabel;
    private javax.swing.JToolBar windowToolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
