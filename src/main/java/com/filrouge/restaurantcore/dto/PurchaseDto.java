package com.filrouge.restaurantcore.dto;


import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;

import com.filrouge.restaurantcore.entity.Order;
import com.filrouge.restaurantcore.entity.Purchase;



import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from purchase (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class PurchaseDto {
	/**
	 * Id
	 */
     private String id;
	/**
	 * Date
	 */
	private Instant date;

	/**
	 * Orders.
	 * 
	 */
	// @Builder.Default permet de surcharger la construction de la collection de
	// lombok
	@Builder.Default
	private List<OrderDto> orders = new ArrayList<OrderDto>(0);

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
		
		final List<OrderDto> ordersDTO = new ArrayList<OrderDto>(entity.getOrders().size());
		for (final Order order : entity.getOrders()) {
			ordersDTO.add(OrderDto.fromEntity(order));
		}
		return PurchaseDto.builder().id(entity.getId()).date(entity.getDate()).orders(ordersDTO).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Purchase toEntity(PurchaseDto dto) {
		if (dto == null) {
			return null;
		}
		
		final Purchase purchase = new Purchase();
		purchase.setDate(dto.getDate());
		
		final List<Order> orders = dto.getOrders()
		        .stream()
		        .map(OrderDto::toEntity)
		        .collect(Collectors.toList());
		purchase.setOrders(orders);
		return purchase;
	}
}
