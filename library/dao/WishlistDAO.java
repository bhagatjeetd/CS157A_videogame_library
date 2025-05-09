package library.dao;

import library.model.Wishlist;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * WishlistDAO
 * CRUD for customer wishlists.
 */
public class WishlistDAO {
    private Connection conn = DatabaseConnection.getConnection();

    public void addWishlist(Wishlist w) throws SQLException {
        String sql = "INSERT INTO Wishlists "
                + "(WishlistID, CustomerID, GameID, AddedDate) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, w.getWishlistId());
            ps.setInt(2, w.getCustomerId());
            ps.setInt(3, w.getGameId());
            ps.setTimestamp(4, new Timestamp(w.getAddedDate().getTime()));
            ps.executeUpdate();
        }
    }

    public List<Wishlist> getAllWishlists() throws SQLException {
        List<Wishlist> list = new ArrayList<>();
        String sql = "SELECT * FROM Wishlists";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Wishlist(
                        rs.getInt("WishlistID"),
                        rs.getInt("CustomerID"),
                        rs.getInt("GameID"),
                        rs.getTimestamp("AddedDate")
                ));
            }
        }
        return list;
    }

    public void updateWishlist(Wishlist w) throws SQLException {
        String sql = "UPDATE Wishlists SET "
                + "CustomerID=?, GameID=?, AddedDate=? "
                + "WHERE WishlistID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, w.getCustomerId());
            ps.setInt(2, w.getGameId());
            ps.setTimestamp(3, new Timestamp(w.getAddedDate().getTime()));
            ps.setInt(4, w.getWishlistId());
            ps.executeUpdate();
        }
    }

    public void deleteWishlist(int id) throws SQLException {
        String sql = "DELETE FROM Wishlists WHERE WishlistID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Integer> getWishlistGameIds(int customerId) throws SQLException {
        List<Integer> ids = new ArrayList<>();
        String sql = "SELECT GameID FROM Wishlists WHERE CustomerID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) ids.add(rs.getInt("GameID"));
            }
        }
        return ids;
    }

    public void addToWishlist(int customerId, int gameId) throws SQLException {
        String sql = "INSERT INTO Wishlists (CustomerID, GameID, AddedDate) VALUES (?, ?, NOW())";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setInt(2, gameId);
            ps.executeUpdate();
        }
    }

    public void removeFromWishlist(int customerId, int gameId) throws SQLException {
        String sql = "DELETE FROM Wishlists WHERE CustomerID = ? AND GameID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.setInt(2, gameId);
            ps.executeUpdate();
        }
    }
}
