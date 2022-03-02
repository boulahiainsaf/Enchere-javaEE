package fr.eni.enchere.bo;

public class Retrait {
	
	private static final long serialVersionUID = 1L;
 private int noRetrait;
 private String rue;
 private String code_postal;
 private String ville;
 private ArticleVendu article ;
 
 
public Retrait() {
	super();
}


public Retrait(String rue, String code_postal, String ville) {
	super();
	this.rue = rue;
	this.code_postal = code_postal;
	this.ville = ville;
}


public Retrait(String rue, String code_postal, String ville, ArticleVendu article) {
	this(rue,code_postal,ville);
	this.article = article;
}


public Retrait(int noRetrait, String rue, String code_postal, String ville) {
	super();
	this.noRetrait = noRetrait;
	this.rue = rue;
	this.code_postal = code_postal;
	this.ville = ville;
}




public Retrait(int noRetrait, String rue, String code_postal, String ville, ArticleVendu article) {
	super();
	this.noRetrait = noRetrait;
	this.rue = rue;
	this.code_postal = code_postal;
	this.ville = ville;
	this.article = article;
}


public String getRue() {
	return rue;
}


public void setRue(String rue) {
	this.rue = rue;
}


public String getCode_postal() {
	return code_postal;
}


public void setCode_postal(String code_postal) {
	this.code_postal = code_postal;
}


public String getVille() {
	return ville;
}


public int getNoRetrait() {
	return noRetrait;
}


public void setNoRetrait(int noRetrait) {
	this.noRetrait = noRetrait;
}


public void setVille(String ville) {
	this.ville = ville;
}


public ArticleVendu getArticle() {
	return article;
}


public void setArticle(ArticleVendu article) {
	this.article = article;
}


@Override
public String toString() {
	return "Retrait [rue=" + rue + ", code_postal=" + code_postal + ", ville=" + ville + ", article=" + article + "]";
}
 
 
 
}
