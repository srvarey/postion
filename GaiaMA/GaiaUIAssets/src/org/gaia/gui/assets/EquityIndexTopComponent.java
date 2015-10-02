/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.assets;

import java.awt.HeadlessException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.pricing.DayCountAccessor;
import org.gaia.dao.referentials.FrequencyUtil;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import static org.gaia.dao.trades.ProductTypeUtil.ProductType.EQUITY_INDEX;
import static org.gaia.dao.trades.ProductTypeUtil.ProductType.STOCK;
import org.gaia.dao.utils.DateUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductReference;
import org.gaia.domain.trades.ProductUnderlying;
import org.gaia.domain.trades.Scheduler;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.GUIUtils;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * @author Zakaria Laguel
 */
/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.assets//EquityIndex//EN",autostore = false)
@TopComponent.Description(preferredID = "EquityIndexTopComponent",iconBase="org/gaia/gui/assets/Index.png",persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.assets.EquityIndexTopComponent")
@ActionReference(path = "Menu"+MenuManager.EquityIndexTopComponentMenu, position = MenuManager.EquityIndexTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_EquityIndexAction",preferredID = "EquityIndexTopComponent")
@Messages({
    "CTL_EquityIndexAction=Equity Index",
    "CTL_EquityIndexTopComponent=Equity Index",
    "HINT_EquityIndexTopComponent=This is an Equity Index"
})
public final class EquityIndexTopComponent extends CDSIndexTopComponent {

    private static final Logger logger = Logger.getLogger(EquityIndexTopComponent.class);

    public EquityIndexTopComponent() {
        super();
        setName(Bundle.CTL_EquityIndexTopComponent());
        setToolTipText(Bundle.HINT_EquityIndexTopComponent());
        isIndexEquityWindow();
        MyIndexEquityCellEditorListener listener=new MyIndexEquityCellEditorListener();
        indexTable.getDefaultEditor(Object.class).addCellEditorListener(listener);
    }

    @Override
    public void initContext() {
        try {
            super.initContext();
            jComboBoxType.removeAllItems();
            listTypes = new ArrayList();
            listTypes.add(ProductTypeUtil.ProductType.EQUITY_INDEX);
            if (listTypes != null) {
                for (ProductTypeUtil.ProductType type : listTypes) {
                    jComboBoxType.addItem(type.name);
                }
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    @Override
    public void fillProduct() {
        if (getProduct() == null) {
            setProduct(new Product());
        }
        getProduct().setIsAsset(true);
        try {
            getProduct().setShortName(jTextFieldShortName.getText());
            getProduct().setProductType(jComboBoxType.getSelectedItem().toString());
            getProduct().setNotionalCurrency(jComboBoxCurrency.getSelectedItem().toString());
            getProduct().setComment(jTextAreaComment.getText());
            getProduct().setProductReferences(getProductReferences());
            if (jDateChooserIssueDate.getDate() != null) {
                getProduct().setStartDate(jDateChooserIssueDate.getDate());
            }
            getProduct().setUnderlyingProducts(underlyings);
            /**
             * scheduler
             */
            Scheduler scheduler = getProduct().getScheduler();
            if (scheduler == null) {
                scheduler = new Scheduler();
            }
            scheduler.setFrequency(FrequencyUtil.Frequency.QUARTERLY.name);
            scheduler.setDaycount(DayCountAccessor.DayCount.ACT_360.getName());
            scheduler.setAdjustment(DateUtils.ADJUSTMENT_FOLLOW);
            scheduler.setPaymentLag(0);
            scheduler.setIsPayInArrears(true);
            scheduler.setIsPayLagBusDays(true);
            getProduct().setScheduler(scheduler);
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    @Override
    public void storeProduct() {
        if (getProduct().getNotionalCurrency().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Currency must not be empty.");
        } else if (getProduct()
                .getProductType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Product Type must not be empty.");
        } else if (getProduct()
                .getQuotationType().equals(StringUtils.EMPTY_STRING)) {
            JOptionPane.showMessageDialog(this, "Quotation Type must not be empty.");
        } else {
            try {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class,
                        ProductAccessor.STORE_PRODUCT, getProduct()));
                if (getProduct().getId() != null) {
                    jTextFieldId.setText(getProduct().getId().toString());
                    load(getProduct().getId());
                    JOptionPane.showMessageDialog(this, "Saved with id " + getProduct().getId());
                }
            } catch (HeadlessException ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    @Override
    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    @Override
    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    public class MyIndexEquityCellEditorListener implements CellEditorListener {

        @Override
        public void editingStopped(ChangeEvent e) {
            int row = indexTable.getSelectedRow();
            ProductUnderlying underlying = getUnderlyingFromTable(Integer.parseInt(
                    indexTable.getValueAt(indexTable.getSelectedRow(), model.findColumn("Id")).toString()));
            Object objectValue = indexTable.getValueAt(row, model.findColumn("Weight"));
            String columnvalue = objectValue == null ? StringUtils.EMPTY_STRING : objectValue.toString();

            if (!StringUtils.isEmptyString(columnvalue)) {
                BigDecimal weight = NumberUtils.stringToNumber(columnvalue, rateDecimalFormat);
                underlying.setWeight(weight.divide(NumberUtils.BIGDECIMAL_100, 10, RoundingMode.HALF_UP));
                indexTable.setValueAt(weight, row, model.findColumn("Weight"));
            }

            underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
        }

        @Override
        public void editingCanceled(ChangeEvent e) {
            throw new UnsupportedOperationException("Not supported yet"); //To change body of generated methods, choose Tools | Templates.
        }
    }

    /**
     * load product
     *
     * @param productId
     */
    @Override
    public void load(Integer productId) {
        if (productId != null) {
            try {
                setProduct((Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId));
                List<ProductReference> references = (List) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_REFERENCES, productId);

                getProduct().setProductReferences(references);
                jTextFieldId.setText(productId.toString());
                jTextFieldShortName.setText(getProduct().getShortName());
                jComboBoxType.setSelectedItem(getProduct().getProductType());
                jComboBoxCurrency.setSelectedItem(getProduct().getNotionalCurrency());

                prodrefs = new ArrayList();
                for (ProductReference productReference : getProduct().getProductReferences()) {
                    prodrefs.add(productReference);
                }
                fillIndexTable();
                setDisplayName(getDisplayName());
            } catch (Exception ex) {
                logger.error(StringUtils.formatErrorMessage(ex));
            }
        }
    }

    @Override
    public void loadAction() {
        /**
         * find and load
         *
         */
        List<ProductTypeUtil.ProductType> typeList = ProductTypeUtil.loadTypesByFamilyAndUse(ProductTypeUtil.ProductFamily.EQUITY, ProductTypeUtil.ProductTypeUse.OBSERVABLE);

        listTypes = new ArrayList();

        for (ProductTypeUtil.ProductType type : typeList) {
            if (type.equals(EQUITY_INDEX)) {
                listTypes.add(EQUITY_INDEX);
            }
        }
        assetFinder = new AssetFinder(typeList);
        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE,
                null, NotifyDescriptor.OK_OPTION);

        /**
         * display the dialog
         */
        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            load(productId);
            assetFinder.setVisible(false);
        }
    }

    @Override
    public void addIndex() {

//        List<ProductTypeUtil.ProductType> typeList = ProductTypeUtil.loadListed();
        List<ProductTypeUtil.ProductType> typeList = ProductTypeUtil.loadProductTypes();
        listTypes = new ArrayList();
        for (ProductTypeUtil.ProductType type : typeList) {
            if (type.equals(STOCK)) {
                listTypes.add(STOCK);
            }
        }

        Vector<Object> rowToadd = new Vector<>();
        assetFinder = new AssetFinder(listTypes);

        NotifyDescriptor nd = new NotifyDescriptor(assetFinder, "Asset Finder", NotifyDescriptor.OK_CANCEL_OPTION,
                NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

        if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            Integer productId = assetFinder.getAssetId();
            if (productId != null) {

                Product equityProduct = (Product) DAOCallerAgent.callMethod(ProductAccessor.class, ProductAccessor.GET_PRODUCT_BY_ID, productId);
                if (equityProduct != null) {
                    rowToadd.add(equityProduct.getProductId());
                    String equityStr = equityProduct.getShortName();
                    rowToadd.add(equityStr);
                    rowToadd.add(1.0);
                    model.addRow(rowToadd);
                    getProduct().addUnderlyingWithWeight(equityProduct, BigDecimal.ZERO);
                    underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
                }
            }
        }
    }

    @Override
    public void fillIndexTable() {
        underlyings = new HashSet<>(getProduct().getUnderlyingProducts());
        Vector<Vector<Object>> tableData = new Vector();
        for (ProductUnderlying underlying : underlyings) {
            Vector<Object> rowData = new Vector<>();
            rowData.add(underlying.getUnderlying().getProductId().toString());
            rowData.add(underlying.getUnderlying().getProduct().getShortName());
            rowData.add(underlying.getWeight().multiply(NumberUtils.BIGDECIMAL_100));

            tableData.add(rowData);
            GUIUtils.setNumberEditor(indexTable, 2, sDecimalFormat);
            GUIUtils.setNumberEditor(indexTable, 3, sDecimalFormat);
        }
        model.getDataVector().removeAllElements();
        model.getDataVector().addAll(tableData);
        indexTable.setModel(model);
        model.fireTableDataChanged();
    }
}
