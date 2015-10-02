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
package org.gaia.core.database;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.jade.server.Application;

/**
 * This class is used within the execution mode as server.master for the
 * background system of Agent.GUI.<br> In this mode the server.master agent will
 * manage all server.slaves and server.clients in a database table.
 *
 */
public class DBConnection {

    private final String newLine = Application.getGlobalInfo().getNewLineSeparator();
    private static final Logger logger = Logger.getLogger(DBConnection.class);
    private String dbHost = null;
    private String dbName = null;
    private String dbUser = null;
    private String dbPswd = null;
    private Connection connection = null;
    private String sql = null;
    /**
     * The Error object of this class
     */
    public Error dbError = new Error();
    /**
     * Flag that shows if errors have occurred
     */
    public boolean hasErrors = false;

    /**
     * Constructor of this class. Try's to connect to or build the database for
     * the server.master agent
     */
    public DBConnection() {

        // --- Can the connection be established? ---------
        if (!this.connect()) {
            hasErrors = true;
            logger.error("DBError :" + this.dbError.errText);
            return;
        }

        this.setConnection2Database(this.dbName);
    }

    /**
     * Does the setCatalog-method for the current connection
     *
     * @param database on which we would like to connect
     */
    private void setConnection2Database(String database) {

        try {
            connection.setCatalog(database);
        } catch (SQLException e) {
            logger.error(e);
            this.dbError.setText(e.getLocalizedMessage());
            this.dbError.setErrNumber(e.getErrorCode());
            this.dbError.setHead("executeUpdate!");
            this.dbError.setErr(true);
            this.dbError.show();
        }

    }

    /**
     * This Method executes a SQL-Statement (Create, Insert, Update) in the
     * database and returns true if this was successful
     *
     * @param sqlStatement the SQL-Statement to execute
     * @return True, if the execution was successful
     */
    public int getSqlExecuteUpdate(String sqlStatement) {

        Statement state = getNewStatement();
        int res = -1;
        if (state != null) {
            try {
                state.executeUpdate(sqlStatement);
                res = state.getUpdateCount();
                return res;

            } catch (SQLException e) {
                String msg = e.getLocalizedMessage() + newLine;
                msg += sqlStatement;
                this.dbError.setText(msg);
                this.dbError.setErrNumber(e.getErrorCode());
                this.dbError.setErr(true);
                this.dbError.show();

            }
        }
        return res;
    }

    /**
     * This method returns the number of rows from a ResultSet-Object
     *
     * @param rs
     * @return Number of rows from a result set, after SQL execution
     */
    public int getRowCount(ResultSet rs) {
        int numResults = 0;
        try {
            rs.last();
            numResults = rs.getRow();
            rs.beforeFirst();
        } catch (SQLException e) {
            logger.error(e);
        }
        return numResults;
    }

    /**
     * Returns a ResultSet - Object for a SQL-Statement
     *
     * @param sqlStmt the SQL statement
     * @return com.mysql.jdbc.ResultSet
     */
    public ResultSet getSqlResult4ExecuteQuery(String sqlStmt) {
        return this.getSqlResult4ExecuteQuery(sqlStmt, true);
    }

    /**
     * Returns a ResultSet - Object for a SQL-Statement.
     *
     * @param sqlStmt the SQL statement
     * @param showErrorDialog the show error dialog
     * @return com.mysql.jdbc.ResultSet
     */
    public ResultSet getSqlResult4ExecuteQuery(String sqlStmt, boolean showErrorDialog) {

        ResultSet res = null;
        Statement statement = this.getNewStatement();

        if (statement != null) {
            try {
                res = (ResultSet) statement.executeQuery(sqlStmt);
            } catch (SQLException e) {
                String msg = e.getLocalizedMessage() + newLine;
                msg += sqlStmt;
                this.dbError.setText(msg);
                this.dbError.setErrNumber(e.getErrorCode());
                this.dbError.setHead("Error in the 'executeQuery'!");
                this.dbError.setErr(true);
                logger.error(e);
            }
        }
        return res;

    }

    /**
     * This method returns a new Statement-Object for a further handling of
     * SQL-Interaction (e. g. for an executeUpdate)
     *
     * @return com.mysql.jdbc.Statement
     */
    public Statement getNewStatement() {
        try {
            return (Statement) connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (SQLException e) {
            logger.fatal("Code: " + e.getErrorCode());
            this.dbError.setErrNumber(e.getErrorCode());
            this.dbError.setHead("Error creating a new instance statement!");
            this.dbError.setText(e.getLocalizedMessage());
            this.dbError.setErr(true);
            this.dbError.show();
            return null;
        }
    }

    /**
     * This method initialises the connection to the Database-Server defined in
     * the Application-Options
     *
     * @return True, if the connection was established successfully
     */
    private boolean connect() {

        String configuredHost = Application.getGlobalInfo().getServerMasterDBHost();

        if (configuredHost == null) {
            StringBuilder msg = new StringBuilder("ERROR : Fail to connect to database.");
            msg.append(newLine);
            this.dbError.setText(msg.toString());
            this.dbError.setHead("Error in the configuration database!");
            this.dbError.setErrNumber(-1);
            this.dbError.setErr(true);
            this.dbError.show();
            logger.error(msg);
            return false;
        }

        dbName = Application.getGlobalInfo().getServerMasterDBName();
        dbUser = Application.getGlobalInfo().getServerMasterDBUser();
        dbPswd = Application.getGlobalInfo().getServerMasterDBPswd();
        dbHost = configuredHost + "/";
        if (Application.getGlobalInfo().isJnlpStart()) {
            String path = System.getProperty("java.io.tmpdir");
            String regex = "/";
            String replacement = "\\";
            configuredHost = "jdbc:h2:" + path.replaceAll(regex, replacement) + "gaia;MVCC=true";
        }

        try {
            Properties props = new Properties();
            props.setProperty("user", dbUser);
            props.setProperty("password", dbPswd == null ? "" : dbPswd);
            logger.error("host" + configuredHost);
            connection = (Connection) DriverManager.getConnection(configuredHost, props);

        } catch (SQLException e) {
            logger.error(e);
            this.dbError.setErrNumber(e.getErrorCode());
            this.dbError.setText(e.getLocalizedMessage());
            this.dbError.setErr(true);
        }

        // --- Show Error if there ----------------------------------
        if (this.dbError.isErr()) {
            this.dbError.setHead("Failed to connect to database server");
            this.dbError.show();
            return false;
        }
        return true;
    }

    /**
     * This inner class handles the Errors which can occur during the
     * SQL-Interactions
     *
     */
    public class Error {

        private Integer errNumber = 0;
        private String errHead = null;
        private String errText = null;
        private boolean err = false;
        private String msg = "";

        /**
         * Sets the Message which will be shown in an Error-Dialog
         */
        private void setMessage() {

            if (errNumber.equals(0)) {
                msg = errText + newLine;
            } else if (errNumber.equals(-1)) {
                msg = errText + newLine;
            } else {
                msg = errNumber + ":" + newLine;
                msg += errText;
                msg = formatText(msg);
            }
            errHead = msg;
        }

        /**
         * Shows the current Error-Message on an OptionPane-MessageDialog.
         * Afterwards, the error will be reseted
         */
        public void show() {
            this.setMessage();
            logger.error(msg);
            this.resetError();
        }

        /**
         * This Method resets the current Err-Object to a non-Error State
         */
        private void resetError() {
            errNumber = 0;
            errHead = null;
            errText = null;
            err = false;
            msg = "";
        }

        /**
         * Here a error-number (for SQL) can be set
         *
         * @param newErrNumber
         */
        public void setErrNumber(Integer newErrNumber) {
            errNumber = newErrNumber;
        }

        /**
         * Here the Dialog-Title of the JOptionPane can be set
         *
         * @param newErrHead
         */
        public void setHead(String newErrHead) {
            errHead = newErrHead;
        }

        /**
         * Here, the Text inside of the JOptionPane can be set
         *
         * @param newErrText
         */
        public void setText(String newErrText) {
            errText = newErrText;
        }

        /**
         * Here, an indicator for an error can be set
         *
         * @param err
         */
        public void setErr(boolean err) {
            this.err = err;
        }

        /**
         * This method returns, if there is an error or not
         *
         * @return boolean
         */
        public boolean isErr() {
            return err;
        }

        /**
         * This method puts the value of 'toClipboard' to the clipboard In case
         * of an SQL-Error, the SQL-Statement will be placed in such a way and
         * can be used in an external application as well.
         *
         * @param toClipboard String which will be placed in the clipboard
         */
        public void put2Clipboard(String toClipboard) {
            StringSelection data = new StringSelection(toClipboard);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(data, data);
        }

        /**
         * In case of an SQL-Error the Text can be very long. This Method checks
         * a Text and will reduce the width of the Text (shown in the
         * JOptionpane) to a maximum width of 100 characters by adding line
         * breaks.
         *
         * @param currText
         * @return formated String with line breaks
         */
        private String formatText(String currText) {

            String newTextArr[] = currText.split(StringUtils.SPACE);
            String newText = "";
            String line = "";

            for (int i = 0; i < newTextArr.length; i++) {
                line += newTextArr[i] + StringUtils.SPACE;
                if (line.length() >= 100) {
                    newText += line + newLine;
                    line = "";
                }
            }
            newText += line;
            return newText;

        }
    }
    // ------------------------------
    // --- End Sub-Class ------------
    // ------------------------------

    /**
     * This method converts an DB-Integer to a Java-boolean
     *
     * @param intValue
     * @return False, if the integer value is equal 0 - otherwise true
     */
    public boolean dbInteger2Bool(Integer intValue) {
        if (intValue.equals(0)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * This method converts an DB-boolean to an Java-Integer
     *
     * @param booleanValue
     * @return 0, if the boolean is true - otherwise -1
     */
    public Integer dbBool2Integer(boolean booleanValue) {
        if (booleanValue == true) {
            return -1;
        } else {
            return 0;
        }
    }
}
