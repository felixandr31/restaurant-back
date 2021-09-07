package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.IngredientRecipe;

/**
 * Personalized DAO services for Ingredient.
 * @author FAndre
 *
 */

public interface IIngredientRecipeRepository extends MongoRepository<IngredientRecipe, String> {
	


}
