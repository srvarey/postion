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
package org.gaia.gui.admin;

import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.utils.Message;
import org.gaia.gui.common.MenuManager;
import org.gaia.io.dtcc5_3.XMLDTCC;
import org.gaia.io.lch.LCHImporter;
import org.gaia.markit.MarkitIndexDefinitionImporter;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays file importer.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.admin//FileImport//EN", autostore = false)
@TopComponent.Description(preferredID = "FileImportTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.admin.FileImportTopComponent")
@ActionReference(path = "Menu"+MenuManager.FileImportTopComponentMenu , position = MenuManager.FileImportTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FileImportAction", preferredID = "FileImportTopComponent")
@Messages({"CTL_FileImportAction=File Import", "CTL_FileImportTopComponent=File Import Window", "HINT_FileImportTopComponent=This is a File Import window"})
public final class FileImportTopComponent extends TopComponent {

    private JFileChooser fileChooser = null;
    private final String LCH_VAR_MARGIN = "LCH_Variation_Margin";
    private final String LCH_SPAN_MARGIN = "LCH_Initial_Margin";
    private final String DTCC = "DTCC";
    //private final String REUTERS = "Reuters";
    private final String MARKIT = "Markit Credit Index";
    private static final Logger logger = Logger.getLogger(FileImportTopComponent.class);
    private final String defaultPath="C:\\";

    public FileImportTopComponent() {
        initComponents();
        setName(Bundle.CTL_FileImportTopComponent());
        setToolTipText(Bundle.HINT_FileImportTopComponent());

        jComboBoxFileType.addItem(LCH_SPAN_MARGIN);
        jComboBoxFileType.addItem(LCH_VAR_MARGIN);
        jComboBoxFileType.addItem(DTCC);
        //jComboBoxFileType.addItem(REUTERS);
        jComboBoxFileType.addItem(MARKIT);

        fileChooser = new JFileChooser();

        jTextFieldFilePath.setText(defaultPath);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButtonLoad = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldFilePath = new javax.swing.JTextField();
        jButtonFind = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jComboBoxFileType = new javax.swing.JComboBox();

        jPanel1.setBackground(new java.awt.Color(245, 252, 253));

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(FileImportTopComponent.class, "FileImportTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(FileImportTopComponent.class, "FileImportTopComponent.jLabel1.text")); // NOI18N

        jTextFieldFilePath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFilePathActionPerformed(evt);
            }
        });

        jButtonFind.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFind, org.openide.util.NbBundle.getMessage(FileImportTopComponent.class, "FileImportTopComponent.jButtonFind.text")); // NOI18N
        jButtonFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(FileImportTopComponent.class, "FileImportTopComponent.jLabel2.text")); // NOI18N

        jComboBoxFileType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxFileType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldFilePath)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonFind)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLoad))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBoxFileType, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 445, Short.MAX_VALUE)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxFileType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonLoad)
                    .addComponent(jLabel1)
                    .addComponent(jTextFieldFilePath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonFind))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed

        if (!jTextFieldFilePath.getText().isEmpty() && !jTextFieldFilePath.getText().equalsIgnoreCase(defaultPath)) {
            try {
                List<Message> messages = null;
                switch (jComboBoxFileType.getSelectedItem().toString()) {
                    case DTCC:
                        messages = (List) DAOCallerAgent.callMethod(XMLDTCC.class, XMLDTCC.IMPORT_DTCC, jTextFieldFilePath.getText());
                        break;
                    case LCH_VAR_MARGIN:
                        messages = (List) DAOCallerAgent.callMethod(LCHImporter.class, LCHImporter.LOAD_VARIATION_MARGIN, jTextFieldFilePath.getText());
                        break;
                    case LCH_SPAN_MARGIN:
                        messages = (List) DAOCallerAgent.callMethod(LCHImporter.class, LCHImporter.LOAD_INITIAL_MARGIN, jTextFieldFilePath.getText());
                        break;
                    case MARKIT:
                        messages = (List) DAOCallerAgent.callMethod(MarkitIndexDefinitionImporter.class, MarkitIndexDefinitionImporter.IMPORT_INDEX_DEF, jTextFieldFilePath.getText());
                        break;
                }
                JOptionPane.showMessageDialog(this, ReportUtils.formatMessageList(messages));

            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please choose a valid file");
        }
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindActionPerformed

        //In response to a button click:
        fileChooser.setSelectedFile(new File("C:"));
        int returnVal = fileChooser.showOpenDialog(this);

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            jTextFieldFilePath.setText(file.getPath());
        }

    }//GEN-LAST:event_jButtonFindActionPerformed

    private void jTextFieldFilePathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFilePathActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFilePathActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFind;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JComboBox jComboBoxFileType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldFilePath;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
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