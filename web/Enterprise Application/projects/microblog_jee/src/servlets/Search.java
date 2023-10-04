package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;

public class Search extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/search.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getParameter("otherId");
        if (s != null) {
            AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
            DAO<User> userDAO = adf.getUser();
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute(ATT_USER);
            if (userDAO.createFriend(user, Integer.valueOf(s))) {
                Date date = new Date();
                DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
                User otherU = userDAO.findId(Integer.valueOf(s));
                String res = "Vous venez de suivre " + otherU.getNom();
                String otheres = "Vous êtes suivie par " + user.getNom();
                userDAO.createHistory(user, shortDateFormat.format(date), res);
                userDAO.createHistory(otherU, shortDateFormat.format(date), otheres);
                String contextPath = getServletContext().getContextPath();
                response.sendRedirect(contextPath + "/home");
            } else {
                this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
            }
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}