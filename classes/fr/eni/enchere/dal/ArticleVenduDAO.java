package fr.eni.enchere.dal;

import java.time.LocalDate;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.exceptions.BusinessException;

public interface ArticleVenduDAO {
	public ArticleVendu insert(ArticleVendu art) throws BusinessException ;
	public void update(ArticleVendu art) throws BusinessException;
	public ArticleVendu selectById(int idArt) throws BusinessException;
	public void delete(int idArt) throws BusinessException;
	public List<ArticleVendu> selectAll() throws BusinessException;
	public List<ArticleVendu> selectByNomArtilce(String nomArticle) throws BusinessException;
	public List<ArticleVendu> selectByCategorie(Integer idCategorie) throws BusinessException ;
	public List<ArticleVendu> selectArticleEnCoursByNom(String nom,LocalDate date) throws BusinessException;
	public List<ArticleVendu> selectArticleEnCoursByCategori(int id,LocalDate date) throws BusinessException;
}
