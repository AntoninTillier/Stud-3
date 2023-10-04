package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import form.ViewForm;

/**
 * Servlet implementation class View
 */

public class View extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String VUE = "/WEB-INF/view.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public View() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewForm viewForm = new ViewForm();
        viewForm.createComment(request);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }
}