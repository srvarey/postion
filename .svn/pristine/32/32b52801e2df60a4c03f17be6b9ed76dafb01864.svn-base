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
public class CDSProductTCTest extends JellyTestCase{

    public CDSProductTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(CDSProductTCTest.class);
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
        new Action("Static Data|Assets|CDS Product", null).perform();
        TopComponentOperator screen=new TopComponentOperator("CDS Product");
        new JButtonOperator(screen, "Load").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        new JButtonOperator(assetFinder, "Find").push();
        new JTableOperator (assetFinder).selectCell(0, 0);
        new JButtonOperator(assetFinder, "OK").push();
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldShortName"));
        String name=field.getText();
        assertFalse("empty cds name",name.isEmpty());
        //save
        new JButtonOperator(screen, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        //open credit entity
        new JButtonOperator(screen, "Show").push();
        TopComponentOperator creditentity=new TopComponentOperator("Legal Entity");
        creditentity.closeWindow();
        //open references
        new JButtonOperator(screen, "Show Refs").push();
        DialogOperator references=new DialogOperator("Product References");
        new JTableOperator (references).selectCell(0, 0);
        new JButtonOperator(references, "OK").push();
        // show events
        new JButtonOperator(screen, "Show events").push();
        TopComponentOperator events=new TopComponentOperator("Events on CDS Product "+name);
        events.closeWindow();
        //load schedules
        new JButtonOperator(screen, "Schedule").push();
        TopComponentOperator schedule=new TopComponentOperator("Schedule");
        JTableOperator table= new JTableOperator (schedule);
        int count =table.getModel().getRowCount();
        assertFalse("empty cds  schedule",count==0);
        System.out.println("bye");
    }
}
