package inventory.ui.frame;

import inventory.dao.BrandDAO;
import inventory.dao.CategoryDAO;
import inventory.dao.ProductDAO;
import inventory.dao.SupplierDAO;
import inventory.dto.ProductDTO;
import inventory.ui.panel.ProductPanel;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ProductFrame extends javax.swing.JFrame {

    public ProductFrame() {
        initComponents();
        this.setResizable(false);
        this.setTitle("Product");
        this.setLocationRelativeTo(null);
        setBrand();
        setCategory();
        setSupplier();
        setProductID();
        setDate();
        //setDisable();
        productidtxtfield.setEditable(false);
        productprofitttxfield.setEditable(false);
        reset();
    }
    BrandDAO branddao = new BrandDAO();
    CategoryDAO categegorydao = new CategoryDAO();
    SupplierDAO supplierdao = new SupplierDAO();
    ProductDAO productdao = new ProductDAO();
    ProductDTO productdto = new ProductDTO();

    public void setBrand() {
        try {
            productbrandcombobox.removeAllItems();
            productbrandcombobox.addItem("Select Brand");
            ResultSet brandname = branddao.getbrandName();
            while (brandname.next()) {
                productbrandcombobox.addItem(brandname.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void setCategory() {
        try {
            productcategorycombobox.removeAllItems();
            productcategorycombobox.addItem("Select Category");
            ResultSet categoryname = categegorydao.getcatgeoryName();
            while (categoryname.next()) {
                productcategorycombobox.addItem(categoryname.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void setSupplier() {
        try {
            productsuppliercombobox.removeAllItems();
            productsuppliercombobox.addItem("Select Supplier");
            ResultSet suppliername = supplierdao.getsupplierName();
            while (suppliername.next()) {
                productsuppliercombobox.addItem(suppliername.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void setProductID() {
        int productid = productdao.productId() + 1;
        productidtxtfield.setText(String.valueOf(productid));
    }

    public void setDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMMM dd yyyy");
        productentrydatetxtfield.setDate(dNow);
    }

    public void check() {
        if (((JTextField) productentrydatetxtfield.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the valid date", "Inventory Management System",
                    +JOptionPane.INFORMATION_MESSAGE);
        }
        if (productmodeltxtfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the model", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (productquantitytxtfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the quantity", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (productsellingpricetxtfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the selling price", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (productcostpricetxtfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the cost price", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (productprofitttxfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the profit", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        if (productwarrantytxtfield.getText().equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(null, "Enter the warranty", "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void reset() {
        setDate();
        productbrandcombobox.setSelectedItem("Select Brand");
        productsuppliercombobox.setSelectedItem("Select Supplier");
        productcategorycombobox.setSelectedItem("Select Category");
        productmodeltxtfield.setText("");
        productquantitytxtfield.setText("");
        productwarrantytxtfield.setText("");
        productsellingpricetxtfield.setText("");
        productprofitttxfield.setText("");
        productcostpricetxtfield.setText("");
        productspecificationtxtarea.setText("");
        productmodeltxtfield.setEditable(true);
        savebtn.setText("Save");
    }

    public void calculateProfit() {
        double sellingprice = Double.parseDouble(productsellingpricetxtfield.getText());
        double costprice = Double.parseDouble(productcostpricetxtfield.getText());
        double profit = sellingprice - costprice;
        if (sellingprice > costprice) {
            productprofitttxfield.setText(String.valueOf(profit));
        } else {
            productprofitttxfield.setText("-" + String.valueOf(profit));
        }
        System.out.println(profit);
    }

    public void setDisable() {
        productquantitytxtfield.setEditable(false);
        productwarrantytxtfield.setEditable(false);
        productsellingpricetxtfield.setEditable(false);
        productcostpricetxtfield.setEditable(false);
        productspecificationtxtarea.setEditable(false);
    }

    public void setEnable() {
        productquantitytxtfield.setEditable(true);
        productwarrantytxtfield.setEditable(true);
        productsellingpricetxtfield.setEditable(true);
        productcostpricetxtfield.setEditable(true);
        productspecificationtxtarea.setEditable(true);
    }

    public void insert() {
        productdto.setProductid(Integer.parseInt(productidtxtfield.getText()));
        productdto.setProductentrydate(((JTextField) productentrydatetxtfield.getDateEditor().getUiComponent()).getText());
        productdto.setProductsuppliername((String) productsuppliercombobox.getSelectedItem());
        productdto.setProductmodel(productmodeltxtfield.getText());
        productdto.setProductbrand((String) productbrandcombobox.getSelectedItem());
        productdto.setProductcategory((String) productcategorycombobox.getSelectedItem());
        productdto.setProductqty(Integer.parseInt(productquantitytxtfield.getText()));
        productdto.setProductsellingprice(Double.valueOf(productsellingpricetxtfield.getText()));
        productdto.setProductcostprice(Double.valueOf(productcostpricetxtfield.getText()));
        productdto.setProductprofit(Double.valueOf(productprofitttxfield.getText()));
        productdto.setProductwarranty(productwarrantytxtfield.getText() + "-" + productwarrantytimecombobox.getSelectedItem());
        productdto.setProductspecification(productspecificationtxtarea.getText());
        if (productdao.insertproduct(productdto) == true) {
            reset();
        }
    }

    public void update(ProductDTO productdto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        productidtxtfield.setText(String.valueOf(productdto.getProductid()));
        ((JTextField) productentrydatetxtfield.getDateEditor().getUiComponent()).setText(productdto.
                getProductentrydate());
        productsuppliercombobox.setSelectedItem(productdto.getProductsuppliername());
        productmodeltxtfield.setText(productdto.getProductmodel());
        productbrandcombobox.setSelectedItem(productdto.getProductbrand());
        productcategorycombobox.setSelectedItem(productdto.getProductcategory());
        productquantitytxtfield.setText(String.valueOf(productdto.getProductqty()));
        productsellingpricetxtfield.setText(String.valueOf(productdto.getProductsellingprice()));
        productcostpricetxtfield.setText(String.valueOf(productdto.getProductcostprice()));
        productprofitttxfield.setText(String.valueOf(productdto.getProductprofit()));
        productspecificationtxtarea.setText(productdto.getProductspecification());
        String warranty = productdto.getProductwarranty();
        String[] parts = warranty.split("-");
        String part1 = parts[0];
        String part2 = parts[1];
        productwarrantytxtfield.setText(part1);
        productwarrantytimecombobox.setSelectedItem(part2);
    }

    public void updateFinal() {
        productdto.setProductid(Integer.parseInt(productidtxtfield.getText()));
        productdto.setProductentrydate(((JTextField) productentrydatetxtfield.getDateEditor().getUiComponent()).getText());
        productdto.setProductsuppliername((String) productsuppliercombobox.getSelectedItem());
        productdto.setProductmodel(productmodeltxtfield.getText());
        productdto.setProductbrand((String) productbrandcombobox.getSelectedItem());
        productdto.setProductcategory((String) productcategorycombobox.getSelectedItem());
        productdto.setProductqty(Integer.parseInt(productquantitytxtfield.getText()));
        productdto.setProductsellingprice(Double.valueOf(productsellingpricetxtfield.getText()));
        productdto.setProductcostprice(Double.valueOf(productcostpricetxtfield.getText()));
        productdto.setProductprofit(Double.valueOf(productprofitttxfield.getText()));
        productdto.setProductwarranty(productwarrantytxtfield.getText() + "-" + productwarrantytimecombobox.getSelectedItem());
        productdto.setProductspecification(productspecificationtxtarea.getText());
        try {
            if (productdao.updateproduct(productdto) == true) {
                reset();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void verifyProduct() {

        try {
            ResultSet rs = productdao.searchProduct(productmodeltxtfield.getText(),
                    String.valueOf(productbrandcombobox.getSelectedItem()),
                    String.valueOf(productsuppliercombobox.getSelectedItem())
            );
            if (rs != null) {

                while (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Record exist of the inputed product \n Please update in it",
                            "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                    productmodeltxtfield.setEnabled(false);
                    int productid = rs.getInt(1);
                    productidtxtfield.setText(String.valueOf(productid));
                    String productentrydate = rs.getString(2);
                    ((JTextField) productentrydatetxtfield.getDateEditor().getUiComponent()).setText(productentrydate);
                    //String productsuppliername = rs.getString(3);
                    //productsuppliercombobox.setSelectedItem(productsuppliername);
                    //String productbrand = rs.getString(4);
                    //productbrandcombobox.setSelectedItem(productbrand);
                    String productcategory = rs.getString(6);
                    productcategorycombobox.setSelectedItem(productcategory);
                    int productqty = rs.getInt(7);
                    productquantitytxtfield.setText(String.valueOf(productqty));
                    double productsellingprice = rs.getDouble(8);
                    productsellingpricetxtfield.setText(String.valueOf(productsellingprice));
                    double productcostprice = rs.getDouble(9);
                    productcostpricetxtfield.setText(String.valueOf(productcostprice));
                    double productprofit = rs.getDouble(10);
                    productprofitttxfield.setText(String.valueOf(productprofit));

                    String warranty = rs.getString(11);

                    String[] parts = warranty.split("-");
                    String part1 = parts[0];
                    productwarrantytxtfield.setText(part1);
                    String part2 = parts[1];
                    productwarrantytimecombobox.setSelectedItem(part2);
                    String specification = rs.getString(12);
                    productspecificationtxtarea.setText(specification);
                    savebtn.setText("Update");
                }
            } else {

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        idlab = new javax.swing.JLabel();
        productidtxtfield = new javax.swing.JTextField();
        emtrydatelab = new javax.swing.JLabel();
        productsupplierlab = new javax.swing.JLabel();
        productmodeltxtfield = new javax.swing.JTextField();
        productentrydatetxtfield = new com.toedter.calendar.JDateChooser();
        productsuppliercombobox = new javax.swing.JComboBox();
        productmodellab = new javax.swing.JLabel();
        productbrandlab = new javax.swing.JLabel();
        productbrandcombobox = new javax.swing.JComboBox();
        productcategorylab = new javax.swing.JLabel();
        productcategorycombobox = new javax.swing.JComboBox();
        productquantitylab = new javax.swing.JLabel();
        productquantitytxtfield = new javax.swing.JTextField();
        productsellingpricelab = new javax.swing.JLabel();
        productsellingpricetxtfield = new javax.swing.JTextField();
        productcostpricelab = new javax.swing.JLabel();
        productcostpricetxtfield = new javax.swing.JTextField();
        productprofitlab = new javax.swing.JLabel();
        productprofitttxfield = new javax.swing.JTextField();
        productwarrantylab = new javax.swing.JLabel();
        productwarrantytxtfield = new javax.swing.JTextField();
        productwarrantytimecombobox = new javax.swing.JComboBox();
        productspecificationlab = new javax.swing.JLabel();
        product = new javax.swing.JScrollPane();
        productspecificationtxtarea = new javax.swing.JTextArea();
        lowerpanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        savebtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        idlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idlab.setText("Id :-");

        productidtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        emtrydatelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        emtrydatelab.setText("Entry Date :-");

        productsupplierlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productsupplierlab.setText("Supplier :-");

        productmodeltxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productmodeltxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                productmodeltxtfieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productmodeltxtfieldKeyReleased(evt);
            }
        });

        productentrydatetxtfield.setDateFormatString("MMM d yyyy");

        productsuppliercombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        productmodellab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productmodellab.setText("Model :-");

        productbrandlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productbrandlab.setText("Brand :-");

        productbrandcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        productcategorylab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productcategorylab.setText("Category :-");

        productcategorycombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        productquantitylab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productquantitylab.setText("Quantity :-");

        productquantitytxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        productsellingpricelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productsellingpricelab.setText("Selling Price :-");

        productsellingpricetxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        productcostpricelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productcostpricelab.setText("Cost Price :-");

        productcostpricetxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        productcostpricetxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                productcostpricetxtfieldKeyReleased(evt);
            }
        });

        productprofitlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productprofitlab.setText("Profit :-");

        productprofitttxfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        productwarrantylab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productwarrantylab.setText("Warranty :-");

        productwarrantytxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        productwarrantytimecombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Year", "Month" }));

        productspecificationlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productspecificationlab.setText("Product Specification :-");

        productspecificationtxtarea.setColumns(20);
        productspecificationtxtarea.setRows(5);
        product.setViewportView(productspecificationtxtarea);

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(product)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(productquantitylab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productquantitytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(productsellingpricelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productsellingpricetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(idlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(emtrydatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productentrydatetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(productsupplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productsuppliercombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(productbrandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productbrandcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addComponent(productmodellab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productmodeltxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(productcategorylab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(productcategorycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(productcostpricelab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(productcostpricetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(productwarrantylab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(productwarrantytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(productspecificationlab, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productwarrantytimecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addComponent(productprofitlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(productprofitttxfield, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(idlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(productidtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emtrydatelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(productentrydatetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productsupplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productsuppliercombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productbrandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productbrandcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productmodeltxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productmodellab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(productcategorylab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(productcategorycombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productquantitytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productquantitylab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productsellingpricetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productsellingpricelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productprofitttxfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productprofitlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productcostpricetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productcostpricelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productwarrantytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productwarrantylab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productwarrantytimecombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(productspecificationlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(product, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                    .addComponent(jSeparator1)
                    .addGroup(lowerpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lowerpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(lowerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new ProductPanel().visible();
        reset();
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing


    }//GEN-LAST:event_formWindowClosing

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            setProductID();
        } else {
            check();
            updateFinal();
            setProductID();
        }
    }//GEN-LAST:event_savebtnActionPerformed

    private void productcostpricetxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productcostpricetxtfieldKeyReleased
        if (productsellingpricetxtfield.getText().length() != 0) {
            calculateProfit();
        }

    }//GEN-LAST:event_productcostpricetxtfieldKeyReleased

    private void productmodeltxtfieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productmodeltxtfieldKeyPressed

    }//GEN-LAST:event_productmodeltxtfieldKeyPressed

    private void productmodeltxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_productmodeltxtfieldKeyReleased
        int keycode = evt.getKeyCode();
        if (evt.getSource() == productmodeltxtfield) {
            if (keycode == com.sun.glass.events.KeyEvent.VK_BACKSPACE && productmodeltxtfield.getText().length() == 0) {
                //reset();
            } else {
                verifyProduct();

            }

        }

    }//GEN-LAST:event_productmodeltxtfieldKeyReleased

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel emtrydatelab;
    private javax.swing.JLabel idlab;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JScrollPane product;
    private javax.swing.JComboBox productbrandcombobox;
    private javax.swing.JLabel productbrandlab;
    private javax.swing.JComboBox productcategorycombobox;
    private javax.swing.JLabel productcategorylab;
    private javax.swing.JLabel productcostpricelab;
    private javax.swing.JTextField productcostpricetxtfield;
    private com.toedter.calendar.JDateChooser productentrydatetxtfield;
    private javax.swing.JTextField productidtxtfield;
    private javax.swing.JLabel productmodellab;
    private javax.swing.JTextField productmodeltxtfield;
    private javax.swing.JLabel productprofitlab;
    private javax.swing.JTextField productprofitttxfield;
    private javax.swing.JLabel productquantitylab;
    private javax.swing.JTextField productquantitytxtfield;
    private javax.swing.JLabel productsellingpricelab;
    private javax.swing.JTextField productsellingpricetxtfield;
    private javax.swing.JLabel productspecificationlab;
    private javax.swing.JTextArea productspecificationtxtarea;
    private javax.swing.JComboBox productsuppliercombobox;
    private javax.swing.JLabel productsupplierlab;
    private javax.swing.JLabel productwarrantylab;
    private javax.swing.JComboBox productwarrantytimecombobox;
    private javax.swing.JTextField productwarrantytxtfield;
    private javax.swing.JButton savebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
