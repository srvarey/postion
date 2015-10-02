package org.gaia.gui.assets;


import java.awt.event.KeyEvent;
import junit.framework.Test;
import org.gaia.dao.trades.TradeAccessor;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.EventTool;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;

/**
 *
 * @author Benjamin Frerejean
 */
public class CreditEventTest extends JellyTestCase{
    Integer tradeId=null;
    Integer productId=null;
    TopComponentOperator legalEntity=null;

    public CreditEventTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(CreditEventTest.class);
        testConfig = testConfig.addTest("creditEventTest");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    /** Called before every test case. */
    @Override
    public void setUp() {
        System.out.println("########  "+getName()+"  #######");
    }


    public void creditEventTest(){

        // CREATE CREDIT ENTITY
        System.out.println("create legal entity");
        new Action("Static Data|Legal Entities|Legal Entity", null).perform();
        legalEntity=new TopComponentOperator("Legal Entity");
        JTextFieldOperator field=new JTextFieldOperator(legalEntity,new NameComponentChooser("jTextFieldShortName"));
        field.setText("TEST CREDIT ENTITY");
        JComboBoxOperator combo=new JComboBoxOperator(legalEntity, new NameComponentChooser("jComboBoxRoles"));
        combo.setSelectedItem("Issuer"); // set role issuer
        new JButtonOperator(legalEntity, "Add").push();
        combo=new JComboBoxOperator(legalEntity, new NameComponentChooser("contratTypeComboBox"));
        combo.setSelectedIndex(1);
        //save
        new JButtonOperator(legalEntity, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // CREATE CDS PRODUCT
        System.out.println("create cds product");
        new Action("Static Data|Assets|CDS Product", null).perform();
        TopComponentOperator cdsProduct=new TopComponentOperator("CDS Product");
        field=new JTextFieldOperator(cdsProduct,new NameComponentChooser("jTextFieldShortName"));
        field.setText("TEST CDS");
        // set credit entity
        new JButtonOperator(cdsProduct, "Find").push();
        DialogOperator entityFinder=new DialogOperator("Entity Finder");
        field=new JTextFieldOperator(entityFinder,new NameComponentChooser("jTextFieldNameLike"));
        field.setText("TEST CREDIT ENTITY");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        //fill other fields
        combo=new JComboBoxOperator(cdsProduct, new NameComponentChooser("jComboBoxMaturity"));
        combo.setSelectedIndex(combo.getItemCount()-60);
        combo=new JComboBoxOperator(cdsProduct, new NameComponentChooser("jComboBoxCurrency"));
        combo.setSelectedItem("USD");
        field=new JTextFieldOperator(cdsProduct,new NameComponentChooser("jFormattedTextFieldCoupon"));
        field.setText("100");
        combo=new JComboBoxOperator(cdsProduct, new NameComponentChooser("jComboBoxType"));
        combo.setSelectedIndex(1);
        combo=new JComboBoxOperator(cdsProduct, new NameComponentChooser("contractTypeComboBox"));
        combo.setSelectedIndex(1);
        new JButtonOperator(cdsProduct, "Save").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        field=new JTextFieldOperator(cdsProduct,new NameComponentChooser("jTextFieldId"));
        if (!field.getText().isEmpty()){
            productId=new Integer(field.getText());
        }

        // TRADE ON THE PRODUCT
        System.out.println("capture trade");
        new Action("Trades|Asset Trade", null).perform();
        TopComponentOperator tradeTC=new TopComponentOperator("Asset Trade");
        //set counterparty
        new JButtonOperator(tradeTC, "...").push();
        entityFinder=new DialogOperator("Entity Finder");
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
        DialogOperator ccp=new DialogOperator("Set Default CCP");
        new JButtonOperator(ccp, "NO").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jTextFieldTradeId"));
        if (!field.getText().isEmpty()){
            tradeId=new Integer(field.getText());
        }

        // CREATE CREDIT EVENT
        System.out.println("create credit event");
        legalEntity.makeComponentVisible();
        new JButtonOperator(legalEntity, "Credit Events").push();
        TopComponentOperator creditEvent=new TopComponentOperator("Credit Event");
        new JButtonOperator(creditEvent, "+").push();
        JTableOperator eventTable = new JTableOperator(creditEvent,new NameComponentChooser("eventsTable"));
        eventTable.setValueAt(true, 0, 7);
        new JButtonOperator(creditEvent, "Save").push();
        new JButtonOperator(creditEvent, "Load").push();
        JTableOperator productsTable = new JTableOperator(creditEvent,new NameComponentChooser("productsTable"));
        productsTable.setValueAt(true, 0, 5);

        System.out.println("apply credit event");
        new JButtonOperator(creditEvent, "Apply").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        //checks
        System.out.println("check result");
        tradeTC.makeComponentVisible();
        new JButtonOperator(tradeTC, "Load").push();
        new EventTool().waitNoEvent(200);
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("lifeCycleStatusTextField"));
        assertEquals("Wrong trade status",TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name,field.getText());
        new JButtonOperator(tradeTC, "Show").push();
        cdsProduct=new TopComponentOperator("CDS Product");
        combo=new JComboBoxOperator(cdsProduct, new NameComponentChooser("statusComboBox"));
        assertEquals("Wrong product status",TradeAccessor.TradeLifeCycleStatus.DEFAULTED.name,combo.getSelectedItem().toString());
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
        if (productId!=null){
                // delete product
                field=new JTextFieldOperator(admin,new NameComponentChooser("jTextFieldProductId"));
                field.setText(productId.toString());
                new JButtonOperator(admin, "Delete product").push();
                ok=new DialogOperator("Message");
                new JButtonOperator(ok, "OK").push();
        }
        // delete legal entity
        if (legalEntity!=null){
            legalEntity.makeComponentVisible();
            new JButtonOperator(legalEntity, "Delete").push();
            ok=new DialogOperator("Confirm");
            new JButtonOperator(ok, "OK").push();
        }
        System.out.println("bye");
    }
}
