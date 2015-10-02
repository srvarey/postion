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
package org.gaia.domain.observables;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import org.gaia.domain.reports.Filter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.Audited;

/**
 *
 * @author Benjamin Frerejean
 * 
 */
@Audited
@Entity
@Table(name = "market_data_source")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@XmlRootElement
public class MarketDataSource implements Serializable{    
    
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "market_data_source_id")
    private Integer marketDataSourceId;
    @Column(name = "market_data_source_name")
    private String marketDataSourceName;
    @Column(name = "class_name")
    private String className;
    @Column(name = "is_real_time")
    private Boolean isRealTime;
    @Column(name = "server_host")
    private String serverHost;
    @Column(name = "server_port")
    private Integer serverPort;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JoinColumn(name = "filter_id", referencedColumnName = "filter_id")
    private Filter filter;
    
    @OneToMany(mappedBy = "marketDataSource")
    @LazyCollection(LazyCollectionOption.TRUE)
    private Collection<MarketDataCode> marketDataLinkCollection;

    public Integer getMarketDataSourceId() {
        return marketDataSourceId;
    }

    public void setMarketDataSourceId(Integer marketDataSourceId) {
        this.marketDataSourceId = marketDataSourceId;
    }

    public String getMarketDataSourceName() {
        return marketDataSourceName;
    }

    public void setMarketDataSourceName(String marketDataSourceName) {
        this.marketDataSourceName = marketDataSourceName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Boolean isRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(Boolean isRealTime) {
        this.isRealTime = isRealTime;
    }       

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    
}
