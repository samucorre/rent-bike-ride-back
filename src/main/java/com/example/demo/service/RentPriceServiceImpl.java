package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.borjaglez.springify.repository.specification.SpecificationBuilder;
import com.example.demo.entity.RentPrice;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.RentPriceError;
import com.example.demo.entity.messages.RentPriceMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.RentPriceRepository;
import com.example.demo.rest.model.CreateRentPriceRequest;
import com.example.demo.rest.model.EditRentPriceRequest;
import com.example.demo.rest.response.RentPriceResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;

@Service
public class RentPriceServiceImpl extends AbstractDemoService implements RentPriceService {

	@Autowired
	private RentPriceRepository rentPriceRepository;

	@Override
	@Transactional(readOnly = true)
	public RESTResponse<RentPriceResponse> getRentPrice(Integer id) {
		Optional<RentPrice> optRentPrice = rentPriceRepository.findById(id);
		if (!optRentPrice.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new RentPriceResponse(optRentPrice.get()));
	}

	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<RentPriceResponse>> getRentPrices(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<RentPrice> rentPrice = SpecificationBuilder.selectDistinctFrom(rentPriceRepository).where(pageFilter).findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) rentPrice.getTotalElements(),
				rentPrice.getContent().stream().map(RentPriceResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createRentPrice(CreateRentPriceRequest createRentPriceRequest) {
		try {
			checkInputParams(createRentPriceRequest);
			RentPrice newRentPrice = rentPriceRepository.save(fromCreateRentPriceRequest(createRentPriceRequest));
			rentPriceRepository.flush();
			return new RESTResponse<>(Code.OK, RentPriceMessages.RENTPRICE_CREATE_SUCCESS.toString(), newRentPrice.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("rentPrices_unique")) {
				throw new DemoException(RentPriceError.RENTPRICE_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editRentPrice(EditRentPriceRequest editRentPriceRequest) {
		try {
			checkInputParams(editRentPriceRequest);
			RentPrice editRentPrice = rentPriceRepository.save(fromEditRentPriceRequest(editRentPriceRequest));
			rentPriceRepository.flush();
			return new RESTResponse<>(Code.OK, RentPriceMessages.RENTPRICE_EDIT_SUCCESS.toString(), editRentPrice.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteRentPrice(Integer id) {
		try {
			rentPriceRepository.deleteById(id);
			rentPriceRepository.flush();
			return new RESTResponse<>(Code.OK, RentPriceMessages.RENTPRICE_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(RentPriceError.RENTPRICE_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		
	} catch (DataIntegrityViolationException e) {
		throw new DemoException(RentPriceError.RENTPRICE_DATA_INTEGRITY_VIOLATION.toString());
	}
}
	

}
