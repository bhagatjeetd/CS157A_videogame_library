<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head><title>Register</title></head>
<body>
    <h2>User Registration</h2>
    <form method="post" action="auth">
        <input type="hidden" name="action" value="register">

        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Phone: <input type="text" name="phone" required><br><br>
        Address: <input type="text" name="address" required><br><br>
        Username: <input type="text" name="username" required><br><br>
        Password: <input type="password" name="password" required><br><br>

        Role:
        <select name="role">
            <option value="customer">Customer</option>
            <option value="staff">Staff</option>
        </select><br><br>

        <input type="submit" value="Register">
    </form>
    <a href="login.jsp">Already have an account? Log in</a>
</body>
</html>
