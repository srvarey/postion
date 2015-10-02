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
package org.gaia.core.project;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

/**
 * The Class AgentStartArgument is used in {@link Project} as attribute for the
 * start configuration and a single (of many) start arguments for a single
 * agent.
 *
 */
public class AgentStartArgument implements Serializable {

    private static final long serialVersionUID = -1171812430048976361L;
    @XmlAttribute
    private int position = 1;
    @XmlAttribute
    private String displayTitle = null;
    @XmlAttribute
    private String ontologyReference = null;

    /**
     * Default constructor
     */
    public AgentStartArgument() {
    }

    /**
     * Instantiates a new agent start argument.
     *
     * @param ontologyReference the reference to the ontology class
     */
    public AgentStartArgument(int position, String ontologyReference) {
        this.position = position;
        this.ontologyReference = ontologyReference;
    }

    /**
     * Instantiates a new agent start argument.
     *
     * @param ontologyReference the reference to the ontology class
     * @param displayTitle the display title
     */
    public AgentStartArgument(int position, String ontologyReference, String displayTitle) {
        this.position = position;
        this.ontologyReference = ontologyReference;
        this.displayTitle = displayTitle;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder display = new StringBuilder();
        if (this.displayTitle != null) {
            display.append(this.position);
            display.append(": ");
            display.append("[");
            display.append(this.displayTitle);
            display.append("] ");
            display.append(this.ontologyReference);
        } else {
            display.append(this.position);
            display.append(": ");
            display.append(this.ontologyReference);
        }
        return display.toString();
    }

    /**
     * Sets the position.
     *
     * @param position the new position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * Returns the order position of the current start argument.
     *
     * @return the position
     */
    @XmlTransient
    public int getPosition() {
        return position;
    }

    /**
     * Sets the ontology reference.
     *
     * @param ontologyReference the new ontology reference
     */
    public void setOntologyReference(String ontologyReference) {
        this.ontologyReference = ontologyReference;
    }

    /**
     * Gets the ontology reference.
     *
     * @return the ontology reference
     */
    @XmlTransient
    public String getOntologyReference() {
        return ontologyReference;
    }

    /**
     * Sets the display title.
     *
     * @param displayTitle the new display title
     */
    public void setDisplayTitle(String displayTitle) {
        this.displayTitle = displayTitle;
    }

    /**
     * Gets the display title.
     *
     * @return the display title
     */
    @XmlTransient
    public String getDisplayTitle() {
        return displayTitle;
    }
}
