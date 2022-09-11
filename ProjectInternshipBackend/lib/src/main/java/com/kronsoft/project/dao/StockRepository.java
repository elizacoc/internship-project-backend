package com.kronsoft.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kronsoft.project.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long>{

}
