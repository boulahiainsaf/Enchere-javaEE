package fr.eni.enchere.dal.test;

import java.util.List;

import fr.eni.enchere.bo.Categorie;

import fr.eni.enchere.dal.CategorieDAO;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.exceptions.BusinessException;

public class CategorieTest {

	public static void main(String[] args) {
		CategorieDAO catDAO = DAOFactory.getCategorieDAO()  ;
		Categorie cat1=new Categorie(3,"mode");
		Categorie cat2= new Categorie();
		Categorie cat3= new Categorie();
		cat3.setLibelle("accessoire");
		cat3.setNoCategorie(3);
		
		if(cat1==null||cat2==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			
		}
		System.out.println("Ajout des categorie... ");

		try {

			catDAO.insert(cat1);
			System.out.println("categorie ajouté : " + cat1.toString() );
			catDAO.insert(cat3);
			System.out.println("categorie ajouté  : " + cat3.toString() );

		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			
		}
		try {
			//Sélection de tous les categorie
			List<Categorie> categories = catDAO.selectAllCategorie();
			System.out.println("Sélection de tous les categorie  : " + categories.toString() );
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			
		}

	}

}
