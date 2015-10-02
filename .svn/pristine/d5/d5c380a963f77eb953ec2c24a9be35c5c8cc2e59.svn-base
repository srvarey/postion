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

import java.io.Serializable;
import java.util.Date;
import org.gaia.domain.reports.ReportTemplate;

/**
 *
 * @author Benjamin
 */
public class ReportSettings implements Serializable{
    private static final long serialVersionUID = 1L;

    private ReportTemplate template;
    private Date valDate;
    private String pricingEnv;
    private boolean hasPricing;
    private boolean hasFirstDate;
    private boolean justNeededObservables;
    private Date firstDate;
    private String currency;
    private boolean isRealTime;
    private boolean includeCounterpartyInCreditIndicators;

    public ReportSettings(ReportTemplate template, Date valDate, String pricingEnv, boolean hasPricing, boolean hasFirstDate,Date firstDate, boolean justNeededObservables,String currency,boolean isRealTime,boolean includeCounterpartyInCreditIndicators) {

        this.template = template;
        this.valDate = valDate;
        this.pricingEnv = pricingEnv;
        this.hasPricing = hasPricing;
        this.hasFirstDate = hasFirstDate;
        this.firstDate=firstDate;
        this.justNeededObservables=justNeededObservables;
        this.currency=currency;
        this.isRealTime=isRealTime;
        this.includeCounterpartyInCreditIndicators=includeCounterpartyInCreditIndicators;
    }

    public ReportSettings(){}

    public ReportTemplate getTemplate() {
        return template;
    }

    public void setTemplate(ReportTemplate template) {
        this.template = template;
    }

    public Date getValDate() {
        return valDate;
    }

    public void setValDate(Date valDate) {
        this.valDate = valDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPricingEnv() {
        return pricingEnv;
    }

    public void setPricingEnv(String pricingEnv) {
        this.pricingEnv = pricingEnv;
    }

    public boolean hasPricing() {
        return hasPricing;
    }

    public void setHasPricing(boolean hasPricing) {
        this.hasPricing = hasPricing;
    }

    public boolean hasFirstDate() {
        return hasFirstDate;
    }

    public void setHasFirstDate(boolean hasFirstDate) {
        this.hasFirstDate = hasFirstDate;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public boolean isJustNeededObservables() {
        return justNeededObservables;
    }

    public void setJustNeededObservables(boolean justNeededObservables) {
        this.justNeededObservables = justNeededObservables;
    }

    public boolean isRealTime() {
        return isRealTime;
    }

    public void setIsRealTime(boolean isRealTime) {
        this.isRealTime = isRealTime;
    }

    public boolean includeCounterpartyInCreditIndicators() {
        return includeCounterpartyInCreditIndicators;
    }

    public void setIncludeCounterpartyInCreditIndicators(boolean includeCounterpartyInCreditIndicators) {
        this.includeCounterpartyInCreditIndicators = includeCounterpartyInCreditIndicators;
    }


}
