package filters;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class IpListener
 */
@WebListener
public class IpListener implements HttpSessionAttributeListener {
    /**
     * Default constructor.
     */
    public IpListener() {
        // TODO Auto-generated constructor stub
    }
    /**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent se) {
        // TODO Auto-generated method stub
        HttpSession session = se.getSession();
        if (se.getName().equals("ip") || se.getName().equals("user-agent")) {
            session.invalidate();
        }
    }
}