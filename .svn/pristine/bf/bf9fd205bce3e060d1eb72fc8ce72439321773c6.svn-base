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
package org.gaia.domain.trades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin Frerejean
 */
public class Schedule implements Serializable{

    private boolean obsStartOfDay;
    private String accrualPayConv;
    private String dcc;
    private List<ScheduleLine> scheduleLines;

    public Schedule(){
        scheduleLines=new ArrayList();
    }

    public List<ScheduleLine> getScheduleLines() {
        return scheduleLines;
    }

    public void setScheduleLines(List<ScheduleLine> scheduleLines) {
        this.scheduleLines = scheduleLines;
    }

    public String getAccrualPayConv() {
        return accrualPayConv;
    }

    public void setAccrualPayConv(String accrualPayConv) {
        this.accrualPayConv = accrualPayConv;
    }

    public boolean isObsStartOfDay() {
        return obsStartOfDay;
    }

    public void setObsStartOfDay(boolean obsStartOfDay) {
        this.obsStartOfDay = obsStartOfDay;
    }

    public String getDcc() {
        return dcc;
    }

    public void setDcc(String dcc) {
        this.dcc = dcc;
    }

}
