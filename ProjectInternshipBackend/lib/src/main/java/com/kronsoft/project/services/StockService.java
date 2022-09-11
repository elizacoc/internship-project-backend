package com.kronsoft.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.StockRepository;
import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.entities.Stock;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	public List<Stock> getAllStocks(){
		return stockRepository.findAll();
	}
	
	public StockDto stockToPersist(StockDto stockDto) {
		Stock stock = new Stock(stockDto);
		return new StockDto(stockRepository.save(stock));
	}
}
