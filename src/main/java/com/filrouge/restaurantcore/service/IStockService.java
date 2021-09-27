package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.StockDto;

public interface IStockService {
	/**
	 * Creation of stock
	 * 
	 * @param dto
	 * @return
	 */
	StockDto save(StockDto dto);
	/**
	 * Find all Stocks
	 * @return
	 */
	List<StockDto> findAll();
	
	
	Optional<StockDto> findById(String id);
	StockDto update(StockDto dto);
}
