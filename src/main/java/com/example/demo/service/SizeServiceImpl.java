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
import com.example.demo.entity.Size;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.SizeError;
import com.example.demo.entity.messages.SizeMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.SizeRepository;
import com.example.demo.rest.model.CreateSizeRequest;
import com.example.demo.rest.model.EditSizeRequest;
import com.example.demo.rest.response.SizeResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;

@Service
public class SizeServiceImpl extends AbstractDemoService implements SizeService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private SizeRepository sizeRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public RESTResponse<SizeResponse> getSize(Integer id) {
		Optional<Size> optSize = sizeRepository.findById(id);
		if (!optSize.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new SizeResponse(optSize.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<SizeResponse>> getSizes(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Size> size = SpecificationBuilder.selectDistinctFrom(sizeRepository).where(pageFilter).findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) size.getTotalElements(),
				size.getContent().stream().map(SizeResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createSize(CreateSizeRequest createSizeRequest) {
		try {
			checkInputParams(createSizeRequest);
			Size newSize = sizeRepository.save(fromCreateSizeRequest(createSizeRequest));
			sizeRepository.flush();
			return new RESTResponse<>(Code.OK, SizeMessages.SIZE_CREATE_SUCCESS.toString(), newSize.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("sizes_unique")) {
				throw new DemoException(SizeError.SIZE_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editSize(EditSizeRequest editSizeRequest) {
		try {
			checkInputParams(editSizeRequest);
			Size editSize = sizeRepository.save(fromEditSizeRequest(editSizeRequest));
			sizeRepository.flush();
			return new RESTResponse<>(Code.OK, SizeMessages.SIZE_EDIT_SUCCESS.toString(), editSize.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteSize(Integer id) {
		try {
			sizeRepository.deleteById(id);
			sizeRepository.flush();
			return new RESTResponse<>(Code.OK, SizeMessages.SIZE_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(SizeError.SIZE_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		
	} catch (DataIntegrityViolationException e) {
		throw new DemoException(SizeError.SIZE_DATA_INTEGRITY_VIOLATION.toString());
	}
}
	

}
