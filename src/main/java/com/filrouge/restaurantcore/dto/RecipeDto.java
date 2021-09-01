package com.filrouge.restaurantcore.dto;

import java.math.BigInteger;


import java.util.HashSet;

import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.filrouge.restaurantcore.entity.IngredientRecipe;
import com.filrouge.restaurantcore.entity.Recipe;

import lombok.Builder;
import lombok.Data;

/**
 * Recipe Object Transfer (DTO).
 * 
 * @author Hermann
 *
 */

@Data
@Builder
public class RecipeDto {

	private String id;

	private String name;

	private BigInteger craftingPrice;

	private BigInteger sellingPrice;

	@JsonIgnore
	@Builder.Default
	private Set<IngredientRecipeDto> ingredientsRecipe = new HashSet<IngredientRecipeDto>();

	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity
	 * @return DTO of entity
	 */
	public static RecipeDto fromEntity(Recipe entity) {
		if (entity == null) {
			// TODO throw an exception
			return null;
		}

		final Set<IngredientRecipeDto> ingredientsRecipeDto = new HashSet<IngredientRecipeDto>(
				entity.getIngredientsRecipe().size());
		for (final IngredientRecipe ingredientRecipe : entity.getIngredientsRecipe()) {
			ingredientsRecipeDto.add(IngredientRecipeDto.fromEntity(ingredientRecipe));
		}

		return RecipeDto.builder().id(entity.getId()).craftingPrice(entity.getCraftingPrice())
				.sellingPrice(entity.getSellingPrice()).name(entity.getName()).ingredientsRecipe(ingredientsRecipeDto)
				.build();

	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto
	 * @return entity of DTO
	 */
	public static Recipe toEntity(RecipeDto dto) {
		if (dto == null) {
			// TODO throw an exception
			return null;

		}

		final Recipe recipe = new Recipe();

		recipe.setId(dto.getId());
		recipe.setCraftingPrice(dto.getCraftingPrice());
		recipe.setSellingPrice(dto.getSellingPrice());
		recipe.setName(dto.getName());

		final Set<IngredientRecipe> ingredientsRecipe = dto.getIngredientsRecipe().stream()
				.map(IngredientRecipeDto::toEntity).collect(Collectors.toSet());
		recipe.setIngredientsRecipe(ingredientsRecipe);

		return recipe;
	}

}
