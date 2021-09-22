package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IBookingRepository;
import com.filrouge.restaurantcore.dao.IOrderRepository;
import com.filrouge.restaurantcore.dto.BookingDto;
import com.filrouge.restaurantcore.dto.UserDto;
import com.filrouge.restaurantcore.entity.Booking;
import com.filrouge.restaurantcore.entity.Order;
import com.filrouge.restaurantcore.entity.Role;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
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
	private IOrderRepository orderRepository;

	/**
	 * Constructor
	 * 
	 * @param bookingService
	 */
	public BookingServiceImpl(IBookingRepository bookingRepository, IOrderRepository orderRepository) {
		super();
		this.bookingRepository = bookingRepository;
		this.orderRepository = orderRepository;

	}

	@Override
	public BookingDto save(BookingDto dto) {

		return BookingDto.fromEntity(bookingRepository.save(BookingDto.toEntity(dto)));
	}

	@Override
	public List<BookingDto> findAll() {
		return bookingRepository.findAll().stream().map(BookingDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public BookingDto addOrders(String id, final Set<String> orderIds) {
		Optional<Booking> optionalBooking = bookingRepository.findById(id);

		if (!optionalBooking.isPresent()) {
			throw new InvalidEntityException("Booking n'existe pas", ErrorCodes.BOOKING_NOT_FOUND);
		}
		Booking toUpdate = optionalBooking.get();

		// Recherche des entités order existantes
		Set<Order> ordersToAdd = orderIds.stream().map(orderId -> orderRepository.findById(orderId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getOrders().addAll(ordersToAdd);
		toUpdate.setOrdered(true);

		return BookingDto.fromEntity(bookingRepository.save(toUpdate));
	}

	@Override
	public BookingDto removeOrders(String id, Set<String> orderIds) {
		Optional<Booking> optionalBooking = bookingRepository.findById(id);

		if (optionalBooking.isPresent()) {
			throw new InvalidEntityException("Booking n'existe pas", ErrorCodes.BOOKING_NOT_FOUND);
		}
		Booking toUpdate = optionalBooking.get();

		// Finding existing user entities
		Set<Order> ordersToRemove = orderIds.stream().map(orderId -> orderRepository.findById(orderId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getOrders().removeAll(ordersToRemove);

		return BookingDto.fromEntity(bookingRepository.save(toUpdate));
	}

	@Override
	public List<BookingDto> findByTable(String id) {
		return bookingRepository.findByTable(id).stream().map(BookingDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public BookingDto findById(String id) {
		if (id.isEmpty()) {
			return null;
		}
		return bookingRepository.findById(id).map(BookingDto::fromEntity).get();

	}

//	@Override
//	public BookingDto findByIsPayed(Boolean isPayed) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
