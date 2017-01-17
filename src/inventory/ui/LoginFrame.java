package inventory.ui;

import inventory.db.DBConnection;
import inventory.other.LogWriting;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class LoginFrame extends javax.swing.JFrame {

    public LoginFrame() {
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setTitle("Login");
    }

    public static String getusertype() {
        if (adminradiobtn.isSelected()) {
            return "admin";

        } else  {
            return "user";
        }
    }

    DBConnection db;

    public void login() {
        db = new DBConnection();
        String username, password;
        username = UsernameTxt.getText();
        password = new String(passwordTxt.getPassword());

        if (db.checkLogin(username, password, getusertype())) {
            if (getusertype().equalsIgnoreCase("admin")) {
                dispose();
                ITDashboard itd = new ITDashboard();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                itd.setSize(screenSize.width, screenSize.height);
                itd.setExtendedState(this.MAXIMIZED_BOTH);
                itd.setVisible(true);
                itd.setResizable(true);
                itd.setUsername(username);
            } else if (getusertype().equalsIgnoreCase("user")) {
                dispose();
                ITDashboard itd = new ITDashboard();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                itd.setSize(screenSize.width, screenSize.height);
                itd.setExtendedState(this.MAXIMIZED_BOTH);
                itd.setVisible(true);
                itd.setResizable(true);
                itd.setUsername(username);
            }
            new LogWriting(new Date(), username + "(" + getusertype() + ")", "LOGIN", "-", "User",
                      "User");
        } else {
            JOptionPane.showMessageDialog(null, "Wrong username and password", "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
            UsernameTxt.setText("");
            passwordTxt.setText("");
            UsernameTxt.requestFocus(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        upperpanel = new javax.swing.JPanel();
        orglogolab = new javax.swing.JLabel();
        orgnamelab = new javax.swing.JLabel();
        oragaddresslab = new javax.swing.JLabel();
        orgphonenolab = new javax.swing.JLabel();
        orgnamelab1 = new javax.swing.JLabel();
        lowerpanel = new javax.swing.JPanel();
        LoginBttn = new javax.swing.JButton();
        LoginBttn1 = new javax.swing.JButton();
        middlepanel = new javax.swing.JPanel();
        UsernameTxt = new javax.swing.JTextField();
        passwordTxt = new javax.swing.JPasswordField();
        adminradiobtn = new javax.swing.JRadioButton();
        userradiobtn = new javax.swing.JRadioButton();
        usernameiconlab = new javax.swing.JLabel();
        passwordiconlab = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        upperpanel.setBackground(new java.awt.Color(255, 255, 255));
        upperpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        upperpanel.setPreferredSize(new java.awt.Dimension(604, 160));

        orglogolab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/logo.png"))); // NOI18N

        orgnamelab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        orgnamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgnamelab.setText("Sankata Info Sys");

        oragaddresslab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        oragaddresslab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oragaddresslab.setText("01- 4255678");

        orgphonenolab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        orgphonenolab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgphonenolab.setText("New Road, Kathmandu");

        orgnamelab1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        orgnamelab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgnamelab1.setText("IT Inventory Management System");

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(orglogolab, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(oragaddresslab)
                        .addGap(18, 18, 18)
                        .addComponent(orgphonenolab))
                    .addComponent(orgnamelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(orgnamelab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(112, 112, 112))
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(orgnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oragaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orgphonenolab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orgnamelab1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(orglogolab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(upperpanel, java.awt.BorderLayout.PAGE_START);

        lowerpanel.setBackground(new java.awt.Color(102, 153, 255));

        LoginBttn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LoginBttn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/in.png"))); // NOI18N
        LoginBttn.setText("Login");
        LoginBttn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBttnActionPerformed(evt);
            }
        });

        LoginBttn1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LoginBttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/exit.png"))); // NOI18N
        LoginBttn1.setText("Exit");
        LoginBttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBttn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addGap(228, 228, 228)
                .addComponent(LoginBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(LoginBttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginBttn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LoginBttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        getContentPane().add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(604, 200));

        UsernameTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        UsernameTxt.setForeground(new java.awt.Color(102, 102, 102));
        UsernameTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        UsernameTxt.setText("Enter Username");
        UsernameTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        UsernameTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UsernameTxtActionPerformed(evt);
            }
        });

        passwordTxt.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        passwordTxt.setForeground(new java.awt.Color(102, 102, 102));
        passwordTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        passwordTxt.setText("Password");
        passwordTxt.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        passwordTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTxtActionPerformed(evt);
            }
        });
        passwordTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordTxtKeyPressed(evt);
            }
        });

        buttonGroup1.add(adminradiobtn);
        adminradiobtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        adminradiobtn.setText("Admin");

        buttonGroup1.add(userradiobtn);
        userradiobtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        userradiobtn.setText("User");

        usernameiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/visit profile.png"))); // NOI18N

        passwordiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/unlocked.png"))); // NOI18N

        javax.swing.GroupLayout middlepanelLayout = new javax.swing.GroupLayout(middlepanel);
        middlepanel.setLayout(middlepanelLayout);
        middlepanelLayout.setHorizontalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addComponent(usernameiconlab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(UsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addComponent(passwordiconlab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(middlepanelLayout.createSequentialGroup()
                                .addComponent(adminradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(userradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        middlepanelLayout.setVerticalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(UsernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passwordiconlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adminradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userradiobtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        getContentPane().add(middlepanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    private void UsernameTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UsernameTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_UsernameTxtActionPerformed

    private void passwordTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordTxtKeyPressed

    }//GEN-LAST:event_passwordTxtKeyPressed

    private void passwordTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTxtActionPerformed

    private void LoginBttnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBttnActionPerformed
        login();
    }//GEN-LAST:event_LoginBttnActionPerformed

    private void LoginBttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBttn1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_LoginBttn1ActionPerformed

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton LoginBttn;
    private javax.swing.JButton LoginBttn1;
    private javax.swing.JTextField UsernameTxt;
    public static javax.swing.JRadioButton adminradiobtn;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JLabel oragaddresslab;
    private javax.swing.JLabel orglogolab;
    private javax.swing.JLabel orgnamelab;
    private javax.swing.JLabel orgnamelab1;
    private javax.swing.JLabel orgphonenolab;
    private javax.swing.JPasswordField passwordTxt;
    private javax.swing.JLabel passwordiconlab;
    private javax.swing.JPanel upperpanel;
    private javax.swing.JLabel usernameiconlab;
    private javax.swing.JRadioButton userradiobtn;
    // End of variables declaration//GEN-END:variables
}
