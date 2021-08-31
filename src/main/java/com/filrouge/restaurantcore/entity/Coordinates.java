package com.filrouge.restaurantcore.entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * 
 * @author sslimani
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "coordinates")
public class Coordinates extends AbstractEntity {
	
	/**
	 * Latitude
	 */
	@Field("latitude")
	private Float latitude;
	
    /**
     * Longitude
     */
	@Field("longitude")
	private Float longitude;
}
