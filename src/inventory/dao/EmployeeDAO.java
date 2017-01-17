package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.EmployeeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EmployeeDAO {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    // mapping the table of database
    String mappedColumnNames[] = {
        "Id",
        "First Name",
        "Last Name",
        "Contact",
        "Address",
        "Post",
        "Joined Date",
        "Salary (Rs)",
        "Working Period",
        "Leave Date"

    };

    String mappedColumnNamesUsers[] = {
        "Id",
        "First Name",
        "Last Name",
        "Contact",
        "Address",
        "Post",
        "Joined Date",
        "Working Period",
        "Leave Date"

    };

    String mappedColumnNamesUser[] = {
        "UserID",
        "Username",
        "Password",
        "Usertype"

    };

    public EmployeeDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean insertemployee(EmployeeDTO employeedto) {
        boolean insert = false;
        try {
            String sql = "INSERT INTO employeetbl VALUES(?,?,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setInt(1, employeedto.getEmployeeid());
            pstmt.setString(2, employeedto.getEmployeename());
            pstmt.setString(3, employeedto.getEmployeelname());
            pstmt.setString(5, employeedto.getEmployeecontactno());
            pstmt.setString(4, employeedto.getEmployeeaddress());
            pstmt.setString(6, employeedto.getEmployeepost());
            pstmt.setString(7, employeedto.getEmployeejoindate());
            pstmt.setDouble(8, employeedto.getEmployeesalary());
            pstmt.setString(9, employeedto.getEmployeeworkingyears());
            pstmt.setString(10, employeedto.getEmployeeleavedate());
            
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

    public boolean updateEmployee(EmployeeDTO employeedto) {
        boolean update = false;
        try {
            String sql = "Update employeetbl SET employeeid=?, employeename=?,"
                    + " employeelname = ?, employeecontactno = ?, employeeaddress = ?,"
                    + "employeepost = ?, employeejoindate = ?, employeesalary = ?,"
                    + "employeeworkingyears = ?, employeeleavedate = ? WHERE employeeid=?";

            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setInt(1, employeedto.getEmployeeid());
            pstmt.setString(2, employeedto.getEmployeename());
            pstmt.setString(3, employeedto.getEmployeelname());
            pstmt.setString(4, employeedto.getEmployeecontactno());
            pstmt.setString(5, employeedto.getEmployeeaddress());
            pstmt.setString(6, employeedto.getEmployeepost());
            pstmt.setString(7, employeedto.getEmployeejoindate());
            pstmt.setDouble(8, employeedto.getEmployeesalary());
            pstmt.setString(9, employeedto.getEmployeeworkingyears());
            pstmt.setString(10, employeedto.getEmployeeleavedate());
            pstmt.setInt(11, employeedto.getEmployeeid());
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

    public int countEmployee() {
        int countemployee = 0;
        try {
            String query = "SELECT COUNT(employeeid) FROM employeetbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countemployee = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);

        }
        return countemployee;
    }

    public int employeeId() {
        int employeeid = 0;
        try {
            String query = " SELECT employeeid FROM employeetbl\n"
                    + "ORDER BY employeeid DESC\n"
                    + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                employeeid = rs.getInt("employeeid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return employeeid;
    }

    public void deleteRows(String[] value) throws SQLException {
        try {

            String query = " DELETE FROM  employeetbl WHERE employeeid = ?";
            //String reservedquery = "UproductDATE particulartbl SET pname = 'reserved ' WHERE pid=?";

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, value[0]);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        pstmt.executeUpdate();
    }

    public void updateRowsUser(EmployeeDTO employeedto) throws SQLException {
        try {
            String sql = "Update employeetbl SET username = ?, password=?,"
                    + " usertype = ? WHERE employeeid=?";

            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setString(1, String.valueOf(employeedto.getEmployeeid()));
            pstmt.setString(2, String.valueOf(employeedto.getEmployeeid()));
            pstmt.setString(3, String.valueOf(employeedto.getEmployeeid()));
            pstmt.setInt(4, employeedto.getEmployeeid());

            if (pstmt.executeUpdate() == 1) {
                String deletesql = "DELETE FROM logintbl WHERE userid = ?";
                pstmt = (PreparedStatement) con.prepareStatement(deletesql);
                pstmt.setString(1, String.valueOf(employeedto.getEmployeeid()));

                pstmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "One Record Deleted", "Inventory Management System",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {

        }
    }

    public ResultSet getUserProfile(String username) {
        String query = "SELECT employeename, employeelname, employeecontactno, username, password,"
                + "usertype FROM employeetbl WHERE "
                + "username = '" + username + "'";
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet quickSearch(String search) {

        try {
            String query = "SELECT FROM employeetbl WHERE employeeid LIKE '" + search + "%' OR employeename LIKE '" + search + "%'"
                    + "OR employeelname LIKE '" + search + "%' OR employeecontactno LIKE '" + search + "%' "
                    + "OR employeeaddress LIKE '" + search + "%' OR employeepost LIKE '" + search + "%'"
                    + "OR employeejoindate LIKE '" + search + "%' OR employeesalary LIKE '" + search + "%'"
                    + "OR employeeworkingyears LIKE '" + search + "%' OR employeeleavedate LIKE '" + search + "%'";
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

    public DefaultTableModel buildTableModelUser(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {

            columnNames.add(mappedColumnNamesUser[column - 1]);
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

    public DefaultTableModel buildTableModelUsers(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();

        for (int column = 1; column <= columnCount; column++) {

            columnNames.add(mappedColumnNamesUsers[column - 1]);
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
        String query1 = "select employeeid, employeename, employeelname, employeecontactno,"
                + "employeeaddress, employeepost, employeejoindate, employeesalary,"
                + "employeeworkingyears, employeeleavedate from employeetbl";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet getQueryResultUsers() {
        String query1 = "select employeeid, employeename, employeelname, employeecontactno,"
                + "employeeaddress, employeepost, employeejoindate,"
                + "employeeworkingyears, employeeleavedate from employeetbl";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet getQueryResultUser() {
        String query1 = "select *from logintbl";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet getEmployeename(String employeeid) {
        String query = "SELECT  employeename, employeelname FROM employeetbl WHERE employeeid = '" + employeeid + "'";
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
