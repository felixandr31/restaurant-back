package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IPurchaseRepository;
import com.filrouge.restaurantcore.dto.OrderDto;
import com.filrouge.restaurantcore.dto.PurchaseDto;
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

	/**
	 * Constructor.
	 * 
	 * @param purchaseRepository the roles DTO.
	 */
	public PurchaseServiceImpl(IPurchaseRepository purchaseRepository) {
		super();
		this.purchaseRepository = purchaseRepository;
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
		return Optional.of(purchaseRepository.findById(id).map(PurchaseDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Purchase avec l'ID = " + id + " n' est trouve dans la BDD",
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
		toUpdate.setOrders(OrderDto.toEntity(purchaseDto.getOrders()));
		toUpdate.setDate(purchaseDto.getDate());

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
