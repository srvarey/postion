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
package org.gaia.gui.assets;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.ScheduleBuilder;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.observables.MarketQuote.QuotationType;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Schedule;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.sDecimalFormat;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays product schedules.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//Schedule//EN", autostore = false)
@TopComponent.Description(preferredID = "ScheduleTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.ScheduleTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_ScheduleAction")
@Messages({"CTL_ScheduleAction=Schedule", "CTL_ScheduleTopComponent=Schedule", "HINT_ScheduleTopComponent=This is a Schedule window"})
public final class ScheduleTopComponent extends GaiaTopComponent {

    public ArrayList<Integer> editableRows;
    public Schedule schedule;
    public String[] header = {"Start Date", "End Date", "Payment Date", "Reset Date", "Notional", "Fixing", "Spread", "Amount", "Currency", "Custom", "Fixed", "Fixing Instrument"};
    public Trade trade;
    public Product rateIndex;
    private static final Logger logger = Logger.getLogger(ScheduleTopComponent.class);
    List<String> currencies;
    List<String> truefalse;
    public Product product;

    public ScheduleTopComponent() {
        initComponents();
        setName(Bundle.CTL_ScheduleTopComponent());
        setToolTipText(Bundle.HINT_ScheduleTopComponent());
        truefalse = new ArrayList();
        truefalse.add(Boolean.TRUE.toString());
        truefalse.add(Boolean.FALSE.toString());
    }

    public void setTrade(Trade trade_) {
        try {
            trade = trade_;
            schedule = (Schedule) DAOCallerAgent.callMethod(ScheduleBuilder.class, ScheduleBuilder.BUILD_SCHEDULE_FROM_TRADE, trade);
            product = trade.getProduct();

            editableRows = new ArrayList();
            int i = 0;
            if (schedule != null) {
                while (i < schedule.getScheduleLines().size()) {
                    if (schedule.getScheduleLines().get(i).isCustom()) {
                        editableRows.add(i);
                    }
                    i++;
                }
            }
            MyTableModel tableModel = new MyTableModel(editableRows);
            tableModel.setColumnIdentifiers(header);
            jTableScheduleLines.setModel(tableModel);
            jTableScheduleLines.repaint();
            currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            displaySchedule();
            displayScheduleDetail();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * add date from rows
     */
    public void displaySchedule() {

        DefaultTableModel tableModel = (DefaultTableModel) jTableScheduleLines.getModel();
        GUIUtils.clearTableModel(tableModel);

        if (schedule != null) {
            for (ScheduleLine line : schedule.getScheduleLines()) {
                BigDecimal mult = BigDecimal.ONE;
                if (line.getFixingProduct() != null) {
                    mult = MarketQuote.QuotationType.getMult(line.getFixingProduct().getQuotationType());
                }
                Vector row = new Vector();
                row.add(line.getStartDate());
                row.add(line.getEndDate());
                row.add(line.getPaymentDate());
                row.add(line.getResetDate());
                row.add(line.getNotional());
                row.add(line.getFixing().multiply(mult));
                row.add(line.getSpread().multiply(mult));
                row.add(line.getPaymentAmount());
                row.add(line.getCurrency());
                row.add(line.isCustom());
                row.add(line.isFixed());
                if (line.getFixingProduct() != null) {
                    row.add(line.getFixingProduct().getShortName());
                }

                addRow(row);
            }
            GUIUtils.packAllColumns(jTableScheduleLines);
        }
    }

    private void addRow(Vector row) {
        ((DefaultTableModel) jTableScheduleLines.getModel()).addRow(row);
        //dates
        GUIUtils.setDateEditor(jTableScheduleLines, 0);
        GUIUtils.setDateEditor(jTableScheduleLines, 1);
        GUIUtils.setDateEditor(jTableScheduleLines, 2);
        GUIUtils.setDateEditor(jTableScheduleLines, 3);

        // notional
        GUIUtils.setNumberEditor(jTableScheduleLines, 4, sDecimalFormat);
        // rate
        GUIUtils.setNumberEditor(jTableScheduleLines, 5, "#,##0.0000");
        // spread
        GUIUtils.setNumberEditor(jTableScheduleLines, 6, "#,##0.0000");
        // amount
        GUIUtils.setNumberEditor(jTableScheduleLines, 7, sDecimalFormat);
        // currency column
        GUIUtils.setCombo(jTableScheduleLines, 8, currencies);

        // true / false
        GUIUtils.setCombo(jTableScheduleLines, 9, truefalse);
        GUIUtils.setCombo(jTableScheduleLines, 10, truefalse);

    }

    @Override
    public String getDisplayName() {
        String _displayName = super.getDisplayName();
        if (trade != null && product != null) {
            Integer id = null;
            if (trade.getId() != null) {
                id = trade.getId();
            } else if (product != null && product.getId() != null) {
                id = product.getId();
            }
            _displayName = product.getProductType() + StringUtils.SPACE + id;
        }
        return "Schedule of " + _displayName;
    }

    public class MyTableModel extends DefaultTableModel {

        private ArrayList<Integer> editableRows;

        /**
         * list editable rows
         *
         * @param editableRows
         */
        public MyTableModel(ArrayList<Integer> editableRows) {
            super();
            this.editableRows = editableRows;
        }

        @Override
        /**
         * editable cell
         */
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            int i = 0;
            while (i < editableRows.size()) {
                if (rowIndex == editableRows.get(i)) {
                    if (columnIndex < 11) {
                        return true;
                    }
                }
                i++;
            }
            return false;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableScheduleLines = new javax.swing.JTable();
        jButtonModifySelected = new javax.swing.JButton();
        jButtonSaveUpdates = new javax.swing.JButton();
        jButtonUncustomize = new javax.swing.JButton();
        jButtonGenerate = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableScheduleDetail = new javax.swing.JTable();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jTableScheduleLines.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Start Date", "End Date", "Payment Date", "Reset Date", "Notional", "Fixing", "Spread", "Amount", "Currency", "Custom", "Fixed", "Fixing Instrument"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTableScheduleLines.getTableHeader().setReorderingAllowed(false);
        jTableScheduleLines.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableScheduleLinesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableScheduleLines);

        jButtonModifySelected.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonModifySelected, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButtonModifySelected.text")); // NOI18N
        jButtonModifySelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifySelectedActionPerformed(evt);
            }
        });

        jButtonSaveUpdates.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveUpdates, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButtonSaveUpdates.text")); // NOI18N
        jButtonSaveUpdates.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveUpdatesActionPerformed(evt);
            }
        });

        jButtonUncustomize.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonUncustomize, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButtonUncustomize.text")); // NOI18N
        jButtonUncustomize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUncustomizeActionPerformed(evt);
            }
        });

        jButtonGenerate.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonGenerate, org.openide.util.NbBundle.getMessage(ScheduleTopComponent.class, "ScheduleTopComponent.jButtonGenerate.text")); // NOI18N
        jButtonGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerateActionPerformed(evt);
            }
        });

        jTableScheduleDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Rate"
            }
        ));
        jTableScheduleDetail.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTableScheduleDetail.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTableScheduleDetail);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(272, 272, 272)
                        .addComponent(jButtonModifySelected)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonUncustomize)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSaveUpdates)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonModifySelected)
                        .addComponent(jButtonSaveUpdates)
                        .addComponent(jButtonUncustomize))
                    .addComponent(jButtonGenerate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
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

    private void jButtonModifySelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifySelectedActionPerformed

        int selected = jTableScheduleLines.getSelectedRow();
        if (selected >= 0) {
            editableRows.add(selected);
            schedule.getScheduleLines().get(jTableScheduleLines.getSelectedRow()).setCustom(true);
            displaySchedule();
            jTableScheduleLines.setRowSelectionInterval(selected, selected);
        }
    }//GEN-LAST:event_jButtonModifySelectedActionPerformed

    private void jButtonSaveUpdatesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveUpdatesActionPerformed

        if (schedule != null && schedule.getScheduleLines() != null && schedule.getScheduleLines().size() > 0) {
            if (schedule.getScheduleLines().get(0).getProduct().getProductId() == null) {
                JOptionPane.showMessageDialog(this, "Please save product first");
                return;
            }
        }
        int i = 0;
        for (ScheduleLine scheduleLine : schedule.getScheduleLines()) {
            if (editableRows.contains(i)) {
                try {
                    BigDecimal mult = BigDecimal.ONE;
                    if (scheduleLine.getFixingProduct() != null) {
                        QuotationType quotationType = MarketQuote.QuotationType.getQuotationTypeByName(scheduleLine.getFixingProduct().getQuotationType());
                        if (quotationType != null) {
                            mult = quotationType.getMult();
                        }
                    }
                    scheduleLine.setStartDate((Date) jTableScheduleLines.getValueAt(i, 0));
                    scheduleLine.setEndDate((Date) jTableScheduleLines.getValueAt(i, 1));
                    scheduleLine.setPaymentDate((Date) jTableScheduleLines.getValueAt(i, 2));
                    scheduleLine.setResetDate((Date) jTableScheduleLines.getValueAt(i, 3));
                    scheduleLine.setNotional(BigDecimal.valueOf(numberFormat.parse(GUIUtils.getTableValueAt(jTableScheduleLines, i, 4)).doubleValue()));
                    scheduleLine.setFixing(BigDecimal.valueOf(numberFormat.parse(GUIUtils.getTableValueAt(jTableScheduleLines, i, 5)).doubleValue()).divide(mult, 20, RoundingMode.UP));
                    scheduleLine.setSpread(BigDecimal.valueOf(numberFormat.parse(GUIUtils.getTableValueAt(jTableScheduleLines, i, 6)).doubleValue()).divide(mult, 20, RoundingMode.UP));
                    scheduleLine.setPaymentAmount(BigDecimal.valueOf(numberFormat.parse(GUIUtils.getTableValueAt(jTableScheduleLines, i, 7)).doubleValue()));
                    scheduleLine.setCurrency(GUIUtils.getTableValueAt(jTableScheduleLines, i, 8));
                    scheduleLine.setFixed(Boolean.parseBoolean(GUIUtils.getTableValueAt(jTableScheduleLines, i, 10)));
                    scheduleLine.setCustom(true);
                    Integer id = (Integer) DAOCallerAgent.callMethod(ScheduleAccessor.class, ScheduleAccessor.STORE_SCHEDULE_LINE, scheduleLine);
                    scheduleLine.setScheduleLineId(id);
                } catch (Exception e) {
                    new ErrorMessageUI("Invalid data").setVisible(true);
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
            i++;
        }
        JOptionPane.showMessageDialog(this, "OK.");
    }//GEN-LAST:event_jButtonSaveUpdatesActionPerformed

    private void jButtonUncustomizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUncustomizeActionPerformed

        int selected = jTableScheduleLines.getSelectedRow();
        if (selected >= 0) {
            ScheduleLine line = schedule.getScheduleLines().get(selected);
            DAOCallerAgent.callMethod(ScheduleAccessor.class, ScheduleAccessor.DELETE_SCHEDULE_LINE, line);
            Schedule schedule_ = (Schedule) DAOCallerAgent.callMethod(ScheduleBuilder.class, ScheduleBuilder.BUILD_SCHEDULE_FROM_TRADE, trade);
            schedule.getScheduleLines().set(selected, schedule_.getScheduleLines().get(selected));
            editableRows.remove(new Integer(selected));
            line.setCustom(false);
            displaySchedule();
            jTableScheduleLines.setRowSelectionInterval(selected, selected);
        }
    }//GEN-LAST:event_jButtonUncustomizeActionPerformed

    private void jButtonGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerateActionPerformed
        try {
            DAOCallerAgent.callMethod(FlowAccessor.class, FlowAccessor.GENERATE_AND_STORE_SCHEDULE_FLOWS_IF_NEEDED, trade);
            JOptionPane.showMessageDialog(this, "OK.");
            schedule = (Schedule) DAOCallerAgent.callMethod(ScheduleBuilder.class, ScheduleBuilder.BUILD_SCHEDULE_FROM_TRADE, trade);
            displayScheduleDetail();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonGenerateActionPerformed

    private void jTableScheduleLinesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableScheduleLinesMouseClicked
        displayScheduleDetail();
    }//GEN-LAST:event_jTableScheduleLinesMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenerate;
    private javax.swing.JButton jButtonModifySelected;
    private javax.swing.JButton jButtonSaveUpdates;
    private javax.swing.JButton jButtonUncustomize;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableScheduleDetail;
    private javax.swing.JTable jTableScheduleLines;
    // End of variables declaration//GEN-END:variables

    /**
     * table to display detail of calculated Schedule ,display couple date and
     * rate
     *
     */
    public void displayScheduleDetail() {
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        ScheduleLine scheduleLine;
        Integer underlyingId;
        Date startDate;
        Date endfDate;

        /**
         * Clear table jTableScheduledetail
         */
        DefaultTableModel tableModel = (DefaultTableModel) jTableScheduleDetail.getModel();
        tableModel.getDataVector().removeAllElements();
        tableModel.fireTableDataChanged();
        int rownum = jTableScheduleLines.getSelectedRow();

        if (rownum >= 0) {

            scheduleLine = schedule.getScheduleLines().get(rownum);
            if (scheduleLine.getProduct().getScheduler()!=null && scheduleLine.getProduct().getScheduler().getIsCompound()) {
                startDate = scheduleLine.getStartDate();
                endfDate = scheduleLine.getEndDate();
                if (!scheduleLine.isFixed()) {
                    Product _product = scheduleLine.getProduct();
                    if (_product.loadSingleUnderlying() != null) {
                        underlyingId = _product.loadSingleUnderlying().getProductId();
                        LinkedHashMap< Date, Double> mapRateprevious = (LinkedHashMap< Date, Double>) DAOCallerAgent.callMethod(MarketQuoteAccessor.class,
                                MarketQuoteAccessor.GET_DETAIL_QUOTE_VALUE, underlyingId, startDate, endfDate, quoteSet);

                        for (Map.Entry< Date, Double> rateprevious : mapRateprevious.entrySet()) {
                            Date datedetail;
                            Double ratedetail;
                            datedetail = rateprevious.getKey();
                            ratedetail = rateprevious.getValue();
                            Object[] row = {dateFormatter.format(datedetail), pointDecimalFormat.format(ratedetail)};
                            tableModel.addRow(row);
                            setDisplayName(getDisplayName());
                        }
                    }
                }
            }
        }
    }

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
