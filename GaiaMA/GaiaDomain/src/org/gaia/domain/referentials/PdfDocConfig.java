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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.legalEntity.LegalEntity;
import org.hibernate.envers.Audited;

/**
 *
 * @author Jawhar Kamoun
 */
@Entity
@Table(name = "pdf_doc_config")
@XmlRootElement
@Audited
@NamedQueries({
    @NamedQuery(name = "PdfDocConfig.findAll", query = "SELECT p FROM PdfDocConfig p"),
    @NamedQuery(name = "PdfDocConfig.findByPdfDocConfigId", query = "SELECT p FROM PdfDocConfig p WHERE p.pdfDocConfigId = :pdfDocConfigId"),
    @NamedQuery(name = "PdfDocConfig.findByUrl", query = "SELECT p FROM PdfDocConfig p WHERE p.url = :url"),
    @NamedQuery(name = "PdfDocConfig.findByActivated", query = "SELECT p FROM PdfDocConfig p WHERE p.activated = :activated"),
    @NamedQuery(name = "PdfDocConfig.findByValidated", query = "SELECT p FROM PdfDocConfig p WHERE p.validated = :validated")})
public class PdfDocConfig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pdf_doc_config_id")
    private Integer pdfDocConfigId;
    @Column(name = "url")
    private String url;
    @Column(name = "column_index")
    private Integer columnIndex;
    @Column(name = "doc_password")
    private String docPassword;
    @Column(name = "destination_dir")
    private String destinationDir;
    @Column(name = "config_name")
    private String configName;
    @Column(name = "begin_word")
    private String beginWord;
    @Column(name = "end_word")
    private String endWord;
    @Column(name = "activated")
    private Boolean activated;
    @Column(name = "validated")
    private Boolean validated = Boolean.FALSE;
    @JoinColumn(name = "portofolio_id", referencedColumnName = "legal_entity_id")
    @ManyToOne
    private LegalEntity portofolio;
    @Column(name = "mapping_name")
    private String mappingName;

    public PdfDocConfig() {
    }

    public String getDestinationDir() {
        return destinationDir;
    }

    public void setDestinationDir(String destinationDir) {
        this.destinationDir = destinationDir;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }

    public String getDocPassword() {
        return docPassword;
    }

    public void setDocPassword(String docPassword) {
        this.docPassword = docPassword;
    }

    public LegalEntity getPortofolio() {
        return portofolio;
    }

    public void setPortofolio(LegalEntity _portofolio) {
        this.portofolio = _portofolio;
    }

    public PdfDocConfig(Integer pdfDocConfigId) {
        this.pdfDocConfigId = pdfDocConfigId;
    }

    public Integer getPdfDocConfigId() {
        return pdfDocConfigId;
    }

    public void setPdfDocConfigId(Integer pdfDocConfigId) {
        this.pdfDocConfigId = pdfDocConfigId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(Integer columnIndex) {
        this.columnIndex = columnIndex;
    }

    public String getBeginWord() {
        return beginWord;
    }

    public void setBeginWord(String beginWord) {
        this.beginWord = beginWord;
    }

    public String getEndWord() {
        return endWord;
    }

    public void setEndWord(String endWord) {
        this.endWord = endWord;
    }

    public Boolean getActivated() {
        return activated;
    }

    public void setActivated(Boolean activated) {
        this.activated = activated;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public String getMappingName() {
        return mappingName;
    }

    public void setMappingName(String mapping) {
        this.mappingName = mapping;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdfDocConfigId != null ? pdfDocConfigId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PdfDocConfig)) {
            return false;
        }
        PdfDocConfig other = (PdfDocConfig) object;
        return (this.pdfDocConfigId != null || other.pdfDocConfigId == null) && (this.pdfDocConfigId == null || this.pdfDocConfigId.equals(other.pdfDocConfigId));
    }

    @Override
    public String toString() {
        return "gaiaslaveserver.PdfDocConfig[ pdfDocConfigId=" + pdfDocConfigId + " ]";
    }

}
