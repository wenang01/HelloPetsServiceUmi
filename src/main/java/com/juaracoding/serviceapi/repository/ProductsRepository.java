package com.juaracoding.serviceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.juaracoding.serviceapi.entity.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{
	
	
	List<Products> findByProductName(String productName);
	
}
