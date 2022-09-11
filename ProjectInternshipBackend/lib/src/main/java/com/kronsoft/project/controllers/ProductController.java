package com.kronsoft.project.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.exceptions.PznAlreadyExistsException;
import com.kronsoft.project.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDto> getAllProducts(){
		return productService.getAllProducts()
				.stream()
				.map(product -> new ProductDto(product))
				.collect(Collectors.toList());
	}
	
	@PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto createProduct(@RequestBody ProductDto product) throws PznAlreadyExistsException {
		return productService.productToCreate(product);
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductDto updateProduct(@RequestBody ProductDto product) {
		return productService.productToUpdate(product);
	}
	
	@DeleteMapping(value = "/delete/{pzn}")
	public void deleteProduct(@PathVariable String pzn) {
		productService.deleteProduct(pzn);
	}
	
}
