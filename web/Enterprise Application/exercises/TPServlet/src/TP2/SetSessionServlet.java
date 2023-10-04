package TP2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SetSessionServlet
 */
@WebServlet("/SetSessionServlet")
public class SetSessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetSessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        String content = "<!DOCTYPE html><html>"
                + "<head>"
                + "  <meta charset=\"UTF-8\" />"
                + "  <title>Set Session</title>"
                + "</head>"
                + "<body>"
                + "  <form action=\"\" method=\"POST\""
                + "    <label>Pr&eacutenom :"
                + "      <input type=\"text\" name=\"firstname\" />"
                + "    </label><br />"
                + "    <input type=\"submit\" />"
                + "  </form>"
                + "</body></html>";
        out.println(content);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
        String firstname = request.getParameter("firstname");
        HttpSession session = request.getSession(true);
        String contextPath = getServletContext().getContextPath();
        if (null == firstname || firstname.isEmpty()) {
            String pathInfo = request.getServletPath();
            response.sendRedirect(contextPath + pathInfo);
            return;
        }
        session.setAttribute("firstname", firstname);
        response.sendRedirect(contextPath + "/UserServlet");
    }
}