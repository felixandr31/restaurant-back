package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.RecipeDto;


public interface IRecipeService {
	
	/**
	 * Craation of a recipe.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */
		RecipeDto save(RecipeDto dto);

	
	/**
	 * Updating a Recipe without these associations.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */
	RecipeDto update(RecipeDto dto);

	/**
	 * 
	 * Search a Recipe by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<RecipeDto> findById(String id);

	/**
	 * Research of all Recipes.
	 * 
	 * @return the DTOs of the clients.
	 */
	List<RecipeDto> findAll();

	

	/**
	 * deletion  Recipe by his identifier.
	 * 
	 * @param ID identifier
	 */
	void deleteById(String id);

	/**
	 * Add IngredienRecipe of the Recipe. Identifiers of IngredienRecipe not found
     * are ignored
	 * 
	 * @param ID      identifier of Recipe.
	 * @param ingredientrecipeIds the identifier of ingredientrecipe
	 * @return the client's DTO with these roles.
	 */
	RecipeDto addIngredientRecipe(String idRecipe, Set<IngredientRecipeDto> ingredientRecipeDto);
	
	/**
	 * Removal of Recipe IngredientRecipe. Identifiers of I not found
     * are ignored.
	 * 
	 * @param id The Recipe identifier.
     * @param roleIds The role identifiers
     * @return the client's DTO with these roles.
	 * 
	 */
	RecipeDto removeRoles(String id, Set<String> ingredientIds);
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	 List<RecipeDto> findByName(String name);


	
}
	


