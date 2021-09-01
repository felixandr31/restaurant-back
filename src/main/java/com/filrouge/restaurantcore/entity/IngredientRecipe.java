package com.filrouge.restaurantcore.entity;

import javax.persistence.Embedded;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * IngredientRecipe Entity
 * 
 * @author Hermann
 *
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "ingredientRecipe")
public class IngredientRecipe extends AbstractEntity {

	/**
	 * quantity of ingredient
	 */
	@Field("quantity")
	private Integer quantity;

	/**
	 * ingredient
	 */

	@Embedded
	private Ingredient ingredient;

}
