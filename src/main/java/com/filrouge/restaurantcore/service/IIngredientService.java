package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;

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
	 * Création of a ingredient.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the ingredient
	 */
	IngredientDto save(IngredientDto dto);
	
	
	/**
	 * Removal of an ingredient. 
	 * @param id The ingredient identifier.
     * @return the ingredient's DTO.
	 * 
	 */
	void deleteIngredientById(String id);
	
	/**
	 * Mise à jour du Role 
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	IngredientDto update(IngredientDto ingredientDto);
	

}
