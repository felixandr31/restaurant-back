package com.filrouge.restaurantcore.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.filrouge.restaurantcore.entity.Booking;
import com.filrouge.restaurantcore.entity.Order;
import com.filrouge.restaurantcore.entity.User;

import lombok.Builder;
import lombok.Data;

/**
 * Transfert Object from booking (DTO).
 * 
 * @author sslimani
 *
 */
@Data
@Builder
public class BookingDto {
	/**
	 * Id
	 */
	private String id;
	/**
	 * Day.
	 */
	private Date day;

	/**
	 * Hour.
	 */

	private String hour;

	/**
	 * Informations of tables.
	 */

	private TableDto table;

	/**
	 * Informations of orders.
	 */
	// @Builder.Default permet de surcharger la construction de la collection de

	// lombok
	@Builder.Default
	private List<OrderDto> orders = new ArrayList<OrderDto>(0);
	@Builder.Default
	private List<UserDto> clients = new ArrayList<UserDto>(0);

	/**
	 * Transform entity into DTO.
	 * 
	 * @param entity l'entité
	 * @return le DTO
	 */
	public static BookingDto fromEntity(Booking entity) {
		if (entity == null) {
			return null;
		}

		final List<OrderDto> ordersDTO = new ArrayList<OrderDto>(entity.getOrders().size());
		for (final Order order : entity.getOrders()) {
			ordersDTO.add(OrderDto.fromEntity(order));
		}
		final List<UserDto> clientsDTO = new ArrayList<UserDto>(entity.getClients().size());
		for (final User client : entity.getClients()) {
			clientsDTO.add(UserDto.fromEntity(client));
		}

		return BookingDto.builder().id(entity.getId()).day(entity.getDay()).hour(entity.getHour())
				.table(TableDto.fromEntity(entity.getTable())).orders(ordersDTO).clients(clientsDTO).build();
	}

	/**
	 * Transform l DTO into entity.
	 * 
	 * @param dto le DTO
	 * @return l'entité
	 */
	public static Booking toEntity(BookingDto dto) {
		if (dto == null) {
			return null;
		}

		final Booking booking = new Booking();
		booking.setId(dto.getId());
		booking.setDay(dto.getDay());
		booking.setHour(dto.getHour());
		booking.setTable(TableDto.toEntity(dto.getTable()));

		final List<Order> orders = dto.getOrders().stream().map(OrderDto::toEntity).collect(Collectors.toList());
		booking.setOrders(orders);

		final List<User> clients = dto.getClients().stream().map(UserDto::toEntity).collect(Collectors.toList());
		booking.setClients(clients);

		return booking;

	}
}
