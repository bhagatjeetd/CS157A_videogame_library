<%@ page import="java.util.*, model.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null || !"customer".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<html>
<head>
    <title>Your Wishlist</title>
    <style>
        table { border-collapse: collapse; width: 80%; margin: auto; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: center; }
        th { background-color: #f2f2f2; }
        .btn { padding: 5px 10px; margin: 2px; }
    </style>
</head>
<body>
    <h2 style="text-align:center;">🎮 Your Wishlist</h2>

    <div style="text-align:center; margin-bottom: 20px;">
        <a href="games">← Back to Game Catalog</a> |
        <a href="logout.jsp">Logout</a>
    </div>

    <%
        List<Game> wishlist = List<Game>) request.getAttribute("wishlist");

        if (wishlist == null || wishlist.isEmpty()) {
    %>
        <p style="text-align:center;">Your wishlist is currently empty.</p>
    <%
        } else {
    %>
        <table>
            <tr>
                <th>Title</th>
                <th>Genre</th>
                <th>ESRB</th>
                <th>Release Date</th>
                <th>Publisher</th>
                <th>Price ($)</th>
                <th>Remove</th>
            </tr>
            <% for (Game g : wishlist) { %>
                <tr>
                    <td><%= g.getTitle() %></td>
                    <td><%= g.getGenre() %></td>
                    <td><%= g.getEsrb() %></td>
                    <td><%= g.getReleaseDate() %></td>
                    <td><%= g.getPublisher() %></td>
                    <td><%= String.format("%.2f", g.getBaseRentalPrice()) %></td>
                    <td>
                        <form action="wishlist" method="post" style="display:inline;">
                            <input type="hidden" name="action" value="remove">
                            <input type="hidden" name="gameId" value="<%= g.getId() %>">
                            <button type="submit" class="btn">❌ Remove</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
    <%
        }
    %>
</body>
</html>
