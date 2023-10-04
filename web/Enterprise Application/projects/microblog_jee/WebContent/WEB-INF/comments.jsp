<%@page import="beans.Article"%>
<%@page import="dao.AbstractDAOFactory"%>
<%@page import="dao.DAO"%>
<%@page import="beans.User"%>
<%@page import="beans.Commentaire"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" type="text/css" href="view.css" />
	<title>Commentaire</title>
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

<br>
<br>
<br>
<br>

<form action="" method="post">
		<div id="block">
    		<h1>Commentaire</h1>
    		<%String c= (String)request.getAttribute("content");%>
    		<textarea name="content" cols="50" rows="10" required><%if(c!=null) out.print(c); %></textarea>
		</div>
		<div>
    		<br>
		</div>
		<div id="block">
			<input type="submit" name="submit" value="Modifier">
		</div>
	</form>


</body>
</html>