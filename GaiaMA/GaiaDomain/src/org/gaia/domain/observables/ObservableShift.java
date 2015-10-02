/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gaia.domain.observables;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "observable_shift")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "ObservableShift.findAll", query = "SELECT o FROM ObservableShift o"),
    @NamedQuery(name = "ObservableShift.findByObservableShiftId", query = "SELECT o FROM ObservableShift o WHERE o.observableShiftId = :observableShiftId"),
    @NamedQuery(name = "ObservableShift.findByObservableType", query = "SELECT o FROM ObservableShift o WHERE o.observableType = :observableType"),
    @NamedQuery(name = "ObservableShift.findByShift", query = "SELECT o FROM ObservableShift o WHERE o.shift = :shift"),
    @NamedQuery(name = "ObservableShift.findByCoordinate1", query = "SELECT o FROM ObservableShift o WHERE o.coordinate1 = :coordinate1"),
    @NamedQuery(name = "ObservableShift.findByCoordinateType1", query = "SELECT o FROM ObservableShift o WHERE o.coordinateType1 = :coordinateType1"),
    @NamedQuery(name = "ObservableShift.findByCoordinate2", query = "SELECT o FROM ObservableShift o WHERE o.coordinate2 = :coordinate2"),
    @NamedQuery(name = "ObservableShift.findByCoordinateType2", query = "SELECT o FROM ObservableShift o WHERE o.coordinateType2 = :coordinateType2"),
    @NamedQuery(name = "ObservableShift.findByCoordinate3", query = "SELECT o FROM ObservableShift o WHERE o.coordinate3 = :coordinate3"),
    @NamedQuery(name = "ObservableShift.findByCoordinateType3", query = "SELECT o FROM ObservableShift o WHERE o.coordinateType3 = :coordinateType3")})

public class ObservableShift implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "observable_shift_id")
    private Integer observableShiftId;
    @Column(name = "observable_type")
    private String observableType;
    @Column(name = "shift")
    private BigDecimal shift;
    @Column(name = "coordinate_1")
    private String coordinate1;
    @Column(name = "coordinate_type_1")
    private String coordinateType1;
    @Column(name = "coordinate_2")
    private String coordinate2;
    @Column(name = "coordinate_type_2")
    private String coordinateType2;
    @Column(name = "coordinate_3")
    private String coordinate3;
    @Column(name = "coordinate_type_3")
    private String coordinateType3;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "stress_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Stress stress;

    @Transient
    private BigDecimal shiftResult;

    public ObservableShift() {
    }

    public ObservableShift(Integer observableShiftId) {
        this.observableShiftId = observableShiftId;
    }

    public Integer getObservableShiftId() {
        return observableShiftId;
    }

    public void setObservableShiftId(Integer observableShiftId) {
        this.observableShiftId = observableShiftId;
    }

    public String getObservableType() {
        return observableType;
    }

    public void setObservableType(String observableType) {
        this.observableType = observableType;
    }

    public BigDecimal getShift() {
        return shift;
    }

    public void setShift(BigDecimal shift) {
        this.shift = shift;
    }

    public String getCoordinate1() {
        return coordinate1;
    }

    public void setCoordinate1(String coordinate1) {
        this.coordinate1 = coordinate1;
    }

    public String getCoordinateType1() {
        return coordinateType1;
    }

    public void setCoordinateType1(String coordinateType1) {
        this.coordinateType1 = coordinateType1;
    }

    public String getCoordinate2() {
        return coordinate2;
    }

    public void setCoordinate2(String coordinate2) {
        this.coordinate2 = coordinate2;
    }

    public String getCoordinateType2() {
        return coordinateType2;
    }

    public void setCoordinateType2(String coordinateType2) {
        this.coordinateType2 = coordinateType2;
    }

    public String getCoordinate3() {
        return coordinate3;
    }

    public void setCoordinate3(String coordinate3) {
        this.coordinate3 = coordinate3;
    }

    public String getCoordinateType3() {
        return coordinateType3;
    }

    public void setCoordinateType3(String coordinateType3) {
        this.coordinateType3 = coordinateType3;
    }

    public Stress getStress() {
        return stress;
    }

    public void setStress(Stress stress) {
        this.stress = stress;
    }

    public BigDecimal getShiftResult() {
        return shiftResult;
    }

    public void setShiftResult(BigDecimal shiftResult) {
        this.shiftResult = shiftResult;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (observableShiftId != null ? observableShiftId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObservableShift)) {
            return false;
        }
        ObservableShift other = (ObservableShift) object;
        return (this.observableShiftId != null || other.observableShiftId == null) && (this.observableShiftId == null || this.observableShiftId.equals(other.observableShiftId));
    }

    @Override
    public String toString() {
        return "ObservableShift id " + observableShiftId + StringUtils.SPACE + observableType + " : " + (coordinate1 == null ? "" : coordinate1 + "=") + shift;
    }

    @Override
    public ObservableShift clone() {
        ObservableShift clone = null;
        try {
            clone = (ObservableShift) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

}
