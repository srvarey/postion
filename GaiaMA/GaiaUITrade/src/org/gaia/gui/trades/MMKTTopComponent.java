package org.gaia.gui.trades;

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.assets.ScheduleTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import static org.gaia.gui.common.GaiaTopComponent.timeFormatter;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.AmountShortCut;
import org.gaia.gui.utils.DateShortCut;
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

/**
 * Top component which displays MMKT trades
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//MMKT//EN", autostore = false)
@TopComponent.Description(preferredID = "MMKTTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.MMKTTopComponent")
@ActionReference(path = "Menu"+MenuManager.MMKTTopComponentMenu, position = MenuManager.MMKTTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_MMKTAction")
@Messages({"CTL_MMKTAction=Money Market", "CTL_MMKTTopComponent=Money Market Window", "HINT_MMKTTopComponent=This is a Money Market window"
})
public final class MMKTTopComponent extends GaiaTradeTopComponent {

    private AssetFinder assetFinder;
    private static final Logger logger = Logger.getLogger(MMKTTopComponent.class);

    public MMKTTopComponent() {
        initComponents();
        setName(Bundle.CTL_MMKTTopComponent());
        setToolTipText(Bundle.HINT_MMKTTopComponent());
        jFormattedTextFieldType.setText(ProductTypeUtil.ProductType.MMKT.getName());
        setProductReferences(new ArrayList());
        buttonGroup1.add(jRadioButtonFixed);
        buttonGroup1.add(jRadioButtonFloating);
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.MMKT);
        jRadioButtonFixed.setSelected(true);
    }

    @Override
    public void initContext() {
        try {

            jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity.setDate(DateUtils.getDate());

            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);

            List<String> currencies = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCIES, LoggedUser.getLoggedUserId());
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            String cur = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId());
            jComboBoxCurrency.setSelectedItem(cur);

            /**
             * list of dayCounts
             */
            List<String> dayCounts = (List) DAOCallerAgent.callMethod(DayCountAccessor.class, DayCountAccessor.GET_DAYCOUNTS);
            GUIUtils.fillComboWithNullFirst(jComboBoxDayCount, dayCounts);

            /**
             * list of adjustments
             */
            GUIUtils.fillComboWithNullFirst(jComboBoxAdjustment, ProductAccessor.getCouponAdjustments());
            /**
             * list of frequencies
             */
            List<String> frequencies = FrequencyUtil.getFrequencies();
            GUIUtils.fillCombo(compoundedFrequency, frequencies);
            GUIUtils.fillCombo(frequencyComboBox, frequencies);
            compoundedFrequency.setSelectedItem(FrequencyUtil.Frequency.DAILY.name);
            frequencyComboBox.setSelectedItem(FrequencyUtil.Frequency.ZEROCOUPON.name);
            /**
             * list of status
             */
            List<String> status = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
            GUIUtils.fillCombo(statusComboBox, status);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW);

            showFixedRate();
            /**
             * ShortCut for Amout and date
             */
            AmountShortCut.eventkey(jFormattedTextFieldQuantity);

            DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldStartDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldMaturity.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }

    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null && getProduct() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + getTrade().getId();
        } else {
            _displayName = getName();
        }

        return _displayName;
    }

    @Override
    public void loadByTrade(Trade _trade) {
        jTextFieldTradeId.setText(_trade.getTradeId().toString());
        loadTrade(_trade);
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
                loadTrade(tradeLoaded);

            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    public void loadTrade(Trade _trade) {

        try {
            setTrade(_trade);
            if (getTrade() != null) {
                fillWindowWithTrade();
            } else if (getTrade() != null) {
                TradeUtils.openTrade(this, _trade.getTradeId(), false);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    private void fillWindowWithTrade() {

        jTextFieldInternalTradeId.setText(getTrade().getTradeId() != null ? getTrade().getTradeId().toString() : StringUtils.EMPTY_STRING);
        jTextFieldTradeId.setText(getTrade().getTradeId() != null ? getTrade().getTradeId().toString() : StringUtils.EMPTY_STRING);
        jComboBoxInternalCounterparty.setSelectedItem(getTrade().getInternalCounterparty().getShortName());
        counterpartyTextField.setText(getTrade().getCounterparty().getShortName());

        if (getTrade().getTradeDate() != null) {
            jSpinnerTradeDate.setDate(getTrade().getTradeDate());
        }
        if (getTrade().getTradeTime() != null) {
            jTextFieldTradeTime.setText(timeFormatter.format(getTrade().getTradeTime()));
        }

        if (getProduct().getMaturityDate() != null) {
            jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
        }

        if (getTrade().getStatus() != null) {
            statusComboBox.setSelectedItem(getTrade().getStatus());
        } else {
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW);
        }
        lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
        if (getTrade().getQuantity() != null) {
            jFormattedTextFieldQuantity.setText(decimalFormat.format(getTrade().getQuantity()));
        }
        jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());
        jTextFieldName.setText(getProduct().getShortName());

        if (getProduct() == null) {
            setProduct(new Product());
        }
        getProduct().setIsAsset(true);
        if (!jTextFieldId.getText().isEmpty()) {
            getProduct().setId(Integer.parseInt(jTextFieldId.getText()));
        }
        /**
         * fill window product
         */
        try {
            setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, getProduct().getProductId()));
            List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, getProduct().getProductId());
            getProduct().setProductReferences(references);
            jTextFieldId.setText(getProduct().getId().toString());
            jFormattedTextFieldType.setText(getProduct().getProductType());
            jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());

            if (getProduct().loadSingleUnderlying() != null) {
                jTextFieldFloatingRate.setText(getProduct().loadSingleUnderlying().getShortName());
                jLabelUnderlyingId.setText(getProduct().loadSingleUnderlying().getId().toString());
                jRadioButtonFloating.setSelected(true);
                showFloatingRateFields();
                compoundedCheckBox.setEnabled(true);
                compoundedFrequency.setEnabled(true);
            } else {
                jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
                jTextFieldFloatingRate.setText(StringUtils.EMPTY_STRING);
                jRadioButtonFixed.setSelected(true);
            }

            if (getProduct().getScheduler() != null) {

                if (getProduct().getScheduler().getDaycount() != null) {
                    jComboBoxDayCount.setSelectedItem(getProduct().getScheduler().getDaycount());
                } else {
                    jComboBoxDayCount.setSelectedIndex(0);
                }
                if (getProduct().getScheduler().getAdjustment() != null) {
                    jComboBoxAdjustment.setSelectedItem(getProduct().getScheduler().getAdjustment());
                } else {
                    jComboBoxAdjustment.setSelectedIndex(0);
                }
                if (getProduct().getScheduler().getFrequency() != null) {
                    frequencyComboBox.setSelectedItem(getProduct().getScheduler().getFrequency());
                } else {
                    frequencyComboBox.setSelectedItem(FrequencyUtil.Frequency.ZEROCOUPON.name);
                }
                if (getProduct().getScheduler().getPaymentLag() != null) {
                    jFormattedTextFieldPayLag.setText(getProduct().getScheduler().getPaymentLag().toString());
                } else {
                    jFormattedTextFieldPayLag.setText(StringUtils.EMPTY_STRING);
                }
                if (getProduct().getScheduler().getResetLag() != null) {
                    jFormattedTextFieldResetLag.setText(getProduct().getScheduler().getResetLag().toString());
                } else {
                    jFormattedTextFieldResetLag.setText(StringUtils.EMPTY_STRING);
                }
                if (getProduct().getScheduler().getIsPayInArrears() != null) {
                    jCheckBoxPayInArrears.setSelected(getProduct().getScheduler().getIsPayInArrears());
                }
                if (getProduct().getScheduler().getIsResetInArrears() != null) {
                    jCheckBoxResetInArrears.setSelected(getProduct().getScheduler().getIsResetInArrears());
                }
                if (getProduct().getScheduler().getIsPayLagBusDays() != null) {
                    jCheckBoxPayBusDays.setSelected(getProduct().getScheduler().getIsPayLagBusDays());
                }
                if (getProduct().getScheduler().getIsResetLagBusDays() != null) {
                    jCheckBoxResetBusLag.setSelected(getProduct().getScheduler().getIsResetLagBusDays());
                }
                if (getProduct().getScheduler().getIsPayInArrears() != null) {
                    jCheckBoxPayInArrears.setSelected(getProduct().getScheduler().getIsPayInArrears());
                }
                if (getProduct().getScheduler().getIsResetInArrears() != null) {
                    jCheckBoxResetInArrears.setSelected(getProduct().getScheduler().getIsResetInArrears());
                }
                if (getProduct().getScheduler().getIsPayLagBusDays() != null) {
                    jCheckBoxPayBusDays.setSelected(getProduct().getScheduler().getIsPayLagBusDays());
                }
                if (getProduct().getScheduler().getIsResetLagBusDays() != null) {
                    jCheckBoxResetBusLag.setSelected(getProduct().getScheduler().getIsResetLagBusDays());
                }
            }

            if (getProduct().getProductRate()!=null){
                if (getProduct().getProductRate().getSpread() != null) {
                    jFormattedTextFieldSpread.setText(decimalFormat.format(getProduct().getProductRate().getSpread().multiply(BigDecimal.valueOf(10000))));
                } else {
                    jFormattedTextFieldSpread.setText(StringUtils.EMPTY_STRING);
                }
                if (getProduct().getProductRate().getRate() != null) {
                    jFormattedTextFieldRate.setText(decimalFormat.format(getProduct().getProductRate().getRate().multiply(BigDecimal.valueOf(100))));
                } else {
                    jFormattedTextFieldRate.setText(StringUtils.EMPTY_STRING);
                }
            }

            productReferences = new ArrayList();
            for (ProductReference productReference : productReferences) {
                getProductReferences().add(productReference);
            }
            getProduct().setProductReferences(productReferences);
            setDisplayName(getName() + StringUtils.SPACE + getProduct().getShortName());
            /**
             * Compound or not
             */
            Scheduler scheduler = getProduct().getScheduler();
            if (scheduler != null && scheduler.getIsCompound()) {
                compoundedCheckBox.setSelected(true);
                compoundedFrequency.setSelectedItem(scheduler.getCompoundFrequency());
                compoundedFrequency.setEnabled(true);
                compoundedFrequency.setSelectedItem(scheduler.getCompoundFrequency());
            } else {
                compoundedCheckBox.setSelected(false);
                compoundedFrequency.setEnabled(false);
            }

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    @Override
    public void fillTrade() {
        if (getTrade() == null) {
            setTrade(new Trade());
        }
        if (!jTextFieldInternalTradeId.getText().isEmpty()) {
            getTrade().setId(Integer.parseInt(jTextFieldInternalTradeId.getText()));
        }
        try {
            getTrade().setTradeDate(jSpinnerTradeDate.getDate());
            getTrade().setValueDate(jSpinnerTradeDate.getDate());

            if (!jTextFieldTradeTime.getText().isEmpty()) {
                getTrade().setTradeTime(timeFormatter.parse(jTextFieldTradeTime.getText().toString()));
            }
            getTrade().setAmount(BigDecimal.ZERO);
            getTrade().setQuantityType(Trade.QuantityType.NOTIONAL.name);
            getTrade().setPrice(BigDecimal.ZERO);
            getTrade().setPriceCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getTrade().setPriceType(MarketQuote.QuotationType.PRICEpct.getName());
            getTrade().setSettlementCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getTrade().setStatus(statusComboBox.getSelectedItem().toString());
            getTrade().setLifeCycleStatus(lifeCycleStatusTextField.getText());
            if (getProduct() == null) {
                setProduct(new Product());
                getProduct().setProductReferences(new ArrayList());
                getTrade().setProduct(getProduct());
            }

            getProduct().setIsAsset(false);
            if (!jTextFieldId.getText().isEmpty()) {
                getProduct().setId(Integer.parseInt(jTextFieldId.getText()));
            }
            getProduct().setMaturityDate(jFormattedTextFieldMaturity.getDate());
            getProduct().setStartDate(jFormattedTextFieldStartDate.getDate());
            getProduct().setNotionalMultiplier(BigDecimal.ONE);
            try {

                getProduct().setProductType(ProductTypeUtil.ProductType.MMKT.getName());
                getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
                getProduct().setProductReferences(getProductReferences());

                /**
                 * product rate
                 */
                ProductRate productRate = getProduct().getProductRate();
                if (productRate == null) {
                    productRate = new ProductRate();
                    productRate.setProduct(getProduct());
                    getProduct().setProductRate(productRate);
                }
                /**
                 * credit
                 */
                ProductCredit productCredit = getProduct().getProductCredit();
                if (productCredit == null) {
                    productCredit = new ProductCredit();
                    productCredit.setProduct(getProduct());
                    getProduct().setProductCredit(productCredit);

                }
                if (!jFormattedTextFieldSpread.getText().isEmpty()) {
                    productRate.setSpread(new BigDecimal(numberFormat.parse(jFormattedTextFieldSpread.getText()).doubleValue()).divide(BigDecimal.valueOf(10000)));
                } else {
                    productRate.setRate(null);
                }

                if (!jFormattedTextFieldRate.getText().isEmpty()) {
                    productRate.setRate(new BigDecimal(numberFormat.parse(jFormattedTextFieldRate.getText()).doubleValue()).divide(BigDecimal.valueOf(100)));
                } else {
                    productRate.setRate(null);
                }

                /**
                 * sub products
                 */
                if (jRadioButtonFloating.isSelected() && !jLabelUnderlyingId.getText().isEmpty()) {
                    Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID,
                            Integer.parseInt(jLabelUnderlyingId.getText()));
                    if (underlying != null) {
                        getProduct().getUnderlyingProducts().clear();
                        getProduct().addUnderlying(underlying);
                    }
                } else {
                    getProduct().getUnderlyingProducts().clear();
                    getProduct().getProductRate().setSpread(BigDecimal.ZERO);
                }

                /**
                 * scheduler
                 */
                Scheduler scheduler = getProduct().getScheduler();
                if (scheduler == null) {
                    scheduler = new Scheduler();
                    getProduct().setScheduler(scheduler);
                }
                if (jComboBoxDayCount.getSelectedItem() != null && !jComboBoxDayCount.getSelectedItem().toString().isEmpty()) {
                    scheduler.setDaycount(jComboBoxDayCount.getSelectedItem().toString());
                }
                if (jComboBoxAdjustment.getSelectedItem() != null && !jComboBoxAdjustment.getSelectedItem().toString().isEmpty()) {
                    scheduler.setAdjustment(jComboBoxAdjustment.getSelectedItem().toString());
                }
                if (frequencyComboBox.getSelectedItem() != null && !frequencyComboBox.getSelectedItem().toString().isEmpty()) {
                    scheduler.setFrequency(frequencyComboBox.getSelectedItem().toString());
                }
                if (!jFormattedTextFieldPayLag.getText().isEmpty()) {
                    scheduler.setPaymentLag(Integer.valueOf(jFormattedTextFieldPayLag.getText()));
                }
                if (!jFormattedTextFieldResetLag.getText().isEmpty()) {
                    scheduler.setResetLag(Integer.valueOf(jFormattedTextFieldResetLag.getText()));
                }
                scheduler.setIsPayInArrears(jCheckBoxPayInArrears.isSelected());
                scheduler.setIsPayLagBusDays(jCheckBoxPayBusDays.isSelected());
                scheduler.setIsResetInArrears(jCheckBoxResetInArrears.isSelected());
                scheduler.setIsResetLagBusDays(jCheckBoxResetBusLag.isSelected());
                scheduler.setIsCompound(compoundedCheckBox.isSelected());
                scheduler.setCompoundFrequency(GUIUtils.getComponentStringValue(compoundedFrequency));
                /**
                 * Compound or not
                 */
                if (scheduler.getIsCompound()) {
                    compoundedCheckBox.setSelected(true);
                    compoundedFrequency.setSelectedItem(scheduler.getCompoundFrequency());
                    compoundedFrequency.setEnabled(true);
                }

                getTrade().setProduct(getProduct());

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }

            LegalEntity internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxInternalCounterparty.getSelectedItem().toString());
            getTrade().setInternalCounterparty(internalCounterparty);

            LegalEntity counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
            getTrade().setCounterparty(counterpart);

            if(!jFormattedTextFieldQuantity.getText().isEmpty()){
                getTrade().setQuantity(BigDecimal.valueOf(numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue()));
            }
            getTrade().setForexRate(BigDecimal.ONE);
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessageWithCause(e));
        }
    }

    @Override
    public void saveTrade() {
        try {
            setTrade(super.saveTrade(getTrade()));
            if (getTrade().getId() != null) {
                jTextFieldInternalTradeId.setText(getTrade().getId().toString());
                jTextFieldTradeId.setText(getTrade().getId().toString());
                setDisplayName(getDisplayName());
                if (getTrade().getProduct().getShortName() != null) {
                    jTextFieldName.setText(getTrade().getProduct().getShortName());
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanelUpper = new javax.swing.JPanel();
        jLabelBook = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabelTradeDate = new javax.swing.JLabel();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelMaturityDate = new javax.swing.JLabel();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldMaturity = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        jTextFieldInternalTradeId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jLabelCounterparty = new javax.swing.JLabel();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();
        MMKTjPanel = new javax.swing.JPanel();
        jComboBoxDayCount = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        jComboBoxAdjustment = new javax.swing.JComboBox();
        jLabel24 = new javax.swing.JLabel();
        jFormattedTextFieldPayLag = new javax.swing.JFormattedTextField(integerFormat);
        jLabel27 = new javax.swing.JLabel();
        jFormattedTextFieldResetLag = new javax.swing.JFormattedTextField(integerFormat);
        jLabel28 = new javax.swing.JLabel();
        jCheckBoxPayBusDays = new javax.swing.JCheckBox();
        jCheckBoxResetBusLag = new javax.swing.JCheckBox();
        jCheckBoxResetInArrears = new javax.swing.JCheckBox();
        jCheckBoxPayInArrears = new javax.swing.JCheckBox();
        jLabelUnderlyingId = new javax.swing.JLabel();
        jTextFieldId = new javax.swing.JTextField();
        jFormattedTextFieldType = new javax.swing.JFormattedTextField();
        compoundedFrequency = new javax.swing.JComboBox();
        compoundedCheckBox = new javax.swing.JCheckBox();
        jLabelProductType = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jRadioButtonFloating = new javax.swing.JRadioButton();
        jRadioButtonFixed = new javax.swing.JRadioButton();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextFieldRate = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel16 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jFormattedTextFieldSpread = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel11 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabelFloatingRate = new javax.swing.JLabel();
        jTextFieldFloatingRate = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        frequencyComboBox = new javax.swing.JComboBox();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField(decimalFormat);
        jLabelNotional = new javax.swing.JLabel();
        windowToolBar = new javax.swing.JToolBar();
        jButtonPrice = new javax.swing.JButton();
        jButtonScheduler = new javax.swing.JButton();
        tradeDetailsButton1 = new javax.swing.JButton();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        addContractEventButton = new org.jdesktop.swingx.JXButton();
        jButtonExport = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonNew1 = new javax.swing.JButton();
        jButtonSaveAsNew1 = new javax.swing.JButton();
        jButtonSave1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(195, 229, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanelUpper.setBackground(new java.awt.Color(254, 252, 254));
        jPanelUpper.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelBook.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel6.text")); // NOI18N

        jButton2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelTradeDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStartDate, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelStartDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaturityDate, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelMaturityDate.text")); // NOI18N

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N

        jFormattedTextFieldStartDate.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldStartDate.setName("jFormattedTextFieldStartDate"); // NOI18N

        jFormattedTextFieldMaturity.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldMaturity.setName("jFormattedTextFieldMaturity"); // NOI18N

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jTextFieldTradeTime.text")); // NOI18N
        jTextFieldTradeTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTradeTimeActionPerformed(evt);
            }
        });

        jTextFieldInternalTradeId.setEditable(false);
        jTextFieldInternalTradeId.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jTextFieldInternalTradeId.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel2.text")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.counterpartyTextField.text")); // NOI18N
        counterpartyTextField.setToolTipText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.counterpartyTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelCounterparty.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.lifeCycleStatusLabel.text")); // NOI18N

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);

        javax.swing.GroupLayout jPanelUpperLayout = new javax.swing.GroupLayout(jPanelUpper);
        jPanelUpper.setLayout(jPanelUpperLayout);
        jPanelUpperLayout.setHorizontalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelBook)
                    .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCounterparty))
                .addGap(17, 17, 17)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(counterpartyTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)))
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTradeDate)
                            .addComponent(jLabelStartDate))
                        .addGap(17, 17, 17))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jLabelMaturityDate)
                        .addGap(8, 8, 8)))
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanelUpperLayout.setVerticalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(9, 9, 9)
                        .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBook)
                            .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelCounterparty)
                                    .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(legalEntityFinderButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 13, Short.MAX_VALUE))
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabelStartDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabelMaturityDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))))
        );

        jTextFieldInternalTradeId.setVisible(false);

        MMKTjPanel.setBackground(new java.awt.Color(254, 252, 254));
        MMKTjPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        MMKTjPanel.setPreferredSize(new java.awt.Dimension(1041, 281));

        jComboBoxDayCount.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel23.text")); // NOI18N

        jComboBoxAdjustment.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel24, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel24.text")); // NOI18N

        jFormattedTextFieldPayLag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel27, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel27.text")); // NOI18N

        jFormattedTextFieldResetLag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldResetLag.setToolTipText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jFormattedTextFieldResetLag.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel28, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel28.text")); // NOI18N

        jCheckBoxPayBusDays.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jCheckBoxPayBusDays.text")); // NOI18N

        jCheckBoxResetBusLag.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusLag, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jCheckBoxResetBusLag.text")); // NOI18N

        jCheckBoxResetInArrears.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jCheckBoxResetInArrears.text")); // NOI18N

        jCheckBoxPayInArrears.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jCheckBoxPayInArrears.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlyingId, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelUnderlyingId.text")); // NOI18N

        jTextFieldId.setEditable(false);
        jTextFieldId.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jTextFieldId.text")); // NOI18N

        jFormattedTextFieldType.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jFormattedTextFieldType.text")); // NOI18N
        jFormattedTextFieldType.setEnabled(false);

        compoundedFrequency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        compoundedFrequency.setEnabled(false);

        compoundedCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(compoundedCheckBox, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.compoundedCheckBox.text")); // NOI18N
        compoundedCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                compoundedCheckBoxStateChanged(evt);
            }
        });
        compoundedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundedCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelProductType, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelProductType.text")); // NOI18N

        jTextFieldName.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jTextFieldName.text")); // NOI18N

        jRadioButtonFloating.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFloating, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jRadioButtonFloating.text")); // NOI18N
        jRadioButtonFloating.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFloatingActionPerformed(evt);
            }
        });

        jRadioButtonFixed.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jRadioButtonFixed, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jRadioButtonFixed.text")); // NOI18N
        jRadioButtonFixed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFixedActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel31, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel31.text")); // NOI18N

        jFormattedTextFieldRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel16.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel32, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel32.text")); // NOI18N

        jFormattedTextFieldSpread.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel11.text")); // NOI18N

        jButton3.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFloatingRate, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelFloatingRate.text")); // NOI18N

        jTextFieldFloatingRate.setText(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jTextFieldFloatingRate.text")); // NOI18N
        jTextFieldFloatingRate.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabel1.text")); // NOI18N

        frequencyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        frequencyComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrencyActionPerformed(evt);
            }
        });

        jFormattedTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNotional, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jLabelNotional.text")); // NOI18N

        javax.swing.GroupLayout MMKTjPanelLayout = new javax.swing.GroupLayout(MMKTjPanel);
        MMKTjPanel.setLayout(MMKTjPanelLayout);
        MMKTjPanelLayout.setHorizontalGroup(
            MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(MMKTjPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(MMKTjPanelLayout.createSequentialGroup()
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jRadioButtonFixed)
                                .addGap(24, 24, 24)
                                .addComponent(jRadioButtonFloating))
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(65, 65, 65)
                                .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel24)
                                    .addComponent(jLabel23))
                                .addGap(27, 27, 27)
                                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBoxAdjustment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBoxDayCount, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(31, 31, 31)
                                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                        .addComponent(jFormattedTextFieldSpread, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(frequencyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 460, Short.MAX_VALUE)
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldResetLag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldPayLag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jCheckBoxPayBusDays)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxPayInArrears))
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jCheckBoxResetBusLag)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jCheckBoxResetInArrears))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MMKTjPanelLayout.createSequentialGroup()
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jLabelNotional)
                                .addGap(43, 43, 43)
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, MMKTjPanelLayout.createSequentialGroup()
                                .addComponent(jLabelFloatingRate)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldFloatingRate, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(compoundedCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(compoundedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(85, 85, 85)
                                .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        MMKTjPanelLayout.setVerticalGroup(
            MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MMKTjPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNotional)
                    .addComponent(jFormattedTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButtonFloating)
                    .addComponent(jRadioButtonFixed))
                .addGap(4, 4, 4)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelFloatingRate)
                    .addComponent(jTextFieldFloatingRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(compoundedCheckBox)
                    .addComponent(compoundedFrequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldSpread, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MMKTjPanelLayout.createSequentialGroup()
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(frequencyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDayCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(MMKTjPanelLayout.createSequentialGroup()
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jFormattedTextFieldPayLag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxPayBusDays)
                            .addComponent(jCheckBoxPayInArrears))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jFormattedTextFieldResetLag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxResetBusLag)
                            .addComponent(jCheckBoxResetInArrears))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(MMKTjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxAdjustment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextFieldId.setVisible(true);

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);

        jButtonPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonPrice.text")); // NOI18N
        jButtonPrice.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonPrice.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPriceActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonPrice);

        jButtonScheduler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonScheduler, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonScheduler.text")); // NOI18N
        jButtonScheduler.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSchedulerActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonScheduler);

        tradeDetailsButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tradeDetailsButton1.setLabel(org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.tradeDetailsButton1.label")); // NOI18N
        tradeDetailsButton1.setMaximumSize(new java.awt.Dimension(80, 30));
        tradeDetailsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(tradeDetailsButton1);

        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadEventXButton.setMaximumSize(new java.awt.Dimension(90, 30));
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(loadEventXButton);

        org.openide.awt.Mnemonics.setLocalizedText(addContractEventButton, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.addContractEventButton.text")); // NOI18N
        addContractEventButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addContractEventButton.setMaximumSize(new java.awt.Dimension(90, 30));
        addContractEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContractEventButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(addContractEventButton);

        jButtonExport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonExport);

        jSeparator1.setMaximumSize(new java.awt.Dimension(16, 32767));
        jSeparator1.setPreferredSize(new java.awt.Dimension(6, 10));
        windowToolBar.add(jSeparator1);

        jButtonNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew1, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonNew1.text")); // NOI18N
        jButtonNew1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonNew1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonNew1);

        jButtonSaveAsNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew1, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonSaveAsNew1.text")); // NOI18N
        jButtonSaveAsNew1.setMaximumSize(new java.awt.Dimension(90, 30));
        jButtonSaveAsNew1.setMinimumSize(new java.awt.Dimension(50, 30));
        jButtonSaveAsNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSaveAsNew1);

        jButtonSave1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave1, org.openide.util.NbBundle.getMessage(MMKTTopComponent.class, "MMKTTopComponent.jButtonSave1.text")); // NOI18N
        jButtonSave1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonSave1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonSave1.setPreferredSize(new java.awt.Dimension(50, 23));
        jButtonSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSave1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSave1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(MMKTjPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(windowToolBar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(MMKTjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadTrade();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrencyActionPerformed
    }//GEN-LAST:event_jComboBoxCurrencyActionPerformed

    private void legalEntityFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButtonActionPerformed

    private void compoundedCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_compoundedCheckBoxStateChanged
        if (compoundedCheckBox.isSelected()) {
            compoundedFrequency.setEnabled(true);
        } else {
            compoundedFrequency.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBoxStateChanged

    private void compoundedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedCheckBoxActionPerformed
        if (compoundedCheckBox.isSelected()) {
            compoundedFrequency.setEnabled(true);
        } else {
            compoundedFrequency.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBoxActionPerformed

    private void jTextFieldTradeTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTradeTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTradeTimeActionPerformed

    private void jRadioButtonFloatingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFloatingActionPerformed

        showFloatingRateFields();
        /**
         * active or not compounded
         */
        if (jRadioButtonFloating.isSelected()) {
            compoundedCheckBox.setEnabled(true);
        } else {
            compoundedCheckBox.setEnabled(false);
        }
    }//GEN-LAST:event_jRadioButtonFloatingActionPerformed

    private void jRadioButtonFixedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFixedActionPerformed
        showFixedRate();
    }//GEN-LAST:event_jRadioButtonFixedActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        /**
         * find underlying
         */
        List prodtype = new ArrayList();
        prodtype.add(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX);
        assetFinder = new AssetFinder(prodtype);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();

            if (productId != null) {
                Product p = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                jLabelUnderlyingId.setText(productId.toString());
                jTextFieldFloatingRate.setText(p.getShortName());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButtonPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPriceActionPerformed
        price();
    }//GEN-LAST:event_jButtonPriceActionPerformed

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

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButtonActionPerformed

    private void addContractEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContractEventButtonActionPerformed
        showContractEventWindow();
    }//GEN-LAST:event_addContractEventButtonActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        fillTrade();
        XMLExporter.export(getTrade(), this);
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNew1ActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonNew1ActionPerformed

    private void jButtonSaveAsNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNew1ActionPerformed

        /**
         * save as new
         */
        if (getTrade() != null && getTrade().getId() != null) {
            jTextFieldId.setText(StringUtils.EMPTY_STRING);
            jTextFieldTradeId.setText(StringUtils.EMPTY_STRING);
            jTextFieldInternalTradeId.setText(StringUtils.EMPTY_STRING);
            setProduct(null);
            setTrade(null);
            fillTrade();
            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
            saveTrade();
            jTextFieldTradeId.setText(getTrade().getId().toString());
            jTextFieldId.setText(getProduct().getId().toString());
        } else {
            JOptionPane.showMessageDialog(this, "No trade loaded");
        }

    }//GEN-LAST:event_jButtonSaveAsNew1ActionPerformed

    private void jButtonSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSave1ActionPerformed
        fillTrade();
        if (getTrade().getQuantity()==null){
            JOptionPane.showMessageDialog(this,"Please enter the notional");
            return;
        } else if (getTrade().getProduct().getProductRate()!=null
                && jRadioButtonFixed.isSelected()
                && getTrade().getProduct().getProductRate().getRate()==null){
            JOptionPane.showMessageDialog(this,"Please enter the rate");
            return;
        } else if (getTrade().getProduct().getProductRate()!=null
                && jRadioButtonFloating.isSelected()
                && getTrade().getProduct().getUnderlying()==null){
            JOptionPane.showMessageDialog(this,"Please enter the floating rate");
            return;
        }

        saveTrade();

    }//GEN-LAST:event_jButtonSave1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MMKTjPanel;
    private org.jdesktop.swingx.JXButton addContractEventButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox compoundedCheckBox;
    private javax.swing.JComboBox compoundedFrequency;
    private javax.swing.JTextField counterpartyTextField;
    private javax.swing.JComboBox frequencyComboBox;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonNew1;
    private javax.swing.JButton jButtonPrice;
    private javax.swing.JButton jButtonSave1;
    private javax.swing.JButton jButtonSaveAsNew1;
    private javax.swing.JButton jButtonScheduler;
    private javax.swing.JCheckBox jCheckBoxPayBusDays;
    private javax.swing.JCheckBox jCheckBoxPayInArrears;
    private javax.swing.JCheckBox jCheckBoxResetBusLag;
    private javax.swing.JCheckBox jCheckBoxResetInArrears;
    private javax.swing.JComboBox jComboBoxAdjustment;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxDayCount;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldMaturity;
    private javax.swing.JFormattedTextField jFormattedTextFieldPayLag;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantity;
    private javax.swing.JFormattedTextField jFormattedTextFieldRate;
    private javax.swing.JFormattedTextField jFormattedTextFieldResetLag;
    private javax.swing.JFormattedTextField jFormattedTextFieldSpread;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelFloatingRate;
    private javax.swing.JLabel jLabelMaturityDate;
    private javax.swing.JLabel jLabelNotional;
    private javax.swing.JLabel jLabelProductType;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JLabel jLabelUnderlyingId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelUpper;
    private javax.swing.JRadioButton jRadioButtonFixed;
    private javax.swing.JRadioButton jRadioButtonFloating;
    private javax.swing.JToolBar.Separator jSeparator1;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    private javax.swing.JTextField jTextFieldFloatingRate;
    private javax.swing.JTextField jTextFieldId;
    private javax.swing.JTextField jTextFieldInternalTradeId;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JButton legalEntityFinderButton;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JButton tradeDetailsButton1;
    private javax.swing.JToolBar windowToolBar;
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

    private void showFixedRate() {
        /**
         * shows fixed rate
         */
        jLabel16.setVisible(true);
        jFormattedTextFieldRate.setVisible(true);
        jLabel31.setVisible(true);
        jLabelFloatingRate.setVisible(false);
        jTextFieldFloatingRate.setVisible(false);
        jTextFieldFloatingRate.setText(StringUtils.EMPTY_STRING);
        jButton3.setVisible(false);
        jLabelUnderlyingId.setVisible(false);
        jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
        jLabel11.setVisible(false);
        jFormattedTextFieldSpread.setVisible(false);
        jFormattedTextFieldSpread.setText(StringUtils.EMPTY_STRING);
        jLabel32.setVisible(false);
        compoundedFrequency.setVisible(false);
        compoundedCheckBox.setVisible(false);
    }

    private void showFloatingRateFields() {
        /**
         * hide fixed rate
         */
        jLabel16.setVisible(false);
        jFormattedTextFieldRate.setVisible(false);
        jFormattedTextFieldRate.setText(StringUtils.EMPTY_STRING);
        jLabel31.setVisible(false);
        /**
         * shows floating rate
         */
        jLabelFloatingRate.setVisible(true);
        jTextFieldFloatingRate.setVisible(true);
        jButton3.setVisible(true);
        jLabelUnderlyingId.setVisible(true);
        jLabel11.setVisible(true);
        jFormattedTextFieldSpread.setVisible(true);
        jLabel32.setVisible(true);
        compoundedFrequency.setVisible(true);
        compoundedCheckBox.setVisible(true);
    }
}
