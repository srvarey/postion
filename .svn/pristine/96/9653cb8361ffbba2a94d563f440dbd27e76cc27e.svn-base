package org.gaia.dao.trades.events;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.gaia.dao.trades.ProductAccessor;
import static org.gaia.dao.trades.ProductAccessor.getProductById;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.legalEntity.ContractEvent;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class EventEngineTest extends AbstractTest{


    /**
     * Test of getProductEvents method, of class EventEngine.
     */
    @Test
    public void testGetProductEvents() {
        System.out.println("getProductEvents");
        Integer id=(Integer)HibernateUtil.getObjectWithQuery("select min(pe.product.productId) from ProductEvent pe");
        Product product = ProductAccessor.getProductById(id.intValue());
        List result = EventEngine.getProductEvents(product);
        assertNotNull("Problem getting product event", result);
    }

    /**
     * Test of cancelProductEvent method, of class EventEngine.
     */
    @Test
    public void testCancelProductEvent() {
        System.out.println("cancelProductEvent");
        Date date=DateUtils.getDate();
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(p.productId) from Product p left join p.productEvents pe where p.productType='"+ProductTypeUtil.ProductType.IRS+"' and pe.productEventId is null");
        Product product=getProductById(id);
        ContractEvent unwind=new ContractEvent();
        unwind.setContractEvent(ContractEventApply.ContractEventType.Unwind.name());
        unwind.setNegociationDate(date);
        unwind.setPaymentAmount(BigDecimal.ZERO);
        unwind.setPaymentCurrency("XXX");
        unwind.setPaymentDate(date);
        unwind.setValueDate(date);
        unwind.setProduct(product);
        unwind.setUnwindFactor(new BigDecimal(0.5));
        unwind.setUnwindAmount(BigDecimal.ONE);
        HibernateUtil.storeObject(unwind);

        // launch engine
        EventEngine.applyEventOnProduct(ContractEventApply.class,unwind.getContractEventId(),unwind.getProduct().getProductId());
        // check if unwinded
        product = ProductAccessor.getProductById(product.getProductId());
        assertNotNull("problem unwinding product" , product.getProductEvents());
        assertFalse("problem unwinding product" , product.getProductEvents().isEmpty());

        ProductEvent event=product.getProductEvents().iterator().next();

        // test getevent description
        String desc = EventEngine.getEventDescription(event);
        assertNotNull("problem in getEventDescription" , desc);

        // cancel
        Product result = EventEngine.cancelProductEvent(event);

        // checks
        assertTrue("problem cancelling event" , result.getProductEvents().isEmpty());
    }

}