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
package org.gaia.jade.ontology;

/**
 *
 * @author user
 */
public class PricerAgentCreation extends AgentCreation {

    private PricingSetting pricingSetting;

    public PricingSetting getPricingSetting() {
        return pricingSetting;
    }

    public void setPricingSetting(PricingSetting pricingSetting) {
        this.pricingSetting = pricingSetting;
    }
}
