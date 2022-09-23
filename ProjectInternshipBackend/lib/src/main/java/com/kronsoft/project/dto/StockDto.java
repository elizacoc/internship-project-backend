package com.kronsoft.project.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.entities.Stock;

public class StockDto {

	private Long id;
	
	private Long quantity;
	
	private BigDecimal price;
	
	private ProductDto product;
	
	//private String productPzn;
	
	public StockDto() {
		
	}
	
	public StockDto(Stock stock) {
		BeanUtils.copyProperties(stock, this);
		this.product = new ProductDto(stock.getProduct());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public ProductDto getProduct() {
		return product;
	}

	public void setProduct(ProductDto product) {
		this.product = product;
	}
	
}
