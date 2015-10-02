/*
 * To change this template, choose Tools | Templates
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
 * @author Jawhar Kamoun
 */
@Entity
@Table(name = "user_preference")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPreference.findAll", query = "SELECT u FROM UserPreference u"),
    @NamedQuery(name = "UserPreference.findByUserPreferenceId", query = "SELECT u FROM UserPreference u WHERE u.userPreferenceId = :userPreferenceId"),
    @NamedQuery(name = "UserPreference.findBySettingName", query = "SELECT u FROM UserPreference u WHERE u.settingName = :settingName"),
    @NamedQuery(name = "UserPreference.findBySettingValue", query = "SELECT u FROM UserPreference u WHERE u.settingValue = :settingValue")})
public class UserPreference implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_preference_id")
    private Integer userPreferenceId;
    @Column(name = "setting_name")
    private String settingName;
    @Column(name = "setting_value")
    private String settingValue;
    @Column(name = "user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public UserPreference() {
    }

    public UserPreference(Integer userPreferenceId) {
        this.userPreferenceId = userPreferenceId;
    }

    public Integer getUserPreferenceId() {
        return userPreferenceId;
    }

    public void setUserPreferenceId(Integer userPreferenceId) {
        this.userPreferenceId = userPreferenceId;
    }

    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPreferenceId != null ? userPreferenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof UserPreference)) {
            return false;
        }
        UserPreference other = (UserPreference) object;
        if ((this.userPreferenceId == null && other.userPreferenceId != null) || (this.userPreferenceId != null && !this.userPreferenceId.equals(other.userPreferenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "UserPreference " + userPreferenceId + StringUtils.SPACE + settingName + "=" + settingValue;
    }

}
