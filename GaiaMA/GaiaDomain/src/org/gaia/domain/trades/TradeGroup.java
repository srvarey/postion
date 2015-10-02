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
package org.gaia.domain.trades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;


/**
 *
 * @author Bouazzi Saber
 */
@Audited
@Entity
@Table(name = "trade_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeGroup.findAll", query = "SELECT t FROM TradeGroup t"),
    @NamedQuery(name = "TradeGroup.findByTradeGroupId", query = "SELECT t FROM TradeGroup t WHERE t.tradeGroupId = :tradeGroupId"),
    @NamedQuery(name = "TradeGroup.findByTradeGroupType", query = "SELECT t FROM TradeGroup t WHERE t.tradeGroupType = :tradeGroupType")})

public class TradeGroup implements Serializable {

    public enum TradeGroupType{FX_Strategy,Mirrors,Event,Exercise};


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trade_group_id")
    private Integer tradeGroupId;
    @Column(name = "trade_group_type")
    private String tradeGroupType;


    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "trade_group_trade", joinColumns = {
        @JoinColumn(name = "trade_group_id", referencedColumnName = "trade_group_id")}, inverseJoinColumns = {
        @JoinColumn(name = "trade_id", referencedColumnName = "trade_id")})
    @LazyCollection(LazyCollectionOption.FALSE)
    @IndexColumn(name="trade_index")
    private List<Trade> trades;

    public TradeGroup() {
    }

    public TradeGroup(String tradeGroupType) {
        this.tradeGroupType = tradeGroupType;
    }

    public TradeGroup(Integer tradeGroupId) {
        this.tradeGroupId = tradeGroupId;
    }

    public void addTrade(Trade trade) {
        if (trades==null){
            trades=new ArrayList();
        }
        trades.add(trade);
    }

    public Integer getTradeGroupId() {
        return tradeGroupId;
    }

    public void setTradeGroupId(Integer tradeGroupId) {
        this.tradeGroupId = tradeGroupId;
    }

    public String getTradeGroupType() {
        return tradeGroupType;
    }

    public void setTradeGroupType(String tradeGroupType) {
        this.tradeGroupType = tradeGroupType;
    }

    @XmlTransient
    public Collection<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeGroupId != null ? tradeGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TradeGroup)) {
            return false;
        }
        TradeGroup other = (TradeGroup) object;
        if ((this.tradeGroupId == null && other.tradeGroupId != null) || (this.tradeGroupId != null && !this.tradeGroupId.equals(other.tradeGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TradeGroup[ tradeGroupId=" + tradeGroupId + " ]";
    }
}
