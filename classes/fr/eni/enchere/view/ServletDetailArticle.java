package fr.eni.enchere.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.enchere.bll.ArticleVenduManager;
import fr.eni.enchere.bll.EnchereManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

/**
 * Servlet implementation class ServletDetailArticle
 */
@WebServlet("/detail")
public class ServletDetailArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Utilisateur utilisateur = null;
		UtilisateurManager um = UtilisateurManager.getUtilisateurManager();
		ArticleVendu articleDetail=null;
		ArticleVenduManager avm = ArticleVenduManager.getArticleVenduManager();
		try {
			HttpSession session = request.getSession();
			int idUtilisateur = (int) session.getAttribute("id");
			articleDetail = avm.selectById(id);
			request.setAttribute("article", articleDetail);
			utilisateur = um.selectUtilisateurById(idUtilisateur);
			request.setAttribute("utilisateur", utilisateur);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/detailArticle.jsp");
			rd.forward(request, response);
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			System.out.println(e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/detailArticle.jsp");
			rd.forward(request, response);
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			response.sendRedirect(request.getContextPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		ArticleVendu articleDetail=null;
		ArticleVenduManager avm = ArticleVenduManager.getArticleVenduManager();
		Utilisateur utilisateur = null;
		UtilisateurManager um = UtilisateurManager.getUtilisateurManager();

		List<Integer> listeErreurs = new ArrayList<Integer>();
		int idUtilisateur = 0;
		int idArticle = 0;
		int credit = 0;
		HttpSession session = request.getSession();

		try {
			idUtilisateur = (int) session.getAttribute("id");
			idArticle = Integer.parseInt(request.getParameter("idArticle"));
			credit = Integer.parseInt(request.getParameter("montant"));
		}catch(NullPointerException e) {
			listeErreurs.add(CodesResultatServlets.VALEUR_ARTICLE_UTILISATEUR_ERROR);
		}

		if(listeErreurs.size()>0) {
			request.setAttribute("listeCodesErreur", listeErreurs);
			System.out.println("servlet-listeerreurs - "+listeErreurs);
			if(listeErreurs.contains(30011)) {
				response.sendRedirect(request.getContextPath());
			}else {
				response.sendRedirect(request.getContextPath()+"/detail?id="+idArticle);
			}
		}
		else {
			try {
				EnchereManager enchereManager = new EnchereManager();
				Enchere enchere = enchereManager.createEnchere(idUtilisateur, idArticle, credit);
				session.setAttribute("successMessage", "Votre crédit d'enchère a été accepté");
				response.sendRedirect(request.getContextPath()+"/detail?id="+idArticle);

			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			try {
					articleDetail = avm.selectById(idArticle);
					request.setAttribute("article", articleDetail);
					utilisateur = um.selectUtilisateurById(idUtilisateur);
					request.setAttribute("utilisateur", utilisateur);
				} catch (BusinessException e1) {
					e1.printStackTrace();
				}

				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/detailArticle.jsp");
				rd.forward(request, response);
			}
		}	
	}
}
