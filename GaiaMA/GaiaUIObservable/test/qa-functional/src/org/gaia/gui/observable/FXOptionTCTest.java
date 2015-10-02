package org.gaia.gui.observable;

import java.awt.event.KeyEvent;
import junit.framework.Test;
import org.gaia.dao.trades.TradeAccessor;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.jdesktop.swingx.treetable.TreeTableNode;
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
 * @author Benjamin Frerejean
 */
public class FXOptionTCTest extends JellyTestCase{

    Integer productId;
    Integer tradeId;

    public FXOptionTCTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        Configuration testConfig = NbModuleSuite.createConfiguration(FXOptionTCTest.class);
        testConfig = testConfig.addTest("testTradeAndExercise");
        testConfig = testConfig.clusters(".*").enableModules(".*");
        return testConfig.suite();
    }

    /** Called before every test case. */
    @Override
    public void setUp() {
        System.out.println("########  "+getName()+"  #######");
    }

    public void testTradeAndExercise(){
        System.out.println("open screen");

        new Action("Trades|FX|FX Option", null).perform();
        TopComponentOperator fxOption=new TopComponentOperator("FX Option");

        // CREATE FX OPTION
        // Fill the fields

        JComboBoxOperator combo=new JComboBoxOperator(fxOption, new NameComponentChooser("jComboBoxInternalCounterparty"));
        combo.setSelectedIndex(1);

        new JButtonOperator(fxOption, "...").push();
        DialogOperator entityFinder=new DialogOperator("Entity Finder");
        new JButtonOperator(entityFinder, "Find").push();
        new JTableOperator (entityFinder).selectCell(0, 0);
        new JButtonOperator(entityFinder, "OK").push();

        combo=new JComboBoxOperator(fxOption, new NameComponentChooser("ccyPairComboBox"));
        combo.setSelectedItem("EUR/USD");

        JTextFieldOperator field=new JTextFieldOperator(fxOption,new NameComponentChooser("jFormattedTextFieldQuantity"));
        field.setText("1000000");
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("jFormattedTextFieldStrike"));
        field.setText("1.3");
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("jFormattedTextFieldConvertedAmount"));
        field.setText("1300000");

        JComponentOperator dateComponent = new JComponentOperator(fxOption,new NameComponentChooser("jDateChooserMaturityDate"));
        dateComponent.transferFocus();
        for(int i=0;i<365;i++ ){
            dateComponent.pushKey(KeyEvent.VK_UP);
        }
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("priceFormattedTextField"));
        field.setText("2");
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("jFormattedTextFieldPremium"));
        field.setText("20000");

        new JButtonOperator(fxOption, "Save").push();
        DialogOperator ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("jTextFieldTradeId"));
        if (!field.getText().isEmpty()){
            tradeId=new Integer(field.getText());
        } else {
            assertNull("trade not stored",tradeId);
        }

        // MARKET QUOTES
        new Action("Market Data|Market Quotes", null).perform();
        TopComponentOperator quotes=new TopComponentOperator("Market Quotes");
        new JButtonOperator(quotes, "Find").push();
        DialogOperator assetFinder=new DialogOperator("Asset Finder");
        field=new JTextFieldOperator(assetFinder,new NameComponentChooser("jTextFieldNameLike"));
        field.setText("EUR/USD");
        new JButtonOperator(assetFinder, "Find").push();
        new JTableOperator (assetFinder).selectCell(0, 0);
        new JButtonOperator(assetFinder, "OK").push();
        dateComponent = new JComponentOperator(quotes,new NameComponentChooser("jDateChooserStartDate"));
        dateComponent.transferFocus();
        for(int i=0;i<365;i++ ){
            dateComponent.pushKey(KeyEvent.VK_UP);
        }
        dateComponent = new JComponentOperator(quotes,new NameComponentChooser("jDateChooserEndDate"));
        dateComponent.transferFocus();
        for(int i=0;i<365;i++ ){
            dateComponent.pushKey(KeyEvent.VK_UP);
        }
        new JButtonOperator(quotes, "Load").push();
        JTableOperator quoteTable=new JTableOperator(quotes,new NameComponentChooser("jTableQuotes"));
        quoteTable.setValueAt("1.4", 0, 7);
        new JButtonOperator(quotes, "Save").push();
        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();

        // EXERCISE
        new Action("Trades|FX|FX Option Exercise", null).perform();
        FixingTopComponentOperator exerciseOp=new FixingTopComponentOperator("FX Option Exercise");
        dateComponent = new JComponentOperator(exerciseOp,new NameComponentChooser("fixingDateChooser"));
        dateComponent.transferFocus();
        for(int i=0;i<365;i++ ){
            dateComponent.pushKey(KeyEvent.VK_UP);
        }
        new JButtonOperator(exerciseOp, "Load").push();
        new JButtonOperator(exerciseOp, "+").push();

        System.out.println("check result");
        JTableOperator result=new JTableOperator(exerciseOp);
        assertTrue("No row found",result.getRowCount()>0);

        SortableTreeTableModel model=exerciseOp.getModel();
        TreeTableNode root=model.getRoot();
        model.setValueAt(Boolean.TRUE, root.getChildAt(0), 7);
//        model.setValueAt(Boolean.TRUE, root.getChildAt(0).getChildAt(0), 7);

        new EventTool().waitNoEvent(1000);
        new JButtonOperator(exerciseOp, "Exercise").push();
        ok=new DialogOperator("Exercise Options");
        new JButtonOperator(ok, "Yes").push();

        ok=new DialogOperator("Message");
        new JButtonOperator(ok, "OK").push();
        //check expiry
        fxOption.makeComponentVisible();
        new JButtonOperator(fxOption, "Load").push();
        field=new JTextFieldOperator(fxOption,new NameComponentChooser("lifeCycleStatusTextField"));
        String status=field.getText();
        assertEquals("Option Not exercised",status,TradeAccessor.TradeLifeCycleStatus.EXERCISED.name);


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
        System.out.println("bye");
    }

    class FixingTopComponentOperator extends TopComponentOperator{


        public FixingTopComponentOperator(String name){
            super(name);
        }

        public SortableTreeTableModel getModel(){
            SortableTreeTableModel ret=null;
            try {
                FixingTopComponent tc=(FixingTopComponent)TopComponentOperator.waitTopComponent(this.getName(), 0);
                return tc.model;
            } catch (Exception e){
            }
            return ret;
        }
    }
}
