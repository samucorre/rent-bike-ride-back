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
import com.example.demo.entity.Bike;
import com.example.demo.entity.Contact;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.ContactError;
import com.example.demo.entity.error.BikeError;
import com.example.demo.entity.messages.BikeMessages;
import com.example.demo.entity.messages.ContactMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.BikeRepository;
import com.example.demo.repository.ContactRepository;
import com.example.demo.rest.model.CreateBikeRequest;
import com.example.demo.rest.model.CreateContactRequest;
import com.example.demo.rest.model.EditBikeRequest;
import com.example.demo.rest.model.EditContactRequest;
import com.example.demo.rest.response.BikeResponse;
import com.example.demo.rest.response.ContactResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;

@Service
public class BikeServiceImpl extends AbstractDemoService implements BikeService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private BikeRepository bikeRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public RESTResponse<BikeResponse> getBike(Integer id) {
		Optional<Bike> optBike = bikeRepository.findById(id);
		if (!optBike.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new BikeResponse(optBike.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<BikeResponse>> getBikes(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Bike> bikes = SpecificationBuilder.selectDistinctFrom(bikeRepository).where(pageFilter)
				.findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) bikes.getTotalElements(),
				bikes.getContent().stream().map(BikeResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createBike(CreateBikeRequest createBikeRequest) {
		try {
			checkInputParams(createBikeRequest);
			Bike newBike = bikeRepository.save(fromCreateBikeRequest(createBikeRequest));
//			contactRepository.flush();
			return new RESTResponse<>(Code.OK, BikeMessages.BIKE_CREATE_SUCCESS.toString(), newBike.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("bikes_unique")) {
				throw new DemoException(BikeError.BIKE_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editBike(EditBikeRequest editBikeRequest) {
		try {
			checkInputParams(editBikeRequest);
			Bike editBike = bikeRepository.save(fromEditBikeRequest(editBikeRequest));
			bikeRepository.flush();
			return new RESTResponse<>(Code.OK, BikeMessages.BIKE_EDIT_SUCCESS.toString(), editBike.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteBike(Integer id) {
		try {
			bikeRepository.deleteById(id);
			bikeRepository.flush();
			return new RESTResponse<>(Code.OK, BikeMessages.BIKE_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(BikeError.BIKE_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		}
	}
}

