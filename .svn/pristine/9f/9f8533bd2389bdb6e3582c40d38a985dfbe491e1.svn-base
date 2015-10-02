package org.gaia.gui.assets;

import java.awt.event.KeyEvent;
import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JRadioButtonOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;

/**
 * @author Benjamin Frerejean
 */
public class BondsTCTest1 extends JellyTestCase{

    Integer productId;
    Integer tradeId;

    public BondsTCTest1(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(BondsTCTest1.class);
        testConfig = testConfig.addTest("testCreateAndTrade");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    /** Called before every test case. */
    public void setUp() {
        System.out.println("########  "+getName()+"  #######");
    }

    public void testCreateAndTrade(){
        System.out.println("open screen");
        new Action("Static Data|Assets|Bonds", null).perform();
        TopComponentOperator bond=new TopComponentOperator("Bonds");

        //CREATE BOND
        // Fill the fields
        JTextFieldOperator  field=new JTextFieldOperator(bond,new NameComponentChooser("jTextFieldShortName"));
        field.setText("FUNC TEST");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jTextFieldLongName"));
        field.setText("FUNC TEST");
        JComboBoxOperator combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxType"));
        combo.setSelectedItem("Bond");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldNotional"));
        field.setText("1000");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxCurrency"));
        combo.setSelectedItem("USD");
        JComponentOperator dateComponent = new JComponentOperator(bond,new NameComponentChooser("jDateChooserdMaturity"));
        dateComponent.transferFocus();
        for(int i=0;i<365;i++ ){
            dateComponent.pushKey(KeyEvent.VK_UP);
        }
        JRadioButtonOperator radio=new JRadioButtonOperator(bond, new NameComponentChooser("jRadioButtonFloating"));
        radio.setSelected(true);
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldSpread"));
        field.setText("42");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxFrequency"));
        combo.setSelectedItem("Quarterly");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxDayCount"));
        combo.setSelectedItem("ACT/356");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxAdjustment"));
        combo.setSelectedItem("Modified Following");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxSeniority"));
        combo.setSelectedItem("Seniority");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldMinAmount"));
        field.setText("1000");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldSettlementDelay"));
        field.setText("2");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldIssuedAmount"));
        field.setText("1000000000");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxIssuer"));
        combo.setSelectedIndex(0);
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxCountry"));
        combo.setSelectedIndex(0);
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxQuotationType"));
        combo.setSelectedItem("Dirty");
        combo=new JComboBoxOperator(bond, new NameComponentChooser("jComboBoxIssuerType"));
        combo.setSelectedItem("Corp");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldPayLag"));
        field.setText("1");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldIssuePrice"));
        field.setText("100");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldRepayPrice"));
        field.setText("100");
        field=new JTextFieldOperator(bond,new NameComponentChooser("jFormattedTextFieldRate"));
        field.setText("1.2");

        //product references
        new JButtonOperator(bond, "Product References").push();
        DialogOperator refs=new DialogOperator("Product References");
        new JTableOperator (refs).setValueAt("test", 0, 1);
        new JButtonOperator(refs, "OK").push();

        //market codes
        new JButtonOperator(bond, "Market Codes").push();
        DialogOperator codes=new DialogOperator("Product Market Codes");
        new JButtonOperator(codes, "Add").push();
        JTableOperator codeTable = new JTableOperator (codes);
        codeTable.setValueAt("Bloomberg", 0, 0);
        codeTable.setValueAt("default", 0, 0);
        codeTable.setValueAt("test", 0, 2);
        codeTable.setValueAt("USD", 0, 3);
        new JButtonOperator(codes, "OK").push();

        new JButtonOperator(bond, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        field=new JTextFieldOperator(bond,new NameComponentChooser("jTextFieldId"));
        if (!field.getText().isEmpty()){
            productId=new Integer(field.getText());
        } else {
            assertNull("product not stored",productId);
        }
        new JButtonOperator(bond, "Schedule").push();
        TopComponentOperator schedule=new TopComponentOperator("Schedule");
        JTableOperator table= new JTableOperator (schedule);
        int count =table.getModel().getRowCount();
        assertFalse("empty bond schedule",count==0);

        // TRADE ON THE PRODUCT
        System.out.println("capture trade");
        new Action("Trades|Asset Trade", null).perform();
        TopComponentOperator tradeTC=new TopComponentOperator("Asset Trade");
        //set counterparty
        new JButtonOperator(tradeTC, "...").push();
        DialogOperator entityFinder=new DialogOperator("Entity Finder");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        // set cds product
        new JButtonOperator(tradeTC, "Find").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        field=new JTextFieldOperator(assetFinder,new NameComponentChooser("jFormattedTextFieldId"));
        field.setText(productId.toString());
        new JButtonOperator(assetFinder, "Find").push();
        new JTableOperator (assetFinder).selectCell(0, 0);
        new JButtonOperator(assetFinder, "OK").push();
        //fill other fields
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jFormattedTextFieldQuantity"));
        field.setText("1000000");
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jFormattedTextFieldPrice"));
        field.setText("2");
        JComponentOperator tradeDate = new JComponentOperator(tradeTC,new NameComponentChooser("jSpinnerTradeDate"));
        tradeDate.transferFocus();
        for(int i=0;i<100;i++ ){
            tradeDate.pushKey(KeyEvent.VK_DOWN);
        }
        new JButtonOperator(tradeTC, "Save").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jTextFieldTradeId"));
        if (!field.getText().isEmpty()){
            tradeId=new Integer(field.getText());
        } else {
            assertNull("trade not stored",tradeId);
        }

    }

    /** Called after every test case. */
    @Override
    public void tearDown() {
        // CLEAN OBJECTS
        // delete trade
        new Action("Admin|Admin", null).perform();
        JTextFieldOperator field;
        DialogOperator ok;
        TopComponentOperator admin=new TopComponentOperator("Admin");
        if (tradeId!=null){
            field=new JTextFieldOperator(admin,new NameComponentChooser("jTextFieldTradeId"));
            field.setText(tradeId.toString());
            new JButtonOperator(admin, "Delete trade").push();
            ok=new DialogOperator("Message");
            new JButtonOperator(ok, "OK").push();
        }
        // delete product
        if (productId!=null){
            // delete product
            field=new JTextFieldOperator(admin,new NameComponentChooser("jTextFieldProductId"));
            field.setText(productId.toString());
            new JButtonOperator(admin, "Delete product").push();
            ok=new DialogOperator("Message");
            new JButtonOperator(ok, "OK").push();
        }
        System.out.println("bye");
    }
}
