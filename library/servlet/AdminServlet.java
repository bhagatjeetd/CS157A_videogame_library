package library.servlet;

import library.dao.GameDAO;
import library.model.Game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private GameDAO dao = new GameDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Game> games = dao.getAllGames();
        req.setAttribute("games", games);
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }
}
