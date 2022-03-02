package fr.eni.enchere.bll;

import java.sql.Timestamp;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.ArticleVenduDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.EnchereDAO;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;

public class EnchereManager {
	private EnchereDAO enchereDAO;
	private UtilisateurDAO utilisateurDAO;
	private ArticleVenduDAO articleVenduDAO;
	private static EnchereManager enchereManager;

	public static EnchereManager getEnchereManager() {
		if (enchereManager == null) {
			enchereManager = new EnchereManager();
		}
		return enchereManager;
	}

	public EnchereManager() {
		enchereDAO = DAOFactory.getEnchereDAO();
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
		articleVenduDAO = DAOFactory.getArticlVenduDAO();
	}
	public Enchere createEnchere(int idUtilisateur, int idArticle, int creditPropose) throws BusinessException {

		BusinessException businessException = new BusinessException();

		Enchere enchere = null;
		int prixEncheri;

		Utilisateur utilisateur = utilisateurDAO.selectById(idUtilisateur);
		ArticleVendu article = articleVenduDAO.selectById(idArticle);
		Utilisateur utilisateurRembourse=null;
		int montantRembourse = 0;

		// validation
		if(utilisateur==null||article==null) {
			businessException.ajouterErreur(CodesResultatBLL.FORMAT_UTILISATEUR_ARTICLE_NULL);
			throw businessException;
		}else {
			int creditUtilisateur = utilisateur.getCredit();
			int prixPlusHaut = article.getPrixVent();
			int prixDeBase = article.getMiseAPrix();

			if(prixPlusHaut==0 && creditUtilisateur>prixDeBase && creditPropose<=creditUtilisateur) {
				prixEncheri = creditPropose;	
				utilisateur.setCredit(creditUtilisateur-creditPropose);

			}else if(prixPlusHaut!=0 && creditUtilisateur>prixPlusHaut && creditPropose<=creditUtilisateur){
				prixEncheri = creditPropose;	 
				System.out.println("Manager-idArticle : "+idArticle);
				System.out.println("Manager-prixHaut : "+prixPlusHaut);
				utilisateurRembourse = utilisateurDAO.selectById(enchereDAO.getMaxEnchereByArticle(idArticle, prixPlusHaut).getAcheteur().getNoUtilisateur());
			System.out.println("Manager-utilisateur remboursé : "+utilisateurRembourse);
				montantRembourse = prixPlusHaut;
				if(utilisateur.getNoUtilisateur()==utilisateurRembourse.getNoUtilisateur()) {
					utilisateur.setCredit(utilisateur.getCredit()+montantRembourse-creditPropose);
				}
				else {
					utilisateurRembourse.setCredit(utilisateurRembourse.getCredit()+montantRembourse);
					utilisateur.setCredit(creditUtilisateur-creditPropose);
				}
			}else {
				businessException.ajouterErreur(CodesResultatBLL.REGLE_CREDIT_INSUFFISANT);
				throw businessException;
			}
		}
		if(!businessException.hasErreurs()) {
			enchere = new Enchere();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			enchere.setAcheteur(utilisateur);
			enchere.setArticle(article);
			enchere.setDateEnchere(now);
			enchere.setMontantEnchere(prixEncheri);
			article.setPrixVent(prixEncheri);
			enchereDAO.insert(enchere);
			articleVenduDAO.update(article);
			if(utilisateurRembourse!=null && utilisateurRembourse!=utilisateur) {
				utilisateurDAO.updateCredit(utilisateurRembourse);
			}
			utilisateurDAO.updateCredit(utilisateur);
		}
		else {
			throw businessException;  
		}
		return enchere;
	}
}
