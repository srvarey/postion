package org.gaia.gui.assets;


import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;



/**
 *
 * @author Benjamin Frerejean
 */
public class LegalEntityTCTest extends JellyTestCase{

    public LegalEntityTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(LegalEntityTCTest.class);
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
        new Action("Static Data|Legal Entities|Legal Entity", null).perform();
        TopComponentOperator screen=new TopComponentOperator("Legal Entity");
        new JButtonOperator(screen, "Query").push();
        new JTableOperator (screen).selectCell(0, 0);
        // loads
        JTextFieldOperator  field=new JTextFieldOperator(screen,new NameComponentChooser("jTextFieldShortName"));
        String name=field.getText();
        assertFalse("empty legal entity name",name.isEmpty());
        //open credit contract
        JComboBoxOperator combo=new JComboBoxOperator(screen, new NameComponentChooser("contratTypeComboBox"));
        if (combo.getSelectedIndex()<=0){
            combo.setSelectedIndex(1);
        }
        new JButtonOperator(screen, "Show").push();
        TopComponentOperator creditentity=new TopComponentOperator("Credit Contract");
        creditentity.closeWindow();
        // adds & remove roles
        new JButtonOperator(screen, "Add").push();
        JTableOperator rolesTable=new JTableOperator (screen,new NameComponentChooser("jTableRoles"));
        rolesTable.selectCell(rolesTable.getRowCount()-1, 0);
        new JButtonOperator(screen, "Remove").push();
        //open attributes
        new JButtonOperator(screen, "Attributes").push();
        TopComponentOperator attributes=new TopComponentOperator("Legal Entities Properties Editor");
        new JButtonOperator(screen, "Add").push();
        new JTableOperator (attributes).selectCell(0, 0);
        new JButtonOperator(screen, "Remove").push();
        attributes.closeWindow();
        //save
        new JButtonOperator(screen, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        System.out.println("bye");
    }
}
