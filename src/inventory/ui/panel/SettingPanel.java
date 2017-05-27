package inventory.ui.panel;

import inventory.ui.LoginFrame;
import inventory.ui.frame.ChangePasswordFrame;
import inventory.ui.frame.ViewUserFrame;
import java.awt.Color;
import java.io.IOException;
import javax.swing.JOptionPane;

public class SettingPanel extends javax.swing.JPanel {

    public SettingPanel() {
        initComponents();
        if (LoginFrame.getusertype().equalsIgnoreCase("user")){
            viewlogpanel.setVisible(false);
            manageuserpanel.setVisible(false);
        }
    }

    public static boolean show = true;

    public void visible() {
        show = true;
    }

    public void viewLog() {
        String filename = JOptionPane.showInputDialog(null, "Type the product, brand, category, \nsupplier or sales \n To view the log file",
                "Inventory Management System", JOptionPane.INFORMATION_MESSAGE
        );
        String filenameExt = filename + ".txt";
        try {
            Runtime.getRuntime().exec("cmd /c del *.txt %systemdrive%\\InventoryMgmt\\view\\");

            JOptionPane.showMessageDialog(null, "Warning!!! Please donot edit this file");
            switch (filenameExt.toLowerCase()) {
                case "brand.txt":
                    Runtime.getRuntime().exec("cmd /c copy %systemdrive%\\InventoryMgmt\\Store\\brand.txt "
                            + " %systemdrive%\\InventoryMgmt\\view\\brand.txt");
                    Runtime.getRuntime().exec("cmd /c attrib +r %systemdrive%\\InventoryMgmt\\view\\brand.txt");
                    Runtime.getRuntime().exec("cmd /c start %systemdrive%\\InventoryMgmt\\view\\brand.txt");
                    break;

                case "product.txt":
                    Runtime.getRuntime().exec("cmd /c copy %systemdrive%\\InventoryMgmt\\Store\\product.txt "
                            + " %systemdrive%\\InventoryMgmt\\view\\product.txt");
                    Runtime.getRuntime().exec("cmd /c attrib +r %systemdrive%\\InventoryMgmt\\view\\product.txt");
                    Runtime.getRuntime().exec("cmd /c start %systemdrive%\\InventoryMgmt\\view\\product.txt");
                    break;

                case "category.txt":
                    Runtime.getRuntime().exec("cmd /c copy %systemdrive%\\InventoryMgmt\\Store\\category.txt "
                            + " %systemdrive%\\InventoryMgmt\\view\\category.txt");
                    Runtime.getRuntime().exec("cmd /c attrib +r %systemdrive%\\InventoryMgmt\\view\\category.txt");
                    Runtime.getRuntime().exec("cmd /c start %systemdrive%\\InventoryMgmt\\view\\category.txt");
                    break;

                case "supplier.txt":
                    Runtime.getRuntime().exec("cmd /c copy %systemdrive%\\InventoryMgmt\\Store\\supplier.txt "
                            + " %systemdrive%\\InventoryMgmt\\view\\supplier.txt");
                    Runtime.getRuntime().exec("cmd /c attrib +r %systemdrive%\\InventoryMgmt\\view\\supplier.txt");
                    Runtime.getRuntime().exec("cmd /c start %systemdrive%\\InventoryMgmt\\view\\supplier.txt");
                    break;
                    
                case "sales.txt":
                    Runtime.getRuntime().exec("cmd /c copy %systemdrive%\\InventoryMgmt\\sales\\sales.txt "
                            + " %systemdrive%\\InventoryMgmt\\view\\sales.txt");
                    Runtime.getRuntime().exec("cmd /c attrib +r %systemdrive%\\InventoryMgmt\\view\\sales.txt");
                    Runtime.getRuntime().exec("cmd /c start %systemdrive%\\InventoryMgmt\\view\\sales.txt");
                    break;
            }

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
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
        jPanel1 = new javax.swing.JPanel();
        selectedlab = new javax.swing.JLabel();
        lowerpanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        middlepanel = new javax.swing.JPanel();
        manageuserpanel = new javax.swing.JPanel();
        manageuserlab = new javax.swing.JLabel();
        manageusericonlab = new javax.swing.JLabel();
        viewlogpanel = new javax.swing.JPanel();
        viewloglab = new javax.swing.JLabel();
        viewlogiconlab = new javax.swing.JLabel();
        changepasswordpanel = new javax.swing.JPanel();
        changepasswordlab = new javax.swing.JLabel();
        changepasswordiconlab = new javax.swing.JLabel();

        popupmenu.setPreferredSize(new java.awt.Dimension(145, 190));

        addmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        addmenuitem.setText("Add");
        addmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(addmenuitem);

        editmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/pencil.png"))); // NOI18N
        editmenuitem.setText("Edit");
        editmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(editmenuitem);

        deletemenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/bin.png"))); // NOI18N
        deletemenuitem.setText("Delete");
        deletemenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletemenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(deletemenuitem);

        refreshmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/refresh.png"))); // NOI18N
        refreshmenuitem.setText("Refresh");
        refreshmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(refreshmenuitem);

        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 80));

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        selectedlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectedlab.setForeground(new java.awt.Color(255, 255, 255));
        selectedlab.setText("Setting");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedlab, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(739, Short.MAX_VALUE))
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
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        add(upperpanel, java.awt.BorderLayout.PAGE_START);

        lowerpanel.setPreferredSize(new java.awt.Dimension(890, 65));

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 945, Short.MAX_VALUE)
                .addContainerGap())
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        manageuserpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        manageuserlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageuserlab.setForeground(new java.awt.Color(102, 102, 102));
        manageuserlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageuserlab.setText("View User");
        manageuserlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                manageuserlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                manageuserlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                manageuserlabMouseReleased(evt);
            }
        });

        manageusericonlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        manageusericonlab.setForeground(new java.awt.Color(102, 102, 102));
        manageusericonlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        manageusericonlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/user2.png"))); // NOI18N

        javax.swing.GroupLayout manageuserpanelLayout = new javax.swing.GroupLayout(manageuserpanel);
        manageuserpanel.setLayout(manageuserpanelLayout);
        manageuserpanelLayout.setHorizontalGroup(
            manageuserpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageuserpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageusericonlab)
                .addGap(18, 18, 18)
                .addComponent(manageuserlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        manageuserpanelLayout.setVerticalGroup(
            manageuserpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(manageuserpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageuserlab, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, manageuserpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(manageusericonlab)
                .addGap(32, 32, 32))
        );

        viewlogpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        viewloglab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viewloglab.setForeground(new java.awt.Color(102, 102, 102));
        viewloglab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewloglab.setText("View Log ");
        viewloglab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewloglabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewloglabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                viewloglabMouseReleased(evt);
            }
        });

        viewlogiconlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        viewlogiconlab.setForeground(new java.awt.Color(102, 102, 102));
        viewlogiconlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        viewlogiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/log.png"))); // NOI18N

        javax.swing.GroupLayout viewlogpanelLayout = new javax.swing.GroupLayout(viewlogpanel);
        viewlogpanel.setLayout(viewlogpanelLayout);
        viewlogpanelLayout.setHorizontalGroup(
            viewlogpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewlogpanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(viewlogiconlab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewloglab, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        viewlogpanelLayout.setVerticalGroup(
            viewlogpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewlogpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(viewloglab, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewlogpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(viewlogiconlab)
                .addGap(33, 33, 33))
        );

        changepasswordpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        changepasswordlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        changepasswordlab.setForeground(new java.awt.Color(102, 102, 102));
        changepasswordlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changepasswordlab.setText("Change Password");
        changepasswordlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                changepasswordlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                changepasswordlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                changepasswordlabMouseReleased(evt);
            }
        });

        changepasswordiconlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        changepasswordiconlab.setForeground(new java.awt.Color(102, 102, 102));
        changepasswordiconlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        changepasswordiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/key3.png"))); // NOI18N

        javax.swing.GroupLayout changepasswordpanelLayout = new javax.swing.GroupLayout(changepasswordpanel);
        changepasswordpanel.setLayout(changepasswordpanelLayout);
        changepasswordpanelLayout.setHorizontalGroup(
            changepasswordpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changepasswordpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changepasswordiconlab)
                .addGap(18, 18, 18)
                .addComponent(changepasswordlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        changepasswordpanelLayout.setVerticalGroup(
            changepasswordpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(changepasswordpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(changepasswordlab, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, changepasswordpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(changepasswordiconlab)
                .addGap(32, 32, 32))
        );

        javax.swing.GroupLayout middlepanelLayout = new javax.swing.GroupLayout(middlepanel);
        middlepanel.setLayout(middlepanelLayout);
        middlepanelLayout.setHorizontalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(changepasswordpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(manageuserpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewlogpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(742, Short.MAX_VALUE))
        );
        middlepanelLayout.setVerticalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(manageuserpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(changepasswordpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewlogpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(50, Short.MAX_VALUE))
        );

        add(middlepanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed

    }//GEN-LAST:event_addmenuitemActionPerformed

    private void editmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmenuitemActionPerformed

    }//GEN-LAST:event_editmenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed

    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void refreshmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshmenuitemActionPerformed

    }//GEN-LAST:event_refreshmenuitemActionPerformed

    private void manageuserlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserlabMouseEntered
        manageuserlab.setForeground(Color.darkGray);
    }//GEN-LAST:event_manageuserlabMouseEntered

    private void manageuserlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserlabMouseExited
        manageuserlab.setForeground(Color.GRAY);

    }//GEN-LAST:event_manageuserlabMouseExited

    private void viewloglabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewloglabMouseEntered
        viewloglab.setForeground(Color.darkGray);
    }//GEN-LAST:event_viewloglabMouseEntered

    private void viewloglabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewloglabMouseExited
        manageuserlab.setForeground(Color.GRAY);
    }//GEN-LAST:event_viewloglabMouseExited

    private void manageuserlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_manageuserlabMouseReleased
        if (show == true) {
            ViewUserFrame viewuserframe = new ViewUserFrame();
            viewuserframe.pack();
            viewuserframe.setLocationRelativeTo(null);
            viewuserframe.setVisible(true);
        }
        show = false;

    }//GEN-LAST:event_manageuserlabMouseReleased

    private void changepasswordlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepasswordlabMouseEntered
        changepasswordlab.setForeground(Color.darkGray);
    }//GEN-LAST:event_changepasswordlabMouseEntered

    private void changepasswordlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepasswordlabMouseExited
        changepasswordlab.setForeground(Color.GRAY);
    }//GEN-LAST:event_changepasswordlabMouseExited

    private void changepasswordlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changepasswordlabMouseReleased
        if (show == true) {
            ChangePasswordFrame cpf = new ChangePasswordFrame();
            cpf.setLocationRelativeTo(null);
            cpf.pack();
            cpf.setVisible(true);
        }
        show = false;

    }//GEN-LAST:event_changepasswordlabMouseReleased

    private void viewloglabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewloglabMouseReleased
        // TODO add your handling code here:
        viewLog();
    }//GEN-LAST:event_viewloglabMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JLabel changepasswordiconlab;
    private javax.swing.JLabel changepasswordlab;
    private javax.swing.JPanel changepasswordpanel;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem editmenuitem;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JLabel manageusericonlab;
    private javax.swing.JLabel manageuserlab;
    private javax.swing.JPanel manageuserpanel;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JPopupMenu popupmenu;
    private javax.swing.JMenuItem refreshmenuitem;
    private javax.swing.JLabel selectedlab;
    private javax.swing.JPanel upperpanel;
    private javax.swing.JLabel viewlogiconlab;
    private javax.swing.JLabel viewloglab;
    private javax.swing.JPanel viewlogpanel;
    // End of variables declaration//GEN-END:variables
}
