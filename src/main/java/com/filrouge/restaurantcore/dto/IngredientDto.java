package com.filrouge.restaurantcore.dto;

import java.math.BigDecimal;

import com.filrouge.restaurantcore.entity.Ingredient;

import lombok.Builder;
import lombok.Data;

/**
 * Ingredient Object Transfer (DTO).
 * 
 * @author Hermann
 *
 */

@Data
@Builder
public class IngredientDto {

	private String id;

	private String name;

	private BigDecimal price;

	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity
	 * @return DTO of entity
	 */

	public static IngredientDto fromEntity(Ingredient entity) {
		if (entity == null) {
			// TODO THROW AN EXCEPTION
			return null;
		}
		return IngredientDto.builder().id(entity.getId()).name(entity.getName()).price(entity.getPrice()).build();
	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto
	 * @return entity of DTO
	 */

	public static Ingredient toEntity(IngredientDto dto) {
		if (dto == null) {
			// TODO THROW AN EXCEPTION
			return null;
		}
		Ingredient ingredient = new Ingredient();
		ingredient.setId(dto.getId());
		ingredient.setName(dto.getName());
		ingredient.setPrice(dto.getPrice());
		return ingredient;
	}

}
