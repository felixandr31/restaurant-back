package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.service.IRestaurantService;
import com.filrouge.restaurantcore.service.IUserService;

/**
 * Services REST de gestion des restaurants.
 * 
 * @author sslimani
 * 
 */

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200", "http://192.168.20.109:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/restaurant/*")
public class RestaurantController {
	@Autowired
	IRestaurantService restaurantService;
	IUserService userService;

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

//	@GetMapping(value = "/usersrestaurant/{userId}")
//	public ResponseEntity<Collection<RestaurantDto>> findByUserId(@PathVariable ("userId") String userId){
//		//Optional<UserDto> userDto = userService.findById(userDto.);
//		String
//		List<RestaurantDto> restaurantsOfUserX = restaurantService.findByUserId(employees.id)
//		return new ResponseEntity<Collection<RestaurantDto>>(restaurantsOfUserX, HttpStatus.OK);
//	}

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

	@GetMapping("restaurantsid/{id}")
	public ResponseEntity<Optional<RestaurantDto>> findById(@PathVariable("id") String id) {
		Optional<RestaurantDto> restaurantfind = restaurantService.findById(id);
		return new ResponseEntity<Optional<RestaurantDto>>(restaurantfind, HttpStatus.OK);
	}

	@GetMapping("restaurantsname/{name}")
	public ResponseEntity<List<RestaurantDto>> findByName(@PathVariable("name") String name) {
		List<RestaurantDto> restaurantfind = restaurantService.findByName(name);
		return new ResponseEntity<List<RestaurantDto>>(restaurantfind, HttpStatus.OK);
	}

	/**
	 * Update of restaurant (without these associations)
	 * 
	 * @param id            restaurant identifier
	 * @param restaurantDto the data to update
	 * @return the updated restaurant
	 */
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> update(@PathVariable String id, @RequestBody RestaurantDto restaurantDto) {

		restaurantDto.setId(id);
		// TODO users
		// stars
		RestaurantDto restaurantUpdate = restaurantService.update(restaurantDto);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteRestaurantById(@PathVariable("id") String id) {
		this.restaurantService.deleteById(id);
	}

	/**
	 * Adding a role to the User.
	 * 
	 * @param id      the user's
	 * @param roleIds the identifiers of the roles to add
	 * @return updated User
	 */
	@PostMapping("/addusers/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addUsers(@PathVariable String id, @RequestBody Set<String> employeesIds) {

		RestaurantDto restaurantUpdate = restaurantService.addUsers(id, employeesIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	/**
	 * Adding a stock to the restauarant
	 * 
	 * @param id
	 * @param stocksIds
	 * @return
	 */
	@PostMapping("/addstocks/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addStocks(@PathVariable String id, @RequestBody Set<String> stocksIds) {

		RestaurantDto restaurantUpdate = restaurantService.addStocks(id, stocksIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	/**
	 * Removal of a user from a restaurant.
	 * 
	 * @param id      the restaurant's identifier
	 * @param userIds the identifiers of the users to be deleted
	 * @return the updated restaurant
	 */
	@DeleteMapping("/removeusers/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> removeUsers(@PathVariable String id, @RequestBody Set<String> userIds) {

		RestaurantDto restaurantUpdate = restaurantService.removeUsers(id, userIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	@DeleteMapping("/removestocks/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> removeStocks(@PathVariable String id, @RequestBody Set<String> stockIds) {

		RestaurantDto restaurantUpdate = restaurantService.removeStocks(id, stockIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	/**
	 * Adding a table to the Restaurant.
	 * 
	 * @param id      the user's
	 * @param roleIds the identifiers of the roles to add
	 * @return updated User
	 */
	@PostMapping("/addtables/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addTable(@PathVariable String id, @RequestBody Set<String> tablesIds) {

		RestaurantDto restaurantUpdate = restaurantService.addTables(id, tablesIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	@DeleteMapping("/removetables/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> removeTables(@PathVariable String id, @RequestBody Set<String> tableIds) {

		RestaurantDto restaurantUpdate = restaurantService.removeStocks(id, tableIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	@PostMapping("/addrecipes/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> addRecipe(@PathVariable String id, @RequestBody Set<String> recipesIds) {

		RestaurantDto restaurantUpdate = restaurantService.addRecipes(id, recipesIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}

	@DeleteMapping("/removerecipes/{id}")
	@Transactional
	public ResponseEntity<RestaurantDto> removerecipes(@PathVariable String id, @RequestBody Set<String> recipeIds) {

		RestaurantDto restaurantUpdate = restaurantService.removeRecipes(id, recipeIds);
		return new ResponseEntity<RestaurantDto>(restaurantUpdate, HttpStatus.CREATED);
	}
	
	@GetMapping("/tables/{id}")
	public ResponseEntity<RestaurantDto> findByTableId(@PathVariable String id) {
		RestaurantDto restaurant = restaurantService.findByTableId(id);
		return new ResponseEntity<RestaurantDto>(restaurant, HttpStatus.OK);
	}

}
