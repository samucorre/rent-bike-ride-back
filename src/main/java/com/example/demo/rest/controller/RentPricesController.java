package com.example.demo.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.exception.DemoException;
import com.example.demo.rest.model.CreateRentPriceRequest;
import com.example.demo.rest.model.EditRentPriceRequest;
import com.example.demo.rest.response.RentPriceResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.RentPriceService;

@RestController
@RequestMapping(RentPricesController.REQUEST_MAPPING)
public class RentPricesController {
	public static final String REQUEST_MAPPING = "rent_prices";

	private static final Logger LOGGER = LoggerFactory.getLogger(RentPricesController.class);

	@Autowired
	private RentPriceService rentPriceService;

	@GetMapping("getRentPrice")
	@PreAuthorize("hasAnyAuthority('RENTPRICES')")
	public RESTResponse<RentPriceResponse> getRentPrice(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getRentPrice in progress...");
		try {
			return rentPriceService.getRentPrice(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getRentPrice is finished...");
		}
	}

	@PostMapping(path = "/getRentPrices", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('RENTPRICES')")
	public @ResponseBody RESTResponse<List<RentPriceResponse>> getRentPrices(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getRentPrices in progress...");
		try {
			return rentPriceService.getRentPrices(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getRentPrices is finished...");
		}
	}

	@PostMapping(path = "/createRentPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('RENTPRICES')")
	public RESTResponse<Integer> createRentPrice(@RequestBody CreateRentPriceRequest createRentPriceRequest) {
		LOGGER.info("createRentPrice in progress...");
		try {
			return rentPriceService.createRentPrice(createRentPriceRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createRentPrice is finished...");
		}
	}
	@PostMapping(path = "/editRentPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('RENTPRICES')")
	public RESTResponse<Integer> editRentPrice(@RequestBody EditRentPriceRequest editRentPriceRequest) {
		LOGGER.info("editRentPrice in progress...");
		try {
			return rentPriceService.editRentPrice(editRentPriceRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editRentPrice is finished...");
		}
	}
	@DeleteMapping("deleteRentPrice")
	@PreAuthorize("hasAnyAuthority('RENTPRICES')")
	public RESTResponse<Integer> deleteRentPrice(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteRentPrice in progress...");
		try {
			return rentPriceService.deleteRentPrice(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteRentPrice is finished...");
		}
	}
}

