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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "trade_attribute")
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@XStreamAlias("Attribute")

public class TradeAttribute implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "trade_attribute_id")
    private Integer tradeAttibuteId;
    @Basic(optional = false)
    @Column(name = "attribute_name")
    private String attributeName;
    @Basic(optional = false)
    @Column(name = "attribute_value")
    private String attributeValue;
    @ManyToOne(optional = false)
    @JoinColumn(name = "trade_id", referencedColumnName = "trade_id", updatable = false, insertable = false, nullable = false)
    @XStreamOmitField
    @NotAccessibleField(level = 1)
    private Trade trade;

    public TradeAttribute() {
    }

    public TradeAttribute(Integer tradeAttibuteId) {
        this.tradeAttibuteId = tradeAttibuteId;
    }

    public TradeAttribute(Trade trade,String attributeName, String attributeValue) {
        this.trade=trade;
        this.attributeName=attributeName;
        this.attributeValue=attributeValue;
    }

    public TradeAttribute(Integer tradeAttibuteId, String attributeValue) {
        this.tradeAttibuteId = tradeAttibuteId;
        this.attributeValue = attributeValue;
    }

    public Integer getTradeAttibuteId() {
        return tradeAttibuteId;
    }

    public void setTradeAttibuteId(Integer tradeAttibuteId) {
        this.tradeAttibuteId = tradeAttibuteId;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public Trade getTrade() {
        return trade;
    }

    public void setTrade(Trade trade) {
        this.trade = trade;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradeAttibuteId != null ? tradeAttibuteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof TradeAttribute)) {
            return false;
        }
        TradeAttribute other = (TradeAttribute) object;
        if ((this.tradeAttibuteId == null && other.tradeAttibuteId != null) || (this.tradeAttibuteId != null && !this.tradeAttibuteId.equals(other.tradeAttibuteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str="TradeAttribute " + tradeAttibuteId;
        if (trade !=null){
            str+=" on trade "+trade.getTradeId();
        }
        if (attributeName!=null){
            str+=StringUtils.SPACE+attributeName+"="+attributeValue;
        }
        return str;
    }

}
