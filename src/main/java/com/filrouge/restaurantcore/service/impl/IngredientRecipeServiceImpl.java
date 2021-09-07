package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IIngredientRecipeRepository;
import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.service.IIngredientRecipeService;

/**
 * Services métier de gestion des ingredients d'une recette.
 * 
 * @author FAndre
 *
 */
@Service
public class IngredientRecipeServiceImpl implements IIngredientRecipeService {
	
	private IIngredientRecipeRepository ingredientRecipeRepository;

	@Override
	public List<IngredientRecipeDto> findAll() {
		return ingredientRecipeRepository.findAll().stream().map(IngredientRecipeDto::fromEntity).collect(Collectors.toList());
	}

}
