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
package org.gaia.simulationService.ontology;

import jade.content.*;

public class PlatformAddress implements Concept {

    /**
     * Protege name: ip
     */
    private String ip;

    public void setIp(String value) {
        this.ip = value;
    }

    public String getIp() {
        return this.ip;
    }
    /**
     * Protege name: url
     */
    private String url;

    public void setUrl(String value) {
        this.url = value;
    }

    public String getUrl() {
        return this.url;
    }
    /**
     * Protege name: port
     */
    private int port;

    public void setPort(int value) {
        this.port = value;
    }

    public int getPort() {
        return this.port;
    }
    /**
     * Protege name: http4mtp
     */
    private String http4mtp;

    public void setHttp4mtp(String value) {
        this.http4mtp = value;
    }

    public String getHttp4mtp() {
        return this.http4mtp;
    }
}
