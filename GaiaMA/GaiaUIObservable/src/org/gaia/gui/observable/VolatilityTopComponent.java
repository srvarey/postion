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

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteObservable;
import org.gaia.dao.observables.MarketQuoteUtils;
import org.gaia.dao.observables.VolatilityObservable;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.FrequencyUtil.Tenor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormatWithOptionalDecimals;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.observable.VolatilityTopComponent.FxStrike;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ExcelAdapter;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays Volatility.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//Volatility//EN", autostore = false)
@TopComponent.Description(preferredID = "VolatilityTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.VolatilityTopComponent")
@ActionReference(path = "Menu"+MenuManager.VolatilityTopComponentMenu, position = MenuManager.VolatilityTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_VolatilityAction")
@Messages({"CTL_VolatilityAction=Volatility", "CTL_VolatilityTopComponent=Volatilities", "HINT_VolatilityTopComponent=This is a Volatility window"})
public final class VolatilityTopComponent extends GaiaTopComponent {

    Product product;
    ArrayList<ProductTypeUtil.ProductType> listVolatilityTypes;
    AssetFinder assetFinder;
    VolatilityPointPanel volatilityPointPanel;
    ArrayList<Product> underlyings = null;
    Product globalUnderlying = null;
    ArrayList<Product> removedUnderlyings = new ArrayList();
    VolatilityObservable volatility;
    List<Tenor> tenors = new ArrayList();
    List<FxStrike> strikes = new ArrayList();
    List<FxStratStrike> stratSrikes = new ArrayList();
    private static final Logger logger = Logger.getLogger(VolatilityTopComponent.class);
    List<MarketQuote> marketQuotes;
    List<MarketQuote> equivalentStratMarketQuotes;
    public static final BigDecimal hundred = NumberUtils.BIGDECIMAL_100;
    public static final BigDecimal half = new BigDecimal(".5");
    private JFXPanel chartFxPanel;
    private JPanel panel;
    private static final int PANEL_WIDTH_INT = 675;
    private static final int PANEL_HEIGHT_INT = 400;
    private Date startDate;

    public class FxStrike {

        public String name;
        public BigDecimal internalStrikeInPctDelta;
        public BigDecimal realStrikeInPctDelta;
        public FxStratStrike relatedButterflyStrike;
        public FxStratStrike relatedRiskReversalStrike;

        public FxStrike(BigDecimal percents) {

            this.internalStrikeInPctDelta = percents;
            if (percents.doubleValue() < .5) {
                this.realStrikeInPctDelta = percents;
            } else {
                this.realStrikeInPctDelta = BigDecimal.ONE.subtract(percents);
            }
            if (percents.doubleValue() == half.doubleValue()) {
                this.name = FxStratStrikeType.ATM.name();
            } else if (percents.doubleValue() > half.doubleValue()) {
                this.name = decimalFormatWithOptionalDecimals.format(realStrikeInPctDelta.multiply(hundred)) + "P";
            } else {
                this.name = decimalFormatWithOptionalDecimals.format(realStrikeInPctDelta.multiply(hundred)) + "C";
            }
            this.relatedButterflyStrike = new FxStratStrike(realStrikeInPctDelta, FxStratStrikeType.BF.name());
            this.relatedRiskReversalStrike = new FxStratStrike(realStrikeInPctDelta, FxStratStrikeType.RR.name());
        }

        public FxStrike(String name) {
            this.name = name;
            if (name.equalsIgnoreCase(FxStratStrikeType.ATM.name())) {
                this.internalStrikeInPctDelta = half;
                this.realStrikeInPctDelta = half;
            } else {
                this.realStrikeInPctDelta = (new BigDecimal(name.substring(0, name.length() - 1))).divide(hundred);
                if (name.endsWith("C")) {
                    this.internalStrikeInPctDelta = realStrikeInPctDelta;
                } else {
                    this.internalStrikeInPctDelta = BigDecimal.ONE.subtract(realStrikeInPctDelta);
                }
            }
            this.relatedButterflyStrike = new FxStratStrike(realStrikeInPctDelta, FxStratStrikeType.BF.name());
            this.relatedRiskReversalStrike = new FxStratStrike(realStrikeInPctDelta, FxStratStrikeType.RR.name());
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object object) {

            if (!(object instanceof FxStrike)) {
                return false;
            }
            FxStrike other = (FxStrike) object;
            if ((this.name == null && other.name != null) || (this.name != null && !this.name.equalsIgnoreCase(other.name))) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 71 * hash + Objects.hashCode(this.name);
            return hash;
        }
    };

    public enum FxStratStrikeType {
        BF, RR, ATM
    };

    public class FxStratStrike {
//        ATM("ATM", new BigDecimal(".5")), TwentyFiveDRR("25DRR", new BigDecimal(".45")), TwentyFiveDBF("25DBF", new BigDecimal(".4")), TenDRR("10DRR", new BigDecimal(".35")), TenDBF("10DBF", new BigDecimal(".3"));
        public String name;
        public BigDecimal internalStrikeInPctDelta;
        public BigDecimal realStrikeInPctDelta;
        public String type;

        public FxStratStrike(BigDecimal internalStrikeInPctDelta) {
            this.internalStrikeInPctDelta = internalStrikeInPctDelta;
            if (internalStrikeInPctDelta.doubleValue() == .5) {
                type = FxStratStrikeType.ATM.name();
                realStrikeInPctDelta = internalStrikeInPctDelta;
            } else if (internalStrikeInPctDelta.doubleValue() < .5) {
                // BF
                type = FxStratStrikeType.BF.name();
                realStrikeInPctDelta = internalStrikeInPctDelta;
            } else if (internalStrikeInPctDelta.doubleValue() > .5) {
                //RR
                type = FxStratStrikeType.RR.name();
                realStrikeInPctDelta = BigDecimal.ONE.subtract(internalStrikeInPctDelta);
            }
            if (realStrikeInPctDelta.doubleValue() == .5) {
                this.name = FxStratStrikeType.ATM.name();
            } else {
                this.name = decimalFormatWithOptionalDecimals.format(realStrikeInPctDelta.multiply(hundred)).toString() + type;

            }
        }

        public FxStratStrike(String name) {
            this.name = name;
            if (this.name.equalsIgnoreCase(FxStratStrikeType.ATM.name())) {
                type = FxStratStrikeType.ATM.name();
                realStrikeInPctDelta = half;
                internalStrikeInPctDelta = half;
            } else {
                type = name.substring(name.length() - 2);
                realStrikeInPctDelta = new BigDecimal(name.substring(0, name.length() - 2)).divide(hundred);
                if (type.equalsIgnoreCase(FxStratStrikeType.BF.name())) {
                    this.internalStrikeInPctDelta = realStrikeInPctDelta;
                } else if (type.equalsIgnoreCase(FxStratStrikeType.RR.name())) {
                    this.internalStrikeInPctDelta = BigDecimal.ONE.subtract(realStrikeInPctDelta);
                }
            }
        }

        public FxStratStrike(BigDecimal realStrikeInPctDelta, String type) {
            if (realStrikeInPctDelta.doubleValue() == .5) {
                type = FxStratStrikeType.ATM.name();
            }
            this.type = type;
            this.realStrikeInPctDelta = realStrikeInPctDelta;
            if (type.equalsIgnoreCase(FxStratStrikeType.BF.name())) {
                this.internalStrikeInPctDelta = realStrikeInPctDelta;
                this.name = decimalFormatWithOptionalDecimals.format(realStrikeInPctDelta.multiply(hundred)) + type;
            } else if (type.equalsIgnoreCase(FxStratStrikeType.RR.name())) {
                this.internalStrikeInPctDelta = BigDecimal.ONE.subtract(realStrikeInPctDelta);
                this.name = decimalFormatWithOptionalDecimals.format(realStrikeInPctDelta.multiply(hundred)) + type;
            } else {
                this.internalStrikeInPctDelta = realStrikeInPctDelta;
                this.name = FxStratStrikeType.ATM.name();
            }
        }

        public boolean isBF() {
            return type.equalsIgnoreCase(FxStratStrikeType.BF.name());
        }

        public boolean isRR() {
            return type.equalsIgnoreCase(FxStratStrikeType.RR.name());
        }

        public boolean isATM() {
            return type.equalsIgnoreCase(FxStratStrikeType.ATM.name());
        }

        @Override
        public String toString() {
            return name;
        }

        @Override
        public boolean equals(Object object) {

            if (!(object instanceof FxStratStrike)) {
                return false;
            }
            FxStratStrike other = (FxStratStrike) object;
            if ((this.name == null && other.name != null) || (this.name != null && !this.name.equalsIgnoreCase(other.name))) {
                return false;
            }
            return true;
        }

        @Override
        public int hashCode() {
            int hash = 7;
            hash = 83 * hash + Objects.hashCode(this.name);
            return hash;
        }
    }

    public enum quoteMode {

        pctDelta("%Delta"), BFRR("BF/RR");
        public String name;

        quoteMode(String name) {
            this.name = name;
        }
    }

    public class FXStrikeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof FxStrike && o2 instanceof FxStrike) {
                FxStrike s1 = (FxStrike) o1;
                FxStrike s2 = (FxStrike) o2;
                return s1.internalStrikeInPctDelta.subtract(s2.internalStrikeInPctDelta).multiply(NumberUtils.BIGDECIMAL_100).intValue();
            }
            return 0;
        }
    }

    public class FXStratStrikeComparator implements Comparator {

        @Override
        public int compare(Object o1, Object o2) {
            if (o1 instanceof FxStratStrike && o2 instanceof FxStratStrike) {
                FxStratStrike s1 = (FxStratStrike) o1;
                FxStratStrike s2 = (FxStratStrike) o2;

                return s2.realStrikeInPctDelta.subtract(s1.realStrikeInPctDelta).multiply(NumberUtils.BIGDECIMAL_100).intValue();
            }
            return 0;
        }
    }

    public VolatilityTopComponent() {
        initComponents();
        setName(Bundle.CTL_VolatilityTopComponent());
        setToolTipText(Bundle.HINT_VolatilityTopComponent());
        listVolatilityTypes = new ArrayList();
        listVolatilityTypes.add(ProductTypeUtil.ProductType.FX_VOLATILITY);
        quoteModeJComboBox.addItem(quoteMode.pctDelta.name);
        quoteModeJComboBox.addItem(quoteMode.BFRR.name);
        putClientProperty(TopComponent.PROP_UNDOCKING_DISABLED, Boolean.TRUE);
    }

    @Override
    public void initContext() {
        clear();
        jDateChooserCurveDate.setDate(DateUtils.getDate());
        try {
            ExcelAdapter myAd = new ExcelAdapter(jTableQuotes);
            jComboBoxCurveType.removeAllItems();
            for (ProductTypeUtil.ProductType productTypes : listVolatilityTypes) {
                jComboBoxCurveType.addItem(productTypes.name);
            }
            List<String> itemList = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_QUOTE_SETS);
            GUIUtils.fillCombo(jComboBoxQuoteSet, itemList);
            itemList = (List) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.LOAD_CALENDAR_CODES);
            GUIUtils.fillCombo(jComboBoxCalendar, itemList);
            itemList = FrequencyUtil.getTenors();
            GUIUtils.fillCombo(jComboBoxTenor, itemList);
            /**
             * ShortCut for date
             */
            DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserCurveDate.getComponent(1));
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane = new javax.swing.JTabbedPane();
        jPanelDefinition = new javax.swing.JPanel();
        jLabelQuoteType = new javax.swing.JLabel();
        jComboBoxQuoteSet = new javax.swing.JComboBox();
        jLabelCurveName = new javax.swing.JLabel();
        jTextFieldCurveName = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jLabelId = new javax.swing.JLabel();
        jLabelType = new javax.swing.JLabel();
        jComboBoxCurveType = new javax.swing.JComboBox();
        jComboBoxCurveDate = new javax.swing.JComboBox();
        jComboBoxCalendar = new javax.swing.JComboBox();
        jLabelCalendar = new javax.swing.JLabel();
        jLabelUnderlying = new javax.swing.JLabel();
        jTextFieldUnderlying = new javax.swing.JTextField();
        jLabelUnderlyingId = new javax.swing.JLabel();
        jButtonSearchUnderlying = new javax.swing.JButton();
        jPanelMarketQuote = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableQuotes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>0){
                    return true;
                } else {
                    return false;
                }
            }
        };
        jLabelQuoteDate = new javax.swing.JLabel();
        jDateChooserCurveDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jLabelTenor = new javax.swing.JLabel();
        jComboBoxTenor = new javax.swing.JComboBox();
        addTenorjButton = new javax.swing.JButton();
        removeTenorJButton = new javax.swing.JButton();
        strikeLabel = new javax.swing.JLabel();
        addStrikeJButton = new javax.swing.JButton();
        removeStrikeJButton = new javax.swing.JButton();
        quoteModeJComboBox = new javax.swing.JComboBox();
        strikeTextField = new javax.swing.JTextField();
        RTCheckBox = new javax.swing.JCheckBox();
        gaiaRTLabel = new org.gaia.gui.common.GaiaRTLabel();
        deltaLabel = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTabbedPane.setToolTipText(org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jTabbedPane.toolTipText")); // NOI18N
        jTabbedPane.setFocusTraversalPolicyProvider(true);

        jPanelDefinition.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelQuoteType, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelQuoteType.text")); // NOI18N

        jComboBoxQuoteSet.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuoteSet.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCurveName, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelCurveName.text")); // NOI18N

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelType, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelType.text")); // NOI18N

        jComboBoxCurveType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurveType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jComboBoxCurveDate.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurveDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurveDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurveDateActionPerformed(evt);
            }
        });

        jComboBoxCalendar.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCalendar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCalendar, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelCalendar.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlying, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelUnderlying.text")); // NOI18N

        jTextFieldUnderlying.setText(org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jTextFieldUnderlying.text")); // NOI18N
        jTextFieldUnderlying.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlyingId, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelUnderlyingId.text")); // NOI18N

        jButtonSearchUnderlying.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearchUnderlying, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jButtonSearchUnderlying.text")); // NOI18N
        jButtonSearchUnderlying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchUnderlyingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDefinitionLayout = new javax.swing.GroupLayout(jPanelDefinition);
        jPanelDefinition.setLayout(jPanelDefinitionLayout);
        jPanelDefinitionLayout.setHorizontalGroup(
            jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                        .addComponent(jLabelQuoteType)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabelType)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxCurveType, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelCalendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelCurveName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                .addComponent(jTextFieldCurveName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)
                                .addComponent(jButtonFind)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboBoxCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jLabelUnderlying)
                                .addGap(18, 18, 18)
                                .addComponent(jTextFieldUnderlying)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSearchUnderlying)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                        .addContainerGap(229, Short.MAX_VALUE))))
        );
        jPanelDefinitionLayout.setVerticalGroup(
            jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelQuoteType)
                        .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelType)
                        .addComponent(jComboBoxCurveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurveName)
                    .addComponent(jTextFieldCurveName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFind)
                    .addComponent(jComboBoxCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCalendar)
                    .addComponent(jLabelUnderlying)
                    .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearchUnderlying))
                .addContainerGap(389, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jPanelDefinition.TabConstraints.tabTitle"), jPanelDefinition); // NOI18N

        jPanelMarketQuote.setBackground(new java.awt.Color(254, 252, 254));

        jTableQuotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {"Tenor"
            }
        ));
        jScrollPane1.setViewportView(jTableQuotes);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelQuoteDate, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelQuoteDate.text")); // NOI18N

        jDateChooserCurveDate.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTenor, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jLabelTenor.text")); // NOI18N

        jComboBoxTenor.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxTenor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        addTenorjButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(addTenorjButton, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.addTenorjButton.text")); // NOI18N
        addTenorjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addTenorjButtonActionPerformed(evt);
            }
        });

        removeTenorJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(removeTenorJButton, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.removeTenorJButton.text")); // NOI18N
        removeTenorJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeTenorJButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(strikeLabel, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.strikeLabel.text")); // NOI18N

        addStrikeJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(addStrikeJButton, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.addStrikeJButton.text")); // NOI18N
        addStrikeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStrikeJButtonActionPerformed(evt);
            }
        });

        removeStrikeJButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(removeStrikeJButton, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.removeStrikeJButton.text")); // NOI18N
        removeStrikeJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeStrikeJButtonActionPerformed(evt);
            }
        });

        quoteModeJComboBox.setBackground(new java.awt.Color(255, 255, 255));
        quoteModeJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        quoteModeJComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quoteModeJComboBoxActionPerformed(evt);
            }
        });
        quoteModeJComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                quoteModeJComboBoxPropertyChange(evt);
            }
        });

        strikeTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        strikeTextField.setText(org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.strikeTextField.text")); // NOI18N
        strikeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                strikeTextFieldActionPerformed(evt);
            }
        });

        RTCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(RTCheckBox, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.RTCheckBox.text")); // NOI18N
        RTCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RTCheckBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(gaiaRTLabel, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.gaiaRTLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(deltaLabel, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.deltaLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanelMarketQuoteLayout = new javax.swing.GroupLayout(jPanelMarketQuote);
        jPanelMarketQuote.setLayout(jPanelMarketQuoteLayout);
        jPanelMarketQuoteLayout.setHorizontalGroup(
            jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                        .addComponent(jLabelQuoteDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quoteModeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 147, Short.MAX_VALUE)
                        .addComponent(RTCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gaiaRTLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jLabelTenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addTenorjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeTenorJButton)
                        .addGap(18, 18, 18)
                        .addComponent(strikeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(strikeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deltaLabel)
                        .addGap(6, 6, 6)
                        .addComponent(addStrikeJButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeStrikeJButton)))
                .addContainerGap())
        );
        jPanelMarketQuoteLayout.setVerticalGroup(
            jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateChooserCurveDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQuoteDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addTenorjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(removeTenorJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTenor))
                    .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addStrikeJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(deltaLabel))
                    .addComponent(removeStrikeJButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quoteModeJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gaiaRTLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(strikeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(strikeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(RTCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jPanelMarketQuote.TabConstraints.tabTitle"), jPanelMarketQuote); // NOI18N

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(VolatilityTopComponent.class, "VolatilityTopComponent.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))
                    .addComponent(jTabbedPane))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonNew)
                    .addComponent(jButton1))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * find surface
     *
     * @param evt
     */
    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed

        jComboBoxCurveDate.removeAllItems();

        assetFinder = new AssetFinder(listVolatilityTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        /**
         * display the dialog
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();

            if (productId != null) {
                try {
                    clear();
                    volatility = new VolatilityObservable(AbstractObservable.ObservableType.FX_VOLATILITY, AbstractObservable.VOLATILITY);
                    product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, 
                            ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());
                    jLabelId.setText(productId.toString());
                    jTextFieldCurveName.setText(product.getShortName());
                    jComboBoxCurveType.setSelectedItem(product.getProductType());

                    underlyings = new ArrayList();
                    underlyings.addAll(product.loadUnderlyings());
                    if (!underlyings.isEmpty()) {
                        if (underlyings.get(0).loadSingleUnderlying() != null) {
                            globalUnderlying = underlyings.get(0).loadSingleUnderlying();
                            jTextFieldUnderlying.setText(globalUnderlying.getShortName());
                            jLabelUnderlyingId.setText(globalUnderlying.getProductId().toString());
                        }
                    }
                    List<Date> dates = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_CURVES_DATES,
                            productId, jComboBoxQuoteSet.getSelectedItem().toString());
                    for (Date date : dates) {
                        jComboBoxCurveDate.addItem(dateFormatter.format(date));
                    }
                    removedUnderlyings = new ArrayList();
                    loadAndDisplayAll();
                    updateGaiaRTLabel();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonFindActionPerformed

    /**
     * load volatility when select date
     *
     * @param evt
     */
    private void jComboBoxCurveDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurveDateActionPerformed
        if (startDate!=null && !startDate.equals(GUIUtils.getComponentDateValue(jComboBoxCurveDate))){
            loadAndDisplayAll();
        }
    }

    public void loadAndDisplayAll() {
        try {
            loadMarketQuotes();
            displayVolatilities();
            drawSurface();
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }//GEN-LAST:event_jComboBoxCurveDateActionPerformed

    public void loadMarketQuotes() {
        tenors = new ArrayList();
        strikes = new ArrayList();
        try {
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
            tableModel.getDataVector().clear();

            if (jComboBoxCurveDate.getSelectedItem() != null && !jLabelId.getText().isEmpty()) {
                startDate = dateFormatter.parse(jComboBoxCurveDate.getSelectedItem().toString());
                jDateChooserCurveDate.setDate(startDate);

                marketQuotes = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, 
                        MarketQuoteAccessor.GET_CURVE_BY_DATES, product, jComboBoxQuoteSet.getSelectedItem().toString(), startDate);

                /**
                 * set end date to sort by dates
                 */
                for (MarketQuote marketQuote : marketQuotes) {
                    if (!underlyings.contains(marketQuote.getProduct())) {
                        underlyings.add(marketQuote.getProduct());
                    }
                }
            }

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void buildEquivalentStratMarketQuotes() {
        equivalentStratMarketQuotes = new ArrayList();
        Map<String, BigDecimal> atms = new HashMap();
        for (MarketQuote marketQuote : marketQuotes) {
            String tenor = marketQuote.getProduct().getProductCurve().getTenor();
            FxStrike strike = new FxStrike(marketQuote.getProduct().getProductCurve().getStrike());
            if (strike.internalStrikeInPctDelta.doubleValue() == .5) {
                atms.put(tenor, marketQuote.getClose());
            }
        }
        for (MarketQuote marketQuote : marketQuotes) {
            String tenor = marketQuote.getProduct().getProductCurve().getTenor();
            FxStrike strike = new FxStrike(marketQuote.getProduct().getProductCurve().getStrike());
            boolean found = false;
            for (MarketQuote eqQuote : equivalentStratMarketQuotes) {
                if (eqQuote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(tenor)) {
                    if (eqQuote.getProduct().getProductCurve().getStrike().doubleValue() == strike.relatedButterflyStrike.internalStrikeInPctDelta.doubleValue()) {

                        found = true;
                        BigDecimal quote = eqQuote.getClose();
                        BigDecimal stratQuote = null;

                        BigDecimal atm = atms.get(tenor);
                        stratQuote = quote.add(marketQuote.getClose()).subtract(atm).subtract(atm);
                        stratQuote = stratQuote.divide(new BigDecimal("2"), 20, RoundingMode.UP);
                        eqQuote.setClose(stratQuote);
                    } else if (eqQuote.getProduct().getProductCurve().getStrike().doubleValue() == strike.relatedRiskReversalStrike.internalStrikeInPctDelta.doubleValue()) {
                        found = true;
                        BigDecimal quote = eqQuote.getClose();
                        BigDecimal stratQuote = null;
                        if (marketQuote.getProduct().getProductCurve().getStrike().doubleValue() > .5) {
                            stratQuote = quote.subtract(marketQuote.getClose());
                        } else if (marketQuote.getProduct().getProductCurve().getStrike().doubleValue() < .5) {
                            stratQuote = marketQuote.getClose().subtract(quote);
                        }
                        eqQuote.setClose(stratQuote);
                    }
                }
            }
            if (!found) {

                MarketQuote BFQuote = marketQuote.clone();
                Product product_ = new Product();
                ProductCurve curve = new ProductCurve();
                curve.setTenor(marketQuote.getProduct().getProductCurve().getTenor());
                curve.setStrike(strike.relatedButterflyStrike.internalStrikeInPctDelta);
                curve.setProduct(product_);
                product_.setProductCurve(curve);
                BFQuote.setProduct(product_);
                equivalentStratMarketQuotes.add(BFQuote);

                MarketQuote RRQuote = marketQuote.clone();
                product_ = new Product();
                curve = new ProductCurve();
                curve.setTenor(marketQuote.getProduct().getProductCurve().getTenor());
                curve.setStrike(strike.relatedRiskReversalStrike.internalStrikeInPctDelta);
                curve.setProduct(product_);
                product_.setProductCurve(curve);
                RRQuote.setProduct(product_);
                equivalentStratMarketQuotes.add(RRQuote);
            }
        }
    }

    public void displayVolatilities() {

        try {
            boolean isInStratVol = false;
            if (quoteModeJComboBox.getSelectedItem() != null && quoteModeJComboBox.getSelectedItem().toString().equalsIgnoreCase(quoteMode.BFRR.name)) {
                isInStratVol = true;
            }
            if (underlyings == null) {
                underlyings = new ArrayList();
            }
            for (Product underlying
                    : underlyings) {
                if (underlying.getProductCurve() != null) {
                    if (underlying.getProductCurve() != null) {
                        if (!tenors.contains(Tenor.getTenor(underlying.getProductCurve().getTenor()))) {
                            tenors.add(Tenor.getTenor(underlying.getProductCurve().getTenor()));
                        }
                        if (!strikes.contains(new FxStrike(underlying.getProductCurve().getStrike()))) {
                            strikes.add(new FxStrike(underlying.getProductCurve().getStrike()));
                        }
                    }
                }
            }


            // sort lists
            Collections.sort(tenors);
            Collections.sort(strikes, new FXStrikeComparator());
            updatesStratSrikes(strikes);
            Collections.sort(stratSrikes, new FXStratStrikeComparator());

            //build header
            List<String> headings = new ArrayList();
            headings.add("tenor");
            if (isInStratVol) {
                for (FxStratStrike strike : stratSrikes) {
                    headings.add(strike.name);
                }
                buildEquivalentStratMarketQuotes();
            } else {
                for (FxStrike strike : strikes) {
                    headings.add(strike.name);
                }
            }

            BigDecimal mult = BigDecimal.ONE;
            if (underlyings.size() > 0) {
                MarketQuote.QuotationType type = MarketQuoteUtils.getQuotationTypeByName(underlyings.get(0).getQuotationType());
                mult = type.getMult();
            }
            Object[][] data = null;
            if (marketQuotes != null) {

                if (isInStratVol) {
                    data = new Object[tenors.size()][stratSrikes.size() + 1];
                    for (MarketQuote marketQuote : equivalentStratMarketQuotes) {
                        int y = stratSrikes.indexOf(new FxStratStrike(marketQuote.getProduct().getProductCurve().getStrike())) + 1;
                        int x = tenors.indexOf(Tenor.getTenor(marketQuote.getProduct().getProductCurve().getTenor()));
                        data[x][y] = decimalFormat.format(marketQuote.getClose().multiply(mult));
                    }
                } else {
                    data = new Object[tenors.size()][strikes.size() + 1];
                    for (MarketQuote marketQuote : marketQuotes) {
                        int y = strikes.indexOf(new FxStrike(marketQuote.getProduct().getProductCurve().getStrike())) + 1;
                        int x = tenors.indexOf(Tenor.getTenor(marketQuote.getProduct().getProductCurve().getTenor()));
                        data[x][y] = decimalFormat.format(marketQuote.getClose().multiply(mult));
                    }
                }
                for (Tenor tenor : tenors) {
                    data[tenors.indexOf(tenor)][0] = tenor.name;
                }
            }

            DefaultTableModel model = new DefaultTableModel(data, headings.toArray());
            jTableQuotes.setModel(model);

            for (int column = 1; column < jTableQuotes.getColumnCount(); column++) {
                GUIUtils.setNumberEditor(jTableQuotes, column, "0.00");
                GUIUtils.packColumn(jTableQuotes, column, 2);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }

    public void updatesStratSrikes(List<FxStrike> strikes) {
        stratSrikes = new ArrayList();
        for (FxStrike strike : strikes) {
            if (!stratSrikes.contains(strike.relatedButterflyStrike)) {
                stratSrikes.add(strike.relatedButterflyStrike);
            }
            if (!stratSrikes.contains(strike.relatedRiskReversalStrike)) {
                stratSrikes.add(strike.relatedRiskReversalStrike);
            }
        }
    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        clear();
    }//GEN-LAST:event_jButtonNewActionPerformed

    public void clear() {

        jTextFieldCurveName.setText(StringUtils.EMPTY_STRING);
        product = null;
        underlyings = null;
        removedUnderlyings = new ArrayList();
        volatility = null;
        globalUnderlying = null;

        jLabelId.setText(StringUtils.EMPTY_STRING);
    }

    public Product createUnderlying(String tenor, BigDecimal strike) {

        if (globalUnderlying == null) {
            JOptionPane.showMessageDialog(this, "Please choose an underlying");
        }

        Product underlying = new Product();
        if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.FX_VOLATILITY.name)) {
            underlying.setProductType(ProductTypeUtil.ProductType.FX_VOLATILITY_POINT.name);
            underlying.setQuotationType(MarketQuote.QuotationType.VOLATILITY.getName());
        }
        ProductCurve productCurve = new ProductCurve();
        productCurve.setTenor(tenor);
        FxStrike str = new FxStrike(strike);
        productCurve.setStrike(str.internalStrikeInPctDelta);
        productCurve.setProduct(underlying);
        underlying.setProductCurve(productCurve);
        if (globalUnderlying != null) {
            underlying.setNotionalCurrency(globalUnderlying.getNotionalCurrency());
            underlying.addUnderlying(globalUnderlying);
        }
        ArrayList<Product> parents = new ArrayList();
        parents.add(product);
        underlying.setParentProducts(parents);
        return underlying;
    }

    public void fillQuotesFromStratQuotes() {

        equivalentStratMarketQuotes = new ArrayList();

        // creates / updates volatility quotes
        DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();

        BigDecimal mult = NumberUtils.BIGDECIMAL_100;
        if (underlyings==null){
            return;
        } else if (underlyings.size() > 0) {
            MarketQuote.QuotationType type = MarketQuoteUtils.getQuotationTypeByName(underlyings.get(0).getQuotationType());
            mult = type.getMult();
        }

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 1; j < tableModel.getColumnCount(); j++) {
                try {
                    String sValue = GUIUtils.getTableValueAt(jTableQuotes, i, j);
                    if (sValue != null && !sValue.isEmpty()) {
                        String tenor = GUIUtils.getTableValueAt(jTableQuotes, i, 0);
                        String sStrike = tableModel.getColumnName(j);
                        BigDecimal stratQuoteValue = new BigDecimal(Double.parseDouble(sValue));
                        MarketQuote stratQuote = new MarketQuote();
                        Product product_ = new Product();
                        ProductCurve curve = new ProductCurve();
                        product_.setProductCurve(curve);
                        curve.setProduct(product_);
                        curve.setTenor(tenor);
                        FxStratStrike strike = new FxStratStrike(sStrike);
                        curve.setStrike(strike.internalStrikeInPctDelta);
                        stratQuote.setProduct(product_);
                        stratQuote.setClose(stratQuoteValue.divide(mult));
                        equivalentStratMarketQuotes.add(stratQuote);
                    }
                } catch (Exception e) {
                    logger.error("Error " + StringUtils.formatErrorMessage(e));
                }
            }
        }
        BigDecimal atm;
        BigDecimal rr;
        BigDecimal bf;
        for (MarketQuote quote : marketQuotes) {
            atm = null;
            rr = null;
            bf = null;
            for (MarketQuote stratQuote : equivalentStratMarketQuotes) {
                if (quote.getProduct().getProductCurve() != null
                        && quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(stratQuote.getProduct().getProductCurve().getTenor())) {
                    if (stratQuote.getProduct().getProductCurve().getStrike().doubleValue() == .5) {
                        atm = stratQuote.getClose();
                    } else {
                        FxStrike strike = new FxStrike(quote.getProduct().getProductCurve().getStrike());
                        if (strike.relatedRiskReversalStrike.internalStrikeInPctDelta.doubleValue() == stratQuote.getProduct().getProductCurve().getStrike().doubleValue()) {
                            rr = stratQuote.getClose();
                        } else if (strike.relatedButterflyStrike.internalStrikeInPctDelta.doubleValue() == stratQuote.getProduct().getProductCurve().getStrike().doubleValue()) {
                            bf = stratQuote.getClose();
                        }
                    }
                }
            }
            if (atm != null) {
                if (quote.getProduct().getProductCurve() != null
                        && quote.getProduct().getProductCurve().getStrike().doubleValue() == .5) {
                    quote.setClose(atm);
                } else if (rr != null && bf != null) {
                    rr = rr.divide(new BigDecimal("2"), 20, RoundingMode.UP);
                    if (quote.getProduct().getProductCurve().getStrike().doubleValue() > .5) {
                        quote.setClose(atm.add(bf).subtract(rr));
                    } else {
                        quote.setClose(atm.add(bf).add(rr));
                    }
                }

            }
        }
    }

    public void fillQuotesFromDeltaQuotes() {
        Date valdate;
        BigDecimal mid;
        String quotationtype;
        Integer productid;
        String quoteset = null;
        if (jComboBoxQuoteSet.getSelectedItem() != null) {
            quoteset = jComboBoxQuoteSet.getSelectedItem().toString();
        }
        marketQuotes = new ArrayList();

        // creates / updates volatility quotes
        DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();

        for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 1; j < tableModel.getColumnCount(); j++) {
                mid = null;
                try {
                    Product underlying = null;
                    FxStrike strike = new FxStrike(tableModel.getColumnName(j));
                    String tenor = GUIUtils.getTableValueAt(jTableQuotes, i, 0);
                    for (Product und : underlyings) {
                        if (und.getProductCurve().getTenor().equalsIgnoreCase(tenor)
                                && new FxStrike(und.getProductCurve().getStrike()).name.equalsIgnoreCase(strike.name)) {
                            underlying = und;
                        }
                    }
                    if (underlying == null) {
                        underlying = createUnderlying(tenor, strike.internalStrikeInPctDelta);
                        if (underlyings == null) {
                            underlyings = new ArrayList();
                        }
                        underlyings.add(underlying);
                    }
                    valdate = jDateChooserCurveDate.getDate();
                    quotationtype = underlying.getQuotationType();

                    if (!GUIUtils.getTableValueAt(jTableQuotes, i, j).isEmpty()) {
                        mid = new BigDecimal(Double.parseDouble(GUIUtils.getTableValueAt(jTableQuotes, i, j)));
                    }
                    if (mid != null) {
                        productid = underlying.getId();
                        MarketQuote marketQuote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_PRODUCT_QUOTE, productid, valdate, quoteset);
                        if (marketQuote == null) {
                            marketQuote = new MarketQuote();
                            marketQuote.setProduct(underlying);
                            marketQuote.setQuoteSet(quoteset);
                            marketQuote.setValuationDate(valdate);
                        }
                        marketQuote.setQuotationType(quotationtype);
                        MarketQuote.QuotationType type = MarketQuoteUtils.getQuotationTypeByName(underlying.getQuotationType());
                        BigDecimal mult = type.getMult();
                        marketQuote.setClose(mid.divide(mult));
                        marketQuotes.add(marketQuote);
                    }

                } catch (Exception e) {
                    logger.error("Error " + StringUtils.formatErrorMessage(e));
                }
            }
        }
    }

    public void fillQuotes() {
        boolean isInStratVol = false;
        if (quoteModeJComboBox.getSelectedItem() != null && quoteModeJComboBox.getSelectedItem().toString().equalsIgnoreCase(quoteMode.BFRR.name)) {
            isInStratVol = true;
        }
        if (isInStratVol) {
            fillQuotesFromStratQuotes();
        } else {
            fillQuotesFromDeltaQuotes();
        }
    }

    public void drawSurface() {

        jTabbedPane.remove(panel);
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Platform.setImplicitExit(false);

        chartFxPanel = new JFXPanel();
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));
        /**
         * create JavaFX scene
         */
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                chartFxPanel = new JFXPanel();
                double mult = 100.;
                Double xmin = Double.MAX_VALUE;
                Double xmax = 0.;
                Double zmin = Double.MAX_VALUE;
                Double zmax = 0.;
                for (Product product_ : underlyings) {
                    if (product_.getProductCurve().getStrike().doubleValue() > xmax) {
                        xmax = product_.getProductCurve().getStrike().doubleValue();
                    } else if (product_.getProductCurve().getStrike().doubleValue() < xmin) {
                        xmin = product_.getProductCurve().getStrike().doubleValue();
                    }
                }
                List<XYChart.Series> series = new ArrayList();
                XYChart.Series<Number, Number> serie;
                for (Tenor tenor : tenors) {
                    serie = new XYChart.Series<>();
                    serie.setName(tenor.name);
                    series.add( serie);

                }
                for (MarketQuote quote : marketQuotes) {
                    if (quote.getClose().doubleValue() * mult > zmax) {
                        zmax = quote.getClose().doubleValue() * mult;
                    } else if (quote.getClose().doubleValue() * mult < zmin) {
                        zmin = quote.getClose().doubleValue() * mult;
                    }
                }


                for (XYChart.Series serie_ : series) {
                    for (FxStrike strike : strikes) {
                        Double vol = null;
                        for (MarketQuote quote : marketQuotes) {
                            if (quote.getProduct().getProductCurve().getStrike().doubleValue() == strike.internalStrikeInPctDelta.doubleValue()
                                    && quote.getProduct().getProductCurve().getTenor().equalsIgnoreCase(serie_.getName())) {
                                vol = quote.getClose().doubleValue() * mult;
                            }
                        }
                        if (vol != null && serie_ != null) {
                            serie_.getData().add(new XYChart.Data(strike.internalStrikeInPctDelta, vol));
                        }
                    }
                }
                NumberAxis xAxis = new NumberAxis(xmin, xmax, 5);
                NumberAxis yAxis = new NumberAxis(zmin, zmax, 1);
                xAxis.setMinorTickCount(2);
                LineChart<Number, Number> areaChart = null;
                areaChart = new LineChart<>(xAxis, yAxis);
                areaChart.setTitle("Volatility Surface");

                for (XYChart.Series serie_ : series) {
                    areaChart.getData().add(serie_);
                }
                /**
                 * create javafx panel for browser
                 */
                chartFxPanel.setScene(new Scene(areaChart, 800, 600));
                chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));

                JPanel chartTablePanel = new JPanel();
                chartTablePanel.setLayout(new BorderLayout());
                chartTablePanel.add(chartFxPanel, BorderLayout.CENTER);

                panel.add(chartTablePanel, BorderLayout.CENTER);
                add(panel, BorderLayout.CENTER);
                jTabbedPane.add("Surface", panel);
                panel.requestFocus();
                panel.requestFocusInWindow();
            }
        });
    }

    /**
     * save
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        fillQuotes();

        for (MarketQuote quote : marketQuotes) {

            BigDecimal strike = quote.getProduct().getProductCurve().getStrike();
            String tenor = quote.getProduct().getProductCurve().getTenor();
            if (quote.getProduct().getProductId() == null) {
                quote.setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, quote.getProduct()));
                int i = 0;
                int index = -1;
                for (Product und : underlyings) {
                    if (und.getProductCurve().getTenor().equalsIgnoreCase(tenor)
                            && und.getProductCurve().getStrike().doubleValue() == strike.doubleValue()) {
                        index = i;
                    }
                    i++;
                }
                underlyings.set(index, quote.getProduct());
            }
            DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.STORE_QUOTE, quote);
        }

        // updates the product
        try {
            if (product == null) {
                product = new Product();
            }
            product.setShortName(jTextFieldCurveName.getText());
            product.setProductType(jComboBoxCurveType.getSelectedItem().toString());
            product.setIsAsset(false);
            Set<ProductUnderlying> productUnderlyingList = new HashSet<>();
            product.setUnderlyingProducts(productUnderlyingList);
            for (Product und : underlyings) {
                product.addUnderlying(und);
            }

            product.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            product.setNotionalCurrency(globalUnderlying.getNotionalCurrency());
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, product);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        if (product.getProductId() != null) {
            JOptionPane.showMessageDialog(this, "Surface saved with id " + product.getProductId());
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed
    private void jButtonSearchUnderlyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchUnderlyingActionPerformed

        ArrayList<ProductTypeUtil.ProductType> underlyingTypes = new ArrayList();

        if (jComboBoxCurveType.getSelectedItem().toString().equalsIgnoreCase(ProductTypeUtil.ProductType.FX_VOLATILITY.getName())) {
            underlyingTypes.add(ProductType.CURRENCY_PAIR);
        }

        assetFinder = new AssetFinder(underlyingTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        /**
         * display the asset finder dialog
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer underlyingId = assetFinder.getAssetId();
            if (underlyingId != null) {
                try {
                    globalUnderlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, underlyingId.intValue());
                    jLabelUnderlyingId.setText(underlyingId.toString());
                    jTextFieldUnderlying.setText(globalUnderlying.getShortName());
                    updateGaiaRTLabel();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonSearchUnderlyingActionPerformed

    private void RTCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RTCheckBoxActionPerformed

        if (RTCheckBox.isSelected() && globalUnderlying != null) {
            MarketQuoteObservable obs = new MarketQuoteObservable(globalUnderlying);
            gaiaRTLabel.setObservable(obs);
            gaiaRTLabel.startRTQuote();
        } else {
            gaiaRTLabel.stopRTQuoteSubscription();
        }
    }//GEN-LAST:event_RTCheckBoxActionPerformed

    private void removeTenorJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeTenorJButtonActionPerformed

        if (jComboBoxTenor.getSelectedItem() != null && underlyings != null) {
            String tenor = jComboBoxTenor.getSelectedItem().toString();
            List<Product> toRemove = new ArrayList();
            for (Product underlying : underlyings) {
                if (underlying.getProductCurve().getTenor().equalsIgnoreCase(tenor)) {
                    toRemove.add(underlying);
                }
            }
            underlyings.removeAll(toRemove);
            removedUnderlyings.addAll(toRemove);
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String tenor_ = tableModel.getValueAt(i, 0).toString();
                if (tenor_.equalsIgnoreCase(tenor)) {
                    tableModel.removeRow(i);
                }
            }
        }
    }//GEN-LAST:event_removeTenorJButtonActionPerformed

    private void addTenorjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addTenorjButtonActionPerformed
        if (jComboBoxTenor.getSelectedItem() != null) {
            String tenor = jComboBoxTenor.getSelectedItem().toString();
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
            boolean alreadyExisting = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                String t = tableModel.getValueAt(i, 0).toString();
                if (t.equalsIgnoreCase(tenor)) {
                    alreadyExisting = true;
                }
            }
            if (!alreadyExisting) {
                FxStrike strike;
                if (tableModel.getColumnCount() > 1) {
                    strike = new FxStrike(tableModel.getColumnName(1));
                } else {
                    strike = new FxStrike(FxStratStrikeType.ATM.name());
                }

                Product underlying = createUnderlying(tenor, strike.internalStrikeInPctDelta);
                if (underlyings == null) {
                    underlyings = new ArrayList();
                }
                underlyings.add(underlying);
                fillQuotes();
                displayVolatilities();
            }
        }
    }//GEN-LAST:event_addTenorjButtonActionPerformed

    private void removeStrikeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeStrikeJButtonActionPerformed

        if (!strikeTextField.getText().isEmpty() && underlyings != null) {

            FxStrike strikeC = new FxStrike(strikeTextField.getText() + "C");
            FxStrike strikeP = new FxStrike(strikeTextField.getText() + "P");

            List<Product> toRemove = new ArrayList();
            for (Product underlying : underlyings) {
                if (underlying.getProductCurve().getStrike().doubleValue() == strikeC.internalStrikeInPctDelta.doubleValue()) {
                    toRemove.add(underlying);
                }
                if (underlying.getProductCurve().getStrike().doubleValue() == strikeP.internalStrikeInPctDelta.doubleValue()) {
                    toRemove.add(underlying);
                }
            }
            underlyings.removeAll(toRemove);
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();

            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                String sStrike = tableModel.getColumnName(i);
                if (sStrike.equalsIgnoreCase(strikeC.name)) {;
                    jTableQuotes.setModel(removeColumn(tableModel, i));
                }
                if (sStrike.equalsIgnoreCase(strikeP.name)) {;
                    jTableQuotes.setModel(removeColumn(tableModel, i));
                }
            }
        }
    }//GEN-LAST:event_removeStrikeJButtonActionPerformed

    public DefaultTableModel removeColumn(DefaultTableModel tableModel, int column) {
        if (column < tableModel.getColumnCount() && column >= 0 && tableModel.getColumnCount() > 0) {
            String[] newHeader = new String[tableModel.getColumnCount() - 1];
            Object[][] data = new Object[tableModel.getRowCount()][tableModel.getColumnCount() - 1];
            int j = 0;
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                if (i != column) {
                    newHeader[j] = tableModel.getColumnName(i);
                    for (int k = 0; k < tableModel.getRowCount(); k++) {
                        data[k][j] = tableModel.getValueAt(k, i);
                    }
                    j++;
                }
            }
            return new DefaultTableModel(data, newHeader);
        }
        return tableModel;
    }

    private void addStrikeJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStrikeJButtonActionPerformed
        if (!strikeTextField.getText().isEmpty()) {

            if (!NumberUtils.isNumber(strikeTextField.getText())) {
                return;
            }
            Integer pct = Integer.parseInt(strikeTextField.getText());
            if (pct >= 100 || pct <= 0) {
                JOptionPane.showConfirmDialog(this, "strike is in % delta, so it must be less than 100");
                return;
            }

            FxStrike strikeC = new FxStrike(strikeTextField.getText() + "C");
            FxStrike strikeP = new FxStrike(strikeTextField.getText() + "P");
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
            boolean alreadyExisting = false;
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                String s = tableModel.getColumnName(i);
                if (s.equalsIgnoreCase(strikeC.name)) {
                    alreadyExisting = true;
                }
            }
            if (!alreadyExisting) {
                String tenor;
                if (tableModel.getRowCount() > 0) {
                    tenor = tableModel.getValueAt(0, 0).toString();
                } else {
                    tenor = "1M";
                }

                Product underlyingC = createUnderlying(tenor, strikeC.internalStrikeInPctDelta);
                Product underlyingP = createUnderlying(tenor, strikeP.internalStrikeInPctDelta);
                if (underlyings == null) {
                    underlyings = new ArrayList();
                }
                underlyings.add(underlyingC);
                underlyings.add(underlyingP);
                loadAndDisplayAll();
            }
        }
    }//GEN-LAST:event_addStrikeJButtonActionPerformed

    private void quoteModeJComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_quoteModeJComboBoxPropertyChange

//        if (evt.getNewValue() != null && evt.getNewValue().toString().equalsIgnoreCase("NORMAL")
//                && evt.getOldValue() != null && evt.getOldValue().toString().equalsIgnoreCase("PRESSED")) {
//
//            boolean isInStratVol = false;
//            if (quoteModeJComboBox.getSelectedItem() != null && quoteModeJComboBox.getSelectedItem().toString().equalsIgnoreCase(quoteMode.BFRR.name)) {
//                isInStratVol = true;
//            }
//            if (isInStratVol) {
//                fillQuotesFromDeltaQuotes();
//            } else {
//                fillQuotesFromStratQuotes();
//            }
//            displayVolatilities();
//        }
    }//GEN-LAST:event_quoteModeJComboBoxPropertyChange

    private void strikeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_strikeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_strikeTextFieldActionPerformed

    private void quoteModeJComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quoteModeJComboBoxActionPerformed

            boolean isInStratVol = false;
            if (quoteModeJComboBox.getSelectedItem() != null && quoteModeJComboBox.getSelectedItem().toString().equalsIgnoreCase(quoteMode.BFRR.name)) {
                isInStratVol = true;
            }
            if (isInStratVol) {
                fillQuotesFromDeltaQuotes();
            } else {
                fillQuotesFromStratQuotes();
            }
            displayVolatilities();
    }//GEN-LAST:event_quoteModeJComboBoxActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        drawSurface();
    }//GEN-LAST:event_jButton1ActionPerformed

    public void updateGaiaRTLabel() {
        Integer pairId = GUIUtils.getComponentIntegerValue(jLabelUnderlyingId);
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        MarketQuote quote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_LAST_QUOTE, pairId, DateUtils.getDate(), quoteSet);
        gaiaRTLabel.setText(decimalFormat.format(quote.getClose()));
        if (RTCheckBox.isSelected() && globalUnderlying != null) {
            MarketQuoteObservable obs = new MarketQuoteObservable(globalUnderlying);
            gaiaRTLabel.setObservable(obs);
            gaiaRTLabel.startRTQuote();
        } else {
            gaiaRTLabel.stopRTQuoteSubscription();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox RTCheckBox;
    private javax.swing.JButton addStrikeJButton;
    private javax.swing.JButton addTenorjButton;
    private javax.swing.JLabel deltaLabel;
    private org.gaia.gui.common.GaiaRTLabel gaiaRTLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearchUnderlying;
    private javax.swing.JComboBox jComboBoxCalendar;
    private javax.swing.JComboBox jComboBoxCurveDate;
    private javax.swing.JComboBox jComboBoxCurveType;
    private javax.swing.JComboBox jComboBoxQuoteSet;
    private javax.swing.JComboBox jComboBoxTenor;
    private com.toedter.calendar.JDateChooser jDateChooserCurveDate;
    private javax.swing.JLabel jLabelCalendar;
    private javax.swing.JLabel jLabelCurveName;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelQuoteDate;
    private javax.swing.JLabel jLabelQuoteType;
    private javax.swing.JLabel jLabelTenor;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelUnderlying;
    private javax.swing.JLabel jLabelUnderlyingId;
    private javax.swing.JPanel jPanelDefinition;
    private javax.swing.JPanel jPanelMarketQuote;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableQuotes;
    private javax.swing.JTextField jTextFieldCurveName;
    private javax.swing.JTextField jTextFieldUnderlying;
    private javax.swing.JComboBox quoteModeJComboBox;
    private javax.swing.JButton removeStrikeJButton;
    private javax.swing.JButton removeTenorJButton;
    private javax.swing.JLabel strikeLabel;
    private javax.swing.JTextField strikeTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
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
