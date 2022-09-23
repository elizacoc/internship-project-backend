package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kronsoft.project.entities.Product;
import com.kronsoft.project.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

	Stock findByProductPzn(String pzn);

	boolean existsByProductPzn(String pzn);
	
	@Query("SELECT s FROM Stock s WHERE s.id = :id")
	Stock findByStockId(@Param("id") Long id);
	

}
