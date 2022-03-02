<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page import="fr.eni.enchere.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Connexion Utilisateur</title>
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
		<div class="main-wrapper ">
			<div class="form-wrapper d-flex justify-content-center">
				<form action="<%=request.getContextPath()%>/connexion"
					method="post" class = "form-horizontal form-connection">
					<div class="form-group d-flex justify-content-between">
					<label for="identifiant">Identifiant : </label> <input type="text" id="identifiant"
						name="identifiant" value="<%=request.getParameter("identifiant")!=null?request.getParameter("identifiant"):""%>" placeholder = " E-mail ou pseudo"/> 
					</div>
					<div class="form-group d-flex justify-content-between">
					<label for="mdp">Mot de passe : </label> <input type="password" id="mdp"
						name="mdp" value="<%=request.getParameter("mdp")!=null?request.getParameter("mdp"):""%>" /> 
					</div>	
					<div class="form-group row d-flex justify-content-between">
						<button type="submit" class="btn btn-black ">Connexion</button>
						<div class="pr-3">
						<input type="checkbox" id="souvenir" name="souvenir" value="souvenir">
						<label for="souvenir"> Se souvenir de moi </label><br>
						<a href="">Mot de passe oublié</a></div>
					</div>
				</form>		
			</div>

			 	<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
				
		%>
		<div class="error-wrapper">
		<div class="error-msg row justify-content-center">
		<div class="col-sm-4 text-center pt-3 ">
				<p>Erreur! La connexion n'est pas réussie :</p>
				<ul>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<li><%=LecteurMessage.getMessageErreur(codeErreur)%></li> <!-- TODO comment utiliser expression language pour accèder la classe LecteurMessage ?  -->
			<% } %>
			</ul>
			</div>
			</div>
			</div>
		<% } %> 
	<div class="d-flex justify-content-center">
		<a href="<%=request.getContextPath()%>/inscription"><button type="button" class="btn btn-light btn-big">Créer un compte</button></a>
		</div>
		</div>
	</main>
</body>
</html>
			