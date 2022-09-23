package com.kronsoft.project.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.kronsoft.project.dto.StockDto;
import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;
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
		List<Product> productList = productRepository.findAll();
		for(Product product : productList) {
			if(!stockRepository.existsByProductPzn(product.getPzn())) {
				createStockForProduct(new ProductDto(product));
			}
		}
		return productList;
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
		productRepository.save(product);
		createStockForProduct(productDto);
		
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
		return String.format("%1$" + 8 + "s", stringToFormat).replace(' ', '0');
	}

	public ProductDto getProductByPzn(String pzn) {
		ProductDto productDto = productRepository.findByPzn(pzn);
		return productDto;
	}
	
	private StockDto createStockForProduct(ProductDto product) {
		Stock stock = new Stock();
		stock.setPrice(new BigDecimal(0.00).setScale(2, RoundingMode.HALF_EVEN));
		stock.setQuantity(0L);
		stock.setProduct(new Product(product));
		StockDto stockDto = new StockDto(stock);
		return stockService.stockToPersist(stockDto);
	}
}
