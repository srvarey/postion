
package org.gaia.gui.referentials;

import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.EventTool;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;



/**
 *
 * @author Benjamin Frerejean
 */
public class DomainValueTCTest extends JellyTestCase{

    public DomainValueTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(DomainValueTCTest.class);
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
        new Action("Static Data|Domain Values", null).perform();
        new EventTool().waitNoEvent(100);
        TopComponentOperator screen=new TopComponentOperator("Domain Values");
        new JButtonOperator(screen, "Query").push();
        new EventTool().waitNoEvent(100);
        JTreeOperator tree=new JTreeOperator(screen);
        tree.doExpandRow(1);
        tree.selectRow(2);
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldName"));
        String val=field.getText();
        assertFalse("wrong domain value code",val.isEmpty());
        new JButtonOperator(screen, "Save").push();
        System.out.println("bye");
    }
}
