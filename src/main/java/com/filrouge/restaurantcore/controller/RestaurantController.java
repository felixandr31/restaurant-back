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

import com.filrouge.restaurantcore.dto.RestaurantDto;

import com.filrouge.restaurantcore.service.IRestaurantService;

/**
 * Services REST de gestion des restaurants.
 * 
 * @author sslimani
 *
 */
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/restaurant/*")
public class RestaurantController {
	@Autowired
	IRestaurantService restaurantService;

	/**
	 * Search all Restaurants.
	 * 
	 * @return Restaurants of the DTO.
	 */
	@GetMapping(value = "/restaurants")
	public ResponseEntity<Collection<RestaurantDto>> findAll() {
		Collection<RestaurantDto> restaurants = restaurantService.findAll();
		return new ResponseEntity<Collection<RestaurantDto>>(restaurants, HttpStatus.OK);
	}

	/**
	 * Create restaurant
	 * 
	 * @param restaurantDto
	 * @return
	 */
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<RestaurantDto> save(@RequestBody RestaurantDto restaurantDto) {

		RestaurantDto restaurantCreated = restaurantService.save(restaurantDto);
		return new ResponseEntity<RestaurantDto>(restaurantCreated, HttpStatus.CREATED);
	}

	/**
	 * Update of restaurant (without these associations)
	 * @param  id restaurant identifier
	 * @param restaurantDto the data to update
	 * @return the updated restaurant
	 */
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> update(@PathVariable String id,
			@RequestBody RestaurantDto restaurantDto) {

		restaurantDto.setId(id);
		//TODO users
		//stars
		RestaurantDto restaurantUpdate = restaurantService.update(restaurantDto);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}
    
	
	/**
	 * Adding a role to the User.
	 * @param id the user's 
	 * @param roleIds the identifiers of the roles to add
	 * @return updated User
	 */
	@PostMapping("/addusers/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addUsers(@PathVariable String id,
			@RequestBody Set<String> userIds) {

		RestaurantDto restaurantUpdate = restaurantService.addUsers(id, userIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}
	/**
	 * Removal of a user from a restaurant.
	 * @param id the restaurant's identifier
	 * @param userIds the identifiers of the users to be deleted
	 * @return the updated restaurant
	 */
	@PostMapping("/removeusers/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> removeUsers(@PathVariable String id,
			@RequestBody Set<String> userIds) {

		RestaurantDto restaurantUpdate = restaurantService.removeUsers(id, userIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}
	

	// ajout/ suppression recette 
	// ajout/ suppression table
	// ajout/ suppression stock
	// 
}
