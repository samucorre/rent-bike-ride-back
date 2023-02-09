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
import com.example.demo.entity.Brand;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.BikeError;
import com.example.demo.entity.error.BrandError;
import com.example.demo.entity.messages.BrandMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.BrandRepository;
import com.example.demo.rest.model.CreateBrandRequest;
import com.example.demo.rest.model.EditBrandRequest;
import com.example.demo.rest.response.BrandResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;
@Service
public class BrandServiceImpl extends AbstractDemoService implements BrandService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private BrandRepository brandRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public RESTResponse<BrandResponse> getBrand(Integer id) {
		Optional<Brand> optBrand = brandRepository.findById(id);
		if (!optBrand.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new BrandResponse(optBrand.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<BrandResponse>> getBrands(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Brand> brands = SpecificationBuilder.selectDistinctFrom(brandRepository).where(pageFilter)
				.findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) brands.getTotalElements(),
				brands.getContent().stream().map(BrandResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createBrand(CreateBrandRequest createBrandRequest) {
		try {
			checkInputParams(createBrandRequest);
			Brand newBrand = brandRepository.save(fromCreateBrandRequest(createBrandRequest));
//			contactRepository.flush();
			return new RESTResponse<>(Code.OK, BrandMessages.BRAND_CREATE_SUCCESS.toString(), newBrand.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("brands_unique")) {
				throw new DemoException(BrandError.BRAND_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editBrand(EditBrandRequest editBrandRequest) {
		try {
			checkInputParams(editBrandRequest);
			Brand editBrand = brandRepository.save(fromEditBrandRequest(editBrandRequest));
			brandRepository.flush();
			return new RESTResponse<>(Code.OK, BrandMessages.BRAND_EDIT_SUCCESS.toString(), editBrand.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteBrand(Integer id) {
		try {
			brandRepository.deleteById(id);
			brandRepository.flush();
			return new RESTResponse<>(Code.OK, BrandMessages.BRAND_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(BrandError.BRAND_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(BrandError.BRAND_DATA_INTEGRITY_VIOLATION.toString());
		}
	}

}

