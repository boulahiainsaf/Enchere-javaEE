package fr.eni.enchere.bll;

import fr.eni.enchere.dal.UtilisateurDAO;
import fr.eni.enchere.exceptions.BusinessException;
import fr.eni.enchere.view.CodesResultatServlets;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.eni.enchere.bll.utils.MD5Utils;
import fr.eni.enchere.bo.Utilisateur;
import fr.eni.enchere.dal.DAOFactory;


public class UtilisateurManager {

	private static final String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
	private static final String PHONE_REGEX = "^[0]{1}([4]|[6-7]){1}[0-9]{8}$";
	private static final String TEXT_REGEX = "^[\\pL\\pM\\p{Zs} ]+$";
	private static final String TEXT_NUMBER_REGEX = "[\\p{L}\\p{M}\\s\\d'-]+$";
	private static final String PSEUDO_REGEX = "^([a-z]|[A-Z]|[�-�]|[0-9])+$";
	private static final String CP_REGEX = "^[0-9]{5}$";
	private static final String MDP_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()�[{}]:;',?/*~$^+=<>_]).{8,30}$";
	private UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager utilisateurManager;

	public static UtilisateurManager getUtilisateurManager() {
		if (utilisateurManager == null) {
			utilisateurManager = new UtilisateurManager();
		}
		return utilisateurManager;
	}

	public UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public Utilisateur createUtilisateur(String pseudo, String nom, String prenom, String email, String telephone, String rue, String cp, String ville, String mdp, String mdp2) throws BusinessException {

		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;

		// valider pseudo (unique)
		this.validerPseudo(pseudo, businessException);
		// valider nom
		this.validerChamp(nom, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_NOM_ERROR, businessException);
		// valider pr�nom
		this.validerChamp(prenom, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_PRENOM_ERROR, businessException);
		// valider email (unique)
		this.validerEmail(email,businessException);
		// valider telephone (peut �tre null)
		this.validerChampNull(telephone, 15, PHONE_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_TELEPHONE_ERROR, businessException);
		// valider rue
		this.validerChamp(rue, 30, TEXT_NUMBER_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_RUE_ERROR, businessException);
		// valider code postale
		this.validerChamp(cp, 10, CP_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_CP_ERROR, businessException);
		// valider ville
		this.validerChamp(ville, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_VILLE_ERROR, businessException);
		// valider premier champs du mdp
		this.validerChamp(mdp, 30, MDP_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_MDP_ERROR, businessException);
		// valider deuxi�me champs du mdp (comparaison)
		this.comparerMdp(mdp, mdp2, CodesResultatBLL.FORMAT_UTILISATEUR_MDP_CONF_ERROR, businessException);
		// Chiffrer le mot de passe
		String hashMdp = MD5Utils.digest(mdp);
		System.out.println("Manager-liste erreurs : "+businessException.getListeCodesErreur());
		if(!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(cp);
			utilisateur.setVille(ville);
			utilisateur.setMdp(hashMdp);
			utilisateurDAO.insert(utilisateur);
		}
		else {
			throw businessException;  
		}
		return utilisateur;
	}

	public List<Utilisateur> selectAllUtilisateurs() throws BusinessException {
		return this.utilisateurDAO.selectAll();
	}

	public Utilisateur selectUtilisateurById(int id) throws BusinessException {
		return this.utilisateurDAO.selectById(id);
	}

	public Utilisateur verifyUtilisateurInscription(String identifiant, String mdp) throws BusinessException {
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;
		if(identifiant==null||mdp==null||identifiant.length()==0||mdp.length()==0) {
			businessException.ajouterErreur(CodesResultatBLL.FORMAT_CHAMPS_VIDE);
			throw businessException;
		}else {
			String hashMdp = MD5Utils.digest(mdp);
			utilisateur =  utilisateurDAO.findByIdentifiantAndEmail(identifiant, hashMdp);
			System.out.println("Manager-liste erreurs : "+businessException.getListeCodesErreur());
			System.out.println("Manager-"+utilisateur);
		}
		return utilisateur;
	}

	public void validerPseudo(String pseudo, BusinessException businessException) {
		if(pseudo==null || pseudo.trim()=="" || pseudo.trim().length()>30 || !pseudo.trim().matches(PSEUDO_REGEX)) {
			System.out.println("BLL - regex pseudo");
			businessException.ajouterErreur(CodesResultatBLL.FORMAT_UTILISATEUR_PSEUDO_ERROR);
		}else {
			if(this.utilisateurDAO.isPseudo(pseudo)==true) {
				System.out.println("BLL - regex pseudo MEME");
				businessException.ajouterErreur(CodesResultatBLL.REGLE_PSEUDO_UTILISATEUR_ERREUR);
			}
		}
	}

	public void validerEmail(String email, BusinessException businessException) {
		if(email==null || email.trim()=="" || email.trim().length()>128 || !email.trim().matches(EMAIL_REGEX)) {
			businessException.ajouterErreur(CodesResultatBLL.FORMAT_UTILISATEUR_EMAIL_ERROR);
			System.out.println("BLL - regex email");
		}else {
			if(this.utilisateurDAO.isEmail(email)==true) {
				System.out.println("BLL - regex email MEME");
				businessException.ajouterErreur(CodesResultatBLL.REGLE_EMAIL_UTILISATEUR_ERREUR);
			}
		}
	}

	// validation du format du champs de formulaire qui ne peut pas �tre null
	public void validerChamp(String paramAValider, int maxLength, String regex, int erreurAAjouter, BusinessException businessException) {

		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(paramAValider);

		if(paramAValider==null || paramAValider.trim()=="" || paramAValider.trim().length()>maxLength || !m.find()) {
			businessException.ajouterErreur(erreurAAjouter);
		}
	}

	// validation du format du champs de formulaire qui peut �tre null
	public void validerChampNull(String paramAValider, int maxLength, String regex, int erreurAAjouter, BusinessException businessException) {
		if(paramAValider!=null && paramAValider.trim()!="" && (paramAValider.trim().length()>maxLength || !paramAValider.trim().matches(regex))) {
			businessException.ajouterErreur(erreurAAjouter);
		}
	}

	public void comparerMdp(String mdp, String mdp2, int erreurAAjouter, BusinessException businessException ) {
		if(!mdp2.trim().equals(mdp)) {
			businessException.ajouterErreur(erreurAAjouter);
		}
	}

	public Utilisateur updateUtilisateur(String pseudo, String nom, String prenom, String email,
			String telephone, String rue, String cp, String ville, String mdp, String mdp2, int id ) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		Utilisateur utilisateur = null;
		
		// valider pseudo (unique)
		this.validerPseudo(pseudo, businessException);
		// valider nom
		this.validerChamp(nom, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_NOM_ERROR, businessException);
		// valider pr�nom
		this.validerChamp(prenom, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_PRENOM_ERROR, businessException);
		// valider email (unique)
		this.validerEmail(email,businessException);
		// valider telephone (peut �tre null)
		this.validerChampNull(telephone, 15, PHONE_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_TELEPHONE_ERROR, businessException);
		// valider rue
		this.validerChamp(rue, 30, TEXT_NUMBER_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_RUE_ERROR, businessException);
		// valider code postale
		this.validerChamp(cp, 10, CP_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_CP_ERROR, businessException);
		// valider ville
		this.validerChamp(ville, 30, TEXT_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_VILLE_ERROR, businessException);
		// valider premier champs du mdp

		
		this.validerChampNull(mdp, 30, MDP_REGEX, CodesResultatBLL.FORMAT_UTILISATEUR_MDP_ERROR, businessException);
		// valider deuxi�me champs du mdp (comparaison)
		this.comparerMdp(mdp, mdp2, CodesResultatBLL.FORMAT_UTILISATEUR_MDP_CONF_ERROR, businessException);
		// Chiffrer le mot de passe
		String hashMdp = MD5Utils.digest(mdp);
		System.out.println("Manager-liste erreurs : "+businessException.getListeCodesErreur());
		if(!businessException.hasErreurs()) {
			utilisateur = new Utilisateur();
			utilisateur.setPseudo(pseudo);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			utilisateur.setRue(rue);
			utilisateur.setCodePostal(cp);
			utilisateur.setVille(ville);
			utilisateur.setMdp(hashMdp);
			utilisateur.setMdp(hashMdp);
			utilisateurDAO.update(utilisateur);
		}
		else {
			throw businessException;  
		}
		return utilisateur;
		
	}


}