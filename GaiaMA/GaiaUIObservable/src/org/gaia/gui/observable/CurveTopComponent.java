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
import java.awt.Dimension;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.CurveObservable;
import org.gaia.dao.observables.CurveObservable.TenorComparator;
import org.gaia.dao.observables.CurveUtils;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.MarketQuoteUtils;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.pricing.pricers.isda.DateIntervalUtil;
import org.gaia.dao.pricing.pricers.isda.IsdaCurve;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCurve;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.ExcelAdapter;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimePeriod;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.XYDataset;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays curves.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//Curve//EN", autostore = false)
@TopComponent.Description(preferredID = "CurveTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.CurveTopComponent")
@ActionReference(path = "Menu"+MenuManager.CurveTopComponentMenu, position = MenuManager.CurveTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CurveAction")
@Messages({"CTL_CurveAction=Curve", "CTL_CurveTopComponent=Curves", "HINT_CurveTopComponent=This is a Curve window"})
public final class CurveTopComponent extends GaiaTopComponent {

    private Product product;
    private final ArrayList<ProductTypeUtil.ProductType> listCurveTypes;
    private AssetFinder assetFinder;
    private ArrayList<Product> underlyings = new ArrayList();
    private ArrayList<Product> intermediateUnderlyings = new ArrayList();
    private Product globalUnderlying = null;
    private final ArrayList<ProductTypeUtil.ProductType> underlyingTypes = new ArrayList();
    private CurveObservable curve;
    private ChartPanel chartpanel;
    private static final Logger logger = Logger.getLogger(CurveTopComponent.class);
    private final Format mydecimalFormat;

    private enum CurveUnderlyings {

        MoneyMarket, Swap, Credit, Recovery, FXForward, SpreadCurve, compositeCurve
    };

    public CurveTopComponent() {
        initComponents();
        setName(Bundle.CTL_CurveTopComponent());
        setToolTipText(Bundle.HINT_CurveTopComponent());
        mydecimalFormat = new DecimalFormat("#,##0.0000", decimalFormatSymbol);
        listCurveTypes = new ArrayList();
        listCurveTypes.add(ProductTypeUtil.ProductType.IR_CURVE);
        listCurveTypes.add(ProductTypeUtil.ProductType.CREDIT_CURVE);
        listCurveTypes.add(ProductTypeUtil.ProductType.RECOVERY_CURVE);
        listCurveTypes.add(ProductTypeUtil.ProductType.FX_FORWARD_CURVE);
        listCurveTypes.add(ProductTypeUtil.ProductType.IR_SPREAD_CURVE);
        listCurveTypes.add(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE);

    }

    @Override
    public void initContext() {

        clear();
        jDateChooserCurveDate.setDate(DateUtils.getDate());
        try {
            ExcelAdapter myAd = new ExcelAdapter(jTableQuotes);

            jComboBoxCurveType.removeAllItems();
            for (ProductTypeUtil.ProductType productTypes : listCurveTypes) {
                jComboBoxCurveType.addItem(productTypes.name);
            }

            chartpanel = new ChartPanel(null);
            chartpanel.setPreferredSize(new Dimension(500, 270));
            jTabbedPane.addTab("Graph", chartpanel);

            List<String> itemList = (List) DAOCallerAgent.callMethod(DayCountAccessor.class, DayCountAccessor.GET_DAYCOUNTS);
            GUIUtils.fillCombo(jComboBoxBasis, itemList);

            itemList = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_QUOTE_SETS);
            GUIUtils.fillCombo(jComboBoxQuoteSet, itemList);

            itemList = FrequencyUtil.getTenors();
            GUIUtils.fillCombo(jComboBoxTenor, itemList);

            itemList = (List) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.LOAD_CALENDAR_CODES);
            GUIUtils.fillCombo(jComboBoxCalendar, itemList);

            /**
             * ShortCut for  date
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

        jPanel1 = new javax.swing.JPanel();
        jButtonSave = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
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
        curve1Label = new javax.swing.JLabel();
        curve1TextField = new javax.swing.JTextField();
        findCurve1Button = new javax.swing.JButton();
        curve2Label = new javax.swing.JLabel();
        curve2TextField = new javax.swing.JTextField();
        findCurve2Button = new javax.swing.JButton();
        isCompositeCheckBox = new javax.swing.JCheckBox();
        jPanelMarketQuote = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableQuotes = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>3)
                return true;   //Disallow the editing of any cell
                else return false;
            }
        };
        jLabelQuoteDate = new javax.swing.JLabel();
        jLabelTenor = new javax.swing.JLabel();
        jComboBoxTenor = new javax.swing.JComboBox();
        jButtonAddTenor = new javax.swing.JButton();
        jButtonRemove = new javax.swing.JButton();
        jLabelSubProductType = new javax.swing.JLabel();
        jComboBoxUnderlyingType = new javax.swing.JComboBox();
        jButtonAddUnderlying = new javax.swing.JButton();
        jDateChooserCurveDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jPanelCurve = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableCurve = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                if (colIndex>2)
                return true;   //Disallow the editing of any cell
                else return false;
            }
        };
        jComboBoxBasis = new javax.swing.JComboBox();
        jLabelBasis = new javax.swing.JLabel();
        jButtonGenerate = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jPanelDefinition.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelQuoteType, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelQuoteType.text")); // NOI18N

        jComboBoxQuoteSet.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxQuoteSet.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCurveName, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelCurveName.text")); // NOI18N

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelType, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelType.text")); // NOI18N

        jComboBoxCurveType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurveType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurveType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurveTypeActionPerformed(evt);
            }
        });

        jComboBoxCurveDate.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurveDate.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxCurveDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxCurveDateActionPerformed(evt);
            }
        });

        jComboBoxCalendar.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCalendar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NONE" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCalendar, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelCalendar.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlying, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelUnderlying.text")); // NOI18N

        jTextFieldUnderlying.setText(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jTextFieldUnderlying.text")); // NOI18N
        jTextFieldUnderlying.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelUnderlyingId, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelUnderlyingId.text")); // NOI18N

        jButtonSearchUnderlying.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSearchUnderlying, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonSearchUnderlying.text")); // NOI18N
        jButtonSearchUnderlying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchUnderlyingActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(curve1Label, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.curve1Label.text")); // NOI18N

        curve1TextField.setEditable(false);
        curve1TextField.setText(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.curve1TextField.text")); // NOI18N

        findCurve1Button.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(findCurve1Button, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.findCurve1Button.text")); // NOI18N
        findCurve1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findCurve1ButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(curve2Label, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.curve2Label.text")); // NOI18N

        curve2TextField.setEditable(false);
        curve2TextField.setText(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.curve2TextField.text")); // NOI18N

        findCurve2Button.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(findCurve2Button, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.findCurve2Button.text")); // NOI18N
        findCurve2Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                findCurve2ButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(isCompositeCheckBox, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.isCompositeCheckBox.text")); // NOI18N
        isCompositeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isCompositeCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelDefinitionLayout = new javax.swing.GroupLayout(jPanelDefinition);
        jPanelDefinition.setLayout(jPanelDefinitionLayout);
        jPanelDefinitionLayout.setHorizontalGroup(
            jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                        .addComponent(jLabelUnderlying)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelQuoteType)
                            .addComponent(jLabelCurveName, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(curve1Label)
                            .addComponent(curve2Label, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                        .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButtonSearchUnderlying)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                                .addComponent(jTextFieldCurveName, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(8, 8, 8)
                                                .addComponent(jButtonFind))
                                            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                                .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabelType)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBoxCurveType, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(isCompositeCheckBox)
                                            .addComponent(jComboBoxCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                                    .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(curve2TextField, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(curve1TextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(findCurve1Button)
                                        .addComponent(findCurve2Button))
                                    .addGap(118, 118, 118))))
                        .addContainerGap())))
        );
        jPanelDefinitionLayout.setVerticalGroup(
            jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(543, 543, 543))
            .addGroup(jPanelDefinitionLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelQuoteType)
                    .addComponent(jComboBoxQuoteSet, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelType)
                    .addComponent(jComboBoxCurveType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(isCompositeCheckBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCurveName)
                    .addComponent(jTextFieldCurveName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFind)
                    .addComponent(jComboBoxCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelUnderlyingId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelUnderlying)
                        .addComponent(jTextFieldUnderlying, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSearchUnderlying)))
                .addGap(14, 14, 14)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxCalendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCalendar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curve1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findCurve1Button)
                    .addComponent(curve1Label))
                .addGap(14, 14, 14)
                .addGroup(jPanelDefinitionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(curve2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(findCurve2Button)
                    .addComponent(curve2Label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jPanelDefinition.TabConstraints.tabTitle"), jPanelDefinition); // NOI18N

        jPanelMarketQuote.setBackground(new java.awt.Color(254, 252, 254));

        jTableQuotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {"Id" ,"Name","Tenor","Quotation Type","Mid","Bid","Ask"
            }
        ));
        jScrollPane1.setViewportView(jTableQuotes);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelQuoteDate, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelQuoteDate.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTenor, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelTenor.text")); // NOI18N

        jComboBoxTenor.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxTenor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jButtonAddTenor.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddTenor, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonAddTenor.text")); // NOI18N
        jButtonAddTenor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddTenorActionPerformed(evt);
            }
        });

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelSubProductType, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelSubProductType.text")); // NOI18N

        jComboBoxUnderlyingType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxUnderlyingType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        jButtonAddUnderlying.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddUnderlying, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonAddUnderlying.text")); // NOI18N
        jButtonAddUnderlying.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddUnderlyingActionPerformed(evt);
            }
        });

        jDateChooserCurveDate.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.GroupLayout jPanelMarketQuoteLayout = new javax.swing.GroupLayout(jPanelMarketQuote);
        jPanelMarketQuote.setLayout(jPanelMarketQuoteLayout);
        jPanelMarketQuoteLayout.setHorizontalGroup(
            jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                        .addComponent(jLabelQuoteDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserCurveDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelSubProductType)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxUnderlyingType, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAddUnderlying)
                        .addGap(38, 38, 38)
                        .addComponent(jLabelTenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBoxTenor, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonAddTenor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemove))
                    .addComponent(jScrollPane1))
                .addGap(57, 57, 57))
        );
        jPanelMarketQuoteLayout.setVerticalGroup(
            jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMarketQuoteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelMarketQuoteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonAddTenor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButtonAddUnderlying, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBoxUnderlyingType)
                    .addComponent(jComboBoxTenor)
                    .addComponent(jLabelTenor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelQuoteDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooserCurveDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabelSubProductType, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jPanelMarketQuote.TabConstraints.tabTitle"), jPanelMarketQuote); // NOI18N

        jPanelCurve.setBackground(new java.awt.Color(254, 252, 254));

        jTableCurve.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Date", "Rate", "Discount"
            }
        ));
        jScrollPane2.setViewportView(jTableCurve);

        jComboBoxBasis.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxBasis.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelBasis, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jLabelBasis.text")); // NOI18N

        jButtonGenerate.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonGenerate, org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jButtonGenerate.text")); // NOI18N
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelCurveLayout = new javax.swing.GroupLayout(jPanelCurve);
        jPanelCurve.setLayout(jPanelCurveLayout);
        jPanelCurveLayout.setHorizontalGroup(
            jPanelCurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCurveLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelCurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelCurveLayout.createSequentialGroup()
                        .addComponent(jLabelBasis)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxBasis, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerate)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelCurveLayout.setVerticalGroup(
            jPanelCurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelCurveLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanelCurveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxBasis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelBasis)
                    .addComponent(jButtonGenerate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 392, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        jTabbedPane.addTab(org.openide.util.NbBundle.getMessage(CurveTopComponent.class, "CurveTopComponent.jPanelCurve.TabConstraints.tabTitle"), jPanelCurve); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave))
                    .addComponent(jTabbedPane, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 530, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonSave, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonNew, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * find curve
     *
     * @param evt
     */
    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed

        jComboBoxCurveDate.removeAllItems();
        assetFinder = new AssetFinder(listCurveTypes);
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
        /**
         * find the curve
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();

            curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);

            if (productId != null) {
                try {
                    clear();
                    product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());
                    jLabelId.setText(productId.toString());
                    jTextFieldCurveName.setText(product.getShortName());
                    jComboBoxCurveType.setSelectedItem(product.getProductType());
                    if (product.getProductCurve() != null) {
                        jComboBoxCalendar.setSelectedItem(product.getProductCurve().getCalendarCode());
                        isCompositeCheckBox.setSelected(product.getProductCurve().isComposite());
                    }
                    refreshFields();
                    List<Date> dates;
                    underlyings = new ArrayList();
                    intermediateUnderlyings = new ArrayList();
                    if (product.getProductCurve().isComposite()) {
                        // on composite curves, things are more complicated
                        //curve components
                        intermediateUnderlyings.addAll(product.loadUnderlyings());
                        // components underlyings
                        for (Product und : intermediateUnderlyings) {
                            underlyings.addAll(und.loadUnderlyings());
                        }
                        curve1TextField.setText(intermediateUnderlyings.get(0).getShortName());
                        curve2TextField.setText(intermediateUnderlyings.get(1).getShortName());
                        dates = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_CURVES_DATES, intermediateUnderlyings.get(0).getProductId(), jComboBoxQuoteSet.getSelectedItem().toString());
                        List<Date> dates2 = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_CURVES_DATES, intermediateUnderlyings.get(1).getProductId(), jComboBoxQuoteSet.getSelectedItem().toString());
                        dates = DateUtils.getCommonFromDateLists(dates, dates2);

                    } else {
                        underlyings.addAll(product.loadUnderlyings());
                        dates = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_CURVES_DATES, productId, jComboBoxQuoteSet.getSelectedItem().toString());
                    }

                    for (Date date : dates) {
                        jComboBoxCurveDate.addItem(dateFormatter.format(date));
                    }
                    jTextFieldUnderlying.setText(StringUtils.EMPTY_STRING);
                    jLabelUnderlyingId.setText(StringUtils.EMPTY_STRING);
                    if (underlyings.size() > 0) {
                        if (underlyings.iterator().next().loadSingleUnderlying() != null) {
                            globalUnderlying = underlyings.iterator().next().loadSingleUnderlying();
                            jTextFieldUnderlying.setText(globalUnderlying.getShortName());
                            jLabelUnderlyingId.setText(globalUnderlying.getProductId().toString());
                        }
                    }
                    loadAndDisplayAll();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonFindActionPerformed

    private void jComboBoxCurveTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurveTypeActionPerformed
        refreshFields();
    }//GEN-LAST:event_jComboBoxCurveTypeActionPerformed

    public void refreshFields() {
        boolean showCompositeCurveFields = isCompositeCheckBox.isSelected();

        jComboBoxUnderlyingType.removeAllItems();
        underlyingTypes.clear();
        if (jComboBoxCurveType.getSelectedItem() != null) {
            if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.IR_CURVE.name)) {
                jComboBoxUnderlyingType.addItem(CurveUnderlyings.MoneyMarket);
                jComboBoxUnderlyingType.addItem(CurveUnderlyings.Swap);
                underlyingTypes.add(ProductType.CASH);
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.CREDIT_CURVE.name)) {
                jComboBoxUnderlyingType.addItem(CurveUnderlyings.Credit);
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.RECOVERY_CURVE.name)) {
                jComboBoxUnderlyingType.addItem(CurveUnderlyings.Recovery);
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.FX_FORWARD_CURVE.name)) {
                jComboBoxUnderlyingType.addItem(CurveUnderlyings.FXForward);
                underlyingTypes.add(ProductType.CURRENCY_PAIR);
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.IR_SPREAD_CURVE.name)) {
            }

            jLabelUnderlying.setVisible(underlyingTypes.size() > 0);
            jTextFieldUnderlying.setVisible(underlyingTypes.size() > 0);
            jButtonSearchUnderlying.setVisible(underlyingTypes.size() > 0);
            jLabelUnderlyingId.setVisible(underlyingTypes.size() > 0);

            jComboBoxUnderlyingType.setEnabled(jComboBoxUnderlyingType.getItemCount() > 0);
            jButtonAddUnderlying.setEnabled(jComboBoxUnderlyingType.getItemCount() > 0);

            jLabelCalendar.setVisible(!showCompositeCurveFields);
            jComboBoxCalendar.setVisible(!showCompositeCurveFields);

            curve1Label.setVisible(showCompositeCurveFields);
            curve2Label.setVisible(showCompositeCurveFields);
            curve1TextField.setVisible(showCompositeCurveFields);
            curve2TextField.setVisible(showCompositeCurveFields);
            findCurve1Button.setVisible(showCompositeCurveFields);
            findCurve2Button.setVisible(showCompositeCurveFields);

            jButtonAddUnderlying.setVisible(!showCompositeCurveFields);
            jButtonAddTenor.setVisible(!showCompositeCurveFields);
            jButtonRemove.setVisible(!showCompositeCurveFields);
        }
    }

    /**
     * load curve when select date
     *
     * @param evt
     */
    private void jComboBoxCurveDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxCurveDateActionPerformed

        loadAndDisplayAll();
    }

    public void loadAndDisplayAll() {

        try {

            loadAndDisplayMarketQuotes();
            generateZCCurve();
            displayZCCurve();

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }//GEN-LAST:event_jComboBoxCurveDateActionPerformed

    public void loadAndDisplayMarketQuotes() {

        try {
            DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
            tableModel.getDataVector().clear();

            List<MarketQuote> marketQuotes = null;
            if (jComboBoxCurveDate.getSelectedItem() != null && !jLabelId.getText().isEmpty()) {
                Date startDate = dateFormatter.parse(jComboBoxCurveDate.getSelectedItem().toString());
                jDateChooserCurveDate.setDate(startDate);

                marketQuotes = (List) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_CURVE_BY_DATES, product, jComboBoxQuoteSet.getSelectedItem().toString(), startDate);

                /**
                 * forced set end date to sort by dates
                 */
                for (MarketQuote marketQuote : marketQuotes) {
                    if (marketQuote.getProduct().getProductCurve() != null) {
                        String tenor = marketQuote.getProduct().getProductCurve().getTenor();
                        Date date = (Date) DAOCallerAgent.callMethod(DateIntervalUtil.class, DateIntervalUtil.GET_DATE_FROM_START_AND_TENOR, startDate, tenor);
                        marketQuote.setDateEnd(date);
                    }
                }
                Collections.sort(marketQuotes);
            }
            if (underlyings != null) {
                if (curve == null) {
                    curve = new CurveObservable(AbstractObservable.ObservableType.IR_CURVE, AbstractObservable.DISCOUNTING);
                }
                CurveObservable.TenorComparator comp = curve.new TenorComparator();
                Collections.sort(underlyings, comp);

                for (Product underlying : underlyings) {
                    Vector row = new Vector<>();
                    row.add(underlying.getId());
                    row.add(underlying.getShortName());
                    if (underlying.getProductCurve() != null) {
                        row.add(underlying.getProductCurve().getTenor());
                    } else {
                        row.add(StringUtils.EMPTY_STRING);
                    }
                    row.add(underlying.getQuotationType());

                    MarketQuote.QuotationType type = MarketQuoteUtils.getQuotationTypeByName(underlying.getQuotationType());
                    BigDecimal mult = type.getMult();
                    if (marketQuotes != null) {
                        for (MarketQuote marketQuote : marketQuotes) {
                            if (marketQuote.getProduct() != null && underlying.getId() != null) {
                                if (underlying.getId().equals(marketQuote.getProduct().getId())) {

                                    if (marketQuote.getClose() != null) {
                                        marketQuote.setClose(marketQuote.getClose().multiply(mult));
                                        row.add(mydecimalFormat.format(marketQuote.getClose().doubleValue()));
                                    } else {
                                        row.add(null);
                                    }
                                    if (marketQuote.getBid() != null) {
                                        marketQuote.setBid(marketQuote.getBid().multiply(mult));
                                        row.add(mydecimalFormat.format(marketQuote.getBid().doubleValue()));
                                    } else {
                                        row.add(null);
                                    }
                                    if (marketQuote.getAsk() != null) {
                                        marketQuote.setAsk(marketQuote.getAsk().multiply(mult));
                                        row.add(mydecimalFormat.format(marketQuote.getAsk().doubleValue()));
                                    } else {
                                        row.add(null);
                                    }
                                }
                            }
                        }
                    }
                    tableModel.addRow(row);
                }
            }

            jTableQuotes.setModel(tableModel);
            TableColumnModel columnModel = jTableQuotes.getColumnModel();
            columnModel.getColumn(0).setMaxWidth(45);
            GUIUtils.setNumberEditor(jTableQuotes, 4, "0.0000");
            GUIUtils.setNumberEditor(jTableQuotes, 5, "0.0000");
            GUIUtils.setNumberEditor(jTableQuotes, 6, "0.0000");

//            for (int column = 4; column < jTableQuotes.getColumnCount(); column++) {
//                jTableQuotes.getColumnModel().getColumn(column).setCellRenderer(new DecimalFormatRenderer("0.0000"));
//                GUIUtils.packColumn(jTableQuotes, column, 2);
//            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        clear();
    }//GEN-LAST:event_jButtonNewActionPerformed

    public void clear() {

        jTextFieldCurveName.setText(StringUtils.EMPTY_STRING);
        product = null;
        underlyings = new ArrayList();
        intermediateUnderlyings = new ArrayList();
        curve = null;
        globalUnderlying = null;
        jLabelId.setText(StringUtils.EMPTY_STRING);
        curve1TextField.setText(StringUtils.EMPTY_STRING);
        curve2TextField.setText(StringUtils.EMPTY_STRING);
        // curve table
        GUIUtils.emptyTable(jTableCurve);
        GUIUtils.emptyTable(jTableQuotes);
        if (chartpanel != null) {
            chartpanel.removeAll();
        }
    }

    /**
     * add tenor
     *
     * @param evt
     */
    private void jButtonAddTenorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddTenorActionPerformed

        if (!jComboBoxTenor.getSelectedItem().toString().isEmpty()) {
            String tenor = jComboBoxTenor.getSelectedItem().toString();

            for (Product und : underlyings) {
                if (und.getProductCurve() != null && und.getProductCurve().getTenor() != null && und.getProductCurve().getTenor().equalsIgnoreCase(tenor)) {
                    JOptionPane.showMessageDialog(this, "Tenor already in the curve");
                    return;
                }
            }

            Product underlying = new Product();
            if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.IR_CURVE.name)) {
                underlying.setProductType(ProductTypeUtil.ProductType.IR_CURVE_POINT.name);
                underlying.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.RECOVERY_CURVE.name)) {
                underlying.setProductType(ProductType.RECOVERY_CURVE_POINT.name);
                underlying.setQuotationType(MarketQuote.QuotationType.RECOVERY_RATE.getName());
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.CREDIT_CURVE.name)) {
                underlying.setProductType(ProductTypeUtil.ProductType.CREDIT_CURVE_POINT.name);
                underlying.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.FX_FORWARD_CURVE.name)) {
                underlying.setProductType(ProductTypeUtil.ProductType.FX_FORWARD_CURVE_POINT.name);
                underlying.setQuotationType(MarketQuote.QuotationType.FWDpts.getName());
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.IR_SPREAD_CURVE.name)) {
                underlying.setProductType(ProductTypeUtil.ProductType.IR_SPREAD_CURVE_POINT.name);
                underlying.setQuotationType(MarketQuote.QuotationType.SPREAD.getName());
            } else if (jComboBoxCurveType.getSelectedItem().equals(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE.name)) {
                underlying.setProductType(ProductTypeUtil.ProductType.IR_BASIS_SWAP_CURVE_UNDERLYING.name);
                underlying.setQuotationType(MarketQuote.QuotationType.SPREAD.getName());
            }
            ProductCurve productCurve = new ProductCurve();
            productCurve.setTenor(tenor);
            productCurve.setProduct(underlying);
            if (product != null) {
                underlying.setNotionalCurrency(product.getNotionalCurrency());
            }
            underlying.setProductCurve(productCurve);
            underlying.setShortName(ProductAccessor.getDefaultProductShortName(underlying));
            underlying.setNotionalMultiplier(BigDecimal.ZERO);
            underlyings.add(underlying);
            loadAndDisplayAll();
        }
    }//GEN-LAST:event_jButtonAddTenorActionPerformed

    /**
     * remove a curve entry
     *
     * @param evt
     */
    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed

        int row = jTableQuotes.getSelectedRow();

        DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
        if (row > -1) {
            String sId = GUIUtils.getTableValueAt(jTableQuotes, row, 0);
            if (!sId.isEmpty()) {
                Integer id = new Integer(sId);
                Product toRemove = null;
                for (Product underlying : underlyings) {
                    if (underlying.getId() != null && underlying.getId().equals(id)) {
                        toRemove = underlying;
                    }
                }
                if (toRemove != null) {
                    underlyings.remove(toRemove);
                }

            } else if (jTableQuotes.getSelectedRow() < underlyings.size()) {
                underlyings.remove(row);
            }
            tableModel.removeRow(row);
        }

    }//GEN-LAST:event_jButtonRemoveActionPerformed

    /**
     * Add a line find and load
     *
     * @param evt
     */
    private void jButtonAddUnderlyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddUnderlyingActionPerformed

        List productTypes = new ArrayList();

        if (jComboBoxUnderlyingType.getSelectedItem().equals(CurveUnderlyings.MoneyMarket)) {
            ProductTypeUtil.ProductType productType = ProductTypeUtil.ProductType.IR_CURVE_MMKT_UNDERLYING;
            productTypes.add(productType);
        } else if (jComboBoxUnderlyingType.getSelectedItem().equals(CurveUnderlyings.Swap)) {
            ProductTypeUtil.ProductType productType = ProductTypeUtil.ProductType.IR_CURVE_SWAP_UNDERLYING;
            productTypes.add(productType);
        } else if (jComboBoxUnderlyingType.getSelectedItem().equals(CurveUnderlyings.Credit)) {
            ProductTypeUtil.ProductType productType1 = ProductTypeUtil.ProductType.CDS_INDEX;
            productTypes.add(productType1);
            ProductTypeUtil.ProductType productType2 = ProductTypeUtil.ProductType.CDS_PRODUCT;
            productTypes.add(productType2);
        } else if (jComboBoxUnderlyingType.getSelectedItem().equals(CurveUnderlyings.FXForward)) {
            ProductTypeUtil.ProductType productType = ProductTypeUtil.ProductType.FX_FORWARD_CURVE_POINT;
            productTypes.add(productType);
        } else if (jComboBoxUnderlyingType.getSelectedItem().equals(CurveUnderlyings.SpreadCurve)) {
            ProductTypeUtil.ProductType productType = ProductTypeUtil.ProductType.IR_SPREAD_CURVE_POINT;
            productTypes.add(productType);
        }

        assetFinder = new AssetFinder(productTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            try {
                Integer productId = assetFinder.getAssetId();

                Product product_ = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);

                if (product_.getProductCurve() != null && product_.getProductCurve().getTenor() != null) {
                    if (underlyings == null) {
                        underlyings = new ArrayList();
                    }
                    for (Product underlying : underlyings) {
                        if (underlying.getProductCurve().getTenor() != null && underlying.getProductCurve().getTenor().equalsIgnoreCase(product_.getProductCurve().getTenor())) {
                            JOptionPane.showMessageDialog(this, "The tenor is already present");
                            product_ = null;
                        }
                    }
                    if (product_ != null) {
                        underlyings.add(product_);
                        loadAndDisplayAll();
                    }
                } else {
                    (new ErrorMessageUI("Missing Tenor")).setVisible(true);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }//GEN-LAST:event_jButtonAddUnderlyingActionPerformed

    private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateActionPerformed

        generateZCCurve();
        displayZCCurve();

    }//GEN-LAST:event_jButtonGenerateActionPerformed

    /**
     * save
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        jTableQuotes.getTableHeader().setReorderingAllowed(false);

        Integer productid;
        String quotationtype;
        Date valdate;
        BigDecimal mid;
        BigDecimal bid;
        BigDecimal ask;
        String quoteset = jComboBoxQuoteSet.getSelectedItem().toString();
        String type = jComboBoxCurveType.getSelectedItem().toString();

        if (globalUnderlying == null && !jLabelUnderlyingId.getText().isEmpty()) {
            Integer id = Integer.parseInt(jLabelUnderlyingId.getText());
            globalUnderlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, id);
        }

        if (jTextFieldCurveName.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Missing name");
            return;
        } else if (globalUnderlying == null && (type.equals(ProductTypeUtil.ProductType.IR_CURVE.name) || type.equals(ProductTypeUtil.ProductType.FX_FORWARD_CURVE.name))) {
            JOptionPane.showMessageDialog(this, "The curve needs an underlying");
            return;
        } else if ((underlyings == null || underlyings.isEmpty()|| underlyings.size()<2) && isCompositeCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "Please fill underlying curves");
            return;
        } else if ((underlyings == null || underlyings.isEmpty()) && intermediateUnderlyings.isEmpty() && !isCompositeCheckBox.isSelected()) {
            JOptionPane.showMessageDialog(this, "A curve needs points");
            return;
        }
        // updates the product
        try {
            if (product == null) {
                product = new Product();
            }
            product.setNotionalMultiplier(BigDecimal.ONE);

            ProductCurve productCurve = product.getProductCurve();
            if (productCurve == null) {
                productCurve = new ProductCurve();
                productCurve.setProduct(product);
                product.setProductCurve(productCurve);
            }
            if (jComboBoxCalendar.getSelectedItem() != null) {
                productCurve.setCalendarCode(jComboBoxCalendar.getSelectedItem().toString());
            }
            productCurve.setIsComposite(isCompositeCheckBox.isSelected());
            product.setShortName(jTextFieldCurveName.getText());
            product.setProductType(jComboBoxCurveType.getSelectedItem().toString());
            product.setIsAsset(false);

            if (underlyings != null) {
                int i = 0;
                for (Product underlying : underlyings) {
                    if (globalUnderlying != null) {
                        underlying.addUnderlying(globalUnderlying);
                    }
                    if (underlying.getProductId() == null) {
                        if (underlying.getNotionalCurrency() == null) {
                            if (globalUnderlying != null) {
                                underlying.setNotionalCurrency(globalUnderlying.getNotionalCurrency());
                            } else {
                                underlying.setNotionalCurrency("EUR");
                            }
                        }
                        underlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                                ProductAccessor.STORE_PRODUCT, underlying);
                        underlyings.set(i, underlying);
                    }
                    i++;
                }
            }
            if (globalUnderlying != null) {
                product.setNotionalCurrency(globalUnderlying.getNotionalCurrency());
            } else {
                product.setNotionalCurrency("EUR");
            }

            Set<ProductUnderlying> productUnderlyingList = new HashSet<>();
            product.setUnderlyingProducts(productUnderlyingList);
            if (intermediateUnderlyings.size() == 0) {
                for (Product _underlying : underlyings) {
                    product.addUnderlying(_underlying);
                }
            } else {
                for (Product _underlying : intermediateUnderlyings) {
                    product.addUnderlying(_underlying);
                }
            }

            product.setQuotationType(MarketQuote.QuotationType.RATE.getName());
            DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, product);

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        // creates / updates curve quotes
        DefaultTableModel tableModel = (DefaultTableModel) jTableQuotes.getModel();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            mid = null;
            bid = null;
            ask = null;
            try {
                Product underlying = underlyings.get(i);
                valdate = jDateChooserCurveDate.getDate();
                quotationtype = underlying.getQuotationType();

                if (!GUIUtils.getTableValueAt(jTableQuotes, i, 4).isEmpty()) {
                    mid = new BigDecimal(Double.parseDouble(GUIUtils.getTableValueAt(jTableQuotes, i, 4)));
                }
                if (!GUIUtils.getTableValueAt(jTableQuotes, i, 5).isEmpty()) {
                    bid = new BigDecimal(Double.parseDouble(GUIUtils.getTableValueAt(jTableQuotes, i, 5)));
                }
                if (!GUIUtils.getTableValueAt(jTableQuotes, i, 6).isEmpty()) {
                    ask = new BigDecimal(Double.parseDouble(GUIUtils.getTableValueAt(jTableQuotes, i, 6)));
                }

                if (mid != null || ask != null || bid != null) {
                    productid = underlying.getId();
                    MarketQuote marketQuote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_PRODUCT_QUOTE, productid, valdate, quoteset);
                    if (marketQuote == null) {
                        marketQuote = new MarketQuote();
                        marketQuote.setProduct(underlying);
                        marketQuote.setQuoteSet(quoteset);
                        marketQuote.setValuationDate(valdate);
                    }
                    marketQuote.setQuotationType(quotationtype);
                    MarketQuote.QuotationType quotationType = MarketQuoteUtils.getQuotationTypeByName(underlying.getQuotationType());
                    BigDecimal mult = quotationType.getMult();
                    if (ask != null) {
                        marketQuote.setAsk(ask.divide(mult));
                    }
                    if (bid != null) {
                        marketQuote.setBid(bid.divide(mult));
                    }
                    if (mid != null) {
                        marketQuote.setClose(mid.divide(mult));
                    }
                    DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.STORE_QUOTE, marketQuote);
                }
            } catch (Exception e) {
                logger.error("Error " + StringUtils.formatErrorMessage(e));
            }
        }
        if (product.getId() != null) {
            JOptionPane.showMessageDialog(this, "Saved with id " + product.getId());
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonSearchUnderlyingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchUnderlyingActionPerformed

        assetFinder = new AssetFinder(underlyingTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        /**
         * display the ZCCurve dialog
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer underlyingId = assetFinder.getAssetId();

            if (underlyingId != null) {
                try {
                    globalUnderlying = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, underlyingId.intValue());
                    jLabelUnderlyingId.setText(underlyingId.toString());
                    jTextFieldUnderlying.setText(globalUnderlying.getShortName());

                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_jButtonSearchUnderlyingActionPerformed

    private void findCurve1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findCurve1ButtonActionPerformed

        String previousProduct = curve1TextField.getText();
        assetFinder = new AssetFinder(listCurveTypes);
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
        /**
         * find the curve
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();

            if (productId != null) {
                try {
                    Product product1 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());

                    curve1TextField.setText(product1.getShortName());

                    // replace or add to underlyings
                    Product toRemove = null;
                    for (Product undl : intermediateUnderlyings) {
                        if (undl.getShortName().equalsIgnoreCase(previousProduct)) {
                            toRemove = undl;
                            underlyings.removeAll(undl.getUnderlyingProducts());
                        }
                    }
                    intermediateUnderlyings.remove(toRemove);

                    if (intermediateUnderlyings.size() < 2) {
                        intermediateUnderlyings.add(product1);
                    }

                    loadAndDisplayAll();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }//GEN-LAST:event_findCurve1ButtonActionPerformed

    private void findCurve2ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_findCurve2ButtonActionPerformed

        String previousProduct = curve2TextField.getText();
        assetFinder = new AssetFinder(listCurveTypes);
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);
        /**
         * find the curve
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();

            if (productId != null) {
                try {
                    Product product1 = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());

                    curve2TextField.setText(product1.getShortName());

                    // replace or add to underlyings
                    Product toRemove = null;
                    for (Product undl : intermediateUnderlyings) {
                        if (undl.getShortName().equalsIgnoreCase(previousProduct)) {
                            toRemove = undl;
                            underlyings.removeAll(undl.getUnderlyingProducts());
                        }
                    }
                    intermediateUnderlyings.remove(toRemove);

                    if (intermediateUnderlyings.size() < 2) {
                        intermediateUnderlyings.add(product1);
                    }

                    loadAndDisplayAll();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }

    }//GEN-LAST:event_findCurve2ButtonActionPerformed

    private void isCompositeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_isCompositeCheckBoxActionPerformed
        refreshFields();
    }//GEN-LAST:event_isCompositeCheckBoxActionPerformed

    public void loadCompositeUnderlyings() {
        String name1 = curve1TextField.getText();
        Product product1 = null;
        if (!StringUtils.isEmptyString(name1)) {
            product1 = ProductAccessor.getProductByShortName(name1);

        }
        curve2TextField.setText(product1.getShortName());
    }

    /**
     * calculation of zero curve
     */
    public void generateZCCurve() {
        try {
            if (jComboBoxCurveDate.getSelectedItem() != null) {
                Date date = dateFormatter.parse(jComboBoxCurveDate.getSelectedItem().toString());
                IsdaCurve isdaCurve = (IsdaCurve) DAOCallerAgent.callMethodWithXMLSerialization(CurveUtils.class, CurveUtils.GENERATE_ISDA_CURVE, product, date);
                curve.setIsdaCurve(isdaCurve);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public void displayZCCurve() {
        try {
            if (curve != null) {
                DefaultTableModel tableModel = (DefaultTableModel) jTableCurve.getModel();
                GUIUtils.clearTableModel(tableModel);

                for (int i = 0; i < curve.getZCCurveDates().size(); i++) {
                    Object[] row = new Object[3];
                    row[0] = dateFormatter.format(curve.getZCCurveDates().get(i));
                    row[1] = mydecimalFormat.format(curve.getZCCurveRates().get(i).doubleValue() * 100);
                    row[2] = curve.getZCCurveDiscountFactors().get(i);
                    tableModel.addRow(row);
                }

                drawCurveChart();
            }
        } catch (Exception e) {
            logger.error("Error " + StringUtils.formatErrorMessage(e));
        }

    }

    public void drawCurveChart() {

        TimeTableXYDataset data = new TimeTableXYDataset();
        String serieName = "ZC";
        for (int i = 0; i < curve.getZCCurveDates().size(); i++) {
            TimePeriod period = new Day(curve.getZCCurveDates().get(i));
            data.add(period, curve.getZCCurveRates().get(i).doubleValue() * 100, serieName);
        }
        JFreeChart jfreechart = ChartFactory.createTimeSeriesChart("Zero Curve", "Date", "Rate", (XYDataset) data, true, true, true);

        chartpanel.setChart(jfreechart);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel curve1Label;
    private javax.swing.JTextField curve1TextField;
    private javax.swing.JLabel curve2Label;
    private javax.swing.JTextField curve2TextField;
    private javax.swing.JButton findCurve1Button;
    private javax.swing.JButton findCurve2Button;
    private javax.swing.JCheckBox isCompositeCheckBox;
    private javax.swing.JButton jButtonAddTenor;
    private javax.swing.JButton jButtonAddUnderlying;
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonGenerate;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSearchUnderlying;
    private javax.swing.JComboBox jComboBoxBasis;
    private javax.swing.JComboBox jComboBoxCalendar;
    private javax.swing.JComboBox jComboBoxCurveDate;
    private javax.swing.JComboBox jComboBoxCurveType;
    private javax.swing.JComboBox jComboBoxQuoteSet;
    private javax.swing.JComboBox jComboBoxTenor;
    private javax.swing.JComboBox jComboBoxUnderlyingType;
    private com.toedter.calendar.JDateChooser jDateChooserCurveDate;
    private javax.swing.JLabel jLabelBasis;
    private javax.swing.JLabel jLabelCalendar;
    private javax.swing.JLabel jLabelCurveName;
    private javax.swing.JLabel jLabelId;
    private javax.swing.JLabel jLabelQuoteDate;
    private javax.swing.JLabel jLabelQuoteType;
    private javax.swing.JLabel jLabelSubProductType;
    private javax.swing.JLabel jLabelTenor;
    private javax.swing.JLabel jLabelType;
    private javax.swing.JLabel jLabelUnderlying;
    private javax.swing.JLabel jLabelUnderlyingId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelCurve;
    private javax.swing.JPanel jPanelDefinition;
    private javax.swing.JPanel jPanelMarketQuote;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTable jTableCurve;
    private javax.swing.JTable jTableQuotes;
    private javax.swing.JTextField jTextFieldCurveName;
    private javax.swing.JTextField jTextFieldUnderlying;
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
