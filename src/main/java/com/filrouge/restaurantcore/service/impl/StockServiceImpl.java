package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IStockRepository;
import com.filrouge.restaurantcore.dto.StockDto;
import com.filrouge.restaurantcore.service.IStockService;
@Service
public class StockServiceImpl implements IStockService{
	 
	private IStockRepository stockRepository;
	
	public StockServiceImpl (IStockRepository stockRepository) {
		super();
		this.stockRepository = stockRepository;
	}
	
	/**
	 * Create a stock
	 */
	@Override
	public StockDto save(StockDto dto) {
		return StockDto.fromEntity(stockRepository.save(StockDto.toEntity(dto)));
		
	}
	
	/**
	 * Find All Stocks
	 */
	@Override
	public List<StockDto> findAll() {
		return stockRepository.findAll().stream().map(StockDto::fromEntity).collect(Collectors.toList());
	}
}
