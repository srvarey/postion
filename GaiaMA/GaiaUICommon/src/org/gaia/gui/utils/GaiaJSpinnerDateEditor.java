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
package org.gaia.gui.utils;

import com.toedter.calendar.DateUtil;
import com.toedter.calendar.JSpinnerDateEditor;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 * @author Saber Bouazzi
 */
public class GaiaJSpinnerDateEditor extends JSpinnerDateEditor {

    public GaiaJSpinnerDateEditor() {
        setModel(new SpinnerDateModel());
        dateFormatter = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((JSpinner.DateEditor) getEditor()).getTextField().addFocusListener(this);
        DateUtil dateUtil = new DateUtil();
        setMinSelectableDate(dateUtil.getMinSelectableDate());
        setMaxSelectableDate(dateUtil.getMaxSelectableDate());
        ((JSpinner.DateEditor) getEditor()).getTextField().setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
        addChangeListener(this);
    }

    public void setText(String t) {
        this.setText(t);
    }

    public String getText() {
        return this.getText();
    }
}
