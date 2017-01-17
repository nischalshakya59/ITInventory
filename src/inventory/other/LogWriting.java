package inventory.other;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogWriting {
    private String date;

    private String name;

    private String operation;

    private String sql;

    private String filePath;

    private final String rootFolderPath = "C:\\InventoryMgmt";

    private final String storeFolderPath = "\\Store";

    private final String salesFolderPath = "\\Sales";

    private final String userFolderPath = "\\User";

    private final String productFileName = "\\Product.txt";

    private final String brandFileName = "\\Brand.txt";

    private final String categoryFileName = "\\Category.txt";

    private final String supplierFileName = "\\Supplier.txt";

    private final String salesFileName = "\\Sales.txt";

    private final String userFileName = "\\User.txt";

    private final String billingFileName = "\\Billing.txt";

    private final String separator = "\t";

    public LogWriting(Date date, String name, String operation, String sql, String foldername, String filename) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(date);
        this.name = name;
        this.operation = operation.toUpperCase();
        this.sql = sql;
        makeDirectory(rootFolderPath);

        this.filePath = createFilePath(foldername, filename);
        writeToFile();
    }

    public LogWriting() {

    }

    public String createFilePath(String foldername, String filename) {
        String filePath = rootFolderPath;
        System.out.println("filename : " + filename);
        switch (foldername) {
            case "Store":
                filePath += storeFolderPath;
                makeDirectory(filePath);
                break;
            case "Sales":
                filePath += salesFolderPath;
                makeDirectory(filePath);
                break;
            case "User":
                filePath += userFolderPath;
                makeDirectory(filePath);
                break;
        }
        switch (filename) {
            case "Product":
                filePath += productFileName;
                break;

            case "Brand":
                filePath += brandFileName;
                break;

            case "Category":
                filePath += categoryFileName;
                break;

            case "Supplier":
                filePath += supplierFileName;
                break;
            case "Sales":
                filePath += salesFileName;
                break;
            case "Billing":
                filePath += billingFileName;
                break;
            case "User":
                filePath += userFileName;
                break;
        }
        System.out.println("name : " + filePath);
        return filePath;
    }

    public boolean deleteFile(String foldername, String filename) {
        String deletingFilePath = createFilePath(foldername, filename);
        File file = new File(deletingFilePath);
        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
            return true;
        } else {
            System.out.println("Delete operation is failed.");
            return false;
        }

    }

    // main opearation
    public void writeToFile() {
        File file = new File(filePath);
        try {
            FileWriter writer = null;

            // creates the file
            if (!file.exists()) {
                file.createNewFile();

                writer = new FileWriter(file, true);

                writer.write("Date" + separator + "madeby" + separator + "operation" + separator + "sql");
            }
            if (writer == null) {
                writer = new FileWriter(file, true);
            }

            // Writes the content to the file
            writer.write(System.lineSeparator());
            writer.write(date + separator + name + separator + operation + separator + sql);
            writer.flush();
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        // creates a FileWriter Object

    }

    public boolean makeDirectory(String directoryName) {
        File theDir = new File(directoryName);

// if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + directoryName);

            try {
                theDir.mkdir();
                return true;
            } catch (SecurityException se) {
                se.printStackTrace();

            }

        }
        return false;
    }

    public static void main(String[] args) {
        new LogWriting(new Date(), "rajya", "INSERT", "INSERT INTO KLKDF", "Store", "Supplier").writeToFile();
        //new LogWriting().deleteFile("store", "supplier");
    }
}
