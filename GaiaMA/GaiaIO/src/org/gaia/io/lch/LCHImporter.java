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
package org.gaia.io.lch;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
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
import org.gaia.dao.legalEntity.LegalEntityAccessor;
import org.gaia.dao.pricing.MeasuresAccessor;
import org.gaia.dao.reports.PositionBuilder;
import org.gaia.dao.reports.ReportUtils;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.observables.MarketQuote;
import org.gaia.domain.reports.Position;
import org.gaia.domain.reports.PositionHistoricalMeasure;
import org.gaia.domain.trades.Product;
import org.gaia.domain.utils.Message;

/**
 *
 * @author Jawhar Kamoun
 */
public class LCHImporter {

    public final static Charset ENCODING = StandardCharsets.UTF_8;
    private static final Logger logger = Logger.getLogger(LCHImporter.class);
    static Integer LCHId;
    public final static String LOAD_VARIATION_MARGIN = "loadVariationMargin";
    private final static String dateFormat = "yyyyMMdd";
    private final static String separator = "\t";
    private final static String numberFormat = "#0.##";

    public static List<Message> loadVariationMargin(String filePath) {

        LegalEntity lch = LegalEntityAccessor.getLegalEntityByShortName("LCH");
        if (lch != null) {
            LCHId = lch.getLegalEntityId();
        }

        PositionHistoricalMeasure positionHistoricalMeasure = new PositionHistoricalMeasure();

        positionHistoricalMeasure.setCreationDate(new Date());
        positionHistoricalMeasure.setQuotationType(MarketQuote.QuotationType.PRICE.name());
        positionHistoricalMeasure.setProvider("LCH");
        positionHistoricalMeasure.setMeasureName(MeasuresAccessor.Measure.MARGIN_VARIATION.getName());

        List<String> importedFieldList = new ArrayList();
        importedFieldList.add(null);
        importedFieldList.add("Comment");
        importedFieldList.add(null);
        importedFieldList.add("Position.InternalCounterparty.AccountsAsBroker");
        importedFieldList.add("Position.Product.ExternalReferences.CCP");
        importedFieldList.add("PositionDate");
        importedFieldList.add("Currency");
        importedFieldList.add("MeasureValue");


        List<Message> messages;
        try {
            messages = loadFile(filePath, positionHistoricalMeasure, importedFieldList, separator, dateFormat, numberFormat);
        } catch (ParseException parse) {
            logger.error("LCH File Importer Error" + StringUtils.formatErrorMessage(parse));
            return ReportUtils.makeErrorMessageList(parse);
        } catch (NoSuchFileException nosuch) {
            logger.error("LCH File Importer Error " + StringUtils.formatErrorMessage(nosuch));
            return ReportUtils.makeErrorMessageList(nosuch);
        }
        return messages;
    }
    public final static String LOAD_INITIAL_MARGIN = "loadInitialMargin";

    public static List<Message> loadInitialMargin(String filePath) {

        LegalEntity lch = LegalEntityAccessor.getLegalEntityByShortName("LCH");
        if (lch != null) {
            LCHId = lch.getLegalEntityId();
        }

        PositionHistoricalMeasure positionHistoricalMeasure = new PositionHistoricalMeasure();

        positionHistoricalMeasure.setCreationDate(new Date());
        positionHistoricalMeasure.setQuotationType(MarketQuote.QuotationType.PRICE.name());
        positionHistoricalMeasure.setProvider("LCH");
        positionHistoricalMeasure.setMeasureName(MeasuresAccessor.Measure.MARGIN_SPAN.getName());

        // first run for span
        List<String> importedFieldList = new ArrayList();
        importedFieldList.add("PositionDate");
        importedFieldList.add("Comment");
        importedFieldList.add(null);
        importedFieldList.add(null);
        importedFieldList.add(null);
        importedFieldList.add("Position.InternalCounterparty.AccountsAsBroker");
        importedFieldList.add("Position.Product.ShortName");
        importedFieldList.add("MeasureValue");

        //List<Message> messages = ReportUtils.makeMessageList("SPAN");
        List<Message> messages;
        try {
            messages = loadFile(filePath, positionHistoricalMeasure, importedFieldList, separator, dateFormat, numberFormat);
        } catch (ParseException | NoSuchFileException parse) {
            logger.error(StringUtils.formatErrorMessage(parse));
            return ReportUtils.makeErrorMessageList(parse);
        }

        // second run for variation margin
        positionHistoricalMeasure.setMeasureName(MeasuresAccessor.Measure.MARGIN_VARIATION.getName());
        importedFieldList = new ArrayList();
        importedFieldList.add("PositionDate");
        importedFieldList.add("Comment");
        importedFieldList.add(null);
        importedFieldList.add(null);
        importedFieldList.add(null);
        importedFieldList.add("Position.InternalCounterparty.AccountsAsBroker");
        importedFieldList.add("Position.Product.ShortName");
        importedFieldList.add(null);
        importedFieldList.add(null);
        importedFieldList.add("MeasureValue");

        //List<Message> messages2 = ReportUtils.makeMessageList("VARIATION");
        List<Message> messages2;
        try {
            messages2 = loadFile(filePath, positionHistoricalMeasure, importedFieldList, separator, dateFormat, numberFormat);
        } catch (ParseException | NoSuchFileException parse) {
            logger.error(StringUtils.formatErrorMessage(parse));
            return ReportUtils.makeErrorMessageList(parse);
        }
        messages.addAll(messages2);
        return messages;
    }

    public static List<Message> loadFile(String filePath, Cloneable template, List<String> importedFieldList, String fieldSeparator,
            String dateFormat, String Numberformat) throws ParseException, NoSuchFileException {

        Path path = Paths.get(filePath);
        List<Message> messages = new ArrayList();
        int count = 0;
        try {
            Scanner scanner = new Scanner(path, ENCODING.name());
            //skip first line (header)
            scanner.nextLine();
            while (scanner.hasNextLine()) {
                //process each line
                Method cloneMethod = template.getClass().getMethod("clone", (Class<?>[]) null);
                Cloneable lineObject = (Cloneable) cloneMethod.invoke(template, (Object[]) null);
                String messageText = readLine(scanner.nextLine(), lineObject, importedFieldList, fieldSeparator, dateFormat, numberFormat);
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

    private static String readLine(String line, Cloneable lineObject, List<String> importedFieldList, String fieldSeparator, String dateFormat, String numberformat) throws ParseException {
        String[] fields = line.split(fieldSeparator);
        DateFormat df = new SimpleDateFormat(dateFormat);
        Product product = null;
        Integer internalCounterpartyId = null;
        Date valDate = null;
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
                            if (importField.equals("PositionDate")) {
                                valDate = df.parse(field);
                            }
                        } else if (getter.getReturnType().equals(BigDecimal.class)) {
                            setter.invoke(lineObject, new BigDecimal(field));
                        } else if (getter.getReturnType().equals(Integer.class)) {
                            setter.invoke(lineObject, Integer.parseInt(field));
                        } else if (getter.getReturnType().equals(String.class)) {
                            setter.invoke(lineObject, field);
                        } else {
                            logger.error("Warning : unknown format");
                        }
                    } else if (importField.startsWith("Position")) {
                        importField = importField.substring(importField.indexOf(StringUtils.DOT) + 1);
                        switch (importField) {
                            case "InternalCounterparty.AccountsAsBroker":
                                BoAccount account = LegalEntityAccessor.getBoAccountByCode(field);
                                if (account != null) {
                                    internalCounterpartyId = account.getClient().getLegalEntityId();
                                } else {
                                    errorMessage += "no account found for code " + field + StringUtils.getNewLine();
                                }
                                break;
                            case "Product.ExternalReferences.CCP":
                                product = ProductAccessor.getProductByReferenceAndValue("ISIN", field);
                                if (product == null) {
                                    errorMessage += "no product found for code " + field + StringUtils.getNewLine();
                                }
                                break;
                            case "Product.ShortName":
                                product = ProductAccessor.getProductByShortName(field);
                                if (field.length() == 3) {
                                    Method setter = lineObject.getClass().getMethod("setCurrency", String.class);
                                    setter.invoke(lineObject, field);
                                }
                                if (product == null) {
                                    errorMessage += "no product found for code " + field + StringUtils.getNewLine();
                                }
                                break;
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
        if (product != null && internalCounterpartyId != null && valDate != null) {

            List<Position> positions = PositionBuilder.getPositions(product.getId(), internalCounterpartyId, null, true, LCHId);

            if (positions != null) {
                try {
                    Method cloneMethod = lineObject.getClass().getMethod("clone", (Class<?>[]) null);
                    Method setter = lineObject.getClass().getMethod("setPosition", Position.class);
                    for (Position position : positions) {
                        Object myObject = (Cloneable) cloneMethod.invoke(lineObject, (Object[]) null);
                        setter.invoke(myObject, position);
                        PositionHistoricalMeasure positionHistoricalMeasure = PositionBuilder.getPositionHistoricalMeasureByPositionAndMeasure(((PositionHistoricalMeasure) myObject)
                                .getPosition().getId(), valDate, ((PositionHistoricalMeasure) myObject).getMeasureName());
                        if (positionHistoricalMeasure != null) {
                            positionHistoricalMeasure.setMeasureValue(((PositionHistoricalMeasure) lineObject).getMeasureValue());
                            positionHistoricalMeasure.setUpdateDate(new Date());
                            HibernateUtil.storeObject(positionHistoricalMeasure);
                        } else {
                            HibernateUtil.storeObject((Serializable) myObject);
                        }
                    }
                } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                    logger.error(StringUtils.formatErrorMessage(e));
                }
            }
        }
        return errorMessage;

    }
}
