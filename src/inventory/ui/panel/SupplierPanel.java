package inventory.ui.panel;

import inventory.backup.SupplierBck;
import inventory.dao.SupplierDAO;
import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import inventory.dto.SupplierDTO;
import inventory.restore.SupplierRestore;
import inventory.ui.JFileChooserOpen;
import inventory.ui.JFileChooserSave;
import inventory.ui.LoginFrame;
import inventory.ui.frame.SupplierFrame;
import java.sql.Connection;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;

public class SupplierPanel extends javax.swing.JPanel {

    public SupplierPanel() {
        initComponents();
        loadDatas();
        countRows();
        reset();
    }
    public static boolean show = true;

    public void visible() {
        show = true;
    }

    SupplierDTO supplierdto = new SupplierDTO();
    SupplierDAO supplierdao = new SupplierDAO();
    SupplierFrame sf = new SupplierFrame();
    Connection con;

    public void countRows() {
        noofrecordslab.setText("No of Records :- " + suppliertbl.getRowCount());
    }

    public String searchComboBox() {
        String search = String.valueOf(searchcombobox.getSelectedItem());
        return search;
    }

    public void reset() {
        loadDatas();
        countRows();
        searchtxtfield.setText("");
        quicksearchtxtfield.setText("");
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
        if (suppliertbl.getSelectedRowCount() < 2) {
            if (suppliertbl.getSelectedRowCount() != 0) {
                supplierdto.setSupplierid((int) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 0));
                supplierdto.setSupplierentrydate((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 1));
                supplierdto.setSuppliername((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 2));
                supplierdto.setSuppliercontactno((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 3));
                supplierdto.setSupplieraddress((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 4));
                supplierdto.setSupplieremailaddress((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 5));
                supplierdto.setSupplierdescription((String) suppliertbl.getValueAt(suppliertbl.getSelectedRow(), 6));
                sf.update(supplierdto);
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
            int[] rows = suppliertbl.getSelectedRows();

            if (rows.length != 0) {
                int clicked = JOptionPane.showConfirmDialog(null, "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String supplierid = Integer.toString((int) suppliertbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{supplierid};
                        supplierdao.deleteRows(strArray);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Select rows to delete", "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            reset();
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

            for (int c = 0; c < suppliertbl.getColumnCount(); c++) {
                Class<?> col_class = suppliertbl.getColumnClass(c);
                suppliertbl.setDefaultEditor(col_class, null);
            }
            suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.getQueryResult()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatasSearch() {
        try {
            for (int c = 0; c < suppliertbl.getColumnCount(); c++) {
                Class<?> col_class = suppliertbl.getColumnClass(c);
                suppliertbl.setDefaultEditor(col_class, null);
            }
            if (searchComboBox().equalsIgnoreCase("id")) {

                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(searchtxtfield.getText(), null, null, null, null, null)));
            } else if (searchComboBox().equalsIgnoreCase("name")) {
                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(null, searchtxtfield.getText(), null, null, null, null)));
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(null, null, searchtxtfield.getText(), null, null, null)));
            } else if (searchComboBox().equalsIgnoreCase("contact no")) {
                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(null, null, null, searchtxtfield.getText(), null, null)));
            } else if (searchComboBox().equalsIgnoreCase("address")) {
                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(null, null, null, null, searchtxtfield.getText(), null)));
            } else if (searchComboBox().equalsIgnoreCase("e-mail")) {
                suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.search(null, null, null, null, null, searchtxtfield.getText())));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < suppliertbl.getColumnCount(); c++) {
                Class<?> col_class = suppliertbl.getColumnClass(c);
                suppliertbl.setDefaultEditor(col_class, null);
            }
            suppliertbl.setModel(supplierdao.buildTableModel(supplierdao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            suppliertbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void backup() {
        SupplierBck supplierbck = new SupplierBck();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserSave jfcs = new JFileChooserSave(brd);
        if (suppliertbl.getRowCount() != 0) {
            if (searchtxtfield.getText().equals("")) {
                supplierbck.suppliertblBck(brd);
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                supplierbck.suppliertblbckDate(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("name")) {
                supplierbck.suppliertblbckName(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("contact no")) {
                supplierbck.suppliertblbckContactno(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("address")) {
                supplierbck.suppliertblbckAddress(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("e-mail")) {
                supplierbck.suppliertblbckEmail(brd, searchtxtfield.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Record To Backup", "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        countRows();
    }

    public void restore() {
        SupplierRestore supplierrestore = new SupplierRestore();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserOpen jfco = new JFileChooserOpen();
        jfco.open(brd);
        supplierrestore.suppliertblRestore(brd);
        reset();
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
        lowerpanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        deletebtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        insertbtn = new javax.swing.JButton();
        openbtn = new javax.swing.JButton();
        savebtn = new javax.swing.JButton();
        noofrecordslab = new javax.swing.JLabel();
        middlepanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        suppliertbl = new javax.swing.JTable();

        popupmenu.setPreferredSize(new java.awt.Dimension(145, 170));

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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 65));

        searchusinglab.setText("Search Using ");

        searchcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Entry Date", "Name", "Contact No", "Address", "E-mail" }));
        searchcombobox.setBorder(null);
        searchcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcomboboxActionPerformed(evt);
            }
        });

        searchtxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        quicksearchtxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        lowerpanel.setPreferredSize(new java.awt.Dimension(890, 65));

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
        insertbtn.setText("Add Supplier");
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

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 144, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertbtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deletebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deletebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(lowerpanelLayout.createSequentialGroup()
                        .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(890, 420));

        suppliertbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        suppliertbl.setModel(new javax.swing.table.DefaultTableModel(
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
        suppliertbl.setComponentPopupMenu(popupmenu);
        suppliertbl.setRowHeight(20);
        suppliertbl.setRowMargin(4);
        suppliertbl.setSelectionBackground(new java.awt.Color(102, 153, 255));
        suppliertbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                suppliertblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(suppliertbl);

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
        if (show == true) {
            SupplierFrame sf = new SupplierFrame();
            sf.setLocationRelativeTo(null);
            sf.pack();
            sf.setVisible(true);
        }
        show = false;

    }//GEN-LAST:event_insertbtnActionPerformed

    private void searchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchtxtfieldActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        updateValue();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
        reset();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        if (show == true) {
            SupplierFrame sf = new SupplierFrame();
            sf.setLocationRelativeTo(null);
            sf.pack();
            sf.setVisible(true);
        }
        show = false;
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void updatemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatemenuitemActionPerformed
        updateValue();
        reset();
    }//GEN-LAST:event_updatemenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed
        deleteUsingArray();
        reset();
    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        backup();
    }//GEN-LAST:event_savebtnActionPerformed

    private void openbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbtnActionPerformed
        restore();
    }//GEN-LAST:event_openbtnActionPerformed

    private void searchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == com.sun.glass.events.KeyEvent.VK_ENTER) {
            searchData();
            countRows();
        }

    }//GEN-LAST:event_searchtxtfieldKeyPressed

    private void quicksearchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyPressed

    }//GEN-LAST:event_quicksearchtxtfieldKeyPressed

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == quicksearchtxtfield && keycode == com.sun.glass.events.KeyEvent.VK_BACKSPACE && quicksearchtxtfield.getText().length() == 0) {
            reset();
        } else {
            loadDataQuickSearch();
        }
    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    private void searchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == com.sun.glass.events.KeyEvent.VK_BACKSPACE && searchtxtfield.getText().length() == 0) {
            reset();
        }

    }//GEN-LAST:event_searchtxtfieldKeyReleased

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void searchcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcomboboxActionPerformed
        if (searchComboBox().equalsIgnoreCase("id")) {
            savebtn.setEnabled(false);
            exporttoexcelmenuitem.setEnabled(false);
        } else {
            savebtn.setEnabled(true);
            exporttoexcelmenuitem.setEnabled(true);
        }
    }//GEN-LAST:event_searchcomboboxActionPerformed

    private void exporttoexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporttoexcelmenuitemActionPerformed
        backup();
    }//GEN-LAST:event_exporttoexcelmenuitemActionPerformed

    private void importfromexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importfromexcelmenuitemActionPerformed
        restore();
    }//GEN-LAST:event_importfromexcelmenuitemActionPerformed

    private void suppliertblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_suppliertblKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == suppliertbl && keycode == com.sun.glass.events.KeyEvent.VK_DELETE) {
            deleteUsingArray();
            countRows();
        }
    }//GEN-LAST:event_suppliertblKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JButton deletebtn;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem exporttoexcelmenuitem;
    private javax.swing.JMenuItem importfromexcelmenuitem;
    private javax.swing.JButton insertbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowerpanel;
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
    private javax.swing.JTable suppliertbl;
    private javax.swing.JButton updatebtn;
    private javax.swing.JMenuItem updatemenuitem;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
