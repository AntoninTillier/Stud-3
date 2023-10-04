<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="history.css" />
<title>Historique</title>
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

<%ArrayList<String> listH= (ArrayList<String> )request.getAttribute("listH");
	if(listH.isEmpty()){
	    out.println("<div id=\"block\"><h1>Aucun historique</h1></div>");
	} else {
		for(int i =0;i <listH.size();i++){%>
	 		<div id="block">
	 			<% out.println("<h1>"+listH.get(i)+"</h1>"); 
	 				out .println("<br><br>");%>
	 		</div>
		<%}
	}%>
</body>
</html>