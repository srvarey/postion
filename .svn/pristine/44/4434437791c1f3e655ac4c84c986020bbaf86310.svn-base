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

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.envers.Audited;

/**
 *
 * @author Bouazzi Saber
 */
@Audited
@Entity
@Table(name = "gaia_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GaiaUser.findAll", query = "SELECT g FROM GaiaUser g"),
    @NamedQuery(name = "GaiaUser.findByUserId", query = "SELECT g FROM GaiaUser g WHERE g.userId = :userId"),
    @NamedQuery(name = "GaiaUser.findByShortName", query = "SELECT g FROM GaiaUser g WHERE g.shortName = :shortName"),
    @NamedQuery(name = "GaiaUser.findByFirstName", query = "SELECT g FROM GaiaUser g WHERE g.firstName = :firstName"),
    @NamedQuery(name = "GaiaUser.findByLastName", query = "SELECT g FROM GaiaUser g WHERE g.lastName = :lastName"),
    @NamedQuery(name = "GaiaUser.findByUserType", query = "SELECT g FROM GaiaUser g WHERE g.userType = :userType"),
    @NamedQuery(name = "GaiaUser.findByStatus", query = "SELECT g FROM GaiaUser g WHERE g.status = :status"),
    @NamedQuery(name = "GaiaUser.findByCreationDateTime", query = "SELECT g FROM GaiaUser g WHERE g.creationDateTime = :creationDateTime"),
    @NamedQuery(name = "GaiaUser.findByUpdateDateTime", query = "SELECT g FROM GaiaUser g WHERE g.updateDateTime = :updateDateTime")})


public class GaiaUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "user_type")
    private String userType;
    @Column(name = "status")
    private String status;
    @Column(name = "user_passwd")
    private String password;
    @Column(name = "creation_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDateTime;
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDateTime;
    @JoinTable(name = "user_group_link", joinColumns = {
        @JoinColumn(name = "user_id", referencedColumnName = "user_id")}, inverseJoinColumns = {
        @JoinColumn(name = "user_group_id", referencedColumnName = "user_group_id")})
    @ManyToMany
    private Collection<UserGroup> userGroupCollection;

    public GaiaUser() {
    }

    public GaiaUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }



    public Date getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public Date getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    @XmlTransient
    public Collection<UserGroup> getUserGroupCollection() {
        return userGroupCollection;
    }

    public void setUserGroupCollection(Collection<UserGroup> userGroupCollection) {
        this.userGroupCollection = userGroupCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof GaiaUser)) {
            return false;
        }
        GaiaUser other = (GaiaUser) object;
        return (this.userId != null || other.userId == null) && (this.userId == null || this.userId.equals(other.userId));
    }

    @Override
    public String toString() {

        if (!StringUtils.isEmptyString(shortName)){
            return shortName;
        } else  if (!StringUtils.isEmptyString(lastName)){
            return firstName+StringUtils.SPACE+lastName;
        } else {
            return StringUtils.EMPTY_STRING;
        }
    }

}
