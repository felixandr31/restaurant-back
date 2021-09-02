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
import com.filrouge.restaurantcore.service.IClientService;



/**
 * REST role management services.
 * 
 * @author Hermann
 *
 */
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/user/*")
public class UserController {

	@Autowired
	IClientService clientService;

	/**
	 * Search all Clients.
	 * 
	 * @return Clients of the DTO.
	 */
	@GetMapping(value = "/users")
	public ResponseEntity<Collection<UserDto>> findAll() {
		Collection<UserDto> clients = clientService.findAll();
		return new ResponseEntity<Collection<UserDto>>(clients, HttpStatus.FOUND);
	}

	/**
	 * création d'un utilisateur avec leurs roles si existe;
	 * @param UserDto the client to create.
	 * @return Created Client.
	 */
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {

		UserDto userCreated = clientService.save(userDto);
		Optional<UserDto> userFind = clientService.findById(userCreated.getId());
		return new ResponseEntity<UserDto>(userFind.get(), HttpStatus.CREATED);
	}

	/**
	 * Update of an administrator (without these associations)
	 * @param  id the client's identifier
	 * @param userDto the data to update
	 * @return the updated Client
	 */
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<UserDto> update(@PathVariable String id,
			@RequestBody UserDto userDto) {

		userDto.setId(id);
		UserDto clientUpdate = clientService.update(userDto);
		return new ResponseEntity<UserDto>(clientUpdate, HttpStatus.CREATED);
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

		UserDto clientUpdate = clientService.addRoles(id, roleIds);
		return new ResponseEntity<UserDto>(clientUpdate, HttpStatus.CREATED);
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

		UserDto clientUpdate = clientService.removeRoles(id, roleIds);
		return new ResponseEntity<UserDto>(clientUpdate, HttpStatus.CREATED);
	}
}

