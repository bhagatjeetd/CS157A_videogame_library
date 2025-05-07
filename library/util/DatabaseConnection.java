package library.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.FileInputStream;

/**
 * DatabaseConnection
 * Utility class that initializes single JDBC Connection
 * based on properties in db.properties and provides it to callers.
 */
public class DatabaseConnection {

    // Holds singleton Connection instance
    private static Connection conn = null;

    // Static initializer block: runs once when class is first loaded
    static {
        try {
            // Load database configuration from properties file
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));
            String url      = props.getProperty("url");       // JDBC URL
            String user     = props.getProperty("user");      // DB user
            String password = props.getProperty("password");  // DB password

            // Load MySQL JDBC driver class into memory
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection using DriverManager
            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            // Print stack trace if loading properties, driver, or connection fails
            e.printStackTrace();
        }
    }

    /**
     * getConnection()
     * Provides access to single Connection instance.
     * @return live JDBC Connection to application database
     */
    public static Connection getConnection() {
        return conn;
    }
}
