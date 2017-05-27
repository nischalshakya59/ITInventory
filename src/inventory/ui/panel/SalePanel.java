package inventory.ui.panel;

import inventory.backup.SaleBck;
import inventory.dao.SaleDAO;
import inventory.dto.BckRestoreDTO;
import inventory.dto.SaleDTO;
import inventory.restore.ProductRestore;
import inventory.restore.SaleRestore;
import inventory.ui.JFileChooserOpen;
import inventory.ui.JFileChooserSave;
import inventory.ui.LoginFrame;
import inventory.ui.frame.SaleFrame;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class SalePanel extends javax.swing.JPanel {

    public SalePanel() {
        initComponents();
        countRows();
        loadDatas();
        reset();
//        if (LoginFrame.getusertype().equalsIgnoreCase("user")) {
//            savebtn.setEnabled(false);
//            openbtn.setEnabled(false);
//            insertbtn.setEnabled(false);
//
//            deletebtn.setEnabled(false);
//        }
    }

    public static boolean show = true;

    SaleFrame sf = new SaleFrame();

    SaleDAO saledao = new SaleDAO();

    SaleDTO saledto = new SaleDTO();

    SaleFrame saleframe = new SaleFrame();

    public void countRows() {
        noofrecordslab.setText("No Of Records :- " + saletbl.getRowCount());
    }

    public void reset() {
        quicksearchtxtfield.setText("");
        loadDatas();
        countRows();
    }

    public void visible() {
        show = true;
    }

    public void displaySaleFrame() {
        if (show == true) {
            sf.setLocationRelativeTo(null);
            sf.pack();
            sf.setVisible(true);
        }
        show = false;

    }

    public void updateValue() {
        if (saletbl.getSelectedRowCount() < 2) {
            if (saletbl.getSelectedRowCount() != 0) {
                saledto.setSaleid((int)saletbl.getValueAt(saletbl.getSelectedRow(), 0));
                saledto.setSaledate((String)saletbl.getValueAt(saletbl.getSelectedRow(), 1));
                saledto.setSalecustomername((String)saletbl.getValueAt(saletbl.getSelectedRow(), 2));
                saledto.setSalecustomeraddress((String)saletbl.getValueAt(saletbl.getSelectedRow(), 3));
                saledto.setSalecustomercontactno((String)saletbl.getValueAt(saletbl.getSelectedRow(), 4));
                saledto.setSalesupplier((String)saletbl.getValueAt(saletbl.getSelectedRow(), 5));
                saledto.setSalebrand((String)saletbl.getValueAt(saletbl.getSelectedRow(), 6));
                saledto.setSaleproduct((String)saletbl.getValueAt(saletbl.getSelectedRow(), 7));
                saledto.setSaleqty((int)saletbl.getValueAt(saletbl.getSelectedRow(), 8));
                saledto.setSaleamtperpiece((Double)saletbl.getValueAt(saletbl.getSelectedRow(), 9));
                saledto.setSaletotal((Double)saletbl.getValueAt(saletbl.getSelectedRow(), 10));
                saleframe.update(saledto);
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
            int[] rows = saletbl.getSelectedRows();

            if (rows.length != 0) { // return the no of selected rows in contacttbl
                int clicked = JOptionPane.showConfirmDialog(null,
                          "Are you Sure You want to delete " + rows.length + " Record ?",
                          "Inventory Management System",
                          JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String catid = Integer.toString((int)saletbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{catid};
                        saledao.deleteRows(strArray);
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

    public void loadDatas() {
        try {

            for (int c = 0; c < saletbl.getColumnCount(); c++) {
                Class<?> col_class = saletbl.getColumnClass(c);
                saletbl.setDefaultEditor(col_class, null);
            }
            saletbl.setModel(saledao.buildTableModel(saledao.getQueryResult()));
            saletbl.getColumnModel().getColumn(5).setPreferredWidth(90);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < saletbl.getColumnCount(); c++) {
                Class<?> col_class = saletbl.getColumnClass(c);
                saletbl.setDefaultEditor(col_class, null);
            }
            saletbl.setModel(saledao.buildTableModel(saledao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            saletbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void backup() {
        SaleBck salebck = new SaleBck();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserSave jfcs = new JFileChooserSave(brd);
        salebck.saletblBck(brd);
        countRows();

    }

    public void restore() {
        SaleRestore salerestore = new SaleRestore();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserOpen jfco = new JFileChooserOpen();
        jfco.open(brd);
        salerestore.saletblRestore(brd);
        loadDatas();
        countRows();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupmenu = new javax.swing.JPopupMenu();
        addmenuitem = new javax.swing.JMenuItem();
        deletemenuitem = new javax.swing.JMenuItem();
        refreshmenuitem = new javax.swing.JMenuItem();
        seperator1 = new javax.swing.JPopupMenu.Separator();
        exporttoexcelmenuitem = new javax.swing.JMenuItem();
        importfromexcelmenuitem = new javax.swing.JMenuItem();
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
        insertbtn = new javax.swing.JButton();
        openbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        noofrecordslab = new javax.swing.JLabel();
        middlepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        saletbl = new javax.swing.JTable();

        popupmenu.setPreferredSize(new java.awt.Dimension(145, 160));

        addmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        addmenuitem.setText("Add");
        addmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(addmenuitem);

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
        popupmenu.add(seperator1);

        exporttoexcelmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/exporttoexcel.png"))); // NOI18N
        exporttoexcelmenuitem.setText("Export To Excel");
        exporttoexcelmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exporttoexcelmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(exporttoexcelmenuitem);

        importfromexcelmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/importfromexcel.png"))); // NOI18N
        importfromexcelmenuitem.setText("Import From Excel");
        importfromexcelmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importfromexcelmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(importfromexcelmenuitem);

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

        quicksearchtxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        quicksearchtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        quicksearchtxtfield.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        quicksearchtxtfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quicksearchtxtfieldActionPerformed(evt);
            }
        });
        quicksearchtxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                quicksearchtxtfieldKeyReleased(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(102, 102, 102));

        selectedlab.setBackground(new java.awt.Color(102, 102, 102));
        selectedlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectedlab.setForeground(new java.awt.Color(255, 255, 255));
        selectedlab.setText("Sale");

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

        insertbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        insertbtn.setText("Add Sales");
        insertbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertbtnActionPerformed(evt);
            }
        });

        openbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/importfromexcel.png"))); // NOI18N
        openbtn.setText("Open");
        openbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openbtnActionPerformed(evt);
            }
        });

        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/exporttoexcel.png"))); // NOI18N
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        noofrecordslab.setText("No Of Records :-");

        javax.swing.GroupLayout lowepanelLayout = new javax.swing.GroupLayout(lowepanel);
        lowepanel.setLayout(lowepanelLayout);
        lowepanelLayout.setHorizontalGroup(
            lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lowepanelLayout.setVerticalGroup(
            lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noofrecordslab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deletebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(lowepanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(890, 420));

        saletbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saletbl.setModel(new javax.swing.table.DefaultTableModel(
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
        saletbl.setComponentPopupMenu(popupmenu);
        saletbl.setRowHeight(23);
        saletbl.setRowMargin(4);
        saletbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saletblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(saletbl);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
        );

        add(middlepanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void quicksearchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldActionPerformed

    }//GEN-LAST:event_quicksearchtxtfieldActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void insertbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbtnActionPerformed
        displaySaleFrame();
    }//GEN-LAST:event_insertbtnActionPerformed

    private void openbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbtnActionPerformed
        restore();
    }//GEN-LAST:event_openbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        backup();
    }//GEN-LAST:event_savebtnActionPerformed

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        loadDataQuickSearch();
    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        displaySaleFrame();
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void refreshmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshmenuitemActionPerformed
        reset();
    }//GEN-LAST:event_refreshmenuitemActionPerformed

    private void exporttoexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporttoexcelmenuitemActionPerformed
        backup();
    }//GEN-LAST:event_exporttoexcelmenuitemActionPerformed

    private void importfromexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importfromexcelmenuitemActionPerformed
        restore();
    }//GEN-LAST:event_importfromexcelmenuitemActionPerformed

    private void saletblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saletblKeyPressed
        // TODO add your handling code here:
        int keycode = evt.getKeyCode();
        if (evt.getSource() == saletbl && keycode == KeyEvent.VK_DELETE) {
            deleteUsingArray();
            reset();
        }
    }//GEN-LAST:event_saletblKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JButton deletebtn;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem exporttoexcelmenuitem;
    private javax.swing.JMenuItem importfromexcelmenuitem;
    private javax.swing.JButton insertbtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowepanel;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JLabel noofrecordslab;
    private javax.swing.JButton openbtn;
    private javax.swing.JPopupMenu popupmenu;
    private javax.swing.JLabel quicksearchlab;
    private javax.swing.JTextField quicksearchtxtfield;
    private javax.swing.JButton refreshbtn;
    private javax.swing.JMenuItem refreshmenuitem;
    private javax.swing.JTable saletbl;
    private javax.swing.JButton savebtn;
    private javax.swing.JLabel selectedlab;
    private javax.swing.JPopupMenu.Separator seperator1;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
