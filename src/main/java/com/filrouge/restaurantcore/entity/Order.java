package com.filrouge.restaurantcore.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@Builder
@Document(collection = "order")
public class Order extends AbstractEntity {
	
	/**
	 * the order item
	 */
	
	@Field("item")
	private Object item;
	
	/**
	 * the order quantity
	 */
	@Field("quantity")
	private Integer quantity;

}
