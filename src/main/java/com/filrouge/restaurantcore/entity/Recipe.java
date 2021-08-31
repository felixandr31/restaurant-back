package com.filrouge.restaurantcore.entity;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entity Recipe
 * 
 * @author formation
 *
 */

@Data
@Builder

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Document("recipe")
public class Recipe extends AbstractEntity {

	/**
	 * the name of recipe
	 */
	@Field("recipe")
	@NonNull
	private String name;

	/**
	 * the craftion price
	 */
	@NonNull
	@Field("craftingPrice")
	private BigInteger craftingPrice;

	/**
	 * the selling price
	 */
	@Field("sellingPrice")
	private BigInteger sellingPrice;

	/**
	 * set of ingredientRecipe
	 */
	@DBRef
	private Set<IngredientRecipe> ingredientsRecipe;

	/**
	 * constructor
	 */

	public Recipe() {
		this.ingredientsRecipe = new HashSet<IngredientRecipe>(0);

	}

}
