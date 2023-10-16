package com.asignacion.espacios.service;

import java.util.List;

import com.asignacion.espacios.clases.Mensaje;
import com.asignacion.espacios.model.entity.Puesto;

public interface IPuestoService {
	// NATIVO=======================================================================================================================
		Puesto buscarPuestoId(Integer idPuesto);

		List<Puesto> listarTodos();

		void guardarPuesto(Puesto puesto);
		
		void guardarPuestoLista(List<Puesto> listaPuesto);

		Puesto guardarPuestoReturn(Puesto puesto);

		// REPOSITORY===================================================================================================================
		Puesto buscarPorCodigoAndModulo(String codigo, int idModulo);

		// METODOS Y LOGICA=============================================================================================================
		Mensaje guardarValidando(Puesto puesto);
		
	

}
