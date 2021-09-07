package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.Optional;

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

import com.filrouge.restaurantcore.dto.IngredientDto;
import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IIngredientRecipeService;

/**
 * Services REST de gestion des restaurants.
 * 
 * @author FAndre
 * 
 */

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/ingredientrecipe/*")
public class IngredientRecipeController {
	
	@Autowired
	IIngredientRecipeService ingredientRecipeService;
	
	@GetMapping(value = "/ingredientrecipes")
	public ResponseEntity<Collection<IngredientRecipeDto>> findAll() {
		Collection<IngredientRecipeDto> ingredientRecipes = ingredientRecipeService.findAll();
		return new ResponseEntity<Collection<IngredientRecipeDto>>(ingredientRecipes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/ingredientrecipes/{id}")
	public ResponseEntity<Optional<IngredientRecipeDto>> findById(@PathVariable String id) {
		Optional<IngredientRecipeDto> ingredientRecipes = ingredientRecipeService.findById(id);
		return new ResponseEntity<Optional<IngredientRecipeDto>>(ingredientRecipes, HttpStatus.OK);
	}
	

	 
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<IngredientRecipeDto> save(@RequestBody IngredientRecipeDto ingredientRecipeDto) {
		IngredientRecipeDto ingredientRecipeCreated = ingredientRecipeService.save(ingredientRecipeDto);
		return new ResponseEntity<IngredientRecipeDto>(ingredientRecipeCreated, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<IngredientRecipeDto> update(@PathVariable String id,
			@RequestBody IngredientRecipeDto ingredientRecipeDto) {

		ingredientRecipeDto.setId(id);
		IngredientRecipeDto ingredientRecipeUpdate = ingredientRecipeService.update(ingredientRecipeDto);
		return new ResponseEntity<IngredientRecipeDto>(ingredientRecipeUpdate, HttpStatus.CREATED);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public void deleteIngredientById(@PathVariable("id") String id){
	        this.ingredientRecipeService.deleteIngredientRecipeById(id);
	    }

}
