package com.filrouge.restaurantcore.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.filrouge.restaurantcore.entity.Booking;
import com.filrouge.restaurantcore.entity.Restaurant;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;

/**
 * User Object Transfer (DTO).
 * 
 * @author Hermann
 *
 */
@Data
@Builder

public class UserDto {

	private String id;

	private String firstName;

	private String lastName;

	private String password;

	private String email;

	private String restaurantId;

	// private RestaurantDto restaurant;

	// @JsonIgnore
	@Builder.Default
	private List<RoleDto> roles = new ArrayList<RoleDto>();

	// @JsonIgnore
	@Builder.Default
	private List<String> friends = new ArrayList<String>();

	// @JsonIgnore
	@Builder.Default
	private List<RestaurantDto> restaurants = new ArrayList<RestaurantDto>();

	// @JsonIgnore
	@Builder.Default
	private List<String> bookings = new ArrayList<String>();

	/**
	 * Transform the entity into a DTO.
	 * 
	 * @param entity
	 * @return DTO of entity
	 */
	public static UserDto fromEntity(User entity) {
		if (entity == null) {
			// TODO throw an exception
			return null;
		}
		final List<RoleDto> rolesDto = new ArrayList<RoleDto>(entity.getRoles().size());
		for (final Role role : entity.getRoles()) {
			rolesDto.add(RoleDto.fromEntity(role));
		}
		final List<String> friendsDto = new ArrayList<String>(entity.getFriends().size());
		for (final String user : entity.getFriends()) {
			friendsDto.add(user);
		}

		final List<String> bookingsDto = new ArrayList<String>(entity.getBookings().size());
		for (final String booking : entity.getBookings()) {
			bookingsDto.add(booking);
		}

		final List<RestaurantDto> restaurantsDto = new ArrayList<RestaurantDto>(entity.getRestaurants().size());
		for (final Restaurant restaurant : entity.getRestaurants()) {
			restaurantsDto.add(RestaurantDto.fromEntity(restaurant));
		}
		return UserDto.builder().id(entity.getId()).restaurants(restaurantsDto).firstName(entity.getFirstName())
				.lastName(entity.getLastName()).email(entity.getEmail()).password(entity.getPassword())
				.restaurantId(entity.getRestaurantId()).roles(rolesDto).bookings(bookingsDto).friends(friendsDto)
				.build();

	}

	/**
	 * Transform the DTO into entity.
	 * 
	 * @param dto
	 * @return entity of DTO
	 */

	public static User toEntity(UserDto dto) {
		if (dto == null) {
			// TODO throw an exception
			return null;

		}
		final User user = new User();

		user.setId(dto.getId());

		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPassword(dto.getPassword());
		user.setEmail(dto.getEmail());
		user.setRestaurantId(dto.getRestaurantId());
		// user.setRestaurant(RestaurantDto.toEntity(dto.getRestaurant()));

		final List<Role> roles = dto.getRoles().stream().map(RoleDto::toEntity).collect(Collectors.toList());
		user.setRoles(roles);

		if (dto.getRestaurants() == null) {
			dto.setRestaurants(new ArrayList<RestaurantDto>());
		}
		final List<Restaurant> restaurants = dto.getRestaurants().stream().map(RestaurantDto::toEntity)
				.collect(Collectors.toList());
		user.setRestaurants(restaurants);

		final List<String> friends = dto.getFriends();
		user.setFriends(friends);

		final List<String> bookings = dto.getBookings();
		user.setBookings(bookings);
		
		return user;
	}

}
