package fr.eni.enchere.dal;

import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.exceptions.BusinessException;

public interface CategorieDAO {
	public void insert(Categorie cat) throws BusinessException;
	public void update(Categorie cat) throws BusinessException;
	public Categorie selectById(int idCat) throws BusinessException ;
	public void delete(int idCat) throws BusinessException;
	public List<Categorie> selectAllCategorie() throws BusinessException;
	
}
