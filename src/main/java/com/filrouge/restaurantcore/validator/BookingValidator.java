package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.BookingDto;

public class BookingValidator {
	/**
	 * Validation du DTO.
	 * 
	 * @param dto le DTO
	 * @return les erreurs pour chaque attribut.
	 */
	public static List<String> validate(final BookingDto dto) {
		final List<String> errors = new ArrayList<String>();
		// TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("date is required");
			// errors.addAll(OrderValidator.validate(null));

			return errors;
		}
		// errors.addAll(OrderValidator.validate(dto.getOrders()));

		if (dto.getDay() != null && !StringUtils.hasLength(dto.getDay().toString())) {
			errors.add("date is required");
		}

		return errors;
	}
}
