/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon
 * - 94300 VINCENNES
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Affero General Public License as published by the Free
 * Software Foundation, either version 3.0 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * jComboBoxIssuer but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package org.gaia.dao.audit;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Entity;
import org.apache.log4j.Logger;
import org.gaia.domain.trades.Trade;
import org.gaia.domain.utils.HibernateUtil;
import org.gaia.domain.utils.IntrospectUtil;
import org.gaia.domain.utils.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.entities.mapper.relation.lazy.proxy.ListProxy;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.proxy.HibernateProxy;
import org.hibernate.proxy.LazyInitializer;

/**
 *
 * @author Jawhar Kamoun
 */
public class AuditAccessor {

    private static final Logger logger = Logger.getLogger(AuditAccessor.class.getName());
    public static final String GET_PREVIOUS_VERSION = "getPreviousVersion";
    public static final int MAXLEVEL = 5;

    public static Trade getPreviousVersion(Trade entity) {
        Session session = HibernateUtil.getSession();
        Trade trade = null;
        try {
            List<Trade> oldVersionList = getOldVersionList(entity);
            if (!oldVersionList.isEmpty()) {
                trade = oldVersionList.get(0);
            }
            if (trade != null) {
                unProxyObject(trade);
            }

        } catch (IllegalAccessException | IllegalArgumentException |
                InvocationTargetException | HibernateException |
                ClassNotFoundException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
        return trade;
    }
    public static final String GET_OLD_VERSION_LIST = "getOldVersionList";

    public static List<Trade> getOldVersionList(Trade trade) {
        Session session = HibernateUtil.getSession();
        List<Trade> result = new ArrayList<>();
        try {
            if (trade != null) {
                result.add(trade);
                AuditReader reader = AuditReaderFactory.get(session);
                List<Object[]> prior_revision = (List<Object[]>) reader.createQuery()
                        .forRevisionsOfEntity(trade.getClass(), false, true)
                        .add(AuditEntity.property("tradeVersion").lt(trade.getTradeVersion()))
                        .add(AuditEntity.id().eq(trade.getId()))
                        .addOrder(AuditEntity.revisionNumber().desc())
                        .getResultList();
                for (Object[] objects : prior_revision) {
                    Trade version = (Trade) objects[0];
                    unProxyObject(version);
                    result.add(version);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException |
                HibernateException | IllegalArgumentException |
                InvocationTargetException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
        return result;
    }

    public static void unProxyObject(Object object) throws
            ClassNotFoundException, IllegalAccessException, HibernateException,
            IllegalArgumentException, InvocationTargetException {
        Method[] methods = object.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().startsWith("get") && method.getGenericParameterTypes().length == 0) {
                Class returnType = method.getReturnType();
                Object attribute = method.invoke(object, (Object[]) null);
                if (attribute instanceof HibernateProxy) {
                    HibernateProxy proxy = (HibernateProxy) attribute;
                    attribute = loadLazyObject(proxy);
                    Object newValue = returnType.cast(attribute);
                    IntrospectUtil.invokeSetter(object.getClass(), object, method.getName().substring(3), newValue);
                } else if (attribute instanceof ListProxy) {
                    ListProxy listProxy = (ListProxy) attribute;
                    List<Object> newList = new ArrayList<>();
                    for (Object object1 : listProxy) {
                        newList.add(object1);
                    }

                    IntrospectUtil.invokeSetter(object.getClass(), object,
                            method.getName().substring(3), newList);
                }
            }
        }
    }

    /**
     *
     * @param proxy
     * @return
     * @throws ClassNotFoundException
     */
    public static Object loadLazyObject(HibernateProxy proxy) throws ClassNotFoundException {
        LazyInitializer init = proxy.getHibernateLazyInitializer();
        logger.debug(init.getEntityName() + StringUtils.SPACE + init.getIdentifier());
        return HibernateUtil.getObject(Class.forName(init.getEntityName()), init.getIdentifier());
    }

    public static void unLazyObjectRecursively(Object object) {
        try {
            if (object != null) {
                unLazyObjectRecursively(object, MAXLEVEL);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static void unLazyObjectRecursively(Object object, int level) throws
            ClassNotFoundException, IllegalAccessException, HibernateException,
            IllegalArgumentException, InvocationTargetException {
        Class clazz = object.getClass();
        Annotation ano = clazz.getAnnotation(Entity.class);
//        if (ano!=null){
            for (Method method : clazz.getMethods()) {
                if (method.getName().startsWith("get") && method.getGenericParameterTypes().length == 0) {
                    try {
                        String field = method.getName().substring(3);
                        ano=null;
                        for (Field field_ : clazz.getDeclaredFields()) {
                            if (field_.getName().equalsIgnoreCase(field)){
                                ano = field_.getAnnotation(XStreamOmitField.class);
                            }
                        }
                        Object attribute = method.invoke(object, (Object[]) null);
                        if (ano != null){
                            IntrospectUtil.invokeSetter(clazz, object, field, null);
                        } else  if (attribute instanceof HibernateProxy
                                || attribute instanceof ListProxy) {

                            if (attribute instanceof HibernateProxy){
                                attribute=loadLazyObject((HibernateProxy)attribute);
                            }
                            IntrospectUtil.invokeSetter(clazz, object, field, attribute);
                        } else if (attribute != null
                                && attribute.getClass().getName().startsWith("org.gaia")
                                && !attribute.equals(object) && level > 0 ) {
                            unLazyObjectRecursively(attribute, level - 1);
                        } else if (attribute != null && attribute instanceof Collection) {
                            if (level > 0) {
                                Collection collec = (Collection) attribute;
                                try {
                                    for (Object obj : collec) {
                                        unLazyObjectRecursively(obj, level - 1);
                                    }
                                } catch (Exception e) {
                                    IntrospectUtil.invokeSetter(clazz, object, field, null);
                                }
                            } else {
//                                IntrospectUtil.invokeSetter(clazz, object, field, null);
                            }
                        }
                    } catch (Exception e) {
                        logger.error("Error while calling method " + method.getName() + " on " + object.toString());
                        logger.error(StringUtils.formatErrorMessage(e));
                    }
                }
            }
//        }
    }
}
