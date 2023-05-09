package com.onlineshop.repository;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.entity.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	List<Product> findByCategoryNameIgnoreCase(String category); 

}
