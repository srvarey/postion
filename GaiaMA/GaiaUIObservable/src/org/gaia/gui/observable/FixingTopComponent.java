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
 *
 * @author Jawhar Kamoun
 */
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.ScheduleBuilder;
import org.gaia.dao.trades.events.ContractEventApply;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.DefaultSortableTreeTableNode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.dateFormat;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.gaia.gui.utils.SortableTreeTable;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component to achieve fixings
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//Fixing//EN", autostore = false)
@TopComponent.Description(preferredID = "FixingTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.FixingTopComponent")
@ActionReference(path = "Menu/Market Data" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FixingAction")
@Messages({
    "CTL_FixingAction=Fixing",
    "CTL_FixingTopComponent=Fixing Window",
    "HINT_FixingTopComponent=This is a Fixing window"
})
public class FixingTopComponent extends GaiaTopComponent {

    // DefaultTreeTableModel model = new DefaultTreeTableModel();
    ArrayList<ScheduleLine> selectedRows = new ArrayList<>();
    Map<String, BigDecimal> quotes = new HashMap();
    Filter filter = null;
    protected SortableTreeTable table = new SortableTreeTable();
    public SortableTreeTableModel model;
    protected List<ScheduleLine> scheduleList;
    protected boolean expanded = false;

    public FixingTopComponent() {
        initComponents();
        setName(Bundle.CTL_FixingTopComponent());
        setToolTipText(Bundle.HINT_FixingTopComponent());
        loadBarriers.setVisible(false);
        termination.setVisible(false);
    }

    @Override
    public void initContext() {
        List<String> filterNames = (List<String>) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES,
                Trade.class);
        GUIUtils.fillComboWithNullFirst(filterComboBox, filterNames);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        saveButton = new javax.swing.JButton();
        loadBarriers = new javax.swing.JButton();
        loadFixingButton = new javax.swing.JButton();
        filterComboBox = new javax.swing.JComboBox();
        filterLabel = new javax.swing.JLabel();
        fixingDateChooser = new com.toedter.calendar.JDateChooser(null,DateUtils.getDate(),dateFormat,new GaiaJSpinnerDateEditor());
        termination = new javax.swing.JButton();
        fixingScrollPane = new javax.swing.JScrollPane();
        expandButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        saveButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(saveButton, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.saveButton.text")); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        loadBarriers.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadBarriers, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.loadBarriers.text")); // NOI18N
        loadBarriers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBarriersActionPerformed(evt);
            }
        });

        loadFixingButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadFixingButton, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.loadFixingButton.text")); // NOI18N
        loadFixingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadFixingButtonActionPerformed(evt);
            }
        });

        filterComboBox.setBackground(new java.awt.Color(255, 255, 255));
        filterComboBox.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                filterComboBoxPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(filterLabel, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.filterLabel.text")); // NOI18N

        fixingDateChooser.setBackground(new java.awt.Color(254, 252, 254));
        fixingDateChooser.setName("fixingDateChooser"); // NOI18N

        termination.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(termination, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.termination.text")); // NOI18N
        termination.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                terminationActionPerformed(evt);
            }
        });

        expandButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(expandButton, org.openide.util.NbBundle.getMessage(FixingTopComponent.class, "FixingTopComponent.expandButton.text")); // NOI18N
        expandButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expandButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(fixingScrollPane)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fixingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(loadFixingButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(loadBarriers)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expandButton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(termination)
                        .addGap(18, 18, 18)
                        .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(saveButton)
                        .addComponent(termination))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(filterLabel)
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(loadFixingButton)
                        .addComponent(loadBarriers)
                        .addComponent(expandButton))
                    .addComponent(fixingDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fixingScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFixingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadFixingButtonActionPerformed
        loadFixing();
    }//GEN-LAST:event_loadFixingButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        applyFixings();
    }//GEN-LAST:event_saveButtonActionPerformed

    public void applyFixings() {
        int i = 0;
        selectedRows = new ArrayList();
        if (model != null && model.getRoot() != null) {
            Enumeration parents = model.getRoot().children();
            while (parents.hasMoreElements()) {
                TreeTableNode parent = (TreeTableNode) parents.nextElement();
                Enumeration children = parent.children();
                while (children.hasMoreElements()) {
                    Object[] data = (Object[]) ((TreeTableNode) children.nextElement()).getUserObject();
                    if ((Boolean) data[FixingTreeTableModel.fixed] && data[FixingTreeTableModel.quote] != null) {
                        selectedRows.add(scheduleList.get(i));
                        ProductType type = ProductTypeUtil.getProductTypeByName(scheduleList.get(i).getFixingProduct().getProductType());
                        BigDecimal fixing = BigDecimal.valueOf(Double.parseDouble(data[FixingTreeTableModel.quote].toString()));
                        if (type.getDefaultQuotationType() != null) {
                            BigDecimal mult = MarketQuote.QuotationType.getMult(type.getDefaultQuotationType().getName());
                            if (mult != null) {
                                fixing = fixing.divide(mult, 20, RoundingMode.UP);
                            }
                        }
                        scheduleList.get(i).setFixing(fixing);
                    }
                    i++;
                }
            }
        }
        if (!selectedRows.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(this, "Save " + selectedRows.size() + " Fixing confirm?", "Save Fixing", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Boolean saveOK = (Boolean) DAOCallerAgent.callMethod(ScheduleBuilder.class, ScheduleBuilder.MAKE_SCHEDULE_LINES_FIXINGS, selectedRows, filter);
                String msg;
                if (saveOK) {
                    msg = "Save OK";
                } else {
                    msg = "Failed show server log for more details";
                }
                JOptionPane.showMessageDialog(this, msg);
                loadFixing();
            }
        }
    }

    public void applyTermination() {
        DAOCallerAgent.callMethod(ContractEventApply.class, ContractEventApply.APPLY_TERMINATION, selectedRows);
        selectedRows = new ArrayList();
        JOptionPane.showMessageDialog(this, "Done");
        loadFixing();
    }

    private void filterComboBoxPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_filterComboBoxPropertyChange
        if (filterComboBox.getSelectedItem() != null) {
            filter = ((Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, filterComboBox.getSelectedItem().toString(),
                    Trade.class));
        } else {
            filter = null;
        }
    }//GEN-LAST:event_filterComboBoxPropertyChange

    private void terminationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_terminationActionPerformed
        applyTermination();
    }//GEN-LAST:event_terminationActionPerformed

    private void loadBarriersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBarriersActionPerformed
        loadBarriers();
    }//GEN-LAST:event_loadBarriersActionPerformed

    private void expandButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expandButtonActionPerformed

        // expand
        if (expanded) {
            expanded = false;
            table.collapseAll();
            expandButton.setText("+");
        } else {
            table.expandAll();
            expanded = true;
            expandButton.setText("-");
        }
    }//GEN-LAST:event_expandButtonActionPerformed

    public void loadBarriers() {
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton expandButton;
    protected javax.swing.JComboBox filterComboBox;
    protected javax.swing.JLabel filterLabel;
    protected com.toedter.calendar.JDateChooser fixingDateChooser;
    protected javax.swing.JScrollPane fixingScrollPane;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JButton loadBarriers;
    protected javax.swing.JButton loadFixingButton;
    protected javax.swing.JButton saveButton;
    protected javax.swing.JButton termination;
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
        // TODO read your settings according to their version
    }

    protected SortableTreeTableModel generateModel(List<ScheduleLine> scheduleList) {
        Map<String, ArrayList<ScheduleLine>> dataMap = dataToMap(scheduleList);
        DefaultSortableTreeTableNode root = new DefaultSortableTreeTableNode();
        DefaultSortableTreeTableNode currentNameNode;
        for (Entry<String, ArrayList<ScheduleLine>> entry : dataMap.entrySet()) {
            Object[] firstNodeData = new Object[4];
            firstNodeData[FixingTreeTableModel.underlying] = entry.getKey();
            ArrayList<ScheduleLine> lines = entry.getValue();
            BigDecimal mult = BigDecimal.ONE;
            if (lines.size() > 0) {
                ProductType type = ProductTypeUtil.getProductTypeByName(lines.get(0).getFixingProduct().getProductType());
                if (type.getDefaultQuotationType() != null) {
                    mult = MarketQuote.QuotationType.getMult(type.getDefaultQuotationType().getName());
                }
            }
            firstNodeData[FixingTreeTableModel.product] = null;
            BigDecimal quote = quotes.get(entry.getKey());
            if (quote != null) {
                firstNodeData[FixingTreeTableModel.quote] = quotes.get(entry.getKey()).multiply(mult);
            } else {
                firstNodeData[FixingTreeTableModel.quote] = null;
            }
            firstNodeData[FixingTreeTableModel.fixed] = Boolean.FALSE;
            currentNameNode = new DefaultSortableTreeTableNode(firstNodeData);
            ArrayList<ScheduleLine> arrayList = entry.getValue();
            for (ScheduleLine scheduleLine : arrayList) {
                Object[] dataNode = firstNodeData.clone();

                Product parent = null;
                if (scheduleLine.getProduct().getParentProducts() != null && !scheduleLine.getProduct().getParentProducts().isEmpty()) {
                    parent = scheduleLine.getProduct().getParentProducts().iterator().next();
                }
                if (parent != null) {
                    dataNode[FixingTreeTableModel.underlying] = parent.getShortName();
                } else {
                    dataNode[FixingTreeTableModel.underlying] = scheduleLine.getProduct().getShortName();
                }
                dataNode[FixingTreeTableModel.product] = scheduleLine.getProduct().getProductId();
                currentNameNode.add(new DefaultSortableTreeTableNode(dataNode));
                root.add(currentNameNode);
            }
        }
        List headings = Arrays.asList(new String[]{"Underlying", "Product Id", "Quote", "Fixed"});
        return new FixingTreeTableModel(root, headings);
    }

    protected Map<String, ArrayList<ScheduleLine>> dataToMap(List<ScheduleLine> scheduleList) {
        Map<String, ArrayList<ScheduleLine>> dataMap = new HashMap();
        String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();
        for (ScheduleLine scheduleLine : scheduleList) {
            if (scheduleLine.getFixingProduct() != null) {
                ArrayList<ScheduleLine> scheduleListByUnderlying = dataMap.get(scheduleLine.getFixingProduct().getShortName());
                if (scheduleListByUnderlying == null) {
                    scheduleListByUnderlying = new ArrayList<>();
                    MarketQuote quote = (MarketQuote) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_LAST_QUOTE, scheduleLine.getFixingProduct().getProductId(), fixingDateChooser.getDate(), quoteSet);
                    if (quote != null) {
                        quotes.put(scheduleLine.getFixingProduct().getShortName(), quote.getClose());
                    }
                }
                scheduleLine.setFixing(quotes.get(scheduleLine.getFixingProduct().getShortName()));
                scheduleListByUnderlying.add(scheduleLine);
                dataMap.put(scheduleLine.getFixingProduct().getShortName(), scheduleListByUnderlying);
            }
        }
        return dataMap;
    }

    protected void loadFixing() {
        scheduleList = (List<ScheduleLine>) DAOCallerAgent.callMethod(ScheduleAccessor.class,
                ScheduleAccessor.GET_SCHEDULE_LINES_BY_DATE, fixingDateChooser.getDate(), filter);
        for (ScheduleLine line : scheduleList) {
            Product parent = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PARENT_PRODUCT, line.getProduct());
            line.getProduct().setParentProducts(new HashSet());
            line.getProduct().getParentProducts().add(parent);
        }
        model = generateModel(scheduleList);
        table.getTableHeader().addMouseListener(new MyHeaderListener());
        table.setTreeTableModel(model);
        setModel();
        table.setSortable(true);
        table.setVisible(true);

        table.expandAll();
        table.packAll();
        table.collapseAll();

        fixingScrollPane.setViewportView(table);
        selectedRows = new ArrayList<>();
    }

    public void setModel() {
        GUIUtils.setCheckBoxEditor(table, 3);
        GUIUtils.setNumberEditor(table, 2, "0.0000");
    }

    protected class FixingTreeTableModel extends SortableTreeTableModel {

        public static final int underlying = 0;
        public static final int product = 1;
        public static final int quote = 2;
        public static final int fixed = 3;

        protected FixingTreeTableModel(TreeTableNode root, List headers) {
            super(root, headers);
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public String getColumnName(int column) {
            String res = "";
            switch (column) {
                case underlying:
                    res = "Underlying";
                    break;
                case product:
                    res = "Product Id";
                    break;
                case quote:
                    res = "Quote";
                    break;
                case fixed:
                    res = "Fixed";
                    break;
            }
            return res;
        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            if (column == quote || column == fixed) {
                return true;
            }
            return false;
        }

        @Override
        public void setValueAt(Object value, Object node, int column) {
            if (node instanceof TreeTableNode) {
                TreeTableNode defNode = (TreeTableNode) node;

                Object[] userObject = (Object[]) defNode.getUserObject();

                switch (column) {
                    case quote:
                        userObject[quote] = value;
                        defNode.setUserObject(userObject);
                        break;
                    case fixed:
                        userObject[fixed] = Boolean.parseBoolean(value.toString());
                        defNode.setUserObject(userObject);
                        break;
                }
                for (int i = 0; i < defNode.getChildCount(); i++) {
                    TreeTableNode treeTableNode = defNode.getChildAt(i);
                    this.setValueAt(value, treeTableNode, column);
                }
            }

        }

        @Override
        public Class<?> getColumnClass(int column) {
            if (column == fixed) {
                return Boolean.class;
            }
            return super.getColumnClass(column); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Object getValueAt(Object node, int column) {
            Object res = null;
            if (node instanceof TreeTableNode) {
                TreeTableNode defNode = (TreeTableNode) node;
                Object[] userObject = (Object[]) defNode.getUserObject();
                if (userObject != null) {
                    res = userObject[column];
                }
            }
            return res;
        }
    }

    public class MyHeaderListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int index = table.getTableHeader().getColumnModel().getColumnIndexAtX(e.getX());
            model.setSortColumn(index);
            model.sort();

            table = new SortableTreeTable(model);

            table.expandAll();
            table.setSortable(true);

            setModel();
            table.packAll();
            fixingScrollPane.setViewportView(table);
            table.setVisible(true);
            table.getTableHeader().addMouseListener(new MyHeaderListener());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

}
