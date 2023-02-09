package com.example.demo.rest.model;
import com.example.demo.entity.Brand;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateModelRequest implements Serializable {

	private Integer id;
	private String model;
	private Brand brand;

	
	public CreateModelRequest() {
		super();
	}

	public CreateModelRequest(String model, Brand brand) {
		super();
		this.model = model;
		this.brand = brand;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}


}