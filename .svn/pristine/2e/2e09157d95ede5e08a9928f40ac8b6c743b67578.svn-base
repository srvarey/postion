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

import java.awt.Component;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import org.gaia.dao.reports.customColumns.IColorableColumn;
import static org.gaia.gui.common.GaiaTopComponent.dateFormatter;
import static org.gaia.gui.common.GaiaTopComponent.dateFormatterWithTime;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormat;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormatSymbol;

/**
 *
 * @author Benjamin Frerejean
 */
public class UtilsDecimalFormatRenderer extends DefaultTableCellRenderer {

    private final Format rendererDecimalFormat;
    private HashMap<Integer, IColorableColumn> coloredcolumns;

    public UtilsDecimalFormatRenderer(String format) {
        super();
        if (format != null && !format.isEmpty()) {
            rendererDecimalFormat = new DecimalFormat(format, decimalFormatSymbol);
        } else {
            rendererDecimalFormat = decimalFormat;
        }
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel renderedLabel = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        try {
            if (coloredcolumns != null && coloredcolumns.containsKey(column)) {
                renderedLabel = new JLabel();
                renderedLabel.setOpaque(true);
                renderedLabel.setBackground(coloredcolumns.get(column).getColor(value,hasFocus));
            }
            if (value instanceof Double || value instanceof BigDecimal) {
                // do not delete please : to fix
                renderedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                renderedLabel.setText(rendererDecimalFormat.format((Number) value));
            } else if (value instanceof String) {
                if (!value.toString().contains(":")) {
                    NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
                    value = numberFormat.parse(value.toString());
                    value = rendererDecimalFormat.format(value);
                    renderedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                    renderedLabel.setText(value.toString());
                }
            } else if (value instanceof Date) {
                renderedLabel.setHorizontalAlignment(SwingConstants.RIGHT);
                Calendar cal = Calendar.getInstance();
                cal.setTime((Date) value);
                if (cal.get(Calendar.MILLISECOND)+cal.get(Calendar.SECOND)+cal.get(Calendar.MINUTE)==0  ){
                    renderedLabel.setText(dateFormatter.format((Date) value));
                } else {
                    renderedLabel.setText(dateFormatterWithTime.format((Date) value));
                }
            }
        } catch (Exception e) {
        }
        return renderedLabel;
    }

    public void addColoredColumn(int colIndex, IColorableColumn column) {
        if (coloredcolumns == null) {
            coloredcolumns = new HashMap();
        }
        coloredcolumns.put(colIndex, column);
    }
}
