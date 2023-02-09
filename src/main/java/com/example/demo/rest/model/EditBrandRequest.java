package com.example.demo.rest.model;

@SuppressWarnings("serial")
public class EditBrandRequest extends CreateBrandRequest {

	private Integer id;
	
	public EditBrandRequest() {
		super();
	}
	
	public EditBrandRequest(Integer id, String brand) {
		super(brand);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
