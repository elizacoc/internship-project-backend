package com.kronsoft.project.services;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
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
	
	private String leftPadding(String stringToFormat) {
		return String.format("%1$" + 8 + "s", stringToFormat).replace(' ', '0');
	}
	
	public List<Product> getAllProducts(){
		List<Product> productList = productRepository.findAll(Sort.by(Sort.Direction.ASC, "productName"));
		for(Product product : productList) {
			if(!stockRepository.existsByProductPzn(product.getPzn())) {
				stockService.createStockForProduct(new ProductDto(product));
			}
		}
		return productList;
	}
	
	public ProductDto productToCreate(ProductDto productDto) throws PznAlreadyExistsException {
		String pzn = productDto.getPzn();
		if(Objects.isNull(pzn)) {
			throw new DataIntegrityViolationException("Pzn must not be null!");
		}
		if(pzn.length() != 0)
		{
			productDto.setPzn(leftPadding(pzn));
		}
		if(productRepository.existsByPzn(pzn)) {
			throw new PznAlreadyExistsException(pzn);
		}
		Product product = new Product(productDto);
		productRepository.save(product);
		stockService.createStockForProduct(productDto);
		
		return new ProductDto(productRepository.save(product));
		
	}
	
	public ProductDto productToUpdate(ProductDto productDto) {
		Optional<Product> productOpt = productRepository.findById(productDto.getPzn());
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			BeanUtils.copyProperties(productDto, product, "stock");
			return new ProductDto(productRepository.save(product));
		} else {
			throw new EntityNotFoundException("Product with pzn " + productDto.getPzn() + " could not be found!");
		}		
	}
	
	public void deleteProduct(String pzn) {
		productRepository.deleteById(pzn);
	}

	public ProductDto getProductByPzn(String pzn) {
		Optional<Product> productOpt = productRepository.findById(pzn);
		
		if(productOpt.isPresent()) {
			Product product = productOpt.get();
			return new ProductDto(product);
		} else {
			throw new EntityNotFoundException("Pzn " + pzn + " not found");
		}
	}
	
	
}
