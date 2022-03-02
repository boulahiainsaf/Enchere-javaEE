<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="fr.eni.enchere.bo.Utilisateur"%>
<%@page import="java.util.List"%>
<%@ page import="fr.eni.enchere.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Modifier Utilisateur</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/inscription-connexion.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
<% String lienPageAccueil = request.getContextPath();%>
<%@ include file="entete.jspf" %>
</header>
	<main>
		<div class="main-wrapper">
			<div class="form-wrapper">
				<h1 class=" form-row justify-content-center mb-4" >Modifier compte</h1>
				<form action="<%=request.getContextPath()%>/profil1" method="post">
					<div class="form-row justify-content-center">
						<div class="col-sm-3">
							<label for="pseudo">Pseudo : </label> 
							<input type="text"  class="form-control mb-2" id="pseudo" value="${utilisateur.pseudo}"name="pseudo" >
						</div>
						<div class="col-sm-3">	
							<label for="nom">Nom : </label> 
							<input type="text" class="form-control mb-2" id="nom" name="nom" value="${utilisateur.nom}"name="nom"/>
						</div>
					</div>
					<div class="form-row justify-content-center">
						<div class="col-sm-3">
							<label for="prenom">Prénom : </label> 
							<input type="text" class="form-control mb-2" id="prenom" name="prenom" value="${utilisateur.prenom}"name="prenom"/>
						</div>
						<div class="col-sm-3">
							<label for="email">E-mail : </label> 
							<input type="email" class="form-control mb-2" id="email" name="email" value="${utilisateur.email}"name="email"/>
						</div>
					</div>
					<div class="form-row justify-content-center">
						<div class="col-sm-3">
							<label for="telephone">Téléphone : </label> 
							<input type="tel" class="form-control mb-2" id="telephone" name="telephone" value="${utilisateur.telephone}"name="telephone"/>
						</div>
						<div class="col-sm-3">
							<label for="rue">Rue : </label> 
							<input type="text" class="form-control mb-2" id="rue" name="rue" value="${utilisateur.rue}"name="rue"/>
						</div>
					</div>
					<div class="form-row justify-content-center">
						<div class="col-sm-3">
							<label for="cp">Code Postal : </label> 
							<input type="text" class="form-control mb-2" id="cp" name="cp" value="${utilisateur.codePostal}"name="cp"/>
						</div>
						<div class="col-sm-3">
							<label for="ville">Ville : </label> 
							<input type="text" class="form-control mb-2" id="ville" name="ville" value="${utilisateur.ville}"name="ville"/> 
						</div>
					</div>
					<div class="form-row justify-content-center">
						<div class="col-sm-3">
								<label for="mdp">Mot de passe : </label> 
								<input type="password" class="form-control" id="mdp" name="mdp" value="<%=request.getParameter("mdp")!=null?request.getParameter("mdp"):""%>" />
						</div>
						<div class="col-sm-3">
									<label for="mdp2">Confirmation : </label> 
									<input type="password" class="form-control" id="mdp2" name="mdp2" value="<%=request.getParameter("mdp2")!=null?request.getParameter("mdp2"):""%>" /> <br />
						</div>
					</div>	
					<label for="id" class="id"> ID</label> <input type="password" class="id" name="id" value="<%=request.getParameter("id")!=null?request.getParameter("id"):""%>" />
					<div class="form-row justify-content-center">
						<button type="submit"  class="btn btn-black" value="Modifier">Modifier</button>
					</div>
				</form>
					
				</div>
				</div>
				</main>

</body>
</html>