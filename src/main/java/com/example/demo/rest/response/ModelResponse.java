package com.example.demo.rest.response;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Model;

public class ModelResponse {
	private Integer id;
	private String model;
	private Brand brand;

	public ModelResponse(Integer id, String model, Brand brand) {
		this.id = id;
		this.model = model;
		this.brand = brand;
	}

	public ModelResponse(Model model) {
		this.id = model.getId();
		this.model = model.getModel();
		this.brand = model.getBrand();
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