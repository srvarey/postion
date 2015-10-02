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
public class AssetFinderTest extends JellyTestCase{

    public AssetFinderTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(AssetFinderTest.class);
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
        //load
        new JButtonOperator(screen, "Load").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        new JButtonOperator(assetFinder, "Find").push();
        JTableOperator table=new JTableOperator (assetFinder);
        if (table.getRowCount()>0){
            Integer id =new Integer(table.getValueAt(0, 0).toString());
            String name = table.getValueAt(0, 1).toString();
            String code = table.getValueAt(0, 2).toString();
            String type = table.getValueAt(0, 3).toString();
            JTextFieldOperator  field=new JTextFieldOperator(assetFinder,new NameComponentChooser("jTextFieldNameLike"));
            field.setText(name);
            new JButtonOperator(assetFinder, "Find").push();
            assertTrue("missing asset",table.getRowCount()>0);

            field=new JTextFieldOperator(assetFinder,new NameComponentChooser("jFormattedTextFieldId"));
            field.setText(id.toString());
            new JButtonOperator(assetFinder, "Find").push();
            assertTrue("missing asset",table.getRowCount()>0);

            field=new JTextFieldOperator(assetFinder,new NameComponentChooser("jTextFieldCodeLike"));
            field.setText(code);
            new JButtonOperator(assetFinder, "Find").push();
            assertTrue("missing asset",table.getRowCount()>0);

            JComboBoxOperator combo=new JComboBoxOperator(assetFinder, new NameComponentChooser("jComboBoxType"));
            combo.setSelectedItem(type);
            new JButtonOperator(assetFinder, "Find").push();
            assertTrue("missing asset",table.getRowCount()>0);


        }
        new JButtonOperator(assetFinder, "OK").push();
        System.out.println("bye");
    }
}
