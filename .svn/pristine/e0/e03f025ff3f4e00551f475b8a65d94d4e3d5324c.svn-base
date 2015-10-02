package org.gaia.io.dtcc5_3.products;

import java.math.BigDecimal;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.fpml.fpml_5.recordkeeping.FxOption;

/**
 *
 * @author Saber
 */
public class FxOptionLoader {

    private static Product productCcy1;
    private static Product productCcy2;
    private static BigDecimal strik;

    public static Product read(FxOption fxOption) {
        Product product = new Product();
        ProductForex forex = new ProductForex();


        if (fxOption.getProductId().get(0).getValue().contains("VAN")) {

            product.setProductType(ProductTypeUtil.ProductType.FX_VANILLA_OPTION.name);
            product.setIsAsset(Boolean.FALSE);
            product.setQuantityType(Trade.QuantityType.NOTIONAL.name);
            product.setStartDate(fxOption.getPremium().getPaymentDate().getAdjustableDate().getUnadjustedDate().getValue().toGregorianCalendar().getTime());
            product.setNotionalMultiplier(BigDecimal.ONE);

            if ("call".equals(fxOption.getSoldAs().value().toLowerCase())) {
                product.setNotionalCurrency(fxOption.getCallCurrencyAmount().getCurrency().getValue());
                productCcy1 =  ProductAccessor.getProductByShortName(fxOption.getCallCurrencyAmount().getCurrency().getValue());
                productCcy2 =  ProductAccessor.getProductByShortName(fxOption.getPutCurrencyAmount().getCurrency().getValue());
            } else {
                product.setNotionalCurrency(fxOption.getPutCurrencyAmount().getCurrency().getValue());
                productCcy1 = ProductAccessor.getProductByShortName(fxOption.getPutCurrencyAmount().getCurrency().getValue());
                productCcy2 = ProductAccessor.getProductByShortName(fxOption.getCallCurrencyAmount().getCurrency().getValue());
            }
            String currencyPair = fxOption.getPutCurrencyAmount().getCurrency().getValue() + "/" + fxOption.getCallCurrencyAmount().getCurrency().getValue();
            Product underlying = ProductAccessor.getProductByShortName(currencyPair);
            if (underlying==null){
                currencyPair = fxOption.getCallCurrencyAmount().getCurrency().getValue() + "/" + fxOption.getPutCurrencyAmount().getCurrency().getValue();
                underlying = ProductAccessor.getProductByShortName(currencyPair);
            }

            forex.setCurrency1(productCcy1);
            forex.setCurrency2(productCcy2);

            if (fxOption.getEuropeanExercise() != null) {

                forex.setExerciceType("European");
                product.setMaturityDate(fxOption.getEuropeanExercise().getExpiryDate().toGregorianCalendar().getTime());
            } else {
                forex.setExerciceType("American");
                product.setMaturityDate(fxOption.getAmericanExercise().getExpiryDate().toGregorianCalendar().getTime());
            }

            forex.setStrike(fxOption.getStrike().getRate());
            forex.setOptionStyle(fxOption.getSoldAs().value());
            forex.setProduct(product);
            product.setProductForex(forex);
            product.addUnderlying(underlying);

            return product;
        } else {
            return null;
        }
    }
}
