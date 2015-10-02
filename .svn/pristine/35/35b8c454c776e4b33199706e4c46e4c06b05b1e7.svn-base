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
package org.gaia.io;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductForex;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.Message;

/**
 *
 * @author User
 *
 * NOT FINISHED:
 * Is meant to interface Reuters fx spot messages
 */
public class ReutersImporter {

    private static final Logger logger = Logger.getLogger(ReutersImporter.class);
    public final static Charset ENCODING = StandardCharsets.UTF_8;
    private static final String separator = ";";
    public final static String LOAD_REUTERS_MSG = "loadReutersMsg";

    public static List<Message> loadReutersMsg(String filePath) {
        Trade trade = new Trade();
        trade.setPrice(BigDecimal.ZERO);
        trade.setAmount(BigDecimal.ZERO);
        trade.setPriceType(MarketQuote.QuotationType.PRICE.getName());
        trade.setNegociatedPriceType(MarketQuote.QuotationType.FWDpts.name());
        trade.setTradeType(Trade.TradeType.BUY_SELL.name);
        Product product = new Product();
        ProductForex forex = new ProductForex();


        return null;
    }

    private static String readLine(String line, Cloneable lineObject, List<String> importedFieldList, String fieldSeparator, String dateFormat) throws ParseException {
        String[] fields = line.split(fieldSeparator);
        DateFormat df = new SimpleDateFormat(dateFormat);
        String errorMessage = StringUtils.EMPTY_STRING;

        for (int i = 0; i < fields.length; i++) {
            try {
                String field = fields[i];
                String importField = null;
                if (i < importedFieldList.size()) {
                    importField = importedFieldList.get(i);
                }
                if (importField != null) {
                    if (importField.indexOf(StringUtils.DOT) == -1) {
                        Method getter = lineObject.getClass().getMethod("get" + importField, (Class<?>[]) null);
                        Method setter = lineObject.getClass().getMethod("set" + importField, getter.getReturnType());
                        if (getter.getReturnType().equals(Date.class)) {
                            setter.invoke(lineObject, df.parse(field));
                        } else if (getter.getReturnType().equals(BigDecimal.class)) {
                            setter.invoke(lineObject, new BigDecimal(field));
                        } else if (getter.getReturnType().equals(Integer.class)) {
                            setter.invoke(lineObject, Integer.parseInt(field));
                        } else if (getter.getReturnType().equals(String.class)) {
                            setter.invoke(lineObject, field);
                        } else {
                            errorMessage = "Warning : unknown format";
                            logger.error(errorMessage);
                        }
                    }
                }

            } catch (ParseException pe) {
                logger.error(StringUtils.formatErrorMessage(pe));
                throw pe;
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
        return errorMessage;
    }

    public static List<Message> loadFile(String filePath, Cloneable template, List<String> importedFieldList, String fieldSeparator,
            String dateFormat, String Numberformat) throws ParseException, NoSuchFileException {

        Path path = Paths.get(filePath);
        List<Message> messages = new ArrayList();
        int count = 0;
        try {
            Scanner scanner = new Scanner(path, ENCODING.name());
            while (scanner.hasNextLine()) {
                //process each line
                Method cloneMethod = template.getClass().getMethod("clone", (Class<?>[]) null);
                Cloneable lineObject = (Cloneable) cloneMethod.invoke(template, (Object[]) null);
                String messageText = readLine(scanner.nextLine(), lineObject, importedFieldList, fieldSeparator, dateFormat);
                if (messageText.isEmpty()) {
                    count++;
                } else {
                    Message message = new Message(null, null, messageText);
                    messages.add(message);
                }
            }
            Message message = new Message(null, null, "Number of lines inserted : " + count);
            messages.add(message);
        } catch (ParseException | NoSuchFileException parse) {
            throw parse;
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return messages;
    }
}
