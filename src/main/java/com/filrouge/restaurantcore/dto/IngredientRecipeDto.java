package com.filrouge.restaurantcore.dto;

import com.filrouge.restaurantcore.entity.IngredientRecipe;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngredientRecipeDto {

	private String id;

	/**
	 * quantity of ingredient
	 */

	private Integer quantity;

	/**
	 * Ingredient Embedded
	 */

	private IngredientDto ingredient;

	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity
	 * @return DTO of entity
	 */
	public static IngredientRecipeDto fromEntity(IngredientRecipe entity) {
		if (entity == null) {
			// TODO throw an exception
			return null;
		}
		return IngredientRecipeDto.builder().id(entity.getId()).quantity(entity.getQuantity())
				.ingredient(IngredientDto.fromEntity(entity.getIngredient())).build();

	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto
	 * @return entity of DTO
	 */

	public static IngredientRecipe toEntity(IngredientRecipeDto dto) {
		if (dto == null) {
			// TODO THROW AN EXCEPTION
			return null;
		}
		final IngredientRecipe ingredientRecipe = new IngredientRecipe();
		ingredientRecipe.setId(dto.getId());
		ingredientRecipe.setQuantity(dto.getQuantity());
		ingredientRecipe.setIngredient(IngredientDto.toEntity(dto.getIngredient()));

		return ingredientRecipe;
	}

}
