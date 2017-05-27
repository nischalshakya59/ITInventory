package inventory.ui.frame;

import inventory.dao.BrandDAO;
import inventory.dto.BrandDTO;
import inventory.ui.panel.BrandPanel;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class BrandFrame extends javax.swing.JFrame {

    BrandDTO bdto = new BrandDTO();
    BrandDAO bdao = new BrandDAO();
    //BrandPanel bp = new BrandPanel();

    public BrandFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Brand");
        brandidtxtfield.setEditable(false);
        setBrandId();
        setDate();
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }

    public void check() {
        if (brandnametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the brand name", "Inventory Mangement System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (brandidtxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the brand id", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JTextField) brandentrydate.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the valid date", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setBrandId() {
        int brandid = bdao.brandId() + 1;
        brandidtxtfield.setText(String.valueOf(brandid));
    }

    public void setDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMMM dd yyyy");
        brandentrydate.setDate(dNow);
    }

    public void insert() {
        bdto.setBrandentrydate(((JTextField) brandentrydate.getDateEditor().getUiComponent()).getText());
        String brandname = brandnametxtfield.getText().substring(0, 1).toUpperCase() + brandnametxtfield.getText().substring(1);
        bdto.setBrandname(brandname);
        bdto.setBrandid(Integer.valueOf(brandidtxtfield.getText()));
        bdao.insertBrand(bdto);
    }

    public void update(BrandDTO bdto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        brandidtxtfield.setText(String.valueOf(bdto.getBrandid()));
        brandnametxtfield.setText(bdto.getBrandname());
        ((JTextField) brandentrydate.getDateEditor().getUiComponent()).setText(bdto.getBrandentrydate());
    }

    public void updateFinal() {
        String brandname = brandnametxtfield.getText().substring(0, 1).toUpperCase() + brandnametxtfield.getText().substring(1);
        bdto.setBrandname(brandname);
        bdto.setBrandid(Integer.parseInt(brandidtxtfield.getText()));
        bdto.setBrandentrydate(((JTextField) brandentrydate.getDateEditor().getUiComponent()).getText());
        bdto.setBrandname(brandname);
        bdao.updateBrand(bdto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        brandidtxtfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        brandnametxtfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        brandentrydate = new com.toedter.calendar.JDateChooser();
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

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText(" Id :-");

        brandidtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Name :-");

        brandnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        brandnametxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                brandnametxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                brandnametxtfieldKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Entry Date :-");

        brandentrydate.setDateFormatString("MMM d yyyy");

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(brandnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/save.png"))); // NOI18N
        savebtn.setMnemonic('s');
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
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
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
            .addComponent(lowerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            setBrandId();
            brandnametxtfield.setText("");
        } else if (savebtn.getText().equalsIgnoreCase("update")) {
            check();
            updateFinal();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void brandnametxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brandnametxtfieldKeyReleased
    }//GEN-LAST:event_brandnametxtfieldKeyReleased

    private void brandnametxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brandnametxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == brandnametxtfield && keycode == KeyEvent.VK_ENTER && savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            setBrandId();
            brandnametxtfield.setText("");
        } else if (evt.getSource() == brandnametxtfield && keycode == KeyEvent.VK_ENTER && savebtn.getText().equalsIgnoreCase("update")) {
            check();
            updateFinal();
        }
    }//GEN-LAST:event_brandnametxtfieldKeyPressed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new BrandPanel().visible();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BrandFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser brandentrydate;
    private javax.swing.JTextField brandidtxtfield;
    private javax.swing.JTextField brandnametxtfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JButton savebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
