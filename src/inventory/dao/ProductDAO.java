package inventory.dao;

import inventory.db.DBConnection;
import inventory.dto.ProductDTO;
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

public class ProductDAO {

    Connection con;

    PreparedStatement pstmt;

    ResultSet rs;

    boolean flag = false;

    Statement stmt;

    // mapping the table of database
    String mappedColumnNames[] = {
        "Id",
        "Entry Date",
        "Supplier",
        "Brand",
        "Model",
        "Category",
        "Quantity",
        "Selling Price",
        "Cost Price",
        "Profit",
        "Warranty",
        "Specification"
    };

    public ProductDAO() {
        try {
            con = new DBConnection().getConnection();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public boolean insertproduct(ProductDTO productdto) {
        boolean insert = false;
        try {
            String sql = "INSERT INTO producttbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setInt(1, productdto.getProductid());
            pstmt.setString(2, productdto.getProductentrydate());
            pstmt.setString(3, productdto.getProductsuppliername());
            pstmt.setString(5, productdto.getProductmodel());
            pstmt.setString(4, productdto.getProductbrand());
            pstmt.setString(6, productdto.getProductcategory());
            pstmt.setInt(7, productdto.getProductqty());
            pstmt.setDouble(8, productdto.getProductsellingprice());
            pstmt.setDouble(9, productdto.getProductcostprice());
            pstmt.setDouble(10, productdto.getProductprofit());
            pstmt.setString(11, productdto.getProductwarranty());
            pstmt.setString(12, productdto.getProductspecification());
            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "INSERT", trimmedQuery, "Store",
                        "Product");
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

    public boolean updateproduct(ProductDTO productdto) throws SQLException {
        boolean update = false;
        try {
            String sql = "Update producttbl SET productid=?, productentrydate=?,"
                    + " productsuppliername = ?, productmodel = ?, productbrand = ?,"
                    + "productcategory = ?, productqty = ?, productsellingprice = ?,"
                    + "productcostprice = ?, productprofit = ?, productwarranty = ?,  "
                    + "productspecification= ? WHERE productid=?";

            pstmt = (PreparedStatement) con.prepareStatement(sql);
            pstmt.setInt(1, productdto.getProductid());
            pstmt.setString(2, productdto.getProductentrydate());
            pstmt.setString(3, productdto.getProductsuppliername());
            pstmt.setString(4, productdto.getProductmodel());
            pstmt.setString(5, productdto.getProductbrand());
            pstmt.setString(6, productdto.getProductcategory());
            pstmt.setInt(7, productdto.getProductqty());
            pstmt.setDouble(8, productdto.getProductsellingprice());
            pstmt.setDouble(9, productdto.getProductcostprice());
            pstmt.setDouble(10, productdto.getProductprofit());
            pstmt.setString(11, productdto.getProductwarranty());
            pstmt.setString(12, productdto.getProductspecification());
            pstmt.setInt(13, productdto.getProductid());

            if (pstmt.executeUpdate() == 1) {
                String query = pstmt.toString();
                String trimmedQuery = query.substring(query.indexOf(":") + 2, query.length());
                new LogWriting(new Date(), ITDashboard.usernamelab.getText(), "UPDATE", trimmedQuery, "Store",
                        "Product");
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

    public int productId() {
        int productid = 0;
        try {
            String query = " SELECT productid FROM producttbl\n"
                    + "ORDER BY productid DESC\n"
                    + "LIMIT 1 ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                productid = rs.getInt("productid");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return productid;
    }

    public void deleteRows(String[] value) throws SQLException {
        try {

            String sql = " DELETE FROM  producttbl WHERE productid = ?";
            //String reservedquery = "UproductDATE particulartbl SET pname = 'reserved ' WHERE pid=?";

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
                    "Product");
        }
    }

    public int countProduct() {
        int countproduct = 0;
        try {
            String query = "SELECT COUNT(productid) FROM producttbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                countproduct = rs.getInt(1);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return countproduct;
    }

    public ResultSet getProductModelSupplier(String productbrand) {
        try {
            String query = "SELECT productmodel,productsuppliername FROM producttbl WHERE productbrand = '" + productbrand + "'"
                    + "AND productqty > 0";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet getSupplierName(String productbrand, String productmodel) {
        try {
            String query = "SELECT productsuppliername FROM producttbl WHERE productbrand = '" + productbrand + "'"
                    + "AND productmodel = '" + productmodel + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet getBrandName() {
        try {
            String query = "SELECT productbrand FROM producttbl";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public boolean updateProductQty(ProductDTO productdto) {
        boolean updateproductqty = false;
        int productquantity = 0;
        try {
            String query = "SELECT productqty FROM producttbl WHERE productmodel=?"
                    + "AND productbrand = ? AND productsuppliername = ? AND productqty > 0";

            pstmt = (PreparedStatement) con.prepareStatement(query);
            pstmt.setString(1, productdto.getProductmodel());
            pstmt.setString(2, productdto.getProductbrand());
            pstmt.setString(3, productdto.getProductsuppliername());

            rs = pstmt.executeQuery();

            while (rs.next()) {
                productquantity = rs.getInt(1);

                String sql = "Update producttbl SET productqty = ? WHERE productmodel=?"
                        + "AND productbrand = ? AND productsuppliername = ?";

                pstmt = (PreparedStatement) con.prepareStatement(sql);
                pstmt.setInt(1, productquantity - productdto.getProductqty());
                pstmt.setString(2, productdto.getProductmodel());
                pstmt.setString(3, productdto.getProductbrand());
                pstmt.setString(4, productdto.getProductsuppliername());

                if (pstmt.executeUpdate() == 1) {
                    updateproductqty = true;
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return updateproductqty;
    }

    public ResultSet getQuantitySellingprice(String productmodel, String productbrand,
            String productsuppliername) {
        try {
            String query = " SELECT productqty, productsellingprice FROM producttbl WHERE productmodel = '" + productmodel + "'"
                    + " AND productbrand = '" + productbrand + "'  AND  "
                    + "productsuppliername = '" + productsuppliername + "'"
                    + "AND productmodel = '" + productmodel + "'";
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }

        return rs;

    }

    public ResultSet searchProduct(String productmodel, String productbrand, String productsuppliername) {
        try {
            String query = " SELECT *from producttbl WHERE productmodel = '" + productmodel + "'"
                    + " AND productbrand = '" + productbrand + "'  AND  "
                    + "productsuppliername = '" + productsuppliername + "'";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductId(String productid) {
        try {
            String query = " SELECT *from producttbl WHERE productid = " + productid + "";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductEntryDate(String productentrydate) {
        try {
            String query = " SELECT *from producttbl WHERE productentrydate =  '" + productentrydate + "' ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductSupplierName(String productsuppliername) {
        try {
            System.out.println(productsuppliername);
            String query = " SELECT *from producttbl WHERE productsuppliername = '" + productsuppliername + "' ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductModel(String productmodel) {
        try {
            String query = " SELECT *from producttbl WHERE productmodel = '" + productmodel + "' ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductBrand(String productbrand) {
        try {
            String query = " SELECT *from producttbl WHERE productbrand = '" + productbrand + "' ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductCategory(String productcategory) {
        try {
            String query = " SELECT *from producttbl WHERE productcategory = '" + productcategory + "' ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductQty(String productqty) {
        try {
            String query = " SELECT *from producttbl WHERE productqty = " + productqty + " ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchSellingPrice(String productsellingprice) {
        try {
            String query = " SELECT *from producttbl WHERE productsellingprice = " + productsellingprice + " ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchCostPrice(String productcostprice) {
        try {
            String query = " SELECT *from producttbl WHERE productcostprice = " + productcostprice + " ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProduuctProfit(String productprofit) {
        try {
            String query = " SELECT *from producttbl WHERE productprofit = " + productprofit + " ";

            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        return rs;
    }

    public ResultSet searchProductWarranty(String productwarranty) {
        try {
            String query = " SELECT *from producttbl WHERE productwarranty = '" + productwarranty + "' ";

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
            String query = "SELECT *FROM producttbl WHERE productid LIKE '" + search + "%' OR productentrydate LIKE '" + search + "%'"
                    + "OR productsuppliername LIKE '" + search + "%' OR productmodel LIKE '" + search + "%' "
                    + "OR productbrand LIKE '" + search + "%' OR productcategory LIKE '" + search + "%'"
                    + "OR productqty LIKE '" + search + "%' OR productsellingprice LIKE '" + search + "%'"
                    + "OR productcostprice LIKE '" + search + "%' OR productprofit LIKE '" + search + "%'"
                    + "OR productwarranty LIKE '" + search + "%'";
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
        String query = "SELECT  productid, productentrydate,productsuppliername,"
                + "productbrand, productmodel, productcategory, productqty,"
                + "productsellingprice, productcostprice, productprofit, "
                + "productwarranty, productspecification FROM producttbl";
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
