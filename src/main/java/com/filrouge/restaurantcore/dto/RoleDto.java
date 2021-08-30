package com.filrouge.restaurantcore.dto;



import com.filrouge.restaurantcore.entity.Role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
	
	/**
	 * L'identifiant.
	 */
	private String id;

	/**
	 * Le nom
	 */
	private String name;

	/**
	 * Transforme l'entité en un DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static RoleDto fromEntity(Role role) {
		if (role == null) {
			return null;
		}
		return RoleDto.builder().id(role.getId()).name(role.getRoleName()).build();
	}

	/**
	 * Transforme le DTO en une entité.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Role toEntity(RoleDto dto) {
		if (dto == null) {
			return null;
		}
		final Role role = new Role();
		role.setId(dto.getId());
		role.setRoleName(dto.getName());
		return role;
	}

}
