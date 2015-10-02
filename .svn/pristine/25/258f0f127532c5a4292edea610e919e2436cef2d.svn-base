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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.referentials.UserAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays fx forward.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.trades//FXForward//EN", autostore = false)
@TopComponent.Description(preferredID = "FXForwardTopComponent", iconBase="org/gaia/gui/trades/fxtrade.png", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.FXForwardTopComponent")
@ActionReference(path = "Menu"+MenuManager.FXForwardTopComponentMenu, position = MenuManager.FXForwardTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_FXForwardAction")
@Messages({"CTL_FXForwardAction=FX Forward", "CTL_FXForwardTopComponent=FX Forward Window", "HINT_FXForwardTopComponent=This is a FX Forward window"})
public class FXForwardTopComponent extends GaiaTradeTopComponent {

    private static final Logger logger = Logger.getLogger(FXForwardTopComponent.class);

    public FXForwardTopComponent() {
        initComponents();
        setName(Bundle.CTL_FXForwardTopComponent());
        setToolTipText(Bundle.HINT_FXForwardTopComponent());
        fXBasePanel1.setFXFwd(true);
        fxGlobalInfoPanel1.tradeTypeComboBox.setSelectedItem(Trade.TradeType.FORWARD.name);
        fXBasePanel1.setParentTopComponent(this);
        availableProductTypes = new ArrayList<>();
        availableProductTypes.add(ProductTypeUtil.ProductType.CURRENCY_PAIR);
    }

    @Override
    public void initContext() {
        List<String> defaultCcyPairs = (List) DAOCallerAgent.callMethod(UserAccessor.class, UserAccessor.GET_DEFAULT_CURRENCY_PAIRS, LoggedUser.getLoggedUserId());
        Dimension buttonSize = new Dimension(100, 30);
        Border buttonBorder = new BasicBorders.ButtonBorder(Color.GRAY, Color.GRAY, Color.GRAY, Color.GRAY);
        for (String ccyPair : defaultCcyPairs) {
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
                    fXBasePanel1.setCcyPair(evt.getActionCommand());
                }
            });
            ccyPairPanel.add(button);
            ccyPairPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        }
        fxGlobalInfoPanel1.initContext();
        fXBasePanel1.initContext();
        fxGlobalInfoPanel1.setRelatedFxPanel(fXBasePanel1);
        fXBasePanel1.setRelatedFxPanel(null, fxGlobalInfoPanel1);

    }

    public void setSpot() {
        fXBasePanel1.setFXFwd(false);
    }

    @Override
    public void loadByTradeId(Integer id) {

        setTrade((Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, id));
        setIsTradeAudited(false);
        fxGlobalInfoPanel1.setTrade(getTrade(), true);
        setDisplayName(getDisplayName());
    }

    @Override
    public void loadByTrade(Trade _trade) {
        setTrade(_trade);
        fxGlobalInfoPanel1.setTrade(getTrade(), true);
        setDisplayName(getDisplayName());
    }

    @Override
    public String getDisplayName() {
        String _displayName;
        if (getTrade() != null) {
            _displayName = getTrade().getTradeType() + StringUtils.SPACE + String.valueOf(getTrade().getId());
        } else {
            _displayName = "FX Forward Window";
        }
        return _displayName;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        fXBasePanel1 = new org.gaia.gui.trades.FXBasePanel();
        ccyPairPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        saveTradeButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        jButtonPrice = new javax.swing.JButton();
        jButtonTradeDetails = new javax.swing.JButton();
        loadEventXButton = new org.jdesktop.swingx.JXButton();
        fxGlobalInfoPanel1 = new org.gaia.gui.trades.FxGlobalInfoPanel();

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        ccyPairPanel.setBackground(new java.awt.Color(255, 255, 255));
        ccyPairPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        ccyPairPanel.setLayout(new javax.swing.BoxLayout(ccyPairPanel, javax.swing.BoxLayout.Y_AXIS));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        saveTradeButton.setBackground(new java.awt.Color(195, 229, 255));
        saveTradeButton.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(saveTradeButton, org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.saveTradeButton.text")); // NOI18N
        saveTradeButton.setToolTipText(org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.saveTradeButton.toolTipText")); // NOI18N
        saveTradeButton.setBorder(javax.swing.BorderFactory.createMatteBorder(3, 3, 3, 3, new java.awt.Color(0, 255, 51)));
        saveTradeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTradeButtonActionPerformed(evt);
            }
        });

        updateButton.setBackground(new java.awt.Color(195, 229, 255));
        updateButton.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(updateButton, org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.updateButton.text")); // NOI18N
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        jButtonPrice.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonPrice, org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.jButtonPrice.text")); // NOI18N
        jButtonPrice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPriceActionPerformed(evt);
            }
        });

        jButtonTradeDetails.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonTradeDetails, org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.jButtonTradeDetails.text")); // NOI18N
        jButtonTradeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTradeDetailsActionPerformed(evt);
            }
        });

        loadEventXButton.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(loadEventXButton, org.openide.util.NbBundle.getMessage(FXForwardTopComponent.class, "FXForwardTopComponent.loadEventXButton.text")); // NOI18N
        loadEventXButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadEventXButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jButtonPrice)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonTradeDetails)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateButton)
                .addGap(42, 42, 42)
                .addComponent(saveTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(saveTradeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButtonPrice)
                .addComponent(jButtonTradeDetails)
                .addComponent(loadEventXButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(ccyPairPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(fxGlobalInfoPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(fXBasePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(ccyPairPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(fxGlobalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fXBasePanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveTradeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTradeButtonActionPerformed
        storeTrade(true);
    }//GEN-LAST:event_saveTradeButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        storeTrade(false);
    }//GEN-LAST:event_updateButtonActionPerformed

    private void jButtonPriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPriceActionPerformed
        price();
    }//GEN-LAST:event_jButtonPriceActionPerformed

    private void jButtonTradeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTradeDetailsActionPerformed
        showTradeInPropertiePanel();
    }//GEN-LAST:event_jButtonTradeDetailsActionPerformed

    private void loadEventXButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadEventXButtonActionPerformed
        loadProductEvents();
    }//GEN-LAST:event_loadEventXButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ccyPairPanel;
    private org.gaia.gui.trades.FXBasePanel fXBasePanel1;
    protected org.gaia.gui.trades.FxGlobalInfoPanel fxGlobalInfoPanel1;
    private javax.swing.JButton jButtonPrice;
    private javax.swing.JButton jButtonTradeDetails;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private org.jdesktop.swingx.JXButton loadEventXButton;
    private javax.swing.JButton saveTradeButton;
    private javax.swing.JButton updateButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
        fXBasePanel1.closeComponent();
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

    @Override
    public void fillTrade() {
        if (getTrade() != null) {
            fillTrade(getTrade().getTradeId() == null);
        } else {
            fillTrade(true);
        }
    }

    public void fillTrade(boolean newTrade) {
        try {
            setTrade(fXBasePanel1.getTrade(newTrade));
            if (fXBasePanel1.realTimeCheckBox.isSelected()) {
                String s = (String) JOptionPane.showInputDialog(this, "Sure?", fXBasePanel1.priceFormattedTextField.getText());
                BigDecimal price = BigDecimal.valueOf(GaiaTopComponent.numberFormat.parse(s).doubleValue());
                getTrade().setPrice(price);
                BigDecimal amount = price.multiply(getTrade().getQuantity()).negate();
                getTrade().setAmount(amount);
                getTrade().setMarketPrice(price.subtract(fXBasePanel1.getFwdPoints()));
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }

    private void storeTrade(boolean newTrade) {
        try {
            fillTrade(newTrade);
            if (getTrade() != null) {
                Trade trade = saveTrade(getTrade());
                setTrade(trade);
                if (getTrade().getId() != null) {
                    setDisplayName(getDisplayName());
                    fxGlobalInfoPanel1.setTrade(getTrade(), true);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }

    }

    @Override
    public void setNpvPricer(String npvPricer) {
        this.npvPricer = npvPricer;
        fXBasePanel1.changeRTQuoteSubscription();
    }
}
