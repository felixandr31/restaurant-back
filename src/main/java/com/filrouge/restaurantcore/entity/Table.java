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
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Builder
@Document("table")
public class Table extends AbstractEntity{
	
	/**
	 * table name
	 */

	@Field("name")
	private String name;
	
	/**
	 * the number of seat
	 */
	@Field("capacity")
	private Integer capacity;

}
