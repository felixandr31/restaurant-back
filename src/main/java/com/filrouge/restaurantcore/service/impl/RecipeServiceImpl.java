package com.filrouge.restaurantcore.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IIngredientRecipeRepository;
import com.filrouge.restaurantcore.dao.IIngredientRepository;
import com.filrouge.restaurantcore.dao.IRecipeRepository;
import com.filrouge.restaurantcore.dto.IngredientRecipeDto;
import com.filrouge.restaurantcore.dto.RecipeDto;
import com.filrouge.restaurantcore.entity.Ingredient;
import com.filrouge.restaurantcore.entity.IngredientRecipe;
import com.filrouge.restaurantcore.entity.Recipe;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IRecipeService;
import com.filrouge.restaurantcore.util.MessagesUtil;
import com.filrouge.restaurantcore.validator.RecipeValidator;


@Service
public class RecipeServiceImpl implements IRecipeService {

	private static final MessagesUtil MESSAGE_UTILS = MessagesUtil.getInstance("message");

	// DAOs

	private IRecipeRepository recipeRepository;
	private IIngredientRepository ingredientRepository;
	private IIngredientRecipeRepository ingredientRecipeRepository;

	/**
	 * Constructor
	 * 
	 * @param userepository The DTO of user
	 */

	public RecipeServiceImpl(IRecipeRepository recipeRepository, IIngredientRepository ingredientRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.ingredientRecipeRepository = ingredientRecipeRepository;
	}

	@Override
	public RecipeDto save(RecipeDto dto) {

		return RecipeDto.fromEntity(recipeRepository.save(RecipeDto.toEntity(dto)));
	}

	@Override
	public RecipeDto update(final RecipeDto dto) {
		List<String> errors = RecipeValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le Recipe n'est pas valide", ErrorCodes.USER_NOT_VALID, errors);
		}

		Optional<Recipe> optionalRecipe = recipeRepository.findById(dto.getId());

		if (!optionalRecipe.isPresent()) {
			throw new InvalidEntityException("Le Recipe n'existe pas", ErrorCodes.USER_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Recipe toUpdate = optionalRecipe.get();
		toUpdate.setCraftingPrice(dto.getCraftingPrice());
		toUpdate.setSellingPrice(dto.getSellingPrice());

		return RecipeDto.fromEntity(recipeRepository.save(toUpdate));
	}

	@Override
	public Optional<RecipeDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(recipeRepository.findById(id).map(RecipeDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Recipe avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.RECIPE_NOT_FOUND));
	}

	@Override
	public List<RecipeDto> findAll() {
		return recipeRepository.findAll().stream().map(RecipeDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public void deleteById(String id) {
		if (id == null) {
			return;
		}
		recipeRepository.deleteById(id);

	}

	@Override
	public RecipeDto removeRoles(String id, Set<String> ingredientIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RecipeDto addIngredientRecipe(String idRecipe, Set<IngredientRecipeDto>ingredientRecipeDto) {
		Optional<Recipe> optionalRecipe = recipeRepository.findById(idRecipe);
		if (!optionalRecipe.isPresent()) {
			throw new InvalidEntityException("Le Recipe n'existe pas", ErrorCodes.RECIPE_NOT_VALID);
		}
		Recipe toUpdate = optionalRecipe.get();

		Set<IngredientRecipe> toAdd = new HashSet<IngredientRecipe>();

		for (final IngredientRecipeDto ingRecipeDto : ingredientRecipeDto) {
			String id = ingRecipeDto.getIngredient().getId();

			final Optional<Ingredient> ingredient = ingredientRepository.findById(id);
			if (ingredient.isPresent()) {
				IngredientRecipe ing = new IngredientRecipe();
				//ing.setIngredient(ingredient);
				ing.setQuantity(ingRecipeDto.getQuantity());
				ingredientRecipeRepository.save(ing);
				toAdd.add(ing);
			}

		}

		toUpdate.getIngredientsRecipe().addAll(toAdd);
		return RecipeDto.fromEntity(recipeRepository.save(toUpdate));
	}
	
	@Override
	public List<RecipeDto> findByName(String name) {
		List<Recipe> recipeFind = recipeRepository.findByName(name);
		return recipeFind.stream().map(RecipeDto::fromEntity).collect(Collectors.toList());
	}
	
	

}
