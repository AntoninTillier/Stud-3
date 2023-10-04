package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;
import form.HomeForm;


public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/home.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATT_USER);
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        user = userDAO.find(user.getEmail());
        session.setAttribute(ATT_USER, user);
        HomeForm homeForm = new HomeForm();
        homeForm.listArticle(request);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HomeForm homeForm = new HomeForm();
        ArrayList<User> listUser = homeForm.addFriends(request);
        if (listUser != null) {
            HttpSession session = request.getSession();
            session.setAttribute("friends", listUser);
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/search");
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}