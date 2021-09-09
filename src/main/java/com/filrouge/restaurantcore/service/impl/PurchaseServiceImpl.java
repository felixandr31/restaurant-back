package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IOrderRepository;
import com.filrouge.restaurantcore.dao.IPurchaseRepository;
import com.filrouge.restaurantcore.dto.PurchaseDto;
import com.filrouge.restaurantcore.entity.Order;
import com.filrouge.restaurantcore.entity.Purchase;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IPurchaseService;
import com.filrouge.restaurantcore.validator.PurchaseValidator;

@Service
public class PurchaseServiceImpl implements IPurchaseService {

	// DAOs
	private IPurchaseRepository purchaseRepository;
	private IOrderRepository orderRepository;

	/**
	 * Constructor.
	 * 
	 * @param purchaseRepository the roles DTO.
	 */
	public PurchaseServiceImpl(IPurchaseRepository purchaseRepository, IOrderRepository orderRepository) {
		super();
		this.purchaseRepository = purchaseRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public List<PurchaseDto> findAll() {
		return purchaseRepository.findAll().stream().map(PurchaseDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Optional<PurchaseDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(purchaseRepository.findById(id).map(PurchaseDto::fromEntity)).orElseThrow(
				() -> new EntityNotFoundException("Aucun Purchase avec l'ID = " + id + " n' est trouve dans la BDD",
						ErrorCodes.ROLE_NOT_FOUND));
	}
	
	
	@Override
	public PurchaseDto save(PurchaseDto dto) {
		return PurchaseDto.fromEntity(purchaseRepository.save(PurchaseDto.toEntity(dto)));
	}

	@Override
	public PurchaseDto update(PurchaseDto purchaseDto) {
		List<String> errors = PurchaseValidator.validate(purchaseDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("le ROLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
		}

		Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseDto.getId());

		if (!optionalPurchase.isPresent()) {
			throw new InvalidEntityException("Le ROLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Purchase toUpdate = optionalPurchase.get();
		toUpdate.setDate(purchaseDto.getDate());

		return PurchaseDto.fromEntity(purchaseRepository.save(toUpdate));
	}

	@Override
	public PurchaseDto addOrders(String id, final Set<String> orderIds) {
		Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);

//		if (!optionalRestaurant.isPresent()) {
//			throw new InvalidEntityException("L'admininistrateur n'existe pas", ErrorCodes.ADMINISTRATOR_NOT_VALID);
//		}
		Purchase toUpdate = optionalPurchase.get();

		// Recherche des entités role existantes
		Set<Order> ordersToAdd = orderIds.stream().map(orderId -> orderRepository.findById(orderId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getOrders().addAll(ordersToAdd);

		return PurchaseDto.fromEntity(purchaseRepository.save(toUpdate));
	}

	@Override
	public PurchaseDto removeOrders(String id, Set<String> orderIds) {
		Optional<Purchase> optionalPurchase = purchaseRepository.findById(id);

		Purchase toUpdate = optionalPurchase.get();

		// Finding existing user entities
		Set<Order> ordersToRemove = orderIds.stream().map(orderId -> orderRepository.findById(orderId))
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toSet());

		toUpdate.getOrders().removeAll(ordersToRemove);

		return PurchaseDto.fromEntity(purchaseRepository.save(toUpdate));
	}

	@Override
	public void deletePurchaseById(String id) {
		if (id == null) {
			return;
		}
		purchaseRepository.deleteById(id);
	}

}
