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
package org.gaia.simulationService.distribution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

/**
 *
 * @author Benji
 */
public class ContainerKiller extends Thread {

    private static final Logger logger = Logger.getLogger(ContainerKiller.class);
    private String name;

    public ContainerKiller(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        String os = System.getProperty("os.name");
        String kill;

        logger.info("I'm going to kill " + name);
        if (name != null) {
            String tmp = name.substring(name.indexOf("@") + 1);
            String ip = tmp.substring(0, tmp.indexOf(":"));
            String port = tmp.substring(tmp.indexOf(":") + 1);


            if (os.toLowerCase().contains("linux") == true) {
                kill = "kill `ps -ef | grep java | grep " + port + "| grep " + ip + " | grep -v grep | awk '{print $2}'`";
                logger.info("Execute: " + kill);
                Runtime run = Runtime.getRuntime();
                Process pr = null;
                try {
                    pr = run.exec(kill);
                } catch (IOException e) {
                    logger.error(e);
                }
                try {
                    pr.waitFor();
                } catch (InterruptedException e) {
                    logger.error(e);
                }
                BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line;
                try {
                    while ((line = buf.readLine()) != null) {
                        logger.info(line);
                    }
                } catch (IOException e) {
                    logger.error(e);
                }
            }
        }

    }
}
