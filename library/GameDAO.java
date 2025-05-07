package library;

import library.Game;
import library.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

/**
 * GameDAO
 * Data Access Object for Game model.
 * Encapsulates all CRUD operations on Games table.
 */
public class GameDAO {

    // Reuse singleton Connection from DatabaseConnection
    private Connection conn = DatabaseConnection.getConnection();

    /**
     * addGame()
     * Inserts new Game record into database.
     *
     * @param g Game object containing all necessary fields
     * @throws SQLException if database access error occurs
     */
    public void addGame(Game g) throws SQLException {
        // SQL statement with placeholders for prepared statement
        String sql = "INSERT INTO Games "
                + "(GameID, Title, Genre, ESRB, ReleaseDate, Publisher, BaseRentalPrice) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        // Use try-with-resources to auto-close PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Bind each Game field to corresponding placeholder
            ps.setInt(1, g.getId());
            ps.setString(2, g.getTitle());
            ps.setString(3, g.getGenre());
            ps.setString(4, g.getEsrb());
            // Convert java.util.Date to java.sql.Date for JDBC
            ps.setDate(5, new java.sql.Date(g.getReleaseDate().getTime()));
            ps.setString(6, g.getPublisher());
            ps.setDouble(7, g.getBaseRentalPrice());

            // Execute INSERT statement
            ps.executeUpdate();
        }
    }

    /**
     * getAllGames()
     * Fetches all Game records from database.
     *
     * @return List of Game objects representing every row in Games
     * @throws SQLException if database access error occurs
     */
    public List<Game> getAllGames() throws SQLException {
        // Container for results
        List<Game> games = new ArrayList<>();

        // Simple SELECT * statement
        String sql = "SELECT * FROM Games";

        // Use Statement/ResultSet to iterate through query results
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Loop through each row in ResultSet
            while (rs.next()) {
                // Construct new Game from current row
                Game g = new Game(
                        rs.getInt("GameID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getString("ESRB"),
                        rs.getDate("ReleaseDate"),
                        rs.getString("Publisher"),
                        rs.getDouble("BaseRentalPrice")
                );
                // Add to list
                games.add(g);
            }
        }

        // Return complete list
        return games;
    }

    /**
     * updateGame()
     * Updates an existing Game record based on its GameID.
     *
     * @param g Game object containing updated data
     * @throws SQLException if database access error occurs
     */
    public void updateGame(Game g) throws SQLException {
        // SQL UPDATE statement with placeholders
        String sql = "UPDATE Games SET "
                + "Title = ?, Genre = ?, ESRB = ?, ReleaseDate = ?, "
                + "Publisher = ?, BaseRentalPrice = ? "
                + "WHERE GameID = ?";

        // Auto-close PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Bind updated values
            ps.setString(1, g.getTitle());
            ps.setString(2, g.getGenre());
            ps.setString(3, g.getEsrb());
            ps.setDate(4, new java.sql.Date(g.getReleaseDate().getTime()));
            ps.setString(5, g.getPublisher());
            ps.setDouble(6, g.getBaseRentalPrice());
            ps.setInt(7, g.getId());

            // Execute UPDATE
            ps.executeUpdate();
        }
    }

    /**
     * deleteGame()
     * Deletes Game record by GameID.
     *
     * @param id GameID of record to delete
     * @throws SQLException if database access error occurs
     */
    public void deleteGame(int id) throws SQLException {
        // SQL DELETE statement
        String sql = "DELETE FROM Games WHERE GameID = ?";

        // Auto-close PreparedStatement
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            // Bind GameID parameter
            ps.setInt(1, id);

            // Execute DELETE
            ps.executeUpdate();
        }
    }
    // Utility method to get next GameID (for JSP/servlet)
    public int getNextGameId() throws SQLException {
        String sql = "SELECT MAX(GameID) AS MaxID FROM Games";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getInt("MaxID") + 1;
            } else {
                return 1; // start from 1 if table is empty
            }
        }
    }
}
