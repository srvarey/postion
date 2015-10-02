package org.gaia.dao.legalEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.gaia.dao.trades.ProductAccessor;
import org.gaia.dao.utils.AbstractTest;
import org.gaia.domain.legalEntity.Agreement;
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
import org.gaia.domain.trades.Product;
import org.gaia.domain.trades.ProductEvent;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.StringUtils;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author Benjamin Frerejean
 */
public class LegalEntityAccessorTest extends AbstractTest {

    /**
     * Test of storeLegalEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreLegalEntity() {
        System.out.println("storeLegalEntity");
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setShortName("testLe");
        LegalEntityAccessor.storeLegalEntity(legalEntity);
        LegalEntity res = (LegalEntity) HibernateUtil.getObjectWithQuery("from LegalEntity le where le.shortName='testLe'");
        assertNotNull("legal entity not stored", res);
        HibernateUtil.deleteObject(res);
    }

    /**
     * Test of getRoles method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetRoles() {
        System.out.println("getRoles");
        List<String> result = LegalEntityAccessor.getRoles();
        assertNotNull("null role list ", result);
        assertFalse("empty role list ", result.isEmpty());
    }

    /**
     * Test of getAttributes method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        List<String> result = LegalEntityAccessor.getAttributes();
        assertNotNull("null attribute list ", result);
        assertFalse("empty attribute list ", result.isEmpty());
    }

    /**
     * Test of getLegalEntityById method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityById() {
        System.out.println("getLegalEntityById");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le");
        LegalEntity result = LegalEntityAccessor.getLegalEntityById(id);
        assertNotNull("legal entity not found", result);
    }

    /**
     * Test of getLegalEntityByShortName method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityByShortName() {
        System.out.println("getLegalEntityByShortName");
        String name = (String) HibernateUtil.getObjectWithQuery("select min(le.shortName) from LegalEntity le");
        LegalEntity result = LegalEntityAccessor.getLegalEntityByShortName(name);
        assertNotNull("legal entity not found", result);
    }

    /**
     * Test of getLegalEntityRoleByLegalEntityIdAndRole method, of class
     * LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityRoleByLegalEntityIdAndRole() {
        System.out.println("getLegalEntityRoleByLegalEntityIdAndRole");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(ler.legalEntityRoleId) from LegalEntityRole ler");
        LegalEntityRole ler = (LegalEntityRole) HibernateUtil.getObject(LegalEntityRole.class, id);
        LegalEntityRole result = LegalEntityAccessor.getLegalEntityRoleByLegalEntityIdAndRole(ler.getLegalEntity().getLegalEntityId(), ler.getRole());
        assertNotNull("missing le role", result);
    }

    /**
     * Test of getLegalEntityAttributeByIdAndName method, of class
     * LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityAttributeByIdAndName() {
        System.out.println("getLegalEntityAttributeByIdAndName");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.legalEntityAttributes lea where lea.attributeName is not null");
        String attributeName = (String) HibernateUtil.getObjectWithQuery("select lea.attributeName from LegalEntityAttribute lea where lea.legalEntity.legalEntityId=" + id);
        LegalEntityAttribute result = LegalEntityAccessor.getLegalEntityAttributeByIdAndName(id, attributeName);
        assertNotNull("missing le attribute", result);
    }

    /**
     * Test of getLegalEntityByAttribute method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityByAttribute() {
        System.out.println("getLegalEntityByAttribute");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.legalEntityAttributes lea where lea.attributeName is not null");
        String attributeValue = (String) HibernateUtil.getObjectWithQuery("select lea.attributeValue from LegalEntityAttribute lea where lea.legalEntity.legalEntityId=" + id);
        LegalEntity result = LegalEntityAccessor.getLegalEntityByAttribute(attributeValue);
        assertNotNull("legal entity not found by attribute", result);
    }

    /**
     * Test of getLegalEntityAttributes method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityAttributes() {
        System.out.println("getLegalEntityAttributes");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.legalEntityAttributes lea where lea.attributeName is not null");
        List<LegalEntityAttribute> result = LegalEntityAccessor.getLegalEntityAttributes(id);
        assertNotNull("null attribute list ", result);
        assertFalse("empty attribute list ", result.isEmpty());
    }

    /**
     * Test of deleteLegalEntityAttribute method, of class LegalEntityAccessor.
     */
    @Test
    public void testDeleteLegalEntityAttribute() {
        System.out.println("deleteLegalEntityAttribute");
        Integer id = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le");
        LegalEntity le = (LegalEntity) HibernateUtil.getObject(LegalEntity.class, id);
        LegalEntityAttribute newOne = new LegalEntityAttribute();
        newOne.setAttributeName("test");
        newOne.setAttributeValue("value");
        newOne.setLegalEntity(le);
        newOne = (LegalEntityAttribute) HibernateUtil.storeAndReturnObject(newOne);
        LegalEntityAccessor.deleteLegalEntityAttribute(newOne);
        LegalEntityAttribute res = (LegalEntityAttribute) HibernateUtil.getObject(LegalEntityAttribute.class, newOne.getLegalEntityId());
        assertNull("legal entity attribute not deleted", res);
    }

    /**
     * Test of getLegalEntityRoles method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityRoles() {
        System.out.println("getLegalEntityRoles");
        Integer legalEntityId = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le");
        ArrayList<LegalEntityRole> result = LegalEntityAccessor.getLegalEntityRoles(legalEntityId);
        assertNotNull("null role list ", result);
        assertFalse("empty role list ", result.isEmpty());
    }

    /**
     * Test of getLegalEntityRatings method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityRatings() {
        System.out.println("getLegalEntityRatings");
        Integer legalEntityId = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.ratings r where r.rating is not null");
        ArrayList<Rating> result = LegalEntityAccessor.getLegalEntityRatings(legalEntityId);
        assertNotNull("null ratings list ", result);
        assertFalse("empty ratings list ", result.isEmpty());
    }

    /**
     * Test of getLegalEntitiesByRole method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntityByRole() {
        System.out.println("getLegalEntityByRole");
        String role = "InternalCounterparty";
        List<LegalEntity> result = LegalEntityAccessor.getLegalEntitiesByRole(role);
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getShortNamesByRoles method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetShortNamesByRoles() {
        System.out.println("getShortNamesByRoles");
        Collection<String> roleList = new HashSet();
        roleList.add("InternalCounterparty");
        List<String> result = LegalEntityAccessor.getShortNamesByRoles(roleList);
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getInternalCounterparties method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetInternalCounterparties() {
        System.out.println("getInternalCounterparties");
        List<String> result = LegalEntityAccessor.getInternalCounterparties();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getCounterparts method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCounterparts() {
        System.out.println("getCounterparts");
        List<String> result = LegalEntityAccessor.getCounterparts();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getLegalEntities method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetLegalEntities() {
        System.out.println("getLegalEntities");
        List<String> result = LegalEntityAccessor.getLegalEntities();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getIssuers method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetIssuers() {
        System.out.println("getIssuers");
        List<String> result = LegalEntityAccessor.getIssuers();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getMarkets method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetMarkets() {
        System.out.println("getMarkets");
        List<String> result = LegalEntityAccessor.getMarkets();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of getCCPs method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCCPs() {
        System.out.println("getCCPs");
        List<String> result = LegalEntityAccessor.getCCPs();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of loadAllLegalEntities method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadAllLegalEntities() {
        System.out.println("loadAllLegalEntities");
        List result = LegalEntityAccessor.loadAllLegalEntities();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of loadLegalEntityShortNames method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadLegalEntityShortNames() {
        System.out.println("loadLegalEntityShortNames");
        List<String> result = LegalEntityAccessor.loadLegalEntityShortNames();
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of loadLegalEntitiesWithFinder method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadLegalEntitiesWithFinder() {
        System.out.println("loadLegalEntitiesWithFinder");
        Integer legalEntityId = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le inner join le.legalEntityAttributes lea where lea.attributeName is not null");
        LegalEntity le = (LegalEntity) HibernateUtil.getObject(LegalEntity.class, legalEntityId);
        List result = LegalEntityAccessor.loadLegalEntitiesWithFinder(legalEntityId.toString(), "", "", "", "");
        assertNotNull("null entity list ", result);
        assertFalse("empty entity list ", result.isEmpty());

        result = LegalEntityAccessor.loadLegalEntitiesWithFinder("", "", le.getShortName(), "", "");
        assertFalse("empty entity list ", result.isEmpty());

        result = LegalEntityAccessor.loadLegalEntitiesWithFinder("", "", "", le.getRoles().iterator().next().getRole(), "");
        assertFalse("empty entity list ", result.isEmpty());

        LegalEntityAttribute attr = LegalEntityAccessor.getLegalEntityAttributes(legalEntityId).get(0);
        result = LegalEntityAccessor.loadLegalEntitiesWithFinder("", attr.getAttributeName(), "", "", attr.getAttributeValue());
        assertFalse("empty entity list ", result.isEmpty());
    }

    /**
     * Test of deleteLegalEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testDeleteLegalEntity() {
        System.out.println("deleteLegalEntity");
        LegalEntity legalEntity = new LegalEntity();
        legalEntity.setShortName("unit test");
        LegalEntityRole role = new LegalEntityRole();
        role.setRole("test");
        role.setLegalEntity(legalEntity);
        legalEntity.getRoles().add(role);
        HibernateUtil.storeObject(legalEntity);
        Integer id=(Integer) HibernateUtil.getObjectWithQuery("select min(f.filterId) from Filter f");
        Filter filter=(Filter)HibernateUtil.getObject(Filter.class, id);
        FilterGroup group = new FilterGroup();
        group.getFilterCollection().add(filter);
        group.setLinkedObjectClass(LegalEntity.class.getName());
        group.setLinkedObjectId(legalEntity.getLegalEntityId());
        HibernateUtil.storeObject(group);
        LegalEntityAccessor.deleteLegalEntity(legalEntity);
        LegalEntity res = (LegalEntity) HibernateUtil.getObject(LegalEntity.class, legalEntity.getLegalEntityId());
        assertNull("failed to delete legal entity", res);
    }

    /**
     * Test of deleteLegalEntityRole method, of class LegalEntityAccessor.
     */
    @Test
    public void testDeleteLegalEntityRole() {
        System.out.println("deleteLegalEntityRole");
        Integer legalEntityId = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le");
        LegalEntityRole role = new LegalEntityRole();
        role.setRole("unit test");
        LegalEntity le = (LegalEntity) HibernateUtil.getObject(LegalEntity.class, legalEntityId);
        role.setLegalEntity(le);
        HibernateUtil.storeObject(role);
        LegalEntityAccessor.deleteLegalEntityRole(role);
        LegalEntityRole res = (LegalEntityRole) HibernateUtil.getObject(LegalEntityRole.class, role.getLegalEntityRoleId());
        assertNull("failed to delete legal entity role", res);
    }

    /**
     * Test of getAgreementTypes method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAgreementTypes() {
        System.out.println("getAgreementTypes");
        List<String> result = LegalEntityAccessor.getAgreementTypes();
        assertNotNull("null agreement type list ", result);
        assertFalse("empty agreement type list ", result.isEmpty());
    }

    /**
     * Test of storeAgreement method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreAgreement() {
        System.out.println("storeAgreement");
        Agreement agreement = new Agreement();
        agreement.setAgreementType("unit test");
        LegalEntityAccessor.storeAgreement(agreement);
        assertNotNull("failed to store agreement", agreement.getAgreementId());
        HibernateUtil.deleteObject(agreement);
    }

    /**
     * Test of getAgreementById method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAgreementById() {
        System.out.println("getAgreementById");
        Integer agreementId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.agreementId) from Agreement a");
        Agreement result = LegalEntityAccessor.getAgreementById(agreementId);
        assertNotNull("agreement not found with id " + agreementId, result);
    }

    /**
     * Test of loadAgreementsByType method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadAgreementsByType() {
        System.out.println("loadAgreementsByType");
        String type = (String) HibernateUtil.getObjectWithQuery("select min(a.agreementType) from Agreement a");
        List result = LegalEntityAccessor.loadAgreementsByType(type);
        assertNotNull("agreement not found with type " + type, result);
    }

    /**
     * Test of getParentLegalEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetParentLegalEntity_LegalEntity() {
        System.out.println("getParentLegalEntity");
        Object[] objs = (Object[]) HibernateUtil.getObjectWithQuery("from LegalEntity le inner join le.roles r inner join le.children c"
                + " where r.role='" + LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name + "' and c.shortName is not null");
        LegalEntity parent = (LegalEntity) objs[0];
        LegalEntity legalEntity = (LegalEntity) objs[2];
        LegalEntity result = LegalEntityAccessor.getParentLegalEntity(legalEntity);
        assertEquals("parent legalentity not found", parent, result);
    }

    /**
     * Test of isLegalEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testIsLegalEntity_LegalEntity() {
        System.out.println("isLegalEntity");
        Object[] objs = (Object[]) HibernateUtil.getObjectWithQuery("from LegalEntity le inner join le.roles r"
                + " where r.role='" + LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name + StringUtils.QUOTE);
        LegalEntity legalEntity = (LegalEntity) objs[0];
        boolean result = LegalEntityAccessor.isLegalEntity(legalEntity);
        assertTrue("is legal check failed", result);

    }

    /**
     * Test of isInternal method, of class LegalEntityAccessor.
     */
    @Test
    public void testIsInternal() {
        System.out.println("isInternal");
        Object[] objs = (Object[]) HibernateUtil.getObjectWithQuery("from LegalEntity le inner join le.roles r"
                + " where r.role='" + LegalEntityRole.LegalEntityRoleEnum.INTERNAL_COUNTERPARTY_ROLE.name + StringUtils.QUOTE);
        LegalEntity legalEntity = (LegalEntity) objs[0];
        boolean result = LegalEntityAccessor.isInternal(legalEntity);
        assertTrue("is internal check failed", result);
    }

    /**
     * Test of hasRole method, of class LegalEntityAccessor.
     */
    @Test
    public void testHasRole() {
        System.out.println("hasRole");
        Integer legalEntityId = (Integer) HibernateUtil.getObjectWithQuery("select min(le.legalEntityId) from LegalEntity le");
        LegalEntity legalEntity = (LegalEntity) HibernateUtil.getObject(LegalEntity.class, legalEntityId);
        LegalEntityRole role = LegalEntityAccessor.getLegalEntityRoles(legalEntityId).get(0);
        boolean result = LegalEntityAccessor.hasRole(legalEntity, role.getRole());
        assertTrue("failed to test role", result);
    }

    /**
     * Test of getAgreementByTrade method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAgreementByTrade() {
        System.out.println("getAgreementByTrade");
        Integer agreementId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.agreementId) from Agreement a");
        Agreement agreement = LegalEntityAccessor.getAgreementById(agreementId);
        Trade trade = (Trade) HibernateUtil.getObjectWithQuery("from Trade t where t.internalCounterparty.legalEntityId=" + agreement.getLegalEntity1().getLegalEntityId() + " and t.counterparty.legalEntityId=" + agreement.getLegalEntity2().getLegalEntityId());
        if (trade != null) {
            Agreement result = LegalEntityAccessor.getAgreementByTrade(trade);
            assertNotNull("missing agreement", result);
        }
    }

    /**
     * Test of getAgreementByEntities method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAgreementByEntities() {
        System.out.println("getAgreementByEntities");
        Integer agreementId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.agreementId) from Agreement a");
        Agreement agreement = LegalEntityAccessor.getAgreementById(agreementId);
        Agreement result = LegalEntityAccessor.getAgreementByEntities(agreement.getLegalEntity1().getLegalEntityId(), agreement.getLegalEntity2().getLegalEntityId());
        assertEquals("missing agreement", result, agreement);
    }

    /**
     * Test of getMarginClearerRules method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetMarginClearerRules() {
        System.out.println("getMarginClearerRules");
        MarginClearerRule margin = (MarginClearerRule) HibernateUtil.getObjectWithQuery("from MarginClearerRule");
        List<MarginClearerRule> result = LegalEntityAccessor.getMarginClearerRules(margin.getLegalEntity().getLegalEntityId());
        assertNotNull("missing clearer rule", result);
    }

    /**
     * Test of getMarginClearerRule method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetMarginClearerRule() {
        System.out.println("getMarginClearerRule");
        Integer ruleId = (Integer) HibernateUtil.getObjectWithQuery("select min(mcr.marginClearerRuleId) from MarginClearerRule mcr");
        MarginClearerRule result = LegalEntityAccessor.getMarginClearerRule(ruleId);
        assertNotNull("missing margin clearer rule", result);
    }

    /**
     * Test of storeMarginClearerRule method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreMarginClearerRule() {
        System.out.println("storeMarginClearerRule");
        MarginClearerRule marginClearerRule = new MarginClearerRule();
        marginClearerRule.setMarginType("unit test");
        LegalEntityAccessor.storeMarginClearerRule(marginClearerRule);
        MarginClearerRule res = (MarginClearerRule) HibernateUtil.getObject(MarginClearerRule.class, marginClearerRule.getMarginClearerRuleId());
        assertNotNull("failed to store margin clearer rule", res);
        HibernateUtil.deleteObject(res);
    }

    /**
     * Test of storeBoAccount method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreBoAccount() {
        System.out.println("storeBoAccount");
        BoAccount account = new BoAccount();
        account.setAccountCode("unit test");
        LegalEntityAccessor.storeBoAccount(account);
        assertNotNull("failed to store account", account.getAccountId());
        HibernateUtil.deleteObject(account);
    }

    /**
     * Test of deleteBoAccount method, of class LegalEntityAccessor.
     */
    @Test
    public void testDeleteBoAccount() {
        System.out.println("deleteBoAccount");
        BoAccount account = new BoAccount();
        account.setAccountCode("unit test");
        HibernateUtil.saveObject(account);
        LegalEntityAccessor.deleteBoAccount(account);
        BoAccount result = LegalEntityAccessor.getBoAccount(account.getAccountId());
        assertNull("failed to delete account", result);

    }

    /**
     * Test of getBoAccount method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetBoAccount() {
        System.out.println("getBoAccount");
        Integer accountId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.accountId) from BoAccount a");
        BoAccount result = LegalEntityAccessor.getBoAccount(accountId);
        assertNotNull("acount not found", result);
    }

    /**
     * Test of getBoAccountByCode method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetBoAccountByCode() {
        System.out.println("getBoAccountByCode");
        String code = (String) HibernateUtil.getObjectWithQuery("select min(a.accountCode) from BoAccount a");
        BoAccount result = LegalEntityAccessor.getBoAccountByCode(code);
        assertNotNull("acount not found", result);
    }

    /**
     * Test of getBoAccounts method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetBoAccounts() {
        System.out.println("getBoAccounts");
        Integer accountId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.accountId) from BoAccount a");
        BoAccount account = (BoAccount) HibernateUtil.getObject(BoAccount.class, accountId);
        List<BoAccount> result = LegalEntityAccessor.getBoAccounts(account.getClient().getLegalEntityId(), account.getAccountManager().getLegalEntityId(), account.getType());
        assertEquals("account not found", result.iterator().next(), account);
    }

    /**
     * Test of getDefaultIntermediaryFromList method, of class
     * LegalEntityAccessor.
     */
    @Test
    public void testGetDefaultIntermediaryFromList() {
        System.out.println("getDefaultIntermediaryFromList");
        Integer accountId = (Integer) HibernateUtil.getObjectWithQuery("select min(a.accountId) from BoAccount a");
        BoAccount account = (BoAccount) HibernateUtil.getObject(BoAccount.class, accountId);
        ArrayList<BoAccountIntermediary> intermediaries = new ArrayList(account.getBoAccountIntermediaryCollection());
        BoAccountIntermediary result = LegalEntityAccessor.getDefaultIntermediaryFromList(intermediaries);
        assertNotNull("default intermediary not found", result);
    }

    /**
     * Test of getCreditEventById method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditEventById() {
        System.out.println("getCreditEventById");
        Integer creditEventId = (Integer) HibernateUtil.getObjectWithQuery("select min(evt.creditEventId) from CreditEvent evt");
        CreditEvent result = LegalEntityAccessor.getCreditEventById(creditEventId);
        assertNotNull("credit event not found", result);
    }

    /**
     * Test of storeCreditEvent method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreCreditEvent() {
        System.out.println("storeCreditEvent");
        CreditEvent creditEvent = new CreditEvent();
        creditEvent.setCreditEvent("unit test");
        CreditEvent result = LegalEntityAccessor.storeCreditEvent(creditEvent);
        assertNotNull("credit event not stored", result.getCreditEventId());
        HibernateUtil.deleteObject(result);
    }

    /**
     * Test of storeCreditEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreCreditEntity() {
        System.out.println("storeCreditEntity");
        CreditEntity creditEntity = new CreditEntity();
        creditEntity.setDefaultContractType("unit test");
        CreditEntity result = LegalEntityAccessor.storeCreditEntity(creditEntity);
        assertNotNull("failed to store credit entity", result.getCreditEntityId());
        HibernateUtil.deleteObject(result);
    }

    /**
     * Test of saveCreditEvent method, of class LegalEntityAccessor.
     */
    @Test
    public void testSaveCreditEvent() {
        System.out.println("saveCreditEvent");
        CreditEvent creditEntity = new CreditEvent();
        creditEntity.setCreditEvent("unit test");
        LegalEntityAccessor.saveCreditEvent(creditEntity);
        assertNotNull("failed to store credit event", creditEntity.getCreditEventId());
        HibernateUtil.deleteObject(creditEntity);
    }

    /**
     * Test of deleteCreditEvent method, of class LegalEntityAccessor.
     */
    @Test
    public void testDeleteCreditEvent() {
        System.out.println("deleteCreditEvent");
        CreditEvent creditEvent = new CreditEvent();
        creditEvent.setCreditEvent("unit test");
        HibernateUtil.saveObject(creditEvent);
        LegalEntityAccessor.deleteCreditEvent(creditEvent);
        assertNull("failed to delete credit event", HibernateUtil.getObject(CreditEvent.class, creditEvent.getCreditEventId()));
    }

    /**
     * Test of loadProductsByEvent method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadProductsByEvent() {
        System.out.println("loadProductsByEvent");
        Integer creditEventId = (Integer) HibernateUtil.getObjectWithQuery("select min(pe.productEventId) from ProductEvent pe where pe.eventClassName='org.gaia.dao.trades.events.CreditEventApply'");
        ProductEvent pevt = (ProductEvent) HibernateUtil.getObject(ProductEvent.class, creditEventId);
        CreditEvent creditEvent = (CreditEvent) HibernateUtil.getObject(CreditEvent.class, pevt.getEventId());
        List<Product> result = LegalEntityAccessor.loadProductsByEvent(creditEvent);
        assertNotNull("failed to load products", result);
        assertFalse("failed to load products", result.isEmpty());
    }

    /**
     * Test of loadCreditEntityEvents method, of class LegalEntityAccessor.
     */
    @Test
    public void testLoadCreditEntityEvents() {
        System.out.println("loadCreditEntityEvents");
        Integer creditEventId = (Integer) HibernateUtil.getObjectWithQuery("select min(evt.creditEventId) from CreditEvent evt");
        CreditEvent evt = (CreditEvent) HibernateUtil.getObject(CreditEvent.class, creditEventId);
        List<CreditEvent> result = LegalEntityAccessor.loadCreditEntityEvents(evt.getIssuer().getShortName());
        assertNotNull("failed to load credit entities", result);
        assertFalse("failed to load credit entities", result.isEmpty());
    }

    /**
     * Test of getCreditEntity method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditEntity() {
        System.out.println("getCreditEntity");
        Integer entityId = (Integer) HibernateUtil.getObjectWithQuery("select min(ce.legalEntity.legalEntityId) from CreditEntity ce");
        CreditEntity result = LegalEntityAccessor.getCreditEntity(entityId);
        assertNotNull("failed to get credit entity", result);
    }

    /**
     * Test of getCreditEvents method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditEvents() {
        System.out.println("getCreditEvents");
        List result = LegalEntityAccessor.getCreditEvents();
        assertNotNull("failed to load credit events", result);
        assertFalse("failed to load credit events", result.isEmpty());
    }

    /**
     * Test of getCreditContractTypeNames method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditContractTypeNames() {
        System.out.println("getCreditContractTypeNames");
        List<String> result = LegalEntityAccessor.getCreditContractTypeNames();
        assertNotNull("failed to load credit contracts", result);
        assertFalse("failed to load credit contracts", result.isEmpty());
    }

    /**
     * Test of getCreditContractTypeByName method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditContractTypeByName() {
        System.out.println("getCreditContractTypeByName");
        String name = (String) HibernateUtil.getObjectWithQuery("select min(ccType.creditContractTypeName) from CreditContractType ccType");
        CreditContractType result = LegalEntityAccessor.getCreditContractTypeByName(name);
        assertNotNull("failed to load credit contract", result);
    }

    /**
     * Test of getCreditIssuerId method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetCreditIssuerId() {
        System.out.println("getCreditIssuerId");
        Integer productId = (Integer) HibernateUtil.getObjectWithQuery("select min(pc.product.productId) from ProductCredit pc where pc.issuer is not null");
        Product product = ProductAccessor.getProductById(productId);
        Integer result = LegalEntityAccessor.getCreditIssuerId(product);
        assertNotNull("failed to load product issuer", result);
    }

    /**
     * Test of deleteCreditContractTypeByName method, of class
     * LegalEntityAccessor.
     */
    @Test
    public void testDeleteCreditContractTypeByName() {
        System.out.println("deleteCreditContractTypeByName");
        CreditContractType creditContractType = new CreditContractType();
        creditContractType.setCreditContractTypeName("unit test");
        HibernateUtil.saveObject(creditContractType);
        LegalEntityAccessor.deleteCreditContractTypeByName("unit test");
        Object res = HibernateUtil.getObject(CreditContractType.class, creditContractType.getCreditContractTypeId());
        assertNull("failed to delete credit contract", res);
    }

    /**
     * Test of storeCreditContractType method, of class LegalEntityAccessor.
     */
    @Test
    public void testStoreCreditContractType() {
        System.out.println("storeCreditContractType");
        CreditContractType creditContractType = new CreditContractType();
        creditContractType.setCreditContractTypeName("unit test");
        CreditContractType result = LegalEntityAccessor.storeCreditContractType(creditContractType);
        assertNotNull("failed to store creditContractType", result.getCreditContractTypeId());
        HibernateUtil.deleteObject(creditContractType);
    }

    /**
     * Test of getAnyLegalEntityByRole method, of class LegalEntityAccessor.
     */
    @Test
    public void testGetAnyLegalEntityByRole() {
        System.out.println("getAnyLegalEntityByRole");
        LegalEntity result = LegalEntityAccessor.getAnyLegalEntityByRole(LegalEntityRole.LegalEntityRoleEnum.LEGAL_ENTITY_ROLE.name);
        assertNotNull("filed to load entitiy", result);
    }

}
