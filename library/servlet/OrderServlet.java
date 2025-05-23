package library.servlet;

import library.dao.OrderDAO;
import library.dao.OrderItemDAO;
import library.dao.GameDAO;
import library.model.Customer;
import library.model.Game;
import library.model.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO;
    private OrderItemDAO itemDAO;
    private GameDAO gameDAO;

    public void init() {
        orderDAO = new OrderDAO();
        itemDAO = new OrderItemDAO();
        gameDAO = new GameDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("rentForm".equals(action)) {
                int gameId = Integer.parseInt(request.getParameter("gameId"));
                Game game = gameDAO.getGameById(gameId);
                request.setAttribute("game", game);
                request.getRequestDispatcher("web/rentGame.jsp").forward(request, response);
            } else if ("return".equals(action)) {
                int orderId = Integer.parseInt(request.getParameter("orderId"));
                orderDAO.returnOrder(orderId);  // Marks ReturnDate
                response.sendRedirect("order");
            } else {
                Customer customer = (Customer) request.getSession().getAttribute("user");
                List<Order> orders = orderDAO.getOrdersByCustomer(customer.getId());
                request.setAttribute("orders", orders);
                request.getRequestDispatcher("web/orders.jsp").forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("rent".equals(action)) {
                Customer customer = (Customer) request.getSession().getAttribute("user");
                int gameId = Integer.parseInt(request.getParameter("gameId"));
                int duration = Integer.parseInt(request.getParameter("duration"));

                java.util.Date utilToday = new java.util.Date();
                java.util.Date utilDue = new java.util.Date(utilToday.getTime() + (duration * 86400000L));

                java.sql.Date today = new java.sql.Date(utilToday.getTime());
                java.sql.Date due = new java.sql.Date(utilDue.getTime());

                Game game = gameDAO.getGameById(gameId);
                int orderId = orderDAO.createOrder(customer.getId(), today, due, game.getBaseRentalPrice());
                itemDAO.addItem(orderId, gameId, game.getBaseRentalPrice());

                response.sendRedirect("order");
          }

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
