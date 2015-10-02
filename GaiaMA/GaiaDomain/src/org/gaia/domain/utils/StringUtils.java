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
package org.gaia.domain.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Benjamin Frerejean
 */
public class StringUtils {

    public static final String EMPTY_STRING = "";
    public static final String PUT = "Put";
    public static final String CALL = "Call";
    public static final String BUY = "Buy";
    public static final String SELL = "Sell";
    public static final String DOT = ".";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String QUOTE = "'";


    public static String cleanStringForTables(String str) {
        String ret = cleanString(str);
        ret = ret.replace(StringUtils.SPACE, "_");
        ret = ret.replace("&", "and");
        ret = ret.replace(":", StringUtils.EMPTY_STRING);
        ret = ret.replace("-", StringUtils.EMPTY_STRING);
        ret = ret.replace("~#", StringUtils.EMPTY_STRING);
        ret = ret.replace("[", StringUtils.EMPTY_STRING);
        ret = ret.replace("]", StringUtils.EMPTY_STRING);
        ret = ret.replace("`", StringUtils.EMPTY_STRING);
        ret = ret.replace("|", StringUtils.EMPTY_STRING);
        ret = ret.replace("\"", StringUtils.EMPTY_STRING);
        ret = ret.replace("\\", StringUtils.EMPTY_STRING);
        ret = ret.replace("^", StringUtils.EMPTY_STRING);
        ret = ret.replace("@", StringUtils.EMPTY_STRING);
        ret = ret.replace("$", StringUtils.EMPTY_STRING);
        ret = ret.replace("*", StringUtils.EMPTY_STRING);
        ret = ret.replace("ù", StringUtils.EMPTY_STRING);
        ret = ret.replace("!", StringUtils.EMPTY_STRING);
        ret = ret.replace(";", StringUtils.EMPTY_STRING);
        ret = ret.replace(StringUtils.COMMA, StringUtils.EMPTY_STRING);
        ret = ret.replace(")", StringUtils.EMPTY_STRING);
        ret = ret.replace("(", StringUtils.EMPTY_STRING);

        return ret;
    }

    public static String cleanString(String str) {
        String ret = str;
        if (ret==null){
            ret=StringUtils.EMPTY_STRING;
        }
        ret = ret.replace(StringUtils.QUOTE, StringUtils.EMPTY_STRING);
        ret = ret.replace("{", StringUtils.EMPTY_STRING);
        ret = ret.replace("}", StringUtils.EMPTY_STRING);
        return ret;
    }

    public static int countString(String in,String searched ){
        Pattern pattern = Pattern.compile(searched);
        Matcher  matcher = pattern.matcher(in);

        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }
    /**
     * if the string is empty or null
     * @param str
     * @return
     */
    public static boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }

    public static String getNewLine() {
        return System.getProperty("line.separator");
    }

    /**
     * build a list of string with separator
     * @param string
     * @param separator
     * @return
     */
    public static ArrayList<String> stringToArrayList(String string, String separator) {
        ArrayList<String> res = new ArrayList<>();
        if (separator == null) {
            res.add(string);
        } else if (!isEmptyString(string)) {
            int index;
            int prevIndex = 0;
            String str;
            while ((index = string.indexOf(separator, prevIndex)) != -1) {
                str = string.substring(prevIndex, index);
                res.add(str);
                prevIndex = index + separator.length();
            }
            str = string.substring(prevIndex, string.length());
            if (!isEmptyString(str)) {
                res.add(str);
            }
        }
        return res;
    }

    public static String collectionToSQLString(Collection a) {
        if (isEmpty(a)) {
            return "()";
        }
        StringBuilder sb = new StringBuilder(a.size() * 20);
        Iterator i = a.iterator();
        sb.append("(").append(string2SQLString(i.next().toString()));
        while (i.hasNext()) {
            sb.append(StringUtils.COMMA).append(string2SQLString(i.next().toString()));
        }
        sb.append(")");
        return sb.toString();
    }

    public static String collectionToString(Collection c, String separator) {
        if (isEmpty(c)) {
            return EMPTY_STRING;
        } else {
            StringBuilder s = new StringBuilder(c.size() * 20);
            Iterator iterator = c.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                if (item != null) {
                    if (s.length() > 0) {
                        s.append(separator);
                    }
                    s.append(item.toString());
                }
            }
            return s.toString();
        }
    }

    public static String string2SQLString(String s) {
        if (s == null) {
            return "null";
        }
        StringBuilder buf = new StringBuilder();
        char ar[] = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            buf.append(ar[i]);
            if (ar[i] == '\'') {
                buf.append(ar[i]);
            }
        }
        return StringUtils.QUOTE + buf.toString() + StringUtils.QUOTE;
    }

    /**
     * Format the error message
     * @param exception
     * @return
     */
    public static String formatErrorMessage(Exception exception) {
        String message = "Error : " + exception.getClass().getName() + "/" + exception.getMessage();
        StackTraceElement[] stackTraces = exception.getStackTrace();
        int i = 0;
        String newLine = System.getProperty("line.separator");
        for (StackTraceElement stackTraceElement : stackTraces) {
            if (stackTraceElement.getClassName().contains(".gaia.") && i < 4) {
                message = message + newLine + "   " + stackTraceElement.toString();
                i++;
            }
        }
        return message;
    }

    /**
     * @param collection
     * @return true if collection is not null and not empty
     * @see java.util.Collection#isEmpty()
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Format the error message plus its cause
     * @param exception
     * @return
     */
    public static String formatErrorMessageWithCause(Exception exception) {
        String message = formatErrorMessage(exception);
        Throwable cause = exception.getCause();
        if (cause != null) {

            String newLine = System.getProperty("line.separator");
            message += newLine + "Cause " + cause.getClass().getName() + "/" + cause.getMessage();
            int i = 0;
            StackTraceElement[] stackTraces = cause.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTraces) {
                if (stackTraceElement.getClassName().contains(".gaia.") && i < 3) {
                    message = message + newLine + "   " + stackTraceElement.toString();
                    i++;
                }
            }
        }
        return message;
    }
}

