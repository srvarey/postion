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
import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.NotAccessibleField;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author user
 */
@Entity
@Table(name = "position_history")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "PositionHistory.findAll", query = "SELECT p FROM PositionHistory p"),
    @NamedQuery(name = "PositionHistory.findByPositionHistoryId", query = "SELECT p FROM PositionHistory p WHERE p.positionHistoryId = :positionHistoryId"),
    @NamedQuery(name = "PositionHistory.findByPositionDate", query = "SELECT p FROM PositionHistory p WHERE p.positionDate = :positionDate"),
    @NamedQuery(name = "PositionHistory.findByPositionMode", query = "SELECT p FROM PositionHistory p WHERE p.positionMode = :positionMode"),
    @NamedQuery(name = "PositionHistory.findByQuantity", query = "SELECT p FROM PositionHistory p WHERE p.quantity = :quantity"),
    @NamedQuery(name = "PositionHistory.findByQuantityType", query = "SELECT p FROM PositionHistory p WHERE p.quantityType = :quantityType"),
//    @NamedQuery(name = "PositionHistory.findByPrice", query = "SELECT p FROM PositionHistory p WHERE p.price = :price"),
//    @NamedQuery(name = "PositionHistory.findByMtm", query = "SELECT p FROM PositionHistory p WHERE p.mtm = :mtm"),
//    @NamedQuery(name = "PositionHistory.findByMtmPtfCur", query = "SELECT p FROM PositionHistory p WHERE p.mtmPtfCur = :mtmPtfCur"),
//    @NamedQuery(name = "PositionHistory.findByMtmFxRate", query = "SELECT p FROM PositionHistory p WHERE p.mtmFxRate = :mtmFxRate")
})

public class PositionHistory implements Serializable,Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_history_id")
    private Integer positionHistoryId;
    @Column(name = "position_date")
    @Temporal(TemporalType.DATE)
    private Date positionDate;
    @Column(name = "position_mode")
    private String positionMode;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "quantity")
    private BigDecimal quantity;
    @Column(name = "quantity_type")
    private String quantityType;
    @Column(name = "price")
    private BigDecimal price;
//    @Column(name = "mtm")
//    private BigDecimal mtm;
//    @Column(name = "mtm_ptf_cur")
//    private BigDecimal mtmPtfCur;
//    @Column(name = "mtm_fx_rate")
//    private BigDecimal mtmFxRate;
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    @ManyToOne
    @NotAccessibleField(level=1)
    private Position position;

    public PositionHistory() {
    }

    public PositionHistory(Date positionDate, BigDecimal quantity, String quantityType, BigDecimal price, Position position) {
        this.positionDate = positionDate;
        this.quantity = quantity;
        this.quantityType = quantityType;
        this.price = price;
        this.position = position;
    }


    public PositionHistory(Integer positionHistoryId) {
        this.positionHistoryId = positionHistoryId;
    }

    public Integer getPositionHistoryId() {
        return positionHistoryId;
    }

    public void setPositionHistoryId(Integer positionHistoryId) {
        this.positionHistoryId = positionHistoryId;
    }

    public Date getPositionDate() {
        return positionDate;
    }

    public void setPositionDate(Date positionDate) {
        this.positionDate = positionDate;
    }

    public String getPositionMode() {
        return positionMode;
    }

    public void setPositionMode(String positionMode) {
        this.positionMode = positionMode;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public String getQuantityType() {
        return quantityType;
    }

    public void setQuantityType(String quantityType) {
        this.quantityType = quantityType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

//    public BigDecimal getMtm() {
//        return mtm;
//    }
//
//    public void setMtm(BigDecimal mtm) {
//        this.mtm = mtm;
//    }
//
//    public BigDecimal getMtmPtfCur() {
//        return mtmPtfCur;
//    }
//
//    public void setMtmPtfCur(BigDecimal mtmPtfCur) {
//        this.mtmPtfCur = mtmPtfCur;
//    }
//
//    public BigDecimal getMtmFxRate() {
//        return mtmFxRate;
//    }
//
//    public void setMtmFxRate(BigDecimal mtmFxRate) {
//        this.mtmFxRate = mtmFxRate;
//    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionHistoryId != null ? positionHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof PositionHistory)) {
            return false;
        }
        PositionHistory other = (PositionHistory) object;
        if ((this.positionHistoryId == null && other.positionHistoryId != null) || (this.positionHistoryId != null && !this.positionHistoryId.equals(other.positionHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str="PositionHistory[ Id=" + positionHistoryId + StringUtils.SPACE;
        if (position!=null){
            str+=" position : "+position.toString();
        }
        if (positionDate!=null){
            str+=" date = "+positionDate.toString();
        }
        if (quantity!=null){
            str+=" qtty = "+quantity.toString();
        }


        return str;
    }

    @Override
    public PositionHistory clone() {
        PositionHistory myClone = null;
        try {
            myClone = (PositionHistory) super.clone();
            myClone.setPositionHistoryId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }
}
