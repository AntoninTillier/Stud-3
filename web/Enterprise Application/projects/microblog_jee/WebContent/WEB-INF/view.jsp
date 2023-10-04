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
	<title>Article</title>
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

<%	ArrayList<Article> listArticle= new ArrayList<Article>();
	listArticle = (ArrayList<Article>) session.getAttribute("homeForm");
	User user = (User) session.getAttribute("utilisateur");
	Article a = null;
	for(int i= 0; i< listArticle.size(); i++){
	    if(listArticle.get(i).getId() == Integer.valueOf(request.getParameter("i")))
	    a = listArticle.get(i);
	}
	User userOther = null;
	if(a!= null){
	    AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	    DAO<User> userDAO = adf.getUser();
	    userOther = userDAO.findId(a.getIdUser());
	}
	ArrayList<Commentaire> listC = new ArrayList<Commentaire>();
	if(a!= null){
	    AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	    DAO<Commentaire> commentaireDAO = adf.getCommentaire();
	    ArrayList<Commentaire> listCommentaire = commentaireDAO.getAll();
	    if(!listCommentaire.isEmpty()){
	    	for(int i = 0; i < listCommentaire.size(); i++){
		    	if(listCommentaire.get(i).getPostId() == Integer.valueOf(request.getParameter("i"))){
					listC.add(listCommentaire.get(i));
		    	}
			}
	    }
	}%>
	<div id="post">
		<h3> <% if(a!=null) out.println(a.getTitre());%></h3>
		<p>Ecris par <% if(a!=null) out.println(userOther.getNom()); %></p>
		<p><%if(a != null) out.println(a.getValue()); %></p>
		<br>
	</div>
	<div id="block">
		<%
			if(a!= null) {
			    if(!a.getImage().equals(""))
			    	out.println("<br><img src='image?src="+a.getImage()+"'>");
			}
		%>
	</div>
	
	<%	
	if(!listC.isEmpty()) {
		for(int i = 0; i < listC.size(); i++){
	    	out.println("<hr size=\"\">");
	    	out.println("<p>&nbsp;&nbsp;&nbsp;"+listC.get(i).getValue()+"</p>");
	    	AbstractDAOFactory adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
	    	DAO<User> userDAO = adf.getUser();
	    	User u = userDAO.findId(listC.get(i).getUserId());
	    	out.println("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;commentaire posté par "+u.getNom()+" le "+listC.get(i).getDate()+"</p>");
	    	if(u.getId() == user.getId()){
	    	    out.println("<br>");
				out.println("&nbsp;&nbsp;<a id=\"modif\" href=\"/ProjetJEE/comments?i="+listC.get(i).getId()+"\">Modifier</a>");
				out.println("&nbsp;&nbsp;<a id=\"supp\" href=\"/ProjetJEE/comments?j="+listC.get(i).getId()+"\">Supprimer</a>");
	    	}
	    	out.println("<br><br>");
		}
	}
	%>
	<hr id="endComment"size="0">
	
	<form action="" method="post">
		<div id="block">
    		<h1>Commentaire</h1>
    		<textarea name="content" cols="50" rows="10" required></textarea>
		</div>
		<div>
    		<br>
		</div>
		<div id="block">
			<input type="submit" name="submit" value="Soumettre">
		</div>
	</form>

</body>
</html>