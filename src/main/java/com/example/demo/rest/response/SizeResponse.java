package com.example.demo.rest.response;

import com.example.demo.entity.Size;

public class SizeResponse {
	private Integer id;
	private String size;

	public SizeResponse(Integer id, String size) {
		this.id = id;
		this.size = size;
	}

	public SizeResponse(Size size) {
		this.id = size.getId();		
		this.size = size.getSize();

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

	public void setSize(String Size) {
		this.size = size;
	}

}
