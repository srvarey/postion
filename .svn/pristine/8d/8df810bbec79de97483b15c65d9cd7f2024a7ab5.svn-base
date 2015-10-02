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

package org.gaia.gui.observable;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.observables.AbstractObservable.ObservableType;
import org.gaia.dao.observables.IObservable;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.domain.observables.ObservableShift;
import org.gaia.domain.observables.Stress;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaPanel;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.utils.GUIUtils;

/**
 * @author Benjamin Frerejean
 */

public class ObservableShiftPanel extends GaiaPanel {

    private Stress stress;
    private ObservableType observableType;
    private Class type1;
    private final Format mydecimalFormat= new DecimalFormat("#,##0.0000", decimalFormatSymbol);
    private List<String> values1;
    private int dimension;


    private static final Logger logger = Logger.getLogger(ObservableShiftPanel.class);

    /** Creates new form ObservableShiftPanel */
    public ObservableShiftPanel(String observableTypeName, Stress stress) {
        initComponents();

        this.stress=stress;
        if (stress!=null && stress.getObservableShifts()!=null && !stress.getObservableShifts().isEmpty()){
            if (stress.getObservableShifts().size()>=1 && stress.getObservableShifts().get(0).getCoordinate1()!=null){
                dimension=2;
            } else {
                dimension=1;
            }
        } else {
            dimension=1;
        }
        this.observableType=AbstractObservable.ObservableType.getObservableByName(observableTypeName);
    }

    public void refreshDimension(int dim ){
        try {
            dimension=dim;
            switch (dimension){
                case 1:
                    twoDimensionScrollPane.setVisible(false);
                    twoDimensionTable.setVisible(false);
                    oneDimensionTextField.setVisible(true);
                    addDim1Button.setVisible(false);
                    removeDim1Button.setVisible(false);
                    if (stress!=null && stress.getObservableShifts()!=null && !stress.getObservableShifts().isEmpty()){
                        oneDimensionTextField.setText(mydecimalFormat.format(stress.getObservableShifts().get(0).getShift()));
                    }
                break;
                case 2:
                    oneDimensionTextField.setVisible(false);
                    twoDimensionScrollPane.setVisible(true);
                    twoDimensionTable.setVisible(true);
                    addDim1Button.setVisible(true);
                    removeDim1Button.setVisible(true);
                    this.getParent().setSize(467,376);
                    ((DefaultTableModel)twoDimensionTable.getModel()).getDataVector().removeAllElements();
                    Object obj =observableType.observableClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
                    Class[] coordinates=((IObservable)obj).getCoordinateClass();
                    if (coordinates!=null && coordinates.length==1){
                        type1=coordinates[0];
                        if (type1.equals(Date.class)){
                            values1=FrequencyUtil.getTenors();
                        } else if (type1.equals(BigDecimal.class)){

                        }
                    }
                    if (stress!=null && stress.getObservableShifts()!=null){
                        for (ObservableShift shift : stress.getObservableShifts()){
                            addrow(new Object[]{shift.getCoordinate1(),mydecimalFormat.format(shift.getShift())});
                        }
                    }

                break;
            }
        } catch (Exception e){
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public Stress getStress(){

        if (stress==null){
            stress=new Stress();
            stress.setObservableShifts(new ArrayList());
        }
        ObservableShift shift;
        switch (dimensionComboBox.getSelectedIndex()+1){
            case 1:
               if (stress.getObservableShifts().isEmpty()){
                    shift=new ObservableShift();
               } else {
                   shift=stress.getObservableShifts().get(0);
               }
               shift.setShift(GUIUtils.getComponentBigDecimalValue(oneDimensionTextField));
               shift.setObservableType(observableType.name);
               shift.setStress(stress);
               if (stress.getObservableShifts().isEmpty()){
                    stress.getObservableShifts().add(shift);
               }
            break;
            case 2:
                DefaultTableModel model = (DefaultTableModel)twoDimensionTable.getModel();
                for (int i=0;i<model.getRowCount();i++){
                    try {
                        shift=null;
                        boolean isNew=false;
                        String coord=GUIUtils.getTableValueAt(twoDimensionTable, i, 0);
                        for (ObservableShift tmp : stress.getObservableShifts()){
                            if (coord.equalsIgnoreCase(tmp.getCoordinate1())){
                                shift=tmp;
                            }
                        }
                        if (shift==null){
                            shift=new ObservableShift();
                            isNew=true;
                        }
                        String sShift=model.getValueAt(i, 1).toString();
                        BigDecimal shiftNum=BigDecimal.valueOf(GaiaTopComponent.numberFormat.parse(sShift).doubleValue());
                        shift.setShift(shiftNum);
                        shift.setCoordinate1(coord);
                        shift.setObservableType(observableType.name);
                        if (isNew){
                            stress.getObservableShifts().add(shift);
                        }
                        shift.setStress(stress);
                    } catch (Exception e){

                    }
                }
            break;
        }
        return stress;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        oneDimensionTextField = new javax.swing.JTextField();
        twoDimensionScrollPane = new javax.swing.JScrollPane();
        twoDimensionTable = new javax.swing.JTable();
        addDim1Button = new javax.swing.JButton();
        removeDim1Button = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        dimensionComboBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(254, 252, 254));
        setName("jPanel"); // NOI18N
        addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                formPropertyChange(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ObservableShiftPanel.class, "ObservableShiftPanel.jLabel1.text")); // NOI18N

        oneDimensionTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        oneDimensionTextField.setText(org.openide.util.NbBundle.getMessage(ObservableShiftPanel.class, "ObservableShiftPanel.oneDimensionTextField.text")); // NOI18N

        twoDimensionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] { "Coord", "Value"
            }
        ));
        twoDimensionScrollPane.setViewportView(twoDimensionTable);

        addDim1Button.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(addDim1Button, org.openide.util.NbBundle.getMessage(ObservableShiftPanel.class, "ObservableShiftPanel.addDim1Button.text")); // NOI18N
        addDim1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDim1ButtonActionPerformed(evt);
            }
        });

        removeDim1Button.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(removeDim1Button, org.openide.util.NbBundle.getMessage(ObservableShiftPanel.class, "ObservableShiftPanel.removeDim1Button.text")); // NOI18N
        removeDim1Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeDim1ButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ObservableShiftPanel.class, "ObservableShiftPanel.jLabel2.text")); // NOI18N

        dimensionComboBox.setBackground(new java.awt.Color(255, 255, 255));
        dimensionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2"}));
        dimensionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dimensionComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addComponent(removeDim1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addDim1Button, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dimensionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(twoDimensionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(oneDimensionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(dimensionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(oneDimensionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(twoDimensionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addDim1Button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(removeDim1Button)))
                .addGap(14, 14, 14))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addDim1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addDim1ButtonActionPerformed
       addrow(new Object[1]);
    }//GEN-LAST:event_addDim1ButtonActionPerformed

    private void removeDim1ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeDim1ButtonActionPerformed
        DefaultTableModel model = (DefaultTableModel)twoDimensionTable.getModel();
        int row = twoDimensionTable.getSelectedRow();
        if (row>=0){
            model.removeRow(row);
        }
    }//GEN-LAST:event_removeDim1ButtonActionPerformed

    private void dimensionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dimensionComboBoxActionPerformed
       refreshDimension(dimensionComboBox.getSelectedIndex()+1);
    }//GEN-LAST:event_dimensionComboBoxActionPerformed

    private void formPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_formPropertyChange

        if (evt.getPropertyName().equalsIgnoreCase("ancestor")&& evt.getNewValue()!=null){
            dimensionComboBox.setSelectedIndex(dimension-1);
        }
    }//GEN-LAST:event_formPropertyChange

    public void addrow(Object[] row){
        DefaultTableModel model = (DefaultTableModel)twoDimensionTable.getModel();
        model.addRow(row);
        if (values1!=null){
            String[] arrayListMeasures = (String[]) values1.toArray(new String[values1.size()]);
            twoDimensionTable.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(new JComboBox(arrayListMeasures)));
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addDim1Button;
    private javax.swing.JComboBox dimensionComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField oneDimensionTextField;
    private javax.swing.JButton removeDim1Button;
    private javax.swing.JScrollPane twoDimensionScrollPane;
    private javax.swing.JTable twoDimensionTable;
    // End of variables declaration//GEN-END:variables
}
