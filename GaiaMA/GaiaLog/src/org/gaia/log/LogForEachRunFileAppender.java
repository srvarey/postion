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

package org.gaia.log;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.ErrorCode;

/**
 * This is a customized log4j appender, which will create a new file for every
 * run of the application.
 *
 *
 */
public class LogForEachRunFileAppender extends FileAppender {

    public LogForEachRunFileAppender() {
    }

    public LogForEachRunFileAppender(Layout layout, String filename,
            boolean append, boolean bufferedIO, int bufferSize)
            throws IOException {
        super(layout, filename, append, bufferedIO, bufferSize);
    }

    public LogForEachRunFileAppender(Layout layout, String filename,
            boolean append) throws IOException {
        super(layout, filename, append);
    }

    public LogForEachRunFileAppender(Layout layout, String filename)
            throws IOException {
        super(layout, filename);
    }

    @Override
    public void activateOptions() {
        if (fileName != null) {
            try {
                fileName = getNewLogFileName();
                setFile(fileName, fileAppend, bufferedIO, bufferSize);
            } catch (Exception e) {
                errorHandler.error("Error while activating log options", e,
                        ErrorCode.FILE_OPEN_FAILURE);
            }
        }
    }

    /**
     *Return the file names
     *
     */
    private String getNewLogFileName() {
        if (fileName != null) {
            final String DOT = ".";
            final String HIPHEN = "-";
            final File logFile = new File(fileName);
            final String logfileName = logFile.getName();
            String newFileName = "";

            final int dotIndex = logfileName.indexOf(DOT);
            if (dotIndex != -1) {
                /**
                 * the file name has an extension. so, insert the time stamp
                 * between the file name and the extension
                 */
                newFileName = logfileName.substring(0, dotIndex) + HIPHEN + +System.currentTimeMillis() + DOT + logfileName.substring(dotIndex + 1);
            } else {
                /**
                 *  the file name has no extension. So, just append the timestamp
                 *  at the end.
                 */
                newFileName = logfileName + HIPHEN + System.currentTimeMillis();
            }
            return logFile.getParent() + File.separator + newFileName;
        }
        return null;
    }
}