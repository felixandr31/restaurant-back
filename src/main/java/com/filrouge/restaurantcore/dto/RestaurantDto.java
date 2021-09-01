package com.filrouge.restaurantcore.dto;

import java.math.BigDecimal;
import java.time.Instant;

import java.util.List;

import javax.persistence.Embedded;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.filrouge.restaurantcore.entity.Address;
import com.filrouge.restaurantcore.entity.Coordinates;
import com.filrouge.restaurantcore.entity.Purchase;
import com.filrouge.restaurantcore.entity.Recipe;
import com.filrouge.restaurantcore.entity.Table;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;
/**
 * Transfert Object from restaurant (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class RestaurantDto {

	/**
	 * Name.
	 */
	private String name;
	
	/**
	 * Informations of l'adresses dans un sous document.
	 */
	private Address address;

	/**
	 * Stars. 
	 */
	private Float stars;

	/**
	 * Coordinates.
	 */
	private Coordinates coordinates;

	/**
	 * Les informations des employés dans un sous document. 
	 */
	private List<User> employees;
	
	/**
	 * Informations of tables dans un sous document. 
	 */
	private List<Table> tables;
	
    /**
     * Budget
     */
	private BigDecimal budget;
	
	/**
	 * Purchase list. 
	 */
	private List<Purchase> purchases;
	
    /**
     * list of recipes 
     */
	private List<Recipe> recipes;
	
}
