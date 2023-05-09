package com.onlineshop.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import com.onlineshop.entity.Attributes;
import com.onlineshop.entity.Category;
import com.onlineshop.entity.Inventory;
import com.onlineshop.entity.Price;
import com.onlineshop.entity.Product;

//@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ProductRepositoryTest {

	@Autowired
	private ProductRepository productRepository;

	private Product product;

	@BeforeEach
	public void setup() {
		Price price = new Price();
		price.setAmount(49.99);
		price.setCurrency("USD");

		Inventory inventory = new Inventory();
		inventory.setAvailable(40);
		inventory.setReserved(10);
		inventory.setTotal(50);

		Attributes attributes = new Attributes();
		attributes.setName("Color");
		attributes.setValue("blue");
		List<Attributes> attributesList = Arrays.asList(attributes);

		Category category = new Category();
		category.setName("Shirt");

		product = new Product("644a7e96590bb9082f919423", "Men's Striped Dress Shirt", "Brand Name",
				"Classic striped dress shirt for formal occasions.", price, inventory, attributesList, category);
	}

    @DisplayName("JUnit test for save product operation")
	@Test
	public void givenProductObject_whenSave_thenReturnSavedProduct() {

		// given - precondition or setup

		// when - action or the behaviour that we are going test
		Product savedProduct = productRepository.save(product);

		// then - verify the output
		assertThat(savedProduct).isNotNull();
		assertThat(savedProduct.getId()).hasSizeGreaterThan(0);
	}
	
	 @DisplayName("JUnit test for get product by id operation")
	    @Test
	    public void givenProductObject_whenFindById_thenReturnProductObject(){
	        // given - precondition or setup
	        productRepository.save(product);

	        // when -  action or the behaviour that we are going test
	        Product productDB = productRepository.findById(product.getId()).get();

	        // then - verify the output
	        assertThat(productDB).isNotNull();
	    }
	 
	 @DisplayName("JUnit test for update product operation")
	    @Test
	    public void givenProductObject_whenUpdateProduct_thenReturnUpdatedProduct(){
	        productRepository.save(product);
	        // when -  action or the behaviour that we are going test
	        Product savedProduct = productRepository.findById(product.getId()).get();
	        savedProduct.setName("Book");
	        savedProduct.setBrand("Hindi");
	        Product updatedProduct =  productRepository.save(savedProduct);

	        // then - verify the output
	        assertThat(updatedProduct.getName()).isEqualTo("Book");
	        assertThat(updatedProduct.getBrand()).isEqualTo("Hindi");
	    }

	 // JUnit test for delete product operation
	    @DisplayName("JUnit test for delete product operation")
	    @Test
	    public void givenProductObject_whenDelete_thenRemoveProduct(){

	        productRepository.save(product);

	        // when -  action or the behaviour that we are going test
	        productRepository.deleteById(product.getId());
	        Optional<Product> productOptional = productRepository.findById(product.getId());

	        // then - verify the output
	        assertThat(productOptional).isEmpty();
	    }
	    
	    // JUnit test for get product by category operation
	    @DisplayName("JUnit test for get product by findByCategoryNameIgnoreCase operation")
	    @Test
	    public void givenProductfindByCategoryName_whenFindByfindByCategoryName_thenReturnProductObject(){

	        productRepository.save(product);

	        // when -  action or the behaviour that we are going test
	        List<Product> productDB = productRepository.findByCategoryNameIgnoreCase(product.getCategory().getName());

	        // then - verify the output
	        assertThat(productDB).isNotNull();
	    }

}
