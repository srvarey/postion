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

/**
 *
 * @author Jawhar Kamoun
 */
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.gaia.dao.reports.AbstractSortableTreeTableNode;
import org.gaia.dao.reports.PositionTree;
import org.gaia.dao.reports.ReportBuilder;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.utils.NumberUtils;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.gui.utils.SortableTreeTable;
import org.gaia.gui.utils.SortableTreeTableModel;
import org.openide.util.Exceptions;

public class ExcelReportExporter {

    private static final Logger logger = Logger.getLogger(ExcelReportExporter.class);
    private static final List<Point> groupList = new ArrayList<>();
    private static final List<AbstractSortableTreeTableNode> allNodeList = new ArrayList<>();
    private static final List<AbstractSortableTreeTableNode> nodeList = new ArrayList<>();
    private static final String SEPARATOR_FILE_NAME = "_";

    /**
     * generate file EXCEL
     *
     * @param table
     * @param template
     */
    public static void generateExcel(SortableTreeTable table, ReportTemplate template) {
        FileOutputStream fileOut = null;
        XSSFWorkbook wb = new XSSFWorkbook();
        try {
            String excelFilename = generateFileName(template);
            fileOut = new FileOutputStream(excelFilename);

            List<TemplateColumnItem> items=ReportBuilder.orderColumns(template.getTemplateColumnItems());
            TemplateColumnItem item;
            int colMax = table.getColumnModel().getColumnCount();
            if (items.size() < colMax) {
                colMax = items.size();
            }

            XSSFSheet bomSheet = (XSSFSheet) wb.createSheet(template.getTemplateName());

            XSSFRow headerRow = (XSSFRow) bomSheet.createRow(0);
            XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
            Font font = wb.createFont();
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("Tahoma");
            headerStyle.setFont(font);
            headerStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            headerStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            headerStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
            headerStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            for (int i = 0; i < colMax; i++) {
                XSSFCell cell = (XSSFCell) headerRow.createCell(i);
                cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                cell.setCellValue(table.getColumnName(i));
                cell.setCellStyle(headerStyle);
            }

            XSSFCellStyle cellStyle = (XSSFCellStyle) wb.createCellStyle();

            font = wb.createFont();
            font.setFontName("Tahoma");
            cellStyle.setFont(font);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            for (int j = 0; j < table.getRowCount(); j++) {
                XSSFRow row = (XSSFRow) bomSheet.createRow(j + 1);
                for (int i = 0; i < colMax; i++) {
                    XSSFCell cell = (XSSFCell) row.createCell(i);
                    cell.setCellStyle(cellStyle);
                    Object value = table.getValueAt(j, i);
                    item = (TemplateColumnItem) items.get(i);
                    if (value != null && !value.equals(StringUtils.EMPTY_STRING)) {
                        Class<?> clazz = Class.forName(item.getReturnType());

                        //used for Snapshot Export
                        if (clazz == String.class && NumberUtils.isInteger(value.toString())) {
                            clazz = Integer.class;
                        } else if (clazz == String.class && NumberUtils.isNumber(value.toString())) {
                            clazz = BigDecimal.class;
                        }

                        if (short.class.isAssignableFrom(clazz) || Short.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(((Short) value).intValue());
                        } else if (int.class.isAssignableFrom(clazz)
                                || Integer.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(Integer.parseInt(value.toString()));
                        } else if (long.class.isAssignableFrom(clazz)
                                || Long.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(((Long) value).intValue());
                        } else if (float.class.isAssignableFrom(clazz)
                                || Float.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(((Float) value).doubleValue());
                        } else if (BigDecimal.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(Double.parseDouble(value.toString()));
                        } else if (double.class.isAssignableFrom(clazz)
                                || Double.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue((Double) value);
                        } else if (Date.class.isAssignableFrom(clazz)) {
                            cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);
                            cell.setCellValue(HSSFDateUtil.getExcelDate((java.sql.Date) value));
                        } else {
                            cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                            cell.setCellValue(value.toString());
                        }
                    }
                }

            }

            for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
                bomSheet.autoSizeColumn(i);
            }

            SortableTreeTableModel model = (SortableTreeTableModel) table.getTreeTableModel();
            AbstractSortableTreeTableNode root = (AbstractSortableTreeTableNode) model.getRoot();
            groupNode(root, bomSheet);
            wb.write(fileOut);
            fileOut.flush();
            fileOut.close();
            nodeList.clear();
            groupList.clear();
            allNodeList.clear();

            openExcel(excelFilename);
        } catch (ClassNotFoundException | IOException ex) {
            Exceptions.printStackTrace(ex);
        } finally {
            try {
                fileOut.close();
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
    }

    private static String generateFileName(ReportTemplate template) {
        StringBuilder builder = new StringBuilder(System.getProperty("user.home"));
        builder.append(File.separator);
        String dateFormatForExport = "ddMMyyyy_HHmmss";
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormatForExport);
        builder.append(ReportUtils.getReportType(template.getObjectTypeClass())).append(SEPARATOR_FILE_NAME);
        builder.append(template.getTemplateName()).append(SEPARATOR_FILE_NAME);
        builder.append(dateFormatter.format(new Date(System.currentTimeMillis())));
        builder.append(".xlsx");

        return builder.toString();
    }

    /**
     * open Excel file
     *
     * @param exportedFile
     */
    public static void openExcel(String exportedFile) {
        try {
            Process p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "assoc", ".xls"});
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String extensionType = input.readLine();
            input.close();
            /**
             * extract type
             */
            if (extensionType == null) {
                logger.error("no office installed ?");
                return;
            }
            String fileType[] = extensionType.split("=");

            p = Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "ftype", fileType[1]});
            input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String fileAssociation = input.readLine();
            /**
             * extract path
             */
            String officePath = fileAssociation.split("=")[1];
            officePath = officePath.substring(0, officePath.lastIndexOf('"') + 1);
            Runtime.getRuntime().exec(new String[]{officePath, "/c", exportedFile});
        } catch (IOException err) {
            logger.error(err);
        }
    }

    /**
     * group Node
     */
    private static void groupNode(AbstractSortableTreeTableNode root, XSSFSheet bomSheet) {
        listGrouppedNode(root);
        appendAllChildrenToList(allNodeList, root, true);
        createMapGroup4();
        for (Point object : groupList) {
            bomSheet.groupRow(object.x, object.y);
            bomSheet.setRowGroupCollapsed(new Integer(2), false);
            bomSheet.setRowSumsBelow(false);
        }
    }

    /**
     * list Groupped Node
     */
    private static void listGrouppedNode(AbstractSortableTreeTableNode root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            AbstractSortableTreeTableNode child = (AbstractSortableTreeTableNode) root.getChildAt(i);
            if (child instanceof PositionTree.AggregNode) {
                nodeList.add(child);
                listGrouppedNode(child);
            }

        }
    }

    /**
     * appendAllChildrenToList
     */
    private static void appendAllChildrenToList(List<AbstractSortableTreeTableNode> nodes, AbstractSortableTreeTableNode parent, boolean getChildChildren) {
        Enumeration children = parent.children();
        if (children != null) {
            while (children.hasMoreElements()) {
                AbstractSortableTreeTableNode node = (AbstractSortableTreeTableNode) children.nextElement();
                nodes.add(node);
                if (getChildChildren) {
                    appendAllChildrenToList(nodes, node, getChildChildren);
                }
            }
        }
    }

    /**
     * create Map Group
     */
    private static void createMapGroup4() {
        int begin;
        for (int i = 0; i < nodeList.size(); i++) {
            AbstractSortableTreeTableNode node = nodeList.get(i);
            begin = 2 + allNodeList.indexOf(node);
            List<AbstractSortableTreeTableNode> childrenListCurrenrNode = new ArrayList<>();
            appendAllChildrenToList(childrenListCurrenrNode, node, true);
            Point p = new Point(begin, begin + childrenListCurrenrNode.size() - 1);
            groupList.add(p);
            childrenListCurrenrNode.clear();
        }
    }
}
