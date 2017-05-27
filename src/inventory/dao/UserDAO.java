package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.UserDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserDAO {

    public Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    String mappedColumnNames[] = {
        "User Id",
        "User Name",
        "Password",
        "User Type"
    };

    public UserDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public int userId() {
        int userid = 0;
        try {
            String query = " SELECT userid FROM logintbl\n"
                      + "ORDER BY userid DESC\n"
                      + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                userid = rs.getInt("userid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        return userid;
    }

    public boolean insertUser(UserDTO userdto) {
        boolean insert = false;
        try {
            String sql = "INSERT INTO logintbl VALUES(?,?,?,?)";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(1, userdto.getUserid());
            pstmt.setString(2, userdto.getUsername());
            pstmt.setString(3, userdto.getPassword());
            pstmt.setString(4, userdto.getUsertype());

            if (pstmt.executeUpdate() == 1) {
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

    public boolean updateUser(UserDTO userdto) {
        boolean update = false;
        try {
            String sql = "UPDATE logintbl SET username = ?, password = ?, usertype = ? WHERE userid=?";
            pstmt = (PreparedStatement)con.prepareStatement(sql);
            pstmt.setInt(4, userdto.getUserid());
            pstmt.setString(1, userdto.getUsername());
            pstmt.setString(2, userdto.getPassword());
            pstmt.setString(3, userdto.getUsertype());

            if (pstmt.executeUpdate() == 1) {
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

    public void deleteUser(String[] value) throws SQLException {
        String query = " DELETE FROM  logintbl WHERE userid = ?";
        try {

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, value[0]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                      JOptionPane.INFORMATION_MESSAGE);
        }
        pstmt.executeUpdate();
    }

    public UserDTO editUser(JTable table) {
        UserDTO userEdit = new UserDTO();
        userEdit.setUserid((int)table.getValueAt(table.getSelectedRow(), 0));
        userEdit.setUsername((String)table.getValueAt(table.getSelectedRow(), 1));
        userEdit.setPassword((String)table.getValueAt(table.getSelectedRow(), 2));
        return userEdit;
    }

    public ResultSet verifyUser(String fname, String lname) {
        try {
            String query = "SELECT *FROM employeetbl WHERE employeename = '" + fname + "'"
                      + "AND employeelname = '" + lname + "'";
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
        String query = "SELECT  *FROM logintbl";
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
