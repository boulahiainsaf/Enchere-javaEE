package fr.eni.enchere.view;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.CategorieManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAccueil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		try {
			CategorieManager categorieManager = new CategorieManager();
			List<Categorie> listeCategories =  categorieManager.selectAllCategories();
			request.setAttribute("listeCategories", listeCategories);
			ArticleVenduManager avm = ArticleVenduManager.getArticleVenduManager();
			List<ArticleVendu> lstArtEnCours = avm.selectAllArticle();
			for(ArticleVendu art : lstArtEnCours) {
				System.out.println(art.getNomArticle());
			}
			request.setAttribute("listeArticles", lstArtEnCours);
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		


		

		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArticleVenduManager artManager = new ArticleVenduManager();
		CategorieManager categorieManager = new CategorieManager();
		List<Integer> listeErreurs = new ArrayList<Integer>();
		List<ArticleVendu>listarticl = new ArrayList<ArticleVendu>();
		List<ArticleVendu>list = new ArrayList<ArticleVendu>();
		String nomArt1 = request.getParameter("nomArt");
		System.out.println("param"+nomArt1);
		int idCat = Integer.parseInt(request.getParameter("categories"));
		String test =request.getParameter("categories");
		System.out.println("param"+idCat);
		try {
		
		List<Categorie> listeCategories =  categorieManager.selectAllCategories();
		request.setAttribute("listeCategories", listeCategories);
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
		}
		
		try {
			listarticl = artManager.selectByNomCour(nomArt1, LocalDate.now());
			request.setAttribute("ListeArticleEnCours", listarticl);
			for(ArticleVendu art : listarticl) {
				System.out.println(art.getNomArticle());
			}
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
			
	//select  by categorie
			
			try {
				list = artManager.selectByCatCour(idCat, LocalDate.now());
				request.setAttribute("ListeArticleEnCourscat", list);
				for(ArticleVendu art : list) {
					System.out.println(art.getMiseAPrix());
				}
			} catch (BusinessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
		request.setAttribute("nomArt",nomArt1);
		request.setAttribute("categories",idCat);
		request.setAttribute("test",test);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/accueil.jsp");
		rd.forward(request, response);
	}

}
