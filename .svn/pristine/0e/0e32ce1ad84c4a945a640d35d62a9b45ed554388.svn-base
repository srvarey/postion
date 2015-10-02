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

import java.awt.Component;
import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.audit.AuditAccessor;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.LoggedUser;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.trades.TradeEntity;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.assets.EquityTopComponent;

/**
 *
 * @author Benjamin Frerejean
 */
public class TradeUtils {

    private static final Logger logger = Logger.getLogger(EquityTopComponent.class);

    public static Boolean isAssetTrade(Trade trade) {
        ProductType productType = ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType());
        return productType.use.equals(ProductTypeUtil.ProductTypeUse.LISTED)
                || productType.equals(ProductTypeUtil.ProductType.CDS_INDEX)
                || productType.equals(ProductTypeUtil.ProductType.CDS_PRODUCT);
    }

    public static Boolean isSwapTrade(Trade trade) {
        ProductType productType = ProductTypeUtil.getProductTypeByName(trade.getProduct().getProductType());
        return productType.equals(ProductTypeUtil.ProductType.IRS)
                || productType.equals(ProductTypeUtil.ProductType.CCS)
                || productType.equals(ProductTypeUtil.ProductType.CUSTOM_CDS)
                || productType.equals(ProductTypeUtil.ProductType.LOAN_CDS)
                || productType.equals(ProductTypeUtil.ProductType.CDS_INDEX_TRANCH)
                || productType.equals(ProductTypeUtil.ProductType.CDS_FIXED_RECOVERY)
                || productType.equals(ProductTypeUtil.ProductType.CDS_RECOVERY_LOCKS)
                || productType.equals(ProductTypeUtil.ProductType.PERFORMANCE_SWAP);
    }

    public static boolean isParentProductType(String parent, Product product) {
        ProductTypeUtil.ProductType productType = ProductTypeUtil.getProductTypeByName(product.getProductType());
        return parent.equalsIgnoreCase(productType.parent.name);
    }

    public static void openTrade(Component component, int tradeId, boolean isOldVersion) {
        try {
            Trade trade = (Trade) DAOCallerAgent.callMethod(TradeAccessor.class, TradeAccessor.GET_TRADE_BY_ID, tradeId);
            loadTrade(trade, isOldVersion, component);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /**
     * function create MarginCall .
     *
     * @param quantity
     * @param marginType
     * @param account
     * @return MarginCall
     */
    public static Trade createMarginCall(BigDecimal quantity, String marginType, BoAccount account) {
        try {
            Trade trade;
            trade = new Trade();
            trade.setAmount(BigDecimal.ZERO);
            trade.setTradeDate(DateUtils.getDate());
            trade.setTradeTime(new Date());
            trade.setValueDate(DateUtils.getDate());
            trade.setInternalCounterparty(account.getClient());
            trade.setCounterparty(account.getAccountManager());
            TradeEntity ccpEntity = new TradeEntity();
            ccpEntity.setTrade(trade);
            ccpEntity.setLegalEntity(account.getAccountManager());
            ccpEntity.setRole(LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
            trade.setTradeEntityCollection(new HashSet());
            trade.getTradeEntityCollection().add(ccpEntity);
            trade.setIsCollateral(Boolean.TRUE);
            trade.setComment("Generated from margin call file");
            trade.setQuantity(quantity);
            trade.setQuantityType(Trade.QuantityType.NOTIONAL.name);
            trade.setSettlementCurrency(account.getCurrency());
            trade.setPrice(BigDecimal.ZERO);
            trade.setStatus(TradeAccessor.TradeStatus.VALIDATED.name);
            trade.setIsCollateral(Boolean.TRUE);
            trade.setTradeType(marginType);
            trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
            trade.setTrader(LoggedUser.getGaiaUser());
            Product product = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_SHORT_NAME, account.getCurrency());
            if (product != null) {
                trade.setProduct(product);
            } else {
                return null;
            }
            return trade;
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
            return null;
        }
    }

    /**
     * function create Trade .
     *
     * @param product
     * @param internalCounterpart
     * @param counterparty
     * @param valueDate
     * @param quantity
     * @param amount
     * @param settlementCurrency
     * @param price
     * @param priceCurrency
     * @param tradeDate
     * @param forexRate
     * @return trade
     */
    public static Trade createTrade(Product product, LegalEntity internalCounterpart, LegalEntity counterparty,
            BigDecimal quantity, BigDecimal amount, String settlementCurrency, BigDecimal price, String priceCurrency, BigDecimal forexRate,
            Date tradeDate, Date valueDate) {

        if (product != null && internalCounterpart != null && quantity != null && !quantity.equals(BigDecimal.ZERO)) {
            Trade trade = new Trade();
            trade.setProduct(product);
            trade.setInternalCounterparty(internalCounterpart);
            trade.setCounterparty(counterparty);
            trade.setQuantity(quantity);
            if (amount == null) {
                amount = BigDecimal.ZERO;
            }
            trade.setAmount(amount);
            if (settlementCurrency == null && product.getNotionalCurrency() != null) {
                settlementCurrency = product.getNotionalCurrency();
            }
            trade.setSettlementCurrency(settlementCurrency);
            if (price == null) {
                price = BigDecimal.ZERO;
                if (quantity.doubleValue() != 0) {
                    price = amount.divide(quantity, 20, BigDecimal.ROUND_HALF_DOWN);
                    BigDecimal mult = MarketQuote.QuotationType.getMult(product.getQuotationType());
                    price = price.multiply(mult);
                }
            }
            trade.setPrice(price);
            if (priceCurrency == null) {
                priceCurrency = settlementCurrency;
            }
            trade.setPriceCurrency(priceCurrency);
            if (tradeDate == null) {
                tradeDate = DateUtils.getDate();
            }
            trade.setTradeDate(tradeDate);
            if (valueDate == null) {
                if (product.getProductEquity() != null) {
                    valueDate = DateUtils.addCalendarDay(tradeDate, product.getSettlementDelay());
                } else if (product.getProductRate() != null && product.getSettlementDelay() != null) {
                    valueDate = DateUtils.addCalendarDay(tradeDate, product.getSettlementDelay());
                } else {
                    valueDate = tradeDate;
                }
            }
            trade.setValueDate(valueDate);
            trade.setStatus(TradeAccessor.TradeStatus.VALIDATED.name);
            trade.setIsCollateral(false);
            trade.setQuantityType(product.getQuantityType());

            return trade;
        }
        return null;
    }

    public static void loadTrade(Trade trade, boolean isOldVersion, Component component) throws HeadlessException {
        if (trade != null) {
            GaiaTradeTopComponent gaiaTradeTopComponent = null;
            if (trade.getIsCollateral()) {
                gaiaTradeTopComponent = new MarginCallTopComponent();
            } else if ((Trade.TradeType.FORWARD.name.equalsIgnoreCase(trade.getTradeType()))
                    || ProductTypeUtil.ProductType.FX_NDF.name.equalsIgnoreCase(trade.getProduct().getProductType())) {
                gaiaTradeTopComponent = new FXForwardTopComponent();
            } else if (ProductTypeUtil.ProductType.CURRENCY_PAIR.name.equalsIgnoreCase(trade.getProduct().getProductType())) {
                gaiaTradeTopComponent = new FXSpotTopComponent();
            } else if (isAssetTrade(trade)) {
                gaiaTradeTopComponent = new AssetTradeTopComponent();
            } else if (isSwapTrade(trade)) {
                gaiaTradeTopComponent = new SwapTopComponent();
            } else if (ProductTypeUtil.ProductType.FX_SWAP.name.equalsIgnoreCase(trade.getProduct().getProductType())) {
                gaiaTradeTopComponent = new FXSwapTopComponent();
            } else if (isParentProductType(ProductTypeUtil.ProductType.FX_STRATEGY.name, trade.getProduct())) {
                gaiaTradeTopComponent = new FXStrategyTopComponent();
            } else if (ProductTypeUtil.ProductType.FX_OPTION.name.equalsIgnoreCase(trade.getProduct().getProductType())
                    || TradeUtils.isParentProductType(ProductTypeUtil.ProductType.FX_OPTION.name, trade.getProduct())) {
                gaiaTradeTopComponent = new FXOptionTopComponent();
            } else if (ProductTypeUtil.ProductType.MMKT.name.equalsIgnoreCase(trade.getProduct().getProductType())) {
                gaiaTradeTopComponent = new MMKTTopComponent();
            } else if (ProductTypeUtil.ProductType.FRA.name.equalsIgnoreCase(trade.getProduct().getProductType())) {
                gaiaTradeTopComponent = new FRATopComponent();
            } else if (gaiaTradeTopComponent == null) {
                gaiaTradeTopComponent = new CustomTradeTopComponent();
            }

            gaiaTradeTopComponent.setIsTradeAudited(isOldVersion);
            gaiaTradeTopComponent.open();
            gaiaTradeTopComponent.requestActive();
            if (isOldVersion){
                AuditAccessor.unLazyObjectRecursively(trade);
            }
            gaiaTradeTopComponent.loadByTrade(trade);

        } else {
            JOptionPane.showMessageDialog(component, "No trade found.", "Warning", JOptionPane.WARNING_MESSAGE);

        }
    }
}
