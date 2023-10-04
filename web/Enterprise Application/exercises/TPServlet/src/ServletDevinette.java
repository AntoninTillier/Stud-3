

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
 * Servlet implementation class ServletDevinette
 */
@WebServlet("/ServletDevinette")
public class ServletDevinette extends HttpServlet {
    private static final long serialVersionUID = 1L;
    int nombreAleatoire = (int) (Math.random() * ((100) + 1));

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDevinette() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        BufferedReader br = new BufferedReader(new FileReader("/Users/macbookpro/Documents/Java programme/TPServlet/WebContent/NewFile2.html"));
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
        doGet(request, response);
        request.getSession().setAttribute("n", request.getParameter("n"));
        request.getSession().setAttribute("nb", nombreAleatoire);
        request.getRequestDispatcher("/ServletDevinetteRes").forward(request, response);
    }
}