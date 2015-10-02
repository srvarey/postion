/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.reports;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionHistoricalMeasure;
import org.gaia.domain.reports.PositionHistoricalMeasure.PositionMeasure;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.Mapping;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.AssetFinder;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.GaiaTopComponent.DecimalFormatRenderer;
import org.gaia.gui.common.HeaderChooser;
import org.gaia.gui.common.LegalEntityFinder;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.trades.TradeUtils;
import org.gaia.gui.utils.CheckBoxHeader;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ExcelAdapter;
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
 * Top component which displays position historical measures.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//PositionHistorical//EN", autostore = false)
@TopComponent.Description(preferredID = "PositionHistoricalTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.ProductHistoricalTopComponent")
@ActionReference(path = "Menu"+MenuManager.PositionHistoricalTopComponentMenu, position = MenuManager.PositionHistoricalTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_PositionHistoricalAction", preferredID = "PositionHistoricalTopComponent")
@Messages({"CTL_PositionHistoricalAction=Position Historical", "CTL_PositionHistoricalTopComponent=Position Historical", "HINT_PositionHistoricalTopComponent=This is a Position Historical window"})
public final class PositionHistoricalTopComponent extends GaiaTopComponent {

    private Map<String, Boolean> listColumns = new HashMap();
    private List<Mapping> mappingList;
    private static final Logger logger = Logger.getLogger(PositionHistoricalTopComponent.class);

    public PositionHistoricalTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_PositionHistoricalTopComponent());
        setToolTipText(Bundle.HINT_PositionHistoricalTopComponent());
        positionHistTable.getColumnModel().getColumn(0).sizeWidthToFit();
    }

    @Override
    public void initContext() {

        jDatechooserStartDate.setDate(DateUtils.getDate());
        jdateChooserEndDate.setDate(DateUtils.getDate());
        positionHistTable.getTableHeader().setReorderingAllowed(false);
        PositionMeasure[] arr = PositionHistoricalMeasure.PositionMeasure.values();
        for (int i = 0; i < arr.length; i++) {
            listColumns.put(arr[i].name(), Boolean.TRUE);
        }
        refreshHeader(listColumns);
        ExcelAdapter myAd = new ExcelAdapter(positionHistTable);
        jLabelCounterpartyId.setVisible(false);
        jLabelEntityId.setVisible(false);
        jLabelProductId.setVisible(false);

        mappingList = (List) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_MAPPINGS_BY_NAME, "marginTypes");
        GUIUtils.packAllColumns(positionHistTable);

        /**
         * ShortCut for date
         */
        DateShortCut.eventkey((JSpinnerDateEditor) jDatechooserStartDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jdateChooserEndDate.getComponent(1));

    }

    public void refreshHeader(Map<String, Boolean> listColumns) {
        Vector<String> tableHeaders = new Vector<>();
        tableHeaders.add(StringUtils.EMPTY_STRING);
        tableHeaders.add("InternalCounterparty");
        tableHeaders.add("Counterparty");
        tableHeaders.add("Product");
        tableHeaders.add("Valuation Date");
        for (Entry e : listColumns.entrySet()) {
            if (e.getValue().equals(Boolean.TRUE)) {
                tableHeaders.add(e.getKey().toString());
            }
        }
        Vector data = new Vector();
        DefaultTableModel tm = new DefaultTableModel(data, tableHeaders);
        positionHistTable.setModel(tm);
    }

    private void generateMarginCall() {
        DefaultTableModel tm = (DefaultTableModel) positionHistTable.getModel();
        TableColumnModel columnModel = positionHistTable.getTableHeader().getColumnModel();
        Vector<Vector<Object>> data = tm.getDataVector();
        ArrayList<Trade> tradeCreated = new ArrayList<>();
        StringBuilder errors = new StringBuilder();
        List<Integer> errorRow = new ArrayList<>();
        if (!jLabelProductId.getText().isEmpty()) {
            Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.parseInt(jLabelProductId.getText()));
            for (Vector<Object> dataVector : data) {
                Boolean line = (Boolean) dataVector.get(0);
                if (line) {
                    BoAccount account = new BoAccount();
                    account.setAccountManager((LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelCounterpartyId.getText())));
                    account.setClient((LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelEntityId.getText())));
                    account.setCurrency(product.getShortName());
                    for (Entry<String, Boolean> entry : listColumns.entrySet()) {
                        if (entry.getValue()) {
                            String quantity = dataVector.get(columnModel.getColumnIndex(entry.getKey())).toString();

                            if (!StringUtils.isEmptyString(quantity)) {
                                String marginType = getMarginType(entry.getKey());
                                if (marginType != null) {
                                    BigDecimal quantityDouble = new BigDecimal(Double.parseDouble(quantity));
                                    if (Double.parseDouble(quantity) != 0) {
                                        tradeCreated.add(TradeUtils.createMarginCall(quantityDouble, marginType, account));
                                    }
                                } else if (!errorRow.contains(data.indexOf(dataVector) + 1)) {
                                    errorRow.add(data.indexOf(dataVector) + 1);
                                    errors.append("Margin Type ");
                                    errors.append(entry.getKey());
                                    errors.append(" not fount row ");
                                    errors.append(data.indexOf(dataVector) + 1);
                                    errors.append("\n");
                                }
                            }
                        }
                    }
                }
            }
        }
        for (Trade trade : tradeCreated) {
            trade = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.STORE_TRADE, trade);
            errors.append("Marin Call  created on ");
            errors.append(trade.getInternalCounterparty().getShortName());
            errors.append(" with id ");
            errors.append(trade.getTradeId());
            errors.append("\n");
        }
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, errors.toString());
        }
    }

    private String getMarginType(String columnName) {
        for (Mapping mapping : mappingList) {
            if (columnName.equalsIgnoreCase(mapping.getKey1())) {
                return mapping.getValue();
            }
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldEntity = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        positionHistTable = new javax.swing.JTable();
        jButtonLoad = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldProductName = new javax.swing.JTextField();
        jButtonFindProduct = new javax.swing.JButton();
        jLabelProductId = new javax.swing.JLabel();
        jButtonSave = new javax.swing.JButton();
        jCheckBoxOpenDays = new javax.swing.JCheckBox();
        jButtonColumns = new javax.swing.JButton();
        jButtonEntity = new javax.swing.JButton();
        jLabelEntityId = new javax.swing.JLabel();
        jButtonExport = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldCounterparty = new javax.swing.JTextField();
        jButtonFindCounterparty = new javax.swing.JButton();
        jLabelCounterpartyId = new javax.swing.JLabel();
        generateMarginButton = new javax.swing.JButton();
        jDatechooserStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());
        jdateChooserEndDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabel1.text")); // NOI18N

        jTextFieldEntity.setEditable(false);
        jTextFieldEntity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabel3.text")); // NOI18N

        positionHistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {StringUtils.EMPTY_STRING,"InternalCounterparty","Counterparty","Product","Valuation Date"
            }
        ));
        jScrollPane1.setViewportView(positionHistTable);

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabel7.text")); // NOI18N

        jTextFieldProductName.setEditable(false);
        jTextFieldProductName.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jButtonFindProduct.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFindProduct, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonFindProduct.text")); // NOI18N
        jButtonFindProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindProductActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jCheckBoxOpenDays.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(jCheckBoxOpenDays, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jCheckBoxOpenDays.text")); // NOI18N

        jButtonColumns.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonColumns, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonColumns.text")); // NOI18N
        jButtonColumns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonColumnsActionPerformed(evt);
            }
        });

        jButtonEntity.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonEntity, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonEntity.text")); // NOI18N
        jButtonEntity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEntityActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelEntityId, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabelEntityId.text")); // NOI18N

        jButtonExport.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExport, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonExport.text")); // NOI18N
        jButtonExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExportActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabel4.text")); // NOI18N

        jTextFieldCounterparty.setEditable(false);
        jTextFieldCounterparty.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldCounterparty.setText(org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jTextFieldCounterparty.text")); // NOI18N

        jButtonFindCounterparty.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFindCounterparty, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jButtonFindCounterparty.text")); // NOI18N
        jButtonFindCounterparty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindCounterpartyActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterpartyId, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.jLabelCounterpartyId.text")); // NOI18N

        generateMarginButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(generateMarginButton, org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.generateMarginButton.text")); // NOI18N
        generateMarginButton.setToolTipText(org.openide.util.NbBundle.getMessage(PositionHistoricalTopComponent.class, "PositionHistoricalTopComponent.generateMarginButton.toolTipText")); // NOI18N
        generateMarginButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generateMarginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateMarginButtonActionPerformed(evt);
            }
        });

        jDatechooserStartDate.setBackground(new java.awt.Color(254, 252, 254));

        jdateChooserEndDate.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButtonExport)
                            .addGap(697, 697, 697)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextFieldEntity)
                                        .addComponent(jTextFieldCounterparty, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                    .addComponent(jTextFieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jButtonEntity, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabelEntityId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButtonFindProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonFindCounterparty, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelCounterpartyId, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGap(0, 0, 0)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jCheckBoxOpenDays)
                                    .addGap(284, 284, 284)
                                    .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jdateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jDatechooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButtonColumns)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(generateMarginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldEntity, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonEntity, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCounterparty)
                            .addComponent(jButtonFindCounterparty))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonFindProduct)
                            .addComponent(jTextFieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelEntityId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(generateMarginButton)
                                        .addComponent(jButtonColumns))
                                    .addComponent(jDatechooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jdateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jCheckBoxOpenDays)
                                    .addComponent(jButtonLoad)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelCounterpartyId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelProductId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonExport)
                    .addComponent(jButtonSave))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        // LOAD
        List<PositionHistoricalMeasure> listHistoricalMeasures;
        Date startDate = DateUtils.removeTime(jDatechooserStartDate.getDate());
        Date endDate = DateUtils.removeTime(jdateChooserEndDate.getDate());
        Integer productId;
        Integer ctpId;
        Product product = null;
        LegalEntity internalCounterparty = null;
        LegalEntity ctp = null;
        if (!jLabelProductId.getText().isEmpty()) {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.parseInt(jLabelProductId.getText()));
        }
        if (!jLabelEntityId.getText().isEmpty()) {
            internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelEntityId.getText()));
        }
        if (!jLabelCounterpartyId.getText().isEmpty()) {
            ctp = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelCounterpartyId.getText()));
        }
        if (product != null && internalCounterparty != null && ctp != null) {
            productId = product.getId();
            ctpId = ctp.getLegalEntityId();
            listHistoricalMeasures = (List) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_HISTORICAL_MEASURES_ON_PERIOD, productId, internalCounterparty.getLegalEntityId(), ctpId, null, null, startDate, endDate);
        } else {
            return;
        }
        if (listHistoricalMeasures == null) {
            return;
        }
        Vector tableData = new Vector();
        Date displayedDate = startDate;
        while (displayedDate.before(endDate) || displayedDate.equals(endDate)) {
            Vector row = new Vector();
            row.add(Boolean.FALSE);
            row.add(internalCounterparty.getShortName());
            row.add(ctp.getShortName());
            row.add(product.getShortName());
            row.add(dateFormatter.format(displayedDate));
            for (Entry<String, Boolean> entry : listColumns.entrySet()) {
                if (entry.getValue()) {
                    String columnMeasure = entry.getKey();
                    boolean found = false;
                    for (PositionHistoricalMeasure positionHistoricalMeasure : listHistoricalMeasures) {
                        if (positionHistoricalMeasure.getPositionDate().equals(displayedDate)) {
                            if (positionHistoricalMeasure.getMeasureName().equals(columnMeasure) && !found) {
                                row.add(positionHistoricalMeasure.getMeasureValue());
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        row.add(StringUtils.EMPTY_STRING);
                    }
                }
            }
            tableData.add(row);
            if (jCheckBoxOpenDays.isSelected()) {
                displayedDate = DateUtils.addOpenDay(displayedDate, 1);
            } else {
                displayedDate = DateUtils.addCalendarDay(displayedDate, 1);
            }
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableData, GUIUtils.getHeader(positionHistTable));
        positionHistTable.setModel(tableModel);

        for (int col = 5; col < positionHistTable.getColumnCount(); col++) {
            positionHistTable.getColumnModel().getColumn(col).setCellRenderer(new DecimalFormatRenderer("0.00"));
            GUIUtils.packColumn(positionHistTable, col, 2);
        }
        TableColumn tableColumn = positionHistTable.getColumnModel().getColumn(0);
        tableColumn.setCellEditor(positionHistTable.getDefaultEditor(Boolean.class));
        tableColumn.setCellRenderer(positionHistTable.getDefaultRenderer(Boolean.class));
        tableColumn.setHeaderRenderer(new CheckBoxHeader(new MyItemListener()));
        tableColumn.sizeWidthToFit();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonFindProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindProductActionPerformed
        // find product

        final AssetFinder af = new AssetFinder(ProductTypeUtil.loadTradeableAndCashTypes());

        NotifyDescriptor nd = new NotifyDescriptor(af, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = af.getAssetId();

            if (productId != null) {
                Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId.intValue());
                jLabelProductId.setText(productId.toString());
                jTextFieldProductName.setText(product.getShortName());
            }

        }
    }//GEN-LAST:event_jButtonFindProductActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed

        DefaultTableModel tableModel = (DefaultTableModel) positionHistTable.getModel();
        TableColumnModel columnModel = positionHistTable.getTableHeader().getColumnModel();
        Vector<Vector<Object>> data = tableModel.getDataVector();
        Product product;
        Integer internalCounterpartyId;
        Integer counterpartyId;
        String svaldate;

        Date valdate;
        StringBuilder errorList = new StringBuilder();

        try {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.parseInt(jLabelProductId.getText()));
            internalCounterpartyId = Integer.parseInt(jLabelEntityId.getText());
            counterpartyId = Integer.parseInt(jLabelCounterpartyId.getText());
            List<Integer> errorRow = new ArrayList<>();
            for (Vector<Object> dataVector : data) {
                svaldate = (String) dataVector.get(columnModel.getColumnIndex("Valuation Date"));
                valdate = dateFormatter.parse(svaldate);

                List<PositionHistoricalMeasure> positionHistoricalMeasurelist;
                for (Entry<String, Boolean> entry : listColumns.entrySet()) {

                    String val = dataVector.get(columnModel.getColumnIndex(entry.getKey())).toString();
                    if (!StringUtils.isEmptyString(val)) {
                        try {
                            positionHistoricalMeasurelist = (List<PositionHistoricalMeasure>) DAOCallerAgent.callMethod(PositionBuilder.class,
                                    PositionBuilder.GET_POSITION_HISTORICAL_MEASURES_BY_MEASURE, product.getProductId(), internalCounterpartyId,
                                    counterpartyId, Boolean.TRUE, null, valdate, entry.getKey());
                            if (positionHistoricalMeasurelist.isEmpty()) {
                                createorUpdatePositionHistorical(null, product, internalCounterpartyId, counterpartyId, errorRow, data,
                                        dataVector, errorList, entry, val, valdate);
                            } else {
                                for (PositionHistoricalMeasure positionHistoricalMeasure : positionHistoricalMeasurelist) {
                                    createorUpdatePositionHistorical(positionHistoricalMeasure, product, internalCounterpartyId, counterpartyId,
                                            errorRow, data, dataVector, errorList, entry, val, valdate);

                                }
                            }
                        } catch (Exception e) {
                            errorRow.add(data.indexOf(dataVector) + 1);
                            errorList.append("Unparseable number :").append(val).append("\n");
                        }
                    }
                }
            }
            if (errorList.length() <= 0) {
                errorList.append("OK.");
            }
            JOptionPane.showMessageDialog(this, errorList.toString());
        } catch (NumberFormatException | ParseException | HeadlessException e) {
            logger.error(StringUtils.formatErrorMessageWithCause(e));
        }

    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void createorUpdatePositionHistorical(PositionHistoricalMeasure positionHistoricalMeasure, Product product, Integer internalCounterpartyId, Integer counterpartyId, List<Integer> errorRow, Vector<Vector<Object>> data, Vector<Object> dataVector, StringBuilder errorList, Entry<String, Boolean> entry, String val, Date valdate) {
        try {
            List<Position> positionList = (List<Position>) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITIONS, product.getProductId(), internalCounterpartyId, counterpartyId, Boolean.TRUE, null);
            if (positionList.isEmpty()) {
                if (!errorRow.contains(data.indexOf(dataVector) + 1)) {
                    errorRow.add(data.indexOf(dataVector) + 1);
                    errorList.append("Position missing at row :").append(data.indexOf(dataVector) + 1).append("\n");
                }
            } else {
                for (Position position : positionList) {
                    if (positionHistoricalMeasure == null) {
                        positionHistoricalMeasure = new PositionHistoricalMeasure();
                        positionHistoricalMeasure.setCurrency(product.getShortName());
                        positionHistoricalMeasure.setMeasureName(entry.getKey());
                        positionHistoricalMeasure.setPosition(position);
                    }
                    positionHistoricalMeasure.setMeasureValue(new BigDecimal(val));
                    positionHistoricalMeasure.setProvider("internal");
                    positionHistoricalMeasure.setPositionDate(valdate);
                    DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.STORE_POSITION_HISTORICAL_MEASURE, positionHistoricalMeasure);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessageWithCause(e));
        }

    }
    private void jButtonColumnsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonColumnsActionPerformed
        final HeaderChooser hc = new HeaderChooser(listColumns);

        NotifyDescriptor nd = new NotifyDescriptor(hc, "Column Chooser", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            listColumns = hc.getSelectedColumns();
            refreshHeader(listColumns);
        }

    }//GEN-LAST:event_jButtonColumnsActionPerformed

    private void jButtonEntityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEntityActionPerformed
        // find entity

        final LegalEntityFinder lef = new LegalEntityFinder(null);

        NotifyDescriptor nd = new NotifyDescriptor(lef, "Entity Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer entityId = lef.getLegalEntityId();

            if (entityId != null) {
                LegalEntity le = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, entityId);
                jLabelEntityId.setText(entityId.toString());
                jTextFieldEntity.setText(le.getShortName());
            }
        }
    }//GEN-LAST:event_jButtonEntityActionPerformed

    private void jButtonExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExportActionPerformed

        List<PositionHistoricalMeasure> listHistoricalMeasures = null;
        Date startDate = DateUtils.removeTime(jDatechooserStartDate.getDate());
        Date endDate = DateUtils.removeTime(jdateChooserEndDate.getDate());
        Integer productId;
        Product product = null;
        LegalEntity internalCounterparty = null;
        LegalEntity ctp = null;
        XMLExporter exportxml = new XMLExporter();
        if (!StringUtils.isEmptyString(jLabelProductId.getText())) {
            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, Integer.parseInt(jLabelProductId.getText()));
        }
        if (!StringUtils.isEmptyString(jLabelEntityId.getText())) {
            internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelEntityId.getText()));
        }
        if (!StringUtils.isEmptyString(jLabelCounterpartyId.getText())) {
            ctp = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, Integer.parseInt(jLabelCounterpartyId.getText()));
        }
        for (Entry<String, Boolean> entry : listColumns.entrySet()) {

            if (product != null && internalCounterparty != null && ctp != null) {
                productId = product.getId();
                listHistoricalMeasures = (List) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_HISTORICAL_MEASURES_BY_MEASURE,
                        productId, internalCounterparty.getLegalEntityId(), ctp.getLegalEntityId(),
                        Boolean.TRUE, null, startDate, entry.getKey());

            }
        }
        exportxml.export(listHistoricalMeasures, this);
    }//GEN-LAST:event_jButtonExportActionPerformed

    private void jButtonFindCounterpartyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindCounterpartyActionPerformed
        // find entity

        final LegalEntityFinder lef = new LegalEntityFinder(null);

        NotifyDescriptor nd = new NotifyDescriptor(lef, "Entity Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer entityId = lef.getLegalEntityId();

            if (entityId != null) {
                LegalEntity le = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, entityId.intValue());
                jLabelCounterpartyId.setText(entityId.toString());
                jTextFieldCounterparty.setText(le.getShortName());
            }
        }
    }//GEN-LAST:event_jButtonFindCounterpartyActionPerformed

    private void generateMarginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateMarginButtonActionPerformed
        generateMarginCall();
    }//GEN-LAST:event_generateMarginButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton generateMarginButton;
    private javax.swing.JButton jButtonColumns;
    private javax.swing.JButton jButtonEntity;
    private javax.swing.JButton jButtonExport;
    private javax.swing.JButton jButtonFindCounterparty;
    private javax.swing.JButton jButtonFindProduct;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JCheckBox jCheckBoxOpenDays;
    private com.toedter.calendar.JDateChooser jDatechooserStartDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabelCounterpartyId;
    private javax.swing.JLabel jLabelEntityId;
    private javax.swing.JLabel jLabelProductId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCounterparty;
    private javax.swing.JTextField jTextFieldEntity;
    private javax.swing.JTextField jTextFieldProductName;
    private com.toedter.calendar.JDateChooser jdateChooserEndDate;
    private javax.swing.JTable positionHistTable;
    // End of variables declaration//GEN-END:variables

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

    class MyItemListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0, y = positionHistTable.getRowCount(); x < y; x++) {
                positionHistTable.setValueAt(checked, x, 0);
            }
        }
    }
}
