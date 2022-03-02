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

import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/connexion")
public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletConnexion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/connexion.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		List<Integer> listeErreurs = new ArrayList<Integer>();

		String identifiant = request.getParameter("identifiant");
		String mdp = request.getParameter("mdp");

		if(listeErreurs.size()>0) {
			request.setAttribute("listeCodesErreur", listeErreurs);
			System.out.println(listeErreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/connexion.jsp");
			rd.forward(request, response);
		}else {

			try {
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				Utilisateur utilisateur = utilisateurManager.verifyUtilisateurInscription(identifiant, mdp);
				
				// session		
				HttpSession session = request.getSession();
				session.setAttribute("id", utilisateur.getNoUtilisateur());
				session.setAttribute("pseudo", utilisateur.getPseudo());
				session.setAttribute("email", utilisateur.getEmail());

				response.sendRedirect(request.getContextPath());
			
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				System.out.println(e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/connexion.jsp");
				rd.forward(request, response);
			}
		}

	}

}
