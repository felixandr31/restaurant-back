package com.filrouge.restaurantcore.entity;

import java.math.BigDecimal;
import java.util.List;
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
 * Restaurant Entity
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
	

	
}