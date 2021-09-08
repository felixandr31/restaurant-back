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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.filrouge.restaurantcore.dto.TableDto;
import com.filrouge.restaurantcore.service.ITableService;

/**
 * Services REST de gestion des tables.
 * 
 * @author FAndre
 *
 */
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:4200" }, maxAge = 3600)
@RestController
@RequestMapping("/table/*")
public class TableController {

	@Autowired
	ITableService tableService;

	@GetMapping(value = "/tables")
	public ResponseEntity<Collection<TableDto>> findAll() {
		Collection<TableDto> tables = tableService.findAll();
		return new ResponseEntity<Collection<TableDto>>(tables, HttpStatus.OK);
	}

	@GetMapping(value = "tables/{id}")
	public ResponseEntity<Optional<TableDto>> findById(@PathVariable String id) {
		Optional<TableDto> tables = tableService.findById(id);
		return new ResponseEntity<Optional<TableDto>>(tables, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	@Transactional
	public ResponseEntity<TableDto> save(@RequestBody TableDto tableDto) {
		TableDto tableCreated = tableService.save(tableDto);
		return new ResponseEntity<TableDto>(tableCreated, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteIngredientById(@PathVariable("id") String id) {
		this.tableService.deleteTableById(id);
	}
}
