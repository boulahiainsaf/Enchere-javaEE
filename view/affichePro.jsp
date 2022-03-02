<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="fr.eni.enchere.bll.ArticleVenduManager"%>
<%@page import="fr.eni.enchere.bo.ArticleVendu"%>
<%@page import="java.util.List"%>
<%@page import="fr.eni.enchere.bll.CategorieManager"%>
<%@page import="fr.eni.enchere.bo.Categorie"%>
<%@page import="java.util.Locale"%>
  

    <div class="head">
        <h1>Enchère En Cours</h1>
    </div>
   

<%-- 	<% List<ArticleVendu> listeArticles = (List<ArticleVendu>)request.getAttribute("listeArticles"); %>
	        <% if(listeArticles.size() != 0) { %>
		        <% for(ArticleVendu article : listeArticles) { %> 
	 <div class="container-fluid">
        <div class="card-grid">
		   
		      
		              <div class="card col-lg-4 col-sm-12">
		                <div class="card-header">
		                    <h1 name="nom">
		                     
         						 <%if (request.getSession(false)!=null){%>
		                    		<a href="<%=request.getContextPath()%>/detail?id=<%=article.getNoArticle() %>"><%=article.getNomArticle()%></a>
		               			 <% } else { %>
		               		
	         					 
	         					 <%=article.getNomArticle()%>
	         			 		<%} %>
	         			 		 
          				  
		               </h1>
		                </div>
		                <div class="">
		                    <img src="<%=request.getContextPath() %>/images/<%=article.getNoArticle() %>.jpg" alt="" widthe="120">
		                </div>
		                <div class="card-body">
		                    <div class="prix"><i class="fas fa-tag"></i><%=article.getMiseAPrix()%></div>
		                    <% if(article.getPrixVent()!= 0) { %> 
		                   	 <div class="prix"><i class="fas fa-tag"></i><%=article.getPrixVent()%></div> 
		                   	 <%} %>
		                    <div class="card-date-enchere">
		                        <p><i class="far fa-clock"></i><%=article.getDateDebutEncheres().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE))%></p>
		                    </div>
		                    <div class="vendeur">
		                        <p><i class="fas fa-id-badge"></i> <%=article.getVendeur().getPseudo()%></p>
		                        <input class="label" name="id" value="<%=article.getNoArticle() %>" hidden/>
		                    </div>
		                </div>
		          </div>
		     </div>
		     
		     
		      
	            <% } %>
	           
			<% } else { %>
             <h1>Aucun article</h1>
            <% } %>
            
        </div> --%>
    </div>
    <div class="row">
    <% List<ArticleVendu> listeArticles = (List<ArticleVendu>)request.getAttribute("listeArticles"); %>
	        <% if(listeArticles.size() != 0) { %>
		        <% for(ArticleVendu article : listeArticles) { %> 
    
    <div class="col-lg-4 col-sm-12">
                <ul id="listeMedia" class="navbar ">
                    <li class=" tr col-lg-4 col-md-4 col-sm-6 col-xs-6 ">
                   
                        <div class="a" href="# ">
                        <div>
                         <%if (request.getSession(false)!=null){%>
		                    		<a href="<%=request.getContextPath()%>/detail?id=<%=article.getNoArticle() %>"><%=article.getNomArticle()%></a>
		               			 <% } else { %>
		               		
	         					 
	         					 <%=article.getNomArticle()%>
	         			 		<%} %>
	         			 </div>
                           <img src="<%=request.getContextPath() %>/images/<%=article.getNoArticle() %>.jpg" alt="" widthe="120">
                            <div class="prix"><i class="fas fa-tag"></i><%=article.getMiseAPrix()%></div>
		                    <% if(article.getPrixVent()!= 0) { %> 
		                   	 <div class="prix"><i class="fas fa-tag"></i><%=article.getPrixVent()%></div> 
		                   	 <%} %>
		                    <div class="card-date-enchere">
		                        <p><i class="far fa-clock"></i><%=article.getDateDebutEncheres().format(DateTimeFormatter.ofPattern("dd/MM/YYYY", Locale.FRANCE))%></p>
		                    </div>
		                    <div class="vendeur">
		                        <p><i class="fas fa-id-badge"></i> <%=article.getVendeur().getPseudo()%></p>
		                        <input class="label" name="id" value="<%=article.getNoArticle() %>" hidden/>
		                    </div>
                        </a>
                    </li>
                    
                </ul>
      </div>
      
	            <% } %>
	           
			<% } else { %>
             <h1>Aucun article</h1>
            <% } %>
    </div>
