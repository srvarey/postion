package org.gaia.dao.referentials;

import java.util.Date;
import java.util.List;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.referentials.Currency;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */

/**
 *
 * @author Benjamin Frerejean
 */
public class CurrencyAccessorTest extends AbstractTest{

    /**
     * Test of storeCurrency method, of class CurrencyAccessor.
     */
    @Test
    public void testStoreCurrency() {
        System.out.println("storeCurrency");
        Currency currency = new Currency();
        currency.setCode("XXX");
        currency.setName("test");
        CurrencyAccessor.storeCurrency(currency);
        Currency res =(Currency) HibernateUtil.getObjectWithQuery("from Currency where name='test'");
        assertNotNull("failed to store currency",res);
        CurrencyAccessor.deleteCurrency("XXX");
    }

    /**
     * Test of getCurrencyByCode method, of class CurrencyAccessor.
     */
    @Test
    public void testGetCurrencyByCode() {
        System.out.println("getCurrencyByCode");
        String code = (String)HibernateUtil.getObjectWithQuery("select code from Currency");
        Currency result = CurrencyAccessor.getCurrencyByCode(code);
        assertNotNull("failed to get currency",result);
    }

    /**
     * Test of getCurrencyProductByCode method, of class CurrencyAccessor.
     */
    @Test
    public void testGetCurrencyProductByCode() {
        System.out.println("getCurrencyProductByCode");
        String code = (String)HibernateUtil.getObjectWithQuery("select code from Currency");
        Product result = CurrencyAccessor.getCurrencyProductByCode(code);
        assertNotNull("failed to get currency product",result);
    }

    /**
     * Test of loadAllCurrencies method, of class CurrencyAccessor.
     */
    @Test
    public void testLoadAllCurrencies() {
        System.out.println("loadAllCurrencies");
        List result = CurrencyAccessor.loadAllCurrencies();
        assertNotNull("failed to get currencies",result);
    }

    /**
     * Test of loadCurrencyCodes method, of class CurrencyAccessor.
     */
    @Test
    public void testLoadCurrencyCodes() {
        System.out.println("loadCurrencyCodes");
        List<String> result = CurrencyAccessor.loadCurrencyCodes();
        assertNotNull("failed to get currency codes",result);
    }

    /**
     * Test of loadCurrencyPairs method, of class CurrencyAccessor.
     */
    @Test
    public void testLoadCurrencyPairs() {
        System.out.println("loadCurrencyPairs");
        List<String> result = CurrencyAccessor.loadCurrencyPairs();
        assertNotNull("failed to get currency pairs",result);
    }

    /**
     * Test of deleteCurrency method, of class CurrencyAccessor.
     */
    @Test
    public void testDeleteCurrency() {
        System.out.println("deleteCurrency");
        Currency currency = new Currency();
        currency.setCode("XXX");
        currency.setName("test");
        CurrencyAccessor.storeCurrency(currency);
        CurrencyAccessor.deleteCurrency("XXX");
        Currency result = CurrencyAccessor.getCurrencyByCode("XXX");
        assertNull("failed to delete currency",result);
    }

    /**
     * Test of getSpotDate method, of class CurrencyAccessor.
     */
    @Test
    public void testGetSpotDate() {
        System.out.println("getSpotDate");
        String sPair = (String) HibernateUtil.getObjectWithQuery("select p.shortName from Product p where p.productType='"+ProductTypeUtil.ProductType.CURRENCY_PAIR.name+StringUtils.QUOTE);
        Date result = CurrencyAccessor.getSpotDate(new Date(), sPair);
        assertNotNull("failed to reolve spot date", result);
    }

}
