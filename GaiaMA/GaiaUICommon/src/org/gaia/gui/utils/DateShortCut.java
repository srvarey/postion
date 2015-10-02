package org.gaia.gui.utils;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import org.gaia.dao.utils.DateUtils;
import static org.gaia.dao.utils.DateUtils.ADJUSTMENT_FOLLOW;
import org.gaia.gui.common.GaiaTopComponent;

/**
 *
 * @author User
 */
public class DateShortCut {

    public static String textField;
    public static JFormattedTextField editor;
    boolean verif = false;
    public static Date finalDate = DateUtils.getDate();

    public static void eventkey(final JSpinnerDateEditor textfiled) {

        Action actionListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date valuemill = convertDate(textfiled);
                textfiled.setDate(valuemill);
            }
        };
        editor = ((JSpinner.DateEditor) textfiled.getEditor()).getTextField();
        editor.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), actionListener);
    }

    /**
     * return Date
     * @param field
     * @return
     */
    public static Date convertDate(JSpinnerDateEditor field) {

        int finalDay;
        editor = ((JSpinner.DateEditor) field.getEditor()).getTextField();
        textField = editor.getText();
        if (textField.toLowerCase().indexOf("d") > 0) {
            int idx = textField.toLowerCase().indexOf("d");
            String cString = textField.substring(0, idx);

            boolean verif = isInteger(cString);
            if (verif == true) {
                int cInt = Integer.parseInt(cString);
                finalDate = DateUtils.addOpenDay(DateUtils.getDate(), cInt);
            }
        } else  if (textField.toLowerCase().indexOf("w") > 0) {
            int idx = textField.toLowerCase().indexOf("w");
            String cString = textField.substring(0, idx);

            boolean verif = isInteger(cString);
            if (verif == true) {
                int cInt = Integer.parseInt(cString);
                finalDay = cInt * 7;
                finalDate = DateUtils.addCalendarDay(DateUtils.getDate(), finalDay);
                finalDate = DateUtils.adjustDate(finalDate, DateUtils.ADJUSTMENT_FOLLOW, null);
            }
        } else if (textField.toLowerCase().indexOf("m") > 0) {
            int idx = textField.toLowerCase().indexOf("m");
            String cString = textField.substring(0, idx);

            boolean verif = isInteger(cString);
            if (verif == true) {
                int cInt = Integer.parseInt(cString);
                finalDate=DateUtils.addMonth(DateUtils.getDate(), cInt);
                finalDate = DateUtils.adjustDate(finalDate, DateUtils.ADJUSTMENT_FOLLOW, null);
            }
        } else if (textField.toLowerCase().indexOf("y") > 0) {
            int idx = textField.toLowerCase().indexOf("y");
            String cString = textField.substring(0, idx);

            boolean verif = isInteger(cString);
            if (verif == true) {
                int cInt = Integer.parseInt(cString);
                finalDate=DateUtils.addYear(DateUtils.getDate(), cInt);
                finalDate = DateUtils.adjustDate(finalDate, DateUtils.ADJUSTMENT_FOLLOW, null);
            }
        } else {
            try {
                finalDate = GaiaTopComponent.dateFormatter.parse(textField);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(field, "the format is wrong");
            }
        }
        return DateUtils.adjustDate(finalDate, ADJUSTMENT_FOLLOW, null);
    }

    /**
     * check if the string is a nomber
     * @param string
     * @return
     */
    public static boolean isInteger(String string) {
        try {
            Integer.valueOf(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
