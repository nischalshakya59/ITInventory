package inventory.ui.frame;

import inventory.dao.EmployeeDAO;
import inventory.dao.UserDAO;
import inventory.dto.EmployeeDTO;
import inventory.dto.UserDTO;
import inventory.ui.panel.SettingPanel;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ViewUserFrame extends javax.swing.JFrame {

    public ViewUserFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("View User");
        this.setResizable(false);
        reset();
        useridtxtfield.setEditable(false);

    }

    EmployeeDAO employeedao = new EmployeeDAO();
    EmployeeDTO employeedto = new EmployeeDTO();
    UserDTO userdto = new UserDTO();
    UserDAO userdao = new UserDAO();

    public String UserType() {
        if (adminradiobtn.isSelected()) {
            return "admin";
        } else if (userradiobtn.isSelected()) {
            return "user";
        } else {
            return "null";
        }
    }

    public void countRows() {
        noofrecordslab.setText("No Of Records :- " + usertbl.getRowCount());
    }

    public void reset() {
        useridtxtfield.setEditable(false);
        usernametxtfield.setText("");
        passwrodtxtfield.setText("");
        fnametxtfield.setText("");
        lnametxtfield.setText("");
        countRows();
        loadDatas();
        setUserID();
        usernametxtfield.setEditable(false);
        passwrodtxtfield.setEditable(false);
        fnametxtfield.setEditable(true);
        lnametxtfield.setEditable(true);
        addbtn.setEnabled(true);
        updatebtn.setText("Edit");
    }

    public void setUserID() {
        int userid = userdao.userId() + 1;
        useridtxtfield.setText(String.valueOf(userid));
    }

    public void insert() {

        if (checkUser() == true) {
            userdto.setUserid(Integer.parseInt(useridtxtfield.getText()));
            userdto.setUsername(usernametxtfield.getText());
            userdto.setPassword(String.valueOf(passwrodtxtfield.getPassword()));
            userdto.setUsertype(UserType());
            if (userdao.insertUser(userdto) == true) {
                countRows();
                reset();
            }
        }

    }

    public boolean checkUser() {
        boolean check = false;
        String fname = fnametxtfield.getText();
        String lname = lnametxtfield.getText();
        System.out.println(fname);
        System.out.println(lname);
        ResultSet rs = new UserDAO().verifyUser(fname, lname);
        if (rs != null) {
            try {
                while (rs.next()) {
                    int userid = rs.getInt(1);
                    System.out.println(userid);
                    useridtxtfield.setText(String.valueOf(userid));
                    usernametxtfield.setEditable(true);
                    passwrodtxtfield.setEditable(true);
                    check = true;
                }

            } catch (SQLException ex) {
                Logger.getLogger(ViewUserFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return check;
    }

    public Set getValueTable() {
        int row = usertbl.getRowCount();
        Set<Integer> treeset = new TreeSet<>();
        for (int i = 0; i < row; i++) {
            treeset.add((Integer) usertbl.getValueAt(i, 0));
        }
        return treeset;
    }

    public void updateValue() throws SQLException {
        if (usertbl.getSelectedRowCount() < 2) {
            if (usertbl.getSelectedRowCount() != 0) {
                if (updatebtn.getText().equalsIgnoreCase("Edit")) {
                    updatebtn.setText("Update");
                    addbtn.setEnabled(false);
                    UserDTO userdto = new UserDAO().editUser(usertbl);

                    useridtxtfield.setEditable(false);
                    useridtxtfield.setText(String.valueOf(userdto.getUserid()));
                    ResultSet rs = employeedao.getEmployeename(useridtxtfield.getText());
                    while (rs.next()) {
                        fnametxtfield.setText(rs.getString(1));
                        fnametxtfield.setEditable(false);
                        lnametxtfield.setText(rs.getString(2));
                        lnametxtfield.setEditable(false);
                    }
                    usernametxtfield.setEditable(true);
                    passwrodtxtfield.setEditable(true);
                    usernametxtfield.setText(String.valueOf(userdto.getUsername()));
                    passwrodtxtfield.setText(String.valueOf(userdto.getPassword()));

                } else {
                    addbtn.setEnabled(true);
                    updatebtn.setText("Edit");
                    userdto.setUserid(Integer.parseInt(useridtxtfield.getText()));
                    userdto.setUsername(usernametxtfield.getText());
                    userdto.setPassword(String.valueOf(passwrodtxtfield.getPassword()));
                    userdto.setUsertype(UserType());
                    if (userdao.updateUser(userdto) == true) {
                        reset();
                        updatebtn.setText("Edit");
                    }
                }
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
            int[] rows = usertbl.getSelectedRows();

            if (rows.length != 0) {
                int clicked = JOptionPane.showConfirmDialog(null, "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String supplierid = Integer.toString((int) usertbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{supplierid};
                        userdao.deleteUser(strArray);
                    }
                }
                reset();
            } else {
                JOptionPane.showMessageDialog(null, "Select rows to delete", "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatas() {
        try {

            for (int c = 0; c < usertbl.getColumnCount(); c++) {
                Class<?> col_class = usertbl.getColumnClass(c);
                usertbl.setDefaultEditor(col_class, null);
            }
            usertbl.setModel(userdao.buildTableModel(userdao.getQueryResult()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usertypebtngroup = new javax.swing.ButtonGroup();
        lowerpanel = new javax.swing.JPanel();
        deletebtn = new javax.swing.JButton();
        addbtn = new javax.swing.JButton();
        noofrecordslab = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        updatebtn = new javax.swing.JButton();
        upperpanel = new javax.swing.JPanel();
        refreshbtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        leftpanel = new javax.swing.JPanel();
        useridlab = new javax.swing.JLabel();
        fnamelab = new javax.swing.JLabel();
        lastnamelab = new javax.swing.JLabel();
        usernamelab = new javax.swing.JLabel();
        passwordlab = new javax.swing.JLabel();
        usertypelab = new javax.swing.JLabel();
        useridtxtfield = new javax.swing.JTextField();
        fnametxtfield = new javax.swing.JTextField();
        usernametxtfield = new javax.swing.JTextField();
        lnametxtfield = new javax.swing.JTextField();
        passwrodtxtfield = new javax.swing.JPasswordField();
        adminradiobtn = new javax.swing.JRadioButton();
        userradiobtn = new javax.swing.JRadioButton();
        righttablepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usertbl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        lowerpanel.setPreferredSize(new java.awt.Dimension(515, 65));

        deletebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/bin.png"))); // NOI18N
        deletebtn.setText("Delete");
        deletebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletebtnActionPerformed(evt);
            }
        });

        addbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        addbtn.setText("Add User");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        noofrecordslab.setText("No Of Records :-");

        updatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/pencil.png"))); // NOI18N
        updatebtn.setText("Edit");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(lowerpanelLayout.createSequentialGroup()
                        .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 365, Short.MAX_VALUE)
                        .addComponent(addbtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        getContentPane().add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        upperpanel.setPreferredSize(new java.awt.Dimension(515, 60));

        refreshbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/refresh.png"))); // NOI18N
        refreshbtn.setText("Refresh");
        refreshbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 751, Short.MAX_VALUE))
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(upperpanel, java.awt.BorderLayout.PAGE_START);

        leftpanel.setPreferredSize(new java.awt.Dimension(250, 329));

        useridlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        useridlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        useridlab.setText("User Id :-");

        fnamelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        fnamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fnamelab.setText("First Name :-");

        lastnamelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lastnamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lastnamelab.setText("Last Name :-");

        usernamelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usernamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usernamelab.setText("Username :-");

        passwordlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        passwordlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        passwordlab.setText("Password :-");

        usertypelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usertypelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        usertypelab.setText("Usertype :-");

        useridtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        fnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        fnametxtfield.setToolTipText("enter the first name");
        fnametxtfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fnametxtfieldActionPerformed(evt);
            }
        });

        usernametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        usernametxtfield.setToolTipText("enter the username");

        lnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lnametxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lnametxtfieldKeyPressed(evt);
            }
        });

        passwrodtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        usertypebtngroup.add(adminradiobtn);
        adminradiobtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        adminradiobtn.setText("Admin");

        usertypebtngroup.add(userradiobtn);
        userradiobtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        userradiobtn.setText("User");

        javax.swing.GroupLayout leftpanelLayout = new javax.swing.GroupLayout(leftpanel);
        leftpanel.setLayout(leftpanelLayout);
        leftpanelLayout.setHorizontalGroup(
            leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(useridlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fnamelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lastnamelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usernamelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passwordlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(usertypelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(useridtxtfield, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(fnametxtfield, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(usernametxtfield, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(lnametxtfield, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                        .addComponent(passwrodtxtfield))
                    .addComponent(adminradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        leftpanelLayout.setVerticalGroup(
            leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(useridlab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(useridtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordlab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwrodtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usertypelab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adminradiobtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(userradiobtn)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        getContentPane().add(leftpanel, java.awt.BorderLayout.LINE_START);

        righttablepanel.setPreferredSize(new java.awt.Dimension(571, 300));

        usertbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        usertbl.setModel(new javax.swing.table.DefaultTableModel(
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
        usertbl.setRowMargin(4);
        jScrollPane1.setViewportView(usertbl);

        javax.swing.GroupLayout righttablepanelLayout = new javax.swing.GroupLayout(righttablepanel);
        righttablepanel.setLayout(righttablepanelLayout);
        righttablepanelLayout.setHorizontalGroup(
            righttablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, righttablepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                .addContainerGap())
        );
        righttablepanelLayout.setVerticalGroup(
            righttablepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
        );

        getContentPane().add(righttablepanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed

        insert();
    }//GEN-LAST:event_addbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        try {
            updateValue();
        } catch (SQLException ex) {
            Logger.getLogger(ViewUserFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_updatebtnActionPerformed

    private void fnametxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fnametxtfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fnametxtfieldActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
        reset();
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new SettingPanel().visible();
    }//GEN-LAST:event_formWindowClosed

    private void lnametxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lnametxtfieldKeyPressed
        // TODO add your handling code here:

        int keycode = evt.getKeyCode();
        if (evt.getSource() == lnametxtfield) {
            if (keycode == KeyEvent.VK_ENTER && lnametxtfield.getText().length() != 0) {

                checkUser();
            }
        }
    }//GEN-LAST:event_lnametxtfieldKeyPressed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewUserFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addbtn;
    private javax.swing.JRadioButton adminradiobtn;
    private javax.swing.JButton deletebtn;
    private javax.swing.JLabel fnamelab;
    private javax.swing.JTextField fnametxtfield;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lastnamelab;
    private javax.swing.JPanel leftpanel;
    private javax.swing.JTextField lnametxtfield;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JLabel noofrecordslab;
    private javax.swing.JLabel passwordlab;
    private javax.swing.JPasswordField passwrodtxtfield;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JPanel righttablepanel;
    private javax.swing.JButton updatebtn;
    private javax.swing.JPanel upperpanel;
    private javax.swing.JLabel useridlab;
    private javax.swing.JTextField useridtxtfield;
    private javax.swing.JLabel usernamelab;
    private javax.swing.JTextField usernametxtfield;
    private javax.swing.JRadioButton userradiobtn;
    private javax.swing.JTable usertbl;
    private javax.swing.ButtonGroup usertypebtngroup;
    private javax.swing.JLabel usertypelab;
    // End of variables declaration//GEN-END:variables
}
