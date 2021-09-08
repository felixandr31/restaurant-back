package com.filrouge.restaurantcore.service;

import java.util.List;

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
}
