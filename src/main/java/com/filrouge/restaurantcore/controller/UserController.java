package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IUserService;




/**
 * REST role management services.
 * 
 * @author Hermann
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	IUserService userService;

	/**
	 * Search all Clients.
	 * 
	 * @return Clients of the DTO.
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<Collection<UserDto>> findAll() {
 		Collection<UserDto> users = userService.findAll();
		return new ResponseEntity<Collection<UserDto>>(users, HttpStatus.FOUND);
	}

	/**
	 * création d'un utilisateur avec leurs roles si existe;
	 * @param UserDto the client to create.
	 * @return Created Client.
	 */
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {

		UserDto userCreated = userService.save(userDto);
		Optional<UserDto> userFind = userService.findById(userCreated.getId());
		return new ResponseEntity<UserDto>(userFind.get(), HttpStatus.CREATED);
	}

	/**
	 * Update of an user (without these associations)
	 * @param  id the user's identifier
	 * @param userDto the data to update
	 * @return the updated user
	 */
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@PathVariable String id,
			@RequestBody UserDto userDto) {

		userDto.setId(id);
		UserDto userUpdate = userService.update(userDto);
		return new ResponseEntity<UserDto>(userUpdate, HttpStatus.CREATED);
	}

	/**
	 * Adding a role to the User.
	 * @param id the user's 
	 * @param roleIds the identifiers of the roles to add
	 * @return updated User
	 */
	@PostMapping("/addroles/{id}")
	@Transactional
	public ResponseEntity<UserDto> addRoles(@PathVariable String id,
			@RequestBody Set<String> roleIds) {

		UserDto userUpdate = userService.addRoles(id, roleIds);
		return new ResponseEntity<UserDto>(userUpdate, HttpStatus.CREATED);
	}

	/**
	 * Removal of a role from the client.
	 * @param id the client's identifier
	 * @param roleIds the identifiers of the roles to be deleted
	 * @return the updated client
	 */
	@PostMapping("/removeroles/{id}")
	@Transactional
	public ResponseEntity<UserDto> removeRoles(@PathVariable String id,
			@RequestBody Set<String> roleIds) {

		UserDto userUpdate = userService.removeRoles(id, roleIds);
		return new ResponseEntity<UserDto>(userUpdate, HttpStatus.CREATED);
	}
	
	/**
	 * Adding a friend to the User.
	 * @param id the user's 
	 * @param friendIds the identifiers of the friends to add
	 * @return updated User
	 */
	@PostMapping("/addfriends/{id}")
	@Transactional
	public ResponseEntity<UserDto> addFriends(@PathVariable String id,
			@RequestBody Set<String> friendIds) {

		UserDto friendUpdate = userService.addFriends(id, friendIds);
		return new ResponseEntity<UserDto>(friendUpdate, HttpStatus.CREATED);
	}
	
	/**
	 * Removal of a friend from the client.
	 * @param id the client's identifier
	 * @param friendIds the identifiers of the friends to be deleted
	 * @return the updated client
	 */
	@PostMapping("/removefriends/{id}")
	@Transactional
	public ResponseEntity<UserDto> removeFriends(@PathVariable String id,
			@RequestBody Set<String> friendIds) {

		UserDto friendUpdate = userService.removeFriends(id, friendIds);
		return new ResponseEntity<UserDto>(friendUpdate, HttpStatus.CREATED);
	}
}

