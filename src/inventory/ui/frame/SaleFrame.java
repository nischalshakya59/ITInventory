package inventory.ui.frame;

import inventory.dao.BrandDAO;
import inventory.dao.CategoryDAO;
import inventory.dao.ProductDAO;
import inventory.dao.SaleDAO;
import inventory.dao.SupplierDAO;
import inventory.db.DBConnection;
import inventory.dto.ProductDTO;
import inventory.dto.SaleDTO;
import inventory.ui.panel.SalePanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class SaleFrame extends javax.swing.JFrame {

    public SaleFrame() {
        initComponents();
        this.setTitle("Sale");
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        saletotaltxtfield.setEditable(false);
        setSupplierBrandName();
        setSaleID();
        setDate();
        saleavaiablequantitytxtfield.setEditable(false);
        makebillbtn.setEnabled(false);
        saleratetxtfield.setEditable(false);
        reset();
    }
    @SuppressWarnings("unchecked")

    BrandDAO branddao = new BrandDAO();
    CategoryDAO categegorydao = new CategoryDAO();
    SupplierDAO supplierdao = new SupplierDAO();
    ProductDAO productdao = new ProductDAO();
    ProductDTO productdto = new ProductDTO();
    SaleDAO saledao = new SaleDAO();
    SaleDTO saledto = new SaleDTO();

    // set the avaiable brand name from the producttbl
    public void setSupplierBrandName() {
        try {
            salesuppliercombobox.addItem("Select Supplier");
            ResultSet name = productdao.getBrandName();
            Set<String> brandset = new TreeSet<>();

            while (name.next()) {
                brandset.add(name.getString(1));
            }
            for (String brandelement : brandset) {
                salebrandcombobox.addItem(brandelement);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /* set the avaiable model as well as the respective supplier once the brand name is 
     chosen from brand combobox */
    public void setProductModelSupplier() {
        saleratetxtfield.setText("");
        saleavaiablequantitytxtfield.setText("");
        try {
            String productbrand = String.valueOf(salebrandcombobox.getSelectedItem());
            if (productbrand.equalsIgnoreCase("Select Brand")) {

            } else {
                ResultSet productmodel = productdao.getProductModelSupplier(productbrand);
                Set<String> productset = new TreeSet<>();
                Set<String> supplierset = new TreeSet<>();
                while (productmodel.next()) {
                    productset.add(productmodel.getString(1));
                    supplierset.add(productmodel.getString(2));
                }
                for (String productelement : productset) {
                    saleproductcombobox.addItem(productelement);
                }
                for (String supplierelement : supplierset) {
                    //salesuppliercombobox.addItem(supplierelement);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /* set the avaiable quantity and amount per piece of the product 
     with respect to the brand name, supplier name and product model*/
    public void setQuantityAndAmount() {

        try {
            ResultSet quantityamount = productdao.getQuantitySellingprice(
                    (String) saleproductcombobox.getSelectedItem(),
                    (String) salebrandcombobox.getSelectedItem(),
                    (String) salesuppliercombobox.getSelectedItem());
            while (quantityamount.next()) {
                saleavaiablequantitytxtfield.setText(String.valueOf(quantityamount.getInt(1)));
                saleratetxtfield.setText(String.valueOf(quantityamount.getDouble(2)));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /*set the supplier when the product model is choosen from productmodelcombobox
     */
    public void setSupplier() {
        try {
            String productbrand = String.valueOf(salebrandcombobox.getSelectedItem());
            String productmodel = String.valueOf(saleproductcombobox.getSelectedItem());
            if (productbrand.equalsIgnoreCase("Select Brand")) {

            } else {
                ResultSet suppliername = productdao.getSupplierName(productbrand, productmodel);
                Set<String> supplierset = new TreeSet<>();
                while (suppliername.next()) {
                    supplierset.add(suppliername.getString(1));
                }
                for (String supplierelement : supplierset) {
                    salesuppliercombobox.addItem(supplierelement);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void setSaleID() {
        int saleid = saledao.saleId() + 1;
        saleidlab.setText(String.valueOf(saleid));
    }

    public void setDate() {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("MMMMM dd yyyy");
        saleentrydate.setDate(dNow);
    }

    public void reset() {
        setSaleID();
        //setDate();
        salebrandcombobox.setSelectedItem("Select Brand");
        salesuppliercombobox.setSelectedItem("Select Supplier");
        saleproductcombobox.setSelectedItem("Select Product");
        saleavaiablequantitytxtfield.setText("");
        saletotaltxtfield.setText("");
        saleratetxtfield.setText("");
        salequantitypurchasedtxtfield.setText("");
        savebtn.setText("Save");
    }

    public void disableCustomerInfo() {
        salenametxtfield.setEditable(false);
        ((JTextField) saleentrydate.getDateEditor().getUiComponent()).setEditable(false);
        saleaddresstxtfield.setEditable(false);
        salecontactnotxtfield.setEditable(false);

    }

    public void calculateTotal() {
        double total = Double.valueOf(salequantitypurchasedtxtfield.getText())
                * Double.parseDouble(saleratetxtfield.getText());
        saletotaltxtfield.setText(String.valueOf(total));
    }

    public void check() {
        if (salenametxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the customer name",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }

        if (((JTextField) saleentrydate.getDateEditor().getUiComponent()).getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the date",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (saleaddresstxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the customer address",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (salecontactnotxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the customer contact no",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (salebrandcombobox.getSelectedItem().equals("Select Brand")) {
            JOptionPane.showMessageDialog(null, "Enter the brand",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (saleproductcombobox.getSelectedItem().equals("Select Product")) {
            JOptionPane.showMessageDialog(null, "Enter the product model",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (salesuppliercombobox.getSelectedItem().equals("Select Supplier")) {
            JOptionPane.showMessageDialog(null, "Enter the supplier",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
        if (salequantitypurchasedtxtfield.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Enter the sale quantity",
                    "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    public void insert() {
        saledto.setSaleid(Integer.parseInt(saleidlab.getText()));
        saledto.setSaledate(((JTextField) saleentrydate.getDateEditor().getUiComponent()).getText());
        saledto.setSalecustomername(salenametxtfield.getText().substring(0, 1).toUpperCase()
                + salenametxtfield.getText().substring(1));
        saledto.setSalecustomeraddress(saleaddresstxtfield.getText().substring(0, 1).toUpperCase()
                + saleaddresstxtfield.getText().substring(1));
        saledto.setSalecustomercontactno(salecontactnotxtfield.getText());
        saledto.setSalesupplier((String) salesuppliercombobox.getSelectedItem());
        saledto.setSalebrand((String) (salebrandcombobox.getSelectedItem()));
        saledto.setSaleproduct((String) saleproductcombobox.getSelectedItem());
        saledto.setSaleqty(Integer.parseInt(salequantitypurchasedtxtfield.getText()));
        saledto.setSaleamtperpiece(Double.valueOf(saleratetxtfield.getText()));
        saledto.setSaletotal(Double.valueOf(saletotaltxtfield.getText()));

        productdto.setProductsuppliername((String) salesuppliercombobox.getSelectedItem());
        productdto.setProductbrand((String) salebrandcombobox.getSelectedItem());
        productdto.setProductmodel((String) saleproductcombobox.getSelectedItem());
        productdto.setProductqty(Integer.parseInt(salequantitypurchasedtxtfield.getText()));
        if (saledao.insertsale(saledto) == true && productdao.updateProductQty(productdto) == true) {
            disableCustomerInfo();
            displaytable();
            reset();
            makebillbtn.setEnabled(true);
        }
    }

    public void update(SaleDTO saledto) {
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        savebtn.setText("Update");
        saleidlab.setText(String.valueOf(saledto.getSaleid()));
        salenametxtfield.setText(saledto.getSalecustomername());
        saleaddresstxtfield.setText(saledto.getSalecustomeraddress());
        salecontactnotxtfield.setText(saledto.getSalecustomercontactno());

        salebrandcombobox.setSelectedItem(saledto.getSalebrand());
        saleproductcombobox.setSelectedItem(saledto.getSaleproduct());
        salesuppliercombobox.setSelectedItem(saledto.getSalesupplier());
        salequantitypurchasedtxtfield.setText(String.valueOf(saledto.getSaleqty()));
        saletotaltxtfield.setText(String.valueOf(saledto.getSaletotal()));
    }

    public void updateFinal() {
        saledto.setSaleid(Integer.parseInt(saleidlab.getText()));
        saledto.setSaledate(((JTextField) saleentrydate.getDateEditor().getUiComponent()).getText());
        saledto.setSalecustomername(salenametxtfield.getText());
        saledto.setSalecustomeraddress(saleaddresstxtfield.getText());
        saledto.setSalecustomercontactno(salecontactnotxtfield.getText());
        saledto.setSalesupplier((String) salesuppliercombobox.getSelectedItem());
        saledto.setSalebrand((String) salebrandcombobox.getSelectedItem());
        saledto.setSaleproduct((String) saleproductcombobox.getSelectedItem());
        saledto.setSaleqty(Integer.parseInt(salequantitypurchasedtxtfield.getText()));
        saledto.setSaleamtperpiece(Double.valueOf(saleratetxtfield.getText()));
        saledto.setSaletotal(Double.valueOf(saletotaltxtfield.getText()));

        productdto.setProductsuppliername((String) salesuppliercombobox.getSelectedItem());
        productdto.setProductbrand((String) salebrandcombobox.getSelectedItem());
        productdto.setProductmodel((String) saleproductcombobox.getSelectedItem());
        productdto.setProductqty(Integer.parseInt(salequantitypurchasedtxtfield.getText()));

        if (saledao.updatesale(saledto) == true && productdao.updateProductQty(productdto) == true) {
            reset();
        }
    }

    public void generateReport() {
        try {
            JasperDesign jd = JRXmlLoader.load("C:\\InventoryMgmt\\Report\\Sale.jrxml");
            String customername = salenametxtfield.getText();
            String saledate = (((JTextField) saleentrydate.getDateEditor().getUiComponent()).getText());
            String query1 = "select saleid as BillId, \n"
                    + "saledate as Date,\n"
                    + "salecustomername as Name,\n"
                    + "salebrand as Brand,\n"
                    + "saleproduct as Product,\n"
                    + "saleqty as Quantity,\n"
                    + "saleamtperpiece as Rate,\n"
                    + "saletotal as Total \n"
                    + " from saletbl "
                    + "WHERE salecustomername = '" + customername + "' AND  saledate = '" + saledate + "'";

            JRDesignQuery newQuery = new JRDesignQuery();
            newQuery.setText(query1);
            jd.setQuery(newQuery);

            JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperPrint jp = JasperFillManager.fillReport(jr, null, new DBConnection().getConnection());

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());

            jv.setTitle("Customer Bill");
            jv.setVisible(true);
            jv.pack();
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            jv.setSize(screenSize.width, screenSize.height);
            jv.setExtendedState(jv.MAXIMIZED_BOTH);
            jv.setZoomRatio((float) 0.75);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void displaytable() {
        DefaultTableModel model = (DefaultTableModel) saletbl.getModel();
        List<String> list = new ArrayList<String>();

        for (int c = 0; c < saletbl.getColumnCount(); c++) {
            Class<?> col_class = saletbl.getColumnClass(c);
            saletbl.setDefaultEditor(col_class, null);
        }

        list.add((String) salebrandcombobox.getSelectedItem());
        list.add((String) saleproductcombobox.getSelectedItem());
        list.add(salequantitypurchasedtxtfield.getText());
        list.add(saleratetxtfield.getText());
        list.add(saletotaltxtfield.getText());

        model.addRow(list.toArray());

        saletbl.setModel(model);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        upperpanel = new javax.swing.JPanel();
        orgnamelab = new javax.swing.JLabel();
        contactnolab = new javax.swing.JLabel();
        contactnolab1 = new javax.swing.JLabel();
        salelab = new javax.swing.JLabel();
        saleidlab = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        lowerpanel = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        saletbl = new javax.swing.JTable();
        middlepanel = new javax.swing.JPanel();
        salename = new javax.swing.JLabel();
        salenametxtfield = new javax.swing.JTextField();
        saledate = new javax.swing.JLabel();
        saleentrydate = new com.toedter.calendar.JDateChooser();
        salechoosesupplierlab = new javax.swing.JLabel();
        salesuppliercombobox = new javax.swing.JComboBox();
        salelavaiableproduct = new javax.swing.JLabel();
        salechoosebrandlab = new javax.swing.JLabel();
        salebrandcombobox = new javax.swing.JComboBox();
        saleaddresslab = new javax.swing.JLabel();
        saleaddresstxtfield = new javax.swing.JTextField();
        salecontactnolab = new javax.swing.JLabel();
        salecontactnotxtfield = new javax.swing.JTextField();
        saleavaiablequantitylab = new javax.swing.JLabel();
        saleavaiablequantitytxtfield = new javax.swing.JTextField();
        salequantitypurchasedtxtfield = new javax.swing.JTextField();
        salequantitypurchasedlab = new javax.swing.JLabel();
        saleamountperpiecelab = new javax.swing.JLabel();
        saleratetxtfield = new javax.swing.JTextField();
        saletotallab = new javax.swing.JLabel();
        saletotaltxtfield = new javax.swing.JTextField();
        saleproductcombobox = new javax.swing.JComboBox();
        savebtn = new javax.swing.JButton();
        makebillbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
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

        upperpanel.setPreferredSize(new java.awt.Dimension(552, 75));

        orgnamelab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        orgnamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgnamelab.setText("Sankata Info Sys");

        contactnolab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contactnolab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contactnolab.setText("Contact No : - 01-4255678");

        contactnolab1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        contactnolab1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        contactnolab1.setText("New Road, Kathmandu");

        salelab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        salelab.setText("Sale Id :-");

        saleidlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saleidlab.setText("saleidlab");

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(upperpanelLayout.createSequentialGroup()
                        .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(salelab)
                                .addGap(18, 18, 18)
                                .addComponent(saleidlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(orgnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(upperpanelLayout.createSequentialGroup()
                                .addGap(105, 105, 105)
                                .addComponent(contactnolab)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(contactnolab1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orgnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saleidlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contactnolab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(contactnolab1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        saletbl.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saletbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Brand", "Product", "Quantity", "Rate", "Amount"
            }
        ));
        saletbl.setRowHeight(20);
        saletbl.setRowMargin(4);
        jScrollPane2.setViewportView(saletbl);

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator1))
                .addContainerGap())
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        salename.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salename.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salename.setText("Name :-");

        salenametxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salenametxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        saledate.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saledate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saledate.setText("Date :-");

        saleentrydate.setDateFormatString("MMM d yyyy");

        salechoosesupplierlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salechoosesupplierlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salechoosesupplierlab.setText(" Supplier :-");

        salesuppliercombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salesuppliercombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Supplier" }));
        salesuppliercombobox.setSelectedItem("Choose Supplier"
            + "");
        salesuppliercombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesuppliercomboboxActionPerformed(evt);
            }
        });

        salelavaiableproduct.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salelavaiableproduct.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salelavaiableproduct.setText("Product Model:-");

        salechoosebrandlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salechoosebrandlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salechoosebrandlab.setText(" Brand :-");

        salebrandcombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salebrandcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Brand" }));
        salebrandcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salebrandcomboboxActionPerformed(evt);
            }
        });

        saleaddresslab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleaddresslab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saleaddresslab.setText("Address :-");

        saleaddresstxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleaddresstxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        salecontactnolab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salecontactnolab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salecontactnolab.setText("Contact No :-");

        salecontactnotxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salecontactnotxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        saleavaiablequantitylab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleavaiablequantitylab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saleavaiablequantitylab.setText("Avaiable Quantity :-");

        saleavaiablequantitytxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleavaiablequantitytxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        salequantitypurchasedtxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salequantitypurchasedtxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        salequantitypurchasedtxtfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                salequantitypurchasedtxtfieldKeyReleased(evt);
            }
        });

        salequantitypurchasedlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        salequantitypurchasedlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salequantitypurchasedlab.setText("Quantity Sales :-");

        saleamountperpiecelab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleamountperpiecelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saleamountperpiecelab.setText("Rate :-");

        saleratetxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleratetxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        saletotallab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saletotallab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        saletotallab.setText("Total :-");

        saletotaltxtfield.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saletotaltxtfield.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        saleproductcombobox.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saleproductcombobox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose Product" }));
        saleproductcombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saleproductcomboboxActionPerformed(evt);
            }
        });

        savebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/save.png"))); // NOI18N
        savebtn.setText("Save");
        savebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                savebtnActionPerformed(evt);
            }
        });

        makebillbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/print.png"))); // NOI18N
        makebillbtn.setText("Make Bill");
        makebillbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                makebillbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout middlepanelLayout = new javax.swing.GroupLayout(middlepanel);
        middlepanel.setLayout(middlepanelLayout);
        middlepanelLayout.setHorizontalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saleaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salechoosebrandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salename, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salequantitypurchasedlab, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saletotallab, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(saleaddresstxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salebrandcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salenametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salequantitypurchasedtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saletotaltxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addComponent(salechoosesupplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesuppliercombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(middlepanelLayout.createSequentialGroup()
                                .addComponent(saledate, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(saleentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(salelavaiableproduct, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(salecontactnolab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(salecontactnotxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saleproductcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                                .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(makebillbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                                .addComponent(saleamountperpiecelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(saleratetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, middlepanelLayout.createSequentialGroup()
                                .addComponent(saleavaiablequantitylab)
                                .addGap(18, 18, 18)
                                .addComponent(saleavaiablequantitytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );

        middlepanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {makebillbtn, savebtn});

        middlepanelLayout.setVerticalGroup(
            middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(middlepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salename, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saledate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saleaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addComponent(saleentrydate, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salecontactnolab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salecontactnotxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addComponent(salenametxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saleaddresstxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(saleproductcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(salebrandcombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(salelavaiableproduct, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(salechoosebrandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(salesuppliercombobox, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(salechoosesupplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(11, 11, 11)
                        .addComponent(salequantitypurchasedtxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(middlepanelLayout.createSequentialGroup()
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saleavaiablequantitylab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(saleavaiablequantitytxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(middlepanelLayout.createSequentialGroup()
                                .addComponent(salequantitypurchasedlab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saletotallab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saletotaltxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(middlepanelLayout.createSequentialGroup()
                                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(saleratetxtfield, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(saleamountperpiecelab, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(middlepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(makebillbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(savebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 16, Short.MAX_VALUE))
        );

        middlepanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {makebillbtn, savebtn});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(upperpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(middlepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lowerpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(upperpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(middlepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lowerpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new SalePanel().visible();

    }//GEN-LAST:event_formWindowClosed

    private void saleproductcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saleproductcomboboxActionPerformed
        salesuppliercombobox.removeAllItems();
        salesuppliercombobox.addItem("Select Supplier");
        setSupplier();

    }//GEN-LAST:event_saleproductcomboboxActionPerformed

    private void salebrandcomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salebrandcomboboxActionPerformed
        saleproductcombobox.removeAllItems();
        saleproductcombobox.addItem("Select Product");
        salesuppliercombobox.removeAllItems();
        salesuppliercombobox.addItem("Select Supplier");
        setProductModelSupplier();
    }//GEN-LAST:event_salebrandcomboboxActionPerformed

    private void salesuppliercomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesuppliercomboboxActionPerformed

        String supplier = String.valueOf(salesuppliercombobox.getSelectedItem());
        String brand = String.valueOf(salebrandcombobox.getSelectedItem());
        String productmodel = String.valueOf(saleproductcombobox.getSelectedItem());
        if (supplier.equalsIgnoreCase("Selct Supplier") && brand.equalsIgnoreCase("Selct Brand")
                && productmodel.equalsIgnoreCase("Select Product")) {

        } else {
            setQuantityAndAmount();
        }

    }//GEN-LAST:event_salesuppliercomboboxActionPerformed

    private void salequantitypurchasedtxtfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salequantitypurchasedtxtfieldKeyReleased
        calculateTotal();
        int purchasequantity = Integer.parseInt(salequantitypurchasedtxtfield.getText());
        int avaiablequantity = Integer.parseInt(saleavaiablequantitytxtfield.getText());
        if (purchasequantity > avaiablequantity) {
            salequantitypurchasedtxtfield.setForeground(Color.red);
            savebtn.setEnabled(false);
        } else {
            salequantitypurchasedtxtfield.setForeground(Color.BLACK);
            savebtn.setEnabled(true);
        }
    }//GEN-LAST:event_salequantitypurchasedtxtfieldKeyReleased

    private void savebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_savebtnActionPerformed
        if (savebtn.getText().equalsIgnoreCase("save")) {
            check();
            insert();
            reset();
        } else {
            updateFinal();
            reset();
        }

    }//GEN-LAST:event_savebtnActionPerformed

    private void makebillbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_makebillbtnActionPerformed

        generateReport();

    }//GEN-LAST:event_makebillbtnActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        if (salenametxtfield.getText().length() == 0) {
            this.dispose();
        } else {
            String customername = salenametxtfield.getText().substring(0, 1).toUpperCase()
                    + salenametxtfield.getText().substring(1);
            int clicked = JOptionPane.showConfirmDialog(null, "Are you sure you make the bill for " + customername + " ?", "Inventory Management System",
                    JOptionPane.YES_NO_OPTION);
            if (clicked == 0) {
                this.dispose();
            }else{
                generateReport();
            }
        }
    }//GEN-LAST:event_formWindowClosing

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SaleFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel contactnolab;
    private javax.swing.JLabel contactnolab1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JButton makebillbtn;
    private javax.swing.JPanel middlepanel;
    private javax.swing.JLabel orgnamelab;
    private javax.swing.JLabel saleaddresslab;
    private javax.swing.JTextField saleaddresstxtfield;
    private javax.swing.JLabel saleamountperpiecelab;
    private javax.swing.JLabel saleavaiablequantitylab;
    private javax.swing.JTextField saleavaiablequantitytxtfield;
    private javax.swing.JComboBox salebrandcombobox;
    private javax.swing.JLabel salechoosebrandlab;
    private javax.swing.JLabel salechoosesupplierlab;
    private javax.swing.JLabel salecontactnolab;
    private javax.swing.JTextField salecontactnotxtfield;
    private javax.swing.JLabel saledate;
    private com.toedter.calendar.JDateChooser saleentrydate;
    private javax.swing.JLabel saleidlab;
    private javax.swing.JLabel salelab;
    private javax.swing.JLabel salelavaiableproduct;
    private javax.swing.JLabel salename;
    private javax.swing.JTextField salenametxtfield;
    private javax.swing.JComboBox saleproductcombobox;
    private javax.swing.JLabel salequantitypurchasedlab;
    private javax.swing.JTextField salequantitypurchasedtxtfield;
    private javax.swing.JTextField saleratetxtfield;
    private javax.swing.JComboBox salesuppliercombobox;
    private javax.swing.JTable saletbl;
    private javax.swing.JLabel saletotallab;
    private javax.swing.JTextField saletotaltxtfield;
    private javax.swing.JButton savebtn;
    private javax.swing.JPanel upperpanel;
    // End of variables declaration//GEN-END:variables
}
