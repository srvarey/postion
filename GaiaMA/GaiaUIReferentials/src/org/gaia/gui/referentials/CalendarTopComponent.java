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
package org.gaia.gui.referentials;

import com.toedter.calendar.JSpinnerDateEditor;
import java.awt.Component;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import org.apache.log4j.Logger;
import org.gaia.dao.jade.DAOCallerAgent;
import org.gaia.dao.referentials.CalendarAccessor;
import org.gaia.dao.referentials.CurrencyAccessor;
import org.gaia.dao.utils.DateUtils;
import org.gaia.domain.referentials.CalendarDate;
import org.gaia.domain.referentials.HolidayCalendar;
import org.gaia.domain.utils.StringUtils;
import org.gaia.gui.common.GaiaTopComponent;
import org.gaia.gui.common.MenuManager;
import org.gaia.gui.utils.DateShortCut;
import org.gaia.gui.utils.ExcelAdapter;
import org.gaia.gui.utils.GUIUtils;
import org.gaia.gui.utils.GaiaJSpinnerDateEditor;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 * Top component which displays calendars.
 */
@ConvertAsProperties(dtd = "-//org.gaia.gui.referentials//Calendar//EN", autostore = false)
@TopComponent.Description(preferredID = "CalendarTopComponent", iconBase = "org/gaia/gui/referentials/calendar.png", persistenceType = TopComponent.PERSISTENCE_ALWAYS)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "org.gaia.gui.referentials.CalendarTopComponent")
@ActionReference(path = "Menu" + MenuManager.CalendarTopComponentMenu, position = MenuManager.CalendarTopComponent)
@TopComponent.OpenActionRegistration(displayName = "#CTL_CalendarAction", preferredID = "CalendarTopComponent")
@Messages({"CTL_CalendarAction=Calendar", "CTL_CalendarTopComponent=Calendar", "HINT_CalendarTopComponent=This is a Calendar window"})
public final class CalendarTopComponent extends GaiaTopComponent {

    private static final Logger logger = Logger.getLogger(CalendarTopComponent.class);
    List<CalendarDate> dates = new ArrayList();
    List<CalendarDate> removedDates = null;

    public CalendarTopComponent() {
        initComponents();
        setName(Bundle.CTL_CalendarTopComponent());
        setToolTipText(Bundle.HINT_CalendarTopComponent());
    }

    @Override
    public void initContext() {

        jDateChooserDate.setDate(DateUtils.getDate());
        try {
            List<String> currencies = (List) DAOCallerAgent.callMethod(CurrencyAccessor.class, CurrencyAccessor.LOAD_CURRENCY_CODES);
            GUIUtils.fillCombo(jComboBoxCurrency, currencies);
            /**
             * ShortCut for date
             */
            DateShortCut.eventkey((JSpinnerDateEditor) jDateChooserDate.getComponent(1));

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
        ExcelAdapter myAd = new ExcelAdapter(jTableCalendars);
        TableCellRenderer tableCellRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table,
                    Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {
                if (value instanceof Date) {
                    value = dateFormatter.format(value);
                }
                return super.getTableCellRendererComponent(table, value, isSelected,
                        hasFocus, row, column);
            }
        };
        jTableDates.getColumnModel().getColumn(0).setCellRenderer(tableCellRenderer);
        removedDates = new ArrayList();
        displayTable();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButtonQuery = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCalendars = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldHolidayCode = new javax.swing.JTextField();
        jButtonDelete = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonNew = new javax.swing.JButton();
        jComboBoxStatus = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxCurrency = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableDates = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;   //Disallow the editing of any cell
            }
        };
        jButtonAdd = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jButtonRemove = new javax.swing.JButton();
        jDateChooserDate = new com.toedter.calendar.JDateChooser(null,null,dateFormat,new GaiaJSpinnerDateEditor());

        jPanel2.setBackground(new java.awt.Color(254, 252, 254));

        jButtonQuery.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonQuery, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonQuery.text")); // NOI18N
        jButtonQuery.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonQueryActionPerformed(evt);
            }
        });

        jTableCalendars.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Code","Currency"
            }
        ));
        jTableCalendars.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableCalendars.setGridColor(new java.awt.Color(204, 255, 255));
        jTableCalendars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableCalendarsMouseClicked(evt);
            }
        });
        jTableCalendars.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTableCalendarsFocusGained(evt);
            }
        });
        jTableCalendars.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTableCalendarsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableCalendars);

        jPanel1.setBackground(new java.awt.Color(238, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel4, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jLabel4.text")); // NOI18N

        jTextFieldHolidayCode.setName("jTextFieldHolidayCode"); // NOI18N

        jButtonDelete.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonDelete, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonDelete.text")); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonSave.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonSave, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonSave.text")); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonNew.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonNew, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonNew.text")); // NOI18N
        jButtonNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewActionPerformed(evt);
            }
        });

        jComboBoxStatus.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxStatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Active", "Inactive" }));

        org.openide.awt.Mnemonics.setLocalizedText(jLabel7, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jLabel7.text")); // NOI18N

        jComboBoxCurrency.setBackground(new java.awt.Color(255, 255, 255));
        jComboBoxCurrency.setModel(new javax.swing.DefaultComboBoxModel(new String[] { }));

        jTableDates.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableDates.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
            },
            new String [] {
                "Date"
            }
        ));
        jScrollPane2.setViewportView(jTableDates);

        jButtonAdd.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonAdd, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonAdd.text")); // NOI18N
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jLabel3.text")); // NOI18N

        jButtonRemove.setBackground(new java.awt.Color(195, 229, 255));
        org.openide.awt.Mnemonics.setLocalizedText(jButtonRemove, org.openide.util.NbBundle.getMessage(CalendarTopComponent.class, "CalendarTopComponent.jButtonRemove.text")); // NOI18N
        jButtonRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRemoveActionPerformed(evt);
            }
        });

        jDateChooserDate.setBackground(new java.awt.Color(238, 254, 254));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldHolidayCode)
                                    .addComponent(jComboBoxCurrency, 0, 101, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButtonNew)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonSave)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonDelete))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jDateChooserDate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jButtonRemove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButtonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 10, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextFieldHolidayCode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBoxCurrency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonAdd)
                            .addComponent(jDateChooserDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBoxStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonSave)
                            .addComponent(jButtonDelete)
                            .addComponent(jButtonNew))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonQuery)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButtonQuery)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Load all
     *
     * @param evt
     */
    private void jButtonQueryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonQueryActionPerformed
        displayTable();
    }//GEN-LAST:event_jButtonQueryActionPerformed

    /**
     * Display selected
     *
     * @param evt
     */
    private void jTableCalendarsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableCalendarsMouseClicked
        refereshData(evt);
    }//GEN-LAST:event_jTableCalendarsMouseClicked

    private void jTableCalendarsFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTableCalendarsFocusGained
    }//GEN-LAST:event_jTableCalendarsFocusGained

    /**
     * Display seleted
     *
     * @param evt
     */
    private void jTableCalendarsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTableCalendarsKeyPressed
        refereshData(evt);
    }//GEN-LAST:event_jTableCalendarsKeyPressed

    /**
     * delete
     *
     * @param evt
     */
    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        try {
            String calendarName = jTextFieldHolidayCode.getText();
            if (StringUtils.isEmptyString(calendarName)) {
                return;
            }
            int confirm = JOptionPane.showConfirmDialog(this, "Delete Calendar " + calendarName + "?", "Confirmation",
                    JOptionPane.YES_NO_OPTION);
            if (JOptionPane.YES_OPTION == confirm) {
                DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.DELETE_CALENDAR, calendarName);
                removedDates = new ArrayList();
                displayTable();
            }

        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    /**
     * store data
     *
     * @param evt
     */
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        try {
            String name = jTextFieldHolidayCode.getText();
            if (!name.isEmpty()) {
                HolidayCalendar calendar = (HolidayCalendar) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.GET_CALENDAR_BY_CODE, name);
                if (calendar == null) {
                    calendar = new HolidayCalendar();
                }
                calendar.setCalendarCode(name);
                calendar.setCurrency(jComboBoxCurrency.getSelectedItem().toString());
                calendar.setCalendarDateList(dates);
                DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.STORE_CALENDAR, calendar);
                for (CalendarDate calendarDate : removedDates) {
                    DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.DELETE_CALENDAR_DATE, calendarDate);
                }
                displayTable();
            } else {
                JOptionPane.showMessageDialog(this, "Empty Holiday code", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }//GEN-LAST:event_jButtonSaveActionPerformed

    /**
     * new date
     *
     * @param evt
     */
    private void jButtonNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewActionPerformed
        jTextFieldHolidayCode.setText(StringUtils.EMPTY_STRING);
        DefaultTableModel tm = new DefaultTableModel(new Object[][]{}, new String[]{"Date"});
        jTableDates.setModel(tm);
        dates = new ArrayList();
        removedDates = new ArrayList();
    }//GEN-LAST:event_jButtonNewActionPerformed

    /**
     * Add Date
     *
     * @param evt
     */
    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDates.getModel();
        boolean alreadyIn = false;
        String sDate = dateFormatter.format(jDateChooserDate.getDate());
        for (int i = 0; i < model.getRowCount(); i++) {
            if (sDate.equals(model.getValueAt(i, 0))) {
                alreadyIn = true;
            }
        }
        if (!alreadyIn) {
            Object[] row = {sDate};
            model.addRow(row);
            if (dates != null) {
                dates.add(new CalendarDate(null, jDateChooserDate.getDate()));
            }
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    /**
     * Remove Date
     *
     * @param evt
     */
    private void jButtonRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRemoveActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDates.getModel();
        int selectedRow = jTableDates.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Date date = dateFormatter.parse(model.getValueAt(selectedRow, 0).toString());
                model.removeRow(selectedRow);
                dates.remove(selectedRow);
                String code = jTextFieldHolidayCode.getText();
                CalendarDate calendarDate = (CalendarDate) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.GET_CALENDAR_DATE_BY_CODE_AND_DATE, code, date);
                removedDates.add(calendarDate);
            } catch (ParseException e) {
                logger.error("Error " + StringUtils.formatErrorMessage(e));
            }
        }
    }//GEN-LAST:event_jButtonRemoveActionPerformed

    /**
     * refresh data
     *
     * @param evt
     */
    private void refereshData(java.awt.event.ComponentEvent evt) {
        int rownum = jTableCalendars.getSelectedRow();
        if (rownum >= 0) {
            String calendarCode = GUIUtils.getTableValueAt(jTableCalendars, rownum, 0);
            if (calendarCode != null) {
                DefaultTableModel tableModel = (DefaultTableModel) jTableDates.getModel();
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
                HolidayCalendar calendar;
                try {
                    calendar = (HolidayCalendar) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.GET_CALENDAR_BY_CODE, calendarCode);
                    jTextFieldHolidayCode.setText(calendar.getCalendarCode());
                    jComboBoxCurrency.setSelectedItem(calendar.getCurrency());
                    dates = calendar.getCalendarDateList();
                } catch (Exception ex) {
                    logger.error(StringUtils.formatErrorMessage(ex));
                }

                for (CalendarDate calendarDate : dates) {
                    Object[] row = {dateFormatter.format(calendarDate.getCalendarDate())};
                    tableModel.addRow(row);
                }
            }
            removedDates = new ArrayList();
        }
    }

    /**
     * display table
     */
    private void displayTable() {
        try {
            List< HolidayCalendar> resultList = (List) DAOCallerAgent.callMethod(CalendarAccessor.class, CalendarAccessor.LOAD_ALL_CALENDARS);
            DefaultTableModel tableModel = (DefaultTableModel) jTableCalendars.getModel();
            while (tableModel.getRowCount() > 0) {
                tableModel.removeRow(0);
            }
            for (HolidayCalendar calendar : resultList) {
                Object[] row = {calendar.getCalendarCode(), calendar.getCurrency()};
                tableModel.addRow(row);
            }
        } catch (Exception ex) {
            logger.error(StringUtils.formatErrorMessage(ex));
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonNew;
    private javax.swing.JButton jButtonQuery;
    private javax.swing.JButton jButtonRemove;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox jComboBoxCurrency;
    private javax.swing.JComboBox jComboBoxStatus;
    private com.toedter.calendar.JDateChooser jDateChooserDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableCalendars;
    private javax.swing.JTable jTableDates;
    private javax.swing.JTextField jTextFieldHolidayCode;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentOpened() {
        initContext();
    }

    @Override
    public void componentClosed() {
    }

    void writeProperties(java.util.Properties p) {

        p.setProperty("version", "1.0");

    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");

    }
}
