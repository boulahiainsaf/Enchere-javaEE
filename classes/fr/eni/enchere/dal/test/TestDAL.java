package fr.eni.enchere.dal.test;

import java.util.List;

import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.CodesResultatDAL;
import fr.eni.enchere.dal.DAOFactory;
import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;


public class TestDAL {

	public static void main(String[] args) throws BusinessException {

		// Déclaration et instanciation de la DAO
		UtilisateurDAO utilisateurDAO = DAOFactory.getUtilisateurDAO();

		//Instanciation du jeu d'essai 
		Utilisateur ut1 = new Utilisateur();
		ut1.setNoUtilisateur(1);
		ut1.setPseudo("toto");
		ut1.setNom("Toto");
		ut1.setPrenom("Otto");
		ut1.setEmail("oto@toto.fr");
		ut1.setRue("18 rue Encheres");
		ut1.setVille("Rennes");
		ut1.setCodePostal("21500");
		ut1.setMdp("toto123");

		Utilisateur ut2 = new Utilisateur(2, "pepe", "Dupont", "Paul", "pauld@inbox.fr", "26 allée Garros", "13582", "Amiens", "coucou123");
		ut2.setCredit(150);
		Utilisateur ut3 = new Utilisateur(3, "cat55", "Russel", "Carol", "cruss@gmail.fr", "0758962535", "12 avenue general Daumont", "19856", "Paris", "passw0rd");
		ut3.setAdmin(true);

		if(ut1==null||ut2==null||ut3==null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_NULL);
			throw businessException;
		}

		System.out.println("Ajout des utilisateurs... ");

		try {

			utilisateurDAO.insert(ut1);
			System.out.println("Utilisateur ajouté  : " + ut1.toString() );
			utilisateurDAO.insert(ut2);
			System.out.println("Utilisateur ajouté  : " + ut2.toString() );
			utilisateurDAO.insert(ut3);
			System.out.println("Utilisateur ajouté  : " + ut3.toString() );

		}catch(Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_OBJECT_ECHEC);
			throw businessException;
		}
		try {
			//Sélection de l'utilisateur par id
			//			Utilisateur u = utilisateurDAO.selectById(ut2.getNoUtilisateur());
			//			System.out.println("Sélection de l'utilisateur par id  : " + u.toString() );


			//Sélection de l'utilisateur par email
			Utilisateur u3 = utilisateurDAO.findByEmail(ut3.getEmail(), ut3.getMdp());
			System.out.println("Sélection de l'utilisateur par email : " + u3.toString() );
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEUR_ECHEC);
			throw businessException;
		}

		try {
			//Sélection de tous les utilisateurs
			List<Utilisateur> utilisateurs = utilisateurDAO.selectAll();
			System.out.println("Sélection de tous les utilisateurs  : " + utilisateurs.toString() );
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_UTILISATEURS_ECHEC);
			throw businessException;
		}

		// Modification d'un utilisateur
		//			System.out.println("Modification d'un utilisateur  : " );
		//			System.out.println("Utilisateur avant modification : "  + ut1.toString());
		//			ut1.setRue("55 boulevard Roi René");
		//			ut1.setTelephone("0785965421");
		//			ut1.setCredit(230);
		//			utilisateurDAO.update(ut1);
		//			System.out.println("Utilisateur après modification  : " + ut1.toString() );



//		try {
//			//Suppression d'un utilisateur
//			System.out.println("Suppression de l'utilisateur  : " + ut1.toString());
//			utilisateurDAO.delete(ut1.getNoUtilisateur());
//			List<Utilisateur> utilisateurs = utilisateurDAO.selectAll();
//			System.out.println("Liste des utilisateurs après suppression : "  );
//			StringBuffer sb = new StringBuffer();
//			for(Utilisateur u: utilisateurs){
//				sb.append("Utilisateur [id=");
//				sb.append(u.getNoUtilisateur());
//				sb.append(", pseudo=");
//				sb.append(u.getPseudo());
//				sb.append(", nom=");
//				sb.append(u.getNom());
//				sb.append(", prenom=");
//				sb.append(u.getPrenom());
//				sb.append(", email=");
//				sb.append(u.getEmail());
//				sb.append(", rue=");
//				sb.append(u.getRue());
//				sb.append(", ville=");
//				sb.append(u.getVille());
//				sb.append(", cp=");
//				sb.append(u.getCodePostal());
//				sb.append(", telephone=");
//				sb.append(u.getTelephone());
//				sb.append(", credit=");
//				sb.append(u.getCredit());
//				sb.append(", admin=");
//				sb.append(u.isAdmin()).append("]\n");
//			}
//			System.out.println(sb.toString());
//			System.out.println("---------------------------------------------------------------");
//
//		}catch(Exception e) {
//			e.printStackTrace();
//			BusinessException businessException = new BusinessException();
//			businessException.ajouterErreur(CodesResultatDAL.DELETE_OBJET_ECHEC);
//		}

	}
}
