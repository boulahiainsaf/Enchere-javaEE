package fr.eni.enchere.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import fr.eni.enchere.bo.Retrait;
import fr.eni.enchere.exceptions.BusinessException;

public class RetraitDAOJdbcImpl implements RetraitDAO {
	private static final String INSERT = "insert into RETRAITS (no_article,rue, code_postal, ville) values (?,?,?,?)";
	private static final String GET_BY_ID = "SELECT * FROM RETRAITS WHERE no_retrait = ?";
	private static final String GET_ALL = "SELECT * FROM RETRAITS";
	private static final String UPDATE = "UPDATE RETRAITS SET rue = ?, code_postal = ?, ville = ? WHERE no_retrait=?";
	private static final String DELETE = "DELETE RETRAITS WHERE no_retrait = ?";
	private static final String SELECTID = "SELECT MAX(no_article) From ARTICLES_VENDUS";
	
	public  void insert(Retrait ret ) throws BusinessException{
		checkNull(ret);

		try (Connection con = ConnectionProvider.getConnection();
				PreparedStatement stmt = con.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);){

			int i = 1;
			stmt.setInt 	(i++, ret.getArticle().getNoArticle());
			stmt.setString	(i++, ret.getRue());
			stmt.setString	(i++, ret.getCode_postal());
			stmt.setString	(i++, ret.getVille());
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
			ret.setNoRetrait(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
	}
	public void checkNull(Retrait retrait) throws BusinessException {
		if(retrait == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.OBJECT_NULL);
			throw businessException;
		}		
	
	}
}
