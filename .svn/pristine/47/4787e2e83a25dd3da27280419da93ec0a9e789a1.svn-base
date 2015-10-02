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
package org.gaia.domain.referentials;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "rating")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rating.findAll", query = "SELECT r FROM Rating r"),
    @NamedQuery(name = "Rating.findByRatingId", query = "SELECT r FROM Rating r WHERE r.ratingId = :ratingId"),
    @NamedQuery(name = "Rating.findByAgency", query = "SELECT r FROM Rating r WHERE r.agency = :agency"),
    @NamedQuery(name = "Rating.findByRating", query = "SELECT r FROM Rating r WHERE r.rating = :rating"),
    @NamedQuery(name = "Rating.findByRank", query = "SELECT r FROM Rating r WHERE r.rank = :rank")})



public class Rating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rating_id")
    private Integer ratingId;
    @Basic(optional = false)
    @Column(name = "agency")
    private String agency;
    @Basic(optional = false)
    @Column(name = "rating")
    private String rating;
    @Column(name = "rank")
    private Short rank;

    
    @XStreamOmitField
    @ManyToMany(mappedBy = "ratings") // of which this is sub products
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<LegalEntity> legalEntities;
    
    public Rating() {
    }

    public Rating(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public Rating(Integer ratingId, String agency, String rating) {
        this.ratingId = ratingId;
        this.agency = agency;
        this.rating = rating;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public Short getRank() {
        return rank;
    }

    public void setRank(Short rank) {
        this.rank = rank;
    }

    public Collection<LegalEntity> getLegalEntities() {
        return legalEntities;
    }

    public void setLegalEntities(Collection<LegalEntity> legalEntities) {
        this.legalEntities = legalEntities;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingId != null ? ratingId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
       
        if (!(object instanceof Rating)) {
            return false;
        }
        Rating other = (Rating) object;
        if ((this.ratingId == null && other.ratingId != null) || (this.ratingId != null && !this.ratingId.equals(other.ratingId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaia.domain.referentials.Rating[ ratingId=" + ratingId + " ]";
    }
}
