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

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Document(collection = "address")
@Data
@Builder
public class Address extends AbstractEntity {
	/**
	 * L'adresse complète.
	 */
	@Field("streetName")
	private String streetName;

	/**
	 * La ville.
	 */
	@Field("city")
	private String city;

	/**
	 * Le code postal.
	 */
	@Field("zipcode")
	private BigInteger zipCode;
	
	/**
	 * Le pays .
	 */
	@Field("country")
	private String country;
}
