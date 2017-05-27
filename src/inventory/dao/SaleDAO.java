package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.SaleDTO;
import inventory.other.LogWriting;
import inventory.ui.ITDashboard;
import inventory.ui.frame.SaleFrame;
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

public class SaleDAO {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    // mapping the table of database
    String mappedColumnNames[] = {
        "Id",
        "Date",
        "Customer Name",
        "Customer Address",
        "Customer Contact",
        "Supplier",
        "Brand",
        "Product",
        "Quantiy",
        "Amount Per Piece",
        "Total"
    };

    public SaleDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean insertsale(SaleDTO saledto) {
        boolean insert = false;
        try {
            String sql = "INSERT INTO saletbl VALUES(?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, saledto.getSaleid());
            pstmt.setString(2, saledto.getSaledate());
            pstmt.setString(3, saledto.getSalecustomername());
            pstmt.setString(4, saledto.getSalecustomeraddress());
            pstmt.setString(5, saledto.getSalecustomercontactno());
            pstmt.setString(6, saledto.getSalesupplier());
            pstmt.setString(7, saledto.getSalebrand());
            pstmt.setString(8, saledto.getSaleproduct());
            pstmt.setDouble(9, saledto.getSaleqty());
            pstmt.setDouble(10, saledto.getSaleamtperpiece());
            pstmt.setDouble(11, saledto.getSaletotal());
            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "INSERT", trimmedQuery, "Sales",
                          "Sales");
                JOptionPane.showMessageDialog(null, "Record Inserted", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
                insert = true;

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return insert;
    }

    public boolean updatesale(SaleDTO saledto) {
        boolean update = false;
        try {
            String sql = "Update saletbl SET saleid=?, saledate=?,"
                      + " salecustomername = ?, salecustomeraddress = ?, salecustomercontactno = ?,"
                      + "salesupplier = ?, salebrand = ?, saleproduct = ?,"
                      + "saleqty = ?, saleamtperpiece = ?, saletotal = ?  "
                      + " WHERE saleid=?";

            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, saledto.getSaleid());
            pstmt.setString(2, saledto.getSaledate());
            pstmt.setString(3, saledto.getSalecustomername());
            pstmt.setString(4, saledto.getSalecustomeraddress());
            pstmt.setString(5, saledto.getSalecustomercontactno());
            pstmt.setString(6, saledto.getSalesupplier());
            pstmt.setString(7, saledto.getSalebrand());
            pstmt.setString(8, saledto.getSaleproduct());
            pstmt.setDouble(9, saledto.getSaleqty());
            pstmt.setDouble(10, saledto.getSaleamtperpiece());
            pstmt.setDouble(11, saledto.getSaletotal());
            pstmt.setInt(12, saledto.getSaleid());

            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "UPDATE", trimmedQuery, "Sales",
                          "Sales");
                JOptionPane.showMessageDialog(null, "One Record Updated", "Inventory Management System",
                          JOptionPane.INFORMATION_MESSAGE);
                update = true;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return update;
    }

    public int saleId() {
        int saleid = 0;
        try {
            String query = " SELECT saleid FROM saletbl\n"
                      + "ORDER BY saleid DESC\n"
                      + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                saleid = rs.getInt("saleid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return saleid;
    }

    public int countSale() {
        int countsale = 0;
        try {
            String query = "SELECT COUNT(saleid) FROM saletbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countsale = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return countsale;
    }

    public void deleteRows(String[] value) throws SQLException {
        try {

            String sql = " DELETE FROM  saletbl WHERE saleid = ?";
            //String reservedquery = "UsaleDATE particulartbl SET pname = 'reserved ' WHERE pid=?";

            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, value[0]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        if (pstmt.executeUpdate() == 1) {
            String query = pstmt.toString();
            String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
            new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "DELETE", trimmedQuery, "Sales",
                      "Sales");
        }
    }

    public ResultSet quickSearch(String search) {

        try {
            String query = "SELECT *FROM saletbl WHERE saleid LIKE '" + search + "%' OR saledate LIKE '" + search + "%'"
                      + "OR salecustomername LIKE '" + search + "%' OR salecustomeraddress LIKE '" + search + "%' "
                      + "OR salecustomercontact LIKE '" + search + "%' OR salesupplier LIKE '" + search + "%'"
                      + "OR salebrand LIKE '" + search + "%' OR saleproduct LIKE '" + search + "%'"
                      + "OR saleqty LIKE '" + search + "%' OR saleamtperpiece LIKE '" + search + "%'"
                      + "OR saletotal LIKE '" + search + "%'";
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
        String query = "SELECT *FROM saletbl";
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
