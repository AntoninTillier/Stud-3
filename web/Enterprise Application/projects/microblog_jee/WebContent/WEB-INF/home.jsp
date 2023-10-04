<%@page import="beans.Article"%>
<%@page import="beans.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="home.css" />
<title>Home</title>
</head>
<body bgcolor="#EEEEEE">

<header id="header" role="banner" class="main-header">
	<div class="header-inner">
		<div class="header-logo">
			<img src="logo.png" alt="Liste des Annonces" width="150" height="45">
		</div>
		<form method="post" action="">
			<div>
				<input id="search" type="text" name="search" placeholder="Recherher quelqu'un">
				<input id="bSearch" type="submit" name="submit" value="">
			</div>
		</form>
		<nav class="header-nav">
			<ul>
				<li><a href="/ProjetJEE/post">Ecrire un Article</a></li>
				<li><a href="/ProjetJEE/updateuser">Modifier le compte</a></li>
				<li><a id="deco" href="/ProjetJEE/deconnexion" title="Déconnexion"></a></li>
			</ul>
		</nav>

	</div>
</header>

<%
    ArrayList<Article> listArticle= new ArrayList<Article>();
	listArticle = (ArrayList<Article>) session.getAttribute("homeForm");
	User user = (User) session.getAttribute("utilisateur");%>

<div id="global">
	<div id="menu">
		<h1>Bonjour, <% out.println(user.getNom());%></h1>
		<br>
		<a id="histo" href="/ProjetJEE/history">Historique</a>
	</div>

	<div class="postView"> 
    	<% for(int i =0; i < listArticle.size(); i++ ){%>
    	<div class="post">
        	<% Article a = listArticle.get(i);
        	StringBuilder s = new StringBuilder();
        	s.append(
        		"<p>Article posté le, "+a.getDate()+"</p>"+
				"<br>"+
				"<a id=\"linkPost\" href=\"/ProjetJEE/view?i="+a.getId()+"\">"+a.getTitre()+"</a>"
			);
        	if(a.getIdUser() == user.getId()){
            	s.append(
        	   		 	"<br>"+
        	    		"<br>"+
          				"<br>"+
        	    		"<a id=\"modif\" href=\"/ProjetJEE/post?i="+a.getId()+"\">Modifier</a> "+
                    	"<a id=\"supp\" href=\"/ProjetJEE/post?j="+a.getId()+"\">Supprimer</a>"
        	    	);
        	}
       	 	out.println(s);%>
    	</div>
    	<br>
    	<%}%>
	</div>
</div>
</body>
</html>