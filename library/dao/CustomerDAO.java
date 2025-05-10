package library.dao;

import library.model.Customer;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerDAO
 * CRUD operations for the Customers table.
 */
public class CustomerDAO {
    // Reuse the singleton Connection
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * addCustomer()
     * Insert a new customer into the DB.
     *
     * @param c the Customer to insert
     * @throws SQLException on DB errors
     */
    public void addCustomer(Customer c) throws SQLException {
        String sql = "INSERT INTO Customers "
                + "(Name, Email, Phone, Address, Username, PasswordHash, RegistrationDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getUsername());
            ps.setString(6, c.getPasswordHash());
            ps.setTimestamp(7, new Timestamp(c.getRegistrationDate().getTime()));
            ps.executeUpdate();

            // Retrieve generated CustomerID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId(rs.getInt(1));
                }
            }
        }
    }

    /**
     * getAllCustomers()
     * Fetch all customers from the DB.
     *
     * @return list of Customers
     * @throws SQLException on DB errors
     */
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM Customers";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Customer(
                        rs.getInt("CustomerID"),
                        rs.getString("CustomerName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Address"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getTimestamp("RegistrationDate")
                ));
            }
        }
        return list;
    }

    /**
     * updateCustomer()
     * Modify an existing customer's data.
     *
     * @param c the Customer with updated fields
     * @throws SQLException on DB errors
     */
    public void updateCustomer(Customer c) throws SQLException {
        String sql = "UPDATE Customers SET "
                + "Name=?, Email=?, Phone=?, Address=?, Username=?, PasswordHash=?, RegistrationDate=? "
                + "WHERE CustomerID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getAddress());
            ps.setString(5, c.getUsername());
            ps.setString(6, c.getPasswordHash());
            ps.setTimestamp(7, new Timestamp(c.getRegistrationDate().getTime()));
            ps.setInt(8, c.getId());
            ps.executeUpdate();
        }
    }

    /**
     * deleteCustomer()
     * Remove a customer by ID.
     *
     * @param id the CustomerID to delete
     * @throws SQLException on DB errors
     */
    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM Customers WHERE CustomerID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
