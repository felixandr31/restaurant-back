package com.filrouge.restaurantcore.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
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

}
