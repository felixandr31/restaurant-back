package com.filrouge.restaurantcore.entity;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Stock Entity
 * 
 * @author sslimani
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "stock")

public class Stock extends AbstractEntity {
	/**
	 * Ingredient
	 */
	@DBRef
	private Ingredient ingredient;
	/**
	 * Quantity
	 */
	@Field("quantity")
	private BigDecimal quantity;
}
