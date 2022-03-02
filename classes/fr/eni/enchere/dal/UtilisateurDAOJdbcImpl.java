package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.exceptions.BusinessException;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO{

	private static final String INSERT_USER = "INSERT INTO UTILISATEURS(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?)";
	private static final String FIND_USER_BY_EMAIL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur FROM UTILISATEURS WHERE email = ? AND mot_de_passe = ?";
	private static final String FIND_USER_BY_IDENTIFIANT = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur FROM UTILISATEURS WHERE (email = ? OR pseudo = ?) AND mot_de_passe = ?";
	private static final String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville,  credit, administrateur FROM UTILISATEURS";
	private static final String SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, credit, administrateur FROM UTILISATEURS WHERE  no_utilisateur = ?";
	private static final String IS_EMAIL = "SELECT email FROM UTILISATEURS WHERE  email = ?";
	private static final String IS_PSEUDO = "SELECT pseudo FROM UTILISATEURS WHERE  pseudo = ?";
	private static final String UPDATE_USER = "UPDATE UTILISATEURS SET pseudo = ?, nom = ?, prenom = ?, email = ?, telephone =?, rue =?, code_postal = ?, ville = ?, mot_de_passe = ? , credit = ? , administrateur = ? WHERE pseudo = ? AND email = ?";
	private static final String UPDATE_CREDIT = "UPDATE UTILISATEURS SET credit = ? WHERE no_utilisateur = ? AND pseudo = ? AND email = ?";


	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {

		if(utilisateur==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(INSERT_USER, PreparedStatement.RETURN_GENERATED_KEYS);) {

			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMdp());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setBoolean(11, utilisateur.isAdmin());
			pstmt.executeUpdate();
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
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
	public Utilisateur findByIdentifiantAndEmail(String identifiant, String mdp) throws BusinessException {
		Utilisateur utilisateur = null;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(FIND_USER_BY_IDENTIFIANT);) {
			pstmt.setString(1, identifiant);
			pstmt.setString(2, identifiant);
			pstmt.setString(3, mdp);
			try(ResultSet rs = pstmt.executeQuery()){	
				if (rs.next()) {
					utilisateur=mapping(rs);
					return utilisateur;
				}else{
					BusinessException businessException = new BusinessException();
					businessException.ajouterErreur(CodesResultatDAL.REGLE_MDP_UTILISATEUR_ERREUR);
					throw businessException;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public Utilisateur findByEmail(String email, String mdp) throws BusinessException {
		Utilisateur utilisateur = null;

		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(FIND_USER_BY_EMAIL);) {
			pstmt.setString(1, email);
			pstmt.setString(2, mdp);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					utilisateur=mapping(rs);
				}
				return utilisateur;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}

	}

	@Override
	public Utilisateur selectById(int id) throws BusinessException {
		Utilisateur utilisateur = null;

		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SELECT_BY_ID);) {
			pstmt.setInt(1, id);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					utilisateur=mapping(rs);
				}
				return utilisateur;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}
	}

	@Override
	public List<Utilisateur> selectAll() throws BusinessException {
		List<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(SELECT_ALL);) {
			try(ResultSet rs = pstmt.executeQuery();){
				while(rs.next()) {
					Utilisateur utilisateur = new Utilisateur();
					utilisateur.setNoUtilisateur(rs.getInt("no_utilisateur"));
					utilisateur.setPseudo(rs.getString("pseudo"));
					utilisateur.setNom(rs.getString("nom"));
					utilisateur.setPrenom(rs.getString("prenom"));
					utilisateur.setEmail(rs.getString("email"));
					utilisateur.setTelephone(rs.getString("telephone"));
					utilisateur.setRue(rs.getString("rue"));
					utilisateur.setCodePostal(rs.getString("code_postal"));
					utilisateur.setVille(rs.getString("ville"));
					utilisateur.setMdp(rs.getString("mot_de_passe"));
					utilisateur.setCredit(rs.getInt("credit"));
					utilisateur.setAdmin(rs.getBoolean("administrateur"));
					listeUtilisateurs.add(utilisateur);
				}
				return listeUtilisateurs;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;
		}
	}

	@Override
	public boolean isEmail(String email){
		boolean isEmail = false;

		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(IS_EMAIL);) {
			pstmt.setString(1, email);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					isEmail = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isEmail;
	}


	@Override
	public boolean isPseudo(String pseudo){
		boolean isPseudo = false;
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(IS_PSEUDO);) {
			pstmt.setString(1, pseudo);
			try(ResultSet rs = pstmt.executeQuery();){
				if (rs.next()) {
					isPseudo = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isPseudo;
	}

	@Override
	public void update(Utilisateur utilisateur) throws BusinessException {

		if(utilisateur==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(UPDATE_USER);) {
			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMdp());
			pstmt.setString(10, utilisateur.getMdp());
			pstmt.setInt(11, utilisateur.getCredit());
			pstmt.setBoolean(12, utilisateur.isAdmin());
			pstmt.setInt(13, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.UPDATE_OBJECT_ECHEC);
			throw businessException;
		} 
	}
	@Override
	public void updateCredit(Utilisateur utilisateur) throws BusinessException {

		if(utilisateur==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(UPDATE_CREDIT);) {

			pstmt.setInt(1, utilisateur.getCredit());
			pstmt.setInt(2, utilisateur.getNoUtilisateur());
			pstmt.setString(3, utilisateur.getPseudo());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		} 
	}
	/*@Override
	public void delete( Utilisateur utilisateur) throws BusinessException {

		if(utilisateur==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		}
		try(Connection cnx = ConnectionProvider.getConnection(); PreparedStatement pstmt = cnx.prepareStatement(DELETE_USER);) {
			pstmt.setInt(1, utilisateur.getNoUtilisateur());
			pstmt.executeUpdate();
			try(ResultSet rs = pstmt.getGeneratedKeys()){
				if (rs.next()) {
					utilisateur.setNoUtilisateur(rs.getInt(1));
				}	
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
			throw businessException;
		} 
	}*/


	private Utilisateur mapping(ResultSet rs) throws SQLException {
		Utilisateur u;
		int id = rs.getInt("no_utilisateur");
		String pseudo = rs.getString("pseudo");
		String nom = rs.getString("nom");
		String prenom = rs.getString("prenom");
		String email = rs.getString("email");
		String telephone = rs.getString("telephone");
		String rue = rs.getString("rue");
		String codePostal = rs.getString("code_postal");
		String ville = rs.getString("ville");
		int credit = rs.getInt("credit");
		Boolean admin = rs.getBoolean("administrateur");

		u = new Utilisateur(id, pseudo, nom, prenom, email, telephone, rue, codePostal, ville, null, credit, admin);
		return u;
	}

	@Override
	public boolean delete(Utilisateur utilisateur) throws BusinessException {
		// TODO Auto-generated method stub
		return false;
	}


}
