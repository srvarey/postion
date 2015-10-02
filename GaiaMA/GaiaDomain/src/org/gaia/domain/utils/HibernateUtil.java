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

import java.io.Serializable;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author Benjamin Frerejean
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static final ServiceRegistry serviceRegistry;
    private static final Logger logger = Logger.getLogger(HibernateUtil.class.getName());
    public static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    static {
        try {
            if (GlobalInfo.isRunningAsServer()) {
                 /**
                  * Create the SessionFactory from standard config file (hibernate.cfg.xml).
                  */
                logger.info("CONNECTING TO DATABASE " + GlobalInfo.getInstance().getServerMasterDBHost());
                Configuration configuration = new Configuration();
                configuration.configure();
                String configuredHost = GlobalInfo.getInstance().getServerMasterDBHost();
                if (GlobalInfo.getInstance().isJnlpStart()) {
                    String path = System.getProperty("java.io.tmpdir");
                    String regex = "/";
                    String replacement = "\\";
                    configuredHost = "jdbc:h2:" + path.replaceAll(regex, replacement) + "gaia;MVCC=true";
                }
                configuration.setProperty("hibernate.connection.url", configuredHost);
                configuration.setProperty("hibernate.connection.username", GlobalInfo.getInstance().getServerMasterDBUser());
                configuration.setProperty("hibernate.connection.password",GlobalInfo.getInstance().getServerMasterDBPswd());
                configuration.setProperty("hibernate.cache.provider_configuration_file_resource_path", GlobalInfo.getInstance().getEhcachFile());
                serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } else {
                logger.fatal("TRYING TO CONNECT TO DATABASE FROM GUI! (" + GlobalInfo.getInstance().getServerMasterDBHost() + ")");
                sessionFactory = null;
                serviceRegistry = null;
            }

        } catch (Throwable ex) {
            /**
             * Log the exception.
             */
            logger.fatal("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static final String INIT_SESSION = "initSession";

    public static void initSession() {
        try {
            sessionFactory.openSession();
        } catch (Exception e) {
            logger.fatal("Pb opening connection." + e.getMessage());
        }
    }

    public static Session getSession() throws HibernateException {
        Session sess = null;
        try {
            sess = sessionFactory.getCurrentSession();
        } catch (org.hibernate.HibernateException he) {
            try {
                sess = sessionFactory.openSession();
            } catch (Exception e) {
                logger.fatal("Pb opening connection." + e.getMessage());
            }
        }
        return sess;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * Store an object
     *
     * @param object
     * @return
     */
    public static Object storeAndReturnObject(Object object) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object id = IntrospectUtil.getGeneratedIdFieldValue(object);
            cleanStrings(object);
            if (id == null) {
                id = session.save(object);
                IntrospectUtil.setIdFieldValue(object, id);
            } else {
                session.update(object);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (IntrospectUtil.getIdFieldValue(object) != null) {
                String id = IntrospectUtil.getIdFieldValue(object).toString();
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id));
            } else {
                logger.fatal(StringUtils.formatErrorMessage(e));
                logger.fatal("Error storing " + object.toString());
            }
            object = null;
        } finally {
            session.close();
        }
        return object;
    }

    /**
     * Store an object
     *
     * @param object
     */
    public static void storeObject(Serializable object) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            cleanStrings(object);
            storeObject(object, session);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            if (IntrospectUtil.getIdFieldValue(object) != null) {
                String id = IntrospectUtil.getIdFieldValue(object).toString();
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id));
            } else {
                logger.fatal("Error storing " + object.toString());
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
        } finally {
            session.close();
        }
    }

    /**
     * Store an Object
     *
     * @param object
     * @param session
     * @return
     */
    public static boolean storeObject(Serializable object, Session session) {

        boolean result = true;
        try {
            cleanStrings(object);
            session.saveOrUpdate(object);
        } catch (HibernateException e) {
            if (IntrospectUtil.getIdFieldValue(object) != null) {
                String id = IntrospectUtil.getIdFieldValue(object).toString();
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id));
            } else {
                logger.fatal("Error storing " + object.toString());
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
            result = false;
        }
        return result;
    }

    /**
     * Store an object list
     * @param objectList
     * @return
     */
    public static boolean storeObjects(List<Serializable> objectList) {
        boolean result = true;
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            for (Serializable serializable : objectList) {
                cleanStrings(serializable);
                session.saveOrUpdate(serializable);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.fatal(StringUtils.formatErrorMessage(e));
            result = false;
        } finally {
            session.close();
        }
        return result;
    }

    public static Serializable saveObject(Serializable object) {
        Serializable ret = null;
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            cleanStrings(object);
            ret = session.save(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            Object id = IntrospectUtil.getIdFieldValue(object);
            if (id!=null){
                logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id.toString()));
            } else {
                logger.fatal(StringUtils.formatErrorMessage(e));
            }
        } finally {
            session.close();
        }
        return ret;
    }

    public static void updateObject(Serializable object) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            cleanStrings(object);
            session.update(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String id = IntrospectUtil.getIdFieldValue(object).toString();
            logger.fatal(StringUtils.formatErrorMessage(e).replace("?", id));
        } finally {
            session.close();
        }
    }

    /**
     * retrieve the product
     * @param name
     * @param orderBy
     * @return
     */
    public static List<Serializable> getObjects(String name, String orderBy) {
        List<Serializable> resultList = null;
        Session session = HibernateUtil.getSession();
        try {
            Query queryObject;
            if (orderBy == null || orderBy.isEmpty()) {
                queryObject = session.createQuery("from " + name).setCacheable(true);
            } else {
                queryObject = session.createQuery("from " + name + " order by " + orderBy).setCacheable(false);
            }
            resultList = queryObject.list();
        } catch (HibernateException e) {
            logger.error(StringUtils.formatErrorMessage(e));
        } finally {
            session.close();
        }
        return resultList;
    }

    public static Serializable getObject(Class cl, Serializable param) {
        Serializable returnValue = null;
        if (param != null) {
            Session session = HibernateUtil.getSession();
            try {
                returnValue = (Serializable) session.get(cl, param);
            } catch (Exception e) {

                logger.error(StringUtils.formatErrorMessage(e));
            } finally {
                session.close();
            }
        }
        return returnValue;
    }

    public static void cleanStrings(Object object){
        if (object!=null){
            Class clazz = object.getClass();
            try {
                for (Method method : clazz.getMethods()){
                    if (method.getName().startsWith("get") && method.getReturnType().equals(String.class) && method.getParameterTypes().length==0){
                        Method setter= clazz.getMethod("set"+method.getName().substring(3), String.class);
                        Object obj=method.invoke(object, new Object[]{});
                        if (obj!=null){
                            String val=obj.toString();
                            setter.invoke(object, val.replace(StringUtils.QUOTE, "\'"));
                        }
                    }
                }
            } catch (Exception e) {
                logger.error(StringUtils.formatErrorMessage(e));
            }
        }
    }

    public static boolean isValidated(String query){
        int occur= StringUtils.countString(query, StringUtils.QUOTE);
        return !(occur % 2>0);
    }

    public static Serializable getObjectWithQuery(String query) {
        Serializable returnValue = null;
        if (query != null && isValidated(query)) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createQuery(query).setCacheable(false);
                List<Serializable> resultList = queryObject.list();
                if (resultList.size() > 0) {
                    returnValue = resultList.get(0);
                }
            } catch (HibernateException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return returnValue;
    }

    public static Serializable getObjectWithQueryWithCache(String query) {
        Serializable returnValue = null;
        if (query != null) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createQuery(query).setCacheable(true);

                List<Serializable> resultList = queryObject.list();
                if (resultList.size() > 0) {
                    returnValue = resultList.get(0);
                }
            } catch (HibernateException e) {

                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return returnValue;
    }

    /**
     * execution of a query
     * @param query
     * @return
     */
    public static List getObjectsWithQuery(String query) {
        List<Serializable> resultList = new ArrayList();
        if (query != null) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createQuery(query).setCacheable(false);
                resultList = queryObject.list();
            } catch (HibernateException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return resultList;
    }

    /**
     * execution of a query without cache
     * @param query
     * @return
     */
    public static List getObjectsWithQueryWithCache(String query) {
        List<Serializable> resultList = new ArrayList();
        if (query != null) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createQuery(query).setCacheable(true);
                resultList = queryObject.list();
            } catch (HibernateException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return resultList;
    }

    public static List getListWithSQLQuery(String SQLQuery) {

        List<Serializable> returnList = new ArrayList();
        if (SQLQuery != null) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createSQLQuery(SQLQuery);
                returnList = queryObject.list();
            } catch (HibernateException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+SQLQuery);
            } finally {
                session.close();
            }
        }
        return returnList;
    }

    public static List<Serializable> getEntitiesWithSQLQuery(String SQLQuery, Class entity) {
        List<Serializable> returnList = null;
        if (SQLQuery != null) {
            Session session = HibernateUtil.getSession();
            try {
                Query queryObject = session.createSQLQuery(SQLQuery).addEntity(entity).setCacheable(true);
                returnList = queryObject.list();
            } catch (HibernateException e) {
                logger.error(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+SQLQuery);
            } finally {
                session.close();
            }
        }
        return returnList;
    }

    public static ArrayList<String> getStringListWithQuery(String query) {
        ArrayList<String> ret = new ArrayList();
        List results = getObjectsWithQuery(query);
        for (Object result : results) {
            if (result != null) {
                ret.add(result.toString());
            }
        }
        return ret;
    }

    /**
     * retrieve list
     * @param SQLQuery
     * @return
     */
    public static ArrayList<String> getStringListWithSQLQuery(String SQLQuery) {
        ArrayList<String> ret = new ArrayList();
        List results = getListWithSQLQuery(SQLQuery);
        for (Object result : results) {
            if (result != null) {
                ret.add(result.toString());
            }
        }
        return ret;
    }

    public static int executeQuery(String query) {
        int ret = 0;
        if (query != null) {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query queryObject = session.createQuery(query).setCacheable(true);
                ret = queryObject.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.fatal(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return ret;
    }

    public static int executeSQLQuery(String query) {
        int ret = 0;
        if (query != null) {
            Session session = HibernateUtil.getSession();
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Query queryObject = session.createSQLQuery(query);
                ret = queryObject.executeUpdate();
                transaction.commit();
            } catch (HibernateException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                logger.fatal(StringUtils.formatErrorMessage(e));
                logger.error("QUERY="+query);
            } finally {
                session.close();
            }
        }
        return ret;
    }

    public static Serializable getFieldWithSQLQuery(String SQLQuery) {
        List<Serializable> result = getListWithSQLQuery(SQLQuery);
        if (result != null && result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }
    public static String DELETE_OBJECT = "deleteObject";

    public static void deleteObject(Serializable object) {
        if (object == null) {
            return;
        }
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            String id = IntrospectUtil.getIdFieldValue(object).toString();
            logger.error(StringUtils.formatErrorMessage(e).replace("?", id));
        } finally {
            session.close();
        }
    }


}
