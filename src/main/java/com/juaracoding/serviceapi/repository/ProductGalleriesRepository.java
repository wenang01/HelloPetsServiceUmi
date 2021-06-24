package com.juaracoding.serviceapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.juaracoding.serviceapi.entity.ProductGalleries;
import com.juaracoding.serviceapi.entity.Products;

public interface ProductGalleriesRepository extends CrudRepository<ProductGalleries, Long>{

//	@Query(value="select image, products_id from product_galleries where products_id = :products_id")
//	List<ProductGalleries> findByProductsId(Long products_id);
//	List<ProductGalleries> findByProductsId(String products_id);
	
//	select t.id, t.name, t.description, count(p) as amount from Thing as t left join t.persons as p group by t.id
//	select t.id, t.name, t.description, count(p) as amount from Person as p right join p.thing as t group by t.id
	
	
}
