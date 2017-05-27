package inventory.restore;

import inventory.db.DBConnection;
import inventory.dto.BckRestoreDTO;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class ProductRestore {

    Connection con;
    Statement stmt;
    ResultSet rs;

    public void producttblRestore(BckRestoreDTO brd) {
        try {

            con = new DBConnection().getConnection();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            String fname = brd.getFilename();
            File directory = brd.getDirectory();

            String query = "load data local infile '" + directory + "/" + fname + "' \n"
                    + "   replace \n"
                    + "   into table producttbl \n"
                    + "   fields terminated by ',' \n"
                    + "   ignore 1 lines";

            String query1 = query.replace("\\", "/");

            rs = stmt.executeQuery(query1);
            if (rs != null) {
                JOptionPane.showMessageDialog(null, "Backup Restored Sucessfully " + fname, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Failed To Restore " + fname, "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
