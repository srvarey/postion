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

/**
 * @author Jawhar Kamoun
 */
import com.sun.glass.events.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaProductTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays swap curve underlying
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//CurveUnderlying//EN", autostore = false)
@TopComponent.Description(preferredID = "CurveUnderlyingTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.SwapCurveUnderlyingTopComponent")
@ActionReference(path = "Menu"+MenuManager.SwapCurveUnderlyingTopComponentMenu, position = MenuManager.SwapCurveUnderlyingTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CurveUnderlyingAction", preferredID = "CurveUnderlyingTopComponent")
@Messages({
    "CTL_CurveUnderlyingAction=Swap Curve Underlying",
    "CTL_CurveUnderlyingTopComponent=Swap Curve Underlying Window",
    "HINT_CurveUnderlyingTopComponent=This is a Swap Curve Underlying window"
})
public final class SwapCurveUnderlyingTopComponent extends GaiaProductTopComponent {

    private static final Logger logger = Logger.getLogger(SwapCurveUnderlyingTopComponent.class);
    Product leg1, leg2 = null;
    Scheduler leg1Scheduler, leg2Scheduler = null;
    private static final ArrayList<ProductTypeUtil.ProductType> curveType = new ArrayList<>();

    public SwapCurveUnderlyingTopComponent() {
        initComponents();
        setName(Bundle.CTL_CurveUnderlyingTopComponent());
        setToolTipText(Bundle.HINT_CurveUnderlyingTopComponent());
        curveType.add(ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING);
        curveType.add(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING);
    }

    private void loadCurve() {
        curveTypeComboBox.setSelectedItem(getProduct().getProductType());
        if (getProduct().getProductCurve() != null) {
            tenorComboBox.setSelectedItem(getProduct().getProductCurve().getTenor());
            startLagFormattedTextField.setText(String.valueOf(getProduct().getProductCurve().getStartLag()));
        }
        if (getProduct().getSubProducts() != null && !getProduct().getSubProducts().isEmpty() && getProduct().getSubProducts().size() > 1) {
            List<Product> subProduct = new ArrayList<>(getProduct().getSubProducts());
            leg1 = subProduct.get(0);
            loadLeg1();
            leg2 = subProduct.get(1);
            loadLeg2();
        }
    }

    private void loadCurveById(Integer curveId) {
        if (curveId != null) {
            Product product_ = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, curveId);
            if (product_ != null) {
                setProduct(product_);
                List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class,
                        ProductAccessor.GET_PRODUCT_REFERENCES, curveId);
                getProduct().setProductReferences(references);
                curveIdTextField.setText(curveId.toString());
                loadCurve();
            }
        }
    }

    private void saveCurve() {
        try {
            if (getProduct() != null) {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, getProduct()));
                if (getProduct().getId() != null) {
                    curveIdTextField.setText(getProduct().getId().toString());
                    JOptionPane.showMessageDialog(this, "Saved with id " + getProduct().getId());
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    private void loadLeg1() {
        leg1Scheduler = leg1.getScheduler();
        currency1ComboBox.setSelectedItem(leg1.getNotionalCurrency());
        jComboBoxType1.setSelectedItem(leg1.getProductType());
        if (leg1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
            if (leg1.loadSingleUnderlying() != null) {
                jComboIndex1.setSelectedItem(leg1.loadSingleUnderlying().getShortName());
            }
        }
        if (leg1Scheduler != null) {
            jComboBoxFrequency1.setSelectedItem(leg1Scheduler.getFrequency());
            jComboBoxDayCount1.setSelectedItem(leg1Scheduler.getDaycount());
            jComboBoxAdjustment1.setSelectedItem(leg1Scheduler.getAdjustment());
            jFormattedTextFieldPaymentLag1.setText(String.valueOf(leg1Scheduler.getPaymentLag()));

            jFormattedTextFieldResetLag1.setText(String.valueOf(leg1Scheduler.getResetLag()));
            jCheckBoxPayInArrears1.setSelected(leg1Scheduler.getIsPayInArrears());
            jCheckBoxPayBusDays1.setSelected(leg1Scheduler.getIsPayLagBusDays());
            jCheckBoxResetInArrears1.setSelected(leg1Scheduler.getIsResetInArrears());
            jCheckBoxResetBusDays1.setSelected(leg1Scheduler.getIsResetLagBusDays());

            compoundedCheckBox1.setSelected(leg1Scheduler.getIsCompound());
            compoundedFrequency1.setSelectedItem(leg1Scheduler.getCompoundFrequency());
        }

    }

    private void loadLeg2() {
        leg2Scheduler = leg2.getScheduler();
        currency2ComboBox.setSelectedItem(leg2.getNotionalCurrency());
        jComboBoxType2.setSelectedItem(leg2.getProductType());
        if (leg2.loadSingleUnderlying() != null) {
            jComboIndex2.setSelectedItem(leg2.loadSingleUnderlying().getShortName());
        }
        if (leg2Scheduler != null) {
            jComboBoxFrequency2.setSelectedItem(leg2Scheduler.getFrequency());
            jComboBoxDayCount2.setSelectedItem(leg2Scheduler.getDaycount());
            jComboBoxAdjustment2.setSelectedItem(leg2Scheduler.getAdjustment());
            jFormattedTextFieldPaymentLag2.setText(String.valueOf(leg2Scheduler.getPaymentLag()));

            jFormattedTextFieldResetLag1.setText(String.valueOf(leg2Scheduler.getResetLag()));
            jCheckBoxPayInArrears2.setSelected(leg2Scheduler.getIsPayInArrears());
            jCheckBoxPayBusDays2.setSelected(leg2Scheduler.getIsPayLagBusDays());
            jCheckBoxResetInArrears2.setSelected(leg2Scheduler.getIsResetInArrears());
            jCheckBoxResetBusDays2.setSelected(leg1Scheduler.getIsResetLagBusDays());

            compoundedCheckBox2.setSelected(leg2Scheduler.getIsCompound());
            compoundedFrequency2.setSelectedItem(leg2Scheduler.getCompoundFrequency());
        }

    }

    private void fillCurve() {
        leg1 = null;
        leg2 = null;
        if (getProduct() == null) {
            setProduct(new Product());
            getProduct().setProductReferences(new ArrayList());
        }
        getProduct().setNotionalCurrency(GUIUtils.getComponentStringValue(currency1ComboBox));
        getProduct().setQuotationType(MarketQuote.QuotationType.RATE.getName());
        getProduct().setNotionalMultiplier(BigDecimal.ONE);
        getProduct().setProductType(GUIUtils.getComponentStringValue(curveTypeComboBox));
        getProduct().setIsAsset(false);
        getProduct().setQuantityType(Trade.QuantityType.NOTIONAL.name);
        getProduct().setStatus(ProductConst.ProductStatus.Active.name());
        getProduct().setShortName(getCurveShortName());

        buildLeg1();
        buildLeg2();

        if (leg1 != null && leg2 != null) {
            if (getProduct().getSubProducts() == null || getProduct().getSubProducts().size() < 2) {
                ArrayList<Product> subProducts = new ArrayList();
                subProducts.add(leg1);
                subProducts.add(leg2);
                getProduct().setSubProducts(subProducts);
            }

            ProductCurve productCurve = getProduct().getProductCurve();
            if (productCurve == null) {
                productCurve = new ProductCurve();
            }
            productCurve.setTenor(GUIUtils.getComponentStringValue(tenorComboBox));
            productCurve.setStartLag(GUIUtils.getComponentIntegerValue(startLagFormattedTextField));
            productCurve.setProduct(getProduct());
            getProduct().setProductCurve(productCurve);
        } else {
            JOptionPane.showMessageDialog(this, "The product is not complete", "Error", JOptionPane.ERROR_MESSAGE);
            setProduct(null);
        }
    }

    private void buildLeg1() {
        if (jComboIndex1.getSelectedItem() != null) {
            if (leg1 == null) {
                leg1 = new Product();
                leg1.setProductReferences(new ArrayList());
            }
            leg1.setNotionalMultiplier(BigDecimal.ONE);
            leg1.setNotionalCurrency(GUIUtils.getComponentStringValue(currency1ComboBox));
            leg1.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            leg1.setProductType(GUIUtils.getComponentStringValue(jComboBoxType1));
            leg1.setStatus(ProductConst.ProductStatus.Active.name());
            leg1.setIsAsset(false);
            if (jComboIndex1.getSelectedItem() != null && leg1.getProductType().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                        ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboIndex1.getSelectedItem().toString());
                if (underlying != null && !underlying.equals(leg1.loadSingleUnderlying())) {
                    List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class,
                            ProductAccessor.GET_PRODUCT_REFERENCES, underlying.getId());
                    underlying.setProductReferences(references);
                    leg1.addUnderlying(underlying);
                }
            }
            if (leg1Scheduler == null) {
                leg1Scheduler = new Scheduler();
            }
            leg1Scheduler.setFrequency(GUIUtils.getComponentStringValue(jComboBoxFrequency1));
            leg1Scheduler.setDaycount(GUIUtils.getComponentStringValue(jComboBoxDayCount1));
            leg1Scheduler.setAdjustment(GUIUtils.getComponentStringValue(jComboBoxAdjustment1));
            leg1Scheduler.setPaymentLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldPaymentLag1));
            leg1Scheduler.setResetLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldResetLag1));
            leg1Scheduler.setIsPayInArrears(jCheckBoxPayInArrears1.isSelected());
            leg1Scheduler.setIsPayLagBusDays(jCheckBoxPayBusDays1.isSelected());
            leg1Scheduler.setIsResetInArrears(jCheckBoxResetInArrears1.isSelected());
            leg1Scheduler.setIsResetLagBusDays(jCheckBoxResetBusDays1.isSelected());
            leg1Scheduler.setIsCompound(compoundedCheckBox1.isSelected());
            leg1Scheduler.setCompoundFrequency(GUIUtils.getComponentStringValue(compoundedFrequency1));
            leg1.setScheduler(leg1Scheduler);
        }
    }

    private void buildLeg2() {
        if (jComboIndex2.getSelectedItem() != null && !jComboIndex2.getSelectedItem().toString().isEmpty()) {
            if (leg2 == null) {
                leg2 = new Product();
                leg2.setProductReferences(new ArrayList());
            }
            leg2.setNotionalMultiplier(BigDecimal.ONE);
            leg2.setNotionalCurrency(GUIUtils.getComponentStringValue(currency2ComboBox));
            leg2.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            leg2.setProductType(GUIUtils.getComponentStringValue(jComboBoxType2));
            leg2.setStatus(ProductConst.ProductStatus.Active.name());
            leg2.setIsAsset(false);

            Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                    ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, jComboIndex2.getSelectedItem().toString());
            if (underlying != null && !underlying.equals(leg2.loadSingleUnderlying())) {
                List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class,
                        ProductAccessor.GET_PRODUCT_REFERENCES, underlying.getId());
                underlying.setProductReferences(references);
                leg2.addUnderlying(underlying);
            }

            if (leg2Scheduler == null) {
                leg2Scheduler = new Scheduler();
            }
            leg2Scheduler.setFrequency(GUIUtils.getComponentStringValue(jComboBoxFrequency2));
            leg2Scheduler.setDaycount(GUIUtils.getComponentStringValue(jComboBoxDayCount2));
            leg2Scheduler.setAdjustment(GUIUtils.getComponentStringValue(jComboBoxAdjustment2));
            leg2Scheduler.setPaymentLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldPaymentLag2));
            leg2Scheduler.setResetLag(GUIUtils.getComponentIntegerValue(jFormattedTextFieldResetLag2));
            leg2Scheduler.setIsPayInArrears(jCheckBoxPayInArrears2.isSelected());
            leg2Scheduler.setIsPayLagBusDays(jCheckBoxPayBusDays2.isSelected());
            leg2Scheduler.setIsResetInArrears(jCheckBoxResetInArrears2.isSelected());
            leg2Scheduler.setIsResetLagBusDays(jCheckBoxResetBusDays2.isSelected());
            leg2Scheduler.setIsCompound(compoundedCheckBox2.isSelected());
            leg2Scheduler.setCompoundFrequency(GUIUtils.getComponentStringValue(compoundedFrequency2));
            leg2.setScheduler(leg2Scheduler);
        }
    }

    private String getCurveShortName() {
        StringBuilder shortName = new StringBuilder(GUIUtils.getComponentStringValue(curveTypeComboBox));
        shortName.append(StringUtils.SPACE);
        if (ProductTypeUtil.ProductType.FLOATING_LEG.getName().equalsIgnoreCase(GUIUtils.getComponentStringValue(jComboBoxType1))) {
            shortName.append(GUIUtils.getComponentStringValue(jComboIndex1)).append(StringUtils.SPACE);
        }
        shortName.append(GUIUtils.getComponentStringValue(jComboIndex2)).append(StringUtils.SPACE);
        shortName.append(GUIUtils.getComponentStringValue(tenorComboBox));
        return shortName.toString();
    }

    @Override
    public void initContext() {
        jComboBoxType1.setSelectedItem("Floating Leg");
        jComboBoxType2.setSelectedItem("Fixed Leg");
        jFormattedTextFieldResetLag1.setText("2");
        jFormattedTextFieldResetLag2.setText("2");
        jFormattedTextFieldPaymentLag1.setText("2");
        jFormattedTextFieldPaymentLag2.setText("2");
        jCheckBoxPayBusDays1.setSelected(true);
        jCheckBoxResetBusDays1.setSelected(true);
        jCheckBoxPayBusDays2.setSelected(true);
        jCheckBoxResetBusDays2.setSelected(true);
        jCheckBoxPayInArrears1.setSelected(true);
        jCheckBoxPayInArrears2.setSelected(true);

        List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        GUIUtils.fillCombo(currency1ComboBox, currencies);
        GUIUtils.fillCombo(currency2ComboBox, currencies);
        String cur=(String)DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY);
        currency1ComboBox.setSelectedItem(cur);
        currency2ComboBox.setSelectedItem(cur);

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
        jComboBoxType1.addItem(ProductTypeUtil.ProductType.FIXED_LEG.getName());
        jComboBoxType1.addItem(ProductTypeUtil.ProductType.FLOATING_LEG.getName());

        jComboBoxType2.addItem(ProductTypeUtil.ProductType.FLOATING_LEG.getName());
        curveIdTextField.setText(StringUtils.EMPTY_STRING);

        List<String> tenorList = FrequencyUtil.getTenors();
        GUIUtils.fillCombo(tenorComboBox, tenorList);
        if (curveTypeComboBox.getItemCount() == 0) {
            curveTypeComboBox.addItem(ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING.getName());
            curveTypeComboBox.addItem(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING.getName());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        leg2Panel = new org.jdesktop.swingx.JXPanel();
        currency2ComboBox = new javax.swing.JComboBox();
        jComboBoxType2 = new javax.swing.JComboBox();
        jLabelIndex2 = new javax.swing.JLabel();
        jComboIndex2 = new javax.swing.JComboBox();
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
        compoundedFrequency2 = new javax.swing.JComboBox();
        compoundedCheckBox2 = new javax.swing.JCheckBox();
        leg1Panel = new org.jdesktop.swingx.JXPanel();
        currency1ComboBox = new javax.swing.JComboBox();
        jComboBoxType1 = new javax.swing.JComboBox();
        jComboIndex1 = new javax.swing.JComboBox();
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
        compoundedCheckBox1 = new javax.swing.JCheckBox();
        compoundedFrequency1 = new javax.swing.JComboBox();
        jLabelIndex1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        windowToolBar = new javax.swing.JToolBar();
        loadButton = new org.jdesktop.swingx.JXButton();
        curveIdTextField = new javax.swing.JFormattedTextField();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        newButton = new org.jdesktop.swingx.JXButton();
        saveAsNewButton = new org.jdesktop.swingx.JXButton();
        saveButton = new org.jdesktop.swingx.JXButton();
        curvePropertiesPanel = new org.jdesktop.swingx.JXPanel();
        tenorComboBox = new javax.swing.JComboBox();
        indexTenorLabel = new org.jdesktop.swingx.JXLabel();
        jXLabel1 = new org.jdesktop.swingx.JXLabel();
        curveTypeComboBox = new javax.swing.JComboBox();
        startLagLabel = new org.jdesktop.swingx.JXLabel();
        startLagFormattedTextField = new javax.swing.JFormattedTextField();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        leg2Panel.setBackground(new java.awt.Color(255, 255, 255));
        leg2Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        leg2Panel.setPreferredSize(new java.awt.Dimension(460, 265));

        currency2ComboBox.setBackground(new java.awt.Color(255, 255, 255));
        currency2ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        currency2ComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                currency2ComboBoxItemStateChanged(evt);
            }
        });

        jComboBoxType2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxType2.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIndex2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelIndex2.text")); // NOI18N

        jComboIndex2.setBackground(new java.awt.Color(255, 255, 255));
        jComboIndex2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jPanelScheduler2.setBackground(new java.awt.Color(230, 230, 253));
        jPanelScheduler2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelScheduler2.setPreferredSize(new java.awt.Dimension(480, 191));
        jPanelScheduler2.setRequestFocusEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFrequency2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelFrequency2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelDayCount2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelDayCount2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAdjustment2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelAdjustment2.text")); // NOI18N

        jComboBoxFrequency2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFrequency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxDayCount2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jComboBoxAdjustment2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPaymentLag2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelPaymentLag2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelResetLag2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelResetLag2.text")); // NOI18N

        jFormattedTextFieldPaymentLag2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextFieldResetLag2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jCheckBoxPayBusDays2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxPayBusDays2.text")); // NOI18N

        jCheckBoxResetBusDays2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusDays2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxResetBusDays2.text")); // NOI18N

        jCheckBoxPayInArrears2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxPayInArrears2.text")); // NOI18N

        jCheckBoxResetInArrears2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxResetInArrears2.text")); // NOI18N

        compoundedFrequency2.setBackground(new java.awt.Color(255, 255, 255));
        compoundedFrequency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        compoundedFrequency2.setEnabled(false);

        compoundedCheckBox2.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(compoundedCheckBox2, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.compoundedCheckBox2.text")); // NOI18N
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
                        .addGap(10, 10, 10)
                        .addComponent(compoundedCheckBox2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(compoundedFrequency2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(81, Short.MAX_VALUE))
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
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxDayCount2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelDayCount2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAdjustment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdjustment2))
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
                .addContainerGap(11, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leg2PanelLayout = new javax.swing.GroupLayout(leg2Panel);
        leg2Panel.setLayout(leg2PanelLayout);
        leg2PanelLayout.setHorizontalGroup(
            leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leg2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelScheduler2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leg2PanelLayout.createSequentialGroup()
                        .addGroup(leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIndex2)
                            .addComponent(currency2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboIndex2, 0, 182, Short.MAX_VALUE)
                            .addComponent(jComboBoxType2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        leg2PanelLayout.setVerticalGroup(
            leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leg2PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(currency2ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(leg2PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboIndex2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIndex2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelScheduler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        leg1Panel.setBackground(new java.awt.Color(255, 255, 255));
        leg1Panel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        leg1Panel.setPreferredSize(new java.awt.Dimension(460, 265));

        currency1ComboBox.setBackground(new java.awt.Color(255, 255, 255));
        currency1ComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        currency1ComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                currency1ComboBoxItemStateChanged(evt);
            }
        });

        jComboBoxType1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxType1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBoxType1ItemStateChanged(evt);
            }
        });

        jComboIndex1.setBackground(new java.awt.Color(255, 255, 255));
        jComboIndex1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jPanelScheduler1.setBackground(new java.awt.Color(230, 230, 253));
        jPanelScheduler1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanelScheduler1.setPreferredSize(new java.awt.Dimension(480, 191));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelFrequency1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelFrequency1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelDayCount1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelDayCount1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAdjustment1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelAdjustment1.text")); // NOI18N

        jComboBoxFrequency1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFrequency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBoxDayCount1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxDayCount1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jComboBoxAdjustment1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAdjustment1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPaymentLag1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelPaymentLag1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelResetLag1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelResetLag1.text")); // NOI18N

        jFormattedTextFieldPaymentLag1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jFormattedTextFieldResetLag1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jCheckBoxPayBusDays1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayBusDays1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxPayBusDays1.text")); // NOI18N

        jCheckBoxResetBusDays1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetBusDays1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxResetBusDays1.text")); // NOI18N

        jCheckBoxPayInArrears1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxPayInArrears1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxPayInArrears1.text")); // NOI18N

        jCheckBoxResetInArrears1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxResetInArrears1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jCheckBoxResetInArrears1.text")); // NOI18N

        compoundedCheckBox1.setBackground(new java.awt.Color(230, 230, 253));
        org.openide.awt.Mnemonics.setLocalizedText(compoundedCheckBox1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.compoundedCheckBox1.text")); // NOI18N
        compoundedCheckBox1.setEnabled(false);
        compoundedCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                compoundedCheckBox1ActionPerformed(evt);
            }
        });

        compoundedFrequency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        compoundedFrequency1.setEnabled(false);

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
                        .addComponent(compoundedCheckBox1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(compoundedFrequency1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanelScheduler1Layout.setVerticalGroup(
            jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                .addContainerGap()
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
                    .addComponent(jLabelDayCount1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxAdjustment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelAdjustment1))
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
                .addContainerGap(11, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jLabelIndex1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jLabelIndex1.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 70, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout leg1PanelLayout = new javax.swing.GroupLayout(leg1Panel);
        leg1Panel.setLayout(leg1PanelLayout);
        leg1PanelLayout.setHorizontalGroup(
            leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leg1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(leg1PanelLayout.createSequentialGroup()
                        .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(currency1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIndex1))
                        .addGap(18, 18, 18)
                        .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboIndex1, 0, 195, Short.MAX_VALUE)
                            .addComponent(jComboBoxType1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        leg1PanelLayout.setVerticalGroup(
            leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leg1PanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leg1PanelLayout.createSequentialGroup()
                        .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(currency1ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(leg1PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboIndex1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIndex1)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        windowToolBar.setFloatable(false);
        windowToolBar.setRollover(true);
        windowToolBar.setAutoscrolls(true);

        loadButton.setBorder(null);
        org.openide.awt.Mnemonics.setLocalizedText(loadButton, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.loadButton.text")); // NOI18N
        loadButton.setFocusable(false);
        loadButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loadButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        loadButton.setMaximumSize(new java.awt.Dimension(80, 30));
        loadButton.setPreferredSize(new java.awt.Dimension(60, 30));
        loadButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(loadButton);

        curveIdTextField.setText(org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.curveIdTextField.text")); // NOI18N
        curveIdTextField.setMaximumSize(new java.awt.Dimension(90, 30));
        curveIdTextField.setMinimumSize(new java.awt.Dimension(90, 30));
        curveIdTextField.setPreferredSize(new java.awt.Dimension(50, 28));
        curveIdTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                curveIdTextFieldKeyPressed(evt);
            }
        });
        windowToolBar.add(curveIdTextField);

        jSeparator1.setPreferredSize(new java.awt.Dimension(6, 10));
        windowToolBar.add(jSeparator1);

        newButton.setBorder(null);
        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.newButton.text")); // NOI18N
        newButton.setFocusable(false);
        newButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        newButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newButton.setMaximumSize(new java.awt.Dimension(80, 30));
        newButton.setPreferredSize(new java.awt.Dimension(60, 30));
        newButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(newButton);

        saveAsNewButton.setBorder(null);
        org.openide.awt.Mnemonics.setLocalizedText(saveAsNewButton, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.saveAsNewButton.text")); // NOI18N
        saveAsNewButton.setFocusable(false);
        saveAsNewButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveAsNewButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveAsNewButton.setMaximumSize(new java.awt.Dimension(80, 30));
        saveAsNewButton.setPreferredSize(new java.awt.Dimension(90, 23));
        saveAsNewButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveAsNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsNewButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(saveAsNewButton);

        saveButton.setBorder(null);
        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.saveButton.text")); // NOI18N
        saveButton.setFocusable(false);
        saveButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setMaximumSize(new java.awt.Dimension(80, 30));
        saveButton.setPreferredSize(new java.awt.Dimension(80, 30));
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        windowToolBar.add(saveButton);

        curvePropertiesPanel.setBackground(new java.awt.Color(254, 252, 254));

        tenorComboBox.setBackground(new java.awt.Color(255, 255, 255));

        org.openide.awt.Mnemonics.setLocalizedText(indexTenorLabel, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.indexTenorLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jXLabel1, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.jXLabel1.text")); // NOI18N

        curveTypeComboBox.setBackground(new java.awt.Color(255, 255, 255));
        curveTypeComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                curveTypeComboBoxItemStateChanged(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(startLagLabel, org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.startLagLabel.text")); // NOI18N

        startLagFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        startLagFormattedTextField.setText(org.openide.util.NbBundle.getMessage(SwapCurveUnderlyingTopComponent.class, "SwapCurveUnderlyingTopComponent.startLagFormattedTextField.text")); // NOI18N

        javax.swing.GroupLayout curvePropertiesPanelLayout = new javax.swing.GroupLayout(curvePropertiesPanel);
        curvePropertiesPanel.setLayout(curvePropertiesPanelLayout);
        curvePropertiesPanelLayout.setHorizontalGroup(
            curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvePropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(indexTenorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tenorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(curveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(startLagLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(startLagFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        curvePropertiesPanelLayout.setVerticalGroup(
            curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(curvePropertiesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(curvePropertiesPanelLayout.createSequentialGroup()
                        .addGroup(curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(indexTenorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tenorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(startLagLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(startLagFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, curvePropertiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jXLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(curveTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(leg1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 523, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(leg2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 494, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 6, Short.MAX_VALUE))
                    .addComponent(curvePropertiesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(windowToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(curvePropertiesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(leg1Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(leg2Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(windowToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void compoundedCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedCheckBox1ActionPerformed
        if (compoundedCheckBox1.isSelected()) {
            compoundedFrequency1.setEnabled(true);
        } else {
            compoundedFrequency1.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBox1ActionPerformed

    private void compoundedCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_compoundedCheckBox2ActionPerformed
        if (compoundedCheckBox2.isSelected()) {
            compoundedFrequency2.setEnabled(true);
        } else {
            compoundedFrequency2.setEnabled(false);
        }
    }//GEN-LAST:event_compoundedCheckBox2ActionPerformed

    public void fillDefaultDayCounts(int leg) {
        // leg 1 for leg 1
        // leg 2 for leg 2
        // leg 0 for bothlegs
        if (leg <= 1 && jComboBoxType1.getSelectedItem() != null
                && (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name))) {
            Currency cur = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE,
                    currency1ComboBox.getSelectedItem().toString());
            if (cur != null) {
                jComboBoxDayCount1.setSelectedItem(cur.getDaycount());
            }
        } else if (leg != 1 && jComboBoxType2.getSelectedItem() != null
                && (jComboBoxType2.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FIXED_LEG.name))) {
            Currency cur = (Currency) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.GET_CURRENCY_BY_CODE,
                    currency2ComboBox.getSelectedItem().toString());
            if (cur != null) {
                jComboBoxDayCount2.setSelectedItem(cur.getDaycount());
            }
        }

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
    private void currency1ComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currency1ComboBoxItemStateChanged
        if (currency1ComboBox.getSelectedItem() != null) {
            fillIndexCombo(GUIUtils.getComponentStringValue(currency1ComboBox), jComboIndex1);
            fillDefaultDayCounts(1);
        }
    }//GEN-LAST:event_currency1ComboBoxItemStateChanged

    private void currency2ComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_currency2ComboBoxItemStateChanged
        if (currency2ComboBox.getSelectedItem() != null) {
            fillIndexCombo(GUIUtils.getComponentStringValue(currency2ComboBox), jComboIndex2);
            fillDefaultDayCounts(2);
        }
    }//GEN-LAST:event_currency2ComboBoxItemStateChanged

    private void jComboBoxType1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBoxType1ItemStateChanged
        if (jComboBoxType1.getSelectedItem() != null) {
            jLabelIndex1.setVisible(false);
            jComboIndex1.setVisible(false);
            jPanelScheduler1.setVisible(false);
            compoundedCheckBox1.setEnabled(false);
            if (jComboBoxType1.getSelectedItem().toString().equals(ProductTypeUtil.ProductType.FLOATING_LEG.name)) {
                jComboIndex1.setVisible(true);
                jLabelIndex1.setVisible(true);
                jPanelScheduler1.setVisible(true);
                compoundedCheckBox1.setEnabled(true);
                if (curveTypeComboBox.getSelectedItem() != null && !curveTypeComboBox.getSelectedItem().toString().
                        equalsIgnoreCase(ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING.getName())) {
                    curveTypeComboBox.setSelectedItem(ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING.getName());
                }

            } else {
                jPanelScheduler1.setVisible(true);
                fillDefaultDayCounts(1);
                if (curveTypeComboBox.getSelectedItem() != null && !curveTypeComboBox.getSelectedItem().toString().
                        equalsIgnoreCase(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING.getName())) {
                    curveTypeComboBox.setSelectedItem(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING.getName());
                }
            }
        }
    }//GEN-LAST:event_jComboBoxType1ItemStateChanged

    private void curveTypeComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_curveTypeComboBoxItemStateChanged
        if (curveTypeComboBox.getSelectedItem().toString().
                equalsIgnoreCase(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING.getName())) {
            jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.FLOATING_LEG.name);
        } else {
            jComboBoxType1.setSelectedItem(ProductTypeUtil.ProductType.FIXED_LEG.name);
        }
    }//GEN-LAST:event_curveTypeComboBoxItemStateChanged

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        load();
    }//GEN-LAST:event_loadButtonActionPerformed

    public void load(){
        /**
         * find and load
         */
        if (NumberUtils.isInteger(curveIdTextField.getText())) {
            Integer id = Integer.parseInt(curveIdTextField.getText());
            loadCurveById(id);
        } else {

            AssetFinder assetFinder = new AssetFinder(curveType);

            NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Curve Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE,
                    null, NotifyDescriptor.OK_OPTION);

            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
                Integer curveId = assetFinder.getAssetId();
                loadCurveById(curveId);
                assetFinder.setVisible(false);
            }
        }
    }

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        fillCurve();
        saveCurve();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        /**
         * Clear screen
         */
        clearFields(this);
    }//GEN-LAST:event_newButtonActionPerformed

    private void saveAsNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsNewButtonActionPerformed
        curveIdTextField.setText(StringUtils.EMPTY_STRING);
        setProduct(null);
        fillCurve();
        saveCurve();
    }//GEN-LAST:event_saveAsNewButtonActionPerformed

    private void curveIdTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_curveIdTextFieldKeyPressed
       if (evt.getKeyCode()==KeyEvent.VK_ENTER){
           load();
       }
    }//GEN-LAST:event_curveIdTextFieldKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox compoundedCheckBox1;
    private javax.swing.JCheckBox compoundedCheckBox2;
    private javax.swing.JComboBox compoundedFrequency1;
    private javax.swing.JComboBox compoundedFrequency2;
    private javax.swing.JComboBox currency1ComboBox;
    private javax.swing.JComboBox currency2ComboBox;
    private javax.swing.JFormattedTextField curveIdTextField;
    private org.jdesktop.swingx.JXPanel curvePropertiesPanel;
    private javax.swing.JComboBox curveTypeComboBox;
    private org.jdesktop.swingx.JXLabel indexTenorLabel;
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
    private javax.swing.JComboBox jComboBoxDayCount1;
    private javax.swing.JComboBox jComboBoxDayCount2;
    private javax.swing.JComboBox jComboBoxFrequency1;
    private javax.swing.JComboBox jComboBoxFrequency2;
    private javax.swing.JComboBox jComboBoxType1;
    private javax.swing.JComboBox jComboBoxType2;
    private javax.swing.JComboBox jComboIndex1;
    private javax.swing.JComboBox jComboIndex2;
    private javax.swing.JFormattedTextField jFormattedTextFieldPaymentLag1;
    private javax.swing.JFormattedTextField jFormattedTextFieldPaymentLag2;
    private javax.swing.JFormattedTextField jFormattedTextFieldResetLag1;
    private javax.swing.JFormattedTextField jFormattedTextFieldResetLag2;
    private javax.swing.JLabel jLabelAdjustment1;
    private javax.swing.JLabel jLabelAdjustment2;
    private javax.swing.JLabel jLabelDayCount1;
    private javax.swing.JLabel jLabelDayCount2;
    private javax.swing.JLabel jLabelFrequency1;
    private javax.swing.JLabel jLabelFrequency2;
    private javax.swing.JLabel jLabelIndex1;
    private javax.swing.JLabel jLabelIndex2;
    private javax.swing.JLabel jLabelPaymentLag1;
    private javax.swing.JLabel jLabelPaymentLag2;
    private javax.swing.JLabel jLabelResetLag1;
    private javax.swing.JLabel jLabelResetLag2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelScheduler1;
    private javax.swing.JPanel jPanelScheduler2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private org.jdesktop.swingx.JXLabel jXLabel1;
    private org.jdesktop.swingx.JXPanel leg1Panel;
    private org.jdesktop.swingx.JXPanel leg2Panel;
    private org.jdesktop.swingx.JXButton loadButton;
    private org.jdesktop.swingx.JXButton newButton;
    private org.jdesktop.swingx.JXButton saveAsNewButton;
    private org.jdesktop.swingx.JXButton saveButton;
    private javax.swing.JFormattedTextField startLagFormattedTextField;
    private org.jdesktop.swingx.JXLabel startLagLabel;
    private javax.swing.JComboBox tenorComboBox;
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
