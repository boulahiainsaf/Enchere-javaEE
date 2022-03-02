package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.ArticleVendu;
import fr.eni.enchere.bo.Categorie;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.exceptions.BusinessException;

public class ArticleVendueDAOjdbcImpl implements ArticleVenduDAO {
	
	
	String sqlInsert = "INSERT INTO ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,no_utilisateur,no_categorie) VALUES (?,?,?,?,?,?,?)";	
	
	String sqlUpdate = "UPDATE ARTICLES_VENDUS SET nom_article = ?, description = ?, date_debut_encheres = ?, date_fin_encheres = ?, prix_initial = ?, prix_vente = ?, no_categorie = ? WHERE no_article = ?";
	
	/*String sqlSelectById = "SELECT nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+ "WHERE no_article = ?";*/
	
	String sqlDelete = "DELETE FROM ARTICLES_VENDUS WHERE no_article = ?";

	String sqlSelectAll = "SELECT no_article,nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie ";
	
	StringBuilder sq=new StringBuilder (sqlSelectAll);
	
	String sqlSelectById =sq.append(" WHERE no_article= ?").toString();
	
	String selectNomArticle=sq.append(" WHERE nom_article like ?").toString();
	
	/*String selectNomArticle="SELECT no_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+ "WHERE nom_article like ?";*/
	
	/*String selectByIdCategorie="SELECT no_article,nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "
			+"WHERE no_categorie=?";*/
	StringBuilder sql=new StringBuilder (sqlSelectAll);
	String selectByIdCategorie=sq.append(" WHERE no_categorie=?").toString();
	
	String inserRetrait ="INSERT INTO RETRAITS(no_article, rue, code_postal, ville)VALUES(?, ?, ?, ?)";
	
	String selectArticlCours=sq.append(" WHERE date_debut_encheres <  ? AND (date_fin_encheres >  ? " ).toString();
	
	String selectArticlCoursByNom="SELECT no_article,nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "+
	" WHERE nom_article like ? AND date_debut_encheres < ? AND date_fin_encheres >  ? " ;

	String selectArticlCoursByCat =" SELECT no_article,nom_article, description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,a.no_utilisateur,a.no_categorie,"
			+ "pseudo,email,code_postal,ville,"
			+ "libelle "
			+ "FROM ARTICLES_VENDUS a "
			+ "INNER JOIN UTILISATEURS u ON a.no_utilisateur = u.no_utilisateur "
			+ "INNER JOIN CATEGORIES c ON a.no_categorie = c.no_categorie "+
			"   WHERE a.no_categorie = ? AND date_debut_encheres <  ?  AND  date_fin_encheres >  ? ";
	String selectArticlNomDebuter = sq.append(" WHERE((no_utilisateur =?)AND(date_debut_encheres >  ? ))" ).toString();
	//insert article

	public ArticleVendu insert(ArticleVendu art) throws BusinessException {
		checkNull(art);
		try(Connection cnx = ConnectionProvider.getConnection();PreparedStatement stmt = cnx.prepareStatement(sqlInsert, PreparedStatement.RETURN_GENERATED_KEYS)){
			
			int i = 1;
			
			stmt.setString(i++, art.getNomArticle());					
			stmt.setString(i++, art.getDescription());					
			stmt.setDate(i++,java.sql.Date.valueOf(art.getDateDebutEncheres()));	
			stmt.setDate(i++,java.sql.Date.valueOf(art.getDateFinEncheres()));	
			stmt.setInt(i++, art.getMiseAPrix());						
			stmt.setInt(i++, art.getVendeur().getNoUtilisateur());		
			stmt.setInt(i++, art.getCategorie().getNoCategorie());		
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
				if(rs.next()) {
				art.setNoArticle(rs.getInt(1));
				
				
			
			}	
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
		return art;
		
	}

//update article
	public void update(ArticleVendu art) throws BusinessException {
		checkNull(art);
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlUpdate);){
			int i = 1;
			stmt.setString(i++, art.getNomArticle());					
			stmt.setString(i++, art.getDescription());					
			stmt.setDate(i++,java.sql.Date.valueOf(art.getDateDebutEncheres()));
			stmt.setDate(i++, java.sql.Date.valueOf(art.getDateFinEncheres()));	
			stmt.setInt(i++, art.getMiseAPrix());	
			stmt.setInt(i++, art.getPrixVent());	
			stmt.setInt(i++, art.getCategorie().getNoCategorie());		
			stmt.setInt(i++, art.getNoArticle());						
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJECT_ECHEC);
			throw businessException;
		}
	}


//selectionner un article par son Id
	public ArticleVendu selectById(int idArt) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlSelectById);){
			stmt.setInt(1, idArt);
			try(ResultSet rs = stmt.executeQuery();){
				rs.next();
													
				Utilisateur vendeur = userConstructore(rs.getInt(8),rs.getString(10),rs.getString(11),rs.getString(13),rs.getString(12));
				
													
				Categorie categorie = categorieConstructor(rs.getInt(9),rs.getString(14));
						
				ArticleVendu art = new ArticleVendu(
						idArt, 						
						rs.getString(2),			
						rs.getString(3),			
						rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(),
						rs.getInt(6),				
						rs.getInt(7),				
						vendeur,					
						categorie					
						);
				return art;

			}
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
	}

//delet article
	
	public void delete(int idArt) throws BusinessException {
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlDelete);){
			stmt.setInt(1, idArt);
			stmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
	}

	
//liste de tous les article 

	public List<ArticleVendu> selectAll() throws BusinessException {
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(sqlSelectAll);
				ResultSet rs = stmt.executeQuery();){
			while(rs.next()) {
											
				Utilisateur vendeur = userConstructore(rs.getInt(8),rs.getString(10),rs.getString(11),rs.getString(13),rs.getString(12));
												
				Categorie categorie = categorieConstructor(rs.getInt(9),rs.getString(14));
						
				ArticleVendu art = new ArticleVendu(
						rs.getInt(1), 				
						rs.getString(2),			
						rs.getString(3),			
						rs.getDate(4).toLocalDate(),
						rs.getDate(5).toLocalDate(),
						rs.getInt(6),				
						rs.getInt(7),				
						vendeur,					
						categorie					
						);
				lstArticles.add(art);
			}
			return lstArticles;
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
	}
	
	//tester la nullité de l'article 
	
	public void checkNull(ArticleVendu art) throws BusinessException {
		if(art == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}		
	}

	//selectionner un article par son nom 
	public  List<ArticleVendu> selectByNomArtilce(String nomArticle) throws BusinessException {
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(selectNomArticle);){
			stmt.setString(1, "%"+nomArticle+"%");
			try(ResultSet rs = stmt.executeQuery();){
				rs.next();
				while(rs.next()) {									
					Utilisateur vendeur = userConstructore(rs.getInt(8),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
														
					Categorie categorie = categorieConstructor(rs.getInt(9),rs.getString(14));
							
					ArticleVendu art = new ArticleVendu(
							rs.getInt(1), 						
							rs.getString(2),			
							rs.getString(3),			
							rs.getDate(4).toLocalDate(),
							rs.getDate(5).toLocalDate(),
							rs.getInt(6),				
							rs.getInt(7),				
							vendeur,					
							categorie					
							);
					lstArticles.add(art);
					}
			}
				return lstArticles;
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
	}
	
	

	//selectionner un article par categorie
	public List<ArticleVendu> selectByCategorie(Integer idCategorie) throws BusinessException {
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(selectNomArticle);){
			stmt.setInt(1, idCategorie);
			try(ResultSet rs = stmt.executeQuery();){
				rs.next();
				while(rs.next()) {									
				ArticleVendu art=article(rs);
				lstArticles.add(art);
				}
				
			}
				return lstArticles;
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
		
	}
	//selectionner tous les enchere en cour 
	public List<ArticleVendu> selectArticleEnCours(LocalDate dateDeJour)throws BusinessException{
		
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement stmt = cnx.prepareStatement(selectArticlCours);){
			stmt.setDate(1, java.sql.Date.valueOf(dateDeJour));
			stmt.setDate(2, java.sql.Date.valueOf(dateDeJour));
			try(ResultSet rs = stmt.executeQuery();){
				rs.next();
				while(rs.next()) {									
				ArticleVendu art=article(rs);
				lstArticles.add(art);
				}
				
			}
				return lstArticles;
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
 		
	}
	//selectionner tous les enchere en cour par son nom 
	public List<ArticleVendu> selectArticleEnCoursByNom(String nomArticl ,LocalDate dateDeJour)throws BusinessException{
		
		
		
		List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
		try(Connection cnx = ConnectionProvider.getConnection();
				
				PreparedStatement stmt = cnx.prepareStatement(selectArticlCoursByNom);){
			stmt.setString(1,"%"+nomArticl+"%");
			stmt.setDate(2, java.sql.Date.valueOf(dateDeJour));
			stmt.setDate(3, java.sql.Date.valueOf(dateDeJour));
			try(ResultSet rs = stmt.executeQuery();){
			
				while(rs.next()) {									
				ArticleVendu art=article(rs);
				
				lstArticles.add(art);
				}
				
			}
				return lstArticles;
			
		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
			throw businessException;
		}
		
		
	}
	//selectionner tous les enchere en cour par son ID Categorie selectArticlCoursByCat
		public List<ArticleVendu> selectArticleEnCoursByCategori(int idCategorie ,LocalDate dateDeJour)throws BusinessException{
	
			System.out.println(selectArticlCoursByCat);
			
			List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
			try(Connection cnx = ConnectionProvider.getConnection();
					
					PreparedStatement stmt = cnx.prepareStatement(selectArticlCoursByCat);){
				stmt.setInt(1,idCategorie);
				stmt.setDate(2, java.sql.Date.valueOf(dateDeJour));
				stmt.setDate(3, java.sql.Date.valueOf(dateDeJour));
				try(ResultSet rs = stmt.executeQuery();){
				
					while(rs.next()) {									
					ArticleVendu art=article(rs);
					
					lstArticles.add(art);
					}
					
				}
					return lstArticles;
				
			}catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
				throw businessException;
			}
		}
		
//selection mes vente non debuter 
public List<ArticleVendu> selectArticleNonDebuter(int idVendeur ,LocalDate dateDeJour)throws BusinessException{
			
			
			List<ArticleVendu> lstArticles = new ArrayList<ArticleVendu>();
			try(Connection cnx = ConnectionProvider.getConnection();
					PreparedStatement stmt = cnx.prepareStatement(selectArticlNomDebuter);){
				stmt.setInt(1, idVendeur);
				stmt.setDate(2, java.sql.Date.valueOf(dateDeJour));
				try(ResultSet rs = stmt.executeQuery();){
					rs.next();
					while(rs.next()) {									
					ArticleVendu art=article(rs);
					lstArticles.add(art);
					}
					
				}
					return lstArticles;
				
			}catch(Exception e) {
				e.printStackTrace();
				BusinessException businessException = new BusinessException();
				businessException.ajouterErreur(CodesResultatDAL.SELECT_OBJECT_ECHEC);
				throw businessException;
			}
	 		
		}
		
	private Utilisateur userConstructore(int no, String pseudo, String mail, String ville, String cp) {
		return new Utilisateur(no, pseudo, mail, cp, ville);
	}
	
	private Categorie categorieConstructor(Integer no, String libelle) {
		return new Categorie(no, libelle);
	}

	
	private ArticleVendu article(ResultSet rs ) throws BusinessException,SQLException{
	
			Utilisateur vendeur = userConstructore(rs.getInt(8),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13));
												
			Categorie categorie = categorieConstructor(rs.getInt(9),rs.getString(14));
					
			ArticleVendu art = new ArticleVendu(
					rs.getInt(1), 						
					rs.getString(2),			
					rs.getString(3),			
					rs.getDate(4).toLocalDate(),
					rs.getDate(5).toLocalDate(),
					rs.getInt(6),				
					rs.getInt(7),				
					vendeur,					
					categorie					
					);
			
			
		return art;
	}
	
	
}
