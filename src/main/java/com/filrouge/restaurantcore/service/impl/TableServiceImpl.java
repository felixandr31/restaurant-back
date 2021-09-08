package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.ITableRepository;
import com.filrouge.restaurantcore.dto.TableDto;
import com.filrouge.restaurantcore.entity.Table;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.ITableService;
import com.filrouge.restaurantcore.validator.TableValidator;

@Service
public class TableServiceImpl implements ITableService {

	// DAOs
	private ITableRepository tableRepository;

	public TableServiceImpl(ITableRepository tableRepository) {
		super();
		this.tableRepository = tableRepository;
	}

	@Override
	public List<TableDto> findAll() {
		return tableRepository.findAll().stream().map(TableDto::fromEntity).collect(Collectors.toList());
	}

	@Override
	public Optional<TableDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(tableRepository.findById(id).map(TableDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Ingredient avec l'ID = " + id + " n' est trouve dans la BDD",
						ErrorCodes.ROLE_NOT_FOUND));
	}
	
	@Override
	public TableDto save(TableDto dto) {
		return TableDto.fromEntity(tableRepository.save(TableDto.toEntity(dto)));
	}
	
	@Override
	public TableDto update(TableDto tableDto) {
		List<String> errors = TableValidator.validate(tableDto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("la TABLE n'est pas valide", ErrorCodes.ROLE_NOT_VALID, errors);
		}

		Optional<Table> optionalTable = tableRepository.findById(tableDto.getId());

		if (!optionalTable.isPresent()) {
			throw new InvalidEntityException("la TABLE n'existe pas", ErrorCodes.ROLE_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Table toUpdate = optionalTable.get();
		toUpdate.setName(tableDto.getName());
		toUpdate.setName(tableDto.getName());

		return TableDto.fromEntity(tableRepository.save(toUpdate));
	}

	@Override
	public void deleteTableById(String id) {
		if (id == null) {
			return;
		}
		tableRepository.deleteById(id);
	}

}
