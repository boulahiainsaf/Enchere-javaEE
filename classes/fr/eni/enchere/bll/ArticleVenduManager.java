package fr.eni.enchere.bll;

import java.nio.charset.CoderResult;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduDAO;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

public class ArticleVenduManager {
	private ArticleVenduDAO articleVenduDAO;
	private static ArticleVenduManager articleVenduManager ;
private static UtilisateurManager utilisateurManager;
	private UtilisateurDAO utilisateurDAO;
	private CategorieDAO categorieDAO;
	private RetraitDAO retraitDAO;
	
	
	public static ArticleVenduManager getArticleVenduManager() {
		if(articleVenduManager == null) {
			articleVenduManager = new ArticleVenduManager();
		}
		return articleVenduManager;
	}
	
	public ArticleVenduManager () {
		articleVenduDAO= DAOFactory.getArticlVenduDAO();
	}
	//ajouter un article 
	public void addArticle(String nomArticle, String description, LocalDate dateDebutEncheres,LocalDate dateFinEncheres, int miseAPrix, Utilisateur vendeur, Categorie Categorie,String rue ,String cp,String ville) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Retrait retrait = new Retrait();
		ArticleVendu art = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix, Categorie, vendeur,retrait);
		 retrait = new Retrait(rue, cp, ville, art);
		validerChamp(art.getNomArticle(), 30, CodesResultatBLL.FORMAT_ARTICLE_NOM_ERROR, businessException);
		validerChamp(art.getDescription(), 300, CodesResultatBLL.FORMAT_ARTICLE_DISCRIPTION_ERROR, businessException);
		verif(art, businessException);
		
		
		if (!businessException.hasErreurs()) {
			ArticleVenduDAO aDAO = DAOFactory.getArticlVenduDAO();
			RetraitDAO rdao = DAOFactory.getRetraitDAO();
			aDAO.insert(art);
			rdao.insert(retrait);
		}else
			throw businessException;
		
	}
	
	public  List<ArticleVendu> selectAllArticle() throws BusinessException{
		
		return this.articleVenduDAO.selectAll();
		
	}
	public  ArticleVendu selectById(int id) throws BusinessException{
		
		return this.articleVenduDAO.selectById(id);
		
	}
	
public  List<ArticleVendu> selectByNomCour(String nom,LocalDate date ) throws BusinessException{
		
		return this.articleVenduDAO.selectArticleEnCoursByNom(nom, date);
		
	}

//selectby categorie Article en cours
public  List<ArticleVendu> selectByCatCour(int id,LocalDate date ) throws BusinessException{
	
	return this.articleVenduDAO.selectArticleEnCoursByCategori(id, date);
	
}
//modifier un article 
	public void modifArticle( String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, Integer miseAPrix, Utilisateur vendeur, Categorie Categorie)
			throws BusinessException {

		BusinessException businessException = new BusinessException();
		
		ArticleVendu artModif = null;
		artModif = new ArticleVendu(nomArticle, description, dateDebutEncheres, dateFinEncheres, miseAPrix,
				vendeur, Categorie);
		
		verif(artModif, businessException);
		

		
		if (!businessException.hasErreurs()) {
			
			this.articleVenduDAO.insert(artModif);
			
			
		}else
			throw businessException;
	}
		
	
	// validation du format du champs de formulaire qui ne peut pas �tre null
		public static void validerChamp(String paramAValider, int maxLength, int erreurAAjouter, BusinessException businessException) {
			if(paramAValider==null || paramAValider.trim()=="" || paramAValider.trim().length()>maxLength) {
				businessException.ajouterErreur(erreurAAjouter);
			}
		}
		
		private static void verif(ArticleVendu art, BusinessException exception) {
			
			
			if(art.getMiseAPrix()==0 ||art.getMiseAPrix()<0)
				exception.ajouterErreur(CodesResultatBLL.PRIX_INITIALE_INVALID);
			if (art.getDateDebutEncheres().isBefore(LocalDate.now())||art.getDateDebutEncheres()==null)
				exception.ajouterErreur(CodesResultatBLL.DATE_DEBUT_ENCHERE_INVALIDE);
			if (art.getDateFinEncheres().isBefore(art.getDateDebutEncheres())
					|| art.getDateFinEncheres().isEqual(art.getDateDebutEncheres())||art.getDateFinEncheres()==null)
				exception.ajouterErreur(CodesResultatBLL.DATE_FIN_ENCHERE_INVALIDE);
			

		}
		
		
}
