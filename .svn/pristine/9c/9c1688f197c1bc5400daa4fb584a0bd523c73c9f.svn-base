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
package org.gaia.gui.assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityAttribute;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.ProductReferencesPanel;
import org.gaia.gui.utils.GUIUtils;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Benjamin Frerejean
 */
public class LegalEntitiesAttributesPanel extends javax.swing.JPanel {

    private static final Logger logger = Logger.getLogger(ProductReferencesPanel.class);
    private List<LegalEntityAttribute> attributes;
    private LegalEntity entity;
    List<String> attributeNameList;
    DefaultTableModel tableModel;

    /**
     *
     * Creates new form TradeAttributesPanel
     */
    public LegalEntitiesAttributesPanel() {
        initComponents();
        attributeNameList = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_ATTRIBUTES);
        tableModel = (DefaultTableModel) tradeAttributeTable.getModel();
    }

    /** add a TradeAttribute */
    public void setAttribute(LegalEntity _entity) {
        attributes = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_ATTRIBUTES, _entity.getLegalEntityId());
        entity = _entity;
        if (attributes != null || !attributes.isEmpty()) {
            displayTable();
        }
    }

    private void displayTable() {
        GUIUtils.clearTableModel(tableModel);
        String[] row = new String[2];
        for (String attribute : attributeNameList) {
            row[0] = attribute;
            row[1] = getAttributeValue(attribute);
            tableModel.addRow(row);
        }
    }

    private String getAttributeValue(String attribute) {
        String values = null;
        if (attributes != null) {
            try {
                for (LegalEntityAttribute tradeAttribute1 : attributes) {
                    if (attribute.equalsIgnoreCase(tradeAttribute1.getAttributeName())) {
                        values = tradeAttribute1.getAttributeValue();
                        break;
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return values;
    }

    private void updateAttributes() {
        attributes = new ArrayList();
        for (int i = 0; i < tableModel.getDataVector().size(); i++) {
            Vector row = (Vector) tableModel.getDataVector().get(i);
            LegalEntityAttribute attribute = null;
            for (LegalEntityAttribute _attribute : attributes) {
                if (row.get(0).toString().equalsIgnoreCase(_attribute.getAttributeName())) {
                    attribute = _attribute;
                    break;
                }
            }
            if (attribute == null) {
                attribute = new LegalEntityAttribute();
            }
            attribute.setAttributeName(row.get(0).toString());
            attribute.setAttributeValue(row.get(1) != null ? row.get(1).toString() : StringUtils.EMPTY_STRING);
            if (StringUtils.isEmptyString(attribute.getAttributeValue()) && attribute.getLegalEntityId() != null) {
                DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.DELETE_LEGAL_ENTITY_ATTRIBUTE, attribute);
                attributes.remove(attribute);
            } else if (!StringUtils.isEmptyString(attribute.getAttributeValue())) {
                attribute.setLegalEntity(entity);
                attributes.add(attribute);
            }
        }
        entity.setLegalEntityAttributes(attributes);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonSaveAttributes = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tradeAttributeTable = new org.jdesktop.swingx.JXTable();

        setBackground(new java.awt.Color(254, 252, 254));

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jButtonSaveAttributes.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSaveAttributes, org.openide.util.NbBundle.getMessage(LegalEntitiesAttributesPanel.class, "LegalEntitiesAttributesPanel.jButtonSaveAttributes.text")); // NOI18N
        jButtonSaveAttributes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveAttributesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonSaveAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonSaveAttributes))
        );

        tradeAttributeTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attribute", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tradeAttributeTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonSaveAttributesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveAttributesActionPerformed
        try {
            updateAttributes();
            DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.STORE_LEGAL_ENTITY, entity);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        Set<TopComponent> openedComponent = WindowManager.getDefault().getRegistry().getOpened();
        for (TopComponent topComponent : openedComponent) {
            if (topComponent instanceof LegalEntityTopComponent) {
                LegalEntityTopComponent component = (LegalEntityTopComponent) topComponent;
                if (component.getLegalEntity() != null && component.getLegalEntity().getLegalEntityId() == entity.getLegalEntityId()) {
                    component.setLegalEntity(entity);
                    component.loadEntity();
                }
            }
        }
    }//GEN-LAST:event_jButtonSaveAttributesActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonSaveAttributes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private org.jdesktop.swingx.JXTable tradeAttributeTable;
    // End of variables declaration//GEN-END:variables
}
