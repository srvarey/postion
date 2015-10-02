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

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;
import org.gaia.gui.utils.CheckBoxHeader;
import org.gaia.gui.utils.GUIUtils;

/**
 *
 * @author Jawhar
 */
public class FieldValuesListChooser extends JPanel {

    private TableRowSorter filter;
    private final DefaultTableModel valueListModel;
    private List<String> selectedValuesList;

    /**
     * Creates new form FieldValuesListChooser
     * @param listOfValues
     * @param _selectedValuesList
     */
    public FieldValuesListChooser(List<String> listOfValues, List<String> _selectedValuesList) {
        initComponents();
        selectedValuesList = _selectedValuesList;
        if (selectedValuesList == null) {
            selectedValuesList = new ArrayList<>();
        }
        valueListTable.setAutoCreateRowSorter(false);
        valueListModel = (DefaultTableModel) valueListTable.getModel();
        TableColumn tableColumn = valueListTable.getColumnModel().getColumn(0);
        tableColumn.setCellEditor(valueListTable.getDefaultEditor(Boolean.class));
        tableColumn.setCellRenderer(valueListTable.getDefaultRenderer(Boolean.class));
        tableColumn.setHeaderRenderer(new CheckBoxHeader(new CheckBoxListener()));
        tableColumn.sizeWidthToFit();
        GUIUtils.packAllColumns(valueListTable);
        fillTable(listOfValues);
        filterTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void changedUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                newFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newFilter();
            }
        });
    }

    /**
     * Update the row filter regular expression from the expression in the text
     * box.
     */
    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rowFilter;
        // If current expression doesn't parse, don't update.
        try {
            rowFilter = RowFilter.regexFilter("(?i)" + filterTextField.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        filter.setRowFilter(rowFilter);
    }

    private void fillTable(List<String> listOfValues) {
        Vector<Vector<Object>> dataVector = new Vector<>();

        for (String object : listOfValues) {
            Vector<Object> row = new Vector<>();
            row.add(selectedValuesList.contains(object));
            row.add(object.toString());
            dataVector.add(row);
        }
        valueListModel.getDataVector().removeAllElements();
        valueListModel.getDataVector().addAll(dataVector);
        valueListTable.setModel(valueListModel);
        valueListModel.fireTableDataChanged();

        filter = new TableRowSorter<>(valueListModel);
        valueListTable.setModel(valueListModel);
        valueListTable.setRowSorter(filter);
    }

    public List<String> getSelectedValues() {
        selectedValuesList.clear();
        for (int x = 0; x < valueListTable.getRowCount(); x++) {
            Boolean selected = (Boolean) valueListTable.getValueAt(x, 0);
            if (selected) {
                selectedValuesList.add(valueListTable.getValueAt(x, 1).toString());
            }
        }
        return selectedValuesList;
    }

    class CheckBoxListener implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {
            Object source = e.getSource();
            if (source instanceof AbstractButton == false) {
                return;
            }
            boolean checked = e.getStateChange() == ItemEvent.SELECTED;
            for (int x = 0; x < valueListTable.getRowCount(); x++) {
                valueListTable.setValueAt(checked, x, 0);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableScrollPane = new javax.swing.JScrollPane();
        valueListTable = new javax.swing.JTable();
        filterTextField = new org.jdesktop.swingx.JXTextField();

        valueListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableScrollPane.setViewportView(valueListTable);

        filterTextField.setText(org.openide.util.NbBundle.getMessage(FieldValuesListChooser.class, "FieldValuesListChooser.filterTextField.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filterTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(filterTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 496, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTextField filterTextField;
    private javax.swing.JScrollPane tableScrollPane;
    private javax.swing.JTable valueListTable;
    // End of variables declaration//GEN-END:variables
}
