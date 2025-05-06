<%@ page import="model.Game" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    Game game = (Game) request.getAttribute("game");
%>
<html>
<head><title>Edit Game</title></head>
<body>
    <h2>Edit Game</h2>
    <form action="games" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="<%= game.getId() %>">
        Title: <input type="text" name="title" value="<%= game.getTitle() %>" required><br><br>
        Genre: <input type="text" name="genre" value="<%= game.getGenre() %>" required><br><br>
        ESRB: <input type="text" name="esrb" value="<%= game.getEsrb() %>" required><br><br>
        Release Date: <input type="date" name="releaseDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(game.getReleaseDate()) %>" required><br><br>
        Publisher: <input type="text" name="publisher" value="<%= game.getPublisher() %>" required><br><br>
        Base Rental Price: <input type="number" name="price" step="0.01" value="<%= game.getBaseRentalPrice() %>" required><br><br>
        <input type="submit" value="Update Game">
    </form>
    <a href="games">Back to Game List</a>
</body>
</html>
