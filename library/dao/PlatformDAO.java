package library.dao;

import library.model.Platform;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlatformDAO
 * CRUD operations for the Platforms table.
 */
public class PlatformDAO {
    // Shared connection from our utility
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * Inserts a new Platform record.
     */
    public void addPlatform(Platform p) throws SQLException {
        String sql = "INSERT INTO Platforms (PlatformID, PlatformName) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getPlatformId());
            ps.setString(2, p.getName());
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all platforms.
     */
    public List<Platform> getAllPlatforms() throws SQLException {
        List<Platform> list = new ArrayList<>();
        String sql = "SELECT * FROM Platforms";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Platform(
                        rs.getInt("PlatformID"),
                        rs.getString("PlatformName")
                ));
            }
        }
        return list;
    }

    /**
     * Updates an existing platform.
     */
    public void updatePlatform(Platform p) throws SQLException {
        String sql = "UPDATE Platforms SET PlatformName = ? WHERE PlatformID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getName());
            ps.setInt(2, p.getPlatformId());
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a platform by ID.
     */
    public void deletePlatform(int id) throws SQLException {
        String sql = "DELETE FROM Platforms WHERE PlatformID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
