<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Inscription</title>
<link type="text/css" rel="stylesheet" href="inscription.css" />
</head>
<body bgcolor="#EEEEEE">
	<br/> 
	<br/> 
	<form method="post" action="">
		<div>
			<label>Vous pouvez vous inscrire avec ce formulaire</label> 
			<br /> 
			<h1>Adresse email</h1>
			<input type="email" id="email" name="email" value="" size="20" maxlength="60" /> 
			<br/> 
			<h1>Mot de passe</h1> 
			<input type="password" id="motdepasse"name="motdepasse" value="" size="20" maxlength="20" /> 
		 	<br/> 
		 	<h1>Confirmation du mot de passe</h1>
		 	<input type="password" id="confirmation" name="confirmation" value="" size="20" maxlength="20" /> 
		 	 <br/> 
		  	<h1>Nom d'utilisateur</h1>
		  	<input type="text" id="nom" name="nom" value="" size="20" maxlength="20" required />
		    	<br/>
		    	<br/> 
		   	<input type="submit" value="Inscription" class="sansLabel" /> <br />
		</div>
	</form>
</body>
</html>