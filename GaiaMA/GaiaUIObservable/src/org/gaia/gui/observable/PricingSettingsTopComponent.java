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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.AbstractObservable.ObservableType;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.pricers.ICVAPricer;
import org.gaia.dao.pricing.pricers.ICashPricer;
import org.gaia.dao.pricing.pricers.INPVPricer;
import org.gaia.dao.pricing.pricers.PricersUtils;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.observables.PricersSetting;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.observables.PricingSettingItem;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays pricing settings.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//PricingSettings//EN", autostore = false)
@TopComponent.Description(preferredID = "PricingSettingsTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.PricingSettingsTopComponent")
@ActionReference(path = "Menu"+MenuManager.PricingSettingsTopComponentMenu , position = MenuManager.PricingSettingsTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PricingSettingsAction", preferredID = "PricingSettingsTopComponent")
@Messages({"CTL_PricingSettingsAction=Pricing Settings", "CTL_PricingSettingsTopComponent=Pricing Settings Window", "HINT_PricingSettingsTopComponent=This is a Pricing Settings window"})

public final class PricingSettingsTopComponent extends TopComponent {

    private List<String> listDataTypes;
    private List<String> listFunctions;
    private List<String> listCurrencies;
    private List<String> listProductTypes;
    private List<String> listTradeFilters;
    private List<String> listPricers;
    private List<String> listPricingGroup;
    private PricingEnvironment pricingEnv;
    private List<PricersSetting> removedPricers;
    private List<PricingSettingItem> removedItems;
    ObservableType[] obsTypes = null;

    private static final Logger logger = Logger.getLogger(PricingSettingsTopComponent.class);

    public PricingSettingsTopComponent() {
        initComponents();
        setName(Bundle.CTL_PricingSettingsTopComponent());
        setToolTipText(Bundle.HINT_PricingSettingsTopComponent());

        TableColumnModel tableModelPricers = jTablePricers.getColumnModel();
        tableModelPricers.getColumn(0).setMinWidth(0);
        tableModelPricers.getColumn(0).setMaxWidth(0);

        TableColumnModel tableModel = jTableSettings.getColumnModel();
        tableModel.getColumn(0).setMinWidth(0);
        tableModel.getColumn(0).setMaxWidth(0);
    }

    public void initContext() {
        jTablePricers.getTableHeader().setReorderingAllowed(false);
        jTableSettings.getTableHeader().setReorderingAllowed(false);
        obsTypes = AbstractObservable.ObservableType.values();
        listDataTypes = new ArrayList();
        listFunctions = new ArrayList();
        listFunctions.add(StringUtils.EMPTY_STRING);
        for (int i = 0; i < obsTypes.length; i++) {
            listDataTypes.add(obsTypes[i].name);
            for (String function : obsTypes[i].functions) {
                if (!listFunctions.contains(function)) {
                    listFunctions.add(function);
                }
            }
        }
        listCurrencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
        listCurrencies.add(0, StringUtils.EMPTY_STRING);
        listProductTypes = ProductTypeUtil.loadTradeableTypeNames();
        listProductTypes.add(0, StringUtils.EMPTY_STRING);
        listTradeFilters = (List) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES, Position.class);
        listTradeFilters.add(0, StringUtils.EMPTY_STRING);
        listPricers = (List) DAOCallerAgent.callMethod(PricersUtils.class, PricersUtils.GET_PRICERS);
        listPricingGroup = PricersUtils.getPricingGroups();
        List<String> pricingSettingList = (List) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_LIST);
        GUIUtils.fillComboWithNullFirst(jComboPEnv, pricingSettingList);
    }

    public class MyComboBoxEditor extends DefaultCellEditor {

        public MyComboBoxEditor(String[] items) {
            super(new JComboBox(items));
        }
    }

    /**
     * add Row Settings
     *
     * @param row
     */
    private void addRowSettings(Vector row) {
        ((DefaultTableModel) jTableSettings.getModel()).addRow(row);

        String[] arrayDataObjects = (String[]) listDataTypes.toArray(new String[listDataTypes.size()]);
        TableColumn col = jTableSettings.getColumnModel().getColumn(1);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayDataObjects));

        String[] arrayFunctions = (String[]) listFunctions.toArray(new String[listFunctions.size()]);
        col = jTableSettings.getColumnModel().getColumn(2);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayFunctions));

        String[] arrayCur = (String[]) listCurrencies.toArray(new String[listCurrencies.size()]);
        col = jTableSettings.getColumnModel().getColumn(3);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayCur));

        String[] arrayProdTypes = (String[]) listProductTypes.toArray(new String[listProductTypes.size()]);
        col = jTableSettings.getColumnModel().getColumn(4);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayProdTypes));

        String[] arrayFilters = (String[]) listTradeFilters.toArray(new String[listTradeFilters.size()]);
        col = jTableSettings.getColumnModel().getColumn(5);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayFilters));
    }

    /**
     * add row pricers
     *
     * @param row
     */
    private void addRowPricers(Vector row) {
        ((DefaultTableModel) jTablePricers.getModel()).addRow(row);

        String[] arrayProdTypes = (String[]) listPricingGroup.toArray(new String[listPricingGroup.size()]);
        TableColumn col = jTablePricers.getColumnModel().getColumn(1);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayProdTypes));

        arrayProdTypes = (String[]) listProductTypes.toArray(new String[listProductTypes.size()]);
        col = jTablePricers.getColumnModel().getColumn(2);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayProdTypes));

        arrayProdTypes = (String[]) listTradeFilters.toArray(new String[listTradeFilters.size()]);
        col = jTablePricers.getColumnModel().getColumn(3);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayProdTypes));

        arrayProdTypes = (String[]) listPricers.toArray(new String[listPricers.size()]);
        col = jTablePricers.getColumnModel().getColumn(4);
        col.setCellEditor(new PricingSettingsTopComponent.MyComboBoxEditor(arrayProdTypes));
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
        jLabel1 = new javax.swing.JLabel();
        jButtonNew = new javax.swing.JButton();
        jComboPEnv = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablePricers = new javax.swing.JTable();
        jButtonAddPricer = new javax.swing.JButton();
        jButtonRemovePricer = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSettings = new javax.swing.JTable();
        jButtonAddSetting = new javax.swing.JButton();
        jButtonRemoveSetting = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jLabel1.text")); // NOI18N

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jComboPEnv.setBackground(new java.awt.Color(255, 255, 255));
        jComboPEnv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        jComboPEnv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboPEnvActionPerformed(evt);
            }
        });

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        jTablePricers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id","Pricing Group","Product Type", "Position Filter", "Pricer"
            }
        ));
        jScrollPane2.setViewportView(jTablePricers);

        jButtonAddPricer.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddPricer, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonAddPricer.text")); // NOI18N
        jButtonAddPricer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddPricerActionPerformed(evt);
            }
        });

        jButtonRemovePricer.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemovePricer, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonRemovePricer.text")); // NOI18N
        jButtonRemovePricer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemovePricerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonRemovePricer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAddPricer, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 535, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonAddPricer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemovePricer)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jPanel2.TabConstraints.tabTitle"), jPanel2); // NOI18N

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jTableSettings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Id","Data type","Function" ,"Currency", "Product Type", "Position Filter",  "Value"
            }
        ));
        jScrollPane1.setViewportView(jTableSettings);

        jButtonAddSetting.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAddSetting, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonAddSetting.text")); // NOI18N
        jButtonAddSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddSettingActionPerformed(evt);
            }
        });

        jButtonRemoveSetting.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemoveSetting, org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jButtonRemoveSetting.text")); // NOI18N
        jButtonRemoveSetting.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveSettingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 892, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonAddSetting, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonRemoveSetting))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButtonAddSetting)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemoveSetting)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(PricingSettingsTopComponent.class, "PricingSettingsTopComponent.jPanel4.TabConstraints.tabTitle"), jPanel4); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboPEnv, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboPEnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonNew, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonSave))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * save
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        boolean isNew = false;
        if (pricingEnv == null) {
            pricingEnv = new PricingEnvironment();
        }
        String pricingEnvName;
        if (jComboPEnv.getSelectedItem() != null) {
            pricingEnvName = jComboPEnv.getSelectedItem().toString();
            pricingEnv.setName(pricingEnvName);
        } else {
            pricingEnvName = JOptionPane.showInputDialog(this, "Please enter the name of the pricing environment", "Pricing Environment", JOptionPane.QUESTION_MESSAGE);
            isNew = true;
        }
        HashSet<PricingSettingItem> settingsItems = new HashSet();
        DefaultTableModel tableModel = (DefaultTableModel) jTableSettings.getModel();
        for (int i = 0; i < jTableSettings.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1) != null && tableModel.getValueAt(i, 6) != null) {
                PricingSettingItem item = null;
                if (!GUIUtils.getTableValueAt(jTableSettings, i, 0).isEmpty() && !isNew) {
                    item = (PricingSettingItem) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_SETTING_ITEM,
                            Integer.parseInt(GUIUtils.getTableValueAt(jTableSettings, i, 0)));
                }
                if (item == null) {
                    item = new PricingSettingItem();
                }
                item.setItemType(GUIUtils.getTableValueAt(jTableSettings, i, 1));
                item.setPricingFunction(GUIUtils.getTableValueAt(jTableSettings, i, 2));
                item.setCurrency(GUIUtils.getTableValueAt(jTableSettings, i, 3));
                item.setProductType(GUIUtils.getTableValueAt(jTableSettings, i, 4));
                item.setTradeFilterName(GUIUtils.getTableValueAt(jTableSettings, i, 5));
                item.setPricingEnvironment(pricingEnv);
                Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, GUIUtils.getTableValueAt(jTableSettings, i, 6));
                if (product != null) {
                    item.setItemValueId(product.getId());
                    item.setItemValue(GUIUtils.getTableValueAt(jTableSettings, i, 6));
                } else if (item.getPricingSettingItemId()==null){
                    JOptionPane.showMessageDialog(this, "market data not found : "+GUIUtils.getTableValueAt(jTableSettings, i, 6));
                    item=null;
                }
                if (item!=null){
                    settingsItems.add(item);
                }
            }
            pricingEnv.setPricingSettingItemCollection(settingsItems);
        }
        tableModel = (DefaultTableModel) jTablePricers.getModel();
        ArrayList<PricersSetting> pricers = new ArrayList();
        for (int i = 0; i < jTablePricers.getRowCount(); i++) {
            if (tableModel.getValueAt(i, 1) != null && tableModel.getValueAt(i, 4) != null) {
                PricersSetting pricer = null;
                if (!GUIUtils.getTableValueAt(jTablePricers, i, 0).isEmpty() && !isNew) {
                    pricer = (PricersSetting) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICERS_SETTING,
                            Integer.parseInt(GUIUtils.getTableValueAt(jTablePricers, i, 0)));
                }
                if (pricer == null) {
                    pricer = new PricersSetting();
                }
                pricer.setMeasureGroup(GUIUtils.getTableValueAt(jTablePricers, i, 1));
                pricer.setProductType(GUIUtils.getTableValueAt(jTablePricers, i, 2));
                pricer.setTradeFilterName(GUIUtils.getTableValueAt(jTablePricers, i, 3));
                // controle the pricer
                String pricerName = GUIUtils.getTableValueAt(jTablePricers, i, 4);
                String pricerClassName = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_MAPPING_VALUE_BY_NAME_AND_KEY, "pricers", pricerName);
                if (pricerClassName != null) {
                    try {
                        Class pricerClass = Class.forName(pricerClassName);
                        if (pricer.getMeasureGroup().equalsIgnoreCase(MeasuresAccessor.MeasureGroup.PV_GROUP.name())
                                &&!INPVPricer.class.isAssignableFrom(pricerClass)){
                            JOptionPane.showMessageDialog(this, "The pricer "+pricerName+" is not an NPV pricer!", "WARNING", JOptionPane.WARNING_MESSAGE);
                        } else if (pricer.getMeasureGroup().equalsIgnoreCase(MeasuresAccessor.MeasureGroup.CVA_GROUP.name())
                                &&!ICVAPricer.class.isAssignableFrom(pricerClass)){
                            JOptionPane.showMessageDialog(this, "The pricer "+pricerName+" is not a CVA pricer!", "WARNING", JOptionPane.WARNING_MESSAGE);
                        } else if (pricer.getMeasureGroup().equalsIgnoreCase(MeasuresAccessor.MeasureGroup.CASH_GROUP.name())
                                &&!ICashPricer.class.isAssignableFrom(pricerClass)){
                            JOptionPane.showMessageDialog(this, "The pricer "+pricerName+" is not a CASH pricer!", "WARNING", JOptionPane.WARNING_MESSAGE);
                        }
                    } catch (Exception e) {
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
                pricer.setPricer(GUIUtils.getTableValueAt(jTablePricers, i, 4));
                pricer.setPricingEnvironment(pricingEnv);
                pricers.add(pricer);
            }
            pricingEnv.setPricersSettingCollection(pricers);
        }

        DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.STORE_PRICING_ENVIRONMENT, pricingEnv);

        for (PricersSetting removed : removedPricers) {
            DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.DELETE_PRICERS_SETTING, removed);
        }
        for (PricingSettingItem removed : removedItems) {
            DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.DELETE_PRICING_SETTING_ITEM, removed);
        }

        load(pricingEnvName);

        JOptionPane.showMessageDialog(this, "Saved");
    }//GEN-LAST:event_jButtonSaveActionPerformed

    public void load(String name) {

        GUIUtils.emptyTable(jTablePricers);
        GUIUtils.emptyTable(jTableSettings);

        pricingEnv = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_FROM_NAME, name);
        if (pricingEnv != null) {

            if (pricingEnv.getPricersSettingCollection() != null) {
                for (PricersSetting pricerSetting : pricingEnv.getPricersSettingCollection()) {
                    Vector row = new Vector<>();
                    row.add(pricerSetting.getPricersSettingId());
                    row.add(pricerSetting.getMeasureGroup());
                    row.add(pricerSetting.getProductType());
                    row.add(pricerSetting.getTradeFilterName());
                    row.add(pricerSetting.getPricer());
                    addRowPricers(row);
                }
            }

            if (pricingEnv.getPricingSettingItemCollection() != null) {
                for (PricingSettingItem pricingSetting : pricingEnv.getPricingSettingItemCollection()) {
                    Vector row = new Vector<>();
                    row.add(pricingSetting.getPricingSettingItemId());
                    row.add(pricingSetting.getItemType());
                    row.add(pricingSetting.getPricingFunction());
                    row.add(pricingSetting.getCurrency());
                    row.add(pricingSetting.getProductType());
                    row.add(pricingSetting.getTradeFilterName());
                    if (pricingSetting.getProductUnderlyingId() != null) {
                        row.add(pricingSetting.getProductUnderlyingId());
                    }
                    row.add(pricingSetting.getItemValue());
                    addRowSettings(row);
                }
            }
        }
        removedPricers = new ArrayList();
        removedItems = new ArrayList();

    }

    /**
     * add
     *
     * @param evt
     */
    private void jButtonAddSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddSettingActionPerformed

        addRowSettings(new Vector<>(6));
    }//GEN-LAST:event_jButtonAddSettingActionPerformed

    /**
     * remove setting
     *
     * @param evt
     */
    private void jButtonRemoveSettingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveSettingActionPerformed

        if (jTableSettings.getSelectedRow() > -1) {
            DefaultTableModel tableModel = (DefaultTableModel) jTableSettings.getModel();
            int i = jTableSettings.getSelectedRow();
            PricingSettingItem item = null;
            if (!GUIUtils.getTableValueAt(jTableSettings, i, 0).isEmpty()) {
                item = (PricingSettingItem) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICING_SETTING_ITEM, Integer.parseInt(GUIUtils.getTableValueAt(jTableSettings, i, 0)));
            }
            if (item == null) {
                item = new PricingSettingItem();
            }
            item.setItemType(GUIUtils.getTableValueAt(jTableSettings, i, 1));
            item.setPricingFunction(GUIUtils.getTableValueAt(jTableSettings, i, 2));
            item.setCurrency(GUIUtils.getTableValueAt(jTableSettings, i, 3));
            item.setProductType(GUIUtils.getTableValueAt(jTableSettings, i, 4));
            item.setTradeFilterName(GUIUtils.getTableValueAt(jTableSettings, i, 5));
            item.setItemValue(GUIUtils.getTableValueAt(jTableSettings, i, 6));
            tableModel.removeRow(jTableSettings.getSelectedRow());

            removedItems.add(item);
        }
    }//GEN-LAST:event_jButtonRemoveSettingActionPerformed

    /**
     * New
     *
     * @param evt
     */
    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed

        String name = (String) JOptionPane.showInputDialog(this, "Name of the template", "Template name", JOptionPane.PLAIN_MESSAGE, null, null, "");

        if (!StringUtils.isEmptyString(name)) {
            pricingEnv = new PricingEnvironment();
            pricingEnv.setName(name);
            jComboPEnv.addItem(name);
            jComboPEnv.setSelectedIndex(jComboPEnv.getItemCount() - 1);
        }
    }//GEN-LAST:event_jButtonNewActionPerformed

    /**
     * Load
     *
     * @param evt
     */
    private void jComboPEnvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboPEnvActionPerformed

        if (jComboPEnv.getSelectedItem() != null) {
            load(jComboPEnv.getSelectedItem().toString());
        } else {
            load(StringUtils.EMPTY_STRING);
        }
    }//GEN-LAST:event_jComboPEnvActionPerformed

    private void jButtonAddPricerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddPricerActionPerformed
        addRowPricers(new Vector<>(4));
    }//GEN-LAST:event_jButtonAddPricerActionPerformed

    /**
     * remove pricer
     *
     * @param evt
     */
    private void jButtonRemovePricerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemovePricerActionPerformed

        if (jTablePricers.getSelectedRow() > -1) {
            DefaultTableModel tableModel = (DefaultTableModel) jTablePricers.getModel();
            int i = jTablePricers.getSelectedRow();
            PricersSetting pricer = null;
            if (!GUIUtils.getTableValueAt(jTablePricers, i, 0).isEmpty()) {
                pricer = (PricersSetting) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class, PricingEnvironmentAccessor.GET_PRICERS_SETTING, Integer.parseInt(GUIUtils.getTableValueAt(jTablePricers, i, 0)));
            }
            if (pricer == null) {
                pricer = new PricersSetting();
            }
            pricer.setProductType(GUIUtils.getTableValueAt(jTableSettings, i, 1));
            pricer.setProductType(GUIUtils.getTableValueAt(jTableSettings, i, 2));
            pricer.setPricer(GUIUtils.getTableValueAt(jTableSettings, i, 3));
            tableModel.removeRow(jTablePricers.getSelectedRow());
            removedPricers.add(pricer);
        }
    }//GEN-LAST:event_jButtonRemovePricerActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAddPricer;
    private javax.swing.JButton jButtonAddSetting;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonRemovePricer;
    private javax.swing.JButton jButtonRemoveSetting;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboPEnv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTablePricers;
    private javax.swing.JTable jTableSettings;
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
