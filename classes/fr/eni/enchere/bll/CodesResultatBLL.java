package fr.eni.enchere.bll;
/**
 * Les codes disponibles sont entre 20000 et 29999
 *
 */
public abstract class CodesResultatBLL {
	/*
	 * Mot de passe ou email incorrect
	 */
//	public static final int REGLE_MDP_UTILISATEUR_ERREUR = 20000;
	/*
	 * Email existe d�j�
	 */
	public static final int REGLE_EMAIL_UTILISATEUR_ERREUR = 20001;
	/*
	 * Pseudo existe d�j�
	 */
	public static final int REGLE_PSEUDO_UTILISATEUR_ERREUR = 20002;

	/**
	 * Echec quand la description de l'article ne repsecte pas les r�gles d�finies
	 */
	public static final int REGLE_ARTICLE_DESCRIPTION_ERREUR=20003;
	
	/*
	 * Format Utilisateur.pseudo incorrect
	 */
	public static final int FORMAT_UTILISATEUR_PSEUDO_ERROR = 20004;
	/*
	 * Format Utilisateur.nom incorrect
	 */
	public static final int FORMAT_UTILISATEUR_NOM_ERROR = 20005;
	/*
	 * Format Utilisateur.prenom incorrect
	 */
	public static final int FORMAT_UTILISATEUR_PRENOM_ERROR = 20006;
	/*
	 * Format Utilisateur.email incorrect
	 */
	public static final int FORMAT_UTILISATEUR_EMAIL_ERROR = 20007;
	/*
	 * Format Utilisateur.telephone incorrect
	 */
	public static final int FORMAT_UTILISATEUR_TELEPHONE_ERROR = 20008;
	/*
	 * Format Utilisateur.rue incorrect
	 */
	public static final int FORMAT_UTILISATEUR_RUE_ERROR = 20009;
	/*
	 * Format Utilisateur.codePostal incorrect
	 */
	public static final int FORMAT_UTILISATEUR_CP_ERROR = 20010;
	/*
	 * Format Utilisateur.ville incorrect
	 */
	public static final int FORMAT_UTILISATEUR_VILLE_ERROR = 20011;
	/*
	 * Format Utilisateur.mot de passe incorrect
	 */
	public static final int FORMAT_UTILISATEUR_MDP_ERROR = 20012;
	/*
	 * Format Utilisateur.mot de passe CONFIRMATION incorrect
	 */
	public static final int FORMAT_UTILISATEUR_MDP_CONF_ERROR = 20013;
	/*
	 * Format champs vides interdites
	 */
	public static final int FORMAT_CHAMPS_VIDE = 20014;
	
	//vendeur introvable 
	
	public static final int VENDEUR_INCONNU = 20015;
	
	// categorie inconnu
	
	public static final int CATEGORIE_INCONNU =20016;
	
	//forma nom article invalide
	
	public static final int FORMAT_ARTICLE_NOM_ERROR=20017;
	
	//forma champs discription invalide 
	
	public static final int FORMAT_ARTICLE_DISCRIPTION_ERROR=20018;
	
	//forma champs prix-initial invalid 
	
	public static final int PRIX_INITIALE_INVALID=20019;
	
	//formate date de debut de l'encher invalid 
	
	public static final int DATE_DEBUT_ENCHERE_INVALIDE =20020;
	
	//forma date de fin de l'enchere invalid 
	
	public static final int DATE_FIN_ENCHERE_INVALIDE =20021;
	
	/*
	 * Utilisateur ou article pas trouv�
	 */
	public static final int FORMAT_UTILISATEUR_ARTICLE_NULL = 20022;
	/*
	 * Credit d'utilisateur insuffisant pour encherir
	 */
	public static final int	REGLE_CREDIT_INSUFFISANT = 20023;
	
	//Nom de la rue invalid
	
	public static final int FORMAT_RETRAIT_RUE_ERROR = 20024;
	
	//Nom de la cp invalid
	public static final int FORMAT_RETRAIT_CP_ERROR = 20025;
	
	//Nom de la ville invalid
	public static final int FORMAT_RETRAIT_VILLE_ERROR = 20026;
}
