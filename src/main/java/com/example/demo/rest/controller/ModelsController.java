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
import com.example.demo.rest.model.CreateModelRequest;
import com.example.demo.rest.model.EditModelRequest;
import com.example.demo.rest.response.ModelResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.ModelService;

@RestController
@RequestMapping(ModelsController.REQUEST_MAPPING)
public class ModelsController {
	public static final String REQUEST_MAPPING = "models";

	private static final Logger LOGGER = LoggerFactory.getLogger(ModelsController.class);

	@Autowired
	private ModelService modelService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("getModel")
	@PreAuthorize("hasAnyAuthority('MODELS')")
	public RESTResponse<ModelResponse> getModel(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getModel in progress...");
		try {
			return modelService.getModel(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getModel is finished...");
		}
	}

	/**
	 * Llamada REST para obtener usuarios que alguno de sus campos contenga la
	 * 'query' independientemente de las mayúsculas.
	 * 
	 * @return usuarios que alguno de sus campos contenga la 'query'
	 *         independientemente de las mayúsculas.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/getModels", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('MODELS')")
	public @ResponseBody RESTResponse<List<ModelResponse>> getModels(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getModels in progress...");
		try {
			return modelService.getModels(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getModels is finished...");
		}
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createModel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('MODELS')")
	public RESTResponse<Integer> createModel(@RequestBody CreateModelRequest createModelRequest) {
		LOGGER.info("createModel in progress...");
		try {
			return modelService.createModel(createModelRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createModel is finished...");
		}
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/editModel", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('MODELS')")
	public RESTResponse<Integer> editModel(@RequestBody EditModelRequest editModelRequest) {
		LOGGER.info("editModel in progress...");
		try {
			return modelService.editModel(editModelRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editModel is finished...");
		}
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("deleteModel")
	@PreAuthorize("hasAnyAuthority('MODELS')")
	public RESTResponse<Integer> deleteModel(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteModel in progress...");
		try {
			return modelService.deleteModel(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteModel is finished...");
		}
	}
}