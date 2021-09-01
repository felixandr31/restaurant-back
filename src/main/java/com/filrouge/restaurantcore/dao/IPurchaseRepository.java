package com.filrouge.restaurantcore.dao;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.filrouge.restaurantcore.entity.Purchase;

public interface IPurchaseRepository extends MongoRepository<Purchase, Long> {

}
