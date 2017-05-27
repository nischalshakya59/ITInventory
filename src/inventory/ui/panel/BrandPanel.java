package inventory.ui.panel;

import com.sun.glass.events.KeyEvent;
import inventory.backup.BrandBck;
import inventory.dao.BrandDAO;
import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import inventory.dto.BrandDTO;
import inventory.restore.BrandRestore;
import inventory.ui.JFileChooserOpen;
import inventory.ui.JFileChooserSave;
import inventory.ui.LoginFrame;
import inventory.ui.frame.BrandFrame;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class BrandPanel extends javax.swing.JPanel {

    public BrandPanel() {
        initComponents();
        reset();
    }

    public static boolean show = true;
    BrandDAO bdao = new BrandDAO();
    BrandDTO bdto = new BrandDTO();
    BrandFrame bf = new BrandFrame();
    Connection con;

    public void visible() {
        show = true;
    }

    public void displayBrandFrame() {
        if (show == true) {
            BrandFrame bf = new BrandFrame();
            bf.setLocationRelativeTo(null);
            bf.pack();
            bf.setVisible(true);
        }
        show = false;
    }

    public String searchComboBox() {
        String search = String.valueOf(searchcombobox.getSelectedItem());
        return search;
    }

    public void countRows() {
        noofrecordslab.setText("No of Records :- " + brandtbl.getRowCount());
    }

    public void reset() {
        searchcombobox.setSelectedItem("ID");
        searchtxtfield.setText("");
        quicksearchtxtfield.setText("");
        loadDatas();
        countRows();
        if (LoginFrame.getusertype().equalsIgnoreCase("user")) {
            savebtn.setEnabled(false);
            openbtn.setEnabled(false);
            insertbtn.setEnabled(false);
            updatebtn.setEnabled(false);
            deletebtn.setEnabled(false);
            addmenuitem.setEnabled(false);
            updatemenuitem.setEnabled(false);
            deletemenuitem.setEnabled(false);
            importfromexcelmenuitem.setEnabled(false);
            exporttoexcelmenuitem.setEnabled(false);
        }
    }

    public void updateValue() {
        bdto.setBrandid((int) brandtbl.getValueAt(brandtbl.getSelectedRow(), 0));
        bdto.setBrandentrydate((String) brandtbl.getValueAt(brandtbl.getSelectedRow(), 1));
        bdto.setBrandname((String) brandtbl.getValueAt(brandtbl.getSelectedRow(), 2));
        System.out.println((int) brandtbl.getValueAt(brandtbl.getSelectedRow(), 0));
        System.out.println((String) brandtbl.getValueAt(brandtbl.getSelectedRow(), 1));
        System.out.println((String) brandtbl.getValueAt(brandtbl.getSelectedRow(), 2));
        bf.update(bdto);
    }

    public void deleteUsingArray() {
        try {
            con = new DBConnection().getConnection();
            int[] rows = brandtbl.getSelectedRows();

            if (rows.length != 0) { // return the no of selected rows in contacttbl
                int clicked = JOptionPane.showConfirmDialog(null, "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String catid = Integer.toString((int) brandtbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{catid};
                        bdao.deleteRows(strArray);
                    }
                }
            }
            loadDatas();
            countRows();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void searchData() {
        if (searchtxtfield.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Search Field Is Empty?", "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        } else {
            loadDatasSearch();
        }
    }

    public void loadDatas() {
        try {

            for (int c = 0; c < brandtbl.getColumnCount(); c++) {
                Class<?> col_class = brandtbl.getColumnClass(c);
                brandtbl.setDefaultEditor(col_class, null);
            }
            brandtbl.setModel(bdao.buildTableModel(bdao.getQueryResult()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatasSearch() {
        if (searchComboBox().equals("")) {
            JOptionPane.showMessageDialog(null, "Search field is empty", "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                for (int c = 0; c < brandtbl.getColumnCount(); c++) {
                    Class<?> col_class = brandtbl.getColumnClass(c);
                    brandtbl.setDefaultEditor(col_class, null);
                }
                if (searchComboBox().equalsIgnoreCase("id")) {
                    brandtbl.setModel(bdao.buildTableModel(bdao.search(searchtxtfield.getText(), null, null)));
                } else if (searchComboBox().equalsIgnoreCase("name")) {
                    brandtbl.setModel(bdao.buildTableModel(bdao.search(null, searchtxtfield.getText(), null)));
                } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                    brandtbl.setModel(bdao.buildTableModel(bdao.search(null, null, searchtxtfield.getText())));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        }

    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < brandtbl.getColumnCount(); c++) {
                Class<?> col_class = brandtbl.getColumnClass(c);
                brandtbl.setDefaultEditor(col_class, null);
            }
            brandtbl.setModel(bdao.buildTableModel(bdao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            brandtbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void backup() {
        BrandBck catbck = new BrandBck();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserSave jfcs = new JFileChooserSave(brd);
        if (brandtbl.getRowCount() != 0) {
            if (searchtxtfield.getText().equals("")) {
                catbck.brandtblBck(brd);
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                catbck.brandtblbckDate(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("name")) {
                catbck.brandtblbckBrandname(brd, searchtxtfield.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Record To Backup", "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        countRows();
    }

    public void restore() {
        BrandRestore dbrest = new BrandRestore();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserOpen jfco = new JFileChooserOpen();
        jfco.open(brd);
        dbrest.brandtblRestore(brd);
        loadDatas();
        countRows();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupmenu = new javax.swing.JPopupMenu();
        addmenuitem = new javax.swing.JMenuItem();
        updatemenuitem = new javax.swing.JMenuItem();
        deletemenuitem = new javax.swing.JMenuItem();
        refreshmenuitem = new javax.swing.JMenuItem();
        seperator1 = new javax.swing.JPopupMenu.Separator();
        exporttoexcelmenuitem = new javax.swing.JMenuItem();
        importfromexcelmenuitem = new javax.swing.JMenuItem();
        upperpanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        searchusinglab = new javax.swing.JLabel();
        searchcombobox = new javax.swing.JComboBox();
        searchtxtfield = new javax.swing.JTextField();
        refreshbtn = new javax.swing.JButton();
        quicksearchlab = new javax.swing.JLabel();
        quicksearchtxtfield = new javax.swing.JTextField();
        lowepanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        insertbtn = new javax.swing.JButton();
        openbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        noofrecordslab = new javax.swing.JLabel();
        middlepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        brandtbl = new javax.swing.JTable();

        popupmenu.setPreferredSize(new java.awt.Dimension(145, 180));

        addmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/add.png"))); // NOI18N
        addmenuitem.setText("Add");
        addmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(addmenuitem);

        updatemenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/pencil.png"))); // NOI18N
        updatemenuitem.setText("Update");
        updatemenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatemenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(updatemenuitem);

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
        popupmenu.add(refreshmenuitem);
        popupmenu.add(seperator1);

        exporttoexcelmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/exporttoexcel.png"))); // NOI18N
        exporttoexcelmenuitem.setText("Export To Excel");
        popupmenu.add(exporttoexcelmenuitem);

        importfromexcelmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/importfromexcel.png"))); // NOI18N
        importfromexcelmenuitem.setText("Import From Excel");
        popupmenu.add(importfromexcelmenuitem);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 65));

        searchusinglab.setText("Search Using ");

        searchcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Name", "Entry Date" }));
        searchcombobox.setBorder(null);

        searchtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        searchtxtfield.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        searchtxtfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchtxtfieldActionPerformed(evt);
            }
        });
        searchtxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchtxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchtxtfieldKeyReleased(evt);
            }
        });

        refreshbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/refresh.png"))); // NOI18N
        refreshbtn.setText("Refresh");
        refreshbtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true));
        refreshbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshbtnActionPerformed(evt);
            }
        });

        quicksearchlab.setText("Quick Search ");

        quicksearchtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
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

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchusinglab, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchusinglab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(searchcombobox)
                                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(searchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
        insertbtn.setText("Add Brand");
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
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lowepanelLayout.createSequentialGroup()
                        .addGroup(lowepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(lowepanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(890, 420));

        brandtbl.setAutoCreateRowSorter(true);
        brandtbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        brandtbl.setModel(new javax.swing.table.DefaultTableModel(
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
        brandtbl.setComponentPopupMenu(popupmenu);
        brandtbl.setInheritsPopupMenu(true);
        brandtbl.setRowHeight(20);
        brandtbl.setRowMargin(4);
        brandtbl.setSelectionBackground(new java.awt.Color(102, 153, 255));
        brandtbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                brandtblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(brandtbl);

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
        );

        add(middlepanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void quicksearchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quicksearchtxtfieldActionPerformed

    private void insertbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbtnActionPerformed
        displayBrandFrame();
    }//GEN-LAST:event_insertbtnActionPerformed

    private void searchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtfieldActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        updateValue();
        reset();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
        reset();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        backup();
        reset();
    }//GEN-LAST:event_savebtnActionPerformed

    private void openbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbtnActionPerformed
        // TODO add your handling code here:
        restore();
        reset();
    }//GEN-LAST:event_openbtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        // TODO add your handling code here:
        displayBrandFrame();
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void updatemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatemenuitemActionPerformed
        // TODO add your handling code here:
        updateValue();
        reset();
    }//GEN-LAST:event_updatemenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed
        // TODO add your handling code here:
        deleteUsingArray();
        reset();
    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        // TODO add your handling code here:

        int keycode = evt.getKeyCode();
        if (evt.getSource() == brandtbl && keycode == KeyEvent.VK_ENTER && searchtxtfield.getText().length() != 0) {
            loadDataQuickSearch();
        }
    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    private void quicksearchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyPressed
        // TODO add your handling code here:
        int keycode = evt.getKeyCode();
        if (evt.getSource() == quicksearchtxtfield && keycode == KeyEvent.VK_BACKSPACE && quicksearchtxtfield.getText().length() == 0) {
            reset();
        } else {
            loadDataQuickSearch();

        }
    }//GEN-LAST:event_quicksearchtxtfieldKeyPressed

    private void searchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == KeyEvent.VK_ENTER) {
            searchData();
            countRows();
        }

    }//GEN-LAST:event_searchtxtfieldKeyPressed

    private void searchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == KeyEvent.VK_BACKSPACE && searchtxtfield.getText().length() == 0) {
            loadDatas();
            countRows();
        }

    }//GEN-LAST:event_searchtxtfieldKeyReleased

    private void brandtblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_brandtblKeyPressed
        // TODO add your handling code here:
        int keycode = evt.getKeyCode();
        if (evt.getSource() == brandtbl && keycode == KeyEvent.VK_DELETE){
            deleteUsingArray();
            reset();
        }
    }//GEN-LAST:event_brandtblKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JTable brandtbl;
    private javax.swing.JButton deletebtn;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem exporttoexcelmenuitem;
    private javax.swing.JMenuItem importfromexcelmenuitem;
    private javax.swing.JButton insertbtn;
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
    private javax.swing.JButton savebtn;
    private javax.swing.JComboBox searchcombobox;
    private javax.swing.JTextField searchtxtfield;
    private javax.swing.JLabel searchusinglab;
    private javax.swing.JPopupMenu.Separator seperator1;
    private javax.swing.JButton updatebtn;
    private javax.swing.JMenuItem updatemenuitem;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
