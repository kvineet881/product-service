package com.onlineshop.entity;

public class Attributes {
	private String name;
	private String value;
	
	public Attributes() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Attributes(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	

}
