package com.example.demo.service;
import java.util.List;

import com.borjaglez.springify.repository.filter.impl.AnyPageFilter;
import com.example.demo.rest.model.CreateSizeRequest;
import com.example.demo.rest.model.EditSizeRequest;
import com.example.demo.rest.response.SizeResponse;
import com.example.demo.rest.response.DataSourceRESTResponse;
import com.example.demo.rest.response.RESTResponse;


	public interface SizeService {

		/**ADAPTADO A BICICLETAS
		 * Obtiene un de BDD con el id indicado.
		 * 
		 * @param id el id del usuario de la BDD.
		 * @return el usuario cuyo id sea el pasado por parámetros.
		 */
		RESTResponse<SizeResponse> getSize(Integer id);

		/**
		 * Devuelve los usuarios que alguno de sus campos contenga la 'query'
		 * independientemente de las mayúsculas.
		 * 
		 * @param pageFilter filtro de la tabla
		 * @return usuarios que alguno de sus campos contenga la 'query'
		 *         independientemente de las mayúsculas.
		 * @since 0.0.5
		 */
		DataSourceRESTResponse<List<SizeResponse>> getSizes(AnyPageFilter pageFilter);

		/**
		 * Crea un nuevo usuario en la BDD.
		 * 
		 * @return el id del usuario creado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> createSize(CreateSizeRequest createSizeRequest);

		/**
		 * Modifica un usuario en la BDD.
		 * 
		 * @return el id del usuario modificado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> editSize(EditSizeRequest editSizeRequest);

		/**
		 * Elimina un usuario de la BDD.
		 * 
		 * @return el id del usuario eliminado.
		 * @since 0.0.5
		 */
		RESTResponse<Integer> deleteSize(Integer id);
	}
