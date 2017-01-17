package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.BrandDTO;
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

public class BrandDAO {

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

    public BrandDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void insertBrand(BrandDTO bdto) {
        try {
            String sql = "INSERT INTO brandtbl VALUES(?,?,?)";
            new DBConnection().getConnection();
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setInt(1, bdto.getBrandid());
            pstmt.setString(2, bdto.getBrandentrydate());
            pstmt.setString(3, bdto.getBrandname());

            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "INSERT", trimmedQuery, "Store",
                        "Brand");
                JOptionPane.showMessageDialog(null, "Record Inserted", "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }


public void updateBrand(BrandDTO bdto) {
        try {
            String sql = "UPDATE brandtbl SET brandid=?, brandentrydate=?, brandname=?"
                    + "WHERE brandid=?";
            pstmt = (PreparedStatement) con.prepareStatement(sql);

            pstmt.setInt(1, bdto.getBrandid());
            pstmt.setString(2, bdto.getBrandentrydate());
            pstmt.setString(3, bdto.getBrandname());
            pstmt.setInt(4, bdto.getBrandid());

            if (pstmt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "One Record Updated", "Home Management System",
                        JOptionPane.INFORMATION_MESSAGE);
                boolean flag = true;
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "UPDATE", trimmedQuery, "Store",
                        "Brand");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int brandId() {
        int brandid = 0;
        try {
            String query = " SELECT brandid FROM brandtbl\n"
                    + "ORDER BY brandid DESC\n"
                    + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                brandid = rs.getInt("brandid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return brandid;
    }

    public int countBrand() {
        int countbrand = 0;
        try {
            String query = "SELECT COUNT(brandid) FROM brandtbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countbrand = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        return countbrand;
    }

    public void deleteRows(String[] value) throws SQLException {
        try {

            String sql = " DELETE FROM  brandtbl WHERE brandid = ?";
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
                    "Brand");
        }
    }

    public ResultSet getbrandName() {
        try {
            String query = " SELECT brandname FROM brandtbl";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet search(String brandid, String brandname, String brandentrydate) {
        if (brandid != null) {
            try {
                String query = " SELECT *from brandtbl WHERE brandid = " + brandid + " ";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (brandname != null) {
            try {
                String query = " SELECT *FROM brandtbl WHERE brandname = '" + brandname + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (brandentrydate != null) {

            try {
                String query = " SELECT *FROM brandtbl WHERE brandentrydate = '" + brandentrydate + "' ";
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
            String query = "SELECT *FROM brandtbl WHERE brandid LIKE '" + search + "%' OR brandname LIKE '" + search + "%'"
                    + "OR brandentrydate LIKE '" + search + "%'";
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
        String query = "SELECT  *FROM brandtbl";
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
