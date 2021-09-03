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
		return new ResponseEntity<Collection<RestaurantDto>>(restaurants, HttpStatus.FOUND);
	}

	/**
	 * Créer un restaurant
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
	 * Adding a employee to restaurant.
	 * 
	 * @param id          of Restaurant
	 * @param employeeIds
	 * @return updated Restaurant
	 */
	@PostMapping("/addusers/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addUsers(@PathVariable String id,
			@RequestBody Set<String> usersIds) {

		RestaurantDto restaurantUpdate = restaurantService.addUsers(id, usersIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}
}
