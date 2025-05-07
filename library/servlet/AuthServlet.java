package library.servlet;

import library.dao.UserDAO; // you’ll need to write this
import library.model.Customer;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.security.MessageDigest;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String action = request.getParameter("action");

        try {
            if ("login".equals(action)) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String hash = hashPassword(password);

                Object user = userDAO.login(username, hash);
                if (user != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", user);
                    session.setAttribute("role", (user instanceof Customer) ? "customer" : "staff");
                    response.sendRedirect("index.jsp");
                } else {
                    request.setAttribute("error", "Invalid credentials");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                }

            } else if ("register".equals(action)) {
                // Simplified registration logic
                String role = request.getParameter("role");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String hash = hashPassword(password);

                if ("customer".equals(role)) {
                    userDAO.registerCustomer(name, email, username, hash);
                } else {
                    userDAO.registerStaff(name, email, username, hash);
                }

                response.sendRedirect("login.jsp");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String hashPassword(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] hash = md.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : hash) sb.append(String.format("%02x", b));
        return sb.toString();
    }
}
