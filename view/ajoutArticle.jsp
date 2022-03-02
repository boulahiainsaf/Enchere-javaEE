
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
    
<%@page import="fr.eni.enchere.bo.Utilisateur"%>
<%@ page import="fr.eni.enchere.exceptions.LecteurMessage"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Ajout Articler</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/ajout.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
<% String lienPageAccueil = request.getContextPath();%>
<%@ include file="entete.jspf" %>
</header>
<main>
    <div class="head">
      <h1 class="titre">Nouvelle vente :</h1>
    </div>

	<section class="titre">
	<div class="tout col-lg-8 col-sm-12">
		
		
		
		<img class="imgArticle" alt="" src="/imageBase.png">

		<form  method="post" action="<%=request.getContextPath()%>/ServletAddArticle" >
		<div class="col-lg-6 col-sd-12">
		    <div class="partie">
			    <label class="label ">Article : </label>
			    <div class="part2">
       		    <input class="input " type="text" name="Nom" value="<%=request.getParameter("Nom")!=null?request.getParameter("Nom"):""%>"placeholder="Le nom de l'article à vendre."/>
       		    </div>
		    </div> 
		    
		    <div class="partie">
			    <label class="label">Description : </label>
			    <div class="part2">
       		    <input class="input " type="text" name="Description" value="<%=request.getParameter("Description")!=null?request.getParameter("Description"):""%>"placeholder="La description de l'article à vendre." />
		    	</div>
		    </div>
		     
            <div class="partie">
		        <label class="label">Début de l'enchère : </label>
       		    <input class="input" type="date" name="DateDebut" value="<%=request.getParameter("DateDebut")!="2018-01-01"?request.getParameter("DateDebut"):"2018-01-01"%>"/>
		    </div>
		   <div class="partie">
		        <label class="label">Fin de l'enchère : </label>
       		    <input class="input" type="date" name="DateFin" value="<%=request.getParameter("DateFin")!="2018-01-01"?request.getParameter("DateFin"):"2018-01-01"%>" />
		    </div>		

		    <div class="partie">
		        <label class="label">Mise à prix : </label>
       		    <input class="input" type="number" name="MiseAPrix" min=10 value="<%=request.getParameter("MiseAPrix")!="10"?request.getParameter("MiseAPrix"):"10"%>" placeholder="Le prix initial de l'article à vendre." step="10"/>
		    </div>
		        
			<div class="partie">
				<label class="labCategorie">Catégorie : </label> 
				<select name="Categorie" class="Categorie form-select">
					<option value="1">Mode</option>
					<option value="2">Informatique</option>
					<option value="3">Ameublement</option>
					<option value="4">Accessoires</option>
					<option value="5">Electroménager</option>
					<option value="6">Jeux&JouetsS</option>
				</select>
			</div>	
			</div>
			<div class="partie">	    
		    <fieldset class="border">
		    
		        <legend>Retrait</legend>
		        <div class="par3">
			        <label class="label">Rue : </label>
       		        <input class="input" type="text" name="RueRetrait" value="${utilisateur.getRue()}" required />
		        </div> 
		        
		        <div class="par3">
			        <label class="label">Code postal : </label>
       		        <input class="input" type="text" name="CodePostalRetrait" value="${utilisateur.getCodePostal()}"required/>
       		    </div>
       		    <div class="par3">
       	        	<label class="label">Ville : </label>
       		        <input class="input" type="text" name="VilleRetrait" value="${utilisateur.getVille()}"/>
		        </div>
		        <p></p>
		        
		    </fieldset>
		    <hr>
		    
		    </div>
				<div class="divBtRechercher">
					<input type="submit" class="btn btn-black" value="Enregistrer" /> 
					<button type="button" class="btn btn-light" >Annuler</button>
				</div>
				
		</form>
		        
	 </div>
	</section>
      <div class="error-msg">
    <%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
		%>
				<p style="color:red;">Erreur, l'article n'a pas pu être ajouté :</p>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<p><%=LecteurMessage.getMessageErreur(codeErreur)%></p>
		<%	
				}
			}
		%>
	</div>
    </section>
   </main>
</body>
</html>