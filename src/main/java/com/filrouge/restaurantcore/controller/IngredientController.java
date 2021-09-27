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
import com.filrouge.restaurantcore.service.IIngredientService;

/**
 * Services REST de gestion des ingredients.
 * 
 * @author FAndre
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200", "http://192.168.20.109:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/ingredient/*")
public class IngredientController {
	
	@Autowired
	IIngredientService ingredientService;
	
	
	/**
	 * Search all Ingredients.
	 * 
	 * @return Ingredients of the DTO.
	 */
	@GetMapping(value = "/ingredients")
	public ResponseEntity<Collection<IngredientDto>> findAll() {
		Collection<IngredientDto> ingredients = ingredientService.findAll();
		return new ResponseEntity<Collection<IngredientDto>>(ingredients, HttpStatus.OK);
	}
	
	@GetMapping(value = "/ingredients/{id}")
	public ResponseEntity<Optional<IngredientDto>> findById(@PathVariable String id) {
		Optional<IngredientDto> ingredients = ingredientService.findById(id);
		return new ResponseEntity<Optional<IngredientDto>>(ingredients, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<IngredientDto> save(@RequestBody IngredientDto ingredientDto) {
		IngredientDto ingredientCreated = ingredientService.save(ingredientDto);
		return new ResponseEntity<IngredientDto>(ingredientCreated, HttpStatus.CREATED);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public void deleteIngredientById(@PathVariable("id") String id){
	        this.ingredientService.deleteIngredientById(id);
	    }
	 
	 /**
		 * Mise à jour d'un administrateur (sans ces associations)
		 * @param id l'identifiant du Ingredient
		 * @param ingredientDto les données à mettre à jour
		 * @return l'administrateur mis à jour
		 */
		@PutMapping("/update/{id}")
		@Transactional
		public ResponseEntity<IngredientDto> update(@PathVariable String id,
				@RequestBody IngredientDto ingredientDto) {

			ingredientDto.setId(id);
			IngredientDto ingredientUpdate = ingredientService.update(ingredientDto);
			return new ResponseEntity<IngredientDto>(ingredientUpdate, HttpStatus.CREATED);
		}
		
		
	 
	 
}
