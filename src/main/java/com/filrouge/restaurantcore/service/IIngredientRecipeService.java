package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.RestaurantDto;

/**
 * Services métier de gestion des ingredients d'une recette.
 * 
 * @author FAndre
 *
 */
public interface IIngredientRecipeService {
	/**
	 * Creation of ingredient recipe.
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */

	/**
	 * Recherche de l'ensemble des ingredients d'une recette.
	 * 
	 * @return les DTOs des ingredients d'une recette.
	 */
	List<IngredientRecipeDto> findAll();
	
	/**
	 * Find a ingredientRecipe by its id.
	 * 
	 * @param id L'identifiant.
	 * @return le DTO trouvée selon son identifiant.
	 */
	Optional<IngredientRecipeDto> findById(String id);
	
	/**
	 * find if an ingredientRecipe exist by finding it with its quantity and ingredient.
	 * 
	 * @param quantity quantity.
	 * @param dto the DTO
	 * @return true if the IngredientRecipe already exists, or false
	 */
//	boolean existsByQuantityAndIngredient  (int quantity, IngredientDto dto);
	
	/**
	 * Création of a ingredientRecipe.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the ingredientRecipe
	 */
	Optional<IngredientRecipeDto> save(IngredientRecipeDto dto);
	
	/**
	 * Mise à jour de IngredientRecipe 
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	IngredientRecipeDto update(IngredientRecipeDto ingredientRecipeDto);
	
	/**
	 * Removal of an ingredientRecipe. 
	 * @param id The ingredientRecipe identifier.
     * @return the ingredientRecipe's DTO.
	 * 
	 */
	void deleteIngredientRecipeById(String id);
	

	
}
