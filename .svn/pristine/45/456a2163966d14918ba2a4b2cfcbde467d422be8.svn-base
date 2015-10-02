/**
 * Copyright (C) 2013 Gaia Transparence Gaia Transparence, 1 allée Paul Barillon - 94300 VINCENNES strategyComboBox This program is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3.0 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
/**
 *
 * @author Benjamin Frerejean
 */
package org.gaia.gui.reports;

import java.awt.Dimension;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.text.Text;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import org.gaia.dao.reports.PositionTree;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.reports//ReportBarChart//EN", autostore = false)
@TopComponent.Description(preferredID = "ReportBarChartTopComponent", persistenceType = TopComponent.PERSISTENCE_NEVER)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.trades.ReportBarChartTopComponent")
@TopComponent.OpenActionRegistration(displayName = "#CTL_ReportBarChartAction")
@Messages({
    "CTL_ReportBarChartAction=Bar Chart",
    "CTL_ReportBarChartTopComponent=Bar Chart",
    "HINT_ReportBarChartTopComponent=Bar Chart"
})
public class ReportBarChartTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(ReportBarChartTopComponent.class);

    /**
     * javafx fields
     */
    private static final int PANEL_WIDTH_INT = 800;
    private static final int PANEL_HEIGHT_INT = 600;
    private JFXPanel chartFxPanel;
    private BarChart chart;
    private PositionTree.PositionNode root;
    private ArrayList<String> headers;

    /**
     *
     */
    public ReportBarChartTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_ReportBarChartTopComponent());
        setToolTipText(Bundle.HINT_ReportBarChartTopComponent());
    }

    /**
     * @param root
     * @param headers
     * @param columnNames
     */
    public void drawChart(final PositionTree.PositionNode root, final ArrayList<String> headers, final List<String> columnNames) {
        this.root = root;
        this.headers = headers;
        if (chartFxPanel != null) {
            jPanel1.removeAll();
        }
        chartFxPanel = null;
        /**
         * create javafx panel for charts
         */
        JFrame frame = new JFrame("FX");
        chartFxPanel = new JFXPanel();
        frame.add(chartFxPanel);
        chartFxPanel.setVisible(true);

        Platform.setImplicitExit(false);

        /**
         * create JavaFX scene
         */
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                createSceneForBarChart(columnNames);
            }
        });
        chartFxPanel.setPreferredSize(new Dimension(PANEL_WIDTH_INT, PANEL_HEIGHT_INT));
        chartFxPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));

        chartFxPanel.setEnabled(true);
        BoxLayout myLayout = new BoxLayout(jPanel1, BoxLayout.PAGE_AXIS);
        jPanel1.setLayout(myLayout);
        /**
         * create javafx panel for browser
         */
        jPanel1.add("Graph", chartFxPanel);
    }

    public void createSceneForBarChart(final List<String> columnNames) {
        Timeline Updater = null;
        try {
            final CategoryAxis xAxis = new CategoryAxis();
            final NumberAxis yAxis = new NumberAxis();
            chart = new BarChart(xAxis, yAxis);
            chart.setTitle("Report");
            yAxis.setLabel("Values");
            HashMap<String, XYChart.Series> series = new HashMap();
            if (root != null && root.getChildCount() > 0 && columnNames != null && headers != null) {
                for (final String column : columnNames) {
                    int index = headers.indexOf(column);
                    if (index == -1) {
                        JOptionPane.showMessageDialog(this, "Missing defined columns");
                        return;
                    }
                    if (index >= 0) {
                        final HashMap<String, BigDecimal> values = new HashMap();                        if (!root.isLeaf()) {
                            for (int i = 0; i < root.getChildCount(); i++) {
                                PositionTree.PositionNode child = (PositionTree.PositionNode) root.getChildAt(i);
                                Object[] data = child.getData();

                                if (data != null && data[index] != null && data[index] instanceof BigDecimal) {
                                    BigDecimal value = (BigDecimal) data[index];
                                    if (value.doubleValue() != 0) {
                                        String tag = null;
                                        int j = 0;
                                        if (data[j] == null || data[j].toString().equalsIgnoreCase(StringUtils.EMPTY_STRING)) {
                                            data[j] = "Other";
                                        }
                                        while (tag == null && j < data.length) {
                                            if (data[j] != null && data[j] instanceof String && !data[j].toString().equalsIgnoreCase(StringUtils.EMPTY_STRING)) {
                                                tag = data[j].toString();
                                            }
                                            j++;
                                        }
                                        if (tag != null) {
                                            values.put(tag, value);
                                        }
                                    }
                                }
                            }
                        }
                        final List<String> keys = new ArrayList(values.keySet());
                        Collections.sort(keys, new HashMapComparator(values));
                        final XYChart.Series serie = new XYChart.Series();
                        serie.setName(column);
                        series.put(column, serie);
                        for (String key : keys) {
                            final XYChart.Data<String, BigDecimal> item = new XYChart.Data(key, values.get(key));
                            item.nodeProperty().addListener(new ChangeListener<Node>() {
                                @Override
                                public void changed(ObservableValue<? extends Node> ov, Node oldNode, final Node bar) {
                                    if (bar != null) {
                                        setNodeStyle(item, column, columnNames);
                                        displayLabelForData(item);
                                    }
                                }
                            });
                            serie.getData().add(item);
                        }
                        if (!values.isEmpty()) {
                            chart.getData().add(serie);
                        }
                    }
                }
            }
            if (chartFxPanel != null && chart != null) {
                chartFxPanel.setScene(new Scene(chart));

                for (Node n : chart.lookupAll(".bar-legend-symbol.default-color0")) {
                    n.setStyle("-fx-background-color: " + "navy" + ";");
                }
                for (Node n : chart.lookupAll(".bar-legend-symbol.default-color1")) {
                    n.setStyle("-fx-background-color: " + "tomato" + ";");
                }

            }

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }

    /*
     *Change color of the bar for a given column bench expo or fund expo%
     */
    private void setNodeStyle(XYChart.Data<String, BigDecimal> data, String column, List<String> columnNames) {
        Node bar = data.getNode();
        if (columnNames.indexOf(column) == 0) {
            bar.setStyle("-fx-bar-fill: navy;");
        } else {
            bar.setStyle("-fx-bar-fill: tomato;");
        }
    }

    /*
     *places a text label with a bar's value above a bar node for a given XYChart.Data
     */
    private void displayLabelForData(XYChart.Data<String, BigDecimal> data) {
        final Node bar = data.getNode();
        double value = floor(data.getYValue().doubleValue(), 2);
        final Text dataText = new Text(value + "");
        bar.parentProperty().addListener(new ChangeListener<Parent>() {
            @Override
            public void changed(ObservableValue<? extends Parent> ov, Parent oldParent, Parent parent) {
                Group parentGroup = (Group) parent;
                parentGroup.getChildren().add(dataText);
            }
        });
        bar.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
            @Override
            public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                dataText.setLayoutX(
                        Math.round(
                                bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                        )
                );
                dataText.setLayoutY(
                        Math.round(
                                bounds.getMinY() - dataText.prefHeight(-1) * 0.5
                        )
                );
            }
        });

    }

    public void getValues(PositionTree.PositionNode bar, HashMap<String, BigDecimal> values, int index) {
        Object[] data = bar.getData();
        bar.getParent();
        if (data != null && data[index] != null && data[index] instanceof BigDecimal) {
            BigDecimal value = (BigDecimal) data[index];
            if (value.doubleValue() != 0) {
                String tag = null;
                int i = 0;
                while (tag == null && i < data.length) {
                    if (data[i] != null && data[i] instanceof String && !data[i].toString().equalsIgnoreCase(StringUtils.EMPTY_STRING)) {
                        tag = data[i].toString();
                    }
                    i++;
                }
                if (tag != null) {
                    values.put(tag, value);
                }
            }
        }
        if (!bar.isLeaf()) {
            for (int i = 0; i < bar.getChildCount(); i++) {
                PositionTree.PositionNode child = (PositionTree.PositionNode) bar.getChildAt(i);
                getValues(child, values, index);
            }
        }
    }

    public static double floor(double a, int n) {
        double p = Math.pow(10.0, n);
        return Math.floor((a * p) + 0.5) / p;

    }

    public class HashMapComparator implements Comparator<String> {

        private final Map<String, BigDecimal> myMap;

        public HashMapComparator(Map<String, BigDecimal> myMap) {
            this.myMap = myMap;
        }

        @Override
        public int compare(String str1, String str2) {
            BigDecimal bd1 = myMap.get(str1);
            BigDecimal bd2 = myMap.get(str2);
            if (bd1.doubleValue() > bd2.doubleValue()) {
                return -1;
            } else if (bd1.doubleValue() < bd2.doubleValue()) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        jPanel1.setBackground(new java.awt.Color(254, 252, 254));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

}
