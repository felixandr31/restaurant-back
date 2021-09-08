package com.filrouge.restaurantcore.dto;

import java.math.BigInteger;

import com.filrouge.restaurantcore.entity.Address;

import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from address (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class AddressDto {
	
	/**
	 * Address.
	 */
	private String id;
	/**
	 * Address.
	 */
	private String streetName;

	/**
	 * City.
	 */
	private String city;

	/**
	 * Zip-code.
	 */
	private BigInteger zipCode;

	/**
	 * Country .
	 */
	private String country;

	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static AddressDto fromEntity(Address entity) {
		if (entity == null) {
			return null;
		}
		return AddressDto.builder().streetName(entity.getStreetName()).city(entity.getCity())
				.zipCode(entity.getZipCode()).country(entity.getCountry()).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Address toEntity(AddressDto dto) {
		if (dto == null) {
			return null;
		}
		final Address address = new Address();
		address.setStreetName(dto.getStreetName());
		address.setCity(dto.getCity());
		address.setZipCode(dto.getZipCode());
		address.setCountry(dto.getCountry());
		return address;
	}
}
