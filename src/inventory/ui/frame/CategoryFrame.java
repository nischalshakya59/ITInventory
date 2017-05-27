package inventory.ui.frame;

import inventory.dao.CategoryDAO;
import inventory.dto.CategoryDTO;
import inventory.ui.panel.CategoryPanel;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CategoryFrame extends javax.swing.JFrame {

    public CategoryFrame() {
        initComponents();
        this.setTitle("Category");
        this.setLocationRelativeTo(null);
        setcatId();
        catidtxtfield.setEditable(false);
        setDate();
    }

    CategoryDAO cdao = new CategoryDAO();
    CategoryDTO cdto = new CategoryDTO();

    @SuppressWarnings("unchecked")

    public void setcatId() {
        int catid = cdao.catId() + 1;
        catidtxtfield.setText(String.valueOf(catid));
    }

    public void setDate() {
        Date dNow = new Date();
        catentrydate.setDate(dNow);
    }

    public void check() {
        if (catnametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the cat name", "Inventory Mangement System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (catidtxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the cat id", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (((JTextField) catentrydate.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the valid date", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void reset() {
        catnametxtfield.setText("");
    }

    public void insert() {
        cdto.setCatentrydate(((JTextField) catentrydate.getDateEditor().getUiComponent()).getText());
        String categoryname = catnametxtfield.getText().substring(0, 1).toUpperCase() + catnametxtfield.getText().substring(1);
        cdto.setCatname(categoryname);
        cdto.setCatid(Integer.valueOf(catidtxtfield.getText()));
        cdao.insertCategory(cdto);
        reset();
    }

    public void update(CategoryDTO cdto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        catidtxtfield.setText(String.valueOf(cdto.getCatid()));
        catnametxtfield.setText(cdto.getCatname());
        ((JTextField) catentrydate.getDateEditor().getUiComponent()).setText(cdto.getCatentrydate());
    }

    public void updateFinal() {
        String catname = catnametxtfield.getText().substring(0, 1).toUpperCase() + catnametxtfield.getText().substring(1);
        cdto.setCatname(catname);
        cdto.setCatid(Integer.parseInt(catidtxtfield.getText()));
        cdto.setCatentrydate(((JTextField) catentrydate.getDateEditor().getUiComponent()).getText());
        cdao.updateCategory(cdto);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        catidtxtfield = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        catnametxtfield = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        catentrydate = new com.toedter.calendar.JDateChooser();
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

        catidtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Name :-");

        catnametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        catnametxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                catnametxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                catnametxtfieldKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Entry Date :-");

        catentrydate.setDateFormatString("MMM d yyyy");

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
                        .addComponent(catnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(catidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(catentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(catnametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
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
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addGroup(lowerpanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new CategoryPanel().visible();
    }//GEN-LAST:event_formWindowClosed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed

        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            reset();
            setcatId();

        } else if (savebtn.getText().equalsIgnoreCase("update")) {
            check();
            updateFinal();
            setcatId();
            reset();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void catnametxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catnametxtfieldKeyReleased

    }//GEN-LAST:event_catnametxtfieldKeyReleased

    private void catnametxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_catnametxtfieldKeyPressed
        // TODO add your handling code here:
        int keycode = evt.getKeyCode();
        if (evt.getSource() == catnametxtfield && keycode == KeyEvent.VK_ENTER && savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            reset();
            setcatId();
        } else if (evt.getSource() == catnametxtfield && keycode == KeyEvent.VK_ENTER && savebtn.getText().equalsIgnoreCase("update")) {
            check();
            updateFinal();
            reset();
            setcatId();
        }
    }//GEN-LAST:event_catnametxtfieldKeyPressed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser catentrydate;
    private javax.swing.JTextField catidtxtfield;
    private javax.swing.JTextField catnametxtfield;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JButton savebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
