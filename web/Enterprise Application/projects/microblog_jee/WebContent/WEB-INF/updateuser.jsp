<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="updateuser.css" />
<title>Modifier le compte</title>
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
	<br/>
	<br/>
	<div id="block">
		<h1 id="titreInfo">Voici vos informations personnelles : </h1>
		<label>Nom : <c:out value="${utilisateur.nom}"/></label>
		<br/>
		<br/>
		<label>Email : <c:out value="${utilisateur.email}"/></label>
		<br/>
		<br/>
		<label>Date d'inscription : <c:out value="${utilisateur.dateInscription}"/></label>
	</div>
	<br/>
	<div id="block">
		<form  method="post" action="">
			<h1>Nouveau nom d'utilisateur : </h1>
			<input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
			<br>
			<input type="submit" name="modifNom" value="Modifier">
		</form>
	</div>
	<br/>
	<div id="block">
		<form  method="post" action="">
			<h1>Nouvel email : </h1>
			<input type="email" id="email" name="email" value="" size="20" maxlength="60" />
			<br>
			<input type="submit" name="modifEmail" value="Modifier">
		</form>
	</div>
	<br/>
	<div id="block">
		<form  method="post" action="">
			<h1>Nouveau mot de passe : </h1>
			<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
			<br>
			<input type="submit" name="modifMDP" value="Modifier">
		</form>
	</div>
	<br/>
	<div id="block">
		<form  method="post" action="">
			<h1>Supprimer mon compte : </h1>
			<input class="supCompte" type="submit" name="supCompte" value="Supprimer le compte">
		</form>
	</div>
</body>
</html>