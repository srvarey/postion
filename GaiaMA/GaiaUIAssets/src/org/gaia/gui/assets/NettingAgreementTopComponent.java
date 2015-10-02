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
package org.gaia.gui.assets;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.ErrorMessageUI;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays netting agreements.
 */
@ConvertAsProperties(    dtd = "-//org.gaia.gui.legalentity//NettingAgreement//EN", autostore = false)
@TopComponent.Description(    preferredID = "NettingAgreementTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.legalentity.NettingAgreementTopComponent")
@ActionReference(path = "Menu"+MenuManager.NettingAgreementTopComponentMenu, position = MenuManager.NettingAgreementTopComponent)
@TopComponent.OpenActionRegistration(    displayName = "#CTL_NettingAgreementAction", preferredID = "NettingAgreementTopComponent")
@Messages({"CTL_NettingAgreementAction=Netting Agreement", "CTL_NettingAgreementTopComponent=Netting Agreement Window", "HINT_NettingAgreementTopComponent=This is a Netting Agreement window"
})
public final class NettingAgreementTopComponent extends TopComponent {

    private AgreementFinder agreementFinder = null;
    Agreement agreement = null;
    private static final Logger logger = Logger.getLogger(NettingAgreementTopComponent.class);

    public NettingAgreementTopComponent() {
        initComponents();
        setName(Bundle.CTL_NettingAgreementTopComponent());
        setToolTipText(Bundle.HINT_NettingAgreementTopComponent());
    }

    private void initContext() {
        try {
            /**
             * recover legal Entitie
             */
            List entities = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITIES);
            GUIUtils.fillCombo(jComboBoxEntity1, entities);
            GUIUtils.fillCombo(jComboBoxEntity2, entities);
            /**
             * recover domain value
             */
            jComboBoxStatus.removeAllItems();
            jComboBoxStatus.addItem("Active");
            jComboBoxStatus.addItem("Inactive");

            /**
             * recover Argeement types
             */
            List agreementTypes = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_AGREEMENT_TYPES);
            GUIUtils.fillCombo(jComboBoxType, agreementTypes);

            ActionListener listener = new EnterListener(jTextFieldAgreementId);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    public class EnterListener implements ActionListener {

        /**
         * loading when you hit enter in trade id
         */
        public EnterListener(JTextField jTextFieldTradeId) {
            KeyStroke enter = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
            jTextFieldTradeId.registerKeyboardAction(this, "Enter", enter, JComponent.WHEN_FOCUSED);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().compareTo("Enter") == 0) {
                loadAgreement();
            }
        }
    }

    /**
     * fill Agreement
     */
    public void fillAgreement() {
        if (agreement == null) {
            agreement = new Agreement();
        }
        if (!jLabelInternalAgreementId.getText().isEmpty()) {
            agreement.setAgreementId(Integer.parseInt(jLabelInternalAgreementId.getText()));
        }
        try {


            if (jComboBoxEntity1.getSelectedItem() != null) {
                LegalEntity le1 = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxEntity1.getSelectedItem().toString());
                agreement.setLegalEntity1(le1);
            }
            if (jComboBoxEntity2.getSelectedItem() != null) {
                LegalEntity le2 = (LegalEntity) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_LEGAL_ENTITY_BY_SHORT_NAME, jComboBoxEntity2.getSelectedItem().toString());
                agreement.setLegalEntity2(le2);
            }
            if (jComboBoxType.getSelectedItem() != null) {
                agreement.setAgreementType(jComboBoxType.getSelectedItem().toString());
            }
            if (jComboBoxStatus.getSelectedItem() != null) {
                agreement.setStatus(jComboBoxStatus.getSelectedItem().toString());
            }

        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    /**
     * store Agreement
     */
    public void storeAgreement() {
        try {
            DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.STORE_AGREEMENT, agreement);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * load Agreement
     */
    public void loadAgreement() {

        if (NumberUtils.isInteger(jTextFieldAgreementId.getText())) {
            try {
                int agreementId = Integer.parseInt(jTextFieldAgreementId.getText());
                agreement = (Agreement) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_AGREEMENT_BY_ID, agreementId);
                if (agreement != null) {
                    jLabelInternalAgreementId.setText(Integer.toString(agreementId));

                    jComboBoxEntity1.setSelectedItem(agreement.getLegalEntity1().getShortName());
                    jComboBoxEntity2.setSelectedItem(agreement.getLegalEntity2().getShortName());

                    jComboBoxType.setSelectedItem(agreement.getAgreementType());
                    jComboBoxStatus.setSelectedItem(agreement.getStatus());

                } else {
                    (new ErrorMessageUI("No such agreement.")).setVisible(true);
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButtonNew = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabelInternalCounterparty = new javax.swing.JLabel();
        jComboBoxEntity1 = new javax.swing.JComboBox();
        jLabelCounterparty = new javax.swing.JLabel();
        jComboBoxEntity2 = new javax.swing.JComboBox();
        jLabelTradeId = new javax.swing.JLabel();
        jTextFieldAgreementId = new javax.swing.JTextField();
        jButtonLoad = new javax.swing.JButton();
        jLabelInternalAgreementId = new javax.swing.JLabel();
        jLabelStatus = new javax.swing.JLabel();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabelProductType2 = new javax.swing.JLabel();
        jComboBoxType = new javax.swing.JComboBox();
        jButtonsaveAsNew = new javax.swing.JButton();
        jButtonFindAsset = new javax.swing.JButton();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabelInternalCounterparty, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jLabelInternalCounterparty.text")); // NOI18N

        jComboBoxEntity1.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxEntity1.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelCounterparty, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jLabelCounterparty.text")); // NOI18N

        jComboBoxEntity2.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxEntity2.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));
        jComboBoxEntity2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxEntity2ActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelTradeId, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jLabelTradeId.text")); // NOI18N

        jTextFieldAgreementId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAgreementIdActionPerformed(evt);
            }
        });

        jButtonLoad.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonLoad, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jButtonLoad.text")); // NOI18N
        jButtonLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoadActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabelStatus, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jLabelStatus.text")); // NOI18N

        jComboBoxStatus.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabelProductType2, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jLabelProductType2.text")); // NOI18N

        jComboBoxType.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxType.setModel(new javax.swing.DefaultComboBoxModel(new String[] {  }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelInternalCounterparty)
                        .addGap(27, 27, 27)
                        .addComponent(jComboBoxEntity1, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelProductType2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(34, 34, 34)
                .addComponent(jLabelCounterparty)
                .addGap(18, 18, 18)
                .addComponent(jComboBoxEntity2, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelStatus)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jLabelInternalAgreementId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabelTradeId)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextFieldAgreementId, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButtonLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelInternalCounterparty)
                    .addComponent(jComboBoxEntity1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCounterparty)
                    .addComponent(jComboBoxEntity2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelTradeId)
                    .addComponent(jTextFieldAgreementId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLoad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelStatus)
                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelProductType2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBoxType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelInternalAgreementId, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jButtonsaveAsNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonsaveAsNew, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jButtonsaveAsNew.text")); // NOI18N
        jButtonsaveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsaveAsNewActionPerformed(evt);
            }
        });

        jButtonFindAsset.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonFindAsset, org.openide.util.NbBundle.getMessage(NettingAgreementTopComponent.class, "NettingAgreementTopComponent.jButtonFindAsset.text")); // NOI18N
        jButtonFindAsset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFindAssetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonFindAsset, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonsaveAsNew))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNew)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonsaveAsNew)
                    .addComponent(jButtonFindAsset))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        /**
         * save trade
         */
        fillAgreement();
        if (agreement.getLegalEntity1() != null && agreement.getLegalEntity2() != null) {
            storeAgreement();
            if (agreement.getAgreementId() != null) {
                JOptionPane.showMessageDialog(this, "Saved with id " + agreement.getAgreementId());
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving");
            }
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jComboBoxEntity2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxEntity2ActionPerformed
    }//GEN-LAST:event_jComboBoxEntity2ActionPerformed

    private void jTextFieldAgreementIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAgreementIdActionPerformed
    }//GEN-LAST:event_jTextFieldAgreementIdActionPerformed

    private void jButtonLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoadActionPerformed
        /**
         * load trade
         */
        loadAgreement();
    }//GEN-LAST:event_jButtonLoadActionPerformed

    private void jButtonFindAssetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFindAssetActionPerformed
        /**
         * agreement finder
         */
        agreementFinder = new AgreementFinder();

        NotifyDescriptor nd = new NotifyDescriptor(agreementFinder, "Find Agreement", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            try {
                Integer agreementId = agreementFinder.getAgreementId();
                agreement = (Agreement) DAOCallerAgent.callMethod(LegalEntityAccessor.class, LegalEntityAccessor.GET_AGREEMENT_BY_ID, agreementId);
                jTextFieldAgreementId.setText(agreement.getAgreementId().toString());
                loadAgreement();
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }

        }

    }//GEN-LAST:event_jButtonFindAssetActionPerformed

    private void jButtonsaveAsNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsaveAsNewActionPerformed

        agreement = null;
        jLabelInternalAgreementId.setText("");
        fillAgreement();
        if (agreement.getLegalEntity1() != null && agreement.getLegalEntity2() != null) {
            storeAgreement();
            if (agreement.getAgreementId() != null) {
                JOptionPane.showMessageDialog(this, "Saved with id " + agreement.getAgreementId());
            } else {
                JOptionPane.showMessageDialog(this, "Error while saving");
            }
        }
    }//GEN-LAST:event_jButtonsaveAsNewActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFindAsset;
    private javax.swing.JButton jButtonLoad;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonsaveAsNew;
    private javax.swing.JComboBox jComboBoxEntity1;
    private javax.swing.JComboBox jComboBoxEntity2;
    private javax.swing.JComboBox jComboBoxStatus;
    private javax.swing.JComboBox jComboBoxType;
    private javax.swing.JLabel jLabelCounterparty;
    private javax.swing.JLabel jLabelInternalAgreementId;
    private javax.swing.JLabel jLabelInternalCounterparty;
    private javax.swing.JLabel jLabelProductType2;
    private javax.swing.JLabel jLabelStatus;
    private javax.swing.JLabel jLabelTradeId;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField jTextFieldAgreementId;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
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
