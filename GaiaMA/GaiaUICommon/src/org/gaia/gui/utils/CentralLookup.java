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
package org.gaia.gui.utils;

import java.util.HashMap;
import java.util.Map;
import org.gaia.domain.reports.ReportTemplate;
import org.gaia.gui.common.GaiaReportTopComponent;
import org.openide.util.lookup.AbstractLookup;
import org.openide.util.lookup.InstanceContent;
import org.openide.windows.TopComponent;

/**
 *
 * @author User
 */
public class CentralLookup extends AbstractLookup {

    private InstanceContent content = null;
    private static CentralLookup def = new CentralLookup();
    private final Map<GaiaReportTopComponent, ReportTemplate> map = new HashMap<>();
    private final Map<GaiaReportTopComponent, ReportTemplate> mapLastActive = new HashMap<>();

    public CentralLookup(InstanceContent content) {
        super(content);
        this.content = content;
    }

    private CentralLookup() {
        this(new InstanceContent());
    }

    public void add(GaiaReportTopComponent top, ReportTemplate template) {
        getDefault().map.put(top, template);
        if (template != null) {
            content.add(template);
        }
    }

    public void remove(Object instance) {
        if (instance != null) {
            content.remove(instance);
        }
    }

    public static ReportTemplate getLastActiveReportTemplate() {
        if (!getDefault().mapLastActive.values().isEmpty()) {
            return getDefault().mapLastActive.values().iterator().next();
        }
        return null;
    }

    public static ReportTemplate getReportTemplate(GaiaReportTopComponent top) {
        return getDefault().map.get(top);
    }

    public static void setLastActive(GaiaReportTopComponent top, ReportTemplate template) {
        getDefault().mapLastActive.clear();
        if (template != null) {
            getDefault().mapLastActive.put(top, template);
        }
    }

    public static GaiaReportTopComponent LastActiveReportTopComponent(ReportTemplate template) {
        GaiaReportTopComponent top = null;
        if (getDefault().mapLastActive.containsValue(template)) {
            top = getDefault().mapLastActive.keySet().iterator().next();
        }
        for (TopComponent topComponent : TopComponent.getRegistry().getOpened()) {
            if (topComponent instanceof GaiaReportTopComponent && CentralLookup.getReportTemplate((GaiaReportTopComponent) topComponent) != null) {
                if (topComponent.equals(top)) {
                    return (GaiaReportTopComponent) topComponent;
                }
            }
        }
        return null;
    }

    public static CentralLookup getDefault() {
        return def;
    }
}
