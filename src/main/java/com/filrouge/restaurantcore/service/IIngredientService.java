package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import com.filrouge.restaurantcore.dto.IngredientDto;


public interface IIngredientService {
	
	/**
	 * Research of all ingredients.
	 * 
	 * @return the DTOs of the ingredient.
	 */
	List<IngredientDto> findAll();
	
	/**
	 * 
	 * Search an ingredient by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<IngredientDto> findById(String id);
	
	/**
	 * 
	 * Search an ingredient by name
	 * 
	 * @param name identifier
	 * @return the DTO found according to its name identifier.
	 */
	Optional<IngredientDto> findByName(String name);
	
	
	/**
	 * Création of a ingredient.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the ingredient
	 */
	IngredientDto save(IngredientDto dto);
	
	/**
	 * Mise à jour du Ingredient
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	IngredientDto update(IngredientDto ingredientDto);
	
	/**
	 * Removal of an ingredient. 
	 * @param id The ingredient identifier.
     * @return the ingredient's DTO.
	 * 
	 */
	void deleteIngredientById(String id);
	

}
