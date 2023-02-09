package com.example.demo.service;
import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.rest.model.CreateBikeRequest;
import com.example.demo.rest.model.EditBikeRequest;
import com.example.demo.rest.response.BikeResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;


	public interface BikeService {

		/**ADAPTADO A BICICLETAS
		 * Obtiene un de BDD con el id indicado.
		 * 
		 * @param id el id del usuario de la BDD.
		 * @return el usuario cuyo id sea el pasado por parámetros.
		 */
		RESTResponse<BikeResponse> getBike(Integer id);

		/**
		 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
		 * independientemente de las mayúsculas.
		 * 
		 * @param pageFilter filtro de la tabla
		 * @return usuarios que alguno de sus campos contenga la 'query'
		 *         independientemente de las mayúsculas.
		 * @since 0.0.5
		 */
		DataSourceRESTResponse<List<BikeResponse>> getBikes(AnyPageFilter pageFilter);

		/**
		 * Crea un nuevo usuario en la BDD.
		 * 
		 * @return el id del usuario creado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> createBike(CreateBikeRequest createBikeRequest);

		/**
		 * Modifica un usuario en la BDD.
		 * 
		 * @return el id del usuario modificado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> editBike(EditBikeRequest editBikeRequest);

		/**
		 * Elimina un usuario de la BDD.
		 * 
		 * @return el id del usuario eliminado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> deleteBike(Integer id);
	}
