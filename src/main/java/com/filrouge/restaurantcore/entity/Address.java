package com.filrouge.restaurantcore.entity;

import java.math.BigInteger;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Address Entity
 * 
 * @author sslimani
 *
 */
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Document(collection = "address")
@Data
public class Address extends AbstractEntity {
	/**
	 * Address.
	 */
	@Field("streetName")
	private String streetName;

	/**
	 * City.
	 */
	@Field("city")
	private String city;

	/**
	 * Zip-code.
	 */
	@Field("zipcode")
	private BigInteger zipCode;

	/**
	 * Country .
	 */
	@Field("country")
	private String country;
}
