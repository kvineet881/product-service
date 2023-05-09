package com.onlineshop.entity;

import jakarta.validation.constraints.NotBlank;

public class Category {
	@NotBlank(message = "category is mandatory should not be emplty")
    private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
  
    
}