package com.onlineshop.service;

import java.util.List;
import java.util.Optional;

import com.onlineshop.entity.Product;

public interface ProductService {
	Product saveProduct(Product product);
    List<Product> getAllProduct();
    Product getProductById(String id);
    Product updateProduct( String productId, Product product);
    void deleteProduct(String id);
	List<Product> getProductByCategory(String category);
	List<Product> getProductByCategoryWithPaging(String sortBy, int pageNo, int pagesize);
}

