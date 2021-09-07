package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;



import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;

public interface IRoleService {

	/**
	 * Creation of a role.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */

	RoleDto save(RoleDto dto);

	/**
	 * Search for a role by its identifier.
	 * 
	 * @param id the identifier.
	 * @return found according to its identifier.
	 */
	
	Optional<RoleDto> findById(String id);

	/**
	 * search all roles.
	 * 
	 * @return the Clients DTOs.
	 */
	List<RoleDto> findAll();

	/**
	 * Delete role of Client by its ID.
	 * 
	 * @param id L'identifiant
	 */
	void deleteRoleById(String id);

/**
 * Recherche Role par son nom
 * @param name
 * @return
 */

	RoleDto findByName(String name);
	
	/**
	 * Mise à jour du Role 
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	RoleDto update(RoleDto dto);
}
	


