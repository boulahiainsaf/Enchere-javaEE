<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.Locale"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="fr.eni.enchere.bll.ArticleVenduManager"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.enchere.bll.CategorieManager"%>
<%@page import="fr.eni.enchere.bo.Categorie"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>Accueil</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/affiche.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/4-col-protfolio.css">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn" crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js" crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css"
	integrity="sha384-zCbKRCUGaJDkqS1kPbPd7TveP5iyJE0EjAuZQTgFLD2ylzuqKfdKlfG/eSrtxUkn"
	crossorigin="anonymous">
<script src="https://kit.fontawesome.com/ca09bd6d26.js"
	crossorigin="anonymous"></script>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
			<div class="container">
				<!--  <p class="navbar-brand">ENI-Enchères</p> -->
				 
			<a href="<%=request.getContextPath() %>">	<img src="<%=request.getContextPath() %>/images/logo.JPG" alt="logo encheres" width="150"> </a>
				
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbarResponsive" aria-controls="navbarResponsive"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbarResponsive">
					<!-- <ul
						class="navbar-nav ml-auto border border-primary border-top-0 border-right-0 border-left-0 nav-border"> -->
						<ul	class="navbar-nav ml-auto"> 
						<c:choose>
							<c:when
								test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
								<li class="nav-item mb-0"><a class="nav-link pr-2 disabled" href="<%=request.getContextPath()%>/articles" >Enchères</a>
								</li>
								<li class="nav-item"><a class="nav-link pl-2"
									href="<%=request.getContextPath()%>/ServletAddArticle">Vendre un article</a></li>
								<li class="nav-item"><a class="nav-link pl-2" href="<%=request.getContextPath()%>/profil1">Compte de ${sessionScope.pseudo}</a></li>
								<li class="nav-item"><a class="nav-link pl-2"
									href="<%=request.getContextPath()%>/logout">Déconnexion</a></li>
							</c:when>
							<c:otherwise>
								<li class="nav-item mb-0"><a class="nav-link pr-0"
									href="<%=request.getContextPath()%>/inscription">S'inscrire
										-</a></li>
								<li class="nav-item"><a class="nav-link pl-2"
									href="<%=request.getContextPath()%>/connexion">Se connecter</a>
								</li>

							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</header>
<main>
		<h2 class="titre">Liste des enchères</h2>
		<p id="filtres">Filtres :</p>
<form action="<%=request.getContextPath()%>/accueil" method="post">
	<div id="content">
		<div classe = "box col-lg-12">
			<div
				class="input-group md-form form-sm form-1 col-lg-12 col-sm-5 recherche ">
				<div class="input-group-prepend ">
					<span class="input-group-text" id="basic-text1"><i
						class="fas fa-search text-black" aria-hidden="true"></i></span>
				</div>
				<input class="form-control  " type="text" name="nomArt"
					placeholder="Le nom de l'article contient" size="40" aria-label="Search" >
			</div>
			<div class="t form-group form-inline choix formselect col-lg-12">
				<label for="exampleFormControlSelect1">Catégorie :</label>
			  <select name="categories" class="form-control col-lg-6 col-sm-3 ml-4 "
					id="exampleFormControlSelect1">
					<option value="0">Toutes</option>
					<c:forEach var="categorie" items="${listeCategories}">

						<%
						System.out.println("jsp accueil parameter categorie - "+request.getParameter("categorie"));
						%>

						<option value="${categorie.getNoCategorie()}"
							${categorie.getNoCategorie()==param.categorie?'selected':''}>${categorie.getLibelle()} </option>
					</c:forEach>
				</select> 
				
				</div>
				<c:if
					test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
					<div class="row" id="connected-filters-section">
						<div id="filters-section-achats">
							<div class="form-check">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="optradio" checked="checked">Achats
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="">enchères ouvertes
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="">mes enchères en cours
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="">mes enchères
									remportées
								</label>
							</div>
						</div>
						<div id="filters-section-ventes">
							<div class="form-check">
								<label class="form-check-label"> <input type="radio"
									class="form-check-input" name="optradio">Mes ventes
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="" disabled >mes ventes
									en cours
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="" disabled>ventes non
									débutées
								</label>
							</div>
							<div class="form-check checkbox-padding">
								<label class="form-check-label"> <input type="checkbox"
									class="form-check-input" value="" disabled>ventes
									terminées
								</label>
							</div>
						</div>
					</div>
				</c:if>
			</div>
			<div class="box">
				<button type="submit" class="btn btn-black btn-big">Rechercher</button>
			</div>
		</div>
		</form>

     <div class="row list-grid">
		<%if(request.getParameter("nomArt")==null || request.getParameter("categories")=="0"){ %>
  				 <% List<ArticleVendu> listeArticles = (List<ArticleVendu>)request.getAttribute("listeArticles"); %>
	       		 <% if(listeArticles.size() != 0) { %>
		        		<% for(ArticleVendu article : listeArticles) { %>     
      					
     					<article class="list-card" id="list-card-<%=article.getNoArticle()%>">
      				 	<div class="card-content-wrapper"> 
      					
      			<div class="list-image-wrapper">
      					<img class="list-card-img" src="<%=request.getContextPath() %>/images/<%=article.getNoArticle() %>.jpg" alt="image enchere en cours"/>
      	 	</div> 
      					
      					
      					<div class="list-info-wrapper">
      					<div class="card-title">
      					<c:if test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
      					
		                    					<a href="<%=request.getContextPath()%>/detail?id=<%=article.getNoArticle() %>"><%=article.getNomArticle()%></a>
		               							</c:if>
		               	 						<c:if test="${empty sessionScope.id || empty sessionScope.pseudo || empty sessionScope.email}">
		                    						<p ><%=article.getNomArticle()%></p>
		               							</c:if></div>
		               				
		                    					<% if(article.getPrixVent()!= 0) { %> 
		                   	 				<div class="prix"><i class="fas fa-tag"></i>  Prix  : <%=article.getPrixVent()%></div> 
		                   	 					<%} else {%>
		                   	 					<div class="prix"><i class="fas fa-tag"></i> Prix : <%=article.getMiseAPrix()%></div>
		                   	 					<%} %>
		                   					<div class="card-date-enchere">
		                        			<p><i class="far fa-clock"></i> Fin de l'enchère : <%=article.getDateFinEncheres().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE))%></p>
		                   					 </div>
		                    				<div class="vendeur">
		                    				<c:choose>
											<c:when
											test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
		                       				<p><i class="fas fa-id-badge"></i> Vendeur :  <a href=""> <%=article.getVendeur().getPseudo()%></a> </p>
		                       				 </c:when>
		                       				 <c:otherwise>
		                       				 <p><i class="fas fa-id-badge"></i> Vendeur : <%=article.getVendeur().getPseudo()%></p>
		                       				 </c:otherwise>
		                       				 </c:choose>
		                        			<input class="label" name="id" value="<%=article.getNoArticle() %>" hidden/>
		                    				</div>						
      					</div>
      				</div> 
      					</article>
	            <% } %>
	           
			<% }else{ %>
			<p></p>
			 <%} %>
			
    <% }else{ %>
			<p></p>
			 <%} %>
			
  </div>    

    <div class="row list-grid">
    <%if(request.getAttribute("nomArt")!=null || request.getParameter("categories")=="0"  ){ %>
    <% List<ArticleVendu> listeArt2 = (List<ArticleVendu>)request.getAttribute("ListeArticleEnCours"); %>
	        <% if(listeArt2.size() != 0) { %>
		        <% for(ArticleVendu article : listeArt2) { %> 
  
      					<article class="list-card" id="list-card-<%=article.getNoArticle()%>">
      				 	<div class="card-content-wrapper"> 
      					
      			<div class="list-image-wrapper">
      					<img class="list-card-img" src="<%=request.getContextPath() %>/images/<%=article.getNoArticle() %>.jpg" alt="image enchere en cours"/>
      	 	</div> 
      					
      					
      					<div class="list-info-wrapper">
      					<div class="card-title">
      					<c:if test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
      					
		                    					<a href="<%=request.getContextPath()%>/detail?id=<%=article.getNoArticle() %>"><%=article.getNomArticle()%></a>
		               							</c:if>
		               	 						<c:if test="${empty sessionScope.id || empty sessionScope.pseudo || empty sessionScope.email}">
		                    						<p ><%=article.getNomArticle()%></p>
		               							</c:if></div>
		               				
		                    					<% if(article.getPrixVent()!= 0) { %> 
		                   	 				<div class="prix"><i class="fas fa-tag"></i>  Prix  : <%=article.getPrixVent()%></div> 
		                   	 					<%} else {%>
		                   	 					<div class="prix"><i class="fas fa-tag"></i> Prix : <%=article.getMiseAPrix()%></div>
		                   	 					<%} %>
		                   					<div class="card-date-enchere">
		                        			<p><i class="far fa-clock"></i> Fin de l'enchère : <%=article.getDateFinEncheres().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE))%></p>
		                   					 </div>
		                    				<div class="vendeur">
		                    				<c:choose>
											<c:when
											test="${!empty sessionScope.id && !empty sessionScope.pseudo && !empty sessionScope.email}">
		                       				<p><i class="fas fa-id-badge"></i> Vendeur :  <a href=""> <%=article.getVendeur().getPseudo()%></a> </p>
		                       				 </c:when>
		                       				 <c:otherwise>
		                       				 <p><i class="fas fa-id-badge"></i> Vendeur : <%=article.getVendeur().getPseudo()%></p>
		                       				 </c:otherwise>
		                       				 </c:choose>
		                        			<input class="label" name="id" value="<%=article.getNoArticle() %>" hidden/>
		                    				</div>						
      					</div>
      				</div> 
      					</article>
      
	            <% } %>
	           
			<% } else { %>
             <h6>Aucun article trouvé</h6>
            <% } %>
       
    <% } %>
    </div>       

    </main>

</body>
</html>