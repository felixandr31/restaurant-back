package com.filrouge.restaurantcore.dto;

import com.filrouge.restaurantcore.entity.Role;


import lombok.Builder;
import lombok.Data;

/**
 * Role Object Transfer (DTO).
 * 
 * @author Hermann
 *
 */

@Data
@Builder
public class RoleDto {

	/**
	 * ID
	 */
	private String id;

	/**
	 * Le nom
	 */
	private String name;

	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static RoleDto fromEntity(Role role) {
		if (role == null) {
			return null;
		}
		return RoleDto.builder().id(role.getId()).name(role.getName()).build();
	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto
	 * @return entity of dto
	 */
	public static Role toEntity(RoleDto dto) {
		if (dto == null) {
			return null;
		}
		final Role role = new Role();
		role.setId(dto.getId());
		role.setName(dto.getName());
		return role;
	}

}
