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
package org.gaia.gui.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SortOrder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.table.JTableHeader;
import javax.swing.tree.TreePath;
import org.gaia.dao.reports.AbstractSortableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;
import org.jdesktop.swingx.treetable.MutableTreeTableNode;
import org.jdesktop.swingx.treetable.TreeTableNode;

/**
 *
 * @author User
 */
public class SortableTreeTableModel extends DefaultTreeTableModel implements TreeExpansionListener, Serializable {

    private String sortColumn = null;
    private SortOrder sortOrder = SortOrder.UNSORTED;
    private int columnIndex = -1;
    private List<TreePath> expanded;
    private SortableTreeTable treeTable = null;
    private JTableHeader header = null;
    boolean expanding = false;

    public SortableTreeTableModel() {
    }

    public SortableTreeTableModel(TreeTableNode root) {
        super(root);
    }

    public SortableTreeTableModel(TreeTableNode root, List<?> columnNames) {
        super(root, columnNames);
    }

    // =========================================================== public api
    /**
     * Set name of column to sort. If null, will reset to unsorted. Sort Order
     * will be set ASCENDING<br> Name must exist in the Column Identifiers.
     *
     * @param column - name of column to sort on
     */
    public void setSortColumn(String column) {
        if (column == null) {
            sortColumn = null;
            if (SortOrder.UNSORTED != sortOrder) {
                sortOrder = SortOrder.UNSORTED;
                reset();
            }
        } else {
            toggleSortOrder();
            setKeys(column, sortOrder);
        }
    }

    @Override
    public void setValueAt(Object value, Object node, int column) {
        if (column < 0 || column >= getColumnCount()) {
            throw new IllegalArgumentException("column must be a valid index");
        } else if (node instanceof TreeTableNode) {
            TreeTableNode ttn = (TreeTableNode) node;

            if (column < ttn.getColumnCount()) {
                ttn.setValueAt(value, column);
                TreeTableNode[] arr = getPathToRoot(ttn);
                TreePath path = new TreePath(arr);
                modelSupport.firePathChanged(path);
            }
        }
    }

//    public void setValueAt(Object value, TreeTableNode node, int column) {
//        if (column < node.getColumnCount()) {
//            node.setValueAt(value, column);
//        }
//    }
    @Override
    public Object getValueAt(Object node, int column) {
        TreeTableNode ttn = (TreeTableNode) node;
        Object[] objects = (Object[]) ttn.getUserObject();
        if (objects != null && column < objects.length) {
            return objects[column];
        }
        return null;
    }

    public void fireNodeChange(TreeTableNode node) {
        TreeTableNode[] arr = getPathToRoot(node);
        TreePath path = new TreePath(arr);
        modelSupport.firePathChanged(path);
    }

    @Override
    public TreeTableNode[] getPathToRoot(TreeTableNode aNode) {
        List<TreeTableNode> path = new ArrayList<>();
        TreeTableNode node = aNode;

        while (node != root && node != null) {
            path.add(0, node);
            node = node.getParent();
        }
        if (node != null) {
            path.add(0, node);
        }
        return path.toArray(new TreeTableNode[0]);
    }

    /**
     * Set column to sort by column index. If -1 or greater than number of
     * columns - 1, will be set Unsorted, otherwise sort order will be set
     * Ascending.
     *
     * @param column - index of column name in columnNames List
     */
    public void setSortColumn(int column) {
        if (column == -1 || column > columnIdentifiers.size() - 1) {
            sortColumn = null;
            if (SortOrder.UNSORTED != sortOrder) {
                sortOrder = SortOrder.UNSORTED;
                reset();
            }
        } else {
            toggleSortOrder();
            Object obj = columnIdentifiers.get(column);
            if (obj != null) {
                setKeys(obj.toString(), sortOrder);
            }
        }
    }

    /**
     * Set Sort Order. If null, order will be Unsorted.<br> If no sort column
     * has been set has no effect.
     *
     * @param order
     */
    public void setSortOrder(SortOrder order) {
        if (sortColumn == null) {
            return;
        }
        if (order == null) {
            order = SortOrder.UNSORTED;
        }
        setKeys(sortColumn, order);
    }

    /**
     * Toggle sort order. Unsorted will be sorted ascending, and sorted columns
     * order will be reversed. If sort column not set, has no effect.
     */
    public void toggleSortOrder() {
        if (SortOrder.ASCENDING == sortOrder) {
            setSortOrder(SortOrder.DESCENDING);
        } else {
            setSortOrder(SortOrder.ASCENDING);
        }
    }

    /**
     * Set sort options. If either option is null, will be set Unsorted
     *
     * @param column - name of column to sort. Must be a column in column names
     * list.
     * @param order - see Swingx org.jdesktop.swingx.decorator.SortOrder
     */
    public void setSortOptions(String column, SortOrder order) {
        if (column == null) {
            setSortColumn(null);
            return;
        }
        if (order == null) {
            order = SortOrder.UNSORTED;
        }
        setKeys(column, order);
    }

    /**
     * Sorts complete TreeTable. This will be called automatically if any of the
     * sort options (column or order) are changed. It is not usually neccessary
     * to called this directly unless TreeTable is changed external to model, or
     * TreeTable data changed.
     */
    public void sort() {
        if (SortOrder.UNSORTED == sortOrder) {
            reset();
        } else {
            doFullSort(false);
        }
    }

    /**
     * Sorts children of node, and all their children. Node need not be
     * sortable. (Although if it is not and neither are any descendants, nothing
     * will happen.) Called automatically if a child is added to or removed from
     * a node. Usually not necessary to call this directly unless node added
     * outside model or data changed.
     *
     * @param parent - first node to be sorted.
     */
    public void sort(TreeTableNode parent) {
        boolean reset;
        if (SortOrder.UNSORTED == sortOrder) {
            reset = true;
        } else {
            reset = false;
        }
        doSort(parent, reset);

        TreePath path = new TreePath(getPathToRoot(parent));
        modelSupport.fireTreeStructureChanged(path);
        reExpand();
    }

    public int getSortColumnIndex() {
        return columnIndex;
    }

    public SortOrder getSortOrder() {
        return sortOrder;
    }

    public void insertNodeInto(MutableTreeTableNode newChild, MutableTreeTableNode parent) {
        int index = getChildCount(parent);
        insertNodeInto(newChild, parent, index);
    }

    public void setSortable(MutableTreeTableNode node, boolean sortable) {
        if (!(node instanceof AbstractSortableTreeTableNode)) {
            return;
        }
        AbstractSortableTreeTableNode oneNode = (AbstractSortableTreeTableNode) node;
        oneNode.setSortable(sortable);
        if (oneNode.isSorted() && !sortable) {
            oneNode.reset();
            TreePath path = new TreePath(getPathToRoot(oneNode));
            modelSupport.firePathChanged(path);
        }
    }

    // ==================================================== overridden methods
    @Override
    public void insertNodeInto(MutableTreeTableNode newChild,
            MutableTreeTableNode parent, int index) {
        parent.insert(newChild, index);
        if (SortOrder.UNSORTED != sortOrder) {
            sort(parent);
        } else {
            modelSupport.fireChildAdded(new TreePath(getPathToRoot(parent)),
                    index,
                    newChild);
        }
    }

    @Override
    public void removeNodeFromParent(MutableTreeTableNode node) {
        MutableTreeTableNode parent = (MutableTreeTableNode) node.getParent();
        if (parent == null) {
            throw new IllegalArgumentException("node does not have a parent.");
        }
        //int index = parent.getIndex(node);
        int index = getIndexOfChild(parent, node);
        expanded.remove(new TreePath(getPathToRoot(node)));
        node.removeFromParent();
        if (SortOrder.UNSORTED != sortOrder) {
            sort(parent);
        } else {
            modelSupport.fireChildRemoved(new TreePath(getPathToRoot(parent)),
                    index, node);
        }
    }

    @Override
    public void setRoot(TreeTableNode root) {
        expanded = new ArrayList<>();
        super.setRoot(root);
    }

    /*
     * private methods
     * =================
     **
     * this is a hack to enable expanded nodes to be re-expanded after structure
     * change. It is called from SortableTreeTable<br> It is also used to
     * retrieve the table header to repaint if sort options changed
     * programatically<p> It means model can only be used in one treetable.
     *
     */
    public void setTreeTable(SortableTreeTable treeTable) {
        this.treeTable = treeTable;
        expanded = new ArrayList<>();
        header = treeTable.getTableHeader();
    }

    private void setKeys(String column, SortOrder order) {
        if (sortColumn != null && sortColumn.equals(column)) {
            if (sortOrder.equals(order)) {
                return;
            }
        } else {
            if (column != null) {
                int x = columnIdentifiers.indexOf(column);
                if (x == -1) {
                    throw new IllegalArgumentException("Column " + column
                            + " not in Column Identifiers");
                } else {
                    columnIndex = x;
                }
            }
        }
        sortColumn = column;
        sortOrder = order;
        sort();
        if (header != null) {
            header.repaint();
        }
    }

    private void reset() {
        doFullSort(true);
    }

    private void doFullSort(boolean reset) {
        TreeTableNode root = getRoot();
        if (root == null) {
            return;
        }
        doSort(root, reset);
        modelSupport.fireTreeStructureChanged(new TreePath(root));
        reExpand();
    }

    /*
     * Start from node and drill down all nodes looking to sort.
     */
    private void doSort(TreeTableNode parent, boolean reset) {
        boolean canSort;
        AbstractSortableTreeTableNode node;
        if (parent instanceof AbstractSortableTreeTableNode) {
            node = (AbstractSortableTreeTableNode) parent;
            canSort = true;
        } else {
            node = null;
            canSort = false;
        }
        if (canSort && node != null) {
            canSort = node.canSort();
        }
        if (canSort && node != null) {
            if (reset) {
                canSort = node.isSorted();
            } else {
                canSort = node.canSort(sortColumn);
            }
        }
        if (canSort && node != null) {
            if (reset) {
                node.reset();
            } else {
                node.sort(columnIndex, sortOrder);
            }
        } else if (node != null) {
            node.reset();
        }

        // model use version
        for (int i = 0; i < getChildCount(parent); ++i) {
            TreeTableNode child = (TreeTableNode) getChild(parent, i);
            doSort(child, reset);
        }
    }

    private void reExpand() {
        if (treeTable == null) {
            return;
        }
        expanding = true;
        for (TreePath path : expanded) {
            treeTable.expandPath(path);
        }
        expanding = false;
    }

    /*
     * Inherited
     */
    @Override
    public void treeCollapsed(TreeExpansionEvent arg0) {
        TreePath p = arg0.getPath();
        expanded.remove(p);
    }

    /*
     * Inherited
     */
    @Override
    public void treeExpanded(TreeExpansionEvent arg0) {
        if (expanding) {
            return;
        }
        TreePath p = arg0.getPath();
        if (!expanded.contains(p)) {
            expanded.add(p);
        }
    }
}
