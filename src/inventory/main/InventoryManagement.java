package inventory.main;

import inventory.ui.LoginFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class InventoryManagement {

    public void login() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            LoginFrame lu = new LoginFrame();
            lu.setVisible(true);
            lu.pack();
            lu.setLocationRelativeTo(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Inventory Management System", JOptionPane.INFORMATION_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InventoryManagement().login();

    }

}
