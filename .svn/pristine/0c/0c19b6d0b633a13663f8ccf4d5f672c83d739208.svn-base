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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.MeasuresAccessor.Measure;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.reports.AbstractSortableTreeTableNode;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.PositionTree.AggregNode;
import org.gaia.dao.reports.PositionTree.PositionNode;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportTemplateAccessor;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.reports.customColumns.DefaultColorableColumn;
import org.gaia.dao.reports.customColumns.IColorableColumn;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.MappingsAccessor;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionConfiguration;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.SnapshotReport;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductCredit;
import org.gaia.domain.trades.ProductRate;
import org.gaia.domain.trades.Scheduler;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.IPriceable;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.trades.AssetTradeTopComponent;
import org.gaia.gui.trades.TradeUtils;
import org.gaia.gui.utils.CentralLookup;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.gaia.gui.utils.SortableTreeTable;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 * Top component which displays reports.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//Report//EN", autostore = false)
@TopComponent.Description(preferredID = "ReportTopComponent", iconBase = "org/gaia/gui/reports/report3.png", persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED)
@TopComponent.Registration(mode = "editor", openAtStartup = true)
@ActionID(category = "Window", id = "org.gaia.gui.reports.ReportTopComponent")
@ActionReference(path = "Menu" + MenuManager.ReportTopComponentMenu, position = MenuManager.ReportTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ReportAction")
@Messages({"CTL_ReportAction=Monitors", "CTL_ReportTopComponent=Monitors", "HINT_ReportTopComponent=This is Monitors window"})
public class ReportTopComponent extends GaiaReportTopComponent {

    public ArrayList<String> headings = null;
    public PositionTree.PositionNode root;
    private AbstractSortableTreeTableNode headerNode;
    private SortableTreeTableModel model;
    public SortableTreeTable table = new SortableTreeTable();
    public Date valDate;
    private boolean isRealTimeOn = false;
    private ReportLauncherAgent myAgent;
    static final Logger logger = Logger.getLogger(ReportTopComponent.class);
    private boolean hasCreditLine = false;
    String previousHeaderString = StringUtils.EMPTY_STRING;
    boolean expanded = true;
    int coloredColIndex = -1;

    public ReportTopComponent() {
        /*
         *We initialize the different components of the view
         *coming from the Swing library
         */
        initComponents();
        setName(Bundle.CTL_ReportTopComponent());
        setToolTipText(Bundle.HINT_ReportTopComponent());
        table.getTableHeader().setReorderingAllowed(false);
        table.setName("ReportTable");
    }

    @Override
    public void initContext() {
        /*
         *Calendar Initialization
         *We Initialize the calendar of the Report view thanks to
         *A date chooser containing a date spinner and a button,
         *that makes a JCalendar visible for choosing a date.
         */
        jDateChooserEndDate.setDate(DateUtils.getDate());
        jDateChooserStartDate.setDate(DateUtils.getDate());
        jDateChooserEndDate.setMinSelectableDate(jDateChooserStartDate.getDate());
        jDateChooserStartDate.setMaxSelectableDate(jDateChooserEndDate.getDate());
        /*
         *Retrieving the possible reports to select
         */
        List<String> reports = ReportUtils.getReportObjectList();
        GUIUtils.fillCombo(jComboBoxObject, reports);

        headings = new ArrayList();
        collateralPanel.setVisible(false);
        /*
         *Pricing Environments
         */
        String pricingEnv;
        pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
        jComboPEnv.setSelectedItem(pricingEnv);

        GaiaAgentController reportAgentController = GaiaAgentController.getInstance();
        myAgent = (ReportLauncherAgent) reportAgentController.createLocalAgent(ReportLauncherAgent.class);
        myAgent.setUI(this);

        jComboBoxObject.setSelectedIndex(0);
        Class objectType = ReportUtils.getObjectType(getReportType());
        ArrayList<String> templates = (ArrayList) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_TEMPLATE_LIST, objectType);
        GUIUtils.fillCombo(viewsComboBox, templates);

        try {
            List<String> pricingEnvironments = (List) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class,
                    PricingEnvironmentAccessor.GET_PRICING_ENVIRONMENT_LIST);
            GUIUtils.fillCombo(jComboPEnv, pricingEnvironments);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

        try {
            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboCurrency, currencies);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        try {
            List<String> configurations = (List) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_CONFIGURATION_NAME_LIST);
            GUIUtils.fillCombo(jComboBoxPositionConfiguration, configurations);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        /**
         * load default template
         */
        setReportType(jComboBoxObject.getSelectedItem().toString());
        loadDefaultTemplate(getReportType());
        refreshIncludeCounterpartyInCreditCheckBox();
        if (getTemplate() != null) {
            getTemplate().setIsDrillDown(false);
            jComboCurrency.setSelectedItem(getTemplate().getCurrency());
        }
        if (getLookup() == null) {
            associateLookup(CentralLookup.getDefault());
        }
        jButtonGO.setEnabled(true);
        /**
         * ShortCut for date
         */
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserEndDate.getComponent(1));
        DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserStartDate.getComponent(1));

    }

    @Override
    public void setFilter(Filter filter) {
        super.setFilter(filter);
    }

    /**
     * return Current Display Name
     */
    private String getCurrentDisplayName() {
        return getReportType() + " Monitor " + getTemplate().getTemplateName() + StringUtils.SPACE + getStringValDate();
    }

    public void loadDefaultTemplate(String reportType) {
        try {
            String name = (String) DAOCallerAgent.callMethod(ReportUtils.class, ReportUtils.GET_DEFAULT_TEMPLATE, reportType, LoggedUser.getLoggedUserId());
            loadTemplate(name, false);
            viewsComboBox.setSelectedItem(name);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * load Template loads the template
     *
     * @param name
     * @param load
     */
    @Override
    public void loadTemplate(String name, boolean load) {
        if (name != null) {
            try {
                Class objectType = ReportUtils.getObjectType(getReportType());
                ReportTemplate newTemplate = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_REPORT_TEMPLATE_BY_NAME_AND_TYPE, name, objectType);
                if (newTemplate != null) {
                    setFilter(newTemplate.getFilter());
                    if (getFilter() != null) {
                        filterComboBox.setSelectedItem(getFilter().getName());
                    }
                    if (getTemplate() != null) {
                        CentralLookup.getDefault().remove(getTemplate());
                    }
                    if (newTemplate.getCurrency() != null) {
                        jComboCurrency.setSelectedItem(newTemplate.getCurrency());
                    }
                    if (newTemplate.getPricingEnvironment() != null) {
                        jComboPEnv.setSelectedItem(newTemplate.getPricingEnvironment());
                    }
                    if (newTemplate.getPositionConfiguration() != null) {
                        jComboBoxPositionConfiguration.setSelectedItem(newTemplate.getPositionConfiguration().getName());
                    }
                    showRootCheckBox.setSelected(newTemplate.isShowRoot());
                    newTemplate.setIsDrillDown(false);
                    newTemplate.setReportEnabled(load);
                    setTemplate(newTemplate);
                    setDisplayName(getCurrentDisplayName());
                    CentralLookup.setLastActive(this, getTemplate());
                    CentralLookup.getDefault().add(this, newTemplate);
                } else {
                    setTemplate(null);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     * MyTableListener listens to mouse clicks
     */
    public class MyTableListener implements MouseListener {

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
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                if (getReportType().equalsIgnoreCase(Position.class.getSimpleName()) && hasCreditLine) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Generate CVA hedge");
                    ActionListener menuListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            generateCVAHedge();
                        }
                    };
                    menuItem.addActionListener(menuListener);
                    popup.add(menuItem);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                } else if (getReportType().equalsIgnoreCase(SnapshotReport.class.getSimpleName())) {
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem menuItem = new JMenuItem("Delete Snapshot");
                    ActionListener menuListener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {
                            deleteSnapshot();
                        }
                    };
                    menuItem.addActionListener(menuListener);
                    popup.add(menuItem);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        }

        @Override
        /**
         * Double click
         */
        public void mouseClicked(MouseEvent e) {
            if (e.getClickCount() == 2
                    && (getReportType().equals(Trade.class.getSimpleName())
                    || getReportType().equals(SnapshotReport.class.getSimpleName())
                    || getReportType().equals(Position.class.getSimpleName()))) {
                /**
                 * code to execute
                 */
                int id = getObjectId();
                if (id > 0) {
                    if (getReportType().equals(Trade.class.getSimpleName())) {
                        TradeUtils.openTrade(WindowManager.getDefault().getMainWindow(), id, false);
                    } else if (getReportType().equals(Position.class.getSimpleName())) {
                        try {
                            Position position = (Position) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_AND_POSITION_HISTORY,
                                    id, valDate);
                            if (position != null) {
                                if (ProductTypeUtil.getProductTypeByName(position.getProduct().getProductType()).use.equals(ProductTypeUtil.ProductTypeUse.OTC)) {
                                    Integer tradeId = (Integer) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_TRADE_ID_FROM_POSITION, id);
                                    if (tradeId != null) {
                                        TradeUtils.openTrade(WindowManager.getDefault().getMainWindow(), tradeId, false);
                                    }
                                } else {
                                    Filter _filter = (Filter) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_FLOW_FILTER_FROM_POSITION,
                                            position, valDate);
                                    WindowManager wm = WindowManager.getDefault();
                                    DrillDownTopComponent drillDownTopComp = (DrillDownTopComponent) wm.findTopComponent("DrillDownTopComponent");
                                    drillDownTopComp.open();
                                    drillDownTopComp.load(Flow.class.getSimpleName(), _filter, valDate, jComboPEnv.getSelectedItem().toString());
                                    drillDownTopComp.requestActive();
                                    drillDownTopComp.setDisplayName("DrillDown " + getCurrentDisplayName());
                                    table.clearSelection();
                                }
                            }
                        } catch (Exception ex) {
                            logger.error(StringUtils.formatErrorMessage(ex));
                        }
                    } else if (getReportType().equals(SnapshotReport.class.getSimpleName())) {
                        WindowManager wm = WindowManager.getDefault();
                        SnapshotReportTopComponent snapshotTopComp = (SnapshotReportTopComponent) wm.findTopComponent(
                                SnapshotReportTopComponent.class.getSimpleName());
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

    private void deleteSnapshot() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            Integer id = getObjectId();
            SnapshotReport snapshot = (SnapshotReport) DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.GET_SNAPSHOT_REPORT_BY_ID, id);
            if (snapshot != null) {
                int confirm = JOptionPane.showConfirmDialog(this, "Delete Snapshot Monitor id " + id.toString(), "Delete confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (JOptionPane.YES_OPTION == confirm) {
                    DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.DELETE_SNAPSHOT_REPORT, snapshot);
                    runReport();
                }
            }
        }
    }

    /**
     * \brief generate margin call from current config \param <generateAll> true for global generation false for right click
     */
    private void generateCVAHedge() {
        try {
            Integer id = getFirstLeafObjectId();
            if (id > 0) {
                Position position = (Position) DAOCallerAgent.callMethod(PositionBuilder.class, PositionBuilder.GET_POSITION_AND_POSITION_HISTORY, id, new Date());
                String currency = jComboCurrency.getSelectedItem().toString();
                int index = -1;
                int i = 0;
                for (String header : headings) {
                    if (header.equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_LINE.name() + ReportTemplate.CONVERSION_SUFFIX)) {
                        index = i;
                    }
                    i++;
                }
                if (index == -1) {
                    i = 0;
                    for (String header : headings) {
                        if (header.equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_LINE.name())) {
                            index = i;
                        }
                        i++;
                    }
                }
                if (index == -1) {
                    JOptionPane.showMessageDialog(this, "Please display CREDIT_LINE column");
                } else {
                    // on counterparty aggregation
                    TreePath treePath = table.getPathForRow(table.getSelectedRow());
                    BigDecimal creditLine = null;
                    Object componentObject;
                    while (treePath != null) {
                        componentObject = treePath.getPathComponent(treePath.getPathCount() - 1);
                        PositionNode node = (PositionNode) componentObject;
                        componentObject = node.getValueAt(index);
                        if (componentObject != null && componentObject instanceof BigDecimal) {
                            creditLine = (BigDecimal) componentObject;
                            break;
                        }
                        treePath = treePath.getParentPath();
                    }
                    if (creditLine != null) {
                        if (creditLine.doubleValue()==0){
                            JOptionPane.showMessageDialog(this, "Credit Line set to zero\nNo trade will be generated");
                        }
                        Date maturity = ProductAccessor.getCdsImmDates(new Date(), 5).get(19); // 5Y maturity
                        Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_TYPE_AND_ISSUER_AND_MATURITY,
                                ProductType.CDS_PRODUCT.getName(), position.getCounterparty().getShortName(), maturity);
                        if (product == null) {
                            product = new Product();
                            product.setProductType(ProductType.CDS_PRODUCT.name);
                            product.setShortName(position.getCounterparty().getShortName() + StringUtils.SPACE + dateFormatter.format(maturity));
                            product.setIsAsset(true);
                            product.setNotionalMultiplier(BigDecimal.ONE);
                            product.setNotionalCurrency(currency);
                            product.setMaturityDate(maturity);
                            product.setStartDate(DateUtils.addYear(maturity, -5));
                            product.setQuantityType(Trade.QuantityType.NOTIONAL.name);
                            product.setQuotationType(MarketQuote.QuotationType.BASIS_POINT.getName());
                            ProductCredit credit = new ProductCredit();
                            credit.setIssuer(position.getCounterparty());
                            credit.setSeniority("Senior");
                            credit.setProduct(product);
                            product.setProductCredit(credit);
                            ProductRate rate = new ProductRate();
                            rate.setRate(new BigDecimal(0.01));
                            rate.setProduct(product);
                            product.setProductRate(rate);
                            Scheduler scheduler = new Scheduler();
                            scheduler.setFrequency(FrequencyUtil.Frequency.QUARTERLY.name);
                            scheduler.setDaycount(DayCountAccessor.DayCount.ACT_360.getName());
                            scheduler.setAdjustment(DateUtils.ADJUSTMENT_FOLLOW);
                            scheduler.setPaymentLag(0);
                            scheduler.setIsPayInArrears(true);
                            scheduler.setIsPayLagBusDays(true);
                            product.setScheduler(scheduler);

                            product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, product);
                        }

                        String pricingEnv = (String) DAOCallerAgent.callMethod(MappingsAccessor.class, MappingsAccessor.GET_DEFAULT_PRICING_ENVIRONMENT_NAME);
                        Trade trade = TradeUtils.createTrade(product, position.getInternalCounterparty(), null, BigDecimal.ONE, null, null, null, null, null, null, null);
                        Map<String, BigDecimal> measures = (Map<String, BigDecimal>) DAOCallerAgent.callMethod(PricingBuilder.class,
                                PricingBuilder.GET_TRADE_PRICING, trade, valDate, pricingEnv, null, null);
                        BigDecimal creditleg = measures.get(MeasuresAccessor.Measure.NPV_CREDIT_unit.name());
                        /**
                         * get spread
                         */
                        BigDecimal spread = (BigDecimal) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.GET_QUOTE_SPREAD, trade, valDate);

                        if (creditleg != null && creditleg.doubleValue() != 0) {
                            BigDecimal tradeNotional = creditLine.negate().divide(creditleg, 20, BigDecimal.ROUND_HALF_UP);
                            trade.setQuantity(tradeNotional);
                            trade.setPrice(spread);
                            Double dUpfront = (Double) DAOCallerAgent.callMethod(PricingBuilder.class, PricingBuilder.GET_CDS_UPFRONT, trade, DateUtils.getDate(), pricingEnv);
                            BigDecimal upfront = new BigDecimal(dUpfront);
                            trade.setAmount(upfront);
                            trade.setComment("Generated by automatic hedge");
                            AssetTradeTopComponent tradeWindow = new AssetTradeTopComponent();
                            tradeWindow.open();
                            tradeWindow.fillWindowWithTrade(trade);
                            tradeWindow.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(this, "No figure found.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "No figure found.");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wrong position");
            }

        } catch (HeadlessException e) {
            logger.error("Error " + StringUtils.formatErrorMessage(e));
        }
    }

    public Object getValueAtSelectedRow(int row, int column) {
        Object value = null;
        TreePath treePath = table.getPathForRow(row);
        if (treePath != null) {
            PositionNode node = (PositionNode) treePath.getPathComponent(treePath.getPathCount() - 1);
            while (node.getParent() != root) {
                node = (PositionNode) node.getParent();
            }
            Object[] nodeData = node.getData();
            value = nodeData[column];
        }
        return value;
    }

    @Override
    public void runReport() {
        if (getTemplate() != null) {
            valDate = jDateChooserEndDate.getDate();
            Class objectType = ReportUtils.getObjectType(getReportType());
            getTemplate().setObjectTypeClass(objectType);
            if (getTemplate().getTemplateColumnItems() == null) {
                (new ErrorMessageUI("No column selected.")).setVisible(true);
            } else {
                jProgressBar.setIndeterminate(true);
                jButtonGO.setEnabled(false);
                viewsComboBox.setEnabled(false);
                jComboBoxObject.setEnabled(false);
                boolean hasPricing = false;
                Date firstDate = jDateChooserStartDate.getDate();
                boolean calDayMinusOne = !firstDate.equals(valDate);
                String pricingEnv = jComboPEnv.getSelectedItem().toString();
                String currency = jComboCurrency.getSelectedItem().toString();
                getTemplate().setCurrency(currency);
                getTemplate().setFilter(getFilter());

                /**
                 * LAUNCH
                 */
                try {
                    if (IPriceable.class.isAssignableFrom(objectType)) {
                        for (TemplateColumnItem item : getTemplate().getTemplateColumnItems()) {
                            if (item.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_MEASURE)
                                    || item.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_CUSTOM)) {
                                hasPricing = true;
                                break;
                            }
                        }
                    }
                    String configName = jComboBoxPositionConfiguration.getSelectedItem().toString();
                    PositionConfiguration configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class,
                            PositionBuilder.GET_POSITION_CONFIGURATION, configName);
                    getTemplate().setPositionConfiguration(configuration);
                    ReportSettings reportSetting = new ReportSettings(getTemplate(), valDate, pricingEnv, hasPricing, calDayMinusOne,
                            firstDate, false, currency, isRealTimeOn, includeCounterpartyInCreditCheckBox.isSelected());
                    myAgent.addBehaviour(myAgent.new LaunchReport(reportSetting));
                    for (TemplateColumnItem item : template.getTemplateColumnItems()) {
                        if (item.getName().equalsIgnoreCase(MeasuresAccessor.Measure.CREDIT_LINE.name())) {
                            hasCreditLine = true;
                        }
                    }
                } catch (Exception e) {
                    logger.fatal(StringUtils.formatErrorMessage(e));
                    JOptionPane.showMessageDialog(this, "Error");
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "no template defined");
        }
    }

    public void displayResult(Object obj) {
        try {
            boolean isTheSameTree = false;
            /**
             * result treatments
             */
            PositionNode root_ = (PositionNode) obj;

            if (root_ != null && root_.getChildCount() > 0) {
                headerNode = (AbstractSortableTreeTableNode) root_.getChildAt(0);
                if (headerNode.getUserObject() != null && ((Object[]) headerNode.getUserObject()).length == 1) {
                    JOptionPane.showMessageDialog(this, "No column set!");
                }
                root_.remove(headerNode);
                if (!showRootCheckBox.isSelected() && root_.getChildCount() > 0) {
                    root_ = (PositionNode) root_.getChildAt(0);
                }
                if (headerNode.getValueAt(headerNode.getColumnCount() - 1) instanceof List) {
                    isTheSameTree = true;
                    // for real time mode
                    // just replace the line
                    List<Integer> idList = (List) headerNode.getValueAt(headerNode.getColumnCount() - 1);
                    for (Integer id : idList) {
                        PositionTree.PositionNode newnode = getNode(root_, id);
                        PositionTree.PositionNode oldnode = getNode(root, id);
                        do {
                            if (oldnode != null) {
                                oldnode.setData(newnode.getData());
                                newnode = (PositionTree.PositionNode) newnode.getParent();
                                oldnode = (PositionTree.PositionNode) oldnode.getParent();
                            }
                        } while (newnode != null);
                    }
                    int row = table.getSelectedRow();
                    table.setTreeTableModel(model);
                    table.repaint();
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                    }
                } else if (root != null && root.getChildCount() == root_.getChildCount()
                        && previousHeaderString.equalsIgnoreCase(headerToString(headerNode))) {
                    isTheSameTree = refreshChildren(root, root_);
                    int row = table.getSelectedRow();
                    table.setTreeTableModel(model);
                    table.repaint();
                    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    if (row >= 0) {
                        table.setRowSelectionInterval(row, row);
                    }
                }
                if (!isTheSameTree) {
                    root = root_;
                    // display all
                    headings = new ArrayList();
                    previousHeaderString = headerToString(headerNode);
                    coloredColIndex = -1;
                    for (int i = 0; i < headerNode.getColumnCount(); i++) {
                        headings.add(headerNode.getValueAt(i).toString());
                    }

                    model = new SortableTreeTableModel(root, headings);

                    int row = table.getSelectedRow();
                    table = new SortableTreeTable(model);
                    table.setName("ReportTable");
                    table.expandAll();
                    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
                    if (row >= 0 && row < table.getSelectedRow()) {
                        table.setRowSelectionInterval(row, row);
                    }
                    // hide last column : objects id
                    TableColumnModel columnModel = table.getColumnModel();
                    if (columnModel.getColumnCount() > 0) {
                        columnModel.removeColumn(columnModel.getColumn(columnModel.getColumnCount() - 1));
                    }
                    table.setSortable(true);
                    table.setShowGrid(true, true);
                    table.setRootVisible(false);
                    table.setTreeCellRenderer(new MyTreeCellRenderer());
                    table.setColumnControlVisible(true);
                    table.setHorizontalScrollEnabled(true);
                    table.setFillsViewportHeight(true);

                    Iterator<TemplateColumnItem> it = template.getTemplateColumnItems().iterator();
                    for (int col = 0; col < table.getColumnCount(); col++) {
                        DecimalFormatRenderer render = new DecimalFormatRenderer(sDecimalFormat);
                        if (headerNode.getValueAt(col).toString().equalsIgnoreCase("NPV")
                                || headerNode.getValueAt(col).toString().equalsIgnoreCase("NPV.ref")
                                || headerNode.getValueAt(col).toString().equalsIgnoreCase("MtM")) {
                            coloredColIndex = col;
                            render.addColoredColumn(col, new DefaultColorableColumn());
                        }
                        TemplateColumnItem item = it.next();
                        if (item.getColumnType().equalsIgnoreCase(TemplateColumnItem.COLUMN_CUSTOM)) {
                            CustomColumn customColumn = (CustomColumn) DAOCallerAgent.callMethod(CustomColumnAccessor.class, CustomColumnAccessor.GET_CUSTOM_COLUMN_BY_NAME, item.getName());
                            if (customColumn != null) {
                                Class clazz = Class.forName(customColumn.getClassName());
                                if (IColorableColumn.class.isAssignableFrom(clazz)) {
                                    IColorableColumn colorableColumn = (IColorableColumn) clazz.newInstance();
                                    render.addColoredColumn(col, colorableColumn);
                                }
                            }
                        }
                        table.getColumn(col).setCellRenderer(render);
                        table.getColumn(col).setHeaderRenderer(headerRenderer);
                        GUIUtils.packColumn(table, col, 2);
                    }
                    table.addMouseListener(new MyTableListener());
                    table.getTableHeader().addMouseListener(new MyHeaderListener());
                    table.packAll();
                    scrollReportPanel.setViewportView(table);
                    table.setVisible(true);
                }
                rowCountTextField.setText(StringUtils.EMPTY_STRING + table.getRowCount());
            } else {
                JOptionPane.showMessageDialog(this, "no data");
                model = new SortableTreeTableModel(root, headings);
                table = new SortableTreeTable(model);
                table.setName("ReportTable");
            }
            jButtonGO.setEnabled(true);
            viewsComboBox.setEnabled(true);
            jComboBoxObject.setEnabled(true);
            jProgressBar.setIndeterminate(false);
            jProgressBar.setValue(jProgressBar.getMinimum());
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public String headerToString(AbstractSortableTreeTableNode header) {
        String str = StringUtils.EMPTY_STRING;
        if (header.getUserObject() != null) {
            Object[] vals = (Object[]) header.getUserObject();
            for (Object val : vals) {
                if (val != null) {
                    str += val.toString();
                }
            }
        }
        return str;
    }

    public boolean refreshChildren(PositionTree.PositionNode oldRoot, PositionTree.PositionNode newRoot) {
        if (oldRoot.getChildCount() != newRoot.getChildCount()) {
            return false;
        }
        if (newRoot.getData() != null) {
            oldRoot.setData(newRoot.getData());
        }
        for (int i = 0; i < newRoot.getChildCount(); i++) {
            PositionTree.PositionNode newnode = (PositionTree.PositionNode) newRoot.getChildAt(i);
            PositionTree.PositionNode oldnode = (PositionTree.PositionNode) oldRoot.getChildAt(i);
            if (newnode.isLeaf()) {
                oldnode.setData(newnode.getData());
            } else {
                boolean ret = refreshChildren(oldnode, newnode);
                if (!ret) {
                    return ret;
                }
            }
        }
        return true;

    }

    public void replaceLines(PositionTree.PositionNode root, Integer lineId, int row) {
        try {
            if (root.isLeaf()) {
                if (root.getData()[root.getData().length - 1] instanceof Integer) {
                    Integer id = (Integer) root.getData()[root.getData().length - 1];
                    if (id.intValue() == lineId.intValue()) {
                        PositionTree.PositionNode node = root;
                        while (node != null && node.getData() != null) {
                            Object[] objects = ((Object[]) node.getUserObject()).clone();
                            node.setUserObject(objects);

                            model.fireNodeChange(node);
                            node = (PositionTree.PositionNode) node.getParent();
                            row--;
                        }
                    }
                }
            } else {
                PositionTree.PositionNode node;
                for (int i = 0; i < root.getChildCount(); i++) {
                    node = (PositionTree.PositionNode) root.getChildAt(i);
                    replaceLines(node, lineId, row);
                    row++;
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public PositionTree.PositionNode getNode(PositionTree.PositionNode root, Integer lineId) {
        try {
            if (root.isLeaf()) {
                if (root.getData()[root.getData().length - 1] instanceof Integer) {
                    Integer id = (Integer) root.getData()[root.getData().length - 1];
                    if (id.intValue() == lineId.intValue()) {
                        return root;
                    }
                }
            } else {
                PositionTree.PositionNode node;
                for (int i = 0; i < root.getChildCount(); i++) {
                    node = (PositionTree.PositionNode) root.getChildAt(i);
                    PositionTree.PositionNode ret = getNode(node, lineId);
                    if (ret != null) {
                        return ret;
                    }
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public void refreshIncludeCounterpartyInCreditCheckBox() {
        boolean hasCreditCol = false;
        if (getTemplate() != null && getTemplate().getTemplateColumnItems() != null) {
            for (TemplateColumnItem item : getTemplate().getTemplateColumnItems()) {
                if (item.getName().equalsIgnoreCase(Measure.CVA.getName())
                        || item.getName().equalsIgnoreCase(Measure.CVA_unit.getName())
                        || item.getName().equalsIgnoreCase(Measure.CREDIT_EXPOSURE.getName())
                        || item.getName().equalsIgnoreCase(Measure.CREDIT_LINE.getName())
                        || item.getName().equalsIgnoreCase(Measure.JUMP_TO_DEFAULT.getName())
                        || item.getName().equalsIgnoreCase(Measure.JUMP_TO_DEFAULT_unit.getName())
                        || item.getName().equalsIgnoreCase(Measure.SINGLE_CREDIT_LINE.getName())) {
                    hasCreditCol = true;
                }
            }
        }
        includeCounterpartyInCreditCheckBox.setVisible(hasCreditCol);
    }

    public class MyHeaderListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            int index = table.getTableHeader().getColumnModel().getColumnIndexAtX(e.getX());

            model.setSortColumn(index);
            model.sort();
            // fill again the
            int row = table.getSelectedRow();
            table = new SortableTreeTable(model);
            table.setName("ReportTable");
            for (int i = 0; i < table.getRowCount(); i++) {
                table.expandRow(i);
            }
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            if (row >= 0 && row < table.getSelectedRow()) {
                table.setRowSelectionInterval(row, row);
            }
            // hide last column : objects id
            TableColumnModel columnModel = table.getColumnModel();
            if (columnModel.getColumnCount() > 0) {
                columnModel.removeColumn(columnModel.getColumn(columnModel.getColumnCount() - 1));
            }

            table.setSortable(true);
            table.setShowGrid(true, true);
            table.setRootVisible(false);
            table.setTreeCellRenderer(new MyTreeCellRenderer());
            table.setColumnControlVisible(true);
            table.setHorizontalScrollEnabled(true);
            table.setFillsViewportHeight(true);

            for (int col = 0; col < table.getColumnCount(); col++) {
                table.getColumn(col).setCellRenderer(new DecimalFormatRenderer(sDecimalFormat));
                table.getColumn(col).setHeaderRenderer(headerRenderer);
                GUIUtils.packColumn(table, col, 2);
            }
            table.addMouseListener(new MyTableListener());
            table.getTableHeader().addMouseListener(new MyHeaderListener());
            table.packAll();
            scrollReportPanel.setViewportView(table);
            table.setVisible(true);

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

    public void emptyArray() {
        PositionTree tree = new PositionTree();
        PositionTree.AggregNode _root = tree.new AggregNode(null);
        headings = new ArrayList();
        if (getTemplate().getTemplateColumnItems() != null) {
            for (Iterator<TemplateColumnItem> it = getTemplate().getTemplateColumnItems().iterator(); it.hasNext();) {
                TemplateColumnItem tci = it.next();
                if (!tci.getDisplayName().isEmpty()) {
                    headings.add(tci.getDisplayName());
                } else {
                    headings.add(tci.getName());
                }
            }
        }
        model = new SortableTreeTableModel(_root, headings);
        table = new SortableTreeTable(model);
        table.setName("ReportTable");
        scrollReportPanel.setViewportView(table);
        table.setVisible(true);
    }

    /**
     * return id of the first leaf node object
     *
     * @return
     */
    public int getFirstLeafObjectId() {
        int id = 0;
        int row = table.getSelectedRow();
        AbstractSortableTreeTableNode node;
        if (row >= 0) {
            TreePath treePath = table.getPathForRow(row);
            Object componentObject = treePath.getPathComponent(treePath.getPathCount() - 1);
            node = (AbstractSortableTreeTableNode) componentObject;
            while (node.getChildCount() > 0) {
                node = (AbstractSortableTreeTableNode) node.getChildAt(0);
            }
            int col = model.getColumnCount() - 1;
            Object objectId = model.getValueAt(node, col);
            if (objectId != null && objectId instanceof Integer) {
                id = Integer.parseInt(objectId.toString());
            }
        }
        return id;
    }

    /**
     * return object id Object componentObject = treePath.getPathComponent(table.getPathForRow(table.getSelectedRow()).getPathCount() - 1);
     *
     * @return seletec object ID
     */
    public int getObjectId() {
        int id = 0;
        int row = table.getSelectedRow();
        if (row >= 0) {
            TreePath treePath = table.getPathForRow(row);
            Object componentObject = treePath.getPathComponent(treePath.getPathCount() - 1);
            AbstractSortableTreeTableNode node = (AbstractSortableTreeTableNode) componentObject;
            SortableTreeTableModel treeTableModel = (SortableTreeTableModel) table.getTreeTableModel();
            int column = treeTableModel.getColumnCount() - 1;
            componentObject = treeTableModel.getValueAt(node, column);
            if (componentObject != null && (componentObject instanceof Integer || componentObject instanceof BigInteger)) {
                id = Integer.parseInt(componentObject.toString());
            }
        }
        return id;
    }

    public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            if (value != null) {
                super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                PositionTree.PositionNode node = (PositionTree.PositionNode) value;
                // only formats first column
                Object nodeValue = node.getValueAt(0);
                if (nodeValue instanceof Integer) {
                    setText(nodeValue.toString());
                } else if (nodeValue instanceof Number) {
                    setText(numberFormat.format(nodeValue));
                } else if (nodeValue != null) {
                    setText(nodeValue.toString());
                } else {
                    setText(StringUtils.EMPTY_STRING);
                }
                setIcon(null);
            }
            return this;
        }
    }

    @Override
    public synchronized void refresh(Object obj) {
        displayResult(obj);
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        cmdScrollPane = new javax.swing.JScrollPane();
        cmdPanel = new javax.swing.JPanel();
        jLabelObject = new javax.swing.JLabel();
        jComboBoxObject = new javax.swing.JComboBox();
        jLabelValDate = new javax.swing.JLabel();
        jComboPEnv = new javax.swing.JComboBox();
        jLabelPEnv = new javax.swing.JLabel();
        jComboCurrency = new javax.swing.JComboBox();
        jLabelCurrency = new javax.swing.JLabel();
        jLabelStartDate = new javax.swing.JLabel();
        collateralPanel = new javax.swing.JPanel();
        internalCounterpartyComboBox = new javax.swing.JComboBox();
        ccpLabel = new javax.swing.JLabel();
        ccpComboBox = new javax.swing.JComboBox();
        generateMarginButton = new javax.swing.JButton();
        cptyRoleComboBox = new javax.swing.JComboBox();
        positionLabel = new javax.swing.JLabel();
        jComboBoxPositionConfiguration = new javax.swing.JComboBox();
        jDateChooserEndDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new MyReportDatesListener());
        jDateChooserStartDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new MyReportDatesListener());
        filterLabel = new javax.swing.JLabel();
        filterComboBox = new javax.swing.JComboBox();
        filterButton = new javax.swing.JButton();
        viewsComboBox = new javax.swing.JComboBox();
        viewLabel = new javax.swing.JLabel();
        jButtonGO = new javax.swing.JButton();
        jButtonDeleteSettings = new javax.swing.JButton();
        jButtonSaveSettings = new javax.swing.JButton();
        viewSeparator = new javax.swing.JSeparator();
        valueDateSeparator = new javax.swing.JSeparator();
        scrollReportPanel = new javax.swing.JScrollPane();
        jProgressBar = new javax.swing.JProgressBar();
        realTimeCheckBox = new javax.swing.JCheckBox();
        rowCountTextField = new javax.swing.JTextField();
        rowLabel = new javax.swing.JLabel();
        reportToolBar = new javax.swing.JToolBar();
        exportExcelButton = new javax.swing.JButton();
        snapshotButton = new javax.swing.JButton();
        deleteSnapshotButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        showTotalSeparator = new javax.swing.JToolBar.Separator();
        showRootCheckBox = new javax.swing.JCheckBox();
        includeCounterpartyInCreditCheckBox = new javax.swing.JCheckBox();
        fixeToolBar = new javax.swing.JToolBar();
        columnConfigButton = new javax.swing.JButton();
        expandSeparator = new javax.swing.JToolBar.Separator();
        jButtonExpand = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1191, 670));

        backgroundPanel.setBackground(new java.awt.Color(255, 255, 255));
        backgroundPanel.setAutoscrolls(true);
        backgroundPanel.setPreferredSize(new java.awt.Dimension(1191, 670));

        cmdScrollPane.setAutoscrolls(true);
        cmdScrollPane.setPreferredSize(new java.awt.Dimension(1191, 82));

        cmdPanel.setBackground(new java.awt.Color(254, 252, 254));
        cmdPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        cmdPanel.setAutoscrolls(true);

        org.openide.awt.Mnemonics.setLocalizedText(jLabelObject, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelObject.text")); // NOI18N

        jComboBoxObject.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxObject.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxObject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxObjectActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelValDate, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelValDate.text")); // NOI18N

        jComboPEnv.setBackground(new java.awt.Color(255, 255, 255));
        jComboPEnv.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboPEnv.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jComboPEnv.toolTipText")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelPEnv, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelPEnv.text")); // NOI18N
        jLabelPEnv.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelPEnv.toolTipText")); // NOI18N

        jComboCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCurrency, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelCurrency.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStartDate, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelStartDate.text")); // NOI18N
        jLabelStartDate.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jLabelStartDate.toolTipText")); // NOI18N

        collateralPanel.setBackground(new java.awt.Color(254, 252, 254));

        internalCounterpartyComboBox.setBackground(new java.awt.Color(255, 255, 255));
        internalCounterpartyComboBox.setName("internalCounterpartyComboBox"); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(ccpLabel, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.ccpLabel.text")); // NOI18N

        ccpComboBox.setBackground(new java.awt.Color(255, 255, 255));
        ccpComboBox.setName("ccpComboBox"); // NOI18N

        generateMarginButton.setBackground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(generateMarginButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.generateMarginButton.text")); // NOI18N
        generateMarginButton.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.generateMarginButton.toolTipText")); // NOI18N
        generateMarginButton.setEnabled(false);

        cptyRoleComboBox.setBackground(new java.awt.Color(255, 255, 255));
        cptyRoleComboBox.setName("cptyRoleComboBox"); // NOI18N
        cptyRoleComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cptyRoleComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout collateralPanelLayout = new javax.swing.GroupLayout(collateralPanel);
        collateralPanel.setLayout(collateralPanelLayout);
        collateralPanelLayout.setHorizontalGroup(
            collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(collateralPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(generateMarginButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cptyRoleComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ccpLabel)
                .addGap(5, 5, 5)
                .addGroup(collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(internalCounterpartyComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ccpComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        collateralPanelLayout.setVerticalGroup(
            collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(collateralPanelLayout.createSequentialGroup()
                .addGroup(collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ccpComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ccpLabel)
                    .addComponent(generateMarginButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(collateralPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(internalCounterpartyComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cptyRoleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        org.openide.awt.Mnemonics.setLocalizedText(positionLabel, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.positionLabel.text")); // NOI18N
        positionLabel.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.positionLabel.toolTipText")); // NOI18N

        jComboBoxPositionConfiguration.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxPositionConfiguration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));
        jComboBoxPositionConfiguration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPositionConfigurationActionPerformed(evt);
            }
        });

        jDateChooserEndDate.setBackground(new java.awt.Color(254, 252, 254));

        jDateChooserStartDate.setBackground(new java.awt.Color(254, 252, 254));

        org.openide.awt.Mnemonics.setLocalizedText(filterLabel, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.filterLabel.text")); // NOI18N

        filterComboBox.setBackground(new java.awt.Color(255, 255, 255));
        filterComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                filterComboBoxItemStateChanged(evt);
            }
        });

        filterButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(filterButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.filterButton.text")); // NOI18N
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });

        viewsComboBox.setBackground(new java.awt.Color(255, 255, 255));
        viewsComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        viewsComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewsComboBoxActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(viewLabel, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.viewLabel.text")); // NOI18N

        jButtonGO.setBackground(new java.awt.Color(0, 255, 0));
        jButtonGO.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonGO, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonGO.text")); // NOI18N
        jButtonGO.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonGO.toolTipText")); // NOI18N
        jButtonGO.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, new java.awt.Color(102, 255, 102), null, null));
        jButtonGO.setBorderPainted(false);
        jButtonGO.setMaximumSize(new java.awt.Dimension(85, 19));
        jButtonGO.setMinimumSize(new java.awt.Dimension(85, 19));
        jButtonGO.setName("jButtonGO"); // NOI18N
        jButtonGO.setPreferredSize(new java.awt.Dimension(85, 19));
        jButtonGO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGOActionPerformed(evt);
            }
        });

        jButtonDeleteSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/trash_16x16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDeleteSettings, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonDeleteSettings.text")); // NOI18N
        jButtonDeleteSettings.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonDeleteSettings.toolTipText")); // NOI18N
        jButtonDeleteSettings.setBorder(null);
        jButtonDeleteSettings.setBorderPainted(false);
        jButtonDeleteSettings.setContentAreaFilled(false);
        jButtonDeleteSettings.setMaximumSize(new java.awt.Dimension(79, 19));
        jButtonDeleteSettings.setMinimumSize(new java.awt.Dimension(79, 19));
        jButtonDeleteSettings.setPreferredSize(new java.awt.Dimension(79, 19));
        jButtonDeleteSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteSettingsActionPerformed(evt);
            }
        });

        jButtonSaveSettings.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/save.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveSettings, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonSaveSettings.text")); // NOI18N
        jButtonSaveSettings.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonSaveSettings.toolTipText")); // NOI18N
        jButtonSaveSettings.setBorder(null);
        jButtonSaveSettings.setBorderPainted(false);
        jButtonSaveSettings.setContentAreaFilled(false);
        jButtonSaveSettings.setMaximumSize(new java.awt.Dimension(80, 19));
        jButtonSaveSettings.setMinimumSize(new java.awt.Dimension(80, 19));
        jButtonSaveSettings.setPreferredSize(new java.awt.Dimension(80, 19));
        jButtonSaveSettings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveSettingsActionPerformed(evt);
            }
        });

        viewSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        valueDateSeparator.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout cmdPanelLayout = new javax.swing.GroupLayout(cmdPanel);
        cmdPanel.setLayout(cmdPanelLayout);
        cmdPanelLayout.setHorizontalGroup(
            cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmdPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelObject, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(viewSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdPanelLayout.createSequentialGroup()
                        .addComponent(viewLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(viewsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdPanelLayout.createSequentialGroup()
                        .addComponent(jButtonSaveSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDeleteSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valueDateSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(jLabelValDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(jLabelStartDate)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(jLabelCurrency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(jLabelPEnv)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboPEnv, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10)
                .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(positionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBoxPositionConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cmdPanelLayout.createSequentialGroup()
                        .addComponent(filterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filterButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(collateralPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonGO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        cmdPanelLayout.setVerticalGroup(
            cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cmdPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(cmdPanelLayout.createSequentialGroup()
                            .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboPEnv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabelPEnv)
                                .addComponent(positionLabel)
                                .addComponent(jComboBoxPositionConfiguration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jComboCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filterLabel)
                                .addComponent(filterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(filterButton)
                                .addComponent(jLabelCurrency)))
                        .addComponent(collateralPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonGO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(valueDateSeparator, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(cmdPanelLayout.createSequentialGroup()
                            .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jDateChooserEndDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelValDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelObject)
                                    .addComponent(jComboBoxObject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(viewLabel)
                                    .addComponent(viewsComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(cmdPanelLayout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(cmdPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonDeleteSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabelStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonSaveSettings, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(cmdPanelLayout.createSequentialGroup()
                                    .addGap(5, 5, 5)
                                    .addComponent(jDateChooserStartDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(cmdPanelLayout.createSequentialGroup()
                            .addComponent(viewSeparator)
                            .addGap(2, 2, 2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmdScrollPane.setViewportView(cmdPanel);

        scrollReportPanel.setAutoscrolls(true);
        scrollReportPanel.setName("scrollReportPanel"); // NOI18N

        jProgressBar.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jProgressBar.toolTipText")); // NOI18N
        jProgressBar.setValue(1);

        realTimeCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(realTimeCheckBox, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.realTimeCheckBox.text")); // NOI18N
        realTimeCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realTimeCheckBoxActionPerformed(evt);
            }
        });

        rowCountTextField.setEditable(false);
        rowCountTextField.setBackground(new java.awt.Color(254, 252, 254));
        rowCountTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        rowCountTextField.setText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.rowCountTextField.text")); // NOI18N
        rowCountTextField.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.rowCountTextField.toolTipText")); // NOI18N
        rowCountTextField.setBorder(null);

        org.openide.awt.Mnemonics.setLocalizedText(rowLabel, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.rowLabel.text")); // NOI18N

        reportToolBar.setBackground(new java.awt.Color(255, 255, 255));
        reportToolBar.setFloatable(false);

        exportExcelButton.setBackground(new java.awt.Color(195, 229, 255));
        exportExcelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/excel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(exportExcelButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.exportExcelButton.text")); // NOI18N
        exportExcelButton.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.exportExcelButton.toolTipText")); // NOI18N
        exportExcelButton.setBorderPainted(false);
        exportExcelButton.setContentAreaFilled(false);
        exportExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportExcelButtonActionPerformed(evt);
            }
        });
        reportToolBar.add(exportExcelButton);

        snapshotButton.setBackground(new java.awt.Color(195, 229, 255));
        snapshotButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/snapshot.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(snapshotButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.snapshotButton.text")); // NOI18N
        snapshotButton.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.snapshotButton.toolTipText")); // NOI18N
        snapshotButton.setBorder(null);
        snapshotButton.setBorderPainted(false);
        snapshotButton.setContentAreaFilled(false);
        snapshotButton.setFocusPainted(false);
        snapshotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                snapshotButtonActionPerformed(evt);
            }
        });
        reportToolBar.add(snapshotButton);

        deleteSnapshotButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/trash_toolbar.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(deleteSnapshotButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.deleteSnapshotButton.text")); // NOI18N
        deleteSnapshotButton.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.deleteSnapshotButton.toolTipText")); // NOI18N
        deleteSnapshotButton.setBorder(null);
        deleteSnapshotButton.setBorderPainted(false);
        deleteSnapshotButton.setContentAreaFilled(false);
        deleteSnapshotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSnapshotButtonActionPerformed(evt);
            }
        });
        reportToolBar.add(deleteSnapshotButton);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/chart.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButton1.text")); // NOI18N
        jButton1.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButton1.toolTipText")); // NOI18N
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        reportToolBar.add(jButton1);

        showTotalSeparator.setSeparatorSize(new java.awt.Dimension(60, 10));
        reportToolBar.add(showTotalSeparator);

        showRootCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        org.openide.awt.Mnemonics.setLocalizedText(showRootCheckBox, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.showRootCheckBox.text")); // NOI18N
        showRootCheckBox.setMaximumSize(new java.awt.Dimension(100, 18));
        showRootCheckBox.setMinimumSize(new java.awt.Dimension(100, 18));
        showRootCheckBox.setPreferredSize(new java.awt.Dimension(100, 18));
        reportToolBar.add(showRootCheckBox);

        includeCounterpartyInCreditCheckBox.setBackground(new java.awt.Color(254, 252, 254));
        includeCounterpartyInCreditCheckBox.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(includeCounterpartyInCreditCheckBox, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.includeCounterpartyInCreditCheckBox.text")); // NOI18N
        includeCounterpartyInCreditCheckBox.setPreferredSize(new java.awt.Dimension(130, 18));
        includeCounterpartyInCreditCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                includeCounterpartyInCreditCheckBoxActionPerformed(evt);
            }
        });
        reportToolBar.add(includeCounterpartyInCreditCheckBox);

        fixeToolBar.setBackground(new java.awt.Color(255, 255, 255));
        fixeToolBar.setFloatable(false);
        fixeToolBar.setRollover(true);

        columnConfigButton.setBackground(new java.awt.Color(195, 229, 255));
        columnConfigButton.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        columnConfigButton.setForeground(new java.awt.Color(204, 204, 204));
        columnConfigButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/bullet_gear.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(columnConfigButton, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.columnConfigButton.text")); // NOI18N
        columnConfigButton.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.columnConfigButton.toolTipText")); // NOI18N
        columnConfigButton.setActionCommand(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.columnConfigButton.actionCommand")); // NOI18N
        columnConfigButton.setBorder(null);
        columnConfigButton.setBorderPainted(false);
        columnConfigButton.setContentAreaFilled(false);
        columnConfigButton.setMaximumSize(new java.awt.Dimension(35, 32));
        columnConfigButton.setMinimumSize(new java.awt.Dimension(35, 32));
        columnConfigButton.setPreferredSize(new java.awt.Dimension(35, 32));
        columnConfigButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnConfigButtonActionPerformed(evt);
            }
        });
        fixeToolBar.add(columnConfigButton);

        expandSeparator.setSeparatorSize(new java.awt.Dimension(20, 20));
        fixeToolBar.add(expandSeparator);

        jButtonExpand.setBackground(new java.awt.Color(255, 255, 255));
        jButtonExpand.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jButtonExpand, org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonExpand.text")); // NOI18N
        jButtonExpand.setToolTipText(org.openide.util.NbBundle.getMessage(ReportTopComponent.class, "ReportTopComponent.jButtonExpand.toolTipText")); // NOI18N
        jButtonExpand.setMaximumSize(new java.awt.Dimension(35, 32));
        jButtonExpand.setMinimumSize(new java.awt.Dimension(35, 32));
        jButtonExpand.setPreferredSize(new java.awt.Dimension(35, 32));
        jButtonExpand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExpandActionPerformed(evt);
            }
        });
        fixeToolBar.add(jButtonExpand);

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(reportToolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(fixeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(cmdScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(scrollReportPanel)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(jProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(realTimeCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rowCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rowLabel)
                .addContainerGap())
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addComponent(cmdScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(reportToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fixeToolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(scrollReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                .addGap(3, 3, 3)
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(realTimeCheckBox)
                        .addComponent(rowCountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rowLabel))
                    .addComponent(jProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonExpandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExpandActionPerformed
        // expand
        if (expanded) {
            expanded = false;
            table.collapseAll();
            jButtonExpand.setText("+");
        } else {
            table.expandAll();
            expanded = true;
            jButtonExpand.setText("-");
        }
    }//GEN-LAST:event_jButtonExpandActionPerformed

    /**
     * save template settings
     *
     * @param evt
     */
    private void jButtonSaveSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveSettingsActionPerformed
        saveSettings();
    }

    private void saveSettings() throws HeadlessException {
        boolean isNew;
        Collection<TemplateColumnItem> removedCols = null;
        if (template == null) {
            template = new ReportTemplate();
        }
        String name = (String) JOptionPane.showInputDialog(this, "Name of the view", "View name", JOptionPane.PLAIN_MESSAGE, null, null,
                getTemplate().getTemplateName());
        /**
         * find out if there
         */
        ReportTemplate template_ = null;
        try {
            template_ = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.GET_REPORT_TEMPLATE_BY_NAME_AND_TYPE,
                    name, ReportUtils.getObjectType(getReportType()));
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        if (template_ != null) {
            isNew = false;
            int paneReturn = JOptionPane.showConfirmDialog(this, "Template alredy exists. Do you want to update it?", "", JOptionPane.YES_NO_OPTION);

            if (paneReturn == 0) {
                template.setTemplateId(template_.getTemplateId());
                removedCols = template_.getTemplateColumnItems();
            } else {
                name = StringUtils.EMPTY_STRING;
            }
        } else {
            template.setTemplateId(null);
            if (template.getTemplateColumnItems() != null) {
                for (TemplateColumnItem templateColumnItem : template.getTemplateColumnItems()) {
                    templateColumnItem.setColumnItemId(null);
                }
            }
            isNew = true;
        }
        if (name != null) {
            if (!name.equals(StringUtils.EMPTY_STRING)) {
                template.setFilter(getFilter());
                template.setTemplateName(name);
                Class objectType = ReportUtils.getObjectType(getReportType());
                template.setObjectTypeClass(objectType);
                template.setShowRoot(showRootCheckBox.isSelected());

                if (objectType.equals(Position.class)) {
                    populatePositionTemplate();
                }
                Collection<TemplateColumnItem> items = template.getTemplateColumnItems();
                if (isNew) {
                    template = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.SAVE_REPORT_TEMPLATE, template);
                    viewsComboBox.addItem(name);
                }
                try {
                    if (items != null) {
                        for (TemplateColumnItem templateColumnItem : items) {

                            TemplateColumnItem tempItem = (TemplateColumnItem) DAOCallerAgent.callMethod(ReportTemplateAccessor.class,
                                    ReportTemplateAccessor.GET_TEMPLATE_COLUMN_ITEM_BY_ID, templateColumnItem.getColumnItemId());

                            if (removedCols != null && removedCols.contains(tempItem)) {
                                removedCols.remove(tempItem);
                            }
                            if (tempItem != null) {
                                templateColumnItem.setColumnItemId(tempItem.getColumnItemId());
                            }
                            templateColumnItem.setTemplate(template);
                            DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.STORE_TEMPLATE_COLUMN_ITEM, templateColumnItem);
                        }
                        if (removedCols != null) {
                            for (TemplateColumnItem templateColumnItem : removedCols) {
                                DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.DELETE_TEMPLATE_COLUMN_ITEM, templateColumnItem);
                            }
                        }
                    }
                    if (getFilter() != null) {
                        DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.STORE_FILTER, getFilter());
                    }
                    if (jComboCurrency.getSelectedItem() != null) {
                        template.setCurrency(jComboCurrency.getSelectedItem().toString());
                    }
                    if (jComboPEnv.getSelectedItem() != null) {
                        template.setPricingEnvironment(jComboPEnv.getSelectedItem().toString());
                    }
                    template.setTemplateColumnItems(items);
                    template = (ReportTemplate) DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.STORE_REPORT_TEMPLATE, template);

                } catch (Exception e) {
                    logger.fatal(StringUtils.formatErrorMessage(e));
                }
            }
            viewsComboBox.setSelectedItem(name);
        }
    }//GEN-LAST:event_jButtonSaveSettingsActionPerformed

    public void populatePositionTemplate() {
        PositionConfiguration configuration = null;
        try {
            configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class,
                    PositionBuilder.GET_POSITION_CONFIGURATION, jComboBoxPositionConfiguration.getSelectedItem().toString());
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        template.setPositionConfiguration(configuration);
    }

    public void loadSettings() {
        try {
            /**
             * load settings
             */
            if (viewsComboBox.getSelectedItem() != null) {
                String name = viewsComboBox.getSelectedItem().toString();
                loadTemplate(name, true);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    private void realTimeCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realTimeCheckBoxActionPerformed
        if (isRealTimeOn) {
            isRealTimeOn = false;
            realTimeCheckBox.setForeground(Color.BLACK);
            myAgent.sendStopRealTimeRequest();
        } else {
            isRealTimeOn = true;
            realTimeCheckBox.setForeground(Color.GREEN);
            runReport();
        }
    }//GEN-LAST:event_realTimeCheckBoxActionPerformed

    private void columnConfigRequest() {
        WindowManager wm = WindowManager.getDefault();
        ColumnFieldsTopComponent columnFieldsTopComponent = (ColumnFieldsTopComponent) wm.findTopComponent("ColumnFieldsTopComponent");
        columnFieldsTopComponent.setDisplayName(columnFieldsTopComponent.getName() + StringUtils.SPACE + getReportType() + StringUtils.SPACE + getTemplate().getTemplateName());
        Mode mode = WindowManager.getDefault().findMode("properties");
        if (mode != null) {
            mode.dockInto(columnFieldsTopComponent);
        }
        columnFieldsTopComponent.loadTemplate(getTemplate());
        columnFieldsTopComponent.open();
        columnFieldsTopComponent.requestActive();
    }
    private void columnConfigButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnConfigButtonActionPerformed
        columnConfigRequest();
    }//GEN-LAST:event_columnConfigButtonActionPerformed

    private void snapshotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_snapshotButtonActionPerformed
        if (root == null) {
            JOptionPane.showMessageDialog(this, "No report loaded");
            return;
        }
        Object[] selectionValues = null;
        String name = (String) JOptionPane.showInputDialog(this, "Snapshot description", "Enter Snapshot description",
                JOptionPane.PLAIN_MESSAGE, null, selectionValues, getCurrentDisplayName());

        if (!StringUtils.isEmptyString(name)) {
            Map<String, String> criteriaMap = new HashMap<>();
            SnapshotReport snapshot = new SnapshotReport();

            criteriaMap.put("ValDate", valDate.toString());
            snapshot.setSnapshotReportValueDate(new Date());

            criteriaMap.put("Pricing Env", jComboPEnv.getSelectedItem().toString());
            snapshot.setSnapshotReportType(getReportType());

            criteriaMap.put("Report Template", getTemplate().getTemplateName());
            snapshot.setSnapshotTemplateName(getTemplate().getTemplateName());

            criteriaMap.put("Report Type", getReportType());

            Collection<FilterCriteria> criteariaList = null;
            if (getFilter() != null) {
                criteariaList = getFilter().getFilterCriteria();
            }
            if (criteariaList != null) {
                for (FilterCriteria criteria : criteariaList) {
                    criteriaMap.put(criteria.getColumnName(), criteria.toString());
                }
            }

            XStream xstream = new XStream(new StaxDriver());
            ByteArrayOutputStream bytesTable = new ByteArrayOutputStream();
            root.insert(headerNode, 0);
            xstream.toXML(root, bytesTable);

            snapshot.setSnapshotBlob(bytesTable.toByteArray());

            ByteArrayOutputStream bytesCriteria = new ByteArrayOutputStream();

            xstream.toXML(criteriaMap, bytesCriteria);
            snapshot.setSnapshotCriteria(bytesCriteria.toByteArray());

            snapshot.setSnapshotDescription(name);
            DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.SAVE_SNAPSHOT_REPORT, snapshot);
            root.remove(headerNode);
            JOptionPane.showMessageDialog(this, "Snapshot saved");
        }

    }//GEN-LAST:event_snapshotButtonActionPerformed

    private void exportExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportExcelButtonActionPerformed
        if (root != null) {
            table.expandAll();
            ExcelReportExporter.generateExcel(table, getTemplate());
        } else {
            JOptionPane.showMessageDialog(this, "No report loaded");
        }
    }//GEN-LAST:event_exportExcelButtonActionPerformed

    /**
     * Delete settings
     *
     */
    private void jButtonDeleteSettingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteSettingsActionPerformed

        Class objectType = ReportUtils.getObjectType(getReportType());
        ArrayList<ReportTemplate> list = (ArrayList) DAOCallerAgent.callMethod(ReportTemplateAccessor.class,
                ReportTemplateAccessor.GET_TEMPLATE_LIST, objectType);
        try {
            String name;
            String mapName;
            mapName = (String) DAOCallerAgent.callMethod(ReportUtils.class, ReportUtils.GET_DEFAULT_TEMPLATE, getReportType(), LoggedUser.getLoggedUserId());
            name = (String) JOptionPane.showInputDialog(this, "Delete the view?", "View delete",
                    JOptionPane.PLAIN_MESSAGE, null, list.toArray(), getTemplate().getTemplateName());

            try {
                if (!mapName.equalsIgnoreCase(name)) {
                    DAOCallerAgent.callMethod(ReportTemplateAccessor.class, ReportTemplateAccessor.DELETE_TEMPLATE, name,
                            ReportUtils.getObjectType(getReportType()));
                    viewsComboBox.removeItem(name);
                } else {
                    JOptionPane.showMessageDialog(this, "You can not delete the default view");
                }
            } catch (HeadlessException e) {
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
        } catch (HeadlessException ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }

    }//GEN-LAST:event_jButtonDeleteSettingsActionPerformed

    private void jComboBoxPositionConfigurationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPositionConfigurationActionPerformed
        if (jComboBoxPositionConfiguration.getSelectedItem() != null) {
            try {
                String configName = jComboBoxPositionConfiguration.getSelectedItem().toString();
                PositionConfiguration configuration = (PositionConfiguration) DAOCallerAgent.callMethod(PositionBuilder.class,
                        PositionBuilder.GET_POSITION_CONFIGURATION, configName);
                if (getTemplate() != null) {
                    getTemplate().setPositionConfiguration(configuration);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }//GEN-LAST:event_jComboBoxPositionConfigurationActionPerformed

    private void jComboBoxObjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxObjectActionPerformed
        updateGuiFromTemplate();
    }

    public void updateGuiFromTemplate() throws HeadlessException {
        if (jComboBoxObject.getSelectedItem() != null) {
            setReportType(jComboBoxObject.getSelectedItem().toString());
            List<String> filterNames = (List<String>) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_NAMES,
                    ReportUtils.getObjectType(getReportType()));
            GUIUtils.fillComboWithNullFirst(filterComboBox, filterNames);
            if (getReportType() != null) {
                /**
                 * empty content before
                 */
                if (getTemplate() != null) {
                    CentralLookup.getDefault().remove(getTemplate());
                }
                Class objectType = ReportUtils.getObjectType(getReportType());
                ArrayList<String> templates = (ArrayList) DAOCallerAgent.callMethod(ReportTemplateAccessor.class,
                        ReportTemplateAccessor.GET_TEMPLATE_LIST, objectType);
                GUIUtils.fillCombo(viewsComboBox, templates);
                /**
                 * load
                 */
                loadDefaultTemplate(getReportType());
                /**
                 * fill content
                 */
                if (getTemplate() != null) {
                    CentralLookup.getDefault().add(this, getTemplate());
                } else {
                    JOptionPane.showMessageDialog(this, "No template defined for report type " + getReportType());
                    setTemplate(new ReportTemplate());
                    setFilter(null);
                    getTemplate().setObjectTypeClass(ReportUtils.getObjectType(getReportType()));
                    columnConfigRequest();
                    CentralLookup.getDefault().add(this, getTemplate());
                }
                emptyArray();
                /**
                 * specific components management
                 */
                if (Position.class.getSimpleName().equalsIgnoreCase(getReportType())) {
                    jComboBoxPositionConfiguration.setEnabled(Boolean.TRUE);
                } else {
                    jComboBoxPositionConfiguration.setEnabled(Boolean.FALSE);
                }
                if (getReportType().equals(SnapshotReport.class.getSimpleName())) {
                    deleteSnapshotButton.setEnabled(Boolean.TRUE);
                    deleteSnapshotButton.setVisible(Boolean.TRUE);
                } else {
                    deleteSnapshotButton.setEnabled(Boolean.FALSE);
                    deleteSnapshotButton.setVisible(Boolean.FALSE);
                }
                if (root != null) {
                    emptyArray();
                }
            }
        }
    }//GEN-LAST:event_jComboBoxObjectActionPerformed

    private void jButtonGOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGOActionPerformed
        // RUN!!!
        runReport();
    }//GEN-LAST:event_jButtonGOActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        WindowManager wm = WindowManager.getDefault();
        FilterTopComponent filterTopComponent = (FilterTopComponent) wm.findTopComponent(FilterTopComponent.class.getSimpleName());
        Mode mode = WindowManager.getDefault().findMode("output");
        if (mode != null) {
            mode.dockInto(filterTopComponent);
        }
        filterTopComponent.open();
        filterTopComponent.setTemplate(getTemplate(), this);
        filterTopComponent.requestActive();
    }//GEN-LAST:event_filterButtonActionPerformed

    private void viewsComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewsComboBoxActionPerformed
        if (evt.getActionCommand().equalsIgnoreCase("comboBoxChanged")) {
            loadSettings();
            refreshIncludeCounterpartyInCreditCheckBox();
            if (jComboPEnv.getSelectedItem() != null) {
                myAgent.sendStopRealTimeRequest();
            }
        }
    }//GEN-LAST:event_viewsComboBoxActionPerformed

    private void includeCounterpartyInCreditCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_includeCounterpartyInCreditCheckBoxActionPerformed
        root = null;
    }//GEN-LAST:event_includeCounterpartyInCreditCheckBoxActionPerformed

    private void filterComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_filterComboBoxItemStateChanged
        if (filterComboBox.getSelectedItem() != null) {
            setFilter((Filter) DAOCallerAgent.callMethod(FilterAccessor.class, FilterAccessor.GET_FILTER_BY_NAME_AND_TYPE, filterComboBox.getSelectedItem().toString(),
                    ReportUtils.getObjectType(getReportType())));
        }
    }//GEN-LAST:event_filterComboBoxItemStateChanged

    private void cptyRoleComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cptyRoleComboBoxActionPerformed
        if (cptyRoleComboBox.getSelectedItem() != null && cptyRoleComboBox.getSelectedItem().toString().equalsIgnoreCase("Counterparty")) {
            String cptyRole = cptyRoleComboBox.getSelectedItem().toString();
            List<String> counterparts;
            if (LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name.equalsIgnoreCase(cptyRole)) {
                counterparts = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            } else {
                counterparts = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_COUNTERPARTIES);
            }
            GUIUtils.fillComboWithNullFirst(internalCounterpartyComboBox, counterparts);
            internalCounterpartyComboBox.setSelectedItem(StringUtils.EMPTY_STRING);
        }
    }//GEN-LAST:event_cptyRoleComboBoxActionPerformed

    private void deleteSnapshotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSnapshotButtonActionPerformed
        boolean refresh = false;
        int[] rows = table.getSelectedRows();
        if (rows.length <= 0) {
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this, "Delete selected snapshot?", "Confirmation", JOptionPane.OK_CANCEL_OPTION);
        if (JOptionPane.OK_OPTION == answer) {
            for (int row : rows) {
                TreePath treePath = table.getPathForRow(row);
                AbstractSortableTreeTableNode node = (AbstractSortableTreeTableNode) treePath.getPathComponent(treePath.getPathCount() - 1);
                Object[] userObject = (Object[]) node.getUserObject();
                Object obj = userObject[userObject.length - 1];
                if (obj instanceof Integer) {
                    Integer id = (Integer) obj;
                    SnapshotReport snap = (SnapshotReport) DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.GET_SNAPSHOT_REPORT_BY_ID, id);
                    if (snap != null) {
                        DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.DELETE_SNAPSHOT_REPORT, snap);
                        refresh = true;
                    }
                }
            }
            if (refresh) {
                runReport();
            }
        }
    }//GEN-LAST:event_deleteSnapshotButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        openBarChart();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void openBarChart() {
        if (root == null) {
            JOptionPane.showMessageDialog(this, "No report loaded");
            return;
        }
        List<String> barChartColumns = new ArrayList<>();
        for (TemplateColumnItem item : getTemplate().getTemplateColumnItems()) {
            if (item.isInChart()) {
                if (item.getDisplayName().isEmpty()) {
                    barChartColumns.add(item.getName());
                } else {
                    barChartColumns.add(item.getDisplayName());

                }
            }
        }
        boolean found = false;
        for (String column : barChartColumns) {
            int index = headings.indexOf(column);
            if (index > -1) {
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Missing bar chart columns, go check domain value bar chart");
            return;
        }
        BarChartPanel barChartPanel = new BarChartPanel((PositionTree.PositionNode) root.getChildAt(0), headings, barChartColumns);
        NotifyDescriptor.Message notifyDescriptor = new NotifyDescriptor.Message(barChartPanel, NotifyDescriptor.PLAIN_MESSAGE);
        DialogDisplayer.getDefault().notify(notifyDescriptor);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backgroundPanel;
    public javax.swing.JComboBox ccpComboBox;
    public javax.swing.JLabel ccpLabel;
    private javax.swing.JPanel cmdPanel;
    private javax.swing.JScrollPane cmdScrollPane;
    public javax.swing.JPanel collateralPanel;
    private javax.swing.JButton columnConfigButton;
    public javax.swing.JComboBox cptyRoleComboBox;
    private javax.swing.JButton deleteSnapshotButton;
    private javax.swing.JToolBar.Separator expandSeparator;
    private javax.swing.JButton exportExcelButton;
    protected javax.swing.JButton filterButton;
    protected javax.swing.JComboBox filterComboBox;
    protected javax.swing.JLabel filterLabel;
    private javax.swing.JToolBar fixeToolBar;
    public javax.swing.JButton generateMarginButton;
    private javax.swing.JCheckBox includeCounterpartyInCreditCheckBox;
    public javax.swing.JComboBox internalCounterpartyComboBox;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonDeleteSettings;
    private javax.swing.JButton jButtonExpand;
    public javax.swing.JButton jButtonGO;
    private javax.swing.JButton jButtonSaveSettings;
    public javax.swing.JComboBox jComboBoxObject;
    public javax.swing.JComboBox jComboBoxPositionConfiguration;
    public javax.swing.JComboBox jComboCurrency;
    private javax.swing.JComboBox jComboPEnv;
    private com.toedter.calendar.JDateChooser jDateChooserEndDate;
    private com.toedter.calendar.JDateChooser jDateChooserStartDate;
    private javax.swing.JLabel jLabelCurrency;
    private javax.swing.JLabel jLabelObject;
    private javax.swing.JLabel jLabelPEnv;
    private javax.swing.JLabel jLabelStartDate;
    private javax.swing.JLabel jLabelValDate;
    private javax.swing.JProgressBar jProgressBar;
    private javax.swing.JLabel positionLabel;
    private javax.swing.JCheckBox realTimeCheckBox;
    public javax.swing.JToolBar reportToolBar;
    public javax.swing.JTextField rowCountTextField;
    private javax.swing.JLabel rowLabel;
    private javax.swing.JScrollPane scrollReportPanel;
    public javax.swing.JCheckBox showRootCheckBox;
    private javax.swing.JToolBar.Separator showTotalSeparator;
    private javax.swing.JButton snapshotButton;
    private javax.swing.JSeparator valueDateSeparator;
    private javax.swing.JLabel viewLabel;
    private javax.swing.JSeparator viewSeparator;
    protected javax.swing.JComboBox viewsComboBox;
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
        if (myAgent != null) {
            myAgent.sendCloseRequest();
        }
    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    private String getStringValDate() {
        if (valDate == null) {
            valDate = new Date();
        }
        return dateFormatter.format(valDate);
    }

    @Override
    protected void componentActivated() {
//        Collection<? extends ReportTemplate> allEvents = CentralLookup.getDefault().lookupAll(ReportTemplate.class);
//        if (allEvents.isEmpty()) {
        Set<TopComponent> opended = TopComponent.getRegistry().getOpened();
        for (TopComponent topComponent : opended) {
            if (topComponent instanceof ColumnFieldsTopComponent) {
                ((ColumnFieldsTopComponent) topComponent).loadTemplate(getTemplate());
                ((ColumnFieldsTopComponent) topComponent).refreshFromTemplate(getTemplate());
            }
        }
//        }
        CentralLookup.getDefault().add(this, getTemplate());
        CentralLookup.setLastActive(this, getTemplate());
    }

    @Override
    protected void componentDeactivated() {
        CentralLookup.getDefault().remove(getTemplate());
    }

    @Override
    public void resultChanged(LookupEvent le) {
        Object source = le.getSource();
        if (source != null) {
            Collection<? extends ReportTemplate> allEvents = CentralLookup.getDefault().lookupAll(ReportTemplate.class);
            if (!allEvents.isEmpty()) {
                ReportTemplate _template = allEvents.iterator().next();
                if (_template != null
                        && this.equals(CentralLookup.LastActiveReportTopComponent(_template))
                        && !_template.isDrillDown()) {
                    setTemplate(_template);
                    refreshIncludeCounterpartyInCreditCheckBox();
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
    }

    private class MyReportDatesListener extends GaiaJSpinnerDateEditor {

        @Override
        public void focusGained(FocusEvent e) {
            jDateChooserEndDate.setMinSelectableDate(jDateChooserStartDate.getDate());
            jDateChooserStartDate.setMaxSelectableDate(jDateChooserEndDate.getDate());
        }

//         @Override
//          public void focusLost(FocusEvent e) {
//            jDateChooserEndDate.setMinSelectableDate(jDateChooserStartDate.getDate());
//            jDateChooserStartDate.setMaxSelectableDate(jDateChooserEndDate.getDate());
//        }
    }
}
