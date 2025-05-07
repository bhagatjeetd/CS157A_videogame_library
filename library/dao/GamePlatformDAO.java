package library.dao;

import library.model.GamePlatform;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * GamePlatformDAO
 * CRUD for the GamePlatforms join table.
 */
public class GamePlatformDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * Creates a new game-platform mapping.
     */
    public void addGamePlatform(GamePlatform gp) throws SQLException {
        String sql = "INSERT INTO GamePlatforms (GameID, PlatformID) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gp.getGameId());
            ps.setInt(2, gp.getPlatformId());
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves all mappings.
     */
    public List<GamePlatform> getAllGamePlatforms() throws SQLException {
        List<GamePlatform> list = new ArrayList<>();
        String sql = "SELECT * FROM GamePlatforms";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new GamePlatform(
                        rs.getInt("GameID"),
                        rs.getInt("PlatformID")
                ));
            }
        }
        return list;
    }

    /**
     * Updates an existing mapping.
     * Note: generally, you delete+reinsert composite-key rows,
     * but here we illustrate an UPDATE that changes PlatformID.
     */
    public void updateGamePlatform(int oldGameId, int oldPlatformId,
                                   GamePlatform newGp) throws SQLException {
        String sql = "UPDATE GamePlatforms SET GameID = ?, PlatformID = ? "
                + "WHERE GameID = ? AND PlatformID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newGp.getGameId());
            ps.setInt(2, newGp.getPlatformId());
            ps.setInt(3, oldGameId);
            ps.setInt(4, oldPlatformId);
            ps.executeUpdate();
        }
    }

    /**
     * Deletes a mapping by composite key.
     */
    public void deleteGamePlatform(int gameId, int platformId) throws SQLException {
        String sql = "DELETE FROM GamePlatforms WHERE GameID = ? AND PlatformID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, gameId);
            ps.setInt(2, platformId);
            ps.executeUpdate();
        }
    }
}
