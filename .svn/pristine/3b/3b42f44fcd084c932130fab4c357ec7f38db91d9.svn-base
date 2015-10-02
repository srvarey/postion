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

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentCollectionConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedMapConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernatePersistentSortedSetConverter;
import com.thoughtworks.xstream.hibernate.converter.HibernateProxyConverter;
import com.thoughtworks.xstream.hibernate.mapper.HibernateMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;
import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;
import org.gaia.domain.utils.StringUtils;

/**
 *
 * @author Benjamin Frerejean
 */
public class XMLExporter {

    private static final Logger logger = Logger.getLogger(XMLExporter.class);

    public static void export(Object object, Component comp) {
        XStream xstream;
        xstream = new XStream() {
            @Override
            protected MapperWrapper wrapMapper(final MapperWrapper next) {
                return new HibernateMapper(next);
            }
        };
        xstream.registerConverter(new HibernateProxyConverter());
        xstream.registerConverter(new HibernatePersistentCollectionConverter(xstream.getMapper()));
        xstream.registerConverter(new HibernatePersistentMapConverter(xstream.getMapper()));
        xstream.registerConverter(new HibernatePersistentSortedMapConverter(xstream.getMapper()));
        xstream.registerConverter(new HibernatePersistentSortedSetConverter(xstream.getMapper()));

        xstream.autodetectAnnotations(true);

        if (object != null) {
            /**
             * export to xml file
             */
            try {

                String xml = xstream.toXML(object);
                FileNameExtensionFilter filter = new FileNameExtensionFilter("xml files (*.xml)", "xml");
                JFileChooser filechooser = new JFileChooser();
                filechooser.addChoosableFileFilter(filter);
                filechooser.setFileFilter(filter);
                int returnVal = filechooser.showOpenDialog(comp);
                String filePath;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = filechooser.getSelectedFile();
                    filePath = file.getPath();

                    if (!filePath.toLowerCase().endsWith(".xml")) {
                        file = new File(filePath + ".xml");
                        try (BufferedWriter out = new BufferedWriter(new FileWriter(file))) {
                            out.write(xml);
                        }
                    }
                }

            } catch (IOException e) {
                logger.error("Error " + StringUtils.formatErrorMessage(e));
            }
        }
    }
}
