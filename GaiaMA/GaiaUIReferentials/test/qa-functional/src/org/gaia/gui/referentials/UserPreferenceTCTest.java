
package org.gaia.gui.referentials;

import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.EventTool;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;



/**
 *
 * @author Benjamin Frerejean
 */
public class UserPreferenceTCTest extends JellyTestCase{

    public UserPreferenceTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(UserPreferenceTCTest.class);
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
        new Action("Admin|User Preferences", null).perform();
        new EventTool().waitNoEvent(100);
        TopComponentOperator screen=new TopComponentOperator("User Preferences");
        JTableOperator table=new JTableOperator(screen);
        int r1=table.getModel().getRowCount();
        new JButtonOperator(screen, "Add").push();
        int r2=table.getModel().getRowCount();
        assertTrue("problem adding row",r2==r1+1);
        table.setValueAt("Currency", r1, 0);
        table.setValueAt("USD", r1, 0);
        new JButtonOperator(screen, "Save").push();
        new JButtonOperator(screen, "Remove").push();
        new JButtonOperator(screen, "Save").push();
        System.out.println("bye");
    }
}
