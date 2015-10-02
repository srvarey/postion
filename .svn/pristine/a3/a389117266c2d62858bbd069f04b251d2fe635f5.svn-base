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

/**
 *
 * @author Jawhar Kamoun
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.events.EventEngine;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.ProductEventDetail;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays product events.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//ProductEvent//EN", autostore = false)
@TopComponent.Description(preferredID = "ProductEventTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "explorer", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.ProductEventTopComponent")
@ActionReference(path = "Menu/File" /*, position = 333 */)
@TopComponent.OpenActionRegistration(displayName = "#CTL_ProductEventAction", preferredID = "ProductEventTopComponent")
@Messages({
    "CTL_ProductEventAction=Product Event",
    "CTL_ProductEventTopComponent=Product Event",
    "HINT_ProductEventTopComponent=This is a Product Event window"
})
public final class ProductEventTopComponent extends GaiaTopComponent {

    DefaultTableModel eventModel, eventDetailModel;
    List<ProductEvent> productEventList;
    private static final Logger logger = Logger.getLogger(ProductEventTopComponent.class);

    public ProductEventTopComponent() {
        initComponents();
        setName(Bundle.CTL_ProductEventTopComponent());
        setToolTipText(Bundle.HINT_ProductEventTopComponent());
        eventModel = (DefaultTableModel) eventXTable.getModel();
        eventDetailModel = (DefaultTableModel) eventDetailXTable.getModel();
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);
    }

    public void setProductEventList(List<ProductEvent> _productEventList) {
        productEventList = _productEventList != null ? _productEventList : new ArrayList<ProductEvent>();

        fillEventTable();
    }

    private void fillEventTable() {

        Vector<Vector<Object>> data = new Vector<>();
        for (ProductEvent productEvent : productEventList) {
            try {
                String desc=(String)DAOCallerAgent.callMethod(EventEngine.class, EventEngine.GET_EVENT_DESCRIPTION,productEvent);
                Vector<Object> row = new Vector<>();
                row.add(productEvent.getProductEventId());
                row.add(dateFormatter.format(productEvent.getEffectiveDate()));
                row.add(productEvent.getEventType());
                row.add(desc);
                data.add(row);
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        eventModel.getDataVector().removeAllElements();
        eventModel.getDataVector().addAll(data);
        eventXTable.setModel(eventModel);
        fillEventDetailsTable(null);
        eventModel.fireTableDataChanged();
    }

    private void fillEventDetailsTable(Collection<ProductEventDetail> selectedEvent) {

        Vector<Vector<Object>> data = new Vector<>();
        if (selectedEvent != null) {
            for (ProductEventDetail productEventDetail : selectedEvent) {

                Vector<Object> row = new Vector<>();
                row.add(productEventDetail.getProductColumn());
                row.add(productEventDetail.getOldValue());
                row.add(productEventDetail.getProductValue());
                data.add(row);
            }
        }
        eventDetailModel.getDataVector().removeAllElements();
        eventDetailModel.getDataVector().addAll(data);
        eventDetailXTable.setModel(eventDetailModel);
        eventDetailModel.fireTableDataChanged();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSplitPane = new javax.swing.JSplitPane();
        eventScrollPane = new javax.swing.JScrollPane();
        eventXTable = new org.jdesktop.swingx.JXTable();
        eventDetailScrollPane = new javax.swing.JScrollPane();
        eventDetailXTable = new org.jdesktop.swingx.JXTable();
        cancelEventButton = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        jSplitPane.setDividerLocation(100);
        jSplitPane.setDividerSize(10);
        jSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane.setOneTouchExpandable(true);

        eventXTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Event Id", "Effective Date", "Event Type", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventXTable.getTableHeader().setReorderingAllowed(false);
        eventXTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eventXTableMouseClicked(evt);
            }
        });
        eventScrollPane.setViewportView(eventXTable);

        jSplitPane.setTopComponent(eventScrollPane);

        eventDetailXTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Field", "Old value", "New value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        eventDetailScrollPane.setViewportView(eventDetailXTable);

        jSplitPane.setRightComponent(eventDetailScrollPane);

        org.openide.awt.Mnemonics.setLocalizedText(cancelEventButton, org.openide.util.NbBundle.getMessage(ProductEventTopComponent.class, "ProductEventTopComponent.cancelEventButton.text")); // NOI18N
        cancelEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelEventButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cancelEventButton)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelEventButton)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void eventXTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eventXTableMouseClicked
        if (eventXTable.getSelectedRow() >= 0) {
            ProductEvent selectedEvent = productEventList.get(eventXTable.getSelectedRow());
            fillEventDetailsTable(selectedEvent.getProductEventDetails());
        }
    }//GEN-LAST:event_eventXTableMouseClicked

    private void cancelEventButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelEventButtonActionPerformed
        int row = eventXTable.getSelectedRow();
        int yes = JOptionPane.showConfirmDialog(this, "Are you sure?");
        if (yes == 0 && row > -1) {
            ProductEvent selectedEvent = productEventList.get(eventXTable.getSelectedRow());
            Product product =(Product)DAOCallerAgent.callMethod(EventEngine.class, EventEngine.CANCEL_PRODUCT_EVENT, selectedEvent);
            ((DefaultTableModel)eventXTable.getModel()).removeRow(row);
            GUIUtils.emptyTable(eventDetailXTable);
            DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.STORE_PRODUCT, product);

        }
    }//GEN-LAST:event_cancelEventButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelEventButton;
    private javax.swing.JScrollPane eventDetailScrollPane;
    private org.jdesktop.swingx.JXTable eventDetailXTable;
    private javax.swing.JScrollPane eventScrollPane;
    private org.jdesktop.swingx.JXTable eventXTable;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }

    @Override
    protected void componentActivated() {
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }
}
