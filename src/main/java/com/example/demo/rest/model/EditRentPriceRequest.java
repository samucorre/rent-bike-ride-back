package com.example.demo.rest.model;

@SuppressWarnings("serial")
public class EditRentPriceRequest extends CreateRentPriceRequest {

	private Integer id;
	
	public EditRentPriceRequest() {
		super();
	}
	public EditRentPriceRequest(Integer id, float rentPrice) {
		super(rentPrice);
		this.id=id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
}
