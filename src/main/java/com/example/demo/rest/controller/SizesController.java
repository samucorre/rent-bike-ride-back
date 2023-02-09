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
import com.example.demo.rest.model.CreateSizeRequest;
import com.example.demo.rest.model.EditSizeRequest;
import com.example.demo.rest.response.SizeResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.SizeService;

@RestController
@RequestMapping(SizesController.REQUEST_MAPPING)
public class SizesController {
	public static final String REQUEST_MAPPING = "sizes";

	private static final Logger LOGGER = LoggerFactory.getLogger(SizesController.class);

	@Autowired
	private SizeService sizeService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("getSize")
	@PreAuthorize("hasAnyAuthority('SIZES')")
	public RESTResponse<SizeResponse> getSize(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getSize in progress...");
		try {
			return sizeService.getSize(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getSize is finished...");
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
	@PostMapping(path = "/getSizes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('SIZES')")
	public @ResponseBody RESTResponse<List<SizeResponse>> getSizes(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getSizes in progress...");
		try {
			return sizeService.getSizes(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getSizes is finished...");
		}
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createSize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('SIZES')")
	public RESTResponse<Integer> createSize(@RequestBody CreateSizeRequest createSizeRequest) {
		LOGGER.info("createSize in progress...");
		try {
			return sizeService.createSize(createSizeRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createSize is finished...");
		}
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/editSize", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('SIZES')")
	public RESTResponse<Integer> editSize(@RequestBody EditSizeRequest editSizeRequest) {
		LOGGER.info("editSize in progress...");
		try {
			return sizeService.editSize(editSizeRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editSize is finished...");
		}
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("deleteSize")
	@PreAuthorize("hasAnyAuthority('SIZES')")
	public RESTResponse<Integer> deleteSize(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteSize in progress...");
		try {
			return sizeService.deleteSize(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteSize is finished...");
		}
	}
}

