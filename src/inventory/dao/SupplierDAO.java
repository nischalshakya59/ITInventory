package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.SupplierDTO;
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

public class SupplierDAO {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    // mapping the table of database
    String mappedColumnNames[] = {
        "Id",
        "Entry Date",
        "Name",
        "Contact No",
        "Address",
        "Email",
        "Description"};

    public SupplierDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean insertSupplier(SupplierDTO supplierdto) {
        boolean insert = false;
        try {
            String sql = "INSERT INTO suppliertbl VALUES(?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, supplierdto.getSupplierid());
            pstmt.setString(2, supplierdto.getSupplierentrydate());
            pstmt.setString(3, supplierdto.getSuppliername());
            pstmt.setString(4, supplierdto.getSuppliercontactno());
            pstmt.setString(5, supplierdto.getSupplieraddress());
            pstmt.setString(6, supplierdto.getSupplieremailaddress());
            pstmt.setString(7, supplierdto.getSupplierdescription());
            if (pstmt.executeUpdate() == 1) {

                JOptionPane.showMessageDialog(null, "Record Inserted", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
                insert = true;
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "INSERT", trimmedQuery, "Store",
                          "Supplier");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return insert;
    }

    public boolean updateSupplier(SupplierDTO supplierdto) {
        boolean update = false;
        try {
            String sql = "UPDATE suppliertbl SET supplierid=?, supplierentrydate=?, suppliername=?,"
                      + " suppliercontactno = ?, supplieraddress = ?, supplieremailaddress = ?,"
                      + "supplierdescription = ? WHERE supplierid=?";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, supplierdto.getSupplierid());
            pstmt.setString(2, supplierdto.getSupplierentrydate());
            pstmt.setString(3, supplierdto.getSuppliername());
            pstmt.setString(4, supplierdto.getSuppliercontactno());
            pstmt.setString(5, supplierdto.getSupplieraddress());
            pstmt.setString(6, supplierdto.getSupplieremailaddress());
            pstmt.setString(7, supplierdto.getSupplierdescription());
            pstmt.setInt(8, supplierdto.getSupplierid());

            if (pstmt.executeUpdate() == 1) {
                JOptionPane.showMessageDialog(null, "One Record Updated", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);

                update = true;
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "UPDATE", trimmedQuery, "Store",
                          "Supplier");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return update;
    }

    public int supplierId() {
        int supplierid = 0;
        try {
            String query = " SELECT supplierid FROM suppliertbl\n"
                      + "ORDER BY supplierid DESC\n"
                      + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                supplierid = rs.getInt("supplierid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return supplierid;
    }

    public int countSupplier() {
        int countsupplier = 0;
        try {
            String query = "SELECT COUNT(supplierid) FROM suppliertbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countsupplier = rs.getInt(1);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return countsupplier;
    }

    public void deleteRows(String[] value) throws SQLException {
        String query = " DELETE FROM  suppliertbl WHERE supplierid = ?";
        try {

            //String reservedquery = "UPDATE particulartbl SET pname = 'reserved ' WHERE pid=?";
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, value[0]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        if (pstmt.executeUpdate() == 1) {
            String sql = pstmt.toString();
            String trimmedQuery = sql.substring(sql.indexOf(":") + 2, sql.length());
            new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "DELETE", trimmedQuery, "Store",
                      "Supplier");
        }

    }

    public ResultSet getsupplierName() {
        try {
            String query = " SELECT suppliername FROM suppliertbl";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet search(String supplierid, String suppliername, String supplierentrydate,
              String suppliercontactno, String supplieraddress, String supplieremailaddress) {
        if (supplierid != null) {
            try {
                String query = " SELECT *from suppliertbl WHERE supplierid = " + supplierid + " ";

                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }

        } else if (suppliername != null) {
            try {
                String query = " SELECT *FROM suppliertbl WHERE suppliername = '" + suppliername + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (supplierentrydate != null) {

            try {
                String query = " SELECT *FROM suppliertbl WHERE supplierentrydate = '" + supplierentrydate + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (supplieraddress != null) {

            try {
                String query = " SELECT *FROM suppliertbl WHERE supplieraddress = '" + supplieraddress + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (suppliercontactno != null) {

            try {
                String query = " SELECT *FROM suppliertbl WHERE suppliercontactno = '" + suppliercontactno + "' ";
                stmt = con.createStatement();
                rs = stmt.executeQuery(query);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
            }
        } else if (supplieremailaddress != null) {

            try {
                String query = " SELECT *FROM suppliertbl WHERE supplieremailaddress = '" + supplieremailaddress + "' ";
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
            String query = "SELECT *FROM suppliertbl WHERE supplierid LIKE '" + search + "%' OR suppliername LIKE '" + search + "%'"
                      + "OR supplierentrydate LIKE '" + search + "%' OR supplieraddress LIKE '" + search + "%' "
                      + "OR supplieremailaddress LIKE '" + search + "%' OR suppliercontactno LIKE '" + search + "%'";
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
        String query = "SELECT  *FROM suppliertbl";
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
