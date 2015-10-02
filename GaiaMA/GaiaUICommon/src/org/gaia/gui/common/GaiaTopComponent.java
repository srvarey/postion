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
package org.gaia.gui.common;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableCellRenderer;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.gaia.gui.utils.UtilsDecimalFormatRenderer;
import org.openide.windows.TopComponent;

/**
 *
 * @author jkamoun
 */
public class GaiaTopComponent extends TopComponent {

    public static final String dateFormat = "dd/MM/yyyy";
    public static final String sDecimalFormat = "#,##0.00";
    public static final DateFormat dateFormatter = new SimpleDateFormat(dateFormat);
    public static final DateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
    public static final DateFormat dateFormatterWithTime = new SimpleDateFormat(dateFormat + " HH:mm:ss");
    public static final DecimalFormatSymbols decimalFormatSymbol = new DecimalFormatSymbols(Locale.US);
    public static final DecimalFormat decimalFormat = new DecimalFormat(sDecimalFormat, decimalFormatSymbol);
    public static final DecimalFormat noDecimalFormat = new DecimalFormat("#,###", decimalFormatSymbol);
    public static final Format pointDecimalFormat = new DecimalFormat("#,##0.0000", decimalFormatSymbol);
    public static final Format longFormat = new DecimalFormat("# ###");
    public static final Format integerFormat = new DecimalFormat("##");
    public static final Format decimalFormatWithOptionalDecimals = new DecimalFormat("#,###.#####", new DecimalFormatSymbols(Locale.US));
    public static final NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
    public static SpinnerDateModel spinnerModel = null;
    public static SpinnerDateModel spinnerModel2 = null;
    public DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();

    public final int PDFDOCPARSER = 1;

    public GaiaTopComponent() {
        super();
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.AM_PM, Calendar.AM);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date initDate = cal.getTime();
        cal.add(Calendar.YEAR, -100);
        Date earliestDate = cal.getTime();
        cal.add(Calendar.YEAR, 200);
        Date latestDate = cal.getTime();
        spinnerModel = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.YEAR);
        spinnerModel2 = new SpinnerDateModel(initDate, earliestDate, latestDate, Calendar.YEAR);

        headerRenderer.setBackground(Color.blue.darker());
        headerRenderer.setForeground(Color.white);
        getHtmlDisplayName();
        this.requestAttention(true);
    }

    public void initContext() {
    }

    public class DecimalFormatRenderer extends UtilsDecimalFormatRenderer {

        public DecimalFormatRenderer(String format) {
            super(format);
        }

    }

    /**
     * Function to Clear fields
     *
     * @param container
     */
    public void clearFields(Container container) {

        Component[] comps = container.getComponents();
        for (int i = 0; i < comps.length; ++i) {

            if (comps[i] instanceof JTextField) {
                if (comps[i].getParent() instanceof GaiaJSpinnerDateEditor) {
                    ((JDateChooser) comps[i].getParent()).setDate(DateUtils.getDate());
                } else {
                    JTextField field = (JTextField) comps[i];
                    field.setText(StringUtils.EMPTY_STRING);
                }

            } else if (comps[i] instanceof JComboBox) {
                JComboBox combo = (JComboBox) comps[i];
                if (combo.getSelectedItem() != null) {
                    combo.setSelectedIndex(0);
                }
            } else if (comps[i] instanceof JCheckBox) {
                JCheckBox checkbox = (JCheckBox) comps[i];
                checkbox.setSelected(false);
            } else if (comps[i] instanceof Container && !(comps[i] instanceof JDateChooser)) {
                clearFields((Container) comps[i]);
            }

        }
    }

    @Override
    public void componentOpened() {
        initContext();
    }
}
