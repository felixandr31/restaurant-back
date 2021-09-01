package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.filrouge.restaurantcore.entity.Booking;

public interface IBookingRepository extends MongoRepository<Booking, String> {

}
