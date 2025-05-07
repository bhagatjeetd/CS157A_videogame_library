<%@ page import="java.util.*, model.Game" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Your Wishlist</title></head>
<body>
    <h2>Your Wishlist</h2>
    <table border="1">
        <tr>
            <th>Title</th><th>Genre</th><th>Remove</th>
        </tr>
        <%
            List<Game> wishlist = (List<Game>) request.getAttribute("wishlist");
            for (Game g : wishlist) {
        %>
        <tr>
            <td><%= g.getTitle() %></td>
            <td><%= g.getGenre() %></td>
            <td>
                <form action="wishlist" method="post">
                    <input type="hidden" name="action" value="remove">
                    <input type="hidden" name="gameId" value="<%= g.getId() %>">
                    <button type="submit">Remove</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <a href="games">← Back to Games</a>
</body>
</html>
