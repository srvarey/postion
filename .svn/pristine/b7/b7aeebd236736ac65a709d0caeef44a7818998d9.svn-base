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
package org.gaia.domain.utils;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author user
 */
@Entity
@Table(name = "platforms")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Platforms.findAll", query = "SELECT p FROM Platforms p"),
    @NamedQuery(name = "Platforms.findByIdPlatform", query = "SELECT p FROM Platforms p WHERE p.idPlatform = :idPlatform"),
    @NamedQuery(name = "Platforms.findByContactAgent", query = "SELECT p FROM Platforms p WHERE p.contactAgent = :contactAgent"),
    @NamedQuery(name = "Platforms.findByPlatformName", query = "SELECT p FROM Platforms p WHERE p.platformName = :platformName"),
    @NamedQuery(name = "Platforms.findByIsServer", query = "SELECT p FROM Platforms p WHERE p.isServer = :isServer"),
    @NamedQuery(name = "Platforms.findByIp", query = "SELECT p FROM Platforms p WHERE p.ip = :ip"),
    @NamedQuery(name = "Platforms.findByUrl", query = "SELECT p FROM Platforms p WHERE p.url = :url"),
    @NamedQuery(name = "Platforms.findByJadePort", query = "SELECT p FROM Platforms p WHERE p.jadePort = :jadePort"),
    @NamedQuery(name = "Platforms.findByHttp4mtp", query = "SELECT p FROM Platforms p WHERE p.http4mtp = :http4mtp"),
    @NamedQuery(name = "Platforms.findByVersMajor", query = "SELECT p FROM Platforms p WHERE p.versMajor = :versMajor"),
    @NamedQuery(name = "Platforms.findByVersMinor", query = "SELECT p FROM Platforms p WHERE p.versMinor = :versMinor"),
    @NamedQuery(name = "Platforms.findByVersBuild", query = "SELECT p FROM Platforms p WHERE p.versBuild = :versBuild"),
    @NamedQuery(name = "Platforms.findByOsName", query = "SELECT p FROM Platforms p WHERE p.osName = :osName"),
    @NamedQuery(name = "Platforms.findByOsVersion", query = "SELECT p FROM Platforms p WHERE p.osVersion = :osVersion"),
    @NamedQuery(name = "Platforms.findByOsArch", query = "SELECT p FROM Platforms p WHERE p.osArch = :osArch"),
    @NamedQuery(name = "Platforms.findByCpuVendor", query = "SELECT p FROM Platforms p WHERE p.cpuVendor = :cpuVendor"),
    @NamedQuery(name = "Platforms.findByCpuModel", query = "SELECT p FROM Platforms p WHERE p.cpuModel = :cpuModel"),
    @NamedQuery(name = "Platforms.findByCpuN", query = "SELECT p FROM Platforms p WHERE p.cpuN = :cpuN"),
    @NamedQuery(name = "Platforms.findByCpuSpeedMhz", query = "SELECT p FROM Platforms p WHERE p.cpuSpeedMhz = :cpuSpeedMhz"),
    @NamedQuery(name = "Platforms.findByMemoryTotalMb", query = "SELECT p FROM Platforms p WHERE p.memoryTotalMb = :memoryTotalMb"),
    @NamedQuery(name = "Platforms.findByBenchmarkValue", query = "SELECT p FROM Platforms p WHERE p.benchmarkValue = :benchmarkValue"),
    @NamedQuery(name = "Platforms.findByOnlineSince", query = "SELECT p FROM Platforms p WHERE p.onlineSince = :onlineSince"),
    @NamedQuery(name = "Platforms.findByLastContactAt", query = "SELECT p FROM Platforms p WHERE p.lastContactAt = :lastContactAt"),
    @NamedQuery(name = "Platforms.findByLocalOnlineSince", query = "SELECT p FROM Platforms p WHERE p.localOnlineSince = :localOnlineSince"),
    @NamedQuery(name = "Platforms.findByLocalLastContactAt", query = "SELECT p FROM Platforms p WHERE p.localLastContactAt = :localLastContactAt"),
    @NamedQuery(name = "Platforms.findByCurrentlyAvailable", query = "SELECT p FROM Platforms p WHERE p.currentlyAvailable = :currentlyAvailable"),
    @NamedQuery(name = "Platforms.findByCurrentLoadCpu", query = "SELECT p FROM Platforms p WHERE p.currentLoadCpu = :currentLoadCpu"),
    @NamedQuery(name = "Platforms.findByCurrentLoadMemorySystem", query = "SELECT p FROM Platforms p WHERE p.currentLoadMemorySystem = :currentLoadMemorySystem"),
    @NamedQuery(name = "Platforms.findByCurrentLoadMemoryJvm", query = "SELECT p FROM Platforms p WHERE p.currentLoadMemoryJvm = :currentLoadMemoryJvm"),
    @NamedQuery(name = "Platforms.findByCurrentLoadNoThreads", query = "SELECT p FROM Platforms p WHERE p.currentLoadNoThreads = :currentLoadNoThreads"),
    @NamedQuery(name = "Platforms.findByCurrentLoadThresholdExceeded", query = "SELECT p FROM Platforms p WHERE p.currentLoadThresholdExceeded = :currentLoadThresholdExceeded")})
public class Platforms implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_platform")
    private Integer idPlatform;
    @Basic(optional = false)
    @Column(name = "contact_agent")
    private String contactAgent;
    @Basic(optional = false)
    @Column(name = "platform_name")
    private String platformName;
    @Column(name = "is_server")
    private Short isServer;
    @Column(name = "ip")
    private String ip;
    @Column(name = "url")
    private String url;
    @Column(name = "jade_port")
    private Long jadePort;
    @Column(name = "http4mtp")
    private String http4mtp;
    @Column(name = "vers_major")
    private Long versMajor;
    @Column(name = "vers_minor")
    private Long versMinor;
    @Column(name = "vers_build")
    private Long versBuild;
    @Column(name = "os_name")
    private String osName;
    @Column(name = "os_version")
    private String osVersion;
    @Column(name = "os_arch")
    private String osArch;
    @Column(name = "cpu_vendor")
    private String cpuVendor;
    @Column(name = "cpu_model")
    private String cpuModel;
    @Column(name = "cpu_n")
    private Short cpuN;
    @Column(name = "cpu_speed_mhz")
    private Long cpuSpeedMhz;
    @Column(name = "memory_total_mb")
    private Long memoryTotalMb;
   /** // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation */
    @Column(name = "benchmark_value")
    private Double benchmarkValue;
    @Column(name = "online_since")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onlineSince;
    @Column(name = "last_contact_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastContactAt;
    @Column(name = "local_online_since")
    @Temporal(TemporalType.TIMESTAMP)
    private Date localOnlineSince;
    @Column(name = "local_last_contact_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date localLastContactAt;
    @Column(name = "currently_available")
    private Short currentlyAvailable;
    @Column(name = "current_load_cpu")
    private Double currentLoadCpu;
    @Column(name = "current_load_memory_system")
    private Double currentLoadMemorySystem;
    @Column(name = "current_load_memory_jvm")
    private Double currentLoadMemoryJvm;
    @Column(name = "current_load_no_threads")
    private Long currentLoadNoThreads;
    @Column(name = "current_load_threshold_exceeded")
    private Short currentLoadThresholdExceeded;

    public Platforms() {
    }

    public Platforms(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public Platforms(Integer idPlatform, String contactAgent, String platformName) {
        this.idPlatform = idPlatform;
        this.contactAgent = contactAgent;
        this.platformName = platformName;
    }

    public Integer getIdPlatform() {
        return idPlatform;
    }

    public void setIdPlatform(Integer idPlatform) {
        this.idPlatform = idPlatform;
    }

    public String getContactAgent() {
        return contactAgent;
    }

    public void setContactAgent(String contactAgent) {
        this.contactAgent = contactAgent;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public Short getIsServer() {
        return isServer;
    }

    public void setIsServer(Short isServer) {
        this.isServer = isServer;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getJadePort() {
        return jadePort;
    }

    public void setJadePort(Long jadePort) {
        this.jadePort = jadePort;
    }

    public String getHttp4mtp() {
        return http4mtp;
    }

    public void setHttp4mtp(String http4mtp) {
        this.http4mtp = http4mtp;
    }

    public Long getVersMajor() {
        return versMajor;
    }

    public void setVersMajor(Long versMajor) {
        this.versMajor = versMajor;
    }

    public Long getVersMinor() {
        return versMinor;
    }

    public void setVersMinor(Long versMinor) {
        this.versMinor = versMinor;
    }

    public Long getVersBuild() {
        return versBuild;
    }

    public void setVersBuild(Long versBuild) {
        this.versBuild = versBuild;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getOsArch() {
        return osArch;
    }

    public void setOsArch(String osArch) {
        this.osArch = osArch;
    }

    public String getCpuVendor() {
        return cpuVendor;
    }

    public void setCpuVendor(String cpuVendor) {
        this.cpuVendor = cpuVendor;
    }

    public String getCpuModel() {
        return cpuModel;
    }

    public void setCpuModel(String cpuModel) {
        this.cpuModel = cpuModel;
    }

    public Short getCpuN() {
        return cpuN;
    }

    public void setCpuN(Short cpuN) {
        this.cpuN = cpuN;
    }

    public Long getCpuSpeedMhz() {
        return cpuSpeedMhz;
    }

    public void setCpuSpeedMhz(Long cpuSpeedMhz) {
        this.cpuSpeedMhz = cpuSpeedMhz;
    }

    public Long getMemoryTotalMb() {
        return memoryTotalMb;
    }

    public void setMemoryTotalMb(Long memoryTotalMb) {
        this.memoryTotalMb = memoryTotalMb;
    }

    public Double getBenchmarkValue() {
        return benchmarkValue;
    }

    public void setBenchmarkValue(Double benchmarkValue) {
        this.benchmarkValue = benchmarkValue;
    }

    public Date getOnlineSince() {
        return onlineSince;
    }

    public void setOnlineSince(Date onlineSince) {
        this.onlineSince = onlineSince;
    }

    public Date getLastContactAt() {
        return lastContactAt;
    }

    public void setLastContactAt(Date lastContactAt) {
        this.lastContactAt = lastContactAt;
    }

    public Date getLocalOnlineSince() {
        return localOnlineSince;
    }

    public void setLocalOnlineSince(Date localOnlineSince) {
        this.localOnlineSince = localOnlineSince;
    }

    public Date getLocalLastContactAt() {
        return localLastContactAt;
    }

    public void setLocalLastContactAt(Date localLastContactAt) {
        this.localLastContactAt = localLastContactAt;
    }

    public Short getCurrentlyAvailable() {
        return currentlyAvailable;
    }

    public void setCurrentlyAvailable(Short currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }

    public Double getCurrentLoadCpu() {
        return currentLoadCpu;
    }

    public void setCurrentLoadCpu(Double currentLoadCpu) {
        this.currentLoadCpu = currentLoadCpu;
    }

    public Double getCurrentLoadMemorySystem() {
        return currentLoadMemorySystem;
    }

    public void setCurrentLoadMemorySystem(Double currentLoadMemorySystem) {
        this.currentLoadMemorySystem = currentLoadMemorySystem;
    }

    public Double getCurrentLoadMemoryJvm() {
        return currentLoadMemoryJvm;
    }

    public void setCurrentLoadMemoryJvm(Double currentLoadMemoryJvm) {
        this.currentLoadMemoryJvm = currentLoadMemoryJvm;
    }

    public Long getCurrentLoadNoThreads() {
        return currentLoadNoThreads;
    }

    public void setCurrentLoadNoThreads(Long currentLoadNoThreads) {
        this.currentLoadNoThreads = currentLoadNoThreads;
    }

    public Short getCurrentLoadThresholdExceeded() {
        return currentLoadThresholdExceeded;
    }

    public void setCurrentLoadThresholdExceeded(Short currentLoadThresholdExceeded) {
        this.currentLoadThresholdExceeded = currentLoadThresholdExceeded;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPlatform != null ? idPlatform.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        
        if (!(object instanceof Platforms)) {
            return false;
        }
        Platforms other = (Platforms) object;
        if ((this.idPlatform == null && other.idPlatform != null) || (this.idPlatform != null && !this.idPlatform.equals(other.idPlatform))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "gaiaslaveserver.Platforms[ idPlatform=" + idPlatform + " ]";
    }
    
}
