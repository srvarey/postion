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
package org.gaia.dao.utils;

import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.ImportMap;
import org.gaia.domain.utils.ImportMapField;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author Benjamin Frerejean
 */
public class ImportMapAccessor {

    private static final Logger logger = Logger.getLogger(ImportMapAccessor.class);
    public static final String IMPORT_FILE = "importFile";

    public static void importFile(String mapName) throws Exception {
        ImportMap map = getImportMap(mapName);
        String file = map.getFilePath();
        try {

            /**
             * import file xml type
             */
            if (map.getFileType().equals("XML")) {
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    Document doc = builder.parse(file);
                    XPathExpression expr;
                    XPathExpression subexpr;

                    /**
                     * Create a XPathFactory
                     */
                    XPathFactory xFactory = XPathFactory.newInstance();

                    /**
                     * Create a XPath object
                     */
                    XPath xpath = xFactory.newXPath();

                    /**
                     * Compile the XPath expression
                     */
                    expr = xpath.compile(map.getObjectPath());
                    /**
                     * Run the query and get a nodeset
                     */
                    Object result = expr.evaluate(doc, XPathConstants.NODESET);

                    /**
                     * Cast the result to a DOM NodeList
                     */
                    NodeList nodes = (NodeList) result;
                    for (int i = 0; i < nodes.getLength(); i++) {
                        Object object;
                        Class clazz = Class.forName(map.getObjectType());
                        object = clazz.newInstance();
                        XPath subxpath = xFactory.newXPath();
                        for (ImportMapField imf : map.getImportMapFieldCollection()) {
                            String s = map.getObjectPath() + "[" + (i + 1) + "]" + imf.getSourceField() + "/text()";
                            subexpr = subxpath.compile(s);
                            Node field = (Node) subexpr.evaluate(doc, XPathConstants.NODE);

                            String get = imf.getGetter();
                            Method mget = clazz.getMethod(get, (Class[]) null);

                            get = get.substring(get.lastIndexOf("/") + 1);
                            String set = "s" + get.substring(1, get.length());
                            Class retclassc = Class.forName(mget.getReturnType().getName());
                            Method mset = clazz.getMethod(set, mget.getReturnType());
                            mset.invoke(object, retclassc.cast(field.getNodeValue()));
                        }
                        HibernateUtil.storeObject((Serializable) object);
                    }

                } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException | ClassNotFoundException | InstantiationException | IllegalAccessException | DOMException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }

        } catch (Exception ex) {
            logger.error("Error " + StringUtils.formatErrorMessage(ex));
        }

    }
    /**
     * return map request
     */
    public static final String GET_IMPORT_MAP = "getImportMap";

    public static ImportMap getImportMap(String name) {
        return (ImportMap) HibernateUtil.getObjectWithQuery("from ImportMap where name='" + name + StringUtils.QUOTE);
    }
    /**
     * return import map
     */
    public static final String GET_IMPORT_MAP_FIELD = "getImportMapField";

    public static ImportMapField getImportMapField(Integer mapFieldId) {
        return (ImportMapField) HibernateUtil.getObjectWithQuery("from ImportMapField where import_map_field_id=" + mapFieldId);
    }
    /**
     * delete import map field
     */
    public static final String DELETE_IMPORT_MAP_FIELD = "deleteImportMapField";

    public static void deleteImportMapField(ImportMapField importMapField) {
        HibernateUtil.deleteObject(importMapField);
    }
    /**
     * store import map field
     */
    public static final String STORE_IMPORT_MAP_FIELD = "storeImportMapField";

    public static void storeImportMapField(ImportMapField importMapField) {
        HibernateUtil.storeObject(importMapField);
    }
    /**
     * store imported map
     */
    public static final String STORE_IMPORT_MAP = "storeImportMap";

    public static void storeImportMap(ImportMap importMap) {
        HibernateUtil.storeObject(importMap);
    }
    /**
     * return All imported map
     */
    public static final String GET_IMPORT_MAPS = "getImportMaps";

    public static List<String> getImportMaps() {
        return (List<String>) HibernateUtil.getStringListWithQuery("select name from ImportMap");
    }
}
