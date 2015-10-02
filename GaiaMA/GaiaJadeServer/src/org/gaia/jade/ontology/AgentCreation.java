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


import jade.content.*;
import jade.util.leap.List;

public class AgentCreation implements AgentAction {

   private String agentName;
   private String agentClassName;
   private Boolean addNoSuffixToName=false;
   private List args;

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getAgentClassName() {
        return agentClassName;
    }

    public void setAgentClassName(String agentClassName) {
        this.agentClassName = agentClassName;
    }

    public List getArgs() {
        return args;
    }

    public void setArgs(List args) {
        this.args = args;
    }

    public Boolean getAddNoSuffixToName() {
        return addNoSuffixToName;
    }

    public void setAddNoSuffixToName(Boolean addNoSuffixToName) {
        this.addNoSuffixToName = addNoSuffixToName;
    }

   

}
