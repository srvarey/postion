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
package org.gaia.gui.referentials;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.RatingsAccessor;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.referentials.Rating;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays ratings.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.referentials//Rating//EN", autostore = false)
@TopComponent.Description(preferredID = "RatingTopComponent", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.referentials.RatingTopComponent")
@ActionReference(path = "Menu" + MenuManager.RatingTopComponentMenu, position = MenuManager.RatingTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_RatingAction", preferredID = "RatingTopComponent")
@Messages({"CTL_RatingAction=Ratings", "CTL_RatingTopComponent=Ratings", "HINT_RatingTopComponent=This is Rating's window"})
public final class RatingTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(RatingTopComponent.class);

    public RatingTopComponent() {
        initComponents();
        setName(Bundle.CTL_RatingTopComponent());
        setToolTipText(Bundle.HINT_RatingTopComponent());
    }

    @Override
    public void initContext() {
        refreshTable();
        try {
            List<String> agencies = (List) DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.GET_AGENCIES);
            GUIUtils.fillCombo(jComboBoxAgency, agencies);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        ratingLabel = new javax.swing.JLabel();
        jTextFieldRating = new javax.swing.JTextField();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        agencyLabel = new javax.swing.JLabel();
        rankLabel = new javax.swing.JLabel();
        jComboBoxAgency = new javax.swing.JComboBox();
        jTextFieldRank = new javax.swing.JFormattedTextField();
        jButtonQuery = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRatings = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };

        backgroundPanel.setBackground(new java.awt.Color(254, 252, 254));

        jPanel1.setBackground(new java.awt.Color(230, 230, 253));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        org.openide.awt.Mnemonics.setLocalizedText(ratingLabel, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.ratingLabel.text")); // NOI18N

        jTextFieldRating.setName("jTextFieldRating"); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(agencyLabel, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.agencyLabel.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(rankLabel, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.rankLabel.text")); // NOI18N

        jComboBoxAgency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxAgency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));

        jTextFieldRank.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
        jTextFieldRank.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextFieldRank.setText(org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.jTextFieldRank.text")); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(agencyLabel)
                            .addComponent(rankLabel)
                            .addComponent(ratingLabel))
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldRating)
                                    .addComponent(jComboBoxAgency, 0, 133, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldRank, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 157, Short.MAX_VALUE)
                        .addComponent(jButtonNew)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonSave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonDelete)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxAgency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agencyLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldRating, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ratingLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rankLabel)
                    .addComponent(jTextFieldRank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonSave)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonNew))
                .addContainerGap())
        );

        jButtonQuery.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonQuery, org.openide.util.NbBundle.getMessage(RatingTopComponent.class, "RatingTopComponent.jButtonQuery.text")); // NOI18N
        jButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQueryActionPerformed(evt);
            }
        });

        jTableRatings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Agency","Rating","Rank"  }
        ));
        jTableRatings.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableRatings.getTableHeader().setReorderingAllowed(false);
        jTableRatings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRatingsMouseClicked(evt);
            }
        });
        jTableRatings.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableRatingsFocusGained(evt);
            }
        });
        jTableRatings.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableRatingsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableRatings);

        javax.swing.GroupLayout backgroundPanelLayout = new javax.swing.GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(backgroundPanelLayout);
        backgroundPanelLayout.setHorizontalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonQuery)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        backgroundPanelLayout.setVerticalGroup(
            backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(backgroundPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(backgroundPanelLayout.createSequentialGroup()
                        .addComponent(jButtonQuery)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * delete
     *
     * @param evt
     */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            String rating = jTextFieldRating.getText();
            if (StringUtils.isEmptyString(rating)) {
                return;
            }
            String agency = jComboBoxAgency.getSelectedItem().toString();

            DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.DELETE_RATING, agency, rating);
            refreshTable();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * store data
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            if (jTextFieldRating.getText() != null) {
                Rating rating = (Rating) DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.GET_RATING_BY_AGENY_AND_RATING,
                        jComboBoxAgency.getSelectedItem().toString(), jTextFieldRating.getText());
                if (rating == null) {
                    rating = new Rating();
                    rating.setAgency(jComboBoxAgency.getSelectedItem().toString());
                    rating.setRating(jTextFieldRating.getText());
                }
                Short rank;
                String sRank = jTextFieldRank.getText();
                if (NumberUtils.isInteger(sRank)) {
                    rank = Short.valueOf(sRank);
                    rating.setRank(rank);
                    DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.STORE_RATING, rating);
                } else {
                    JOptionPane.showMessageDialog(this, "Rank field must be an integer");
                }
            }
            refreshTable();
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        clearFields(this);
    }//GEN-LAST:event_jButtonNewActionPerformed

    private void jButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueryActionPerformed
        refreshTable();
    }//GEN-LAST:event_jButtonQueryActionPerformed

    private void jTableRatingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRatingsMouseClicked
        refereshData(evt);
    }//GEN-LAST:event_jTableRatingsMouseClicked

    private void jTableRatingsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableRatingsFocusGained
    }//GEN-LAST:event_jTableRatingsFocusGained

    private void jTableRatingsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableRatingsKeyPressed
        refereshData(evt);
    }//GEN-LAST:event_jTableRatingsKeyPressed

    /**
     * referesh Data
     *
     * @param evt
     */
    private void refereshData(java.awt.event.ComponentEvent evt) {
        int rownum = jTableRatings.getSelectedRow();
        if (rownum >= 0) {
            String agency = GUIUtils.getTableValueAt(jTableRatings, rownum, 0);
            String ratingCode = GUIUtils.getTableValueAt(jTableRatings, rownum, 1);
            if (ratingCode != null) {
                try {
                    Rating rating = (Rating) DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.GET_RATING_BY_AGENY_AND_RATING, agency, ratingCode);
                    jTextFieldRating.setText(rating.getRating());
                    jComboBoxAgency.setSelectedItem(rating.getAgency());
                    jTextFieldRank.setText(Short.valueOf(rating.getRank()).toString());
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }
            }
        }
    }

    /**
     * refreshTable
     */
    private void refreshTable() {
        try {
            List resultList = (List) DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.LOAD_ALL_RATINGS);
            DefaultTableModel tableModel = (DefaultTableModel) jTableRatings.getModel();
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            for (Object o : resultList) {
                Rating rating = (Rating) o;
                Object[] row = {rating.getAgency(), rating.getRating(), rating.getRank()};
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agencyLabel;
    private javax.swing.JPanel backgroundPanel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonQuery;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxAgency;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRatings;
    private javax.swing.JFormattedTextField jTextFieldRank;
    private javax.swing.JTextField jTextFieldRating;
    private javax.swing.JLabel rankLabel;
    private javax.swing.JLabel ratingLabel;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {
        p.setProperty("version", "1.0");
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
    }
}