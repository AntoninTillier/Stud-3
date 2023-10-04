package servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Article;
import beans.Commentaire;
import dao.AbstractDAOFactory;
import dao.DAO;
import form.PostForm;

/**
 * Servlet implementation class Post
 */

public class Post extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/post.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings({"unused", "unchecked"})
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("j");
        String m = request.getParameter("i");
        if (s != null) {
            long id = Integer.valueOf(s);
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Article> articleDAO = adf.getArticle();
            ArrayList<Article> listArticle = new ArrayList<Article>();
            HttpSession session = request.getSession();
            listArticle = (ArrayList<Article>) session.getAttribute("homeForm");
            Article a = null;
            for (int i = 0; i < listArticle.size(); i++) {
                if (listArticle.get(i).getId() == Integer.valueOf(request.getParameter("j")))
                    a = listArticle.get(i);
            }
            if (articleDAO.delete(a)) {
                File f = new File(a.getImage());
                f.delete();
                DAO<Commentaire> commentaireDAO = adf.getCommentaire();
                commentaireDAO.delete(a.getId());
            }
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/home");
        } else if (m != null) {
            long id = Integer.valueOf(m);
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<Article> articleDAO = adf.getArticle();
            ArrayList<Article> listArticle = new ArrayList<Article>();
            HttpSession session = request.getSession();
            listArticle = (ArrayList<Article>) session.getAttribute("homeForm");
            Article a = null;
            for (int i = 0; i < listArticle.size(); i++) {
                if (listArticle.get(i).getId() == Integer.valueOf(request.getParameter("i")))
                    a = listArticle.get(i);
            }
            request.setAttribute("title", a.getTitre());
            request.setAttribute("content", a.getValue());
            request.setAttribute("submit", "Modifier");
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("i");
        PostForm postForm = new PostForm();
        Article article = null;
        Article a = null;
        if (s == null)
            article = postForm.postArticle(request);
        else {
            article = postForm.updateArticle(request);
            ArrayList<Article> listArticle = new ArrayList<Article>();
            HttpSession session = request.getSession();
            listArticle = (ArrayList<Article>) session.getAttribute("homeForm");
            for (int i = 0; i < listArticle.size(); i++) {
                if (listArticle.get(i).getId() == Integer.valueOf(request.getParameter("i")))
                    a = listArticle.get(i);
            }
            request.setAttribute("title", a.getTitre());
            request.setAttribute("content", a.getValue());
            request.setAttribute("submit", "Modifier");
        }
        if (article != null) {
            if (article.getId() == 0) {
                String contextPath = getServletContext().getContextPath();
                response.sendRedirect(contextPath + "/home");
            } else {
                String contextPath = getServletContext().getContextPath();
                response.sendRedirect(contextPath + "/view?i=" + a.getId());
            }
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}