package com.filrouge.restaurantcore.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.filrouge.restaurantcore.dto.UserDto;



/**
 * Administrator data validation services
 * 
 * @author formation
 *
 */
public class UserValidator {
	
	/**
	 * DTO validation
	 * @param dto the DTO
	 * @return errors for each attribute.
	 */
	
	public static List<String> validate(final UserDto dto) {
		final List<String> errors = new ArrayList<String>();

		//TODO utiliser des messages dans des fichiers de propriétes
		if (dto == null) {
			errors.add("First name is required");
			errors.add("Last name is required");
			errors.add("E-Mail is required");
			return errors;
		}

		if (!StringUtils.hasLength(dto.getFirstName())) {
			errors.add("First name is required");
		}
		if (!StringUtils.hasLength(dto.getLastName())) {
			errors.add("Last name is required");
		}
		if (!StringUtils.hasLength(dto.getEmail())) {
			errors.add("E-Mail is required");
		}

		
		return errors;
	}

}
