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
package org.gaia.domain.utils;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "import_map_field")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportMapField.findAll", query = "SELECT i FROM ImportMapField i"),
    @NamedQuery(name = "ImportMapField.findByImportMapField", query = "SELECT i FROM ImportMapField i WHERE i.importMapField = :importMapField"),
    @NamedQuery(name = "ImportMapField.findByColumnName", query = "SELECT i FROM ImportMapField i WHERE i.columnName = :columnName"),
    @NamedQuery(name = "ImportMapField.findByGetter", query = "SELECT i FROM ImportMapField i WHERE i.getter = :getter"),
    @NamedQuery(name = "ImportMapField.findByParam", query = "SELECT i FROM ImportMapField i WHERE i.param = :param"),
    @NamedQuery(name = "ImportMapField.findByReturnType", query = "SELECT i FROM ImportMapField i WHERE i.returnType = :returnType"),
    @NamedQuery(name = "ImportMapField.findByColumnType", query = "SELECT i FROM ImportMapField i WHERE i.columnType = :columnType")})
public class ImportMapField implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "import_map_field_id")
    private Integer importMapField;
    @Column(name = "column_name")
    private String columnName;
    @Column(name = "constant")
    private String constant;
    @Column(name = "source_field")
    private String sourceField;
    @Column(name = "getter")
    private String getter;
    @Column(name = "param")
    private String param;
    @Column(name = "return_type")
    private String returnType;
    @Column(name = "column_type")
    private String columnType;
    @JoinColumn(name = "import_map_id", referencedColumnName = "import_map_id")
    @ManyToOne
    private ImportMap importMap;

    public ImportMapField() {
    }

    public ImportMapField(Integer importMapFieldId) {
        this.importMapField = importMapFieldId;
    }

    public Integer getImportMapField() {
        return importMapField;
    }

    public void setImportMapField(Integer importMapField) {
        this.importMapField = importMapField;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getConstant() {
        return constant;
    }

    public void setConstant(String constant) {
        this.constant = constant;
    }

    public String getSourceField() {
        return sourceField;
    }

    public void setSourceField(String sourceField) {
        this.sourceField = sourceField;
    }

    public String getGetter() {
        return getter;
    }

    public void setGetter(String getter) {
        this.getter = getter;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getColumnType() {
        return columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public ImportMap getImportMap() {
        return importMap;
    }

    public void setImportMap(ImportMap importMap) {
        this.importMap = importMap;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importMapField != null ? importMapField.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof ImportMapField)) {
            return false;
        }
        ImportMapField other = (ImportMapField) object;
        if ((this.importMapField == null && other.importMapField != null) || (this.importMapField != null && !this.importMapField.equals(other.importMapField))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.io.ImportMapField[ importMapFieldId=" + importMapField + " ]";
    }
}
