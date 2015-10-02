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
import org.gaia.domain.utils.DomainUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 */
@Audited
@Entity
@Table(name = "calendar_date")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "CalendarDate.findAll", query = "SELECT h FROM CalendarDate h"),
    @NamedQuery(name = "CalendarDate.findByCalendarDateId", query = "SELECT h FROM CalendarDate h WHERE h.calendarDateId = :calendarDateId"),
    @NamedQuery(name = "CalendarDate.findByCalendarDate", query = "SELECT h FROM CalendarDate h WHERE h.calendarDate = :calendarDate")})

public class CalendarDate implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "calendar_date_id")
    private Integer calendarDateId;

    @Basic(optional = false)
    @Column(name = "calendar_date")
    @Temporal(TemporalType.DATE)
    private Date calendarDate;
    @ManyToOne()
    @JoinColumn(name = "calendar_code", nullable = false)
    private HolidayCalendar calendar;

    public HolidayCalendar getCalendar() {
        return calendar;
    }

    public void setCalendar(HolidayCalendar calendar) {
        this.calendar = calendar;
    }

    public String getCalendarCode() {
        return calendar.getCalendarCode();
    }

    public void setCalendarCode(String calendar) {
        this.calendar.setCalendarCode(calendar);
    }

    public CalendarDate() {
    }

    public CalendarDate(Integer calendarDateId) {
        this.calendarDateId = calendarDateId;
    }

    public CalendarDate(HolidayCalendar calendar, Date calendarDate) {
        this.calendar = calendar;
        this.calendarDate = calendarDate;
    }

    public Integer getCalendarDateId() {
        return calendarDateId;
    }

    public void setCalendarDateId(Integer calendarDateId) {
        this.calendarDateId = calendarDateId;
    }

    public Date getCalendarDate() {
        return calendarDate;
    }

    public void setCalendarDate(Date calendarDate) {
        this.calendarDate = calendarDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calendarDateId != null ? calendarDateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof CalendarDate)) {
            return false;
        }
        CalendarDate other = (CalendarDate) object;
        if ((this.calendarDateId == null && other.calendarDateId != null) || (this.calendarDateId != null && !this.calendarDateId.equals(other.calendarDateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalendarDate " + DomainUtils.dateFormat.format(calendarDate);
    }

    @Override
    public CalendarDate clone() {
        CalendarDate cloned = null;
        try {
            cloned = (CalendarDate) super.clone();
            cloned.setCalendarDateId(null);
        } catch (CloneNotSupportedException cnse) {
            cnse.printStackTrace(System.err);
        }
        return cloned;
    }

}
