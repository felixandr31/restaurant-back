package com.filrouge.restaurantcore.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Message util. It's a singleton.
 * 
 * @author Hermann
 *
 */
public class MessagesUtil {

	private static MessagesUtil instance;
	// Le resource bundle du fichier de propriétés
	private ResourceBundle bundle;

	/**
	 * Contructeur.
	 * 
	 * @param fichierBundle le nom du fichier de propri�t�s du bundle
	 */
	private MessagesUtil(String fichierBundle) {
		this(fichierBundle, Locale.FRANCE);
	}

	/**
	 * Contructor.
	 * 
	 * @param fichierBundle le nom du fichier de propri�t�s du bundle
	 */
	private MessagesUtil(String fichierBundle, Locale maLangue) {
		this.bundle = ResourceBundle.getBundle("messages", maLangue);
	}

	/**
	 * Retourne une unique instance de la classe.
	 * 
	 * @param fichierBundle le nom du fichier de propri�t�s du bundle
	 * @return une instance de MessagesUtil
	 */
	public static MessagesUtil getInstance(String value) {
		if (instance == null) {
			instance = new MessagesUtil(value);
		}
		return instance;
	}

	/**
	 * Retourne le message d'une cl� dans le fichier de propri�t�s.
	 * 
	 * @param key la cl� du message
	 * @return le message
	 */
	public String getMessage(final String key) {
		return bundle.getString(key);
	}
}
