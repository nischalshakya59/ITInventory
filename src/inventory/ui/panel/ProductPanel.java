package inventory.ui.panel;

import com.sun.glass.events.KeyEvent;
import inventory.backup.ProductBck;
import inventory.dao.ProductDAO;
import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import inventory.dto.ProductDTO;
import inventory.restore.ProductRestore;
import inventory.ui.JFileChooserOpen;
import inventory.ui.JFileChooserSave;
import inventory.ui.LoginFrame;
import inventory.ui.frame.ProductFrame;
import inventory.ui.frame.ProductSpecificationFrame;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ProductPanel extends javax.swing.JPanel {

    public ProductPanel() {
        initComponents();
        reset();

    }

    public static boolean show = true;

    ProductDAO productdao = new ProductDAO();

    ProductDTO productdto = new ProductDTO();

    ProductFrame pf = new ProductFrame();

    ResultSet rs;

    Connection con;

    Statement stmt;

    public void visible() {
        show = true;
    }

    public void checkUser() {

    }

    public void displayProductFrame() {
        if (show == true) {
            pf.setLocationRelativeTo(null);
            pf.pack();
            pf.setVisible(true);
        }
        show = false;
    }

    public void countRows() {
        noofrecordslab.setText("No Of Records :- " + producttbl.getRowCount());
    }

    public void reset() {
        loadDatas();
        countRows();
        if (LoginFrame.getusertype().equalsIgnoreCase("user")) {
            savebtn.setEnabled(false);
            openbtn.setEnabled(false);
            insertbtn.setEnabled(false);
            updatebtn.setEnabled(false);
            deletebtn.setEnabled(false);
            addmenuitem.setEnabled(false);
            editmenuitem.setEnabled(false);
            deletemenuitem.setEnabled(false);
            importfromexcelmenuitem.setEnabled(false);
            exporttoexcelmenuitem.setEnabled(false);
            quicksearchtxtfield.setText("");
            searchtxtfield.setText("");
        }

    }

    public void displaySpecification() {
        if (show == true) {
            ProductSpecificationFrame psf = new ProductSpecificationFrame();
            psf.pack();
            psf.setLocationRelativeTo(null);
            psf.setVisible(true);
            psf.setSpecification((String) producttbl.getValueAt(producttbl.getSelectedRow(), 11));
        }
        show = false;
    }

    public String searchComboBox() {
        String search = String.valueOf(searchcombobox.getSelectedItem());
        return search;
    }

    public void searchData() {
        if (searchtxtfield.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Search Field Is Empty?", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            loadDatasSearch();
        }
    }

    public void checkInventory() {
        int productqty = ((int) producttbl.getValueAt(producttbl.getSelectedRow(), 6));
        System.out.println(productqty);
        if (productqty < 3) {
            JOptionPane.showMessageDialog(null, "Inventory is running low. Please Update",
                    "InventoryManagementSystem", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateValue() {
        if (producttbl.getSelectedRowCount() < 2) {
            if (producttbl.getSelectedRowCount() != 0) {
                productdto.setProductid((int) producttbl.getValueAt(producttbl.getSelectedRow(), 0));
                productdto.setProductentrydate((String) producttbl.getValueAt(producttbl.getSelectedRow(), 1));
                productdto.setProductsuppliername((String) producttbl.getValueAt(producttbl.getSelectedRow(), 2));
                productdto.setProductbrand((String) producttbl.getValueAt(producttbl.getSelectedRow(), 3));
                productdto.setProductmodel((String) producttbl.getValueAt(producttbl.getSelectedRow(), 4));
                productdto.setProductcategory((String) producttbl.getValueAt(producttbl.getSelectedRow(), 5));
                productdto.setProductqty((Integer) producttbl.getValueAt(producttbl.getSelectedRow(), 6));
                productdto.setProductsellingprice((Double) producttbl.getValueAt(producttbl.getSelectedRow(), 7));
                productdto.setProductcostprice((Double) producttbl.getValueAt(producttbl.getSelectedRow(), 8));
                productdto.setProductprofit((Double) producttbl.getValueAt(producttbl.getSelectedRow(), 9));
                productdto.setProductwarranty((String) producttbl.getValueAt(producttbl.getSelectedRow(), 10));
                productdto.setProductspecification((String) producttbl.getValueAt(producttbl.getSelectedRow(), 11));
                pf.update(productdto);
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
            int[] rows = producttbl.getSelectedRows();

            if (rows.length != 0) { // return the no of selected rows in contacttbl
                int clicked = JOptionPane.showConfirmDialog(null,
                        "Are you Sure You want to delete " + rows.length + " Record ?",
                        "Inventory Management System",
                        JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    for (int i = 0; i < rows.length; i++) {
                        String catid = Integer.toString((int) producttbl.getValueAt(rows[i], 0));

                        String[] strArray = new String[]{catid};
                        productdao.deleteRows(strArray);
                    }
                }
            }
            reset();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatas() {
        try {

            for (int c = 0; c < producttbl.getColumnCount(); c++) {
                Class<?> col_class = producttbl.getColumnClass(c);
                producttbl.setDefaultEditor(col_class, null);
            }

            producttbl.setModel(productdao.buildTableModel(productdao.getQueryResult()));
            producttbl.getColumnModel().getColumn(2).setPreferredWidth(90);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDatasSearch() {
        try {
            for (int c = 0; c < producttbl.getColumnCount(); c++) {
                Class<?> col_class = producttbl.getColumnClass(c);
                producttbl.setDefaultEditor(col_class, null);
            }
            if (searchComboBox().equalsIgnoreCase("id")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductId(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("supplier name")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductSupplierName(
                        searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductEntryDate(
                        searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("model")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductModel(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("category")) {
                producttbl.setModel(productdao.buildTableModel(
                        productdao.searchProductCategory(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("quantity")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductQty(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("selling price")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchSellingPrice(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("cost price")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchCostPrice(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("profit")) {
                producttbl.setModel(
                        productdao.buildTableModel(productdao.searchProduuctProfit(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("warranty")) {
                producttbl.setModel(productdao.buildTableModel(
                        productdao.searchProductWarranty(searchtxtfield.getText())));
            } else if (searchComboBox().equalsIgnoreCase("brand")) {
                producttbl.setModel(productdao.buildTableModel(productdao.searchProductBrand(searchtxtfield.getText())));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void loadDataQuickSearch() {
        try {
            for (int c = 0; c < producttbl.getColumnCount(); c++) {
                Class<?> col_class = producttbl.getColumnClass(c);
                producttbl.setDefaultEditor(col_class, null);
            }
            producttbl.setModel(productdao.buildTableModel(productdao.quickSearch(quicksearchtxtfield.getText())));
            countRows();
            producttbl.setRowSelectionAllowed(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void backup() {
        ProductBck productbck = new ProductBck();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserSave jfcs = new JFileChooserSave(brd);
        if (producttbl.getRowCount() != 0) {
            if (searchtxtfield.getText().equals("")) {
                productbck.producttblBck(brd);
            } else if (searchComboBox().equalsIgnoreCase("entry date")) {
                productbck.producttblbckEntryDate(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("supplier name")) {
                productbck.producttblbckSupplierName(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("model")) {
                productbck.producttblbckmodel(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("brand")) {
                productbck.producttblBrand(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("category")) {
                productbck.producttblbckCategory(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("quantity")) {
                productbck.producttblbckQuantity(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("selling price")) {
                productbck.producttblbckSellingPrice(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("cost price")) {
                productbck.producttblbckCostPrice(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("profit")) {
                productbck.producttblbckProfit(brd, searchtxtfield.getText());
            } else if (searchComboBox().equalsIgnoreCase("warranty")) {
                productbck.producttblbckWarranty(brd, searchtxtfield.getText());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No Record To Backup", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        countRows();
    }

    public void restore() {
        ProductRestore productrestore = new ProductRestore();
        BckRestoreDTO brd = new BckRestoreDTO();
        JFileChooserOpen jfco = new JFileChooserOpen();
        jfco.open(brd);
        productrestore.producttblRestore(brd);
        loadDatas();
        countRows();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popupmenu = new javax.swing.JPopupMenu();
        addmenuitem = new javax.swing.JMenuItem();
        editmenuitem = new javax.swing.JMenuItem();
        deletemenuitem = new javax.swing.JMenuItem();
        refreshmenuitem = new javax.swing.JMenuItem();
        seperator1 = new javax.swing.JPopupMenu.Separator();
        exporttoexcelmenuitem = new javax.swing.JMenuItem();
        importfromexcelmenuitem = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        viewspecificationmenuitem = new javax.swing.JMenuItem();
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
        producttbl = new javax.swing.JTable();

        popupmenu.setPreferredSize(new java.awt.Dimension(145, 160));

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
        popupmenu.add(jSeparator3);

        viewspecificationmenuitem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/clipboard.png"))); // NOI18N
        viewspecificationmenuitem.setText("View Specification");
        viewspecificationmenuitem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewspecificationmenuitemActionPerformed(evt);
            }
        });
        popupmenu.add(viewspecificationmenuitem);

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        upperpanel.setPreferredSize(new java.awt.Dimension(890, 65));

        searchusinglab.setText("Search Using ");

        searchcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "ID", "Entry Date", "Supplier Name", "Brand", "Model", "Category", "Quantity", "Selling Price", "Cost Price", "Profit", "Warranty" }));
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
        insertbtn.setText("Add Product");
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
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 890, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deletebtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(insertbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(openbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(noofrecordslab, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        middlepanel.setPreferredSize(new java.awt.Dimension(890, 420));

        producttbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        producttbl.setModel(new javax.swing.table.DefaultTableModel(
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
        producttbl.setComponentPopupMenu(popupmenu);
        producttbl.setRowHeight(20);
        producttbl.setRowMargin(4);
        producttbl.setSelectionBackground(new java.awt.Color(102, 153, 255));
        producttbl.setUpdateSelectionOnSort(false);
        producttbl.setVerifyInputWhenFocusTarget(false);
        producttbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                producttblMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                producttblMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                producttblMouseReleased(evt);
            }
        });
        producttbl.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                producttblKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(producttbl);

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

    }//GEN-LAST:event_quicksearchtxtfieldActionPerformed

    private void searchtxtfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchtxtfieldActionPerformed

    }//GEN-LAST:event_searchtxtfieldActionPerformed

    private void deletebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletebtnActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletebtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        updateValue();
    }//GEN-LAST:event_updatebtnActionPerformed

    private void insertbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertbtnActionPerformed
        displayProductFrame();
    }//GEN-LAST:event_insertbtnActionPerformed

    private void openbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openbtnActionPerformed
        restore();
    }//GEN-LAST:event_openbtnActionPerformed

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        backup();
    }//GEN-LAST:event_savebtnActionPerformed

    private void refreshbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshbtnActionPerformed
        reset();
        searchcombobox.setSelectedItem("ID");
    }//GEN-LAST:event_refreshbtnActionPerformed

    private void addmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmenuitemActionPerformed
        displayProductFrame();
    }//GEN-LAST:event_addmenuitemActionPerformed

    private void editmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editmenuitemActionPerformed
        updateValue();
    }//GEN-LAST:event_editmenuitemActionPerformed

    private void deletemenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletemenuitemActionPerformed
        deleteUsingArray();
    }//GEN-LAST:event_deletemenuitemActionPerformed

    private void refreshmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshmenuitemActionPerformed
        reset();
    }//GEN-LAST:event_refreshmenuitemActionPerformed

    private void quicksearchtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quicksearchtxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == quicksearchtxtfield && keycode == KeyEvent.VK_BACKSPACE && quicksearchtxtfield.getText().length() == 0) {
            reset();
        } else {
            loadDataQuickSearch();
        }

    }//GEN-LAST:event_quicksearchtxtfieldKeyReleased

    private void importfromexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importfromexcelmenuitemActionPerformed
        restore();
    }//GEN-LAST:event_importfromexcelmenuitemActionPerformed

    private void exporttoexcelmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporttoexcelmenuitemActionPerformed
        backup();
    }//GEN-LAST:event_exporttoexcelmenuitemActionPerformed

    private void searchtxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchtxtfieldKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == searchtxtfield && keycode == com.sun.glass.events.KeyEvent.VK_ENTER) {
            loadDatasSearch();
            countRows();
        }
    }//GEN-LAST:event_searchtxtfieldKeyPressed

    private void producttblKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_producttblKeyPressed
        int keycode = evt.getKeyCode();
        if (evt.getSource() == producttbl && keycode == com.sun.glass.events.KeyEvent.VK_DELETE) {
            deleteUsingArray();
            countRows();
        }
    }//GEN-LAST:event_producttblKeyPressed

    private void producttblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_producttblMouseClicked
    }//GEN-LAST:event_producttblMouseClicked

    private void producttblMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_producttblMousePressed
    }//GEN-LAST:event_producttblMousePressed

    private void producttblMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_producttblMouseReleased
        checkInventory();
    }//GEN-LAST:event_producttblMouseReleased

    private void viewspecificationmenuitemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewspecificationmenuitemActionPerformed
        displaySpecification();
    }//GEN-LAST:event_viewspecificationmenuitemActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem addmenuitem;
    private javax.swing.JButton deletebtn;
    private javax.swing.JMenuItem deletemenuitem;
    private javax.swing.JMenuItem editmenuitem;
    private javax.swing.JMenuItem exporttoexcelmenuitem;
    private javax.swing.JMenuItem importfromexcelmenuitem;
    private javax.swing.JButton insertbtn;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JLabel noofrecordslab;
    private javax.swing.JButton openbtn;
    private javax.swing.JPopupMenu popupmenu;
    private javax.swing.JTable producttbl;
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
    private javax.swing.JPanel upperpanel;
    private javax.swing.JMenuItem viewspecificationmenuitem;
    // End of variables declaration//GEN-END:variables
}
