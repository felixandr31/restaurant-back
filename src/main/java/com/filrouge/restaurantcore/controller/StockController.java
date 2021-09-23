package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.RestaurantDto;
import com.filrouge.restaurantcore.dto.StockDto;
import com.filrouge.restaurantcore.service.IStockService;

/**
 * Services REST de gestion des restaurants.
 * 
 * @author sslimani
 * 
 */

@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200", "http://192.168.20.109:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/stock/*")
public class StockController {
	@Autowired
	IStockService stockService;
	
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<StockDto> save(@RequestBody StockDto stockDto) {
		StockDto stockCreated = stockService.save(stockDto);
		return new ResponseEntity<StockDto>(stockCreated, HttpStatus.CREATED);
	}
	
	
	/**
	 * Search all Stocks.
	 * 
	 * @return Stocks of the DTO.
	 */
	@GetMapping(value = "/stocks")
	public ResponseEntity<Collection<StockDto>> findAll() {
		Collection<StockDto> stocks = stockService.findAll();
		return new ResponseEntity<Collection<StockDto>>(stocks, HttpStatus.OK);
	}
	
	/**
	 * Get stock by Id.
	 * 
	 * @return Stock.
	 */
	@GetMapping(value = "/stocksid/{id}")
	public ResponseEntity<Optional<StockDto>> findById(@PathVariable("id") String id) {
		Optional<StockDto> stockById = stockService.findById(id);
		return new ResponseEntity<Optional<StockDto>>(stockById, HttpStatus.OK);
		
	}
	
}
