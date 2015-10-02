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

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.TickerBehaviour;
import java.util.Date;
import org.apache.log4j.Logger;
import org.gaia.jade.ontology.GaiaMobilityAgent;

/**
 *
 * @author Benjamin
 */
public class GaiaMobilitySampleAgent extends GaiaMobilityAgent {

    private static final long serialVersionUID = 1L;
    private long timer;
    private int timeout = 60;/**in seconds */
    private int tickerPeriod = 60;/**in seconds */
    private static final Logger logger = Logger.getLogger(GaiaDAOAgent.class);


    public GaiaMobilitySampleAgent() {
        super(null,null);
    }

    public GaiaMobilitySampleAgent(AID clientAID,jade.util.leap.List args) {
        super(clientAID,null);
        /**
         *  use args.
         */
    }

    @Override
    protected void setup() {

        super.setup();

      /** Add Behaviours
        * Warning : please add them in the afterMove too!
        */
        addBehaviour(new MyTickerBehaviour(this, tickerPeriod * 1000));

        timer = (new Date()).getTime();
        logger.info(getAID().getName() + " is ready.");
    }

    public class MyTickerBehaviour extends TickerBehaviour {

        /**
         * The Constant serialVersionUID.
         */
        private static final long serialVersionUID = -5802791218164507242L;

        public MyTickerBehaviour(Agent agent, long tickerPeriod) {
            super(agent, tickerPeriod);
        }


        @Override
        protected void onTick() {
            refreshTimer();
        }
    }

    public class DoItBehaviour extends OneShotBehaviour {

        String agentName;
        String agentClassName;

        public DoItBehaviour(Agent agent, String agentName, String agentClassName) {
            super(agent);
        }

        @Override
        public void action() {
            /**
             *  do somethig clever.
             */
            refreshTimer();
        }
    }

    public void refreshTimer() {
        long newTime = (new Date()).getTime();
        if (newTime - timer > timeout * 1000) {
            this.doDelete();
        }
        timer = newTime;
    }


    @Override
    protected void afterMove() {
        super.afterMove();
        this.addBehaviour(new MyTickerBehaviour(this, tickerPeriod * 1000));

    }

    @Override
    protected void takeDown() {
        super.takeDown();
    }
}
