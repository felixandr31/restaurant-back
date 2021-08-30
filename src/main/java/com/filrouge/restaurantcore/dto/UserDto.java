package com.filrouge.restaurantcore.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
	
	
	
	 private String id;
	 
	
		private String firstName;
		
		private String lastName;
		
		private String email;
		
		@JsonIgnore
		private List<Role> Roles;
		
		/**
		 * Transform the entity into a DTO.
		 * 
		 * @param entity 
		 * @return DTO of entity
		 */
			public static UserDto fromEntity(User entity) {
				if(entity == null) {
					//TODO throw an exception
					return null;
				}
				return UserDto.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
						.email(entity.getEmail()).build();
						
			}
			
			
			/**
			 * Transform the DTO into entity.
			 * 
			 * @param dto 
			 * @return entity of DTO
			 */
			
			public static User fromEntity(UserDto dto) {
				if(dto == null) {
					//TODO throw an exception
					return null;
					
				}
				final User user = new User();
				
				user.setId(dto.getId());
				user.setFirstName(dto.getFirstName());
				user.setLastName(dto.getLastName());
				user.setEmail(dto.getEmail());
				return user;
			}
		
		

}
