<%@ page import="java.util.*, model.Order" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Reports</title></head>
<body>
    <h2>📊 Reports</h2>
    <a href="admin.jsp">← Back to Admin</a><br><br>

    <h3>Current Rentals</h3>
    <table border="1">
        <tr><th>Order ID</th><th>Customer</th><th>Due Date</th><th>Status</th></tr>
        <%
            List<Order> current = (List<Order>) request.getAttribute("currentOrders");
            for (Order o : current) {
        %>
        <tr>
            <td><%= o.getOrderId() %></td>
            <td><%= o.getCustomerId() %></td>
            <td><%= o.getDueDate() %></td>
            <td><%= o.getStatus() %></td>
        </tr>
        <% } %>
    </table>

    <h3>Overdue Rentals</h3>
    <table border="1">
        <tr><th>Order ID</th><th>Customer</th><th>Due Date</th><th>Status</th></tr>
        <%
            List<Order> overdue = (List<Order>) request.getAttribute("overdueOrders");
            for (Order o : overdue) {
        %>
        <tr>
            <td><%= o.getOrderId() %></td>
            <td><%= o.getCustomerId() %></td>
            <td><%= o.getDueDate() %></td>
            <td><%= o.getStatus() %></td>
        </tr>
        <% } %>
    </table>

    <h3>Total Revenue</h3>
    <p>$<%= request.getAttribute("revenue") %></p>
</body>
</html>
