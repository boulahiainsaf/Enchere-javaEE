
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
 * Servlet implementation class ServletProfil
 */
@WebServlet("/profil1")
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UtilisateurManager manager = new UtilisateurManager();
		int id = (int) request.getSession().getAttribute("id");
		Utilisateur utilisateur;
		try {
			utilisateur = manager.selectUtilisateurById(id);
			request.setAttribute("utilisateur", utilisateur);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/profil1.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String pseudo = null;
		String nom = null;
		String prenom = null;
		String email = null;
		String telephone = null;
		String rue = null;
		String cp = null;
		String ville = null;
		String mdp = null;
		String mdp2 = null;
		HttpSession session = request.getSession();
		int id = (int) session.getAttribute("id");
		

		List<Integer> listeErreurs = new ArrayList<Integer>();
		
		pseudo = request.getParameter("pseudo");
		nom = request.getParameter("nom");
		prenom = request.getParameter("prenom");
		email = request.getParameter("email");
		telephone = request.getParameter("telephone");
		rue = request.getParameter("rue");
		cp = request.getParameter("cp");
		ville = request.getParameter("ville");
		mdp = request.getParameter("mdp");
		

		if(listeErreurs.size()>0) {
			request.setAttribute("listeCodesErreur", listeErreurs);
			System.out.println(listeErreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/profil1.jsp");
			rd.forward(request, response);
		}
		else {
			try {
				UtilisateurManager utilisateurManager = new UtilisateurManager();
				Utilisateur utilisateur = utilisateurManager.updateUtilisateur(pseudo, nom, prenom, email, telephone, rue, cp, ville, mdp, mdp2, id);
				System.out.println("Servlet-"+utilisateur);
				
				session.setAttribute("id", utilisateur.getNoUtilisateur());
				session.setAttribute("pseudo", utilisateur.getPseudo());
				session.setAttribute("email", utilisateur.getEmail());
				
				response.sendRedirect(request.getContextPath());
			}catch(BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				System.out.println(e.getListeCodesErreur());
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/profil1.jsp");
				rd.forward(request, response);
			}
		}
	}
}
