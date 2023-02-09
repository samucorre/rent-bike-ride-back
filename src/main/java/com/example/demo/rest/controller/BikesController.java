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
import com.example.demo.rest.model.CreateBikeRequest;
import com.example.demo.rest.model.EditBikeRequest;
import com.example.demo.rest.response.BikeResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.BikeService;

@RestController
@RequestMapping(BikesController.REQUEST_MAPPING)
public class BikesController {
	public static final String REQUEST_MAPPING = "bikes";

	private static final Logger LOGGER = LoggerFactory.getLogger(BikesController.class);

	@Autowired
	private BikeService bikeService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("getBike")
	@PreAuthorize("hasAnyAuthority('BIKES')")
	public RESTResponse<BikeResponse> getBike(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getBike in progress...");
		try {
			return bikeService.getBike(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getBike is finished...");
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
	@PostMapping(path = "/getBikes", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BIKES')")
	public @ResponseBody RESTResponse<List<BikeResponse>> getBikes(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getBikes in progress...");
		try {
			return bikeService.getBikes(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getBikes is finished...");
		}
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createBike", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BIKES')")
	public RESTResponse<Integer> createBike(@RequestBody CreateBikeRequest createBikeRequest) {
		LOGGER.info("createBike in progress...");
		try {
			return bikeService.createBike(createBikeRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createBike is finished...");
		}
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/editBike", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BIKES')")
	public RESTResponse<Integer> editBike(@RequestBody EditBikeRequest editBikeRequest) {
		LOGGER.info("editBike in progress...");
		try {
			return bikeService.editBike(editBikeRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editBike is finished...");
		}
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("deleteBike")
	@PreAuthorize("hasAnyAuthority('BIKES')")
	public RESTResponse<Integer> deleteBike(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteBike in progress...");
		try {
			return bikeService.deleteBike(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteBike is finished...");
		}
	}
}

