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
package org.gaia.dao.jade;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author user
 */
public class MessageErrorListenerBehaviour extends CyclicBehaviour{
    private static final Logger logger = Logger.getLogger(MessageErrorListenerBehaviour.class);

        @Override
        public void action() {
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                /** Message received. Process it */
                try {
                    if (msg.getPerformative() == ACLMessage.FAILURE) {
                        logger.error("Message error "+msg.getContent()+" on "+myAgent.getAID().getName());
                    }
                } catch (Exception e) {
                    logger.error("Error "+ e.getMessage());
                }
            } else {
                block();
            }
        }
}
