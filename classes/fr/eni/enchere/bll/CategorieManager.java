package fr.eni.enchere.bll;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.exceptions.BusinessException;

public class CategorieManager {
	private CategorieDAO categorieDAO;
		private static CategorieManager categorieManager;

	public static CategorieManager getCategorieManager() {
		if (categorieManager == null) {
			categorieManager = new CategorieManager();
		}
		return categorieManager;
	}

	public CategorieManager() {
		categorieDAO = DAOFactory.getCategorieDAO();	
	}
	
	public List<Categorie> selectAllCategories() throws BusinessException{
		return this.categorieDAO.selectAllCategorie();
	}
}
