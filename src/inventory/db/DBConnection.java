package inventory.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class DBConnection {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    public Connection getConnection() throws SQLException {
        if (con != null) {
            return con;
        }
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("DBCOnnected");
        } catch (Exception e) {
            System.out.println("Driver not loaded");
        }

        String url = "jdbc:mysql://localhost:3306/inventorymgmt";
        String user = "root";
        String password = "nischal";

        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("connection created");
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
//                      JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();

        }
        return con;
    }

    public DBConnection() {
        try {
            //        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventorymgmt", "root", "nischal");
//
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
//                      JOptionPane.INFORMATION_MESSAGE);
//        }
            getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int noofConnection() {
        int processid = 0;
        try {
            String countProcessID = "SELECT COUNT(ID) FROM information_schema.processlist WHERE DB = 'inventorymgmt'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(countProcessID);
            while (rs.next()) {
                processid = rs.getInt(1);
                System.out.println("ID of inventory mgmt " + processid);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return processid;
    }

    public void killConnection() {
        try {
            int killid = 0;
            Set<Integer> treeset = new TreeSet<>();
            stmt = con.createStatement();
            String querykillselect = "SELECT *FROM information_schema.processlist WHERE DB = 'inventorymgmt'";

            rs = stmt.executeQuery(querykillselect);
            while (rs.next()) {
                killid = rs.getInt(1);
                treeset.add(killid);
            }
            for (int id : treeset) {
                System.out.println(id);
                String kill = "KILL " + id + "";
                stmt = con.prepareStatement(kill);
                stmt.executeQuery(kill);
//                System.out.println(stmt.executeQuery(kill));

            }

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
//                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean checkLogin(String username, String password, String usertype) {
        try {
            String query = "SELECT *FROM logintbl WHERE username = ? AND password=? AND usertype = ?";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, usertype);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
        return flag;
    }

    public boolean verifyLogin(String username, String password) {
        try {
            String query = "SELECT *FROM logintbl WHERE username = ? AND password=? ";
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
        return flag;
    }

    public void updateLogin(String username, String password, String currentusername) {
        try {
            String query = "UPDATE  logintbl  SET username = '" + username + "' , password='" + password + "' "
                      + "WHERE username = '" + currentusername + "'";
            stmt = con.createStatement();

            if (stmt.executeUpdate(query) >= 1) {
                JOptionPane.showMessageDialog(null, "Username And Password Change Sucessfully",
                          "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
                JOptionPane.showMessageDialog(null, "Please Login Again To Make Changes", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
//        new DBConnection().getConnection();
//        new DBConnection().killConnection();
//        new DBConnection().noofConnection();

    }
}
