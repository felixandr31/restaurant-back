package com.filrouge.restaurantcore.dto;

import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;

/**
 * User Object Transfer (DTO).
 * @author Hermann
 *
 */

@Data
@Builder
public class UserDto {
	
	
	
	 private String id;
	 
	
		private String firstName;
		
		private String lastName;
		
		private String password;
		
		private String email;
		
		//@JsonIgnore
		@Builder.Default
		private List<RoleDto> roles = new ArrayList<RoleDto>();
		
		//@JsonIgnore
		@Builder.Default
		private List<UserDto> friends = new ArrayList<UserDto>();
		
		
		
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
				final List<RoleDto> rolesDto = new ArrayList<RoleDto>(entity.getRoles().size());
				for (final Role role : entity.getRoles()) {
					rolesDto.add(RoleDto.fromEntity(role));
				}
				final List<UserDto> friendsDto = new ArrayList<UserDto>(entity.getFriends().size());
				for (final User user : entity.getFriends()) {
					friendsDto.add(UserDto.fromEntity(user));
				}
				return UserDto.builder().id(entity.getId()).firstName(entity.getFirstName()).lastName(entity.getLastName())
						.email(entity.getEmail()).roles(rolesDto).friends(friendsDto).build();
						
			}
			
			
			/**
			 * Transform the DTO into entity.
			 * 
			 * @param dto 
			 * @return entity of DTO
			 */
			
			public static User toEntity(UserDto dto) {
				if(dto == null) {
					//TODO throw an exception
					return null;
					
				}
				final User user = new User();
				
				user.setId(dto.getId());
				user.setFirstName(dto.getFirstName());
				user.setLastName(dto.getLastName());
				user.setPassword(dto.getPassword());
				user.setEmail(dto.getEmail());
				
				final List<Role> roles = dto.getRoles()
				        .stream()
				        .map(RoleDto::toEntity)
				        .collect(Collectors.toList());
				user.setRoles(roles);
				
				final List<User> friends = dto.getFriends()
						.stream()
						.map(UserDto::toEntity)
						.collect(Collectors.toList());
				       
				user.setFriends(friends);
				return user;
			}
		
		

}
