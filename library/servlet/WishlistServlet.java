package library.servlet;

import library.dao.GameDAO;
import library.dao.WishlistDAO;
import library.model.Customer;
import library.model.Game;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    private WishlistDAO wishlistDAO;
    private GameDAO gameDAO;

    public void init() {
        wishlistDAO = new WishlistDAO();
        gameDAO = new GameDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("user");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            List<Integer> gameIds = wishlistDAO.getWishlistGameIds(customer.getId());
            List<Game> games = new ArrayList<>();
            for (int gameId : gameIds) {
                games.add(gameDAO.getGameById(gameId));
            }

            request.setAttribute("wishlist", games);
            RequestDispatcher dispatcher = request.getRequestDispatcher("web/wishlist.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("user");
        if (customer == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");
        int gameId = Integer.parseInt(request.getParameter("gameId"));

        try {
            if ("add".equals(action)) {
                wishlistDAO.addToWishlist(customer.getId(), gameId);
            } else if ("remove".equals(action)) {
                wishlistDAO.removeFromWishlist(customer.getId(), gameId);
            }

            response.sendRedirect("wishlist");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
