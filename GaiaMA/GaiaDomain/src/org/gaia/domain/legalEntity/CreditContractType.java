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
package org.gaia.domain.legalEntity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "credit_contract_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CreditContractType.findAll", query = "SELECT c FROM CreditContractType c"),
    @NamedQuery(name = "CreditContractType.findByCreditContractTypeId", query = "SELECT c FROM CreditContractType c WHERE c.creditContractTypeId = :creditContractTypeId"),
    @NamedQuery(name = "CreditContractType.findByCreditContractTypeName", query = "SELECT c FROM CreditContractType c WHERE c.creditContractTypeName = :creditContractTypeName"),
    @NamedQuery(name = "CreditContractType.findByCreditBackStopDate", query = "SELECT c FROM CreditContractType c WHERE c.creditBackStopDate = :creditBackStopDate"),
    @NamedQuery(name = "CreditContractType.findByCorporateBackStopDate", query = "SELECT c FROM CreditContractType c WHERE c.corporateBackStopDate = :corporateBackStopDate"),
    @NamedQuery(name = "CreditContractType.findByEventList", query = "SELECT c FROM CreditContractType c WHERE c.eventList = :eventList")})
public class CreditContractType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "credit_contract_type_id")
    private Integer creditContractTypeId;
    @Column(name = "credit_contract_type_name")
    private String creditContractTypeName;
    @Column(name = "credit_back_stop_date")
    private Short creditBackStopDate;
    @Column(name = "corporate_back_stop_date")
    private Short corporateBackStopDate;
    @Column(name = "event_list")
    private String eventList;

    public CreditContractType() {
    }

    public CreditContractType(Integer CreditContractTypeId) {
        this.creditContractTypeId = CreditContractTypeId;
    }

    public Integer getCreditContractTypeId() {
        return creditContractTypeId;
    }

    public void setCreditContractTypeId(Integer CreditContractTypeId) {
        this.creditContractTypeId = CreditContractTypeId;
    }

    public String getCreditContractTypeName() {
        return creditContractTypeName;
    }

    public void setCreditContractTypeName(String CreditContractTypeName) {
        this.creditContractTypeName = CreditContractTypeName;
    }

    public Short getCreditBackStopDate() {
        return creditBackStopDate;
    }

    public void setCreditBackStopDate(Short creditBackStopDate) {
        this.creditBackStopDate = creditBackStopDate;
    }

    public Short getCorporateBackStopDate() {
        return corporateBackStopDate;
    }

    public void setCorporateBackStopDate(Short corporateBackStopDate) {
        this.corporateBackStopDate = corporateBackStopDate;
    }

    public String getEventList() {
        return eventList;
    }

    public void setEventList(String eventList) {
        this.eventList = eventList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (creditContractTypeId != null ? creditContractTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CreditContractType)) {
            return false;
        }
        CreditContractType other = (CreditContractType) object;
        if ((this.creditContractTypeId == null && other.creditContractTypeId != null) || (this.creditContractTypeId != null && !this.creditContractTypeId.equals(other.creditContractTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiajademasterdispatcherserver.CreditContractType[ CreditContractTypeId=" + creditContractTypeId + " ]";
    }

}
