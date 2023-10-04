<%-- Include the header. --%>
<%@ include file="header.jsp" %>

	<h1>Restricted area</h1>
	
	<%-- In an ideal world we really SHOULD encode the HTML entities in
	this variable to be sure to prevent an XSS breach. --%>
	<p>Hello, <%= session.getAttribute("login") %> .</p>

<%-- Include the footer. --%>
<%@ include file="footer.jsp" %>