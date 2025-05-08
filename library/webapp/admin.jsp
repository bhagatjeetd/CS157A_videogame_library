<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Object user = session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null || !"staff".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head><title>Admin Dashboard</title></head>
<body>
    <h2>Admin Dashboard</h2>
    <ul>
        <li><a href="games">Manage Games</a></li>
        <li><a href="report">View Reports</a></li>
        <li><a href="logout.jsp">Logout</a></li>
    </ul>
</body>
</html>
