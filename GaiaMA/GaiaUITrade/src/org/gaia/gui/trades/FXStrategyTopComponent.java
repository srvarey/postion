/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES strategyComboBox This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General
 * Public License as published by the Free Software Foundation, either version
 * 3.0 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 *
 * @author Jawhar Kamoun
 */
package org.gaia.gui.trades;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.CurveUtils;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.observables.VolatilityObservable;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.gaia.OSJavaQuant;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.pricing.pricers.isda.IsdaPricerCaller;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.trades.schedulers.RateScheduler;
import org.gaia.dao.utils.DateInterval;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.HeaderTableAccessor;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.referentials.UserPricingPreference;
import org.gaia.domain.trades.HeaderTable;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.Trade.TradeType;
import org.gaia.domain.trades.TradeGroup;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import static org.gaia.gui.common.GaiaTopComponent.timeFormatter;
import org.gaia.gui.common.HeaderChooser;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//FXStrategy//EN", autostore = false)
@TopComponent.Description(preferredID = "FXStrategyTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.FXStrategyTopComponent")
@ActionReference(path = "Menu"+MenuManager.FXStrategyTopComponentMenu, position = MenuManager.FXStrategyTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FXStrategyAction")
@Messages({
    "CTL_FXStrategyAction=FX Strategy",
    "CTL_FXStrategyTopComponent=FX Strategy Window",
    "HINT_FXStrategyTopComponent=This is a FX Strategy window"
})
public final class FXStrategyTopComponent extends GaiaTradeTopComponent {

    private static final Logger logger = Logger.getLogger(FXStrategyTopComponent.class);
    private DefaultTableModel model;
    private boolean initProcess = true;
    private Product productCcyPair;
    private final boolean isFxFwd = false;
    private String quoteSet;
    private TradeGroup tradeGroup;
    private final BigDecimal rateMultiplicator = NumberUtils.BIGDECIMAL_100;
    private Trade hedgeTrade;
    private Set tradeGroupList;
    private LinkedHashMap<String, Boolean> columnMap = new LinkedHashMap<>();
    List<String> listPairs;
    VolatilityObservable volatility;
    private MarketQuote defaultQuote;
    BigDecimal delta = BigDecimal.ONE;
    /**
     * javafx fields
     */
    private static final int PANEL_WIDTH_INT = 675;
    private static final int PANEL_HEIGHT_INT = 400;
    private static final int TABLE_PANEL_HEIGHT_INT = 100;
    private JFXPanel chartFxPanel;
    private LineChart<Number, Number> chart;
    private JPanel panel;
    List<HeaderTable> tableHeaders;
    List<String> callPut;
    List<String> buysell;
    List<String> barriers;

    /**
     *
     */
    public FXStrategyTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_FXStrategyTopComponent());
        setToolTipText(Bundle.HINT_FXStrategyTopComponent());
        quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        fwdRateLabel1.setValueMultiplicator(rateMultiplicator);
        fwdRateLabel2.setValueMultiplicator(rateMultiplicator);
        optionTable.getDefaultEditor(String.class).addCellEditorListener(new MyCellEditorListener());
        availableProductTypes = new ArrayList<>();
        availableProductTypes.addAll(ProductTypeUtil.getChildrenTypes(ProductTypeUtil.ProductType.FX_STRATEGY));
        callPut = new ArrayList();
        callPut.add(StringUtils.CALL);
        callPut.add(StringUtils.PUT);
        buysell = new ArrayList();
        buysell.add(StringUtils.BUY);
        buysell.add(StringUtils.SELL);
        barriers = new ArrayList();
        for (ProductConst.BarrierType barrierType : ProductConst.BarrierType.values()) {
            barriers.add(barrierType.name());
        }
    }

    /**
     *
     */
    @Override
    public void initContext() {
        model = (DefaultTableModel) optionTable.getModel();
        spotFwdPanel.setVisible(false);
        setVisibleFwdFields(false);
        rateLabel.setText("Spot");
        listPairs = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIRS, LoggedUser.getLoggedUserId());
        GUIUtils.fillCombo(underlyingComboBox, listPairs);
        String pair = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIR, LoggedUser.getLoggedUserId());
        underlyingComboBox.setSelectedItem(pair);
        Product pairProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, pair);
        quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        MarketQuote quote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_LAST_QUOTE, pairProduct.getProductId(), DateUtils.getDate(), quoteSet);
        realTimeQuoteLabel.setText(pointDecimalFormat.format(quote.getClose().doubleValue()));
        hedgePriceFormattedTextField.setText(pointDecimalFormat.format(quote.getClose().doubleValue()));
        lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);

        tableHeaders = (List) DAOCallerAgent.callMethod(HeaderTableAccessor.class, HeaderTableAccessor.LOAD_TABLE_HEADERS, "optionTable");
        for (HeaderTable header : tableHeaders) {
            columnMap.put(header.getColumnName(), header.getIsVisible());
        }
        refreshHeader(columnMap, true);
        startRTQuote();

        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_STRADDLE.name);
        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_STRANGLE.name);
        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_STRIP.name);
        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_BUTTERFLY.name);
        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_RISK_REVERSAL.name);
        strategyComboBox.addItem(ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name);

        stripTypeComboBox.addItem(ProductTypeUtil.ProductType.FX_STRADDLE.name);
        stripTypeComboBox.addItem(ProductTypeUtil.ProductType.FX_STRANGLE.name);
        stripTypeComboBox.addItem(ProductTypeUtil.ProductType.FX_BUTTERFLY.name);
        stripTypeComboBox.addItem(ProductTypeUtil.ProductType.FX_RISK_REVERSAL.name);
        stripTypeComboBox.addItem(StringUtils.PUT);
        stripTypeComboBox.addItem(StringUtils.CALL);

        List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        GUIUtils.fillCombo(premiumCurrencyComboBox, currencies);
        GUIUtils.fillCombo(notionalCurrencyComboBox, currencies);
        String cur = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId());
        premiumCurrencyComboBox.setSelectedItem(cur);
        notionalCurrencyComboBox.setSelectedItem(cur);

        List<String> frequencies = FrequencyUtil.getFrequencies();
        GUIUtils.fillCombo(frequencyComboBox, frequencies);
        jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
        jSpinnerTradeDate.setDate(DateUtils.getDate());
        valueDateChooser1.setDate(DateUtils.getDate());
        List<String> statusList = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
        GUIUtils.fillCombo(statusComboBox, statusList);
        statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
        List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
        GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);
        maturityDateChooser.setDate(DateUtils.addMonth(DateUtils.getDate(), 3));
        valueDateChooser.setDate(DateUtils.getDate());
        initProcess = false;

        spotToggleButton.setSelected(true);
        valueDateChooser.setDate(DateUtils.getDate());
        /**
         * ShortCut for Amout and date
         */
        AmountShortCut.eventkey(notionalFormattedTextField1);
        AmountShortCut.eventkey(amountFormattedTextField);
        AmountShortCut.eventkey(quantityFormattedTextField);
        AmountShortCut.eventkey(amountFormattedTextField2);
        DateShortCut.eventkey((JSpinnerDateEditor) maturityDateChooser.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) valueDateChooser.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));

        barriers = new ArrayList();
        for (ProductConst.BarrierType barrierType : ProductConst.BarrierType.values()) {
            barriers.add(barrierType.name());
        }
    }

    /**
     *
     * @param id
     */
    @Override
    public void loadByTradeId(Integer id) {
        jTextFieldTradeId.setText(id.toString());
        loadTrade();
    }

    /**
     *
     * @return
     */
    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = getName();
        }
        return _displayName;
    }

    /**
     *
     * @param _trade
     */
    @Override
    public void loadByTrade(Trade _trade) {
        strategyLoader(_trade);
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
            try {
                Trade tradeLoaded = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId);
                strategyLoader(tradeLoaded);

            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     *
     */
    @Override
    public void fillTrade() {
        if (getTrade() == null) {
            setTrade(new Trade());
            setProduct(new Product());
        }
        try {
            optionsFactory();
        } catch (ParseException ex) {
            Exceptions.printStackTrace(ex);
        }
        getProduct().setMaturityDate(maturityDateChooser.getDate());
        productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(underlyingComboBox));
        getProduct().setNotionalCurrency(notionalCurrencyComboBox.getSelectedItem().toString());
        getProduct().setProductType(GUIUtils.getComponentStringValue(strategyComboBox));
        getProduct().setShortName(labelTextField.getText());

        getTrade().setQuantity(GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1));
        getTrade().setAmount(GUIUtils.getComponentBigDecimalValue(amountFormattedTextField));
        getTrade().setSettlementCurrency(GUIUtils.getComponentStringValue(premiumCurrencyComboBox));
        getTrade().setQuantityType(Trade.QuantityType.NOTIONAL.name);
        getTrade().setPrice(GUIUtils.getComponentBigDecimalValue(stratPriceFormattedTextField).divide(new BigDecimal(100), 20, RoundingMode.UP));
        getTrade().setPriceCurrency(GUIUtils.getComponentStringValue(premiumCurrencyComboBox));

        LegalEntity counterpart = null;
        if (!StringUtils.isEmptyString(counterpartyTextField.getText())) {
            counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
        }
        getTrade().setCounterparty(counterpart);
        LegalEntity internalCounterparty = null;
        if (!StringUtils.isEmptyString(GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty))) {
            internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                    GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty));
        }
        getTrade().setInternalCounterparty(internalCounterparty);
        getTrade().setTradeDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));
        getTrade().setTradeTime(GUIUtils.getComponentTimeValue(jTextFieldTradeTime));
        getTrade().setValueDate(valueDateChooser.getDate());
        getTrade().setStatus(GUIUtils.getComponentStringValue(statusComboBox));
        getTrade().setPriceType(MarketQuote.QuotationType.PRICE.getName());
        getTrade().setProduct(getProduct());
        getTrade().setLifeCycleStatus(lifeCycleStatusTextField.getText());

    }

    /**
     *
     */
    public void fillTradehedge() {
        if (jCheckBoxHedge.isSelected()) {

            if (hedgeTrade == null) {
                hedgeTrade = new Trade();
            }

            String pair = underlyingComboBox.getSelectedItem().toString();
            productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, pair);
            hedgeTrade.setProduct(productCcyPair);
            hedgeTrade.setTradeType(Trade.TradeType.SPOT.name);
            hedgeTrade.setQuantity(GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField));
            hedgeTrade.setAmount(GUIUtils.getComponentBigDecimalValue(amountFormattedTextField2));
            hedgeTrade.setSettlementCurrency(GUIUtils.getComponentStringValue(premiumCurrencyComboBox));
            hedgeTrade.setQuantityType(Trade.QuantityType.NOTIONAL.name);
            hedgeTrade.setMarketPrice(GUIUtils.getComponentBigDecimalValue(hedgePriceFormattedTextField));
            LegalEntity counterpart = null;
            if (!StringUtils.isEmptyString(hedgeCounterpartyTextField.getText())) {
                counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, hedgeCounterpartyTextField.getText());
            } else {
                counterpart = getTrade().getCounterparty();
            }
            hedgeTrade.setCounterparty(counterpart);
            LegalEntity internalCounterparty = null;

            if (!StringUtils.isEmptyString(GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty))) {
                internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME,
                        GUIUtils.getComponentStringValue(jComboBoxInternalCounterparty));
            }
            hedgeTrade.setInternalCounterparty(internalCounterparty);
            hedgeTrade.setTradeDate(GUIUtils.getComponentDateValue(jSpinnerTradeDate));
            hedgeTrade.setTradeTime(GUIUtils.getComponentTimeValue(jTextFieldTradeTime));
            hedgeTrade.setValueDate(valueDateChooser.getDate());
            hedgeTrade.setStatus(GUIUtils.getComponentStringValue(statusComboBox));
            hedgeTrade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
            hedgeTrade.setPrice(GUIUtils.getComponentBigDecimalValue(hedgePriceFormattedTextField));
            if (fwdToggleButton.isSelected()) {
                hedgeTrade.setNegociatedPrice(GUIUtils.getComponentBigDecimalValue(fwdPointsFormattedTextField));
                hedgeTrade.setNegociatedPriceType(MarketQuote.QuotationType.PRICE.name());
                hedgeTrade.setTradeType((Trade.TradeType.FORWARD.name));
            }
        }
    }

    private void fillWindow() {
        initProcess = true;
        if (getTrade() == null) {
            setProduct(new Product());
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name());
            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jTextFieldTradeTime.setText(timeFormatter.format(DateUtils.getDate()));
            maturityDateChooser.setDate(DateUtils.getDate());
            valueDateChooser.setDate(DateUtils.getDate());
            return;
        }
        statusComboBox.setSelectedItem(getTrade().getStatus());
        jSpinnerTradeDate.setDate(getTrade().getTradeDate());
        jComboBoxInternalCounterparty.setSelectedItem(getTrade().getInternalCounterparty().getShortName());
        counterpartyTextField.setText(getTrade().getCounterparty().getShortName());
        jTextFieldTradeTime.setText(timeFormatter.format(getTrade().getTradeTime()));
        volatilityFormattedTextField.setText(decimalFormat.format(getTrade().getMarketPrice()));
        maturityDateChooser.setDate(getProduct().getMaturityDate());
        valueDateChooser.setDate(getTrade().getValueDate());
        strategyComboBox.setSelectedItem(getProduct().getProductType());
        notionalFormattedTextField1.setText(decimalFormat.format(getTrade().getQuantity()));
        amountFormattedTextField.setText(decimalFormat.format(getTrade().getAmount()));
        premiumCurrencyComboBox.setSelectedItem(getTrade().getSettlementCurrency());
        stratPriceFormattedTextField.setText(decimalFormat.format(getTrade().getPrice().multiply(new BigDecimal(100))));
        if (getTrade() != null) {
            jTextFieldTradeId.setText(getTrade().getId().toString());
        }
        if (getProduct().getSubProducts() != null && !getProduct().getSubProducts().isEmpty()) {
            Product subProduct = getProduct().getSubProducts().iterator().next();
            if (subProduct.loadSingleUnderlying() != null) {
                productCcyPair = subProduct.loadSingleUnderlying();
                underlyingComboBox.setSelectedItem(productCcyPair.getShortName());
            }
        } else {
            underlyingComboBox.setSelectedIndex(1);
        }
        rowFactory(GUIUtils.getComponentStringValue(strategyComboBox));
        initProcess = false;
        lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
        labelTextField.setText(getTrade().getProduct().getShortName());
        setDisplayName(getDisplayName());
    }

    private void fillWindowHedge() {
        if (hedgeTrade != null) {
            quantityFormattedTextField.setText(hedgeTrade.getQuantity() != null ? decimalFormat.format(hedgeTrade.getQuantity()) : BigDecimal.ZERO.toString());
            amountFormattedTextField2.setText(hedgeTrade.getAmount() != null ? decimalFormat.format(hedgeTrade.getAmount()) : BigDecimal.ZERO.toString());
            hedgeCounterpartyTextField.setText(hedgeTrade.getCounterparty().getShortName() != null ? hedgeTrade.getCounterparty().getShortName() : StringUtils.EMPTY_STRING);
            valueDateChooser1.setDate(hedgeTrade.getTradeDate() != null ? hedgeTrade.getTradeDate() : DateUtils.getDate());
            hedgePriceFormattedTextField.setText(hedgeTrade.getPrice() != null ? pointDecimalFormat.format(hedgeTrade.getPrice()) : BigDecimal.ZERO.toString());
            jLabelSpotInfo.setText(pointDecimalFormat.format(hedgeTrade.getMarketPrice()));
            jTextFieldTradeIdhedge.setText(hedgeTrade.getId() != null ? hedgeTrade.getId().toString() : StringUtils.EMPTY_STRING);
            if (TradeType.FORWARD.name.equalsIgnoreCase(hedgeTrade.getTradeType())) {
                setVisibleFwdFields(true);
                fwdPointsFormattedTextField.setText(hedgeTrade.getNegociatedPrice() != null ? pointDecimalFormat.format(hedgeTrade.getNegociatedPrice())
                        : pointDecimalFormat.format(updateFwdPoints()));
                jLabelSpotInfo.setText(hedgeTrade.getMarketPrice() != null ? pointDecimalFormat.format(hedgeTrade.getMarketPrice()) : StringUtils.EMPTY_STRING);

            }
        }
    }

    /**
     * Rows factory for Jtable optionTable with strategy parameter
     *
     * @param strategy
     */
    private void rowFactory(String strategy) {
        Vector dataTable = new Vector<>();
        if (getProduct().getId() != null && initProcess) {
            dataTable = buildRowFromOptions();
        } else {
            if (!initProcess) {
                List<Product> subProducts = new ArrayList<>();
                getProduct().setSubProducts(subProducts);
            }
            if (ProductTypeUtil.ProductType.FX_STRADDLE.name.equalsIgnoreCase(strategy) || ProductTypeUtil.ProductType.FX_STRANGLE.name.equalsIgnoreCase(strategy)) {
                generateStraddleStrangleRows(dataTable, null);
            } else if (ProductTypeUtil.ProductType.FX_BUTTERFLY.name.equalsIgnoreCase(strategy)) {
                generateButterFlyRows(dataTable, null);
            } else if (ProductTypeUtil.ProductType.FX_STRIP.name.equalsIgnoreCase(strategy)) {
                dataTable = generateStripOptions();
            } else if (ProductTypeUtil.ProductType.FX_RISK_REVERSAL.name.equalsIgnoreCase(strategy)) {
                generateRiskReversalRows(dataTable, null);
            } else if (ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name.equalsIgnoreCase(strategy)) {
                dataTable = new Vector(model.getDataVector());
            }
        }

        model.getDataVector().removeAllElements();
        Vector columns = new Vector(columnMap.keySet());
        model.setDataVector(dataTable, columns);
        optionTable.setModel(model);
        model.fireTableDataChanged();
        setUpColumnEditor(optionTable);
        refreshHeader(columnMap, false);

    }

    /**
     * Construct Vector contain data for JTable optionTable
     *
     * @param buySell
     * @param putCall
     * @param fxOption
     * @param stripMaturity
     * @return Vector
     */
    private Vector buildRow(String buySell, String putCall, Product fxOption, Date stripMaturity, BigDecimal notionalMultiplier) {
        Vector row = new Vector();
        row.add(fxOption != null ? fxOption.getId() : null);
        row.add(buySell);
        row.add(putCall);
        row.add((fxOption != null && fxOption.loadSingleUnderlying() != null) ? fxOption.loadSingleUnderlying().getShortName() : GUIUtils.getComponentStringValue(underlyingComboBox));
        if (notionalMultiplier != null) {
            row.add(notionalMultiplier);
        } else if (buySell.equalsIgnoreCase("Sell")) {
            row.add(GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1).negate());
        } else {
            row.add(GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1));
        }

        if (stripMaturity == null) {
            row.add(fxOption != null ? fxOption.getMaturityDate() : maturityDateChooser.getDate());
        } else {
            row.add(stripMaturity);
        }
        if (fxOption != null) {
            if (fxOption.getProductForex().getBarrier() != null) {
                row.add(fxOption.getProductForex().getBarrierType());
            } else {
                row.add(null);
            }
            if (fxOption.getProductForex().getBarrier() != null) {
                row.add(pointDecimalFormat.format(fxOption.getProductForex().getBarrier()));
            } else {
                row.add(null);
            }
            if (fxOption.getProductForex().getStrike() != null) {
                row.add(pointDecimalFormat.format(fxOption.getProductForex().getStrike()));
            } else {
                row.add(null);
            }
        } else {
            row.add(null);
            row.add(null);
            row.add(pointDecimalFormat.format(defaultQuote.getClose()));

        }
        row.add(getTrade() != null ? decimalFormat.format(getTrade().getMarketPrice()) : GUIUtils.getComponentBigDecimalValue(volatilityFormattedTextField));
        row.add(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(deltaFormattedTextField)));
        return row;
    }

    /**
     * build product Forex options from JTable optionTable and add all them in
     * subProduct collection if change any parameter a new product will be build
     */
    private void optionsFactory() throws ParseException {
        List<Product> subProducts = new ArrayList<>(getProduct().getSubProducts());
        List<Product> subProductsTemp = new ArrayList<>();

        for (int i = 0; i < model.getDataVector().size(); i++) {
            Product productOption = new Product();
            ProductForex forex = new ProductForex();
            Vector row = (Vector) model.getDataVector().elementAt(i);
            for (Product oldOption : subProducts) {
                if (oldOption.getId() != null && oldOption.getId().equals(row.elementAt(model.findColumn("id")))) {
                    productOption = oldOption;
                    forex = productOption.getProductForex();
                    break;
                }
            }
            subProducts.remove(productOption);
            if (productOption.getId() == null) {
                forex.setProduct(productOption);
                productOption.setProductForex(forex);
            }

            productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, row.elementAt(model.findColumn("Underlying")).toString());
            productOption.addUnderlying(productCcyPair);
            productOption.setNotionalMultiplier(GUIUtils.convertBuySellStringToBigDecimal((row.elementAt(model.findColumn("Buy/Sell")).toString())));
            forex.setOptionStyle(row.elementAt(model.findColumn("Option Style")).toString());
            forex.setStrike(BigDecimal.valueOf(numberFormat.parse(row.elementAt(model.findColumn("Strike")).toString()).doubleValue()));
            forex.setCurrency1(productCcyPair.getProductForex().getCurrency1());
            forex.setCurrency2(productCcyPair.getProductForex().getCurrency2());
            productOption.setMaturityDate((Date) row.elementAt(model.findColumn("Maturity Date")));
            forex.setBarrierType(row.elementAt(model.findColumn("Exotic")) != null ? row.elementAt(model.findColumn("Exotic")).toString() : null);
            Object barrier = row.elementAt(model.findColumn("Barrier"));
            if (barrier != null) {
                forex.setBarrier(BigDecimal.valueOf(numberFormat.parse(barrier.toString()).doubleValue()));
            }
            productOption.setProductType(ProductTypeUtil.ProductType.FX_VANILLA_OPTION.name);
            productOption.setIsAsset(Boolean.FALSE);
            productOption.setNotionalCurrency(GUIUtils.getComponentStringValue(premiumCurrencyComboBox));
            productOption.setStartDate(valueDateChooser.getDate());
            productOption.setQuotationType(MarketQuote.QuotationType.VOLATILITY.getName());
            if (GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1).doubleValue() != 0) {
                productOption.setNotionalMultiplier((BigDecimal.valueOf(numberFormat.parse(row.elementAt(model.findColumn("Notional")).toString()).doubleValue())).divide(GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1)));
            }

            subProductsTemp.add(productOption);
        }
        getProduct().setSubProducts(subProductsTemp);
    }

    /**
     * Build Row vector data for JTable optionTable from Product Forex (Option)
     * sub products
     *
     * @return Vector
     */
    private Vector buildRowFromOptions() {
        Collection<Product> subProductList = getProduct().getSubProducts();
        Vector data = new Vector<>();
        for (Product subProduct : subProductList) {
            String buySell = GUIUtils.convertBuySellBigDecimalToString(subProduct.getNotionalMultiplier());
            BigDecimal notionalMultiplier = (subProduct.getNotionalMultiplier()).multiply(getTrade().getQuantity());
            data.add(buildRow(buySell, subProduct.getProductForex().getOptionStyle(), subProduct, null, notionalMultiplier));
        }
        return data;
    }

    /**
     * make a cellRender and editor for different JTable columns
     *
     * @param optionTable
     */
    public void setUpColumnEditor(JTable optionTable) {

        JXComboBox combo = new JXComboBox();
        combo.addItem(StringUtils.BUY);
        combo.addItem(StringUtils.SELL);
        optionTable.getColumnModel().getColumn(model.findColumn("Buy/Sell")).setCellEditor(new ComboBoxCellEditor(combo));

        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.DESELECTED) {
                    updateRowNotional();
                    drawChart(false);
                }
            }
        });

        combo = new JXComboBox();
        combo.addItem(StringUtils.CALL);
        combo.addItem(StringUtils.PUT);
        optionTable.getColumnModel().getColumn(model.findColumn("Option Style")).setCellEditor(new ComboBoxCellEditor(combo));
        combo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.ITEM_STATE_CHANGED) {
                    updateStrikes();
                    calculateHedgeTrade();
                    drawChart(false);
                }
            }
        });

        GUIUtils.setNumberEditor(optionTable, model.findColumn("Strike"), "0.0000");
        GUIUtils.setDateEditor(optionTable, model.findColumn("Maturity Date"));
        GUIUtils.setCombo(optionTable, model.findColumn("Exotic"), barriers);
        GUIUtils.setNumberEditor(optionTable, model.findColumn("Barrier"), "0.0000");
        GUIUtils.setNumberEditor(optionTable, model.findColumn("Volatility"), "0.00");
        GUIUtils.setNumberEditor(optionTable, model.findColumn("Delta"), "0.00");
        GUIUtils.setCombo(optionTable, model.findColumn("Underlying"), listPairs);
        GUIUtils.setNumberEditor(optionTable, model.findColumn("Notional"), "#,##0.00");

    }

    /**
     * Build data vector for JTable optionTable with maturity date , frequency
     * and stripping mode
     *
     * @return Vector
     */
    private Vector generateStripOptions() {

        String sdateInterval = IsdaPricerCaller.getIntervalFromFrequency(GUIUtils.getComponentStringValue(frequencyComboBox));
        DateInterval dateInterval = DateIntervalUtil.JpmcdsStringToDateInterval(sdateInterval);
        ArrayList<Date> dates = RateScheduler.JpmcdsDateListMakeRegular(jSpinnerTradeDate.getDate(), maturityDateChooser.getDate(), dateInterval, false, false);
        Vector data = new Vector<>();
        String strippingType = GUIUtils.getComponentStringValue(stripTypeComboBox);
        for (Date stripDateMaturity : dates) {
            if (ProductTypeUtil.ProductType.FX_STRADDLE.name.equalsIgnoreCase(strippingType)
                    || ProductTypeUtil.ProductType.FX_STRANGLE.name.equalsIgnoreCase(strippingType)) {
                data.addAll(generateStraddleStrangleRows(new Vector<>(), stripDateMaturity));
            } else if (ProductTypeUtil.ProductType.FX_BUTTERFLY.name.equalsIgnoreCase(strippingType)) {
                data.addAll(generateButterFlyRows(new Vector<>(), stripDateMaturity));
            } else if (ProductTypeUtil.ProductType.FX_RISK_REVERSAL.name.equalsIgnoreCase(strippingType)) {
                data.addAll(generateRiskReversalRows(new Vector<>(), stripDateMaturity));
            } else {
                data.add(buildRow(StringUtils.BUY, strippingType, null, stripDateMaturity, null));
            }
        }
        return data;
    }

    private void updateRowNotional() {
        int row = optionTable.getSelectedRow();
        Integer notionalIndex = model.findColumn("Notional");
        Integer BSIndex = model.findColumn("Buy/Sell");
        try {
            String sNotional = GUIUtils.getTableValueAt(optionTable, row, notionalIndex);
            if (!sNotional.isEmpty()) {
                BigDecimal baseNotional = new BigDecimal(numberFormat.parse(GUIUtils.getTableValueAt(optionTable, row, notionalIndex)).doubleValue());
                baseNotional = baseNotional.abs();
                String buySell = model.getValueAt(row, BSIndex).toString();
                if (!buySell.equalsIgnoreCase(StringUtils.BUY)) {
                    model.setValueAt(decimalFormat.format(baseNotional), row, notionalIndex);
                } else {
                    model.setValueAt(decimalFormat.format(baseNotional.negate()), row, notionalIndex);
                }
            }
        } catch (Exception e) {
            logger.warn(StringUtils.formatErrorMessage(e));
        }
        drawChart(false);
    }

    private void updateNotional(BigDecimal baseNotional) {
        Integer notionalIndex = model.findColumn("Notional");
        Integer BSIndex = model.findColumn("Buy/Sell");
        for (int i = 0; i < model.getRowCount(); i++) {
            String bs = model.getValueAt(i, BSIndex).toString();
            if (bs.equalsIgnoreCase("Buy")) {
                model.setValueAt(decimalFormat.format(baseNotional), i, notionalIndex);
            } else {
                model.setValueAt(decimalFormat.format(baseNotional.negate()), i, notionalIndex);
            }
        }
    }

    private void updateOptionTable(Object value, int columnIndex) {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.setValueAt(value, i, columnIndex);
        }
    }

    private void updateBuySell(int columnIndex) {

        for (int i = 0; i < model.getRowCount(); i++) {
            String BuySell = (String) model.getValueAt(i, columnIndex);
            if (StringUtils.BUY.equalsIgnoreCase(BuySell)) {
                model.setValueAt(StringUtils.SELL, i, columnIndex);
            } else {
                model.setValueAt(StringUtils.BUY, i, columnIndex);
            }
        }
    }

    /**
     * calculate the Quantity for the Hedge
     */
    private void calculateHedgeTrade() {

        try {
            if (delta!=null){
                BigDecimal hedgeAmount = delta.negate();
                quantityFormattedTextField.setText(decimalFormat.format(hedgeAmount));
                BigDecimal spot = GUIUtils.getComponentBigDecimalValue(hedgePriceFormattedTextField);
                amountFormattedTextField2.setText(decimalFormat.format(hedgeAmount.multiply(spot).negate()));
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    /**
     * set currency pair
     *
     * @param ccyPair
     */
    public void setCcyPair(String ccyPair) {
        underlyingComboBox.setSelectedItem(ccyPair);
    }

    /**
     * calculate
     *
     * @param ccyPair
     */
    private void calculateConvertedAmount() {
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        amountFormattedTextField2.setText(decimalFormat.format(GUIUtils.getComponentBigDecimalValue(hedgePriceFormattedTextField).multiply(quantity).negate()));
    }

    /**
     * update the maturity for curves real time
     */
    public void updateRTMaturity() {
        if (product != null) {
            product.setMaturityDate(maturityDateChooser.getDate());
        }
        fwdRateLabel1.setProduct(product);
        fwdRateLabel2.setProduct(product);
    }

    /**
     * start real time
     */
    public void startRTQuote() {
        if (productCcyPair != null) {

            CurveObservable curveCcy1 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                    CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency1().getShortName());
            curveCcy1 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                    CurveUtils.GENERATE_CURVE, curveCcy1, new Date());
            fwdRateLabel1.setObservable(curveCcy1);
            CurveObservable curveCCy2 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                    CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex().getCurrency2().getShortName());
            curveCCy2 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class,
                    CurveUtils.GENERATE_CURVE, curveCCy2, new Date());

            fwdRateLabel2.setObservable(curveCCy2);
            fwdRateLabel1.startRTQuote();
            fwdRateLabel2.startRTQuote();
            realTimeQuoteLabel.setObservable(new MarketQuoteObservable(productCcyPair));
            realTimeQuoteLabel.startRTQuote();
            updateRTMaturity();
        }
    }

    /**
     * change Real-Time Quotes Subscription
     */
    public void changeRTQuoteSubscription() {
        if (productCcyPair != null) {
            CurveObservable curveCCy1 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class, CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex()
                    .getCurrency1().getShortName());
            fwdRateLabel1.setObservable(curveCCy1);
            CurveObservable curveCCy2 = (CurveObservable) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class, CurveUtils.GET_DEFAULT_IR_CURVE, productCcyPair.getProductForex()
                    .getCurrency2().getShortName());
            fwdRateLabel2.setObservable(curveCCy2);
            realTimeQuoteLabel.setObservable(new MarketQuoteObservable(productCcyPair));
            updateRTMaturity();
        }
    }

    /**
     * @param listColumns
     * @param init
     */
    public void refreshHeader(Map<String, Boolean> listColumns, boolean init) {
        if (init) {
            for (Map.Entry<String, Boolean> entry : listColumns.entrySet()) {
                String columnName = entry.getKey();
                model.addColumn(columnName);
            }
            optionTable.setModel(model);
        }

        for (Map.Entry<String, Boolean> entry : listColumns.entrySet()) {
            String columnName = entry.getKey();
            optionTable.getColumnExt(columnName).setVisible(entry.getValue());
        }
    }

    /**
     * loading when you hit enter in trade id
     */
    public class EnterListener implements ActionListener {

        /**
         *
         * @param jTextFieldTradeId
         */
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

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        strategyjTabbedPane = new javax.swing.JTabbedPane();
        tradeGlobalInfoPanel = new javax.swing.JPanel();
        strategyComboBox = new javax.swing.JComboBox();
        strategyLabel = new javax.swing.JLabel();
        nominalLabel = new javax.swing.JLabel();
        amountFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        amountLabel = new javax.swing.JLabel();
        notionalFormattedTextField1 = new javax.swing.JFormattedTextField(decimalFormat);
        premiumCurrencyComboBox = new javax.swing.JComboBox();
        maturityDateChooser = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        maturityLabel = new javax.swing.JLabel();
        volatilityFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        volatilityLabel2 = new javax.swing.JLabel();
        underlyingLabel = new javax.swing.JLabel();
        underlyingComboBox = new javax.swing.JComboBox();
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
        jLabelCounterparty = new javax.swing.JLabel();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        tradeDetailsButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        saveAsNewButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        deltaLabel = new javax.swing.JLabel();
        deltaFormattedTextField = new javax.swing.JFormattedTextField(GaiaTopComponent.decimalFormat);
        frequencyLabel = new javax.swing.JLabel();
        frequencyComboBox = new javax.swing.JComboBox();
        stippingTypeLabel = new javax.swing.JLabel();
        stripTypeComboBox = new javax.swing.JComboBox();
        valueDateChooser = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        valueLabel = new javax.swing.JLabel();
        addOptionButton = new javax.swing.JButton();
        removeOptionButton = new javax.swing.JButton();
        addRemoveLabel = new javax.swing.JLabel();
        jButtonBuySell = new javax.swing.JButton();
        jCheckBoxHedge = new javax.swing.JCheckBox();
        optionScrollPane = new javax.swing.JScrollPane();
        spotFwdPanel = new javax.swing.JPanel();
        jLabelSpot = new javax.swing.JLabel();
        fwdToggleButton = new javax.swing.JToggleButton();
        spotToggleButton = new javax.swing.JToggleButton();
        jLabelSpotInfo = new javax.swing.JLabel();
        amount1Label = new javax.swing.JLabel();
        quantityFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        amountFormattedTextField2 = new javax.swing.JFormattedTextField(decimalFormat);
        amount2Label = new javax.swing.JLabel();
        rateLabel = new javax.swing.JLabel();
        hedgePriceFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        jLabelCounterparty1 = new javax.swing.JLabel();
        hedgeCounterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton1 = new javax.swing.JButton();
        valueDateLabel = new javax.swing.JLabel();
        valueDateChooser1 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jLabelTradeIdhedge = new javax.swing.JLabel();
        jTextFieldTradeIdhedge = new javax.swing.JTextField();
        rateLabel3 = new javax.swing.JLabel();
        fwdPtsLabel = new javax.swing.JLabel();
        fwdPointsFormattedTextField = new javax.swing.JFormattedTextField(decimalFormat);
        fwdRateLabel1 = new org.gaia.gui.common.GaiaRTLabel();
        rateLabel4 = new javax.swing.JLabel();
        fwdRateLabel2 = new org.gaia.gui.common.GaiaRTLabel();
        jButtonColumns = new javax.swing.JButton();
        realTimeQuoteLabel = new org.gaia.gui.common.GaiaRTLabel();
        realTimeCheckBox = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        stratPriceFormattedTextField = new javax.swing.JFormattedTextField();
        priceButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        notionalCurrencyComboBox = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        labelTextField = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        strategyjTabbedPane.setBackground(new java.awt.Color(255, 255, 255));

        tradeGlobalInfoPanel.setBackground(new java.awt.Color(254, 252, 254));
        tradeGlobalInfoPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        strategyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        strategyComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                strategyComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(strategyLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.strategyLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(nominalLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.nominalLabel.text")); // NOI18N

        amountFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.amountFormattedTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(amountLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.amountLabel.text")); // NOI18N

        notionalFormattedTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        notionalFormattedTextField1.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.notionalFormattedTextField1.text")); // NOI18N
        notionalFormattedTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                notionalFormattedTextField1FocusLost(evt);
            }
        });
        notionalFormattedTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                notionalFormattedTextField1KeyPressed(evt);
            }
        });

        premiumCurrencyComboBox.setBackground(new java.awt.Color(255, 255, 255));

        maturityDateChooser.setBackground(new java.awt.Color(255, 255, 255));
        maturityDateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                maturityDateChooserPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(maturityLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.maturityLabel.text")); // NOI18N

        volatilityFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        volatilityFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.volatilityFormattedTextField.text")); // NOI18N
        volatilityFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                volatilityFormattedTextFieldFocusLost(evt);
            }
        });
        volatilityFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                volatilityFormattedTextFieldKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(volatilityLabel2, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.volatilityLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(underlyingLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.underlyingLabel.text")); // NOI18N

        underlyingComboBox.setBackground(new java.awt.Color(255, 255, 255));
        underlyingComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                underlyingComboBoxActionPerformed(evt);
            }
        });

        globalInfoPanel.setBackground(new java.awt.Color(230, 230, 253));
        globalInfoPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        globalInfoPanel.setMaximumSize(new java.awt.Dimension(625, 285));
        globalInfoPanel.setPreferredSize(new java.awt.Dimension(625, 285));

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeId, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelTradeId.text")); // NOI18N

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStatus, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelStatus.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelBook.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelTradeDate.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jTextFieldTradeTime.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelCounterparty.text")); // NOI18N

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.counterpartyTextField.text")); // NOI18N

        legalEntityFinderButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        jSpinnerTradeDate.setBackground(new java.awt.Color(230, 230, 253));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N

        tradeDetailsButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(tradeDetailsButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.tradeDetailsButton.text")); // NOI18N
        tradeDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButtonActionPerformed(evt);
            }
        });

        newButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.newButton.text")); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        saveAsNewButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(saveAsNewButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.saveAsNewButton.text")); // NOI18N
        saveAsNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsNewButtonActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.lifeCycleStatusLabel.text")); // NOI18N

        javax.swing.GroupLayout globalInfoPanelLayout = new javax.swing.GroupLayout(globalInfoPanel);
        globalInfoPanel.setLayout(globalInfoPanelLayout);
        globalInfoPanelLayout.setHorizontalGroup(
            globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addComponent(jLabelBook)
                        .addGap(14, 14, 14)
                        .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelCounterparty)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(globalInfoPanelLayout.createSequentialGroup()
                            .addComponent(tradeDetailsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(newButton)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(saveAsNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(globalInfoPanelLayout.createSequentialGroup()
                            .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelTradeId)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(101, 101, 101))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelTradeDate)
                                    .addGap(18, 18, 18)
                                    .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(27, 27, 27)))
                            .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                    .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(globalInfoPanelLayout.createSequentialGroup()
                                    .addComponent(jLabelStatus)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(30, 30, 30))))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        globalInfoPanelLayout.setVerticalGroup(
            globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(globalInfoPanelLayout.createSequentialGroup()
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelTradeId)
                    .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad)
                    .addComponent(jLabelStatus)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(globalInfoPanelLayout.createSequentialGroup()
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, globalInfoPanelLayout.createSequentialGroup()
                        .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBook)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCounterparty)
                    .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalEntityFinderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(globalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tradeDetailsButton)
                    .addComponent(newButton)
                    .addComponent(saveAsNewButton)
                    .addComponent(saveButton)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(deltaLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.deltaLabel.text")); // NOI18N

        deltaFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        deltaFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                deltaFormattedTextFieldFocusLost(evt);
            }
        });
        deltaFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                deltaFormattedTextFieldKeyPressed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(frequencyLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.frequencyLabel.text")); // NOI18N

        frequencyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        frequencyComboBox.setEnabled(false);
        frequencyComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                frequencyComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(stippingTypeLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.stippingTypeLabel.text")); // NOI18N

        stripTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        stripTypeComboBox.setEnabled(false);
        stripTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                stripTypeComboBoxItemStateChanged(evt);
            }
        });

        valueDateChooser.setBackground(new java.awt.Color(255, 255, 255));

        org.openide.awt.Mnemonics.setLocalizedText(valueLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.valueLabel.text")); // NOI18N

        addOptionButton.setBackground(new java.awt.Color(195, 229, 255));
        addOptionButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addOptionButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.addOptionButton.text")); // NOI18N
        addOptionButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.addOptionButton.toolTipText")); // NOI18N
        addOptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOptionButtonActionPerformed(evt);
            }
        });

        removeOptionButton.setBackground(new java.awt.Color(195, 229, 255));
        removeOptionButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(removeOptionButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.removeOptionButton.text")); // NOI18N
        removeOptionButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.removeOptionButton.toolTipText")); // NOI18N
        removeOptionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOptionButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(addRemoveLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.addRemoveLabel.text")); // NOI18N
        addRemoveLabel.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.addRemoveLabel.toolTipText")); // NOI18N

        jButtonBuySell.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonBuySell, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jButtonBuySell.text")); // NOI18N
        jButtonBuySell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonBuySellActionPerformed(evt);
            }
        });

        jCheckBoxHedge.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxHedge, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jCheckBoxHedge.text")); // NOI18N
        jCheckBoxHedge.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jCheckBoxHedgeStateChanged(evt);
            }
        });
        jCheckBoxHedge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHedgeActionPerformed(evt);
            }
        });

        optionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        optionTable.setColumnControlVisible(true);
        optionTable.getTableHeader().setReorderingAllowed(false);
        optionScrollPane.setViewportView(optionTable);

        spotFwdPanel.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSpot, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelSpot.text")); // NOI18N

        fwdToggleButton.setBackground(new java.awt.Color(204, 255, 255));
        fwdToggleButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(fwdToggleButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.fwdToggleButton.text")); // NOI18N
        fwdToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fwdToggleButtonActionPerformed(evt);
            }
        });

        spotToggleButton.setBackground(new java.awt.Color(204, 255, 255));
        spotToggleButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        spotToggleButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(spotToggleButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.spotToggleButton.text")); // NOI18N
        spotToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spotToggleButtonActionPerformed(evt);
            }
        });

        jLabelSpotInfo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabelSpotInfo, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelSpotInfo.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(amount1Label, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.amount1Label.text")); // NOI18N

        quantityFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantityFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.quantityFormattedTextField.text")); // NOI18N
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

        amountFormattedTextField2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        amountFormattedTextField2.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.amountFormattedTextField2.text")); // NOI18N
        amountFormattedTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amountFormattedTextField2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(amount2Label, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.amount2Label.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(rateLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.rateLabel.text")); // NOI18N

        hedgePriceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        hedgePriceFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.hedgePriceFormattedTextField.text")); // NOI18N
        hedgePriceFormattedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hedgePriceFormattedTextFieldActionPerformed(evt);
            }
        });
        hedgePriceFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                hedgePriceFormattedTextFieldFocusLost(evt);
            }
        });
        hedgePriceFormattedTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                hedgePriceFormattedTextFieldPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty1, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelCounterparty1.text")); // NOI18N

        hedgeCounterpartyTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.hedgeCounterpartyTextField.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton1, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.legalEntityFinderButton1.text")); // NOI18N
        legalEntityFinderButton1.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.legalEntityFinderButton1.toolTipText")); // NOI18N
        legalEntityFinderButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButton1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(valueDateLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.valueDateLabel.text")); // NOI18N

        valueDateChooser1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeIdhedge, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabelTradeIdhedge.text")); // NOI18N

        jTextFieldTradeIdhedge.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jTextFieldTradeIdhedge.text")); // NOI18N
        jTextFieldTradeIdhedge.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(rateLabel3, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.rateLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(fwdPtsLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.fwdPtsLabel.text")); // NOI18N

        fwdPointsFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        fwdPointsFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.fwdPointsFormattedTextField.text")); // NOI18N
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

        fwdRateLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fwdRateLabel1.setForeground(new java.awt.Color(51, 51, 255));
        fwdRateLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(fwdRateLabel1, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.fwdRateLabel1.text")); // NOI18N
        fwdRateLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fwdRateLabel1PropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(rateLabel4, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.rateLabel4.text")); // NOI18N

        fwdRateLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        fwdRateLabel2.setForeground(new java.awt.Color(51, 51, 255));
        fwdRateLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(fwdRateLabel2, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.fwdRateLabel2.text")); // NOI18N
        fwdRateLabel2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fwdRateLabel2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout spotFwdPanelLayout = new javax.swing.GroupLayout(spotFwdPanel);
        spotFwdPanel.setLayout(spotFwdPanelLayout);
        spotFwdPanelLayout.setHorizontalGroup(
            spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spotFwdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(spotToggleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fwdToggleButton, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(76, 76, 76)
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addComponent(amount2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(amountFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(amount1Label, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hedgePriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(quantityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addComponent(fwdPtsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fwdPointsFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(valueDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(spotFwdPanelLayout.createSequentialGroup()
                            .addComponent(rateLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fwdRateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelCounterparty1))
                        .addGroup(spotFwdPanelLayout.createSequentialGroup()
                            .addComponent(rateLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(fwdRateLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jLabelSpot, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addComponent(hedgeCounterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(legalEntityFinderButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jLabelTradeIdhedge, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldTradeIdhedge, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(521, 521, 521))
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSpotInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(valueDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        spotFwdPanelLayout.setVerticalGroup(
            spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(spotFwdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(spotToggleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(amount1Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(quantityFormattedTextField)
                            .addComponent(rateLabel3))
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(spotFwdPanelLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(fwdToggleButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, spotFwdPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(hedgePriceFormattedTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(spotFwdPanelLayout.createSequentialGroup()
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fwdRateLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(hedgeCounterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(legalEntityFinderButton1)
                                .addComponent(jLabelCounterparty1)
                                .addComponent(jTextFieldTradeIdhedge, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelTradeIdhedge)))
                        .addGap(18, 18, 18)
                        .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valueDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(valueDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fwdPointsFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fwdPtsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(18, 18, 18)
                .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelSpotInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(spotFwdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(amountFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rateLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(amount2Label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(fwdRateLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSpot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButtonColumns.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonColumns, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jButtonColumns.text_1")); // NOI18N
        jButtonColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColumnsActionPerformed(evt);
            }
        });

        realTimeQuoteLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        realTimeQuoteLabel.setForeground(new java.awt.Color(0, 51, 255));
        realTimeQuoteLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        org.openide.awt.Mnemonics.setLocalizedText(realTimeQuoteLabel, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.realTimeQuoteLabel.text")); // NOI18N
        realTimeQuoteLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        realTimeQuoteLabel.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                realTimeQuoteLabelPropertyChange(evt);
            }
        });

        realTimeCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(realTimeCheckBox, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.realTimeCheckBox.text")); // NOI18N
        realTimeCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.realTimeCheckBox.toolTipText")); // NOI18N
        realTimeCheckBox.setBorder(null);
        realTimeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realTimeCheckBoxActionPerformed(evt);
            }
        });
        realTimeCheckBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                realTimeCheckBoxPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabel1.text")); // NOI18N

        stratPriceFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        stratPriceFormattedTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.stratPriceFormattedTextField.text")); // NOI18N
        stratPriceFormattedTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                stratPriceFormattedTextFieldFocusLost(evt);
            }
        });
        stratPriceFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stratPriceFormattedTextFieldKeyPressed(evt);
            }
        });

        priceButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/trades/refresh.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(priceButton, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.priceButton.text")); // NOI18N
        priceButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabel2.text")); // NOI18N

        notionalCurrencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        notionalCurrencyComboBox.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabel3.text")); // NOI18N

        labelTextField.setText(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.labelTextField.text")); // NOI18N

        jButton2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.jLabel5.text")); // NOI18N

        javax.swing.GroupLayout tradeGlobalInfoPanelLayout = new javax.swing.GroupLayout(tradeGlobalInfoPanel);
        tradeGlobalInfoPanel.setLayout(tradeGlobalInfoPanelLayout);
        tradeGlobalInfoPanelLayout.setHorizontalGroup(
            tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tradeGlobalInfoPanelLayout.createSequentialGroup()
                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spotFwdPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tradeGlobalInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(nominalLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(underlyingLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                                    .addComponent(jLabel1)
                                    .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(stratPriceFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(priceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(underlyingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tradeGlobalInfoPanelLayout.createSequentialGroup()
                                            .addComponent(notionalFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(notionalCurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                        .addComponent(amountFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(premiumCurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addComponent(realTimeCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(realTimeQuoteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(48, 48, 48)
                                .addComponent(jButtonBuySell)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addComponent(deltaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(21, 21, 21)
                                .addComponent(deltaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(volatilityLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(maturityLabel)
                                    .addComponent(valueLabel)
                                    .addComponent(stippingTypeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(strategyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(frequencyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(maturityDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(valueDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(frequencyComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(stripTypeComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(strategyComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addComponent(volatilityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addRemoveLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addOptionButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(removeOptionButton)
                                .addGap(78, 78, 78)
                                .addComponent(jButtonColumns)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)
                                .addGap(12, 12, 12)
                                .addComponent(jCheckBoxHedge))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(labelTextField))
                                .addComponent(globalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(optionScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 1166, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        tradeGlobalInfoPanelLayout.setVerticalGroup(
            tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addGap(41, 41, 41)
                                .addComponent(nominalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(underlyingLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tradeGlobalInfoPanelLayout.createSequentialGroup()
                                .addComponent(underlyingComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(notionalFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notionalCurrencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(maturityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(stratPriceFormattedTextField)
                                .addComponent(jLabel1))
                            .addComponent(priceButton, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(premiumCurrencyComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(amountLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(amountFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(tradeGlobalInfoPanelLayout.createSequentialGroup()
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(strategyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(strategyLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(maturityDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(valueDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(valueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(frequencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(frequencyLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(stripTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(stippingTypeLabel)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tradeGlobalInfoPanelLayout.createSequentialGroup()
                        .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(labelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(globalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jCheckBoxHedge)
                        .addComponent(jButtonColumns)
                        .addComponent(addOptionButton)
                        .addComponent(removeOptionButton)
                        .addComponent(addRemoveLabel)
                        .addComponent(jButton2))
                    .addGroup(tradeGlobalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(volatilityLabel2)
                        .addComponent(deltaLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deltaFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonBuySell)
                        .addComponent(volatilityFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(jLabel5))
                    .addComponent(realTimeQuoteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(realTimeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spotFwdPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8))
        );

        strategyjTabbedPane.addTab(org.openide.util.NbBundle.getMessage(FXStrategyTopComponent.class, "FXStrategyTopComponent.tradeGlobalInfoPanel.TabConstraints.tabTitle"), tradeGlobalInfoPanel); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(strategyjTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(strategyjTabbedPane)
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

    /**
     * regenerate JTable optionTable with select strategy in strategyComboBox
     *
     * @param evt
     */
    private void strategyComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_strategyComboBoxItemStateChanged
        if (ProductTypeUtil.ProductType.FX_STRIP.name.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
            frequencyComboBox.setEnabled(true);
            stripTypeComboBox.setEnabled(true);
        } else {
            frequencyComboBox.setEnabled(false);
            stripTypeComboBox.setEnabled(false);
        }
        rowFactory(GUIUtils.getComponentStringValue(strategyComboBox));
    }//GEN-LAST:event_strategyComboBoxItemStateChanged

    /**
     * regenerate JTable optionTable if strip strategy is selected on
     * strategyComboBox with choosen frequency on frequencyComboBox
     *
     * @param evt
     */
    private void frequencyComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_frequencyComboBoxItemStateChanged
        if (ProductTypeUtil.ProductType.FX_STRIP.name.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
            rowFactory(GUIUtils.getComponentStringValue(strategyComboBox));
        }
    }//GEN-LAST:event_frequencyComboBoxItemStateChanged

    /**
     * regenerate JTable optionTable if strip strategy is selected on
     * strategyComboBox with choosen stripping mode in stripTypeComboBox if
     * selected item equal "Put" or "Call" just update all values at column
     * "Option Style"
     *
     * @param evt
     */
    private void stripTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_stripTypeComboBoxItemStateChanged
        if (StringUtils.PUT.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))
                || StringUtils.CALL.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
            updateOptionTable(GUIUtils.getComponentStringValue(stripTypeComboBox), model.findColumn("Option Style"));
        } else {
            rowFactory(GUIUtils.getComponentStringValue(strategyComboBox));
        }
    }//GEN-LAST:event_stripTypeComboBoxItemStateChanged

    /**
     *
     */
    public void updateStrikes() {
        setVolatility();
        for (int i = 0; i < model.getDataVector().size(); i++) {
            updateStrike(i);
        }
        drawChart(false);
    }

    public void updateStrike(int rowIndex) {
        try {
            Vector row = (Vector) model.getDataVector().elementAt(rowIndex);
            Date maturity = (Date) row.elementAt(model.findColumn("Maturity Date"));
            String underlying = (String) row.elementAt(model.findColumn("Underlying"));
            BigDecimal delta = BigDecimal.valueOf(numberFormat.parse(row.elementAt(model.findColumn("Delta")).toString()).doubleValue());
            delta = delta.divide(rateMultiplicator, 20, RoundingMode.UP);
            if (delta.doubleValue() < 0) {
                delta = delta.negate();
            }
            double time = DateUtils.dateDiff(DateUtils.getDate(), maturity);
            time = time / 365;

            Double spot = numberFormat.parse(realTimeQuoteLabel.getText()).doubleValue();
            Double r1 = getFirstRate(underlying, maturity);
            Double r2 = getSecondRate(underlying, maturity);

            Object[] coord = new Object[2];
            coord[0] = delta;
            coord[1] = maturity;
            Double vol = volatility.getObservableValue(coord).doubleValue();

            String call = row.elementAt(model.findColumn("Option Style")).toString();
            Double strike = NewtonRaphson(call.equalsIgnoreCase(ProductConst.OptionType.Call.name()), spot, spot, r1, r2, vol, time, Math.abs(delta.doubleValue()));

            model.setValueAt(strike, rowIndex, model.findColumn("Strike"));
            model.setValueAt(vol * 100, rowIndex, model.findColumn("Volatility"));
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    /**
     *
     * @param underlying
     * @param maturity
     * @return
     */
    public Double getFirstRate(String underlying, Date maturity) {
        Double r1 = 0.;
        try {
            if (!fwdRateLabel1.getText().isEmpty()) {
                r1 = numberFormat.parse(fwdRateLabel1.getText()).doubleValue() / 100;
            } else {
                String cur1 = underlying.substring(0, 3);
                BigDecimal bdr1 = (BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_RATE, cur1, maturity);
                if (bdr1 != null) {
                    r1 = bdr1.doubleValue();
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return r1;
    }

    /**
     *
     * @param underlying
     * @param maturity
     * @return
     */
    public Double getSecondRate(String underlying, Date maturity) {
        Double r1 = 0.;
        try {
            if (!fwdRateLabel1.getText().isEmpty()) {
                r1 = numberFormat.parse(fwdRateLabel1.getText()).doubleValue() / 100;
            } else {
                String cur1 = underlying.substring(4, 7);
                BigDecimal bdr1 = (BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_RATE, cur1, maturity);
                if (bdr1 != null) {
                    r1 = bdr1.doubleValue();
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return r1;
    }

    /**
     *
     * @param isCall
     * @param spot
     * @param seedStrike
     * @param r1
     * @param r2
     * @param vol
     * @param time
     * @param targetDelta
     * @return
     */
    public double NewtonRaphson(boolean isCall, Double spot, Double seedStrike, Double r1, Double r2, Double vol, Double time, Double targetDelta) {

        double x0, x1, y0, y1;
        double tol = 0.1;
        x0 = seedStrike;
        while (Math.abs(tol) > 0.00000001) {
            y0 = OSJavaQuant.option_delta_european_call_payout(spot, x0, r1, r2, vol, time) - targetDelta;
            y1 = OSJavaQuant.option_delta_european_call_payout(spot, x0 * 1.00001, r1, r2, vol, time) - targetDelta;

            x1 = x0 - y0 / ((y1 - y0) / (x0 * 0.00001));
            tol = x1 - x0;
            if (x1 > 0) {
                x0 = x1;
            } else {
                x0 = x0 / 2;
            }
        }

        return x0;
    }

    /**
     *
     */
    public void setVolatility() {
        if (volatility == null) {
            Product underlying = null;
            if (getTrade() != null && getTrade().getProduct() != null && getTrade().getProduct().loadSingleUnderlying() != null) {
                underlying = getTrade().getProduct().loadSingleUnderlying();
            } else if (underlyingComboBox.getSelectedItem() != null) {
                String pair = underlyingComboBox.getSelectedItem().toString();
                underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, pair);
            }
            if (underlying != null) {
                pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
                PricingEnvironment pe = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_FROM_NAME, pricingEnv);
                volatility = new VolatilityObservable(AbstractObservable.ObservableType.FX_VOLATILITY, AbstractObservable.OBSERVABLE_FX_VOLATILITY);
                Integer obsId = (Integer) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.LOOK_FOR_OBSERVABLE_ID_LINKED_WITH_PRICING_ENVIRONMENT, volatility, underlying, pe);
                volatility.setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, obsId));
                volatility = (VolatilityObservable) DAOCallerAgent.callMethodWithXMLSerialization(MarketQuoteAccessor.class, MarketQuoteAccessor.FILL_OBSERVABLE, volatility, DateUtils.getDate(), quoteSet);
                if (volatility != null) {
                    BigDecimal defaultVolatility = volatility.getObservableValue(new Object[]{DateUtils.getDate()});
                    if (defaultVolatility != null) {
                        defaultVolatility = defaultVolatility.multiply(NumberUtils.BIGDECIMAL_100);
                        volatilityFormattedTextField.setText(decimalFormat.format(defaultVolatility));
                    }
                }
            }
        }
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        storeTrade();
        if (getProduct().getSubProducts() != null) {
            setLayout(new BorderLayout());
            drawChart(true);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * update JTable optionTable all values at column "Maturity Date" with
     * selected date in maturityDateChooser
     *
     * @param evt
     */
    private void maturityDateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_maturityDateChooserPropertyChange
        if (model != null) {
            if (ProductTypeUtil.ProductType.FX_STRIP.name.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
                rowFactory(GUIUtils.getComponentStringValue(strategyComboBox));
            } else {
                updateOptionTable(maturityDateChooser.getDate(), model.findColumn("Maturity Date"));
            }
        }
        updateRTMaturity();
    }//GEN-LAST:event_maturityDateChooserPropertyChange

    /**
     * add row on JTable optionTable select "CUSTOM_STRATEGY" item on
     * strategyComboBox
     *
     * @param evt
     */
    private void addOptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addOptionButtonActionPerformed
        if (!ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
            strategyComboBox.setSelectedItem(ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name);
        }
        model.getDataVector().add(buildRow(StringUtils.BUY, StringUtils.PUT, null, null, null));
        model.fireTableDataChanged();
    }//GEN-LAST:event_addOptionButtonActionPerformed

    /**
     * remeve selected row from JTable optionTable select "CUSTOM_STRATEGY" item
     * on strategyComboBox
     *
     * @param evt
     */
    private void removeOptionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOptionButtonActionPerformed
        if (optionTable.getSelectedRows().length > 0) {
            if (!ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name.equalsIgnoreCase(GUIUtils.getComponentStringValue(strategyComboBox))) {
                strategyComboBox.setSelectedItem(ProductTypeUtil.ProductType.FX_CUSTOM_STRATEGY.name);
            }
            int[] selectedRows = optionTable.getSelectedRows();
            for (int i = 0; i < selectedRows.length; i++) {
                model.removeRow(selectedRows[i]);
            }
            model.fireTableDataChanged();
        }
    }//GEN-LAST:event_removeOptionButtonActionPerformed

    private void saveAsNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsNewButtonActionPerformed
        setTrade(null);
        hedgeTrade = null;
        tradeGroup = null;
        jTextFieldTradeId.setText(StringUtils.EMPTY_STRING);
        jTextFieldTradeIdhedge.setText(StringUtils.EMPTY_STRING);
        lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
        statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
        storeTrade();
        setDisplayName(getDisplayName());

    }//GEN-LAST:event_saveAsNewButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        clearFields(this);
        setTrade(null);
        fillWindow();
        hedgeTrade = null;
        tradeGroup = null;
        fillWindowHedge();
        setDisplayName(getDisplayName());
    }//GEN-LAST:event_newButtonActionPerformed

    private void tradeDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeDetailsButtonActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_tradeDetailsButtonActionPerformed

    private void jButtonBuySellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonBuySellActionPerformed
        updateBuySell(model.findColumn("Buy/Sell"));
        updateNotional(GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1));
        drawChart(false);
    }//GEN-LAST:event_jButtonBuySellActionPerformed

    private void amountFormattedTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amountFormattedTextField2ActionPerformed
        BigDecimal quantity = GUIUtils.getComponentBigDecimalValue(quantityFormattedTextField);
        BigDecimal amount = GUIUtils.getComponentBigDecimalValue(amountFormattedTextField2);
        hedgePriceFormattedTextField.setText(pointDecimalFormat.format(quantity.divide(amount, 20, RoundingMode.HALF_UP)));
    }//GEN-LAST:event_amountFormattedTextField2ActionPerformed

    private void quantityFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityFormattedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantityFormattedTextFieldActionPerformed

    private void quantityFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_quantityFormattedTextFieldFocusLost
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(hedgePriceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_quantityFormattedTextFieldFocusLost

    private void hedgePriceFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hedgePriceFormattedTextFieldActionPerformed
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(hedgePriceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_hedgePriceFormattedTextFieldActionPerformed

    private void hedgePriceFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_hedgePriceFormattedTextFieldFocusLost
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(hedgePriceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_hedgePriceFormattedTextFieldFocusLost

    private void hedgePriceFormattedTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_hedgePriceFormattedTextFieldPropertyChange
        if (!StringUtils.isEmptyString(quantityFormattedTextField.getText()) && !StringUtils.isEmptyString(hedgePriceFormattedTextField.getText())) {
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_hedgePriceFormattedTextFieldPropertyChange

    private void fwdPointsFormattedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fwdPointsFormattedTextFieldActionPerformed
        updateForwardWithFwdPoint();
    }//GEN-LAST:event_fwdPointsFormattedTextFieldActionPerformed

    private void fwdPointsFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fwdPointsFormattedTextFieldFocusLost
        updateForwardWithFwdPoint();
    }//GEN-LAST:event_fwdPointsFormattedTextFieldFocusLost

    private void realTimeQuoteLabelPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_realTimeQuoteLabelPropertyChange
        if (realTimeCheckBox.isSelected() || hedgePriceFormattedTextField.getText().isEmpty()) {
            if (isFxFwd) {
                updateForwardWithFwdPoint();
            } else {
                hedgePriceFormattedTextField.setText(realTimeQuoteLabel.getText());
            }
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_realTimeQuoteLabelPropertyChange

    private void fwdToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fwdToggleButtonActionPerformed
        setVisibleFwdFields(true);
        spotToggleButton.setSelected(false);
        calculateConvertedAmount();
        rateLabel.setText("Forward");

    }//GEN-LAST:event_fwdToggleButtonActionPerformed

    private void spotToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spotToggleButtonActionPerformed

        setVisibleFwdFields(false);
        fwdToggleButton.setSelected(false);
        calculateConvertedAmount();
        closeComponent();
        rateLabel.setText("Spot");
    }//GEN-LAST:event_spotToggleButtonActionPerformed

    /**
     *
     * @param isVisible
     */
    public void setVisibleFwdFields(boolean isVisible) {
        rateLabel3.setVisible(isVisible);
        fwdRateLabel1.setVisible(isVisible);
        fwdPtsLabel.setVisible(isVisible);
        fwdPointsFormattedTextField.setVisible(isVisible);
        rateLabel4.setVisible(isVisible);
        fwdRateLabel2.setVisible(isVisible);
    }

    private void legalEntityFinderButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButton1ActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            hedgeCounterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButton1ActionPerformed

    private void underlyingComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_underlyingComboBoxActionPerformed
        updateOptionTable(GUIUtils.getComponentStringValue(underlyingComboBox), model.findColumn("Underlying"));

        if (underlyingComboBox.getSelectedItem() != null) {
            productCcyPair = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getComponentStringValue(underlyingComboBox));
        }

        setQuotes(quoteSet);
        String pair = productCcyPair.getShortName();
        if (pair.length() == 7) {
            String cur1 = pair.substring(0, 3);
            String cur2 = pair.substring(4, 7);
            amount1Label.setText(cur1);
            amount2Label.setText(cur2);
            rateLabel3.setText(cur1 + " rate %");
            rateLabel4.setText(cur2 + " rate %");
            notionalCurrencyComboBox.setSelectedItem(cur1);
            premiumCurrencyComboBox.setSelectedItem(cur1);
            changeRTQuoteSubscription();
            deltaFormattedTextField.setText("50");
            setVolatility();
        }
    }//GEN-LAST:event_underlyingComboBoxActionPerformed

    private void jCheckBoxHedgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHedgeActionPerformed
        if (jCheckBoxHedge.isSelected()) {
            spotFwdPanel.setVisible(true);
            calculateHedgeTrade();
        } else {
            spotFwdPanel.setVisible(false);
        }

        spotToggleButton.setSelected(true);
    }//GEN-LAST:event_jCheckBoxHedgeActionPerformed

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

    private void jCheckBoxHedgeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jCheckBoxHedgeStateChanged
        if (jCheckBoxHedge.isSelected()) {
            spotFwdPanel.setVisible(true);
            calculateHedgeTrade();
        } else {
            spotFwdPanel.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxHedgeStateChanged

    private void realTimeCheckBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_realTimeCheckBoxPropertyChange
        if (realTimeCheckBox.isSelected()) {
            if (isFxFwd) {
                updateForwardWithFwdPoint();
            } else {
                hedgePriceFormattedTextField.setText(realTimeQuoteLabel.getText());
            }
            calculateConvertedAmount();
        }
    }//GEN-LAST:event_realTimeCheckBoxPropertyChange

    private void realTimeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeCheckBoxActionPerformed
        if (realTimeCheckBox.isSelected()) {
            realTimeCheckBox.setSelected(this.realTimeCheckBox.isSelected());
        }
    }//GEN-LAST:event_realTimeCheckBoxActionPerformed

    /**
     *
     */
    public void updateDeltas() {
        int deltaColumnIndex = model.findColumn("Delta");
        int CPcolumnIndex = model.findColumn("Option Style");
        int BScolumnIndex = model.findColumn("Buy/Sell");
        for (int i = 0; i < model.getRowCount(); i++) {
            Double value = new Double(GUIUtils.getComponentBigDecimalValue(deltaFormattedTextField).doubleValue());
            if (model.getValueAt(i, CPcolumnIndex).toString().equalsIgnoreCase(ProductConst.OptionType.Put.name())) {
                value = value * -1;
            }
            if (model.getValueAt(i, BScolumnIndex).toString().equalsIgnoreCase(StringUtils.SELL)) {
                value = value * -1;
            }
            model.setValueAt(value, i, deltaColumnIndex);
        }
    }

    public void notionalRefreshed() {
        calculateHedgeTrade();
        BigDecimal notional = GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1);
        updateNotional(notional);
        priceTrade();
    }

    private void notionalFormattedTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_notionalFormattedTextField1FocusLost
        notionalRefreshed();
    }//GEN-LAST:event_notionalFormattedTextField1FocusLost

    private void jButtonColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColumnsActionPerformed
        final HeaderChooser hc = new HeaderChooser(columnMap);

        NotifyDescriptor nd = new NotifyDescriptor(hc, "Column Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            columnMap = hc.getSelectedColumns();
            refreshHeader(columnMap, false);
        }
        for (HeaderTable headerTable : tableHeaders) {
            headerTable.setIsVisible(columnMap.get(headerTable.getColumnName()));
        }
        DAOCallerAgent.callMethod(HeaderTableAccessor.class, HeaderTableAccessor.STORE_HEADER_TABLE, (Serializable) tableHeaders);
    }//GEN-LAST:event_jButtonColumnsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        price();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void refreshVolatility() {
        try {
            BigDecimal vol = GUIUtils.getComponentBigDecimalValue(volatilityFormattedTextField);
            updateOptionTable(vol, model.findColumn("Volatility"));
            for (int i = 0; i < model.getRowCount(); i++) {
                Double strike = new BigDecimal(model.getValueAt(i, model.findColumn("Strike")).toString()).doubleValue();
                Date maturity = (Date) model.getValueAt(i, model.findColumn("Maturity Date"));
                String underlying = model.getValueAt(i, model.findColumn("Underlying")).toString();
                String callPut = model.getValueAt(i, model.findColumn("Option Style")).toString();

                Double spot = numberFormat.parse(realTimeQuoteLabel.getText()).doubleValue();
                Double r1 = getFirstRate(underlying, maturity);
                Double r2 = getSecondRate(underlying, maturity);
                double time = DateUtils.dateDiff(DateUtils.getDate(), maturity);
                time = time / 365;
                Double delta;
                if (callPut.equalsIgnoreCase(ProductConst.OptionType.Call.name())) {
                    delta = OSJavaQuant.option_delta_european_call_payout(spot, strike, r1, r2, vol.doubleValue() / 100, time);
                } else {
                    delta = OSJavaQuant.option_delta_european_put_payout(spot, strike, r1, r2, vol.doubleValue() / 100, time);
                }
                model.setValueAt(delta * 100, i, model.findColumn("Delta"));
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    private void priceButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceButtonActionPerformed
        priceTrade();
    }//GEN-LAST:event_priceButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            drawChart(false);
            strategyjTabbedPane.setSelectedIndex(1);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void deltaFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deltaFormattedTextFieldFocusLost
        propagateDelta();
    }//GEN-LAST:event_deltaFormattedTextFieldFocusLost

    private void propagateDelta() {
        updateDeltas();
        updateStrikes();
        calculateHedgeTrade();
        drawChart(false);
    }

    private void notionalFormattedTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_notionalFormattedTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            notionalRefreshed();
        }
    }//GEN-LAST:event_notionalFormattedTextField1KeyPressed

    private void deltaFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_deltaFormattedTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            propagateDelta();
        }
    }//GEN-LAST:event_deltaFormattedTextFieldKeyPressed

    private void volatilityFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_volatilityFormattedTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !isLoading) {
            refreshVolatility();
        }
    }//GEN-LAST:event_volatilityFormattedTextFieldKeyPressed

    private void volatilityFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_volatilityFormattedTextFieldFocusLost
        refreshVolatility();
    }//GEN-LAST:event_volatilityFormattedTextFieldFocusLost

    private void stratPriceFormattedTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_stratPriceFormattedTextFieldFocusLost

        BigDecimal pricePct = GUIUtils.getComponentBigDecimalValue(stratPriceFormattedTextField);
        BigDecimal notional = GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1);
        amountFormattedTextField.setText(numberFormat.format(pricePct.multiply(notional).divide(NumberUtils.BIGDECIMAL_100, 10, RoundingMode.UP)));
    }//GEN-LAST:event_stratPriceFormattedTextFieldFocusLost

    private void stratPriceFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stratPriceFormattedTextFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            BigDecimal pricePct = GUIUtils.getComponentBigDecimalValue(stratPriceFormattedTextField);
            BigDecimal notional = GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1);
            amountFormattedTextField.setText(numberFormat.format(pricePct.multiply(notional).divide(NumberUtils.BIGDECIMAL_100, 10, RoundingMode.UP)));
        }

    }//GEN-LAST:event_stratPriceFormattedTextFieldKeyPressed

    /**
     *
     */
    public void priceTrade() {
        pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        fillTrade();
        if (getTrade() != null) {
            ArrayList<UserPricingPreference> preferences = new ArrayList();
            UserPricingPreference prefDelta = new UserPricingPreference();
            prefDelta.setPricingMeasure("DELTA");
            prefDelta.setUserId(0);
//            prefDelta.setProductType(pricingEnv);
            preferences.add(prefDelta);
            Map<String, BigDecimal> measures = (Map<String, BigDecimal>) DAOCallerAgent.callMethod(PricingBuilder.class,
                    PricingBuilder.GET_TRADE_PRICING, getTrade(), DateUtils.getDate(), pricingEnv, null, preferences);
            if (measures != null) {
                BigDecimal price = measures.get(MeasuresAccessor.Measure.NPV_unit.name());

                if (price != null) {
                    price = price.multiply(new BigDecimal(100));
                    stratPriceFormattedTextField.setText(pointDecimalFormat.format(price));
                    BigDecimal quantity1 = GUIUtils.getComponentBigDecimalValue(notionalFormattedTextField1);
                    amountFormattedTextField.setText(decimalFormat.format(quantity1.multiply(price).divide(new BigDecimal(100)).negate()));
                    premiumCurrencyComboBox.setSelectedItem(notionalCurrencyComboBox.getSelectedItem().toString());
                }
                delta = measures.get("DELTA." + underlyingComboBox.getSelectedItem().toString());
                if (delta != null) {
                    calculateHedgeTrade();
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addOptionButton;
    private javax.swing.JLabel addRemoveLabel;
    private javax.swing.JLabel amount1Label;
    private javax.swing.JLabel amount2Label;
    private javax.swing.JFormattedTextField amountFormattedTextField;
    private javax.swing.JFormattedTextField amountFormattedTextField2;
    private javax.swing.JLabel amountLabel;
    private javax.swing.JTextField counterpartyTextField;
    private javax.swing.JFormattedTextField deltaFormattedTextField;
    private javax.swing.JLabel deltaLabel;
    private javax.swing.JComboBox frequencyComboBox;
    private javax.swing.JLabel frequencyLabel;
    private javax.swing.JFormattedTextField fwdPointsFormattedTextField;
    private javax.swing.JLabel fwdPtsLabel;
    private org.gaia.gui.common.GaiaRTLabel fwdRateLabel1;
    private org.gaia.gui.common.GaiaRTLabel fwdRateLabel2;
    private javax.swing.JToggleButton fwdToggleButton;
    private javax.swing.JPanel globalInfoPanel;
    private javax.swing.JTextField hedgeCounterpartyTextField;
    public javax.swing.JFormattedTextField hedgePriceFormattedTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonBuySell;
    private javax.swing.JButton jButtonColumns;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JCheckBox jCheckBoxHedge;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelCounterparty1;
    private javax.swing.JLabel jLabelSpot;
    private javax.swing.JLabel jLabelSpotInfo;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JLabel jLabelTradeId;
    private javax.swing.JLabel jLabelTradeIdhedge;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    protected javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JTextField jTextFieldTradeIdhedge;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JTextField labelTextField;
    private javax.swing.JButton legalEntityFinderButton;
    private javax.swing.JButton legalEntityFinderButton1;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    private com.toedter.calendar.JDateChooser maturityDateChooser;
    private javax.swing.JLabel maturityLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JLabel nominalLabel;
    private javax.swing.JComboBox notionalCurrencyComboBox;
    private javax.swing.JFormattedTextField notionalFormattedTextField1;
    private javax.swing.JScrollPane optionScrollPane;
    private final org.jdesktop.swingx.JXTable optionTable = new org.jdesktop.swingx.JXTable();
    private javax.swing.JComboBox premiumCurrencyComboBox;
    private javax.swing.JButton priceButton;
    public javax.swing.JFormattedTextField quantityFormattedTextField;
    private javax.swing.JLabel rateLabel;
    private javax.swing.JLabel rateLabel3;
    private javax.swing.JLabel rateLabel4;
    public javax.swing.JCheckBox realTimeCheckBox;
    private org.gaia.gui.common.GaiaRTLabel realTimeQuoteLabel;
    private javax.swing.JButton removeOptionButton;
    private javax.swing.JButton saveAsNewButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JPanel spotFwdPanel;
    private javax.swing.JToggleButton spotToggleButton;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JLabel stippingTypeLabel;
    private javax.swing.JFormattedTextField stratPriceFormattedTextField;
    private javax.swing.JComboBox strategyComboBox;
    private javax.swing.JLabel strategyLabel;
    private javax.swing.JTabbedPane strategyjTabbedPane;
    private javax.swing.JComboBox stripTypeComboBox;
    private javax.swing.JButton tradeDetailsButton;
    private javax.swing.JPanel tradeGlobalInfoPanel;
    private javax.swing.JComboBox underlyingComboBox;
    private javax.swing.JLabel underlyingLabel;
    private com.toedter.calendar.JDateChooser valueDateChooser;
    private com.toedter.calendar.JDateChooser valueDateChooser1;
    private javax.swing.JLabel valueDateLabel;
    private javax.swing.JLabel valueLabel;
    private javax.swing.JFormattedTextField volatilityFormattedTextField;
    private javax.swing.JLabel volatilityLabel2;
    // End of variables declaration//GEN-END:variables

    /**
     *
     */
    @Override
    public void componentOpened() {
        initContext();
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

    /**
     *
     */
    public void updateForwardPointsWithRate() {
        BigDecimal rate1 = GUIUtils.getComponentBigDecimalValue(fwdRateLabel1).divide(rateMultiplicator, 20, RoundingMode.UP);
        BigDecimal rate2 = GUIUtils.getComponentBigDecimalValue(fwdRateLabel2).divide(rateMultiplicator, 20, RoundingMode.UP);
        BigDecimal fwdPoints = BigDecimal.ONE;
        if (fwdToggleButton.isSelected() && rate2.doubleValue() != 0) {
            fwdPoints = rate1.add(BigDecimal.ONE).divide(rate2.add(BigDecimal.ONE), 20, RoundingMode.UP).subtract(BigDecimal.ONE);
            fwdPoints = fwdPoints.divide(productCcyPair.getProductForex().getTickSize(), 20, RoundingMode.UP);
            fwdPointsFormattedTextField.setText(pointDecimalFormat.format(fwdPoints));
        } else if (rate1.doubleValue() != 0) {
            fwdPoints = rate2.add(BigDecimal.ONE).divide(rate1.add(BigDecimal.ONE), 20, RoundingMode.UP).subtract(BigDecimal.ONE);
            fwdPoints = fwdPoints.divide(productCcyPair.getProductForex().getTickSize(), 20, RoundingMode.UP);
            fwdPointsFormattedTextField.setText(pointDecimalFormat.format(fwdPoints));
        }
        updateForwardWithFwdPoint(fwdPoints);
    }

    /**
     * stor all trade and create a trade groupe if we have a trade hedge
     */
    private void storeTrade() {
        fillTrade();
        saveTrade();
        Trade mainTrade = getTrade();
        if (mainTrade.getTradeId() != null) {
            jTextFieldTradeId.setText(mainTrade.getTradeId().toString());
            int i = 0;
            for (Product und : mainTrade.getProduct().getSubProducts()) {
                optionTable.getModel().setValueAt(und.getProductId(), i, 0);
                i++;
            }

            if (jCheckBoxHedge.isSelected()) {
                fillTradehedge();
                storeTradeHedge();
                jTextFieldTradeIdhedge.setText(hedgeTrade.getTradeId().toString());
                /**
                 * create trade Group
                 */
                if (tradeGroup == null) {
                    tradeGroup = new TradeGroup(TradeGroup.TradeGroupType.FX_Strategy.name());
                    tradeGroup.addTrade(mainTrade);
                    tradeGroup.addTrade(hedgeTrade);
                    getTrade().setTradeGroups(tradeGroupList);
                    hedgeTrade.setTradeGroups(tradeGroupList);
                    tradeGroup = (TradeGroup) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.STORE_TRADE_GROUP, tradeGroup);
                }
            }
            setTrade(mainTrade);
        }
    }

    /**
     * save trade hedge
     */
    private void storeTradeHedge() {
        hedgeTrade = saveTrade(hedgeTrade);
    }

    private Vector generateStraddleStrangleRows(Vector dataTable, Date stripMaturity) {
        dataTable.add(buildRow(StringUtils.BUY, StringUtils.PUT, null, stripMaturity, null));
        dataTable.add(buildRow(StringUtils.BUY, StringUtils.CALL, null, stripMaturity, null));
        return dataTable;
    }

    private Vector generateButterFlyRows(Vector dataTable, Date stripMaturity) {
        generateStraddleStrangleRows(dataTable, stripMaturity);
        dataTable.add(buildRow(StringUtils.SELL, StringUtils.PUT, null, stripMaturity, null));
        dataTable.add(buildRow(StringUtils.SELL, StringUtils.CALL, null, stripMaturity, null));
        return dataTable;
    }

    private Vector generateRiskReversalRows(Vector dataTable, Date stripMaturity) {
        dataTable.add(buildRow(StringUtils.BUY, StringUtils.CALL, null, stripMaturity, null));
        dataTable.add(buildRow(StringUtils.SELL, StringUtils.PUT, null, stripMaturity, null));
        return dataTable;
    }

    private void setQuotes(String quoteSet) {
        if (productCcyPair != null) {
            MarketQuote quote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_LAST_QUOTE,
                    productCcyPair.getId(), DateUtils.getDate(), quoteSet);
            if (quote != null) {
                defaultQuote = quote;
                for (int i = 0; i < model.getRowCount(); i++) {
                    model.setValueAt(quote.getClose(), i, model.findColumn("Strike"));
                }
                hedgePriceFormattedTextField.setText(pointDecimalFormat.format(defaultQuote.getClose()));

                if (isFxFwd) {
                    jLabelSpotInfo.setText(pointDecimalFormat.format(defaultQuote.getClose()));
                } else {
                    calculateConvertedAmount();
                }
            } else {
                hedgePriceFormattedTextField.setText(BigDecimal.ZERO.toString());
            }

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
            fwdPointsFormattedTextField.setText(pointDecimalFormat.format(fwdPoint));
            calculateConvertedAmount();
        } else {
            fwdPoint = BigDecimal.ZERO;
        }
        return fwdPoint;
    }

    private synchronized void updateForwardWithFwdPoint(BigDecimal fwdPoints) {
        if (productCcyPair != null && productCcyPair.getProductForex() != null) {

            fwdPoints = fwdPoints.multiply(productCcyPair.getProductForex().getTickSize());
            BigDecimal price = GUIUtils.getComponentBigDecimalValue(realTimeQuoteLabel);
            if (!jLabelSpotInfo.getText().isEmpty() && !realTimeCheckBox.isSelected()) {
                price = GUIUtils.getComponentBigDecimalValue(jLabelSpotInfo);
            }
            hedgePriceFormattedTextField.setText(pointDecimalFormat.format(fwdPoints.add(price)));

            calculateConvertedAmount();
        }
    }

    private void updateForwardWithFwdPoint() {
        BigDecimal fwdPoints = GUIUtils.getComponentBigDecimalValue(fwdPointsFormattedTextField);
        updateForwardWithFwdPoint(fwdPoints);
    }

    /**
     * Stop real time
     */
    public void closeComponent() {
        if (isFxFwd) {
            fwdRateLabel1.stopRTQuoteSubscription();
            fwdRateLabel2.stopRTQuoteSubscription();
        }
        realTimeQuoteLabel.stopRTQuoteSubscription();
    }

    /**
     *
     * @return
     */
    public BigDecimal getFwdPoints() {
        try {
            BigDecimal pts = BigDecimal.valueOf(GaiaTopComponent.numberFormat.parse(fwdPointsFormattedTextField.getText()).doubleValue());
            return pts.multiply(productCcyPair.getProductForex().getTickSize());
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return BigDecimal.ZERO;
    }

    /**
     * java fx methods create Area Chart Pay Off
     */
    protected LineChart<Number, Number> createAreaChartFromProduct() {
        LineChart<Number, Number> areaChart = null;

        List<Double> xList = new ArrayList();

        if (getProduct().getSubProducts() != null && getProduct().getSubProducts().size() > 0) {
            for (Product sub : getProduct().getSubProducts()) {
                if (sub.getProductForex() != null && sub.getProductForex().getStrike() != null) {
                    if (!xList.contains(sub.getProductForex().getStrike().doubleValue())) {
                        xList.add(sub.getProductForex().getStrike().doubleValue());
                    }
                }
            }

            Double min = Double.MAX_VALUE;
            Double max = 0.;
            for (Double x : xList) {
                if (x > max) {
                    max = x;
                }
                if (x < min) {
                    min = x;
                }
            }

            double lag = (max + min) / 2 / 2;
            xList.add(min - lag);
            xList.add(max + lag);

            Object[] xArray = xList.toArray();
            Arrays.sort(xArray);

            NumberAxis yAxis = new NumberAxis();
            NumberAxis xAxis = new NumberAxis(min - lag, max + lag, lag / 10);
            xAxis.setMinorTickCount(2);

            areaChart = new LineChart<>(xAxis, yAxis);
            areaChart.setTitle("Option Combination Payoff");

            XYChart.Series<Number, Number> series = new XYChart.Series<>();
            series.setName("Strategy Payoff");
            for (Object o : xArray) {
                Double x = (Double) o;
                Double y = 0.;
                for (Product sub : getProduct().getSubProducts()) {

                    Double multiplier = sub.getNotionalMultiplier().doubleValue();

                    if (sub.getProductForex() != null && sub.getProductForex().getStrike() != null && sub.getProductForex().getOptionStyle() != null) {
                        if (sub.getProductForex().getOptionStyle().equalsIgnoreCase("Call")) {
                            y += Math.max(0, x - sub.getProductForex().getStrike().doubleValue()) * multiplier;
                        } else {
                            y += Math.max(0, sub.getProductForex().getStrike().doubleValue() - x) * multiplier;
                        }
                    }
                }
                series.getData().add(new XYChart.Data<Number, Number>(x, y));
            }
            areaChart.getData().add(series);
        }
        return areaChart;
    }

    /**
     *
     * @return @throws ParseException
     */
    protected LineChart<Number, Number> createAreaChartFromArray() throws ParseException, NumberFormatException {
        LineChart<Number, Number> areaChart = null;
        List<Double> xList = new ArrayList();

        for (int i = 0; i < optionTable.getRowCount(); i++) {
            if (model.getValueAt(i, model.findColumn("Strike")) != null) {
                Object oStrike = model.getValueAt(i, model.findColumn("Strike"));
                if (oStrike instanceof Double) {
                    Double dStrikeChanged = (Double) oStrike;
                    if (!dStrikeChanged.isInfinite() && !dStrikeChanged.isNaN()) {
                        if (!xList.contains(dStrikeChanged.doubleValue())) {
                            xList.add(dStrikeChanged.doubleValue());
                        }
                    }
                } else if (NumberUtils.isNumber(oStrike.toString())) {
                    Double dStrikeChanged = Double.parseDouble(oStrike.toString());
                    if (!dStrikeChanged.isInfinite() && !dStrikeChanged.isNaN()) {
                        if (!xList.contains(dStrikeChanged.doubleValue())) {
                            xList.add(dStrikeChanged.doubleValue());
                        }
                    }
                }
            }
        }

        Double min = Double.MAX_VALUE;
        Double max = 0.;
        for (Double x : xList) {
            if (x > max) {
                max = x;
            }
            if (x < min) {
                min = x;
            }
        }

        double lag = (max + min) / 2 / 2;
        xList.add(min - lag);
        xList.add(max + lag);

        Object[] xArray = xList.toArray();
        Arrays.sort(xArray);

        NumberAxis yAxis = new NumberAxis();
        NumberAxis xAxis = new NumberAxis(min - lag, max + lag, lag / 10);
        xAxis.setMinorTickCount(2);

        areaChart = new LineChart<>(xAxis, yAxis);
        areaChart.setTitle("Option Combination Payoff");

        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        series.setName("Strategy Payoff");
        for (Object o : xArray) {
            Double x = (Double) o;
            Double y = 0.;
            for (int j = 0; j < optionTable.getRowCount(); j++) {
                BigDecimal strikechanged = (new BigDecimal(model.getValueAt(j, model.findColumn("Strike")).toString()));
                Double multiplier = numberFormat.parse(model.getValueAt(j, model.findColumn("Notional")).toString()).doubleValue();

                if ((model.getValueAt(j, model.findColumn("Option Style")).toString()).equalsIgnoreCase("Call")) {
                    y += Math.max(0, x - strikechanged.doubleValue()) * multiplier;
                } else {
                    y += Math.max(0, strikechanged.doubleValue() - x) * multiplier;
                }

            }
            series.getData().add(new XYChart.Data<Number, Number>(x, y));
        }
        areaChart.getData().add(series);
        return areaChart;
    }

    /**
     * create Scene for Päy Off when load or save product
     */
    private void createSceneFromProduct() {
        chart = createAreaChartFromProduct();
        if (chartFxPanel != null && chart != null) {
            chartFxPanel.setScene(new Scene(chart));
        }
    }

    /**
     * create Scene for Päy Off when the value is modified in the tabel
     */
    private void createSceneFromArray() throws ParseException, NumberFormatException {
        chart = createAreaChartFromArray();
        if (chartFxPanel != null && chart != null) {
            chartFxPanel.setScene(new Scene(chart));
        }
    }

    /**
     *
     * @param fromProduct
     */
    public void drawChart(final boolean fromProduct) {

        strategyjTabbedPane.remove(panel);
        /**
         * create javafx panel for charts
         */
        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));
        Platform.setImplicitExit(false);

        /**
         * create javafx panel for browser
         */
        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        /**
         * JTable
         */
        JTable table = new JTable(model);
        table.setAutoCreateRowSorter(true);
        JScrollPane tablePanel = new JScrollPane(table);
        tablePanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, TABLE_PANEL_HEIGHT_INT));
        JPanel chartTablePanel = new JPanel();
        chartTablePanel.setLayout(new BorderLayout());
        chartTablePanel.add(chartFxPanel, BorderLayout.CENTER);
        panel.add(chartTablePanel, BorderLayout.CENTER);
        panel.add(tablePanel, BorderLayout.SOUTH);
        add(panel, BorderLayout.CENTER);
        strategyjTabbedPane.add("Payoff", panel);

        /**
         * create JavaFX scene
         */
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                /**
                 * if load or save product
                 */
                if (fromProduct == true) {
                    createSceneFromProduct();
                } /**
                 * if value modified in the table
                 */
                else {
                    try {
                        createSceneFromArray();
                    } catch (Exception ex) {
                        logger.error(StringUtils.formatErrorMessage(ex));
                    }
                }
            }
        });
    }

    /**
     * load trade by trade
     *
     * @param tradeLoaded
     */
    private void strategyLoader(Trade tradeLoaded) {
        if (tradeLoaded != null
                && (TradeUtils.isParentProductType(ProductTypeUtil.ProductType.FX_STRATEGY.name, tradeLoaded.getProduct())
                || !tradeLoaded.getTradeGroups().isEmpty())) {

            if (!tradeLoaded.getTradeGroups().isEmpty()) {
                Collection<TradeGroup> groupList = tradeLoaded.getTradeGroups();

                for (TradeGroup tradeGroup_ : groupList) {
                    if (tradeGroup_.getTradeGroupType().equalsIgnoreCase(TradeGroup.TradeGroupType.FX_Strategy.name())) {
                        tradeGroup = tradeGroup_;
                        break;
                    }
                }
                if (tradeGroup != null) {
                    for (Trade trade : tradeGroup.getTrades()) {
                        if (ProductTypeUtil.isChildOf(trade.getProduct().getProductType(), ProductTypeUtil.ProductType.FX_STRATEGY.getName())) {
                            setTrade(trade);
                            fillWindow();
                        } else {
                            hedgeTrade = trade;
                            jCheckBoxHedge.setSelected(true);
                            fillWindowHedge();
                        }
                    }
                }

            } else if (TradeUtils.isParentProductType(ProductTypeUtil.ProductType.FX_STRATEGY.name, tradeLoaded.getProduct())) {
                setTrade(tradeLoaded);
                fillWindow();
            }
        } else {
            if (getTrade() != null) {
                TradeUtils.openTrade(this, getTrade().getId(), false);
            }
        }
        /**
         * load Curve *
         */
        if (getProduct().getSubProducts() != null) {
            setLayout(new BorderLayout());
            drawChart(true);
        }
        startRTQuote();
    }

    /**
     * class event on the table option table
     */
    public class MyCellEditorListener implements CellEditorListener {

        @Override
        public void editingStopped(ChangeEvent evt) {

            int row = optionTable.getSelectedRow();
            int col = optionTable.getSelectedColumn();
            String head = optionTable.getColumn(col).getHeaderValue().toString();
            if (head.equalsIgnoreCase("Delta") || head.equalsIgnoreCase("Volatility")) {
                updateStrike(row);
            }
            if (head.equalsIgnoreCase("Strike") || head.equalsIgnoreCase("Volatility")) {
                try {
                    Double volatility = new Double(model.getValueAt(row, model.findColumn("Volatility")).toString()) / 100;
                    Double strike = new Double(model.getValueAt(row, model.findColumn("Strike")).toString());
                    Double spot = new Double(realTimeQuoteLabel.getText());
                    Date maturity = (Date) model.getValueAt(row, model.findColumn("Maturity Date"));
                    Double r1 = 0.;
                    Double r2 = 0.;
                    String underlying = model.getValueAt(row, model.findColumn("Underlying")).toString();
                    if (!fwdRateLabel1.getText().isEmpty()) {
                        r1 = numberFormat.parse(fwdRateLabel1.getText()).doubleValue();
                    } else {
                        String cur1 = underlying.substring(0, 3);
                        BigDecimal bdr1 = (BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_RATE, cur1, maturity);
                        if (bdr1 != null) {
                            r1 = bdr1.doubleValue();
                        }
                    }
                    if (!fwdRateLabel2.getText().isEmpty()) {
                        r2 = numberFormat.parse(fwdRateLabel2.getText()).doubleValue();
                    } else {
                        String cur2 = underlying.substring(4, 7);
                        BigDecimal bdr2 = (BigDecimal) DAOCallerAgent.callMethod(CurveUtils.class, CurveUtils.GET_DEFAULT_RATE, cur2, maturity);
                        if (bdr2 != null) {
                            r2 = bdr2.doubleValue();
                        }
                    }
                    double time = DateUtils.dateDiff(DateUtils.getDate(), maturity);
                    time = time / 365;
                    String call = model.getValueAt(row, model.findColumn("Option Style")).toString();
                    Double delta;
                    if (call.equalsIgnoreCase(ProductConst.OptionType.Call.name())) {
                        delta = OSJavaQuant.option_delta_european_call_payout(spot, strike, r1, r2, volatility, time);
                    } else {
                        delta = OSJavaQuant.option_delta_european_put_payout(spot, strike, r1, r2, volatility, time);
                    }
                    model.setValueAt(delta * 100, row, model.findColumn("Delta"));

                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
            if (head.equalsIgnoreCase("Delta")) {
                BigDecimal delta = new BigDecimal(model.getValueAt(row, model.findColumn("Delta")).toString());
                BigDecimal defaultVolatility = volatility.getObservableValue(new Object[]{delta, DateUtils.getDate()});
                if (defaultVolatility != null) {
                    defaultVolatility = defaultVolatility.multiply(NumberUtils.BIGDECIMAL_100);
                    volatilityFormattedTextField.setText(decimalFormat.format(defaultVolatility));
                }
            }
            calculateHedgeTrade();
            drawChart(false);
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
