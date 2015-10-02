
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
import org.gaia.core.agents.behaviour.PlatformShutdownBehaviour;
import org.gaia.core.agents.behaviour.ShowDFBehaviour;
import org.gaia.jade.server.Platform;

/**
 * The UtilityAgent is used to affect a running Multi-Agent system from the
 * application.<br> Since the application does not have direct access to any
 * agents, the UtilityAgent will do some tasks here. <br> Depending on the
 * start-arguments for this Agent the tasks are as follows:<br> <ul>
 * <li><b>int</b> Platform.UTIL_CMD_OpenDF: will send a message in order to show
 * the DF</li> <li><b>int</b> Platform.UTIL_CMD_ShutdownPlatform: will send a
 * message to the AMS in order to shutdown the whole platform</li>
 * <li><b>int</b> Platform.UTIL_CMD_OpenLoadMonitor: will send a message to show
 * the LoadMonitor</li> </ul> The setup-method of the agent will evaluate the
 * start argument and will add the corresponding behaviour.
 *
 *
 */
public class UtilityAgent extends Agent {

    private static final long serialVersionUID = 4018534357973603L;

    /**
     * The setup will evaluate the start argument for the agent and will add the
     * corresponding behaviour to it.
     */
    @Override
    protected void setup() {
        super.setup();

        Object[] args = getArguments();
        if (args == null || args.length == 0) {
            this.doDelete();
            return;
        }
        Integer start4 = (Integer) args[0];
        switch (start4) {
            case Platform.UTIL_CMD_OpenDF:
                this.addBehaviour(new ShowDFBehaviour());
                break;

            case Platform.UTIL_CMD_ShutdownPlatform:
                this.addBehaviour(new PlatformShutdownBehaviour());
                break;

  
            default:
                this.doDelete();
        }
    }
}
