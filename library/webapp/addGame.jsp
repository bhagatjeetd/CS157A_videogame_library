<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Add Game</title></head>
<body>
    <h2>Add New Game</h2>
    <form action="games" method="post">
        <input type="hidden" name="action" value="add">
        Title: <input type="text" name="title" required><br><br>
        Genre: <input type="text" name="genre" required><br><br>
        ESRB: <input type="text" name="esrb" required><br><br>
        Release Date: <input type="date" name="releaseDate" required><br><br>
        Publisher: <input type="text" name="publisher" required><br><br>
        Base Rental Price: <input type="number" name="price" step="0.01" required><br><br>
        <input type="submit" value="Add Game">
    </form>
    <a href="games">Back to Game List</a>
</body>
</html>
