package com.filrouge.restaurantcore.dto;

import com.filrouge.restaurantcore.entity.Order;



import lombok.Builder;
import lombok.Data;


@Builder
@Data

/**
 * Order Object Transfer (DTO).
 * @author Hermann
 *
 */
public class OrderDto {
	
	private String id;
	
	private Object item;
	
	
	private Integer quantity;
	
	
	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity 
	 * @return DTO of entity
	 */
	public static OrderDto fromEntity(Order order) {
		if (order == null) {
			return null;
		}
		return OrderDto.builder().id(order.getId()).item(order.getItem())
				.quantity(order.getQuantity()).build();
	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto 
	 * @return entity of DTO
	 */
	public static Order toEntity(OrderDto dto) {
		if (dto == null) {
			return null;
		}
		final Order order = new Order();
		order.setId(dto.getId());
		order.setItem(dto.getItem());
		order.setQuantity(dto.getQuantity());
		return order;
	}

}
