package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Set;

import com.filrouge.restaurantcore.dto.BookingDto;
/**
 * Services métier de gestion des restaurants.
 * 
 * @author sslimani
 *
 */
public interface IBookingService {
	BookingDto save(BookingDto dto);
	List<BookingDto> findAll();
	BookingDto addOrders(String id, final Set<String> orderIds);
	BookingDto removeOrders(String id, Set<String> orderIds);
	List<BookingDto> findByTable(String id);
}
