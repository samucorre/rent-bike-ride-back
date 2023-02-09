package com.example.demo.service;
import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.rest.model.CreateBrandRequest;
import com.example.demo.rest.model.EditBrandRequest;
import com.example.demo.rest.response.BrandResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;


	public interface BrandService {

		/**ADAPTADO A BICICLETAS
		 * Obtiene un de BDD con el id indicado.
		 * 
		 * @param id el id del usuario de la BDD.
		 * @return el usuario cuyo id sea el pasado por parámetros.
		 */
		RESTResponse<BrandResponse> getBrand(Integer id);

		/**
		 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
		 * independientemente de las mayúsculas.
		 * 
		 * @param pageFilter filtro de la tabla
		 * @return usuarios que alguno de sus campos contenga la 'query'
		 *         independientemente de las mayúsculas.
		 * @since 0.0.5
		 */
		DataSourceRESTResponse<List<BrandResponse>> getBrands(AnyPageFilter pageFilter);

		/**
		 * Crea un nuevo usuario en la BDD.
		 * 
		 * @return el id del usuario creado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> createBrand(CreateBrandRequest createBrandRequest);

		/**
		 * Modifica un usuario en la BDD.
		 * 
		 * @return el id del usuario modificado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> editBrand(EditBrandRequest editBrandRequest);

		/**
		 * Elimina un usuario de la BDD.
		 * 
		 * @return el id del usuario eliminado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> deleteBrand(Integer id);
	}
