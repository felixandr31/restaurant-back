package com.filrouge.restaurantcore.dto;

import java.math.BigDecimal;

import com.filrouge.restaurantcore.entity.Stock;

import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from stock (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class StockDto {
	/**
	 * Ingredient
	 */
	private IngredientDto ingredient;
	/**
	 * Quantity
	 */
	private BigDecimal quantity;

	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static StockDto fromEntity(Stock entity) {
		if (entity == null) {
			return null;
		}
		return StockDto.builder().ingredient(IngredientDto.fromEntity(entity.getIngredient()))
				.quantity(entity.getQuantity()).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Stock toEntity(StockDto dto) {
		if (dto == null) {
			return null;
		}
		final Stock stock = new Stock();
		stock.setIngredient(IngredientDto.toEntity(dto.getIngredient()));
		stock.setQuantity(dto.getQuantity());
		return stock;
	}
}
