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
package org.gaia.gui.reports;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.reports.AbstractSortableTreeTableNode;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.SnapshotReport;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.GaiaTopComponent.DecimalFormatRenderer;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.SortableTreeTable;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays snapshots.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//SnapshotReport//EN", autostore = false)
@TopComponent.Description(preferredID = "SnapshotReportTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.reports.SnapshotReportTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_SnapshotReportAction", preferredID = "SnapshotReportTopComponent")
@Messages({"CTL_SnapshotReportAction=Snapshot Report", "CTL_SnapshotReportTopComponent=Snapshot Report Window", "HINT_SnapshotReportTopComponent=This is a Snapshot Report window"
})
public final class SnapshotReportTopComponent extends GaiaTopComponent {

    private ArrayList<String> headings = null;
    private PositionTree.PositionNode root;
    private SortableTreeTableModel model;
    private SortableTreeTable table;
    private SnapshotReport snapshot;
    private ReportTemplate template;

    public SnapshotReportTopComponent() {
        initComponents();
        setName(Bundle.CTL_SnapshotReportTopComponent());
        setToolTipText(Bundle.HINT_SnapshotReportTopComponent());
    }

    /**
     * load snapshot report
     *
     * @param snapshotId
     */
    public void load(int snapshotId) {
        snapshot = (SnapshotReport) DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.GET_SNAPSHOT_REPORT_BY_ID, snapshotId);
        displayResult(snapshot);
        titleLabel.setText(snapshot.getSnapshotDescription());
    }

    /**
     * display Result of Snapshot Report
     *
     * @param snapshot
     */
    public void displayResult(SnapshotReport snapshot) {

        if (snapshot != null) {
            XStream xstream = new XStream(new StaxDriver());
            root = (PositionTree.PositionNode) xstream.fromXML(new ByteArrayInputStream(snapshot.getSnapshotBlob()));
            Map<String, String> criteriaMap;
            criteriaMap = (Map<String, String>) xstream.fromXML(new ByteArrayInputStream(snapshot.getSnapshotCriteria()));
            displayCriteriaMap(criteriaMap);
            displayRoot();
        }
    }

    /**
     * display Criteria
     *
     * @param criteriaMap
     */
    public void displayCriteriaMap(Map<String, String> criteriaMap) {
        if (criteriaMap != null) {
            DefaultTableModel modelCriteria = (DefaultTableModel) tableCriteria.getModel();
            GUIUtils.clearTableModel(modelCriteria);
            for (Map.Entry<String, String> entry : criteriaMap.entrySet()) {
                modelCriteria.addRow(new Object[]{entry.getKey(), entry.getValue()});
            }
            modelCriteria.fireTableDataChanged();
        }
    }

    /**
     * display Root
     */
    public void displayRoot() {

        if (root != null && root.getChildCount() > 0) {
            template = new ReportTemplate();
            template.setTemplateName(snapshot.getSnapshotTemplateName());
            template.setObjectTypeClass(SnapshotReport.class);
            TreeTableNode headerNode = root.getChildAt(0);
            headings = new ArrayList();
            Collection<TemplateColumnItem> templateColumnItemCollection = new ArrayList();
            for (int i = 0; i < headerNode.getColumnCount(); i++) {
                if (headerNode.getValueAt(i) != null) {
                    headings.add(headerNode.getValueAt(i).toString());
                    TemplateColumnItem column = new TemplateColumnItem();
                    column.setName(headerNode.getValueAt(i).toString());
                    column.setColumnIndex(i);
                    column.setReturnType(String.class.getName());
                    column.setTemplate(template);
                    templateColumnItemCollection.add(column);
                }
            }
            template.setTemplateColumnItems(templateColumnItemCollection);
            root.remove((AbstractSortableTreeTableNode) headerNode);

            /**
             * display
             */
            model = new SortableTreeTableModel(root, headings);
            table = new SortableTreeTable(model);
            table.setSortable(true);

            /**
             * hide last column : objects id
             */
            TableColumnModel colunModel = table.getColumnModel();
            if (colunModel.getColumnCount() > 0) {
                colunModel.removeColumn(colunModel.getColumn(colunModel.getColumnCount() - 1));
            }

            /**
             * show
             */
            table.setShowGrid(true, true);
            table.setRootVisible(false);
            table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

            table.setColumnControlVisible(true);
            table.setHorizontalScrollEnabled(true);
            table.setFillsViewportHeight(true);
            for (int i = 0; i < table.getRowCount(); i++) {
                table.expandRow(i);
            }

            for (int c = 0; c < table.getColumnCount(); c++) {
                table.getColumn(c).setCellRenderer(new DecimalFormatRenderer("#,##0.00"));
                GUIUtils.packColumn(table, c, 2);
            }

        } else {
            JOptionPane.showMessageDialog(this, "no data");
            model = new SortableTreeTableModel(root, headings);
            table = new SortableTreeTable(model);
        }
        table.packAll();
        scrollReportPanel.setViewportView(table);
        table.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        titlePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        exportExcelButton = new javax.swing.JButton();
        scrollCriteriaPanel = new javax.swing.JScrollPane();
        tableCriteria = new javax.swing.JTable();
        scrollReportPanel = new javax.swing.JScrollPane();
        exportExcelButton1 = new javax.swing.JButton();

        titlePanel.setBackground(new java.awt.Color(254, 252, 254));

        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(0, 0, 204));
        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(SnapshotReportTopComponent.class, "SnapshotReportTopComponent.titleLabel.text")); // NOI18N

        exportExcelButton.setBackground(new java.awt.Color(195, 229, 255));
        exportExcelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/excel.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(exportExcelButton, org.openide.util.NbBundle.getMessage(SnapshotReportTopComponent.class, "SnapshotReportTopComponent.exportExcelButton.text")); // NOI18N
        exportExcelButton.setToolTipText(org.openide.util.NbBundle.getMessage(SnapshotReportTopComponent.class, "SnapshotReportTopComponent.exportExcelButton.toolTipText")); // NOI18N
        exportExcelButton.setBorderPainted(false);
        exportExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportExcelButtonActionPerformed(evt);
            }
        });

        tableCriteria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Criteria", "Values"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrollCriteriaPanel.setViewportView(tableCriteria);

        exportExcelButton1.setBackground(new java.awt.Color(195, 229, 255));
        exportExcelButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/gaia/gui/reports/trash_16x16.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(exportExcelButton1, org.openide.util.NbBundle.getMessage(SnapshotReportTopComponent.class, "SnapshotReportTopComponent.exportExcelButton1.text")); // NOI18N
        exportExcelButton1.setToolTipText(org.openide.util.NbBundle.getMessage(SnapshotReportTopComponent.class, "SnapshotReportTopComponent.exportExcelButton1.toolTipText")); // NOI18N
        exportExcelButton1.setBorderPainted(false);
        exportExcelButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportExcelButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout titlePanelLayout = new javax.swing.GroupLayout(titlePanel);
        titlePanel.setLayout(titlePanelLayout);
        titlePanelLayout.setHorizontalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addGap(358, 358, 358)
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(exportExcelButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(exportExcelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollCriteriaPanel)
                            .addComponent(scrollReportPanel))))
                .addContainerGap())
        );
        titlePanelLayout.setVerticalGroup(
            titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(titlePanelLayout.createSequentialGroup()
                .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(titlePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(titlePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportExcelButton1)
                            .addComponent(exportExcelButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollCriteriaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollReportPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(titlePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void exportExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportExcelButtonActionPerformed
        table.expandAll();
        ExcelReportExporter.generateExcel(table, template);
    }//GEN-LAST:event_exportExcelButtonActionPerformed

    private void exportExcelButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportExcelButton1ActionPerformed

        if (snapshot != null) {
            int confirm = JOptionPane.showConfirmDialog(this, "Delete Snapshot Report id " + snapshot.getSnapshotReportId().toString() + StringUtils.SPACE + snapshot.getSnapshotDescription() + "?", "Delete confirmation?", JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirm) {
                DAOCallerAgent.callMethod(ReportBuilder.class, ReportBuilder.DELETE_SNAPSHOT_REPORT, snapshot);
                JOptionPane.showMessageDialog(this, "Snaphshot Deleted");
                this.close();
            }
        }
    }//GEN-LAST:event_exportExcelButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exportExcelButton;
    private javax.swing.JButton exportExcelButton1;
    private javax.swing.JScrollPane scrollCriteriaPanel;
    private javax.swing.JScrollPane scrollReportPanel;
    private javax.swing.JTable tableCriteria;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JPanel titlePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
