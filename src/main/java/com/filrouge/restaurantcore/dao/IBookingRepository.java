package com.filrouge.restaurantcore.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.filrouge.restaurantcore.entity.Booking;

public interface IBookingRepository extends MongoRepository<Booking, String> {
	List<Booking> findByTable(String id);
	Optional<Booking> findById(String id);
}
