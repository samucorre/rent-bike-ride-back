package com.example.demo.rest.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CreateSizeRequest implements Serializable {

	private Integer id;
	private String size;

	
	public CreateSizeRequest() {
		super();
	}

	public CreateSizeRequest(String size) {
		super();
		this.size = size;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}


}