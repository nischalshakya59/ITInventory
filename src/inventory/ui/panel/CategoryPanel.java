package inventory.ui.panel;

import inventory.backup.CategoryBck;
import inventory.dao.CategoryDAO;
import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import inventory.dto.CategoryDTO;
import inventory.restore.CategoryRestore;
import inventory.ui.JFileChooserOpen;
import inventory.ui.JFileChooserSave;
import inventory.ui.LoginFrame;
import inventory.ui.frame.CategoryFrame;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JOptionPane;

public class CategoryPanel extends javax.swing.JPanel {

    public CategoryPanel() {
        initComponents();
        countRows();
        loadDatas();
        reset();
    }

    public static boolean show = true;
    CategoryDAO cdao = new CategoryDAO();
    CategoryDTO cdto = new CategoryDTO();
    CategoryFrame cf = new CategoryFrame();
    Connection con;

    public void visible() {
        show = true;
    }

    public void displayCategoryFrame() {
        if (show == true) {
            CategoryFrame cf = new CategoryFrame();
            cf.setLocationRelativeTo(null);
            cf.pack();
            cf.setVisible(true);
        }
        show = false;
    }

    public void countRows() {
        noofrecordslab.setText("No of Records :- " + categorytbl.getRowCount());
    }

    public String searchComboBox() {
        String search = String.valueOf(searchcombobox.getSelectedItem());
        return search;
    }

    public void reset() {
        loadDatas();
        countRows();
        searchcombobox.setSelectedItem("Id");
        searchtxtfield.setText("");
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
        if (categorytbl.getSelectedRowCount() < 2) {
            if (categorytbl.getSelectedRowCount() != 0) {
                cdto.setCatid((int) categorytbl.getValueAt(categorytbl.getSelectedRow(), 0));
                cdto.setCatentrydate((String) categorytbl.getValueAt(categorytbl.getSelectedRow(), 1));
                cdto.setCatname((String) categorytbl.getValueAt(categorytbl.getSelectedRow(), 2));

                System.out.println((int) categorytbl.getValueAt(categorytbl.getSelectedRow(), 0));
                System.out.println((String) categorytbl.getValueAt(categorytbl.getSelectedRow(), 1));
                System.out.println((String) categorytbl.getValueAt(categorytbl.getSelectedRow(), 2));
                cf.update(cdto);
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
            int[] rows = categorytbl.getSelectedRows();

            if (rows.length != 0) {
                int clicked = JOptionPane.showConfirmDialog(null, "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String catid = Integer.toString((int) categorytbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{catid};
                        cdao.deleteRows(strArray);
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

    public void loadDatas() {
        try {

            for (int c = 0; c < categorytbl.getColumnCount(); c++) {
                Class<?> col_class = categorytbl.getColumnClass(c);
                categorytbl.setDefaultEditor(col_class, null);
            }
            categorytbl.setModel(cdao.buildTableModel(cdao.getQueryResult()));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatasSearch() {
        try {
            for (int c = 0; c < categorytbl.getColumnCount(); c++) {
                Class<?> col_class = categorytbl.getColumnClass(c);
                categorytbl.setDefaultEditor(col_class, null);
            }
            if (searchComboBox().equalsIgnoreCase("id")) {
                categorytbl.setModel(cdao.buildTableModel(cdao.search(searchtxtfield.getText(), null, null)));
            } else if (searchComboBox().equalsIgnoreCase("name")) {
                String catname = searchtxtfield.getText().substring(0, 1).toUpperCase() + searchtxtfield.getText().substring(1);
                System.out.println(catname);
                categorytbl.setModel(cdao.buildTableModel(cdao.search(null, catname, null)));

            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                String entrydate = searchtxtfield.getText().substring(0, 1).toUpperCase() + searchtxtfield.getText().substring(1);
                categorytbl.setModel(cdao.buildTableModel(cdao.search(null, null, entrydate)));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < categorytbl.getColumnCount(); c++) {
                Class<?> col_class = categorytbl.getColumnClass(c);
                categorytbl.setDefaultEditor(col_class, null);
            }
            categorytbl.setModel(cdao.buildTableModel(cdao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            categorytbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void backup() {
        CategoryBck catbck = new CategoryBck();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserSave jfcs = new JFileChooserSave(brd);
        if (categorytbl.getRowCount() != 0) {
            if (searchtxtfield.getText().equals("")) {
                catbck.categorytblBck(brd);
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                catbck.categorytblbckDate(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("name")) {
                catbck.categorytblbckCatname(brd, searchtxtfield.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Record To Backup", "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        countRows();
    }

    public void restore() {
        CategoryRestore dbrest = new CategoryRestore();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserOpen jfco = new JFileChooserOpen();
        jfco.open(brd);
        dbrest.categorytblRestore(brd);
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
        categorytbl = new javax.swing.JTable();

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

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 65));

        searchusinglab.setText("Search Using ");

        searchcombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        searchcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Entry Date", "Name" }));
        searchcombobox.setBorder(null);

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
                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchusinglab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchcombobox)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(quicksearchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(quicksearchlab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(refreshbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
        insertbtn.setText("Add Category");
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        categorytbl.setAutoCreateRowSorter(true);
        categorytbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        categorytbl.setModel(new javax.swing.table.DefaultTableModel(
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
        categorytbl.setComponentPopupMenu(popupmenu);
        categorytbl.setInheritsPopupMenu(true);
        categorytbl.setRowHeight(20);
        categorytbl.setRowMargin(4);
        categorytbl.setSelectionBackground(new java.awt.Color(102, 153, 255));
        categorytbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                categorytblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(categorytbl);

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

    private void insertbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbtnActionPerformed
        displayCategoryFrame();
    }//GEN-LAST:event_insertbtnActionPerformed

    private void searchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtfieldActionPerformed

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
        reset();
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void searchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == KeyEvent.VK_ENTER) {
            loadDatasSearch();
            countRows();
        }
    }//GEN-LAST:event_searchtxtfieldKeyPressed

    private void searchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == KeyEvent.VK_BACK_SPACE && searchtxtfield.getText().length() == 0) {
            reset();
        }
    }//GEN-LAST:event_searchtxtfieldKeyReleased

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == KeyEvent.VK_BACK_SPACE && searchtxtfield.getText().length() == 0) {
            reset();
        } else {
            loadDataQuickSearch();
            countRows();
        }

    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    private void quicksearchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyPressed

    }//GEN-LAST:event_quicksearchtxtfieldKeyPressed

    private void categorytblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_categorytblKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == categorytbl && keycode == KeyEvent.VK_DELETE) {
            deleteUsingArray();
            reset();
        }
    }//GEN-LAST:event_categorytblKeyPressed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        backup();
        reset();
    }//GEN-LAST:event_savebtnActionPerformed

    private void openbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbtnActionPerformed
        restore();
        reset();
    }//GEN-LAST:event_openbtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        displayCategoryFrame();
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void updatemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatemenuitemActionPerformed
        updateValue();
    }//GEN-LAST:event_updatemenuitemActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JTable categorytbl;
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
