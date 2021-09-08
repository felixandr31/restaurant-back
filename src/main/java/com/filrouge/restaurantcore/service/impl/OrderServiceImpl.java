package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IOrderRepository;
import com.filrouge.restaurantcore.dto.OrderDto;
import com.filrouge.restaurantcore.entity.Order;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IOrderService;
import com.filrouge.restaurantcore.validator.OrderValidator;

@Service
public class OrderServiceImpl implements IOrderService{
	
	// DAOs
	private IOrderRepository orderRepository;

	/**
	 * Constructor.
	 * 
	 * @param orderRepository the roles DTO.
	 */
	public OrderServiceImpl(IOrderRepository orderRepository) {
		super();
		this.orderRepository = orderRepository;
	}

	@Override
	public List<OrderDto> findAll() {
		return orderRepository.findAll().stream().map(OrderDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Optional<OrderDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(orderRepository.findById(id).map(OrderDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Order avec l'ID = " + id + " n' est trouve dans la BDD",
						ErrorCodes.ROLE_NOT_FOUND));
	}

	@Override
	public OrderDto save(OrderDto dto) {
		return OrderDto.fromEntity(orderRepository.save(OrderDto.toEntity(dto)));
	}

	@Override
	public OrderDto update(OrderDto orderDto) {
		List<String> errors = OrderValidator.validate(orderDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
		}

		Optional<Order> optionalOrder = orderRepository.findById(orderDto.getId());

		if (!optionalOrder.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Order toUpdate = optionalOrder.get();
		toUpdate.setItem(orderDto.getItem());
		toUpdate.setQuantity(orderDto.getQuantity());

		return OrderDto.fromEntity(orderRepository.save(toUpdate));
	}

	@Override
	public void deleteOrderById(String id) {
		if (id == null) {
			return;
		}
		orderRepository.deleteById(id);
	}

}
