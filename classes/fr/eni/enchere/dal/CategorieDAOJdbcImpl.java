package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.exceptions.BusinessException;

public class CategorieDAOJdbcImpl implements CategorieDAO {
	String sqlInsert = "INSERT INTO CATEGORIES (libelle) VALUES (?)";
	String sqlUpdate = "UPDATE CATEGORIES SET libelle = ? WHERE no_categorie = ?";
	String sqlSelectById = "SELECT nom_article FROM CATEGORIES WHERE no_categorie = ?";
	String sqlDelete = "DELETE FROM CATEGORIES WHERE no_categorie = ?";
	
	String sqlSelectAll = "SELECT no_categorie,libelle FROM CATEGORIES";
	
//ajouter une catégorie

	public void insert(Categorie cat) throws BusinessException {
		//verifier la nullité de l'objet 
		checkNull(cat);
		//connexion à la base de données
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, cat.getLibelle());
			stmt.executeUpdate();
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				rs.next();
				cat.setNoCategorie(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
	}

	//modifier une catégorie
	public void update(Categorie cat) throws BusinessException {
		checkNull(cat);
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);) {
			stmt.setString(1, cat.getLibelle());
			stmt.setInt(2, cat.getNoCategorie());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJECT_ECHEC);
			throw businessException;
			
		}
	}

	//sélectionner une catégorie par l'ID
	public Categorie selectById(int idCat) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);) {
			stmt.setInt(1, idCat);
			
			try (ResultSet rs = stmt.executeQuery();) {
				rs.next();
				return new Categorie(idCat, rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
	}

	//supprimer une catégorie
	public void delete(int idCat) throws BusinessException {
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlDelete);) {
			stmt.setInt(1, idCat);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
	}
//verifier si la catégorie est null ou pas 
	public void checkNull(Categorie cat) throws BusinessException {
		if (cat == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJECT_NULL);
			throw businessException;
		}
	}

	
	


	//liste des categories
	public List<Categorie> selectAllCategorie() throws BusinessException {
		List<Categorie> lstCategorie = new ArrayList<Categorie>();
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
															
				Categorie categorie = new Categorie(rs.getInt(1), rs.getString(2));
				lstCategorie.add(categorie);
			}
			return lstCategorie;
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
	}
}
