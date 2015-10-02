/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.domain.utils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Benjamin Frerejean
 *
 */
public class Message implements Serializable {

    private Integer objectId;
    private String objectType;
    private String messageText;
    private Date timestamp;

    public Date getTimestamp() {
        return timestamp;
    }

    public Message(Integer objectId, String objectType, String messageText) {
        this.objectId = objectId;
        this.objectType = objectType;
        this.messageText = messageText;
        this.timestamp = new Date();
    }

    public Integer getObjectId() {
        return objectId;
    }

    public String getObjectType() {
        return objectType;
    }

    public String getMessageText() {
        return messageText;
    }

    @Override
    public String toString() {
        return messageText;
    }
}
