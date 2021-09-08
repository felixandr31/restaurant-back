package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IBookingRepository;
import com.filrouge.restaurantcore.dto.BookingDto;
import com.filrouge.restaurantcore.service.IBookingService;

/**
 * Services métier de gestion des reservations.
 * 
 * @author sslimani
 *
 */
@Service
public class BookingServiceImpl implements IBookingService {
	private IBookingRepository bookingRepository;

	/**
	 * Constructor
	 * 
	 * @param bookingService
	 */
	public BookingServiceImpl(IBookingRepository bookingRepository) {
		super();
		this.bookingRepository = bookingRepository;
	}

	@Override
	public BookingDto save(BookingDto dto) {
//		List<String> errors = RestaurantValidator.validate(dto);
//		if (!errors.isEmpty()) {
//			throw new InvalidEntityException("Le restaurant n'est pas valide", ErrorCodes.RESTAURANT_NOT_VALID, errors);
//		}
		return BookingDto.fromEntity(bookingRepository.save(BookingDto.toEntity(dto)));
	}

	@Override
	public List<BookingDto> findAll() {
		return bookingRepository.findAll().stream().map(BookingDto::fromEntity).collect(Collectors.toList());
	}
}
