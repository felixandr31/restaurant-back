package com.filrouge.restaurantcore.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Restaurant Entity
 * 
 * @author sslimani
 *
 */
@Data

@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "restaurant")
public class Restaurant extends AbstractEntity {

	/**
	 * Name.
	 */
	@Field("name")
	private String name;

	/**
	 * Informations of l'adresses dans un sous document.
	 */
	@Embedded
	private Address address;

	/**
	 * Stars.
	 */
	@Field("stars")
	private Float stars;

	/**
	 * Coordinates.
	 */
	@Embedded
	private Coordinates coordinates;

	/**
	 * Les informations des employés dans un sous document.
	 */
	@DBRef
	private List<User> employees;

	/**
	 * Informations of tables dans un sous document.
	 */
	@DBRef
	private List<Table> tables;

	/**
	 * Budget
	 */
	@Field("budget")
	private BigDecimal budget;

	/**
	 * Purchase list.
	 */
	@DBRef
	private List<Purchase> purchases;

	/**
	 * list of recipes
	 */
	@DBRef
	private List<Recipe> recipes;

	public Restaurant() {
		this.employees = new ArrayList<User>(0);
		this.tables = new ArrayList<Table>(0);
		this.purchases = new ArrayList<Purchase>(0);
		this.recipes = new ArrayList<Recipe>(0);
	}

}
