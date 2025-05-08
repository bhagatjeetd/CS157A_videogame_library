package library.servlet;

import library.dao.CustomerDAO;
import library.model.Customer;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Date;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private CustomerDAO dao = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            Customer c = new Customer(
                    0,
                    req.getParameter("name"),
                    req.getParameter("email"),
                    req.getParameter("phone"),
                    req.getParameter("address"),
                    req.getParameter("username"),
                    req.getParameter("passwordHash"),
                    new Date()
            );
            dao.addCustomer(c);
            resp.sendRedirect("login.jsp");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
