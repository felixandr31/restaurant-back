package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.IngredientDto;
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
	 * Search a client by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<UserDto> findById(String id);
	
	
	/**
	 * Création of a ingredient.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the ingredient
	 */
	IngredientDto save(IngredientDto dto);
	
	

}
