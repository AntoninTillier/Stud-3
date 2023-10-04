<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="post.css" />
<title>post</title>
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

<form  method="post" action="" enctype="multipart/form-data">
	<div id="block">
    	<h1>Titre</h1>
    	<%String s= (String)request.getAttribute("title");%>
    	<input type="text" name="title" value=<%if(s!=null) out.print("'"+s+"'"); else out.print("''"); %> placeholder="" required>
	</div>
	<div id="block">
    	<br>
	</div>
	<div id="block">
    	<h1>Contenu</h1>
    	<%String c= (String)request.getAttribute("content");%>
    	<textarea name="content" value="" rows=10 cols=40 placeholder="" required><%if(c!=null) out.print(c); %></textarea>
	</div>
	<div id="block">
    	<br>
	</div>
	<div id="block">
    	<h1>Photo</h1>
    	<input class="file" type="file" name="photo" value="" accept=".jpg, .jpeg, .png, .bmp, .gif">
	</div>
	<div id="block">
    	<br>
	</div>
	<div id="block">
		<%String v = (String) request.getAttribute("submit"); %>
		<input type="submit" name="submit" value=<%if(v!=null) out.print("'"+v+"'"); else out.print("'Créer'");%>>
	</div>
</form>

</body>
</html>