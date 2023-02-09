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
import com.example.demo.entity.Model;
import com.example.demo.entity.error.CommonError;
import com.example.demo.entity.error.ModelError;
import com.example.demo.entity.messages.ModelMessages;
import com.example.demo.exception.DemoException;
import com.example.demo.repository.ModelRepository;
import com.example.demo.rest.model.CreateModelRequest;
import com.example.demo.rest.model.EditModelRequest;
import com.example.demo.rest.response.ModelResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.rest.response.RESTResponse.Code;

@Service
public class ModelServiceImpl extends AbstractDemoService implements ModelService {

	/**
	 * Especificación JPA para {@link Contact}.
	 */
	@Autowired
	private ModelRepository modelRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public RESTResponse<ModelResponse> getModel(Integer id) {
		Optional<Model> optModel = modelRepository.findById(id);
		if (!optModel.isPresent()) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
		return new RESTResponse<>(new ModelResponse(optModel.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public DataSourceRESTResponse<List<ModelResponse>> getModels(AnyPageFilter pageFilter) {
		checkInputParams(pageFilter);
		Page<Model> model = SpecificationBuilder.selectDistinctFrom(modelRepository).where(pageFilter).findAll(pageFilter);
		return new DataSourceRESTResponse<>((int) model.getTotalElements(),
				model.getContent().stream().map(ModelResponse::new).collect(Collectors.toList()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> createModel(CreateModelRequest createModelRequest) {
		try {
			checkInputParams(createModelRequest);
			Model newModel = modelRepository.save(fromCreateModelRequest(createModelRequest));
			modelRepository.flush();
			return new RESTResponse<>(Code.OK, ModelMessages.MODEL_CREATE_SUCCESS.toString(), newModel.getId());
		} catch (DataIntegrityViolationException e) {
			if (e.getMessage().contains("models_unique")) {
				throw new DemoException(ModelError.MODEL_ALREADY_EXISTS.toString());
			}
			throw new DemoException(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> editModel(EditModelRequest editModelRequest) {
		try {
			checkInputParams(editModelRequest);
			Model editModel = modelRepository.save(fromEditModelRequest(editModelRequest));
			modelRepository.flush();
			return new RESTResponse<>(Code.OK, ModelMessages.MODEL_EDIT_SUCCESS.toString(), editModel.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public RESTResponse<Integer> deleteModel(Integer id) {
		try {
			modelRepository.deleteById(id);
			modelRepository.flush();
			return new RESTResponse<>(Code.OK, ModelMessages.MODEL_DELETE_SUCCESS.toString(), id);
		} catch (EmptyResultDataAccessException e) {
			if (e.getCause() instanceof ConstraintViolationException) {
				throw new DemoException(ModelError.MODEL_CONSTRAINT_VIOLATION.toString());
			} else {
				throw new DemoException(CommonError.ID_NOT_EXISTS.toString());
			}
		
	} catch (DataIntegrityViolationException e) {
		throw new DemoException(ModelError.MODEL_DATA_INTEGRITY_VIOLATION.toString());
	}
}
	

}