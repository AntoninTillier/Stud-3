<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="connection.css" />
        <title>Connexion</title>
    </head>
    <body bgcolor="#EEEEEE">
    	<br>
    	<br>
        <form method="post" action="">
                <div>
                	<h1>Adresse email</h1>
                	<input type="email" id="email" name="email" value="<c:out value="${utilisateur.email}"/>" size="20" maxlength="60" />
                </div>
                
				<div>
					<h1>Mot de passe</h1>
                	<input type="password" id="motdepasse" name="motdepasse" value="" size="20" maxlength="20" />
                </div>
				<div>
					<br>
                	<input type="submit" value="Connexion"/>
                </div>
        </form>
        <br>
        <br>
		<div id=inscription>
  			<a href="/ProjetJEE/inscription">Créer un compte</a>
		</div>
    </body>
</html>