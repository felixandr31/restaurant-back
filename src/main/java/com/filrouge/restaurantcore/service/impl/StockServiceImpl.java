package com.filrouge.restaurantcore.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.filrouge.restaurantcore.dao.IStockRepository;

import com.filrouge.restaurantcore.dto.StockDto;

import com.filrouge.restaurantcore.entity.Stock;
import com.filrouge.restaurantcore.exception.EntityNotFoundException;
import com.filrouge.restaurantcore.exception.ErrorCodes;
import com.filrouge.restaurantcore.exception.InvalidEntityException;
import com.filrouge.restaurantcore.service.IStockService;

import com.filrouge.restaurantcore.validator.StockValidator;

@Service
public class StockServiceImpl implements IStockService {

	private IStockRepository stockRepository;

	public StockServiceImpl(IStockRepository stockRepository) {
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


	/**
	 * Find stock by id
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public Optional<StockDto> findById(String id) {
		if (id == null) {
			return null;
		}
		return Optional.of(stockRepository.findById(id).map(StockDto::fromEntity))
				.orElseThrow(() -> new EntityNotFoundException(
						"Aucun Stock avec l'ID = " + id + " n' ete trouve dans la BDD",
						ErrorCodes.STOCK_NOT_FOUND));
	}
	@Override
	public StockDto update(StockDto dto) {
		List<String> errors = StockValidator.validate(dto);
		if (!errors.isEmpty()) {
			throw new InvalidEntityException("Le stock n'est pas valide", ErrorCodes.STOCK_NOT_VALID, errors);
		}

		Optional<Stock> optionalStock = stockRepository.findById(dto.getId());

		if (!optionalStock.isPresent()) {
			throw new InvalidEntityException("Le stock n'existe pas", ErrorCodes.STOCK_NOT_VALID);
		}

		// Ne mettre à jour que ce dont on a besoin
		Stock toUpdate = optionalStock.get();
		toUpdate.setQuantity(dto.getQuantity());  
		

		return StockDto.fromEntity(stockRepository.save(toUpdate));
	}

	
	
}
