package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.RestaurantDto;

/**
 * Services de validation des données de l'adresse.
 * 
 * @author sslimani
 *
 */
public class RestaurantValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(RestaurantDto dto) {
		final List<String> errors = new ArrayList<String>();

		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("name is required");
			errors.addAll(AddressValidator.validate(null));
			errors.add("stars is required");
			errors.addAll(CoordinatesValidator.validate(null));
			errors.addAll(UserValidator.validate(null));
			errors.addAll(TableValidator.validate(null));
			errors.add("budget is required");
			
		
			return errors;
		}

		if (!StringUtils.hasLength(dto.getName())) {
			errors.add("Name is required");
		}
		if (dto.getStars() != null && !StringUtils.hasLength(dto.getStars().toString())) {
			errors.add("stars is required");
		}
		if (dto.getBudget() != null && !StringUtils.hasLength(dto.getBudget().toString())) {
			errors.add("budget is required");
		}
		errors.addAll(AddressValidator.validate(dto.getAddress()));
		errors.addAll(CoordinatesValidator.validate(dto.getCoordinates()));
		

		return errors;
	}
}
