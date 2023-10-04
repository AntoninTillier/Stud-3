package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Commentaire;
import dao.AbstractDAOFactory;
import dao.DAO;
import form.CommentsForm;

/**
 * Servlet implementation class Comments
 */
public class Comments extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/comments.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Comments() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("j");
        String m = request.getParameter("i");
        if (s != null) {
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            ArrayList<Commentaire> listCom = new ArrayList<Commentaire>();
            DAO<Commentaire> commentaireDAO = adf.getCommentaire();
            listCom = commentaireDAO.getAll();
            Commentaire c = null;
            for (int i = 0; i < listCom.size(); i++) {
                if (listCom.get(i).getId() == Integer.valueOf(request.getParameter("j")))
                    c = listCom.get(i);
            }
            commentaireDAO.delete(c);
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        } else if (m != null) {
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            ArrayList<Commentaire> listCom = new ArrayList<Commentaire>();
            DAO<Commentaire> commentaireDAO = adf.getCommentaire();
            listCom = commentaireDAO.getAll();
            Commentaire c = null;
            for (int i = 0; i < listCom.size(); i++) {
                if (listCom.get(i).getId() == Integer.valueOf(request.getParameter("i")))
                    c = listCom.get(i);
            }
            request.setAttribute("content", c.getValue());
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("i");
        CommentsForm commentsForm = new CommentsForm();
        Commentaire comment = null;
        if (s != null)
            comment = commentsForm.updateComment(request);
        else {
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        }
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        ArrayList<Commentaire> listCom = new ArrayList<Commentaire>();
        DAO<Commentaire> commentaireDAO = adf.getCommentaire();
        listCom = commentaireDAO.getAll();
        Commentaire c = null;
        for (int i = 0; i < listCom.size(); i++) {
            if (listCom.get(i).getId() == Integer.valueOf(request.getParameter("i")))
                c = listCom.get(i);
        }
        if (comment != null) {
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/view?i=" + c.getPostId());
        } else {
            request.setAttribute("content", c.getValue());
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}