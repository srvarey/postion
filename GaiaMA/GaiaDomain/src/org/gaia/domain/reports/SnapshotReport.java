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
package org.gaia.domain.reports;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author User
 */
@Entity
@Table(name = "snapshotreport")
@XmlRootElement
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "SnapshotReport.findAll", query = "SELECT s FROM SnapshotReport s"),
    @NamedQuery(name = "SnapshotReport.findBySnapshotId", query = "SELECT s FROM SnapshotReport s WHERE s.snapshotReportId = :snapshotReportId"),
    @NamedQuery(name = "SnapshotReport.findBySnapshotTemplateName", query = "SELECT s FROM SnapshotReport s WHERE s.snapshotTemplateName = :snapshotTemplateName"),
    @NamedQuery(name = "SnapshotReport.findBySnapshotReportType", query = "SELECT s FROM SnapshotReport s WHERE s.snapshotReportType = :snapshotReportType")})
public class SnapshotReport implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "snapshot_report_id")
    private Integer snapshotReportId;
    @Column(name = "snapshot_template_name")
    private String snapshotTemplateName;
    @Column(name = "snapshot_report_valDate")
    @Temporal(TemporalType.DATE)
    private Date snapshotReportValueDate;
    @Column(name = "snapshot_report_type")
    private String snapshotReportType;

    @Column(name = "snapshot_description")
    private String snapshotDescription;

    @Column(name = "snapshot_blob")
    private byte[] snapshotBlob;

    @Column(name = "snapshot_criteria")
    private byte[] snapshotCriteria;

    public SnapshotReport() {
    }

    public SnapshotReport(Integer snapshotId) {
        this.snapshotReportId = snapshotId;
    }

    public Integer getSnapshotReportId() {
        return snapshotReportId;
    }

    public void setSnapshotReportId(Integer snapshotReportId) {
        this.snapshotReportId = snapshotReportId;
    }

    public String getSnapshotTemplateName() {
        return snapshotTemplateName;
    }

    public void setSnapshotTemplateName(String snapshotTemplateName) {
        this.snapshotTemplateName = snapshotTemplateName;
    }

    public Date getSnapshotReportValueDate() {
        return snapshotReportValueDate;
    }

    public void setSnapshotReportValueDate(Date snapshotReportValueDate) {
        this.snapshotReportValueDate = snapshotReportValueDate;
    }

    public String getSnapshotReportType() {
        return snapshotReportType;
    }

    public void setSnapshotReportType(String snapshotReportType) {
        this.snapshotReportType = snapshotReportType;
    }

     public String getSnapshotDescription() {
        return snapshotDescription;
    }

    public void setSnapshotDescription(String _snapshotDescription) {
        this.snapshotDescription = _snapshotDescription;
    }
    public byte[] getSnapshotBlob() {
        return snapshotBlob;
    }

    public void setSnapshotBlob(byte[] _snapshotBlob) {
        this.snapshotBlob = _snapshotBlob;
    }
    public byte[] getSnapshotCriteria() {
        return snapshotCriteria;
    }

    public void setSnapshotCriteria(byte[] _snapshotCriteria) {
        this.snapshotCriteria = _snapshotCriteria;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (snapshotReportId != null ? snapshotReportId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof SnapshotReport)) {
            return false;
        }
        SnapshotReport other = (SnapshotReport) object;
        if ((this.snapshotReportId == null && other.snapshotReportId != null) || (this.snapshotReportId != null && !this.snapshotReportId.equals(other.snapshotReportId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication15.snapshot_Report[ snapshotId=" + snapshotReportId + " ]";
    }
}
