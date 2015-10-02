package org.gaia.gui.assets;


import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.operators.DialogOperator;
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
public class EquityTCTest extends JellyTestCase{

    public EquityTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(EquityTCTest.class);
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
        new Action("Static Data|Assets|Equity", null).perform();
        TopComponentOperator screen=new TopComponentOperator("Equity");
        new JButtonOperator(screen, "Load").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        new JButtonOperator(assetFinder, "Find").push();
        new JTableOperator (assetFinder).selectCell(0, 0);
        new JButtonOperator(assetFinder, "OK").push();
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldShortName"));
        String val=field.getText();
        assertFalse("empty equity name",val.isEmpty());
        new JButtonOperator(screen, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        System.out.println("bye");
    }
}
