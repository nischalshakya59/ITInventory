package inventory.ui.panel;

import com.sun.glass.events.KeyEvent;
import inventory.dao.EmployeeDAO;
import inventory.db.DBConnection;
import inventory.dto.EmployeeDTO;
import inventory.ui.LoginFrame;
import inventory.ui.frame.EmployeeFrame;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class EmployeePanel extends javax.swing.JPanel {

    public EmployeePanel() {
        initComponents();
        loadDatas();
        countRows();
        if (LoginFrame.getusertype().equalsIgnoreCase("user")) {

            insertbtn.setEnabled(false);
            updatebtn.setEnabled(false);
            deletebtn.setEnabled(false);
            addmenuitem.setEnabled(false);
            editmenuitem.setEnabled(false);
            deletemenuitem.setEnabled(false);
            
        }
    }

    public static boolean show = true;

    Connection con;

    EmployeeFrame ef = new EmployeeFrame();

    EmployeeDAO edao = new EmployeeDAO();

    EmployeeDTO edto = new EmployeeDTO();

    public void displayEmployeeFrame() {
        if (show == true) {
            ef.setLocationRelativeTo(null);
            ef.pack();
            ef.setVisible(true);
        }
        show = false;
    }

    public void setVisible() {
        show = true;
    }

    public void loadDatas() {
        if (LoginFrame.getusertype().equalsIgnoreCase("admin")) {
            try {

                for (int c = 0; c < employeetbl.getColumnCount(); c++) {
                    Class<?> col_class = employeetbl.getColumnClass(c);
                    employeetbl.setDefaultEditor(col_class, null);
                }
                employeetbl.setModel(edao.buildTableModel(edao.getQueryResult()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            try {

                for (int c = 0; c < employeetbl.getColumnCount(); c++) {
                    Class<?> col_class = employeetbl.getColumnClass(c);
                    employeetbl.setDefaultEditor(col_class, null);
                }
                employeetbl.setModel(edao.buildTableModelUsers(edao.getQueryResultUsers()));

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void countRows() {
        noofrecorslab.setText("No of Records :- " + employeetbl.getRowCount());
    }

    public void reset() {
        quicksearchtxtfield.setText("");
        loadDatas();
        countRows();
    }

    public void updateValue() {
        if (employeetbl.getSelectedRowCount() < 2) {
            if (employeetbl.getSelectedRowCount() != 0) {
                edto.setEmployeeid((int) employeetbl.getValueAt(employeetbl.getSelectedRow(), 0));
                edto.setEmployeename((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 1));
                edto.setEmployeelname((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 2));
                edto.setEmployeecontactno((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 3));
                edto.setEmployeeaddress((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 4));
                edto.setEmployeepost((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 5));
                edto.setEmployeejoindate((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 6));
                edto.setEmployeesalary((double) employeetbl.getValueAt(employeetbl.getSelectedRow(), 7));
                edto.setEmployeeworkingyears((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 8));
                edto.setEmployeeleavedate((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 9));

                System.out.println((int) employeetbl.getValueAt(employeetbl.getSelectedRow(), 0));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 1));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 2));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 3));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 4));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 5));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 6));
                System.out.println((double) employeetbl.getValueAt(employeetbl.getSelectedRow(), 7));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 8));
                System.out.println((String) employeetbl.getValueAt(employeetbl.getSelectedRow(), 9));
                ef.update(edto);
            } else {
                JOptionPane.showMessageDialog(null, "Select one row to update", "Inventory Management",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(null, "You can only update one row at a time", "Inventory Management",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deleteUsingArray() {
        try {
            con = new DBConnection().getConnection();
            int[] rows = employeetbl.getSelectedRows();

            if (rows.length != 0) {
                int clicked = JOptionPane.showConfirmDialog(null,
                        "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String empid = Integer.toString((int) employeetbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{empid};
                        edao.deleteRows(strArray);
                    }
                }
            }
            loadDatas();
            countRows();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < employeetbl.getColumnCount(); c++) {
                Class<?> col_class = employeetbl.getColumnClass(c);
                employeetbl.setDefaultEditor(col_class, null);
            }
            employeetbl.setModel(edao.buildTableModel(edao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            employeetbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupmenu = new javax.swing.JPopupMenu();
        addmenuitem = new javax.swing.JMenuItem();
        editmenuitem = new javax.swing.JMenuItem();
        deletemenuitem = new javax.swing.JMenuItem();
        refreshmenuitem = new javax.swing.JMenuItem();
        upperpanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        refreshbtn = new javax.swing.JButton();
        quicksearchlab = new javax.swing.JLabel();
        quicksearchtxtfield = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        selectedlab = new javax.swing.JLabel();
        lowepanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        insertbtn = new javax.swing.JButton();
        noofrecorslab = new javax.swing.JLabel();
        middlepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        employeetbl = new javax.swing.JTable();

        popupmenu.setPreferredSize(new java.awt.Dimension(100, 115));

        addmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        addmenuitem.setText("Add");
        addmenuitem.setPreferredSize(new java.awt.Dimension(145, 22));
        addmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(addmenuitem);

        editmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/pencil.png"))); // NOI18N
        editmenuitem.setText("Edit");
        editmenuitem.setPreferredSize(new java.awt.Dimension(145, 22));
        editmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(editmenuitem);

        deletemenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/bin.png"))); // NOI18N
        deletemenuitem.setText("Delete");
        deletemenuitem.setPreferredSize(new java.awt.Dimension(145, 22));
        deletemenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletemenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(deletemenuitem);

        refreshmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/refresh.png"))); // NOI18N
        refreshmenuitem.setText("Refresh");
        refreshmenuitem.setPreferredSize(new java.awt.Dimension(145, 22));
        refreshmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(refreshmenuitem);

        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 120));

        refreshbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/refresh.png"))); // NOI18N
        refreshbtn.setText("Refresh");
        refreshbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        quicksearchlab.setText("Quick Search ");

        quicksearchtxtfield.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        quicksearchtxtfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quicksearchtxtfieldActionPerformed(evt);
            }
        });
        quicksearchtxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quicksearchtxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quicksearchtxtfieldKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        selectedlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectedlab.setForeground(new java.awt.Color(255, 255, 255));
        selectedlab.setText("Employee ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedlab, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedlab, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 515, Short.MAX_VALUE)
                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        add(upperpanel, java.awt.BorderLayout.PAGE_START);

        lowepanel.setPreferredSize(new java.awt.Dimension(890, 65));

        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/bin.png"))); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        updatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/pencil.png"))); // NOI18N
        updatebtn.setText("Update");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        insertbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        insertbtn.setText("Add Employee");
        insertbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertbtnActionPerformed(evt);
            }
        });

        noofrecorslab.setText("No Of Records :-");

        javax.swing.GroupLayout lowepanelLayout = new javax.swing.GroupLayout(lowepanel);
        lowepanel.setLayout(lowepanelLayout);
        lowepanelLayout.setHorizontalGroup(
            lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noofrecorslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 356, Short.MAX_VALUE)
                .addComponent(insertbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lowepanelLayout.setVerticalGroup(
            lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deletebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(insertbtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lowepanelLayout.createSequentialGroup()
                        .addGroup(lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noofrecorslab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(lowepanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(890, 420));

        employeetbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        employeetbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        employeetbl.setComponentPopupMenu(popupmenu);
        employeetbl.setRowHeight(20);
        employeetbl.setRowMargin(4);
        employeetbl.setSelectionBackground(new java.awt.Color(102, 153, 255));
        jScrollPane1.setViewportView(employeetbl);

        javax.swing.GroupLayout middlepanelLayout = new javax.swing.GroupLayout(middlepanel);
        middlepanel.setLayout(middlepanelLayout);
        middlepanelLayout.setHorizontalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 870, Short.MAX_VALUE)
                .addContainerGap())
        );
        middlepanelLayout.setVerticalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(middlepanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void quicksearchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldActionPerformed

    }//GEN-LAST:event_quicksearchtxtfieldActionPerformed

    private void insertbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbtnActionPerformed
        displayEmployeeFrame();
    }//GEN-LAST:event_insertbtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        displayEmployeeFrame();
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void editmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmenuitemActionPerformed
        updateValue();
    }//GEN-LAST:event_editmenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void refreshmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshmenuitemActionPerformed
        reset();
    }//GEN-LAST:event_refreshmenuitemActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        updateValue();
        reset();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void quicksearchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == quicksearchtxtfield && keycode == KeyEvent.VK_BACKSPACE && quicksearchtxtfield.getText().length() == 0) {
            reset();
        } else {
            loadDataQuickSearch();

        }
    }//GEN-LAST:event_quicksearchtxtfieldKeyPressed

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        loadDataQuickSearch();
    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JButton deletebtn;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem editmenuitem;
    private javax.swing.JTable employeetbl;
    private javax.swing.JButton insertbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowepanel;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JLabel noofrecorslab;
    private javax.swing.JPopupMenu popupmenu;
    private javax.swing.JLabel quicksearchlab;
    private javax.swing.JTextField quicksearchtxtfield;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JMenuItem refreshmenuitem;
    private javax.swing.JLabel selectedlab;
    private javax.swing.JButton updatebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
