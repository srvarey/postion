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
package org.gaia.dao.pricing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gaia.dao.utils.IntrospectTree;
import org.gaia.domain.observables.GreekSetting;
import org.gaia.domain.observables.Stress;
import org.gaia.domain.reports.TemplateColumnItem;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class MeasuresAccessor {

    public enum MeasureGroup {

        PV_GROUP, CASH_GROUP, CVA_GROUP, PNL_GROUP, INPUT_GROUP
    }

    public static final String UNIT_POSTFIX = "_unit";

    /**
     * enum from measure.
     */
    public enum Measure implements Serializable, IPricerMeasure {

        CASH(MeasureGroup.CASH_GROUP, false, null, 1, false),
        CASH_unit(MeasureGroup.CASH_GROUP, true, CASH, 1, false),
        CREDIT_EXPOSURE(MeasureGroup.CVA_GROUP, false, null, 1, false),
        CREDIT_LINE(MeasureGroup.CVA_GROUP, false, null, 1, false),
        CVA(MeasureGroup.CVA_GROUP, false, null, 1, false),
        CVA_unit(MeasureGroup.CVA_GROUP, true, CVA, 1, false),
        CVA_unit_line(MeasureGroup.CVA_GROUP, false, null, 1, true),
        JUMP_TO_DEFAULT(MeasureGroup.CVA_GROUP, false, null, 1, false),
        JUMP_TO_DEFAULT_unit(MeasureGroup.CVA_GROUP, true, JUMP_TO_DEFAULT, 1, false),
        MARGINAL_CVA(MeasureGroup.CVA_GROUP, false, null, 1, false),
        MARGINAL_CVA_unit(MeasureGroup.CVA_GROUP, false, null, 1, true),
        MARGIN_BROKERAGE(MeasureGroup.INPUT_GROUP, false, null, 1, false),
        MARGIN_GLOBAL(MeasureGroup.INPUT_GROUP, false, null, 1, false),
        MARGIN_SPAN(MeasureGroup.INPUT_GROUP, false, null, 1, false),
        MARGIN_VARIATION(MeasureGroup.INPUT_GROUP, false, null, 1, false),
        NPV(MeasureGroup.PV_GROUP, false, null, 1, false),
        NPV_unit(MeasureGroup.PV_GROUP, true, NPV, 1, false),
        NPV_CREDIT(MeasureGroup.PV_GROUP, false, null, 1, false),
        NPV_CREDIT_unit(MeasureGroup.PV_GROUP, true, NPV_CREDIT, 1, true),
        NPV_FEE(MeasureGroup.PV_GROUP, false, null, 1, false),
        NPV_FEE_unit(MeasureGroup.PV_GROUP, true, NPV_FEE, 1, true),
        QUOTE(MeasureGroup.PV_GROUP, false, null, 2, false),
        SINGLE_CREDIT_LINE(MeasureGroup.CVA_GROUP, false, null, 2, false),
        VOLATILITY(MeasureGroup.PV_GROUP, false, null, 1, false);

        private final MeasureGroup group;
        private final boolean isUnit;
        private final Measure linkedAmountMeasure;
        private final int dimension;
        private final boolean isHidden;

        Measure(MeasureGroup group, boolean isUnit, Measure linkedAmountMeasure, int dimension, boolean isHidden) {
            this.group = group;
            this.isUnit = isUnit;
            this.linkedAmountMeasure = linkedAmountMeasure;
            this.dimension = dimension;
            this.isHidden = isHidden;
        }

        @Override
        public String getName() {
            return this.name();
        }

        @Override
        public MeasureGroup getGroup() {
            return group;
        }

        @Override
        public Measure getLinkedAmountMeasure() {
            return linkedAmountMeasure;
        }

        @Override
        public int getDimension() {
            return dimension;
        }

        @Override
        public boolean isUnit() {
            return isUnit;
        }

        @Override
        public boolean isHidden() {
            return isHidden;
        }
    }

    public static IPricerMeasure getUnitMeasure(String measureName) {
        for (Measure measure : Measure.values()) {
            if (measure.getName().equalsIgnoreCase(measureName + UNIT_POSTFIX)) {
                return measure;
            }
        }
        GreekSetting dbGreek = (GreekSetting) HibernateUtil.getObjectWithQuery("from GreekSetting where pricer_measure='" + measureName + StringUtils.QUOTE);
        if (dbGreek != null) {
            return new StoredGreek(dbGreek, true);
        }
        return null;
    }

    public static String GET_MEASURE_BY_NAME = "getMeasureByName";

    /**
     * return measure required .
     *
     * @param name
     * @return IPricerMeasure
     */
    public static IPricerMeasure getMeasureByName(String name) {
        for (Measure value : Measure.values()) {
            if (value.name().equals(name)) {
                return value;
            }
        }
        boolean isUnit = false;
        if (name.endsWith(UNIT_POSTFIX)) {
            name = name.substring(0, name.length() - UNIT_POSTFIX.length());
            isUnit = true;
        }
        if (name.indexOf(StringUtils.DOT)>=0){
            name = name.substring(0, name.indexOf(StringUtils.DOT));
        }
        GreekSetting dbGreek = (GreekSetting) HibernateUtil.getObject(GreekSetting.class, name);
        if (dbGreek != null) {
            if (isUnit) {
                return new StoredGreek(dbGreek, true);
            } else {
                return new StoredGreek(dbGreek, false);
            }
        }
        return null;
    }

    public static List<Measure> getMeasuresByGroup(MeasuresAccessor.MeasureGroup group) {
        List list = new ArrayList();
        for (Measure measure : Measure.values()) {
            if (measure.group.equals(group)) {
                list.add(measure);
            }
        }
        return list;
    }

    public static String GET_MEASURES_BY_GROUP_NO_UNITS = "getMeasuresByGroupNoUnits";

    public static List<IPricerMeasure> getMeasuresByGroupNoUnits(MeasuresAccessor.MeasureGroup group) {
        List<IPricerMeasure> list = new ArrayList();
        for (IPricerMeasure measure : getAllMeasures()) {
            if (measure.getGroup().equals(group) && !measure.isUnit()) {
                list.add(measure);
            }
        }
        return list;
    }

    public static String GET_ALL_MEASURES = "getAllMeasures";

    public static List<IPricerMeasure> getAllMeasures() {
        List<IPricerMeasure> allMeasures = getStoredMeasures();
        allMeasures.addAll(Arrays.asList(Measure.values()));
        return allMeasures;
    }

    public static String GET_STORED_MEASURES = "getStoredMeasures";

    public static List<IPricerMeasure> getStoredMeasures() {
        List<IPricerMeasure> returnedList = new ArrayList();
        List<GreekSetting> dbGreeks = HibernateUtil.getObjectsWithQuery("from GreekSetting order by pricerMeasure");
        for (GreekSetting greek : dbGreeks) {
            returnedList.add(new StoredGreek(greek, false));
            returnedList.add(new StoredGreek(greek, true));
        }
        return returnedList;
    }

    public static String GET_MEASURES_TREE = "getMeasuresTree";

    /**
     * retrieve new file node from measure.
     * @return
     */
    public static IntrospectTree.IntrospectNode getMeasuresTree() {
        List<IPricerMeasure> allMeasures = getAllMeasures();
        IntrospectTree tree = new IntrospectTree();
        IntrospectTree.ClassNode root = tree.new ClassNode("getMeasures", 1, "");
        for (IPricerMeasure measure : allMeasures) {
            if (!measure.isHidden()) {
                IntrospectTree.IntrospectNode node = tree.new FieldNode("get" + measure.getName(), 1, "");
                node.setResultType(BigDecimal.class.getName());
                node.setColumnType(TemplateColumnItem.COLUMN_MEASURE);
                node.setIsConversion(Boolean.FALSE);
                root.add(node);
            }
        }
        return root;
    }

    public static String GET_PNL_MEASURES_TREE = "getPnLMeasuresTree";

    /**
     * retrieve new file node from measure.
     * @return
     */
    public static IntrospectTree.IntrospectNode getPnLMeasuresTree() {
        List<PnLMeasure> allMeasures = Arrays.asList(PnLMeasure.values());
        IntrospectTree tree = new IntrospectTree();
        IntrospectTree.ClassNode root = tree.new ClassNode("getPnLMeasures", 1, "");
        for (PnLMeasure measure : allMeasures) {
                IntrospectTree.IntrospectNode node = tree.new FieldNode("get" + measure.name(), 1, "");
                node.setResultType(BigDecimal.class.getName());
                node.setColumnType(TemplateColumnItem.COLUMN_MEASURE);
                node.setIsConversion(Boolean.FALSE);
                root.add(node);
        }
        return root;
    }
    public enum PnLMeasure implements Serializable{ TotalPNL, PnL_Realized, PnL_UnRealized, PnL_Funding, PnL_ValueAdjustments}

    public static final String GET_GREEK_SETTINGS = "getGreekSettings";

    public static List<GreekSetting> getGreekSettings() {
        return HibernateUtil.getObjectsWithQuery("from GreekSetting");
    }
    public static final String GET_GREEK_SETTING = "getGreekSetting";

    public static GreekSetting getGreekSetting(String pricerMeasure) {
        // no query cache : name is the key
        return (GreekSetting) HibernateUtil.getObjectWithQuery("from GreekSetting gs where gs.pricerMeasure='" + pricerMeasure + StringUtils.QUOTE);
    }
    public static final String DELETE_GREEK_SETTING = "deleteGreekSetting";

    public static void deleteGreekSetting(GreekSetting greekSetting) {
        HibernateUtil.deleteObject(greekSetting);
    }
    public static final String SAVE_GREEK_SETTING = "saveGreekSetting";

    public static Integer saveGreekSetting(GreekSetting greekSetting) {
        return (Integer) HibernateUtil.saveObject(greekSetting);
    }

    public static final String STORE_GREEK_SETTING = "storeGreekSetting";

    public static void storeGreekSetting(GreekSetting greekSetting) {
        HibernateUtil.storeObject(greekSetting);
    }

    public static final String STORE_STRESS = "storeStress";

    public static Stress storeStress(Stress stress) {
        if (stress.getStressId() == null) {
            Integer id = (Integer) HibernateUtil.saveObject(stress);
            stress.setStressId(id);
        } else {
            HibernateUtil.storeObject(stress);
        }
        return stress;
    }
}
