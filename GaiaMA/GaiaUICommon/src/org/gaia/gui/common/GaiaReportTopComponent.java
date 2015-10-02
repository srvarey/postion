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
package org.gaia.gui.common;

import java.awt.LayoutManager;
import java.io.Serializable;
import org.gaia.dao.jade.IRefreshableWindow;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.ReportTemplate;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 * @author jkamoun
 */
public class GaiaReportTopComponent extends GaiaTopComponent implements LookupListener, Serializable, IRefreshableWindow {

    private Lookup.Result<ReportTemplate> result = null;
    protected ReportTemplate template = null;
    public Filter filter;
    private String reportType;

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public ReportTemplate getTemplate() {
        return this.template;
    }

    public void setTemplate(ReportTemplate _template) {
        this.template = _template;
    }

    public GaiaReportTopComponent() {
    }

    @Override
    public void refresh(Object obj) {
    }

    @Override
    public void resultChanged(LookupEvent le) {
    }

    public void runReport() {
    }

    public void loadTemplate(String name, boolean load) {
    }

    public Lookup.Result<ReportTemplate> getResult() {
        return result;
    }

    public void setResult(Lookup.Result<ReportTemplate> result) {
        this.result = result;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        if (template != null) {
            template.setFilter(filter);
        }
        this.filter = filter;
    }

    @Override
    public LayoutManager getLayout() {
        return super.getLayout();
    }

    public void populateReportToolBar() {

    }
}
