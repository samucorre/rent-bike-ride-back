package com.example.demo.rest.model;

@SuppressWarnings("serial")
public class EditSizeRequest extends CreateSizeRequest {

	private Integer id;
	
	public EditSizeRequest() {
		super();
	}
	
	public EditSizeRequest(Integer id, String size) {
		super(size);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
