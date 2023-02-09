package com.example.demo.rest.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateBrandRequest implements Serializable {

	private Integer id;
	private String brand;
		
	public CreateBrandRequest() {
		super();
	}

	public CreateBrandRequest( String brand) {
		super();
		this.brand = brand;
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
