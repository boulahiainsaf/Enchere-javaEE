package fr.eni.enchere.bll;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.RetraitDAO;
import fr.eni.enchere.exceptions.BusinessException;

public class RetraitManager {
	private RetraitDAO retraiDao;
	private static RetraitManager retraitManager;
	
	
	public static RetraitManager getRetraitManager() {
		if(retraitManager == null) {
			retraitManager = new RetraitManager();
		}
		return retraitManager;
		
	}
	
	public RetraitManager () {
		retraiDao= DAOFactory.getRetraitDAO();
	}
	public static void validerChamp(String paramAValider, int maxLength, int erreurAAjouter, BusinessException businessException) {
		if(paramAValider==null || paramAValider.trim()=="" || paramAValider.trim().length()>maxLength) {
			businessException.ajouterErreur(erreurAAjouter);
		}
	}
	
	public Retrait insert(int noArticle ,String rue ,String cp,String ville)throws BusinessException {
		Retrait ret =null;
		BusinessException businessException = new BusinessException();
		ret = new Retrait ( noArticle ,rue,cp,ville);
		validerChamp(ret.getRue(), 30, CodesResultatBLL.FORMAT_RETRAIT_RUE_ERROR, businessException);
		validerChamp(ret.getCode_postal(), 15, CodesResultatBLL.FORMAT_RETRAIT_CP_ERROR, businessException);
		validerChamp(ret.getVille(), 30, CodesResultatBLL.FORMAT_RETRAIT_VILLE_ERROR, businessException);
		if (!businessException.hasErreurs()) {
			this.retraiDao.insert(ret);;
			
		}else {
			throw businessException;
		}return ret;
	}
	
}
