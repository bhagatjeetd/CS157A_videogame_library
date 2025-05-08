<%@ page import="java.util.*, model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null || !"customer".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>
<html>
<head><title>Your Orders</title></head>
<body>
    <h2>Your Rentals</h2>
    <a href="games">Back to Catalog</a><br><br>

    <table border="1">
        <tr>
            <th>Order ID</th><th>Order Date</th><th>Due Date</th>
            <th>Return Date</th><th>Status</th><th>Total Fee</th><th>Action</th>
        </tr>
        <% for (Order o : orders) { %>
            <tr>
                <td><%= o.getOrderId() %></td>
                <td><%= o.getOrderDate() %></td>
                <td><%= o.getDueDate() %></td>
                <td><%= o.getReturnDate() != null ? o.getReturnDate() : "-" %></td>
                <td><%= o.getStatus() %></td>
                <td>$<%= String.format("%.2f", o.getTotalFee()) %></td>
                <td>
                    <% if ("Active".equals(o.getStatus())) { %>
                        <a href="order?action=return&orderId=<%= o.getOrderId() %>">Return</a>
                    <% } else { %>
                        —
                    <% } %>
                </td>
            </tr>
        <% } %>
    </table>
</body>
</html>
