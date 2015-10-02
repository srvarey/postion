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

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.log4j.Logger;

/**
 *
 * @author Benjamin Frerejean
 */
public class IntrospectUtil {

    private static final Logger logger = Logger.getLogger(IntrospectUtil.class);

    public static String getFieldName(String getter) {
        String field = getter;
        field = field.substring(field.lastIndexOf("/") + 4);
        field = field.substring(0, 1).toLowerCase() + field.substring(1);
        return field;
    }

    public static ArrayList<Method> getGettersNoObject(String classname) {
        ArrayList<Method> methods = new ArrayList();
        for (Method method : methods) {
            if (!method.getReturnType().equals(String.class)
                    && !method.getReturnType().equals(Integer.class)
                    && !method.getReturnType().equals(Double.class)
                    && !method.getReturnType().equals(Boolean.class)
                    && !method.getReturnType().equals(Short.class)
                    && !method.getReturnType().equals(Date.class)
                    && !method.getReturnType().equals(short.class)
                    && !method.getReturnType().equals(BigDecimal.class)) {
                methods.remove(method);
            }
        }
        return methods;
    }

    public static void invokeSetter(Class clazz, Object obj, String field, Object data) {
        try {
            Method mget = clazz.getMethod("get" + field, (Class[]) null);
            Class cp = mget.getReturnType();
            Method mset = clazz.getMethod("set" + field, cp);
            IntrospectUtil.invokeSetter(obj, mset, data);
        } catch (SecurityException e) {
            logger.error("error  in a invokeSetter " + StringUtils.formatErrorMessage(e));
        } catch (NoSuchMethodException e) {
        }
    }

    public static void invokeSetter(Object obj, Method setter, Object data) {
        DateFormat dfdate = new SimpleDateFormat("dd/MM/yyyy");
        Class clazz = obj.getClass();
        String gettername = "get" + setter.getName().substring(3);
        try {
            Method mget = clazz.getMethod(gettername, (Class[]) null);
            Class returnType = mget.getReturnType();
            if (data != null && !data.equals(StringUtils.EMPTY_STRING)) {
                if (returnType.getName().equals(Integer.class.getName())) {
                    setter.invoke(obj, Integer.parseInt(data.toString()));
                } else if (returnType.getName().equals(Date.class.getName())) {
                    setter.invoke(obj, dfdate.parse(data.toString()));
                } else if (returnType.getName().equals(Double.class.getName())) {
                    setter.invoke(obj, Double.parseDouble(data.toString()));
                } else if (returnType.getName().equals(Short.class.getName())) {
                    setter.invoke(obj, Short.parseShort(data.toString()));
                } else if (returnType.getName().equals(Boolean.class.getName())) {
                    setter.invoke(obj, Boolean.parseBoolean(data.toString()));
                } else if (returnType.getName().equals(BigDecimal.class.getName())) {
                    setter.invoke(obj, BigDecimal.valueOf(Double.parseDouble(data.toString())));
                } else if (returnType.getName().equals("short")) {
                    setter.invoke(obj, Short.parseShort(data.toString()));
                } else {// string
                    setter.invoke(obj, data);
                }
            } else if (data == null) {
                setter.invoke(obj, data);
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ParseException e) {
            logger.error("error  in a invokeSetter " + StringUtils.formatErrorMessage(e));
        }
    }

    public static ArrayList<Method> getGetters(String classname) {
        ArrayList<Method> ret = new ArrayList();
        try {
            Class clazz = Class.forName(classname);
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (method.getName().startsWith("get")) {

                    if (!method.getName().equals("getClass") && !method.getName().substring(method.getName().length() - 2).equals("PK")
                            && !method.getReturnType().toString().substring(method.getReturnType().toString().lastIndexOf(StringUtils.DOT) + 1).equals("Set")
                            && !method.getReturnType().toString().substring(method.getReturnType().toString().lastIndexOf(StringUtils.DOT) + 1).equals("List")
                            && !method.getReturnType().toString().substring(method.getReturnType().toString().lastIndexOf(StringUtils.DOT) + 1).equals("Collection")
                            && method.getGenericParameterTypes().length == 0) {
                        ret.add(method);
                    }

                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            logger.error("error   " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static Method getIdGetter(String className) {
        Method ret = null;
        try {
            if (className != null) {
                Class clazz = Class.forName(className);
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.getAnnotation(Id.class) != null) {
                        Method[] methods = clazz.getMethods();
                        for (Method merthod : methods) {
                            if (merthod.getName().equalsIgnoreCase("get" + field.getName())) {
                                return merthod;
                            }
                        }
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            logger.error("error  in a getIdGetter " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static Method getGeneratedIdGetter(String className) {
        Method ret = null;
        try {
            if (className != null) {
                Class clazz = Class.forName(className);
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.getAnnotation(Id.class) != null && field.getAnnotation(GeneratedValue.class) != null) {

                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            if (method.getName().equalsIgnoreCase("get" + field.getName()) && method.getGenericParameterTypes().length == 0) {
                                return method;
                            }
                        }
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            logger.error("error  in a getIdGetter " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static Method getIdSetter(String className) {
        Method ret = null;
        try {
            if (className != null) {
                Class clazz = Class.forName(className);
                Field[] fields = clazz.getDeclaredFields();

                for (Field field : fields) {
                    if (field.getAnnotation(Id.class) != null) {
                        Method[] methods = clazz.getMethods();
                        for (Method method : methods) {
                            if (method.getName().equalsIgnoreCase("set" + field.getName())) {
                                return method;
                            }
                        }
                    }
                }
            }
        } catch (SecurityException | ClassNotFoundException e) {
            logger.error("error  in a getIdGetter " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static Object getIdFieldValue(Object object) {
        Object ret = null;
        try {
            String className = object.getClass().getName();
            Method method = getIdGetter(className);
            if (method != null) {
                ret = method.invoke(object, new Object[0]);
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static Object getGeneratedIdFieldValue(Object object) {
        Object ret = null;
        try {
            if (object != null) {
                String className = object.getClass().getName();
                Method method = getGeneratedIdGetter(className);
                if (method != null) {
                    ret = method.invoke(object, new Object[0]);
                }
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static void setIdFieldValue(Object object, Object id) {
        try {
            String className = object.getClass().getName();
            Method method = getIdSetter(className);
            if (method != null) {
                method.invoke(object, new Object[]{id});
            }
        } catch (Exception e) {
            logger.error(StringUtils.formatErrorMessage(e));
        }
    }

    public static Field getIdField(String classname) {
        Field ret = null;
        try {
            Class clazz = Class.forName(classname);
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                Annotation ano = field.getAnnotation(Id.class);
                if (ano != null) {
                    return field;
                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            logger.error("error  in a getIdField " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public static String getIdDBFieldName(String classname) {
        Field field = getIdField(classname);
        Annotation ano = field.getAnnotation(Column.class);
        Column col = (Column) ano;
        return col.name();
    }

    public static String getDBTableName(Class clazz) {
        Annotation ano = clazz.getAnnotation(Table.class);
        Table table = (Table) ano;
        return table.name();
    }

    public static String getDBTableName(String className) {
        try {
            Class clazz = Class.forName(className);
            return getDBTableName(clazz);
        } catch (Exception e) {
            logger.error("error  in a getDBTableName " + StringUtils.formatErrorMessage(e));
        }
        return null;
    }

    public static String getDBFieldName(String classname, String fieldName) {
        String ret = null;
        try {
            Class clazz = Class.forName(classname);
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (field.getName().equals(fieldName)) {
                    Annotation ano = field.getAnnotation(Column.class);
                    if (ano != null) {
                        return ((Column) ano).name();
                    } else {
                        ano = field.getAnnotation(JoinColumn.class);
                        if (ano != null) {
                            return ((JoinColumn) ano).name();
                        } else {
                            ano = field.getAnnotation(OneToOne.class);
                            if (ano != null) {
                                String mappedBy = ((OneToOne) ano).mappedBy();
                                return getDBFieldName(field.getType().getName(), mappedBy);
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SecurityException e) {
            logger.error("error  in a getDBFieldName " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    /**
     * This method allows to list all the classes of one package given
     *
     * @param pckgname The name of packages to list
     * @return The list of the classes
     */
    public static List<Class> getClasses(String pckgname) throws ClassNotFoundException, IOException {
        /**
         * Creation of the list which will have returned
         */
        ArrayList<Class> classes = new ArrayList();

        /**
         * One got back all the entries of the CLASSPATH
         */
        String[] entries = System.getProperty("java.class.path")
                .split(System.getProperty("path.separator"));

        /**
         * For all these entries we verifie if they contain
         *
         * A directory or a jar
         */
        for (int i = 0; i < entries.length; i++) {

            if (entries[i].endsWith(".jar")) {
                classes.addAll(loadClassesFromJar(entries[i], pckgname));
            } else {
                classes.addAll(loadClassesFromDirectory(entries[i], pckgname));
            }

        }

        return classes;
    }

    /**
     * This method turns(returns) the list of the present classes to a directory
     * Of the classpath and in one package given
     *
     * @param directory The directory where to look for the classes
     * @param packageName The name of packages
     * @return The list of the classes
     */
    public static Collection<Class> loadClassesFromDirectory(String directory, String packageName) throws ClassNotFoundException {
        ArrayList<Class> classes = new ArrayList();

        /**
         * We generate the absolute path of packages
         */
        StringBuilder sb = new StringBuilder(directory);
        String[] repsPkg = packageName.split("\\.");
        for (int i = 0; i < repsPkg.length; i++) {
            sb.append(System.getProperty("file.separator"));
            sb.append(repsPkg[i]);
        }
        File rep = new File(sb.toString());

        /**
         * If the path exists, and if it is a file, then, we list him
         */
        if (rep.exists() && rep.isDirectory()) {
            /**
             * We filter the entries of the directory
             */
            FilenameFilter filter = new DotClassFilter();
            File[] liste = rep.listFiles(filter);

            /**
             * For every present class in packages it, we add it to the list
             */
            for (int i = 0; i < liste.length; i++) {
                classes.add(Class.forName(packageName + StringUtils.DOT + liste[i].getName().split("\\.")[0]));
            }
        }

        return classes;
    }

    /**
     * This method turns the list of the present classes to a jar of Classpath
     * and in one package given
     *
     *
     * @param repertoire The jar where to find classes
     * @param packageNname the package name
     * @return flasses list
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Collection<Class> loadClassesFromJar(String jar, String packageNname) throws IOException, ClassNotFoundException {
        ArrayList<Class> classes = new ArrayList();

        JarFile jfile = new JarFile(jar);
        String pkgpath = packageNname.replace(StringUtils.DOT, "/");

        /**
         * For every entry of Jar
         */
        for (Enumeration<JarEntry> entries = jfile.entries(); entries.hasMoreElements();) {
            JarEntry element = entries.nextElement();

            /**
             * If the name of the entry begins with the path of packages and
             * finishes by .class
             */
            if (element.getName().startsWith(pkgpath)
                    && element.getName().endsWith(".class")) {

                String nomFichier = element.getName().substring(packageNname.length() + 1);

                classes.add(Class.forName(packageNname + StringUtils.DOT + nomFichier.split("\\.")[0]));

            }

        }

        return classes;
    }

    /**
     * This class allows to filter the files of a directory. He accepts That
     * .class files.
     */
    private static class DotClassFilter implements FilenameFilter {

        @Override
        public boolean accept(File arg0, String arg1) {
            return arg1.endsWith(".class");
        }
    }

    public static Class getClassNameFromColumn(String name, String getter) {
        Class clazz = null;
        try {
            if (getter.indexOf("/") <= 0) {
                return Class.forName(name);
            } else {
                getter = getter.substring(0, getter.indexOf("/"));
            }
            ArrayList<Method> methodList = getGetters(name);

            for (Method method : methodList) {
                if (method.getName().equalsIgnoreCase(getter)) {
                    clazz = method.getReturnType();
                }
            }
            if (getter.indexOf("/") > 0 && clazz != null) {
                clazz = getClassNameFromColumn(clazz.getName(), getter);
            }
        } catch (ClassNotFoundException ex) {
            logger.error(ex);
        }
        return clazz;

    }

    public static List<Field> getFieldsByAnnotation(Class clazz, Class annotation) {
        List<Field> ret = new ArrayList<>();
        try {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                Annotation ano = field.getAnnotation(annotation);
                if (ano != null) {
                    ret.add(field);
                }
            }
        } catch (SecurityException e) {
            logger.error("error  in a getFieldsByAnnotation " + StringUtils.formatErrorMessage(e));
        }
        return ret;
    }

    public <S> Iterable<S> load(Class<S> ifc) throws Exception {
        ClassLoader ldr = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> e = ldr.getResources("META-INF/services/" + ifc.getName());
        Collection<S> services = new ArrayList();
        while (e.hasMoreElements()) {
            URL url = e.nextElement();
            try (InputStream is = url.openStream()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    int comment = line.indexOf('#');
                    if (comment >= 0) {
                        line = line.substring(0, comment);
                    }
                    String name = line.trim();
                    if (name.length() == 0) {
                        continue;
                    }
                    Class<?> clz = Class.forName(name, true, ldr);
                    Class<? extends S> impl = clz.asSubclass(ifc);
                    Constructor<? extends S> ctor = impl.getConstructor();
                    S svc = ctor.newInstance();
                    services.add(svc);
                }
            }
        }
        return services;
    }
}
