package dao;

import model.Customer;
import model.Staff;
import util.DatabaseConnection;

import java.sql.*;

public class UserDAO {

    private Connection conn = DatabaseConnection.getConnection();

    // Register Customer
    public void registerCustomer(String name, String email, String username, String passwordHash) throws SQLException {
        String sql = "INSERT INTO Customers (Name, Email, Username, PasswordHash, RegistrationDate, Phone, Address) " +
                     "VALUES (?, ?, ?, ?, NOW(), '', '')"; // Add phone/address if needed
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, passwordHash);
            ps.executeUpdate();
        }
    }

    // Register Staff
    public void registerStaff(String name, String email, String username, String passwordHash) throws SQLException {
        String sql = "INSERT INTO Staff (Name, Email, Username, PasswordHash, Role, HireDate) " +
                     "VALUES (?, ?, ?, ?, 'staff', NOW())"; // Add Role field if your schema supports it
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, username);
            ps.setString(4, passwordHash);
            ps.executeUpdate();
        }
    }

    // Login (returns Customer or Staff object)
    public Object login(String username, String passwordHash) throws SQLException {
        // Try to login as customer
        String customerSql = "SELECT * FROM Customers WHERE Username = ? AND PasswordHash = ?";
        try (PreparedStatement ps = conn.prepareStatement(customerSql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Username")
                );
            }
        }

        // Try to login as staff
        String staffSql = "SELECT * FROM Staff WHERE Username = ? AND PasswordHash = ?";
        try (PreparedStatement ps = conn.prepareStatement(staffSql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Username"),
                        rs.getString("Role")
                );
            }
        }

        return null; // Login failed
    }
}
