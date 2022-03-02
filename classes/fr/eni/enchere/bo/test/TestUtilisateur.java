package fr.eni.enchere.bo.test;

import fr.eni.enchere.bo.Utilisateur;

public class TestUtilisateur {
	public static void main(String[] args) {
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
		System.out.println("__________________________ Utilisateurs __________________________");
		ut1.afficher();
		ut2.afficher();
		ut3.afficher();
	}
}
