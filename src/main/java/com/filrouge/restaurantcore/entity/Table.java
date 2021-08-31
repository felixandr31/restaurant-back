package com.filrouge.restaurantcore.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Entity Table
 * 
 * @author formation
 *
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

@Builder
@Document("table")
public class Table extends AbstractEntity {

	/**
	 * table name
	 */

	@Field("name")
	@NonNull
	private String name;

	/**
	 * the number of seat
	 */
	@NonNull
	@Field("capacity")
	private Integer capacity;

}
