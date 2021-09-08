package com.filrouge.restaurantcore.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.BookingDto;
import com.filrouge.restaurantcore.service.IBookingService;

/**
 * Services REST de gestion des restaurants.
 * 
 * @author sslimani
 * 
 */

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/booking/*")
public class BookingController {
	@Autowired
	IBookingService bookingService;

	/**
	 * Search all Reservation.
	 * 
	 * @return Ingredients of the DTO.
	 */
	@GetMapping(value = "/reservations")
	public ResponseEntity<Collection<BookingDto>> findAll() {
		Collection<BookingDto> bookings = bookingService.findAll();
		return new ResponseEntity<Collection<BookingDto>>(bookings, HttpStatus.OK);
	}

	/**
	 * Create Reservation.
	 * 
	 * @return Reservation of the DTO.
	 */
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<BookingDto> save(@RequestBody BookingDto bookingDto) {
		BookingDto reservationCreated = bookingService.save(bookingDto);
		return new ResponseEntity<BookingDto>(reservationCreated, HttpStatus.CREATED);
	}
}
