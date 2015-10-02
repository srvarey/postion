/**
 */
package org.gaia.gui.trades;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.ProductSingleTraded;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.assets.ScheduleTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
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

/**
 * Top component which displays swaps.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//Swap//EN", autostore = false)
@TopComponent.Description(preferredID = "SwapTopComponent",iconBase="org/gaia/gui/trades/swap.png", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.SwapTopComponent")
@ActionReference(path = "Menu"+MenuManager.SwapTopComponentMenu, position = MenuManager.SwapTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_SwapAction")
@Messages({"CTL_SwapAction=Swap", "CTL_SwapTopComponent=Swap", "HINT_SwapTopComponent=This is a Swap window"})
public class SwapTopComponent extends GaiaTradeTopComponent {

    private static final Logger logger = Logger.getLogger(SwapTopComponent.class);
    private AssetFinder assetFinder = null;
    Product product1 = null;
    Product product2 = null;
    ProductSingleTraded otc1 = null;
    ProductSingleTraded otc2 = null;
    Scheduler scheduler1 = null;
    Scheduler scheduler2 = null;
    ProductCredit credit1 = null;
    ProductCredit credit2 = null;
    ArrayList<Method> listMethods = null;

    public SwapTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_SwapTopComponent());
        setToolTipText(Bundle.HINT_SwapTopComponent());
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.IRS);
        availableProductTypes.add(ProductTypeUtil.ProductType.CCS);
        availableProductTypes.add(ProductTypeUtil.ProductType.CUSTOM_CDS);
        availableProductTypes.add(ProductTypeUtil.ProductType.LOAN_CDS);
        availableProductTypes.add(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH);
        availableProductTypes.add(ProductTypeUtil.ProductType.CDS_FIXED_RECOVERY);
        availableProductTypes.add(ProductTypeUtil.ProductType.CDS_RECOVERY_LOCKS);
        availableProductTypes.add(ProductTypeUtil.ProductType.PERFORMANCE_SWAP);
        availableProductTypes.add(ProductTypeUtil.ProductType.BASIS_SWAP);
    }

    @Override
    public void initContext() {
        try {
            lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
            jTextFieldTradeTime.setText(timeFormatter.format(new Date()));
            ActionListener listener = new EnterListener(jTextFieldTradeId);

            jComboPayReceive2.setSelectedIndex(1);
            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity.setDate(DateUtils.getDate());

            jFormattedTextFieldResetLag1.setText("2");
            jFormattedTextFieldResetLag2.setText("2");
            jFormattedTextFieldPaymentLag1.setText("2");
            jFormattedTextFieldPaymentLag2.setText("2");
            jCheckBoxPayBusDays1.setSelected(true);
            jCheckBoxPayBusDays2.setSelected(true);
            jCheckBoxResetBusDays1.setSelected(true);
            jCheckBoxResetBusDays2.setSelected(true);
            jCheckBoxResetInArrears1.setSelected(false);
            jCheckBoxResetInArrears2.setSelected(false);
            jCheckBoxPayInArrears1.setSelected(true);
            jCheckBoxPayInArrears2.setSelected(true);
            jFormattedTextFieldFXRate.setText("1.0000");
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.IRS.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.CCS.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.CUSTOM_CDS.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.PERFORMANCE_SWAP.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.CDS_FIXED_RECOVERY.name);
            jComboBoxProductType.addItem(ProductTypeUtil.ProductType.CDS_RECOVERY_LOCKS.name);

            List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            GUIUtils.fillCombo(jComboBoxInternalCounterparty, entities);

            List<String> currencies = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCIES, LoggedUser.getLoggedUserId());
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            GUIUtils.fillCombo(jComboBoxCurrency1, currencies);
            GUIUtils.fillCombo(jComboBoxCurrency2, currencies);
            String cur = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY, LoggedUser.getLoggedUserId());
            jComboBoxCurrency.setSelectedItem(cur);

            List<String> frequencies = FrequencyUtil.getFrequencies();
            GUIUtils.fillCombo(jComboBoxFrequency1, frequencies);
            GUIUtils.fillCombo(jComboBoxFrequency2, frequencies);
            GUIUtils.fillCombo(compoundedFrequency2, frequencies);
            GUIUtils.fillCombo(compoundedFrequency1, frequencies);
            compoundedFrequency1.setSelectedItem(FrequencyUtil.Frequency.DAILY.name);
            compoundedFrequency2.setSelectedItem(FrequencyUtil.Frequency.DAILY.name);

            List<String> dayCounts = (List) DAOCallerAgent.callMethod(DayCountAccessor.class, DayCountAccessor.GET_DAYCOUNTS);
            GUIUtils.fillCombo(jComboBoxDayCount1, dayCounts);
            GUIUtils.fillCombo(jComboBoxDayCount2, dayCounts);

            List<String> adjustments = ProductAccessor.getCouponAdjustments();
            GUIUtils.fillCombo(jComboBoxAdjustment1, adjustments);
            GUIUtils.fillCombo(jComboBoxAdjustment2, adjustments);

            List<String> creditEvents = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_CREDIT_EVENTS);
            GUIUtils.fillCombo(jComboEventList1, creditEvents);
            GUIUtils.fillCombo(jComboEventList6, creditEvents);

            List<String> seniorities = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_BOND_SENIORITIES);
            GUIUtils.fillCombo(jComboSeniority1, seniorities);
            GUIUtils.fillCombo(jComboSeniority2, seniorities);

            List<String> issuers = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ISSUERS);
            GUIUtils.fillCombo(jComboIssuer1, issuers);
            GUIUtils.fillCombo(jComboIssuer2, issuers);

            List<String> statusList = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_STATUS_LIST);
            GUIUtils.fillCombo(statusComboBox, statusList);
            statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW);

            List<ProductTypeUtil.ProductType> productTypes = ProductTypeUtil.loadLegs();
            if (productTypes != null) {
                jComboBoxType1.removeAllItems();
                jComboBoxType2.removeAllItems();
                for (ProductTypeUtil.ProductType productType : productTypes) {
                    jComboBoxType1.addItem(productType.name);
                    jComboBoxType2.addItem(productType.name);
                }
            }

            /**
             * ShortCut for Amout and date
             */
            AmountShortCut.eventkey(jFormattedTextFieldQuantity);
            AmountShortCut.eventkey(jFormattedTextFieldNotional1);
            AmountShortCut.eventkey(jFormattedTextFieldNotional2);

            DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldStartDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldMaturity.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) firstCouponDateChooser1.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) penultimateCouponDateChooser1.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) firstCouponDateChooser2.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) penultimateCouponDateChooser2.getComponent(1));

            jComboBoxProductType.setSelectedIndex(0);

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
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
        setIsTradeAudited(false);
        loadTrade();
    }

    /**
     * function to load Trade.
     *
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
                setTrade((Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId));
                if (getTrade() != null) {
                    fillWindowWithTrade();
                } else if (getTrade() != null) {
                    TradeUtils.openTrade(this, tradeId, false);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        } else {
            JOptionPane.showMessageDialog(this, "No such trade.");
        }
    }

    @Override
    public void loadByTrade(Trade _trade) {
        setTrade(_trade);
        fillWindowWithTrade();
    }

    private void fillIndexCombo(String currency, JComboBox jCombo) {
        try {
            /**
             * load indexes by currency
             */
            jCombo.removeAllItems();
            List<Product> products = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCTS_BY_TYPE_AND_CURRENCY, ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.name, currency);
            for (Product _product : products) {
                jCombo.addItem(_product.getShortName());
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    private void fillWindowWithTrade() {
        if (getTrade() != null && TradeUtils.isSwapTrade(getTrade())) {

            jTextFieldInternalTradeId.setText(getTrade().getTradeId() != null ? getTrade().getTradeId().toString() : StringUtils.EMPTY_STRING);
            jTextFieldTradeId.setText(getTrade().getTradeId() != null ? getTrade().getTradeId().toString() : StringUtils.EMPTY_STRING);
            List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, getProduct().getId());
            getProduct().setProductReferences(references);
            jComboBoxInternalCounterparty.setSelectedItem(getTrade().getInternalCounterparty().getShortName());
            counterpartyTextField.setText(getTrade().getCounterparty().getShortName());

            if (getTrade().getTradeDate() != null) {
                jSpinnerTradeDate.setDate(getTrade().getTradeDate());
            }
            if (getTrade().getTradeTime() != null) {
                jTextFieldTradeTime.setText(timeFormatter.format(getTrade().getTradeTime()));
            }
            if (getTrade().getStatus() != null) {
                statusComboBox.setSelectedItem(getTrade().getStatus());
            }

            if (getProduct().getStartDate() != null) {
                jFormattedTextFieldStartDate.setDate(getProduct().getStartDate());
            }
            if (getTrade().getQuantity() != null && getTrade().getProduct().getNotionalMultiplier() != null) {
                jFormattedTextFieldQuantity.setText(decimalFormat.format(getTrade().getQuantity().multiply(getTrade().getProduct().getNotionalMultiplier())));
            }

            jComboBoxProductType.setSelectedItem(getProduct().getProductType());
            if (getProduct().getMaturityDate() != null) {
                jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
            }
            jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());
            jComment.setText(getProduct().getComment());
            jTextFieldName.setText(getProduct().getShortName());
            lifeCycleStatusTextField.setText(getTrade().getLifeCycleStatus());
            /**
             * leg 1
             */
            if (!getProduct().getSubProducts().isEmpty()) {
                product1 = getProduct().getSubProducts().iterator().next();
            }

            if (getTrade().getQuantity() != null && product1.getNotionalMultiplier() != null) {
                jFormattedTextFieldNotional1.setText(decimalFormat.format(getTrade().getQuantity().abs().multiply(getTrade().getProduct().getNotionalMultiplier()).multiply(product1.getNotionalMultiplier())));
            }

            jComboBoxCurrency1.setSelectedItem(product1.getNotionalCurrency());
            jComboBoxType1.setSelectedItem(product1.getProductType());
            if (product1.getNotionalMultiplier().signum() == -1) {
                jComboPayReceive1.setSelectedItem("Pay");
            }

            scheduler1 = product1.getScheduler();
            if (scheduler1 != null) {
                jComboBoxFrequency1.setSelectedItem(scheduler1.getFrequency());
                jComboBoxDayCount1.setSelectedItem(scheduler1.getDaycount());
                jComboBoxAdjustment1.setSelectedItem(scheduler1.getAdjustment());
                if (scheduler1.getPaymentLag() != null) {
                    jFormattedTextFieldPaymentLag1.setText(scheduler1.getPaymentLag().toString());
                }
                if (scheduler1.getResetLag() != null) {
                    jFormattedTextFieldResetLag1.setText(scheduler1.getResetLag().toString());
                }
                if (scheduler1.getIsPayInArrears() != null) {
                    jCheckBoxPayInArrears1.setSelected(scheduler1.getIsPayInArrears());
                }
                if (scheduler1.getIsResetInArrears() != null) {
                    jCheckBoxResetInArrears1.setSelected(scheduler1.getIsResetInArrears());
                }
                if (scheduler1.getIsPayLagBusDays() != null) {
                    jCheckBoxPayBusDays1.setSelected(scheduler1.getIsPayLagBusDays());
                }
                if (scheduler1.getIsResetLagBusDays() != null) {
                    jCheckBoxResetBusDays1.setSelected(scheduler1.getIsResetLagBusDays());
                }
                if (scheduler1.getFirstDate() != null) {
                    firstCouponDateChooser1.setDate(scheduler1.getFirstDate());
                } else {
                    firstCouponDateChooser1.setDate(null);
                }
                if (scheduler1.getPenultimateDate() != null) {
                    penultimateCouponDateChooser1.setDate(scheduler1.getPenultimateDate());
                } else {
                    penultimateCouponDateChooser1.setDate(null);
                }
                if (scheduler1.getIsCompound()) {
                    compoundedCheckBox1.setSelected(true);
                    compoundedFrequency1.setSelectedItem(scheduler1.getCompoundFrequency());
                    compoundedFrequency1.setEnabled(true);
                }

            }
            credit1 = product1.getProductCredit();
            if (credit1 != null) {
                LegalEntity issuer1 = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product1.getProductId());
                if (issuer1 != null) {
                    jComboIssuer1.setSelectedItem(issuer1.getShortName());
                }
                jComboSeniority1.setSelectedItem(credit1.getSeniority());
                jTextEventList1.setText(credit1.getCreditEvents());
                if (credit1.getAttachmentPoint() != null) {
                    jTextFieldAttachment1.setText(numberFormat.format(credit1.getAttachmentPoint().doubleValue()));
                }
                if (credit1.getExhaustionPoint() != null) {
                    jTextFieldExhaustion1.setText(numberFormat.format(credit1.getExhaustionPoint().doubleValue()));
                }
                if (credit1.getRecoveryFactor() != null) {
                    jTextFieldRecovery1.setText(numberFormat.format(credit1.getRecoveryFactor().doubleValue()));
                }
            }
            otc1 = product1.getProductSingleTraded();
            if (otc1 != null) {
                if (otc1.getRate() != null) {
                    jTextFieldRate1.setText(decimalFormat.format(otc1.getRate().multiply(BigDecimal.valueOf(100))));
                } else if (otc1.getStartPrice() != null) {
                    jTextFieldRate1.setText(decimalFormat.format(otc1.getStartPrice()));
                } else if (otc1.getSpread() != null) {
                    jTextFieldRate1.setText(decimalFormat.format(otc1.getSpread().multiply(BigDecimal.valueOf(100))));
                }
            }
            //Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_SINGLE_UNDERLYING_PRODUCT, product1);
            Product underlying = product1.loadSingleUnderlying();
            if (underlying != null) {
                if (product1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                    jComboIndex1.setSelectedItem(underlying.getShortName());
                } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                    jTextFieldUnderlying1.setText(underlying.getShortName());
                } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                    jTextFieldUnderlying1.setText(underlying.getShortName());
                }
            }

            /**
             * leg 2
             */
            Iterator it = getProduct().getSubProducts().iterator();
            it.next();
            product2 = (Product) it.next();

            if (getTrade().getQuantity() != null && product2.getNotionalMultiplier() != null) {
                jFormattedTextFieldNotional2.setText(decimalFormat.format(getTrade().getQuantity().abs().multiply(getTrade().getProduct().getNotionalMultiplier()).multiply(product2.getNotionalMultiplier())));
            }

            jComboBoxCurrency2.setSelectedItem(product2.getNotionalCurrency());
            jComboBoxType2.setSelectedItem(product2.getProductType());
            if (product2.getNotionalMultiplier().signum() == -1) {
                jComboPayReceive2.setSelectedItem("Pay");
            }

            scheduler2 = product2.getScheduler();
            if (scheduler2 != null) {
                jComboBoxFrequency2.setSelectedItem(scheduler2.getFrequency());
                jComboBoxDayCount2.setSelectedItem(scheduler2.getDaycount());
                jComboBoxAdjustment2.setSelectedItem(scheduler2.getAdjustment());
                if (scheduler2.getPaymentLag() != null) {
                    jFormattedTextFieldPaymentLag2.setText(scheduler2.getPaymentLag().toString());
                }
                if (scheduler2.getResetLag() != null) {
                    jFormattedTextFieldResetLag2.setText(scheduler2.getResetLag().toString());
                }
                jCheckBoxPayInArrears2.setSelected(scheduler2.getIsPayInArrears());
                jCheckBoxResetInArrears2.setSelected(scheduler2.getIsResetInArrears());
                jCheckBoxPayBusDays2.setSelected(scheduler2.getIsPayLagBusDays());
                jCheckBoxResetBusDays2.setSelected(scheduler2.getIsResetLagBusDays());
                if (scheduler2.getFirstDate() != null) {
                    firstCouponDateChooser2.setDate(scheduler2.getFirstDate());
                } else {
                    firstCouponDateChooser2.setDate(null);
                }
                if (scheduler2.getPenultimateDate() != null) {
                    penultimateCouponDateChooser2.setDate(scheduler2.getPenultimateDate());
                } else {
                    penultimateCouponDateChooser2.setDate(null);
                }

                if (scheduler2.getIsCompound()) {
                    compoundedCheckBox2.setSelected(true);
                    compoundedFrequency2.setSelectedItem(scheduler2.getCompoundFrequency());
                    compoundedFrequency2.setEnabled(true);
                }
            }

            credit2 = product2.getProductCredit();
            if (credit2 != null) {
                LegalEntity issuer2 = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product2.getProductId());
                if (issuer2 != null) {
                    jComboIssuer2.setSelectedItem(issuer2.getShortName());
                }
                jComboSeniority2.setSelectedItem(credit2.getSeniority());
                jTextEventList2.setText(credit2.getCreditEvents());
                if (credit2.getAttachmentPoint() != null) {
                    jTextFieldAttachment2.setText(numberFormat.format(credit2.getAttachmentPoint().doubleValue()));
                }
                if (credit2.getExhaustionPoint() != null) {
                    jTextFieldExhaustion2.setText(numberFormat.format(credit2.getExhaustionPoint().doubleValue()));
                }
                if (credit2.getRecoveryFactor() != null) {
                    jTextFieldRecovery2.setText(numberFormat.format(credit2.getRecoveryFactor().doubleValue()));
                }
            }
            otc2 = product2.getProductSingleTraded();
            if (otc2 != null) {
                if (otc2.getRate() != null) {
                    jTextFieldRate2.setText(decimalFormat.format(otc2.getRate().multiply(BigDecimal.valueOf(100))));
                } else if (otc2.getStartPrice() != null) {
                    jTextFieldRate2.setText(decimalFormat.format(otc2.getStartPrice()));
                } else if (otc2.getSpread() != null) {
                    jTextFieldRate2.setText(decimalFormat.format(otc2.getSpread().multiply(BigDecimal.valueOf(100))));
                }

            }
            underlying = product2.loadSingleUnderlying();
            if (underlying != null) {
                if (product2.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                    jComboIndex2.setSelectedItem(underlying.getShortName());
                } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                    jTextFieldUnderlying2.setText(underlying.getShortName());
                } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                    jTextFieldUnderlying2.setText(underlying.getShortName());
                }
            }
            jFormattedTextFieldQuantity.firePropertyChange("value", false, false);
            setDisplayName(getDisplayName());

        } else {
            (new ErrorMessageUI("No such trade.")).setVisible(true);
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

    public void refreshNotionals() {
        if (!jFormattedTextFieldQuantity.getText().isEmpty()) {
            if (jFormattedTextFieldQuantity.getText().contains("m") || jFormattedTextFieldQuantity.getText().contains("k")) {
                jFormattedTextFieldQuantity.setText(decimalFormat.format(AmountShortCut.convertAmount(jFormattedTextFieldQuantity)));
            }
            BigDecimal notional = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity);
            BigDecimal fxRate = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldFXRate);

            if (notional.doubleValue() < 0) {
                notional = notional.negate();
                jFormattedTextFieldQuantity.setText(decimalFormat.format(notional));
            }
            if (jComboPayReceive1.getSelectedItem().toString().equalsIgnoreCase("Pay")) {
                jFormattedTextFieldNotional1.setText(decimalFormat.format(notional.negate()));
                jFormattedTextFieldNotional2.setText(decimalFormat.format(notional.multiply(fxRate)));
            } else {
                jFormattedTextFieldNotional1.setText(decimalFormat.format(notional));
                jFormattedTextFieldNotional2.setText(decimalFormat.format(notional.multiply(fxRate).negate()));
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanelUpper = new javax.swing.JPanel();
        jLabelBook = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jLabelCounterparty = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabelTradeDate = new javax.swing.JLabel();
        jLabelProductType = new javax.swing.JLabel();
        jComboBoxProductType = new javax.swing.JComboBox();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxCurrency = new javax.swing.JComboBox();
        jLabelStartDate = new javax.swing.JLabel();
        jLabelMaturityDate = new javax.swing.JLabel();
        jLabelNotional = new javax.swing.JLabel();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldMaturity = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        jTextFieldInternalTradeId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox();
        lifeCycleStatusTextField = new org.jdesktop.swingx.JXTextField();
        lifeCycleStatusLabel = new org.jdesktop.swingx.JXLabel();
        jPanelLeg1 = new javax.swing.JPanel();
        jLabelNotional1 = new javax.swing.JLabel();
        jFormattedTextFieldNotional1 = new javax.swing.JFormattedTextField(decimalFormat);
        jPanelScheduler1 = new javax.swing.JPanel();
        jLabelFrequency1 = new javax.swing.JLabel();
        jLabelDayCount1 = new javax.swing.JLabel();
        jLabelAdjustment1 = new javax.swing.JLabel();
        jComboBoxFrequency1 = new javax.swing.JComboBox();
        jComboBoxDayCount1 = new javax.swing.JComboBox();
        jComboBoxAdjustment1 = new javax.swing.JComboBox();
        jLabelPaymentLag1 = new javax.swing.JLabel();
        jLabelResetLag1 = new javax.swing.JLabel();
        jFormattedTextFieldPaymentLag1 = new javax.swing.JFormattedTextField(integerFormat);
        jFormattedTextFieldResetLag1 = new javax.swing.JFormattedTextField(integerFormat);
        jCheckBoxPayBusDays1 = new javax.swing.JCheckBox();
        jCheckBoxResetBusDays1 = new javax.swing.JCheckBox();
        jCheckBoxPayInArrears1 = new javax.swing.JCheckBox();
        jCheckBoxResetInArrears1 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        firstCouponDateChooser1 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        penultimateCouponDateChooser1 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        compoundedCheckBox1 = new javax.swing.JCheckBox();
        compoundedFrequency1 = new javax.swing.JComboBox();
        jComboBoxCurrency1 = new javax.swing.JComboBox();
        jComboPayReceive1 = new javax.swing.JComboBox();
        jComboIndex1 = new javax.swing.JComboBox();
        jLabelIndex1 = new javax.swing.JLabel();
        jLabelRate1 = new javax.swing.JLabel();
        jTextFieldRate1 = new javax.swing.JTextField();
        jComboBoxType1 = new javax.swing.JComboBox();
        jLabelInderlying1 = new javax.swing.JLabel();
        jTextFieldUnderlying1 = new javax.swing.JTextField();
        jButtonFind1 = new javax.swing.JButton();
        jLabelPct1 = new javax.swing.JLabel();
        jPanelCredit1 = new javax.swing.JPanel();
        jLabelIssuer1 = new javax.swing.JLabel();
        jComboIssuer1 = new javax.swing.JComboBox();
        jLabelSeniority1 = new javax.swing.JLabel();
        jComboSeniority1 = new javax.swing.JComboBox();
        jLabelEvents1 = new javax.swing.JLabel();
        jComboEventList1 = new javax.swing.JComboBox();
        jButtonAddEvent1 = new javax.swing.JButton();
        jButtonRemoveEvent1 = new javax.swing.JButton();
        jTextEventList1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldAttachment1 = new javax.swing.JTextField();
        jTextFieldRecovery1 = new javax.swing.JTextField();
        jTextFieldExhaustion1 = new javax.swing.JTextField();
        jPanelLeg2 = new javax.swing.JPanel();
        jLabelNotional2 = new javax.swing.JLabel();
        jFormattedTextFieldNotional2 = new javax.swing.JFormattedTextField(decimalFormat);
        jPanelScheduler2 = new javax.swing.JPanel();
        jLabelFrequency2 = new javax.swing.JLabel();
        jLabelDayCount2 = new javax.swing.JLabel();
        jLabelAdjustment2 = new javax.swing.JLabel();
        jComboBoxFrequency2 = new javax.swing.JComboBox();
        jComboBoxDayCount2 = new javax.swing.JComboBox();
        jComboBoxAdjustment2 = new javax.swing.JComboBox();
        jLabelPaymentLag2 = new javax.swing.JLabel();
        jLabelResetLag2 = new javax.swing.JLabel();
        jFormattedTextFieldPaymentLag2 = new javax.swing.JFormattedTextField(integerFormat);
        jFormattedTextFieldResetLag2 = new javax.swing.JFormattedTextField(integerFormat);
        jCheckBoxPayBusDays2 = new javax.swing.JCheckBox();
        jCheckBoxResetBusDays2 = new javax.swing.JCheckBox();
        jCheckBoxPayInArrears2 = new javax.swing.JCheckBox();
        jCheckBoxResetInArrears2 = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        firstCouponDateChooser2 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        penultimateCouponDateChooser2 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        compoundedFrequency2 = new javax.swing.JComboBox();
        compoundedCheckBox2 = new javax.swing.JCheckBox();
        jPanelCredit2 = new javax.swing.JPanel();
        jLabelIssuer2 = new javax.swing.JLabel();
        jComboIssuer2 = new javax.swing.JComboBox();
        jLabelSeniority2 = new javax.swing.JLabel();
        jComboSeniority2 = new javax.swing.JComboBox();
        jLabelEvents2 = new javax.swing.JLabel();
        jComboEventList6 = new javax.swing.JComboBox();
        jButtonAddEvent2 = new javax.swing.JButton();
        jButtonRemoveEvent2 = new javax.swing.JButton();
        jTextEventList2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldAttachment2 = new javax.swing.JTextField();
        jTextFieldRecovery2 = new javax.swing.JTextField();
        jTextFieldExhaustion2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextFieldFXRate = new javax.swing.JFormattedTextField(new DecimalFormat("### ### ### ##0.0000",decimalFormatSymbol));
        jComboBoxCurrency2 = new javax.swing.JComboBox();
        jComboPayReceive2 = new javax.swing.JComboBox();
        jLabelRate2 = new javax.swing.JLabel();
        jTextFieldRate2 = new javax.swing.JTextField();
        jComboIndex2 = new javax.swing.JComboBox();
        jLabelIndex2 = new javax.swing.JLabel();
        jComboBoxType2 = new javax.swing.JComboBox();
        jTextFieldUnderlying2 = new javax.swing.JTextField();
        jLabelInderlying2 = new javax.swing.JLabel();
        jButtonFind2 = new javax.swing.JButton();
        jLabelPct2 = new javax.swing.JLabel();
        jLabelComment = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        windowToolBar = new javax.swing.JToolBar();
        jButtonPrice = new javax.swing.JButton();
        jButtonScheduler = new javax.swing.JButton();
        tradeDetailsButton = new javax.swing.JButton();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        addContractEventButton = new org.jdesktop.swingx.JXButton();
        jButtonExport = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jButtonNew = new javax.swing.JButton();
        jButtonSaveAsNew = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanelUpper.setBackground(new java.awt.Color(254, 252, 254));
        jPanelUpper.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBook, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelBook.text")); // NOI18N

        jComboBoxInternalCounterparty.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelCounterparty.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel6.text")); // NOI18N

        jTextFieldTradeId.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButton2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButton2.text")); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeDate, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelTradeDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelProductType, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelProductType.text")); // NOI18N

        jComboBoxProductType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxProductType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxProductType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxProductTypeActionPerformed(evt);
            }
        });

        jFormattedTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
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

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrencyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStartDate, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelStartDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelMaturityDate, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelMaturityDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNotional, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelNotional.text")); // NOI18N

        counterpartyTextField.setEditable(false);
        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.counterpartyTextField.text")); // NOI18N
        counterpartyTextField.setToolTipText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.counterpartyTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        jTextFieldName.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldName.text")); // NOI18N

        jSpinnerTradeDate.setBackground(new java.awt.Color(254, 252, 254));
        jSpinnerTradeDate.setName("jSpinnerTradeDate"); // NOI18N

        jFormattedTextFieldStartDate.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldStartDate.setName("jFormattedTextFieldStartDate"); // NOI18N

        jFormattedTextFieldMaturity.setBackground(new java.awt.Color(254, 252, 254));
        jFormattedTextFieldMaturity.setName("jFormattedTextFieldMaturity"); // NOI18N

        jTextFieldTradeTime.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldTradeTime.text")); // NOI18N

        jTextFieldInternalTradeId.setEditable(false);
        jTextFieldInternalTradeId.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldInternalTradeId.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel8.text")); // NOI18N

        statusComboBox.setBackground(new java.awt.Color(255, 255, 255));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        lifeCycleStatusTextField.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.lifeCycleStatusTextField.text")); // NOI18N
        lifeCycleStatusTextField.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(lifeCycleStatusLabel, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.lifeCycleStatusLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanelUpperLayout = new javax.swing.GroupLayout(jPanelUpper);
        jPanelUpper.setLayout(jPanelUpperLayout);
        jPanelUpperLayout.setHorizontalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelTradeDate)
                    .addComponent(jLabelBook)
                    .addComponent(jLabelNotional))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldQuantity))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCurrency, 0, 65, Short.MAX_VALUE)
                            .addComponent(jTextFieldTradeTime))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelStartDate))
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUpperLayout.createSequentialGroup()
                                .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addComponent(jLabelCounterparty)
                                .addGap(50, 50, 50)))
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addComponent(jComboBoxProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldName))
                            .addGroup(jPanelUpperLayout.createSequentialGroup()
                                .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelUpperLayout.createSequentialGroup()
                        .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelMaturityDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUpperLayout.setVerticalGroup(
            jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUpperLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelBook)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCounterparty)
                    .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalEntityFinderButton)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabelTradeDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lifeCycleStatusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lifeCycleStatusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelUpperLayout.createSequentialGroup()
                        .addGroup(jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelStartDate)
                                .addComponent(jLabelNotional))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelUpperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelMaturityDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jTextFieldInternalTradeId.setVisible(false);

        jPanelLeg1.setBackground(new java.awt.Color(254, 252, 254));
        jPanelLeg1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelLeg1.setPreferredSize(new java.awt.Dimension(587, 346));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNotional1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelNotional1.text")); // NOI18N

        jFormattedTextFieldNotional1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldNotional1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldNotional1FocusLost(evt);
            }
        });

        jPanelScheduler1.setBackground(new java.awt.Color(230, 230, 253));
        jPanelScheduler1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelScheduler1.setMinimumSize(new java.awt.Dimension(470, 190));
        jPanelScheduler1.setPreferredSize(new java.awt.Dimension(495, 190));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFrequency1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelFrequency1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelDayCount1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelDayCount1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAdjustment1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelAdjustment1.text")); // NOI18N

        jComboBoxFrequency1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFrequency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxDayCount1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxAdjustment1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPaymentLag1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelPaymentLag1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelResetLag1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelResetLag1.text")); // NOI18N

        jFormattedTextFieldPaymentLag1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextFieldResetLag1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jCheckBoxPayBusDays1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxPayBusDays1.text")); // NOI18N

        jCheckBoxResetBusDays1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusDays1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxResetBusDays1.text")); // NOI18N

        jCheckBoxPayInArrears1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxPayInArrears1.text")); // NOI18N

        jCheckBoxResetInArrears1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxResetInArrears1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel26, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel26.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel25, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel25.text")); // NOI18N

        firstCouponDateChooser1.setBackground(new java.awt.Color(230, 230, 253));
        firstCouponDateChooser1.setName("firstCouponDateChooser1"); // NOI18N

        penultimateCouponDateChooser1.setBackground(new java.awt.Color(230, 230, 253));
        penultimateCouponDateChooser1.setName("penultimateCouponDateChooser1"); // NOI18N

        compoundedCheckBox1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(compoundedCheckBox1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.compoundedCheckBox1.text")); // NOI18N
        compoundedCheckBox1.setEnabled(false);
        compoundedCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundedCheckBox1ActionPerformed(evt);
            }
        });

        compoundedFrequency1.setBackground(new java.awt.Color(255, 255, 255));
        compoundedFrequency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        compoundedFrequency1.setEnabled(false);
        compoundedFrequency1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundedFrequency1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelScheduler1Layout = new javax.swing.GroupLayout(jPanelScheduler1);
        jPanelScheduler1.setLayout(jPanelScheduler1Layout);
        jPanelScheduler1Layout.setHorizontalGroup(
            jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResetLag1)
                            .addComponent(jLabelPaymentLag1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldPaymentLag1)
                            .addComponent(jFormattedTextFieldResetLag1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxPayBusDays1)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxPayInArrears1))
                            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                                .addComponent(jCheckBoxResetBusDays1)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxResetInArrears1))))
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFrequency1)
                                    .addComponent(jLabelDayCount1))
                                .addGap(14, 14, 14))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                                .addComponent(jLabelAdjustment1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxAdjustment1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDayCount1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFrequency1, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                                .addComponent(compoundedCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(compoundedFrequency1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(firstCouponDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                    .addComponent(penultimateCouponDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(8, 8, 8))
        );
        jPanelScheduler1Layout.setVerticalGroup(
            jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(compoundedFrequency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(compoundedCheckBox1))
                            .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBoxFrequency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelFrequency1)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDayCount1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDayCount1)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAdjustment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAdjustment1)
                            .addComponent(jLabel26)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                        .addComponent(firstCouponDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(penultimateCouponDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPaymentLag1)
                    .addComponent(jFormattedTextFieldPaymentLag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxPayBusDays1)
                    .addComponent(jCheckBoxPayInArrears1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResetLag1)
                    .addComponent(jFormattedTextFieldResetLag1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxResetBusDays1)
                    .addComponent(jCheckBoxResetInArrears1))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jComboBoxCurrency1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxCurrency1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrency1ActionPerformed(evt);
            }
        });

        jComboPayReceive1.setBackground(new java.awt.Color(255, 255, 255));
        jComboPayReceive1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pay", "Receive" }));
        jComboPayReceive1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPayReceive1ActionPerformed(evt);
            }
        });

        jComboIndex1.setBackground(new java.awt.Color(255, 255, 255));
        jComboIndex1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboIndex1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboIndex1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIndex1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelIndex1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelRate1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelRate1.text")); // NOI18N

        jTextFieldRate1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxType1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxType1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxType1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelInderlying1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelInderlying1.text")); // NOI18N

        jTextFieldUnderlying1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButtonFind1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonFind1.text")); // NOI18N
        jButtonFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFind1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPct1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelPct1.text")); // NOI18N

        jPanelCredit1.setBackground(new java.awt.Color(230, 230, 253));
        jPanelCredit1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelCredit1.setPreferredSize(new java.awt.Dimension(495, 158));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIssuer1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelIssuer1.text")); // NOI18N

        jComboIssuer1.setBackground(new java.awt.Color(255, 255, 255));
        jComboIssuer1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSeniority1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelSeniority1.text")); // NOI18N

        jComboSeniority1.setBackground(new java.awt.Color(255, 255, 255));
        jComboSeniority1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEvents1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelEvents1.text")); // NOI18N

        jComboEventList1.setBackground(new java.awt.Color(255, 255, 255));
        jComboEventList1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jButtonAddEvent1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddEvent1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonAddEvent1.text")); // NOI18N
        jButtonAddEvent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddEvent1ActionPerformed(evt);
            }
        });

        jButtonRemoveEvent1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveEvent1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonRemoveEvent1.text")); // NOI18N
        jButtonRemoveEvent1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveEvent1ActionPerformed(evt);
            }
        });

        jTextEventList1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel3.text")); // NOI18N

        jTextFieldAttachment1.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldAttachment1.text")); // NOI18N

        jTextFieldRecovery1.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldRecovery1.text")); // NOI18N

        jTextFieldExhaustion1.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldExhaustion1.text")); // NOI18N

        javax.swing.GroupLayout jPanelCredit1Layout = new javax.swing.GroupLayout(jPanelCredit1);
        jPanelCredit1.setLayout(jPanelCredit1Layout);
        jPanelCredit1Layout.setHorizontalGroup(
            jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextEventList1)
                    .addGroup(jPanelCredit1Layout.createSequentialGroup()
                        .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSeniority1)
                            .addComponent(jLabelIssuer1)
                            .addComponent(jLabelEvents1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCredit1Layout.createSequentialGroup()
                                .addComponent(jComboEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAddEvent1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoveEvent1))
                            .addComponent(jComboSeniority1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboIssuer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCredit1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldAttachment1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCredit1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldExhaustion1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCredit1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addComponent(jTextFieldRecovery1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelCredit1Layout.setVerticalGroup(
            jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIssuer1)
                    .addComponent(jComboIssuer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldAttachment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeniority1)
                    .addComponent(jComboSeniority1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldExhaustion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEvents1)
                    .addComponent(jComboEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddEvent1)
                    .addComponent(jButtonRemoveEvent1)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldRecovery1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelLeg1Layout = new javax.swing.GroupLayout(jPanelLeg1);
        jPanelLeg1.setLayout(jPanelLeg1Layout);
        jPanelLeg1Layout.setHorizontalGroup(
            jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addComponent(jComboPayReceive1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxType1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addComponent(jLabelNotional1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextFieldNotional1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLeg1Layout.createSequentialGroup()
                            .addComponent(jLabelIndex1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboIndex1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabelRate1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldRate1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabelPct1))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLeg1Layout.createSequentialGroup()
                            .addComponent(jLabelInderlying1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldUnderlying1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonFind1)))
                    .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelCredit1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanelLeg1Layout.setVerticalGroup(
            jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboPayReceive1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextFieldNotional1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNotional1)
                    .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboIndex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIndex1)
                    .addComponent(jLabelRate1)
                    .addComponent(jTextFieldRate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPct1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFind1)
                    .addComponent(jLabelInderlying1)
                    .addComponent(jTextFieldUnderlying1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelLeg2.setBackground(new java.awt.Color(254, 252, 254));
        jPanelLeg2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelLeg2.setPreferredSize(new java.awt.Dimension(587, 346));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelNotional2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelNotional2.text")); // NOI18N

        jFormattedTextFieldNotional2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldNotional2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldNotional2FocusLost(evt);
            }
        });

        jPanelScheduler2.setBackground(new java.awt.Color(230, 230, 253));
        jPanelScheduler2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelScheduler2.setMinimumSize(new java.awt.Dimension(470, 190));
        jPanelScheduler2.setPreferredSize(new java.awt.Dimension(495, 190));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFrequency2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelFrequency2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelDayCount2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelDayCount2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAdjustment2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelAdjustment2.text")); // NOI18N

        jComboBoxFrequency2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFrequency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxDayCount2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxAdjustment2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPaymentLag2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelPaymentLag2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelResetLag2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelResetLag2.text")); // NOI18N

        jFormattedTextFieldPaymentLag2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextFieldResetLag2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jCheckBoxPayBusDays2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxPayBusDays2.text")); // NOI18N

        jCheckBoxResetBusDays2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusDays2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxResetBusDays2.text")); // NOI18N

        jCheckBoxPayInArrears2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxPayInArrears2.text")); // NOI18N

        jCheckBoxResetInArrears2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jCheckBoxResetInArrears2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel38, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel38.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel39, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel39.text")); // NOI18N

        firstCouponDateChooser2.setBackground(new java.awt.Color(230, 230, 253));
        firstCouponDateChooser2.setName("firstCouponDateChooser2"); // NOI18N

        penultimateCouponDateChooser2.setBackground(new java.awt.Color(230, 230, 253));
        penultimateCouponDateChooser2.setName("penultimateCouponDateChooser2"); // NOI18N

        compoundedFrequency2.setBackground(new java.awt.Color(255, 255, 255));
        compoundedFrequency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        compoundedFrequency2.setEnabled(false);

        compoundedCheckBox2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(compoundedCheckBox2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.compoundedCheckBox2.text")); // NOI18N
        compoundedCheckBox2.setEnabled(false);
        compoundedCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundedCheckBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelScheduler2Layout = new javax.swing.GroupLayout(jPanelScheduler2);
        jPanelScheduler2.setLayout(jPanelScheduler2Layout);
        jPanelScheduler2Layout.setHorizontalGroup(
            jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelResetLag2)
                            .addComponent(jLabelPaymentLag2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldPaymentLag2)
                            .addComponent(jFormattedTextFieldResetLag2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                                .addComponent(jCheckBoxPayBusDays2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxPayInArrears2))
                            .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                                .addComponent(jCheckBoxResetBusDays2)
                                .addGap(18, 18, 18)
                                .addComponent(jCheckBoxResetInArrears2))))
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelFrequency2)
                            .addComponent(jLabelAdjustment2)
                            .addComponent(jLabelDayCount2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBoxAdjustment2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxDayCount2, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxFrequency2, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE))
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(compoundedCheckBox2))
                            .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel39))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel38)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(compoundedFrequency2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstCouponDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                            .addComponent(penultimateCouponDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanelScheduler2Layout.setVerticalGroup(
            jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxFrequency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFrequency2)
                    .addComponent(compoundedFrequency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(compoundedCheckBox2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxDayCount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDayCount2)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxAdjustment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAdjustment2)
                            .addComponent(jLabel38)))
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addComponent(firstCouponDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(penultimateCouponDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelPaymentLag2)
                    .addComponent(jFormattedTextFieldPaymentLag2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxPayBusDays2)
                    .addComponent(jCheckBoxPayInArrears2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelResetLag2)
                    .addComponent(jFormattedTextFieldResetLag2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBoxResetBusDays2)
                    .addComponent(jCheckBoxResetInArrears2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanelCredit2.setBackground(new java.awt.Color(230, 230, 253));
        jPanelCredit2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIssuer2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelIssuer2.text")); // NOI18N

        jComboIssuer2.setBackground(new java.awt.Color(255, 255, 255));
        jComboIssuer2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSeniority2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelSeniority2.text")); // NOI18N

        jComboSeniority2.setBackground(new java.awt.Color(255, 255, 255));
        jComboSeniority2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEvents2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelEvents2.text")); // NOI18N

        jComboEventList6.setBackground(new java.awt.Color(255, 255, 255));
        jComboEventList6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jButtonAddEvent2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddEvent2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonAddEvent2.text")); // NOI18N
        jButtonAddEvent2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddEvent2ActionPerformed(evt);
            }
        });

        jButtonRemoveEvent2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveEvent2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonRemoveEvent2.text")); // NOI18N
        jButtonRemoveEvent2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveEvent2ActionPerformed(evt);
            }
        });

        jTextEventList2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel7.text")); // NOI18N

        jTextFieldAttachment2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldAttachment2.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldAttachment2.text")); // NOI18N

        jTextFieldRecovery2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRecovery2.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldRecovery2.text")); // NOI18N

        jTextFieldExhaustion2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldExhaustion2.setText(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jTextFieldExhaustion2.text")); // NOI18N

        javax.swing.GroupLayout jPanelCredit2Layout = new javax.swing.GroupLayout(jPanelCredit2);
        jPanelCredit2.setLayout(jPanelCredit2Layout);
        jPanelCredit2Layout.setHorizontalGroup(
            jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextEventList2)
                    .addGroup(jPanelCredit2Layout.createSequentialGroup()
                        .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelSeniority2)
                            .addComponent(jLabelIssuer2)
                            .addComponent(jLabelEvents2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                                .addComponent(jComboEventList6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAddEvent2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoveEvent2))
                            .addComponent(jComboSeniority2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboIssuer2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelCredit2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldAttachment2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCredit2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(3, 3, 3)
                        .addComponent(jTextFieldExhaustion2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelCredit2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(13, 13, 13)
                        .addComponent(jTextFieldRecovery2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 25, Short.MAX_VALUE))
        );
        jPanelCredit2Layout.setVerticalGroup(
            jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIssuer2)
                    .addComponent(jComboIssuer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextFieldAttachment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelSeniority2)
                    .addComponent(jComboSeniority2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldExhaustion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelEvents2)
                    .addComponent(jComboEventList6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonAddEvent2)
                    .addComponent(jButtonRemoveEvent2)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldRecovery2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEventList2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabel13.text")); // NOI18N

        jFormattedTextFieldFXRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldFXRate.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldFXRateFocusLost(evt);
            }
        });
        jFormattedTextFieldFXRate.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jFormattedTextFieldFXRateKeyPressed(evt);
            }
        });

        jComboBoxCurrency2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrency2ActionPerformed(evt);
            }
        });

        jComboPayReceive2.setBackground(new java.awt.Color(255, 255, 255));
        jComboPayReceive2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  "Pay", "Receive" }));
        jComboPayReceive2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPayReceive2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelRate2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelRate2.text")); // NOI18N

        jTextFieldRate2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboIndex2.setBackground(new java.awt.Color(255, 255, 255));
        jComboIndex2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIndex2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelIndex2.text")); // NOI18N

        jComboBoxType2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxType2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxType2ActionPerformed(evt);
            }
        });

        jTextFieldUnderlying2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelInderlying2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelInderlying2.text")); // NOI18N

        jButtonFind2.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonFind2.text")); // NOI18N
        jButtonFind2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFind2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPct2, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelPct2.text")); // NOI18N

        javax.swing.GroupLayout jPanelLeg2Layout = new javax.swing.GroupLayout(jPanelLeg2);
        jPanelLeg2.setLayout(jPanelLeg2Layout);
        jPanelLeg2Layout.setHorizontalGroup(
            jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jLabelNotional2)
                                .addGap(32, 32, 32)
                                .addComponent(jFormattedTextFieldNotional2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jLabelIndex2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboIndex2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabelRate2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldRate2, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelPct2))))
                    .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanelLeg2Layout.createSequentialGroup()
                            .addComponent(jComboPayReceive2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jComboBoxType2, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelLeg2Layout.createSequentialGroup()
                            .addComponent(jLabelInderlying2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jTextFieldUnderlying2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButtonFind2))
                        .addComponent(jPanelScheduler2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelCredit2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanelLeg2Layout.setVerticalGroup(
            jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13))
                    .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboPayReceive2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNotional2)
                    .addComponent(jFormattedTextFieldNotional2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboIndex2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIndex2)
                    .addComponent(jLabelRate2)
                    .addComponent(jTextFieldRate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPct2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldUnderlying2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelInderlying2)
                    .addComponent(jButtonFind2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelScheduler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelCredit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabelComment, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jLabelComment.text")); // NOI18N

        jComment.setColumns(20);
        jComment.setRows(5);
        jScrollPane5.setViewportView(jComment);

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);

        jButtonPrice.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonPrice.text")); // NOI18N
        jButtonPrice.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonPrice.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPriceActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonPrice);

        jButtonScheduler.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonScheduler, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonScheduler.text")); // NOI18N
        jButtonScheduler.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonScheduler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSchedulerActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonScheduler);

        tradeDetailsButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        tradeDetailsButton.setLabel(org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.tradeDetailsButton.label")); // NOI18N
        tradeDetailsButton.setMaximumSize(new java.awt.Dimension(80, 30));
        tradeDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(tradeDetailsButton);

        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadEventXButton.setMaximumSize(new java.awt.Dimension(90, 30));
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(loadEventXButton);

        org.openide.awt.Mnemonics.setLocalizedText(addContractEventButton, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.addContractEventButton.text")); // NOI18N
        addContractEventButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        addContractEventButton.setMaximumSize(new java.awt.Dimension(90, 30));
        addContractEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addContractEventButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(addContractEventButton);

        jButtonExport.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonExport.text")); // NOI18N
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

        jButtonNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonNew.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonNew);

        jButtonSaveAsNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAsNew, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonSaveAsNew.text")); // NOI18N
        jButtonSaveAsNew.setMaximumSize(new java.awt.Dimension(90, 30));
        jButtonSaveAsNew.setMinimumSize(new java.awt.Dimension(50, 30));
        jButtonSaveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAsNewActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSaveAsNew);

        jButtonSave.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(SwapTopComponent.class, "SwapTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.setMaximumSize(new java.awt.Dimension(80, 30));
        jButtonSave.setMinimumSize(new java.awt.Dimension(139, 23));
        jButtonSave.setPreferredSize(new java.awt.Dimension(60, 23));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        windowToolBar.add(jButtonSave);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(windowToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelComment, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5))
                    .addComponent(jPanelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanelLeg1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelLeg2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanelUpper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanelLeg1, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelLeg2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelComment, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * load trade
     *
     * @param evt
     */
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        loadTrade();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBoxCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrencyActionPerformed
        if (jComboBoxCurrency.getSelectedItem() != null) {
            jComboBoxCurrency1.setSelectedItem(jComboBoxCurrency.getSelectedItem());
            jComboBoxCurrency2.setSelectedItem(jComboBoxCurrency.getSelectedItem());
            fillDefaultDayCounts(0);
        }

    }//GEN-LAST:event_jComboBoxCurrencyActionPerformed

    private void jFormattedTextFieldStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStartDateActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldStartDateActionPerformed

    /**
     * add credit event leg 1
     *
     * @param evt
     */
    private void jButtonAddEvent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddEvent1ActionPerformed

        if (jComboEventList1.getSelectedItem() != null) {
            if (jTextEventList1.getText().length() > 0) {
                jTextEventList1.setText(jTextEventList1.getText() + StringUtils.COMMA + jComboEventList1.getSelectedItem().toString());
            } else {
                jTextEventList1.setText(jComboEventList1.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_jButtonAddEvent1ActionPerformed

    /**
     * remove credit event leg 1
     *
     * @param evt
     */
    private void jButtonRemoveEvent1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveEvent1ActionPerformed
        int i = jTextEventList1.getText().lastIndexOf(StringUtils.COMMA);
        if (jTextEventList1.getText().length() > 0) {
            if (i > 0) {
                jTextEventList1.setText(jTextEventList1.getText().substring(0, i));
            } else {
                jTextEventList1.setText("");
            }
        }
    }//GEN-LAST:event_jButtonRemoveEvent1ActionPerformed

    private void jComboPayReceive1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPayReceive1ActionPerformed
        jComboPayReceive2.setSelectedIndex(1 - jComboPayReceive1.getSelectedIndex());
        checkNotionals();
    }//GEN-LAST:event_jComboPayReceive1ActionPerformed

    public void checkNotionals() {
        if (jComboPayReceive1.getSelectedItem().toString().equalsIgnoreCase("Receive")) {
            if (GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional1).doubleValue() < 0) {
                    jFormattedTextFieldNotional1.setText(jFormattedTextFieldNotional1.getText().substring(1));
            }
            if (GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional2).doubleValue() > 0) {
                jFormattedTextFieldNotional2.setText("-" + jFormattedTextFieldNotional2.getText());
            }
        } else {
            if (GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional1).doubleValue() > 0) {
                jFormattedTextFieldNotional1.setText("-" + jFormattedTextFieldNotional1.getText());
            }
            if (GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional2).doubleValue() < 0) {
                jFormattedTextFieldNotional2.setText(jFormattedTextFieldNotional2.getText().substring(1));
            }
        }
    }

    public void fillDefaultDayCounts(int leg) {
        // leg 1 for leg 1
        // leg 2 for leg 2
        // leg 0 for bothlegs
        if (leg <= 1 && jComboBoxType1.getSelectedItem() != null
                && (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                || jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name))
                && jComboBoxCurrency1.getSelectedItem() != null && !jComboBoxCurrency1.getSelectedItem().toString().isEmpty()) {
            Currency cur = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE, jComboBoxCurrency1.getSelectedItem().toString());
            if (cur != null) {
                jComboBoxDayCount1.setSelectedItem(cur.getDaycount());
            }
        }
        if ((leg == 0 || leg == 2) && jComboBoxType2.getSelectedItem() != null
                && (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                || jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name))
                && jComboBoxCurrency2.getSelectedItem() != null && !jComboBoxCurrency2.getSelectedItem().toString().isEmpty()) {
            Currency cur = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE, jComboBoxCurrency2.getSelectedItem().toString());
            if (cur != null) {
                jComboBoxDayCount2.setSelectedItem(cur.getDaycount());
            }
        }
    }

    private void jComboIndex1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboIndex1ActionPerformed
    }//GEN-LAST:event_jComboIndex1ActionPerformed

    private void jComboBoxType1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxType1ActionPerformed

        if (jComboBoxType1.getSelectedItem() != null) {
            jLabelIndex1.setVisible(false);
            jTextFieldRate1.setVisible(false);
            jLabelInderlying1.setVisible(false);
            jTextFieldUnderlying1.setVisible(false);
            jComboIndex1.setVisible(false);
            jLabelInderlying1.setVisible(false);
            jLabelRate1.setVisible(false);
            jButtonFind1.setVisible(false);
            jPanelCredit1.setVisible(false);
            jPanelScheduler1.setVisible(false);
            jLabelPct1.setVisible(false);
            compoundedCheckBox1.setEnabled(false);
            if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                jComboIndex1.setVisible(true);
                jLabelRate1.setVisible(true);
                jLabelRate1.setText("Spread");
                jTextFieldRate1.setVisible(true);
                jLabelIndex1.setVisible(true);
                jPanelScheduler1.setVisible(true);
                jLabelPct1.setVisible(true);
                compoundedCheckBox1.setEnabled(true);
            } else if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    || jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                jLabelRate1.setVisible(true);
                jLabelRate1.setText("Rate");
                jTextFieldRate1.setVisible(true);
                jPanelScheduler1.setVisible(true);
                fillDefaultDayCounts(1);
                jLabelPct1.setVisible(true);
            } else if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                jLabelInderlying1.setVisible(true);
                jLabelInderlying1.setText("Reference Obligation");
                jTextFieldUnderlying1.setVisible(true);
                jButtonFind1.setVisible(true);
                jPanelCredit1.setVisible(true);
            } else if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                jLabelInderlying1.setText("Underlying");
                jTextFieldUnderlying1.setVisible(true);
                jButtonFind1.setVisible(true);
                jLabelRate1.setVisible(true);
                jLabelRate1.setText("Start Price");
                jTextFieldRate1.setVisible(true);
                jPanelScheduler1.setVisible(true);
            }
        }

    }//GEN-LAST:event_jComboBoxType1ActionPerformed

    /**
     * asset finder
     *
     * @param evt
     */
    private void jButtonFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFind1ActionPerformed

        if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
            assetFinder = new AssetFinder(ProductTypeUtil.loadListed());

            NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                Integer productId = assetFinder.getAssetId();
                if (productId != null) {
                    try {
                        Product tempProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                        jTextFieldUnderlying1.setText(tempProduct.getShortName());
                    } catch (Exception ex) {
                        logger.error(StringUtils.formatErrorMessage(ex));
                    }
                }
                assetFinder.setVisible(false);
            }
        } else if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
            assetFinder = new AssetFinder(ProductTypeUtil.loadListedIRRates());

            NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                Integer productId = assetFinder.getAssetId();
                if (productId != null) {
                    try {
                        Product tempProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                        jTextFieldUnderlying1.setText(tempProduct.getShortName());
                    } catch (Exception ex) {
                        logger.error(StringUtils.formatErrorMessage(ex));
                    }
                }
                assetFinder.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButtonFind1ActionPerformed

    /**
     * add credit event leg 2
     *
     * @param evt
     */
    private void jFormattedTextFieldPenultimateCoupon2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPenultimateCoupon2ActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldPenultimateCoupon2ActionPerformed

    private void jButtonAddEvent2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddEvent2ActionPerformed

        if (jComboEventList6.getSelectedItem() != null) {
            if (jTextEventList2.getText().length() > 0) {
                jTextEventList2.setText(jTextEventList2.getText() + StringUtils.COMMA + jComboEventList6.getSelectedItem().toString());
            } else {
                jTextEventList2.setText(jComboEventList6.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_jButtonAddEvent2ActionPerformed

    /**
     * remove credit event leg 2
     *
     * @param evt
     */
    private void jButtonRemoveEvent2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveEvent2ActionPerformed

        int i = jTextEventList2.getText().lastIndexOf(StringUtils.COMMA);
        if (jTextEventList2.getText().length() > 0) {
            if (i > 0) {
                jTextEventList2.setText(jTextEventList2.getText().substring(0, i));
            } else {
                jTextEventList2.setText("");
            }
        }
    }//GEN-LAST:event_jButtonRemoveEvent2ActionPerformed

    private void jComboPayReceive2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPayReceive2ActionPerformed
        jComboPayReceive1.setSelectedIndex(1 - jComboPayReceive2.getSelectedIndex());
        checkNotionals();
    }//GEN-LAST:event_jComboPayReceive2ActionPerformed

    private void jComboBoxType2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxType2ActionPerformed

        if (jComboBoxType2.getSelectedItem() != null) {
            jLabelIndex2.setVisible(false);
            jTextFieldRate2.setVisible(false);
            jLabelInderlying2.setVisible(false);
            jTextFieldUnderlying2.setVisible(false);
            jComboIndex2.setVisible(false);
            jLabelInderlying2.setVisible(false);
            jLabelRate2.setVisible(false);
            jButtonFind2.setVisible(false);
            jPanelCredit2.setVisible(false);
            jPanelScheduler2.setVisible(false);
            jLabelPct2.setVisible(false);
            compoundedCheckBox2.setEnabled(false);
            if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                jComboIndex2.setVisible(true);
                jLabelRate2.setVisible(true);
                jLabelRate2.setText("Spread");
                jTextFieldRate2.setVisible(true);
                jLabelIndex2.setVisible(true);
                jPanelScheduler2.setVisible(true);
                jLabelPct2.setVisible(true);
                compoundedCheckBox2.setEnabled(true);
            } else if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    || jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                jLabelRate2.setVisible(true);
                jLabelRate2.setText("Rate");
                jTextFieldRate2.setVisible(true);
                jPanelScheduler2.setVisible(true);
                fillDefaultDayCounts(2);
                jLabelPct2.setVisible(true);
            } else if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                jLabelInderlying2.setVisible(true);
                jLabelInderlying2.setText("Reference Obligation");
                jTextFieldUnderlying2.setVisible(true);
                jButtonFind2.setVisible(true);
                jPanelCredit2.setVisible(true);
            } else if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                jLabelInderlying2.setText("Underlying");
                jTextFieldUnderlying2.setVisible(true);
                jButtonFind2.setVisible(true);
                jLabelRate2.setVisible(true);
                jLabelRate2.setText("Start Price");
                jTextFieldRate2.setVisible(true);
                jPanelScheduler2.setVisible(true);
            }
        }
    }//GEN-LAST:event_jComboBoxType2ActionPerformed

    /**
     * asset finder
     *
     * @param evt
     */
    private void jButtonFind2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFind2ActionPerformed

        if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
            assetFinder = new AssetFinder(ProductTypeUtil.loadListed());
            NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                Integer productId = assetFinder.getAssetId();
                if (productId != null) {
                    try {
                        Product tempProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                        jTextFieldUnderlying2.setText(tempProduct.getShortName());
                    } catch (Exception ex) {
                        logger.error(StringUtils.formatErrorMessage(ex));
                    }
                }
            }
        } else if (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
            assetFinder = new AssetFinder(ProductTypeUtil.loadListedIRRates());

            NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                    NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                Integer productId = assetFinder.getAssetId();
                if (productId != null) {
                    try {
                        Product tempProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                        jTextFieldUnderlying2.setText(tempProduct.getShortName());
                    } catch (Exception ex) {
                        logger.error(StringUtils.formatErrorMessage(ex));
                    }
                }
            }
        }
    }//GEN-LAST:event_jButtonFind2ActionPerformed

    /**
     * Export to xml
     *
     * @param evt
     */
    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed
        fillTrade();
        XMLExporter.export(getTrade(), this);
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    /**
     * save trade
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        fillTrade();
        saveTrade();
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
            if (statusComboBox.getSelectedItem() != null) {
                getTrade().setStatus(statusComboBox.getSelectedItem().toString());
            } else {
                getTrade().setStatus(TradeAccessor.TradeStatus.VALIDATED.name);
            }
            getTrade().setLifeCycleStatus(lifeCycleStatusTextField.getText());

            if (getProduct() == null) {
                setProduct(new Product());
                getProduct().setProductReferences(new ArrayList());
                getTrade().setProduct(getProduct());
                getProduct().setNotionalMultiplier(BigDecimal.ONE);
            }
            getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getProduct().setQuotationType(MarketQuote.QuotationType.RATE.getName());
            getProduct().setMaturityDate(jFormattedTextFieldMaturity.getDate());
            getProduct().setStartDate(jFormattedTextFieldStartDate.getDate());
            getProduct().setProductType(jComboBoxProductType.getSelectedItem().toString());
            getProduct().setIsAsset(false);
            getProduct().setComment(jComment.getText());
            getProduct().setQuantityType(Trade.QuantityType.NOTIONAL.name);
            getProduct().setStatus(ProductConst.ProductStatus.Active.name());
            getProduct().setShortName(jTextFieldName.getText());

            /**
             * leg 1
             */
            if (product1 == null) {
                product1 = new Product();
                product1.setProductReferences(new ArrayList());
            }

            if (!jFormattedTextFieldNotional1.getText().isEmpty()) {
                BigDecimal qleg1 = new BigDecimal(numberFormat.parse(jFormattedTextFieldNotional1.getText()).doubleValue());
                BigDecimal qtty = new BigDecimal(numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue());
                if (qtty.doubleValue() != 0) {
                    qleg1 = qleg1.divide(qtty, 20, RoundingMode.HALF_UP);
                }
                if (jComboPayReceive1.getSelectedItem().equals("Pay")&&qleg1.doubleValue()>0) {
                    qleg1 = qleg1.negate();
                }
                product1.setNotionalMultiplier(qleg1);
            }

            product1.setMaturityDate(getProduct().getMaturityDate());
            product1.setStartDate(getProduct().getStartDate());
            product1.setNotionalCurrency(jComboBoxCurrency1.getSelectedItem().toString());
            product1.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            product1.setProductType(GUIUtils.getComponentStringValue(jComboBoxType1));
            product1.setStatus(ProductConst.ProductStatus.Active.name());

            if (otc1 == null) {
                otc1 = new ProductSingleTraded();
                otc1.setProduct(product1);
                product1.setProductSingleTraded(otc1);
            }

            otc1.setPayoffDateClause("OnScheduleDates");
            if (product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                otc1.setPayoffDateClause("OnCreditEvent");
            }
            otc1.setPayoffConditionClause("");
            if (product1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                if (jComboIndex1.getSelectedItem() != null) {
                    otc1.setPayoffFormula("Notional*" + jComboIndex1.getSelectedItem().toString() + "*schedulePeriod");
                }
            } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    || product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                otc1.setPayoffFormula("Notional*Rate*schedulePeriod");
            } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                otc1.setPayoffFormula("Notional");
            } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                otc1.setPayoffFormula("Notional*(" + jTextFieldUnderlying1.getText() + "-startPrice)/startPrice");
            }
            if (!jTextFieldRate1.getText().isEmpty()) {
                if (product1.getProductType().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                        || product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                    otc1.setRate(GUIUtils.getComponentBigDecimalValue(jTextFieldRate1).divide(BigDecimal.valueOf(100)));
                } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                    otc1.setStartPrice(GUIUtils.getComponentBigDecimalValue(jTextFieldRate1));

                } else if (product1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                    otc1.setSpread(GUIUtils.getComponentBigDecimalValue(jTextFieldRate1).divide(BigDecimal.valueOf(100)));
                }
            }

            if (jComboIndex1.getSelectedItem() != null && product1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboIndex1.getSelectedItem().toString());
                if (underlying != null) {
                    product1.getUnderlyingProducts().clear();
                    product1.addUnderlying(underlying);
                }
            } else if ((product1.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name) || product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) && jTextFieldUnderlying1 != null) {
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jTextFieldUnderlying1.getText());
                if (underlying != null) {
                    product1.getUnderlyingProducts().clear();
                    product1.addUnderlying(underlying);
                }
            }
            if (!product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {

                if (scheduler1 == null) {
                    scheduler1 = new Scheduler();
                }
                scheduler1.setFrequency(GUIUtils.getComponentStringValue(jComboBoxFrequency1));
                scheduler1.setDaycount(GUIUtils.getComponentStringValue(jComboBoxDayCount1));
                scheduler1.setAdjustment(GUIUtils.getComponentStringValue(jComboBoxAdjustment1));
                scheduler1.setPaymentLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldPaymentLag1));
                scheduler1.setResetLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldResetLag1));
                scheduler1.setIsPayInArrears(jCheckBoxPayInArrears1.isSelected());
                scheduler1.setIsPayLagBusDays(jCheckBoxPayBusDays1.isSelected());
                scheduler1.setIsResetInArrears(jCheckBoxResetInArrears1.isSelected());
                scheduler1.setIsResetLagBusDays(jCheckBoxResetBusDays1.isSelected());
                scheduler1.setFirstDate(GUIUtils.getComponentDateValue(firstCouponDateChooser1));
                scheduler1.setPenultimateDate(GUIUtils.getComponentDateValue(penultimateCouponDateChooser1));
                scheduler1.setIsCompound(compoundedCheckBox1.isSelected());
                scheduler1.setCompoundFrequency(GUIUtils.getComponentStringValue(compoundedFrequency1));
                product1.setScheduler(scheduler1);
            }

            if (product1.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                if (credit1 == null) {
                    credit1 = new ProductCredit();
                    credit1.setProduct(product1);
                    product1.setProductCredit(credit1);
                }
                String issuerName = jComboIssuer1.getSelectedItem().toString();
                LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, issuerName);
                credit1.setIssuer(issuer);
                credit1.setSeniority(GUIUtils.getComponentStringValue(jComboSeniority1));
                credit1.setCreditEvents(GUIUtils.getComponentStringValue(jTextEventList1));
                credit1.setAttachmentPoint(GUIUtils.getComponentBigDecimalValue(jTextFieldAttachment1));
                credit1.setExhaustionPoint(GUIUtils.getComponentBigDecimalValue(jTextFieldExhaustion1));
                credit1.setRecoveryFactor(GUIUtils.getComponentBigDecimalValue(jTextFieldRecovery1));
            }

            product1.setIsAsset(false);

            /**
             * leg 2
             */
            if (product2 == null) {
                product2 = new Product();
                product2.setProductReferences(new ArrayList());
            }
            product2.setIsAsset(false);
            product2.setMaturityDate(getProduct().getMaturityDate());
            product2.setStartDate(getProduct().getStartDate());
            //            product2.setNotionalMultiplier(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldFXRate.getText())));
            product2.setNotionalCurrency(jComboBoxCurrency2.getSelectedItem().toString());
            product2.setQuotationType(getProduct().getQuotationType());
            product2.setProductType(jComboBoxType2.getSelectedItem().toString());
            product2.setStatus(ProductConst.ProductStatus.Active.name());

            if (!jFormattedTextFieldNotional2.getText().isEmpty()) {
                BigDecimal qleg2 = new BigDecimal(numberFormat.parse(jFormattedTextFieldNotional2.getText()).doubleValue());
                BigDecimal qtty = new BigDecimal(numberFormat.parse(jFormattedTextFieldQuantity.getText()).doubleValue());
                if (qtty.doubleValue() != 0) {
                    qleg2 = qleg2.divide(qtty, 20, RoundingMode.HALF_UP);
                }
                if (jComboPayReceive2.getSelectedItem().equals("Pay")&&qleg2.doubleValue()>0) {
                    qleg2 = qleg2.negate();
                }
                product2.setNotionalMultiplier(qleg2);
            }
            product2.setNotionalCurrency(jComboBoxCurrency2.getSelectedItem().toString());

            if (otc2 == null) {
                otc2 = new ProductSingleTraded();
                otc2.setProduct(product2);
                product2.setProductSingleTraded(otc2);
            }

            otc2.setPayoffDateClause("OnScheduleDates");
            if (product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                otc2.setPayoffDateClause("OnCreditEvent");
            }
            otc2.setPayoffConditionClause("");

            if (product2.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                if (jComboIndex2.getSelectedItem() != null) {
                    otc2.setPayoffFormula("Notional*" + jComboIndex2.getSelectedItem().toString() + "*schedulePeriod");
                }
            } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    || product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                otc2.setPayoffFormula("Notional*Rate*schedulePeriod");
            } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                otc2.setPayoffFormula("Notional");
            } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                otc2.setPayoffFormula("Notional*(" + jTextFieldUnderlying2.getText() + "-startPrice)/startPrice");
            }

            if (!jTextFieldRate2.getText().isEmpty()) {
                if (product2.getProductType().equals(ProductTypeUtil.ProductType.FIXED_LEG.name)
                        || product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name)) {
                    otc2.setRate(GUIUtils.getComponentBigDecimalValue(jTextFieldRate2).divide(BigDecimal.valueOf(100)));
                } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)) {
                    otc2.setStartPrice(GUIUtils.getComponentBigDecimalValue(jTextFieldRate2));

                } else if (product2.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                    otc2.setSpread(GUIUtils.getComponentBigDecimalValue(jTextFieldRate2).divide(BigDecimal.valueOf(100)));
                }
            }

            if (jComboIndex2.getSelectedItem() != null && product2.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboIndex2.getSelectedItem().toString());
                if (underlying != null) {
                    product2.getUnderlyingProducts().clear();
                    product2.addUnderlying(underlying);
                }
            } else if ((product2.getProductType().equals(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name) || product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) && jTextFieldUnderlying2 != null) {
                Product und2 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jTextFieldUnderlying2.getText());
                if (und2 != null) {
                    product2.getUnderlyingProducts().clear();
                    product2.addUnderlying(und2);
                }
            }

            if (!product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {

                if (scheduler2 == null) {
                    scheduler2 = new Scheduler();
                }
                scheduler2.setFrequency(GUIUtils.getComponentStringValue(jComboBoxFrequency2));
                scheduler2.setDaycount(GUIUtils.getComponentStringValue(jComboBoxDayCount2));
                scheduler2.setAdjustment(GUIUtils.getComponentStringValue(jComboBoxAdjustment2));
                scheduler2.setPaymentLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldPaymentLag2));
                scheduler2.setResetLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldResetLag2));
                scheduler2.setIsPayInArrears(jCheckBoxPayInArrears2.isSelected());
                scheduler2.setIsPayLagBusDays(jCheckBoxPayBusDays2.isSelected());
                scheduler2.setIsResetInArrears(jCheckBoxResetInArrears2.isSelected());
                scheduler2.setIsResetLagBusDays(jCheckBoxResetBusDays2.isSelected());
                scheduler2.setFirstDate(GUIUtils.getComponentDateValue(firstCouponDateChooser2));
                scheduler2.setPenultimateDate(GUIUtils.getComponentDateValue(penultimateCouponDateChooser2));
                scheduler2.setIsCompound(compoundedCheckBox2.isSelected());
                scheduler2.setCompoundFrequency(GUIUtils.getComponentStringValue(compoundedFrequency2));

            }
            product2.setScheduler(scheduler2);

            if (product2.getProductType().equals(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)) {
                if (credit2 == null) {
                    credit2 = new ProductCredit();
                    credit2.setProduct(product2);
                    product2.setProductCredit(credit2);
                }
                String issuerName = jComboIssuer2.getSelectedItem().toString();
                LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, issuerName);
                credit2.setIssuer(issuer);
                credit2.setSeniority(GUIUtils.getComponentStringValue(jComboSeniority2));
                credit2.setCreditEvents(GUIUtils.getComponentStringValue(jTextEventList2));
                credit2.setAttachmentPoint(GUIUtils.getComponentBigDecimalValue(jTextFieldAttachment2));
                credit2.setExhaustionPoint(GUIUtils.getComponentBigDecimalValue(jTextFieldExhaustion2));
                credit2.setRecoveryFactor(GUIUtils.getComponentBigDecimalValue(jTextFieldRecovery2));
            }

            ArrayList<Product> products = new ArrayList();
            products.add(product1);
            products.add(product2);
            getProduct().setSubProducts(products);

            LegalEntity internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxInternalCounterparty.getSelectedItem().toString());
            getTrade().setInternalCounterparty(internalCounterparty);

            LegalEntity counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
            getTrade().setCounterparty(counterpart);

            BigDecimal notional = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldQuantity);
            if (getProduct().getNotionalMultiplier().doubleValue() != 0) {
                notional = notional.divide(getProduct().getNotionalMultiplier(), 20, RoundingMode.UP);
            }
            getTrade().setQuantity(notional);
            getTrade().setForexRate(BigDecimal.ONE);
        } catch (Exception e) {
            logger.error("error in a num of product" + StringUtils.formatErrorMessageWithCause(e));
        }
    }

    @Override
    public void saveTrade() {
        try {
            if (jComboBoxType1.getSelectedItem()==null){
                JOptionPane.showMessageDialog(this, "Please select first leg type");
            } else if (jComboBoxType2.getSelectedItem()==null){
                JOptionPane.showMessageDialog(this, "Please select second leg type");
            } else if ((jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    ||jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name))
                    && jTextFieldRate1.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing Fixed rate");
            } else if ((jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    ||jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name))
                    && jTextFieldRate2.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing Fixed rate");
            } else if (jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FLOATING_LEG.name)
                    && jComboIndex1.getSelectedItem()==null){
                 JOptionPane.showMessageDialog(this, "Missing Floating rate");
            } else if (jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FLOATING_LEG.name)
                    && jComboIndex2.getSelectedItem()==null){
                 JOptionPane.showMessageDialog(this, "Missing Floating rate");
            }  else if (jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)
                    && jTextFieldRate1.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing Start Price");
            } else if (jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)
                    && jTextFieldRate2.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing Start Price");
            } else if (jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)
                    && jTextFieldUnderlying1.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing performance underlying");
            } else if (jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name)
                    && jTextFieldUnderlying2.getText().isEmpty()){
                 JOptionPane.showMessageDialog(this, "Missing performance underlying");
            } else if (jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)
                    && jComboIssuer1.getSelectedItem()==null){
                 JOptionPane.showMessageDialog(this, "Missing credit entity");
            } else if (jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name)
                    && jComboIssuer2.getSelectedItem()==null){
                 JOptionPane.showMessageDialog(this, "Missing credit entity");
            }  else if (jComboBoxType1.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FIXED_LEG.name)
                    && jComboBoxType2.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FIXED_LEG.name)) {
                JOptionPane.showMessageDialog(this, "You cannot have two fixed leg");
            } else {
                setTrade(super.saveTrade(getTrade()));
                if (getTrade().getId() != null) {
                    jTextFieldInternalTradeId.setText(getTrade().getId().toString());
                    jTextFieldTradeId.setText(getTrade().getId().toString());
                    jTextFieldName.setText(getTrade().getProduct().getShortName());
                    setDisplayName(getDisplayName());
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * Scheduler
     *
     * @param evt
     */
    private void jButtonSchedulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSchedulerActionPerformed
        fillTrade();

        ScheduleTopComponent scheduleTopComponent = new ScheduleTopComponent();
        scheduleTopComponent.setTrade(getTrade());
        scheduleTopComponent.open();
        scheduleTopComponent.requestActive();

    }//GEN-LAST:event_jButtonSchedulerActionPerformed

    private void jButtonPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPriceActionPerformed
        price();
    }//GEN-LAST:event_jButtonPriceActionPerformed

    private void jButtonSaveAsNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAsNewActionPerformed

        jTextFieldInternalTradeId.setText(StringUtils.EMPTY_STRING);
        setTrade(null);
        setProduct(null);
        product1 = null;
        product2 = null;
        otc1 = null;
        otc2 = null;
        scheduler1 = null;
        scheduler2 = null;
        credit1 = null;
        credit2 = null;
        lifeCycleStatusTextField.setText(TradeAccessor.TradeLifeCycleStatus.CREATED.name);
        statusComboBox.setSelectedItem(TradeAccessor.TradeStatus.NEW.name);
        fillTrade();
        saveTrade();

    }//GEN-LAST:event_jButtonSaveAsNewActionPerformed

    /**
     * open flows
     *
     * @param evt
     */
    private void tradeDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeDetailsButtonActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_tradeDetailsButtonActionPerformed

    private void legalEntityFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButtonActionPerformed

    private void compoundedCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedCheckBox2ActionPerformed
        if (compoundedCheckBox2.isSelected()) {
            compoundedFrequency2.setEnabled(true);
        } else {
            compoundedFrequency2.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBox2ActionPerformed

    private void compoundedCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedCheckBox1ActionPerformed
        if (compoundedCheckBox1.isSelected()) {
            compoundedFrequency1.setEnabled(true);
        } else {
            compoundedFrequency1.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBox1ActionPerformed

    private void jComboBoxCurrency1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrency1ActionPerformed
        if (jComboBoxCurrency1.getSelectedItem() != null) {
            fillIndexCombo(GUIUtils.getComponentStringValue(jComboBoxCurrency1), jComboIndex1);
            fillDefaultDayCounts(1);
        }
    }//GEN-LAST:event_jComboBoxCurrency1ActionPerformed

    private void jComboBoxCurrency2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrency2ActionPerformed
        if (jComboBoxCurrency2.getSelectedItem() != null) {
            fillIndexCombo(GUIUtils.getComponentStringValue(jComboBoxCurrency2), jComboIndex2);
            fillDefaultDayCounts(2);
        }
    }//GEN-LAST:event_jComboBoxCurrency2ActionPerformed

    private void compoundedFrequency1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedFrequency1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_compoundedFrequency1ActionPerformed

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButtonActionPerformed

    private void addContractEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addContractEventButtonActionPerformed
        showContractEventWindow();
    }//GEN-LAST:event_addContractEventButtonActionPerformed

    private void jComboBoxProductTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxProductTypeActionPerformed

        if (jComboBoxProductType.getSelectedItem() != null) {
            String type = jComboBoxProductType.getSelectedItem().toString();
            switch (type) {
                case "IRS":
                    jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.FIXED_LEG.name);
                    jComboBoxType2.setSelectedItem(ProductTypeUtil.ProductType.FLOATING_LEG.name);
                    break;
                case "CCS":
                    jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.FLOATING_LEG.name);
                    jComboBoxType2.setSelectedItem(ProductTypeUtil.ProductType.FLOATING_LEG.name);
                    break;
                case "Custom CDS":
                case "CDS Index Tranch":
                case "CDS Fixed Recovery":
                case "CDS Recovery Locks":
                    jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.CREDIT_FUNDING_LEG.name);
                    jComboBoxType2.setSelectedItem(ProductTypeUtil.ProductType.CREDIT_PROTECTION_LEG.name);
                    break;
                case "Performance Swap":
                    jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.PERFORMANCE_LEG.name);
                    jComboBoxType2.setSelectedItem(ProductTypeUtil.ProductType.FIXED_LEG.name);
                    break;
            }
        }

    }//GEN-LAST:event_jComboBoxProductTypeActionPerformed

    private void jFormattedTextFieldQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityFocusLost
        refreshNotionals();
    }//GEN-LAST:event_jFormattedTextFieldQuantityFocusLost

    private void jFormattedTextFieldFXRateFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFXRateFocusLost
        refreshNotionals();
    }//GEN-LAST:event_jFormattedTextFieldFXRateFocusLost

    private void jFormattedTextFieldQuantityKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            refreshNotionals();
        }
    }//GEN-LAST:event_jFormattedTextFieldQuantityKeyPressed

    private void jFormattedTextFieldFXRateKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFXRateKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            refreshNotionals();
        }
    }//GEN-LAST:event_jFormattedTextFieldFXRateKeyPressed

    private void jFormattedTextFieldNotional1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNotional1FocusLost
        BigDecimal notional = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional1);
        if (jComboPayReceive1.getSelectedItem().toString().equalsIgnoreCase("Pay") && notional.doubleValue() > 0) {
            jFormattedTextFieldNotional1.setText(decimalFormat.format(notional.negate()));
        } else if (jComboPayReceive1.getSelectedItem().toString().equalsIgnoreCase("Receive") && notional.doubleValue() < 0) {
            jFormattedTextFieldNotional1.setText(decimalFormat.format(notional.negate()));
        }
    }//GEN-LAST:event_jFormattedTextFieldNotional1FocusLost

    private void jFormattedTextFieldNotional2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNotional2FocusLost
        BigDecimal notional2 = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional2);
        if (jComboPayReceive2.getSelectedItem().toString().equalsIgnoreCase("Pay") && notional2.doubleValue() > 0) {
            jFormattedTextFieldNotional2.setText(decimalFormat.format(notional2.negate()));
        } else if (jComboPayReceive2.getSelectedItem().toString().equalsIgnoreCase("Receive") && notional2.doubleValue() < 0) {
            jFormattedTextFieldNotional2.setText(decimalFormat.format(notional2.negate()));
        }
        BigDecimal notional1 = GUIUtils.getComponentBigDecimalValue(jFormattedTextFieldNotional1);
        if (notional1.doubleValue() != 0) {
            jFormattedTextFieldFXRate.setText(decimalFormat.format(notional2.divide(notional1, 20, RoundingMode.UP).negate()));
        }
    }//GEN-LAST:event_jFormattedTextFieldNotional2FocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXButton addContractEventButton;
    private javax.swing.JCheckBox compoundedCheckBox1;
    private javax.swing.JCheckBox compoundedCheckBox2;
    private javax.swing.JComboBox compoundedFrequency1;
    private javax.swing.JComboBox compoundedFrequency2;
    private javax.swing.JTextField counterpartyTextField;
    private com.toedter.calendar.JDateChooser firstCouponDateChooser1;
    private com.toedter.calendar.JDateChooser firstCouponDateChooser2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButtonAddEvent1;
    private javax.swing.JButton jButtonAddEvent2;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonFind1;
    private javax.swing.JButton jButtonFind2;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonPrice;
    private javax.swing.JButton jButtonRemoveEvent1;
    private javax.swing.JButton jButtonRemoveEvent2;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSaveAsNew;
    private javax.swing.JButton jButtonScheduler;
    private javax.swing.JCheckBox jCheckBoxPayBusDays1;
    private javax.swing.JCheckBox jCheckBoxPayBusDays2;
    private javax.swing.JCheckBox jCheckBoxPayInArrears1;
    private javax.swing.JCheckBox jCheckBoxPayInArrears2;
    private javax.swing.JCheckBox jCheckBoxResetBusDays1;
    private javax.swing.JCheckBox jCheckBoxResetBusDays2;
    private javax.swing.JCheckBox jCheckBoxResetInArrears1;
    private javax.swing.JCheckBox jCheckBoxResetInArrears2;
    private javax.swing.JComboBox jComboBoxAdjustment1;
    private javax.swing.JComboBox jComboBoxAdjustment2;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxCurrency1;
    private javax.swing.JComboBox jComboBoxCurrency2;
    private javax.swing.JComboBox jComboBoxDayCount1;
    private javax.swing.JComboBox jComboBoxDayCount2;
    private javax.swing.JComboBox jComboBoxFrequency1;
    private javax.swing.JComboBox jComboBoxFrequency2;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JComboBox jComboBoxProductType;
    private javax.swing.JComboBox jComboBoxType1;
    private javax.swing.JComboBox jComboBoxType2;
    private javax.swing.JComboBox jComboEventList1;
    private javax.swing.JComboBox jComboEventList6;
    private javax.swing.JComboBox jComboIndex1;
    private javax.swing.JComboBox jComboIndex2;
    private javax.swing.JComboBox jComboIssuer1;
    private javax.swing.JComboBox jComboIssuer2;
    private javax.swing.JComboBox jComboPayReceive1;
    private javax.swing.JComboBox jComboPayReceive2;
    private javax.swing.JComboBox jComboSeniority1;
    private javax.swing.JComboBox jComboSeniority2;
    private javax.swing.JTextArea jComment;
    private javax.swing.JFormattedTextField jFormattedTextFieldFXRate;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldMaturity;
    private javax.swing.JFormattedTextField jFormattedTextFieldNotional1;
    private javax.swing.JFormattedTextField jFormattedTextFieldNotional2;
    private javax.swing.JFormattedTextField jFormattedTextFieldPaymentLag1;
    private javax.swing.JFormattedTextField jFormattedTextFieldPaymentLag2;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantity;
    private javax.swing.JFormattedTextField jFormattedTextFieldResetLag1;
    private javax.swing.JFormattedTextField jFormattedTextFieldResetLag2;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelAdjustment1;
    private javax.swing.JLabel jLabelAdjustment2;
    private javax.swing.JLabel jLabelBook;
    private javax.swing.JLabel jLabelComment;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelDayCount1;
    private javax.swing.JLabel jLabelDayCount2;
    private javax.swing.JLabel jLabelEvents1;
    private javax.swing.JLabel jLabelEvents2;
    private javax.swing.JLabel jLabelFrequency1;
    private javax.swing.JLabel jLabelFrequency2;
    private javax.swing.JLabel jLabelInderlying1;
    private javax.swing.JLabel jLabelInderlying2;
    private javax.swing.JLabel jLabelIndex1;
    private javax.swing.JLabel jLabelIndex2;
    private javax.swing.JLabel jLabelIssuer1;
    private javax.swing.JLabel jLabelIssuer2;
    private javax.swing.JLabel jLabelMaturityDate;
    private javax.swing.JLabel jLabelNotional;
    private javax.swing.JLabel jLabelNotional1;
    private javax.swing.JLabel jLabelNotional2;
    private javax.swing.JLabel jLabelPaymentLag1;
    private javax.swing.JLabel jLabelPaymentLag2;
    private javax.swing.JLabel jLabelPct1;
    private javax.swing.JLabel jLabelPct2;
    private javax.swing.JLabel jLabelProductType;
    private javax.swing.JLabel jLabelRate1;
    private javax.swing.JLabel jLabelRate2;
    private javax.swing.JLabel jLabelResetLag1;
    private javax.swing.JLabel jLabelResetLag2;
    private javax.swing.JLabel jLabelSeniority1;
    private javax.swing.JLabel jLabelSeniority2;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelTradeDate;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanelCredit1;
    private javax.swing.JPanel jPanelCredit2;
    private javax.swing.JPanel jPanelLeg1;
    private javax.swing.JPanel jPanelLeg2;
    private javax.swing.JPanel jPanelScheduler1;
    private javax.swing.JPanel jPanelScheduler2;
    private javax.swing.JPanel jPanelUpper;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JToolBar.Separator jSeparator1;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    private javax.swing.JTextField jTextEventList1;
    private javax.swing.JTextField jTextEventList2;
    private javax.swing.JTextField jTextFieldAttachment1;
    private javax.swing.JTextField jTextFieldAttachment2;
    private javax.swing.JTextField jTextFieldExhaustion1;
    private javax.swing.JTextField jTextFieldExhaustion2;
    private javax.swing.JTextField jTextFieldInternalTradeId;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldRate1;
    private javax.swing.JTextField jTextFieldRate2;
    private javax.swing.JTextField jTextFieldRecovery1;
    private javax.swing.JTextField jTextFieldRecovery2;
    private javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JTextField jTextFieldUnderlying1;
    private javax.swing.JTextField jTextFieldUnderlying2;
    private javax.swing.JButton legalEntityFinderButton;
    private org.jdesktop.swingx.JXLabel lifeCycleStatusLabel;
    private org.jdesktop.swingx.JXTextField lifeCycleStatusTextField;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private com.toedter.calendar.JDateChooser penultimateCouponDateChooser1;
    private com.toedter.calendar.JDateChooser penultimateCouponDateChooser2;
    private javax.swing.JComboBox statusComboBox;
    private javax.swing.JButton tradeDetailsButton;
    private javax.swing.JToolBar windowToolBar;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void componentActivated() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
