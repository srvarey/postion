
package org.gaia.gui.collat;

import java.awt.event.KeyEvent;
import junit.framework.Test;
import org.netbeans.jellytools.JellyTestCase;
import org.netbeans.jellytools.TopComponentOperator;
import org.netbeans.jellytools.actions.Action;
import org.netbeans.jemmy.operators.DialogOperator;
import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JComboBoxOperator;
import org.netbeans.jemmy.operators.JComponentOperator;
import org.netbeans.jemmy.operators.JLabelOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;
import org.netbeans.jemmy.operators.JPopupMenuOperator;
import org.netbeans.jemmy.operators.JTableOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.util.NameComponentChooser;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbModuleSuite.Configuration;

/**
 *
 * @author Benjamin Frerejean
 */
public class CollateralTest extends JellyTestCase{
    Integer tradeId=null;
    Integer productId=null;
    TopComponentOperator ccpLegalEntity=null;
    TopComponentOperator portfolio=null;

    public CollateralTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(CollateralTest.class);
        testConfig = testConfig.addTest("collateralTest");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return NbModuleSuite.create(testConfig);
    }

    /** Called before every test case. */
    @Override
    public void setUp() {
        System.out.println("########  "+getName()+"  #######");
    }

    public void collateralTest(){

        // CREATE CCP
        System.out.println("create CCP");
        new Action("Static Data|Legal Entities|Legal Entity", null).perform();
        ccpLegalEntity=new TopComponentOperator("Legal Entity");
        JTextFieldOperator field=new JTextFieldOperator(ccpLegalEntity,new NameComponentChooser("jTextFieldShortName"));
        field.setText("TEST CCP");
        JComboBoxOperator combo=new JComboBoxOperator(ccpLegalEntity, new NameComponentChooser("jComboBoxRoles"));
        combo.setSelectedItem("CCP"); // set role issuer
        new JButtonOperator(ccpLegalEntity, "Add").push();
        combo=new JComboBoxOperator(ccpLegalEntity, new NameComponentChooser("contratTypeComboBox"));
        combo.setSelectedIndex(1);
        //save
        new JButtonOperator(ccpLegalEntity, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // SET MARGIN RULES
        System.out.println("set margin rules");
        new Action("Monitoring|Settings|Margin Definition", null).perform();
        TopComponentOperator marginDef=new TopComponentOperator("Margin Definition");
        JTableOperator ccp=new JTableOperator(marginDef,new NameComponentChooser("jTableCCP"));
        int i=0;
        while (i<ccp.getModel().getRowCount() && !ccp.getValueAt(i, 1).toString().equalsIgnoreCase("TEST CCP")){
            i++;
            ccp.selectCell(i, 0);
        }
        //set filter
        new JButtonOperator(marginDef,new NameComponentChooser("jButtonAddFilter")).push();
        JTableOperator ccpFilters=new JTableOperator(marginDef,new NameComponentChooser("jTableCCPTradeLink"));
        ccpFilters.setValueAt("ALL", 0, 0);
        new JButtonOperator(marginDef,new NameComponentChooser("jButtonSaveFilters")).push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        //set rules
        new JButtonOperator(marginDef,new NameComponentChooser("jButtonAddRule")).push();
        JTableOperator ccpRules=new JTableOperator(marginDef,new NameComponentChooser("jTableCCPRuleLink"));
        ccpRules.setValueAt("Collateral Span", 0, 1);
        ccpRules.setValueAt("Margin_Span", 0, 2);
        new JButtonOperator(marginDef,new NameComponentChooser("jButtonAddRule")).push();
        ccpRules.setValueAt("Collateral Variation", 1, 1);
        ccpRules.setValueAt("Margin_Var", 1, 2);
        new JButtonOperator(marginDef,new NameComponentChooser("jButtonSaveCCPRuleLink")).push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // CREATE PORTFOLIO
        System.out.println("create portfolio");
        new Action("Static Data|Legal Entities|Legal Entity", null).perform();
        portfolio=new TopComponentOperator("Legal Entity");
        field=new JTextFieldOperator(portfolio,new NameComponentChooser("jTextFieldShortName"));
        field.setText("TEST PORTFOLIO");
        combo=new JComboBoxOperator(portfolio, new NameComponentChooser("jComboBoxRoles"));
        combo.setSelectedItem("InternalCounterparty"); // set role issuer
        new JButtonOperator(portfolio, "Add").push();
        combo=new JComboBoxOperator(portfolio, new NameComponentChooser("contratTypeComboBox"));
        combo.setSelectedIndex(1);
        //save
        new JButtonOperator(portfolio, "Save").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // ACCOUNT
        System.out.println("create account");
        new Action("Static Data|Legal Entities|BO Account", null).perform();
        TopComponentOperator boAccounts=new TopComponentOperator("BO Accounts");
        //set client
        new JButtonOperator(boAccounts, new NameComponentChooser("jButtonFindClient")).push();
        DialogOperator entityFinder=new DialogOperator("Entity Finder");
        field=new JTextFieldOperator(entityFinder,new NameComponentChooser("jTextFieldNameLike"));
        field.setText("TEST PORTFOLIO");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        //set ccp
        new JButtonOperator(boAccounts, new NameComponentChooser("jButtonFindCCP")).push();
        entityFinder=new DialogOperator("Entity Finder");
        field=new JTextFieldOperator(entityFinder,new NameComponentChooser("jTextFieldNameLike"));
        field.setText("TEST CCP");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        //set account manager
        new JButtonOperator(boAccounts,"Add").push();
        entityFinder=new DialogOperator("Entity Finder");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        new JButtonOperator(boAccounts,"Make Default").push();
        combo=new JComboBoxOperator(boAccounts, new NameComponentChooser("jComboBoxCurrency"));
        combo.setSelectedItem("USD");
        //save
        new JButtonOperator(boAccounts, "Save").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // TRADE on CCP
        System.out.println("capture trade");
        new Action("Trades|Asset Trade", null).perform();
        TopComponentOperator tradeTC=new TopComponentOperator("Asset Trade");
        combo=new JComboBoxOperator(tradeTC, new NameComponentChooser("jComboBoxInternalCounterparty"));
        combo.setSelectedItem("TEST PORTFOLIO"); // set role issuer
        //set counterparty
        new JButtonOperator(tradeTC, "...").push();
        entityFinder=new DialogOperator("Entity Finder");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();
        // set product
        new JButtonOperator(tradeTC, "Find").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        new JButtonOperator(assetFinder, "Find").push();
        new JTableOperator(assetFinder).selectCell(0, 0);
        new JButtonOperator(assetFinder, "OK").push();
        //fill other fields
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jFormattedTextFieldQuantity"));
        field.setText("1000000");
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jFormattedTextFieldPrice"));
        field.setText("2");
        JComponentOperator settleDate = new JComponentOperator(tradeTC,new NameComponentChooser("jFormattedTextFieldSettleDate"));
        settleDate.transferFocus();
        for(i=0;i<2;i++){
            settleDate.pushKey(KeyEvent.VK_DOWN);
        }
        new JButtonOperator(tradeTC, "Save").push();
        DialogOperator ccpConfirm=new DialogOperator("Set Default CCP");
        new JButtonOperator(ccpConfirm, "Yes").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        field=new JTextFieldOperator(tradeTC,new NameComponentChooser("jTextFieldTradeId"));
        if (!field.getText().isEmpty()){
            tradeId=new Integer(field.getText());
        }

        // COLLAT MANAGER
        System.out.println("open collateral manager");
        new Action("Monitoring|Collateral Manager", null).perform();
        TopComponentOperator collatMgr=new TopComponentOperator("Collateral Manager");
        combo=new JComboBoxOperator(collatMgr, new NameComponentChooser("ccpComboBox"));
        combo.setSelectedItem("TEST CCP");
        combo=new JComboBoxOperator(collatMgr, new NameComponentChooser("cptyRoleComboBox"));
        combo.setSelectedItem("InternalCounterparty");
        combo=new JComboBoxOperator(collatMgr, new NameComponentChooser("internalCounterpartyComboBox"));
        combo.setSelectedItem("TEST PORTFOLIO");
        new JButtonOperator(collatMgr, "Run").push();

        //checks that the table is filled
        System.out.println("check result");
        JTableOperator report=new JTableOperator(collatMgr,new NameComponentChooser("ReportTable"));
        assertTrue("No row found",report.getRowCount()>0);

        report.selectCell(0, 0);

        report.clickForPopup(58, 4);

        JPopupMenuOperator popup=new JPopupMenuOperator();
        JMenuItemOperator menu=new JMenuItemOperator(popup);
        menu.pushNoBlock();

        ok=new DialogOperator("Margin Calls Created");
        new JButtonOperator(ok, "OK").push();

    }

    /** Called after every test case. */
    @Override
    public void tearDown() {
        // CLEAN DATA
        System.out.println("clean data");
        new Action("Admin|Admin", null).perform();
        JTextFieldOperator field;
        DialogOperator ok;
        System.out.println("delete trade");
        TopComponentOperator admin=new TopComponentOperator("Admin");
        if (tradeId!=null){
            field=new JTextFieldOperator(admin,new NameComponentChooser("jTextFieldTradeId"));
            field.setText(tradeId.toString());
            new JButtonOperator(admin, "Delete trade").push();
            ok=new DialogOperator("Message");
            new JButtonOperator(ok, "OK").push();
        }
        // delete legal entity
        System.out.println("delete ccp");
        if (ccpLegalEntity!=null){
            ccpLegalEntity.makeComponentVisible();
            new JButtonOperator(ccpLegalEntity, "Delete").push();
            ok=new DialogOperator("Confirm");
            new JButtonOperator(ok, "OK").push();
        }
        if (portfolio!=null){
            portfolio.makeComponentVisible();
            //first delete positions
            System.out.println("delete positions");
            JLabelOperator label=new JLabelOperator(portfolio,new NameComponentChooser("jLabelId"));
            String ptfId=label.getText();
            admin.makeComponentVisible();
            field=new JTextFieldOperator(admin,new NameComponentChooser("jTextFieldPositionId"));
            field.setText(ptfId.toString());
            new JButtonOperator(admin, "Delete Portfolio's Positions").push();
            //then portfolio
            System.out.println("delete portfolio");
            portfolio.makeComponentVisible();
            new JButtonOperator(portfolio, "Delete").push();
            ok=new DialogOperator("Confirm");
            new JButtonOperator(ok, "OK").push();
        }
        System.out.println("bye");
    }
}


