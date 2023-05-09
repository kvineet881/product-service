package com.onlineshop.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Document(collection = "products")
public class Product{

	@Id
	private String id;
	@NotBlank(message = "Name is mandatory should not be emplty")
	private String name;
	@NotBlank(message = "Brand is mandatory should not be emplty")
	private String brand;
    @NotBlank(message = "Description is mandatory should not be emplty")
	private String description;
    @NotNull(message = "Price details is mandatory should not be emplty")
	private Price price;
	private Inventory inventory;
	@NotEmpty
	private List<Attributes> attributes;
	private Category category;
	 
	public Product() {
		super();
	}

	public Product(String id, @NotBlank(message = "Name is mandatory should not be emplty") String name,
			@NotBlank(message = "Brand is mandatory should not be emplty") String brand,
			@NotBlank(message = "Description is mandatory should not be emplty") String description,
			@NotNull(message = "Price details is mandatory should not be emplty") Price price, Inventory inventory,
			@NotEmpty List<Attributes> attributes,
		    Category category) {
		super();
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.inventory = inventory;
		this.attributes = attributes;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public List<Attributes> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attributes> attributes) {
		this.attributes = attributes;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	

	
	
	
}
	