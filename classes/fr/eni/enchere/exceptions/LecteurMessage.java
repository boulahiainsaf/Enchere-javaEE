package fr.eni.enchere.exceptions;

import java.util.ResourceBundle;

public abstract class LecteurMessage {
private static ResourceBundle rb;
	
	static {
		try {
			rb = ResourceBundle.getBundle("fr.eni.enchere.exceptions.erreurs_fr");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 */
	public static String getMessageErreur(int code) {
		String message="";
		try {
			if(rb!=null) {
				message = rb.getString(String.valueOf(code));
			}else {
				message = "Probléme à la lecture du fichier contenant les messages";
			}
		}catch(Exception e) {
			e.printStackTrace();
			message = "Une erreur inconnue est survenue";
		}
		return message;
	}
}
