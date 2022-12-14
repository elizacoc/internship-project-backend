package com.kronsoft.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.services.StockService;

@RestController
@RequestMapping("/stocks")
public class StockController {
	
	@Autowired
	private StockService stockService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<StockDto> getAllStocks(){
		return stockService.getAllStocks().stream().map(stock -> new StockDto(stock)).collect(Collectors.toList());
	}
	
	@GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto getStockById(@PathVariable Long id) {
		return stockService.getStockById(id);
	}
	
	@PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public StockDto updateStock(@RequestBody StockDto stock) {
		return stockService.updateStock(stock);
	}
	
	@GetMapping(value = "product/{pzn}", produces = MediaType.APPLICATION_JSON_VALUE)
	public StockDto getStockByProductPzn(@PathVariable String pzn) {
		return stockService.getStockByProductPzn(pzn);
	}
}
