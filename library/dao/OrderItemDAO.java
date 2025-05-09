package library.dao;

import library.model.OrderItem;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderItemDAO
 * CRUD for items within an order.
 */
public class OrderItemDAO {
    private Connection conn = DatabaseConnection.getConnection();

    public void addOrderItem(OrderItem oi) throws SQLException {
        String sql = "INSERT INTO OrderItems "
                + "(OrderItemID, OrderID, GameID, RentalFee) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, oi.getOrderItemId());
            ps.setInt(2, oi.getOrderId());
            ps.setInt(3, oi.getGameId());
            ps.setDouble(4, oi.getRentalFee());
            ps.executeUpdate();
        }
    }

    public List<OrderItem> getAllOrderItems() throws SQLException {
        List<OrderItem> list = new ArrayList<>();
        String sql = "SELECT * FROM OrderItems";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new OrderItem(
                        rs.getInt("OrderItemID"),
                        rs.getInt("OrderID"),
                        rs.getInt("GameID"),
                        rs.getDouble("RentalFee")
                ));
            }
        }
        return list;
    }

    public void updateOrderItem(OrderItem oi) throws SQLException {
        String sql = "UPDATE OrderItems SET "
                + "OrderID=?, GameID=?, RentalFee=? "
                + "WHERE OrderItemID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, oi.getOrderId());
            ps.setInt(2, oi.getGameId());
            ps.setDouble(3, oi.getRentalFee());
            ps.setInt(4, oi.getOrderItemId());
            ps.executeUpdate();
        }
    }

    public void deleteOrderItem(int id) throws SQLException {
        String sql = "DELETE FROM OrderItems WHERE OrderItemID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void addItem(int orderId, int gameId, double fee) throws SQLException {
        String sql = "INSERT INTO OrderItems (OrderID, GameID, RentalFee) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, gameId);
            ps.setDouble(3, fee);
            ps.executeUpdate();
        }
    }
}
