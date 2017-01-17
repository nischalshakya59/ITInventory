package inventory.ui.frame;

import inventory.dao.SupplierDAO;
import inventory.dto.SupplierDTO;
import inventory.other.LogWriting;
import inventory.ui.ITDashboard;
import inventory.ui.panel.SupplierPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SupplierFrame extends javax.swing.JFrame {

    public SupplierFrame() {
        initComponents();
        this.setResizable(false);
        this.setTitle("Supplier");
        setDate();
        setSupplierId();
        this.setLocationRelativeTo(null);
        supplieridtxtfield.setEditable(false);
    }

    SupplierDTO supplierdto = new SupplierDTO();

    SupplierDAO supplierdao = new SupplierDAO();

    public void check() {
        if (suppliernametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the supplier name", "Inventory Mangement System",
                      +JOptionPane.INFORMATION_MESSAGE);
        }
        if (supplieridtxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the supplier id", "Inventory Management System",
                      +JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JTextField)supplierentrydate.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the valid date", "Inventory Management System",
                      +JOptionPane.INFORMATION_MESSAGE);
        }
        if (supplieraddresstxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the supplier address", "Inventory Management System",
                      +JOptionPane.INFORMATION_MESSAGE);
        }

        if (suppliercontacttxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the supplier contact", "Inventory Management System",
                      +JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void setSupplierId() {
        int supplierid = supplierdao.supplierId() + 1;
        supplieridtxtfield.setText(String.valueOf(supplierid));
    }

    public void setDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMMM dd yyyy");
        supplierentrydate.setDate(dNow);
    }

    public void reset() {
        suppliernametxtfield.setText("");
        supplieraddresstxtfield.setText("");
        suppliercontacttxtfield.setText("");
        supplieremailtxtfield.setText("");
        supplierdescriptiontxtarea.setText("");
    }

    public void insert() {
        String supplieraddress = supplieraddresstxtfield.getText().substring(0, 1).toUpperCase() + supplieraddresstxtfield.getText().substring(
                  1);
        supplierdto.setSupplierentrydate(((JTextField)supplierentrydate.getDateEditor().getUiComponent()).getText());
        String suppliername = suppliernametxtfield.getText().substring(0, 1).toUpperCase() + suppliernametxtfield.getText().substring(
                  1);
        supplierdto.setSuppliername(suppliername);
        supplierdto.setSupplierid(Integer.valueOf(supplieridtxtfield.getText()));
        supplierdto.setSupplieremailaddress(supplieremailtxtfield.getText());
        supplierdto.setSupplieraddress(supplieraddress);
        supplierdto.setSuppliercontactno(suppliercontacttxtfield.getText());
        supplierdto.setSupplierdescription(supplierdescriptiontxtarea.getText());
        supplierdto.setSupplieremailaddress(supplieremailtxtfield.getText());
        if (supplierdao.insertSupplier(supplierdto) == true) {
            setSupplierId();

            reset();
        }
    }

    public void update(SupplierDTO supplierdto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        supplieridtxtfield.setText(String.valueOf(supplierdto.getSupplierid()));
        suppliernametxtfield.setText(supplierdto.getSuppliername());
        ((JTextField)supplierentrydate.getDateEditor().getUiComponent()).setText(supplierdto.getSupplierentrydate());
        supplieraddresstxtfield.setText(supplierdto.getSupplieraddress());
        suppliercontacttxtfield.setText(supplierdto.getSuppliercontactno());
        supplieremailtxtfield.setText(supplierdto.getSupplieremailaddress());
        supplierdescriptiontxtarea.setText(supplierdto.getSupplierdescription());
    }

    public void updateFinal() {
        String suppliername = suppliernametxtfield.getText().substring(0, 1).toUpperCase() + suppliernametxtfield.getText().substring(
                  1);
        supplierdto.setSuppliername(suppliername);
        supplierdto.setSupplierid(Integer.parseInt(supplieridtxtfield.getText()));
        supplierdto.setSupplierentrydate(((JTextField)supplierentrydate.getDateEditor().getUiComponent()).getText());
        supplierdto.setSuppliername(suppliername);
        supplierdto.setSuppliercontactno(suppliercontacttxtfield.getText());
        supplierdto.setSupplieraddress(supplieraddresstxtfield.getText());
        supplierdto.setSupplieremailaddress(supplieremailtxtfield.getText());
        supplierdto.setSupplierdescription(supplierdescriptiontxtarea.getText());
        if (supplierdao.updateSupplier(supplierdto)) {
            setSupplierId();
            reset();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        supplieridlab = new javax.swing.JLabel();
        supplieridtxtfield = new javax.swing.JTextField();
        suppliername = new javax.swing.JLabel();
        suppliernametxtfield = new javax.swing.JTextField();
        supplierdescriptionlab = new javax.swing.JLabel();
        supplierentrydatelab = new javax.swing.JLabel();
        supplierentrydate = new com.toedter.calendar.JDateChooser();
        suppliercontactlab = new javax.swing.JLabel();
        suppliercontacttxtfield = new javax.swing.JTextField();
        supplieraddresslab = new javax.swing.JLabel();
        supplieraddresstxtfield = new javax.swing.JTextField();
        supplieremaillab = new javax.swing.JLabel();
        supplieremailtxtfield = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        supplierdescriptiontxtarea = new javax.swing.JTextArea();
        lowerpanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        savebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        supplieridlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplieridlab.setText(" Id :-");

        supplieridtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        suppliername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        suppliername.setText("Name :-");

        suppliernametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        supplierdescriptionlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplierdescriptionlab.setText("Description :-");

        supplierentrydatelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplierentrydatelab.setText("Entry Date :-");

        supplierentrydate.setDateFormatString("MMM d yyyy");

        suppliercontactlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        suppliercontactlab.setText("Contact :-");

        suppliercontacttxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        suppliercontacttxtfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliercontacttxtfieldActionPerformed(evt);
            }
        });

        supplieraddresslab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplieraddresslab.setText("Address :-");

        supplieraddresstxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        supplieremaillab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        supplieremaillab.setText("Email :-");

        supplieremailtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        supplierdescriptiontxtarea.setColumns(20);
        supplierdescriptiontxtarea.setRows(5);
        jScrollPane1.setViewportView(supplierdescriptiontxtarea);

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(suppliernametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(suppliercontactlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(suppliercontacttxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(supplieridlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(supplieridtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(supplierentrydatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(supplierentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(supplierdescriptionlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(supplieraddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(supplieraddresstxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(supplieremaillab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(supplieremailtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(supplieridlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(supplieridtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(supplierentrydatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(supplierentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suppliername, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliernametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliercontactlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliercontacttxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplieraddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplieraddresstxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplieremaillab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplieremailtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(supplierdescriptionlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/save.png"))); // NOI18N
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(215, 215, 215))
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(upperpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lowerpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lowerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        new SupplierPanel().visible();
    }//GEN-LAST:event_formWindowClosed

    private void suppliercontacttxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliercontacttxtfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suppliercontacttxtfieldActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed

        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
        } else {
            check();
            updateFinal();
        }

    }//GEN-LAST:event_savebtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SupplierFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel supplieraddresslab;
    private javax.swing.JTextField supplieraddresstxtfield;
    private javax.swing.JLabel suppliercontactlab;
    private javax.swing.JTextField suppliercontacttxtfield;
    private javax.swing.JLabel supplierdescriptionlab;
    private javax.swing.JTextArea supplierdescriptiontxtarea;
    private javax.swing.JLabel supplieremaillab;
    private javax.swing.JTextField supplieremailtxtfield;
    private com.toedter.calendar.JDateChooser supplierentrydate;
    private javax.swing.JLabel supplierentrydatelab;
    private javax.swing.JLabel supplieridlab;
    private javax.swing.JTextField supplieridtxtfield;
    private javax.swing.JLabel suppliername;
    private javax.swing.JTextField suppliernametxtfield;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
