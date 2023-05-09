package com.onlineshop.entity;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

public class Price {
	
	    @NotNull(message = "currency type is required,Please Enter currency like USD,INR")
		private String currency;
		 @DecimalMin(value = "1.00", message = "Price must be greater than or equal to 0.00")
		 @Digits(integer=10, fraction=2, message = "Price must have a maximum of 10 integer digits and 2 decimal places")
		private double amount;
		
		public Price() {
			super();
			
		}
		
		public Price(String currency, double amount) {
			super();
			this.currency = currency;
			this.amount = amount;
		}

		public String getCurrency() {
			return currency;
		}
		public void setCurrency(String currency) {
			this.currency = currency;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}

		
}
