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

import com.filrouge.restaurantcore.dto.PurchaseDto;
import com.filrouge.restaurantcore.service.IPurchaseService;

/**
 * Services REST de gestion des purchases.
 * 
 * @author FAndre
 *
 */
@CrossOrigin(origins = {"http://localhost:8080","http://localhost:4200", "http://192.168.20.109:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/purchase/*")
public class PurchaseController {
	

	@Autowired
	IPurchaseService purchaseService;
	
	
	/**
	 * Search all Purchases.
	 * 
	 * @return Purchases of the DTO.
	 */
	@GetMapping(value = "/purchases")
	public ResponseEntity<Collection<PurchaseDto>> findAll() {
		Collection<PurchaseDto> purchases = purchaseService.findAll();
		return new ResponseEntity<Collection<PurchaseDto>>(purchases, HttpStatus.OK);
	}
	
	@GetMapping(value = "/purchases/{id}")
	public ResponseEntity<Optional<PurchaseDto>> findById(@PathVariable String id) {
		Optional<PurchaseDto> purchases = purchaseService.findById(id);
		return new ResponseEntity<Optional<PurchaseDto>>(purchases, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<PurchaseDto> save(@RequestBody PurchaseDto purchaseDto) {
		PurchaseDto purchaseCreated = purchaseService.save(purchaseDto);
		return new ResponseEntity<PurchaseDto>(purchaseCreated, HttpStatus.CREATED);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public void deletePurchaseById(@PathVariable("id") String id){
	        this.purchaseService.deletePurchaseById(id);
	    }
	 
	 /**
		 * Mise à jour d'un administrateur (sans ces associations)
		 * @param id l'identifiant du Purchase
		 * @param purchaseDto les données à mettre à jour
		 * @return l'administrateur mis à jour
		 */
		@PutMapping("/update/{id}")
		@Transactional
		public ResponseEntity<PurchaseDto> update(@PathVariable String id,
				@RequestBody PurchaseDto purchaseDto) {

			purchaseDto.setId(id);
			PurchaseDto purchaseUpdate = purchaseService.update(purchaseDto);
			return new ResponseEntity<PurchaseDto>(purchaseUpdate, HttpStatus.CREATED);
		}
	 

}
