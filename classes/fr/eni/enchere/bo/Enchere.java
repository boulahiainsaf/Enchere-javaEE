package fr.eni.enchere.bo;

import java.sql.Timestamp;

public class Enchere {
	
	private static final long serialVersionUID = 1L;
	
	private int noEnchere;
	private Timestamp dateEnchere;
	private Integer montantEnchere;
	private ArticleVendu article;
	private Utilisateur acheteur;
	
	public Enchere() {
		super();
	}

	public Enchere(int noEnchere, Timestamp dateEnchere, Integer montantEnchere, ArticleVendu article, Utilisateur acheteur) {
		this(dateEnchere,montantEnchere);
		this.article = article;
		this.acheteur = acheteur;
	}

	public Enchere(Timestamp dateEnchere, Integer montantEnchere) {
		super();
		this.dateEnchere = dateEnchere;
		this.montantEnchere = montantEnchere;
	}

	public int getNoEnchere() {
		return noEnchere;
	}

	public void setNoEnchere(int noEnchere) {
		this.noEnchere = noEnchere;
	}

	public Timestamp getDateEnchere() {
		return dateEnchere;
	}


	public void setDateEnchere(Timestamp dateEnchere) {
		this.dateEnchere = dateEnchere;
	}

	public Integer getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(Integer montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public ArticleVendu getArticle() {
		return article;
	}

	public void setArticle(ArticleVendu article) {
		this.article = article;
	}
	
	public Utilisateur getAcheteur() {
		return acheteur;
	}

	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}

	@Override
	public String toString() {
		return "Enchere [noEnchere="+ noEnchere +", dateEnchere=" + dateEnchere + ", montantEnchere=" + montantEnchere + ", article=" + article
				+ ", enchéri par "+acheteur+"]";
	}
	
	
	

}
