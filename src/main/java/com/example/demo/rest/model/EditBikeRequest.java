package com.example.demo.rest.model;
import com.example.demo.entity.Brand;
import com.example.demo.entity.Model;
import com.example.demo.entity.Size;

@SuppressWarnings("serial")
public class EditBikeRequest extends CreateBikeRequest {

	private Integer id;
	
	public EditBikeRequest() {
		super();
	}
	
	public EditBikeRequest(Integer id, Brand brand, Model model, Size size, String use, Integer units) {
		super(brand, model, size, use, units);
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
}
