package com.example.demo.rest.response;

import com.example.demo.entity.RentPrice;

public class RentPriceResponse {
	private Integer id;
	private float rentPrice;

	public RentPriceResponse(Integer id, float rentPrice) {
		this.id = id;
		this.rentPrice = rentPrice;
	}

	public RentPriceResponse(RentPrice rentPrice) {
		this.id = rentPrice.getId();
		this.rentPrice = rentPrice.getRentPrice();
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
