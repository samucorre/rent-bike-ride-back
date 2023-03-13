package com.example.demo.rest.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateRentPriceRequest implements Serializable {

	private Integer id;
	private float rentPrice;
	
	public CreateRentPriceRequest() {
		super();
	}
	public CreateRentPriceRequest(float rentPrice) {
		super();
		this.rentPrice = rentPrice;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(float rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	
}
