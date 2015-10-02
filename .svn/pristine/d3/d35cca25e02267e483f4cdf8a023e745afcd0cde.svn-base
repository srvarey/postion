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
package org.gaia.gui.reports;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.jade.GaiaAgentController;
import org.gaia.dao.jade.ReportLauncherAgent;
import org.gaia.dao.jade.ReportSettings;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.reports.AbstractSortableTreeTableNode;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.FlowAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.SnapshotReport;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.gaia.gui.trades.TradeUtils;
import org.gaia.gui.utils.CentralLookup;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays flow details.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//DrillDown//EN", autostore = false)
@TopComponent.Description(preferredID = "DrillDownTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "bottomSlidingSide", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.DrillDownTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_DrillDownAction", preferredID = "DrillDownTopComponent")
@Messages({"CTL_DrillDownAction=DrillDown", "CTL_DrillDownTopComponent=DrillDown", "HINT_DrillDownTopComponent=This is a DrillDown window"})
public final class DrillDownTopComponent extends GaiaReportTopComponent {

    private ArrayList<String> headings = null;
    private PositionTree.PositionNode root;
    private DefaultTreeTableModel model;
    private JXTreeTable table;
    Date valDate;
    String pricingEnv = "default";
    private ReportLauncherAgent myAgent;
    private boolean hasCreditLine = false;
    private static final Logger logger = Logger.getLogger(DrillDownTopComponent.class);
    private TreeTableModel treeTableModel;

    public DrillDownTopComponent() {
        initComponents();
        setName(Bundle.CTL_DrillDownTopComponent());
        setToolTipText(Bundle.HINT_DrillDownTopComponent());
        associateLookup(CentralLookup.getDefault());
    }

    @Override
    public void initContext() {
        headings = new ArrayList();
        valDate = new Date();
        /**
         * load default template
         */
        setReportType(Flow.class.getSimpleName());
        loadDefaultTemplate(getReportType());
        getTemplate().setReportEnabled(false);
        getTemplate().setIsDrillDown(true);
        GaiaAgentController reportAgentController = GaiaAgentController.getInstance();
        myAgent = (ReportLauncherAgent) reportAgentController.createLocalAgent(ReportLauncherAgent.class);
        myAgent.setUI(this);

    }

    public void loadDefaultTemplate(String reportType) {
        try {
            String name = (String) DAOCallerAgent.callMethod(ReportUtils.class, ReportUtils.GET_DEFAULT_TEMPLATE, Flow.class.getSimpleName(), LoggedUser.getLoggedUserId());
            loadTemplate(name, false);

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Collection<? extends ReportTemplate> allEvents = CentralLookup.getDefault().lookupAll(ReportTemplate.class);
        if (!allEvents.isEmpty()) {
            ReportTemplate _template = allEvents.iterator().next();
            if (_template != null && this.equals(CentralLookup.LastActiveReportTopComponent(_template)) && _template.isDrillDown()) {
                setTemplate((ReportTemplate) _template);
                setFilter(_template.getFilter());
                if (_template.isReportEnabled()) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            runReport();
                        }
                    });
                }
                getTemplate().setReportEnabled(false);
            }
        }
    }

    public void load(String reportType, Filter filter, Date valDate, String pricingEnv) {
        /**
         * load default template
         */
        loadDefaultTemplate(reportType);
        setFilter(filter);
        setReportType(reportType);
        this.valDate = valDate;
        this.pricingEnv = pricingEnv;
        runReport();

    }

    @Override
    public void loadTemplate(String name, boolean reportEnabled) {
        if (name != null) {
            try {
                Class objectType = ReportUtils.getObjectType(getReportType());
                ReportTemplate newTemplate = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class,
                        ReportTemplateAccessor.GET_REPORT_TEMPLATE_BY_NAME_AND_TYPE, name, objectType);

                if (newTemplate != null) {
                    CentralLookup.getDefault().remove(getTemplate());
                    setTemplate(newTemplate);
                    setFilter(newTemplate.getFilter());
                    getTemplate().setIsDrillDown(true);
                    getTemplate().setReportEnabled(true);
                    CentralLookup.setLastActive(this, getTemplate());
                    CentralLookup.getDefault().add(this, newTemplate);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }

        }
    }

    @Override
    protected void componentActivated() {
        CentralLookup.getDefault().add(this, getTemplate());
        CentralLookup.setLastActive(this, getTemplate());
    }

    @Override
    protected void componentDeactivated() {
        CentralLookup.getDefault().remove(getTemplate());
    }

    @Override
    public void runReport() {

        if (getTemplate() != null) {

            getTemplate().setFilter(getFilter());
            Class objectType = ReportUtils.getObjectType(getReportType());
            getTemplate().setObjectTypeClass(objectType);
            if (getTemplate().getTemplateColumnItems() == null) {
                (new ErrorMessageUI("No column selected.")).setVisible(true);
            } else {

                try {

                    boolean hasPricing = false;
                    boolean calDayMinusOne = false;
                    Date firstDate = DateUtils.addOpenDay(valDate, -1);
                    String currency = getTemplate().getCurrency();

                    /**
                     * === LAUNCH
                     */
                    ReportSettings reportSetting = new ReportSettings(getTemplate(), valDate, pricingEnv, hasPricing, calDayMinusOne, firstDate, false, currency, false, false);

                    myAgent.addBehaviour(myAgent.new LaunchReport(reportSetting));

                } catch (Exception e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                    JOptionPane.showMessageDialog(null, "Error");
                }
            }

        } else {
            JOptionPane.showMessageDialog(this, "no template defined");
        }
    }

    @Override
    public void refresh(Object obj) {
        root = (PositionTree.PositionNode) obj;
        displayResult(root);
    }

    public void displayResult(PositionTree.PositionNode _root) {

        /**
         * result treatments
         */
        /**
         * ======================
         */
        /**
         * update the header
         */
        if (_root != null && _root.getChildCount() > 0) {
            TreeTableNode headerNode = root.getChildAt(0);
            headings = new ArrayList<>();
            for (int i = 0; i < headerNode.getColumnCount(); i++) {
                headings.add(headerNode.getValueAt(i).toString());
            }
            root.remove((MutableTreeTableNode) headerNode);

            /**
             * display
             */
            model = new DefaultTreeTableModel(root, headings);
            table = new JXTreeTable(model);
            /**
             * hide last column id
             */
            TableColumnModel m = table.getColumnModel();
            m.removeColumn(m.getColumn(m.getColumnCount() - 1));

            /**
             * show
             */
            table.setShowGrid(true, true);
            table.setRootVisible(false);
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            MyTreeCellRenderer renderer = new MyTreeCellRenderer();
            table.setTreeCellRenderer(renderer);
            table.setColumnControlVisible(true);
            table.setHorizontalScrollEnabled(true);
            table.setFillsViewportHeight(true);
            if (table.getRowCount() > 1) {
                table.setRowSelectionInterval(1, 1);
            }
            for (int i = 0; i < table.getRowCount(); i++) {
                table.expandRow(i);
            }

            for (int c = 0; c < table.getColumnCount(); c++) {
                table.getColumn(c).setCellRenderer(new DecimalFormatRenderer("#,##0.00"));
                GUIUtils.packColumn(table, c, 2);
            }
        } else {

            model = new DefaultTreeTableModel(root, headings);
            table = new JXTreeTable(model);
        }

        table.addMouseListener(new DrillDownTopComponent.MyTableListener());
        table.packAll();
        jScrollPane1.setViewportView(table);
        table.setVisible(true);

    }

    /**
     * empty array
     */
    public void emptyArray() {
        PositionTree tree = new PositionTree();
        PositionTree.AggregNode _root = tree.new AggregNode(null);
        headings = new ArrayList<>();
        if (getTemplate().getTemplateColumnItems() != null) {
            for (Iterator<TemplateColumnItem> it = getTemplate().getTemplateColumnItems().iterator(); it.hasNext();) {
                TemplateColumnItem tci = it.next();
                headings.add(tci.getDisplayName());
            }
        }
        model = new DefaultTreeTableModel(_root, headings);
        table = new JXTreeTable(model);
    }

    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
            PositionTree.PositionNode node = (PositionTree.PositionNode) value;
            Object o = node.getValueAt(0);
            if (o != null) {
                setText(o.toString());
            } else {
                setText("");
            }
            if (node.isLeaf()) {
                setIcon(null);
            }
            return this;
        }
    }

    /**
     * MyTableListener listens to mouse clicks
     */
    private class MyTableListener implements MouseListener {

        @Override
        /**
         * Right click
         */
        public void mouseReleased(MouseEvent e) {

            int row = table.rowAtPoint(e.getPoint());
            if (row >= 0 && row < table.getRowCount()) {
                table.setRowSelectionInterval(row, row);
            } else {
                table.clearSelection();
            }

            int rowindex = table.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable && getReportType().equalsIgnoreCase("Positions") && hasCreditLine) {
                JPopupMenu popup = new JPopupMenu();

                JMenuItem menuItem = new JMenuItem("Generate CVA hedge");

                ActionListener menuListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
//                        generateCVAHedge();
                    }
                };

                menuItem.addActionListener(menuListener);
                popup.add(menuItem);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        /**
         * Double click
         */
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2
                    && (getReportType().equals(Flow.class.getSimpleName()))) {
                /**
                 * code to execute
                 */
                int id = getObjectId();
                if (id > 0) {
                    if (getReportType().equals(Flow.class.getSimpleName())) {
                        Flow flow = (Flow) DAOCallerAgent.callMethod(FlowAccessor.class, FlowAccessor.GET_FLOW_BY_ID, id);
                        Trade trade = flow.getTrade();
                        Integer tradeId = trade.getTradeId();
                        TradeUtils.openTrade(WindowManager.getDefault().getMainWindow(), tradeId, false);
                    }
                    if (getReportType().equals(Trade.class.getSimpleName())) {
                        TradeUtils.openTrade(WindowManager.getDefault().getMainWindow(), id, false);
                    } else if (getReportType().equals(Position.class.getSimpleName())) {
                        try {
                            Position position = (Position) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_AND_POSITION_HISTORY, id, valDate);
                            if (position != null) {
                                if (ProductTypeUtil.getProductTypeByName(position.getProduct().getProductType()).use.equals(ProductTypeUtil.ProductTypeUse.OTC)) {
                                    Integer tradeId = (Integer) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_TRADE_ID_FROM_POSITION, id);
                                    if (tradeId != null) {
                                        TradeUtils.openTrade(WindowManager.getDefault().getMainWindow(), tradeId, false);
                                    }
                                }
                            }
                        } catch (Exception ex) {
                            logger.error(StringUtils.formatErrorMessage(ex));
                        }
                    } else if (getReportType().equals(SnapshotReport.class.getSimpleName())) {
                        WindowManager wm = WindowManager.getDefault();
                        SnapshotReportTopComponent snapshotTopComp = (SnapshotReportTopComponent) wm.findTopComponent("SnapshotReportTopComponent");
                        snapshotTopComp.load(id);
                        snapshotTopComp.open();
                        snapshotTopComp.requestActive();
                    }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    /**
     * return object id Object componentObject =
     * treePath.getPathComponent(table.getPathForRow(table.getSelectedRow()).getPathCount()
     * - 1);
     *
     * @return
     */
    public int getObjectId() {
        int id = 0;
        int row = table.getSelectedRow();
        if (row >= 0) {
            TreePath treePath = table.getPathForRow(row);
            Object componentObject = treePath.getPathComponent(treePath.getPathCount() - 1);
            AbstractSortableTreeTableNode node = (AbstractSortableTreeTableNode) componentObject;
            treeTableModel = table.getTreeTableModel();
            int column = treeTableModel.getColumnCount() - 1;
            componentObject = treeTableModel.getValueAt(node, column);
            if (componentObject != null && (componentObject instanceof Integer || componentObject instanceof BigInteger)) {
                id = Integer.parseInt(componentObject.toString());

            }
        }
        return id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1093, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
        setResult(Utilities.actionsGlobalContext().lookupResult(ReportTemplate.class));
        getResult().addLookupListener(this);
        CentralLookup.setLastActive(this, getTemplate());
        CentralLookup.getDefault().add(this, getTemplate());
    }

    @Override
    public void componentClosed() {

        getResult().removeLookupListener(this);
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
