/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gaia.domain.observables;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.util.List;
import java.util.Locale;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.AuditMappedBy;

/**
 *
 * @author Benjamin Frerejean
 */
@Entity
@Table(name = "stress")
@XmlRootElement
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NamedQueries({
    @NamedQuery(name = "Stress.findAll", query = "SELECT s FROM Stress s"),
    @NamedQuery(name = "Stress.findByStressId", query = "SELECT s FROM Stress s WHERE s.stressId = :stressId"),
    @NamedQuery(name = "Stress.findByStressName", query = "SELECT s FROM Stress s WHERE s.stressName = :stressName")})
public class Stress implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "stress_id")
    private Integer stressId;
    @Column(name = "stress_name")
    private String stressName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stress_id")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @LazyCollection(LazyCollectionOption.FALSE)
    @AuditMappedBy(mappedBy = "stress")
    private List<ObservableShift> observableShifts;

    public Stress() {
    }

    public Stress(Integer stressId) {
        this.stressId = stressId;
    }

    public Integer getStressId() {
        return stressId;
    }

    public void setStressId(Integer stressId) {
        this.stressId = stressId;
    }

    public String getStressName() {
        return stressName;
    }

    public void setStressName(String stressName) {
        this.stressName = stressName;
    }

    public List<ObservableShift> getObservableShifts() {
        return observableShifts;
    }

    public void setObservableShifts(List<ObservableShift> observableShifts) {
        this.observableShifts = observableShifts;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stressId != null ? stressId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stress)) {
            return false;
        }
        Stress other = (Stress) object;
        return (this.stressId != null || other.stressId == null) && (this.stressId == null || this.stressId.equals(other.stressId));
    }

    @Override
    public String toString() {
        String str = "Stress ";
        if (stressId != null) {
            str += stressId + StringUtils.SPACE;
        }
        if (stressName != null) {
            str += stressName + StringUtils.SPACE;
        }
        str += shiftsDesc();
        return str;
    }

    public String shiftsDesc() {
        String str = "";
        Format mydecimalFormat = new DecimalFormat("#,##0.0000", new DecimalFormatSymbols(Locale.US));
        if (observableShifts != null) {
            for (ObservableShift shift : observableShifts) {
                if (shift.getCoordinate1() != null) {
                    str += shift.getCoordinate1() + "=";
                }
                str += mydecimalFormat.format(shift.getShift()) + StringUtils.SPACE;
            }
        }
        return str;
    }

}
