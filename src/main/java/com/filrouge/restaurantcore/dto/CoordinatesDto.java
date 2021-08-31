package com.filrouge.restaurantcore.dto;

import com.filrouge.restaurantcore.entity.Coordinates;
import com.filrouge.restaurantcore.entity.Stock;

import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from coordinates (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class CoordinatesDto {
	/**
	 * Latitude
	 */
	private Float latitude;

	/**
	 * Longitude
	 */
	private Float longitude;

	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static CoordinatesDto fromEntity(Coordinates entity) {
		if (entity == null) {
			return null;
		}
		return CoordinatesDto.builder().latitude(entity.getLatitude())
				.longitude(entity.getLongitude()).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Coordinates toEntity(CoordinatesDto dto) {
		if (dto == null) {
			return null;
		}
		final Coordinates coordinates = new Coordinates();
		coordinates.setLatitude(dto.getLatitude());
		coordinates.setLongitude(dto.getLongitude());
		return coordinates;
	}
}
