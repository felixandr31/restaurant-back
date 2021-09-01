package com.filrouge.restaurantcore.dto;

import java.math.BigInteger;



import com.filrouge.restaurantcore.entity.Recipe;

import lombok.Builder;
import lombok.Data;

/**
 * Recipe Object Transfer (DTO).
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
	
	//@Builder.Default
	//@JsonIgnore
	//private Map<String,Integer> ingredients = new HashMap<String,Integer>();

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
		
		return RecipeDto.builder().id(entity.getId()).craftingPrice(entity.getCraftingPrice())
				.sellingPrice(entity.getSellingPrice()).build();

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
		return recipe;
	}

}
