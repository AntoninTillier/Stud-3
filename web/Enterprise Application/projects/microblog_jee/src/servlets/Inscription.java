package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import form.InscriptionForm;

/**
 * Servlet implementation class Inscription
 */

public class Inscription extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/inscription.jsp";

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* Préparation de l'objet formulaire */
        InscriptionForm form = new InscriptionForm();
        /* Traitement de la requête et récupération du bean en résultant */
        User utilisateur = form.inscrireUtilisateur(request);
        HttpSession session = request.getSession();
        /* Stockage du formulaire et du bean dans l'objet request */
        session.setAttribute(ATT_USER, utilisateur);
        if (form.getErreurs().isEmpty()) {
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}