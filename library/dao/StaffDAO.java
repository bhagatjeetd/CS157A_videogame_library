// src/library/dao/StaffDAO.java
package library.dao;

import library.model.Staff;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * StaffDAO
 * CRUD operations for the Staff table.
 */
public class StaffDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * addStaff()
     * Insert a new staff member.
     *
     * @param s the Staff to insert
     * @throws SQLException on DB errors
     */
    public void addStaff(Staff s) throws SQLException {
        String sql = "INSERT INTO Staff "
                + "(Name, Email, Role, Username, PasswordHash, HireDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getRole());
            ps.setString(4, s.getUsername());
            ps.setString(5, s.getPasswordHash());
            ps.setTimestamp(6, new Timestamp(s.getHireDate().getTime()));
            ps.executeUpdate();

            // Retrieve generated StaffID
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    s.setId(rs.getInt(1));
                }
            }
        }
    }

    /**
     * getAllStaff()
     * Fetch all staff members.
     *
     * @return list of Staff
     * @throws SQLException on DB errors
     */
    public List<Staff> getAllStaff() throws SQLException {
        List<Staff> list = new ArrayList<>();
        String sql = "SELECT * FROM Staff";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getString("Role"),
                        rs.getString("Username"),
                        rs.getString("PasswordHash"),
                        rs.getTimestamp("HireDate")
                ));
            }
        }
        return list;
    }

    /**
     * updateStaff()
     * Update an existing staff member.
     *
     * @param s the Staff with updated data
     * @throws SQLException on DB errors
     */
    public void updateStaff(Staff s) throws SQLException {
        String sql = "UPDATE Staff SET "
                + "Name=?, Email=?, Role=?, Username=?, PasswordHash=?, HireDate=? "
                + "WHERE StaffID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setString(2, s.getEmail());
            ps.setString(3, s.getRole());
            ps.setString(4, s.getUsername());
            ps.setString(5, s.getPasswordHash());
            ps.setTimestamp(6, new Timestamp(s.getHireDate().getTime()));
            ps.setInt(7, s.getId());
            ps.executeUpdate();
        }
    }

    /**
     * deleteStaff()
     * Remove a staff member by ID.
     *
     * @param id the StaffID to delete
     * @throws SQLException on DB errors
     */
    public void deleteStaff(int id) throws SQLException {
        String sql = "DELETE FROM Staff WHERE StaffID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
