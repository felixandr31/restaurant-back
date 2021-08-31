package com.filrouge.restaurantcore.entity;

import java.time.Instant;
import java.util.ArrayList;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Purchase Entity
 * 
 * @author sslimani
 *
 */
@Data

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Builder
@Document(collection = "purchase")
public class Purchase extends AbstractEntity {

	/**
	 * Date
	 */
	@Field("date")
	private Instant date;

	/**
	 * Orders
	 */
	@Field("orders")
	private List<Order> orders;

	/**
	 * Constructor.
	 */
	public Purchase() {
		this.orders = new ArrayList<Order>(0);
	}
}
