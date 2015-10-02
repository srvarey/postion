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
package org.gaia.dao.reports;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Benjamin Frerejean
 */
public class PositionTree implements Serializable{

    public PositionTree(){

    }

    public abstract class PositionNode extends AbstractSortableTreeTableNode implements Serializable {

        private static final long serialVersionUID = 1L;

        public PositionNode(Object[] data) {
            super(data);
        }

        @Override
        public int getColumnCount() {
            if (getData()!=null){
                return getData().length;
            } else {
                return 0;
            }
        }
        @Override
        public Object getValueAt(int column) {
            Object[] o =getData();
            if (o!=null) {
                return o[column];
            }
            else {
                return null;
            }
        }

        public Object[] getData() {
            return (Object[]) getUserObject();
        }

        public void setData(Object[] objectsArray) {
            this.userObject=objectsArray;
        }

	private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(getData());
        }

	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            if (in!=null){
                in.defaultReadObject();
                Object o=in.readObject();

                if (o!=null){
                    Object[] obs=(Object[]) o;
                    this.setUserObject(obs);
                }
            }
        }
    }

    public class AggregNode extends PositionNode implements Serializable {
        private static final long serialVersionUID = 1L;

        public AggregNode(Object[] data) {
            super(data);
        }
    }

    public class LineNode extends PositionNode implements Serializable {
        private static final long serialVersionUID = 1L;

        public LineNode(Object[] data) {
            super(data);
        }
    }

}
