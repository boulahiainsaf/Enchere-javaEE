<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu"%>
<%@page import="fr.eni.enchere.bo.Utilisateur"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.List"%>
<%@ page import="fr.eni.enchere.exceptions.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<html lang="fr">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Detail Article</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/page.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js" crossorigin="anonymous"></script>
</head>
<body>
<header>
<% String lienPageAccueil = request.getContextPath();%>
<%@ include file="entete.jspf" %>
</header>
<main>
<h2 class="titre">Détails de la vente</h2>
<%
					ArticleVendu article = (ArticleVendu) request.getAttribute("article");
					Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
					
				%>
	
	
	

		<form  class = "container-fluid" id="form" method="post" action="<%=request.getContextPath()%>/detail">
		<div class="row mt-5">
		 <div class="col-lg-4 col-sm-12 image-wrapper" >
		        <img src="<%=request.getContextPath()%>/images/<%=article.getNoArticle() %>.jpg" alt="" class="article-image-detail">
		 </div>
		<div class="col-lg-8 col-sm-12">
		    <div>
			    <label class="label font-weight-bold">${article.getNomArticle()}</label>
		    </div> 
		    
		    <div>
			    <label class="label">Description : </label>
                <label >${article.getDescription()}</label>
		    </div>
		     
            <div>
			    <label class="label">Catégorie : </label>
                <label >${article.getCategorie().getLibelle()}</label>
		    </div>
		    
		   
		    
            <div>
			    <label class="label">Mise à prix : </label>
                <label><span class="font-weight-bold">${article.getPrixVent()!=0?article.getPrixVent():article.getMiseAPrix()}</span> points </label>
		    </div>
		    
            <div>
			    <label class="label">Fin de l'enchère : </label>
                <label >${article.getDateFinEncheres()}</label>
		    </div>
		    
            <div>
			    <label class="label">Retrait : </label>
			                     <label class="label">${article.getVendeur().getRue()}</label>
                    <label >${article.getVendeur().getCodePostal()} ${article.getVendeur().getVille()}</label>
			   		    </div>
		    
            <div>
			    <label class="label">Vendeur : </label>
                <a  href="ProfilServlet?id=${article.getVendeur().getPseudo()}">${article.getVendeur().getPseudo()}</a>
		    </div>
		    
		    		<c:if test="${!empty sessionScope.successMessage}">
				<div class="success-wrapper">
				<div class="success-msg row justify-content-center">
				<div class="col-sm-5 text-center pt-3 ">
					<p>${successMessage}</p>
					</div>
					</div>
					</div>
					<c:remove var="successMessage" scope="session" />
				</c:if>   
				
					<%
			List<Integer> listeCodesErreur = (List<Integer>)request.getAttribute("listeCodesErreur");
			if(listeCodesErreur!=null)
			{
				
		%>	<div class="error-wrapper">
		<div class="error-msg row justify-content-center">
		<div class="col-sm-5 text-center pt-3 ">
				<p>Une erreur est survenue :</p>
				<ul>
		<%
				for(int codeErreur:listeCodesErreur)
				{
		%>
					<li><%=LecteurMessage.getMessageErreur(codeErreur)%></li>
			<% } %>
			</ul>
				</div>
			</div>
			</div>
		<% } %> 
                <div>
           	        <label class="label">Ma proposition : </label>
                    <input  type="number" name="montant" min="${article.getPrixVent()!=0?article.getPrixVent()+1:article.getMiseAPrix()+1}" value="${article.getPrixVent()!=0?article.getPrixVent()+1:article.getMiseAPrix()+1}" required/>
                    <input  name="idArticle" value="${article.getNoArticle()}" hidden/>
                    <input  class="btn btn-black" type="submit" value="Enchérir" ${utilisateur.getCredit() < (article.getPrixVent()!=0?article.getPrixVent()+1:article.getMiseAPrix()+1)?'disabled="disabled"':""} />  <span id="credit">Crédit disponible : ${utilisateur.getCredit()}</span>    
                </div>
			<a href="${pageContext.request.contextPath}"> 
		 <button type="button" class="btn btn-black">Back</button></a>
		     </div> 
		  </div>     
		</form>
		
		 
				
		<%-- 		<c:if test="${!empty sessionScope.successMessage}">
				<div class="success-wrapper">
				<div class="success-msg row justify-content-center">
				<div class="col-sm-5 text-center pt-3 ">
					<p>${successMessage}</p>
					</div>
					</div>
					</div>
					<c:remove var="successMessage" scope="session" />
				</c:if>    --%>


	</main>
</body>
</html>