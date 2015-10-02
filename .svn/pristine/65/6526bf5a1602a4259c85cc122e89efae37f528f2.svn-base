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
package org.gaia.domain.referentials;

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
import org.gaia.domain.utils.AvailableValueList;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "exchange")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Exchange.findAll", query = "SELECT e FROM Exchange e"),
    @NamedQuery(name = "Exchange.findByExchangeId", query = "SELECT e FROM Exchange e WHERE e.exchangeId = :exchangeId"),
    @NamedQuery(name = "Exchange.findByMicCode", query = "SELECT e FROM Exchange e WHERE e.micCode = :micCode"),
    @NamedQuery(name = "Exchange.findByCountryId", query = "SELECT e FROM Exchange e WHERE e.countryId = :countryId"),
    @NamedQuery(name = "Exchange.findByLabel", query = "SELECT e FROM Exchange e WHERE e.label = :label"),
    @NamedQuery(name = "Exchange.findByBbgCode", query = "SELECT e FROM Exchange e WHERE e.bbgCode = :bbgCode"),
    @NamedQuery(name = "Exchange.findByRicCode", query = "SELECT e FROM Exchange e WHERE e.ricCode = :ricCode"),
    @NamedQuery(name = "Exchange.findByRicSuffix", query = "SELECT e FROM Exchange e WHERE e.ricSuffix = :ricSuffix"),
    @NamedQuery(name = "Exchange.findByStatus", query = "SELECT e FROM Exchange e WHERE e.status = :status"),
    @NamedQuery(name = "Exchange.findByDefaultCurrendy", query = "SELECT e FROM Exchange e WHERE e.defaultCurrency = :defaultCurrency"),
    @NamedQuery(name = "Exchange.findByHolidayCode", query = "SELECT e FROM Exchange e WHERE e.holidayCode = :holidayCode")})
public class Exchange implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "exchange_id")
    private Integer exchangeId;
    @Column(name = "mic_code")
    private String micCode;
    @Column(name = "country_id")
    private Integer countryId;
    @Column(name = "label")
    private String label;
    @Column(name = "bbg_code")
    private String bbgCode;
    @Column(name = "ric_code")
    private String ricCode;
    @Column(name = "ric_suffix")
    private String ricSuffix;
    @Column(name = "status")
    private String status;
    @Column(name = "default_currency", length = 3)
    @AvailableValueList(isCurrency = true)
    private String defaultCurrency;
    @Column(name = "holiday_code")
    private String holidayCode;
    @Column(name = "town")
    private String town;

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Exchange() {
    }

    public Exchange(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public Integer getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Integer exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getMicCode() {
        return micCode;
    }

    public void setMicCode(String micCode) {
        this.micCode = micCode;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getBbgCode() {
        return bbgCode;
    }

    public void setBbgCode(String bbgCode) {
        this.bbgCode = bbgCode;
    }

    public String getRicCode() {
        return ricCode;
    }

    public void setRicCode(String ricCode) {
        this.ricCode = ricCode;
    }

    public String getRicSuffix() {
        return ricSuffix;
    }

    public void setRicSuffix(String ricSuffix) {
        this.ricSuffix = ricSuffix;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public String getHolidayCode() {
        return holidayCode;
    }

    public void setHolidayCode(String holidayCode) {
        this.holidayCode = holidayCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exchangeId != null ? exchangeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Exchange)) {
            return false;
        }
        Exchange other = (Exchange) object;
        if ((this.exchangeId == null && other.exchangeId != null) || (this.exchangeId != null && !this.exchangeId.equals(other.exchangeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "domain.referentials.Exchange[ exchangeId=" + exchangeId + " ]";
    }
}
