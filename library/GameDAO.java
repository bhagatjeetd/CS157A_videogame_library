package library;

import library.Game;
import library.DatabaseConnection;
import java.sql.*;
import java.util.*;

public class GameDAO {
    private Connection conn = DatabaseConnection.getConnection();

    /** Insert new game record */
    public void addGame(Game g) throws SQLException {
        String sql = "INSERT INTO Games "
                + "(GameID,Title,Genre,ESRB,ReleaseDate,Publisher,BaseRentalPrice) "
                + "VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, g.getId());
            ps.setString(2, g.getTitle());
            ps.setString(3, g.getGenre());
            ps.setString(4, g.getEsrb());
            ps.setDate(5, new java.sql.Date(g.getReleaseDate().getTime()));
            ps.setString(6, g.getPublisher());
            ps.setDouble(7, g.getBaseRentalPrice());
            ps.executeUpdate();
        }
    }

    /** Retrieve all games */
    public List<Game> getAllGames() throws SQLException {
        List<Game> list = new ArrayList<>();
        String sql = "SELECT * FROM Games";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Game(
                        rs.getInt("GameID"),
                        rs.getString("Title"),
                        rs.getString("Genre"),
                        rs.getString("ESRB"),
                        rs.getDate("ReleaseDate"),
                        rs.getString("Publisher"),
                        rs.getDouble("BaseRentalPrice")
                ));
            }
        }
        return list;
    }

    /** Update an existing game */
    public void updateGame(Game g) throws SQLException {
        String sql = "UPDATE Games SET Title=?,Genre=?,ESRB=?,ReleaseDate=?,"
                + "Publisher=?,BaseRentalPrice=? WHERE GameID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, g.getTitle());
            ps.setString(2, g.getGenre());
            ps.setString(3, g.getEsrb());
            ps.setDate(4, new java.sql.Date(g.getReleaseDate().getTime()));
            ps.setString(5, g.getPublisher());
            ps.setDouble(6, g.getBaseRentalPrice());
            ps.setInt(7, g.getId());
            ps.executeUpdate();
        }
    }

    /** Delete game by ID */
    public void deleteGame(int id) throws SQLException {
        String sql = "DELETE FROM Games WHERE GameID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
