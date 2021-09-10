package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.filrouge.restaurantcore.dto.BookingDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.User;


public interface IUserService {
	
	/**
	 * Création of a client.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the creation
	 */
		UserDto save(UserDto dto);

	

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
	List<UserDto>findAll();

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
	 * @return 
	 * @return 
	 */
	 void deleteUserById(String id);

	/**
	 * Add roles of the client. Identifiers of roles not found
     * are ignored
	 * 
	 * @param ID      identifier of client.
	 * @param roleIds the identifier of Roles
	 * @return the client's DTO  DTO with these roles
	 */
	UserDto addRoles(String id, Set<String> roleIds);
	
	/**
	 * Add friends of the client. Identifiers of friends not found
     * are ignored
	 * 
	 * @param ID      identifier of client.
	 * @param friendsIds the identifier of friends
	 * @return the friends's DTO with these Friend.
	 */
	UserDto addFriends(String id, Set<String> FriendsIds);
	
	/**
	 * Removal of client roles. Identifiers of roles not found
     * are ignored.
	 * 
	 * @param id The client identifier.
     * @param roleIds The role identifiers
     * @return the clients's DTO with these roles.
	 * 
	 */
	UserDto removeRoles(String id, Set<String> roleIds);
	
	/**
	 * remove friends of the client. Identifiers of friends not found
     * are ignored
	 * 
	 * @param ID      identifier of client.
	 * @param friendsIds the identifier of friends
	 * @return the friends's DTO with these Friend.
	 */
	UserDto removeFriends(String id, Set<String> roleIds);
	
	
	/**
	 * Mise à jour du role 
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	UserDto update(UserDto dto);
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	
	List<UserDto> findByEmail(String email);
	  
	/**
	 * 
	 * @param lastname
	 * @param password
	 * @return
	 */
	List<UserDto> findByLastNameAndPassword( String lastName, String password);
	  
	  /**
	   * 
	   * @param firstname
	   * @param lastname
	   * @return
	   */
	List<UserDto> findByFirstNameAndLastName(String firstName, String lastName);

	
}
	
	



