package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IIngredientRecipeRepository;
import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.entity.IngredientRecipe;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IIngredientRecipeService;
import com.filrouge.restaurantcore.validator.IngredientRecipeValidator;

/**
 * Services métier de gestion des ingredients d'une recette.
 * 
 * @author FAndre
 *
 */
@Service
public class IngredientRecipeServiceImpl implements IIngredientRecipeService {
	
	private IIngredientRecipeRepository ingredientRecipeRepository;
	
	/**
	 * Constructor.
	 * @param ingredientRepository the roles DTO.
	 */
	public IngredientRecipeServiceImpl(IIngredientRecipeRepository ingredientRecipeRepository) {
		super();
		this.ingredientRecipeRepository = ingredientRecipeRepository;
	}

	@Override
	public List<IngredientRecipeDto> findAll() {
		
		List<IngredientRecipe> list = ingredientRecipeRepository.findAll();
		List<IngredientRecipeDto> list2= list.stream().map(IngredientRecipeDto::fromEntity).collect(Collectors.toList());
		return list2;
	}

	@Override
	public Optional<IngredientRecipeDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(ingredientRecipeRepository.findById(id).map(IngredientRecipeDto::fromEntity)).orElseThrow(() -> new EntityNotFoundException(
				"Aucun Ingredient avec l'ID = " + id + " n' est trouve dans la BDD", ErrorCodes.ROLE_NOT_FOUND));
	}
	
	
	
	@Override
	public IngredientRecipeDto save(IngredientRecipeDto dto) {
		return IngredientRecipeDto.fromEntity(ingredientRecipeRepository.save(IngredientRecipeDto.toEntity(dto)));
	}
	
	

	@Override
	public IngredientRecipeDto update(IngredientRecipeDto ingredientRecipeDto) {
		List<String> errors = IngredientRecipeValidator.validate(ingredientRecipeDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID,
					errors);
		}

		Optional<IngredientRecipe> optionalIngredientRecipe = ingredientRecipeRepository.findById(ingredientRecipeDto.getId());

		if (!optionalIngredientRecipe.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		IngredientRecipe toUpdate = optionalIngredientRecipe.get();
		toUpdate.setQuantity(ingredientRecipeDto.getQuantity());
//		toUpdate.setIngredient(ingredientRecipeDto.getIngredient()); !!How to IngredientDto and Ingredient

		return IngredientRecipeDto.fromEntity(ingredientRecipeRepository.save(toUpdate));
	}

	@Override
	public void deleteIngredientRecipeById(String id) {
		if (id == null) {
			return;
		}
		ingredientRecipeRepository.deleteById(id);
		
	}


	
	
	
	

}
