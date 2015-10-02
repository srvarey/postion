package org.gaia.gui.utils;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.gaia.dao.utils.NumberUtils;
import static org.gaia.gui.common.GaiaTopComponent.decimalFormatWithOptionalDecimals;

public class AmountShortCut {

    private static String fieldValue;
    boolean verif = false;

    public static void eventkey(final JFormattedTextField textField) {

        Action actionListener = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Double value = convertAmount(textField);
                textField.setText(decimalFormatWithOptionalDecimals.format(value));
            }
        };
        /**
         * add the enter key event
         */
        textField.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), actionListener);
    }

    /**
     * return the amount
     * @param field
     * @return
     */
    public static double convertAmount(JTextField field) {
        double millionValue = 0;
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.US);
        fieldValue = field.getText();
        try {
            if (fieldValue.toLowerCase().indexOf("m") > 0) {
                int idx = fieldValue.toLowerCase().indexOf("m");
                String cString = fieldValue.substring(0, idx);

                boolean verif = NumberUtils.isNumber(cString);
                if (verif == true) {
                    millionValue = numberFormat.parse(cString).doubleValue();
                    millionValue = millionValue * 1000000;
                    return millionValue;
                }
            }

            if (fieldValue.toLowerCase().indexOf("k") > 0) {
                int idx = fieldValue.toLowerCase().indexOf("k");
                String cString = fieldValue.substring(0, idx);

                boolean verif = NumberUtils.isNumber(cString);
                if (verif == true) {
                    millionValue = numberFormat.parse(cString).doubleValue();
                    millionValue = millionValue * 1000;
                    return millionValue;
                }
            }
            return numberFormat.parse(fieldValue).doubleValue();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(field, "the format is wrong");
        }
        return millionValue;
    }
}
