package fr.eni.enchere.bo;

import java.util.ArrayList;
import java.util.List;

public class Categorie {
	
	private static final long serialVersionUID = 1L;
	
	private int noCategorie;
	private String libelle;
	private List<ArticleVendu> articles;
	
	//constructeure
	public Categorie() {
		articles=new ArrayList<>();
	}

	public Categorie(int noCategorie, String libelle) {
		super();
		this.noCategorie = noCategorie;
		this.libelle = libelle;
	}

	public Categorie(String libelle, List<ArticleVendu> articles) {
		this();
		this.libelle = libelle;
		this.articles = articles;
	}

	public Categorie(int noCategorie, String libelle, List<ArticleVendu> articles) {
		this(libelle,articles);
		this.noCategorie = noCategorie;
		
	}

	public int getNoCategorie() {
		return noCategorie;
	}

	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public List<ArticleVendu> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleVendu> articles) {
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "Categorie [noCategorie=" + noCategorie + ", libelle=" + libelle + ", articles=" + articles + "]";
	}


	
	
	

}
