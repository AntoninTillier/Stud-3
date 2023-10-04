

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletConnect
 */
@WebServlet("/ServletConnect")
public class ServletConnect extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConnect() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        BufferedReader br = new BufferedReader(new FileReader("/Users/macbookpro/Documents/Java programme/TPServlet/WebContent/NewFile.html"));
        String line;
        String html = "";
        while ((line = br.readLine()) != null) {
            html += line;
        }
        br.close();
        out.append(html);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.getSession().setAttribute("username", request.getParameter("username"));
        request.getSession().setAttribute("password", request.getParameter("password"));
        request.getRequestDispatcher("/ServletWelcome").forward(request, response);
    }
}