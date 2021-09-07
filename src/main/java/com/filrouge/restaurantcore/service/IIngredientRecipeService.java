package com.filrouge.restaurantcore.service;

import java.util.List;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;

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
}
