package com.onlineshop.entity;

public class Inventory {

	private int total;
	private int available;
	private int reserved;
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Inventory(int total, int available, int reserved) {
		super();
		this.total = total;
		this.available = available;
		this.reserved = reserved;
	}
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public int getReserved() {
		return reserved;
	}
	public void setReserved(int reserved) {
		this.reserved = reserved;
	}
	
}
