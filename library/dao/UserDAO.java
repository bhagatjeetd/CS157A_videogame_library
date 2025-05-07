package library.dao;

import library.model.Customer;
import library.model.Staff;
import library.util.DatabaseConnection;

import java.sql.*;

/**
 * UserDAO
 * Handles registration and login for Customers and Staff,
 * using the expanded model constructors.
 */
public class UserDAO {
    private final Connection conn = DatabaseConnection.getConnection();

    /**
     * registerCustomer
     * Creates a new customer. Phone and address are optional;
     * set them to blank if you wish.
     *
     * @param name         full name
     * @param email        email address
     * @param phone        phone number (or "" if none)
     * @param address      postal address (or "" if none)
     * @param username     login username
     * @param passwordHash hashed password
     */
    public void registerCustomer(
            String name,
            String email,
            String phone,
            String address,
            String username,
            String passwordHash
    ) throws SQLException {
        String sql = """
            INSERT INTO Customers
              (Name, Email, Phone, Address, Username, PasswordHash, RegistrationDate)
            VALUES
              (?, ?, ?, ?, ?, ?, NOW())
            """;
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, address);
            ps.setString(5, username);
            ps.setString(6, passwordHash);
            ps.executeUpdate();
        }
    }

    /**
     * loginCustomer
     * Attempts to authenticate a customer.
     *
     * @return a fully‐populated Customer or null
     */
    public Customer loginCustomer(String username, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM Customers WHERE Username = ? AND PasswordHash = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Phone"),
                            rs.getString("Address"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getTimestamp("RegistrationDate")
                    );
                }
            }
        }
        return null;
    }

    /**
     * loginStaff
     * Attempts to authenticate a staff member
     * @return a fully‐populated Staff or null
     */
    public Staff loginStaff(String username, String passwordHash) throws SQLException {
        String sql = "SELECT * FROM Staff WHERE Username = ? AND PasswordHash = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Staff(
                            rs.getInt("StaffID"),
                            rs.getString("Name"),
                            rs.getString("Email"),
                            rs.getString("Role"),
                            rs.getString("Username"),
                            rs.getString("PasswordHash"),
                            rs.getTimestamp("HireDate")
                    );
                }
            }
        }
        return null;
    }
}