package com.onlineshop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.onlineshop.entity.Attributes;
import com.onlineshop.entity.Category;
import com.onlineshop.entity.Inventory;
import com.onlineshop.entity.Price;
import com.onlineshop.entity.Product;
import com.onlineshop.exception.ResourceNotFoundException;
import com.onlineshop.repository.ProductRepository;
import com.onlineshop.utils.Utils;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productServiceImpl;
	@Mock
	private Utils utils;

	@Mock
	private Logger logger;

	private Product product;
	private Price price;

	private Inventory inventory;
	private List<Attributes> attributesList;
	private Attributes attributes;
	private Category category;

	@BeforeEach
	public void setup() {
		price = new Price();
		price.setAmount(49.99);
		price.setCurrency("USD");

		inventory = new Inventory();
		inventory.setAvailable(40);
		inventory.setReserved(10);
		inventory.setTotal(50);

		attributes = new Attributes();
		attributes.setName("Color");
		attributes.setValue("blue");
		attributesList = Arrays.asList(attributes);

		category = new Category();
		category.setName("Shirt");

		product = new Product("644a7e96590bb9082f919423", "Men's Striped Dress Shirt", "Brand Name",
				"Classic striped dress shirt for formal occasions.", price, inventory, attributesList, category);
		product.setId(utils.generateUserId(10));
	}

	// JUnit test for saveProduct method
	@DisplayName("JUnit test for saveProduct method")
	@Test
	public void givenProductObject_whenSaveProduct_thenReturnProductObject() {
		// given - precondition or setup
		// given(productRepository.findByEmail(product.getEmail()))
		// .willReturn(Optional.empty());
		product.setId(utils.generateUserId(10));

		given(productRepository.save(product)).willReturn(product);

		System.out.println(productRepository);
		System.out.println(productServiceImpl);

		// when - action or the behaviour that we are going test
		Product savedProduct = productServiceImpl.saveProduct(product);

		System.out.println(savedProduct);
		// then - verify the output
		assertThat(savedProduct).isNotNull();
	}

//
	// JUnit test for saveProduct method
	@DisplayName("JUnit test for saveProduct method which throws exception")
	@Test
	public void givenExistingEmail_whenSaveProduct_thenThrowsException() {
		// given - precondition or setup
		product.setId(utils.generateUserId(10));
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

		// when - action or the behaviour that we are going test
		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.saveProduct(product);
		});

		// then
		verify(productRepository, never()).save(any(Product.class));
	}

//
	// JUnit test for getAllProducts method
	@DisplayName("JUnit test for getAllProducts method")
	@Test
	public void givenProductsList_whenGetAllProducts_thenReturnProductsList() {
		// given - precondition or setup

		Product product1 = product = new Product("644a7e96590bb9082f919423", "Men's Striped Dress Shirt", "Brand Name",
				"Classic striped dress shirt for formal occasions.", price, inventory, attributesList, category);

		given(productRepository.findAll()).willReturn(List.of(product, product1));

		// when - action or the behaviour that we are going test
		List<Product> productList = productServiceImpl.getAllProduct();
		// then - verify the output
		assertThat(productList).isNotNull();
		assertThat(productList.size()).isEqualTo(2);
	}

	// JUnit test for getAllProducts method
	@DisplayName("JUnit test for getAllProducts method (negative scenario)")
	@Test
	public void givenEmptyProductsList_whenGetAllProducts_thenReturnEmptyProductsList() {
		// given - precondition or setup

		given(productRepository.findAll()).willReturn(Collections.emptyList());

		// then - verify the output
		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.getAllProduct();
		});

	}

	// JUnit test for getProductById method
	@DisplayName("JUnit test for getProductById method")
	@Test
	public void givenProductId_whenGetProductById_thenReturnProductObject() {
		// given
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));

		// when
		Product savedProduct = productServiceImpl.getProductById(product.getId());

		// then
		assertThat(savedProduct).isNotNull();

	}

	// JUnit test for getProductById method
	@DisplayName("JUnit test for getProductById  method (negative scenario)")
	@Test
	public void givenProductId_whenGetProductById_thenReturnNull() {
		// given
		given(productRepository.findById(product.getId())).willReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.getProductById(product.getId());
		});

	}

	// JUnit test for updateProduct method
	@DisplayName("JUnit test for updateProduct method")
	@Test
	public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct() {
		// given - precondition or setup
		product.setId(utils.generateUserId(10));
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		given(productRepository.save(product)).willReturn(product);
		product.setName("Shirt");
		product.setBrand("Levice");
		// when - action or the behaviour that we are going test
		Product updatedProduct = productServiceImpl.updateProduct(product.getId(), product);

		// then - verify the output
		assertThat(updatedProduct.getName()).isEqualTo("Shirt");
		assertThat(updatedProduct.getBrand()).isEqualTo("Levice");
	}

	@DisplayName("JUnit test for updateProduct method (negative scenario)")
	@Test
	public void givenProductObjectWithInvalidId_whenUpdateProduct_thenReturnException() {
		// given - precondition or setup
		product.setId(utils.generateUserId(10));
		given(productRepository.findById(product.getId())).willReturn(Optional.empty());
		// given(productRepository.save(product)).willReturn(product);

		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.updateProduct(product.getId(), product);
		});
	}

	// JUnit test for deleteProduct method
	@DisplayName("JUnit test for deleteProduct method")
	@Test
	public void givenProductId_whenDeleteProduct_thenGetException() {
		product.setId(utils.generateUserId(10));
		given(productRepository.findById(product.getId())).willReturn(Optional.of(product));
		// given - precondition or setup

		willDoNothing().given(productRepository).deleteById(product.getId());
		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.deleteProduct(product.getId());
		});

	}

	// JUnit test for getProductById method
	@DisplayName("JUnit test for getProductByCategory method")
	@Test
	public void givenProductCategoryName_whenGetProductByCategory_thenReturnListProductObject() {
		Product product1 = product = new Product("644a7e96590bb9082f919423", "Men's Striped Dress Shirt", "Brand Name",
				"Classic striped dress shirt for formal occasions.", price, inventory, attributesList, category);
		// given
		given(productRepository.findByCategoryNameIgnoreCase(product.getCategory().getName()))
				.willReturn(List.of(product, product1));

		// when
		List<Product> productList = productServiceImpl.getProductByCategory(product.getCategory().getName());
		// then - verify the output
		assertThat(productList).isNotNull();
		assertThat(productList.size()).isEqualTo(2);

	}

	// JUnit test for getProductById method
	@DisplayName("JUnit test for getProductByCategory with invalid name method")
	@Test
	public void givenProductCategoryNameInvalid_whenGetProductByCategoryInvalid_thenThrowsException() {

		given(productRepository.findByCategoryNameIgnoreCase(product.getCategory().getName()))
				.willReturn(Collections.emptyList());

		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.getProductByCategory(product.getCategory().getName());
		});

	}

	@DisplayName("JUnit test for getProductByCategoryWithPagination method")
	@Test
	public void testGetProductByCategoryWithPaging() {
		// Arrange
		String sortBy = "name";
		int pageNo = 0;
		int pageSize = 10;
		Product product1 = product = new Product("644a7e96590bb9082f919423", "Men's Striped Dress Shirt", "Brand Name",
				"Classic striped dress shirt for formal occasions.", price, inventory, attributesList, category);
		List<Product> productList = new ArrayList<>();
		productList.add(product);
		productList.add(product1);
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		PageImpl<Product> page = new PageImpl<>(productList, pageable, productList.size());
		when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

		// Act
		List<Product> result = productServiceImpl.getProductByCategoryWithPaging(sortBy, pageNo, pageSize);

		// Assert
		assertEquals(productList, result);
		assertTrue(result.size() == productList.size());
	}

	@DisplayName("JUnit test for getProductByCategoryWithPagination negative method")
	@Test
	public void testGetProductByCategoryWithPagingnegative() {
		// Arrange
		String sortBy = "name";
		int pageNo = 0;
		int pageSize = 10;
		List<Product> productList = new ArrayList<>();
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		PageImpl<Product> page = new PageImpl<>(productList, pageable, productList.size());
		when(productRepository.findAll(any(Pageable.class))).thenReturn(page);

		assertThrows(ResourceNotFoundException.class, () -> {
			productServiceImpl.getProductByCategoryWithPaging(sortBy, pageNo, pageSize);

		});
	}

}
