package library;

import java.sql.*;
import java.util.Properties;
import java.io.FileInputStream;

public class DatabaseConnection {
    private static Connection conn = null;
    static {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));
            String url      = props.getProperty("url");
            String user     = props.getProperty("user");
            String password = props.getProperty("password");
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return conn;
    }
}
