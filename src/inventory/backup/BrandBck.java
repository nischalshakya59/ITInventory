package inventory.backup;

import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class BrandBck {

    Connection con;
    Statement stmt;
    ResultSet rs;

    public void brandtblBck(BckRestoreDTO brd) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(SELECT 'brandid','brandentrydate','brandname') \n"
                    + "UNION(SELECT brandid,brandentrydate,brandname FROM brandtbl INTO OUTFILE"
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
                JOptionPane.showMessageDialog(null, "Failed To Create Backup " + fname + ext,
                        "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void brandtblbckDate(BckRestoreDTO brd, String date) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(SELECT 'brandid','brandentrydate','brandname') \n"
                    + "UNION(SELECT brandid,brandentrydate,brandname FROM brandtbl WHERE brandentrydate = '" + date + "'"
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

    public void brandtblbckBrandname(BckRestoreDTO brd, String brandname) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();
            String ext = ".csv";

            String query = "(SELECT 'brandid','brandentrydate','brandname') \n"
                    + "UNION(SELECT brandid,brandentrydate,brandname FROM brandtbl WHERE brandname = '" + brandname + "'"
                    + "INTO OUTFILE"
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
