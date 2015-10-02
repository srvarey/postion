/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */

package org.gaia.domain.utils;

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

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "batch_log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BatchLog.findAll", query = "SELECT b FROM BatchLog b"),
    @NamedQuery(name = "BatchLog.findByBatchLogId", query = "SELECT b FROM BatchLog b WHERE b.batchLogId = :batchLogId"),
    @NamedQuery(name = "BatchLog.findByBatchName", query = "SELECT b FROM BatchLog b WHERE b.batchName = :batchName"),
    @NamedQuery(name = "BatchLog.findByDescription", query = "SELECT b FROM BatchLog b WHERE b.description = :description"),
    @NamedQuery(name = "BatchLog.findByStartDate", query = "SELECT b FROM BatchLog b WHERE b.startDate = :startDate"),
    @NamedQuery(name = "BatchLog.findByEndDate", query = "SELECT b FROM BatchLog b WHERE b.endDate = :endDate"),
    @NamedQuery(name = "BatchLog.findByParamObjectClass", query = "SELECT b FROM BatchLog b WHERE b.paramObjectClass = :paramObjectClass"),
    @NamedQuery(name = "BatchLog.findByParamObjectId", query = "SELECT b FROM BatchLog b WHERE b.paramObjectId = :paramObjectId"),
    @NamedQuery(name = "BatchLog.findByValueDate", query = "SELECT b FROM BatchLog b WHERE b.valueDate = :valueDate"),
    @NamedQuery(name = "BatchLog.findByUserId", query = "SELECT b FROM BatchLog b WHERE b.userId = :userId"),
    @NamedQuery(name = "BatchLog.findByComment", query = "SELECT b FROM BatchLog b WHERE b.comment = :comment")})
public class BatchLog implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "batch_log_id")
    private Integer batchLogId;
    @Column(name = "batch_name")
    private String batchName;
    @Column(name = "description")
    private String description;
    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
    @Column(name = "param_object_class")
    private String paramObjectClass;
    @Column(name = "param_object_id")
    private Integer paramObjectId;
    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    private Date valueDate;
    @Column(name = "user_id")
    private Integer userId;
    private String comment;

    public BatchLog() {
    }

    public BatchLog(Integer batchLogId) {
        this.batchLogId = batchLogId;
    }

    public Integer getBatchLogId() {
        return batchLogId;
    }

    public void setBatchLogId(Integer batchLogId) {
        this.batchLogId = batchLogId;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getParamObjectClass() {
        return paramObjectClass;
    }

    public void setParamObjectClass(String paramObjectClass) {
        this.paramObjectClass = paramObjectClass;
    }

    public Integer getParamObjectId() {
        return paramObjectId;
    }

    public void setParamObjectId(Integer paramObjectId) {
        this.paramObjectId = paramObjectId;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (batchLogId != null ? batchLogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BatchLog)) {
            return false;
        }
        BatchLog other = (BatchLog) object;
        if ((this.batchLogId == null && other.batchLogId != null) || (this.batchLogId != null && !this.batchLogId.equals(other.batchLogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str="BatchLog Id=" + batchLogId;
        if (batchName!=null){
            str+= StringUtils.SPACE+batchName;
        }
        return str;
    }

}
