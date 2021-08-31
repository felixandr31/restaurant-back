package com.filrouge.restaurantcore.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import com.filrouge.restaurantcore.entity.Purchase;
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
public class PurchaseDto {
	/**
	 * Date
	 */
	private Instant date;

	/**
	 * Orders
	 */
	private List<OrderDto> orders;
	
	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static PurchaseDto fromEntity(Purchase entity) {
		if (entity == null) {
			return null;
		}
		return PurchaseDto.builder().date(entity.getDate()).orders(OrderDto.fromEntity(entity.getOrders()))
				.build();
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
