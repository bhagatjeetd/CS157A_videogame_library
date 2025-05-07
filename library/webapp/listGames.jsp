<%@ page import="java.util.*, model.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    boolean isStaff = "staff".equals(role);
    boolean isCustomer = "customer".equals(role);
%>
<html>
<head>
    <title>Game Catalog</title>
    <style>
        table { border-collapse: collapse; width: 90%; margin: auto; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
        th { background-color: #f2f2f2; }
        a, button { margin: 2px; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">Game Catalog</h2>

    <div style="text-align:center; margin-bottom: 20px;">
        <% if (isStaff) { %>
            <a href="addGame.jsp">➕ Add New Game</a> |
        <% } %>
        <% if (isCustomer) { %>
            <a href="wishlist">❤️ View Wishlist</a> |
        <% } %>
        <a href="logout.jsp">🚪 Logout</a>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>Title</th>
            <th>Genre</th>
            <th>ESRB</th>
            <th>Release Date</th>
            <th>Publisher</th>
            <th>Price ($)</th>
            <th>Actions</th>
        </tr>
        <%
            List<Game> games = (List<Game>) request.getAttribute("games");
            for (Game g : games) {
        %>
        <tr>
            <td><%= g.getId() %></td>
            <td><%= g.getTitle() %></td>
            <td><%= g.getGenre() %></td>
            <td><%= g.getEsrb() %></td>
            <td><%= g.getReleaseDate() %></td>
            <td><%= g.getPublisher() %></td>
            <td><%= String.format("%.2f", g.getBaseRentalPrice()) %></td>
            <td>
                <% if (isCustomer) { %>
                    <form action="wishlist" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="gameId" value="<%= g.getId() %>">
                        <button type="submit">❤️ Add to Wishlist</button>
                    </form>
                <% } %>

                <% if (isStaff) { %>
                    <a href="games?action=edit&id=<%= g.getId() %>">✏️ Edit</a>
                    <a href="games?action=delete&id=<%= g.getId() %>"
                       onclick="return confirm('Are you sure you want to delete this game?');">🗑️ Delete</a>
                <% } %>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
