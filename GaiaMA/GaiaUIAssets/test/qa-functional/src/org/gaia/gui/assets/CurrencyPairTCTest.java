package org.gaia.gui.assets;


import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;



/**
 *
 * @author Benjamin Frerejean
 */
public class CurrencyPairTCTest extends JellyTestCase{

    public CurrencyPairTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(CurrencyPairTCTest.class);
        testConfig = testConfig.addTest("testLoadandSave");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    /** Called before every test case. */
    public void setUp() {
        System.out.println("########  "+getName()+"  #######");
    }

    public void testLoadandSave(){
        System.out.println("open screen");
        new Action("Static Data|Assets|Currency Pair", null).perform();
        TopComponentOperator screen=new TopComponentOperator("Currency Pair");
        //load
        new JTableOperator (screen).selectCell(0, 0);
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldShortName"));
        String val=field.getText();
        assertFalse("empty currency pair name",val.isEmpty());
        new JButtonOperator(screen, "Save").push();
        System.out.println("bye");
    }
}
