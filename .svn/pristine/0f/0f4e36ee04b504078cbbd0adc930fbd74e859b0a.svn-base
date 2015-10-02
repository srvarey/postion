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
package org.gaia.core.agents;

import jade.core.Agent;

/**
 * Provides a container instance in order to deal with agent classes in
 * displayable lists, like JList and other.
 *
 */
public class AgentClassElement {

    /**
     * The agent class.
     */
    private Class<? extends Agent> agentClass = null;

    /**
     * Constructor for this class. Needs a class which extends from the class
     * 'jade.core.Agent'
     *
     * @param agentClass the agent class
     */
    public AgentClassElement(Class<? extends Agent> agentClass) {
        this.agentClass = agentClass;
    }

    /**
     * Returns the name of the class.
     *
     * @return String
     */
    @Override
    public String toString() {
        return agentClass.getName();
    }

    /**
     * Returns the agent class.
     *
     * @return Class<? extends Agent>
     */
    public Class<? extends Agent> getElementClass() {
        return agentClass;
    }
}
