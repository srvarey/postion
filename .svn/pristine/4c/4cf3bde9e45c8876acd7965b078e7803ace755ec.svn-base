package org.gaia.gui.trades;

import com.toedter.calendar.JSpinnerDateEditor;
import java.math.BigDecimal;
import java.text.ParseException;
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
 * Top component which displays FRA.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//FRA//EN", autostore = false)
@TopComponent.Description(preferredID = "FRATopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.FRATopComponent")
@ActionReference(path = "Menu" + MenuManager.FRATopComponentMenu, position = MenuManager.FRATopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FRAAction")
@Messages({"CTL_FRAAction=FRA", "CTL_FRATopComponent=FRA Window", "HINT_FRATopComponent=This is a FRA window"})
public final class FRATopComponent extends GaiaTradeTopComponent {

    private AssetFinder assetFinder;
    private static final Logger logger = Logger.getLogger(FRATopComponent.class);
    private static ArrayList<ProductTypeUtil.ProductType> curveType = new ArrayList<>();

    public FRATopComponent() {
        initComponents();
        setName(Bundle.CTL_FRATopComponent());
        setToolTipText(Bundle.HINT_FRATopComponent());
        curveType.add(ProductTypeUtil.ProductType.FRA);
        jFormattedTextFieldType.setText(ProductTypeUtil.ProductType.FRA.getName());
        setProductReferences(new ArrayList());
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.FRA);
    }

    @Override
    public void initContext() {
        try {
            jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity.setDate(DateUtils.getDate());
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
//            List<String> frequencies = FrequencyUtil.getFrequencies();
//            GUIUtils.fillComboWithNullFirst(frequencyComboBox, frequencies);
            /**
             * list of status
             */
            List<String> status = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
            GUIUtils.fillCombo(statusComboBox, status);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW);
            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            /*
             * ShortCut for Amount and date
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
        String _displayName = super.getDisplayName();
        if (getTrade() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = "FRA window";
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
        if (getTrade().getQuantity() != null) {
            jFormattedTextFieldQuantity.setText(decimalFormat.format(getTrade().getQuantity()));
        }
        if (getTrade().getStatus() != null) {
            statusComboBox.setSelectedItem(getTrade().getStatus());
        }
        lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
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

            if (getProduct().getMaturityDate() != null) {
                jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
            }
            if (getProduct().getStartDate() != null) {
                jFormattedTextFieldStartDate.setDate(getProduct().getStartDate());
            }

            if (getProduct().loadSingleUnderlying() != null) {
                jTextFieldFloatingRate.setText(getProduct().loadSingleUnderlying().getShortName());
                jLabelUnderlyingId.setText(getProduct().loadSingleUnderlying().getId().toString());
            } else {
                jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
                jTextFieldFloatingRate.setText(StringUtils.EMPTY_STRING);
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
//                if (getProduct().getScheduler().getFrequency()!= null) {
//                    frequencyComboBox.setSelectedItem(getProduct().getFrequency().getAdjustment());
//                } else {
//                    frequencyComboBox.setSelectedIndex(0);
//                }
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

            getProduct().setIsAsset(true);
            if (!jTextFieldId.getText().isEmpty()) {
                getProduct().setId(Integer.parseInt(jTextFieldId.getText()));
            }

            try {

                getProduct().setProductType(ProductTypeUtil.ProductType.FRA.name());
                getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
                getProduct().setProductReferences(getProductReferences());
                getProduct().setShortName(jTextFieldName.getText());
                if (jFormattedTextFieldMaturity.getDate() != null) {
                    getProduct().setMaturityDate(jFormattedTextFieldMaturity.getDate());
                }
                if (jFormattedTextFieldStartDate.getDate() != null) {
                    getProduct().setStartDate(jFormattedTextFieldStartDate.getDate());
                }
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
                if (!jFormattedTextFieldRate.getText().isEmpty()) {
                    productRate.setRate(new BigDecimal(numberFormat.parse(jFormattedTextFieldRate.getText()).doubleValue()).divide(BigDecimal.valueOf(100)));
                }
                /**
                 * sub products
                 */
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID,
                        GUIUtils.getComponentIntegerValue(jLabelUnderlyingId));
                if (underlying != null) {
                    getProduct().addUnderlying(underlying);
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
                if (!jFormattedTextFieldPayLag.getText().isEmpty()) {
                    scheduler.setPaymentLag(Integer.valueOf(jFormattedTextFieldPayLag.getText()));
                }
                if (!jFormattedTextFieldResetLag.getText().isEmpty()) {
                    scheduler.setResetLag(Integer.valueOf(jFormattedTextFieldResetLag.getText()));
                }
                scheduler.setFrequency(FrequencyUtil.Frequency.ZEROCOUPON.name);
                scheduler.setIsPayInArrears(jCheckBoxPayInArrears.isSelected());
                scheduler.setIsPayLagBusDays(jCheckBoxPayBusDays.isSelected());
                scheduler.setIsResetInArrears(jCheckBoxResetInArrears.isSelected());
                scheduler.setIsResetLagBusDays(jCheckBoxResetBusLag.isSelected());

                getTrade().setProduct(getProduct());
            } catch (ParseException | NumberFormatException e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }

            LegalEntity internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxInternalCounterparty.getSelectedItem().toString());
            getTrade().setInternalCounterparty(internalCounterparty);

            LegalEntity counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
            BigDecimal quantity = BigDecimal.ZERO;
            getTrade().setCounterparty(counterpart);
            if (!StringUtils.isEmptyString(jFormattedTextFieldQuantity.getText())) {
                quantity = BigDecimal.valueOf(numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue());
            }
            getTrade().setQuantity(quantity);
            getTrade().setForexRate(BigDecimal.ONE);
        } catch (ParseException | NumberFormatException e) {
            logger.error("error in a num of product" + StringUtils.formatErrorMessageWithCause(e));
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        FRAjPanel = new javax.swing.JPanel();
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
        jLabelProductType = new javax.swing.JLabel();
        jTextFieldName = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextFieldRate = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel16 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabelFloatingRate = new javax.swing.JLabel();
        jTextFieldFloatingRate = new javax.swing.JTextField();
        NamejLabel = new javax.swing.JLabel();
        jLabelNotional = new javax.swing.JLabel();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxCurrency = new javax.swing.JComboBox();
        jPanelUpper = new javax.swing.JPanel();
        jLabelBook = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jLabelCounterparty = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabelTradeDate = new javax.swing.JLabel();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelMaturityDate = new javax.swing.JLabel();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldMaturity = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        jTextFieldInternalTradeId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        FRAjPanel.setBackground(new java.awt.Color(254, 252, 254));
        FRAjPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBoxDayCount.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel23.text_1")); // NOI18N

        jComboBoxAdjustment.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel24, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel24.text_1")); // NOI18N

        jFormattedTextFieldPayLag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel27, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel27.text_1")); // NOI18N

        jFormattedTextFieldResetLag.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel28, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel28.text_1")); // NOI18N

        jCheckBoxPayBusDays.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jCheckBoxPayBusDays.text_1")); // NOI18N

        jCheckBoxResetBusLag.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusLag, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jCheckBoxResetBusLag.text_1")); // NOI18N

        jCheckBoxResetInArrears.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jCheckBoxResetInArrears.text_1")); // NOI18N

        jCheckBoxPayInArrears.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jCheckBoxPayInArrears.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlyingId, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelUnderlyingId.text_1")); // NOI18N

        jTextFieldId.setEditable(false);
        jTextFieldId.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jTextFieldId.text_1")); // NOI18N

        jFormattedTextFieldType.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jFormattedTextFieldType.text_1")); // NOI18N
        jFormattedTextFieldType.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelProductType, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelProductType.text_1")); // NOI18N

        jTextFieldName.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jTextFieldName.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel31, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel31.text_1")); // NOI18N

        jFormattedTextFieldRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel16.text_1")); // NOI18N

        jButton3.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButton3.text_1")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFloatingRate, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelFloatingRate.text_1")); // NOI18N

        jTextFieldFloatingRate.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jTextFieldFloatingRate.text_1")); // NOI18N
        jTextFieldFloatingRate.setEnabled(false);
        jTextFieldFloatingRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFloatingRateActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(NamejLabel, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.NamejLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNotional, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelNotional.text_1")); // NOI18N

        jFormattedTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrencyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout FRAjPanelLayout = new javax.swing.GroupLayout(FRAjPanel);
        FRAjPanel.setLayout(FRAjPanelLayout);
        FRAjPanelLayout.setHorizontalGroup(
            FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FRAjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(FRAjPanelLayout.createSequentialGroup()
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FRAjPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabelFloatingRate))
                            .addComponent(jLabel24))
                        .addGap(18, 18, 18)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxDayCount, 0, 154, Short.MAX_VALUE)
                            .addComponent(jComboBoxAdjustment, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextFieldFloatingRate, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(FRAjPanelLayout.createSequentialGroup()
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FRAjPanelLayout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(jLabel16))
                            .addComponent(jLabelNotional))
                        .addGap(47, 47, 47)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(FRAjPanelLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel31))
                            .addGroup(FRAjPanelLayout.createSequentialGroup()
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(FRAjPanelLayout.createSequentialGroup()
                        .addComponent(NamejLabel)
                        .addGap(57, 57, 57)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FRAjPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FRAjPanelLayout.createSequentialGroup()
                                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27)
                                    .addComponent(jLabel28))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jFormattedTextFieldResetLag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jFormattedTextFieldPayLag, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(FRAjPanelLayout.createSequentialGroup()
                                        .addComponent(jCheckBoxPayBusDays)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxPayInArrears))
                                    .addGroup(FRAjPanelLayout.createSequentialGroup()
                                        .addComponent(jCheckBoxResetBusLag)
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBoxResetInArrears))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, FRAjPanelLayout.createSequentialGroup()
                                .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addGap(17, 17, 17))
        );
        FRAjPanelLayout.setVerticalGroup(
            FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(FRAjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FRAjPanelLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jFormattedTextFieldPayLag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxPayBusDays)
                            .addComponent(jCheckBoxPayInArrears))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jFormattedTextFieldResetLag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxResetBusLag)
                            .addComponent(jCheckBoxResetInArrears))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, FRAjPanelLayout.createSequentialGroup()
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NamejLabel)
                            .addComponent(jLabelNotional))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextFieldFloatingRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelFloatingRate)
                                .addComponent(jButton3))
                            .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDayCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(FRAjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAdjustment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jTextFieldId.setVisible(true);

        jPanelUpper.setBackground(new java.awt.Color(254, 252, 254));
        jPanelUpper.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelUpper.setPreferredSize(new java.awt.Dimension(1065, 124));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelBook.text_1")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelCounterparty.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel6.text_1")); // NOI18N

        jButton2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButton2.text_1")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelTradeDate.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStartDate, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelStartDate.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaturityDate, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabelMaturityDate.text_1")); // NOI18N

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.counterpartyTextField.text_1")); // NOI18N
        counterpartyTextField.setToolTipText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.counterpartyTextField.toolTipText_1")); // NOI18N

        legalEntityFinderButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.legalEntityFinderButton.text_1")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.legalEntityFinderButton.toolTipText_1")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N

        jFormattedTextFieldStartDate.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldStartDate.setName("jFormattedTextFieldStartDate"); // NOI18N

        jFormattedTextFieldMaturity.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldMaturity.setName("jFormattedTextFieldMaturity"); // NOI18N

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jTextFieldTradeTime.text_1")); // NOI18N

        jTextFieldInternalTradeId.setEditable(false);
        jTextFieldInternalTradeId.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jTextFieldInternalTradeId.text_1")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jLabel2.text")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.lifeCycleStatusLabel.text")); // NOI18N

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);

        javax.swing.GroupLayout jPanelUpperLayout = new javax.swing.GroupLayout(jPanelUpper);
        jPanelUpper.setLayout(jPanelUpperLayout);
        jPanelUpperLayout.setHorizontalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jLabelBook)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jLabelCounterparty)
                        .addGap(49, 49, 49)
                        .addComponent(counterpartyTextField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(52, 52, 52)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jLabelMaturityDate)
                        .addGap(18, 18, 18)
                        .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelTradeDate)
                            .addComponent(jLabelStartDate))
                        .addGap(29, 29, 29)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(statusComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanelUpperLayout.setVerticalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2)
                            .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelStartDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMaturityDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBook)
                            .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelCounterparty)
                            .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(legalEntityFinderButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jTextFieldInternalTradeId.setVisible(false);

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);
        windowToolBar.setMinimumSize(new java.awt.Dimension(1065, 34));

        jButtonPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonPrice.text")); // NOI18N
        jButtonPrice.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonPrice.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPriceActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonPrice);

        jButtonScheduler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonScheduler, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonScheduler.text")); // NOI18N
        jButtonScheduler.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSchedulerActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonScheduler);

        tradeDetailsButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tradeDetailsButton1.setLabel(org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.tradeDetailsButton1.label")); // NOI18N
        tradeDetailsButton1.setMaximumSize(new java.awt.Dimension(80, 30));
        tradeDetailsButton1.setPreferredSize(new java.awt.Dimension(87, 27));
        tradeDetailsButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButton1ActionPerformed(evt);
            }
        });
        windowToolBar.add(tradeDetailsButton1);

        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadEventXButton.setMaximumSize(new java.awt.Dimension(90, 30));
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(loadEventXButton);

        org.openide.awt.Mnemonics.setLocalizedText(addContractEventButton, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.addContractEventButton.text")); // NOI18N
        addContractEventButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addContractEventButton.setMaximumSize(new java.awt.Dimension(90, 30));
        addContractEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContractEventButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(addContractEventButton);

        jButtonExport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonExport.text")); // NOI18N
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
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew1, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonNew1.text")); // NOI18N
        jButtonNew1.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonNew1.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonNew1);

        jButtonSaveAsNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew1, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonSaveAsNew1.text")); // NOI18N
        jButtonSaveAsNew1.setMaximumSize(new java.awt.Dimension(90, 30));
        jButtonSaveAsNew1.setMinimumSize(new java.awt.Dimension(50, 30));
        jButtonSaveAsNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNew1ActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSaveAsNew1);

        jButtonSave1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave1, org.openide.util.NbBundle.getMessage(FRATopComponent.class, "FRATopComponent.jButtonSave1.text")); // NOI18N
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(FRAjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelUpper, javax.swing.GroupLayout.DEFAULT_SIZE, 1071, Short.MAX_VALUE)
                    .addComponent(windowToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(FRAjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

    private void jTextFieldFloatingRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFloatingRateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFloatingRateActionPerformed

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
        saveTrade();
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
    }//GEN-LAST:event_jButtonSave1ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FRAjPanel;
    private javax.swing.JLabel NamejLabel;
    private org.jdesktop.swingx.JXButton addContractEventButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField counterpartyTextField;
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
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate;
    private javax.swing.JFormattedTextField jFormattedTextFieldType;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel31;
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
}
