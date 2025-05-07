package library.servlet;

import library.dao.GameDAO;
import library.model.Game;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/games")
public class GameServlet extends HttpServlet {
    private GameDAO gameDAO;

    public void init() {
        gameDAO = new GameDAO();
    }

    // Handle GET (view list, load edit page, delete)
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("edit".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Game game = gameDAO.getGameById(id);
                request.setAttribute("game", game);
                RequestDispatcher dispatcher = request.getRequestDispatcher("web/editGame.jsp");
                dispatcher.forward(request, response);

            } else if ("delete".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                gameDAO.deleteGame(id);
                response.sendRedirect("games");

            } else {
                listGames(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Handle POST (add or update)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            String title = request.getParameter("title");
            String genre = request.getParameter("genre");
            String esrb = request.getParameter("esrb");
            String releaseDateStr = request.getParameter("releaseDate");
            String publisher = request.getParameter("publisher");
            double price = Double.parseDouble(request.getParameter("price"));
            java.util.Date releaseDate = new SimpleDateFormat("yyyy-MM-dd").parse(releaseDateStr);

            if ("add".equals(action)) {
                int newId = gameDAO.getNextGameId(); // implement this method to get max+1
                Game newGame = new Game(newId, title, genre, esrb, releaseDate, publisher, price);
                gameDAO.addGame(newGame);
            } else if ("update".equals(action)) {
                int id = Integer.parseInt(request.getParameter("id"));
                Game updatedGame = new Game(id, title, genre, esrb, releaseDate, publisher, price);
                gameDAO.updateGame(updatedGame);
            }

            response.sendRedirect("games");

        } catch (Exception e) {
            throw new ServletException("Error processing form", e);
        }
    }

    private void listGames(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        List<Game> games = gameDAO.getAllGames();
        request.setAttribute("games", games);
        RequestDispatcher dispatcher = request.getRequestDispatcher("web/listGames.jsp");
        dispatcher.forward(request, response);
    }
}
