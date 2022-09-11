package com.kronsoft.project.services;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.kronsoft.project.dao.ProductRepository;
import com.kronsoft.project.dao.StockRepository;
import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.exceptions.PznAlreadyExistsException;

@Service
public class ProductService {

	private static final Logger Logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired StockRepository stockRepository;
	
	@Autowired StockService stockService;
	
	@PostConstruct
	private void populateProductList() {
		try {
			if(productRepository.count() == 0) {
				Resource resource = new ClassPathResource("products.csv");
				CsvSchema schema = CsvSchema.emptySchema().withHeader();
				CsvMapper mapper = new CsvMapper();
				MappingIterator<Product> iterator = mapper.readerFor(Product.class).with(schema).readValues(resource.getInputStream());
				List<Product> products = productRepository.saveAll(iterator.readAll());
			}
		} catch(IOException e) {
			Logger.error("An error occurred while populating products.", e);
		}
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public ProductDto productToCreate(ProductDto productDto) throws PznAlreadyExistsException {
		String pzn = productDto.getPzn();
		if(pzn.length() != 0)
		{
			productDto.setPzn(leftPadding(pzn));
		}
		if(productRepository.existsByPzn(pzn)) {
			throw new PznAlreadyExistsException(pzn);
		}
		Product product = new Product(productDto);
		return new ProductDto(productRepository.save(product));
		
	}
	
	public ProductDto productToUpdate(ProductDto productDto) {
		String pzn = productDto.getPzn();
		if(pzn.length() != 0)
		{
			productDto.setPzn(leftPadding(pzn));
		}
		Product product = new Product(productDto);
		return new ProductDto(productRepository.save(product));
		
	}
	
	public void deleteProduct(String pzn) {
		productRepository.deleteById(pzn);
	}
	
	private String leftPadding(String stringToFormat) {
		return String.format("%1$" + stringToFormat.length() + "s", stringToFormat).replace(' ', '0');
	}
	
}
