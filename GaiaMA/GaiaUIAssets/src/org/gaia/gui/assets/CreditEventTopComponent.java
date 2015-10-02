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

import com.l2fprod.common.swing.renderer.DateRenderer;
import com.toedter.calendar.JDateChooserCellEditor;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.trades.events.CreditEventApply;
import org.gaia.dao.trades.events.EventEngine;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.legalEntity.CreditEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.utils.GUIUtils;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.autocomplete.ComboBoxCellEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * @author Benjamin Frerejean
 */
/**
 * Top component which displays credit events.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.legalentity//CreditEvent//EN", autostore = false)
@TopComponent.Description(preferredID = "CreditEventTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.legalentity.CreditEventTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_CreditEventAction", preferredID = "CreditEventTopComponent")
@Messages({
    "CTL_CreditEventAction=Credit Event",
    "CTL_CreditEventTopComponent=Credit Event",
    "HINT_CreditEventTopComponent=This is the Credit Event window"
})
public final class CreditEventTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(CreditEventTopComponent.class);
    private final DefaultTableModel model;
    private String creditEntityName;
    private LegalEntity creditEntity;
    private List<CreditEvent> removed;
    private static final BigDecimal recoRateMultiplier = NumberUtils.BIGDECIMAL_100;
    private static final int iSelect = 7;
    List<String> creditEventNames;

    public CreditEventTopComponent() {
        initComponents();
        setName(Bundle.CTL_CreditEventTopComponent());
        setToolTipText(Bundle.HINT_CreditEventTopComponent());
        model = (DefaultTableModel) eventsTable.getModel();
        setUpColumnEditor(eventsTable);
        removed = new ArrayList();
    }

    public void setCreditEntity(String _creditEntityName) {
        creditEntityName = _creditEntityName;
        creditEntityLabel.setText(creditEntityName);
        creditEntity = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, creditEntityName);
        setDisplayName(creditEntityName + " Credit Event");
        List<CreditEvent> creditEvents = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.LOAD_CREDIT_ENTITY_EVENTS, creditEntityName);

        if (creditEvents != null) {
            for (CreditEvent creditEvent : creditEvents) {
                Object[] row = new Object[]{creditEvent.getCreditEventId(),
                    creditEvent.getCreditEvent(),
                    creditEvent.getIsHard() ? "Hard" : "Soft",
                    creditEvent.getReceptionDate(),
                    creditEvent.getDefaultDate(),
                    creditEvent.getRecoveryRate().multiply(recoRateMultiplier),
                    creditEvent.getPaymentDate(),
                    false
                };
                GUIUtils.addTableRow(eventsTable, row);
            }
        }
    }

    /**
     * make a cellRender and editor for a different JTable columns
     *
     * @param optionTable
     */
    public void setUpColumnEditor(JTable optionTable) {
        JXComboBox combo = new JXComboBox();
        /**
         * list of creditEvents
         */
        creditEventNames = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_CREDIT_EVENTS);
        GUIUtils.fillCombo(combo, creditEventNames);
        optionTable.getColumnModel().getColumn(model.findColumn("Credit event")).setCellEditor(new ComboBoxCellEditor(combo));

        optionTable.getColumnModel().getColumn(model.findColumn("Default Date")).setCellEditor(new JDateChooserCellEditor());
        optionTable.getColumnModel().getColumn(model.findColumn("Default Date")).setCellRenderer(new DateRenderer(dateFormat));

        optionTable.getColumnModel().getColumn(model.findColumn("Receive Date")).setCellEditor(new JDateChooserCellEditor());
        optionTable.getColumnModel().getColumn(model.findColumn("Receive Date")).setCellRenderer(new DateRenderer(dateFormat));

        optionTable.getColumnModel().getColumn(model.findColumn("Payment Date")).setCellEditor(new JDateChooserCellEditor());
        optionTable.getColumnModel().getColumn(model.findColumn("Payment Date")).setCellRenderer(new DateRenderer(dateFormat));

        combo = new JXComboBox();
        combo.addItem("Hard");
        combo.addItem("Soft");
        optionTable.getColumnModel().getColumn(model.findColumn("Type")).setCellEditor(new ComboBoxCellEditor(combo));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        eventsScrollPane = new javax.swing.JScrollPane();
        eventsTable = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        saveButton = new javax.swing.JButton();
        addRowButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        removeRowButton = new javax.swing.JButton();
        productsScrollPane = new javax.swing.JScrollPane();
        productsTable = new javax.swing.JTable();
        creditEntityLabel = new javax.swing.JLabel();
        ApplyButton = new javax.swing.JButton();
        loadButton = new javax.swing.JButton();

        setBackground(java.awt.Color.white);

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        eventsScrollPane.setName("eventsScrollPane"); // NOI18N

        eventsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Credit event", "Type", "Receive Date", "Default Date", "Recovery Rate", "Payment Date", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Double.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventsTable.setName("eventsTable"); // NOI18N
        eventsScrollPane.setViewportView(eventsTable);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setMargin(new java.awt.Insets(0, 5, 0, 5));

        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.saveButton.text")); // NOI18N
        saveButton.setMargin(new java.awt.Insets(2, 20, 2, 20));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(saveButton);

        addRowButton.setBackground(new java.awt.Color(255, 204, 204));
        org.openide.awt.Mnemonics.setLocalizedText(addRowButton, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.addRowButton.text")); // NOI18N
        addRowButton.setToolTipText(org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.addRowButton.toolTipText")); // NOI18N
        addRowButton.setFocusable(false);
        addRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRowButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addRowButton);
        jToolBar1.add(jSeparator2);

        removeRowButton.setBackground(new java.awt.Color(255, 204, 204));
        org.openide.awt.Mnemonics.setLocalizedText(removeRowButton, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.removeRowButton.text")); // NOI18N
        removeRowButton.setToolTipText(org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.removeRowButton.toolTipText")); // NOI18N
        removeRowButton.setFocusable(false);
        removeRowButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeRowButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeRowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeRowButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeRowButton);

        productsScrollPane.setName("productsScrollPane"); // NOI18N

        productsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Product", "Maturity", "Event Id", "Event", "Select"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        productsTable.setName("productsTable"); // NOI18N
        productsScrollPane.setViewportView(productsTable);

        creditEntityLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        creditEntityLabel.setForeground(new java.awt.Color(51, 51, 255));
        org.openide.awt.Mnemonics.setLocalizedText(creditEntityLabel, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.creditEntityLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ApplyButton, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.ApplyButton.text")); // NOI18N
        ApplyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApplyButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(loadButton, org.openide.util.NbBundle.getMessage(CreditEventTopComponent.class, "CreditEventTopComponent.loadButton.text")); // NOI18N
        loadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ApplyButton)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(27, 27, 27)
                            .addComponent(creditEntityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(eventsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 879, Short.MAX_VALUE)
                        .addComponent(productsScrollPane)
                        .addComponent(loadButton, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creditEntityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(eventsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loadButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ApplyButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        save();
    }//GEN-LAST:event_saveButtonActionPerformed

    public void save() {
        for (int row = 0; row < eventsTable.getRowCount(); row++) {
            saveEventAtRow(row);
        }
        for (CreditEvent event : removed) {
            DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.DELETE_CREDIT_EVENT, event);
        }
        removed.clear();
    }

    public void saveEventAtRow(int row) {
        try {
            CreditEvent event = null;
            if (!GUIUtils.getTableValueAt(eventsTable, row, 0).isEmpty()) {
                event = (CreditEvent) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                        LegalEntityAccessor.GET_CREDIT_EVENT_BY_ID,
                        Integer.parseInt(GUIUtils.getTableValueAt(eventsTable, row, 0)));
            }
            if (event == null) {
                event = new CreditEvent();
            }
            event.setIssuer(creditEntity);
            event.setCreditEvent(GUIUtils.getTableValueAt(eventsTable, row, 1));
            event.setIsHard("Hard".equalsIgnoreCase(GUIUtils.getTableValueAt(eventsTable, row, 2)));
            event.setReceptionDate(GUIUtils.getDateTableValueAt(eventsTable, row, 3));
            event.setDefaultDate(DateUtils.removeTime(GUIUtils.getDateTableValueAt(eventsTable, row, 4)));
            event.setRecoveryRate(BigDecimal.valueOf(Double.parseDouble(GUIUtils.getTableValueAt(eventsTable, row, 5))).divide(recoRateMultiplier, 20, RoundingMode.UP));
            event.setPaymentDate(DateUtils.removeTime(GUIUtils.getDateTableValueAt(eventsTable, row, 6)));
            if (event.getCreditEventId() == null) {
                Integer eventId = (Integer) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.SAVE_CREDIT_EVENT, event);
                event.setCreditEventId(eventId);
                model.setValueAt(event.getCreditEventId(), row, 0);
            } else {
                DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.STORE_CREDIT_EVENT, event);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    private void addRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addRowButtonActionPerformed
        Vector newRow = new Vector();
        newRow.add(null);
        newRow.add(creditEventNames.get(0));
        newRow.add("Hard");
        newRow.add(DateUtils.getDate());
        newRow.add(DateUtils.getDate());
        newRow.add(40);
        newRow.add(DateUtils.getDate());
        model.addRow(newRow);
    }//GEN-LAST:event_addRowButtonActionPerformed

    private void removeRowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeRowButtonActionPerformed
        if (eventsTable.getSelectedRow() >= 0) {
            if (!GUIUtils.getTableValueAt(eventsTable, eventsTable.getSelectedRow(), 0).isEmpty()) {
                CreditEvent event = (CreditEvent) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                        LegalEntityAccessor.GET_CREDIT_EVENT_BY_ID,
                        Integer.parseInt(GUIUtils.getTableValueAt(eventsTable, eventsTable.getSelectedRow(), 0)));

                if (event != null) {
                    if (removed == null) {
                        removed = new ArrayList();
                    }
                    removed.add(event);
                }
            }
            model.removeRow(eventsTable.getSelectedRow());
        }
    }//GEN-LAST:event_removeRowButtonActionPerformed

    private void ApplyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApplyButtonActionPerformed
        String message = "Events applied:";
        for (int row = 0; row < productsTable.getRowCount(); row++) {
            try {
                if (Boolean.parseBoolean(GUIUtils.getTableValueAt(productsTable, row, 5))) {
                    Integer productId = Integer.parseInt(GUIUtils.getTableValueAt(productsTable, row, 0));
                    Integer eventId = Integer.parseInt(GUIUtils.getTableValueAt(productsTable, row, 3));
                    String ids = (String) DAOCallerAgent.callMethod(EventEngine.class, EventEngine.APPLY_EVENT_ON_PRODUCT, CreditEventApply.class, eventId, productId);
                    if (!ids.isEmpty()) {
                        message += StringUtils.getNewLine() + "Id " + eventId + " on product " + productId + ", trade : " + ids;
                    } else {
                        message += StringUtils.getNewLine() + "Id " + eventId + " on product " + productId + ", no trade";
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        logger.info(message);
        JOptionPane.showMessageDialog(this, message);
        load();
    }//GEN-LAST:event_ApplyButtonActionPerformed

    private void loadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadButtonActionPerformed
        load();
    }//GEN-LAST:event_loadButtonActionPerformed

    public void load() {

        DefaultTableModel productTableModel = (DefaultTableModel) productsTable.getModel();
        GUIUtils.clearTableModel(productTableModel);

        for (int r = 0; r < eventsTable.getRowCount(); r++) {

            if (GUIUtils.getTableValueAt(eventsTable, r, 0).isEmpty()) {
                saveEventAtRow(r);
            }
            if (Boolean.parseBoolean(GUIUtils.getTableValueAt(eventsTable, r, iSelect))) {
                CreditEvent event = (CreditEvent) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                        LegalEntityAccessor.GET_CREDIT_EVENT_BY_ID,
                        Integer.parseInt(GUIUtils.getTableValueAt(eventsTable, r, 0)));
                List<Product> products = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                        LegalEntityAccessor.LOAD_PRODUCTS_BY_CREDIT_EVENT, event);

                for (Product product : products) {
                    Object[] row = new Object[]{
                        product.getProductId(),
                        product.getShortName(),
                        product.getMaturityDate(),
                        event.getCreditEventId(),
                        event.getCreditEvent(),
                        false
                    };
                    productTableModel.addRow(row);
                }
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApplyButton;
    private javax.swing.JButton addRowButton;
    private javax.swing.JLabel creditEntityLabel;
    private javax.swing.JScrollPane eventsScrollPane;
    private javax.swing.JTable eventsTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton loadButton;
    private javax.swing.JScrollPane productsScrollPane;
    private javax.swing.JTable productsTable;
    private javax.swing.JButton removeRowButton;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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
