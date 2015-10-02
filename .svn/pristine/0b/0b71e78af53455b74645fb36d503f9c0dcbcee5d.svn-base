package org.gaia.dao.observables;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;


/**
 *
 * @author Benjamin Frerejean
 */


public class MarketQuoteAccessorTest extends AbstractTest{

    Date date = DateUtils.getDate();
    String quoteSet = MarketQuoteAccessor.getDefaultQuoteSet();

    public MarketQuoteAccessorTest() {
    }

    /**
     * Test of getLastForexRate method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetLastForexRate() {
        System.out.println("getLastForexRate");
        String forexPair = "EUR/USD";
        BigDecimal result = MarketQuoteAccessor.getLastForexRate(forexPair, date);
        assertNotNull("there should be a eur/usd fx rate", result);
    }

    /**
     * Test of getQuoteSpread method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetQuoteSpread() {
        System.out.println("getQuoteSpread");
        Trade trade = TradeAccessor.getAnyTradeByType(ProductTypeUtil.ProductType.CDS_PRODUCT.name);
        BigDecimal result = MarketQuoteAccessor.getQuoteSpread(trade, date);
        assertNotNull("cds quote sperad should exist", result);
    }

    /**
     * Test of getQuoteTypes method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetQuoteTypes() {
        System.out.println("getQuoteTypes");
        List<String> result = MarketQuoteAccessor.getQuoteTypes();
        assertNotNull("missing quote types", result);
        assertFalse("missing quote types", result.isEmpty());
    }

    /**
     * Test of convertAmount method, of class MarketQuoteAccessor.
     */
    @Test
    public void testConvertAmount() {
        System.out.println("convertAmount");
        BigDecimal amount = BigDecimal.ONE;
        String currencyDest = "EUR";
        String currencySource = "USD";
        BigDecimal result = MarketQuoteAccessor.convertAmount(amount, currencyDest, currencySource, date);
        assertNotNull("converting amount failed", result);
    }

    /**
     * Test of getDefaultQuoteSet method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetDefaultQuoteSet() {
        System.out.println("getDefaultQuoteSet");
        String result = MarketQuoteAccessor.getDefaultQuoteSet();
        assertNotNull("null default quotes", result);
    }

    /**
     * Test of getQuoteSets method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetQuoteSets() {
        System.out.println("getQuoteSets");
        List<String> result = MarketQuoteAccessor.getQuoteSets();
        assertNotNull("missing quote types", result);
        assertFalse("missing quote types", result.isEmpty());
    }

    /**
     * Test of fillObservable method, of class MarketQuoteAccessor.
     */
    @Test
    public void testFillObservable() {
        System.out.println("fillObservable");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        IObservable observable = new MarketQuoteObservable(stock);
        IObservable result = MarketQuoteAccessor.fillObservable(observable, date, quoteSet);
        assertNotNull("could not fill observable", result.getObservableValue(new Object[0]) );
    }

    /**
     * Test of storeQuote method, of class MarketQuoteAccessor.
     */
    @Test
    public void testStoreQuote() {
        System.out.println("storeQuote");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        MarketQuote marketQuote = new MarketQuote();
        marketQuote.setCurrency("USD");
        marketQuote.setProduct(stock);
        marketQuote.setClose(BigDecimal.ZERO);
        marketQuote.setValuationDate(new Date(0,0,0));
        MarketQuoteAccessor.storeQuote(marketQuote);
        assertNotNull("failed to store quote", marketQuote.getMarketQuoteId());
        HibernateUtil.deleteObject(marketQuote);
    }

    /**
     * Test of getProductQuotes method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetProductQuotes() {
        System.out.println("getProductQuotes");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        List<MarketQuote> result = MarketQuoteAccessor.getProductQuotes(stock.getProductId());
        assertNotNull("missing quotes", result);
        assertFalse("missing quotes", result.isEmpty());
    }

    /**
     * Test of getLastQuote method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetLastQuote() {
        System.out.println("getLastQuote");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        MarketQuote result = MarketQuoteAccessor.getLastQuote(stock.getProductId(), date, quoteSet);
        assertNotNull("missing quote", result);
    }

    /**
     * Test of getLastQuoteDate method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetLastQuoteDate() {
        System.out.println("getLastQuoteDate");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        Date result = MarketQuoteAccessor.getLastQuoteDate(stock.getProductId(), date, quoteSet);
        assertNotNull("missing quote date", result);
    }

    /**
     * Test of getCurveDates method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetCurveDates() {
        System.out.println("getCurveDates");
        Product curve=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name);
        List<Date> result = MarketQuoteAccessor.getCurveDates(curve.getProductId(), quoteSet);
        assertNotNull("missing curve date", result);
    }

    /**
     * Test of getCurveByDate method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetCurveByDate() {
        System.out.println("getCurveByDate");
        Product curvePoint=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE_MMKT_UNDERLYING.name);
        MarketQuote quote=(MarketQuote) HibernateUtil.getObjectWithQuery("from MarketQuote mq where mq.marketQuoteId=(select min(marketQuoteId) from MarketQuote mqq where mqq.product.productId="+curvePoint.getProductId()+")");
        List<Product> curves=ProductAccessor.getSuperProductList(curvePoint.getProductId());
        if (curves!=null && curves.size()>0){
            List<MarketQuote> result = MarketQuoteAccessor.getCurveByDate(curves.get(0), quote.getQuoteSet(),quote.getValuationDate());
            assertNotNull("missing curve quotes", result);
            assertFalse("missing curve quotes", result.isEmpty());
        }
    }

    /**
     * Test of getLastCurveDate method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetLastCurveDate() {
        System.out.println("getLastCurveDate");
        Product curve=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name);
        Date result = MarketQuoteAccessor.getLastCurveDate(curve, quoteSet, date);
        assertNotNull("missing curve date", result);
    }

    /**
     * Test of getLastCurveByDate method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetLastCurveByDate() {
        System.out.println("getLastCurveByDate");
        Product curve=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.IR_CURVE.name);
        List<MarketQuote> result = MarketQuoteAccessor.getLastCurveByDate(curve, quoteSet, date);
        assertNotNull("missing curve quotes", result);
        assertFalse("missing curve quotes", result.isEmpty());
    }

    /**
     * Test of getProductQuote method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetProductQuote() {
        System.out.println("getProductQuote");
        MarketQuote quote=(MarketQuote) HibernateUtil.getObjectWithQuery("from MarketQuote mq where mq.marketQuoteId=(select min(marketQuoteId) from MarketQuote)");
        MarketQuote result = MarketQuoteAccessor.getProductQuote(quote.getProduct().getProductId(), quote.getValuationDate(), quote.getQuoteSet());
        assertNotNull("missing quote", result);
    }

    /**
     * Test of getProductQuoteValue method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetProductQuoteValue() {
        System.out.println("getProductQuoteValue");
        Product stock=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.STOCK.name);
        Double result = MarketQuoteAccessor.getProductQuoteValue(stock.getProductId(), date, quoteSet);
        assertNotNull("missing quote", result);
    }

    /**
     * Test of getDetailQuoteValue method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetDetailQuoteValue() {
        System.out.println("getDetailQuoteValue");
        Product rate=ProductAccessor.getAnyProductByType(ProductTypeUtil.ProductType.INTEREST_RATE_INDEX.name);
        LinkedHashMap<Date, Double> result = MarketQuoteAccessor.getDetailQuoteValue(rate.getId(), DateUtils.addOpenDay(date, -30), date, quoteSet);
        assertNotNull("missing rate quotes", result);
        assertFalse("missing rate quotes", result.isEmpty());
    }

    /**
     * Test of getForexObservable method, of class MarketQuoteAccessor.
     */
    @Test
    public void testGetForexObservable() {
        System.out.println("getForexObservable");
        String forexPair = "EUR/USD";
        MarketQuoteObservable result = MarketQuoteAccessor.getForexObservable(forexPair);
        assertNotNull("missing pair", result);
    }

    /**
     * Test of addToFXRatesList method, of class MarketQuoteAccessor.
     */
    @Test
    public void testAddToFXRatesList() {
        System.out.println("addToFXRatesList");
        List<IObservable> forexRates = new ArrayList();
        String currency1 = "EUR";
        String currency2 = "USD";
        MarketQuoteAccessor.addToFXRatesList(forexRates, currency1, currency2);
        assertEquals("missing fx rates", forexRates.size(),1);
    }

}
