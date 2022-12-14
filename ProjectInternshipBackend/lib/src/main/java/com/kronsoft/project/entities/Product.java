package com.kronsoft.project.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.validators.PznConstraint;

@Entity
@Table(name = "products")
public class Product {
	
	@Id
	@Column(name = "pzn", length = 8 , unique = true, nullable = false)
	@PznConstraint
	@Size(max = 8, message = "Pzn must be 8 characters!")
	@NotBlank(message = "Pzn cannot be blank!")
	private String pzn;
	
	@Column(name = "supplier", length = 100)
	@Size(max = 100, message = "Supplier must be less or equal than 100 characters!")
	private String supplier;
	
	@Column(name = "product_name", length = 100, nullable = false)
	@Size(max = 100, message = "Product name must be less or equal than 100 characters!")
	@NotBlank(message = "Product cannot be blank!")
	private String productName;
	
	@Column(name = "strength", length = 100, nullable = false)
	@Size(max = 100, message = "Strength must be less or equal than 100 characters!")
	@NotBlank(message = "Strength cannot be blank!")
	private String strength;
	
	@Column(name = "package_size", length = 20, nullable = false)
	@Size(max = 20, message = "Package size must be less or equal than 20 characters!")
	@NotBlank(message = "Package size cannot be blank!")
	private String packageSize;
	
	@Column(name = "unit", length = 2, nullable = false)
//	@Size(max = 2, message = "Unit must be less or equal than 2 characters!")
	@NotNull(message = "Unit cannot be null!")
	@Enumerated(EnumType.STRING)
	private ProductUnit unit;
	
	@JsonIgnore
	@OneToOne(mappedBy = "product", cascade = CascadeType.REMOVE)
	private Stock stock;
	
	public Product() {
		
	}
	
	public Product(ProductDto product) {
		BeanUtils.copyProperties(product, this, "stock");
	}

	public String getPzn() {
		return pzn;
	}

	public void setPzn(String pzn) {
		this.pzn = pzn;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public String getPackageSize() {
		return packageSize;
	}

	public void setPackageSize(String packageSize) {
		this.packageSize = packageSize;
	}

	public ProductUnit getUnit() {
		return unit;
	}

	public void setUnit(ProductUnit unit) {
		this.unit = unit;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}
	
	
	
}
