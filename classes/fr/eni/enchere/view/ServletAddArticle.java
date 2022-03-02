package fr.eni.enchere.view;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import fr.eni.enchere.bll.RetraitManager;
import fr.eni.enchere.bll.UtilisateurManager;
import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

/**
 * Servlet implementation class ServletAddArticle
 */
@WebServlet("/ServletAddArticle")
public class ServletAddArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAddArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		int id = (int) (session.getAttribute("id"));
		UtilisateurManager uti=new UtilisateurManager();
		Utilisateur util=null;
		try {
			 util = uti.selectUtilisateurById(id);
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("utilisateur", util);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/ajoutArticle.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVendu art=new ArticleVendu();
		Categorie categorie = null;
		switch (request.getParameter("Categorie")) {
		case "1": categorie = new Categorie(1,"mode"); break;
		case "2": categorie = new Categorie(2,"Informatique"); break;
		case "3": categorie = new Categorie(3,"meuble"); break;
		case "4": categorie = new Categorie(4,"Accessoires"); break;
		case "5": categorie = new Categorie(5,"Electroménager"); break;
		default: categorie = new Categorie(6,"Jeux&Jouets"); break;
		}
		LocalDate debutEnchere =null;
		LocalDate datefin=null;

	Retrait rt= null;
	List<Integer> listeErreurs = new ArrayList<Integer>();
	
			String nom = 				request.getParameter("Nom");
			String description = 		request.getParameter("Description");
			
			
			int miseAPrix = 		Integer.parseInt(request.getParameter("MiseAPrix"));
			String rue = 		request.getParameter("RueRetrait");
			String cp = 		request.getParameter("CodePostalRetrait");
			String ville = 				request.getParameter("VilleRetrait");
			Retrait retrait = new Retrait();
	
	HttpSession session=request.getSession();
	int id = (int) (session.getAttribute("id"));
	UtilisateurManager uti=new UtilisateurManager();
	Utilisateur util=null;
	try {
		 util = uti.selectUtilisateurById(id);
	} catch (BusinessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	request.setAttribute("utilisateur", util);
	
	
		 
		
		 
		
		if(listeErreurs.size()>0) {
			request.setAttribute("listeCodesErreur", listeErreurs);
			System.out.println(listeErreurs);
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/ajoutArticle.jsp");
			rd.forward(request, response);
		}
		else {
		try {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			debutEnchere = LocalDate.parse(request.getParameter("DateDebut"),dtf);
			
			datefin =LocalDate.parse(request.getParameter("DateFin"),dtf);
			
			util = uti.selectUtilisateurById(id);
			ArticleVenduManager articleManager = new ArticleVenduManager();
			articleManager.addArticle(nom, description, debutEnchere, datefin, miseAPrix, util, categorie,rue,cp,ville);
			
			System.out.println("Servlet-"+art);
			response.sendRedirect(request.getContextPath());
		}catch(BusinessException e) {
			e.printStackTrace();
			request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			System.out.println(e.getListeCodesErreur());
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/ajoutArticle.jsp");
			rd.forward(request, response);
		}
		
 	}
		
	}
}
