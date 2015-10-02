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
package org.gaia.domain.trades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SABER
 */
@Entity
@Table(name = "header_table")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HeaderTable.findAll", query = "SELECT h FROM HeaderTable h"),
    @NamedQuery(name = "HeaderTable.findByHeaderId", query = "SELECT h FROM HeaderTable h WHERE h.headerId = :headerId"),
    @NamedQuery(name = "HeaderTable.findByTableName", query = "SELECT h FROM HeaderTable h WHERE h.tableName = :tableName"),
    @NamedQuery(name = "HeaderTable.findByColumnName", query = "SELECT h FROM HeaderTable h WHERE h.columnName = :columnName"),
    @NamedQuery(name = "HeaderTable.findByHeaderValue", query = "SELECT h FROM HeaderTable h WHERE h.isvisible = :isvisible")})
public class HeaderTable implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id
    @Column(name = "header_id")
    private int headerId;
    @Basic(optional = false)
    @Column(name = "table_name")
    private String tableName;
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "is_visible")
    private Boolean isvisible;

    public HeaderTable() {
    }

    public HeaderTable(String tableName) {
        this.tableName = tableName;
    }

    public HeaderTable(String tableName,String header) {
        this.tableName = tableName;
        this.columnName =header;
    }

    public HeaderTable(String tableName, int headerId) {
        this.tableName = tableName;
        this.headerId = headerId;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Boolean getIsVisible() {
        return isvisible;
    }

    public void setIsVisible(Boolean isvisible) {
        this.isvisible = isvisible;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tableName != null ? tableName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HeaderTable)) {
            return false;
        }
        HeaderTable other = (HeaderTable) object;
        if ((this.tableName == null && other.tableName != null) || (this.tableName != null && !this.tableName.equals(other.tableName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HeaderTable[ tableName=" + tableName + " ]";
    }
}
