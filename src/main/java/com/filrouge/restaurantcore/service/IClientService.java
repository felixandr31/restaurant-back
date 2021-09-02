package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.filrouge.restaurantcore.dto.UserDto;

public interface IClientService {
	
	/**
	 * Création of a client.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */
		UserDto save(UserDto dto);

	
	/**
	 * Updating a Client without these associations.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */
	UserDto update(UserDto dto);

	/**
	 * 
	 * Search a client by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<UserDto> findById(String id);

	/**
	 * Research of all clients.
	 * 
	 * @return the DTOs of the clients.
	 */
	List<UserDto> findAll();

	/**
	 * Research the managers.
	 * 
	 * @return the DTO of the messages.
	 */
	List<UserDto> findAllUsers();

	/**
	 * deletion  client by his identifier.
	 * 
	 * @param ID identifier
	 */
	void deleteById(String id);

	/**
	 * Add roles of the client. Identifiers of roles not found
     * are ignored
	 * 
	 * @param ID      identifier of Recipe.
	 * @param IngredientRecipeIds the identifier of ingredientRecipe
	 * @return the recipe's DTO with these IngredientRecipe.
	 */
	UserDto addRoles(String id, Set<String> roleIds);
	
	/**
	 * Removal of client roles. Identifiers of roles not found
     * are ignored.
	 * 
	 * @param id The Recipe identifier.
     * @param IngredientRecipeIds The IngredientRecipe identifiers
     * @return the recipe's DTO with these ingredientRecipe.
	 * 
	 */
	UserDto removeRoles(String id, Set<String> roleIds);
}
	
	


