package org.gaia.dao.referentials;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * @author Benjamin Frerejean
 */

public class FrequencyUtilTest {


    /**
     * Test of getTenorFromFrequency method, of class FrequencyUtil.
     */
    @Test
    public void testGetTenorFromFrequency() {
        System.out.println("getTenorFromFrequency");
        String result = FrequencyUtil.getTenorFromFrequency("Annual");
        assertNotNull("missing tenor", result);
        assertFalse("missing tenor", result.isEmpty());
    }

    /**
     * Test of getFrequencyFromTenor method, of class FrequencyUtil.
     */
    @Test
    public void testGetFrequencyFromTenor() {
        System.out.println("getFrequencyFromTenor");
        String result = FrequencyUtil.getFrequencyFromTenor("1Y");
        assertNotNull("missing frequency", result);
        assertFalse("missing frequency", result.isEmpty());
    }

    /**
     * Test of getFrequencies method, of class FrequencyUtil.
     */
    @Test
    public void testGetFrequencies() {
        System.out.println("getFrequencies");
        List<String> result = FrequencyUtil.getFrequencies();
        assertNotNull("missing frequency", result);
        assertFalse("missing frequency", result.isEmpty());
    }

    /**
     * Test of getTenors method, of class FrequencyUtil.
     */
    @Test
    public void testGetTenors() {
        System.out.println("getTenors");
        List<String> result = FrequencyUtil.getTenors();
        assertNotNull("missing tenors", result);
        assertFalse("missing tenors", result.isEmpty());
    }

}
