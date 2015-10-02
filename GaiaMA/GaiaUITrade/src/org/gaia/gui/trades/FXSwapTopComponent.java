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
package org.gaia.gui.trades;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//FX//EN", autostore = false)
@TopComponent.Description(preferredID = "FXSwapTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.FXSwapTopComponent")
@ActionReference(path = "Menu"+MenuManager.FXSwapTopComponentMenu, position = MenuManager.FXSwapTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FXSwapAction")
@Messages({
    "CTL_FXSwapAction=FX Swap",
    "CTL_FXSwapTopComponent=FX Swap Window",
    "HINT_FXSwapTopComponent=This is a FX Swap window"
})
public class FXSwapTopComponent extends GaiaTradeTopComponent {

    public FXSwapTopComponent() {
        initComponents();
        setName(Bundle.CTL_FXSwapTopComponent());
        setToolTipText(Bundle.HINT_FXSwapTopComponent());
        putClientProperty(TopComponent.PROP_KEEP_PREFERRED_SIZE_WHEN_SLIDED_IN, Boolean.TRUE);
        fxGlobalInfoPanel.tradeTypeComboBox.setSelectedItem(Trade.TradeType.BUY_SELL);
        fxSpotBasePanel.setParentTopComponent(this);
        fxFwdBasePanel.setParentTopComponent(this);
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.FX_SWAP);
    }

    @Override
    public void loadByTradeId(Integer id) {
        setTrade((Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, id));
        setIsTradeAudited(false);
        fxGlobalInfoPanel.setTrade(getTrade(), false);
    }

    @Override
    public void loadByTrade(Trade _trade) {
        setTrade(_trade);
        fxGlobalInfoPanel.setTrade(getTrade(), false);
    }

    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null) {
            _displayName = getProduct().getProductType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = "FX Swap Window";
        }

        return _displayName;
    }

    @Override
    public void initContext() {
        fxSpotBasePanel.setFXFwd(false);
        fxSpotBasePanel.setParentTopComponent(this);
        fxFwdBasePanel.setParentTopComponent(this);
        fxFwdBasePanel.setSecondaryPanel();
        fxSpotBasePanel.initContext();
        fxGlobalInfoPanel.initContext();
        fxFwdBasePanel.initContext();
        fxSpotBasePanel.setRelatedFxPanel(fxFwdBasePanel, fxGlobalInfoPanel);
        fxGlobalInfoPanel.setRelatedFxPanel(fxSpotBasePanel);
        List<String> listMarginTypes = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIRS,LoggedUser.getLoggedUserId());
        Dimension buttonSize = new Dimension(100, 30);
        Border buttonBorder = new BasicBorders.ButtonBorder(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
        for (String ccyPair : listMarginTypes) {
            JButton button = new JButton(ccyPair);
            Color color = GUIUtils.randomColor();
            button.setFont(new java.awt.Font("Tahoma", 1, 14));
            button.setForeground(color);
            button.setBackground(Color.WHITE);
            button.setBorder(buttonBorder);
            button.setSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            button.setActionCommand(ccyPair);
            button.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    fxSpotBasePanel.setCcyPair(evt.getActionCommand());
                }
            });
            ccyPairPanel.add(button);
            ccyPairPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        fxGlobalInfoPanel = new org.gaia.gui.trades.FxGlobalInfoPanel();
        fxSpotBasePanel = new org.gaia.gui.trades.FXBasePanel();
        fxFwdBasePanel = new org.gaia.gui.trades.FXBasePanel();
        ccyPairPanel = new javax.swing.JPanel();
        jPanelButtons = new javax.swing.JPanel();
        saveTradeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jButtonShowTradeDetails = new javax.swing.JButton();
        jButtonPrice = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        ccyPairPanel.setBackground(new java.awt.Color(255, 255, 255));
        ccyPairPanel.setLayout(new javax.swing.BoxLayout(ccyPairPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanelButtons.setBackground(new java.awt.Color(255, 255, 255));

        saveTradeButton.setBackground(new java.awt.Color(195, 229, 255));
        saveTradeButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(saveTradeButton, org.openide.util.NbBundle.getMessage(FXSwapTopComponent.class, "FXSwapTopComponent.saveTradeButton.text")); // NOI18N
        saveTradeButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXSwapTopComponent.class, "FXSwapTopComponent.saveTradeButton.toolTipText")); // NOI18N
        saveTradeButton.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 51)));
        saveTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTradeButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(195, 229, 255));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(updateButton, org.openide.util.NbBundle.getMessage(FXSwapTopComponent.class, "FXSwapTopComponent.updateButton.text")); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelButtonsLayout = new javax.swing.GroupLayout(jPanelButtons);
        jPanelButtons.setLayout(jPanelButtonsLayout);
        jPanelButtonsLayout.setHorizontalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonsLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(updateButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(saveTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelButtonsLayout.setVerticalGroup(
            jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelButtonsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(saveTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButtonShowTradeDetails.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonShowTradeDetails, org.openide.util.NbBundle.getMessage(FXSwapTopComponent.class, "FXSwapTopComponent.jButtonShowTradeDetails.text")); // NOI18N
        jButtonShowTradeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonShowTradeDetailsActionPerformed(evt);
            }
        });

        jButtonPrice.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice, org.openide.util.NbBundle.getMessage(FXSwapTopComponent.class, "FXSwapTopComponent.jButtonPrice.text")); // NOI18N
        jButtonPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPriceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ccyPairPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fxGlobalInfoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(fxSpotBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(fxFwdBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonShowTradeDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ccyPairPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(fxGlobalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fxSpotBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(fxFwdBasePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelButtons, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonShowTradeDetails)
                                .addComponent(jButtonPrice)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        storeTrade(false);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void saveTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTradeButtonActionPerformed
        storeTrade(true);
    }//GEN-LAST:event_saveTradeButtonActionPerformed

    private void jButtonPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPriceActionPerformed
        price();
    }//GEN-LAST:event_jButtonPriceActionPerformed

    private void jButtonShowTradeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonShowTradeDetailsActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_jButtonShowTradeDetailsActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ccyPairPanel;
    private org.gaia.gui.trades.FXBasePanel fxFwdBasePanel;
    private org.gaia.gui.trades.FxGlobalInfoPanel fxGlobalInfoPanel;
    private org.gaia.gui.trades.FXBasePanel fxSpotBasePanel;
    private javax.swing.JButton jButtonPrice;
    private javax.swing.JButton jButtonShowTradeDetails;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelButtons;
    private javax.swing.JButton saveTradeButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
        fxSpotBasePanel.closeComponent();
        fxFwdBasePanel.closeComponent();
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

    private void storeTrade(boolean newTrade) {
        if (newTrade){
            product=null;
        }
        setTrade(fxSpotBasePanel.getTrade(newTrade));
        if (getTrade() != null) {
            saveTrade(getTrade());
            setDisplayName(getDisplayName());
            if (getTrade().getId() != null) {
                fxGlobalInfoPanel.setTrade(getTrade(), false);
            }
        }
    }
}
