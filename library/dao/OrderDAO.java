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
    // added following 4 below for reports that would be avaiable from admin view

    //Returns all rental orders that are still active
    public List<Order> getCurrentRentals() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE OrderStatus = 'Active'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(parseOrder(rs));
            }
        }
        return list;
    }

    //Returns orders that are overdue
    public List<Order> getOverdueRentals() throws SQLException {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE OrderStatus = 'Active' AND DueDate < NOW()";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(parseOrder(rs));
            }
        }
        return list;
    }
    //Calculates the total revenue from returned orders and sums TotalFee
    public double getTotalRevenue() throws SQLException {
        String sql = "SELECT SUM(TotalFee) AS Revenue FROM Orders WHERE OrderStatus = 'Returned'";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            if (rs.next()) {
                return rs.getDouble("Revenue");
            }
        }
        return 0.0;
    }
    //Util method to construct an Order object from a ResultSet (avoids dupes)
    private Order parseOrder(ResultSet rs) throws SQLException {
        return new Order(
            rs.getInt("OrderID"),
            rs.getInt("CustomerID"),
            rs.getTimestamp("OrderDate"),
            rs.getTimestamp("DueDate"),
            rs.getTimestamp("ReturnDate"),
            rs.getDouble("TotalFee"),
            rs.getString("OrderStatus"),
            rs.getInt("ProcessedBy")
        );
    }

    public int createOrder(int customerId, Date orderDate, Date dueDate, double fee) throws SQLException {
        String sql = "INSERT INTO Orders (CustomerID, OrderDate, DueDate, TotalFee, OrderStatus) VALUES (?, ?, ?, ?, 'Active')";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, customerId);
            ps.setTimestamp(2, new Timestamp(orderDate.getTime()));
            ps.setTimestamp(3, new Timestamp(dueDate.getTime()));
            ps.setDouble(4, fee);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        }
        return -1;
    }

    public void returnOrder(int orderId) throws SQLException {
        String sql = "UPDATE Orders SET ReturnDate = NOW(), OrderStatus = 'Returned' WHERE OrderID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.executeUpdate();
        }
    }

    public List<Order> getOrdersByCustomer(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders WHERE CustomerID = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(
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
        }
        return orders;
    }
}
