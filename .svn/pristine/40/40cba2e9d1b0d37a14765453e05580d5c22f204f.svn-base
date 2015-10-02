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
package org.gaia.domain.observables;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "pricers_setting")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "PricersSetting.findAll", query = "SELECT p FROM PricersSetting p"),
    @NamedQuery(name = "PricersSetting.findByPricersSettingId", query = "SELECT p FROM PricersSetting p WHERE p.pricersSettingId = :pricersSettingId"),
    @NamedQuery(name = "PricersSetting.findByProductType", query = "SELECT p FROM PricersSetting p WHERE p.productType = :productType"),
    @NamedQuery(name = "PricersSetting.findByTradeFilterName", query = "SELECT p FROM PricersSetting p WHERE p.tradeFilterName = :tradeFilterName"),
    @NamedQuery(name = "PricersSetting.findByPricer", query = "SELECT p FROM PricersSetting p WHERE p.pricer = :pricer")})


public class PricersSetting implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pricers_setting_id")
    private Integer pricersSettingId;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "trade_filter_name")
    private String tradeFilterName;
    @Column(name = "measure_group")
    private String measureGroup;
    @Column(name = "pricer")
    private String pricer;
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JoinColumn(name = "pricing_environment_id", referencedColumnName = "pricing_environment_id", insertable = false, updatable = false, nullable=false)
    private PricingEnvironment pricingEnvironment;

    public PricersSetting() {
    }

    public PricersSetting(Integer pricersSettingId) {
        this.pricersSettingId = pricersSettingId;
    }

    public Integer getPricersSettingId() {
        return pricersSettingId;
    }

    public void setPricersSettingId(Integer pricersSettingId) {
        this.pricersSettingId = pricersSettingId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTradeFilterName() {
        return tradeFilterName;
    }

    public void setTradeFilterName(String TradeFilterName) {
        this.tradeFilterName = TradeFilterName;
    }

    public String getMeasureGroup() {
        return measureGroup;
    }

    public void setMeasureGroup(String measureGroup) {
        this.measureGroup = measureGroup;
    }

    public String getPricer() {
        return pricer;
    }

    public void setPricer(String pricer) {
        this.pricer = pricer;
    }

    public PricingEnvironment getPricingEnvironment() {
        return pricingEnvironment;
    }

    public void setPricingEnvironment(PricingEnvironment pricingEnvironment) {
        this.pricingEnvironment = pricingEnvironment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pricersSettingId != null ? pricersSettingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PricersSetting)) {
            return false;
        }
        PricersSetting other = (PricersSetting) object;
        if ((this.pricersSettingId == null && other.pricersSettingId != null) || (this.pricersSettingId != null && !this.pricersSettingId.equals(other.pricersSettingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.observables.PricersSetting[ pricersSettingId=" + pricersSettingId + " ]";
    }

}
