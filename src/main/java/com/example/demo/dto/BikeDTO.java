package com.example.demo.dto;

public class BikeDTO {

	private String brand;
	private String model;
	private String size;
	private String use;
	private Integer units;


	public String getBrand() {
		return brand;
	}

	public void setBrand (String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}


}

