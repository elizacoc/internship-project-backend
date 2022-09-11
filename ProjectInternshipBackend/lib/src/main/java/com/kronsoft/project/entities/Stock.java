package com.kronsoft.project.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.dto.StockDto;

@Entity
@Table(name = "stocks")
public class Stock {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	@NotNull(message = "Id cannot be null!")
	private Long id;
	
	@Column(name = "quantity", nullable = false)
	@NotNull(message = "Quantity cannot be null!")
	private Long quantity;
	
	@Column(name = "price", nullable = false, precision = 2)
	@NotNull(message = "Price cannot be null!")
	private BigDecimal price;
	
	@OneToOne(optional = false, cascade = { CascadeType.REMOVE })
	@JoinColumn(name = "product", referencedColumnName = "pzn")
	@NotNull(message = "Product cannot be null!")
	private Product product;
	
	public Stock() {
		
	}
	
	public Stock(StockDto stock) {
		BeanUtils.copyProperties(stock, this);
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}
