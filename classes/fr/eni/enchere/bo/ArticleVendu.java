package fr.eni.enchere.bo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArticleVendu {
	
	private static final long serialVersionUID = 1L;
	
	private int noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private int miseAPrix;
	private int prixVent;
	private String etatVente;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	private List<Enchere> enchere;
	private Retrait retrait;
	
	
	public ArticleVendu() {
		enchere=new ArrayList<Enchere>();
	}


	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, Categorie categorie, List<Enchere> enchere, Retrait retrait) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.enchere = enchere;
		this.retrait = retrait;
	}
	
	
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			int miseAPrix, Utilisateur vendeur, Categorie categorie) {
		this();
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}


	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix,Categorie categorie,Utilisateur vendeur, Retrait retrait) {
		this();
		
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.categorie = categorie;
		this.vendeur = vendeur;
		this.retrait = retrait;
	}

	

	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVent, String etatVente,
			Utilisateur vendeur, Categorie categorie, List<Enchere> enchere, Retrait retrait) {
		this();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVent = prixVent;
		this.etatVente = etatVente;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.enchere = enchere;
		this.retrait = retrait;
	}


	public ArticleVendu(int noArticle, String nomArticle, String description, LocalDate dateDebutEncheres,
			LocalDate dateFinEncheres, int miseAPrix, int prixVent, Utilisateur vendeur,
			Categorie categorie) {
		this();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVent = prixVent;
		this.vendeur = vendeur;
		this.categorie = categorie;
	}
	
	public ArticleVendu(int noArticle, String nomArticle, String description,LocalDate dateDebutEncheres, LocalDate dateFinEncheres, int miseAPrix, int prixVente,
			 Utilisateur vendeur, Categorie categorie,
			Retrait retrait) {
		this();
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.dateDebutEncheres = dateDebutEncheres;
		this.dateFinEncheres = dateFinEncheres;
		this.miseAPrix = miseAPrix;
		this.prixVent = prixVent;
		this.vendeur = vendeur;
		this.categorie = categorie;
		this.retrait = retrait;
	}
	


	public int getNoArticle() {
		return noArticle;
	}


	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}


	public String getNomArticle() {
		return nomArticle;
	}


	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}


	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}


	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}


	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}


	public int getMiseAPrix() {
		return miseAPrix;
	}


	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}


	public int getPrixVent() {
		return prixVent;
	}


	public void setPrixVent(int prixVent) {
		this.prixVent = prixVent;
	}


	public String getEtatVente() {
		return etatVente;
	}


	public void setEtatVente(String etatVente) {
		this.etatVente = etatVente;
	}


	public Utilisateur getVendeur() {
		return vendeur;
	}


	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<Enchere> getEnchere() {
		return enchere;
	}


	public void setEnchere(List<Enchere> enchere) {
		this.enchere = enchere;
	}


	public Retrait getRetrait() {
		return retrait;
	}


	public void setRetrait(Retrait retrait) {
		this.retrait = retrait;
	}


	public Utilisateur getAcheteur() {
		return acheteur;
	}


	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}





	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVent=" + prixVent + ", etatVente=" + etatVente + "]";
	}
	

}
