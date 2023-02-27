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
		 * @param id el id de la bike de la BDD.
		 * @return la bike cuyo id sea el pasado por parámetros.
		 */
		RESTResponse<BikeResponse> getBike(Integer id);

		/**
		 * Devuelve las bikes que alguno de sus campos contenga la 'query'
		 * independientemente de las mayúsculas.
		 * 
		 * @param pageFilter filtro de la tabla
		 * @return bikes que alguno de sus campos contenga la 'query'
		 *         independientemente de las mayúsculas.
		 * @since 0.0.5
		 */
		DataSourceRESTResponse<List<BikeResponse>> getBikes(AnyPageFilter pageFilter);

		/**
		 * Crea una nueva bike en la BDD.
		 * 
		 * @return el id de l bike creada.
		 * 
		 */
		RESTResponse<Integer> createBike(CreateBikeRequest createBikeRequest);

		/**
		 * Modifica una bike en la BDD.
		 * 
		 * @return el id de la bike modificada.
		 * 
		 */
		RESTResponse<Integer> editBike(EditBikeRequest editBikeRequest);

		/**
		 * Elimina una bike de la BDD.
		 * 
		 * @return el id de la bike eliminada.
		 * 
		 */
		RESTResponse<Integer> deleteBike(Integer id);
	}
