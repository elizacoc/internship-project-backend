package com.kronsoft.project.entities;

import org.springframework.lang.Nullable;

public enum ProductUnit {

	St("St"),
	
	ml("ml");
	
	private static final ProductUnit[] VALUES;

	static {
		VALUES = values();
	}
	
	private final String key;
	
	private ProductUnit(String key) {
		this.key = key;
	}
	
	public String getKey() {
		return key;
	}
	
//	public static ProductUnit valueOf(String givenUnit) {
//		ProductUnit unit = resolve(givenUnit);
//		if (unit == null) {
//			throw new IllegalArgumentException("No matching constant for [" + givenUnit + "]");
//		}
//		return unit;
//	}
	
	@Nullable
	public static ProductUnit resolve(String givenUnit) {
		// Use cached VALUES instead of values() to prevent array allocation.
		for (ProductUnit unit : VALUES) {
			if (unit.key == givenUnit) {
				return unit;
			}
		}
		return null;
	}

}
