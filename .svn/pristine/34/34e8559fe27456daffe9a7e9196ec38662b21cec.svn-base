package org.gaia.dao.utils;

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
import org.gaia.domain.utils.StringUtils;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.RatingsAccessor;
import org.gaia.dao.trades.ProductReferenceTypeAccessor;
import org.gaia.dao.utils.IntrospectTree.CompareNodes;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.trades.ProductReferenceType;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.NotAccessibleField;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Benjamin Frerejean
 */
public class IntrospectTree implements Serializable {

    private static final Logger logger = Logger.getLogger(IntrospectTree.class);
    private final String objectType;

    public IntrospectTree(Class objectType) {
        this.objectType = objectType.getName();
    }

    public IntrospectTree() {
        this.objectType = Trade.class.getName();
    }

    public IntrospectNode getClassTree(IntrospectNode root, boolean isGUI) {
        Class clazz;
        try {
            if (root == null) {
                root = new ClassNode("", 0, "");
                clazz = Class.forName(objectType);
                root.setResultType(clazz.getName());
            } else {
                clazz = getClass(Class.forName(objectType), root.getUserObject().toString());
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (!field.getName().equals("serialVersionUID")) {
                    Class fieldClass = field.getType();
                    if (fieldClass.equals(String.class)
                            || fieldClass.equals(Integer.class)
                            || fieldClass.equals(Double.class)
                            || fieldClass.equals(Boolean.class)
                            || fieldClass.equals(Short.class)
                            || fieldClass.equals(Date.class)
                            || fieldClass.equals(Long.class)
                            || fieldClass.equals(short.class)
                            || fieldClass.equals(int.class)
                            || fieldClass.equals(BigDecimal.class)) {

                        FieldNode node = new FieldNode(root.getUserObject().toString() + "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1), root.getMyLevel(), "");
                        node.setResultType(field.getType().getName());
                        node.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
                        node.setIsConversion(Boolean.FALSE);
                        root.add(node);

                    } else if (fieldClass.toString().substring(fieldClass.toString().lastIndexOf(StringUtils.DOT) + 1).equals("Set")
                            || fieldClass.toString().substring(fieldClass.toString().lastIndexOf(StringUtils.DOT) + 1).equals("List")
                            || fieldClass.toString().substring(fieldClass.toString().lastIndexOf(StringUtils.DOT) + 1).equals("Collection")) {
                        NotAccessibleField annotation = (NotAccessibleField) field.getAnnotation(NotAccessibleField.class);
                        if ((annotation == null && root.getMyLevel() < 4) || (annotation != null && root.getMyLevel() < annotation.level())) {
                            switch (field.getName()) {
                                case "productReferences": {
                                    if (!root.getUserObject().toString().endsWith("Leg/")) { // useless on sub products
                                        ArrayList<ProductReferenceType> productReferenceTypes;
                                        if (isGUI) {
                                            productReferenceTypes = (ArrayList) DAOCallerAgent.callMethod(ProductReferenceTypeAccessor.class, ProductReferenceTypeAccessor.GET_ALL_PRODUCT_REFERENCE_TYPES);
                                        } else {
                                            productReferenceTypes = (ArrayList) ProductReferenceTypeAccessor.getAllProductReferenceTypes();
                                        }
                                        for (ProductReferenceType productReferenceType : productReferenceTypes) {
                                            FieldNode node = new FieldNode(root.getUserObject().toString() + "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length() - 1), root.getMyLevel(), productReferenceType.getReferenceType());
                                            node.setResultType(String.class.getName());
                                            node.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
                                            node.setIsConversion(Boolean.FALSE);
                                            root.add(node);
                                        }
                                    }
                                    break;
                                }
                                case "ratings": {
                                    ArrayList<String> agencies;
                                    if (isGUI) {
                                        agencies = (ArrayList) DAOCallerAgent.callMethod(RatingsAccessor.class, RatingsAccessor.GET_AGENCIES);
                                    } else {
                                        agencies = (ArrayList) RatingsAccessor.getAgencies();
                                    }
                                    for (String agency : agencies) {
                                        FieldNode node = new FieldNode(root.getUserObject().toString() + "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1, field.getName().length() - 1), root.getMyLevel(), agency);
                                        node.setResultType(String.class.getName());
                                        node.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
                                        node.setIsConversion(Boolean.FALSE);
                                        root.add(node);
                                    }
                                    break;
                                }
                                case "productComponents": {
                                    break;
                                }
                            }
                        }

                    } else {
                        NotAccessibleField annotation = (NotAccessibleField) field.getAnnotation(NotAccessibleField.class);
                        if ((annotation == null && root.getMyLevel() < 3) || (annotation != null && root.getMyLevel() < annotation.level())) {
                            String userObject = root.getUserObject().toString();
                            userObject = userObject + "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1) + "/";
                            ClassNode node = new ClassNode(userObject, root.getMyLevel() + 1, "");
                            node = (ClassNode) getClassTree(node, isGUI);
                            node.setResultType(fieldClass.getName());
                            node.setColumnType(TemplateColumnItem.COLUMN_STANDARD);
                            root.add(node);
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            logger.error("error  in a getClassTree " + StringUtils.formatErrorMessage(e));
        }
        arrange(root);
        return root;
    }

    public static Class getClass(Class o, String object) {
        Class ret = o;
        String get = object;
        if (get == null) {
            get = StringUtils.EMPTY_STRING;
        }
        try {
            while (get.indexOf("/") >= 0) {
                if (ret != null) {
                    o = ret;
                }
                String s = get.substring(0, get.indexOf("/"));
                if (o != null) {
                    if (!s.equals("getPositionHistory")) {
                        Method mget = o.getMethod(s);
                        ret = mget.getReturnType();
                    } else {
                        Method mget = o.getMethod(s, Date.class);
                        ret = mget.getReturnType();
                        get = StringUtils.EMPTY_STRING;
                    }
                }
                get = get.substring(get.indexOf("/") + 1);
            }
        } catch (NoSuchMethodException | SecurityException e) {
            logger.error("error  in a getClass " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    private void arrange(IntrospectNode root) {
        IntrospectNode sorted = new ClassNode(root.getUserObject(), 0, root.getParam());
        List<IntrospectNode> classes = new ArrayList<>();
        List<IntrospectNode> fields = new ArrayList<>();

        for (int i = 0; i < root.getChildCount(); i++) {
            IntrospectNode node = (IntrospectNode) root.getChildAt(i);
            if (node.isLeaf()) {
                fields.add(node);
            } else {
                arrange(node);
                classes.add(node);
            }
        }

        Collections.sort(classes, new CompareNodes());
        for (int i = 0; i < classes.size(); i++) {
            sorted.add((IntrospectNode) classes.get(i));
        }

        Collections.sort(fields, new CompareNodes());
        for (int i = 0; i < fields.size(); i++) {
            sorted.add((IntrospectNode) fields.get(i));
        }

        while (sorted.getChildCount() > 0) {
            root.add((IntrospectNode) sorted.getChildAt(0));
        }
    }

    class CompareNodes implements Comparator<IntrospectNode> {

        @Override
        public int compare(IntrospectNode s1, IntrospectNode s2) {
            /**
             * descending sort
             */
            return s1.toString().compareTo(s2.toString());
        }
    }

    public static void setColumnTypes(IntrospectNode root, String columnType) {
        for (int i = 0; i < root.getChildCount(); i++) {
            IntrospectNode node = (IntrospectNode) root.getChildAt(i);
            if (node.isLeaf()) {
                node.setColumnType(columnType);
                node.setColumnType(TemplateColumnItem.COLUMN_CUSTOM);
            } else {
                setColumnTypes(node, columnType);
            }
        }
    }

    /**
     * explored node
     */
    public abstract class IntrospectNode extends DefaultMutableTreeNode implements Serializable {

        private final int level;
        private String param;
        private String resultType;
        private String columnType;
        private Boolean isConversion;
        private Boolean isFirstDate;
        private Boolean isEvol;

        public String getColumnType() {
            return columnType;
        }

        public void setColumnType(String columnType) {
            this.columnType = columnType;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public Boolean isConversion() {
            return isConversion;
        }

        public void setIsConversion(Boolean isConversion) {
            this.isConversion = isConversion;
        }

        public Boolean getIsFirstDate() {
            return isFirstDate;
        }

        public void setIsFirstDate(Boolean isFirstDate) {
            this.isFirstDate = isFirstDate;
        }

        public Boolean getIsEvol() {
            return isEvol;
        }

        public void setIsEvol(Boolean isEvol) {
            this.isEvol = isEvol;
        }

        public IntrospectNode(Object data, int level, String param) {
            super(data);
            this.level = level;
            this.param = param;

            if (data == null) {
                throw new IllegalArgumentException("Node data cannot be null");
            }
        }

        public abstract String getField();

        public int getMyLevel() {
            return level;
        }

        public Object getData() {
            return getUserObject();
        }

        public String getParam() {
            return param;
        }

        public void setParam(String param) {
            this.param = param;
        }
    }

    /**
     * class to build node
     */
    public class ClassNode extends IntrospectNode {

        public ClassNode(Object data, int level, String getters) {
            super(data, level, getters);
            if (data == null) {
                throw new IllegalArgumentException("Node data cannot be null");
            }
        }

        @Override
        public String getField() {
            if (userObject!=null && userObject instanceof Class){
                Class cl=(Class) userObject;
                if (cl!=null){
                    return cl.getName();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }

        @Override
        public String toString() {
            String str = super.toString();
            if (!str.equals("")) {
                str = super.toString().substring(0, super.toString().length() - 1);
                str = str.substring(str.lastIndexOf("/") + 4);
            } else {
                str = IntrospectTree.this.objectType; //"Trade";
                if (str != null) {
                    str = str.substring(str.lastIndexOf(StringUtils.DOT) + 1);
                }
            }
            return str;
        }
    }

    public class FieldNode extends IntrospectNode implements Serializable {

        public FieldNode(Object data, int level, String param) {
            super(data, level, param);
            if (data == null) {
                throw new IllegalArgumentException("Node data cannot be null");
            }
        }

        @Override
        public String getField() {
            return userObject.toString();//(Method) userObject).getName();
        }

        @Override
        public TreeNode getFirstChild() {
            if (children != null) {
                return super.getFirstChild();
            }
            return null;
        }

        @Override
        public TreeNode getLastChild() {
            if (children != null) {
                return super.getLastChild();
            }
            return null;
        }

        @Override
        public String toString() {
            String s = super.toString();
            if (s.lastIndexOf("/") + 4 < s.length()) {
                s = s.substring(s.lastIndexOf("/") + 4);
                // show param if not a measure (because in measure param = list of underlying id)
                if (!this.getParam().equals("") && !this.getColumnType().equals(TemplateColumnItem.COLUMN_MEASURE)) {
                    s = s + StringUtils.DOT + this.getParam();
                }
            }
            return s;
        }
    }
}
