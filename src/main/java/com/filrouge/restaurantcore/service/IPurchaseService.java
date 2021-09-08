package com.filrouge.restaurantcore.service;

import java.util.List;
import java.util.Optional;

import com.filrouge.restaurantcore.dto.PurchaseDto;

public interface IPurchaseService {
	
	
	
	
	/**
	 * Research of all purchases.
	 * 
	 * @return the DTOs of the purchase.
	 */
	List<PurchaseDto> findAll();
	
	/**
	 * 
	 * Search an purchase by ID
	 * 
	 * @param ID identifier
	 * @return the DTO found according to its identifier.
	 */
	Optional<PurchaseDto> findById(String id);
	
	
	/**
	 * Création of a purchase.
	 * 
	 * @param dto the DTO
	 * @return the DTO following the purchase
	 */
	PurchaseDto save(PurchaseDto dto);
	
	
	/**
	 * Removal of an purchase. 
	 * @param id The purchase identifier.
     * @return the purchase's DTO.
	 * 
	 */
	void deletePurchaseById(String id);
	
	/**
	 * Mise à jour du Purchase
	 * 
	 * @param dto le DTO
	 * @return le DTO suite à la création
	 */
	PurchaseDto update(PurchaseDto purchaseDto);
	

}
