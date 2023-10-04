**JSP**

**Exercice 1: Administrator Login**
- Create a TPJSP project.
- Create the JSP /WEB-INF/admin/login.jsp containing a login form with a username and password.
- Create a servlet tp.servlets.admin.Login, which forwards the request to the login JSP.
- Create the JSP /WEB-INF/admin/reserved.jsp, which contains an HTML page greeting the administrator.
- Create a servlet tp.servlets.admin.Reserved, which displays the reserved JSP.
- Store the administrator's login credentials in the web.xml file using init-parameters.
- Process the login form. If the credentials are valid, store the login in the session and redirect the administrator to the Reserved servlet.
- Create a filter that applies only to the Reserved servlet and redirects the user to the Login servlet if they are not the administrator. It ensures that only the administrator has access to this page.
- On the reserved JSP, display the administrator's login using an expression.

**Exercice 2: File Separation**
- Create a JSP WEB-INF/admin/header.jsp containing the HTML DOCTYPE up to the opening of the HTML body.
- Create a JSP WEB-INF/admin/footer.jsp that contains the closing of the body and HTML.
- In the login and reserved JSPs, remove the opening and closing of the HTML document and instead include the header and footer.
- In the header JSP, set the html > head > title tag with the content of the "title" request attribute if it exists, or default to "Administration" using scriptlets.
- In the Login servlet, set the "title" request attribute to give a title to the page.
- In the reserved JSP, use declarations to store the time of each page visit in a list.
- Display this list on the reserved page using scriptlets.