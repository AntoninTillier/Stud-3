package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.AbstractDAOFactory;
import dao.DAO;


public class History extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/history.jsp";
    public static final String ATT_USER = "utilisateur";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public History() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATT_USER);
        AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
        DAO<User> userDAO = adf.getUser();
        ArrayList<String> listH = userDAO.getAllHistory(user);
        Collections.reverse(listH);
        request.setAttribute("listH", listH);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}