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
package org.gaia.dao.legalEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.gaia.dao.referentials.DomainValuesAccessor;
import org.gaia.dao.reports.FilterAccessor;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.trades.ProductConst;
import org.gaia.domain.legalEntity.Agreement;
import org.gaia.domain.legalEntity.Agreement.AgreementType;
import org.gaia.domain.legalEntity.BoAccount;
import org.gaia.domain.legalEntity.BoAccountIntermediary;
import org.gaia.domain.legalEntity.CreditContractType;
import org.gaia.domain.legalEntity.CreditEntity;
import org.gaia.domain.legalEntity.CreditEvent;
import org.gaia.domain.legalEntity.LegalEntity;
import org.gaia.domain.legalEntity.LegalEntityAttribute;
import org.gaia.domain.legalEntity.LegalEntityRole;
import org.gaia.domain.legalEntity.MarginClearerRule;
import org.gaia.domain.referentials.Rating;
import org.gaia.domain.reports.Filter;
import org.gaia.domain.reports.FilterGroup;
import org.gaia.domain.reports.Position;
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * @author Benjamin Frerejean
 */
public class LegalEntityAccessor {

    private static final Logger logger = Logger.getLogger(LegalEntityAccessor.class);
    public static final String LEGAL_ENTITY_ATTRIBUTE = "legalEntityAttributes";
    public static final String LEGAL_ENTITY_ROLES = "legalEntityRole";
    public static final String STORE_LEGAL_ENTITY = "storeLegalEntity";

    /**
     * stores a legal entity.
     *
     * @param legalEntity
     * @return
     */
    public static LegalEntity storeLegalEntity(LegalEntity legalEntity) {
        if (legalEntity.getLegalEntityId() == null) {
            LegalEntity testUnique = getLegalEntityByShortName(legalEntity.getShortName());
            if (testUnique != null) {
                legalEntity.setLegalEntityId(testUnique.getLegalEntityId());
            }
        }

        legalEntity = (LegalEntity) HibernateUtil.storeAndReturnObject(legalEntity);
        return legalEntity;
    }
    public static final String GET_ALL_ROLES = "getRoles";

    /**
     * gets the list of all legal entity roles
     *
     * @return
     */
    public static List<String> getRoles() {
        return DomainValuesAccessor.getDomainValuesByName(LEGAL_ENTITY_ROLES);
    }
    public static final String GET_ATTRIBUTES = "getAttributes";

    /**
     * gets the list of all attributes
     *
     * @return
     */
    public static List<String> getAttributes() {
        return DomainValuesAccessor.getDomainValuesByName(LEGAL_ENTITY_ATTRIBUTE);
    }
    public static final String GET_LEGAL_ENTITY_BY_ID = "getLegalEntityById";

    /**
     * get a legal entity by id
     *
     * @param legalEntityId
     * @return
     */
    public static LegalEntity getLegalEntityById(Integer legalEntityId) {
        return (LegalEntity) HibernateUtil.getObject(LegalEntity.class, legalEntityId);
    }
    public static final String GET_LEGAL_ENTITY_BY_SHORT_NAME = "getLegalEntityByShortName";

    /**
     * gest a legal entity by name
     *
     * @param name
     * @return
     */
    public static LegalEntity getLegalEntityByShortName(String name) {
        return (LegalEntity) HibernateUtil.getObjectWithQueryWithCache("from LegalEntity le left join fetch le.parent where le.shortName ='" + name + StringUtils.QUOTE);
    }
    public static final String GET_LEGAL_ENTITY_ROLE_BY_LEGAL_ENTITY_ID_AND_ROLE = "getLegalEntityRoleByLegalEntityIdAndRole";

    /**
     * gest a legal entity by id and role
     *
     * @param legalEntityId
     * @param role
     * @return
     */
    public static LegalEntityRole getLegalEntityRoleByLegalEntityIdAndRole(Integer legalEntityId, String role) {
        return (LegalEntityRole) HibernateUtil.getObjectWithQueryWithCache("from LegalEntityRole where legal_entity_id =" + legalEntityId + " and role='" + role + StringUtils.QUOTE);
    }
    public static final String GET_LEGAL_ENTITY_ATTRIBUTE_BY_ID_AND_NAME = "getLegalEntityAttributeByIdAndName";

    /**
     * gest a legal entity attribute by id and attribute name
     *
     * @param legalEntityId
     * @param attributeName
     * @return
     */
    public static LegalEntityAttribute getLegalEntityAttributeByIdAndName(Integer legalEntityId, String attributeName) {
        return (LegalEntityAttribute) HibernateUtil.getObjectWithQueryWithCache("from LegalEntityAttribute where legal_entity_id =" + legalEntityId + " and attribute_name='" + attributeName + StringUtils.QUOTE);
    }
    public static final String GET_LEGAL_ENTITY_BY_ATTRIBUTE = "getLegalEntityByAttribute";

    /**
     * gest a legal entity with an attribute value
     *
     * @param attributeValue
     * @return
     */
    public static LegalEntity getLegalEntityByAttribute(String attributeValue) {
        Object[] objs = (Object[]) HibernateUtil.getObjectWithQueryWithCache("from LegalEntity le inner join le.legalEntityAttributes lea where lea.attributeValue ='" + attributeValue + StringUtils.QUOTE);
        return (LegalEntity) objs[0];
    }
    public static final String GET_LEGAL_ENTITY_ATTRIBUTES = "getLegalEntityAttributes";

    /**
     * gest the attributes of a legal entity
     *
     * @param legalEntityId
     * @return
     */
    public static List<LegalEntityAttribute> getLegalEntityAttributes(Integer legalEntityId) {
        return (List<LegalEntityAttribute>) HibernateUtil.getObjectsWithQuery("from LegalEntityAttribute where legal_entity_id =" + legalEntityId);
    }
    public static final String DELETE_LEGAL_ENTITY_ATTRIBUTE = "getLegalEntityAttributes";

    /**
     * deletes a legal entity attribute
     *
     * @param attribute
     */
    public static void deleteLegalEntityAttribute(LegalEntityAttribute attribute) {
        HibernateUtil.deleteObject(attribute);
    }
    public static final String GET_LEGAL_ENTITY_ROLES = "getLegalEntityRoles";

    /**
     * gest the roles of e legal entity
     *
     * @param legalEntityId
     * @return
     */
    public static ArrayList<LegalEntityRole> getLegalEntityRoles(Integer legalEntityId) {
        return (ArrayList<LegalEntityRole>) HibernateUtil.getObjectsWithQuery("from LegalEntityRole where legal_entity_id =" + legalEntityId);
    }
    public static final String GET_LEGAL_ENTITY_RATINGS = "getLegalEntityRatings";

    /**
     * gest the ratings of e legal entity
     *
     * @param legalEntityId
     * @return
     */
    public static ArrayList<Rating> getLegalEntityRatings(Integer legalEntityId) {
        ArrayList<Rating> ret = new ArrayList();
        List list = HibernateUtil.getObjectsWithQuery("from Rating r inner join r.legalEntities le where le.legalEntityId=" + legalEntityId);
        for (Object o : list) {
            Rating r = (Rating) ((Object[]) o)[0];
            ret.add(r);
        }
        return ret;
    }
    public static final String GET_LEGAL_ENTITIES_BY_ROLE = "getLegalEntitiesByRole";

    /**
     * get all legal entities with a given role
     *
     * @param role
     * @return
     */
    public static List<LegalEntity> getLegalEntitiesByRole(String role) {
        String query = "select ler.legalEntity from LegalEntityRole as ler where ler.role ='" + role + StringUtils.QUOTE;
        return (List<LegalEntity>) HibernateUtil.getObjectsWithQueryWithCache(query);
    }
    /**
     * get all legal entities names with a given role
     *
     */
    public static final String GET_SHORT_NAMES_BY_ROLES = "getShortNamesByRoles";

    public static List<String> getShortNamesByRoles(Collection<String> roleList) {
        return (List<String>) HibernateUtil.getObjectsWithQuery("select le.shortName from LegalEntity le inner join le.roles ler where ler.role IN " + StringUtils.collectionToSQLString(roleList) + " order by le.shortName");
    }
    public static final String GET_INTERNAL_COUNTERPARTIES = "getInternalCounterparties";

    /**
     * get all internal counterparties
     *
     * @return
     */
    public static List<String> getInternalCounterparties() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name);
        return getShortNamesByRoles(roles);
    }
    public static final String GET_FUNDS = "getFunds";

    /**
     * get all funds
     *
     * @return
     */
    public static List<String> getFunds() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.FUND_ROLE.name);
        return getShortNamesByRoles(roles);
    }
    public static final String GET_COUNTERPARTIES = "getCounterparts";

    /**
     * get all counterparties names
     *
     * @return
     */
    public static List<String> getCounterparts() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name);
        return getShortNamesByRoles(roles);
    }
    public static final String GET_LEGAL_ENTITIES = "getLegalEntities";

    /**
     * get all legal entities names with a legal entity role
     *
     * @return
     */
    public static List<String> getLegalEntities() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name);
        return getShortNamesByRoles(roles);
    }
    public static final String GET_ISSUERS = "getIssuers";

    /**
     * get all issuers names
     *
     * @return
     */
    public static List<String> getIssuers() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.ISSUER_ROLE.name);
        return LegalEntityAccessor.getShortNamesByRoles(roles);
    }
    public static final String GET_MARKETS = "getMarkets";

    /**
     * get all market names
     *
     * @return
     */
    public static List<String> getMarkets() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.MARKET_ROLE.name);
        return LegalEntityAccessor.getShortNamesByRoles(roles);
    }
    public static final String GET_CCPS = "getCCPs";

    /**
     * get all ccp names
     *
     * @return
     */
    public static List<String> getCCPs() {
        Collection<String> roles = new ArrayList<>();
        roles.add(LegalEntityRole.LegalEntityRoleEnum.CCP_ROLE.name);
        return LegalEntityAccessor.getShortNamesByRoles(roles);
    }
    public static final String LOAD_ALL_LEGAL_ENTITIES = "loadAllLegalEntities";

    /**
     * get all legal entities
     *
     * @return
     */
    public static List loadAllLegalEntities() {
        return HibernateUtil.getObjects(LegalEntity.class.getSimpleName(), "shortName");
    }
    public static final String LOAD_LEGAL_ENTITY_SHORT_NAMES = "loadLegalEntityShortNames";

    /**
     * get all legal entities names
     * @return
     */
    public static List<String> loadLegalEntityShortNames() {
        return HibernateUtil.getObjectsWithQuery("select le.shortName from LegalEntity le");
    }
    public static final String LOAD_LEGAL_ENTITIES_WITH_FINDER = "loadLegalEntitiesWithFinder";

    /**
     * loading for LegalEntities With a Finder .
     *
     * @param entityIds
     * @param attributeName
     * @param name
     * @param role
     * @param attributeValue
     * @return
     */
    public static List<Object[]> loadLegalEntitiesWithFinder(String entityIds, String attributeName, String name, String role, String attributeValue) {

        List returnList = null;
        try {
            String query = "select le.legal_entity_id,le.short_name,lea.attribute_value,ler.role from legal_entity le";
            query = query + " left outer join legal_entity_attribute lea on le.legal_entity_id=lea.legal_entity_id";
            query = query + " left outer join legal_entity_role ler on le.legal_entity_id=ler.legal_entity_id";
            query = query + " where 1=1";
            if (entityIds != null && !entityIds.isEmpty()) {
                query = query + " and le.legal_entity_id in (" + entityIds + ")";
            } else {
                if (!name.isEmpty()) {
                    query = query + " and upper(le.short_name) like '" + name.toUpperCase() + "%'";
                }
                if (!role.isEmpty()) {
                    query = query + "  and ler.role in ('" + role + "')";
                }
                if (!attributeValue.isEmpty() && !attributeName.isEmpty()) {
                    query = query + "  and lea.attribute_name='" + attributeName + StringUtils.QUOTE;
                    query = query + "  and lea.attribute_value like '" + attributeValue + "%'";
                }
            }
            query = query + " order by le.short_name,le.legal_entity_id,ler.role";
            returnList = HibernateUtil.getListWithSQLQuery(query);
        } catch (HibernateException he) {
            logger.error("error in loading for LegalEntities With a Finder " + StringUtils.formatErrorMessage(he));
        }
        return returnList;
    }
    public static final String DELETE_LEGAL_ENTITY = "deleteLegalEntity";

    /**
     * delete a LegalEntity .
     *
     * @param legalEntity
     * @return
     */
    public static String deleteLegalEntity(LegalEntity legalEntity) {

        String ret = null;

        try {

            // checks if deleting is possible
            if (legalEntity == null) {
                return null;
            }
            List<LegalEntity> children = getChildrenLegalEntities(legalEntity.getLegalEntityId());
            if (children.size() > 0) {
                ret = "Deleting is impossible: children are present";
            }
            List<Trade> trades = (List) HibernateUtil.getObjectsWithQuery("from Trade t where t.counterparty.legalEntityId=" + legalEntity.getLegalEntityId());
            if (trades.size() > 0) {
                ret = "Deleting is impossible: trades are present";
            }
            trades = (List) HibernateUtil.getObjectsWithQuery("from Trade t where t.internalCounterparty.legalEntityId=" + legalEntity.getLegalEntityId());
            if (trades.size() > 0) {
                ret = "Deleting is impossible: trades are present";
            }
            List<Position> positions = (List) HibernateUtil.getObjectsWithQuery("from Position p where p.counterparty.legalEntityId=" + legalEntity.getLegalEntityId());
            if (positions.size() > 0) {
                ret = "Deleting is impossible: positions are present";
            }
            positions = (List) HibernateUtil.getObjectsWithQuery("from Position p where p.internalCounterparty.legalEntityId=" + legalEntity.getLegalEntityId());
            if (positions.size() > 0) {
                ret = "Deleting is impossible: positions are present";
            }

            if (ret == null) {
                // attributes
                Collection<LegalEntityAttribute> attributes = getLegalEntityAttributes(legalEntity.getLegalEntityId());
                if (attributes != null) {
                    for (LegalEntityAttribute attribute : attributes) {
                        HibernateUtil.deleteObject(attribute);
                    }
                }
                //events
                List<CreditEvent> events = getCreditEventByIssuer(legalEntity.getLegalEntityId());
                if (events != null) {
                    for (CreditEvent event : events) {
                        HibernateUtil.deleteObject(event);
                    }
                }
                //bo accounts
                HibernateUtil.executeQuery("delete from BoAccountIntermediary bai where bai.account.accountId in (select a.accountId from BoAccount a where a.client.legalEntityId=" + legalEntity.getLegalEntityId() + ")");

                HibernateUtil.executeQuery("delete from BoAccountIntermediary bai where bai.account.accountId in (select a.accountId from BoAccount a where a.accountManager.legalEntityId=" + legalEntity.getLegalEntityId() + ")");

                HibernateUtil.executeQuery("delete from BoAccount ba where ba.client.legalEntityId=" + legalEntity.getLegalEntityId());

                HibernateUtil.executeQuery("delete from BoAccount ba where ba.accountManager.legalEntityId=" + legalEntity.getLegalEntityId());
                // margin rules
                HibernateUtil.executeQuery("delete from MarginClearerRule mcr where mcr.legalEntity.legalEntityId=" + legalEntity.getLegalEntityId());
                // ccp filter groups
                FilterGroup group = FilterAccessor.getFilterGroup(LegalEntity.class.getName(), legalEntity.getLegalEntityId());
                if (group != null) {
                    if (group.getFilterCollection() != null){
                        for (Filter filter : group.getFilterCollection()) {
                            filter.getFilterGroups().remove(group);
                        }
                    }
                    HibernateUtil.deleteObject(group);
                }

                // product issuers
                HibernateUtil.executeQuery("update ProductCredit pc set pc.issuer=null where pc.issuer.legalEntityId=" + legalEntity.getLegalEntityId());
                // positions
                HibernateUtil.executeQuery("update Position p set p.ccp=null where p.ccp.legalEntityId=" + legalEntity.getLegalEntityId());

                HibernateUtil.executeQuery("update Position p set p.clearingMember=null where p.clearingMember.legalEntityId=" + legalEntity.getLegalEntityId());

                HibernateUtil.executeQuery("update Position p set p.market=null where p.market.legalEntityId=" + legalEntity.getLegalEntityId());
                // trade entities
                HibernateUtil.executeQuery("delete TradeEntity te where te.legalEntity.legalEntityId=" + legalEntity.getLegalEntityId());

                HibernateUtil.deleteObject(legalEntity);
            } else {
                logger.warn("LEGAL ENTITY NOT STORED " + ret);
            }
        } catch (HibernateException he) {
            logger.error("error in deleting of a LegalEntity" + StringUtils.formatErrorMessage(he));
        }
        return ret;
    }
    public static final String DELETE_LEGAL_ENTITY_ROLE = "deleteLegalEntityRole";

    /**
     * delete a legal entity role
     *
     * @param role
     */
    public static void deleteLegalEntityRole(LegalEntityRole role) {
        HibernateUtil.deleteObject(role);
    }
    public static final String GET_AGREEMENT_TYPES = "getAgreementTypes";

    /**
     * get agreement types
     *
     * @return
     */
    public static List<String> getAgreementTypes() {
        AgreementType[] arr = Agreement.AgreementType.values();
        List<String> ret = new ArrayList();
        for (AgreementType agreementType : arr) {
            ret.add(agreementType.name());
        }
        return ret;
    }
    public static final String STORE_AGREEMENT = "storeAgreement";

    /**
     * store an agreement
     *
     * @param agreement
     */
    public static void storeAgreement(Agreement agreement) {
        HibernateUtil.storeObject(agreement);
    }
    public static final String GET_AGREEMENT_BY_ID = "getAgreementById";

    /**
     * get an agreement by id
     *
     * @param agreementId
     * @return
     */
    public static Agreement getAgreementById(Integer agreementId) {
        return (Agreement) HibernateUtil.getObject(Agreement.class, agreementId);
    }
    public static final String LOAD_AGREEMENTS_BY_TYPE = "loadAgreementsByType";

    /**
     * get agreements by type
     *
     * @param type
     * @return
     */
    public static List loadAgreementsByType(String type) {
        String query = "select agreement_id,le1.short_name as le1_short_name,le2.short_name as le2_short_name,a.agreement_type"
                + " from agreement a,legal_entity le1,legal_entity le2"
                + " where a.legal_entity_1=le1.legal_entity_id and a.legal_entity_2=le2.legal_entity_id"
                + " and a.agreement_type='" + type + StringUtils.QUOTE;
        return HibernateUtil.getListWithSQLQuery(query);

    }
    public static final String GET_PARENT_LEGAL_ENTITY = "getParentLegalEntity";

    /**
     * retrive the really legal parent of an entity .
     *
     * @param legalEntity
     * @return
     */
    public static LegalEntity getParentLegalEntity(LegalEntity legalEntity) {
        if (isLegalEntity(legalEntity)) {
            return legalEntity;
        } else if (legalEntity != null) {
            LegalEntity parent = legalEntity.getParent();
            return getParentLegalEntity(parent);
        } else {
            return null;
        }
    }

    public static final String GET_CHILDREN_LEGAL_ENTITIES = "getChildrenLegalEntities";

    /**
     * load the children of a legal entity .
     *
     * @param legalEntityId
     * @return
     */
    public static List<LegalEntity> getChildrenLegalEntities(Integer legalEntityId) {
        return (List<LegalEntity>) HibernateUtil.getObjectsWithQuery("from LegalEntity le where le.parent.legalEntityId=" + legalEntityId);
    }

    /**
     * checks if a LegalEntity has a legal entity role or not.
     *
     * @param legalEntity
     * @return
     */
    public static boolean isLegalEntity(LegalEntity legalEntity) {
        return hasRole(legalEntity, LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name);
    }

    /**
     * checks if a LegalEntity is internal counterparty
     *
     * @param legalEntity
     * @return
     */
    public static boolean isInternal(LegalEntity legalEntity) {
        return hasRole(legalEntity, LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name);
    }

    /**
     * checks if the entity has the specified role or not.
     *
     * @param legalEntity
     * @param role
     * @return
     */
    public static boolean hasRole(LegalEntity legalEntity, String role) {
        if (legalEntity != null) {
            if (legalEntity.getRoles() != null) {
                for (LegalEntityRole leRole : legalEntity.getRoles()) {
                    if (leRole.getRole().equalsIgnoreCase(role)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * get the agreement on a trade
     *
     * @param trade
     * @return
     */
    public static Agreement getAgreementByTrade(Trade trade) {
        Agreement agreement = null;
        LegalEntity internal = LegalEntityAccessor.getParentLegalEntity(trade.getInternalCounterparty());
        if (internal == null) {
            internal = trade.getInternalCounterparty();
        }
        LegalEntity counterparty = LegalEntityAccessor.getParentLegalEntity(trade.getCounterparty());

        if (internal != null && counterparty != null) {
            /**
             * look for agreement
             */
            agreement = LegalEntityAccessor.getAgreementByEntities(internal.getLegalEntityId(), counterparty.getLegalEntityId());
        }
        return agreement;
    }
    public static final String GET_PARENT_BY_ENTITIES = "getAgreementByEntities";

    /**
     * get the agreement between two entities
     *
     * @param entityId1
     * @param entityId2
     * @return
     */
    public static Agreement getAgreementByEntities(Integer entityId1, Integer entityId2) {
        Agreement returnValue = null;
        String query = "from Agreement a where a.legalEntity1=:entity1 and a.legalEntity2=:entity2";

        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query queryObject = session.createQuery(query);
            queryObject.setInteger("entity1", entityId1);
            queryObject.setInteger("entity2", entityId2);
            queryObject.setCacheable(true).uniqueResult();
            List<Serializable> resultList = queryObject.list();
            if (resultList.size() > 0) {
                returnValue = (Agreement) resultList.get(0);
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
        return returnValue;
    }
    public static final String GET_MARGIN_CLEARER_RULES = "getMarginClearerRules";

    /**
     * get the Margin Clearer Rules on a legal entity
     *
     * @param legalEntityId
     * @return
     */
    public static List<MarginClearerRule> getMarginClearerRules(Integer legalEntityId) {
        return (List<MarginClearerRule>) HibernateUtil.getObjectsWithQuery("from MarginClearerRule mcr where mcr.legalEntity.legalEntityId=" + legalEntityId);
    }
    public static final String GET_MARGIN_CLEARER_RULE = "getMarginClearerRule";

    /**
     * get a Margin Clearer Rule
     *
     * @param ruleId
     * @return
     */
    public static MarginClearerRule getMarginClearerRule(Integer ruleId) {
        return (MarginClearerRule) HibernateUtil.getObjectWithQuery("from MarginClearerRule where Margin_Clearer_Rule_id=" + ruleId);
    }
    public static final String STORE_MARGIN_CLEARER_RULE = "storeMarginClearerRule";

    /**
     * store a Margin Clearer Rule
     *
     * @param marginClearerRule
     */
    public static void storeMarginClearerRule(MarginClearerRule marginClearerRule) {

        if (marginClearerRule.getMarginClearerRuleId() == null) {
            Serializable sId = HibernateUtil.saveObject(marginClearerRule);
            marginClearerRule.setMarginClearerRuleId((Integer) sId);
        } else {
            HibernateUtil.updateObject(marginClearerRule);
        }
    }
    public static final String STORE_BO_ACCOUNT = "storeBoAccount";

    /**
     * store an account
     *
     * @param account
     */
    public static void storeBoAccount(BoAccount account) {

        if (account.getAccountId() == null) {
            Serializable sId = HibernateUtil.saveObject(account);
            account.setAccountId((Integer) sId);
        } else {
            HibernateUtil.updateObject(account);
        }
    }
    public static final String DELETE_BO_ACCOUNT = "deleteBoAccount";

    /**
     * delete an account
     *
     * @param account
     */
    public static void deleteBoAccount(BoAccount account) {
        HibernateUtil.deleteObject(account);
    }
    public static final String GET_BO_ACCOUNT = "getBoAccount";

    /**
     * get an account by id
     *
     * @param accountId
     * @return
     */
    public static BoAccount getBoAccount(Integer accountId) {
        return (BoAccount) HibernateUtil.getObject(BoAccount.class, accountId);
    }
    public static final String GET_BO_ACCOUNT_BY_CODE = "getBoAccountByCode";

    /**
     * get an account by code
     *
     * @param code
     * @return
     */
    public static BoAccount getBoAccountByCode(String code) {
        return (BoAccount) HibernateUtil.getObjectWithQuery("from BoAccount a where a.accountCode='" + code + StringUtils.QUOTE);
    }
    public static final String GET_BO_ACCOUNTS = "getBoAccounts";

    /**
     * get accounts by client, manager and type
     *
     * @param clientId
     * @param accountManagerId
     * @param type
     * @return
     */
    public static List<BoAccount> getBoAccounts(Integer clientId, Integer accountManagerId, String type) {
        List<BoAccount> ret = new ArrayList();
        String query = "from BoAccount a inner join a.client cl inner join a.accountManager am where 1=1";
        if (clientId != null) {
            query += " and cl.legalEntityId=" + clientId;
        }
        if (accountManagerId != null) {
            query += " and am.legalEntityId=" + accountManagerId;
        }
        if (type != null) {
            query += " and a.type='" + type + StringUtils.QUOTE;
        }
        List<Object[]> objectList = (List<Object[]>) HibernateUtil.getObjectsWithQuery(query);
        for (Object[] obs : objectList) {
            ret.add((BoAccount) obs[0]);
        }
        return ret;
    }

    /**
     * get default intermediary in the list
     *
     * @param intermediaries
     * @return
     */
    public static BoAccountIntermediary getDefaultIntermediaryFromList(ArrayList<BoAccountIntermediary> intermediaries) {
        for (BoAccountIntermediary boAccountIntermediary : intermediaries) {
            if (boAccountIntermediary.getIsdefault()) {
                return boAccountIntermediary;
            }
        }
        return null;
    }
    public static final String GET_CREDIT_EVENT_BY_ID = "getCreditEventById";

    /**
     * get a credit event by id
     *
     * @param creditEventId
     * @return
     */
    public static CreditEvent getCreditEventById(Integer creditEventId) {
        return (CreditEvent) HibernateUtil.getObjectWithQuery("from CreditEvent ce where ce.creditEventId =" + creditEventId);
    }

    public static final String GET_CREDIT_EVENT_BY_ISSUER = "getCreditEventByIssuer";

    /**
     * get a credit event by issuer id
     *
     * @param issuerId
     * @return
     */
    public static List<CreditEvent> getCreditEventByIssuer(Integer issuerId) {
        return (List) HibernateUtil.getObjectsWithQuery("from CreditEvent ce where ce.issuer.legalEntityId =" + issuerId);
    }
    public static final String STORE_CREDIT_EVENT = "storeCreditEvent";

    /**
     * create/update a credit event
     *
     * @param creditEvent
     * @return
     */
    public static CreditEvent storeCreditEvent(CreditEvent creditEvent) {
        return (CreditEvent) HibernateUtil.storeAndReturnObject(creditEvent);
    }
    public static final String STORE_CREDIT_ENTITY = "storeCreditEntity";

    public static final String SAVE_CREDIT_EVENT = "saveCreditEvent";

    /**
     * creates a credit event
     *
     * @param creditEntity
     * @return
     */
    public static Integer saveCreditEvent(CreditEvent creditEntity) {
        return (Integer) HibernateUtil.saveObject(creditEntity);
    }
    public static final String DELETE_CREDIT_EVENT = "deleteCreditEvent";

    /**
     * delete a credit event
     *
     * @param creditEvent
     */
    public static void deleteCreditEvent(CreditEvent creditEvent) {
        HibernateUtil.deleteObject(creditEvent);
    }
    public static final String LOAD_PRODUCTS_BY_CREDIT_EVENT = "loadProductsByEvent";

    /**
     * load the products affected by a credit event
     *
     * @param creditEvent
     * @return
     */
    public static List<Product> loadProductsByEvent(CreditEvent creditEvent) {
        String query = "from Product p inner join p.productCredits pf inner join pf.issuer i"
                + " where i.shortName='" + creditEvent.getIssuer().getShortName() + StringUtils.QUOTE
                + " and p.maturityDate>='" + HibernateUtil.dateFormat.format(creditEvent.getDefaultDate()) + StringUtils.QUOTE
                + " and p.status in ('" + ProductConst.ProductStatus.Active.name() + "','" + ProductConst.ProductStatus.PartlyDefaulted.name() + "')"
                + " and p.productId not in (select pe.product.productId from ProductEvent pe where pe.eventId=" + creditEvent.getCreditEventId() + " and product_id is not null)"
                + " order by p.shortName";

        List<Object[]> objectList = (List) HibernateUtil.getObjectsWithQuery(query);
        List<Product> products = new ArrayList();
        for (Object[] array : objectList) {
            Product product = (Product) array[0];
            List<Product> supers = ProductAccessor.getSuperProductList(product.getId());
            if (supers != null && !supers.isEmpty()) {
                for (Product super_ : supers) {
                    products.add(super_);
                }
            } else {
                products.add(product);
            }
        }
        return products;
    }
    public static final String LOAD_CREDIT_ENTITY_EVENTS = "loadCreditEntityEvents";

    /**
     * load the trades affected by a credit event
     *
     * @param entityName
     * @return
     */
    public static List<CreditEvent> loadCreditEntityEvents(String entityName) {
        String query = "from CreditEvent e where e.issuer.shortName='" + entityName + StringUtils.QUOTE;
        return (List) HibernateUtil.getObjectsWithQuery(query);
    }
    public static final String GET_CREDIT_ENTITY = "getCreditEntity";

    /**
     * create/update a credit event
     *
     * @param creditEntity
     * @return
     */
    public static CreditEntity storeCreditEntity(CreditEntity creditEntity) {
        return (CreditEntity) HibernateUtil.storeAndReturnObject(creditEntity);
    }

    /**
     * get the credit entity of a legal entity
     *
     * @param entityId
     * @return
     */
    public static CreditEntity getCreditEntity(Integer entityId) {
        String query = "from CreditEntity e where e.legalEntity.legalEntityId='" + entityId + StringUtils.QUOTE;
        return (CreditEntity) HibernateUtil.getObjectWithQuery(query);
    }
    public static final String GET_CREDIT_EVENTS = "getCreditEvents";

    /**
     * gets the list of existing credit events in domain values
     *
     * @return
     */
    public static List getCreditEvents() {
        return DomainValuesAccessor.getDomainValuesByName(ProductConst.PRODUCT_CREDIT_EVENTS);
    }
    public static final String GET_CREDIT_CONTRACT_TYPE_NAMES = "getCreditContractTypeNames";

    /**
     * get the credit contracts
     *
     * @return
     */
    public static List<String> getCreditContractTypeNames() {
        String query = "select cct.creditContractTypeName from CreditContractType cct";
        return (List<String>) HibernateUtil.getObjectsWithQuery(query);
    }
    public static final String GET_CREDIT_CONTRACT_TYPE_BY_NAME = "getCreditContractTypeByName";

    /**
     * get a credit contract
     *
     * @param name
     * @return
     */
    public static CreditContractType getCreditContractTypeByName(String name) {
        String query = "from CreditContractType cct where cct.creditContractTypeName='" + name + StringUtils.QUOTE;
        return (CreditContractType) HibernateUtil.getObjectWithQuery(query);
    }
    public static final String GET_CREDIT_ISSUER_ID = "getCreditIssuerId";

    /**
     * get a credit issuer
     *
     * @param product
     * @return
     */
    public static Integer getCreditIssuerId(Product product) {
        if (product.getSubProducts() != null && product.getSubProducts().isEmpty()) {
            String query = "select pc.issuer.legalEntityId from ProductCredit pc where pc.product.productId=" + product.getProductId();
            return (Integer) HibernateUtil.getObjectWithQuery(query);
        } else {
            String query = "select pc.issuer.legalEntityId from ProductCredit pc join pc.product p join p.parentProducts pp where pp.productId=" + product.getProductId();
            return (Integer) HibernateUtil.getObjectWithQuery(query);
        }
    }
    public static final String DELETE_CREDIT_CONTRACT_TYPE_BY_NAME = "deleteCreditContractTypeByName";

    /**
     * delete credit contract
     *
     * @param name
     */
    public static void deleteCreditContractTypeByName(String name) {
        CreditContractType contract = getCreditContractTypeByName(name);
        HibernateUtil.deleteObject(contract);
    }
    public static final String STORE_CREDIT_CONTRACT_TYPE = "storeCreditContractType";

    /**
     * stores the credit contract
     *
     * @param contractType
     * @return
     */
    public static CreditContractType storeCreditContractType(CreditContractType contractType) {
        if (contractType.getCreditContractTypeId() == null) {
            Integer id = (Integer) HibernateUtil.saveObject(contractType);
            contractType.setCreditContractTypeId(id);
            return contractType;
        } else {
            HibernateUtil.storeObject(contractType);
            return contractType;
        }
    }

    public static LegalEntity getAnyLegalEntityByRole(String role) {
        //used by tests
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.roles r where r.role='" + role + StringUtils.QUOTE);
        return getLegalEntityById(id);
    }
}
