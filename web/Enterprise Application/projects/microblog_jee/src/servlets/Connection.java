package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;

import form.ConnexionForm;

public class Connection extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/connection.jsp";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        ConnexionForm form = new ConnexionForm();
        /* Traitement de la requête et récupération du bean en résultant */
        User utilisateur = form.connecterUtilisateur(request);
        /* Récupération de la session depuis la requête */
        HttpSession session = request.getSession();
        System.out.println(form.getErreurs());
        if (form.getErreurs().isEmpty() && utilisateur != null) {
            session.setAttribute(ATT_USER, utilisateur);
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}