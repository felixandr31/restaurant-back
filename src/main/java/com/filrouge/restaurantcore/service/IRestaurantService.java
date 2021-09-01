package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

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
}
