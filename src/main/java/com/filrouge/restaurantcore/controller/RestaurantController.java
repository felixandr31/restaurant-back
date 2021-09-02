package com.filrouge.restaurantcore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
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

	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<RestaurantDto> save(@RequestBody RestaurantDto restaurantDto) {

		RestaurantDto restaurantCreated = restaurantService.save(restaurantDto);
		return new ResponseEntity<RestaurantDto>(restaurantCreated, HttpStatus.CREATED);
	}
}
