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
package gaiajademasterdispatcherserver;

import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.trades.ProductTypeUtil;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.server.Application;
import org.gaia.jade.server.Platform;
import org.gaia.simulationService.agents.ServerClientAgent;

/**
 *
 * @author Benjamin Frerejean
 */
public class GaiaJadeMasterDispatcherServer {

    private static final Logger logger = Logger.getLogger(GaiaJadeMasterDispatcherServer.class);

    private static void preloadSigar() {

        String arch = System.getProperty("os.arch");
        String libName;

        if (!System.getProperty("os.name").toLowerCase().contains("linux")) {
            if (arch.equalsIgnoreCase("x86")) {
                libName = "sigar-x86-winnt";
            } else {
                libName = "sigar-amd64-winnt";
            }
        } else {
            logger.warn("Unrecognized platform!");
            return;
        }

        System.setProperty("org.hyperic.sigar.path", "-");
        System.loadLibrary(libName);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            if (Application.getGlobalInfo().isJnlpStart()) {
                logger.error("load data base h2");
                deployH2DB();
            }
            preloadSigar();
            logger.info("SERVER STARTING");
            Application.startGaiaAgentPlatform();
            Platform platform = Application.getJadePlatform();
            AgentContainer container = platform.jadeGetMainContainer();

            if (container != null) {

                ServerClientAgent client = new ServerClientAgent();
                AgentController controller = container.acceptNewAgent("server.client", client);
                controller.start();
                if (!System.getProperty("os.name").toLowerCase().contains("linux") && Application.getGlobalInfo().showRMA()) {
                    jade.tools.rma.rma rma = new jade.tools.rma.rma();
                    controller = container.acceptNewAgent("rma", rma);
                    controller.start();
                }
                logger.warn("System : " + System.getProperty("os.name") + StringUtils.SPACE + System.getProperty("os.arch"));
                // generates positions if needed
                logger.warn("Generates positions if needed");

                // just for the demo version
                if (Application.getGlobalInfo().getServerMasterDBHost().contains(":h2:")) {
                    Date date = DateUtils.getDate();
                    String query = "select max(ph.positionDate) from PositionHistory ph";
                    Date lastPositionDate = (Date) HibernateUtil.getObjectWithQuery(query);
                    if (lastPositionDate != null) {
                        Date nextDate = DateUtils.addCalendarDay(lastPositionDate, 1);
                        while (!nextDate.after(date)) {
                            query = "insert into position_history(position_id,position_date,position_mode,quantity,quantity_type,price)"
                                    + " select position_id,'" + HibernateUtil.dateFormat.format(nextDate) + "',position_mode,quantity,quantity_type,price"
                                    + " from position_history where position_date = "
                                    + " (select max(position_date) from position_history)";
                            HibernateUtil.executeSQLQuery(query);
                            nextDate = DateUtils.addCalendarDay(nextDate, 1);
                        }
                    }
                }
                // generate the position on date if it was not calculated before
                PositionBuilder.generatePositionsIfNeeded();
                // cache initialization
                int initLevel = 1;
                if (initLevel > 0) {
                    logger.error("START CACHE INIT :");
                    Date start = new Date();
                    // level 1 : market quotes
                    List list = HibernateUtil.getObjectsWithQuery("from Product p where p.productType in (" + ProductTypeUtil.loadTypesByUseSQL(ProductTypeUtil.ProductTypeUse.OBSERVABLE) + ")");
//                    List list = HibernateUtil.getObjectsWithQuery("from Product");
                    if (initLevel > 1) {
                        // level 2 : positions (and related products)
                        list.addAll(HibernateUtil.getObjectsWithQuery("from Position p join fetch p.positionHistoryCollection ph where ph.positionDate='" + HibernateUtil.dateFormat.format(DateUtils.getDate()) + StringUtils.QUOTE));
                    }
                    Date end = new Date();
                    logger.error("END CACHE INIT IN " + (end.getTime() - start.getTime()) / 1000 + " sec, " + list.size() + " elts");
                }

            } else {
                logger.error("SERVER START ERROR :");
                logger.error(" - Wrong parameters in gaia.ini");
                logger.error(" OR");
                logger.error(" - The database not reachable");
                logger.error(" OR");
                logger.error(" - A server is still running (see java.exe processes)");
                System.exit(0);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

    private static void deployH2DB() {
        try {
            OutputStream os;
            try (InputStream inputStream = GaiaJadeMasterDispatcherServer.class.getClassLoader().getResourceAsStream("gaia.h2.db")) {
                String path = System.getProperty("java.io.tmpdir");

                os = new FileOutputStream(path + "/gaia.h2.db");
                byte[] buffer = new byte[40960];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
            }
            os.close();
            logger.error("Deploy DB h2 OK");
        } catch (FileNotFoundException ex) {
            logger.error(ex);
        } catch (IOException ex) {
            logger.error(ex);
        }

    }
}
