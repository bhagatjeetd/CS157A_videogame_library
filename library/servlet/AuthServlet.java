package library.servlet;

import library.dao.UserDAO;
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

                Customer customer = userDAO.loginCustomer(username, hash);
                if (customer != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", customer);
                    session.setAttribute("role", "customer");
                    response.sendRedirect("index.jsp");
                    return;
                }

                Staff staff = userDAO.loginStaff(username, hash);
                if (staff != null) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", staff);
                    session.setAttribute("role", "staff");
                    response.sendRedirect("index.jsp");
                    return;
                }
                //if login fails
                request.setAttribute("error", "Invalid credentials");
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else if ("register".equals(action)) {
                // Simplified registration logic
                String role = request.getParameter("role");
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String hash = hashPassword(password);

                if ("customer".equals(role)) {
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    userDAO.registerCustomer(name, email, phone, address, username, hash);
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
