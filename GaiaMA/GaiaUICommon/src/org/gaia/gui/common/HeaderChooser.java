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
package org.gaia.gui.common;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.gaia.gui.utils.GUIUtils;

/**
 * @author Benjamin Frerejean
 */
public class HeaderChooser extends javax.swing.JPanel {

    /**
     * Creates new form HeaderChooser
     */
    public HeaderChooser(Map<String,Boolean> columnList) {
        initComponents();

        if (columnList != null) {
            DefaultTableModel tm=(DefaultTableModel)jTable1.getModel();
            Vector tableData =tm.getDataVector();
            for (Entry e : columnList.entrySet()) {
                Vector v = new Vector();
                v.add(e.getKey().toString());
                v.add(e.getValue());
                tableData.add(v);
            }
            Vector h = GUIUtils.getHeader(jTable1);
            DefaultTableModel newtm = new DefaultTableModel(tableData, h);
            jTable1.setModel(newtm);

            TableColumn col2 = jTable1.getColumnModel().getColumn(1);
            col2.setCellEditor(new DefaultCellEditor(new JCheckBox()));
            col2.setCellRenderer(jTable1.getDefaultRenderer(Boolean.class));
        }
    }
      /**return element search */
    public LinkedHashMap<String,Boolean> getSelectedColumns(){
        LinkedHashMap<String,Boolean> cols=new LinkedHashMap();
        DefaultTableModel tm=(DefaultTableModel)jTable1.getModel();
        Vector tableData =tm.getDataVector();
        for (int i=0;i<tableData.size();i++){
            Object o=tableData.get(i);
            Vector v=(Vector)o;
            cols.put(v.elementAt(0).toString(),(Boolean)v.elementAt(1));
        }
        return cols;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {    },
            new String [] {
                "Column", "Select"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
