package library.dao;

import library.model.Order;
import library.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OrderDAO
 * CRUD for rental orders.
 */
public class OrderDAO {
    private Connection conn = DatabaseConnection.getConnection();

    public void addOrder(Order o) throws SQLException {
        String sql = "INSERT INTO Orders "
                + "(OrderID, CustomerID, OrderDate, DueDate, ReturnDate, TotalFee, OrderStatus, ProcessedBy) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getOrderId());
            ps.setInt(2, o.getCustomerId());
            ps.setTimestamp(3, new Timestamp(o.getOrderDate().getTime()));
            ps.setTimestamp(4, new Timestamp(o.getDueDate().getTime()));
            ps.setTimestamp(5, new Timestamp(o.getReturnDate().getTime()));
            ps.setDouble(6, o.getTotalFee());
            ps.setString(7, o.getStatus());
            ps.setInt(8, o.getProcessedBy());
            ps.executeUpdate();
        }
    }

    public List<Order> getAllOrders() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Order(
                        rs.getInt("OrderID"),
                        rs.getInt("CustomerID"),
                        rs.getTimestamp("OrderDate"),
                        rs.getTimestamp("DueDate"),
                        rs.getTimestamp("ReturnDate"),
                        rs.getDouble("TotalFee"),
                        rs.getString("OrderStatus"),
                        rs.getInt("ProcessedBy")
                ));
            }
        }
        return list;
    }

    public void updateOrder(Order o) throws SQLException {
        String sql = "UPDATE Orders SET "
                + "CustomerID=?, OrderDate=?, DueDate=?, ReturnDate=?, TotalFee=?, OrderStatus=?, ProcessedBy=? "
                + "WHERE OrderID=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, o.getCustomerId());
            ps.setTimestamp(2, new Timestamp(o.getOrderDate().getTime()));
            ps.setTimestamp(3, new Timestamp(o.getDueDate().getTime()));
            ps.setTimestamp(4, new Timestamp(o.getReturnDate().getTime()));
            ps.setDouble(5, o.getTotalFee());
            ps.setString(6, o.getStatus());
            ps.setInt(7, o.getProcessedBy());
            ps.setInt(8, o.getOrderId());
            ps.executeUpdate();
        }
    }

    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM Orders WHERE OrderID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
