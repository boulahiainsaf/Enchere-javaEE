package fr.eni.enchere.bo.test;

import fr.eni.enchere.bo.Categorie;

public class TestCategorie {

	public static void main(String[] args) {
		Categorie cat = new Categorie(); 
		cat.setNoCategorie(1);
		cat.setLibelle("multimédia");
		Categorie cat2= new Categorie(2,"meuble");
		System.out.println("__________________________ Categories __________________________");
		System.out.println(cat.toString());
		System.out.println(cat2.toString());

	}

}
