package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.TableDto;

/**
 * Services métier de gestion des tables.
 * 
 * @author FAndre
 *
 */
public interface ITableService {
	
	/**
	 * Research of all ingredients.
	 * 
	 * @return the DTOs of the ingredient.
	 */
	List<TableDto> findAll();
	
	/**
	 * 
	 * Search an table by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<TableDto> findById(String id);
	
	/**
	 * Removal of an table. 
	 * @param id The table identifier.
     * @return the ingredient's DTO.
	 * 
	 */
	void deleteTableById(String id);
	
	/**
	 * Création of a table.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the table
	 */
	TableDto save(TableDto dto);
	
	/**
	 * Mise à jour du Table 
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	TableDto update(TableDto tableDto);

}
