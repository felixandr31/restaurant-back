package com.filrouge.restaurantcore.entity;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Embedded;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * L'entit" restaurant
 * @author sslimani
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "restaurant")
public class Restaurant extends AbstractEntity {


	/**
	 * Le nom.
	 */
	@Field("name")
	private String name;
	
	/**
	 * Les informations de l'adresse dans un sous document.
	 */
	@Embedded
	private Address address;

	/**
	 * Le nombre d'étoiles 
	 */
	@Field("stars")
	private String stars;

	/**
	 * Les coordonnées.
	 */
	@Embedded
	private Coordinates coordinates;

	/**
	 * Les informations des employés dans un sous document. 
	 */
	@DBRef
	private set<User> employees;
	
	/**
	 * Les informations des tables dans un sous document. 
	 */
	@DBRef
	private set<Table> tables;

	

	/**
	 * Les rôles associées.
	 */
	@DBRef
	private Set<Role> roles;

	/**
	 * Constructeur.
	 */
	public Administrator() {
		this.roles = new HashSet<Role>();
	}
}
