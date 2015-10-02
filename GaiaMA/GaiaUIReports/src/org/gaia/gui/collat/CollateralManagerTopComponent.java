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
package org.gaia.gui.collat;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.MarketQuoteAccessor;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.reports.PositionTree.PositionNode;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.legalEntity.MarginClearerRule;
import org.gaia.domain.reports.CustomColumn;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterCriteria;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionConfiguration;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.reports.ReportTopComponent;
import org.gaia.gui.trades.MarginCallTopComponent;
import org.gaia.gui.trades.TradeUtils;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import org.openide.windows.TopComponent;

/**
 * Collateral Manager Top component.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.collat//CollateralManager//EN", autostore = false)
@TopComponent.Description(preferredID = "CollateralManagerTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.collat.CollateralManagerTopComponent")
@ActionReference(path = "Menu" + MenuManager.CollateralManagerTopComponentMenu, position = MenuManager.CollateralManagerTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CollateralManagerAction")
@Messages({
    "CTL_CollateralManagerAction=Collateral Manager",
    "CTL_CollateralManagerTopComponent=Collateral Manager Window",
    "HINT_CollateralManagerTopComponent=This is a Collateral Manager window"
})
public final class CollateralManagerTopComponent extends ReportTopComponent {

    private ArrayList<TemplateColumnItem> initialColumnList = null;
    private final ArrayList<TemplateColumnItem> localColumnList = new ArrayList<>();
    private final Map<String, ArrayList< String>> mapCounterpartyColumnName = new HashMap<>();
    private LegalEntity selectedCcp;
    private List<MarginClearerRule> rules;
    private static final Logger logger = Logger.getLogger(CollateralManagerTopComponent.class);

    public CollateralManagerTopComponent() {
        super();
        setName(Bundle.CTL_CollateralManagerTopComponent());
        setToolTipText(Bundle.HINT_CollateralManagerTopComponent());
    }

    /**
     * return display Name of current Window
     *
     * @return String
     */
    @Override
    public String getDisplayName() {
        return "Collateral Manager";
    }

    /**
     *
     */
    @Override
    public void initContext() {
        super.initContext();
        cptyRoleComboBox.addItem(LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name);
        cptyRoleComboBox.addItem(LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name);
        cptyRoleComboBox.setSelectedIndex(0);
        LocalActionListener localListener = new LocalActionListener();
        cptyRoleComboBox.addActionListener(localListener);
        generateMarginButton.addActionListener(localListener);
        filterComboBox.setVisible(false);
        filterLabel.setVisible(false);
        filterButton.setVisible(false);
        jComboBoxObject.setSelectedItem(Position.class.getSimpleName());
        jComboBoxObject.setEnabled(false);
        collateralPanel.setVisible(true);
        ArrayList< String> columnMethodList = new ArrayList<>();
        columnMethodList.add(0, "Counterparty.ShortName");
        columnMethodList.add(1, "getCounterparty/getShortName");
        mapCounterpartyColumnName.put(LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name, columnMethodList);
        columnMethodList = new ArrayList<>();
        columnMethodList.add(0, "InternalCounterparty.ShortName");
        columnMethodList.add(1, "getInternalCounterparty/getShortName");
        mapCounterpartyColumnName.put(LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name, columnMethodList);
        refreshContext();
    }

    @Override
    public void loadDefaultTemplate(String reportType) {
        try {
            String name = (String) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_COLLATERAL_VIEW, LoggedUser.getLoggedUserId());
            if (name != null) {
                loadTemplate(name, false);
                if (getTemplate() != null) {
                    getTemplate().setFilter(getLocalFilter());
                }
                viewsComboBox.setSelectedItem(name);
            } else {
                viewsComboBox.setSelectedIndex(0);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     *
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentOpened() {
        initContext();
        Utilities.actionsGlobalContext().lookupResult(ReportTemplate.class).removeLookupListener(this);
    }

    /**
     *
     */
    @Override
    public void componentClosed() {
    }

    /**
     *
     */
    @Override
    public void displayResult(Object obj) {
        super.displayResult(obj);
        table.addMouseListener(new MyTableListener());
        generateMarginButton.setEnabled(true);

    }

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }

    /**
     * Brief add column dynamically to the current Report Template from Margin
     * Call Rules
     */
    private void addDefaultsColumns() {
        if (getTemplate() != null && getTemplate().getTemplateColumnItems() != null) {
            initialColumnList = new ArrayList(getTemplate().getTemplateColumnItems());
            localColumnList.clear();
            localColumnList.addAll(initialColumnList);
            if (ccpComboBox.getSelectedItem() != null) {
                selectedCcp = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, ccpComboBox.getSelectedItem().toString());
            }
            if (selectedCcp != null) {
                rules = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_MARGIN_CLEARER_RULES, selectedCcp.getLegalEntityId());
            }

            TemplateColumnItem templateColumnItem;
            int lastIndex = initialColumnList.size();
            if (rules != null) {
                for (MarginClearerRule rule : rules) {
                    CustomColumn ruleColumn = rule.getCustomColumn();
//                String ruleDisplayName = getColumnByMarginType(rule.getMarginType());
                    if (!checkExistByDisplayName(rule.getMarginType()) && ruleColumn != null) {
                        templateColumnItem = new TemplateColumnItem();
                        templateColumnItem.setName(ruleColumn.getName());
                        templateColumnItem.setGetter("get" + ruleColumn.getName());
                        templateColumnItem.setAggregation("Sum");
                        templateColumnItem.setIsAggregated(false);
                        templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_CUSTOM);
                        templateColumnItem.setReturnType(BigDecimal.class.toString());
                        templateColumnItem.setParam("");
                        templateColumnItem.setColumnIndex(lastIndex);
                        templateColumnItem.setIsConversion(true);
                        templateColumnItem.setDisplayName(rule.getMarginType());
                        templateColumnItem.setSuffix("");

                        localColumnList.add(templateColumnItem);
                        lastIndex++;

                    }
                }
            }
            if (cptyRoleComboBox.getSelectedItem() != null) {
                addCounterpartyColumn(cptyRoleComboBox.getSelectedItem().toString());
            }
            getTemplate().setTemplateColumnItems(localColumnList);
        }
    }

    /**
     * add/update counterparty or internalCounterparty column to be a first
     * templateColumitem for aggregation
     * <p/>
     * @return
     */
    private void addCounterpartyColumn(String cptySelectedRole) {
        boolean decaleIndex = false;
        ArrayList<String> columnMethodConf = mapCounterpartyColumnName.get(cptySelectedRole);
        if (columnMethodConf != null && checkExist(columnMethodConf.get(0))) {
            for (TemplateColumnItem templateColumnItem : localColumnList) {
                if (templateColumnItem.getName().equalsIgnoreCase(columnMethodConf.get(0)) && templateColumnItem.getColumnIndex() != 0) {
                    templateColumnItem.setIsAggregated(true);
                    templateColumnItem.setColumnIndex(0);
                    decaleIndex = true;
                    break;
                }
            }
        } else if (columnMethodConf != null) {
            TemplateColumnItem templateColumnItem = new TemplateColumnItem();
            templateColumnItem.setName(columnMethodConf.get(0));
            templateColumnItem.setGetter(columnMethodConf.get(1));
            templateColumnItem.setAggregation(null);
            templateColumnItem.setIsAggregated(true);
            templateColumnItem.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
            templateColumnItem.setReturnType(String.class.getName());
            templateColumnItem.setParam("");
            templateColumnItem.setColumnIndex(0);
            templateColumnItem.setIsConversion(false);
            templateColumnItem.setSuffix("");
            localColumnList.add(0, templateColumnItem);
            decaleIndex = true;
        }
        if (decaleIndex) {
            for (TemplateColumnItem templateColumnItem : localColumnList) {
                if (templateColumnItem.getColumnIndex() != 0) {
                    templateColumnItem.setColumnIndex(localColumnList.indexOf(templateColumnItem) + 1);
                }
            }
        }
    }

    /**
     * \brief add Margin Call columns to the current Template and set current
     * filter before running report
     * <p/>
     *
     */
    @Override
    public void runReport() {
        addDefaultsColumns();
        setFilter(getLocalFilter());
        getTemplate().setReportEnabled(true);
        if (getLocalFilter() != null && !getLocalFilter().getFilterCriteria().isEmpty()) {
            super.runReport();
        }
    }

    /**
     * \brief filter with ccp and counterparty fixed criteria
     * <p/>
     */
//    @Override
//    public Filter getFilter() {
//        return null;
//    }
    public Filter getLocalFilter() {
        Filter _filter = new Filter();
        FilterCriteria criteriaInternalCounterparty;
        ArrayList<FilterCriteria> criteriaList = new ArrayList<>();
        if (!StringUtils.isEmptyString((String) internalCounterpartyComboBox.getSelectedItem())) {
            String cptySelectedRole = cptyRoleComboBox.getSelectedItem().toString();
            ArrayList<String> columnMethodConf = mapCounterpartyColumnName.get(cptySelectedRole);
            criteriaInternalCounterparty = new FilterCriteria(columnMethodConf.get(0), (String) internalCounterpartyComboBox.getSelectedItem(),
                    null, columnMethodConf.get(1), String.class.getName(), TemplateColumnItem.COLUMN_STANDARD);
            criteriaList.add(criteriaInternalCounterparty);
        }
        if (ccpComboBox.getSelectedItem() != null) {
            FilterCriteria criteriaCCP = new FilterCriteria("Ccp.ShortName", ccpComboBox.getSelectedItem().toString(),
                    null, "getCcp/getShortName", String.class.getName(), TemplateColumnItem.COLUMN_STANDARD);
            criteriaList.add(criteriaCCP);
            _filter.setFilterCriteria(criteriaList);
        }
        return _filter;
    }

    /**
     * \brief check if column exist except margin call columns they must be
     * updated with sum and conversion properties
     * <p/>
     * \return return false if exist except Margin Call columns
     */
    private boolean checkExistByDisplayName(String columnName) {

        for (TemplateColumnItem templateColumnItem : localColumnList) {
            if (templateColumnItem.getDisplayName() != null && templateColumnItem.getDisplayName().equalsIgnoreCase(columnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * \brief check if column exist except margin call columns they must be
     * updated with sum and conversion properties
     * <p/>
     * \return return false if exist except Margin Call columns
     */
    private boolean checkExist(String columnName) {

        for (TemplateColumnItem templateColumnItem : localColumnList) {
            if (templateColumnItem.getName() != null && templateColumnItem.getName().equalsIgnoreCase(columnName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * \brief mapping column index and margin margin types
     * <p/>
     * \return Map<Integer, String> column index , margin type
     */
    private Map<Integer, String> getColumnMarginTypeIndex() {
        Map<Integer, String> marginTypeColumnList = new HashMap<>();

        for (MarginClearerRule rule : rules) {
            for (String header : headings) {
                if (header.contains(rule.getMarginType())) {
                    marginTypeColumnList.put(headings.indexOf(header), rule.getMarginType());
                }
            }
        }
        return marginTypeColumnList;
    }

    /**
     * \brief generate margin call from current config \param <generateAll> true
     * for global generation false for right click
     */
    private void generateMarginCall(boolean generateAll) {

        List<BoAccount> accountlist;
        StringBuilder errors = new StringBuilder();
        StringBuilder message = new StringBuilder();
        ArrayList<MarginCreation> creationList = new ArrayList<>();
        if (generateAll && root != null) {
            for (int i = 0; i < root.getChildCount(); i++) {
                try {
                    PositionNode internalCounterpartyNode = (PositionNode) root.getChildAt(i);
                    Object[] nodeData = internalCounterpartyNode.getData();
                    LegalEntity internalCounterparty = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                            LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, (nodeData[0].toString()));
                    accountlist = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                            LegalEntityAccessor.GET_BO_ACCOUNTS, internalCounterparty.getLegalEntityId(),
                            selectedCcp.getLegalEntityId(), LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
                    if (accountlist.isEmpty()) {
                        errors.append("No account defined for ");
                        errors.append(internalCounterparty.getShortName());
                        errors.append(" and CCP ");
                        errors.append(selectedCcp.getShortName());
                        errors.append("\n");
                        continue;
                    }

                    for (Map.Entry<Integer, String> entry : getColumnMarginTypeIndex().entrySet()) {
                        Integer index = entry.getKey();
                        String marginType = entry.getValue();
                        BigDecimal marginSum = (BigDecimal) nodeData[index];
                        marginSum = (BigDecimal) DAOCallerAgent.callMethod(MarketQuoteAccessor.class, MarketQuoteAccessor.CONVERT_AMOUNT, marginSum, accountlist.get(0).getCurrency().toString(), jComboCurrency.getSelectedItem().toString(), valDate);
                        if (marginSum != null && 0 != marginSum.doubleValue()) {
                            MarginCreation margin = new MarginCreation();
                            margin.setAccount(accountlist.get(0));
                            margin.setMarginType(marginType);
                            margin.setQuantity(marginSum);
                            creationList.add(margin);
                        }
                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }

        } else {
            if (getFirstLeafObjectId() > 0) {
                try {
                    Position position = (Position) DAOCallerAgent.callMethod(PositionBuilder.class,
                            PositionBuilder.GET_POSITION_AND_POSITION_HISTORY, getFirstLeafObjectId(), new Date());
                    accountlist = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                            LegalEntityAccessor.GET_BO_ACCOUNTS, position.getInternalCounterparty().getLegalEntityId(),
                            selectedCcp.getLegalEntityId(), LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
                    if (!accountlist.isEmpty()) {
                        for (Map.Entry<Integer, String> entry : getColumnMarginTypeIndex().entrySet()) {
                            Integer index = entry.getKey();
                            String marginType = entry.getValue();
                            BigDecimal marginSum = (BigDecimal) getValueAtSelectedRow(table.getSelectedRow(), index);
                            marginSum = (BigDecimal) DAOCallerAgent.callMethod(MarketQuoteAccessor.class,
                                    MarketQuoteAccessor.CONVERT_AMOUNT, marginSum, accountlist.get(0).getCurrency().toString(), jComboCurrency.getSelectedItem().toString(), valDate);
                            if (marginSum != null && 0 != marginSum.doubleValue()) {
                                MarginCreation margin = new MarginCreation();
                                margin.setAccount(accountlist.get(0));
                                margin.setMarginType(marginType);
                                margin.setQuantity(marginSum);
                                creationList.add(margin);
                            }
                        }
                    } else {
                        errors.append("No account defined for ");
                        errors.append(position.getInternalCounterparty().getShortName());
                        errors.append(" and CCP ");
                        errors.append(selectedCcp.getShortName());
                        errors.append("\n");
                    }
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            } else {
                errors.append("Invalid position");
            }
        }
        if (!creationList.isEmpty()) {
            ArrayList<Trade> tradeCreated = new ArrayList<>();
            for (MarginCreation marginCreation : creationList) {
                tradeCreated.add(TradeUtils.createMarginCall(marginCreation.getQuantity(), marginCreation.getMarginType(), marginCreation.getAccount()));
            }
            for (Trade trade : tradeCreated) {
                if (!generateAll) {
                    MarginCallTopComponent marginWindow = new MarginCallTopComponent();
                    marginWindow.open();
                    marginWindow.fillWindowWithTrade(trade);
                } else {
                    trade = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.STORE_TRADE, trade);
                    message.append("Margin Call on ");
                    message.append(trade.getInternalCounterparty().getShortName());
                    message.append("created with id ");
                    message.append(trade.getTradeId());
                    message.append("\n");
                }
            }
            JOptionPane.showMessageDialog(this, tradeCreated.size() + " margin calls created:\n" + message, "Margin Calls Created", JOptionPane.INFORMATION_MESSAGE);
        }
        if (errors.length() > 0) {
            JOptionPane.showMessageDialog(this, errors.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshContext() throws HeadlessException {
        try {
            String cptyRole = cptyRoleComboBox.getSelectedItem().toString();
            List<PositionConfiguration> positionConfList = (List) DAOCallerAgent.callMethod(PositionBuilder.class,
                    PositionBuilder.GET_COLLATERAL_POSITION_CONFIGURATIONS, cptyRole);
            if (positionConfList.isEmpty()) {
                if (LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name.equalsIgnoreCase(cptyRole)) {
                    positionConfList = (List) DAOCallerAgent.callMethod(PositionBuilder.class,
                            PositionBuilder.GET_COLLATERAL_POSITION_CONFIGURATIONS,
                            LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name);
                } else {
                    positionConfList = (List) DAOCallerAgent.callMethod(PositionBuilder.class,
                            PositionBuilder.GET_COLLATERAL_POSITION_CONFIGURATIONS, LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name);
                }
            }

            if (positionConfList.isEmpty()) {
                jButtonGO.setEnabled(false);
                JOptionPane.showMessageDialog(this, "No Position configuration compatible with collateral Manager Found");
            } else {
                jComboBoxPositionConfiguration.removeAllItems();
                for (PositionConfiguration configuration : positionConfList) {
                    jComboBoxPositionConfiguration.addItem(configuration.getName());
                }
            }
            if (ccpComboBox.getModel().getSize() == 0) {
                List<String> CCPs = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_CCPS);
                GUIUtils.fillCombo(ccpComboBox, CCPs);
            }

            List<String> counterparts;
            if (LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name.equalsIgnoreCase(cptyRole)) {
                counterparts = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_INTERNAL_COUNTERPARTIES);
            } else {
                counterparts = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_COUNTERPARTIES);
            }
            GUIUtils.fillComboWithNullFirst(internalCounterpartyComboBox, counterparts);

            internalCounterpartyComboBox.setSelectedItem(StringUtils.EMPTY_STRING);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     *
     * @return
     */
    private class MyTableListener implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent e) {

            int r = table.rowAtPoint(e.getPoint());
            if (r >= 0 && r < table.getRowCount()) {
                table.setRowSelectionInterval(r, r);
            } else {
                table.clearSelection();
            }

            int rowindex = table.getSelectedRow();
            if (rowindex < 0) {
                return;
            }
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                JPopupMenu popup = new JPopupMenu();
                popup.setName("GenerateMarginCall");
                JMenuItem menuItem = new JMenuItem("Generate margin call");
                ActionListener menuListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        generateMarginCall(false);
                    }
                };
                menuItem.addActionListener(menuListener);
                popup.add(menuItem);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                JPopupMenu popup = new JPopupMenu();
                popup.setName("GenerateMarginCall");
                JMenuItem menuItem = new JMenuItem("Generate margin call");
                ActionListener menuListener = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        generateMarginCall(false);
                    }
                };
                menuItem.addActionListener(menuListener);
                popup.add(menuItem);
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }

    private class LocalActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == generateMarginButton) {
                generateMarginCall(true);
            } else if (object == cptyRoleComboBox) {
                refreshContext();
            }
        }
    }

    private class MarginCreation {

        BoAccount getAccount() {
            return account;
        }

        void setAccount(BoAccount account) {
            this.account = account;
        }

        String getMarginType() {
            return marginType;
        }

        void setMarginType(String marginType) {
            this.marginType = marginType;
        }

        BigDecimal getQuantity() {
            return quantity;
        }

        void setQuantity(BigDecimal quantity) {
            this.quantity = quantity;
        }
        BoAccount account;
        String marginType;
        BigDecimal quantity;
    }
}
