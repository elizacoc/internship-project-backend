package com.kronsoft.project.dto;

import org.springframework.beans.BeanUtils;

import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.ProductUnit;

public class ProductDto {

	private String pzn;
	
	private String supplier;
	
	private String productName;
	
	private String strength;
	
	private String packageSize;
	
	private ProductUnit unit;
	
	public ProductDto() {
		
	}

	public ProductDto(Product product) {
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
	
}
