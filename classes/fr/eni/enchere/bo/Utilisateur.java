package fr.eni.enchere.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;

	private int noUtilisateur;
	private String pseudo;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String rue;
	private String codePostal;
	private String ville;
	private String mdp;
	private int credit;
	private boolean admin;
	private List<ArticleVendu> listeArticles;
	private List<Enchere> listeEncheres;

	public Utilisateur() {
		credit = 0;
		admin = false;
		listeArticles = new ArrayList<ArticleVendu>();
		listeEncheres = new ArrayList<Enchere>();
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String rue, String codePostal, String ville, String mdp) {
		this();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
	}

	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String mdp) {
		this();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
	}
	
	public Utilisateur(int noUtilisateur, String pseudo, String nom, String prenom, String email, String telephone, String rue, String codePostal, String ville, String mdp, int credit, boolean admin) {
		this();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.mdp = mdp;
		this.credit = credit;
		this.admin = admin;
	}
	// constructeur pour ajouter un objet Utilisateur depuis ArticleVenduDAO
	public Utilisateur(int noUtilisateur, String pseudo, String email, String codePostal, String ville) {
		this();
		this.noUtilisateur = noUtilisateur;
		this.pseudo = pseudo;
		this.email = email;
		this.codePostal = codePostal;
		this.ville = ville;
	}



	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	
	public List<ArticleVendu> getListeArticles() {
		return listeArticles;
	}

	public void setListeArticles(List<ArticleVendu> listeArticles) {
		this.listeArticles = listeArticles;
	}

	public List<Enchere> getListeEncheres() {
		return listeEncheres;
	}

	public void setListeEncheres(List<Enchere> listeEncheres) {
		this.listeEncheres = listeEncheres;
	}

	@Override
	public String toString() {
		return "Utilisateur - "+noUtilisateur+" : pseudo: "+pseudo+"; nom: "+nom+"; prenom: "+prenom+"; email: "+email+"; t�l�phone: "+telephone+"; rue: "+rue+ "; code postal: "+codePostal+"; ville: "+ville+"; credit: "+credit+"; admin: "+admin+"; articles � vendre: "+listeArticles+"; ench�res: "+listeEncheres+";";
	}

	public void afficher() {
		System.out.println("Utilisateur - "+noUtilisateur+" : pseudo: "+pseudo+"; nom: "+nom+"; prenom: "+prenom+"; email: "+email+"; t�l�phone: "+telephone+"; rue: "+rue+ "; code postal: "+codePostal+"; ville: "+ville+"; credit: "+credit+"; admin: "+admin+"; articles � vendre: "+listeArticles+"; ench�res: "+listeEncheres+";");
	}

}
