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
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "import_map")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ImportMap.findAll", query = "SELECT i FROM ImportMap i"),
    @NamedQuery(name = "ImportMap.findByImportMapId", query = "SELECT i FROM ImportMap i WHERE i.importMapId = :importMapId"),
    @NamedQuery(name = "ImportMap.findByName", query = "SELECT i FROM ImportMap i WHERE i.name = :name"),
    @NamedQuery(name = "ImportMap.findByObjectType", query = "SELECT i FROM ImportMap i WHERE i.objectType = :objectType"),
    @NamedQuery(name = "ImportMap.findByFileType", query = "SELECT i FROM ImportMap i WHERE i.fileType = :fileType"),
    @NamedQuery(name = "ImportMap.findByFilePath", query = "SELECT i FROM ImportMap i WHERE i.filePath = :filePath")})


public class ImportMap implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "import_map_id")
    private Integer importMapId;
    @Column(name = "name")
    private String name;
    @Column(name = "object_type")
    private String objectType;
    @Column(name = "file_type")
    private String fileType;
    @Column(name = "file_path")
    private String filePath;
    @Column(name = "object_path")
    private String objectPath;

    @OneToMany(mappedBy = "importMap",fetch=FetchType.EAGER)
    private Collection<ImportMapField> importMapFieldCollection;

    public ImportMap() {
    }

    public ImportMap(Integer importMapId) {
        this.importMapId = importMapId;
    }

    public Integer getImportMapId() {
        return importMapId;
    }

    public void setImportMapId(Integer importMapId) {
        this.importMapId = importMapId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getObjectPath() {
        return objectPath;
    }

    public void setObjectPath(String objectPath) {
        this.objectPath = objectPath;
    }

    @XmlTransient
    public Collection<ImportMapField> getImportMapFieldCollection() {
        return importMapFieldCollection;
    }

    public void setImportMapFieldCollection(Collection<ImportMapField> importMapFieldCollection) {
        this.importMapFieldCollection = importMapFieldCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (importMapId != null ? importMapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
      
        if (!(object instanceof ImportMap)) {
            return false;
        }
        ImportMap other = (ImportMap) object;
        if ((this.importMapId == null && other.importMapId != null) || (this.importMapId != null && !this.importMapId.equals(other.importMapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.io.ImportMap[ importMapId=" + importMapId + " ]";
    }

}
