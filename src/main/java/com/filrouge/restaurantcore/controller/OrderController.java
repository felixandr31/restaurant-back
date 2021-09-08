package com.filrouge.restaurantcore.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.OrderDto;
import com.filrouge.restaurantcore.service.IOrderService;

/**
 * Services REST de gestion des orders.
 * 
 * @author FAndre
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/order/*")
public class OrderController {
	
	@Autowired
	IOrderService orderService;
	
	
	/**
	 * Search all Orders.
	 * 
	 * @return Orders of the DTO.
	 */
	@GetMapping(value = "/orders")
	public ResponseEntity<Collection<OrderDto>> findAll() {
		Collection<OrderDto> orders = orderService.findAll();
		return new ResponseEntity<Collection<OrderDto>>(orders, HttpStatus.OK);
	}
	
	@GetMapping(value = "/orders/{id}")
	public ResponseEntity<Optional<OrderDto>> findById(@PathVariable String id) {
		Optional<OrderDto> orders = orderService.findById(id);
		return new ResponseEntity<Optional<OrderDto>>(orders, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<OrderDto> save(@RequestBody OrderDto orderDto) {
		OrderDto orderCreated = orderService.save(orderDto);
		return new ResponseEntity<OrderDto>(orderCreated, HttpStatus.CREATED);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public void deleteOrderById(@PathVariable("id") String id){
	        this.orderService.deleteOrderById(id);
	    }
	 
	 /**
		 * Mise à jour d'un administrateur (sans ces associations)
		 * @param id l'identifiant du Order
		 * @param orderDto les données à mettre à jour
		 * @return l'administrateur mis à jour
		 */
		@PutMapping("/update/{id}")
		@Transactional
		public ResponseEntity<OrderDto> update(@PathVariable String id,
				@RequestBody OrderDto orderDto) {

			orderDto.setId(id);
			OrderDto orderUpdate = orderService.update(orderDto);
			return new ResponseEntity<OrderDto>(orderUpdate, HttpStatus.CREATED);
		}
	 
	 
}
