<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="beans.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.AbstractDAOFactory"%>
<%@page import="dao.DAO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="search.css" />
<title>Search</title>
</head>
<body bgcolor="#EEEEEE">

<header id="header" role="banner" class="main-header">
	<div class="header-inner">
		<div class="header-logo">
			<a href="/ProjetJEE/home">HOME</a>
		</div>

		<nav class="header-nav">
			<ul>
				<li><a href="/ProjetJEE/post">Ecrire un Article</a></li>
				<li><a href="/ProjetJEE/updateuser">Modifier le compte</a></li>
				<li><a id="deco" href="/ProjetJEE/deconnexion" title="Déconnexion"></a></li>
			</ul>
		</nav>

	</div>
</header>

<div class="postView">
<%
    ArrayList<User> listFriends= new ArrayList<User>();
	listFriends = (ArrayList<User>) session.getAttribute("friends");
	User user = (User) session.getAttribute("utilisateur");
	if(listFriends.isEmpty()){
	 	out.println("<div class=\"post\">");   
	 	out.println("<h1>Votre recherche n'a pas donné lieu, ou vous suivez déjà cette personne");
	 	out.println("</div>");
	}
    for(int i =0; i < listFriends.size(); i++ ){%>
    <div class="post">
        <% User u = listFriends.get(i);
        	out.println("<p>Nom : "+u.getNom()+"</p>");
        	out.println("<br>");
        	out.println("<p>email : "+u.getEmail()+"</p>");
        	out.println("<br>");
        	out.println("<p>Date d'inscription : "+u.getDateInscription()+"</p>");
        	out.println("<br>");%>
        	<form action="" method="post">
        		<input type="hidden" name="otherId" value=<%out.println("'"+u.getId()+"'");%>>
        		<input type="submit" name="submit" value="Suivre">
        	</form>
    </div>
    <br>
    <%}%>
</div>

</body>
</html>