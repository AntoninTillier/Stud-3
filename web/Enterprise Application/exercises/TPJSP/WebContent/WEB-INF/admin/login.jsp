<%-- Include the header. --%>
<%@ include file="header.jsp" %>

		<!-- action="" means the form should be sent to the current URL. -->
		<form action="" method="POST">
			<label>
				Login: <input type="text" name="login" />
			</label>
			<br />
			<label>
				Password: <input type="password" name="password" />
			</label>
			<br />
			<input type="submit" value="Submit" />
		</form>

<%-- Include the footer. --%>
<%@ include file="footer.jsp" %>