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
package org.gaia.gui.reports;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.observables.AbstractObservable;
import org.gaia.dao.pricing.IPricerMeasure;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.reports.customColumns.CustomColumnAccessor;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.trades.ProductTypeUtil.ProductType;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionConfiguration;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.Flow;
import org.gaia.domain.trades.Trade;
import org.gaia.gui.common.GaiaPanel;
import org.gaia.gui.utils.GUIUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;

/**
 *
 * @author Jawhar
 */
public class FieldChooser extends GaiaPanel {

    private static ReportTemplate template;
    public static final String UNDERLYNIG_SEPARATOR = "@";

    /**
     * Creates new form FieldChooser
     *
     * @param objectType
     * @param _template
     * @param returnTypeConstraint
     */
    public FieldChooser(Class objectType, ReportTemplate _template, String returnTypeConstraint) {
        initComponents();
        template = _template;

        IntrospectTree introspectTree = new IntrospectTree(objectType);
        /**
         * fields
         */
        IntrospectTree.IntrospectNode root = introspectTree.getClassTree(null, true);

        /**
         * when on a position, we filter depending on the position configuration
         */
        if (objectType.equals(Position.class) || objectType.equals(Flow.class)) {
            if (template != null && template.getPositionConfiguration() != null) {
                PositionConfiguration positionConfiguration = template.getPositionConfiguration();
                Map positionConfigurationMap = positionConfiguration.getPositionConfigurationItemMap();
                Enumeration children = root.children();
                List<IntrospectTree.IntrospectNode> toRemove = new ArrayList();
                while (children.hasMoreElements()) {
                    IntrospectTree.IntrospectNode node = (IntrospectTree.IntrospectNode) children.nextElement();

                    if (node.toString().equalsIgnoreCase("Ccp") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CCP.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("ClearingMember") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_CLEARING_MEMBER.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("Counterparty") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_COUNTERPARTY.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("InternalCounterparty") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_INTERNAL_COUNTERPARTY.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("IsCollateral") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_IS_COLLATERAL.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("Trader") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADER.dbField) == null) {
                        toRemove.add(node);
                    } else if (node.toString().equalsIgnoreCase("TradeType") && positionConfigurationMap.get(PositionConfiguration.PositionAggregationCriteria.AGGREGATION_BY_TRADE_TYPE.dbField) == null) {
                        toRemove.add(node);
                    }
                }
                for (IntrospectTree.IntrospectNode node : toRemove) {
                    root.remove(node);
                }
            }
        }
        /**
         * custom columns
         */
        IntrospectTree.IntrospectNode customColumns = (IntrospectTree.IntrospectNode) DAOCallerAgent.callMethodWithXMLSerialization(CustomColumnAccessor.class,
                CustomColumnAccessor.GET_CUSTOM_COLUMNS_TREE, objectType, null);
        root.add(customColumns);
        /**
         * measures
         */
        if (objectType.equals(Position.class) || objectType.equals(Trade.class)) {
            IntrospectTree.IntrospectNode measures = (IntrospectTree.IntrospectNode) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class,
                    MeasuresAccessor.GET_MEASURES_TREE);
            root.add(measures);
        }
        /**
         * if there is a constraint on return type, filter:
         */
        if (!StringUtils.isEmptyString(returnTypeConstraint)) {
            filterOnType(root, returnTypeConstraint);
        }

        /**
         * set
         */
        jTree1.setModel(new DefaultTreeModel(root));
        jTree1.expandPath(new TreePath(root.getPath()));
    }

    FieldChooser(String objectType, ReportTemplate template, Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void filterOnType(IntrospectTree.IntrospectNode node, String stype) {
        if (!node.isLeaf()) {
            for (int i = 0; i < node.getChildCount(); i++) {
                IntrospectTree.IntrospectNode n = (IntrospectTree.IntrospectNode) node.getChildAt(i);
                if (n.isLeaf()) {
                    if (n.getResultType() != null && !n.getResultType().equals(stype)) {
                        node.remove(i);
                        i--;
                    }
                } else {
                    filterOnType(n, stype);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("root");
        jTree1.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTree1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTree1MousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTree1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTree1MousePressed
        /**
         * special to handle 2D measures : quotes / delta...
         */
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree1.getLastSelectedPathComponent();
        if (node != null && node.getUserObject() != null) {
            String content = node.getUserObject().toString().replaceAll("get", "");

            List<IPricerMeasure> listMeasures = (List) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class, MeasuresAccessor.GET_ALL_MEASURES);
            for (int i = 0; i < listMeasures.size(); i++) {
                if (listMeasures.get(i).getDimension() == 2) {
                    if (content.equals(listMeasures.get(i).getName())) {
                        // open the 2D measures selector window
                        // to select the underlying
                        if (!content.equalsIgnoreCase(MeasuresAccessor.Measure.SINGLE_CREDIT_LINE.getName())) {
                            //standard case
                            List<ProductType> productTypes = ProductTypeUtil.loadProductTypes();
                            AssetSelector selector = new AssetSelector(productTypes, template, content);

                            NotifyDescriptor nd = new NotifyDescriptor(selector, "Asset Selector", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.PLAIN_MESSAGE, null, NotifyDescriptor.OK_OPTION);

                            if (DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {

                                String observable = selector.getObservableType();
                                if (observable != null) {
                                    IntrospectTree.IntrospectNode fieldNode = (IntrospectTree.IntrospectNode) node;
                                    fieldNode.setParam(observable);
                                } else {
                                    Map<Integer, String> underlyingids = selector.getSelectedUnderlyings();
                                    IntrospectTree.IntrospectNode fieldNode = (IntrospectTree.IntrospectNode) node;
                                    String param = StringUtils.EMPTY_STRING;
                                    List<IntrospectTree.FieldNode> children = new ArrayList();
                                    for (Entry e : underlyingids.entrySet()) {
                                        param += e.getKey() + StringUtils.COMMA + e.getValue() + UNDERLYNIG_SEPARATOR;
                                        IntrospectTree.FieldNode child = (IntrospectTree.FieldNode) fieldNode.clone();
                                        child.setUserObject("get" + content + StringUtils.DOT + e.getValue());
                                        children.add(child);
                                    }
                                    fieldNode.setParam(param);
                                    node = (DefaultMutableTreeNode) fieldNode;
                                    for (IntrospectTree.FieldNode child : children) {
                                        node.add((DefaultMutableTreeNode) child);
                                    }
                                }
                            }

                        } else { // special case for single credit line
                            // here we look for legal entities
                            LegalEntity legalEntity = GUIUtils.findCounterParty();
                            if (legalEntity != null) {
                                IntrospectTree.IntrospectNode fieldNode = (IntrospectTree.IntrospectNode) node;
                                String param = legalEntity.getLegalEntityId() + StringUtils.COMMA + legalEntity.getShortName();
                                IntrospectTree.FieldNode child = (IntrospectTree.FieldNode) fieldNode.clone();
                                child.setUserObject("get" + content + StringUtils.DOT + legalEntity.getShortName());
                                fieldNode.setParam(param);
                                node = (DefaultMutableTreeNode) fieldNode;
                                node.add(child);
                            }
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_jTree1MousePressed

    /**
     * add list to Template Column Item
     *
     * @return
     */
    public List<TemplateColumnItem> getTemplateColumnItem() {
        List<TemplateColumnItem> itemList = new ArrayList();
        TemplateColumnItem templateColumnItem;
        TreePath[] paths = jTree1.getSelectionPaths();
        if (paths != null) {
            for (TreePath path : paths) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
                if (node != null) {
                    IPricerMeasure measure = (IPricerMeasure) DAOCallerAgent.callMethodWithXMLSerialization(MeasuresAccessor.class, MeasuresAccessor.GET_MEASURE_BY_NAME, node.toString());
                    if (measure == null || measure.getDimension() == 1 || path.getPathComponent(1).toString().equalsIgnoreCase("CustomColumns")) {
                        if (node.isLeaf()) {
                            IntrospectTree.IntrospectNode n = (IntrospectTree.IntrospectNode) node;
                            templateColumnItem = new TemplateColumnItem();
                            templateColumnItem.setName(node.getUserObject().toString().replaceAll("get", "").replace('/', '.'));
                            templateColumnItem.setGetter(node.getUserObject().toString());
                            templateColumnItem.setParam(n.getParam());
                            templateColumnItem.setReturnType(n.getResultType());
                            templateColumnItem.setDisplayName("");
                            templateColumnItem.setColumnType(n.getColumnType());
                            itemList.add(templateColumnItem);
                        }
                    } else if (measure.getDimension() == 2) {
                        IntrospectTree.IntrospectNode treenode = (IntrospectTree.IntrospectNode) node;
                        String param = treenode.getParam();
                        String fieldName = node.getUserObject().toString().replaceAll("get", "").replace('/', '.');
                        if (AbstractObservable.ObservableType.getObservableByName(param)!=null){
                            templateColumnItem = new TemplateColumnItem();
                            templateColumnItem.setParam(param);
                            templateColumnItem.setName(fieldName + StringUtils.DOT + param);
                            templateColumnItem.setDisplayName(fieldName + "<" + param + ">");
                            templateColumnItem.setReturnType(treenode.getResultType());
                            templateColumnItem.setColumnType(treenode.getColumnType());
                            templateColumnItem.setGetter(param);
                            itemList.add(templateColumnItem);
                        } else if (!param.isEmpty()) {
                            String[] params = param.split(UNDERLYNIG_SEPARATOR);
                            for (String param_ : params) {
                                if (param_.indexOf(StringUtils.COMMA)>=0){
                                    String id = param_.substring(0, param_.indexOf(StringUtils.COMMA));
                                    String name = param_.substring(param_.indexOf(StringUtils.COMMA) + 1);
                                    templateColumnItem = new TemplateColumnItem();
                                    templateColumnItem.setParam(id);
                                    templateColumnItem.setName(fieldName + StringUtils.DOT + name);
                                    templateColumnItem.setDisplayName(fieldName + "<" + name + ">");
                                    templateColumnItem.setReturnType(treenode.getResultType());
                                    templateColumnItem.setColumnType(treenode.getColumnType());
                                    templateColumnItem.setGetter(name);
                                    itemList.add(templateColumnItem);
                                }
                            }
                        }
                    }
                }
            }
        }
        return itemList;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTree jTree1;
    // End of variables declaration//GEN-END:variables
}
