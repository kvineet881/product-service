package com.onlineshop.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.entity.Product;
import com.onlineshop.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@Tag(name = "Online Shopping", description = "Online Shopping APIs")
@SecurityRequirement(name = "shopping-app")
@CrossOrigin(origins = "*")
public class ProductController {
	private Logger logger = LoggerFactory.getLogger(ProductController.class);
	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = "Save Product in DB", description = "save a Product object by specifying Product details. The response is Product object with id, name,brand,description,price and inventary, attributes.")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

	@PostMapping("/api/save")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
		logger.info("ProductController  is calling productService saveProduct() method");
		Product savedProduct = productService.saveProduct(product);
		return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
	}

	@Operation(summary = "Retrieve all Product", description = "Get all Product object. The response is list of  Product object with id, name,brand,description,price,inventary and attributes.")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

	@GetMapping("/api/products")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> getAllProducts() {
		logger.info("ProductController  is calling productService getAllProduct() method");
		return productService.getAllProduct();
	}

	@Operation(summary = "Retrieve a Product by Id", description = "Get a Product object by specifying its id. The response is Product object with id, name,brand,description,price,inventary and attributes")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/api/product/{id}")

	@Secured({ "ROLE_USER", "ROLE_ADMIN" })
	public ResponseEntity<Product> getProductById(@PathVariable("id") String productId) {
		logger.info("ProductController  is calling productService getProductById() method");
		Product savedProduct = productService.getProductById(productId);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@Operation(summary = "Update a Product by Id", description = "Update a Product object by specifying its id.The response is Product object with id, name,brand,description,price,inventary and attributes")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/api/product/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<Product> updateProduct(@PathVariable("id") String productId,
			@Valid @RequestBody Product product) {
		logger.info("ProductController  is calling productService updateProduct() method");
		Product savedProduct = productService.updateProduct(productId, product);

		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@Operation(summary = "Delete a Product by Id", description = "Delete a Product object by specifying its id. The response is string object for the delete operation")
	@ApiResponses({
			@ApiResponse(responseCode = "200", description = "deleted the book", content = {
					@Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", description = "Product not found", content = {
					@Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/api/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> deleteProduct(@PathVariable("id") String productId) {
		logger.info("ProductController  is calling productService deleteProduct() method");
		productService.deleteProduct(productId);

		return new ResponseEntity<String>("Product deleted successfully!.", HttpStatus.OK);

	}

	@Operation(summary = "Retrieve a Product by category", description = "Get a Product object by specifying its category. The response is List of Product object with id, name,brand,description,price,inventary and attributes")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	
	@GetMapping("/getProductByCategory/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) throws Exception {
		logger.info("ProductController  is calling productService getProductByCategory() method");
		List<Product> savedProduct = productService.getProductByCategory(category);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}

	@Operation(summary = "Retrieve a Product by category with pagination and sorting ", description = "Get a Product object by specifying its category. The response is List of Product object with id, name,brand,description,price,inventary and attributes")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Product.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getProductByCategoryWithPagination")
	public ResponseEntity<List<Product>> getProductByCategoryWithPagination(
			@RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
			@RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
			@RequestParam(value = "pagesize", defaultValue = "10") int pagesize,
			@RequestParam(value = "sortOrder", defaultValue = "asc") String sortOrder){
		logger.info("ProductController  is calling productService getProductByCategoryWithPaging() method");
		List<Product> savedProduct = productService.getProductByCategoryWithPaging(sortBy, pageNo, pagesize);
		return new ResponseEntity<>(savedProduct, HttpStatus.OK);
	}
	

}
