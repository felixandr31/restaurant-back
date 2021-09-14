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

import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.RecipeDto;
import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.service.IRecipeService;
import com.filrouge.restaurantcore.service.IUserService;

/**
 * REST recipe management services.
 * 
 * @author Hermann
 *
 */
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/recipe/*")
public class RecipeController {
	

	@Autowired
	IRecipeService recipeService;

	/**
	 * Search all Recipes.
	 * 
	 * @return Recipes of the DTO.
	 */
	@GetMapping(value = "/recipes")
	public ResponseEntity<Collection<RecipeDto>> findAll() {
		Collection<RecipeDto> recipes = recipeService.findAll();
		return new ResponseEntity<Collection<RecipeDto>>(recipes, HttpStatus.OK);
	}
	
	/**
	 * creation of a Recipe with their roles if they exist.
	 * 
	 * @param RecipeDto the recipe to create.
	 * @return Created recipe.
	 */
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<RecipeDto> save(@RequestBody RecipeDto recipeDto) {

		RecipeDto recipeCreated = recipeService.save(recipeDto);
		Optional<RecipeDto> recipeFind = recipeService.findById(recipeCreated.getId());
		return new ResponseEntity<RecipeDto>(recipeFind.get(), HttpStatus.CREATED);
	}
	
	@GetMapping("/{name}")
	public ResponseEntity<List<RecipeDto>> findByName(@PathVariable String name) {
		List<RecipeDto> recipes = recipeService.findByName(name);
		return new ResponseEntity<List<RecipeDto>>(recipes, HttpStatus.OK);
	}
	
	@GetMapping("recipeid/{id}")
	public ResponseEntity<Optional<RecipeDto>> findById(@PathVariable("id") String id) {
		Optional<RecipeDto> recipeIdFind = recipeService.findById(id);
		return new ResponseEntity<Optional<RecipeDto>>(recipeIdFind , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable("id") String id) {
		recipeService.deleteById(id);
	}
	
	/**
	 * Mise à jour d'un recipe (sans ces associations)
	 * 
	 * @param id      l'identifiant du Recipe
	 * @param recipeDto les données à mettre à jour
	 * @return le recipe mis à jour
	 */
	@PutMapping("/update/{id}")
	@Transactional
	public ResponseEntity<RecipeDto> update(@PathVariable String id, @RequestBody RecipeDto recipeDto) {

		recipeDto.setId(id);
		RecipeDto recipeUpdate = recipeService.update(recipeDto);
		return new ResponseEntity<RecipeDto>(recipeUpdate, HttpStatus.CREATED);
	}
	
	@PostMapping("/addingredientrecipes/{id}")
	@Transactional
	public ResponseEntity<RecipeDto> addIngredientRecipe(@PathVariable String id, @RequestBody Set<IngredientRecipeDto> ingredientRecipeDto) {

		RecipeDto recipeUpdate = recipeService.addIngredientRecipes(id, ingredientRecipeDto);
		return new ResponseEntity<RecipeDto>(recipeUpdate, HttpStatus.CREATED);
	}
	

	}
	
	/**
	 * Removal of a ingredientRecipe from the client.
	 * 
	 * @param id      the client's identifier
	 * @param roleIds the identifiers of the roles to be deleted
	 * @return the updated client
	 */
	@PostMapping("/removeingredientrecipes/{id}")
	@Transactional
	public ResponseEntity<RecipeDto> removeIngredientRecipes(@PathVariable String id, @RequestBody Set<String> ingredientRecipeIds) {

		RecipeDto recipeUpdate = recipeService.removeIngredientRecipes(id, ingredientRecipeIds);
		return new ResponseEntity<RecipeDto>(recipeUpdate, HttpStatus.CREATED);
	}

}
