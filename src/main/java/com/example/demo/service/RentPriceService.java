package com.example.demo.service;

import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.rest.model.CreateRentPriceRequest;
import com.example.demo.rest.model.EditRentPriceRequest;
import com.example.demo.rest.response.RentPriceResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;

public interface RentPriceService {

	RESTResponse<RentPriceResponse> getRentPrice(Integer id);

	DataSourceRESTResponse<List<RentPriceResponse>> getRentPrices(AnyPageFilter pageFilter);

	RESTResponse<Integer> createRentPrice(CreateRentPriceRequest createRentPriceRequest);

	RESTResponse<Integer> editRentPrice(EditRentPriceRequest editRentPriceRequest);

	RESTResponse<Integer> deleteRentPrice(Integer id);
}
