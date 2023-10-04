package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import form.UpdateUserForm;

public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String ATT_USER = "utilisateur";
    public static final String VUE = "/WEB-INF/updateuser.jsp";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(ATT_USER);
        System.out.println(user);
        this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UpdateUserForm udpateUserForm = new UpdateUserForm();
        udpateUserForm.update(request);
        if (UpdateUserForm.supp) {
            String contextPath = getServletContext().getContextPath();
            response.sendRedirect(contextPath + "/deconnexion");
        } else {
            this.getServletContext().getRequestDispatcher(VUE).forward(request, response);
        }
    }
}