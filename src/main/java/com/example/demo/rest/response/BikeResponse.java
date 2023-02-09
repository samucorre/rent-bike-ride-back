package com.example.demo.rest.response;

import com.example.demo.entity.Bike;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Model;
import com.example.demo.entity.Size;

public class BikeResponse {
	private Integer id;
	private Brand brand;
	private Model model;
	private Size size;
	private String use;
	private Integer units;

	public BikeResponse(Integer id, Brand brand, Model model, Size size, String use,Integer units) {
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.size = size;
		this.use = use;
		this.units=units;
	}

	public BikeResponse(Bike bike) {
		this.id = bike.getId();
		this.brand = bike.getBrand();
		this.model = bike.getModel();
		this.size = bike.getSize();
		this.use = bike.getUse();
		this.units=bike.getUnits();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}
	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public Integer getUnits() {
		return units;
	}

	public void setUnits(Integer units) {
		this.units = units;
	}


}
