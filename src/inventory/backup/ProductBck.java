package inventory.backup;

import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ProductBck {

    Connection con;
    Statement stmt;
    ResultSet rs;

    String select = "SELECT 'productid','productentrydate','productsuppliername','productmodel',"
            + "'productbrand','productcategory','productqty', 'productsellingprice', 'productcostprice',"
            + "'productprofit', 'productwarranty', 'productspecification'";

    public void producttblBck(BckRestoreDTO brd) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {
                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext,
                        "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void producttblbckEntryDate(BckRestoreDTO brd, String entrydate) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productentrydate = '" + entrydate + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void producttblbckSupplierName(BckRestoreDTO brd, String productsuppliername) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productsuppliername = '" + productsuppliername + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void producttblbckmodel(BckRestoreDTO brd, String productmodel) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productmodel = '" + productmodel + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void producttblBrand(BckRestoreDTO brd, String productbrand) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productbrand = '" + productbrand + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void producttblbckCategory(BckRestoreDTO brd, String productcategory) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productcategory = '" + productcategory + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void producttblbckQuantity(BckRestoreDTO brd, String productqty) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productqty = " + productqty + ""
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void producttblbckSellingPrice(BckRestoreDTO brd, String productsellingprice) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productsellingprice = " + productsellingprice + ""
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void producttblbckCostPrice(BckRestoreDTO brd, String productcostprice) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productcostprice = " + productcostprice + ""
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void producttblbckProfit(BckRestoreDTO brd, String productprofit) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productprofit = " + productprofit + ""
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void producttblbckWarranty(BckRestoreDTO brd, String productwarranty) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(" + select + ") \n"
                    + "UNION(SELECT *FROM producttbl WHERE productwarranty = '" + productwarranty + "'"
                    + " INTO OUTFILE"
                    + " '" + directory + "/" + fname + "" + ext + " ' \n"
                    + "FIELDS TERMINATED BY ',' ESCAPED BY '\' LINES TERMINATED BY '\r\n');";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Created Sucessfully " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                int clicked = JOptionPane.showConfirmDialog(null, "View The Content?", "View Conformation", JOptionPane.YES_NO_OPTION);

                if (clicked == 0) {

                    Runtime.getRuntime().exec("cmd /c start " + directory + "\\" + fname + "" + ext + "");

                }

            } else {
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
