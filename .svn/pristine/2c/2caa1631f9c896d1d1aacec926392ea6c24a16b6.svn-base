/**
 * Copyright (C) 2013 Gaia Transparence
 * Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3.0 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.gui.utils;

import javax.swing.SortOrder;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author User
 */
public class SortableTreeTable extends JXTreeTable {

    private boolean started = false;
    private SortOrder order;
    private int sortColumn;
    private SortableTreeTableModel sortModel = null;
    private boolean suppress = false;

    /**
     *
     */
    public SortableTreeTable() {
        super();
        started = true;
    }

    /**
     * @param treeModel
     */
    public SortableTreeTable(TreeTableModel treeModel) {
        super(treeModel);
        if (!(treeModel instanceof SortableTreeTableModel)) {
            throw new IllegalArgumentException(""
                    + "Model must be a SortableTreeTableModel");
        }
        sortModel = (SortableTreeTableModel) treeModel;
        addTreeExpansionListener(sortModel);
        sortModel.setTreeTable(this);
        started = true;
    }

    // ==================================================== overridden methods
    @Override
    public boolean isSortable() {
        return true;
    }

    @Override
    public void resetSortOrder() {
        if (suppress) {
            return;
        }
        sortModel.setSortOrder(SortOrder.UNSORTED);
    }

    @Override
    public SortOrder getSortOrder(int colIndex) {
        // need this as method called from JXTable constructor and TreeTable
        // model not setup yet
        if (!started || sortModel == null) {
            return SortOrder.UNSORTED;
        }
        int x = convertColumnIndexToModel(colIndex);
        getSortParams();
        if (x != sortColumn) {
            return SortOrder.UNSORTED;
        } else {
            return order;
        }
    }

    @Override
    public void toggleSortOrder(int columnIndex) {
        int x = convertColumnIndexToModel(columnIndex);
        getSortParams();
        if (x == sortColumn) {
            sortModel.toggleSortOrder();
        } else {
            sortModel.setSortColumn(x);
        }
    }

    @Override
    public void setTreeTableModel(TreeTableModel treeModel) {
        TreeTableModel m = getTreeTableModel();
        SortableTreeTableModel old = null;
        if (m instanceof SortableTreeTableModel) {
            old = (SortableTreeTableModel) m;
        }
        super.setTreeTableModel(treeModel);
        if (!(treeModel instanceof SortableTreeTableModel)) {
            throw new IllegalArgumentException(""
                    + "Model must be a SortableTreeTableModel");
        }
        sortModel = (SortableTreeTableModel) treeModel;
        if (old != null) {
            removeTreeExpansionListener(old);
        }
        addTreeExpansionListener(sortModel);
        sortModel.setTreeTable(this);
    }

    @Override
    public void setSortOrder(int columnIndex, SortOrder sortOrder) {
        sortModel.setSortOptions(sortModel.getColumnName(columnIndex), order);
    }

    // ======================================================= private methods
    private void getSortParams() {
        if (sortModel == null) {
            throw new IllegalStateException("No TreeTable Model");
        }
        order = sortModel.getSortOrder();
        sortColumn = sortModel.getSortColumnIndex();
    }
}
