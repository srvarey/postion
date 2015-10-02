
package org.gaia.gui.referentials;

import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.EventTool;
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
public class CalendarTCTest extends JellyTestCase{

    public CalendarTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(CalendarTCTest.class);
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
        new Action("Static Data|Calendar", null).perform();
        new EventTool().waitNoEvent(100);
        TopComponentOperator screen=new TopComponentOperator("Calendar");
        new JButtonOperator(screen, "Query").push();
        new EventTool().waitNoEvent(100);
        new JTableOperator (screen).selectCell(0, 0);
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldHolidayCode"));
        String val=field.getText();
        assertFalse("wrong calendar code",val.isEmpty());
        new JButtonOperator(screen, "Save").push();
        System.out.println("bye");
    }
}
