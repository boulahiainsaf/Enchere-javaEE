package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Enchere;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;


public class EnchereDAOJdbcImpl implements EnchereDAO {
	private static final String INSERT_ENCHERE = "INSERT INTO ENCHERES(no_utilisateur, no_article, date_enchere, montant_enchere) values(?,?,?,?)";
	private static final String SELECT_ALL = "SELECT no_enchere, no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES";
	private static final String SELECT_BY_ID = "SELECT no_enchere, no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE  no_enchere = ?";
	private static final String SELECT_MAX_ENCHERE_BY_ARTICLE = "SELECT no_enchere, no_utilisateur, no_article, date_enchere, montant_enchere FROM ENCHERES WHERE no_article = ? AND montant_enchere = ?";

	@Override
	public void insert(Enchere enchere) throws BusinessException {

		if(enchere==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(INSERT_ENCHERE, PreparedStatement.RETURN_GENERATED_KEYS )) {

			pstmt.setInt(1, enchere.getAcheteur().getNoUtilisateur());
			pstmt.setInt(2, enchere.getArticle().getNoArticle());
			pstmt.setTimestamp(3, enchere.getDateEnchere());
			pstmt.setInt(4, enchere.getMontantEnchere());
			pstmt.executeUpdate();
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				if (rs.next()) {
					enchere.setNoEnchere(rs.getInt(1));
				}	
			}

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		} 

	}

	@Override
	public Enchere getById(int noEnchere) throws BusinessException {
		Enchere enchere = null;
		//TODO
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);) {
			pstmt.setInt(1, noEnchere);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) { 
					Timestamp date_enchere = rs.getTimestamp("date_enchere");
					int montant_enchere = rs.getInt("montant_enchere");
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_article"));
					enchere = new Enchere(noEnchere, date_enchere, montant_enchere, article, utilisateur);
				}
				return enchere;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	public Enchere modify() throws BusinessException{
		Enchere enchere = new Enchere();

		//TODO
		return enchere;
	}
	
	@Override
	public Enchere getMaxEnchereByArticle(int idArticle, int montant) throws BusinessException {
		Enchere enchere = null;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SELECT_MAX_ENCHERE_BY_ARTICLE);) {
			pstmt.setInt(1, idArticle);
			pstmt.setInt(2, montant);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) { 
					int no_enchere = rs.getInt("no_enchere");
					Timestamp date_enchere = rs.getTimestamp("date_enchere");
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					ArticleVendu article = new ArticleVendu();
					article.setNoArticle(rs.getInt("no_article"));
					enchere = new Enchere(no_enchere, date_enchere, montant, article, utilisateur);
				}
				return enchere;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}

	}

}
