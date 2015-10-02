package org.gaia.dao.utils;

import org.gaia.domain.utils.HibernateUtil;
import java.util.List;
import java.util.Map;
import org.gaia.domain.utils.Mapping;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class MappingsAccessorTest extends AbstractTest{

    public MappingsAccessorTest() {
    }

    /**
     * Test of getMappingList method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingList() {
        System.out.println("getMappingList");
        List<Mapping> result = MappingsAccessor.getMappingList();
        assertNotNull("mappings not found", result);
        assertFalse("mappings not found", result.isEmpty());
    }

    /**
     * Test of getMappingNameList method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingNameList() {
        System.out.println("getMappingNameList");
        List<String> result = MappingsAccessor.getMappingNameList();
        assertNotNull("mappings not found", result);
        assertFalse("mappings not found", result.isEmpty());
    }

    /**
     * Test of getMappingsByName method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingsByName() {
        System.out.println("getMappingsByName");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        List<Mapping> result = MappingsAccessor.getMappingsByName(mapping.getMappingName());
        assertNotNull("mapping not found", result);
    }

    /**
     * Test of getMappingByNameAndKey method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingByNameAndKey() {
        System.out.println("getMappingByNameAndKey");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        Mapping result = MappingsAccessor.getMappingByNameAndKey(mapping.getMappingName(), mapping.getKey1());
        assertNotNull("mapping not found", result);
    }

    /**
     * Test of getMappingByNameAndValue method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingByNameAndValue() {
        System.out.println("getMappingByNameAndValue");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        Mapping result = MappingsAccessor.getMappingByNameAndValue(mapping.getMappingName(), mapping.getValue());
        assertNotNull("mapping not found", result);
    }

    /**
     * Test of getMappingKeyByNameAndValue method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingKeyByNameAndValue() {
        System.out.println("getMappingKeyByNameAndValue");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        String result = MappingsAccessor.getMappingKeyByNameAndValue(mapping.getMappingName(), mapping.getValue());
        assertNotNull("mapping not found", result);
    }

    /**
     * Test of getMappingValueByNameAndKey method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingValueByNameAndKey() {
        System.out.println("getMappingValueByNameAndKey");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        String result = MappingsAccessor.getMappingValueByNameAndKey(mapping.getMappingName(), mapping.getKey1());
        assertNotNull("mapping not found", result);
    }

    /**
     * Test of getMappingMapByName method, of class MappingsAccessor.
     */
    @Test
    public void testGetMappingMapByName() {
        System.out.println("getMappingMapByName");
        Mapping mapping=(Mapping) HibernateUtil.getObjectWithQuery("from Mapping m where m.mappingId=(select min(mappingId) from Mapping)");
        Map<String, String> result = MappingsAccessor.getMappingMapByName(mapping.getMappingName());
        assertNotNull("mappings not found", result);
        assertFalse("mappings not found", result.isEmpty());
    }

    /**
     * Test of deleteMapping method, of class MappingsAccessor.
     */
    @Test
    public void testDeleteMapping() {
        System.out.println("deleteMapping");
        Mapping mapping = new Mapping();
        mapping.setMappingName("UNIT TEST");
        HibernateUtil.saveObject(mapping);
        MappingsAccessor.deleteMapping(mapping);
        Object res=HibernateUtil.getObject(Mapping.class, mapping.getMappingId());
        assertNull("failed to delete mapping", res);
    }

    /**
     * Test of storeMapping method, of class MappingsAccessor.
     */
    @Test
    public void testStoreMapping() {
        System.out.println("storeMapping");
        Mapping mapping = new Mapping();
        mapping.setMappingName("UNIT TEST");
        MappingsAccessor.storeMapping(mapping);
        Object res=HibernateUtil.getObject(Mapping.class, mapping.getMappingId());
        assertNotNull("failed to store mapping", res);
        HibernateUtil.deleteObject(mapping);
    }

    /**
     * Test of getDefaultPricingEnvironmentName method, of class MappingsAccessor.
     */
    @Test
    public void testGetDefaultPricingEnvironmentName() {
        System.out.println("getDefaultPricingEnvironmentName");
        String result = MappingsAccessor.getDefaultPricingEnvironmentName();
        assertNotNull("missing default pricing environment", result);
    }

}
