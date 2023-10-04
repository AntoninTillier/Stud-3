**Listeners and Filters**

Session theft involves stealing the content of another user's JSESSIONID cookie in order to be recognized as if you were that user.

**Exercise 1: Session Theft Detection**

- Create a project named "TPListenersFilters."
- Create a servlet called "SetSessionServlet" that displays a form asking for the user's first name.
- Process this form in the `doPost()` method and store the user's first name in a session attribute.
- Create a servlet named "UserServlet" that displays the user's first name stored in the session if it exists, or redirects to "SetSessionServlet" otherwise.
- After entering a first name, copy the value of the "JSESSIONID" cookie.
- Open "UserServlet" in another web browser.
- Modify the value of the "JSESSIONID" cookie to the copied value.
- Return to "UserServlet."

**Exercise 2: Protection Against Session Theft**

- Create an "IpFilter" filter that stores the user's IP address and user-agent (HTTP header "user-agent") in the session. Only update the values if they are different from those currently stored in the session. Use `request.getRemoteAddr()` to read the user's IP address.
- Configure "IpFilter" to be applied to all URLs using the `@WebFilter` annotation.
- Create an "IpListener" listener that reacts when a session attribute changes (implementing `HttpSessionAttributeListener`).
- Add the `@WebListener` annotation to "IpListener."
- Complete "IpListener" to react when the IP address or user-agent is modified in the session. Invalidate the session.
- Modify "IpFilter" to handle the case where the session is invalidated by "IpListener," and in that case, redirect to the form. An `IllegalStateException` is thrown when trying to access an invalidated session.