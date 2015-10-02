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
import org.gaia.simulationService.time.TimeModel;

/**
 * The Class TimeModelConfiguration holds the TimeModel settings for a projects.
 *
 * @see Project
 * @see TimeModel
 *
 */
public class TimeModelConfiguration implements Serializable {

    private static final long serialVersionUID = 6617541726643624421L;
    @XmlAttribute
    private boolean useTimeModel;
    @XmlAttribute
    private String timeModelClass;

    /**
     * Instantiates a new time model configuration.
     */
    public TimeModelConfiguration() {
    }

    /**
     * Instantiates a new time model configuration.
     *
     * @param useTimeModel the use time model
     * @param timeModelClass the time model class
     */
    public TimeModelConfiguration(boolean useTimeModel, String timeModelClass) {
        this.setUseTimeModel(useTimeModel);
        this.setTimeModelClass(timeModelClass);
    }

    /**
     * Sets the use time model.
     *
     * @param useTimeModel the new use time model
     */
    public void setUseTimeModel(boolean useTimeModel) {
        this.useTimeModel = useTimeModel;
    }

    /**
     * Checks if is use time model.
     *
     * @return true, if is use time model
     */
    @XmlTransient
    public boolean isUseTimeModel() {
        return useTimeModel;
    }

    /**
     * Sets the time model class.
     *
     * @param timeModelClass the new time model class
     */
    public void setTimeModelClass(String timeModelClass) {
        this.timeModelClass = timeModelClass;
    }

    /**
     * Gets the time model class.
     *
     * @return the time model class
     */
    @XmlTransient
    public String getTimeModelClass() {
        return timeModelClass;
    }
}
