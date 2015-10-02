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
import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.observables.PricingEnvironmentAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.pricing.PricingBuilder;
import org.gaia.dao.pricing.pricers.IPricer;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.BoAccountIntermediary;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.PricingEnvironment;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeEntity;
import org.gaia.gui.common.GaiaProductTopComponent;
import org.gaia.gui.common.GaiaRTLabel;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.gaia.gui.common.TradeFinder;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.windows.Mode;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;

/**
 *
 * @author Benjamin Frerejean
 */
public class GaiaTradeTopComponent extends GaiaProductTopComponent {

    private Trade trade = null;
    protected ArrayList<String> listAttributes = null;
    private boolean oldVersion = false;
    private static final Logger logger = Logger.getLogger(GaiaReportTopComponent.class);
    protected String npvPricer;
    protected boolean isLoading = false;
    protected List<ProductTypeUtil.ProductType> availableProductTypes;

    public GaiaTradeTopComponent() {
    }

    public void setIsTradeAudited(boolean isTradeAudited) {
        if (isTradeAudited) {
            setBorder(new LineBorder(Color.red, 7));
            oldVersion = true;
        } else {
            setBorder(null);
            oldVersion = false;
        }
    }

    public Integer openTradeFinder() {
        Integer tradeId = null;
        TradeFinder tradeFinder = new TradeFinder(availableProductTypes);
        NotifyDescriptor nd = new NotifyDescriptor(tradeFinder, "Trade Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            tradeId = tradeFinder.getTradeId();
        }
        return tradeId;
    }

    public static Integer openTradeFinder(List<ProductTypeUtil.ProductType> availableProductTypes_) {
        Integer tradeId = null;
        TradeFinder tradeFinder = new TradeFinder(availableProductTypes_);
        NotifyDescriptor nd = new NotifyDescriptor(tradeFinder, "Trade Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            tradeId = tradeFinder.getTradeId();
        }
        return tradeId;
    }
    @Override
    public void initContext() {
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade _trade) {
        this.trade = _trade;
        if (trade != null) {
            setProduct(_trade.getProduct());
            content.set(Collections.emptyList(), null);
            content.add(trade);
        }
    }

    public ArrayList<String> getListAttributes() {
        return listAttributes;
    }

    public void setListAttributes(ArrayList<String> listAttributes) {
        this.listAttributes = listAttributes;
    }

    public void loadByTradeId(Integer id) {
    }

    public void loadByTrade(Trade _trade) {
    }

    public void fillTrade() {
    }

    public void price() {
        fillTrade();
        if (getProduct() != null && getTrade() != null) {
            WindowManager wm = WindowManager.getDefault();
            TradePricingTopComponent tradePricingTopComponent = (TradePricingTopComponent) wm.findTopComponent(TradePricingTopComponent.class.getSimpleName());
            if (tradePricingTopComponent == null) {
                tradePricingTopComponent = new TradePricingTopComponent();
            }

            Mode mode = WindowManager.getDefault().findMode("properties");
            if (mode != null) {
                mode.dockInto(tradePricingTopComponent);
            }

            tradePricingTopComponent.setTrade(getTrade());
            tradePricingTopComponent.open();
            tradePricingTopComponent.requestActive();
        }
    }

    /**
     * save trade
     */
    public void saveTrade() {
        Trade _trade = saveTrade(getTrade());
        setTrade(_trade);
    }

    /**
     * save trade
     * @param _trade
     * @return
     */
    public Trade saveTrade(Trade _trade) {
        if (oldVersion) {
            JOptionPane.showMessageDialog(this, "Old trade version could not be modified");
        } else {
            try {
                LegalEntity ccp = (LegalEntity) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_DEFAULT_CCP, _trade);
                if (_trade != null && _trade.getId() == null && ccp != null && _trade.getCounterparty() != ccp) {
                    int res = JOptionPane.showConfirmDialog(this, "Should trade be cleared through " + ccp.getShortName() + "\nConfirm? ", "Set Default CCP", JOptionPane.YES_NO_OPTION);
                    if (res == 0) {
                        Collection<TradeEntity> tradeEntityList = addCCPTradeEntities(ccp);
                        _trade.setTradeEntityCollection(tradeEntityList);
                    }
                } else if (_trade==null){
                    return null;
                }
                if (_trade.getProduct() == null) {
                    JOptionPane.showMessageDialog(this, "Please fill the product");
                } else if (_trade.getInternalCounterparty() == null) {
                    JOptionPane.showMessageDialog(this, "Please fill the internal counterparty ");
                } else if (_trade.getCounterparty() == null) {
                    JOptionPane.showMessageDialog(this, "Please fill the counterparty");
                } else if (_trade.getQuantity() == null || _trade.getQuantity().doubleValue()==0) {
                    JOptionPane.showMessageDialog(this, "Please fill the quantity");
                } else if (_trade.getProduct().getNotionalCurrency()==null || _trade.getProduct().getNotionalCurrency().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Notional Currency is missing");
                } else if (_trade.getProduct().getNotionalMultiplier()==null || _trade.getProduct().getNotionalMultiplier().doubleValue()==0) {
                    JOptionPane.showMessageDialog(this, "Product Notional is missing");
                } else if (_trade.getProduct().getMaturityDate()!=null && !_trade.getProduct().getMaturityDate().after(trade.getTradeDate())) {
                    JOptionPane.showMessageDialog(this, "Maturity date should be after trade date");
                } else {
                    /**
                     * not selected trade type
                     */
                    if (_trade.getTradeType() == null) {
                        _trade.setTradeType(Trade.TradeType.BUY_SELL.name);
                    }
                    Serializable object = DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.STORE_TRADE, _trade);
                    if (object != null) {
                        _trade = (Trade) object;
                        if (_trade.getId() != null) {
                            setTrade(_trade);
                            JOptionPane.showMessageDialog(this, "Saved with id " + _trade.getId());
                        } else {
                            JOptionPane.showMessageDialog(this, "Error while saving");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Error while saving");
                    }
                }
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
        return _trade;
    }

    public String getNpvPricerName() {
        if (npvPricer == null && trade != null) {

            PricingEnvironment pricingEnvironment = (PricingEnvironment) DAOCallerAgent.callMethod(PricingEnvironmentAccessor.class,
                    PricingEnvironmentAccessor.GET_DEFAULT_PRICING_ENVIRONMENT);

            MeasuresAccessor.MeasureGroup[] groups = new MeasuresAccessor.MeasureGroup[]{MeasuresAccessor.MeasureGroup.PV_GROUP};
            Map<MeasuresAccessor.MeasureGroup, IPricer> pricerMaps = (Map) DAOCallerAgent.callMethodWithXMLSerialization(PricingBuilder.class,
                    PricingBuilder.LOAD_PRICER_MAP, trade, DateUtils.getDate(), pricingEnvironment, groups);
            if (pricerMaps.get(MeasuresAccessor.MeasureGroup.PV_GROUP) != null) {
                npvPricer = pricerMaps.get(MeasuresAccessor.MeasureGroup.PV_GROUP).getClass().getSimpleName();
            }
        }
        return npvPricer;
    }

    public void setNpvPricer(String npvPricer) {
        this.npvPricer = npvPricer;
    }

    /**
     * show Trade In Propertie Panel
     */
    public void showTradeInPropertiePanel() {
        if (getTrade() != null) {
            WindowManager wm = WindowManager.getDefault();
            TradePropertiesEditorTopComponent tradePropertiesEditorTopComponent = (TradePropertiesEditorTopComponent) wm.findTopComponent(TradePropertiesEditorTopComponent.class.getSimpleName());
            if (tradePropertiesEditorTopComponent == null) {
                tradePropertiesEditorTopComponent = new TradePropertiesEditorTopComponent();
            }

            Mode mode = WindowManager.getDefault().findMode("properties");
            if (mode != null) {
                mode.dockInto(tradePropertiesEditorTopComponent);
            }
            tradePropertiesEditorTopComponent.setTrade(getTrade());
            tradePropertiesEditorTopComponent.open();
            tradePropertiesEditorTopComponent.requestActive();
        }else{
            JOptionPane.showMessageDialog(this, "Please load a trade");
        }
    }

    /**
     * add CCP trade Entities
     */
    private Collection<TradeEntity> addCCPTradeEntities(LegalEntity ccp) {
        Collection<TradeEntity> tradeEntityList = new ArrayList<>();
        TradeEntity newTradeEntity = new TradeEntity();
        newTradeEntity.setLegalEntity(ccp);
        newTradeEntity.setTrade(trade);
        newTradeEntity.setRole(LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
        tradeEntityList.add(newTradeEntity);

        List<BoAccount> accountList = null;
        try {
            accountList = (List) DAOCallerAgent.callMethod(LegalEntityAccessor.class,
                    LegalEntityAccessor.GET_BO_ACCOUNTS, trade.getInternalCounterparty().getLegalEntityId(),
                    ccp.getLegalEntityId(), LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        if (accountList != null && !accountList.isEmpty()) {
            BoAccount account = accountList.get(0);
            if (account.getBoAccountIntermediaryCollection() != null) {
                BoAccountIntermediary intermediary = LegalEntityAccessor.getDefaultIntermediaryFromList(new ArrayList(account.getBoAccountIntermediaryCollection()));
                if (intermediary != null) {
                    newTradeEntity = new TradeEntity();
                    newTradeEntity.setLegalEntity(intermediary.getLegalEntity());
                    newTradeEntity.setTrade(trade);
                    newTradeEntity.setRole(LegalEntityRole.LegalEntityRoleEnum.CLEARING_MEMBER_ROLE.name);
                    tradeEntityList.add(newTradeEntity);
                }
            }
        }
        return tradeEntityList;
    }

    @Override
    public String getDisplayName() {
        String _displayName = StringUtils.EMPTY_STRING;

        if (getTrade() == null || getTrade().getId() == null) {
            _displayName += "new";
        }
        if (getProduct() != null && getProduct().getProductType() != null) {
            if (getProduct().getProductType().equalsIgnoreCase(ProductTypeUtil.ProductType.CURRENCY_PAIR.name)) {
                if (getTrade().getTradeType().equalsIgnoreCase(Trade.TradeType.BUY_SELL.name)) {
                    _displayName += " Spot";
                } else {
                    _displayName += StringUtils.SPACE + getTrade().getTradeType();
                }
            } else {
                _displayName += StringUtils.SPACE + getProduct().getProductType();
            }
        } else {
            _displayName += " trade";
        }
        if (getTrade() != null && getTrade().getId() != null) {
            _displayName += StringUtils.SPACE + String.valueOf(getTrade().getId());
        }
        return _displayName;
    }

    public void showContractEventWindow() {
        if (trade != null && trade.getQuantity() != null && trade.getProduct() != null && trade.getProduct().getId() != null
         && !trade.getStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name)
         && !trade.getStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.TERMINATED.name)
         && !trade.getStatus().equalsIgnoreCase(TradeAccessor.TradeLifeCycleStatus.NOVATED.name)) {
            WindowManager wm = WindowManager.getDefault();
            ContractEventTopComponent contractEventTopComponent = (ContractEventTopComponent) wm.findTopComponent(ContractEventTopComponent.class.getSimpleName());
            contractEventTopComponent.open();
            contractEventTopComponent.setTrade(trade);
            contractEventTopComponent.requestActive();
        }
    }

    public static void refreshTradeWindow(Integer tradeId) {
        WindowManager wm = WindowManager.getDefault();
        for (TopComponent comp : wm.getOpenedTopComponents(wm.findMode("editor"))) {
            if (comp instanceof GaiaTradeTopComponent) {
                GaiaTradeTopComponent tradeTC = (GaiaTradeTopComponent) comp;
                if (tradeTC.getTrade().getTradeId().intValue() == tradeId) {
                    tradeTC.loadByTradeId(tradeTC.getTrade().getId());
                }
            }
        }
    }

    public static void refreshTradeWindowByProduct(Integer productId) {
        WindowManager wm = WindowManager.getDefault();
        for (TopComponent comp : wm.getOpenedTopComponents(wm.findMode("editor"))) {
            if (comp instanceof GaiaTradeTopComponent) {
                GaiaTradeTopComponent tradeTC = (GaiaTradeTopComponent) comp;
                if (tradeTC.getProduct() != null && tradeTC.getProduct().getProductId() != null
                        && tradeTC.getProduct().getProductId().intValue() == productId) {
                    tradeTC.loadByTradeId(tradeTC.getTrade().getId());
                }
            }
        }
    }

    @Override
    protected void componentClosed() {
        super.componentClosed();
        stopRTLabels(this);
    }

    public void stopRTLabels(Component comp){
         if (comp instanceof GaiaRTLabel) {
                GaiaRTLabel rtlabel=(GaiaRTLabel)comp ;
                rtlabel.stopRTQuoteSubscription();
         } else if (comp instanceof Container) {
             Container container=(Container)comp;
             for (Component sub : container.getComponents()){
                stopRTLabels((Container) sub);
             }
         }
    }

}
