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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductSingleTraded;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.assets.BondsTopComponent;
import org.gaia.gui.assets.EquityTopComponent;
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
import org.openide.windows.WindowManager;

/**
 * Top component which displays custom trades.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//CustomTrade//EN", autostore = false)
@TopComponent.Description(preferredID = "CustomTradeTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.CustomTradeTopComponent")
@ActionReference(path = "Menu/File", position = 6)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CustomTradeAction")
@Messages({"CTL_CustomTradeAction=Custom Trade", "CTL_CustomTradeTopComponent=Custom Trade", "HINT_CustomTradeTopComponent=This is a CustomTrade window"})
public final class CustomTradeTopComponent extends GaiaTradeTopComponent {

    Product product1 = null;
    Product product2 = null;
    ProductSingleTraded otc = null;
    ProductSingleTraded otc2 = null;
    Scheduler s1 = null;
    Scheduler s2 = null;
    ProductCredit cred1 = null;
    ProductCredit cred2 = null;
    ArrayList<Method> listMethods = null;
    private AssetFinder assetFinder = null;
    private static final Logger logger = Logger.getLogger(CustomTradeTopComponent.class);

    public CustomTradeTopComponent() {
        initComponents();
        setName(Bundle.CTL_CustomTradeTopComponent());
        setToolTipText(Bundle.HINT_CustomTradeTopComponent());
    }

    @Override
    public void loadByTradeId(Integer id) {
        jTextFieldTradeId.setText(id.toString());
        setIsTradeAudited(false);
        loadTrade();
    }

    @Override
    public void initContext() {
        try {

            jCheckBoxIsSingleTraded.setSelected(true);
            jTextFieldTradeTime.setText(timeFormatter.format(new Date()));

            jPanelScheduler1.setVisible(false);
            jPanelScheduler2.setVisible(false);
            jPanelCredit1.setVisible(false);
            jPanelCredit2.setVisible(false);

            jTabbedPane1.setEnabledAt(1, false);
            jTabbedPane1.setEnabledAt(2, false);
            LoadListAttributes();

            jSpinnerTradeDate.setDate(DateUtils.getDate());
            jFormattedTextFieldSettleDate.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity1.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate1.setDate(DateUtils.getDate());
            jFormattedTextField9.setDate(DateUtils.getDate());
            jFormattedTextField8.setDate(DateUtils.getDate());
            jFormattedTextFieldMaturity2.setDate(DateUtils.getDate());
            jFormattedTextFieldStartDate2.setDate(DateUtils.getDate());
            jFormattedTextField14.setDate(DateUtils.getDate());
            jFormattedTextField15.setDate(DateUtils.getDate());
            jFormattedTextFieldQuantity.addPropertyChangeListener("value", new MyPropertyChangeListener());
            jFormattedTextFieldFXRate.addPropertyChangeListener("value", new MyPropertyChangeListener());

            Object oldValue;
            Object oldValue1;
            Object oldValue2;
            Object oldValue3;
            Object oldValue4;

            List<String> entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            if (entities != null) {
                oldValue = jComboBoxInternalCounterparty.getSelectedItem();
                jComboBoxInternalCounterparty.removeAllItems();
                for (String entity : entities) {
                    jComboBoxInternalCounterparty.addItem(entity);
                }
                jComboBoxInternalCounterparty.setSelectedItem(oldValue);
            }

            List<ProductTypeUtil.ProductType> productTypes = ProductTypeUtil.loadOTC();
            if (productTypes != null) {
                oldValue = jComboBoxProductType.getSelectedItem();
                jComboBoxProductType.removeAllItems();
                for (ProductTypeUtil.ProductType productType : productTypes) {
                    jComboBoxProductType.addItem(productType.name);
                }
                jComboBoxProductType.setSelectedItem(oldValue);
            }
            productTypes = ProductTypeUtil.loadLegs();
            if (productTypes != null) {
                oldValue = jComboBoxProductType1.getSelectedItem();
                oldValue1 = jComboBoxProductType2.getSelectedItem();
                jComboBoxProductType1.removeAllItems();
                jComboBoxProductType2.removeAllItems();
                for (ProductTypeUtil.ProductType productType : productTypes) {
                    jComboBoxProductType1.addItem(productType.name);
                    jComboBoxProductType2.addItem(productType.name);
                }
                jComboBoxProductType1.setSelectedItem(oldValue);
                jComboBoxProductType2.setSelectedItem(oldValue1);
            }

            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            if (currencies != null) {
                oldValue = jComboBoxCurrency.getSelectedItem();
                oldValue1 = jComboBoxCurrency1.getSelectedItem();
                oldValue2 = jComboBoxCurrency2.getSelectedItem();
                oldValue3 = jComboBoxPriceCurrency.getSelectedItem();
                oldValue4 = jComboBoxAmountCurrency.getSelectedItem();

                jComboBoxCurrency.removeAllItems();
                jComboBoxCurrency1.removeAllItems();
                jComboBoxCurrency2.removeAllItems();
                jComboBoxPriceCurrency.removeAllItems();
                jComboBoxAmountCurrency.removeAllItems();
                for (String currency : currencies) {
                    jComboBoxCurrency.addItem(currency);
                    jComboBoxCurrency1.addItem(currency);
                    jComboBoxCurrency2.addItem(currency);
                    jComboBoxPriceCurrency.addItem(currency);
                    jComboBoxAmountCurrency.addItem(currency);
                }
                jComboBoxCurrency.setSelectedItem(oldValue);
                jComboBoxCurrency1.setSelectedItem(oldValue1);
                jComboBoxCurrency2.setSelectedItem(oldValue2);
                jComboBoxPriceCurrency.setSelectedItem(oldValue3);
                jComboBoxAmountCurrency.setSelectedItem(oldValue4);
            }

            List<String> frequencies = FrequencyUtil.getFrequencies();
            if (frequencies != null) {
                oldValue = jComboBox8.getSelectedItem();
                oldValue1 = jComboBox11.getSelectedItem();
                jComboBox8.removeAllItems();
                jComboBox11.removeAllItems();
                for (String frequency : frequencies) {
                    jComboBox8.addItem(frequency);
                    jComboBox11.addItem(frequency);
                }
                jComboBox8.setSelectedItem(oldValue);
                jComboBox11.setSelectedItem(oldValue1);
            }

            List<String> dayCounts = (List) DAOCallerAgent.callMethod(DayCountAccessor.class, DayCountAccessor.GET_DAYCOUNTS);
            if (dayCounts != null) {
                oldValue = jComboBox9.getSelectedItem();
                oldValue1 = jComboBox12.getSelectedItem();
                jComboBox9.removeAllItems();
                jComboBox12.removeAllItems();
                for (String dayCount : dayCounts) {
                    jComboBox9.addItem(dayCount);
                    jComboBox12.addItem(dayCount);
                }
                jComboBox9.setSelectedItem(oldValue);
                jComboBox12.setSelectedItem(oldValue1);
            }

            List<String> adjustments = ProductAccessor.getCouponAdjustments();
            if (adjustments != null) {
                oldValue = jComboBox10.getSelectedItem();
                oldValue1 = jComboBox13.getSelectedItem();
                jComboBox10.removeAllItems();
                jComboBox13.removeAllItems();
                for (String adjustment : adjustments) {
                    jComboBox10.addItem(adjustment);
                    jComboBox13.addItem(adjustment);
                }
                jComboBox10.setSelectedItem(oldValue);
                jComboBox13.setSelectedItem(oldValue1);
            }

            List<String> creditEvents = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_CREDIT_EVENTS);
            if (creditEvents != null) {
                oldValue = jComboEventList1.getSelectedItem();
                oldValue1 = jComboEventList6.getSelectedItem();
                jComboEventList1.removeAllItems();
                jComboEventList6.removeAllItems();
                for (String creditEvent : creditEvents) {
                    jComboEventList1.addItem(creditEvent);
                    jComboEventList6.addItem(creditEvent);
                }
                jComboEventList1.setSelectedItem(oldValue);
                jComboEventList6.setSelectedItem(oldValue1);

            }

            List<String> seniorities = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_BOND_SENIORITIES);
            if (seniorities != null) {
                oldValue = jComboSeniority6.getSelectedItem();
                oldValue1 = jComboSeniority1.getSelectedItem();
                jComboSeniority6.removeAllItems();
                jComboSeniority1.removeAllItems();
                for (String seniority : seniorities) {
                    jComboSeniority6.addItem(seniority);
                    jComboSeniority1.addItem(seniority);
                }
                jComboSeniority6.setSelectedItem(oldValue);
                jComboSeniority1.setSelectedItem(oldValue1);
            }

            List<String> issuers = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ISSUERS);
            if (issuers != null) {
                oldValue = jComboIssuer6.getSelectedItem();
                oldValue1 = jComboSeniority1.getSelectedItem();
                jComboIssuer6.removeAllItems();
                jComboSeniority1.removeAllItems();
                for (String issuer : issuers) {
                    jComboIssuer1.addItem(issuer);
                    jComboSeniority1.addItem(issuer);
                }
                jComboIssuer1.setSelectedItem(oldValue);
                jComboIssuer6.setSelectedItem(oldValue1);

            }
            /**
             * ShortCut for Amout and date
             */
            AmountShortCut.eventkey(jFormattedTextFieldPrice);
            AmountShortCut.eventkey(jFormattedTextFieldAmount);
            AmountShortCut.eventkey(jFormattedTextFieldNotional1);
            AmountShortCut.eventkey(jFormattedTextFieldNotional2);

            DateShortCut.eventkey((JSpinnerDateEditor) jSpinnerTradeDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldSettleDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldStartDate.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldMaturity.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldMaturity1.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldStartDate1.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextField9.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextField8.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldMaturity2.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextFieldStartDate2.getComponent(1));
            DateShortCut.eventkey((JSpinnerDateEditor) jFormattedTextField15.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    private void LoadListAttributes() {
        listMethods = new ArrayList();
        ArrayList<Method> list = IntrospectUtil.getGetters(Product.class.getName());
        listAttributes = new ArrayList();

        for (Method s : list) {
            listMethods.add(s);
        }
        list = IntrospectUtil.getGetters(ProductSingleTraded.class.getName());
        for (Method s : list) {
            listMethods.add(s);
        }
        for (Method m : listMethods) {
            if (m.getReturnType().equals(String.class) || m.getReturnType().equals(Integer.class) || m.getReturnType().equals(Double.class) || m.getReturnType().equals(Boolean.class) || m.getReturnType().equals(Short.class) || m.getReturnType().equals(Date.class) || m.getReturnType().equals(short.class) || m.getReturnType().equals(BigDecimal.class)) {
                listAttributes.add(m.getName().substring(3));
            }
        }
    }

    /**
     * trade
     */
    @Override
    public void fillTrade() throws NumberFormatException {

        if (getTrade() == null) {
            setTrade(new Trade());
        }
        if (!jTextFieldInternalTradeId.getText().isEmpty()) {
            getTrade().setId(Integer.parseInt(jTextFieldInternalTradeId.getText()));
        }
        try {
            if (jSpinnerTradeDate.getDate() != null) {
                getTrade().setTradeDate(jSpinnerTradeDate.getDate());
            }
            if (!jTextFieldTradeTime.getText().isEmpty()) {
                getTrade().setTradeTime(timeFormatter.parse(jTextFieldTradeTime.getText().toString()));
            }
            getTrade().setQuantityType(jComboBoxQuantityNotional.getSelectedItem().toString());

            if (jCheckBoxIsSingleTraded.isSelected()) {
                /**
                 * OTC
                 */
                if (jTabbedPane1.isEnabledAt(1)) {
                    if (product1 == null) {
                        product1 = new Product();
                    }
                    if (!jFormattedTextFieldNotional1.getText().equals("")) {
                        BigDecimal qleg1 = new BigDecimal(Double.parseDouble(jFormattedTextFieldNotional1.getText()));
                        qleg1 = qleg1.divide(new BigDecimal(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                        product1.setNotionalMultiplier(qleg1);
                    }
                    product1.setNotionalCurrency(jComboBoxCurrency1.getSelectedItem().toString());
                    product1.setQuotationType("Rate");
                    if (jFormattedTextFieldMaturity1.getDate() != null) {
                        product1.setMaturityDate(jFormattedTextFieldMaturity1.getDate());
                    }
                    if (jFormattedTextFieldStartDate1.getDate() != null) {
                        product1.setStartDate(jFormattedTextFieldStartDate1.getDate());
                    }
                    if (jComboBoxProductType.getSelectedItem() != null) {
                        product1.setProductType(jComboBoxProductType.getSelectedItem().toString());
                    }
                    if (otc == null) {
                        otc = new ProductSingleTraded();
                    }
                    if (!jPayOffPayment1.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc.setPayoffDateClause(jPayOffPayment1.getText());
                    }
                    if (!jPayOffCondition1.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc.setPayoffConditionClause(jPayOffCondition1.getText());
                    }
                    if (!jPayOffFormula1.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc.setPayoffFormula(jPayOffFormula1.getText());
                    }

                    DefaultTableModel tm = (DefaultTableModel) jTableAttributes1.getModel();
                    for (int i = 0; i < tm.getRowCount(); i++) {
                        String data = tm.getValueAt(i, 1).toString();
                        IntrospectUtil.invokeSetter(ProductSingleTraded.class, otc, tm.getValueAt(i, 0).toString(), data);
                    }

                    product1.setProductSingleTraded(otc);

                    ArrayList<Product> listunderlyings = new ArrayList();
                    tm = (DefaultTableModel) jTableUnderlyings1.getModel();
                    for (int i = 0; i < tm.getRowCount(); i++) {
                        Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.getInteger(tm.getValueAt(i, 1).toString()));
                        listunderlyings.add(underlying);
                    }
                    product1.getSubProducts().addAll(listunderlyings);

                    if (jCheckBoxScheduler1.isSelected()) {

                        if (s1 == null) {
                            s1 = new Scheduler();
                        }

                        if (!jComboBox8.getSelectedItem().equals(StringUtils.EMPTY_STRING)) {
                            s1.setFrequency(jComboBox8.getSelectedItem().toString());
                        }
                        if (!jComboBox9.getSelectedItem().equals(StringUtils.EMPTY_STRING)) {
                            s1.setDaycount(jComboBox9.getSelectedItem().toString());
                        }
                        if (!jComboBox10.getSelectedItem().equals(StringUtils.EMPTY_STRING)) {
                            s1.setAdjustment(jComboBox10.getSelectedItem().toString());
                        }

                        if (!jFormattedTextField10.getText().equals(StringUtils.EMPTY_STRING)) {
                            s1.setPaymentLag(Integer.valueOf(jFormattedTextField10.getText()));
                        }
                        if (!jFormattedTextField11.getText().equals(StringUtils.EMPTY_STRING)) {
                            s1.setResetLag(Integer.valueOf(jFormattedTextField11.getText()));
                        }

                        s1.setIsPayInArrears(jCheckBox1.isSelected());
                        s1.setIsPayLagBusDays(jCheckBox3.isSelected());
                        s1.setIsResetInArrears(jCheckBox2.isSelected());
                        s1.setIsResetLagBusDays(jCheckBox4.isSelected());

                        try {
                            if (jFormattedTextField9.getDate() != null) {
                                s1.setFirstDate(jFormattedTextField9.getDate());
                            }
                            if (jFormattedTextField8.getDate() != null) {
                                s1.setPenultimateDate(jFormattedTextField8.getDate());
                            }
                        } catch (Exception ex) {
                            logger.error("error " + StringUtils.formatErrorMessage(ex));
                        }
                        product1.setScheduler(s1);
                    }
                    if (jCheckCreditClause1.isSelected()) {
                        if (cred1 == null) {
                            cred1 = new ProductCredit();
                        }
                        if (!jComboIssuer1.getSelectedItem().equals(StringUtils.EMPTY_STRING)) {
                            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboIssuer1.getSelectedItem().toString());
                            cred1.setIssuer(issuer);
                        }
                        if (!jComboSeniority1.getSelectedItem().equals(StringUtils.EMPTY_STRING)) {
                            cred1.setSeniority(jComboSeniority1.getSelectedItem().toString());
                        }
                        if (!jTextEventList1.getText().equals(StringUtils.EMPTY_STRING)) {
                            cred1.setCreditEvents(jTextEventList1.getText());
                        }
                        product1.setProductCredit(cred1);
                    }

                }

                if (jTabbedPane1.isEnabledAt(2)) {

                    product1.setIsAsset(false);
                    if (product2 == null) {
                        product2 = new Product();
                    }
                    product2.setIsAsset(false);
                    if (getProduct() == null) {
                        setProduct(new Product());
                    }
                    getProduct().setMaturityDate(product1.getMaturityDate());
                    getProduct().setStartDate(product1.getStartDate());
                    getProduct().setNotionalMultiplier(product1.getNotionalMultiplier());
                    getProduct().setNotionalCurrency(product1.getNotionalCurrency());
                    getProduct().setProductType(jComboBoxProductType.getSelectedItem().toString());
                    getProduct().setIsAsset(false);
                    getProduct().setComment(jComment.getText());

                    if (!jFormattedTextFieldNotional2.getText().equals(StringUtils.EMPTY_STRING)) {
                        BigDecimal qleg2 = new BigDecimal(Double.parseDouble(jFormattedTextFieldNotional2.getText()));
                        qleg2 = qleg2.divide(new BigDecimal(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                        product2.setNotionalMultiplier(qleg2);
                    }
                    product2.setNotionalCurrency(jComboBoxCurrency2.getSelectedItem().toString());
                    if (jFormattedTextFieldMaturity2.getDate() != null) {
                        product2.setMaturityDate(jFormattedTextFieldMaturity2.getDate());
                    }
                    if (jFormattedTextFieldStartDate2.getDate() != null) {
                        product2.setStartDate(jFormattedTextFieldStartDate2.getDate());
                    }
                    if (jComboBoxProductType1.getSelectedItem() != null) {
                        product1.setProductType(jComboBoxProductType1.getSelectedItem().toString());
                    }
                    if (jComboBoxProductType2.getSelectedItem() != null) {
                        product2.setProductType(jComboBoxProductType2.getSelectedItem().toString());
                    }
                    otc2 = new ProductSingleTraded();
                    if (!jPayOffPayment2.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc2.setPayoffDateClause(jPayOffPayment2.getText());
                    }
                    if (!jPayOffCondition2.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc2.setPayoffConditionClause(jPayOffCondition2.getText());
                    }
                    if (!jPayOffFormula2.getText().equals(StringUtils.EMPTY_STRING)) {
                        otc2.setPayoffFormula(jPayOffFormula2.getText());
                    }

                    DefaultTableModel tableModel = (DefaultTableModel) jTableAttributes2.getModel();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        String data = tableModel.getValueAt(i, 1).toString();
                        IntrospectUtil.invokeSetter(ProductSingleTraded.class, otc2, tableModel.getValueAt(i, 0).toString(), data);
                    }

                    product2.setProductSingleTraded(otc2);

                    ArrayList<Product> listunderlyings = new ArrayList();
                    tableModel = (DefaultTableModel) jTableUnderlyings2.getModel();
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        Product underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.getInteger(tableModel.getValueAt(i, 1).toString()));
                        listunderlyings.add(underlying);
                    }
                    product2.getSubProducts().addAll(listunderlyings);

                    if (jCheckBoxScheduler2.isSelected()) {

                        if (s2 == null) {
                            s2 = new Scheduler();
                        }

                        if (!jComboBox8.getSelectedItem().equals("")) {
                            s2.setFrequency(jComboBox8.getSelectedItem().toString());
                        }
                        if (!jComboBox9.getSelectedItem().equals("")) {
                            s2.setDaycount(jComboBox9.getSelectedItem().toString());
                        }
                        if (!jComboBox10.getSelectedItem().equals("")) {
                            s2.setAdjustment(jComboBox10.getSelectedItem().toString());
                        }

                        if (!jFormattedTextField10.getText().equals("")) {
                            s2.setPaymentLag(Integer.valueOf(jFormattedTextField10.getText()));
                        }
                        if (!jFormattedTextField11.getText().equals("")) {
                            s2.setResetLag(Integer.valueOf(jFormattedTextField11.getText()));
                        }

                        s2.setIsPayInArrears(jCheckBox1.isSelected());
                        s2.setIsPayLagBusDays(jCheckBox3.isSelected());
                        s2.setIsResetInArrears(jCheckBox2.isSelected());
                        s2.setIsResetLagBusDays(jCheckBox4.isSelected());

                        try {
                            if (jFormattedTextField9.getDate() != null) {
                                s2.setFirstDate(jFormattedTextField9.getDate());
                            }
                            if (jFormattedTextField8.getDate() != null) {
                                s2.setPenultimateDate(jFormattedTextField8.getDate());
                            }
                        } catch (Exception ex) {
                            logger.error("error " + StringUtils.formatErrorMessage(ex));
                        }
                        product2.setScheduler(s2);
                    }

                    if (jCheckCreditClause2.isSelected()) {
                        if (cred2 == null) {
                            cred2 = new ProductCredit();
                        }
                        if (!jComboIssuer6.getSelectedItem().toString().isEmpty()) {
                            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboIssuer6.getSelectedItem().toString());
                            cred2.setIssuer(issuer);
                        }
                        if (!jComboSeniority6.getSelectedItem().toString().isEmpty()) {
                            cred2.setSeniority(jComboSeniority6.getSelectedItem().toString());
                        }
                        if (!jTextEventList6.getText().equals("")) {
                            cred2.setCreditEvents(jTextEventList6.getText());
                        }
                        product2.setProductCredit(cred2);
                    }

                    ArrayList<Product> products = new ArrayList();

                    products.add(product1);
                    products.add(product2);

                    getProduct().getSubProducts().addAll(products);
                } else {

                    setProduct(product1);
                    getProduct().setMaturityDate(product1.getMaturityDate());
                    getProduct().setStartDate(product1.getStartDate());
                    getProduct().setNotionalMultiplier(product1.getNotionalMultiplier());
                    getProduct().setNotionalCurrency(product1.getNotionalCurrency());
                    product1 = null;
                    getProduct().setProductType(jComboBoxProductType.getSelectedItem().toString());
                    getProduct().setIsAsset(false);
                    getProduct().setComment(jComment.getText());
                }

                getTrade().setQuantity(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                getTrade().setPrice(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldPrice.getText())));
                getTrade().setAmount(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldAmount.getText())));
                if (jFormattedTextFieldSettleDate.getDate() != null) {
                    getTrade().setValueDate((jFormattedTextFieldSettleDate.getDate()));
                }
                getTrade().setPriceCurrency(jComboBoxPriceCurrency.getSelectedItem().toString());
                if (jComboBoxAmountCurrency.getSelectedItem() != null) {
                    getTrade().setSettlementCurrency(jComboBoxAmountCurrency.getSelectedItem().toString());
                }
                getTrade().setForexRate(BigDecimal.valueOf(Double.parseDouble("1.")));
                DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, Product.class, getProduct());
            } else {
                if (jLabelProductId.getText() != null && !jLabelProductId.getText().isEmpty()) {
                    setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, jLabelProductId.getText().toString()));

                }

                getTrade().setQuantity(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                getTrade().setPrice(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldPrice.getText())));
                getTrade().setAmount(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldAmount.getText())));
                getTrade().setForexRate(BigDecimal.valueOf(Double.parseDouble(jFormattedTextFieldFXRate.getText())));
                getTrade().setValueDate(jFormattedTextFieldSettleDate.getDate());
                if (jComboBoxPriceCurrency.getSelectedItem() != null) {
                    getTrade().setPriceCurrency(jComboBoxPriceCurrency.getSelectedItem().toString());
                }
                if (jComboBoxAmountCurrency.getSelectedItem() != null) {
                    getTrade().setSettlementCurrency(jComboBoxAmountCurrency.getSelectedItem().toString());
                }

            }

            LegalEntity internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxInternalCounterparty.getSelectedItem().toString());
            getTrade().setInternalCounterparty(internalCounterparty);

            LegalEntity counterpart = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, counterpartyTextField.getText());
            getTrade().setCounterparty(counterpart);

        } catch (ParseException | NumberFormatException e) {
            logger.error("error " + StringUtils.formatErrorMessage(e));
        }
    }

    public class EnterListener implements ActionListener {

        /**
         * chargement quand on tape enter dans trade id
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

    public class MyPropertyChangeListener implements PropertyChangeListener {

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            try {
                jFormattedTextFieldNotional1.setText(decimalFormat.format(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                jFormattedTextFieldNotional2.setText(decimalFormat.format(Double.parseDouble(jFormattedTextFieldQuantity.getText())));
                if (!jFormattedTextFieldNotional1.getText().equals("") && !jFormattedTextFieldFXRate.getText().equals("")) {
                    Double d = Double.parseDouble(jFormattedTextFieldNotional1.getText()) * Double.parseDouble(jFormattedTextFieldFXRate.getText());
                    jFormattedTextFieldNotional2.setText(decimalFormat.format(d));
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    @Override
    public String getDisplayName() {
        String _displayName = super.getDisplayName();
        if (getTrade() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = "Custom Trade";
        }
        return _displayName;
    }

    public void loadTrade() {

        if (NumberUtils.isInteger(jTextFieldTradeId.getText())) {
            try {
                int tradeId = Integer.parseInt(jTextFieldTradeId.getText());
                setTrade((Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId));
                if (getTrade() != null) {
                    jTextFieldInternalTradeId.setText(Integer.toString(tradeId));
                    jComboBoxInternalCounterparty.setSelectedItem(getTrade().getInternalCounterparty().getShortName());
                    counterpartyTextField.setText(getTrade().getCounterparty().getShortName());
                    jSpinnerTradeDate.setDate(getTrade().getTradeDate());
                    jTextFieldTradeTime.setText(timeFormatter.format(getTrade().getTradeTime()));

                    if (getProduct().getIsAsset()) { //listes
                        if (jCheckBoxIsSingleTraded.isSelected()) {
                            jCheckBoxIsSingleTraded.doClick();
                        }
                        jTabbedPane1.setEnabledAt(1, false);
                        jTabbedPane1.setEnabledAt(2, false);
                        jTextFieldAssetName.setText(getProduct().getShortName());
                        jLabelProductId.setText(getProduct().getId().toString());
                        jFormattedTextFieldQuantity.setText(getTrade().getQuantity().toString());
                        jFormattedTextFieldPrice.setText(getTrade().getPrice().toString());
                        jFormattedTextFieldAmount.setText(getTrade().getAmount().toString());
                        jFormattedTextFieldFXRate.setText(getTrade().getForexRate().toString());
                        jComboBoxPriceCurrency.setSelectedItem(getTrade().getPriceCurrency());
                        jComboBoxAmountCurrency.setSelectedItem(getTrade().getSettlementCurrency());
                        jFormattedTextFieldSettleDate.setDate(getTrade().getValueDate());
                        jLabelAssetProductType.setText(getProduct().getProductType());
                        if (getProduct().getMaturityDate() != null) {
                            jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
                        }
                        /**
                         * rate
                         */
                        if (getProduct().getProductRate() != null) {
                            if (getProduct().getProductRate().getRate() != null) {
                                jFormattedTextFieldRate.setText(getProduct().getProductRate().getRate().toString());
                            }
                        } else if (getProduct().getSubProducts() != null) {
                            if (getProduct().getSubProducts().size() > 0) {
                                Product _product = getProduct().getSubProducts().iterator().next();
                                if (_product.getProductSingleTraded() != null) {
                                    jFormattedTextFieldRate.setText(_product.getProductSingleTraded().getRate().toString());
                                }
                            }
                        }
                        /**
                         * issuer
                         */
                        if (getProduct().getProductCredit() != null) {
                            if (getProduct().getProductCredit().getIssuer() != null) {
                                LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product.getProductId());
                                String issuerName = StringUtils.EMPTY_STRING;
                                if (issuer != null) {
                                    issuerName = issuer.getShortName();
                                }
                                jTextFieldIssuer.setText(issuerName);
                            }
                            if (getProduct().loadSingleUnderlying() != null) {
                                jTextFieldRateIndex.setText(getProduct().loadSingleUnderlying().getShortName());
                            }
                            if (getProduct().getProductRate().getSpread() != null) {
                                jFormattedTextFieldRate.setText(getProduct().getProductRate().getSpread().toString());
                            }
                        } else if (getProduct().getSubProducts() != null) {
                            if (getProduct().getSubProducts().size() > 0) {
                                Product _product = getProduct().getSubProducts().iterator().next();
                                if (_product.getProductCredit() != null) {
                                    LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, _product.getProductId());
                                    String issuerName = StringUtils.EMPTY_STRING;
                                    if (issuer != null) {
                                        issuerName = issuer.getShortName();
                                    }
                                    jTextFieldIssuer.setText(issuerName);
                                }
                            }
                        }

                    } else {
                        /**
                         * Single traded
                         */
                        if (!jCheckBoxIsSingleTraded.isSelected()) {
                            jCheckBoxIsSingleTraded.doClick();
                        }
                        if (!jTabbedPane1.isEnabledAt(1)) {
                            jButtonAddComponent.doClick();
                        }
                        jComboBoxProductType.setSelectedItem(getProduct().getProductType());
                        jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
                        jFormattedTextFieldStartDate.setDate(getProduct().getStartDate());
                        jFormattedTextFieldQuantity.setText(getTrade().getQuantity().toString());
                        jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());
                        jFormattedTextFieldAmount.setText(getTrade().getAmount().toString());
                        jComboBoxAmountCurrency.setSelectedItem(getTrade().getSettlementCurrency());
                        jComment.setText(getProduct().getComment());

                        if (!getProduct().getSubProducts().isEmpty()) {
                            product1 = getProduct().getSubProducts().iterator().next();
                        } else {
                            product1 = getProduct();
                        }
                        jFormattedTextFieldMaturity1.setDate(product1.getMaturityDate());
                        jFormattedTextFieldStartDate1.setDate(product1.getStartDate());
                        jFormattedTextFieldNotional1.setText((getTrade().getQuantity().multiply(product1.getNotionalMultiplier())).toString());
                        jComboBoxCurrency1.setSelectedItem(product1.getNotionalCurrency());
                        jComboBoxProductType1.setSelectedItem(product1.getProductType());
                        s1 = product1.getScheduler();
                        if (s1 != null) {
                            if (!jCheckBoxScheduler1.isSelected()) {
                                jCheckBoxScheduler1.doClick();
                            }
                            jComboBox8.setSelectedItem(s1.getFrequency());
                            jComboBox9.setSelectedItem(s1.getDaycount());
                            jComboBox10.setSelectedItem(s1.getAdjustment());
                            if (s1.getPaymentLag() != null) {
                                jFormattedTextField10.setText(s1.getPaymentLag().toString());
                            }
                            if (s1.getResetLag() != null) {
                                jFormattedTextField11.setText(s1.getResetLag().toString());
                            }
                            jCheckBox1.setSelected(s1.getIsPayInArrears());
                            jCheckBox2.setSelected(s1.getIsResetInArrears());
                            jCheckBox3.setSelected(s1.getIsPayLagBusDays());
                            jCheckBox4.setSelected(s1.getIsResetLagBusDays());
                            if (s1.getFirstDate() != null) {
                                jFormattedTextField9.setDate(s1.getFirstDate());
                            }
                            if (s1.getPenultimateDate() != null) {
                                jFormattedTextField8.setDate(s1.getPenultimateDate());
                            }
                        } else {
                            if (jCheckBoxScheduler1.isSelected()) {
                                jCheckBoxScheduler1.doClick();
                            }
                        }
                        cred1 = product1.getProductCredit();
                        if (cred1 != null) {
                            if (!jCheckCreditClause1.isSelected()) {
                                jCheckCreditClause1.doClick();
                            }
                            if (cred1.getIssuer() != null) {
                                jComboIssuer1.setSelectedItem(cred1.getIssuer());
                            }
                            jComboSeniority1.setSelectedItem(cred1.getSeniority());
                            jTextEventList1.setText(cred1.getCreditEvents());
                        }
                        otc = product1.getProductSingleTraded();
                        if (otc != null) {
                            jPayOffPayment1.setText(otc.getPayoffDateClause());
                            jPayOffCondition1.setText(otc.getPayoffConditionClause());
                            jPayOffFormula1.setText(otc.getPayoffFormula());
                        }
                        /**
                         * underlyings
                         */
                        DefaultTableModel tm = (DefaultTableModel) jTableUnderlyings1.getModel();
                        while (tm.getRowCount() > 0) {
                            tm.removeRow(0);
                        }
                        for (Product pu : product1.loadUnderlyings()) {
                            Vector v = new Vector();
                            v.add(pu.getShortName());
                            v.add(pu.getId().toString());
                            tm.addRow(v);
                        }
                        jTableUnderlyings1.setModel(tm);

                        /**
                         * attributes
                         */
                        try {
                            Class c = Class.forName(ProductSingleTraded.class.getName());
                            ArrayList<Method> methods = IntrospectUtil.getGettersNoObject(c.getName());
                            tm = (DefaultTableModel) jTableAttributes1.getModel();
                            while (tm.getRowCount() > 0) {
                                tm.removeRow(0);
                            }
                            for (Method m : methods) {
                                Object data = m.invoke(product1.getProductSingleTraded(), (Object[]) null);
                                if (data != null) {
                                    if (m.getName().length() < 9) {
                                        Vector v = new Vector();
                                        v.add(m.getName().substring(3));
                                        v.add(data);
                                        tm.addRow(v);
                                    } else if (!m.getName().subSequence(0, 9).equals("getPayoff") && !m.equals("getProductId")) {
                                        Vector v = new Vector();
                                        v.add(m.getName().substring(3));
                                        v.add(data);
                                        tm.addRow(v);
                                    }
                                }

                            }
                            jTableAttributes1.setModel(tm);

                        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                            logger.error("error" + StringUtils.formatErrorMessage(e));
                        }
                        /**
                         * leg 2
                         */
                        if (!getProduct().getSubProducts().isEmpty()) {
                            if (getProduct().getSubProducts().size() == 2) {
                                jButtonAddComponent.doClick();
                                Iterator it = getProduct().getSubProducts().iterator();
                                it.next();
                                product2 = (Product) it.next();

                                jFormattedTextFieldMaturity2.setDate(product2.getMaturityDate());
                                jFormattedTextFieldStartDate2.setDate(product2.getStartDate());
                                jFormattedTextFieldNotional2.setText((getTrade().getQuantity().multiply(product2.getNotionalMultiplier())).toString());
                                jComboBoxCurrency2.setSelectedItem(product2.getNotionalCurrency());
                                jComboBoxProductType2.setSelectedItem(product2.getProductType());
                                s2 = product2.getScheduler();
                                if (s2 != null) {
                                    if (!jCheckBoxScheduler2.isSelected()) {
                                        jCheckBoxScheduler2.doClick();
                                    }
                                    jComboBox11.setSelectedItem(s2.getFrequency());
                                    jComboBox12.setSelectedItem(s2.getDaycount());
                                    jComboBox13.setSelectedItem(s2.getAdjustment());
                                    if (s2.getPaymentLag() != null) {
                                        jFormattedTextField12.setText(s2.getPaymentLag().toString());
                                    }
                                    if (s2.getResetLag() != null) {
                                        jFormattedTextField13.setText(s2.getResetLag().toString());
                                    }
                                    jCheckBox5.setSelected(s2.getIsPayInArrears());
                                    jCheckBox6.setSelected(s2.getIsResetInArrears());
                                    jCheckBox7.setSelected(s2.getIsPayLagBusDays());
                                    jCheckBox8.setSelected(s2.getIsResetLagBusDays());
                                    if (s2.getFirstDate() != null) {
                                        jFormattedTextField15.setDate(s2.getFirstDate());
                                    }
                                    if (s2.getPenultimateDate() != null) {
                                        jFormattedTextField14.setDate(s2.getPenultimateDate());
                                    }
                                } else {
                                    if (jCheckBoxScheduler2.isSelected()) {
                                        jCheckBoxScheduler2.doClick();
                                    }
                                }
                                cred2 = product2.getProductCredit();
                                if (cred2 != null) {
                                    if (!jCheckCreditClause2.isSelected()) {
                                        jCheckCreditClause2.doClick();
                                    }
                                    if (cred2.getIssuer() != null) {
                                        jComboIssuer6.setSelectedItem(cred2.getIssuer());
                                    }
                                    jComboSeniority6.setSelectedItem(cred2.getSeniority());
                                    jTextEventList6.setText(cred2.getCreditEvents());
                                }
                                otc2 = product2.getProductSingleTraded();
                                if (otc2 != null) {
                                    jPayOffPayment2.setText(otc2.getPayoffDateClause());
                                    jPayOffCondition2.setText(otc2.getPayoffConditionClause());
                                    jPayOffFormula2.setText(otc2.getPayoffFormula());
                                }
                                /**
                                 * underlyings
                                 */
                                tm = (DefaultTableModel) jTableUnderlyings2.getModel();
                                while (tm.getRowCount() > 0) {
                                    tm.removeRow(0);
                                }
                                for (Product pu : product2.loadUnderlyings()) {
                                    Vector v = new Vector();
                                    v.add(pu.getShortName());
                                    v.add(pu.getId().toString());
                                    tm.addRow(v);
                                }
                                jTableUnderlyings2.setModel(tm);

                                /**
                                 * attributes
                                 */
                                try {
                                    Class c = Class.forName(ProductSingleTraded.class.getName());
                                    ArrayList<Method> methods = IntrospectUtil.getGettersNoObject(c.getName());
                                    tm = (DefaultTableModel) jTableAttributes2.getModel();
                                    while (tm.getRowCount() > 0) {
                                        tm.removeRow(0);
                                    }
                                    for (Method m : methods) {
                                        Object data = m.invoke(product2.getProductSingleTraded(), (Object[]) null);
                                        if (data != null) {
                                            if (m.getName().length() < 9) {
                                                Object[] row = {m.getName().substring(3)};
                                                tm.addRow(row);
                                            } else if (!m.getName().subSequence(0, 9).equals("getPayoff") && !m.equals("getProductId")) {
                                                Object[] row = {m.getName().substring(3)};
                                                tm.addRow(row);
                                            }
                                        }
                                    }
                                    jTableAttributes2.setModel(tm);
                                } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                                    logger.error("error" + StringUtils.formatErrorMessage(e));
                                }
                            }
                        }
                        jFormattedTextFieldQuantity.firePropertyChange("value", false, false);

                        jTabbedPane1.setSelectedIndex(0);
                    }
                    setDisplayName(getDisplayName());
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    public void calculate() {
        Double qtty = 0.;
        Double price = 0.;
        Double fxrate = 0.;
        if (!jFormattedTextFieldQuantity.getText().isEmpty()) {
            qtty = Double.parseDouble(jFormattedTextFieldQuantity.getText());
        }
        if (!jFormattedTextFieldPrice.getText().equals("")) {
            price = Double.parseDouble(jFormattedTextFieldPrice.getText());
        }
        if (!jFormattedTextFieldFXRate.getText().equals("")) {
            fxrate = Double.parseDouble(jFormattedTextFieldFXRate.getText());
        }
        Double amount = 0.;
        if (getProduct() != null) {

            if (getProduct().getQuotationType().equals("Clean") || getProduct().getQuotationType().equals("Dirty") || getProduct().getQuotationType().equals("Rate")) {
                amount = qtty * price * fxrate / 100;
            } else if (getProduct().getProductType().equals("Credit Entity") || getProduct().getProductType().equals("Credit Index")) {
                /**
                 * calcul bien faux : il faut le pricer
                 */
                amount = qtty * (price - getProduct().getProductRate().getRate().doubleValue()) * fxrate / 100;
            } else if (getProduct().getIsAsset() == false) {
                amount = qtty * price / 100;
            }
        }
        if (amount == 0) {
            amount = qtty * price * fxrate;

        }
        jFormattedTextFieldAmount.setText(amount.toString());
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxInternalCounterparty = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldTradeId = new javax.swing.JTextField();
        loadButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxIsSingleTraded = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jButtonAddComponent = new javax.swing.JButton();
        counterpartyTextField = new javax.swing.JTextField();
        legalEntityFinderButton = new javax.swing.JButton();
        jTextFieldTradeTime = new javax.swing.JFormattedTextField(timeFormatter);
        jSpinnerTradeDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jTextFieldInternalTradeId = new javax.swing.JTextField();
        jPanelAsset = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldAssetName = new javax.swing.JTextField();
        jButtonFindAsset = new javax.swing.JButton();
        jLabelProductId = new javax.swing.JLabel();
        jComboBoxProductType = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextFieldQuantity = new javax.swing.JFormattedTextField(decimalFormat);
        jFormattedTextFieldPrice = new javax.swing.JFormattedTextField(decimalFormat);
        jLabel11 = new javax.swing.JLabel();
        jComboBoxPriceCurrency = new javax.swing.JComboBox();
        jFormattedTextFieldAmount = new javax.swing.JFormattedTextField(decimalFormat);
        jComboBoxAmountCurrency = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jFormattedTextFieldFXRate = new javax.swing.JFormattedTextField(new DecimalFormat("### ### ### ##0.0000000",decimalFormatSymbol));
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jComment = new javax.swing.JTextArea();
        jComboBoxQuantityNotional = new javax.swing.JComboBox();
        jButtonShowAsset = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabelAssetProductType = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jFormattedTextFieldRate = new javax.swing.JFormattedTextField();
        jTextFieldIssuer = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextFieldRateIndex = new javax.swing.JTextField();
        jFormattedTextFieldStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldMaturity = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldSettleDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        saveButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        exportButton = new javax.swing.JButton();
        tradeDetailsButton = new javax.swing.JButton();
        jPanelLeg1 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jComboBoxProductType1 = new javax.swing.JComboBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jFormattedTextFieldNotional1 = new javax.swing.JFormattedTextField(decimalFormat);
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUnderlyings1 = new javax.swing.JTable();
        jButtonAddUnderlying1 = new javax.swing.JButton();
        jButtonRemoveUnderlying1 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jButtonShowReferences1 = new javax.swing.JButton();
        jComboBoxCurrency1 = new javax.swing.JComboBox();
        jPanelScheduler1 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jComboBox8 = new javax.swing.JComboBox();
        jComboBox9 = new javax.swing.JComboBox();
        jComboBox10 = new javax.swing.JComboBox();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jFormattedTextField10 = new javax.swing.JFormattedTextField(integerFormat);
        jFormattedTextField11 = new javax.swing.JFormattedTextField(integerFormat);
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jFormattedTextField9 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextField8 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jCheckBoxScheduler1 = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableAttributes1 = new javax.swing.JTable();
        jButtonAddField1 = new javax.swing.JButton();
        jButtonRemoveAttributes1 = new javax.swing.JButton();
        jButtonPayOff1 = new javax.swing.JButton();
        jPayOffPayment1 = new javax.swing.JTextField();
        jPayOffCondition1 = new javax.swing.JTextField();
        jPayOffFormula1 = new javax.swing.JTextField();
        jPanelCredit1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jComboIssuer1 = new javax.swing.JComboBox();
        jLabel41 = new javax.swing.JLabel();
        jComboSeniority1 = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        jComboEventList1 = new javax.swing.JComboBox();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jTextEventList1 = new javax.swing.JTextField();
        jCheckCreditClause1 = new javax.swing.JCheckBox();
        jComboPayReceive1 = new javax.swing.JComboBox();
        jFormattedTextFieldMaturity1 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldStartDate1 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jPanelLeg2 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jComboBoxProductType2 = new javax.swing.JComboBox();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jFormattedTextFieldNotional2 = new javax.swing.JFormattedTextField(decimalFormat);
        jScrollPane3 = new javax.swing.JScrollPane();
        jTableUnderlyings2 = new javax.swing.JTable();
        jButtonAddUnderlying2 = new javax.swing.JButton();
        jButtonRemoveUnderlying2 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        jButtonShowReferences2 = new javax.swing.JButton();
        jComboBoxCurrency2 = new javax.swing.JComboBox();
        jPanelScheduler2 = new javax.swing.JPanel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jComboBox11 = new javax.swing.JComboBox();
        jComboBox12 = new javax.swing.JComboBox();
        jComboBox13 = new javax.swing.JComboBox();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jFormattedTextField12 = new javax.swing.JFormattedTextField(integerFormat);
        jFormattedTextField13 = new javax.swing.JFormattedTextField(integerFormat);
        jCheckBox5 = new javax.swing.JCheckBox();
        jCheckBox6 = new javax.swing.JCheckBox();
        jCheckBox7 = new javax.swing.JCheckBox();
        jCheckBox8 = new javax.swing.JCheckBox();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jFormattedTextField14 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextField15 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jCheckBoxScheduler2 = new javax.swing.JCheckBox();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableAttributes2 = new javax.swing.JTable();
        jButtonAddField2 = new javax.swing.JButton();
        jButtonRemoveAttributes2 = new javax.swing.JButton();
        jButtonPayOff2 = new javax.swing.JButton();
        jPayOffPayment2 = new javax.swing.JTextField();
        jPayOffCondition2 = new javax.swing.JTextField();
        jPayOffFormula2 = new javax.swing.JTextField();
        jPanelCredit2 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jComboIssuer6 = new javax.swing.JComboBox();
        jLabel56 = new javax.swing.JLabel();
        jComboSeniority6 = new javax.swing.JComboBox();
        jLabel57 = new javax.swing.JLabel();
        jComboEventList6 = new javax.swing.JComboBox();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jTextEventList6 = new javax.swing.JTextField();
        jCheckCreditClause2 = new javax.swing.JCheckBox();
        jComboPayReceive2 = new javax.swing.JComboBox();
        jFormattedTextFieldMaturity2 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jFormattedTextFieldStartDate2 = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());

        jTabbedPane1.setToolTipText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jTabbedPane1.toolTipText")); // NOI18N

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel2.text")); // NOI18N

        jComboBoxInternalCounterparty.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel6, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(loadButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.loadButton.text")); // NOI18N
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel4.text")); // NOI18N

        jCheckBoxIsSingleTraded.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxIsSingleTraded, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBoxIsSingleTraded.text")); // NOI18N
        jCheckBoxIsSingleTraded.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxIsSingleTradedActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddComponent, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonAddComponent.text")); // NOI18N
        jButtonAddComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddComponentActionPerformed(evt);
            }
        });

        counterpartyTextField.setText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.counterpartyTextField.text")); // NOI18N
        counterpartyTextField.setToolTipText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.counterpartyTextField.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(legalEntityFinderButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.legalEntityFinderButton.text")); // NOI18N
        legalEntityFinderButton.setToolTipText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.legalEntityFinderButton.toolTipText")); // NOI18N
        legalEntityFinderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                legalEntityFinderButtonActionPerformed(evt);
            }
        });

        jTextFieldTradeTime.setText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jTextFieldTradeTime.text")); // NOI18N

        jTextFieldInternalTradeId.setEditable(false);
        jTextFieldInternalTradeId.setText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jTextFieldInternalTradeId.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(counterpartyTextField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jCheckBoxIsSingleTraded, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(legalEntityFinderButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jButtonAddComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(loadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxInternalCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadButton)
                    .addComponent(counterpartyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(legalEntityFinderButton)
                    .addComponent(jTextFieldInternalTradeId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jCheckBoxIsSingleTraded)
                        .addComponent(jLabel7)
                        .addComponent(jButtonAddComponent)
                        .addComponent(jTextFieldTradeTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSpinnerTradeDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTextFieldInternalTradeId.setVisible(false);

        jPanelAsset.setBackground(new java.awt.Color(254, 252, 254));
        jPanelAsset.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel5, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonFindAsset, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonFindAsset.text")); // NOI18N
        jButtonFindAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindAssetActionPerformed(evt);
            }
        });

        jComboBoxProductType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel8, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel9, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel9.text")); // NOI18N

        jFormattedTextFieldQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldQuantityActionPerformed(evt);
            }
        });

        jFormattedTextFieldPrice.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jFormattedTextFieldPriceFocusLost(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel11, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel11.text")); // NOI18N

        jComboBoxPriceCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxPriceCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPriceCurrencyActionPerformed(evt);
            }
        });

        jFormattedTextFieldAmount.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jComboBoxAmountCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxAmountCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxAmountCurrencyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel12, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel12.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel13, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel13.text")); // NOI18N

        jFormattedTextFieldFXRate.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldFXRate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldFXRateActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel14, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel14.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel15, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel15.text")); // NOI18N

        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurrencyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel1.text")); // NOI18N

        jComment.setColumns(20);
        jComment.setRows(5);
        jScrollPane5.setViewportView(jComment);

        jComboBoxQuantityNotional.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Quantity", "Notional" }));
        jComboBoxQuantityNotional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxQuantityNotionalActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowAsset, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonShowAsset.text")); // NOI18N
        jButtonShowAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowAssetActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelAssetProductType, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabelAssetProductType.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel43, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel43.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel44, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel44.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel45, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel45.text")); // NOI18N

        javax.swing.GroupLayout jPanelAssetLayout = new javax.swing.GroupLayout(jPanelAsset);
        jPanelAsset.setLayout(jPanelAssetLayout);
        jPanelAssetLayout.setHorizontalGroup(
            jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAssetLayout.createSequentialGroup()
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanelAssetLayout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelAssetLayout.createSequentialGroup()
                            .addComponent(jComboBoxQuantityNotional, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAssetLayout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addGap(18, 18, 18)
                            .addComponent(jTextFieldAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelAssetLayout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanelAssetLayout.createSequentialGroup()
                                    .addGap(41, 41, 41)
                                    .addComponent(jFormattedTextFieldAmount))
                                .addGroup(jPanelAssetLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(140, 140, 140)))))
                    .addGroup(jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jFormattedTextFieldSettleDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonFindAsset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxPriceCurrency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxCurrency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxAmountCurrency, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonShowAsset)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(jLabel44))
                .addGap(30, 30, 30)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAssetLayout.createSequentialGroup()
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel45))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldRateIndex, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                            .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(24, 24, 24))
                    .addGroup(jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelAssetLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel10)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanelAssetLayout.createSequentialGroup()
                                .addComponent(jLabelAssetProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
                                .addGap(218, 218, 218))))))
            .addGroup(jPanelAssetLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAssetLayout.setVerticalGroup(
            jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAssetLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextFieldAssetName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonFindAsset)
                            .addComponent(jButtonShowAsset))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jFormattedTextFieldQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jComboBoxQuantityNotional, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanelAssetLayout.createSequentialGroup()
                                .addComponent(jLabelProductId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(11, 11, 11))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAssetLayout.createSequentialGroup()
                                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelAssetProductType, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jComboBoxProductType, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextFieldStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel15)
                                .addComponent(jLabel14))))
                    .addComponent(jFormattedTextFieldMaturity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBoxPriceCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel43)
                    .addComponent(jFormattedTextFieldRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45)
                    .addComponent(jTextFieldRateIndex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldIssuer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel44))
                    .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jFormattedTextFieldAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxAmountCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jFormattedTextFieldFXRate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jFormattedTextFieldSettleDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(jPanelAssetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelAssetLayout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(newButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.newButton.text")); // NOI18N
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(exportButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.exportButton.text")); // NOI18N
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(tradeDetailsButton, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.tradeDetailsButton.text")); // NOI18N
        tradeDetailsButton.setToolTipText(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.tradeDetailsButton.toolTipText")); // NOI18N
        tradeDetailsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tradeDetailsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanelAsset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tradeDetailsButton)
                        .addGap(185, 185, 185)
                        .addComponent(newButton)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton)
                        .addGap(171, 171, 171))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelAsset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(newButton)
                    .addComponent(exportButton)
                    .addComponent(tradeDetailsButton))
                .addContainerGap())
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanelLeg1.setBackground(new java.awt.Color(254, 252, 254));
        jPanelLeg1.setPreferredSize(new java.awt.Dimension(1000, 523));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel16, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel16.text")); // NOI18N

        jComboBoxProductType1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel17, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel17.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel18, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel18.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel19, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel19.text")); // NOI18N

        jFormattedTextFieldNotional1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldNotional1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldNotional1ActionPerformed(evt);
            }
        });

        jTableUnderlyings1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Underlyings","Id"
            }
        ));
        jScrollPane1.setViewportView(jTableUnderlyings1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddUnderlying1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonAddUnderlying1.text")); // NOI18N
        jButtonAddUnderlying1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUnderlying1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveUnderlying1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonRemoveUnderlying1.text")); // NOI18N
        jButtonRemoveUnderlying1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveUnderlying1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel20, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel20.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowReferences1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonShowReferences1.text")); // NOI18N
        jButtonShowReferences1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowReferences1ActionPerformed(evt);
            }
        });

        jComboBoxCurrency1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jPanelScheduler1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel22, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel22.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel23, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel23.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel24, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel24.text")); // NOI18N

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBox9.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBox10.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel27, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel27.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel28, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel28.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox3, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox4, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox4.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel26, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel26.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel25, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel25.text")); // NOI18N

        javax.swing.GroupLayout jPanelScheduler1Layout = new javax.swing.GroupLayout(jPanelScheduler1);
        jPanelScheduler1.setLayout(jPanelScheduler1Layout);
        jPanelScheduler1Layout.setHorizontalGroup(
            jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox8, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addComponent(jLabel27))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextField10)
                    .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addComponent(jCheckBox3)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox1))
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addComponent(jCheckBox4)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(65, 65, 65)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15))
        );
        jPanelScheduler1Layout.setVerticalGroup(
            jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel24)
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jFormattedTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox3)
                            .addComponent(jCheckBox1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel28)
                            .addComponent(jFormattedTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox4)
                            .addComponent(jCheckBox2)))
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25)
                            .addComponent(jFormattedTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel26)
                            .addComponent(jFormattedTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelScheduler1Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler1Layout.createSequentialGroup()
                                .addGroup(jPanelScheduler1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23))
                                .addGap(23, 23, 23))
                            .addComponent(jComboBox10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxScheduler1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBoxScheduler1.text")); // NOI18N
        jCheckBoxScheduler1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxScheduler1ActionPerformed(evt);
            }
        });

        jTableAttributes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Fields","Value"
            }
        ));
        jScrollPane2.setViewportView(jTableAttributes1);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddField1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonAddField1.text")); // NOI18N
        jButtonAddField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddField1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveAttributes1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonRemoveAttributes1.text")); // NOI18N
        jButtonRemoveAttributes1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveAttributes1ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonPayOff1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonPayOff1.text")); // NOI18N
        jButtonPayOff1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayOff1ActionPerformed(evt);
            }
        });

        jPanelCredit1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel40, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel40.text")); // NOI18N

        jComboIssuer1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel41, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel41.text")); // NOI18N

        jComboSeniority1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel42, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel42.text")); // NOI18N

        jComboEventList1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        org.openide.awt.Mnemonics.setLocalizedText(jButton3, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButton3.text")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton4, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButton4.text")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

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
                            .addComponent(jLabel41)
                            .addComponent(jLabel40)
                            .addComponent(jLabel42))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCredit1Layout.createSequentialGroup()
                                .addComponent(jComboEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addComponent(jComboSeniority1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboIssuer1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanelCredit1Layout.setVerticalGroup(
            jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel40)
                    .addComponent(jComboIssuer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jComboSeniority1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel42)
                    .addComponent(jComboEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEventList1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckCreditClause1, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckCreditClause1.text")); // NOI18N
        jCheckCreditClause1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckCreditClause1ActionPerformed(evt);
            }
        });

        jComboPayReceive1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pay", "Receive" }));

        javax.swing.GroupLayout jPanelLeg1Layout = new javax.swing.GroupLayout(jPanelLeg1);
        jPanelLeg1.setLayout(jPanelLeg1Layout);
        jPanelLeg1Layout.setHorizontalGroup(
            jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel17)
                                    .addComponent(jLabel18)
                                    .addComponent(jLabel19))
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(jComboBoxProductType1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jFormattedTextFieldNotional1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldMaturity1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jFormattedTextFieldStartDate1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jButtonAddField1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonRemoveAttributes1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jButtonShowReferences1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg1Layout.createSequentialGroup()
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPayOffPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addComponent(jButtonPayOff1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboPayReceive1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jPayOffFormula1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPayOffCondition1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 210, Short.MAX_VALUE)
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jCheckCreditClause1)
                                    .addComponent(jPanelCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg1Layout.createSequentialGroup()
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButtonRemoveUnderlying1, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButtonAddUnderlying1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBoxScheduler1))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanelLeg1Layout.setVerticalGroup(
            jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxProductType1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17)
                            .addComponent(jFormattedTextFieldMaturity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jFormattedTextFieldStartDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldNotional1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jComboBoxCurrency1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                .addComponent(jButtonAddField1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoveAttributes1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jPayOffPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jPayOffFormula1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButtonPayOff1)
                                            .addComponent(jComboPayReceive1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jPayOffCondition1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addComponent(jCheckBoxScheduler1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(jPanelLeg1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanelLeg1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonShowReferences1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jCheckCreditClause1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanelCredit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonAddUnderlying1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveUnderlying1)
                        .addGap(384, 384, 384)))
                .addComponent(jPanelScheduler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jPanelLeg1.TabConstraints.tabTitle"), jPanelLeg1); // NOI18N

        jPanelLeg2.setBackground(new java.awt.Color(254, 252, 254));
        jPanelLeg2.setPreferredSize(new java.awt.Dimension(1000, 523));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel21, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel21.text")); // NOI18N

        jComboBoxProductType2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel29, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel29.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel30, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel30.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel31, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel31.text")); // NOI18N

        jFormattedTextFieldNotional2.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jFormattedTextFieldNotional2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextFieldNotional2ActionPerformed(evt);
            }
        });

        jTableUnderlyings2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Underlyings","Id"
            }
        ));
        jScrollPane3.setViewportView(jTableUnderlyings2);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddUnderlying2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonAddUnderlying2.text")); // NOI18N
        jButtonAddUnderlying2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUnderlying2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveUnderlying2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonRemoveUnderlying2.text")); // NOI18N
        jButtonRemoveUnderlying2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveUnderlying2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel32, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel32.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowReferences2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonShowReferences2.text")); // NOI18N
        jButtonShowReferences2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowReferences2ActionPerformed(evt);
            }
        });

        jComboBoxCurrency2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jPanelScheduler2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel33, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel33.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel34, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel34.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel35, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel35.text")); // NOI18N

        jComboBox11.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBox12.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jComboBox13.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel36, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel36.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel37, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel37.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox5, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox5.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox6, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox6.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox7, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox7.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBox8, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBox8.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel38, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel38.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel39, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel39.text")); // NOI18N

        javax.swing.GroupLayout jPanelScheduler2Layout = new javax.swing.GroupLayout(jPanelScheduler2);
        jPanelScheduler2.setLayout(jPanelScheduler2Layout);
        jPanelScheduler2Layout.setHorizontalGroup(
            jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel35)
                    .addComponent(jLabel34))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jComboBox13, javax.swing.GroupLayout.Alignment.LEADING, 0, 103, Short.MAX_VALUE)
                    .addComponent(jComboBox12, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox11, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel37)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextField12)
                    .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addComponent(jCheckBox5)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox7))
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addComponent(jCheckBox6)
                        .addGap(18, 18, 18)
                        .addComponent(jCheckBox8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel39, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jFormattedTextField14, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                    .addComponent(jFormattedTextField15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(103, 103, 103))
        );
        jPanelScheduler2Layout.setVerticalGroup(
            jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel39)
                            .addComponent(jFormattedTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel38)
                            .addComponent(jFormattedTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel35)
                        .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                            .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel36)
                                .addComponent(jFormattedTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox5)
                                .addComponent(jCheckBox7))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel37)
                                .addComponent(jFormattedTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jCheckBox6)
                                .addComponent(jCheckBox8)))
                        .addGroup(jPanelScheduler2Layout.createSequentialGroup()
                            .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelScheduler2Layout.createSequentialGroup()
                                    .addGroup(jPanelScheduler2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel34))
                                    .addGap(23, 23, 23))
                                .addComponent(jComboBox13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxScheduler2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckBoxScheduler2.text")); // NOI18N
        jCheckBoxScheduler2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxScheduler2ActionPerformed(evt);
            }
        });

        jTableAttributes2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Fields","Value"
            }
        ));
        jScrollPane4.setViewportView(jTableAttributes2);

        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddField2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonAddField2.text")); // NOI18N
        jButtonAddField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddField2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveAttributes2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonRemoveAttributes2.text")); // NOI18N
        jButtonRemoveAttributes2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveAttributes2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonPayOff2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButtonPayOff2.text")); // NOI18N
        jButtonPayOff2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPayOff2ActionPerformed(evt);
            }
        });

        jPanelCredit2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel55, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel55.text")); // NOI18N

        jComboIssuer6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel56, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel56.text")); // NOI18N

        jComboSeniority6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel57, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jLabel57.text")); // NOI18N

        jComboEventList6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        org.openide.awt.Mnemonics.setLocalizedText(jButton13, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButton13.text")); // NOI18N
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButton14, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jButton14.text")); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCredit2Layout = new javax.swing.GroupLayout(jPanelCredit2);
        jPanelCredit2.setLayout(jPanelCredit2Layout);
        jPanelCredit2Layout.setHorizontalGroup(
            jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextEventList6)
                    .addGroup(jPanelCredit2Layout.createSequentialGroup()
                        .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel56)
                            .addComponent(jLabel55)
                            .addComponent(jLabel57))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                                .addComponent(jComboEventList6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14))
                            .addComponent(jComboSeniority6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboIssuer6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        jPanelCredit2Layout.setVerticalGroup(
            jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCredit2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jComboIssuer6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(jComboSeniority6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelCredit2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(jComboEventList6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextEventList6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        org.openide.awt.Mnemonics.setLocalizedText(jCheckCreditClause2, org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jCheckCreditClause2.text")); // NOI18N
        jCheckCreditClause2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckCreditClause2ActionPerformed(evt);
            }
        });

        jComboPayReceive2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pay","Receive" }));

        javax.swing.GroupLayout jPanelLeg2Layout = new javax.swing.GroupLayout(jPanelLeg2);
        jPanelLeg2.setLayout(jPanelLeg2Layout);
        jPanelLeg2Layout.setHorizontalGroup(
            jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(jCheckBoxScheduler2))
                            .addComponent(jPanelScheduler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)
                                    .addComponent(jLabel30)
                                    .addComponent(jLabel31))
                                .addGap(35, 35, 35)
                                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBoxProductType2, 0, 122, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldNotional2)
                                    .addComponent(jFormattedTextFieldMaturity2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jFormattedTextFieldStartDate2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPayOffFormula2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPayOffPayment2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPayOffCondition2, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jButtonPayOff2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboPayReceive2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButtonRemoveAttributes2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonAddField2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBoxCurrency2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 155, Short.MAX_VALUE)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonShowReferences2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jCheckCreditClause2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE))
                            .addComponent(jPanelCredit2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(jButtonRemoveUnderlying2))
                                    .addComponent(jButtonAddUnderlying2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanelLeg2Layout.setVerticalGroup(
            jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBoxProductType2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel29)
                            .addComponent(jFormattedTextFieldMaturity2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel30)
                            .addComponent(jFormattedTextFieldStartDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jFormattedTextFieldNotional2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel31)
                            .addComponent(jComboBoxCurrency2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jButtonAddField2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoveAttributes2)
                                .addGap(42, 42, 42)
                                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                        .addGap(26, 26, 26)
                                        .addComponent(jPayOffPayment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jPayOffFormula2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jButtonPayOff2)
                                            .addComponent(jComboPayReceive2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addComponent(jPayOffCondition2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanelLeg2Layout.createSequentialGroup()
                        .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addGroup(jPanelLeg2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonShowReferences2)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLeg2Layout.createSequentialGroup()
                                .addComponent(jButtonAddUnderlying2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonRemoveUnderlying2)
                                .addGap(172, 172, 172)))
                        .addComponent(jCheckCreditClause2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelCredit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addComponent(jCheckBoxScheduler2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelScheduler2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(120, 120, 120))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(CustomTradeTopComponent.class, "CustomTradeTopComponent.jPanelLeg2.TabConstraints.tabTitle"), jPanelLeg2); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * load trade
     */
    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed

        loadTrade();
    }//GEN-LAST:event_loadButtonActionPerformed

    /**
     * Single Traded / asset selector
     *
     * hides or shows fields
     */
    private void jCheckBoxIsSingleTradedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxIsSingleTradedActionPerformed

        if (jCheckBoxIsSingleTraded.isSelected()) {
            /**
             * fields OTC
             */
            jComboBoxQuantityNotional.setSelectedItem("Notional");
            jLabel7.setVisible(true);
            jButtonAddComponent.setVisible(true);
            jLabel8.setVisible(true);
            jComboBoxProductType.setVisible(true);
            jLabelAssetProductType.setVisible(false);
            jLabel14.setVisible(true);
            jFormattedTextFieldMaturity.setEnabled(true);
            jLabel15.setVisible(true);
            jFormattedTextFieldStartDate.setVisible(true);
            jComboBoxCurrency.setVisible(true);
            jComment.setVisible(true);
            jFormattedTextFieldStartDate.setDate(jSpinnerTradeDate.getDate());
            jFormattedTextFieldMaturity.setDate(jSpinnerTradeDate.getDate());
            jFormattedTextFieldSettleDate.setDate(jSpinnerTradeDate.getDate());
            jLabel5.setVisible(false);
            jTextFieldAssetName.setVisible(false);
            jButtonFindAsset.setVisible(false);
            jComboBoxPriceCurrency.setVisible(false);
            jLabel13.setVisible(false);
            jFormattedTextFieldFXRate.setVisible(false);
            jButtonShowAsset.setVisible(false);
            jLabelProductId.setVisible(false);
        } else {

            jComboBoxQuantityNotional.setSelectedItem("Quantity");
            jLabel7.setVisible(false);
            jButtonAddComponent.setVisible(false);
            jComboBoxProductType.setVisible(false);
            jLabelAssetProductType.setVisible(true);
            jFormattedTextFieldMaturity.setEnabled(false);
            jLabel15.setVisible(false);
            jFormattedTextFieldStartDate.setVisible(false);
            jComboBoxCurrency.setVisible(false);
            jComment.setVisible(false);
            /**
             * fields Listed
             */
            jLabel5.setVisible(true);
            jTextFieldAssetName.setVisible(true);
            jButtonFindAsset.setVisible(true);
            jFormattedTextFieldQuantity.setText("0");
            jLabel9.setVisible(true);
            jFormattedTextFieldPrice.setVisible(true);
            jFormattedTextFieldPrice.setText("0");
            jComboBoxPriceCurrency.setVisible(true);
            jLabel11.setVisible(true);
            jFormattedTextFieldAmount.setVisible(true);
            jFormattedTextFieldAmount.setText("0");
            jComboBoxAmountCurrency.setVisible(true);
            jLabel13.setVisible(true);
            jFormattedTextFieldFXRate.setVisible(true);
            jLabel12.setVisible(true);
            jFormattedTextFieldSettleDate.setVisible(true);
            jLabelProductId.setVisible(true);
            jButtonShowAsset.setVisible(true);
            jLabelProductId.setVisible(true);
        }

    }//GEN-LAST:event_jCheckBoxIsSingleTradedActionPerformed

    /**
     * add component
     */
    private void jButtonAddComponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddComponentActionPerformed

        if (jTabbedPane1.isEnabledAt(1) == false) {
            jTabbedPane1.setEnabledAt(1, true);
            jFormattedTextFieldMaturity1.setDate(jFormattedTextFieldMaturity.getDate());
            jFormattedTextFieldStartDate1.setDate(jFormattedTextFieldStartDate.getDate());
            jFormattedTextFieldNotional1.setText(jFormattedTextFieldQuantity.getText());
            jComboBoxCurrency1.setSelectedItem(jComboBoxCurrency.getSelectedItem());
            jTabbedPane1.setSelectedIndex(1);

        } else {
            jTabbedPane1.setEnabledAt(2, true);
            jFormattedTextFieldMaturity2.setDate(jFormattedTextFieldMaturity.getDate());
            jFormattedTextFieldStartDate2.setDate(jFormattedTextFieldStartDate.getDate());
            jFormattedTextFieldNotional2.setText("-" + jFormattedTextFieldQuantity.getText());
            jComboBoxCurrency2.setSelectedItem(jComboBoxCurrency.getSelectedItem());
            jTabbedPane1.setSelectedIndex(2);

        }
    }//GEN-LAST:event_jButtonAddComponentActionPerformed

    /**
     * asset finder
     */
    private void jButtonFindAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindAssetActionPerformed

        assetFinder = new AssetFinder(ProductTypeUtil.loadListed());

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

            Integer productId = assetFinder.getAssetId();
            if (productId != null) {
                try {
                    setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));

                    jLabelProductId.setText(productId.toString());
                    jTextFieldAssetName.setText(getProduct().getShortName());
                    jLabelAssetProductType.setText(getProduct().getProductType());
                    if (getProduct().getMaturityDate() != null) {
                        jFormattedTextFieldMaturity.setDate(getProduct().getMaturityDate());
                    }
                    jComboBoxPriceCurrency.setSelectedItem(getProduct().getNotionalCurrency());
                    if (getProduct().getQuantityType() != null) {
                        if (getProduct().getQuantityType().equals("Notional")) {
                            jComboBoxQuantityNotional.setSelectedItem("Notional");
                            jComboBoxCurrency.setVisible(true);
                            jComboBoxPriceCurrency.setVisible(false);
                            jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());
                        } else {
                            jComboBoxQuantityNotional.setSelectedItem("Quantity");
                            jComboBoxCurrency.setVisible(false);
                            jComboBoxPriceCurrency.setVisible(true);
                        }
                    }
                    jComboBoxAmountCurrency.setSelectedItem(getProduct().getNotionalCurrency());
                    Calendar calendar = Calendar.getInstance();
                    if (getProduct().getProductEquity() != null) {
                        calendar.add(Calendar.DATE, getProduct().getSettlementDelay());
                    } else if (getProduct().getProductRate() != null) {
                        calendar.add(Calendar.DATE, getProduct().getSettlementDelay());
                    }
                    jFormattedTextFieldSettleDate.setDate(calendar.getTime());
                    jFormattedTextFieldFXRate.setText("1");
                    jFormattedTextFieldFXRate.setEditable(false);
                    if (getProduct().getProductRate() != null) {
                        if (getProduct().getProductRate().getRate() != null) {
                            jFormattedTextFieldRate.setText(getProduct().getProductRate().getRate().toString());
                        }
                        if (getProduct().loadSingleUnderlying() != null) {
                            jTextFieldRateIndex.setText(getProduct().loadSingleUnderlying().getShortName());
                            jFormattedTextFieldRate.setText(getProduct().getProductRate().getSpread().toString());
                        }
                    }
                    if (getProduct().getProductCredit() != null) {
                        if (getProduct().getProductCredit().getIssuer() != null) {
                            LegalEntity issuer = (LegalEntity) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_ISSUER, product.getProductId());
                            String issuerName = StringUtils.EMPTY_STRING;
                            if (issuer != null) {
                                issuerName = issuer.getShortName();
                            }
                            jTextFieldIssuer.setText(issuerName);
                        }

                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonFindAssetActionPerformed

    private void jFormattedTextFieldQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldQuantityActionPerformed

        calculate();
    }//GEN-LAST:event_jFormattedTextFieldQuantityActionPerformed

    /**
     * price changed => calculate amount
     */
    private void jFormattedTextFieldPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jFormattedTextFieldPriceFocusLost

        calculate();
    }//GEN-LAST:event_jFormattedTextFieldPriceFocusLost

    /**
     * currency change
     */
    private void jComboBoxPriceCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPriceCurrencyActionPerformed

        updateFxRate();
    }//GEN-LAST:event_jComboBoxPriceCurrencyActionPerformed

    /**
     * currency change
     */
    private void jComboBoxAmountCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxAmountCurrencyActionPerformed

        updateFxRate();
    }//GEN-LAST:event_jComboBoxAmountCurrencyActionPerformed

    private void jFormattedTextFieldSettleDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldSettleDateActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldSettleDateActionPerformed

    private void jFormattedTextFieldFXRateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldFXRateActionPerformed

        calculate();
    }//GEN-LAST:event_jFormattedTextFieldFXRateActionPerformed

    private void jFormattedTextFieldStartDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStartDateActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldStartDateActionPerformed

    private void jComboBoxCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurrencyActionPerformed
        /**
         * copy of the currency for otc
         */
        if (jCheckBoxIsSingleTraded.isSelected() && jComboBoxCurrency.getSelectedItem() != null) {
            jComboBoxAmountCurrency.setSelectedItem(jComboBoxCurrency.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBoxCurrencyActionPerformed

    /**
     * Show asset
     */
    private void jButtonShowAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowAssetActionPerformed

        if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.STOCK.name)) {
            WindowManager wm = WindowManager.getDefault();
            EquityTopComponent attc = (EquityTopComponent) wm.findTopComponent("EquityTopComponent");
            attc.load(getProduct().getId());
            attc.open();
            attc.requestActive();
        } else if (getProduct().getProductType().equals(ProductTypeUtil.ProductType.BOND.name) || getProduct().getProductType().equals(ProductTypeUtil.ProductType.CDS_PRODUCT.name)) {
            WindowManager wm = WindowManager.getDefault();
            BondsTopComponent attc = (BondsTopComponent) wm.findTopComponent("BondsTopComponent");
            attc.load(getProduct().getId());
            attc.open();
            attc.requestActive();
        }

    }//GEN-LAST:event_jButtonShowAssetActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        fillTrade();
        saveTrade(getTrade());

        jTextFieldInternalTradeId.setText(getTrade().getId().toString());
        jTextFieldTradeId.setText(getTrade().getId().toString());
    }//GEN-LAST:event_saveButtonActionPerformed

    /**
     * Button clear
     *
     * @param evt
     */
    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed

        clearFields(this);
    }//GEN-LAST:event_newButtonActionPerformed

    /**
     * Export to xml
     */
    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportButtonActionPerformed

        XMLExporter exportxml = new XMLExporter();
        exportxml.export(getTrade(), this);
    }//GEN-LAST:event_exportButtonActionPerformed

    private void jFormattedTextFieldStartDate1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStartDate1ActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldStartDate1ActionPerformed

    private void jFormattedTextFieldNotional1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNotional1ActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldNotional1ActionPerformed

    private void jButtonAddUnderlying1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUnderlying1ActionPerformed
        /**
         * add underlying leg 1 asset finder
         */
        assetFinder = new AssetFinder(ProductTypeUtil.loadUnderlyings());
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            if (productId != null) {
                try {
                    DefaultTableModel tm = (DefaultTableModel) jTableUnderlyings1.getModel();
                    product1 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                    Object[] row = {product1.getShortName(), product1.getId()};
                    tm.addRow(row);
                    jTableUnderlyings1.setModel(tm);
                    assetFinder.setVisible(false);
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonAddUnderlying1ActionPerformed

    /**
     * remove underlying leg 1
     */
    private void jButtonRemoveUnderlying1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveUnderlying1ActionPerformed

        DefaultTableModel tm = (DefaultTableModel) jTableUnderlyings1.getModel();
        int sel = jTableUnderlyings1.getSelectedRow();

        if (sel >= 0) {
            tm.removeRow(sel);
        }
    }//GEN-LAST:event_jButtonRemoveUnderlying1ActionPerformed

    private void jButtonShowReferences1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowReferences1ActionPerformed
    }//GEN-LAST:event_jButtonShowReferences1ActionPerformed

    private void jFormattedTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField8ActionPerformed
    }//GEN-LAST:event_jFormattedTextField8ActionPerformed

    /**
     * jCheckBox Scheduler leg 1
     */
    private void jCheckBoxScheduler1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxScheduler1ActionPerformed

        if (jCheckBoxScheduler1.isSelected()) {
            jPanelScheduler1.setVisible(true);
        } else {
            jPanelScheduler1.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxScheduler1ActionPerformed

    private void jButtonAddField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddField1ActionPerformed
        /**
         * Add Attribute Field leg 1
         */
        String[] array = (String[]) listAttributes.toArray(new String[listAttributes.size()]);
        DefaultTableModel tm = (DefaultTableModel) jTableAttributes1.getModel();
        Vector<Object> vector = new Vector<Object>();
        vector.add("");
        vector.add("");
        tm.addRow(vector);
        jTableAttributes1.setModel(tm);

        int vColIndex = 0;
        TableColumn col = jTableAttributes1.getColumnModel().getColumn(vColIndex);
        col.setCellEditor(new DefaultCellEditor(new JComboBox(array)));
    }//GEN-LAST:event_jButtonAddField1ActionPerformed

    private void jButtonRemoveAttributes1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveAttributes1ActionPerformed
        /**
         * remove attributee leg 1
         */
        DefaultTableModel tm = (DefaultTableModel) jTableAttributes1.getModel();
        int sel = jTableAttributes1.getSelectedRow();
        if (sel >= 0) {
            tm.removeRow(sel);
        }
    }//GEN-LAST:event_jButtonRemoveAttributes1ActionPerformed

    private void jButtonPayOff1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayOff1ActionPerformed

        ArrayList<String> listattrs = new ArrayList();
        listattrs.add("Notional");
        DefaultTableModel tm = (DefaultTableModel) jTableAttributes1.getModel();
        for (int i = 0; i < tm.getRowCount(); i++) {
            listattrs.add(tm.getValueAt(i, 0).toString());
        }
        ArrayList<String> listunderlyings = new ArrayList();
        tm = (DefaultTableModel) jTableUnderlyings1.getModel();
        for (int i = 0; i < tm.getRowCount(); i++) {
            listunderlyings.add(tm.getValueAt(i, 0).toString());
        }

        final PayOff payoff1 = new PayOff(listattrs, listunderlyings);
        if (otc != null) {
            payoff1.setPaymentMode(otc.getPayoffDateClause());
            payoff1.setCondition(otc.getPayoffConditionClause());
            payoff1.setFormula(otc.getPayoffFormula());
        }
        NotifyDescriptor nd = new NotifyDescriptor(
                payoff1, /**
                 * instance of your panel
                 */
                "Payoff", /**
                 * title of the dialog
                 */
                NotifyDescriptor.OK_CANCEL_OPTION, /**
                 * it is Yes/No dialog ...
                 */
                NotifyDescriptor.PLAIN_MESSAGE, /**
                 * ... of a question type => a question mark icon
                 */
                null, /**
                 * we have specified YES_NO_OPTION => can be null, options
                 * specified by L&F, otherwise specify options as: new Object[]
                 * { NotifyDescriptor.YES_OPTION, ... etc. },
                 */
                NotifyDescriptor.OK_OPTION /**
         * default option is "Yes"
         */
        );

        /**
         * let's display the dialog now...
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            jPayOffPayment1.setText(payoff1.getPaymentMode());
            jPayOffCondition1.setText(payoff1.getCondition());
            jPayOffFormula1.setText(payoff1.getFormula());

        }

    }//GEN-LAST:event_jButtonPayOff1ActionPerformed

    /**
     * add credit event leg 1
     *
     * @param evt
     */
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        if (jComboEventList1.getSelectedItem() != null) {
            if (jTextEventList1.getText().length() > 0) {
                jTextEventList1.setText(jTextEventList1.getText() + StringUtils.COMMA + jComboEventList1.getSelectedItem().toString());
            } else {
                jTextEventList1.setText(jComboEventList1.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * remove credit event leg 1
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        int i = jTextEventList1.getText().lastIndexOf(StringUtils.COMMA);
        if (jTextEventList1.getText().length() > 0) {
            if (i > 0) {
                jTextEventList1.setText(jTextEventList1.getText().substring(0, i));
            } else {
                jTextEventList1.setText("");
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * jCheckBox Credit Event leg 1
     */
    private void jCheckCreditClause1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckCreditClause1ActionPerformed

        if (jCheckCreditClause1.isSelected()) {
            jPanelCredit1.setVisible(true);
        } else {
            jPanelCredit1.setVisible(false);
        }
    }//GEN-LAST:event_jCheckCreditClause1ActionPerformed

    private void jFormattedTextFieldStartDate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldStartDate2ActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldStartDate2ActionPerformed

    private void jFormattedTextFieldNotional2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextFieldNotional2ActionPerformed
    }//GEN-LAST:event_jFormattedTextFieldNotional2ActionPerformed

    /**
     * add underlying leg 2 asset finder
     */
    private void jButtonAddUnderlying2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUnderlying2ActionPerformed

        assetFinder = new AssetFinder(ProductTypeUtil.loadUnderlyings());
        NotifyDescriptor nd = new NotifyDescriptor(
                assetFinder, /**
                 * instance of your panel
                 */
                "Asset Finder", /**
                 * title of the dialog
                 */
                NotifyDescriptor.OK_CANCEL_OPTION, /**
                 * it is Yes/No dialog ...
                 */
                NotifyDescriptor.PLAIN_MESSAGE, /**
                 * ... of a question type => a question mark icon
                 */
                null, /**
                 * we have specified YES_NO_OPTION => can be null, options
                 * specified by L&F, otherwise specify options as: new Object[]
                 * { NotifyDescriptor.YES_OPTION, ... etc. },
                 */
                NotifyDescriptor.OK_OPTION /**
         * default option is "Yes"
         */
        );

        /**
         * let's display the dialog now...
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

            Integer productId = assetFinder.getAssetId();

            if (productId != null) {
                try {
                    DefaultTableModel tm = (DefaultTableModel) jTableUnderlyings2.getModel();
                    product2 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                    Object[] row = {product2.getShortName(), product2.getId()};
                    tm.addRow(row);
                    jTableUnderlyings2.setModel(tm);
                    assetFinder.setVisible(false);
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }

    }//GEN-LAST:event_jButtonAddUnderlying2ActionPerformed

    /**
     * remove underlying leg 2
     */
    private void jButtonRemoveUnderlying2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveUnderlying2ActionPerformed

        DefaultTableModel tm = (DefaultTableModel) jTableUnderlyings2.getModel();
        int sel = jTableUnderlyings2.getSelectedRow();

        if (sel >= 0) {
            tm.removeRow(sel);
        }
    }//GEN-LAST:event_jButtonRemoveUnderlying2ActionPerformed

    private void jButtonShowReferences2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowReferences2ActionPerformed
    }//GEN-LAST:event_jButtonShowReferences2ActionPerformed

    private void jFormattedTextField15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField15ActionPerformed
    }//GEN-LAST:event_jFormattedTextField15ActionPerformed

    /**
     * jCheckBox Scheduler leg 2
     */
    private void jCheckBoxScheduler2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxScheduler2ActionPerformed

        if (jCheckBoxScheduler2.isSelected()) {
            jPanelScheduler2.setVisible(true);
        } else {
            jPanelScheduler2.setVisible(false);
        }
    }//GEN-LAST:event_jCheckBoxScheduler2ActionPerformed

    /**
     * Add Attribute Field leg 2
     */
    private void jButtonAddField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddField2ActionPerformed

        String[] array = (String[]) listAttributes.toArray(new String[listAttributes.size()]);
        DefaultTableModel tm = (DefaultTableModel) jTableAttributes2.getModel();
        Vector<Object> vector = new Vector<Object>();
        vector.add("");
        vector.add("");
        tm.addRow(vector);
        jTableAttributes2.setModel(tm);

        int vColIndex = 0;
        TableColumn col = jTableAttributes2.getColumnModel().getColumn(vColIndex);
        col.setCellEditor(new DefaultCellEditor(new JComboBox(array)));
    }//GEN-LAST:event_jButtonAddField2ActionPerformed

    /**
     * remove attribute leg 2
     */
    private void jButtonRemoveAttributes2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveAttributes2ActionPerformed

        DefaultTableModel tm = (DefaultTableModel) jTableAttributes2.getModel();
        int sel = jTableAttributes2.getSelectedRow();
        if (sel >= 0) {
            tm.removeRow(sel);
        }
    }//GEN-LAST:event_jButtonRemoveAttributes2ActionPerformed

    /**
     * open payoff window leg 2
     */
    private void jButtonPayOff2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPayOff2ActionPerformed

        ArrayList<String> listattrs = new ArrayList();
        listattrs.add("Notional");
        DefaultTableModel tm = (DefaultTableModel) jTableAttributes2.getModel();
        for (int i = 0; i < tm.getRowCount(); i++) {
            listattrs.add(tm.getValueAt(i, 0).toString());
        }
        ArrayList<String> listunderlyings = new ArrayList();
        tm = (DefaultTableModel) jTableUnderlyings2.getModel();
        for (int i = 0; i < tm.getRowCount(); i++) {
            listunderlyings.add(tm.getValueAt(i, 0).toString());
        }

        final PayOff payoff2 = new PayOff(listattrs, listunderlyings);

        if (otc2 != null) {
            payoff2.setPaymentMode(otc2.getPayoffDateClause());
            payoff2.setCondition(otc2.getPayoffConditionClause());
            payoff2.setFormula(otc2.getPayoffFormula());
        }

        NotifyDescriptor nd = new NotifyDescriptor(
                payoff2, /**
                 * instance of your panel
                 */
                "Payoff", /**
                 * title of the dialog
                 */
                NotifyDescriptor.OK_CANCEL_OPTION, /**
                 * it is Yes/No dialog ...
                 */
                NotifyDescriptor.PLAIN_MESSAGE, /**
                 * ... of a question type => a question mark icon
                 */
                null, /**
                 * we have specified YES_NO_OPTION => can be null, options
                 * specified by L&F, otherwise specify options as: new Object[]
                 * { NotifyDescriptor.YES_OPTION, ... etc. },
                 */
                NotifyDescriptor.OK_OPTION /**
         * default option is "Yes"
         */
        );

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            jPayOffPayment1.setText(payoff2.getPaymentMode());
            jPayOffCondition1.setText(payoff2.getCondition());
            jPayOffFormula1.setText(payoff2.getFormula());

        }
        payoff2.setVisible(true);
    }//GEN-LAST:event_jButtonPayOff2ActionPerformed

    /**
     * add credit event leg 2
     */
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        if (jComboEventList6.getSelectedItem() != null) {
            if (jTextEventList6.getText().length() > 0) {
                jTextEventList6.setText(jTextEventList6.getText() + StringUtils.COMMA + jComboEventList6.getSelectedItem().toString());
            } else {
                jTextEventList6.setText(jComboEventList6.getSelectedItem().toString());
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    /**
     * remove credit event leg 2
     */
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed

        int i = jTextEventList6.getText().lastIndexOf(StringUtils.COMMA);
        if (jTextEventList6.getText().length() > 0) {
            if (i > 0) {
                jTextEventList6.setText(jTextEventList6.getText().substring(0, i));
            } else {
                jTextEventList6.setText("");
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    /**
     * jCheckBox Credit Event leg 1
     */
    private void jCheckCreditClause2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckCreditClause2ActionPerformed

        if (jCheckCreditClause2.isSelected()) {
            jPanelCredit2.setVisible(true);
        } else {
            jPanelCredit2.setVisible(false);
        }
    }//GEN-LAST:event_jCheckCreditClause2ActionPerformed

    private void tradeDetailsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tradeDetailsButtonActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_tradeDetailsButtonActionPerformed

    private void legalEntityFinderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_legalEntityFinderButtonActionPerformed
        LegalEntity legalEntity = GUIUtils.findCounterParty();
        if (legalEntity != null) {
            counterpartyTextField.setText(legalEntity.getShortName());
        }
    }//GEN-LAST:event_legalEntityFinderButtonActionPerformed

    private void jComboBoxQuantityNotionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxQuantityNotionalActionPerformed
    }//GEN-LAST:event_jComboBoxQuantityNotionalActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField counterpartyTextField;
    private javax.swing.JButton exportButton;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonAddComponent;
    private javax.swing.JButton jButtonAddField1;
    private javax.swing.JButton jButtonAddField2;
    private javax.swing.JButton jButtonAddUnderlying1;
    private javax.swing.JButton jButtonAddUnderlying2;
    private javax.swing.JButton jButtonFindAsset;
    private javax.swing.JButton jButtonPayOff1;
    private javax.swing.JButton jButtonPayOff2;
    private javax.swing.JButton jButtonRemoveAttributes1;
    private javax.swing.JButton jButtonRemoveAttributes2;
    private javax.swing.JButton jButtonRemoveUnderlying1;
    private javax.swing.JButton jButtonRemoveUnderlying2;
    private javax.swing.JButton jButtonShowAsset;
    private javax.swing.JButton jButtonShowReferences1;
    private javax.swing.JButton jButtonShowReferences2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
    private javax.swing.JCheckBox jCheckBox8;
    private javax.swing.JCheckBox jCheckBoxIsSingleTraded;
    private javax.swing.JCheckBox jCheckBoxScheduler1;
    private javax.swing.JCheckBox jCheckBoxScheduler2;
    private javax.swing.JCheckBox jCheckCreditClause1;
    private javax.swing.JCheckBox jCheckCreditClause2;
    private javax.swing.JComboBox jComboBox10;
    private javax.swing.JComboBox jComboBox11;
    private javax.swing.JComboBox jComboBox12;
    private javax.swing.JComboBox jComboBox13;
    private javax.swing.JComboBox jComboBox8;
    private javax.swing.JComboBox jComboBox9;
    private javax.swing.JComboBox jComboBoxAmountCurrency;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxCurrency1;
    private javax.swing.JComboBox jComboBoxCurrency2;
    private javax.swing.JComboBox jComboBoxInternalCounterparty;
    private javax.swing.JComboBox jComboBoxPriceCurrency;
    private javax.swing.JComboBox jComboBoxProductType;
    private javax.swing.JComboBox jComboBoxProductType1;
    private javax.swing.JComboBox jComboBoxProductType2;
    private javax.swing.JComboBox jComboBoxQuantityNotional;
    private javax.swing.JComboBox jComboEventList1;
    private javax.swing.JComboBox jComboEventList6;
    private javax.swing.JComboBox jComboIssuer1;
    private javax.swing.JComboBox jComboIssuer6;
    private javax.swing.JComboBox jComboPayReceive1;
    private javax.swing.JComboBox jComboPayReceive2;
    private javax.swing.JComboBox jComboSeniority1;
    private javax.swing.JComboBox jComboSeniority6;
    private javax.swing.JTextArea jComment;
    private javax.swing.JFormattedTextField jFormattedTextField10;
    private javax.swing.JFormattedTextField jFormattedTextField11;
    private javax.swing.JFormattedTextField jFormattedTextField12;
    private javax.swing.JFormattedTextField jFormattedTextField13;
    private com.toedter.calendar.JDateChooser jFormattedTextField14;
    private com.toedter.calendar.JDateChooser jFormattedTextField15;
    private com.toedter.calendar.JDateChooser jFormattedTextField8;
    private com.toedter.calendar.JDateChooser jFormattedTextField9;
    private javax.swing.JFormattedTextField jFormattedTextFieldAmount;
    private javax.swing.JFormattedTextField jFormattedTextFieldFXRate;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldMaturity;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldMaturity1;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldMaturity2;
    private javax.swing.JFormattedTextField jFormattedTextFieldNotional1;
    private javax.swing.JFormattedTextField jFormattedTextFieldNotional2;
    private javax.swing.JFormattedTextField jFormattedTextFieldPrice;
    private javax.swing.JFormattedTextField jFormattedTextFieldQuantity;
    private javax.swing.JFormattedTextField jFormattedTextFieldRate;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldSettleDate;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate1;
    private com.toedter.calendar.JDateChooser jFormattedTextFieldStartDate2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelAssetProductType;
    private javax.swing.JLabel jLabelProductId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelAsset;
    private javax.swing.JPanel jPanelCredit1;
    private javax.swing.JPanel jPanelCredit2;
    private javax.swing.JPanel jPanelLeg1;
    private javax.swing.JPanel jPanelLeg2;
    private javax.swing.JPanel jPanelScheduler1;
    private javax.swing.JPanel jPanelScheduler2;
    private javax.swing.JTextField jPayOffCondition1;
    private javax.swing.JTextField jPayOffCondition2;
    private javax.swing.JTextField jPayOffFormula1;
    private javax.swing.JTextField jPayOffFormula2;
    private javax.swing.JTextField jPayOffPayment1;
    private javax.swing.JTextField jPayOffPayment2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private com.toedter.calendar.JDateChooser jSpinnerTradeDate;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTableAttributes1;
    private javax.swing.JTable jTableAttributes2;
    private javax.swing.JTable jTableUnderlyings1;
    private javax.swing.JTable jTableUnderlyings2;
    private javax.swing.JTextField jTextEventList1;
    private javax.swing.JTextField jTextEventList6;
    private javax.swing.JTextField jTextFieldAssetName;
    private javax.swing.JTextField jTextFieldInternalTradeId;
    private javax.swing.JTextField jTextFieldIssuer;
    private javax.swing.JTextField jTextFieldRateIndex;
    private javax.swing.JTextField jTextFieldTradeId;
    private javax.swing.JFormattedTextField jTextFieldTradeTime;
    private javax.swing.JButton legalEntityFinderButton;
    private javax.swing.JButton loadButton;
    private javax.swing.JButton newButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton tradeDetailsButton;
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
