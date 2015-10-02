/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "user_pricing_preference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPricingPreference.findAll", query = "SELECT u FROM UserPricingPreference u"),
    @NamedQuery(name = "UserPricingPreference.findByUserPricingPreferenceId", query = "SELECT u FROM UserPricingPreference u WHERE u.userPricingPreferenceId = :userPricingPreferenceId"),
    @NamedQuery(name = "UserPricingPreference.findByProductType", query = "SELECT u FROM UserPricingPreference u WHERE u.productType = :productType"),
    @NamedQuery(name = "UserPricingPreference.findByPricingMeasure", query = "SELECT u FROM UserPricingPreference u WHERE u.pricingMeasure = :pricingMeasure")})
public class UserPricingPreference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_pricing_preference_id")
    private Integer userPricingPreferenceId;
    @Column(name = "product_type")
    private String productType;
    @Column(name = "pricing_measure")
    private String pricingMeasure;
    @Column(name = "user_id")
    private Integer userId;

    public UserPricingPreference() {
    }

    public UserPricingPreference(Integer userPricingPreferenceId) {
        this.userPricingPreferenceId = userPricingPreferenceId;
    }

    public Integer getUserPricingPreferenceId() {
        return userPricingPreferenceId;
    }

    public void setUserPricingPreferenceId(Integer userPricingPreferenceId) {
        this.userPricingPreferenceId = userPricingPreferenceId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getPricingMeasure() {
        return pricingMeasure;
    }

    public void setPricingMeasure(String pricingMeasure) {
        this.pricingMeasure = pricingMeasure;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPricingPreferenceId != null ? userPricingPreferenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPricingPreference)) {
            return false;
        }
        UserPricingPreference other = (UserPricingPreference) object;
        if ((this.userPricingPreferenceId == null && other.userPricingPreferenceId != null) || (this.userPricingPreferenceId != null && !this.userPricingPreferenceId.equals(other.userPricingPreferenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserPricingPreference " + productType + StringUtils.SPACE+pricingMeasure;
    }

}
