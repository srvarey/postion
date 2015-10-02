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
package org.gaia.gui.utils;

import com.l2fprod.common.swing.renderer.DateRenderer;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDateChooserCellEditor;
import java.awt.Color;
import java.awt.Component;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import static org.gaia.gui.common.GaiaTopComponent.numberFormat;
import org.gaia.gui.common.LegalEntityFinder;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Benjamin Frerejean
 */
public class GUIUtils {

    public static final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    private static final Logger logger = Logger.getLogger(GUIUtils.class);

    public static Color randomColor() {
        Random random = new Random();
        int red = random.nextInt(256);
        int green = random.nextInt(256);
        int blue = random.nextInt(256);
        Color color = new Color(red, green, blue);
        return color.darker();
    }

    public static void treeOpenAll(JTree tree, DefaultMutableTreeNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            treeOpenAll(tree, (DefaultMutableTreeNode) node.getChildAt(i));
            tree.expandPath(new TreePath(((DefaultMutableTreeNode) node.getChildAt(i)).getPath()));
        }
    }

    public static void treeOpenLevel2(JTree tree, DefaultMutableTreeNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getChildCount(); i++) {
            tree.expandPath(new TreePath(((DefaultMutableTreeNode) node.getChildAt(i)).getPath()));
        }
    }

    public static void addTableRow(JTable table, Object[] row) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.addRow(row);
    }

    public static Vector getHeader(JTable table) {
        Vector<String> tableHeaders = new Vector<>();
        JTableHeader h = table.getTableHeader();
        Enumeration<TableColumn> columns = h.getColumnModel().getColumns();

        while (columns.hasMoreElements()) {
            TableColumn column = columns.nextElement();
            tableHeaders.add(column.getHeaderValue().toString());
        }
        return tableHeaders;
    }

    public static String getTableValueAt(JTable table, int row, int col) {
        if (row < table.getRowCount() && row >= 0) {
            if (table.getValueAt(row, col) != null) {
                return table.getValueAt(row, col).toString();
            }
        }
        return StringUtils.EMPTY_STRING;
    }

    public static Date getDateTableValueAt(JTable table, int row, int col) {
        if (row < table.getRowCount() && row >= 0) {
            if (table.getValueAt(row, col) != null) {
                if (table.getValueAt(row, col) instanceof Date) {
                    return (Date) table.getValueAt(row, col);
                } else if (table.getValueAt(row, col) instanceof String) {
                    String text = (String) table.getValueAt(row, col);
                    try {
                        return GaiaTopComponent.dateFormatter.parse(text);
                    } catch (ParseException e) {
                        logger.warn(StringUtils.formatErrorMessage(e));
                    }
                }
            }
        }
        return null;
    }

    public static void fillCombo(JComboBox comboBox, List<String> values) {
        if (values != null) {
            DefaultComboBoxModel model = new DefaultComboBoxModel(values.toArray());
            comboBox.setModel(model);
        }
    }

    public static void clearTableModel(DefaultTableModel tableModel) {
        tableModel.getDataVector().removeAllElements();
    }

    public static void fillComboWithNullFirst(JComboBox comboBox, List<String> values) {
        Object[] arrayValues = null;
        if (values != null) {
            arrayValues = new Object[values.size() + 1];
            arrayValues[0] = null;
            int i = 1;
            for (Object obj : values) {
                arrayValues[i] = obj;
                i++;
            }
        }
        DefaultComboBoxModel model = new DefaultComboBoxModel(arrayValues);
        comboBox.setModel(model);
    }

    public static void fillComboWithNullFirst(JComboBox comboBox, String[] values) {
        comboBox.removeAllItems();
        comboBox.addItem(null);
        if (values != null) {
            for (String value : values) {
                comboBox.addItem(value);
            }
        }
    }

    public static String getComponentStringValue(Component component) {
        if (component instanceof JComboBox) {
            JComboBox combo = (JComboBox) component;
            if (combo.getSelectedItem() != null && !combo.getSelectedItem().toString().equalsIgnoreCase("")) {
                return combo.getSelectedItem().toString();
            }
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            return field.getText();
        } else if (component instanceof JLabel) {
            JLabel field = (JLabel) component;
            return field.getText();
        }
        return null;
    }

    public static void emptyTable(JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }
    }

    public static Date getComponentDateValue(Component component) {
        Date ret = null;
        String text = null;
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            text = field.getText();
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            text = field.getText();
        } else if (component instanceof JComboBox) {
            JComboBox field = (JComboBox) component;
            if (field.getSelectedItem() != null) {
                text = field.getSelectedItem().toString();
            }
        } else if (component instanceof JDateChooser) {
            JDateChooser field = (JDateChooser) component;
            ret = field.getDate();
        }
        try {
            if (text != null && !text.isEmpty()) {
                ret = GaiaTopComponent.dateFormatter.parse(text);
            }
        } catch (Exception e) {
            logger.warn(StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static BigDecimal convertBuySellStringToBigDecimal(String type) {
        if (type.equalsIgnoreCase(StringUtils.BUY)) {
            return BigDecimal.ONE;
        }
        return BigDecimal.ONE.negate();
    }

    public static BigDecimal convertCallPutStringToBigDecimal(String type) {
        if (type.equalsIgnoreCase(StringUtils.CALL)) {
            return BigDecimal.ONE;
        }
        return BigDecimal.ONE.negate();
    }

    public static String convertBuySellBigDecimalToString(BigDecimal type) {
        if (BigDecimal.ONE.equals(type)) {
            return StringUtils.BUY;
        }
        return StringUtils.SELL;
    }

    public static String guiDateFormatToHibernateFormat(String sInDate) {
        try {
            Date date = GaiaTopComponent.dateFormatter.parse(sInDate);
            DateFormat format = (DateFormat) DAOCallerAgent.callMethod(DateUtils.class, DateUtils.GET_HIBERNATE_DATE_FORMAT);
            return format.format(date);
        } catch (Exception e) {
            StringUtils.formatErrorMessage(e);
        }
        return "null";
    }

    public static String hibernateDateFormatToGuiFormat(String sInDate) {
        try {
            Date date = HibernateUtil.dateFormat.parse(sInDate);
            return GaiaTopComponent.dateFormatter.format(date);
        } catch (Exception e) {
            StringUtils.formatErrorMessage(e);
        }
        return GaiaTopComponent.dateFormatter.format(DateUtils.getDate());
    }

    public static BigDecimal getComponentBigDecimalValue(Component component) {
        BigDecimal ret = BigDecimal.ZERO;
        if (component instanceof JFormattedTextField) {
            JFormattedTextField field = (JFormattedTextField) component;
            try {
                if (!StringUtils.isEmptyString(field.getText())) {
                    ret = BigDecimal.valueOf(numberFormat.parse(field.getText()).doubleValue());
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            try {
                if (!StringUtils.isEmptyString(field.getText())) {
                    ret = new BigDecimal(Double.parseDouble(field.getText()));
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        } else if (component instanceof JLabel) {
            JLabel field = (JLabel) component;
            try {
                if (!StringUtils.isEmptyString(field.getText())) {
                    ret = new BigDecimal(Double.parseDouble(field.getText()));
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return ret;
    }

    public static Integer getComponentIntegerValue(Object component) {
        Integer ret = null;
        String text = null;
  

        if (component instanceof JComboBox) {
            JComboBox combo = (JComboBox) component;
            if (combo.getSelectedItem() != null) {
                text = combo.getSelectedItem().toString();
            }
        } else if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            text = field.getText();
        } else if (component instanceof JLabel) {
            JLabel field = (JLabel) component;
            text = field.getText();
        } else {
            text = component.toString();
        }
        if (!StringUtils.isEmptyString(text)) {
            try {
                ret = Integer.parseInt(text);
            } catch (NumberFormatException e) {
                logger.warn(StringUtils.formatErrorMessage(e));
            }
        }
        return ret;
    }

    public static Date getComponentTimeValue(Object component) {
        Date ret = null;
        String text;
        if (component instanceof JTextField) {
            JTextField field = (JTextField) component;
            text = field.getText();
            try {
                if (!text.isEmpty()) {
                    ret = GaiaTopComponent.timeFormatter.parse(text);
                }
            } catch (ParseException e) {
                logger.warn(StringUtils.formatErrorMessage(e));
            }
        }
        return ret;
    }

    public static void packAllColumns(JTable table) {
        for (int c = 0; c < table.getColumnCount(); c++) {
            packColumn(table, c, 2);
        }
    }

    /**
     * Sets the preferred width of the visible column specified by vColIndex.
     * The column will be just wide enough to show the column head and the
     * widest cell in the column. margin pixels are added to the left and right
     * (resulting in an additional width of 2*margin pixels).
     *
     * @param table
     * @param vColIndex
     * @param margin
     */
    public static void packColumn(JTable table, int vColIndex, int margin) {
        DefaultTableColumnModel colModel = (DefaultTableColumnModel) table.getColumnModel();
        TableColumn column = colModel.getColumn(vColIndex);
        int width;

        /**
         * Get width of column header
         */
        TableCellRenderer renderer = column.getHeaderRenderer();
        if (renderer == null) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }
        Component comp = renderer.getTableCellRendererComponent(
                table, column.getHeaderValue(), false, false, 0, 0);
        width = comp.getPreferredSize().width;

        /**
         * Get maximum width of column data
         */
        for (int r = 0; r < table.getRowCount(); r++) {
            renderer = table.getCellRenderer(r, vColIndex);
            if (!(renderer instanceof DateRenderer)) {
                comp = renderer.getTableCellRendererComponent(table, table.getValueAt(r, vColIndex), false, false, r, vColIndex);
                width = Math.max(width, comp.getPreferredSize().width);
            } else {
                width = 50;
            }

        }

        /**
         * Add margin
         */
        width += 2 * margin;

        /**
         * Set the width
         */
        column.setPreferredWidth(width);
    }

    public void packAll(JTable table) {
        for (int col = 0; col < table.getColumnCount(); col++) {
            GUIUtils.packColumn(table, col, 2);
        }
    }

    public static List<String> formatDateList(List<Date> dateList, DateFormat dateFormat) {
        List<String> result = new ArrayList();
        for (Date date : dateList) {
            result.add(dateFormat.format(date));
        }
        return result;
    }

    public static String getMessage(Component component, Exception exception) {
        String newLine = System.getProperty("line.separator");
        String message = "Error on : " + component.getName();
        return message + newLine + StringUtils.formatErrorMessage(exception);
    }

    /**
     * find counterparty
     *
     * @return
     */
    public static LegalEntity findCounterParty() {
        List<String> roles = new ArrayList();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.COUNTERPARTY_ROLE.name);
        roles.add(LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
        roles.add(LegalEntityRole.LegalEntityRoleEnum.MARKET_ROLE.name);
        roles.add(LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name);
        roles.add(LegalEntityRole.LegalEntityRoleEnum.BROKER_ROLE.name);
        return findLegalEntity(roles);
    }

    /**
     * find a credit entity
     *
     * @return
     */
    public static LegalEntity findCreditEntity() {
        List<String> roles = new ArrayList();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name);
        roles.add(LegalEntityRole.LegalEntityRoleEnum.ISSUER_ROLE.name);
        return findLegalEntity(roles);
    }

    /**
     * find a legal entity from a role list
     *
     * @param roles
     * @return
     */
    public static LegalEntity findLegalEntity(List<String> roles) {

        final LegalEntityFinder lef = new LegalEntityFinder(roles);
        LegalEntity legalEntity = null;
        NotifyDescriptor nd = new NotifyDescriptor(lef, "Entity Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null,
                NotifyDescriptor.NO_OPTION);
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer entityId = lef.getLegalEntityId();
            /**
             * return legal Entity
             */
            if (entityId != null) {
                try {
                    legalEntity = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_ID, entityId);
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
        return legalEntity;
    }

    public static void setCombo(JTable table, int colNum, List<String> valueList) {
        if (colNum < table.getColumnModel().getColumnCount()) {
            String[] arrayDataObjects = (String[]) valueList.toArray(new String[valueList.size()]);
            TableColumn col = table.getColumnModel().getColumn(colNum);
            col.setCellEditor(new DefaultCellEditor(new JComboBox(arrayDataObjects)));
        }
    }

    public static void setCombo(JTable table, int colNum, String[] valueList) {
        if (colNum < table.getColumnModel().getColumnCount()) {
            TableColumn col = table.getColumnModel().getColumn(colNum);
            col.setCellEditor(new DefaultCellEditor(new JComboBox(valueList)));
        }
    }

    public static void setNumberEditor(JTable table, int colNum, String format) {
        setNumberEditor(table, colNum, new DecimalFormat(format));
    }

    public static void setNumberEditor(JTable table, int colNum, DecimalFormat format) {
        if (colNum < table.getColumnModel().getColumnCount()) {
            TableColumn col = table.getColumnModel().getColumn(colNum);
            JFormattedTextField field = new JFormattedTextField(format);
            field.setHorizontalAlignment(JFormattedTextField.RIGHT);
            Border empty = new EmptyBorder(0, 3, 0, 0);
            field.setBorder(empty);
            TableCellEditor editor = col.getCellEditor();
            if (editor == null) {
                for (Class clazz : new Class[]{Object.class, String.class, Double.class, Integer.class}) {
                    editor = table.getDefaultEditor(clazz);
                    if (editor != null) {
                        break;
                    }
                }
            }
            CellEditorListener[] listeners = null;
            if (editor instanceof DefaultCellEditor) {
                DefaultCellEditor dce = (DefaultCellEditor) editor;
                listeners = dce.getCellEditorListeners();
            }
            editor = new DefaultCellEditor(field);
            if (listeners != null) {
                for (CellEditorListener listener : listeners) {
                    editor.addCellEditorListener(listener);
                }
            }
            col.setCellEditor(editor);
            UtilsDecimalFormatRenderer renderer = new UtilsDecimalFormatRenderer(format.toPattern());
            col.setCellRenderer(renderer);
        }
    }

    public static void setCheckBoxEditor(JTable table, int colNum) {
        if (colNum < table.getColumnModel().getColumnCount()) {
            TableColumn selCol = table.getColumnModel().getColumn(colNum);
            JCheckBox check = new JCheckBox();
            check.setHorizontalAlignment(SwingConstants.CENTER); // without this, checkbox move to left during click
            selCol.setCellEditor(new DefaultCellEditor(check));
            selCol.setCellRenderer(table.getDefaultRenderer(Boolean.class));
        }
    }

    public static void setDateEditor(JTable table, int colNum) {
        if (colNum < table.getColumnModel().getColumnCount()) {
            TableColumn col = table.getColumnModel().getColumn(colNum);
            col.setCellRenderer(new DateRenderer(dateFormat));
            JFormattedTextField field = new JFormattedTextField();
            field.setHorizontalAlignment(JFormattedTextField.RIGHT);
            Border empty = new EmptyBorder(0, 3, 0, 0);
            field.setBorder(empty);
            col.setCellEditor(new DefaultCellEditor(field));
            col.setCellEditor(new JDateChooserCellEditor());
        }
    }

}
