package com.filrouge.restaurantcore.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Ingredient;

/**
 * Personalized DAO services for Ingredient.
 * @author FAndre
 *
 */

public interface IIngredientRepository extends MongoRepository<Ingredient, String>{
	
	Optional<Ingredient> findByName(String name);
	
}
