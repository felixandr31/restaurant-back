package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import com.filrouge.restaurantcore.dto.CoordinatesDto;
import org.springframework.util.StringUtils;


public class CoordinatesValidator {
	/**
	 * Validation du DTO.
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final CoordinatesDto dto) {
		final List<String> errors = new ArrayList<String>();
		//TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("latitude is required");
			errors.add("longitude is required");
			
			return errors;
		}
		if (dto.getLatitude() != null && !StringUtils.hasLength(dto.getLatitude().toString())) {
			errors.add("latitude is required");
		}
		if (dto.getLongitude() != null && !StringUtils.hasLength(dto.getLongitude().toString())) {
			errors.add("longitude is required");
		}
		
		return errors;
	}
}
