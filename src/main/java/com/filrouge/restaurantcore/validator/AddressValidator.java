package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.AddressDto;



public class AddressValidator {
	/**
	 * Validation du DTO.
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final AddressDto dto) {
		final List<String> errors = new ArrayList<String>();
		//TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("streetName is required");
			errors.add("city is required");
			errors.add("zipCode is required");
			errors.add("country is required");
			return errors;
		}
		if (!StringUtils.hasLength(dto.getStreetName())) {
			errors.add("streetName is required");
		}
		if (!StringUtils.hasLength(dto.getCity())) {
			errors.add("city is required");
		}
		if (dto.getZipCode() != null && !StringUtils.hasLength(dto.getZipCode().toString())) {
			errors.add("zipCode is required");
		}
		if (!StringUtils.hasLength(dto.getCountry())) {
			errors.add("country is required");
		}
		return errors;
	}
}
