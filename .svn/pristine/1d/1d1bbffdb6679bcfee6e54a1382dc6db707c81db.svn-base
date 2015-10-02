package org.gaia.dao.pricing;

import java.util.List;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.observables.Stress;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Jawhar Kamoun
 */
public class MeasuresAccessorTest extends AbstractTest {

    public MeasuresAccessorTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getUnitMeasure method, of class MeasuresAccessor.
     */
    @Test
    public void testGetUnitMeasure() {
        System.out.println("getUnitMeasure");
        String measureName = "CVA";
        IPricerMeasure result = MeasuresAccessor.getUnitMeasure(measureName);
        assertNotNull("Pricing Measeare CVA must Be Not null", result);
    }

    /**
     * Test of getMeasureByName method, of class MeasuresAccessor.
     */
    @Test
    public void testGetMeasureByName() {
        System.out.println("getUnitMeasure");
        String measureName = "CVA";
        IPricerMeasure result = MeasuresAccessor.getMeasureByName(measureName);
        assertNotNull("Pricing Measeare CVA must Be Not null", result);
    }

    /**
     * Test of getStoredMeasures method, of class MeasuresAccessor.
     */
    @Test
    public void testGetStoredMeasures() {
        System.out.println("getStoredMeasures");
        List<IPricerMeasure> result = MeasuresAccessor.getStoredMeasures();
        assertNotNull("Greek List must be not null", result);
    }

    /**
     * Test of getGreekSetting method, of class MeasuresAccessor.
     */
    @Test
    public void testGetGreekSetting() {
        System.out.println("getGreekSetting");
        String pricerMeasure = "CHARM";
        GreekSetting result = MeasuresAccessor.getGreekSetting(pricerMeasure);
        assertNotNull("Pricing Measeare THETA must Be Not null", result);
    }

    /**
     * Test of deleteGreekSetting method, of class MeasuresAccessor.
     */
    @Test
    public void testDeleteGreekSetting() {
        System.out.println("deleteGreekSetting");
        GreekSetting greekSettingClone = new GreekSetting("GREEK_TEST");
        greekSettingClone.setIsAbsolute(true);
        greekSettingClone.setIsBusDaysTimeShift(true);
        greekSettingClone.setIsInAmount(false);
        greekSettingClone.setMovingPricerMeasure("VPN");
        greekSettingClone.setShiftDimension(new Short("1"));
        greekSettingClone.setShifted("Time");
        Stress stress = new Stress();
        stress.setStressName("GREEK_TEST");
        stress = MeasuresAccessor.storeStress(stress);
        greekSettingClone.setStress(stress);

        greekSettingClone = (GreekSetting) HibernateUtil.storeAndReturnObject(greekSettingClone);
        MeasuresAccessor.deleteGreekSetting(greekSettingClone);
        greekSettingClone = MeasuresAccessor.getGreekSetting("GREEK_TEST");
        assertTrue("Some flows were not deleted", greekSettingClone == null);

    }

    /**
     * Test of saveGreekSetting method, of class MeasuresAccessor.
     */
    @Test
    public void testSaveGreekSetting() {
        System.out.println("saveGreekSetting");

        GreekSetting greekSettingClone = new GreekSetting("GREEK_TEST");
        greekSettingClone.setIsAbsolute(true);
        greekSettingClone.setIsBusDaysTimeShift(true);
        greekSettingClone.setIsInAmount(false);
        greekSettingClone.setMovingPricerMeasure("VPN");
        greekSettingClone.setShiftDimension(new Short("1"));
        greekSettingClone.setShifted("Time");
        Stress stress = new Stress();
        stress.setStressName("GREEK_TEST");
        stress = MeasuresAccessor.storeStress(stress);
        greekSettingClone.setStress(stress);
        HibernateUtil.storeObject(greekSettingClone);
        GreekSetting result = MeasuresAccessor.getGreekSetting("GREEK_TEST");
        assertNotNull("GreekSetting stored must be not null", result);
        MeasuresAccessor.deleteGreekSetting(result);

    }
}
