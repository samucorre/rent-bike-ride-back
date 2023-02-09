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
import com.example.demo.rest.model.CreateBrandRequest;
import com.example.demo.rest.model.EditBrandRequest;
import com.example.demo.rest.response.BrandResponse;
import com.example.demo.rest.response.RESTResponse;
import com.example.demo.service.BrandService;

@RestController
@RequestMapping(BrandsController.REQUEST_MAPPING)
public class BrandsController {
	public static final String REQUEST_MAPPING = "brands";

	private static final Logger LOGGER = LoggerFactory.getLogger(BrandsController.class);

	@Autowired
	private BrandService brandService;

	/**
	 * Obtiene un contacto de BDD con el id indicado.
	 * 
	 * @param id el id del contacto de la BDD.
	 * @return el contacto cuyo id sea el pasado por parámetros.
	 */
	@GetMapping("getBrand")
	@PreAuthorize("hasAnyAuthority('BRANDS')")
	public RESTResponse<BrandResponse> getBrand(@RequestParam(value = "id") Integer id) {
		LOGGER.info("getBrand in progress...");
		try {
			return brandService.getBrand(id);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getBrand is finished...");
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
	@PostMapping(path = "/getBrands", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BRANDS')")
	public @ResponseBody RESTResponse<List<BrandResponse>> getBrands(@RequestBody AnyPageFilter pageFilter) {
		LOGGER.info("getBrands in progress...");
		try {
			return brandService.getBrands(pageFilter);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("getBrands is finished...");
		}
	}

	/**
	 * Llamada REST para crear un nuevo usuario en la BDD.
	 * 
	 * @return el id del usuario creado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/createBrand", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BRANDS')")
	public RESTResponse<Integer> createBrand(@RequestBody CreateBrandRequest createBrandRequest) {
		LOGGER.info("createBrand in progress...");
		try {
			return brandService.createBrand(createBrandRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("createBrand is finished...");
		}
	}

	/**
	 * Llamada REST para modificar un usuario en la BDD.
	 * 
	 * @return el id del usuario modificado.
	 * @since 0.0.5
	 */
	@PostMapping(path = "/editBrand", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PreAuthorize("hasAnyAuthority('BRANDS')")
	public RESTResponse<Integer> editBrand(@RequestBody EditBrandRequest editBrandRequest) {
		LOGGER.info("editBrand in progress...");
		try {
			return brandService.editBrand(editBrandRequest);
		} catch (DemoException e) {
			LOGGER.error(e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("editBrand is finished...");
		}
	}

	/**
	 * Elimina un usuario de la BDD.
	 * 
	 * @return el id del usuario eliminado.
	 * @since 0.0.5
	 */
	@DeleteMapping("deleteBrand")
	@PreAuthorize("hasAnyAuthority('BRANDS')")
	public RESTResponse<Integer> deleteBrand(@RequestParam(value = "id") Integer id) {
		LOGGER.info("deleteBrand in progress...");
		try {
			return brandService.deleteBrand(id);
		} catch (DemoException e) {
			LOGGER.error("({}) -> {}", id, e.getMessage());
			return new RESTResponse<>(e.getMessage());
		} finally {
			LOGGER.info("deleteBrand is finished...");
		}
	}
}

