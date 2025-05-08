package servlet;

import dao.OrderDAO;
import model.Order;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/report")
public class ReportServlet extends HttpServlet {
    private OrderDAO orderDAO;

    public void init() {
        orderDAO = new OrderDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Order> current = orderDAO.getCurrentRentals();
            List<Order> overdue = orderDAO.getOverdueRentals();
            double revenue = orderDAO.getTotalRevenue();

            request.setAttribute("currentOrders", current);
            request.setAttribute("overdueOrders", overdue);
            request.setAttribute("revenue", revenue);

            RequestDispatcher dispatcher = request.getRequestDispatcher("web/report.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
