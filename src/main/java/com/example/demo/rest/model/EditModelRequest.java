package com.example.demo.rest.model;
import com.example.demo.entity.Brand;

@SuppressWarnings("serial")
public class EditModelRequest extends CreateModelRequest {

	private Integer id;
	
	public EditModelRequest() {
		super();
	}
	
	public EditModelRequest(Integer id, String model, Brand brand) {
		super(model, brand);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

}