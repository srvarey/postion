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
import org.gaia.domain.legalEntity.LegalEntity;
import org.hibernate.envers.Audited;

@Audited
@Entity
@Table(name = "trade_entity")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TradeEntity.findAll", query = "SELECT t FROM TradeEntity t"),
    @NamedQuery(name = "TradeEntity.findByTradeEntityId", query = "SELECT t FROM TradeEntity t WHERE t.tradeEntityId = :tradeEntityId"),
    @NamedQuery(name = "TradeEntity.findByRole", query = "SELECT t FROM TradeEntity t WHERE t.role = :role")})

public class TradeEntity implements Serializable,Cloneable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trade_entity_id")
    private Integer tradeEntityId;
    @Column(name = "role")
    private String role;
    @ManyToOne
    @JoinColumn(name = "trade_id",insertable=false,updatable=false)
    private Trade trade;
    @ManyToOne
    @JoinColumn(name = "legal_entity_id", referencedColumnName = "legal_entity_id")
    private LegalEntity legalEntity;

    public TradeEntity() {
    }

    public TradeEntity(Trade trade,LegalEntity legalEntity, String role) {
        this.trade=trade;
        this.legalEntity=legalEntity;
        this.role=role;
    }

    public TradeEntity(Integer tradeEntityId) {
        this.tradeEntityId = tradeEntityId;
    }

    public Integer getTradeEntityId() {
        return tradeEntityId;
    }

    public void setTradeEntityId(Integer tradeEntityId) {
        this.tradeEntityId = tradeEntityId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }

    public LegalEntity getLegalEntity() {
        return legalEntity;
    }

    public void setLegalEntity(LegalEntity legalEntity) {
        this.legalEntity = legalEntity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeEntityId != null ? tradeEntityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TradeEntity)) {
            return false;
        }
        TradeEntity other = (TradeEntity) object;
        if ((this.tradeEntityId == null && other.tradeEntityId != null) || (this.tradeEntityId != null && !this.tradeEntityId.equals(other.tradeEntityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(legalEntity!=null&& role!=null){
            return role+"="+legalEntity.getShortName();
        }
        return null;
    }

    @Override
    public TradeEntity clone() {
        TradeEntity myClone = null;
        try {
            myClone = (TradeEntity) super.clone();
            myClone.setTradeEntityId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }
}
