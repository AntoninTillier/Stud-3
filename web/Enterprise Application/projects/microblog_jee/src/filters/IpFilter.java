package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class IpFilter
 */
@WebFilter(filterName = "IpFilter", urlPatterns = {"/*"})
public class IpFilter implements Filter {

    /**
     * Default constructor.
     */
    public IpFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        System.out.println("IpFilter");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String contextPath = request.getServletContext().getContextPath();
        // The current IP of the user, may be the same or different.
        String newIp = request.getRemoteAddr();
        // The current User-Agent of the user, may be the same or different.
        String newUserAgent = request.getHeader("user-agent");
        // The IP address as we may have already stored.
        String oldIp = (String) session.getAttribute("ip");
        // The User-Agent as we may have already stored.
        String oldUserAgent = (String) session.getAttribute("user-agent");
        try {
            // Is the current IP address different from the stored one?
            if (!newIp.equals(oldIp)) {
                session.setAttribute("ip", newIp);
            }
            // Is the current User-Agent different from the stored one?
            if (!newUserAgent.equals(oldUserAgent)) {
                session.setAttribute("user-agent", newUserAgent);
            }
            chain.doFilter(request, response);
        } catch (IllegalStateException e) {
            // Session seems to be invalid, redirect the user to
            // the form.
            response.sendRedirect(contextPath + "/connexion");
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}