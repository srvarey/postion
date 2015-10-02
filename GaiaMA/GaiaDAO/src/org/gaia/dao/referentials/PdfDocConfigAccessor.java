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
package org.gaia.dao.referentials;

import java.util.List;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.referentials.PdfDocConfig;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Jawhar Kamoun
 */
public class PdfDocConfigAccessor {

    public static final String STORE_PDFDOC_CONFIG = "storePdfDocConfig";

    /** store pdfDocConfig
     *
     * @param pdfDocConfig
     */
    public static void storePdfDocConfig(PdfDocConfig pdfDocConfig) {
        HibernateUtil.storeObject(pdfDocConfig);
    }

    public static final String GET_PDFDOC_CONFIG_BY_ID = "getPdfDocConfigById";

    /**
     * get PdfDocConfig  by id
     * @param id
     * @return  PdfDocConfig
     */
    public static PdfDocConfig getPdfDocConfigById(Integer id) {
        Session session = HibernateUtil.getSession();
        Query query = session.getNamedQuery("PdfDocConfig.findByPdfDocConfigId");
        query.setInteger("pdfDocConfigId", id);
        return (PdfDocConfig) query.uniqueResult();
    }
    /**
     * get PdfDocConfig  by id
     * @param portfolioId
     * @return  PdfDocConfig
     */
    public static PdfDocConfig getPdfDocConfigByPortfolioId(Integer portfolioId) {
        return (PdfDocConfig)HibernateUtil.getObjectWithQuery("from PdfDocConfig c where c.portofolio.legalEntityId="+portfolioId);
    }

    public static final String GET_ALL_PDFDOC_CONFIG = "getAllPdfDocConfig";

    /** retrieve All PdfDocConfig
     *
     * @return  list of PdfDocConfig
     */
    public static List getAllPdfDocConfig() {
        Session session = HibernateUtil.getSession();
        Query query = session.getNamedQuery("PdfDocConfig.findAll");
        return query.list();
    }

    public static final String DELETE_PDFDOC_CONFIG = "deletePdfDocConfig";

    /** delete PdfDocConfig
     *
     * @param id
     */
    public static void deletePdfDocConfig(Integer id) {
        HibernateUtil.deleteObject(getPdfDocConfigById(id));
    }

    /** delete PdfDocConfig
     *
     * @param pdfDocConfig
     */
    public static void deletePdfDocConfig(PdfDocConfig pdfDocConfig) {
        HibernateUtil.deleteObject(pdfDocConfig);
    }

}
