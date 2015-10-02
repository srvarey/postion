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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author user
 */
@Entity
@Table(name = "position_configuration")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PositionConfiguration.findAll", query = "SELECT p FROM PositionConfiguration p"),
    @NamedQuery(name = "PositionConfiguration.findByPositionConfigurationId", query = "SELECT p FROM PositionConfiguration p WHERE p.positionConfigurationId = :positionConfigurationId"),
    @NamedQuery(name = "PositionConfiguration.findByName", query = "SELECT p FROM PositionConfiguration p WHERE p.name = :name"),
    @NamedQuery(name = "PositionConfiguration.findByIsTradeDate", query = "SELECT p FROM PositionConfiguration p WHERE p.isTradeDate = :isTradeDate")})



public class PositionConfiguration implements Serializable {

    public enum PositionAggregationCriteria{
        AGGREGATION_BY_PRODUCT("product.productId","getProduct"),
        AGGREGATION_BY_INTERNAL_COUNTERPARTY("internalCounterparty.legalEntityId","getInternalCounterparty"),
        AGGREGATION_BY_COUNTERPARTY("counterparty.legalEntityId","getCounterparty"),
        AGGREGATION_BY_TRADER("f.trader","getTrader"),
        AGGREGATION_BY_IS_COLLATERAL("f.isCollateral","getIsCollateral"),
        AGGREGATION_BY_CCP("ccp.legalEntityId","getCcp"),
        AGGREGATION_BY_CLEARING_MEMBER("clearingMember.legalEntityId","getClearingMember"),
        AGGREGATION_BY_TRADE_TYPE("f.tradeType","getTradeType")
        ;

        PositionAggregationCriteria(String dbField,String getter){
            this.dbField=dbField;
            this.getter=getter;
        }

        public String dbField;
        public String getter;

    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_configuration_id")
    private Integer positionConfigurationId;
    @Column(name = "name")
    private String name;
    @Column(name = "is_trade_date")
    private Boolean isTradeDate;
    @Column(name = "start_date")
    private Date startDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name="position_configuration_id")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Collection<PositionConfigurationItem> positionConfigurationItems;

    public PositionConfiguration() {
    }

    public PositionConfiguration(Integer positionConfigurationId) {
        this.positionConfigurationId = positionConfigurationId;
    }

    public Integer getPositionConfigurationId() {
        return positionConfigurationId;
    }

    public void setPositionConfigurationId(Integer positionConfigurationId) {
        this.positionConfigurationId = positionConfigurationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isInTradeDate() {
        return isTradeDate;
    }

    public String getDateField(){
        if (isTradeDate) {
            return "tradeDate";
        } else {
            return "valueDate";
        }
    }

    public void setDateField(String str){
        isTradeDate=str.equalsIgnoreCase("tradeDate");
    }

    public void setIsInTradeDate(Boolean isTradeDate) {
        this.isTradeDate = isTradeDate;
    }

    public Collection<PositionConfigurationItem> getPositionConfigurationItems() {
        return positionConfigurationItems;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Map<String,PositionConfigurationItem> getPositionConfigurationItemMap(){
        HashMap<String,PositionConfigurationItem> result = new HashMap();
        for (PositionConfigurationItem positionConfigurationItem : positionConfigurationItems){
            result.put(positionConfigurationItem.getDbField(), positionConfigurationItem);
        }
        return result;
    }

    public void setPositionConfigurationItems(Collection<PositionConfigurationItem> positionConfigurationItems) {
        this.positionConfigurationItems = positionConfigurationItems;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionConfigurationId != null ? positionConfigurationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PositionConfiguration)) {
            return false;
        }
        PositionConfiguration other = (PositionConfiguration) object;
        if ((this.positionConfigurationId == null && other.positionConfigurationId != null) || (this.positionConfigurationId != null && !this.positionConfigurationId.equals(other.positionConfigurationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiajademasterdispatcherserver.PositionConfiguration[ positionConfigurationId=" + positionConfigurationId + " ]";
    }

}
