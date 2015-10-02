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
package org.gaia.core.network;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

/**
 * This class can be used in order to evaluate the next free or unused port
 * on the local machine, starting from a specified starting port. 
 * 
 */
public class PortChecker {

	private int freePort;
	
	/**
	 * Constructor of this class.
	 *
	 * @param portSearchStart the port search start
	 */
	public PortChecker(int portSearchStart){
		
		int currPort = portSearchStart;
		while (currPort < portSearchStart+30) {
			if (isFreePort(currPort)){
				freePort = currPort;
				break;
			}
			currPort++;
		}
	}
	
	/**
	 * Provides the next free port number.
	 *
	 * @return next free port number
	 */
	public int getFreePort() {
		return freePort;
	}
	
	/**
	 * Checks if a port is available (free).
	 *
	 * @param port the port
	 * @return True, if the port is unused or free
	 */
	public boolean isFreePort(int port) {
		
		ServerSocket ss = null;
		DatagramSocket ds = null;
		try {
			ss = new ServerSocket(port);
			ss.setReuseAddress(true);
			ds = new DatagramSocket(port);
			ds.setReuseAddress(true);
			return true;
		} catch (IOException e) {                    
		} finally {
			if (ds != null) {
				ds.close();
			}

			if (ss != null) {
				try {
					ss.close();
				} catch (IOException e) {
					/* should not be thrown */
				}
			}
		}
		return false;
	}
	
}
