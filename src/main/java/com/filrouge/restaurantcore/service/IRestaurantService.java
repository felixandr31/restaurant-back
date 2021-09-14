package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.filrouge.restaurantcore.dto.RestaurantDto;

/**
 * Services métier de gestion des restaurants.
 * 
 * @author sslimani
 *
 */
public interface IRestaurantService {
	/**
	 * Creation of restaurant.
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	RestaurantDto save(RestaurantDto dto);

	RestaurantDto addUsers(String id, final Set<String> employeeIds);

	RestaurantDto removeUsers(String id, Set<String> userIds);

	RestaurantDto removeStocks(String id, Set<String> stockIds);

	RestaurantDto addStocks(String id, final Set<String> stockIds);

	RestaurantDto addTables(String id, final Set<String> tableIds);

	RestaurantDto removeTables(String id, final Set<String> tableIds);

	RestaurantDto addRecipes(String id, final Set<String> recipeIds);

	RestaurantDto removeRecipes(String id, Set<String> recipeIds);

	/**
	 * Update d'un restaurant sans ces associations.
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	RestaurantDto update(RestaurantDto dto);

	/**
	 * Find a restaurant by his id.
	 * 
	 * @param id L'identifiant.
	 * @return le DTO trouvée selon son identifiant.
	 */
	Optional<RestaurantDto> findById(String id);

	List<RestaurantDto> findByName(String name);
//	List<RestaurantDto> findByUserId(String id);

	/**
	 * Recherche de l'ensemble des restaurants.
	 * 
	 * @return les DTOs des retaurants.
	 */
	List<RestaurantDto> findAll();

	/**
	 * Suppression d'un restaurant par son identifiant.
	 * 
	 * @param id L'identifiant
	 */
	void deleteById(String id);
	
	RestaurantDto findByTableId(String id);
}
