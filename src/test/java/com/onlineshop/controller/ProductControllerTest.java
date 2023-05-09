package com.onlineshop.controller;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.onlineshop.entity.Product;
import com.onlineshop.service.ProductService;

class ProductControllerTest {
    
    @Mock
    private ProductService productService;
    
    @InjectMocks
    private ProductController productController;

    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
//        productController = new ProductController(productService);
    }
    
    @Test
    void testCreateProduct() {
        // Mock data
        Product product = new Product();
        when(productService.saveProduct(product)).thenReturn(product);
        
        // Call the API
        ResponseEntity<Product> response = productController.createProduct(product);
        
        // Verify the response
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).saveProduct(product);
    }
    
    @Test
    void testGetAllProducts() {
        // Mock data
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.getAllProduct()).thenReturn(productList);
        
        // Call the API
        List<Product> response = productController.getAllProducts();
        
        // Verify the response
        assertEquals(productList, response);
        verify(productService, times(1)).getAllProduct();
    }
    
    @Test
    void testGetProductById() {
        // Mock data
        String productId = "123";
        Product product = new Product();
        when(productService.getProductById(productId)).thenReturn(product);
        
        // Call the API
        ResponseEntity<Product> response = productController.getProductById(productId);
        
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).getProductById(productId);
    }
    
    @Test
    void testUpdateProduct() {
        // Mock data
        String productId = "123";
        Product product = new Product();
        when(productService.updateProduct(productId, product)).thenReturn(product);
        
        // Call the API
        ResponseEntity<Product> response = productController.updateProduct(productId, product);
        
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(product, response.getBody());
        verify(productService, times(1)).updateProduct(productId, product);
    }
    
    @Test
    void testDeleteProduct() {
        // Mock data
        String productId = "123";
        
        // Call the API
        ResponseEntity<String> response = productController.deleteProduct(productId);
        
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Product deleted successfully!.", response.getBody());
        verify(productService, times(1)).deleteProduct(productId);
    }
    
    @Test
    void testGetProductByCategory() throws Exception {
        // Mock data
        String category = "electronics";
        List<Product> productList = Arrays.asList(new Product(), new Product());
        when(productService.getProductByCategory(category)).thenReturn(productList);
        
        // Call the API
        ResponseEntity<List<Product>> response = productController.getProductByCategory(category);
        
        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
        verify(productService, times(1)).getProductByCategory(category);
    }
    
    @Test
    @DisplayName("Test getProductByCategoryWithPagination method")
    void testGetProductByCategoryWithPagination() {
        // Arrange
        String sortBy = "name";
        int pageNo = 0;
        int pageSize = 10;
        String sortOrder = "asc";
        List<Product> productList = Arrays.asList(new Product(), new Product());

        when(productService.getProductByCategoryWithPaging(sortBy, pageNo, pageSize))
                .thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> response = productController.getProductByCategoryWithPagination(
                sortBy, pageNo, pageSize, sortOrder);

        // Assert
        verify(productService, times(1)).getProductByCategoryWithPaging(sortBy, pageNo, pageSize);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

}