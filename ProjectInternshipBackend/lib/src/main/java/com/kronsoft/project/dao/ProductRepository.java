package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kronsoft.project.dto.ProductDto;
import com.kronsoft.project.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	boolean existsByPzn(String pzn);

	ProductDto findByPzn(String pzn);

	@Query("SELECT p FROM Product p WHERE p.pzn = :pzn")
	Product findProductByPzn(@Param("pzn") String pzn);
}
