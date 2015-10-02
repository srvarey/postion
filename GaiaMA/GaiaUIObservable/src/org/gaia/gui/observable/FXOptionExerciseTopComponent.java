/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.observable;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ScheduleAccessor;
import org.gaia.dao.trades.ScheduleBuilder;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.trades.events.ContractEventApply;
import org.gaia.dao.utils.DefaultSortableTreeTableNode;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ScheduleLine;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;

/**
 *
 * @author Benjamin Frerejean
 */
/**
 * Top component which displays fx spot.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.observable//FXOptionExerciseTopComponent//EN", autostore = false)
@TopComponent.Description(preferredID = "FXOptionExerciseTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.observable.FXOptionExerciseTopComponent")
@ActionReference(path = "Menu"+MenuManager.FXOptionExerciseTopComponentMenu, position = MenuManager.FXOptionExerciseTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FXOptionExerciseAction")
@NbBundle.Messages({
    "CTL_FXOptionExerciseAction=FX Option Exercise",
    "CTL_FXOptionExerciseTopComponent=FX Option Exercise",
    "HINT_FXOptionExerciseTopComponent=This is a FX Option Exercise window"
})
public class FXOptionExerciseTopComponent extends FixingTopComponent {

    private ArrayList selectedRowsPhysical = new ArrayList<>();
    private HashMap<Integer, List<Trade>> tradeMap;
    private boolean isBarrier = false;

    public FXOptionExerciseTopComponent() {
        super();
        setName(Bundle.CTL_FXOptionExerciseTopComponent());
        setToolTipText(Bundle.HINT_FXOptionExerciseTopComponent());
        table.setTreeTableModel(new FXOExerciseTreeTableModel(null, null, false));
        saveButton.setText("Exercise");
        loadFixingButton.setText("Load");
        loadBarriers.setVisible(true);
        termination.setVisible(true);
        fixingScrollPane.setViewportView(table);
    }

    @Override
    protected void loadFixing() {
        saveButton.setText("Exercise");
        tradeMap = new HashMap();
        scheduleList = (List<ScheduleLine>) DAOCallerAgent.callMethod(ScheduleAccessor.class,
                ScheduleAccessor.GET_FX_OPTIONS_EXERCISES_BY_DATE, fixingDateChooser.getDate(), filter);
        if (scheduleList!=null&&scheduleList.isEmpty()){
            return;
        } 
        for (ScheduleLine line : scheduleList) {
            //default exercise type
            line.type = ProductConst.ExerciseSettlementType.Physical.name();
            Product parent = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PARENT_PRODUCT, line.getProduct());
            line.getProduct().setParentProducts(new HashSet());
            line.getProduct().getParentProducts().add(parent);
            List<Trade> trades = (List) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADES_BY_PRODUCT, line.getProduct());
            tradeMap.put(line.getProduct().getProductId(), trades);
        }
        model = generateModel(scheduleList);
        table.getTableHeader().addMouseListener(new MyHeaderListener());
        table.setTreeTableModel(model);
        isBarrier = false;
        setModel();
        table.expandAll();
        table.packAll();
        table.collapseAll();
        selectedRows = new ArrayList<>();
        selectedRowsPhysical = new ArrayList<>();
        table.setVisible(true);
        table.setSortable(true);
    }

    @Override
    public void loadBarriers() {
        saveButton.setText("Apply Barrier");
        tradeMap = new HashMap();
        isBarrier = true;
        List<Trade> trades = (List) DAOCallerAgent.callMethod(TradeAccessor.class, 
                TradeAccessor.GET_FXBARRIER_OPTIONS, fixingDateChooser.getDate(), filter);
        for (Trade trade : trades) {
            tradeMap.put(trade.getProduct().getProductId(), trades);
        }
        model = generateModelForBarriers(trades);
        table.getTableHeader().addMouseListener(new MyHeaderListener());
        table.setTreeTableModel(model);
        setModel();
        table.setVisible(true);
        table.setSortable(true);
        table.expandAll();
        table.packAll();
        table.collapseAll();
        selectedRows = new ArrayList<>();
        selectedRowsPhysical = new ArrayList<>();
    }

    protected SortableTreeTableModel generateModelForBarriers(List<Trade> trades) {
        scheduleList = new ArrayList();
        for (Trade trade : trades) {
            ScheduleLine line = createFakeScheduleLine(trade);
            scheduleList.add(line);
        }
        Map<String, ArrayList<ScheduleLine>> dataMap = dataToMap(scheduleList);
        DefaultSortableTreeTableNode root = createRoot(dataMap);
        List headings = Arrays.asList(new String[]{"Underlying", "Product Id", "Call/Put", "Notional", "Strike", "Action Type", "Spot"});
        return new FXOExerciseTreeTableModel(root, headings, true);
    }

    public static ScheduleLine createFakeScheduleLine(Trade trade) {
        ScheduleLine line = new ScheduleLine();
        line.setProduct(trade.getProduct());
        line.setFixed(Boolean.FALSE);
        line.setFixing(BigDecimal.ZERO);
        line.setFixingProduct(trade.getProduct().getUnderlying());
        line.type = "FakeForBarriers";
        line.setScheduleLineId(trade.getTradeId());
        return line;
    }

    @Override
    public void setModel() {
        // formatting
        // quote
        GUIUtils.setNumberEditor(table, 6, "0.0000");
        // select
        GUIUtils.setCheckBoxEditor(table, 7);
        // notional
        GUIUtils.setNumberEditor(table, 4, "#,##0.00");
        // strike
        GUIUtils.setNumberEditor(table, 3, "0.0000");
        // physical/cash
        if (!isBarrier) {
            table.getColumnModel().getColumn(5).setCellEditor(new DefaultCellEditor(new JComboBox(ProductConst.ExerciseSettlementType.values())));
        }
    }

    @Override
    protected SortableTreeTableModel generateModel(List<ScheduleLine> scheduleList) {
        Map<String, ArrayList<ScheduleLine>> dataMap = dataToMap(scheduleList);
        DefaultSortableTreeTableNode root = createRoot(dataMap);
        List headings = Arrays.asList(new String[]{"Underlying", "Product Id", "Barrier Type", "Notional", "Barrier", "E", "Fixed"});
        return new FXOExerciseTreeTableModel(root, headings, false);
    }

    public DefaultSortableTreeTableNode createRoot(Map<String, ArrayList<ScheduleLine>> dataMap) {
        DefaultSortableTreeTableNode root = new DefaultSortableTreeTableNode();
        DefaultSortableTreeTableNode currentNameNode;
        for (Map.Entry<String, ArrayList<ScheduleLine>> entry : dataMap.entrySet()) {
            Object[] firstNodeData = new Object[8];
            firstNodeData[FXOExerciseTreeTableModel.underlying] = entry.getKey();
            firstNodeData[FXOExerciseTreeTableModel.product] = null;
            firstNodeData[FXOExerciseTreeTableModel.callPut] = null;
            firstNodeData[FXOExerciseTreeTableModel.strike] = null;
            firstNodeData[FXOExerciseTreeTableModel.notional] = null;
            firstNodeData[FXOExerciseTreeTableModel.exerciseType] = null;
            firstNodeData[FXOExerciseTreeTableModel.quote] = quotes.get(entry.getKey());
            firstNodeData[FXOExerciseTreeTableModel.fixed] = Boolean.FALSE;
            currentNameNode = new DefaultSortableTreeTableNode(firstNodeData);
            ArrayList<ScheduleLine> arrayList = entry.getValue();
            for (ScheduleLine scheduleLine : arrayList) {
                Object[] dataNode = firstNodeData.clone();
                dataNode[FXOExerciseTreeTableModel.underlying] = scheduleLine.getProduct().getShortName();
                dataNode[FXOExerciseTreeTableModel.product] = scheduleLine.getProduct().getProductId();
                if (scheduleLine.getProduct() != null && scheduleLine.getProduct().getProductForex() != null) {
                    if (isBarrier) {
                        dataNode[FXOExerciseTreeTableModel.callPut] = scheduleLine.getProduct().getProductForex().getBarrierType();
                    } else {
                        dataNode[FXOExerciseTreeTableModel.callPut] = scheduleLine.getProduct().getProductForex().getOptionStyle();
                    }
                } else {
                    dataNode[FXOExerciseTreeTableModel.callPut] = null;
                }
                if (scheduleLine.getProduct() != null && scheduleLine.getProduct().getProductForex() != null) {
                    if (isBarrier) {
                        dataNode[FXOExerciseTreeTableModel.strike] = scheduleLine.getProduct().getProductForex().getBarrier();
                    } else {
                        dataNode[FXOExerciseTreeTableModel.strike] = scheduleLine.getProduct().getProductForex().getStrike();
                    }
                } else {
                    dataNode[FXOExerciseTreeTableModel.strike] = null;
                }
                List<Trade> trades = tradeMap.get(scheduleLine.getProduct().getProductId());
                if (trades != null && !trades.isEmpty()) {
                    dataNode[FXOExerciseTreeTableModel.notional] = trades.get(0).getQuantity();
                } else {
                    dataNode[FXOExerciseTreeTableModel.notional] = null;
                }
                if (scheduleLine.type != null && isBarrier) {
                    if (scheduleLine.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.DownAndIn.display)
                            || scheduleLine.getProduct().getProductForex().getBarrierType().equalsIgnoreCase(ProductConst.BarrierType.UpAndIn.display)) {
                        dataNode[FXOExerciseTreeTableModel.exerciseType] = TradeAccessor.TradeLifeCycleStatus.ACTIVATED.name;
                    } else {
                        dataNode[FXOExerciseTreeTableModel.exerciseType] = TradeAccessor.TradeLifeCycleStatus.DEACTIVATED.name;
                    }
                } else {
                    dataNode[FXOExerciseTreeTableModel.exerciseType] = ProductConst.ExerciseSettlementType.Physical.name();
                }
                currentNameNode.add(new DefaultSortableTreeTableNode(dataNode));
                root.add(currentNameNode);
            }
        }
        return root;
    }

    @Override
    public void applyFixings() {
        int i = 0;
        selectedRows = new ArrayList();
        selectedRowsPhysical = new ArrayList();
        if (model != null && model.getRoot() != null) {
            Enumeration parents = model.getRoot().children();
            while (parents.hasMoreElements()) {
                TreeTableNode parent = (TreeTableNode) parents.nextElement();
                Enumeration children = parent.children();
                while (children.hasMoreElements()) {
                    Object[] data = (Object[]) ((TreeTableNode) children.nextElement()).getUserObject();
                    if ((Boolean) data[FXOExerciseTreeTableModel.fixed] && data[FXOExerciseTreeTableModel.quote] != null) {
                        if (data[FXOExerciseTreeTableModel.exerciseType].toString().equalsIgnoreCase(ProductConst.ExerciseSettlementType.Physical.name())) {
                            selectedRowsPhysical.add(scheduleList.get(i));
                        } else {
                            selectedRows.add(scheduleList.get(i));
                        }
                        ProductTypeUtil.ProductType type = ProductTypeUtil.getProductTypeByName(scheduleList.get(i).getFixingProduct().getProductType());
                        BigDecimal fixing = BigDecimal.valueOf(Double.parseDouble(data[FXOExerciseTreeTableModel.quote].toString()));
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
        int total = selectedRows.size() + selectedRowsPhysical.size();

        if (total > 0) {
            int confirm = JOptionPane.showConfirmDialog(this, saveButton.getText() + StringUtils.SPACE + total + " options?", "Exercise Options", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                Boolean saveOK;
                if (isBarrier) {
                    saveOK = (Boolean) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.APPLY_BARRIER_CROSSING, selectedRows);
                } else {
                    saveOK = (Boolean) DAOCallerAgent.callMethod(ScheduleBuilder.class, ScheduleBuilder.MAKE_SCHEDULE_LINES_FIXINGS, selectedRows, filter);
                    saveOK = saveOK & (Boolean) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.CREATE_FXOPTIONS_SPOT_EXPIRY, selectedRowsPhysical);
                }
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

    @Override
    public void applyTermination() {
        int i = 0;
        selectedRows = new ArrayList();
        selectedRowsPhysical = new ArrayList();
        if (model != null && model.getRoot() != null) {
            Enumeration parents = model.getRoot().children();
            while (parents.hasMoreElements()) {
                TreeTableNode parent = (TreeTableNode) parents.nextElement();
                Enumeration children = parent.children();
                while (children.hasMoreElements()) {
                    Object[] data = (Object[]) ((TreeTableNode) children.nextElement()).getUserObject();
                    if ((Boolean) data[FXOExerciseTreeTableModel.fixed] && data[FXOExerciseTreeTableModel.quote] != null) {

                        selectedRows.add(scheduleList.get(i));
                        ProductTypeUtil.ProductType type = ProductTypeUtil.getProductTypeByName(scheduleList.get(i).getFixingProduct().getProductType());
                        BigDecimal fixing = BigDecimal.valueOf(Double.parseDouble(data[FXOExerciseTreeTableModel.quote].toString()));
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
        DAOCallerAgent.callMethod(ContractEventApply.class, ContractEventApply.APPLY_TERMINATION, selectedRows);
        selectedRows = new ArrayList();
        selectedRowsPhysical = new ArrayList();
        JOptionPane.showMessageDialog(this, "Done");
        loadFixing();
    }

    protected class FXOExerciseTreeTableModel extends SortableTreeTableModel {

        public static final int underlying = 0;
        public static final int product = 1;
        public static final int callPut = 2;
        public static final int strike = 3;
        public static final int notional = 4;
        public static final int exerciseType = 5;
        public static final int quote = 6;
        public static final int fixed = 7;

        private final boolean isBarrier;

        private FXOExerciseTreeTableModel(TreeTableNode root, List headers, boolean isBarrier) {
            super(root, headers);
            this.isBarrier = isBarrier;
        }

        @Override
        public int getColumnCount() {
            return 8;
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
                case callPut:
                    if (isBarrier) {
                        res = "Barrier Type";
                    } else {
                        res = "Call/Put";
                    }
                    break;
                case notional:
                    res = "Notional";
                    break;
                case strike:
                    if (isBarrier) {
                        res = "Barrier";
                    } else {
                        res = "Strike";
                    }
                    break;
                case exerciseType:
                    if (isBarrier) {
                        res = "Action Type";
                    } else {
                        res = "Exercise Type";
                    }
                    break;
                case quote:
                    res = "Spot";
                    break;
                case fixed:
                    res = "Fixed";
                    break;
            }
            return res;
        }

        @Override
        public boolean isCellEditable(Object node, int column) {
            if (column == quote || column == fixed || column == exerciseType) {
                return true;
            }
            return false;
        }

        @Override
        public void setValueAt(Object value, Object node, int column) {
            if (node instanceof TreeTableNode) {
                TreeTableNode defNode = (TreeTableNode) node;
                if (column < defNode.getColumnCount()&&column>=FXOExerciseTreeTableModel.quote) {
                    
                    Object[] userObject = (Object[]) defNode.getUserObject();
                    userObject[column] = value;
                    defNode.setUserObject(userObject);
                    for (int i = 0; i < defNode.getChildCount(); i++) {
                        TreeTableNode treeTableNode = defNode.getChildAt(i);
                        setValueAt(value, treeTableNode, column);
                    }
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
                if (userObject != null && column < userObject.length) {
                    res = userObject[column];
                }

            }
            return res;
        }
    }

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
        super.componentClosed();
    }

    @Override
    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    @Override
    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
