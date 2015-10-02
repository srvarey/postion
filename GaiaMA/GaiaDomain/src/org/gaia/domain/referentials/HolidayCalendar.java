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
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "calendar")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement

public class HolidayCalendar implements Serializable, Cloneable {
    @Id
    @Basic(optional = false)
    @Column(name = "calendar_code")
    private String calendarCode;
    @Column(name = "currency")
    private String currency;
    @Column(name = "description")
    private String description;
    @Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
    @OrderBy("calendarDate")
    @OneToMany(mappedBy="calendar", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    private List<CalendarDate> calendarDateList;


    public List<CalendarDate> getCalendarDateList() {
        return calendarDateList;
    }

    public void setCalendarDateList(List<CalendarDate> calendarDateList) {
        this.calendarDateList = calendarDateList;
    }

    public HolidayCalendar() {
        calendarDateList=new ArrayList();
    }

    public HolidayCalendar(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    public String getCalendarCode() {
        return calendarCode;
    }

    public void setCalendarCode(String calendarCode) {
        this.calendarCode = calendarCode;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calendarCode != null ? calendarCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof HolidayCalendar)) {
            return false;
        }
        HolidayCalendar other = (HolidayCalendar) object;
        if ((this.calendarCode == null && other.calendarCode != null) || (this.calendarCode != null && !this.calendarCode.equals(other.calendarCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "accessors.referentials.CalendarCode[ calendarCode=" + calendarCode + " ]";
    }

    public HolidayCalendar clone(){
        HolidayCalendar myClone = null;
        try {
            myClone = (HolidayCalendar) super.clone();
            myClone.setCalendarDateList(this.getCalendarDateList());
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return myClone;
    }

}
