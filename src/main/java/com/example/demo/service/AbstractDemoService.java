package com.example.demo.service;

import org.apache.commons.lang3.StringUtils;

import com.borjaglez.springify.repository.filter.IPageFilter;
import com.example.demo.entity.error.QuerySortPaginationError;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.QuerySortPaginationRequest;

import com.example.demo.entity.Contact;
import com.example.demo.entity.error.ContactError;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditContactRequest;
//
import com.example.demo.entity.Bike;
import com.example.demo.entity.error.BikeError;
import com.example.demo.rest.model.CreateBikeRequest;
import com.example.demo.rest.model.EditBikeRequest;
//
import com.example.demo.entity.Brand;
import com.example.demo.entity.error.BrandError;
import com.example.demo.rest.model.CreateBrandRequest;
import com.example.demo.rest.model.EditBrandRequest;
//
import com.example.demo.entity.Model;
import com.example.demo.entity.error.ModelError;
import com.example.demo.rest.model.CreateModelRequest;
import com.example.demo.rest.model.EditModelRequest;
//
import com.example.demo.entity.Size;
import com.example.demo.entity.error.SizeError;
import com.example.demo.rest.model.CreateSizeRequest;
import com.example.demo.rest.model.EditSizeRequest;
//
import com.example.demo.entity.RentPrice;
import com.example.demo.entity.error.RentPriceError;
import com.example.demo.rest.model.CreateRentPriceRequest;
import com.example.demo.rest.model.EditRentPriceRequest;


public abstract class AbstractDemoService {

	protected void checkInputParams(IPageFilter pageFilter) {
		if (pageFilter.getPageNumber() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_INDEX_REQUIRED.toString());
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_SIZE_REQUIRED.toString());
		}
	}

	protected void checkInputParams(QuerySortPaginationRequest pageFilter) {
		if (pageFilter.getPageIndex() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_INDEX_REQUIRED.toString());
		}
		if (pageFilter.getPageSize() == null) {
			throw new DemoException(QuerySortPaginationError.PAGE_SIZE_REQUIRED.toString());
		}
	}

	protected void checkInputParams(CreateContactRequest createContactRequest) {
		if (StringUtils.isBlank(createContactRequest.getName())) {
			throw new DemoException(ContactError.NAME_REQUIRED.toString());
		}
		if (StringUtils.isBlank(createContactRequest.getSurname1())) {
			throw new DemoException(ContactError.SURNAME1_REQUIRED.toString());
		}
		if (createContactRequest.getPhone() == null) {
			throw new DemoException(ContactError.PHONE_REQUIRED.toString());
		}
	}

	protected void checkInputParams(EditContactRequest editContactRequest) {
		if (editContactRequest.getId() == null) {
			throw new DemoException(ContactError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateContactRequest) editContactRequest);
	}

	public Contact fromEditContactRequest(EditContactRequest contactRequest) {
		return new Contact(contactRequest.getId(), contactRequest.getName(), contactRequest.getSurname1(),
				contactRequest.getSurname2(), contactRequest.getPhone(), contactRequest.getEmail());

	}

	public Contact fromCreateContactRequest(CreateContactRequest contactRequest) {
		return new Contact(contactRequest.getName(), contactRequest.getSurname1(), contactRequest.getSurname2(),
				contactRequest.getPhone(), contactRequest.getEmail());
	}
/////

	protected void checkInputParams(CreateBikeRequest createBikeRequest) {
		if (createBikeRequest.getBrand() == null) {
			throw new DemoException(BikeError.BRAND_REQUIRED.toString());
		}
		if (createBikeRequest.getModel() == null) {
			throw new DemoException(BikeError.MODEL_REQUIRED.toString());
		}
		
	}

	protected void checkInputParams(EditBikeRequest editBikeRequest) {
		if (editBikeRequest.getId() == null) {
			throw new DemoException(BikeError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateBikeRequest) editBikeRequest);
	}

	public Bike fromEditBikeRequest(EditBikeRequest bikeRequest) {
		return new Bike(bikeRequest.getId(), bikeRequest.getBrand(), bikeRequest.getModel(),
				bikeRequest.getSize(), bikeRequest.getUse(), bikeRequest.getUnits());

	}

	public Bike fromCreateBikeRequest(CreateBikeRequest bikeRequest) {
		return new Bike(bikeRequest.getBrand(), bikeRequest.getModel(), bikeRequest.getSize(),
				bikeRequest.getUse(), bikeRequest.getUnits());
	}
	/////
	protected void checkInputParams(CreateBrandRequest createBrandRequest) {
		if (StringUtils.isBlank(createBrandRequest.getBrand())) {
			throw new DemoException(BrandError.BRAND_REQUIRED.toString());
		}
				
	}

	protected void checkInputParams(EditBrandRequest editBrandRequest) {
		if (editBrandRequest.getId() == null) {
			throw new DemoException(BrandError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateBrandRequest) editBrandRequest);
	}

	public Brand fromEditBrandRequest(EditBrandRequest brandRequest) {
		return new Brand(brandRequest.getId(), brandRequest.getBrand());

	}

	public Brand fromCreateBrandRequest(CreateBrandRequest brandRequest) {
		return new Brand(brandRequest.getBrand());
	}
//////
	protected void checkInputParams(CreateModelRequest createModelRequest) {
		if (StringUtils.isBlank(createModelRequest.getModel())) {
			throw new DemoException(ModelError.MODEL_REQUIRED.toString());
		}
	}

	protected void checkInputParams(EditModelRequest editModelRequest) {
		if (editModelRequest.getId() == null) {
			throw new DemoException(ModelError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateModelRequest) editModelRequest);
	}

	public Model fromEditModelRequest(EditModelRequest modelRequest) {
		return new Model(modelRequest.getId(), modelRequest.getModel(), modelRequest.getBrand());

	}

	public Model fromCreateModelRequest(CreateModelRequest modelRequest) {
		return new Model(modelRequest.getModel());
	}

	protected void checkInputParams(CreateSizeRequest createSizeRequest) {
		if (StringUtils.isBlank(createSizeRequest.getSize())) {
			throw new DemoException(SizeError.SIZE_REQUIRED.toString());

	}
}
protected void checkInputParams(EditSizeRequest editSizeRequest) {
		if (editSizeRequest.getId() == null) {
			throw new DemoException(SizeError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateSizeRequest) editSizeRequest);
	}

	public Size fromEditSizeRequest(EditSizeRequest sizeRequest) {
		return new Size(sizeRequest.getId(), sizeRequest.getSize());

	}

	public Size fromCreateSizeRequest(CreateSizeRequest sizeRequest) {
		return new Size(sizeRequest.getSize());
	}
	
	//
	protected void checkInputParams(CreateRentPriceRequest createRentPriceRequest) {
		if (StringUtils.isBlank(createRentPriceRequest.getRentPrice())) {
			throw new DemoException(RentPriceError.RENTPRICE_REQUIRED.toString());

	}
}
protected void checkInputParams(EditRentPriceRequest editRentPriceRequest) {
		if (editRentPriceRequest.getId() == null) {
			throw new DemoException(RentPriceError.ID_REQUIRED.toString());
		}
		checkInputParams((CreateRentPriceRequest) editRentPriceRequest);
	}

	public RentPrice fromEditRentPriceRequest(EditRentPriceRequest rentPriceRequest) {
		return new RentPrice(rentPriceRequest.getId(), rentPriceRequest.getRentPrice());

	}

	public RentPrice fromCreateRentPriceRequest(CreateRentPriceRequest rentPriceRequest) {
		return new RentPrice(rentPriceRequest.getRentPrice());
	}
}