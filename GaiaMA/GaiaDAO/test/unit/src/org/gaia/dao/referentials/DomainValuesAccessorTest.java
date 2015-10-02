package org.gaia.dao.referentials;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.DomainValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class DomainValuesAccessorTest extends AbstractTest{

    /**
     * Test of storeDomainValue method, of class DomainValuesAccessor.
     */
    @Test
    public void testStoreDomainValue() {
        System.out.println("storeDomainValue");
        DomainValue domainValue = new DomainValue();
        domainValue.setDescription("toto");
        domainValue.setName("name");
        domainValue.setValue("value");
        DomainValuesAccessor.storeDomainValue(domainValue);
        DomainValue res =(DomainValue) HibernateUtil.getObjectWithQuery("from DomainValue where name='name'");
        assertNotNull("failed to store domain value",res);
        HibernateUtil.deleteObject(domainValue);
    }

    /**
     * Test of getDomainValuesByName method, of class DomainValuesAccessor.
     */
    @Test
    public void testGetDomainValuesByName() {
        System.out.println("getDomainValuesByName");
        DomainValue domainValue =(DomainValue)HibernateUtil.getObjectWithQuery("from DomainValue");
        List<String> result = DomainValuesAccessor.getDomainValuesByName(domainValue.getName());
        assertNotNull("failed to load domain values", result);
        assertFalse("failed to load domain values", result.isEmpty());
    }

    /**
     * Test of getDomainValueByNameAndValue method, of class DomainValuesAccessor.
     */
    @Test
    public void testGetDomainValueByNameAndValue() {
        System.out.println("getDomainValueByNameAndValue");
        DomainValue domainValue =(DomainValue)HibernateUtil.getObjectWithQuery("from DomainValue");
        DomainValue result = DomainValuesAccessor.getDomainValueByNameAndValue(domainValue.getName(), domainValue.getValue());
        assertNotNull("failed to load domain value", result);
    }

    /**
     * Test of loadAllDomainValues method, of class DomainValuesAccessor.
     */
    @Test
    public void testLoadAllDomainValues() {
        System.out.println("loadAllDomainValues");
        List result = DomainValuesAccessor.loadAllDomainValues();
        assertNotNull("failed to load domain values", result);
        assertFalse("failed to load domain values", result.isEmpty());
    }

    /**
     * Test of deleteDomainValue method, of class DomainValuesAccessor.
     */
    @Test
    public void testDeleteDomainValue() {
        System.out.println("deleteDomainValue");
        DomainValue domainValue = new DomainValue();
        domainValue.setDescription("toto");
        domainValue.setName("name");
        domainValue.setValue("value");
        DomainValuesAccessor.storeDomainValue(domainValue);
        DomainValuesAccessor.deleteDomainValue("name", "value");
        DomainValue result=DomainValuesAccessor.getDomainValueByNameAndValue("name", "value");
        assertNull("failed to delete domain values", result);
    }

}
