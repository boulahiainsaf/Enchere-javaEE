package fr.eni.enchere.dal;

import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

public interface EnchereDAO {

	void insert(Enchere enchere) throws BusinessException;

	Enchere getById(int noEnchere) throws BusinessException;

	Enchere getMaxEnchereByArticle(int idArticle, int montant) throws BusinessException;

}
