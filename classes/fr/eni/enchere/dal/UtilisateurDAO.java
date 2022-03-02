package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

public interface UtilisateurDAO {
	void insert(Utilisateur utilisateur) throws BusinessException;
	Utilisateur findByEmail(String email, String password) throws BusinessException;
	Utilisateur selectById(int id) throws BusinessException;
	List<Utilisateur> selectAll() throws BusinessException;
	boolean isPseudo(String pseudo);
	boolean isEmail(String email);
	Utilisateur findByIdentifiantAndEmail(String identifiant, String mdp) throws BusinessException;
	void update(Utilisateur utilisateur) throws BusinessException;
	boolean delete(Utilisateur utilisateur) throws BusinessException;
	void updateCredit(Utilisateur utilisateur) throws BusinessException;	
}