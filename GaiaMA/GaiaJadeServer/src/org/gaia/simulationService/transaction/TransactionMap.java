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
package org.gaia.simulationService.transaction;

import java.util.HashMap;
import org.gaia.simulationService.SimulationService;
import org.gaia.simulationService.environment.EnvironmentModel;
import org.gaia.simulationService.time.TimeModelDiscrete;
import org.gaia.simulationService.time.TimeModelStroke;

/**
 * This class is used inside the {@link SimulationService} for storing different
 * {@link EnvironmentModel} over time. It extends a HashMap in order to assign
 * time to a state of an {@link EnvironmentModel}. <br><br> This class is not
 * finalized by now.
 *
 */
public class TransactionMap extends HashMap<Long, EnvironmentModel> {

    private static final long serialVersionUID = 7858350066101095998L;
    // --- In case that we are dealing with time  -----
    // --- in our TimeModel: For a faster Mapping! ----
    private HashMap<Long, Long> time2Counter = new HashMap<>();
    private long internalCounter = 0;

    public EnvironmentModel put(EnvironmentModel envModel) {

        long hashKey = 0;

        // --- If the environment model is null -------------------
        if (envModel == null) {
            return null;
        }

        // --- Case distinction for the TimeModel -----------------
        if (envModel.getTimeModel() == null) {
            hashKey = internalCounter;
            internalCounter++;

        } else if (envModel.getTimeModel() instanceof TimeModelStroke) {
            TimeModelStroke tm = (TimeModelStroke) envModel.getTimeModel();
            hashKey = tm.getCounter();

        } else if (envModel.getTimeModel() instanceof TimeModelDiscrete) {
            TimeModelDiscrete tm = (TimeModelDiscrete) envModel.getTimeModel();
            hashKey = tm.getTime();
            // --- In case that we are dealing with time  -----
            // --- in our TimeModel: For a faster Mapping! ----
            time2Counter.put(tm.getTime(), internalCounter);
            internalCounter++;
        }
        return super.put(hashKey, envModel);

    }
}
