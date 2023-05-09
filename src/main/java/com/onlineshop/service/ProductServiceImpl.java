package com.onlineshop.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onlineshop.controller.ProductController;
import com.onlineshop.entity.Product;
import com.onlineshop.exception.ResourceNotFoundException;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.utils.Utils;

@Service
public class ProductServiceImpl implements ProductService {
	private Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	private Utils utils;
	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository, Utils utils) {
		this.productRepository = productRepository;
		this.utils = utils;
	}

	@Override
	public Product saveProduct(Product product) {
		logger.info("productService  saveProduct() method calling..");
		product.setId(utils.generateUserId(10));
		Optional<Product> savedProduct = productRepository.findById(product.getId());
		if (savedProduct.isPresent()) {
			logger.info("Product already exist with given id:" + product.getId());
			throw new ResourceNotFoundException("Product already exist with given id:" + product.getId());
		}
		return productRepository.save(product);
	}

	@Override
	public List<Product> getAllProduct() {

		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			logger.info("Product databse is empty !");
			throw new ResourceNotFoundException("Product databse is empty !");
		}
		return products;
	}

	@Override
	public Product getProductById(String productId) {
		logger.info("productService  getProductById() method calling..");
		Product savedProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));
		return savedProduct;
	}

	@Override
	public Product updateProduct(String productId, Product product) {
		logger.info("productService  updateProduct() method calling..");
		Product savedProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ResourceNotFoundException("Product not found for this id :: " + productId));

		savedProduct.setName(product.getName());
		savedProduct.setBrand(product.getBrand());
		savedProduct.setDescription(product.getDescription());
		savedProduct.setCategory(product.getCategory());
		savedProduct.setPrice(product.getPrice());
		savedProduct.setInventory(product.getInventory());
		savedProduct.setAttributes(product.getAttributes());
		Product updatedProduct = productRepository.save(savedProduct);
		logger.info("productService  updateProduct() method calling done..");
		return updatedProduct;
	}

	@Override
	public void deleteProduct(String id) {
		logger.info("productService  deleteProduct() method calling..");
		Optional<Product> savedProduct = productRepository.findById(id);
		if (savedProduct.isPresent()) {
			productRepository.deleteById(id);
			logger.info("product deleted : "+id);
		} else {
			logger.info("Product is not exist with given id : " + id);
			throw new ResourceNotFoundException("Product is not exist with given id:" + id);
		}
	}

	@Override
	public List<Product> getProductByCategory(String category) {
		logger.info("productService  getProductByCategory() method calling..");
		List<Product> products = productRepository.findByCategoryNameIgnoreCase(category);
	
		if (products.isEmpty())
		{
			logger.info("Product of " + category + " category is not available in databse !");
			throw new ResourceNotFoundException("Product of " + category + " category is not available in databse !");
		}
		return products;

	}

	@Override
	public List<Product> getProductByCategoryWithPaging(String sortBy, int pageNo, int pagesize) {
		logger.info("productService  getProductByCategoryWithPaging() method calling..");

		Sort sort = Sort.by(Sort.Direction.ASC,sortBy);
		Pageable pageable = PageRequest.of(pageNo, pagesize,sort);
		Page<Product> products = productRepository.findAll(pageable);
		List<Product>productList= products.getContent();
		if (products.isEmpty())
		{   logger.info("Product is not available in databse !"); 
			throw new ResourceNotFoundException("Product is not available in databse !");
		}
		return productList;
	}


}
