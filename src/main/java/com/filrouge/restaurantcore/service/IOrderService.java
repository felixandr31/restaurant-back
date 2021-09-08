package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.OrderDto;

public interface IOrderService {

	/**
	 * Research of all orders.
	 * 
	 * @return the DTOs of the order.
	 */
	List<OrderDto> findAll();

	/**
	 * 
	 * Search an order by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<OrderDto> findById(String id);

	/**
	 * Création of a order.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the order
	 */
	OrderDto save(OrderDto dto);

	/**
	 * Removal of an order.
	 * 
	 * @param id The order identifier.
	 * @return the order's DTO.
	 * 
	 */
	void deleteOrderById(String id);

	/**
	 * Mise à jour du Order
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	OrderDto update(OrderDto orderDto);

}