package com.example.demo.rest.response;

import com.example.demo.entity.Brand;

public class BrandResponse {
	private Integer id;
	private String brand;

	public BrandResponse(Integer id, String brand) {
		this.id = id;
		this.brand = brand;
			}

	public BrandResponse(Brand brand) {
		this.id = brand.getId();
		this.brand = brand.getBrand();
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
}
