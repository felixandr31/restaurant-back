package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.filrouge.restaurantcore.entity.Address;

public interface IAddressRepository extends MongoRepository<Address, String> {

}
