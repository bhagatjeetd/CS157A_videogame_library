<%@ page import="java.util.*, library.Game" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Game List</title></head>
<body>
    <h2>Game Catalog</h2>
    <a href="addGame.jsp">Add New Game</a><br><br>

    <table border="1">
        <tr>
            <th>ID</th><th>Title</th><th>Genre</th><th>ESRB</th><th>Release Date</th><th>Publisher</th><th>Price</th><th>Actions</th>
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
            <td><%= g.getBaseRentalPrice() %></td>
            <td>
                <a href="editGame.jsp?id=<%= g.getId() %>">Edit</a> |
                <a href="games?action=delete&id=<%= g.getId() %>">Delete</a>
            </td>
        </tr>
        <% } %>
    </table>
</body>
</html>
