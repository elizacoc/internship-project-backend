package com.kronsoft.project.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kronsoft.project.dao.ProductRepository;
import com.kronsoft.project.dao.StockRepository;
import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Stock> getAllStocks(){
		return stockRepository.findAll(Sort.by(Sort.Direction.ASC, "productProductName"));
	}
	 
	public StockDto updateStock(StockDto stockDto) {
		Optional<Stock> stockOpt = stockRepository.findById(stockDto.getId());
		//stockOpt.ifPresentOrElse((stock) -> {
		//	BeanUtils.copyProperties(stockDto, stock);
		//}, () -> new Exception("test"));
		//verific daca am valoare in optional
		//stockOpt.isPresent(); //true = a gasit stockul
		//Stock stockTest = stockOpt.get(); //Obtin obiectul in sine
//
//		stockOpt.ifPresent((stock) -> {
//			stockTest.setId(1L);
//		});
		
		if (stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			BeanUtils.copyProperties(stockDto, stock, "product");
			
			productRepository.findById(stockDto.getProduct().getPzn()).
			ifPresentOrElse((product) -> stock.setProduct(product), 
			() -> new EntityNotFoundException("Pzn not found"));
			
			return new StockDto(stockRepository.save(stock));
		} else {
			throw new EntityNotFoundException("Stock not found");
		}
		
	}

	public StockDto getStockById(Long id) {
		Optional<Stock> stockOpt = stockRepository.findById(id);
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			return new StockDto(stock);
		} else {
			throw new EntityNotFoundException("Stock not found");
		}
	}

	public StockDto getStockByProductPzn(String pzn) {
		Optional<Stock> stockOpt = stockRepository.findByProductPzn(pzn);
		
		if(stockOpt.isPresent()) {
			Stock stock = stockOpt.get();
			return new StockDto(stock);
		} else {
			throw new EntityNotFoundException("Stock not found");
		}
	}
	
	public StockDto createStockForProduct(ProductDto product) {
		Stock stock = new Stock();
		stock.setPrice(new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN));
		stock.setQuantity(0L);
		stock.setProduct(new Product(product));
		return new StockDto(stockRepository.save(stock));
	}
}
