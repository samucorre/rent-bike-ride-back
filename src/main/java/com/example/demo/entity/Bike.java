package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "bikes")
public class Bike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String use;
	private Integer units;
	 
	@ManyToOne
	@JoinColumn(name="brand")
	private Brand brand;
	@ManyToOne
	@JoinColumn(name="model")
	private Model model;
	@ManyToOne
	@JoinColumn(name="size")
	private Size size;

	

	public Bike() {
	}
	
	public Bike(Brand brand, Model model, Size size, String use, Integer units) {
		this.brand = brand;
		this.model = model;
		this.size = size;
		this.use=use;
		this.units=units;
	}

	public Bike(Integer id, Brand brand, Model model, Size size, String use, Integer units) {
		this(brand, model, size,use,units);
		this.id = id;
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
