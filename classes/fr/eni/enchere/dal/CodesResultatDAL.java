package fr.eni.enchere.dal;
/**
 * Les codes disponibles sont entre 10000 et 19999
 *
 */
public abstract class CodesResultatDAL {
	/*
	 * Echec g�n�ral quand tentative d'ajouter un objet avec la valeur null
	 */
	public static final int INSERT_OBJECT_NULL = 10000;
	/*
	 * Echec g�n�ral quand erreur non g�r� � l'insertion
	 */
	public static final int INSERT_OBJECT_ECHEC = 10001;
	/*
	 * Echec de l'affichage d'un utilisateur
	 */
	public static final int LECTURE_UTILISATEUR_ECHEC = 10002;
	/*
	 * Echec de l'affichage d'une liste des utilisateurs
	 */
	public static final int LECTURE_UTILISATEURS_ECHEC = 10003;
	
	/*
	 * Echec � la supression d'un objet
	 */
	public static final int DELETE_OBJET_ECHEC = 10004;
	//debut
	/**
	 * Echec g�n�ral quand tentative d'ajouter une CATEGORIE null
	 */
	public static final int OBJECT_NULL=10005;
	
	
	/**
	 * Echec g�n�ral quand erreur non g�r� � l'update
	 */
	public static final int UPDATE_OBJECT_ECHEC=10006;
	
	/**
	 * Echec g�n�ral quand erreur non g�r� � la selection
	 */
	public static final int SELECT_OBJECT_ECHEC=10007;

	
	//Une tentative d'enregistrement d'informations inexistante a au lieu.
	public static final int INSERT_OBJECT_EXICTE=10008;
	
	
	/*
	 * Echec � la supression d'un objet
	 */
	public static final int REGLE_MDP_UTILISATEUR_ERREUR = 10009;

	

}
