package com.filrouge.restaurantcore.controller;

import java.util.Collection;
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

import com.filrouge.restaurantcore.dto.RoleDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IRoleService;
import com.filrouge.restaurantcore.service.IUserService;




/**
 * REST role management services.
 * 
 * @author Hermann
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/role/*")
public class RoleController {

	@Autowired
	IRoleService roleService;
	IUserService userService;

	@GetMapping(value = "/roles")
	public ResponseEntity<Collection<RoleDto>> findAll() {
		Collection<RoleDto> roles = roleService.findAll();
		return new ResponseEntity<Collection<RoleDto>>(roles, HttpStatus.FOUND);
	}

	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<RoleDto> save(@RequestBody RoleDto roleDto) {

		RoleDto roleCreated = roleService.save(roleDto);
		return new ResponseEntity<RoleDto>(roleCreated, HttpStatus.CREATED);
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
	
	
}
