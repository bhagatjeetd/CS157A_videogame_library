<%@ page import="model.Game" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Object user = session.getAttribute("user");
    String role = (String) session.getAttribute("role");

    if (user == null || !"customer".equals(role)) {
        response.sendRedirect("login.jsp");
        return;
    }

    Game game = (Game) request.getAttribute("game");
%>
<html>
<head><title>Rent Game</title></head>
<body>
    <h2>Rent: <%= game.getTitle() %></h2>
    <form method="post" action="order">
        <input type="hidden" name="action" value="rent">
        <input type="hidden" name="gameId" value="<%= game.getId() %>">
        Rental Duration (days): <input type="number" name="duration" min="1" value="3" required><br><br>
        <input type="submit" value="Confirm Rental">
    </form>
    <a href="games">Back to Catalog</a>
</body>
</html>
