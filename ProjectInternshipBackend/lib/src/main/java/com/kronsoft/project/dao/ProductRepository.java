package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kronsoft.project.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String>{

	boolean existsByPzn(String pzn);

	
}
