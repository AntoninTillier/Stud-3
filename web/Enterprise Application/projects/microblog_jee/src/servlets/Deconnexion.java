package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Deconnexion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final String VUE = "/connection";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Récupération et destruction de la session en cours */
        HttpSession session = request.getSession();
        session.invalidate();
        /* Affichage de la page de connexion */
        String contextPath = getServletContext().getContextPath();
        response.sendRedirect(contextPath + VUE);
    }
}