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

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JPanel;

/**
 *
 * @author Benjamin Frerejean
 */
public class GaiaPanel extends JPanel {

    public static DateFormat dateFormatter = null;
    public DateFormat timeFormatter = null;
    public static DateFormat dateFormatterWithTime = null;
    public Format decimalFormat = null;
    public DecimalFormatSymbols decimalFormatSymbol = null;
    public static Format pointdecimalFormat = null;
    public Locale locale = null;
    public Format longFormat = null;
    public Format integerFormat = null;
    public NumberFormat nformat = null;
    public String dateFormat;


    public GaiaPanel() {
        super();
        locale = Locale.US;
        decimalFormatSymbol = new DecimalFormatSymbols(locale);
        decimalFormat = new DecimalFormat("#,##0.00", decimalFormatSymbol);
        pointdecimalFormat= new DecimalFormat("#,##0.0000", decimalFormatSymbol);
        longFormat = new DecimalFormat("# ###");
        integerFormat = new DecimalFormat("##");
        dateFormat = "dd/MM/yyyy";
        dateFormatter = new SimpleDateFormat(dateFormat);
        timeFormatter = new SimpleDateFormat("HH:mm:ss");
        dateFormatterWithTime = new SimpleDateFormat(dateFormat+" HH:mm:ss");
        nformat = NumberFormat.getNumberInstance(locale);
    }
}
