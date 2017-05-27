package inventory.ui;

import inventory.dao.BrandDAO;
import inventory.dao.CategoryDAO;
import inventory.dao.EmployeeDAO;
import inventory.dao.ProductDAO;
import inventory.dao.SupplierDAO;
import inventory.db.DBConnection;
import inventory.ui.panel.AboutUsPanel;
import inventory.ui.panel.EmployeePanel;
import inventory.ui.panel.AllRoundPanel;
import inventory.ui.panel.SalePanel;
import inventory.ui.panel.SettingPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ITDashboard extends javax.swing.JFrame {

    public ITDashboard() {
        initComponents();
        allroundpanel.removeAll();
        allroundpanel.add(orginfopanel);
        allroundpanel.repaint();
        allroundpanel.revalidate();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        this.setExtendedState(this.MAXIMIZED_BOTH);
        this.setTitle("Inventory Management System");
        generalInformation();
    }

    ProductDAO productdao = new ProductDAO();

    SupplierDAO supplierdao = new SupplierDAO();

    BrandDAO branddao = new BrandDAO();

    CategoryDAO categorydao = new CategoryDAO();

    DBConnection dbconnection = new DBConnection();

    public static boolean show = true;

    ResultSet rs = null;

    public void visible() {
        show = true;
    }

    public void setUsername(String uname) {
        String username = uname.substring(0, 1).toUpperCase() + uname.substring(1);
        usernamelab.setText(username);
    }

    public void killConnection() {
        int i;
        for (i = 1; i < dbconnection.noofConnection(); i++) {
            dbconnection.killConnection();
        }
    }

    public void generalInformation() {
        try {
            productlab.setText(String.valueOf(productdao.countProduct()));
            categorylab.setText(String.valueOf(categorydao.countCategory()));
            supplierlab.setText(String.valueOf(supplierdao.countSupplier()));
            brandlab.setText(String.valueOf(branddao.countBrand()));
            employeelab.setText(String.valueOf(new EmployeeDAO().countEmployee()));
        } catch (Exception e) {
        } finally {
            try {
                dbconnection.getConnection().close();
            } catch (Exception e) {
            }
        }

    }

//    public void setUserInfo() {
//        String username = usernamelab.getText();
//        try {
//            rs = new EmployeeDAO().getUserProfile(username);
//            while (rs.next()) {
//                String userinfofname = rs.getString(1);
//                String userinfolname = rs.getString(2);
//                String userinfocontact = rs.getString(3);
//                String userinfousername = rs.getString(4);
//                String usertype = rs.getString(6);
//                System.out.println(userinfofname);
//                System.out.println(userinfolname);
//                System.out.println(userinfocontact);
//                System.out.println(userinfousername);
//                new UserProfileFrame().setUserInfo(userinfofname, userinfolname, userinfocontact, userinfousername, usertype);
//
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
//        }
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        upperpanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logouticonlab = new javax.swing.JLabel();
        usernamelab = new javax.swing.JLabel();
        leftpanel = new javax.swing.JPanel();
        homepanel = new javax.swing.JPanel();
        homeiconlab = new javax.swing.JLabel();
        homebreaklab = new javax.swing.JLabel();
        aboutuslab = new javax.swing.JPanel();
        aboutusicon = new javax.swing.JLabel();
        aboutusbreak = new javax.swing.JLabel();
        storepanel = new javax.swing.JPanel();
        storeiconlab = new javax.swing.JLabel();
        storebreaklab = new javax.swing.JLabel();
        salepanel = new javax.swing.JPanel();
        saleiconlab = new javax.swing.JLabel();
        salebreaklab = new javax.swing.JLabel();
        employeepanel = new javax.swing.JPanel();
        employeeiconlab = new javax.swing.JLabel();
        employeebreaklab = new javax.swing.JLabel();
        settingpanel = new javax.swing.JPanel();
        settingiconlab = new javax.swing.JLabel();
        settingbreaklab = new javax.swing.JLabel();
        lowerpanel = new javax.swing.JPanel();
        copyrightlab = new javax.swing.JLabel();
        allroundpanel = new javax.swing.JPanel();
        orginfopanel = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        labpanel = new javax.swing.JPanel();
        selectedlab = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        orgmaininfopanel = new javax.swing.JPanel();
        orgnamelab = new javax.swing.JLabel();
        oragaddresslab = new javax.swing.JLabel();
        orgphonenolab = new javax.swing.JLabel();
        totalstocklab = new javax.swing.JLabel();
        totalsupplierlab = new javax.swing.JLabel();
        totalbrandlab = new javax.swing.JLabel();
        totalcategorylab = new javax.swing.JLabel();
        totalemployeelab = new javax.swing.JLabel();
        productlab = new javax.swing.JLabel();
        supplierlab = new javax.swing.JLabel();
        categorylab = new javax.swing.JLabel();
        brandlab = new javax.swing.JLabel();
        employeelab = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        upperpanel.setBackground(new java.awt.Color(102, 153, 255));
        upperpanel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        upperpanel.setPreferredSize(new java.awt.Dimension(790, 75));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IT Inventory Management System");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Welcome ");

        logouticonlab.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        logouticonlab.setForeground(new java.awt.Color(255, 255, 255));
        logouticonlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logouticonlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/out.png"))); // NOI18N
        logouticonlab.setText("Log Out");
        logouticonlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logouticonlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logouticonlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                logouticonlabMouseReleased(evt);
            }
        });

        usernamelab.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        usernamelab.setForeground(new java.awt.Color(255, 255, 255));
        usernamelab.setText("usernamelab");

        javax.swing.GroupLayout upperpanelLayout = new javax.swing.GroupLayout(upperpanel);
        upperpanel.setLayout(upperpanelLayout);
        upperpanelLayout.setHorizontalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logouticonlab, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        upperpanelLayout.setVerticalGroup(
            upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(upperpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(upperpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logouticonlab, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(upperpanel, java.awt.BorderLayout.PAGE_START);

        leftpanel.setBackground(new java.awt.Color(102, 102, 102));
        leftpanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        leftpanel.setPreferredSize(new java.awt.Dimension(120, 500));

        homepanel.setBackground(new java.awt.Color(51, 51, 51));
        homepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        homeiconlab.setBackground(new java.awt.Color(255, 255, 255));
        homeiconlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        homeiconlab.setForeground(new java.awt.Color(255, 255, 255));
        homeiconlab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        homeiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/home.png"))); // NOI18N
        homeiconlab.setText("  Home");
        homeiconlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeiconlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeiconlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                homeiconlabMouseReleased(evt);
            }
        });

        homebreaklab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        homebreaklab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        homebreaklab.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout homepanelLayout = new javax.swing.GroupLayout(homepanel);
        homepanel.setLayout(homepanelLayout);
        homepanelLayout.setHorizontalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homebreaklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        homepanelLayout.setVerticalGroup(
            homepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homepanelLayout.createSequentialGroup()
                .addComponent(homeiconlab, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(homebreaklab, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        aboutuslab.setBackground(new java.awt.Color(51, 51, 51));
        aboutuslab.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        aboutusicon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aboutusicon.setForeground(new java.awt.Color(255, 255, 255));
        aboutusicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/aboutus.png"))); // NOI18N
        aboutusicon.setText(" About Us");
        aboutusicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                aboutusiconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                aboutusiconMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                aboutusiconMouseReleased(evt);
            }
        });

        aboutusbreak.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aboutusbreak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        aboutusbreak.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout aboutuslabLayout = new javax.swing.GroupLayout(aboutuslab);
        aboutuslab.setLayout(aboutuslabLayout);
        aboutuslabLayout.setHorizontalGroup(
            aboutuslabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutuslabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(aboutuslabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aboutusbreak, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aboutusicon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        aboutuslabLayout.setVerticalGroup(
            aboutuslabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(aboutuslabLayout.createSequentialGroup()
                .addComponent(aboutusicon, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(aboutusbreak, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        storepanel.setBackground(new java.awt.Color(51, 51, 51));
        storepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        storeiconlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        storeiconlab.setForeground(new java.awt.Color(255, 255, 255));
        storeiconlab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        storeiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/store.png"))); // NOI18N
        storeiconlab.setText(" Store");
        storeiconlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                storeiconlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                storeiconlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                storeiconlabMouseReleased(evt);
            }
        });

        storebreaklab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        storebreaklab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        storebreaklab.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout storepanelLayout = new javax.swing.GroupLayout(storepanel);
        storepanel.setLayout(storepanelLayout);
        storepanelLayout.setHorizontalGroup(
            storepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(storepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(storebreaklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        storepanelLayout.setVerticalGroup(
            storepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storepanelLayout.createSequentialGroup()
                .addComponent(storeiconlab)
                .addGap(0, 0, 0)
                .addComponent(storebreaklab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
        );

        salepanel.setBackground(new java.awt.Color(51, 51, 51));
        salepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        saleiconlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saleiconlab.setForeground(new java.awt.Color(255, 255, 255));
        saleiconlab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saleiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/sale.png"))); // NOI18N
        saleiconlab.setText(" Sale");
        saleiconlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saleiconlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saleiconlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                saleiconlabMouseReleased(evt);
            }
        });

        salebreaklab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salebreaklab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        salebreaklab.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout salepanelLayout = new javax.swing.GroupLayout(salepanel);
        salepanel.setLayout(salepanelLayout);
        salepanelLayout.setHorizontalGroup(
            salepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(salepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salebreaklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(saleiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        salepanelLayout.setVerticalGroup(
            salepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salepanelLayout.createSequentialGroup()
                .addComponent(saleiconlab)
                .addGap(0, 0, 0)
                .addComponent(salebreaklab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
        );

        employeepanel.setBackground(new java.awt.Color(51, 51, 51));
        employeepanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        employeeiconlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        employeeiconlab.setForeground(new java.awt.Color(255, 255, 255));
        employeeiconlab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        employeeiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/employee.png"))); // NOI18N
        employeeiconlab.setText(" Employee");
        employeeiconlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                employeeiconlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                employeeiconlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                employeeiconlabMouseReleased(evt);
            }
        });

        employeebreaklab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        employeebreaklab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        employeebreaklab.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout employeepanelLayout = new javax.swing.GroupLayout(employeepanel);
        employeepanel.setLayout(employeepanelLayout);
        employeepanelLayout.setHorizontalGroup(
            employeepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeepanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(employeepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeebreaklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeeiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)))
        );
        employeepanelLayout.setVerticalGroup(
            employeepanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeepanelLayout.createSequentialGroup()
                .addComponent(employeeiconlab)
                .addGap(0, 0, 0)
                .addComponent(employeebreaklab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
        );

        settingpanel.setBackground(new java.awt.Color(51, 51, 51));
        settingpanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));

        settingiconlab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        settingiconlab.setForeground(new java.awt.Color(255, 255, 255));
        settingiconlab.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        settingiconlab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/setting.png"))); // NOI18N
        settingiconlab.setText(" Setting");
        settingiconlab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                settingiconlabMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                settingiconlabMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                settingiconlabMouseReleased(evt);
            }
        });

        settingbreaklab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settingbreaklab.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/break.png"))); // NOI18N
        settingbreaklab.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout settingpanelLayout = new javax.swing.GroupLayout(settingpanel);
        settingpanel.setLayout(settingpanelLayout);
        settingpanelLayout.setHorizontalGroup(
            settingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(settingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(settingbreaklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(settingiconlab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        settingpanelLayout.setVerticalGroup(
            settingpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(settingpanelLayout.createSequentialGroup()
                .addComponent(settingiconlab)
                .addGap(0, 0, 0)
                .addComponent(settingbreaklab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout leftpanelLayout = new javax.swing.GroupLayout(leftpanel);
        leftpanel.setLayout(leftpanelLayout);
        leftpanelLayout.setHorizontalGroup(
            leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftpanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(aboutuslab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(salepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(employeepanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(settingpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        leftpanelLayout.setVerticalGroup(
            leftpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, leftpanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(homepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(storepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(salepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(employeepanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(settingpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(aboutuslab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );

        getContentPane().add(leftpanel, java.awt.BorderLayout.LINE_START);

        lowerpanel.setBackground(new java.awt.Color(204, 204, 204));
        lowerpanel.setPreferredSize(new java.awt.Dimension(790, 40));

        copyrightlab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        copyrightlab.setText("Copyright @ 2016 ");

        javax.swing.GroupLayout lowerpanelLayout = new javax.swing.GroupLayout(lowerpanel);
        lowerpanel.setLayout(lowerpanelLayout);
        lowerpanelLayout.setHorizontalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lowerpanelLayout.createSequentialGroup()
                .addContainerGap(431, Short.MAX_VALUE)
                .addComponent(copyrightlab)
                .addContainerGap(415, Short.MAX_VALUE))
        );
        lowerpanelLayout.setVerticalGroup(
            lowerpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lowerpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(copyrightlab, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(lowerpanel, java.awt.BorderLayout.PAGE_END);

        allroundpanel.setBackground(new java.awt.Color(255, 255, 255));
        allroundpanel.setLayout(new java.awt.CardLayout());

        orginfopanel.setBackground(new java.awt.Color(255, 255, 255));

        labpanel.setBackground(new java.awt.Color(102, 102, 102));

        selectedlab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        selectedlab.setForeground(new java.awt.Color(255, 255, 255));
        selectedlab.setText("Home");

        javax.swing.GroupLayout labpanelLayout = new javax.swing.GroupLayout(labpanel);
        labpanel.setLayout(labpanelLayout);
        labpanelLayout.setHorizontalGroup(
            labpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedlab, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        labpanelLayout.setVerticalGroup(
            labpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(labpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectedlab, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                .addContainerGap())
        );

        orgmaininfopanel.setBackground(new java.awt.Color(255, 255, 255));

        orgnamelab.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        orgnamelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgnamelab.setText("Sankata Info Sys");

        oragaddresslab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        oragaddresslab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        oragaddresslab.setText("01- 4255678");

        orgphonenolab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        orgphonenolab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        orgphonenolab.setText("New Road, Kathmandu");

        totalstocklab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalstocklab.setText("Total Product :-");

        totalsupplierlab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalsupplierlab.setText("Total Supplier :-");

        totalbrandlab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalbrandlab.setText("Total Brand :-");

        totalcategorylab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalcategorylab.setText("Total Category :-");

        totalemployeelab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        totalemployeelab.setText("Total Employee :-");

        productlab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        productlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        supplierlab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        supplierlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        categorylab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        categorylab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        brandlab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        brandlab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        employeelab.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        employeelab.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/inventory/images/logo.png"))); // NOI18N

        javax.swing.GroupLayout orgmaininfopanelLayout = new javax.swing.GroupLayout(orgmaininfopanel);
        orgmaininfopanel.setLayout(orgmaininfopanelLayout);
        orgmaininfopanelLayout.setHorizontalGroup(
            orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                                .addGap(154, 154, 154)
                                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(totalemployeelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(totalcategorylab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orgmaininfopanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(totalbrandlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalsupplierlab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(totalstocklab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(productlab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(brandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(categorylab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(employeelab, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(orgnamelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orgmaininfopanelLayout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(oragaddresslab)
                .addGap(18, 18, 18)
                .addComponent(orgphonenolab)
                .addGap(101, 101, 101))
        );
        orgmaininfopanelLayout.setVerticalGroup(
            orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orgmaininfopanelLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(orgnamelab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(oragaddresslab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(orgphonenolab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addComponent(totalstocklab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalsupplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addComponent(productlab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(supplierlab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(orgmaininfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addComponent(totalbrandlab, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(totalcategorylab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalemployeelab, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orgmaininfopanelLayout.createSequentialGroup()
                        .addComponent(brandlab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(categorylab, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(employeelab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );

        orgmaininfopanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {oragaddresslab, totalbrandlab, totalcategorylab, totalemployeelab, totalstocklab, totalsupplierlab});

        javax.swing.GroupLayout orginfopanelLayout = new javax.swing.GroupLayout(orginfopanel);
        orginfopanel.setLayout(orginfopanelLayout);
        orginfopanelLayout.setHorizontalGroup(
            orginfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 829, Short.MAX_VALUE)
            .addComponent(labpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(orginfopanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(orginfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, orginfopanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(orgmaininfopanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        orginfopanelLayout.setVerticalGroup(
            orginfopanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orginfopanelLayout.createSequentialGroup()
                .addComponent(labpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orgmaininfopanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        allroundpanel.add(orginfopanel, "card2");

        getContentPane().add(allroundpanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void storeiconlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeiconlabMouseReleased
        allroundpanel.removeAll();
        allroundpanel.add(new AllRoundPanel());
        allroundpanel.repaint();
        allroundpanel.revalidate();

    }//GEN-LAST:event_storeiconlabMouseReleased

    private void homeiconlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconlabMouseEntered
        homeiconlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_homeiconlabMouseEntered

    private void homeiconlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconlabMouseExited
        homeiconlab.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_homeiconlabMouseExited

    private void storeiconlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeiconlabMouseEntered
        storeiconlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_storeiconlabMouseEntered

    private void storeiconlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_storeiconlabMouseExited
        storeiconlab.setForeground(java.awt.Color.WHITE);
    }//GEN-LAST:event_storeiconlabMouseExited

    private void homeiconlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeiconlabMouseReleased
        allroundpanel.removeAll();
        allroundpanel.add(orginfopanel);
        allroundpanel.repaint();
        allroundpanel.revalidate();
        generalInformation();
    }//GEN-LAST:event_homeiconlabMouseReleased

    private void saleiconlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saleiconlabMouseReleased
        allroundpanel.removeAll();
        allroundpanel.add(new SalePanel());
        allroundpanel.repaint();
        allroundpanel.revalidate();
    }//GEN-LAST:event_saleiconlabMouseReleased

    private void employeeiconlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeiconlabMouseReleased
        allroundpanel.removeAll();
        allroundpanel.add(new EmployeePanel());
        allroundpanel.revalidate();
        allroundpanel.repaint();

    }//GEN-LAST:event_employeeiconlabMouseReleased

    private void employeeiconlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeiconlabMouseEntered
        employeeiconlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_employeeiconlabMouseEntered

    private void employeeiconlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_employeeiconlabMouseExited
        // TODO add your handling code here:
        employeeiconlab.setForeground(Color.WHITE);
    }//GEN-LAST:event_employeeiconlabMouseExited

    private void saleiconlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saleiconlabMouseEntered
        // TODO add your handling code here:
        saleiconlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_saleiconlabMouseEntered

    private void saleiconlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saleiconlabMouseExited
        // TODO add your handling code here:
        saleiconlab.setForeground(Color.WHITE);
    }//GEN-LAST:event_saleiconlabMouseExited

    private void settingiconlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingiconlabMouseEntered
        // TODO add your handling code here:
        settingiconlab.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_settingiconlabMouseEntered

    private void settingiconlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingiconlabMouseExited
        // TODO add your handling code here:
        settingiconlab.setForeground(Color.WHITE);

    }//GEN-LAST:event_settingiconlabMouseExited

    private void aboutusiconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutusiconMouseEntered
        // TODO add your handling code here:.
        aboutusicon.setForeground(new java.awt.Color(153, 153, 153));
    }//GEN-LAST:event_aboutusiconMouseEntered

    private void aboutusiconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutusiconMouseExited
        // TODO add your handling code here:
        aboutusicon.setForeground(Color.WHITE);
    }//GEN-LAST:event_aboutusiconMouseExited

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        this.setIconImage(new ImageIcon(getClass().getResource("icon.png")).getImage());
    }//GEN-LAST:event_formWindowOpened

    private void settingiconlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingiconlabMouseReleased
        allroundpanel.removeAll();
        allroundpanel.add(new SettingPanel());
        allroundpanel.revalidate();
        allroundpanel.repaint();

    }//GEN-LAST:event_settingiconlabMouseReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(null, "Press logout to exit", "Inventory Management System",
                  JOptionPane.INFORMATION_MESSAGE);

    }//GEN-LAST:event_formWindowClosing

    private void logouticonlabMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logouticonlabMouseReleased
        int clicked = JOptionPane.showConfirmDialog(null,
                  "Make sure you save all the record  \n Are you sure you want to logout?",
                  "Inevntory Management System",
                  JOptionPane.YES_NO_OPTION);
        if (clicked == 0) {
            this.dispose();
            LoginFrame lf = new LoginFrame();
            lf.pack();
            lf.setLocationRelativeTo(null);
            lf.setVisible(true);
        }
    }//GEN-LAST:event_logouticonlabMouseReleased

    private void logouticonlabMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logouticonlabMouseEntered
        logouticonlab.setForeground(Color.GRAY);
    }//GEN-LAST:event_logouticonlabMouseEntered

    private void logouticonlabMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logouticonlabMouseExited
        logouticonlab.setForeground(Color.WHITE);
    }//GEN-LAST:event_logouticonlabMouseExited

    private void aboutusiconMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutusiconMouseReleased
        // TODO add your handling code here:
        allroundpanel.removeAll();
        allroundpanel.add(new AboutUsPanel());
        allroundpanel.revalidate();
        allroundpanel.repaint();
    }//GEN-LAST:event_aboutusiconMouseReleased

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ITDashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ITDashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ITDashboard.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ITDashboard.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ITDashboard().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel aboutusbreak;
    private javax.swing.JLabel aboutusicon;
    private javax.swing.JPanel aboutuslab;
    private javax.swing.JPanel allroundpanel;
    private javax.swing.JLabel brandlab;
    private javax.swing.JLabel categorylab;
    private javax.swing.JLabel copyrightlab;
    private javax.swing.JLabel employeebreaklab;
    private javax.swing.JLabel employeeiconlab;
    private javax.swing.JLabel employeelab;
    private javax.swing.JPanel employeepanel;
    private javax.swing.JLabel homebreaklab;
    private javax.swing.JLabel homeiconlab;
    private javax.swing.JPanel homepanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel labpanel;
    private javax.swing.JPanel leftpanel;
    private javax.swing.JLabel logouticonlab;
    private javax.swing.JPanel lowerpanel;
    private javax.swing.JLabel oragaddresslab;
    private javax.swing.JPanel orginfopanel;
    private javax.swing.JPanel orgmaininfopanel;
    private javax.swing.JLabel orgnamelab;
    private javax.swing.JLabel orgphonenolab;
    private javax.swing.JLabel productlab;
    private javax.swing.JLabel salebreaklab;
    private javax.swing.JLabel saleiconlab;
    private javax.swing.JPanel salepanel;
    private javax.swing.JLabel selectedlab;
    private javax.swing.JLabel settingbreaklab;
    private javax.swing.JLabel settingiconlab;
    private javax.swing.JPanel settingpanel;
    private javax.swing.JLabel storebreaklab;
    private javax.swing.JLabel storeiconlab;
    private javax.swing.JPanel storepanel;
    private javax.swing.JLabel supplierlab;
    private javax.swing.JLabel totalbrandlab;
    private javax.swing.JLabel totalcategorylab;
    private javax.swing.JLabel totalemployeelab;
    private javax.swing.JLabel totalstocklab;
    private javax.swing.JLabel totalsupplierlab;
    private javax.swing.JPanel upperpanel;
    public static javax.swing.JLabel usernamelab;
    // End of variables declaration//GEN-END:variables
}
