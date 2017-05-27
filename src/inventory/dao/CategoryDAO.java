package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.CategoryDTO;
import inventory.other.LogWriting;
import inventory.ui.ITDashboard;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CategoryDAO {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    // mapping the table of database
    String mappedColumnNames[] = {
        "Id",
        "Entry Date",
        "Name"};

    public CategoryDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        } finally {
            try {
                if (con != null) {
                    //con.close();
                }
            } catch (Exception e) {
            }
        }
    }

    public void insertCategory(CategoryDTO cdto) {
        try {
            String sql = "INSERT INTO categorytbl VALUES(?,?,?)";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, cdto.getCatid());
            pstmt.setString(2, cdto.getCatentrydate());
            pstmt.setString(3, cdto.getCatname());

            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "INSERT", trimmedQuery, "Store",
                          "Category");
                JOptionPane.showMessageDialog(null, "Record Inserted", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void updateCategory(CategoryDTO cdto) {
        try {
            String sql = "UPDATE categorytbl SET catid=?, catentrydate=?, catname=?"
                      + "WHERE catid=?";
            pstmt = (PreparedStatement)con.prepareStatement(sql);

            pstmt.setInt(1, cdto.getCatid());
            pstmt.setString(2, cdto.getCatentrydate());
            pstmt.setString(3, cdto.getCatname());
            pstmt.setInt(4, cdto.getCatid());

            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "UPDATE", trimmedQuery, "Store",
                          "Category");
                JOptionPane.showMessageDialog(null, "One Record Updated", "Home Management System",
                          JOptionPane.INFORMATION_MESSAGE);
                boolean flag = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int catId() {
        int brandid = 0;
        try {
            String query = " SELECT catid FROM categorytbl\n"
                      + "ORDER BY catid DESC\n"
                      + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                brandid = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return brandid;
    }

    public int countCategory() {
        int countcategory = 0;
        try {
            String query = "SELECT COUNT(catid) FROM categorytbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countcategory = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);

        }
        return countcategory;
    }

    public void deleteRows(String[] value) throws SQLException {
        try {

            String sql = " DELETE FROM  categorytbl WHERE catid = ?";
            //String reservedquery = "UPDATE particulartbl SET pname = 'reserved ' WHERE pid=?";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, value[0]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        if (pstmt.executeUpdate() == 1) {
            String query = pstmt.toString();
            String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
            new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "DELETE", trimmedQuery, "Store",
                      "Category");
        }
    }

    public ResultSet getcatgeoryName() {
        try {
            String query = " SELECT catname FROM categorytbl";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet search(String catid, String catname, String catentrydate) {
        if (catid != null) {
            try {
                String query = " SELECT *from categorytbl WHERE catid = '" + catid + "' ";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (catname != null) {

            try {
                System.out.println(catname);
                String query = " SELECT *FROM categorytbl WHERE catname = '" + catname + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (catentrydate != null) {

            try {
                String query = " SELECT *FROM categorytbl WHERE catentrydate = '" + catentrydate + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        }
        return rs;
    }

    public ResultSet quickSearch(String search) {

        try {
            String query = "SELECT *FROM categorytbl WHERE catid LIKE '" + search + "%' OR catname LIKE '" + search + "%'"
                      + "OR catentrydate LIKE '" + search + "%'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }

        return rs;
    }

    public DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {

            columnNames.add(mappedColumnNames[column - 1]);
        }

        Vector<Vector<Object>> data = new Vector<Vector<Object>>();

        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();

            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }

    public ResultSet getQueryResult() {
        String query = "SELECT  *FROM categorytbl";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

}
