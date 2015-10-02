package src.org.gaia.dao.pricing;

import org.gaia.dao.pricing.DayCountAccessorTest;
import org.gaia.dao.pricing.MeasuresAccessorTest;
import org.gaia.dao.pricing.PricingBuilderTest;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Jawhar Kamoun
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({PricingBuilderTest.class, src.org.gaia.dao.pricing.pricers.PricersSuite.class, MeasuresAccessorTest.class, DayCountAccessorTest.class})
public class PricingSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
